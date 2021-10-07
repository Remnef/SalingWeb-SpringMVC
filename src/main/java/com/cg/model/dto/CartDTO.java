package com.cg.model.dto;

import com.cg.model.Cart;
import com.cg.model.Sneaker;
import com.cg.service.Account.IAccountService;
import com.cg.service.Cart.ICartService;
import com.cg.service.Sneaker.ISneakerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    @Autowired
    ISneakerService sneakerService;

    @Autowired
    IAccountService accountService;

    @Autowired
    ICartService cartService;

    private Long id;

    private Long id_sneaker;

    private String image;

    private String name;

    private float price;

    private int quantity;

    private float amount;

    private Long id_account;

    private Date time;

    private Boolean status;


    public CartDTO(Long id, Long id_sneaker, String image, String name, float price, int quantity, Long id_account, Boolean status) {
        this.id = id;
        this.id_sneaker = id_sneaker;
        this.image = image;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.id_account = id_account;
        this.status = status;
        this.amount = quantity * price;
    }

    public CartDTO(Long id, Long id_sneaker, String image, String name, float price, int quantity, Long id_account, Date time, Boolean status) {
        this.id = id;
        this.id_sneaker = id_sneaker;
        this.image = image;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.amount = quantity * price;
        this.id_account = id_account;
        this.time = time;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getAmount() {
        return quantity * price;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getTime() {
        return new SimpleDateFormat("yyyy:MM:dd HH:mm").format(time);
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Long getId_sneaker() {
        return id_sneaker;
    }

    public void setId_sneaker(Long id_sneaker) {
        this.id_sneaker = id_sneaker;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId_account() {
        return id_account;
    }

    public void setId_account(Long id_account) {
        this.id_account = id_account;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Cart toCart() {
        Sneaker sneaker = sneakerService.findById(this.id_sneaker).get();
        return new Cart(this.id, sneaker,
                accountService.findById(this.id_account).get(), this.quantity, this.status);
    }
}
