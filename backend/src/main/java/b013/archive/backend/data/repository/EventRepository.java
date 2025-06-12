package b013.archive.backend.data.repository;

import b013.archive.backend.data.dto.EventSearchDto;
import b013.archive.backend.data.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query(value = """
        SELECT
                    e.id AS event_id,
                    e.name AS event_name,
                    e.page AS event_page,
            
                    CAST(IFNULL(p.id, 0) AS SIGNED) AS person_id,
                    p.name AS person_name,
                    p.description AS person_description,
            
                    CAST(IFNULL(l.id, 0) AS SIGNED) AS location_id,
                    l.name AS location_name,
                    l.description AS location_description
            
                FROM event e
                JOIN autobiography a ON e.autobiography_id = a.id
            
                -- 사건-인물
                LEFT JOIN event_person ep ON ep.event_id = e.id
                LEFT JOIN person p ON p.id = ep.person_id
            
                -- 사건-장소
                LEFT JOIN event_location el ON el.event_id = e.id
                LEFT JOIN location l ON l.id = el.location_id
            
                -- 사건-시간
                LEFT JOIN event_time et ON et.event_id = e.id
                LEFT JOIN time t ON t.id = et.time_id
            
                -- 필터링 대상 사건만 선별
                WHERE a.id = 1
                  AND e.id IN (
                      SELECT DISTINCT e_sub.id
                      FROM event e_sub
                      JOIN autobiography a_sub ON e_sub.autobiography_id = a_sub.id
            
                      LEFT JOIN event_person ep_sub ON ep_sub.event_id = e_sub.id
                      LEFT JOIN person p_sub ON p_sub.id = ep_sub.person_id
                      LEFT JOIN person_alias pa_sub ON pa_sub.person_id = p_sub.id
            
                      LEFT JOIN event_location el_sub ON el_sub.event_id = e_sub.id
                      LEFT JOIN location l_sub ON l_sub.id = el_sub.location_id
                      LEFT JOIN location_alias la_sub ON la_sub.location_id = l_sub.id
            
                      WHERE a_sub.id = :autobiographyId
                        AND (
                            e_sub.name LIKE CONCAT('%', :keyword, '%') COLLATE utf8mb4_general_ci OR
                            p_sub.name LIKE CONCAT('%', :keyword, '%') COLLATE utf8mb4_general_ci OR
                            pa_sub.name LIKE CONCAT('%', :keyword, '%') COLLATE utf8mb4_general_ci OR
                            l_sub.name LIKE CONCAT('%', :keyword, '%') COLLATE utf8mb4_general_ci OR
                            la_sub.name LIKE CONCAT('%', :keyword, '%') COLLATE utf8mb4_general_ci
                        )
                  )
                ORDER BY e.id;
    """, nativeQuery = true)
    List<EventSearchDto> findEventsByKeyword(
            @Param("autobiographyId") Long autobiographyId,
            @Param("keyword") String keyword
    );
}