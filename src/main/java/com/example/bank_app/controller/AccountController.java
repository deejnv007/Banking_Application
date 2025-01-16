package com.example.bank_app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank_app.dto.AccountDto;
import com.example.bank_app.service.AccountService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController {
  
	@Autowired
	private AccountService accountService;
	
	// Add Account REST API
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		AccountDto newAccountDto = accountService.createAccount(accountDto);
		return new ResponseEntity<>(newAccountDto, HttpStatus.CREATED);
	}
	
	// Get Account REST API
	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getAccount(@PathVariable("id") Long AccountId){
		AccountDto accountDto = accountService.getAccountById(AccountId);
		//return new ResponseEntity<>(accountDto, HttpStatus.CREATED);
		return ResponseEntity.ok(accountDto);
	}
	
	// Deposit REST API
	@PutMapping("/deposit/{id}")
	public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> obj){
		double amount  = obj.get("amount");
		AccountDto accountDto = accountService.deposit(id, amount);
		return ResponseEntity.ok(accountDto);
	}
	
	// Withdraw REST API
	@PutMapping("/withdraw/{id}")
	public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> obj2){
		AccountDto accountDto = accountService.withdraw(id, obj2.get("amount"));
		return ResponseEntity.ok(accountDto);
	}
	
	// Get All Accounts REST API
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAcconts(){
		List<AccountDto> accounts = accountService.getAllAccounts();
		return ResponseEntity.ok(accounts);
	}
	
	// Delete Account REST API
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
		accountService.deleteAccount(id);
		return ResponseEntity.ok("Account got deleted successfully...");
	}
}
