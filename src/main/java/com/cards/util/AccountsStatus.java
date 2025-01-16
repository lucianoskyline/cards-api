package com.cards.util;

public enum AccountsStatus {

    ACTIVE(1),
    CANCELLED(2);

    private Integer value;

    public Integer getValue() {
        return this.value;
    }

    private AccountsStatus(Integer value) {
        this.value = value;
    }

}
