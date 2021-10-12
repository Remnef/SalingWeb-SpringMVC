package com.cg.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sneakers")
public class Sneaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String image;

    @NotNull
    @Min(0)
    private float price;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    @Min(0)
    private int quantity = 0;

    private boolean status = true;

    @ManyToOne
    @JoinColumn(name = "cateId")
    private Category category;

    public Sneaker(@NotNull String name, @NotNull String image, @NotNull @Min(0) float price, @NotNull String title, @NotNull String description, @NotNull @Min(0) int quantity, Category category) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.title = title;
        this.description = description;
        this.quantity = quantity;
        this.category = category;
    }
}
