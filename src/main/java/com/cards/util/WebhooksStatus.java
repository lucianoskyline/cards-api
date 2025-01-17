package com.cards.util;

import com.cards.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum WebhooksStatus {

    DELIVERED(1),
    CANCELLED(2);

    private Integer value;

    public Integer getValue() {
        return this.value;
    }

    private WebhooksStatus(Integer value) {
        this.value = value;
    }

    @JsonCreator
    public static WebhooksStatus forValue(Integer value) {
        for (WebhooksStatus w : WebhooksStatus.values()) {
            if (w.getValue().equals(value)) {
                return w;
            }
        }
        throw new BusinessException("Status inválido");
    }

    @JsonValue
    public Integer toValue() {
        return this.value;
    }

}
