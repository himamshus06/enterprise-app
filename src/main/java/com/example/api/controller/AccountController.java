package com.example.api.controller;

import com.example.api.entity.Account;
import com.example.api.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestParam BigDecimal deposit) {
        // In a real app, we'd get the User ID from the SecurityContext
        // For this capstone, we'll simulate a user ID or use a simple logic
        Account account = accountService.createAccount(1L, deposit);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/balance/{accountNumber}")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.getBalance(accountNumber));
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam String from, @RequestParam String to, @RequestParam BigDecimal amount) {
        accountService.transfer(from, to, amount);
        return ResponseEntity.ok("Transfer successful");
    }
}
