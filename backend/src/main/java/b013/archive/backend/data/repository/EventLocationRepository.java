package b013.archive.backend.data.repository;

import b013.archive.backend.data.entity.EventLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface EventLocationRepository extends JpaRepository<EventLocation, Integer> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO event_location(event_id, locationt_id) VALUES(?1, ?2)", nativeQuery = true)
    void saveByCompositeId(int event_id, int location_id);
}