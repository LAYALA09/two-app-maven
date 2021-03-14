package ar.com.ada.online.second.tpp.model.entity;

import ar.com.ada.online.second.tpp.model.mapper.converter.YearAttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Year;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

@Table(name= "Movies")
public class Movies implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String posterURL;

    @Column(nullable = false, columnDefinition = "smallint")
    @Convert(converter = YearAttributeConverter.class)
    private Year released;

    @ManyToMany
    @JoinTable(
            name = "movies-actors",
            joinColumns = { @JoinColumn(
                    name = "movies_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk_movies-actors_movies"))
            },
            inverseJoinColumns = { @JoinColumn(
                    name = "actors_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "fk_movies-actors_actors"))
            }
    )
    private Set<Actors> actors;


}
