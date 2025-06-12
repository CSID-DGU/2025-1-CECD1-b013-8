package b013.archive.backend.data.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.*;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    private String username;
    private String password;
    private String email;

    @Column
    @CreatedDate
    private LocalDateTime created_at;

    @Column
    private LocalDateTime updated_at;

    @Builder
    protected User(String username, String password, String email, LocalDateTime created_at) {
        this.username = username;
        this.password = password;
        this.email = email;

        this.created_at = created_at;
    }

    public void update(String username, String email, LocalDateTime updated_at) {
        this.username = username;
        this.email = email;

        this.updated_at = updated_at;
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }
}