package b013.archive.backend.controller;

import b013.archive.backend.data.dto.EventDto;
import b013.archive.backend.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

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
}