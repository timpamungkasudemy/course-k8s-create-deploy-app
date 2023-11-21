package com.alphamart.accounting.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alphamart.accounting.database.ChartOfAccount;
import com.alphamart.accounting.service.ChartOfAccountService;

@RestController
@RequestMapping("/api/chart_of_accounts")
public class ChartOfAccountRestController {

	@Autowired
	private ChartOfAccountService service;

	@GetMapping
	public List<ChartOfAccount> getAllChartOfAccounts() {
		return service.getAllChartOfAccounts();
	}

	@GetMapping("/{coaId}")
	public Optional<ChartOfAccount> getChartOfAccountById(@PathVariable UUID coaId) {
		return service.getChartOfAccountById(coaId);
	}

	@GetMapping("/by_cost_center")
	public List<ChartOfAccount> getChartOfAccountsByCostCenter(@RequestParam String costCenter) {
		return service.getChartOfAccountsByCostCenter(costCenter);
	}

	@GetMapping("/by_natural_account")
	public List<ChartOfAccount> getChartOfAccountsByNaturalAccount(@RequestParam String naturalAccount) {
		return service.getChartOfAccountsByNaturalAccount(naturalAccount);
	}

	@PostMapping
	public ChartOfAccount createChartOfAccount(@RequestBody ChartOfAccount chartOfAccount) {
		return service.createChartOfAccount(chartOfAccount);
	}

	@PutMapping("/{coaId}")
	public ChartOfAccount updateChartOfAccount(@PathVariable UUID coaId,
			@RequestBody ChartOfAccount updatedChartOfAccount) {
		return service.updateChartOfAccount(coaId, updatedChartOfAccount);
	}

	@DeleteMapping("/{coaId}")
	public void deleteChartOfAccount(@PathVariable UUID coaId) {
		service.deleteChartOfAccount(coaId);
	}
}
