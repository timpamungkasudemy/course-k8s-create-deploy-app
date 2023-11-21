package com.alphamart.accounting.database;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChartOfAccountRepository extends JpaRepository<ChartOfAccount, UUID> {

	List<ChartOfAccount> findByCostCenter(String costCenter);

	List<ChartOfAccount> findByNaturalAccount(String naturalAccount);

}
