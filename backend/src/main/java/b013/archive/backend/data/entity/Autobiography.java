package b013.archive.backend.data.entity;

import b013.archive.backend.model.VisibleType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "autobiography")
public class Autobiography implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName="id")
    private User user;

    private String title;
    private String description;

    @Column(columnDefinition = "ENUM('PUBLIC', 'PRIVATE', 'RESTRICTED')")
    @Enumerated(EnumType.STRING)
    private VisibleType visibility;

    @Builder
    protected Autobiography(User user, String title, String description, VisibleType visibility) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.visibility = visibility;
    }

    public void update(User user, String title, String description, VisibleType visibility) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.visibility = visibility;
    }
}