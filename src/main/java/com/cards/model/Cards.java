package com.cards.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "cards")
@Data
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    private LocalDateTime registrationDate;

    @ManyToOne
    @JoinColumn(name = "account", referencedColumnName = "id")
    private Accounts account;

    private String cardId;

    private Integer cardType;

    private Integer cardStatus;

    private LocalDateTime expirationDate;

    private Integer deliveryStatus;

    private LocalDateTime deliveryDate;

    private String deliveryReturnReason;

    private String deliveryAddress;

    private String internalCode;

    private String trackingId;

    private String cvv;

}
