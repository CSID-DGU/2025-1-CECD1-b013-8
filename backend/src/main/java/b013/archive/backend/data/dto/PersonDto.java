package b013.archive.backend.data.dto;

import b013.archive.backend.data.entity.Person;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

public class PersonDto {
    @Builder
    @Getter
    @Setter
    public static class PersonSaveDto{
        private String name;
        private String description;

        public Person toEntity() {
            return Person.builder()
                    .name(name)
                    .description(description)
                    .build();
        }
    }

// ===================요청, 응답 구분선 ================

    @Getter
    @Builder
    @AllArgsConstructor
    public static class PersonResponseDto{
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private int id;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String name;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String description;

        public PersonResponseDto(Person person) {
            this.id = person.getId();
            this.name = person.getName();
            this.description = person.getDescription();
        }
    }
}
