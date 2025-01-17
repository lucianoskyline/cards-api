package com.cards.service;

import com.cards.exception.BusinessException;
import com.cards.model.Cards;
import com.cards.repository.AccountsRepository;
import com.cards.repository.CardsRepository;
import com.cards.request.CardsCreateRequest;
import com.cards.response.CardsCreateResponse;
import com.cards.util.AccountsStatus;
import com.cards.util.CardsStatus;
import com.cards.util.CardsTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

@Service
public class CardsService {

    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private AccountsRepository accountsRepository;


    public CardsCreateResponse create(CardsCreateRequest request) {
        var account = accountsRepository.findByInternalCodeAndStatus(request.getAccount(), AccountsStatus.ACTIVE.getValue());
        if (account == null) {
            throw new BusinessException("Conta não encontrada");
        }

        var cardsStatus=Arrays.asList(CardsStatus.CREATED.getValue(), CardsStatus.ACTIVE.getValue());
        if (cardsRepository.existsByAccountAndCardTypeAndCardStatusIn(account,
                request.getCardType().getValue(),  cardsStatus)) {
            throw new BusinessException("Já existe um cartão cadastrado");
        }

        if (request.getCardType().getValue() == CardsTypes.VIRTUAL.getValue() && !cardsRepository.existsByAccountAndCardTypeAndCardStatusIn(account,
                CardsTypes.PHISICAL.getValue(), cardsStatus)) {
            throw new BusinessException("Não é possivel criar um cartão virtual no momento. Aguarde o recebimento do cartão físico");
        }

        var card = new Cards();
        card.setCardStatus(CardsStatus.CREATED.getValue());
        card.setCardType(request.getCardType().getValue());
        card.setAccount(account);
        card.setRegistrationDate(LocalDateTime.now());
        card.setInternalCode(UUID.randomUUID().toString());
        card.setTrackingId(UUID.randomUUID().toString());
        card = cardsRepository.save(card);
        var response = new CardsCreateResponse();
        response.setTrackingId(card.getTrackingId());
        return response;
    }

}
