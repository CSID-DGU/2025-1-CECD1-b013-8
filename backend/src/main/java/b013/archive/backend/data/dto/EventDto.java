package b013.archive.backend.data.dto;

import b013.archive.backend.data.entity.Autobiography;
import b013.archive.backend.data.entity.Event;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

public class EventDto {
    @Builder
    @Getter
    @Setter
    public static class EventSaveDto{
        private Autobiography autobiography;
        private String name;
        private Long page;

        public Event toEntity() {
            return Event.builder()
                    .autobiography(autobiography)
                    .name(name)
                    .page(page)
                    .build();
        }
    }

// ===================요청, 응답 구분선 ================

    @Getter
    @Builder
    @AllArgsConstructor
    public static class EventResponseDto{
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private Long id;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private Autobiography autobiography;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String name;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private Long page;

        public EventResponseDto(Event event) {
            this.id = event.getId();
            this.autobiography = event.getAutobiography();
            this.name = event.getName();
            this.page = event.getPage();
        }
    }
}