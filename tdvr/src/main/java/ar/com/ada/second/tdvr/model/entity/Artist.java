package ar.com.ada.second.tdvr.model.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//mismos atributos pero distintos roles con ArtistDTO
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "Artist")
@Entity

public class Artist implements Serializable{//vamos a mandar datos a una bd

    @Id
    //indico como se va a incrementar
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 30)
    private String name;

    @OneToMany(mappedBy = "artist")
    private List<Album> albums;
}