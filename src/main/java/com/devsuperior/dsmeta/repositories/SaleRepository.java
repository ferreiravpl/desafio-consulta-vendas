package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleReportMinDTOProjection;
import com.devsuperior.dsmeta.projections.SaleSummaryMinDTOProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(nativeQuery = true, value = "SELECT tb_sales.id AS id, tb_sales.date AS date, tb_sales.amount AS amount, tb_seller.name AS name " +
            "FROM tb_sales " +
            "JOIN tb_seller " +
            "ON tb_sales.seller_id = tb_seller.id " +
            "WHERE tb_seller.name ILIKE CONCAT('%', :name, '%') " +
            "AND tb_sales.date " +
            "BETWEEN :minDate AND :maxDate")
    List<SaleReportMinDTOProjection> findSalesReport(LocalDate minDate, LocalDate maxDate, String name);

    @Query(nativeQuery = true, value = "SELECT tb_seller.name AS name, SUM(tb_sales.amount) AS total " +
            "FROM tb_sales " +
            "JOIN tb_seller " +
            "ON tb_sales.seller_id = tb_seller.id " +
            "WHERE tb_sales.date " +
            "BETWEEN :minDate AND :maxDate " +
            "GROUP BY tb_seller.name")
    List<SaleSummaryMinDTOProjection> findSellerSalesSummary(LocalDate minDate, LocalDate maxDate);
}
