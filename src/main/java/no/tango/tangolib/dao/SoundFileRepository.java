package no.tango.tangolib.dao;

import no.tango.tangolib.model.SoundFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoundFileRepository extends JpaRepository<SoundFile, Long> {
}
