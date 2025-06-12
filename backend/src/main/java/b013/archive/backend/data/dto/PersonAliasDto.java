package b013.archive.backend.data.dto;

import b013.archive.backend.data.entity.Person;
import b013.archive.backend.data.entity.PersonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

public class PersonAliasDto {
    @Getter
    @Builder
    public static class PersonAliasSaveDto{
        private Person person;
        private String name;

        public PersonAlias toEntity() {
            return PersonAlias.builder()
                    .person(person)
                    .name(name)
                    .build();
        }
    }

// ===================요청, 응답 구분선 ================

    @Getter
    @Builder
    @AllArgsConstructor
    public static class PersonAliasResponseDto {
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private Long id;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private Person person;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String name;

        public PersonAliasResponseDto(PersonAlias personAlias) {
            this.id = personAlias.getId();
            this.person = personAlias.getPerson();
            this.name = personAlias.getName();
        }
    }
}