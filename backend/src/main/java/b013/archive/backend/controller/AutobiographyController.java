package b013.archive.backend.controller;

import b013.archive.backend.data.dto.AutobiographyDto;
import b013.archive.backend.service.AutobiographyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/autobiographies")
public class AutobiographyController {

    private final AutobiographyService autobiographyService;

    // 자서전 조회
    @GetMapping("/{id}")
    public ResponseEntity<AutobiographyDto.AutobiographyResponseDto> getAutobiographyById(@PathVariable int id) {
        AutobiographyDto.AutobiographyResponseDto responseDto = autobiographyService.getAutobiographyById(id);
        System.out.println(responseDto);

        return ResponseEntity.ok(responseDto);
    }

    // 전체 자서전 조회
    @GetMapping
    public ResponseEntity<List<AutobiographyDto.AutobiographyResponseDto>> getAutobiographyList() {
        List<AutobiographyDto.AutobiographyResponseDto> autobiographyList = autobiographyService.getAllAutobiography();

        return ResponseEntity.ok(autobiographyList);
    }
}