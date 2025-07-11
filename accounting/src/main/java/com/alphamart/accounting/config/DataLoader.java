package com.alphamart.accounting.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.alphamart.accounting.database.ChartOfAccount;
import com.alphamart.accounting.database.ChartOfAccountRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ChartOfAccountRepository repository;

    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists
        if (repository.count() == 0) {
            // Add initial data
            repository.save(createChartOfAccount("coa-001", "001", "10004", "70910001"));
            repository.save(createChartOfAccount("coa-002", "001", "10004", "70910002"));
            repository.save(createChartOfAccount("coa-003", "001", "10004", "70910003"));
            repository.save(createChartOfAccount("coa-004", "001", "10004", "70910004"));
            repository.save(createChartOfAccount("coa-005", "001", "10004", "70910005"));
            repository.save(createChartOfAccount("coa-006", "001", "10005", "81210016"));
            repository.save(createChartOfAccount("coa-007", "001", "10005", "81210017"));
            repository.save(createChartOfAccount("coa-008", "001", "10005", "81210018"));
            repository.save(createChartOfAccount("coa-009", "001", "10005", "81210019"));
            repository.save(createChartOfAccount("coa-010", "001", "10005", "81210020"));
            
            System.out.println("Initial data loaded successfully!");
        }
    }

    private ChartOfAccount createChartOfAccount(String coaId, String company, String costCenter, String naturalAccount) {
        ChartOfAccount coa = new ChartOfAccount();
        coa.setCoaId(coaId);
        coa.setCompany(company);
        coa.setCostCenter(costCenter);
        coa.setNaturalAccount(naturalAccount);
        return coa;
    }
}
