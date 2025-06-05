package b013.archive.backend.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "event_time")
public class EventTime implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id;

    @ManyToOne
    @JoinColumn(name="event_id", referencedColumnName="id")
    private Event event;

    @ManyToOne
    @JoinColumn(name="time_id", referencedColumnName="id")
    private Time time;

    @Builder
    protected EventTime(Event event, Time time) {
        this.event = event;
        this.time = time;
    }
}