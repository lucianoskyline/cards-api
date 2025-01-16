package com.cards.util;

public enum CardsStatus {

    CREATED(1),
    CANCELLED(2),
    ACTIVE(3);

    private Integer value;

    public Integer getValue() {
        return this.value;
    }

    private CardsStatus(Integer value) {
        this.value = value;
    }

}
