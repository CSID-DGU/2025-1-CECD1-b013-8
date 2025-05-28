package b013.archive.backend.service;

import b013.archive.backend.data.dto.PersonDto;
import b013.archive.backend.data.entity.Person;
import b013.archive.backend.data.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public List<PersonDto.PersonResponseDto> getAllPerson() {
        List<Person> person = personRepository.findAll();

        return person.stream()
                .map(PersonDto.PersonResponseDto::new)
                .collect(Collectors.toList());
    }

    public PersonDto.PersonResponseDto getPersonById(int id) {
        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 인물이 없습니다. id=" + id));

        return new PersonDto.PersonResponseDto(entity);
    }
}