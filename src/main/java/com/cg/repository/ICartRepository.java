package com.cg.repository;

import com.cg.model.Account;
import com.cg.model.Cart;
import com.cg.model.Sneaker;
import com.cg.model.dto.CartDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ICartRepository extends JpaRepository<Cart, Long> {

//    @Query("SELECT NEW com.cg.model.dto.CartDTO (c.sneaker.id, c.sneaker.name, c.sneaker.price, c.quantity, c.time) FROM Cart c where c.id = ?1")
//    Optional<CartDTO> findCartDTOById(Long id);

//    Iterable<Cart> findAllByAccount_IdAndStatusIsTrue(Long id);

    Optional<Cart> findBySneakerAndAccountAndStatus(Sneaker sneaker, Account account, Boolean status);

    @Query("SELECT new com.cg.model.dto.CartDTO " +
            "(c.id, c.sneaker.id,c.sneaker.image, c.sneaker.name, c.sneaker.price" +
            ", c.quantity, c.account.id, c.status)" +
            " from Cart c where c.account.id = ?1 and c.status = ?2")
    Iterable<CartDTO> findCartDTOByAccountIAndStatus(Long id, Boolean status);

    @Query("SELECT new com.cg.model.dto.CartDTO " +
            "(c.id, c.sneaker.id, c.sneaker.image,c.sneaker.name, c.sneaker.price" +
            ", c.quantity, c.account.id,c.time, c.status)" +
            " from Cart c")
    Iterable<CartDTO> findAllCartDTOByCart();

    @Query("SELECT new com.cg.model.dto.CartDTO " +
            "(c.id, c.sneaker.id, c.sneaker.image,c.sneaker.name, c.sneaker.price" +
            ", c.quantity, c.account.id, c.status)" +
            " from Cart c where c.id = ?1")
    Optional<CartDTO> findCartDTOByCartId(Long id);

}
