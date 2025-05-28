package b013.archive.backend.service;

import b013.archive.backend.data.dto.LocationAliasDto;
import b013.archive.backend.data.entity.LocationAlias;
import b013.archive.backend.data.repository.LocationAliasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationAliasService {
    @Autowired
    private LocationAliasRepository locationAliasRepository;

    public List<LocationAliasDto.LocationAliasResponseDto> getAllLocationAlias() {
        List<LocationAlias> locationAlias = locationAliasRepository.findAll();

        return locationAlias.stream()
                .map(LocationAliasDto.LocationAliasResponseDto::new)
                .collect(Collectors.toList());
    }

    public LocationAliasDto.LocationAliasResponseDto getLocationAliasById(int id) {
        LocationAlias entity = locationAliasRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 장소 별칭이 없습니다. id=" + id));

        return new LocationAliasDto.LocationAliasResponseDto(entity);
    }
}