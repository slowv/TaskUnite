package com.vbtn.taskunite.web.rest.custom.vm;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class TaskerVM {
    private String image;
    private String description;
    private Long city;

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public Long getDistrict() {
        return district;
    }

    public void setDistrict(Long district) {
        this.district = district;
    }

    private Long district;
    private String address;

    @Min(1)
    @Max(31)
    private Integer bday;
    @Min(1)
    @Max(12)
    private Integer bmonth;
    @Min(1900)
    @Max(2000)
    private Integer byear;

    public Integer getBday() {
        return bday;
    }

    public void setBday(Integer bday) {
        this.bday = bday;
    }

    public Integer getBmonth() {
        return bmonth;
    }

    public void setBmonth(Integer bmonth) {
        this.bmonth = bmonth;
    }

    public Integer getByear() {
        return byear;
    }

    public void setByear(Integer byear) {
        this.byear = byear;
    }

    public TaskerVM() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
