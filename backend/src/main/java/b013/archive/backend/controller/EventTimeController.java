package b013.archive.backend.controller;

import b013.archive.backend.data.dto.EventTimeDto;
import b013.archive.backend.data.entity.EventTime;
import b013.archive.backend.service.EventTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event-time")
public class EventTimeController {

    private final EventTimeService eventTimeService;

    // 사건-시간 등록
    @PostMapping
    public ResponseEntity<EventTime> createEventTime(@RequestBody EventTimeDto.EventTimeSaveDto dto) {
        EventTime saved = eventTimeService.linkEventTime(dto);

        System.out.println(HttpStatus.CREATED);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    
    // 사건 시간 조회
    @GetMapping("/{id}")
    public ResponseEntity<EventTimeDto.EventTimeResponseDto> getEventTimeById(@PathVariable int id) {
        EventTimeDto.EventTimeResponseDto responseDto = eventTimeService.getEventTimeById(id);
        System.out.println(responseDto);

        return ResponseEntity.ok(responseDto);
    }

    // 전체 사건 시간 조회
    @GetMapping
    public ResponseEntity<List<EventTimeDto.EventTimeResponseDto>> getEventTimeList() {
        List<EventTimeDto.EventTimeResponseDto> eventTimeList = eventTimeService.getAllEventTime();

        return ResponseEntity.ok(eventTimeList);
    }
}