package b013.archive.backend.service;

import b013.archive.backend.data.dto.AutobiographyDto;
import b013.archive.backend.data.entity.Autobiography;
import b013.archive.backend.data.repository.AutobiographyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutobiographyService {
    @Autowired
    private AutobiographyRepository autobiographyRepository;

    public List<AutobiographyDto.AutobiographyResponseDto> getAllAutobiography() {
        List<Autobiography> autobiography = autobiographyRepository.findAll();

        return autobiography.stream()
                .map(AutobiographyDto.AutobiographyResponseDto::new)
                .collect(Collectors.toList());
    }

    public AutobiographyDto.AutobiographyResponseDto getAutobiographyById(int id) {
        Autobiography entity = autobiographyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 자서전 정보가 없습니다. id=" + id));

        return new AutobiographyDto.AutobiographyResponseDto(entity);
    }
}