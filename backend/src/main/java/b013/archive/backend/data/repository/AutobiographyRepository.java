package b013.archive.backend.data.repository;

import b013.archive.backend.data.entity.Autobiography;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutobiographyRepository extends JpaRepository<Autobiography, Integer> {
}