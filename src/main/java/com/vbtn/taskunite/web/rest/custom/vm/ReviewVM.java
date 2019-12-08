package com.vbtn.taskunite.web.rest.custom.vm;

import com.vbtn.taskunite.domain.Task;
import com.vbtn.taskunite.domain.UserInformation;

public class ReviewVM {
    private String content;
    private Double point;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }
}
