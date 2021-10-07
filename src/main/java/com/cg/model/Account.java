package com.cg.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String username;

    @Column(name = "pass")
    @NotBlank
    private String password;

    @JsonProperty
    private boolean isUser;

    private boolean isAdmin = false;

    private String name;

    @NotBlank
    private String email;

    private boolean status = true;

    public Account(String username, String password, boolean isUser, String name, String email) {
        this.username = username;
        this.password = password;
        this.isUser = isUser;
        this.name = name;
        this.email = email;
    }


}
