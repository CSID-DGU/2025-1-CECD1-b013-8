package b013.archive.backend.service;

import b013.archive.backend.data.dto.EventTimeDto;
import b013.archive.backend.data.entity.EventTime;
import b013.archive.backend.data.repository.EventTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventTimeService {
    @Autowired
    private EventTimeRepository eventTimeRepository;

    // 사건-시간 연결
    public void linkEventTime(int event_id, int time_id) {
        eventTimeRepository.saveByCompositeId(event_id, time_id);
    }
    
    public List<EventTimeDto.EventTimeResponseDto> getAllEventTime() {
        List<EventTime> eventTime = eventTimeRepository.findAll();

        return eventTime.stream()
                .map(EventTimeDto.EventTimeResponseDto::new)
                .collect(Collectors.toList());
    }

    public EventTimeDto.EventTimeResponseDto getEventTimeById(int id) {
        EventTime entity = eventTimeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사건 시간이 없습니다. id=" + id));

        return new EventTimeDto.EventTimeResponseDto(entity);
    }
}