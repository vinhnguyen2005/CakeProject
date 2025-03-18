package com.example.cakeprj.dto.request;

public class CategoryIncomeDTO {
    private String categoryId;
    private String categoryName;
    private Double totalIncome;

    public CategoryIncomeDTO(String categoryId, String categoryName, Double totalIncome) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.totalIncome = totalIncome;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Double getTotalIncome() {
        return totalIncome;
    }

    public String getCategoryId() {
        return categoryId;
    }
}
