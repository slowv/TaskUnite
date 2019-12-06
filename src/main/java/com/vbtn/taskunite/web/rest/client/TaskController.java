package com.vbtn.taskunite.web.rest.client;

import com.vbtn.taskunite.domain.*;
import com.vbtn.taskunite.repository.custom.CustomTaskCategoryRepository;
import com.vbtn.taskunite.repository.custom.CustomTaskerRepository;
import com.vbtn.taskunite.service.TaskCategoryService;
import com.vbtn.taskunite.service.api.account.CustomUserInformationService;
import com.vbtn.taskunite.service.api.task.CustomTaskCategoryService;
import com.vbtn.taskunite.service.api.task.CustomTaskService;
import com.vbtn.taskunite.service.api.task.CustomTaskerService;
import com.vbtn.taskunite.service.dto.TaskCategoryDTO;
import com.vbtn.taskunite.service.dto.TaskerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/task")
public class TaskController {
    @Autowired
    CustomTaskCategoryService customTaskCategoryService;
    @Autowired
    CustomTaskerService customTaskerService;
    @Autowired
    CustomUserInformationService customUserInformationService;
    @Autowired
    CustomTaskService customTaskService;

    Logger logger = LoggerFactory.getLogger(TaskController.class);

    @RequestMapping("/create/step1")
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

    @RequestMapping(value = "/create/step1", method = RequestMethod.POST)
    public String updateStep1(HttpSession session, Principal principal, Task taskInfo){
        HashMap<String, Object> step1 = new HashMap<>();
        if(null != principal){
            Optional<UserInformation> userInfo = customUserInformationService.findOneByUsername(principal.getName());
            if(userInfo.isPresent()){
                UserInformation userInformation = userInfo.get();
                taskInfo.setMaster(userInformation.getMaster());
            }
        }
        step1.put("taskInfo", taskInfo);
        session.setAttribute("step1", step1);
        return "redirect:/task/create/step2";
    }

    @RequestMapping("/create/step2")
    public String createStep2(HttpSession session, Model model){
        HashMap step1 = (HashMap) session.getAttribute("step1");
        List<Tasker> taskers = new ArrayList<>();
        for (TaskerCategory t: ((Task) step1.get("taskInfo")).getTaskCategory().getTaskerCategories()) {
            taskers.add(t.getTasker());
        }
        model.addAttribute("taskers", taskers);
        if(null == step1){
            return "redirect:/task/create/step1";
        }
        model.addAttribute("taskInfo", (Task) ((HashMap) session.getAttribute("step1")).get("taskInfo"));
        return "task/create/step2";
    }

    @RequestMapping(value = "/create/step2", method = RequestMethod.POST)
    public String updateStep2(HttpSession session, @RequestParam("tasker") int taskerId ){
        HashMap step1 = (HashMap) session.getAttribute("step1");
        Task taskInfo = (Task)step1.get("taskInfo");

        Optional<Tasker> tasker = customTaskerService.findOne(Long.valueOf(taskerId));
        if(tasker.isPresent()){
            taskInfo.setTasker(tasker.get());
        }

        HashMap<String, Object> step2 = new HashMap<>();
        step2.put("taskInfo", taskInfo);
        session.setAttribute("step2", step2);
        return "redirect:/task/create/step3";
    }

    @RequestMapping("/create/step3")
    public String createStep3(HttpSession session, Model model){
        HashMap step2 = (HashMap) session.getAttribute("step2");
        if(null == step2){
            return "redirect:/task/create/step1";
        }
        return "task/create/step3";
    }

    @RequestMapping(value = "/create/step3", method = RequestMethod.POST)
    public String updateStep3(HttpSession session, @RequestParam("selected-date") Date selectedDate){
        HashMap step2 = (HashMap) session.getAttribute("step2");
        Schedule schedule = new Schedule();
        schedule.setFrom(selectedDate.toInstant());
        schedule.setTo(selectedDate.toInstant().plus(2, ChronoUnit.HOURS));
        Task taskInfo = (Task)step2.get("taskInfo");
        taskInfo.setSchedule(schedule);

        HashMap<String, Object> step3 = new HashMap<>();
        step3.put("taskInfo", taskInfo);
        session.setAttribute("step3", step3);
        return "redirect:/task/create/step4";
    }

    @RequestMapping("/create/step4")
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
    public String updateStep4(HttpSession session, @RequestParam("phone")String phone, @RequestParam("description")String description){
        HashMap step3 = (HashMap) session.getAttribute("step3");
        Task taskInfo = (Task)step3.get("taskInfo");
        taskInfo.setDescription(description);
        taskInfo = customTaskService.save(taskInfo);
        if(null == taskInfo){
            // do something;
        }
        session.removeAttribute("step1");
        session.removeAttribute("step2");
        session.removeAttribute("step3");
        session.removeAttribute("step4");
        return "redirect:/room/" + taskInfo.getRoom().getId();
    }
}
