package com.example.bank_app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.bank_app.exception.AccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank_app.dto.AccountDto;
import com.example.bank_app.entity.Account;
import com.example.bank_app.mapper.AccountMapper;
import com.example.bank_app.repository.AccountRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account  = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new AccountException("Account doesn't exists..."));
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new AccountException("Account doesn't exists..."));
		double totalAmount = account.getBalance()+amount;
		account.setBalance(totalAmount);
		accountRepository.save(account);
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(() -> new AccountException("Account doesn't exists..."));
		if(account.getBalance()<amount) {
			throw new RuntimeException("Insufficient balance...");
		}
		
		double remainingAmount = account.getBalance()-amount;
		account.setBalance(remainingAmount);
		accountRepository.save(account);
		return AccountMapper.mapToAccountDto(account);
	}

	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
	    return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
		        .collect(Collectors.toList());
	}

	@Override
	public void deleteAccount(Long id) {
		         accountRepository
				.findById(id)
				.orElseThrow(() -> new AccountException("Account doesn't exists..."));
		accountRepository.deleteById(id);
		
	}
	
	
}
