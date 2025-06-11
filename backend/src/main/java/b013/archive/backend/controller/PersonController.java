package b013.archive.backend.controller;

import b013.archive.backend.data.dto.PersonDto;
import b013.archive.backend.data.entity.Person;
import b013.archive.backend.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/people")
public class PersonController {

    private final PersonService personService;

    // 인물 등록
    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody PersonDto.PersonSaveDto dto) {
        Person saved = personService.createPerson(dto);

        System.out.println(HttpStatus.CREATED);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // 인물 조회
    @GetMapping("/{id}")
    public ResponseEntity<PersonDto.PersonResponseDto> getPersonById(@PathVariable int id) {
        PersonDto.PersonResponseDto responseDto = personService.getPersonById(id);
        System.out.println(responseDto);

        return ResponseEntity.ok(responseDto);
    }

    // 전체 인물 조회
    @GetMapping
    public ResponseEntity<List<PersonDto.PersonResponseDto>> getPersonList() {
        List<PersonDto.PersonResponseDto> personList = personService.getAllPerson();

        return ResponseEntity.ok(personList);
    }
}