package com.banking.app.controller;

import com.banking.app.entity.AccountDto;
import com.banking.app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;
    @Value("${build.version}")
    float version;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/createAccount")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
    }

    @GetMapping("/fetchAccount/{id}")
    public ResponseEntity<AccountDto> fetchAccountById(@PathVariable Long id){
        return new ResponseEntity<>(accountService.getAccountById(id),HttpStatus.OK);
    }

    @GetMapping("/deposit")
    public ResponseEntity<AccountDto> fetchAccountById(@RequestParam Long id,@RequestParam double balance){
        return new ResponseEntity<>(accountService.deposit(id,balance),HttpStatus.OK);
    }

    @PutMapping("/withdraw/{id}")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id ,
                                               @RequestBody Map<String,Double> request){
        double balance = request.get("amount");
        return new ResponseEntity<>(accountService.withdraw(id,balance),HttpStatus.OK);
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<AccountDto>> fetchAll(){
        System.out.println("build verson:"+version);
        return new ResponseEntity<>(accountService.getAllAccount(),HttpStatus.OK);
    }

    @DeleteMapping("/deleteAccount/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return new ResponseEntity<>("account deleted successfully",HttpStatus.OK);
    }

    @GetMapping("/getAllAccountsByName/{name}")
    public ResponseEntity<List<AccountDto>> getAllAccountsByName(@PathVariable String name){
        return new ResponseEntity<>(accountService.getAllAccountsByName(name),HttpStatus.OK);
    }

    @GetMapping("/getAllAccountsByName/{name}/{balance}")
    public ResponseEntity<List<AccountDto>> getAllAccountsByNameAndBalance(@PathVariable String name, @PathVariable double balance){
        return new ResponseEntity<>(accountService.getAccountsByNameAndBalance(name,balance),HttpStatus.OK);
    }
}
