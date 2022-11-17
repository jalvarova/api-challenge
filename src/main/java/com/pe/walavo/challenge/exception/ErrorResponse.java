package com.pe.walavo.challenge.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(value = {"message", "exception", "error", "errorType", "status", "requestId", "time", "details"})
public class ErrorResponse {

    private String message;

    private String exception;

    private String error;

    private ErrorType errorType;

    private HttpStatus status;

    private String requestId;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSz", iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime time;

    private List<ErrorDetail> details;

}
