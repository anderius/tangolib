package no.tango.tangolib.dao;

import no.tango.tangolib.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {

    List<Work> findByTitle(String title);

    @Query("SELECT w FROM Work w LEFT JOIN FETCH w.performances WHERE w.id = ?1")
    Work findOneWithPerformances(Long id);
}
