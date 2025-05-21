package b013.archive.backend.data.dto;

import b013.archive.backend.data.entity.Location;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

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
        private int id;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String name;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String description;

        public LocationResponseDto(Location location) {
            this.id = location.getId();
            this.name = location.getName();
            this.description = location.getDescription();
        }
    }
}