package no.tango.tangolib.dao;

import no.tango.tangolib.model.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {



}
