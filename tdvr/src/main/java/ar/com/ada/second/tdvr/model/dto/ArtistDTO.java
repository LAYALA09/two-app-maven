package ar.com.ada.second.tdvr.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArtistDTO implements Serializable {//vamos a recibir datos de request http
    private Long id;
    @NotBlank(message = "is required")
    private String name;
}
