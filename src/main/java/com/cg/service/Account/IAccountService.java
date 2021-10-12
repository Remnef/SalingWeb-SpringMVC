package com.cg.service.Account;

import com.cg.model.Account;
import com.cg.service.IGeneralService;

import java.util.Optional;

public interface IAccountService extends IGeneralService<Account> {

    Optional<Account> findByUsernameAndPassword(String username, String password);

    Optional<Account> findByUsername(String username);

    Optional<Account> findByUsernameAndEmailAndName(String username, String email, String name);

    Iterable<Account> findAllByOrderByIdDesc();
}
