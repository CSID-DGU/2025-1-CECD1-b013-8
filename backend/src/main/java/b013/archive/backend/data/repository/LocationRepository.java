package b013.archive.backend.data.repository;

import b013.archive.backend.data.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
}