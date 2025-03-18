package com.example.cakeprj.dto.request;


public class MonthlyOrderDTO {
    private Integer year;
    private Integer month;
    private Long orderCount;

    public MonthlyOrderDTO(Integer year, Integer month, Long orderCount) {
        this.year = year;
        this.month = month;
        this.orderCount = orderCount;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Long getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Long orderCount) {
        this.orderCount = orderCount;
    }
}
