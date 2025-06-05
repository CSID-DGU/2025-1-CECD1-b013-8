package b013.archive.backend.controller;

import b013.archive.backend.data.dto.LocationDto;
import b013.archive.backend.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/location")
public class LocationController {

    private final LocationService locationService;

    // 장소 조회
    @GetMapping("/{id}")
    public ResponseEntity<LocationDto.LocationResponseDto> getLocationById(@PathVariable int id) {
        LocationDto.LocationResponseDto responseDto = locationService.getLocationById(id);
        System.out.println(responseDto);

        return ResponseEntity.ok(responseDto);
    }

    // 전체 장소 조회
    @GetMapping
    public ResponseEntity<List<LocationDto.LocationResponseDto>> getLocationList() {
        List<LocationDto.LocationResponseDto> locationList = locationService.getAllLocation();

        return ResponseEntity.ok(locationList);
    }
}