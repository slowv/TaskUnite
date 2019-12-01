package com.vbtn.taskunite.web.rest.client;

import com.vbtn.taskunite.domain.Task;
import com.vbtn.taskunite.domain.TaskCategory;
import com.vbtn.taskunite.domain.Tasker;
import com.vbtn.taskunite.repository.custom.CustomTaskCategoryRepository;
import com.vbtn.taskunite.repository.custom.CustomTaskerRepository;
import com.vbtn.taskunite.service.TaskCategoryService;
import com.vbtn.taskunite.service.api.task.CustomTaskCategoryService;
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
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {
    @Autowired
    CustomTaskCategoryService customTaskCategoryService;
    @Autowired
    CustomTaskerService customTaskerService;

    Logger logger = LoggerFactory.getLogger(TaskController.class);

    @RequestMapping("/create/step1")
    public String createStep1(HttpSession session, Model model){
        Task taskInfo = new Task();
        if(null != session.getAttribute("step1")){
            HashMap step1 = (HashMap) session.getAttribute("step1");
            taskInfo = (Task) step1.get("taskInfo");
            model.addAttribute("estimateTime", step1.get("estimateTime"));
        }
        Page<TaskCategoryDTO> taskCategories = customTaskCategoryService.findAll(PageRequest.of(0, 5));
        model.addAttribute("taskCategories", taskCategories.getContent());
        model.addAttribute("taskInfo", taskInfo);
        return "task/create/step1";
    }

    @RequestMapping(value = "/create/step1", method = RequestMethod.POST)
    public String updateStep1(HttpSession session, Task taskInfo, @RequestParam("estimateTime") int estimateTime){
        HashMap<String, Object> step1 = new HashMap<>();
        step1.put("taskInfo", taskInfo);
        step1.put("estimateTime", estimateTime);
        session.setAttribute("step1", step1);
        return "redirect:/task/create/step2";
    }

    @RequestMapping("/create/step2")
    public String createStep2(HttpSession session, Model model){
        HashMap step1 = (HashMap) session.getAttribute("step1");
        if(null == step1){
            return "redirect:/task/create/step1";
        }
        model.addAttribute("taskInfo", (Task)step1.get("taskInfo"));
        return "task/create/step2";
    }

    @RequestMapping(value = "/create/step2", method = RequestMethod.POST)
    public String updateStep2(HttpSession session, Task taskInfo){
        session.setAttribute("step2", taskInfo);
        return "redirect:/task/create/step3";
    }

    @RequestMapping("/create/step3")
    public String createStep3(){
        return "task/create/step3";
    }

    @RequestMapping("/create/step4")
    public String createStep4(){
        return "task/create/step4";
    }
}
