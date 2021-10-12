package com.cg.controller;

import com.cg.model.Account;
import com.cg.model.dto.CartDTO;
import com.cg.service.Account.IAccountService;
import com.cg.service.Cart.ICartService;
import com.cg.service.Category.ICategoryService;
import com.cg.service.Sneaker.ISneakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private ICartService cartService;

    @GetMapping("/cartUser/{id}")
    public ModelAndView cartUser(HttpSession session, @PathVariable Long id) {
        Boolean login = (Boolean) session.getAttribute("login");
        if (login == null) login = false;
        if (login) {
            ArrayList<CartDTO> cartDTOS = (ArrayList<CartDTO>) cartService.findCartDTOByAccountIAndStatus(id, true);
            float allAmount = 0;
            for (CartDTO cartDTO : cartDTOS) {
                allAmount += cartDTO.getAmount();
            }
            ModelAndView modelAndView = new ModelAndView("home/Cart");
            modelAndView.addObject("cartList", cartDTOS);
            modelAndView.addObject("allAmount", allAmount);
            modelAndView.addObject("vat", allAmount / 10);
            modelAndView.addObject("getBill", allAmount + allAmount / 10);
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/home");
        }
    }

    @GetMapping("/cartAdmin")
    public ModelAndView cartAdmin(HttpSession session) {
        Boolean login = (Boolean) session.getAttribute("login");
        if (login == null) login = false;
        if (login) {
            Account account = (Account) session.getAttribute("account");
            if (account.isAdmin()) {
                List<CartDTO> cartDTOS = (ArrayList<CartDTO>) cartService.findAllCartDTOByCart();
                return new ModelAndView("/home/saleList", "cartList", cartDTOS);
            } else {
                return new ModelAndView("redirect:/home");
            }
        }
        return new ModelAndView("redirect:/home");
    }


}
