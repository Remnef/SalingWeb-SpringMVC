package com.cg.controller.api;

import com.cg.exception.DataInputException;
import com.cg.exception.ResourceNotFoundException;
import com.cg.model.Account;
import com.cg.model.Cart;
import com.cg.model.Sneaker;
import com.cg.model.dto.CartDTO;
import com.cg.service.Account.IAccountService;
import com.cg.service.Cart.ICartService;
import com.cg.service.Category.ICategoryService;
import com.cg.service.Sneaker.ISneakerService;
import com.cg.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/api/sale")
public class SaleAPI {
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

    @PostMapping("/getCart/{id}")
    public ResponseEntity<?> getCart(
            @PathVariable("id") Long id,
            @RequestParam("quantity") int quantity,
            HttpSession session
    ) {
        Sneaker sneaker = sneakerService.findById(id).get();
        Account account = (Account) session.getAttribute("account");
        if (sneaker != null) {
            if (quantity <= 0) {
                throw new DataInputException("Get quantity more than 0");
            } else {
                Optional<Cart> cart = cartService.findBySneakerAndAccountAndStatus(sneaker, account, true);
                Cart cart1 = new Cart();
                if (!cart.isPresent()) {
                    cart1 = new Cart(sneaker, account, quantity, true);
                } else {
                    cart1 = cart.get();
                    cart1.setQuantity(cart1.getQuantity() + quantity);
                }
                cartService.save(cart1);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } else {
            throw new ResourceNotFoundException("Not found sneaker");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable Long id, HttpSession session) {
        Optional<CartDTO> cart = cartService.findCartDTOByCartId(id);
        if (cart.isPresent()) {
            cartService.deleteById(cart.get().getId());
            return new ResponseEntity<>(id, HttpStatus.OK);
        } else {
            throw new DataInputException("This cart is not exist");
        }
    }

    @GetMapping("/size-cart")
    public ResponseEntity<?> getSizeCart(HttpSession session) {
        Boolean login = (Boolean) session.getAttribute("login");

        if (login == null) {
            return new ResponseEntity<>(0, HttpStatus.OK);
        } else {
            Account account = (Account) session.getAttribute("account");

            int sizeCart = cartService.getSizeCart(account);

            return new ResponseEntity<>(sizeCart, HttpStatus.OK);
        }
    }

    @PostMapping("/getBill/{id}")
    public ResponseEntity<?> getBill(@PathVariable Long id) {
        Optional<Account> accountOptional = accountService.findById(id);
        if (accountOptional.isPresent()) {
            cartService.getBill(accountOptional.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
