package b013.archive.backend.controller;

import b013.archive.backend.data.dto.PersonAliasDto;
import b013.archive.backend.service.PersonAliasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personAlias")
public class PersonAliasController {

    private final PersonAliasService personAliasService;

    // 음식 조회
    @GetMapping("/{id}")
    public ResponseEntity<PersonAliasDto.PersonAliasResponseDto> getPersonAliasById(@PathVariable int id) {
        PersonAliasDto.PersonAliasResponseDto responseDto = personAliasService.getPersonAliasById(id);
        System.out.println(responseDto);

        return ResponseEntity.ok(responseDto);
    }

    // 전체 음식 조회
    @GetMapping
    public ResponseEntity<List<PersonAliasDto.PersonAliasResponseDto>> getPersonAliasList() {
        List<PersonAliasDto.PersonAliasResponseDto> personAliasList = personAliasService.getAllPersonAlias();

        return ResponseEntity.ok(personAliasList);
    }
}