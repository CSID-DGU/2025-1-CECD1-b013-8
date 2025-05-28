package b013.archive.backend.service;

import b013.archive.backend.data.dto.PersonAliasDto;
import b013.archive.backend.data.entity.PersonAlias;
import b013.archive.backend.data.repository.PersonAliasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonAliasService {
    @Autowired
    private PersonAliasRepository personAliasRepository;

    public List<PersonAliasDto.PersonAliasResponseDto> getAllPersonAlias() {
        List<PersonAlias> personAlias = personAliasRepository.findAll();

        return personAlias.stream()
                .map(PersonAliasDto.PersonAliasResponseDto::new)
                .collect(Collectors.toList());
    }

    public PersonAliasDto.PersonAliasResponseDto getPersonAliasById(int id) {
        PersonAlias entity = personAliasRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 인물 별칭이 없습니다. id=" + id));

        return new PersonAliasDto.PersonAliasResponseDto(entity);
    }
}