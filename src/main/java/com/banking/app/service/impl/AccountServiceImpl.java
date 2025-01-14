package com.banking.app.service.impl;

import com.banking.app.entity.Account;
import com.banking.app.entity.AccountDto;
import com.banking.app.mapper.AccountMapper;
import com.banking.app.repository.AccountRepository;
import com.banking.app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.banking.app.exception.AccountNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {

        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        AccountDto savedAccountDto = AccountMapper.mapToAccountDto(savedAccount);
        return savedAccountDto;
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()) {
            AccountDto accountDto = AccountMapper.mapToAccountDto(account.get());
            return accountDto;
        }
        throw new AccountNotFoundException("Account not present for the given id");
    }

    @Override
    public AccountDto deposit(Long id, double balance) {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account no. not found"));
        double newBalance = account.getBalance()+balance;
        account.setBalance(newBalance);
        Account savedAccount = accountRepository.save(account);
        AccountDto accountDto = AccountMapper.mapToAccountDto(savedAccount);
        return accountDto;
    }

    @Override
    public AccountDto withdraw(Long id, double balance) {
       Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account not found exception"));
       if(account.getBalance()<balance){
           throw new RuntimeException("Balance is not sufficient");
       }
        double newBalance = account.getBalance()-balance;
        account.setBalance(newBalance);
       Account savedAccount = accountRepository.save(account);
       AccountDto accountDto = AccountMapper.mapToAccountDto(savedAccount);
       return  accountDto;
    }

    @Override
    public List<AccountDto> getAllAccount() {
        List<Account> accountList = accountRepository.findAll();
        List<AccountDto> list = accountList.stream().map(account -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
        return list;
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account not found exception"));
        accountRepository.deleteById(id);
    }

    @Override
    public List<AccountDto> getAllAccountsByName(String name) {
        List<Account> accountList = accountRepository.findByAccountHolderName(name);
        List<AccountDto> accountDtoList = accountList.stream().map(account -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
        return accountDtoList;
    }

    @Override
    public List<AccountDto> getAccountsByNameAndBalance(String name, double balance) {
        List<Account> accountList = accountRepository.findByNameAndBalance(name,balance);
        List<AccountDto> accountDtoList = accountList.stream().map(account -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
        return accountDtoList;
    }


}
