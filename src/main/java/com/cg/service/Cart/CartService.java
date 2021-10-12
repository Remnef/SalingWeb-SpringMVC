package com.cg.service.Cart;

import com.cg.exception.DataInputException;
import com.cg.model.Account;
import com.cg.model.Cart;
import com.cg.model.Sneaker;
import com.cg.model.dto.CartDTO;
import com.cg.repository.ICartRepository;
import com.cg.repository.ISneakerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartService implements ICartService {

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private ISneakerRepository sneakerRepository;

    @Override
    public Iterable<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Iterable<CartDTO> findCartDTOByAccountIAndStatus(Long id, Boolean status) {
        return cartRepository.findCartDTOByAccountIAndStatus(id, status);
    }

    @Override
    public Iterable<CartDTO> findAllCartDTOByCart() {
        return cartRepository.findAllCartDTOByCart();
    }

    @Override
    public Optional<Cart> findBySneakerAndAccountAndStatus(Sneaker sneaker, Account account, Boolean status) {
        return cartRepository.findBySneakerAndAccountAndStatus(sneaker, account, status);
    }

    @Override
    public Optional<CartDTO> findCartDTOByCartId(Long id) {
        return cartRepository.findCartDTOByCartId(id);
    }

    @Override
    public int getSizeCart(Account account) {
        ArrayList<CartDTO> cartDTOS = (ArrayList<CartDTO>) cartRepository.findCartDTOByAccountIAndStatus(account.getId(), true);
        return cartDTOS.size();
    }

    @Override
    public void getBill(Account account) {
        List<CartDTO> carts = (ArrayList<CartDTO>) cartRepository.findCartDTOByAccountIAndStatus(account.getId(), true);
        for (CartDTO cartDTO : carts) {
            Optional<Cart> cart = cartRepository.findById(cartDTO.getId());
            if (cart.isPresent()) {
                Sneaker sneaker = sneakerRepository.findById(cartDTO.getId_sneaker()).get();
                sneaker.setQuantity(sneaker.getQuantity() - cartDTO.getQuantity());
                sneakerRepository.save(sneaker);
                cart.get().setStatus(false);
                cartRepository.save(cart.get());
            } else {
                throw new DataInputException("Not found");
            }
        }
    }
}
