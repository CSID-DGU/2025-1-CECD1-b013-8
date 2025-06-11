package b013.archive.backend.controller;

import b013.archive.backend.data.dto.PersonAliasDto;
import b013.archive.backend.data.entity.PersonAlias;
import b013.archive.backend.service.PersonAliasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person-aliases")
public class PersonAliasController {

    private final PersonAliasService personAliasService;

    // 장소 별칭 등록
    @PostMapping
    public ResponseEntity<PersonAlias> createPersonAlias(@RequestBody PersonAliasDto.PersonAliasSaveDto dto) {
        PersonAlias saved = personAliasService.createPersonAlias(dto);

        System.out.println(HttpStatus.CREATED);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    
    // 인물 별칭 조회
    @GetMapping("/{id}")
    public ResponseEntity<PersonAliasDto.PersonAliasResponseDto> getPersonAliasById(@PathVariable int id) {
        PersonAliasDto.PersonAliasResponseDto responseDto = personAliasService.getPersonAliasById(id);
        System.out.println(responseDto);

        return ResponseEntity.ok(responseDto);
    }

    // 전체 인물 별칭 조회
    @GetMapping
    public ResponseEntity<List<PersonAliasDto.PersonAliasResponseDto>> getPersonAliasList() {
        List<PersonAliasDto.PersonAliasResponseDto> personAliasList = personAliasService.getAllPersonAlias();

        return ResponseEntity.ok(personAliasList);
    }
}