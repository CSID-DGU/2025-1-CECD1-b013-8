package b013.archive.backend.controller;

import b013.archive.backend.data.dto.EventDto;
import b013.archive.backend.data.dto.EventLocationDto;
import b013.archive.backend.data.entity.Event;
import b013.archive.backend.data.entity.EventLocation;
import b013.archive.backend.service.EventLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event-location")
public class EventLocationController {

    private final EventLocationService eventLocationService;

    // 사건-장소 등록
    @PostMapping
    public ResponseEntity<EventLocation> createEventLocation(@RequestBody EventLocationDto.EventLocationSaveDto dto) {
        EventLocation saved = eventLocationService.linkEventLocation(dto);

        System.out.println(HttpStatus.CREATED);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // 사건 장소 조회
    @GetMapping("/{id}")
    public ResponseEntity<EventLocationDto.EventLocationResponseDto> getEventLocationById(@PathVariable int id) {
        EventLocationDto.EventLocationResponseDto responseDto = eventLocationService.getEventLocationById(id);
        System.out.println(responseDto);

        return ResponseEntity.ok(responseDto);
    }

    // 전체 사건 장소 조회
    @GetMapping
    public ResponseEntity<List<EventLocationDto.EventLocationResponseDto>> getEventLocationList() {
        List<EventLocationDto.EventLocationResponseDto> eventLocationList = eventLocationService.getAllEventLocation();

        return ResponseEntity.ok(eventLocationList);
    }
}