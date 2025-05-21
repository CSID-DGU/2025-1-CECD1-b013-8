package b013.archive.backend.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "time")
public class Time implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id;

    private String year;
    private String month;
    private String day;
    private String description;

    @Builder
    protected Time(String year, String month, String day, String description) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.description = description;
    }
}