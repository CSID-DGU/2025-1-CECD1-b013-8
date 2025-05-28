package b013.archive.backend.data.repository;

import b013.archive.backend.data.entity.EventPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventPersonRepository extends JpaRepository<EventPerson, Integer> {
}