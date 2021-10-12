package com.cg.repository;

import com.cg.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsernameAndPassword(String username, String password);

    Optional<Account> findByUsername(String username);

    Optional<Account> findByUsernameAndEmailAndName(String username, String email, String name);

    Iterable<Account> findAllByOrderByIdDesc();
}
