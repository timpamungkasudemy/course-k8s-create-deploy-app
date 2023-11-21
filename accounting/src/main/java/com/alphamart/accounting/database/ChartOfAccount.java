package com.alphamart.accounting.database;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "chart_of_accounts")
@Entity
public class ChartOfAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID coaId;

	private String company;

	private String costCenter;

	private String naturalAccount;

	public UUID getCoaId() {
		return coaId;
	}

	public void setCoaId(UUID coaId) {
		this.coaId = coaId;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getNaturalAccount() {
		return naturalAccount;
	}

	public void setNaturalAccount(String naturalAccount) {
		this.naturalAccount = naturalAccount;
	}

	@Override
	public String toString() {
		return "ChartOfAccount [coaId=" + coaId + ", company=" + company + ", costCenter=" + costCenter
				+ ", naturalAccount=" + naturalAccount + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(coaId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChartOfAccount other = (ChartOfAccount) obj;
		return Objects.equals(coaId, other.coaId);
	}

}
