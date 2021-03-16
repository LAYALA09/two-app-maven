package ar.com.ada.online.second.tpp.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MoviesDTO implements Serializable {

    private Long id;

    @NotBlank(message = "is required")
    private String name;

    public Boolean hasNullOrEmptyAttributes(){
        return  name==null||name.trim().isEmpty();
    }
}
