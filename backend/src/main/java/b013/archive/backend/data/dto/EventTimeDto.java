package b013.archive.backend.data.dto;

import b013.archive.backend.data.entity.Event;
import b013.archive.backend.data.entity.EventTime;
import b013.archive.backend.data.entity.Time;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

public class EventTimeDto {
    @Getter
    @Builder
    public static class EventTimeSaveDto{
        private Event event;
        private Time time;

        public EventTime toEntity() {
            return EventTime.builder()
                    .event(event)
                    .time(time)
                    .build();
        }
    }

// ===================요청, 응답 구분선 ================

    @Getter
    @Builder
    @AllArgsConstructor
    public static class EventTimeResponseDto {
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private Long id;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private Event event;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private Time time;

        public EventTimeResponseDto(EventTime eventTime) {
            this.id = eventTime.getId();
            this.event = eventTime.getEvent();
            this.time = eventTime.getTime();
        }
    }
}
