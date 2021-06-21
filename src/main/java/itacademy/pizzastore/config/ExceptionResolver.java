package itacademy.pizzastore.config;

import itacademy.pizzastore.service.exceptions.OrderNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
//TODO: add handlers for all the exceptions from Application Services.
public class ExceptionResolver extends ResponseEntityExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrderNotFound(OrderNotFoundException exception, WebRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @AllArgsConstructor
    @Getter
    @Setter
    public static class ErrorResponse {
        private String message;
        private int errorCode;
    }
}
