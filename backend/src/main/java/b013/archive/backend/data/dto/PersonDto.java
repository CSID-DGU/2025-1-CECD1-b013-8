package b013.archive.backend.data.dto;

import b013.archive.backend.data.entity.Person;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Objects;

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
        private Long id;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String name;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String description;

        public PersonResponseDto(Person person) {
            this.id = person.getId();
            this.name = person.getName();
            this.description = person.getDescription();
        }

        // equals와 hashCode 구현 (중복 제거를 위해)
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PersonResponseDto that = (PersonResponseDto) o;
            return Objects.equals(id, that.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}
