package com.cg.controller.api;

import com.cg.exception.DataInputException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.exception.UserNameExistsException;
import com.cg.model.Account;
import com.cg.model.dto.PasswordDTO;
import com.cg.service.Account.IAccountService;
import com.cg.service.Cart.ICartService;
import com.cg.service.Category.ICategoryService;
import com.cg.service.Sneaker.ISneakerService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/account")
public class AccountAPI {

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private ISneakerService sneakerService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ICartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccount(@PathVariable Long id) {
        Optional<Account> account = accountService.findById(id);
        if (account.isPresent()) {
            return new ResponseEntity<Account>(account.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> saveAccount(@Valid @RequestBody Account account, BindingResult bindingResult,
                                         @RequestParam("repassword") String repassword) {
        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Optional<Account> accountOptional = accountService.findByUsername(account.getUsername());
        if (accountOptional.isPresent()) {
            throw new UserNameExistsException("Username already exists");
        } else {
            if (account.getPassword().equals(repassword)) {
                try {
                    accountService.save(account);
                    return new ResponseEntity<Account>(account, HttpStatus.OK);
                } catch (DataIntegrityViolationException e) {
                    throw new DataInputException("Invalid customer creation information");
                }
            } else {
                throw new UserNameExistsException("Repassword is not equal");
            }
        }
    }

    @PostMapping("/setLock/{id}")
    public ResponseEntity<?> setLock(@PathVariable Long id) {
        Optional<Account> accountOptional = accountService.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setStatus(account.isStatus() ? false : true);
            accountService.save(account);
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/setMod/{id}")
    public ResponseEntity<?> setMod(@PathVariable Long id) {
        Optional<Account> accountOptional = accountService.findById(id);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setUser(account.isUser() ? false : true);
            accountService.save(account);
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/password")
    public ResponseEntity<?> changePass(@RequestBody PasswordDTO passwordDTO, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account != null) {
            if (passwordDTO.getNewPassword().equals(passwordDTO.getRetype())) {
                if (passwordDTO.getPassword().equals(account.getPassword())) {
                    account.setPassword(passwordDTO.getNewPassword());
                    accountService.save(account);
                    session.setAttribute("account", account);
                    return new ResponseEntity<>(HttpStatus.OK);
                } else {
                    throw new DataInputException("Password is fail");
                }
            } else {
                throw new DataInputException("retype is not equal");
            }
        } else {
            throw new ResourceNotFoundException("Tham lam");
        }
    }
}
