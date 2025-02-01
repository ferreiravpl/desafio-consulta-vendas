package com.devsuperior.dsmeta.dto;

public class SaleSummaryMinDTO {
    private String sellerName;
    private Double total;

    public SaleSummaryMinDTO() {

    }

    public SaleSummaryMinDTO(String name, double total) {
        this.sellerName = name;
        this.total = total;
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }
}
