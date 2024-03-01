package com.example.accountsystem.serices;

import com.example.accountsystem.models.Account;
import com.example.accountsystem.models.User;
import com.example.accountsystem.repositories.AccountRepository;
import com.example.accountsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void registerUser(User user) {
        Optional <User> found = this.userRepository.
                findByUsername(user.getUsername());

        if (found.isEmpty()) {
            userRepository.save(user);
            Account account = new Account(new BigDecimal("0"), user);
            accountRepository.save(account);


        }
    }
}
