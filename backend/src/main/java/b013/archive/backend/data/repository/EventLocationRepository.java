package b013.archive.backend.data.repository;

import b013.archive.backend.data.entity.EventLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventLocationRepository extends JpaRepository<EventLocation, Integer> {
}