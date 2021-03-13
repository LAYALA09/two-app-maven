package ar.com.ada.online.second.tpp.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity

@Table(name="Actors")
public class Actors implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullname;


    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;

    @Column(nullable=false,length=6)
    private String gender;

    @Column(nullable = false)
    private String photoURL;

    @ManyToMany(mappedBy = "actors")
    private Set<Movies> movies;








}
