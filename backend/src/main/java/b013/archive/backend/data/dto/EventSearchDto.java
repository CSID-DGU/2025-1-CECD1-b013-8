package b013.archive.backend.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class EventSearchDto {
    private Long eventId;
    private String eventName;
    private Long eventPage;
    private Long personId;
    private String personName;
    private String personDescription;
    private Long locationId;
    private String locationName;
    private String locationDescription;

    public EventSearchDto(EventSearchDto eventSearch) {
        this.eventId = eventSearch.getEventId();
        this.eventName = eventSearch.getEventName();

        this.personId = eventSearch.getPersonId();
        this.personName = eventSearch.getPersonDescription();
        this.personDescription = eventSearch.getPersonDescription();

        this.locationId = eventSearch.getLocationId();
        this.locationName = eventSearch.getLocationDescription();
        this.locationDescription = eventSearch.getLocationDescription();
    }

    // ================= 이벤트 카드 DTO ================

    @Getter
    @Builder
    @AllArgsConstructor
    public static class EventCardDto {
        private Long id;
        private String name;
        private Long page;
        private List<PersonDto.PersonResponseDto> persons;
        private List<LocationDto.LocationResponseDto> locations;
    }
}
