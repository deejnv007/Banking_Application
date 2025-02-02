package com.example.bank_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class AccountDto {
//
//	private Long id;
//	private String accountHolderName;
//	private double balance;
//}

public record AccountDto(Long id, String accountHolderName, double balance) {
}
