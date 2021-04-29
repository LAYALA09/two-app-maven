package ar.com.ada.online.second.tpp.advice.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestFieldError {
    private String field;
    private String message;
    public RestFieldError(String field, String defaultMessage) {
    }



}

