package no.tango.tangolib.controller;

import no.tango.tangolib.dao.PerformanceRepository;
import no.tango.tangolib.model.Performance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/performances")
public class PerformanceController {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Transactional(readOnly = true)
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Performance> performances() {
        return performanceRepository.findAll();
    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Performance performance(@PathVariable Long id) {
        return performanceRepository.findOne(id);
    }

}
