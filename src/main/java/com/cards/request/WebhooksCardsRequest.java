package com.cards.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WebhooksCardsRequest {

    private String accountId;

    private String cardId;

    private String nextCvv;

    private LocalDateTime expirationDate;

}
