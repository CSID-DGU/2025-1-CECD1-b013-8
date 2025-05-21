package b013.archive.backend.data.dto;

import b013.archive.backend.data.entity.Autobiography;
import b013.archive.backend.data.entity.User;
import b013.archive.backend.model.VisibleType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

public class AutobiographyDto {
    @Builder
    @Getter
    @Setter
    public static class AutobiographySaveDto{
        private User user;

        private String title;
        private String description;

        private VisibleType visibility;

        public Autobiography toEntity() {
            return Autobiography.builder()
                    .user(user)
                    .title(title)
                    .description(description)
                    .visibility(visibility)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class AutobiographyUpdateDto{
        private String title;
        private String description;

        private VisibleType visibility;
    }

// ===================요청, 응답 구분선 ================

    @Getter
    @Builder
    @AllArgsConstructor
    public static class AutobiographyResponseDto{
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private int id;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String title;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String description;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private VisibleType visibility;

        public AutobiographyResponseDto(Autobiography autobiography) {
            this.id = autobiography.getId();
            this.title = autobiography.getTitle();
            this.description = autobiography.getDescription();
            this.visibility = autobiography.getVisibility();
        }
    }
}