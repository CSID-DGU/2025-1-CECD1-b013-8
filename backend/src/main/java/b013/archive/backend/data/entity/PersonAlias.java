package b013.archive.backend.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "person_alias")
public class PersonAlias implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @ManyToOne
    @JoinColumn(name="person_id", referencedColumnName="id")
    private Person person;

    private String name;

    @Builder
    protected PersonAlias(Person person, String name) {
        this.person = person;
        this.name = name;
    }
}