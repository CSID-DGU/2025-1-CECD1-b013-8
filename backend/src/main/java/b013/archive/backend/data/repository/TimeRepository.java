package b013.archive.backend.data.repository;

import b013.archive.backend.data.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<Time, Integer> {
}