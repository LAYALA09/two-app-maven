package ar.com.ada.second.online.endpointsdefinition.advice;


import ar.com.ada.second.online.endpointsdefinition.advice.validation.RestErrorsResponse;
import ar.com.ada.second.online.endpointsdefinition.advice.validation.RestFieldError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;
@RestControllerAdvice
//creo un advice propio
public class RestValidationExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
//internamente extraigo los campos que fallaron
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
//convierto en una lista
        List<RestFieldError> restFieldErrors = fieldErrors
                .stream()
                .map(fieldError -> new RestFieldError(
                        fieldError.getField(),
                        fieldError.getDefaultMessage()
                ))
                .collect(Collectors.toList());

       //creo el cuerpo completo de la respuesta

        RestErrorsResponse<RestFieldError> restFieldErrorRestErrorsResponse = new RestErrorsResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                //lista de errores
                restFieldErrors
        );
        return ResponseEntity.badRequest().body(restFieldErrorRestErrorsResponse);
    }
}
