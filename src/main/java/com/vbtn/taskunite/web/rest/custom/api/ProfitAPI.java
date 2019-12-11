package com.vbtn.taskunite.web.rest.custom.api;

import com.vbtn.taskunite.domain.AdminProfit;
import com.vbtn.taskunite.repository.custom.CustomAdminProfitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/profit")
public class ProfitAPI {
    @Autowired
    CustomAdminProfitRepository adminProfitRepository;

    @GetMapping("/w")
    @ResponseBody
    public ResponseEntity profit1w() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.WEEK_OF_MONTH, -1);
        List<AdminProfit> profits = adminProfitRepository.findByCreatedAtAfterOrderByCreatedAtAsc(calendar.toInstant());
        HashMap<String, Object> response = new HashMap<>();
        response.put("profit", profits);
        List<String> dateStrings = profits.stream().map(x -> DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withZone(ZoneId.systemDefault()).format(x.getCreatedAt())).collect(Collectors.toList());
        response.put("dates", dateStrings);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/m")
    public ResponseEntity profit1m() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        List<AdminProfit> profits = adminProfitRepository.findByCreatedAtAfterOrderByCreatedAtAsc(calendar.toInstant());
        HashMap<String, Object> response = new HashMap<>();
        response.put("profit", profits);
        List<String> dateStrings = profits.stream().map(x -> DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withZone(ZoneId.systemDefault()).format(x.getCreatedAt())).collect(Collectors.toList());
        response.put("dates", dateStrings);
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
