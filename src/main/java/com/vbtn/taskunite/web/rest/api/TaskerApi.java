package com.vbtn.taskunite.web.rest.api;

import com.vbtn.taskunite.domain.Tasker;
import com.vbtn.taskunite.domain.Tasker_;
import com.vbtn.taskunite.service.api.TaskerApiService;
import com.vbtn.taskunite.specification.SearchCriteria;
import com.vbtn.taskunite.specification.TaskerSpecification;
import com.vbtn.taskunite.util.ConstString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "/rest/tasker")
public class TaskerApi {
    @Autowired
    private TaskerApiService taskerApiService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Tasker> taskerList(
        @RequestParam(value = "schedule", required = false) String schedule,
        @RequestParam(value = "recommended", required = false) String categories,
        @RequestParam(value = "price", required = false) String price
    ) {
        Specification specification = Specification.where(null);
        // Lọc theo Thời gian sẽ là phần trường from và to của Schedule
        if (schedule != null) {
            Date dt = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dt);
            // Ngày hôm nay
            if (schedule.equals(ConstString.SCHEDULE_TODAY)) {
                calendar.add(Calendar.DATE, 0);
            // Trong 3 ngày tới
            }else if (schedule.equals(ConstString.SCHEDULE_3DAY)){
                calendar.add(Calendar.DATE, 3);
            // Trong tuần này tính từ ngày hiện tại đến chủ nhật là hết tuần chứ ko phải +7 ngày.
            }else if(schedule.equals(ConstString.SCHEDULE_WEEK)){
                int currentDay = calendar.get(Calendar.DAY_OF_WEEK);
                int leftDays= Calendar.SATURDAY - currentDay;
                calendar.add(Calendar.DATE, leftDays);
            // Chọn ngày cụ thể
            }else{
                calendar.setTime(new Date(schedule));
            }
            specification = specification.and(new TaskerSpecification(new SearchCriteria("jhi_to", "<=",calendar.getTime())))
                .and(new TaskerSpecification(new SearchCriteria("jhi_from", ">=",calendar.getTime())));
        }

        // Lọc theo Liên quan (recommended) là id category đã được tick ở step 1.
        if (categories != null){
            String[] categoryId = categories.split(",");
            if (categoryId.length > 0){
                specification = specification.and(TaskerSpecification.getTaskersByTaskCategory(Long.parseLong(categoryId[0])));
                for (int i = 1; i < categoryId.length; i++) {
                    specification = specification.or(TaskerSpecification.getTaskersByTaskCategory(Long.parseLong(categoryId[i])));
                }
            }
        }

        // Lọc theo giá
        if (price!= null) {
            String value = "";
            // Từ thấp đến cao
            if (price.equals(ConstString.PRICE_LOWEST_TO_HIGHEST)){
                value = "ASC";
            // Từ cao đến thấp
            }else if (price.equals(ConstString.PRICE_HIGHEST_TO_LOWEST)){
                value = "DESC";
            }
            specification = specification.and(new TaskerSpecification(new SearchCriteria("price", "order", value)));
        }

        // Lọc theo thời gian


        return taskerApiService.getList(specification);
    }

}
