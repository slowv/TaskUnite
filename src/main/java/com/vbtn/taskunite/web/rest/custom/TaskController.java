package com.vbtn.taskunite.web.rest.custom;

import com.vbtn.taskunite.domain.*;
import com.vbtn.taskunite.service.UserService;
import com.vbtn.taskunite.service.custom.CustomUserInformationService;
import com.vbtn.taskunite.service.custom.task.CustomTaskCategoryService;
import com.vbtn.taskunite.service.custom.task.CustomTaskService;
import com.vbtn.taskunite.service.dto.TaskCategoryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
@RequestMapping("/task")
public class TaskController {
    @Autowired
    CustomTaskCategoryService customTaskCategoryService;
    @Autowired
    CustomUserInformationService customUserInformationService;
    @Autowired
    CustomTaskService customTaskService;
    @Autowired
    UserService userService;

    Logger logger = LoggerFactory.getLogger(TaskController.class);

    @GetMapping("/create/step1")
    public String createStep1(HttpSession session, Model model){
        Task taskInfo = new Task();
        if(null != session.getAttribute("step1")){
            HashMap step1 = (HashMap) session.getAttribute("step1");
            taskInfo = (Task) step1.get("taskInfo");
        }
        List<TaskCategoryDTO> taskCategories = customTaskCategoryService.findAll();
        model.addAttribute("taskCategories", taskCategories);
        model.addAttribute("taskInfo", taskInfo);
        return "task/create/step1";
    }

    @PostMapping(value = "/create/step1")
    public String updateStep1(HttpSession session, Task taskInfo){
        HashMap<String, Object> step1 = new HashMap<>();

        UserInformation userInformation = customUserInformationService.findOne(userService.getUserWithAuthorities().get().getId());
        taskInfo.setMaster(userInformation);
        customTaskService.save(taskInfo);

        step1.put("taskInfo", taskInfo);
        session.setAttribute("step1", step1);
        return "redirect:/task/create/step2";
    }

    @GetMapping("/create/step2")
    public String createStep2(HttpSession session, Model model){
        HashMap step1 = (HashMap) session.getAttribute("step1");
        List<UserInformation> taskers = new ArrayList<>();
        List<Double> prices = new ArrayList<>();
        List<Statistic> stats = new ArrayList<>();
        for (TaskerCategory t: ((Task) step1.get("taskInfo")).getTaskCategory().getTaskerCategories()) {
            taskers.add(t.getUser());
            prices.add(t.getPrice());
            stats.add(t.getUser().getStatistic());
        }
        model.addAttribute("taskers", taskers);
        model.addAttribute("stats", stats);
        model.addAttribute("prices", prices);
        if(null == step1){
            return "redirect:/task/create/step1";
        }
        model.addAttribute("taskInfo", (Task) ((HashMap) session.getAttribute("step1")).get("taskInfo"));
        return "task/create/step2";
    }

    @PostMapping(value = "/create/step2")
    public String updateStep2(HttpSession session, @RequestParam("tasker") Long taskerId ){
        HashMap step1 = (HashMap) session.getAttribute("step1");
        Task taskInfo = (Task)step1.get("taskInfo");

        UserInformation tasker = customUserInformationService.findOne(taskerId);
        taskInfo.setTasker(tasker);

        HashMap<String, Object> step2 = new HashMap<>();
        step2.put("taskInfo", taskInfo);
        session.setAttribute("step2", step2);
        return "redirect:/task/create/step3";
    }

    @GetMapping("/create/step3")
    public String createStep3(HttpSession session, Model model){
        HashMap step2 = (HashMap) session.getAttribute("step2");
        if(null == step2){
            return "redirect:/task/create/step1";
        }
        model.addAttribute("taskInfo", (Task) step2.get("taskInfo"));
        return "task/create/step3";
    }

    @PostMapping(value = "/create/step3")
    public String updateStep3(HttpSession session, @RequestParam("selected-date") Date selectedDate){
        HashMap step2 = (HashMap) session.getAttribute("step2");

        Task taskInfo = (Task)step2.get("taskInfo");
        taskInfo.setFrom(selectedDate.toInstant());

        HashMap<String, Object> step3 = new HashMap<>();
        step3.put("taskInfo", taskInfo);
        session.setAttribute("step3", step3);
        return "redirect:/task/create/step4";
    }

    @GetMapping("/create/step4")
    public String createStep4(HttpSession session, Model model){
        HashMap step3 = (HashMap) session.getAttribute("step3");
        if(null == step3){
            return "redirect:/task/create/step1";
        }
        Task taskInfo = (Task)step3.get("taskInfo");
        model.addAttribute("taskInfo", taskInfo);
        return "task/create/step4";
    }

    @RequestMapping(value = "/create/step4", method = RequestMethod.POST)
    public String updateStep4(HttpSession session, @RequestParam("description")String description){
        HashMap step3 = (HashMap) session.getAttribute("step3");
        Task taskInfo = (Task)step3.get("taskInfo");
        taskInfo.setDescription(description);
        taskInfo = customTaskService.save(taskInfo);
        if(null == taskInfo){
            return "redirect:/";
        }
        session.removeAttribute("step1");
        session.removeAttribute("step2");
        session.removeAttribute("step3");
        session.removeAttribute("step4");
        return "redirect:/room/" + taskInfo.getId();
    }
}
