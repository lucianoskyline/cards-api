package com.cards.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountsCancelRequest {

    @NotBlank(message = "Informe o identificador da conta")
    private String internalCode;

    @NotBlank(message = "Informe o motivo do cancelamento")
    private String description;

}
