package com.example.accountsystem.serices;

import com.example.accountsystem.models.Account;
import com.example.accountsystem.models.User;
import com.example.accountsystem.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public void withdrawMoney(BigDecimal amount, Long id) {
        Optional<Account> account = this.accountRepository.findById(id);
        if (account.isPresent() && amount.intValue() > 0) {
            Account account1 = account.get();
            if (account1.getBalance().compareTo(amount) >= 0) {
                account1.setBalance(account1.getBalance().subtract(amount));
                accountRepository.save(account1);
            }
        }


    }

    @Override
    public void transferMoney(BigDecimal amount, Long id) {
        Optional<Account> account = this.accountRepository.findById(id);
        if (account.isPresent() && amount.intValue() > 0) {
            Account account1 = account.get();
                account1.setBalance(account1.getBalance().add(amount));
                accountRepository.save(account1);
            }
        }




}
