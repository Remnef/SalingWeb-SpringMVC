package com.cg.service.Cart;

import com.cg.model.Account;
import com.cg.model.Cart;
import com.cg.model.Sneaker;
import com.cg.model.dto.CartDTO;
import com.cg.service.IGeneralService;

import java.util.Optional;

public interface ICartService extends IGeneralService<Cart> {

    Iterable<CartDTO> findCartDTOByAccountIAndStatus(Long id, Boolean status);

    Iterable<CartDTO> findAllCartDTOByCart();

    Optional<Cart> findBySneakerAndAccountAndStatus(Sneaker sneaker, Account account, Boolean status);

    Optional<CartDTO> findCartDTOByCartId(Long id);

    int getSizeCart(Account account);

    void getBill(Account account);
}
