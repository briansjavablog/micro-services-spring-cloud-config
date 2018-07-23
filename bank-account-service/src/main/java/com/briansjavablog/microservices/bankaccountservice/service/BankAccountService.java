package com.briansjavablog.microservices.bankaccountservice.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briansjavablog.microservices.bankaccountservice.config.Configuration;
import com.briansjavablog.microservices.bankaccountservice.model.BankAccount;
import com.briansjavablog.microservices.bankaccountservice.model.EnumAccountType;

@Service
public class BankAccountService {

	@Autowired
	private Configuration configuration;
	private Map<String, BankAccount> accountCache= new HashMap<>();
	
	
	@PostConstruct
	public void setupTestData() {
		
		BankAccount account1 = new BankAccount("A12345", "Joe Bloggs", EnumAccountType.CURRENT_ACCOUNT, BigDecimal.valueOf(1250.38));
		BankAccount account2 = new BankAccount("A67890", "Jane Doe", EnumAccountType.SAVINGS_ACCOUNT, BigDecimal.valueOf(1550.40));
		
		accountCache.put(account1.getAccountId(), account1);
		accountCache.put(account2.getAccountId(), account2);
	}
	
	
	/**
	 * Add account to cache
	 * 
	 * @param account
	 */
	public void createBankAccount(BankAccount account) {
		
		/* check balance is within allowed limits */
		if(account.getAccountBlance().doubleValue() >= configuration.getMinBalance() && 
		   account.getAccountBlance().doubleValue() <= configuration.getMaxBalance()) {
			
			accountCache.put(account.getAccountId(), account);
		}
		else {
			
			throw new InvalidAccountBalanceException("Bank Account Balance is outside of allowed thresholds");
		}		
	}
	
	
	/**
	 * Get account from cache
	 * 
	 * @param account
	 */
	public BankAccount retrieveBankAccount(String accountId) {
		
		return accountCache.get(accountId);
	}
	
}
