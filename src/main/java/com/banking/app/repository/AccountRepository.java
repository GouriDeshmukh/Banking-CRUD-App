package com.banking.app.repository;

import com.banking.app.entity.Account;
import com.banking.app.entity.AccountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findByAccountHolderName(String name);

    @Query("select a from Account a where a.accountHolderName = :name and a.balance > :balance")
    List<Account> findByNameAndBalance(@Param("name") String name, @Param("balance") double balance);
}

