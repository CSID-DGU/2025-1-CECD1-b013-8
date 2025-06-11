package b013.archive.backend.service;

import b013.archive.backend.data.dto.EventPersonDto;
import b013.archive.backend.data.entity.EventPerson;
import b013.archive.backend.data.repository.EventPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventPersonService {
    @Autowired
    private EventPersonRepository eventPersonRepository;

    // 사건-인물 연결
    public EventPerson linkEventPerson(EventPersonDto.EventPersonSaveDto requestDto) {
        EventPerson eventPerson = requestDto.toEntity();

        return eventPersonRepository.save(eventPerson);
    }
    
    public List<EventPersonDto.EventPersonResponseDto> getAllEventPerson() {
        List<EventPerson> eventPerson = eventPersonRepository.findAll();

        return eventPerson.stream()
                .map(EventPersonDto.EventPersonResponseDto::new)
                .collect(Collectors.toList());
    }

    public EventPersonDto.EventPersonResponseDto getEventPersonById(int id) {
        EventPerson entity = eventPersonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사건 인물이 없습니다. id=" + id));

        return new EventPersonDto.EventPersonResponseDto(entity);
    }
}