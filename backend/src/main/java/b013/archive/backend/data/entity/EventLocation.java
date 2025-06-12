package b013.archive.backend.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "event_location")
public class EventLocation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @ManyToOne
    @JoinColumn(name="event_id", referencedColumnName="id")
    private Event event;

    @ManyToOne
    @JoinColumn(name="location_id", referencedColumnName="id")
    private Location location;

    @Builder
    protected EventLocation(Event event, Location location) {
        this.event = event;
        this.location = location;
    }
}