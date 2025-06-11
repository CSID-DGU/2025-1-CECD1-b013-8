package b013.archive.backend.service;

import b013.archive.backend.data.dto.LocationDto;
import b013.archive.backend.data.entity.Location;
import b013.archive.backend.data.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    // 장소 생성
    public Location createLocation(LocationDto.LocationSaveDto requestDto) {
        Location location = requestDto.toEntity();

        return locationRepository.save(location);
    }

    public List<LocationDto.LocationResponseDto> getAllLocation() {
        List<Location> location = locationRepository.findAll();

        return location.stream()
                .map(LocationDto.LocationResponseDto::new)
                .collect(Collectors.toList());
    }

    public LocationDto.LocationResponseDto getLocationById(int id) {
        Location entity = locationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 장소가 없습니다. id=" + id));

        return new LocationDto.LocationResponseDto(entity);
    }
}