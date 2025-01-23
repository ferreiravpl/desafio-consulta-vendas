package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleReportMinDTOProjection;

import java.time.LocalDate;

public class SaleReportMinDTO {

    private Long id;
    private LocalDate date;
    private Double amount;
    private String sellerName;

    public SaleReportMinDTO(SaleReportMinDTOProjection projection) {
        id = projection.getId();
        date = projection.getDate();
        amount = projection.getAmount();
        sellerName = projection.getName();
    }

    public SaleReportMinDTO(Long id, LocalDate date, Double amount, String sellerName) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.sellerName = sellerName;
    }

    public SaleReportMinDTO() {

    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSellerName() {
        return sellerName;
    }
}
