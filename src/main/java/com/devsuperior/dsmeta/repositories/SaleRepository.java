package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleReportMinDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleReportMinDTO(s.id, s.date, s.amount, s.seller.name) " +
            "FROM Sale s " +
            "WHERE LOWER(s.seller.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
            "AND s.date BETWEEN :minDate AND :maxDate")
    Page<SaleReportMinDTO> findSalesReport(@Param("minDate") LocalDate minDate,
                                           @Param("maxDate") LocalDate maxDate,
                                           @Param("name") String name,
                                           Pageable pageable);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleSummaryMinDTO(s.seller.name, SUM(s.amount)) " +
            "FROM Sale s " +
            "WHERE s.date BETWEEN :minDate AND :maxDate " +
            "GROUP BY s.seller.name")
    Page<SaleSummaryMinDTO> findSellerSalesSummary(@Param("minDate") LocalDate minDate,
                                                   @Param("maxDate") LocalDate maxDate,
                                                   Pageable pageable);
}

