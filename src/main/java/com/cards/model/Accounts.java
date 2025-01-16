package com.cards.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Data
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    private LocalDateTime registrationDate;

    private String name;

    private String cpf;

    private String cellphone;

    private String email;

    private String address;

    private String number;

    private String district;

    private String city;

    private String state;

    private String postalcode;

    private String internalCode;

    private Integer status;

    private LocalDateTime cancelDate;

    private String cancelDescription;

}
