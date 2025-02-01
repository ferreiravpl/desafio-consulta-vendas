package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportMinDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleReportMinDTO> findSalesReport(String minDate, String maxDate, String name, Pageable pageable) {
		LocalDate maxDateParsed = getMaxDate(maxDate);
		LocalDate minDateParsed = getMinDate(minDate, maxDateParsed);
		return repository.findSalesReport(minDateParsed, maxDateParsed, name, pageable);
	}

	public Page<SaleSummaryMinDTO> findSellerSalesSummary(String minDate, String maxDate, Pageable pageable) {
		LocalDate maxDateParsed = getMaxDate(maxDate);
		LocalDate minDateParsed = getMinDate(minDate, maxDateParsed);
		return repository.findSellerSalesSummary(minDateParsed, maxDateParsed, pageable);
	}

	private LocalDate getMaxDate(String maxDate) {
		if (maxDate.isEmpty()) {
			return LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}

		return LocalDate.parse(maxDate);
	}

	private LocalDate getMinDate(String minDate, LocalDate maxDate) {
		if (minDate.isEmpty()) {
			return maxDate.minusYears(1L);
		}

		return LocalDate.parse(minDate);
	}
}
