package com.vbtn.taskunite.web.rest.custom.vm;

public class TaskerCategoryVM {
    public TaskerCategoryVM() {

    }

    private Integer confirm;

    private Integer haveTool;

    private Double price;

    private String description;

    private Long categoryId;

    public Integer getConfirm() {
        return confirm;
    }

    public void setConfirm(Integer confirm) {
        this.confirm = confirm;
    }

    public Integer getHaveTool() {
        return haveTool;
    }

    public void setHaveTool(Integer haveTool) {
        this.haveTool = haveTool;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
