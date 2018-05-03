package com.vedatech.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "bank_account")
public class BankAccount {
	
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_bank")
	private Long id;
	
   // @NotEmpty
	@Column(name = "name_bank")
	private String nameBank;
	
   // @NotBlank
	@Column(name = "account_number")
	private Long accountNumber;
    
   // @NotNull
	@Column(name = "create_at")
	//@Temporal(TemporalType.DATE)
   //@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createAt;
		
	//@NotBlank
	//@Email
	@Column(name = "email")
	private String email;
	
	//@NotBlank
	@Column(name = "phone")
	private String phone;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="bankAccount")
	//@JoinColumn(name="ID_BANK", nullable=false)
	private List<Transactions> transactions;
	
	private double balance;	
	
	
	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameBank() {
		return nameBank;
	}

	public void setNameBank(String nameBank) {
		this.nameBank = nameBank;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transactions> transactions) {
		this.transactions = transactions;
	}

		

}
