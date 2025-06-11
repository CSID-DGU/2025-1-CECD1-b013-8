package b013.archive.backend.controller;

import b013.archive.backend.data.dto.TimeDto;
import b013.archive.backend.data.dto.TimeDto;
import b013.archive.backend.data.entity.Time;
import b013.archive.backend.service.TimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/times")
public class TimeController {

    private final TimeService timeService;

    // 시간 등록
    @PostMapping
    public ResponseEntity<Time> createTime(@RequestBody TimeDto.TimeSaveDto dto) {
        Time saved = timeService.createTime(dto);

        System.out.println(HttpStatus.CREATED);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // 시간 조회
    @GetMapping("/{id}")
    public ResponseEntity<TimeDto.TimeResponseDto> getTimeById(@PathVariable int id) {
        TimeDto.TimeResponseDto responseDto = timeService.getTimeById(id);
        System.out.println(responseDto);

        return ResponseEntity.ok(responseDto);
    }

    // 전체 시간 조회
    @GetMapping
    public ResponseEntity<List<TimeDto.TimeResponseDto>> getTimeList() {
        List<TimeDto.TimeResponseDto> timeList = timeService.getAllTime();

        return ResponseEntity.ok(timeList);
    }
}