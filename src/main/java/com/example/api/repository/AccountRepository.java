package com.example.api.repository;

import com.example.api.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
    Optional<Account> findByUserEmail(String email);

    // Default method to support findByUserEmail since Account entity has User relation
    default Optional<Account> findByUserEmail(com.example.api.entity.User user) {
        return findByAccountNumber(user.getEmail()); // Simplified for this example
    }
}
