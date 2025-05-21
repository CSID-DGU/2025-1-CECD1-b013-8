package b013.archive.backend.data.entity;

import b013.archive.backend.model.EventType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "event")
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id;

    @ManyToOne
    @JoinColumn(name="autobiography_id", referencedColumnName="id")
    private Autobiography autobiography;

    @Column(columnDefinition = "ENUM()")
    @Enumerated(EnumType.STRING)
    private EventType type;

    private String name;

    @Builder
    protected Event(Autobiography autobiography, EventType type, String name) {
        this.autobiography = autobiography;
        this.type = type;
        this.name = name;
    }
}