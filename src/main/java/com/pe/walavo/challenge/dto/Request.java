package com.pe.walavo.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Request {

    private String identifier;

    private String country;

    private String name;

    private String watchTv;

    private ChampionshipType typeChampionship;

    private List<PlayerDTO> players;

}
