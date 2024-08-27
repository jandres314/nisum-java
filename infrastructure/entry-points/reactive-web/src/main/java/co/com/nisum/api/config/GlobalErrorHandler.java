package co.com.nisum.api.config;

import co.com.nisum.model.user.exceptions.BusinessException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.resource.NoResourceFoundException;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalErrorHandler {

    private static final String APP_NAME = "app-nissum-solucion-java";
    private final ObjectMapper mapper;

    @ExceptionHandler(BusinessException.class)
    public Mono<Void> handleBussinesException(BusinessException ex, ServerWebExchange exchange)
            throws JsonProcessingException {
        var correlationId = UUID.randomUUID().toString();
        logException(correlationId, ex.getMessage());
        var dataBuffer = getDataBuffer(correlationId, ex.getMessage(), exchange, HttpStatus.BAD_REQUEST);
        return exchange.getResponse().writeWith(Mono.just(dataBuffer));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<Void> handleWebExchangeBindException(WebExchangeBindException ex, ServerWebExchange exchange)
            throws JsonProcessingException {
        var correlationId = UUID.randomUUID().toString();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        logException(correlationId, errors.toString());
        var dataBuffer = getDataBuffer
                (correlationId, "Error en los datos de entrada", exchange, HttpStatus.BAD_REQUEST);
        return exchange.getResponse().writeWith(Mono.just(dataBuffer));
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    public Mono<Void> handleMethodNotAllowedException(MethodNotAllowedException ex, ServerWebExchange exchange)
            throws JsonProcessingException {
        var correlationId = UUID.randomUUID().toString();
        logException(correlationId, ex.getMessage());
        var dataBuffer = getDataBuffer(correlationId, ex.getMessage(), exchange, HttpStatus.METHOD_NOT_ALLOWED);
        return exchange.getResponse().writeWith(Mono.just(dataBuffer));
    }

    @ExceptionHandler(UnsupportedMediaTypeStatusException.class)
    public Mono<Void> handleUnsupportedMediaTypeStatusException
            (UnsupportedMediaTypeStatusException ex, ServerWebExchange exchange)
            throws JsonProcessingException {
        var correlationId = UUID.randomUUID().toString();
        logException(correlationId, ex.getMessage());
        var dataBuffer = getDataBuffer(correlationId, ex.getMessage(), exchange, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        return exchange.getResponse().writeWith(Mono.just(dataBuffer));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public Mono<Void> handleNoResourceFoundException(NoResourceFoundException ex, ServerWebExchange exchange)
            throws JsonProcessingException {
        var correlationId = UUID.randomUUID().toString();
        logException(correlationId, ex.getMessage());
        var dataBuffer = getDataBuffer(correlationId, ex.getMessage(), exchange, HttpStatus.NOT_FOUND);
        return exchange.getResponse().writeWith(Mono.just(dataBuffer));
    }

    @ExceptionHandler(Exception.class)
    public Mono<Void> handleException(Exception ex, ServerWebExchange exchange)
            throws JsonProcessingException {
        var correlationId = UUID.randomUUID().toString();
        logException(correlationId, ex.getMessage());
        var message = "Error interno en la API";
        var dataBuffer = getDataBuffer(correlationId, message, exchange, HttpStatus.INTERNAL_SERVER_ERROR);
        return exchange.getResponse().writeWith(Mono.just(dataBuffer));
    }

    private DataBuffer getDataBuffer
            (String correlationId, String message, ServerWebExchange exchange, HttpStatus httpStatus)
            throws JsonProcessingException {
        exchange.getResponse().getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        exchange.getResponse().setStatusCode(httpStatus);
        var bufferFactory = exchange.getResponse().bufferFactory();
        return bufferFactory
                .wrap(mapper.writeValueAsBytes(getErrorResponse(correlationId, message)));
    }

    private void logException(String correlationId, String message) {
        log.info("correlation id {} ::: {}", correlationId, message);
    }

    private Map<String, String> getErrorResponse(String correlationId, String message) {
        Map<String, String> response = new HashMap<>();
        response.put("correlationId", correlationId);
        response.put("mensaje", message);
        response.put("requestDateTime", LocalDateTime.now().toString());
        response.put("applicationName", APP_NAME);
        return response;
    }

}
