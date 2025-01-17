package com.cards.service;

import com.cards.model.Accounts;
import com.cards.model.Cards;
import com.cards.repository.AccountsRepository;
import com.cards.repository.CardsRepository;
import com.cards.request.CardsCreateRequest;
import com.cards.util.AccountsStatus;
import com.cards.util.CardsStatus;
import com.cards.util.CardsTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CardsServiceTest {

    @InjectMocks
    private CardsService cardsService;

    @Mock
    private CardsRepository cardsRepository;

    @Mock
    private AccountsRepository accountsRepository;


    @Test
    @DisplayName("Cadastrando cartão")
    public void testCreate() {
        var account = new Accounts();
        account.setInternalCode(UUID.randomUUID().toString());
        account.setStatus(AccountsStatus.ACTIVE.getValue());

        when(accountsRepository.findByInternalCodeAndStatus(account.getInternalCode(), AccountsStatus.ACTIVE.getValue())).thenReturn(account);

        when(cardsRepository.existsByAccountAndCardTypeAndCardStatus(account,
                CardsTypes.PHISICAL.getValue(), CardsStatus.CREATED.getValue())).thenReturn(false);

        var mockCard = new Cards();
        mockCard.setCardStatus(CardsStatus.CREATED.getValue());
        mockCard.setAccount(account);
        mockCard.setCardType(CardsTypes.PHISICAL.getValue());
        mockCard.setTrackingId(UUID.randomUUID().toString());

        when(cardsRepository.save(any(Cards.class))).thenReturn(mockCard);

        var request = new CardsCreateRequest();
        request.setCardType(CardsTypes.PHISICAL);
        request.setAccount(account.getInternalCode());

        var response = cardsService.create(request);
        Assertions.assertNotNull(response.getTrackingId());
    }

}
