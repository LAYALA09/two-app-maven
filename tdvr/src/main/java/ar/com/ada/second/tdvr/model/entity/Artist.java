package ar.com.ada.second.tdvr.model.entity;

import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.io.Serializable;
//mismos atributos pero distintos roles con ArtistDTO
@NoArgsConstructor
@Setter
@Getter
@Table(name = "Artist")
@Entity
public class Artist implements Serializable{//vamos a mandar datos a una bd
    @Id
    //indico como se va a incrementar
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 30)
    private String name;


}