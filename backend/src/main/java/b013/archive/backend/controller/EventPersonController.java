package b013.archive.backend.controller;

import b013.archive.backend.data.dto.EventPersonDto;
import b013.archive.backend.data.entity.EventPerson;
import b013.archive.backend.service.EventPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event-person")
public class EventPersonController {

    private final EventPersonService eventPersonService;

    // 사건-인물 등록
    @PostMapping
    public ResponseEntity<EventPerson> createEventPerson(@RequestBody EventPersonDto.EventPersonSaveDto dto) {
        EventPerson saved = eventPersonService.linkEventPerson(dto);

        System.out.println(HttpStatus.CREATED);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // 사건 인물 조회
    @GetMapping("/{id}")
    public ResponseEntity<EventPersonDto.EventPersonResponseDto> getEventPersonById(@PathVariable int id) {
        EventPersonDto.EventPersonResponseDto responseDto = eventPersonService.getEventPersonById(id);
        System.out.println(responseDto);

        return ResponseEntity.ok(responseDto);
    }

    // 전체 사건 인물 조회
    @GetMapping
    public ResponseEntity<List<EventPersonDto.EventPersonResponseDto>> getEventPersonList() {
        List<EventPersonDto.EventPersonResponseDto> eventPersonList = eventPersonService.getAllEventPerson();

        return ResponseEntity.ok(eventPersonList);
    }
}