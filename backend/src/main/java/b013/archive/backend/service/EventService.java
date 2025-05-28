package b013.archive.backend.service;

import b013.archive.backend.data.dto.EventDto;
import b013.archive.backend.data.entity.Event;
import b013.archive.backend.data.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

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
}