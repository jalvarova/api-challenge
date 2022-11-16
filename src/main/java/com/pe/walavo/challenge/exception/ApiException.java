package com.pe.walavo.challenge.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class ApiException {

    public static final String ERROR_GENERIC = "# Unhandled \n" +
            "No se ha manejado una excepción correctamente y este error indica que no hay más";
    //public static final String NOT_FOUND = "Error no se encontro datos";


    @ExceptionHandler(value = {NullPointerException.class, RuntimeException.class})
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleAllExceptions(Exception ex) {
        log.error("Exception error handler " + "handleAllExceptions" + " error http : " + INTERNAL_SERVER_ERROR);

        List<ErrorDetail> errorDetails = Arrays.stream(ex.getStackTrace())
                .map(error -> ErrorDetail
                        .builder()
                        .field(error.getFileName())
                        .value(error.getMethodName())
                        .message(error.getClassName())
                        .build())
                .collect(Collectors.toList());

        ErrorResponse errorHandler =
                ErrorResponse
                        .builder()
                        .message(ERROR_GENERIC)
                        .exception(ex.getMessage())
                        .error(ErrorType.TECHNICAL_ERROR.value)
                        .errorType(ErrorType.TECHNICAL_ERROR)
                        .status(INTERNAL_SERVER_ERROR)
                        .time(LocalDateTime.now())
                        .requestId(UUID.randomUUID().toString())
                        .details(errorDetails)
                        .build();

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(errorHandler);

    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(CONFLICT)
    protected ResponseEntity<?> handleConflict(IllegalStateException ex) {
        log.error("Exception error handler " + "handleConflict" + " error http : " + CONFLICT);

        String bodyOfResponse = "This should be application specific";
        ErrorResponse errorHandler =
                ErrorResponse
                        .builder()
                        .message(bodyOfResponse)
                        .exception(ex.getMessage())
                        .error(ErrorType.TECHNICAL_ERROR.value)
                        .errorType(ErrorType.TECHNICAL_ERROR)
                        .status(CONFLICT)
                        .time(LocalDateTime.now())
                        .requestId(UUID.randomUUID().toString())
                        .build();

        return ResponseEntity.status(CONFLICT).body(errorHandler);
    }


    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        log.error("Exception error handler " + "handleMethodArgumentNotValid" + " error http : " + BAD_REQUEST);

        List<ErrorDetail> errorDetails = ex.getFieldErrors()
                .stream()
                .map(error -> ErrorDetail
                        .builder()
                        .field(error.getField())
                        .value(Objects.nonNull(error.getRejectedValue()) ? error.getRejectedValue().toString() : "")
                        .message(error.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());

        ErrorResponse errorHandler =
                ErrorResponse
                        .builder()
                        .message("Error de validation de campos")
                        .error(ErrorType.BUSINESS_ERROR.value)
                        .errorType(ErrorType.BUSINESS_ERROR)
                        .status(BAD_REQUEST)
                        .time(LocalDateTime.now())
                        .requestId(UUID.randomUUID().toString())
                        .details(errorDetails)
                        .build();

        return ResponseEntity.status(BAD_REQUEST).body(errorHandler);
    }

    @ResponseBody
    @ResponseStatus(UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> exceptionConstraintValidation(ConstraintViolationException exception) {

        log.error("Exception error handler " + "exceptionConstraintValidation" + " error http : " + UNPROCESSABLE_ENTITY);
        List<ErrorDetail> errorDetails = exception.getConstraintViolations()
                .stream()
                .map(error -> ErrorDetail
                        .builder()
                        .field(error.getPropertyPath().toString())
                        .value(error.getInvalidValue().toString())
                        .message(error.getMessage())
                        .build())
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .message("Error de validation de campos")
                .error(ErrorType.BUSINESS_ERROR.value)
                .errorType(ErrorType.BUSINESS_ERROR)
                .status(UNPROCESSABLE_ENTITY)
                .requestId(UUID.randomUUID().toString())
                .time(LocalDateTime.now())
                .details(errorDetails)
                .build();

        return ResponseEntity.status(UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(value = ResponseStatusException.class)
    public ResponseEntity<Mono<ErrorResponse>> exceptionConstraintValidation(ResponseStatusException exception) {

        log.error("Exception error handler " + "exceptionConstraintValidation" + " error http : " + NOT_FOUND);

        ErrorResponse errorResponse = ErrorResponse
                .builder()
                .message("Error no se encontro recurso")
                .error(ErrorType.BUSINESS_ERROR.value)
                .errorType(ErrorType.BUSINESS_ERROR)
                .status(NOT_FOUND)
                .requestId(UUID.randomUUID().toString())
                .time(LocalDateTime.now())
                .build();

        return ResponseEntity.status(NOT_FOUND).body(Mono.just(errorResponse));
    }
}
