package b013.archive.backend.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "event_person")
public class EventPerson implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @ManyToOne
    @JoinColumn(name="event_id", referencedColumnName="id")
    private Event event;

    @ManyToOne
    @JoinColumn(name="person_id", referencedColumnName="id")
    private Person person;

    @Builder
    protected EventPerson(Event event, Person person) {
        this.event = event;
        this.person = person;
    }
}