package ar.com.ada.online.second.tpp.advice.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;
@Getter
@Setter
@ToString
@AllArgsConstructor
public class RestErrorsResponse {
    private Timestamp timestamp;
    private Integer status;
    private String error;
    private List<T> errors;

    public RestErrorsResponse(Integer status, String error, List<T> errors) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = status;
        this.error = error;
        this.errors = errors;
    }
}
