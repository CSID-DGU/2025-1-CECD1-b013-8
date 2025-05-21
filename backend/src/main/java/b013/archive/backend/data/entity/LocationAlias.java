package b013.archive.backend.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "location_alias")
public class LocationAlias implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id;

    @ManyToOne
    @JoinColumn(name="location_id", referencedColumnName="id")
    private Location location;

    private String name;

    @Builder
    protected LocationAlias(Location location, String name) {
        this.location = location;
        this.name = name;
    }
}