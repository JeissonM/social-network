package com.example.social_network.infrastructure.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;
import com.example.social_network.infrastructure.common.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseBase<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach((FieldError error) -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(
            ResponseBase.error("Errores de validación", 400, errors)
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseBase<Map<String, String>>> handleDuplicateEmail(DataIntegrityViolationException ex) {
        Map<String, String> error = new HashMap<>();
        return ResponseEntity.badRequest().body(
            ResponseBase.error("El correo ya está registrado en otro usuario", 400, error)
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseBase<Map<String, String>>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, String> error = new HashMap<>();
        return ResponseEntity.badRequest().body(
            ResponseBase.error(ex.getMessage(), 400, error)
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseBase<Map<String, String>>> handleInvalidFormat(HttpMessageNotReadableException ex) {
        Map<String, String> error = new HashMap<>();

        // Este mensaje aparece cuando falla la conversión a LocalDate
        if (ex.getCause() instanceof DateTimeParseException || ex.getMessage().contains("LocalDate")) {
            error.put("error", "Formato de fecha inválido. Usa el formato yyyy-MM-dd, por ejemplo: 1990-01-01");
        } else {
            error.put("error", "Solicitud incorrecta.");
        }

        return ResponseEntity.badRequest().body(
            ResponseBase.error("Errores de validación", 400, error)
        );
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseBase<Map<String, String>>> handleUserNotFound(UsernameNotFoundException ex) {
        return ResponseEntity.badRequest().body(
            ResponseBase.error("Intento fallido, verifique los datos", 400, null)
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseBase<Map<String, String>>> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity.badRequest().body(
            ResponseBase.error("Intento fallido, verifique los datos", 400, null)
        );
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ResponseBase<Map<String, String>>> handleNoLike(IllegalStateException ex) {
        Map<String, String> error = new HashMap<>();
        return ResponseEntity.badRequest().body(
            ResponseBase.error("Estimado usuario, ya le dio like a esta publicación", 400, error)
        );
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseBase<Map<String, String>>> handleNoParams(MissingServletRequestParameterException ex) {
        Map<String, String> error = new HashMap<>();
        return ResponseEntity.badRequest().body(
            ResponseBase.error("Debes indicar un usuario y un post", 400, error)
        );
    }
}
