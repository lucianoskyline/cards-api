package com.cards.util;

import com.cards.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CardsTypes {

    PHISICAL(1),
    VIRTUAL(2);

    private Integer value;

    public Integer getValue() {
        return this.value;
    }

    private CardsTypes(Integer value) {
        this.value = value;
    }

    @JsonCreator
    public static CardsTypes forValue(Integer value) {
        for (CardsTypes type : CardsTypes.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new BusinessException("Tipo de cartão inválido");
    }

    @JsonValue
    public Integer toValue() {
        return this.value;
    }

}
