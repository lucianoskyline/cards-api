package com.cards.service;

import com.cards.exception.BusinessException;
import com.cards.repository.CardsRepository;
import com.cards.request.WebhooksDeliveryRequest;
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
