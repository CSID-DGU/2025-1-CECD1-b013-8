package b013.archive.backend.controller;

import b013.archive.backend.data.dto.PersonDto;
import b013.archive.backend.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    // 음식 조회
    @GetMapping("/{id}")
    public ResponseEntity<PersonDto.PersonResponseDto> getPersonById(@PathVariable int id) {
        PersonDto.PersonResponseDto responseDto = personService.getPersonById(id);
        System.out.println(responseDto);

        return ResponseEntity.ok(responseDto);
    }

    // 전체 음식 조회
    @GetMapping
    public ResponseEntity<List<PersonDto.PersonResponseDto>> getPersonList() {
        List<PersonDto.PersonResponseDto> personList = personService.getAllPerson();

        return ResponseEntity.ok(personList);
    }
}