package com.cards.request;

import com.cards.util.CardsTypes;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CardsCreateRequest {

    @NotBlank(message = "Informe o identificador da conta")
    private String account;

    @NotNull(message = "Informe o tipo do cartão")
    private CardsTypes cardType;

}
