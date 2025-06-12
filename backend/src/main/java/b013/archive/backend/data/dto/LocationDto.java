package b013.archive.backend.data.dto;

import b013.archive.backend.data.entity.Location;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Objects;

public class LocationDto {
    @Builder
    @Getter
    @Setter
    public static class LocationSaveDto{
        private String name;
        private String description;

        public Location toEntity() {
            return Location.builder()
                    .name(name)
                    .description(description)
                    .build();
        }
    }

// ===================요청, 응답 구분선 ================

    @Getter
    @Builder
    @AllArgsConstructor
    public static class LocationResponseDto{
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private Long id;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String name;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String description;

        public LocationResponseDto(Location location) {
            this.id = location.getId();
            this.name = location.getName();
            this.description = location.getDescription();
        }

        // equals와 hashCode 구현 (중복 제거를 위해)
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LocationResponseDto that = (LocationResponseDto) o;
            return Objects.equals(id, that.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }
}