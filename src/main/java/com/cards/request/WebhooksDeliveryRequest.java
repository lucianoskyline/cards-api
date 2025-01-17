package com.cards.request;

import com.cards.util.WebhooksStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class WebhooksDeliveryRequest {

    @NotBlank(message = "Informe o identificador do cartão")
    private String trackingId;

    @NotNull(message = "Informe o status da entrega")
    private WebhooksStatus deliveryStatus;

    @NotNull(message = "Informe a data de entrega")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deliveryDate;

    private String deliveryReturnReason;

    private String deliveryAddress;

}
