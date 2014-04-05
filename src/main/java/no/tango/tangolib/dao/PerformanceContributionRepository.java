package no.tango.tangolib.dao;

import no.tango.tangolib.model.Artist;
import no.tango.tangolib.model.PerformanceContribution;
import no.tango.tangolib.model.PerformanceContributionType;
import no.tango.tangolib.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerformanceContributionRepository extends JpaRepository<PerformanceContribution, Long> {

    public List<PerformanceContribution> findByArtistAndPerformanceWorkAndType(Artist artist, Work work,
                                                                    PerformanceContributionType type);

    public List<PerformanceContribution> findByArtistId(Long artistId);

}
