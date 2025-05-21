package b013.archive.backend.data.dto;

import b013.archive.backend.data.entity.Autobiography;
import b013.archive.backend.data.entity.Event;
import b013.archive.backend.model.EventType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

public class EventDto {
    @Builder
    @Getter
    @Setter
    public static class EventSaveDto{
        private Autobiography autobiography;
        private EventType type;
        private String name;

        public Event toEntity() {
            return Event.builder()
                    .autobiography(autobiography)
                    .type(type)
                    .name(name)
                    .build();
        }
    }

// ===================요청, 응답 구분선 ================

    @Getter
    @Builder
    @AllArgsConstructor
    public static class EventResponseDto{
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private int id;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private Autobiography autobiography;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private EventType type;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String name;

        public EventResponseDto(Event event) {
            this.id = event.getId();
            this.autobiography = event.getAutobiography();
            this.type = event.getType();
            this.name = event.getName();
        }
    }
}