package b013.archive.backend.data.repository;

import b013.archive.backend.data.entity.EventTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTimeRepository extends JpaRepository<EventTime, Integer> {
}