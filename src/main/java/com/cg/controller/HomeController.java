package com.cg.controller;

import com.cg.model.Account;
import com.cg.model.Cart;
import com.cg.model.Sneaker;
import com.cg.model.dto.CartDTO;
import com.cg.service.Account.IAccountService;
import com.cg.service.Cart.ICartService;
import com.cg.service.Category.ICategoryService;
import com.cg.service.Sneaker.ISneakerService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/home")
@Controller
public class HomeController {

    @Autowired
    private ISneakerService sneakerService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private ICartService cartService;

    @GetMapping("")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("home/Home");
        modelAndView.addObject("sneakers", sneakerService.findAllByStatusIsTrueOrderByIdDesc());
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("lastP", sneakerService.findTopByOrderByIdDesc().get());
        return modelAndView;
    }

    @GetMapping("/managerProduct")
    public ModelAndView ManagerProduct(HttpSession session) {
        Boolean login = (Boolean) session.getAttribute("login");
        if (login == null) login = false;
        ModelAndView modelAndView = new ModelAndView("home/ManagerProduct");

        if (login) {
            Account account = (Account) session.getAttribute("account");
            if (account.isAdmin()) {
                modelAndView.addObject("sneakers", sneakerService.findAllByStatusIsTrueOrderByIdDesc());
                modelAndView.addObject("categories", categoryService.findAll());
                modelAndView.addObject("lastP", sneakerService.findTopByOrderByIdDesc().get());
            } else {
                modelAndView.setViewName("redirect:/home");
            }
        } else {
            modelAndView.setViewName("redirect:/home");
        }
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login(HttpSession session) {
        Boolean login = (Boolean) session.getAttribute("login");
        if (login == null) login = false;
        if (login) {
            return new ModelAndView("redirect:/home");
        } else {
            return new ModelAndView("login/Login", "account", new Account());
        }
    }

    @PostMapping("/login")
    public ModelAndView getLogin(@ModelAttribute("account") Account account,
                                 RedirectAttributes redirectAttributes, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Account> accountOptional = accountService.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (accountOptional.isPresent()) {
            if (accountOptional.get().isStatus()) {
                session.setAttribute("account", accountOptional.get());
                session.setAttribute("login", true);
                modelAndView.setViewName("redirect:/home");
            } else {
                redirectAttributes.addFlashAttribute("mess", "Your account is Locked");
                modelAndView.setViewName("redirect:/home/login");
            }
        } else {
            redirectAttributes.addFlashAttribute("mess", "Something is Wrong");
            modelAndView.setViewName("redirect:/home/login");
        }
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.removeAttribute("login");
        session.removeAttribute("account");
        return new ModelAndView("redirect:/home/login");
    }

    @GetMapping("/register")
    public ModelAndView register(HttpSession session) {
        Boolean login = (Boolean) session.getAttribute("login");
        if (login == null) login = false;
        if (login) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("login/Register", "account", new Account());
    }

    @PostMapping("/register")
    public ModelAndView getRegister(@Validated @ModelAttribute("account") Account account,
                                    @RequestParam("repassword") String repassword,
                                    BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("login/Register", "account", new Account());
        if (bindingResult.hasFieldErrors()) {
            modelAndView.addObject("mess", "Tham lam");
        } else {
            if (account.getPassword().equals(repassword)) {
                Optional<Account> account1 = accountService.findByUsername(account.getUsername());
                if (account1.isPresent()) {
                    modelAndView.addObject("mess", "This user name is exist");
                } else {
                    accountService.save(account);
                    modelAndView.addObject("mess", "Successful !!!");
                }
            } else {
                modelAndView.addObject("mess", "Retype password is not equal !");
            }
        }
        return modelAndView;
    }

    @GetMapping("/getPassword")
    public ModelAndView getPassword(HttpSession session) {
        Boolean login = (Boolean) session.getAttribute("login");
        if (login == null) login = false;
        if (login) {
            return new ModelAndView("redirect:/home");
        }
        return new ModelAndView("login/getPassword");
    }

    @PostMapping("/getPassword")
    public ModelAndView getPassworded(@RequestParam("username") String username,
                                      @RequestParam("email") String email, @RequestParam("fullName") String name) {
        Optional<Account> account = accountService.findByUsernameAndEmailAndName(username, email, name);
        ModelAndView modelAndView = new ModelAndView("login/getPassword");
        if (account.isPresent()) {
            modelAndView.addObject("mess", account.get().getPassword());
        } else {
            modelAndView.addObject("Something is wrong !", account.get().getPassword());
        }
        return modelAndView;
    }

    @GetMapping("/managerAccount")
    public ModelAndView managetAccount(HttpSession session) {
        Boolean login = (Boolean) session.getAttribute("login");
        if (login == null) login = false;
        if(login){
            Account account = (Account) session.getAttribute("account");
            if (account.isAdmin() || account.isUser()){
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("home/ManagerAccount");
                modelAndView.addObject("accounts", accountService.findAllByOrderByIdDesc());
                modelAndView.addObject("accountObj",account);
                return modelAndView;
            }else{
                return new ModelAndView("redirect:/home");
            }
        }
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/info/{id}")
    public ModelAndView getInfo(HttpSession session, @PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("home/Detail");
        modelAndView.addObject("prod", sneakerService.findById(id).get());
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("lastP", sneakerService.findTopByOrderByIdDesc().get());
        return modelAndView;
    }

    @GetMapping("/infoAccount/{id}")
    public ModelAndView getInfoAccount(HttpSession session, @PathVariable("id") Long id) {
        Boolean login = (Boolean) session.getAttribute("login");
        if (login == null) login = false;
        if (login) {
            ModelAndView modelAndView = new ModelAndView("home/accountInfo");
            modelAndView.addObject("categories", categoryService.findAll());
            modelAndView.addObject("lastP", sneakerService.findTopByOrderByIdDesc().get());
            modelAndView.addObject("account", accountService.findById(id).get());
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/home");
        }
    }

    @GetMapping("/category/{id}")
    public ModelAndView getSneakerByCategory(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("home/Home");
        modelAndView.addObject("sneakers", sneakerService.findAllByCategoryAndStatusIsTrue(id));
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("lastP", sneakerService.findTopByOrderByIdDesc().get());
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView getSearch(@RequestParam("keyword") String keyword) {
        ModelAndView modelAndView = new ModelAndView("home/Home");
        modelAndView.addObject("keyword", keyword);
        modelAndView.addObject("sneakers", sneakerService.findByKeyword(keyword));
        modelAndView.addObject("categories", categoryService.findAll());
        modelAndView.addObject("lastP", sneakerService.findTopByOrderByIdDesc().get());
        return modelAndView;
    }
}
