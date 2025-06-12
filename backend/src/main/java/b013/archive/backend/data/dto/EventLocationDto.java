package b013.archive.backend.data.dto;

import b013.archive.backend.data.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

public class EventLocationDto {
    @Getter
    @Builder
    public static class EventLocationSaveDto{
        private Event event;
        private Location location;

        public EventLocation toEntity() {
            return EventLocation.builder()
                    .event(event)
                    .location(location)
                    .build();
        }
    }

// ===================요청, 응답 구분선 ================

    @Getter
    @Builder
    @AllArgsConstructor
    public static class EventLocationResponseDto {
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private Long id;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private Event event;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private Location location;

        public EventLocationResponseDto(EventLocation eventLocation) {
            this.id = eventLocation.getId();
            this.event = eventLocation.getEvent();
            this.location = eventLocation.getLocation();
        }
    }
}