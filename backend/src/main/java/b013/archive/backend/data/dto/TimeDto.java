package b013.archive.backend.data.dto;

import b013.archive.backend.data.entity.Time;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

public class TimeDto {
    @Builder
    @Getter
    @Setter
    public static class TimeSaveDto{
        private String year;
        private String month;
        private String day;
        private String description;

        public Time toEntity() {
            return Time.builder()
                    .year(year)
                    .month(month)
                    .day(day)
                    .description(description)
                    .build();
        }
    }

// ===================요청, 응답 구분선 ================

    @Getter
    @Builder
    @AllArgsConstructor
    public static class TimeResponseDto{
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private int id;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String year;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String month;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String day;
        @JsonInclude(JsonInclude.Include.NON_DEFAULT)
        private String description;

        public TimeResponseDto(Time time) {
            this.id = time.getId();
            this.year = time.getYear();
            this.month = time.getMonth();
            this.day = time.getDay();
            this.description = time.getDescription();
        }
    }
}