package com.example.accountsystem;

import com.example.accountsystem.models.Account;
import com.example.accountsystem.models.User;
import com.example.accountsystem.serices.AccountService;
import com.example.accountsystem.serices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private UserService userService;
    private AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
        User firstUser = new User("Simona", 29);
        userService.registerUser(firstUser);
        Account ac = new Account(new BigDecimal("1000"));
        ac.setUser(firstUser);
        firstUser.setAccounts(new HashSet<>() {{
            add(ac);
        }});


        accountService.withdrawMoney(new BigDecimal("1000"), (long) 1);
        accountService.transferMoney(new BigDecimal("50000"), (long) 3);

    }
}
