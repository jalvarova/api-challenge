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

    private TYPE_CHAMPIONSHIP type_championship;

    private List<PlayerDTO> players;

}
