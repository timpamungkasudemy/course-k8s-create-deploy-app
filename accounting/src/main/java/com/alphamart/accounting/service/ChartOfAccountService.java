package com.alphamart.accounting.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alphamart.accounting.database.ChartOfAccount;
import com.alphamart.accounting.database.ChartOfAccountRepository;

@Service
public class ChartOfAccountService {

	@Autowired
	private ChartOfAccountRepository repository;

	public List<ChartOfAccount> getAllChartOfAccounts() {
		return repository.findAll();
	}

	public Optional<ChartOfAccount> getChartOfAccountById(UUID coaId) {
		return repository.findById(coaId);
	}

	public List<ChartOfAccount> getChartOfAccountsByCostCenter(String costCenter) {
		return repository.findByCostCenter(costCenter);
	}

	public List<ChartOfAccount> getChartOfAccountsByNaturalAccount(String naturalAccount) {
		return repository.findByNaturalAccount(naturalAccount);
	}

	public ChartOfAccount createChartOfAccount(ChartOfAccount chartOfAccount) {
		return repository.save(chartOfAccount);
	}

	public ChartOfAccount updateChartOfAccount(UUID coaId, ChartOfAccount updatedChartOfAccount) {
		if (repository.existsById(coaId)) {
			updatedChartOfAccount.setCoaId(coaId);
			return repository.save(updatedChartOfAccount);
		} else {
			throw new IllegalArgumentException("Chart of Account with ID " + coaId + " not found.");
		}
	}

	public void deleteChartOfAccount(UUID coaId) {
		repository.deleteById(coaId);
	}
}
