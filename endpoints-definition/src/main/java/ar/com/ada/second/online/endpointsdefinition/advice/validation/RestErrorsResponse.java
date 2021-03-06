package ar.com.ada.second.online.endpointsdefinition.advice.validation;

import lombok.*;

import java.sql.Timestamp;
import  java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RestErrorsResponse<T> {
    private Timestamp timestamp;
    private Integer status;
    private String error;
    private List<T> errors;

    public RestErrorsResponse (Integer status, String error, List<T>errors){
        this.timestamp=new Timestamp(System.currentTimeMillis());
        this.status=status;
        this.error=error;
        this.errors=errors;


    }
}
