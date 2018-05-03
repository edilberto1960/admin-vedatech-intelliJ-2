package com.vedatech.admin.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;


public class GetTransaction {



   // @Min(1)
   //@NotNull
	private BankAccount bank;

   // @NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd" )
	private Date initialDate;

    //@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd" )
	private Date finalDate;

	public BankAccount getBank() {
		return bank;
	}

	public void setBank(BankAccount bank) {
		this.bank = bank;
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}

	@Override
	public String toString() {
		return "GetTransaction{" +
				"bank=" + bank +
				", initialDate=" + initialDate +
				", finalDate=" + finalDate +
				'}';
	}
}
