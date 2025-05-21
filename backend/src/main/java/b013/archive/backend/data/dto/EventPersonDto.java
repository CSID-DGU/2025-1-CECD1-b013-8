package b013.archive.backend.data.dto;

import b013.archive.backend.data.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class EventPersonDto {
    @Getter
    @Builder
    public static class EventPersonSaveDto{
        private Event event;
        private Person person;

        public EventPerson toEntity() {
            return EventPerson.builder()
                    .event(event)
                    .person(person)
                    .build();
        }
    }

// ===================요청, 응답 구분선 ================

    @Getter
    @Builder
    @AllArgsConstructor
    public static class EventPersonResponseDto {
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private int id;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private Event event;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private Person person;

        public EventPersonResponseDto(EventPerson eventPerson) {
            this.id = eventPerson.getId();
            this.event = eventPerson.getEvent();
            this.person = eventPerson.getPerson();
        }
    }
}