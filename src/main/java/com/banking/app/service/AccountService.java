package com.banking.app.service;

import com.banking.app.entity.Account;
import com.banking.app.entity.AccountDto;

import java.util.List;

public interface AccountService {

     AccountDto createAccount(AccountDto accountDto);

     AccountDto getAccountById(Long id);

     AccountDto deposit(Long id, double balance);

     AccountDto withdraw(Long id, double balance);

     List<AccountDto> getAllAccount();

     void deleteAccount(Long id);

     List<AccountDto> getAllAccountsByName(String name);

     List<AccountDto> getAccountsByNameAndBalance(String name,double balance);
}
