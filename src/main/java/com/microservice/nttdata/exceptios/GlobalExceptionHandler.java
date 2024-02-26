package com.microservice.nttdata.exceptios;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMensaje(e.getMessage());
        errorResponse.setError(Collections.singletonList(errorDetails));
        return new ResponseEntity<>(errorResponse, e.getStatus());
    }

    private static class ErrorResponse {
        private List<ErrorDetails> error;

        public List<ErrorDetails> getError() {
            return error;
        }

        public void setError(List<ErrorDetails> error) {
            this.error = error;
        }
    }

    private static class ErrorDetails {
        private String mensaje;

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
    }

}
