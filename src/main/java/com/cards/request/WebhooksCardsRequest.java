package com.cards.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WebhooksCardsRequest {

    @NotBlank(message = "Informe o identificador da conta")
    private String accountId;

    @NotBlank(message = "Informe o identificador do cartão")
    private String cardId;

    @NotBlank(message = "Informe o CVV")
    private String nextCvv;

    @NotNull(message = "Informe a data de expiração")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expirationDate;

}
