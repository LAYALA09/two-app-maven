package ar.com.ada.second.tdvr.advice.validation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@Setter
@ToString


    public RestErrorsResponse(Integer status, String error, List<T> errors) {
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.status = status;
         this.error = error;
        this.errors = errors;
    }

