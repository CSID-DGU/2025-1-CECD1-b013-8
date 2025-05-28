package b013.archive.backend.service;

import b013.archive.backend.data.dto.TimeDto;
import b013.archive.backend.data.entity.Time;
import b013.archive.backend.data.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeService {
    @Autowired
    private TimeRepository timeRepository;

    public List<TimeDto.TimeResponseDto> getAllTime() {
        List<Time> time = timeRepository.findAll();

        return time.stream()
                .map(TimeDto.TimeResponseDto::new)
                .collect(Collectors.toList());
    }

    public TimeDto.TimeResponseDto getTimeById(int id) {
        Time entity = timeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 시간이 없습니다. id=" + id));

        return new TimeDto.TimeResponseDto(entity);
    }
}