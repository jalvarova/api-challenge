package com.pe.walavo.challenge.dto;

public enum TYPE_CHAMPIONSHIP {
    MALE("M"),
    FEMALE("F");

    private final String param;

    TYPE_CHAMPIONSHIP(String param) {
        this.param = param;
    }

    public String getParam() {
        return this.param;
    }
}
