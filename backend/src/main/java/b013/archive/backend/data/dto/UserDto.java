package b013.archive.backend.data.dto;

import b013.archive.backend.data.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

public class UserDto {
    @Builder
    @Getter
    @Setter
    public static class UserSaveDto{
        private String username;
        private String password;
        private String email;

        private LocalDateTime created_at;
        private LocalDateTime updated_at;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .created_at(created_at)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class UserUpdateDto{
        private String username;
        private String password;

        private LocalDateTime updated_at;
    }

// ===================요청, 응답 구분선 ================

    @Getter
    @Builder
    @AllArgsConstructor
    public static class UserResponseDto{
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private Long id;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String username;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String email;

        public UserResponseDto(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
        }
    }
}