package com.example.api.service;

import com.example.api.entity.Account;
import com.example.api.entity.User;
import com.example.api.exception.InsufficientFundsException;
import com.example.api.repository.AccountRepository;
import com.example.api.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public Account createAccount(Long userId, BigDecimal initialDeposit) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String accountNumber = "ACC" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Account account = new Account(accountNumber, initialDeposit, user);
        return accountRepository.save(account);
    }

    public BigDecimal getBalance(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return account.getBalance();
    }

    @Transactional
    public void transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        Account from = accountRepository.findByAccountNumber(fromAccountNumber)
                .orElseThrow(() -> new RuntimeException("Source account not found"));
        Account to = accountRepository.findByAccountNumber(toAccountNumber)
                .orElseThrow(() -> new RuntimeException("Destination account not found"));

        if (from.getBalance().compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient funds for transfer");
        }

        from.setBalance(from.getBalance().subtract(amount));
        to.setBalance(to.getBalance().add(amount));

        accountRepository.save(from);
        accountRepository.save(to);
    }
}
