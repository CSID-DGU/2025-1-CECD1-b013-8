package b013.archive.backend.data.repository;

import b013.archive.backend.data.entity.PersonAlias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonAliasRepository extends JpaRepository<PersonAlias, Integer> {
}