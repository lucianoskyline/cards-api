package com.cards.repository;

import com.cards.model.Accounts;
import com.cards.model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;


@Repository
public interface CardsRepository extends JpaRepository<Cards, BigInteger> {

    Cards findByTrackingIdAndCardType(String trackingId, Integer cardType);

    Boolean existsByAccountAndCardTypeAndCardStatusIn(Accounts account, Integer cardType, List<Integer> cardStatus);

    Cards findByTrackingIdAndCardTypeAndAccount(String trackingId, Integer cardType, Accounts account);

}
