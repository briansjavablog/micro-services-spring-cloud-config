package com.briansjavablog.microservices.bankaccountservice.rest;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.briansjavablog.microservices.bankaccountservice.model.BankAccount;
import com.briansjavablog.microservices.bankaccountservice.service.BankAccountService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BankAccountController {

	@Autowired
	public BankAccountService bankAccountService; 
	
	
	@PostMapping("/bank-account")
	public ResponseEntity<?> createBankAccount(@RequestBody BankAccount bankAccount, HttpServletRequest request) throws URISyntaxException {
		
		bankAccountService.createBankAccount(bankAccount);
		
		log.info("created bank account {}", bankAccount);
		
		URI uri = new URI(request.getRequestURL() + "bank-account/" + bankAccount.getAccountId());
		
		return ResponseEntity.created(uri).build();				
	}
	
	
	@GetMapping("/bank-account/{accountId}")
	public ResponseEntity<BankAccount> getBankAccount(@PathVariable("accountId") String accountId){
		
		BankAccount account = bankAccountService.retrieveBankAccount(accountId);
		
		log.info("retrieved bank account {}", account);
		
		return ResponseEntity.ok(account);				
	}
	
}
