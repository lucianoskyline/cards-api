package com.cards.service;

import com.cards.exception.BusinessException;
import com.cards.repository.AccountsRepository;
import com.cards.repository.CardsRepository;
import com.cards.request.WebhooksCardsRequest;
import com.cards.request.WebhooksDeliveryRequest;
import com.cards.util.AccountsStatus;
import com.cards.util.CardsStatus;
import com.cards.util.CardsTypes;
import com.cards.util.WebhooksStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WebhooksService {

    @Value("${webhook.token.cards}")
    private String tokenCards;

    @Value("${webhook.token.delivery}")
    private String tokenDelivery;

    @Autowired
    private CardsRepository cardsRepository;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountsRepository accountsRepository;


    public void verifyDelivery(WebhooksDeliveryRequest request, String token) {
        validToken("DELIVERY", token);

        var card = cardsRepository.findByTrackingIdAndCardType(request.getTrackingId(), CardsTypes.PHISICAL.getValue());
        if (card == null) {
            throw new BusinessException("Cartão não encontrado");
        } else if (card.getDeliveryDate() != null) {
            throw new BusinessException("Cartão já entregue");
        }

        if (card.getCardStatus() == CardsStatus.CANCELLED.getValue()) {
            throw new BusinessException("Não é possivel atualizar um cartão cancelado");
        }

        if (request.getDeliveryStatus().getValue() == WebhooksStatus.DELIVERED.getValue()) {
            card.setCardStatus(CardsStatus.ACTIVE.getValue());
            card.setDeliveryStatus(WebhooksStatus.DELIVERED.getValue());
            card.setDeliveryAddress(request.getDeliveryAddress());
            card.setDeliveryDate(request.getDeliveryDate());
        } else if (request.getDeliveryStatus().getValue() == WebhooksStatus.CANCELLED.getValue()) {
            card.setCardStatus(CardsStatus.CANCELLED.getValue());
            card.setDeliveryStatus(WebhooksStatus.CANCELLED.getValue());
            card.setDeliveryReturnReason(request.getDeliveryReturnReason());
        }
        cardsRepository.save(card);
    }

    public void updateCard(WebhooksCardsRequest request, String token) {
        validToken("CARDS", token);

        var account = accountsRepository.findByInternalCodeAndStatus(request.getAccountId(), AccountsStatus.ACTIVE.getValue());
        if (account == null) {
            throw new BusinessException("Conta não encontrada");
        }

        var card = cardsRepository.findByTrackingIdAndCardTypeAndAccount(request.getCardId(), CardsTypes.VIRTUAL.getValue(), account);
        if (card == null) {
            throw new BusinessException("Cartão não encontrado");
        }

        if (card.getCardStatus() == CardsStatus.CANCELLED.getValue()) {
            throw new BusinessException("Não é possivel atualizar um cartão cancelado");
        }

        card.setExpirationDate(request.getExpirationDate());
        card.setCvv(request.getNextCvv());
        cardsRepository.save(card);
    }


    private void validToken(String type, String token) {
        if (token == null || token.isBlank()) {
            throw new BusinessException("Token não informado");
        }
        if ((type.equals("DELIVERY") && !token.equals(tokenDelivery)) ||
                (type.equals("CARDS") && !token.equals(tokenCards))) {
            logger.error("Token inválido {}", type);
            throw new BusinessException("Token inválido");
        }
    }

}
