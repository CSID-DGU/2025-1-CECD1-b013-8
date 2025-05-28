package b013.archive.backend.service;

import b013.archive.backend.data.dto.EventLocationDto;
import b013.archive.backend.data.entity.EventLocation;
import b013.archive.backend.data.repository.EventLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventLocationService {
    @Autowired
    private EventLocationRepository eventLocationRepository;

    public List<EventLocationDto.EventLocationResponseDto> getAllEventLocation() {
        List<EventLocation> eventLocation = eventLocationRepository.findAll();

        return eventLocation.stream()
                .map(EventLocationDto.EventLocationResponseDto::new)
                .collect(Collectors.toList());
    }

    public EventLocationDto.EventLocationResponseDto getEventLocationById(int id) {
        EventLocation entity = eventLocationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사건 장소가 없습니다. id=" + id));

        return new EventLocationDto.EventLocationResponseDto(entity);
    }
}