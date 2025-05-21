package b013.archive.backend.data.dto;

import b013.archive.backend.data.entity.Location;
import b013.archive.backend.data.entity.LocationAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

public class LocationAliasDto {
    @Getter
    @Builder
    public static class LocationAliasSaveDto{
        private Location location;
        private String name;

        public LocationAlias toEntity() {
            return LocationAlias.builder()
                    .location(location)
                    .name(name)
                    .build();
        }
    }

// ===================요청, 응답 구분선 ================

    @Getter
    @Builder
    @AllArgsConstructor
    public static class LocationAliasResponseDto {
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private int id;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private Location location;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String name;

        public LocationAliasResponseDto(LocationAlias locationAlias) {
            this.id = locationAlias.getId();
            this.location = locationAlias.getLocation();
            this.name = locationAlias.getName();
        }
    }
}