package com.pe.walavo.challenge.infraestructure.dto;

public enum ChampionshipType {
    MALE("M"),
    FEMALE("F");

    private final String param;

    ChampionshipType(String param) {
        this.param = param;
    }

    public String getParam() {
        return this.param;
    }
}
