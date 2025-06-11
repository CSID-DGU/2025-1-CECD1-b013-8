package b013.archive.backend.data.repository;

import b013.archive.backend.data.entity.EventPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface EventPersonRepository extends JpaRepository<EventPerson, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO event_person(event_id, person_id) VALUES(?1, ?2)", nativeQuery = true)
    void saveByCompositeId(int event_id, int person_id);
}