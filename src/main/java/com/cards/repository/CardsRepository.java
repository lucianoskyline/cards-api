package com.cards.repository;

import com.cards.model.Accounts;
import com.cards.model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;


@Repository
public interface CardsRepository extends JpaRepository<Cards, BigInteger> {

    Cards findByTrackingIdAndCardType(String trackingId, Integer cardType);

    Boolean existsByAccountAndCardTypeAndCardStatus(Accounts account, Integer cardType, Integer cardStatus);

}
