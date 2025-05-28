package b013.archive.backend.data.repository;

import b013.archive.backend.data.entity.LocationAlias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationAliasRepository extends JpaRepository<LocationAlias, Integer> {
}