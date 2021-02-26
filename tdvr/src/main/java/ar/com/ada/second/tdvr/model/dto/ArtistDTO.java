package ar.com.ada.second.tdvr.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArtistDTO {
    private Long id;
    @NotBlank(message = "is required")
    private String name;
}
