package b013.archive.backend.controller;

import b013.archive.backend.data.dto.EventDto;
import b013.archive.backend.data.dto.EventSearchDto;
import b013.archive.backend.data.entity.Event;
import b013.archive.backend.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    // 사건 등록
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody EventDto.EventSaveDto dto) {
        Event saved = eventService.createEvent(dto);

        System.out.println(HttpStatus.CREATED);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // 사건 조회
    @GetMapping("/{id}")
    public ResponseEntity<EventDto.EventResponseDto> getEventById(@PathVariable int id) {
        EventDto.EventResponseDto responseDto = eventService.getEventById(id);
        System.out.println(responseDto);

        return ResponseEntity.ok(responseDto);
    }

    // 전체 사건 조회
    @GetMapping
    public ResponseEntity<List<EventDto.EventResponseDto>> getEventList() {
        List<EventDto.EventResponseDto> eventList = eventService.getAllEvent();

        return ResponseEntity.ok(eventList);
    }

    // 키워드 검색 사건 카드 조회
    @GetMapping("/search")
    public ResponseEntity<List<EventSearchDto.EventCardDto>> searchEvents(@RequestParam Long autobiographyId,
                                                                          @RequestParam String keyword
    ) {
        List<EventSearchDto.EventCardDto> events = eventService.getOrganizedEvents(autobiographyId, keyword);
        return ResponseEntity.ok(events);
    }
}