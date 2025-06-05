package b013.archive.backend.controller;

import b013.archive.backend.data.dto.LocationAliasDto;
import b013.archive.backend.service.LocationAliasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/locationAlias")
public class LocationAliasController {

    private final LocationAliasService locationAliasService;

    // 장소 별칭 조회
    @GetMapping("/{id}")
    public ResponseEntity<LocationAliasDto.LocationAliasResponseDto> getLocationAliasById(@PathVariable int id) {
        LocationAliasDto.LocationAliasResponseDto responseDto = locationAliasService.getLocationAliasById(id);
        System.out.println(responseDto);

        return ResponseEntity.ok(responseDto);
    }

    // 전체 장소 별칭 조회
    @GetMapping
    public ResponseEntity<List<LocationAliasDto.LocationAliasResponseDto>> getLocationAliasList() {
        List<LocationAliasDto.LocationAliasResponseDto> locationAliasList = locationAliasService.getAllLocationAlias();

        return ResponseEntity.ok(locationAliasList);
    }
}