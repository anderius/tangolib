package no.tango.tangolib.controller;

import no.tango.tangolib.dao.WorkRepository;
import no.tango.tangolib.model.Artist;
import no.tango.tangolib.model.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/works")
public class WorkController {

    @Autowired
    private WorkRepository workRepository;

    @Transactional(readOnly = true)
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Work> works() {
        return workRepository.findAll();
    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Work work(@PathVariable Long id) {
        return workRepository.findOne(id);
    }

    @Transactional(readOnly = false)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        workRepository.delete(id);
    }

    @Transactional(readOnly = false)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Work create(@RequestBody Work work) {
        return workRepository.save(work);
    }

    @Transactional(readOnly = false)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Work update(@RequestBody Work work) {
        return workRepository.save(work);
    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "/{id}/withPerformances", method = RequestMethod.GET)
    public Work workWithPerformances(@PathVariable Long id) {
        return workRepository.findOneWithPerformances(id);
    }
}
