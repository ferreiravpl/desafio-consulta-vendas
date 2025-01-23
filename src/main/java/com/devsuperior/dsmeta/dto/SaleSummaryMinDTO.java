package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleSummaryMinDTOProjection;

public class SaleSummaryMinDTO {
    private String name;
    private Double total;

    public SaleSummaryMinDTO() {

    }

    public SaleSummaryMinDTO(Double total, String name) {
        this.total = total;
        this.name = name;
    }

    public SaleSummaryMinDTO(SaleSummaryMinDTOProjection projection) {
        name = projection.getName();
        total = projection.getTotal();
    }

    public String getName() {
        return name;
    }

    public Double getTotal() {
        return total;
    }
}
