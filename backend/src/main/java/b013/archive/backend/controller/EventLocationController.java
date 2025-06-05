package b013.archive.backend.controller;

import b013.archive.backend.data.dto.EventLocationDto;
import b013.archive.backend.service.EventLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/eventLocation")
public class EventLocationController {

    private final EventLocationService eventLocationService;

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