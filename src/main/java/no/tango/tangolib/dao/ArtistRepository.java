package no.tango.tangolib.dao;

import no.tango.tangolib.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    List<Artist> findByName(String name);

    @Query("SELECT a FROM Artist a LEFT JOIN FETCH a.performanceContributions WHERE a.id = ?1")
    Artist findOneWithContributions(Long id);
}
