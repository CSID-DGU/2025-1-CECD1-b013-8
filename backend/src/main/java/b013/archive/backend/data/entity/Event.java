package b013.archive.backend.data.entity;

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

    private String name;

    @Builder
    protected Event(Autobiography autobiography, String name) {
        this.autobiography = autobiography;
        this.name = name;
    }
}