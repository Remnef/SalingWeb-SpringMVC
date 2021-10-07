package com.cg.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "sneaker_id")
    private Sneaker sneaker;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private int quantity;

    Date time = Calendar.getInstance().getTime();

    private Boolean status;

    public Cart(Sneaker sneaker, Account account, int quantity, Boolean status) {
        this.sneaker = sneaker;
        this.account = account;
        this.quantity = quantity;
        this.status = status;
    }

    public Cart(long id, Sneaker sneaker, Account account, int quantity, Boolean status) {
        this.id = id;
        this.sneaker = sneaker;
        this.account = account;
        this.quantity = quantity;
        this.status = status;
    }
}
