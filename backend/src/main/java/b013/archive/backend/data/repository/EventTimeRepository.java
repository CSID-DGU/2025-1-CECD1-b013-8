package b013.archive.backend.data.repository;

import b013.archive.backend.data.entity.EventTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface EventTimeRepository extends JpaRepository<EventTime, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO event_time(event_id, time_id) VALUES(?1, ?2)", nativeQuery = true)
    void saveByCompositeId(int event_id, int time_id);
}