package b013.archive.backend.service;

import b013.archive.backend.data.dto.EventDto;
import b013.archive.backend.data.dto.EventSearchDto;
import b013.archive.backend.data.dto.LocationDto;
import b013.archive.backend.data.dto.PersonDto;
import b013.archive.backend.data.entity.Event;
import b013.archive.backend.data.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    // 사건 생성
    public Event createEvent(EventDto.EventSaveDto requestDto) {
        Event event = requestDto.toEntity();

        return eventRepository.save(event);
    }

    public List<EventDto.EventResponseDto> getAllEvent() {
        List<Event> event = eventRepository.findAll();

        return event.stream()
                .map(EventDto.EventResponseDto::new)
                .collect(Collectors.toList());
    }

    public EventDto.EventResponseDto getEventById(int id) {
        Event entity = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사건이 없습니다. id=" + id));

        return new EventDto.EventResponseDto(entity);
    }

    // 키워드 검색
    public List<EventSearchDto> searchEvents(Long autobiographyId, String keyword) {
        return eventRepository.findEventsByKeyword(autobiographyId, keyword);
    }

    // 정돈된 이벤트 데이터 반환
    public List<EventSearchDto.EventCardDto> getOrganizedEvents(Long autobiographyId, String keyword) {
        List<EventSearchDto> rawData = searchEvents(autobiographyId, keyword);

        return organizeEvents(rawData);
    }

    public List<EventSearchDto.EventCardDto> organizeEvents(List<EventSearchDto> data) {
        Map<Long, EventGroup> grouped = new LinkedHashMap<>();

        for (EventSearchDto row : data) {
            Long eventId = row.getEventId();

            // 이벤트 그룹 초기화
            EventGroup group = grouped.computeIfAbsent(eventId, id ->
                    new EventGroup(
                            row.getEventId(),
                            row.getEventName(),
                            row.getEventPage()
                    )
            );

            // 인물 추가 (중복 제거)
            if (row.getPersonId() != 0) {
                group.addPerson(new PersonDto.PersonResponseDto(
                        row.getPersonId(),
                        row.getPersonName(),
                        row.getPersonDescription()
                ));
            }

            // 장소 추가 (중복 제거)
            if (row.getLocationId() != 0) {
                group.addLocation(new LocationDto.LocationResponseDto(
                        row.getLocationId(),
                        row.getLocationName(),
                        row.getLocationDescription()
                ));
            }
        }

        // EventGroup -> EventCardDto 변환
        return grouped.values().stream()
                .map(EventGroup::toDto)
                .collect(Collectors.toList());
    }

    // 내부 그룹를 위한 클래스
    private static class EventGroup {
        private final Long eventId;
        private final String eventName;
        private final Long eventPage;
        private final Map<Long, PersonDto.PersonResponseDto> persons = new HashMap<>();
        private final Map<Long, LocationDto.LocationResponseDto> locations = new HashMap<>();

        public EventGroup(Long eventId, String eventName, Long eventPage) {
            this.eventId = eventId;
            this.eventName = eventName;
            this.eventPage = eventPage;
        }

        public void addPerson(PersonDto.PersonResponseDto person) {
            persons.putIfAbsent(person.getId(), person);
        }

        public void addLocation(LocationDto.LocationResponseDto location) {
            locations.putIfAbsent(location.getId(), location);
        }

        public EventSearchDto.EventCardDto toDto() {
            return new EventSearchDto.EventCardDto(
                    eventId,
                    eventName,
                    eventPage,
                    new ArrayList<>(persons.values()),
                    new ArrayList<>(locations.values())
            );
        }
    }
}