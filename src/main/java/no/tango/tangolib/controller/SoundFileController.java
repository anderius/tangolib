package no.tango.tangolib.controller;

import no.tango.tangolib.dao.SoundFileRepository;
import no.tango.tangolib.model.SoundFile;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/files")
public class SoundFileController {

    @Autowired
    private SoundFileRepository soundFileRepository;

    @Transactional(readOnly = true)
    @RequestMapping(method = RequestMethod.GET)
    public List<SoundFile> files() {
        return soundFileRepository.findAll();
    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public SoundFile file(@PathVariable Long id) {
        return soundFileRepository.findOne(id);
    }

    @Transactional(readOnly = false)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        soundFileRepository.delete(id);
    }

    @Transactional(readOnly = false)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public SoundFile create(@RequestBody SoundFile soundFile) {
        return soundFileRepository.save(soundFile);
    }

    @Transactional(readOnly = false)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public SoundFile update(@RequestBody SoundFile soundFile) {
        return soundFileRepository.save(soundFile);
    }

}
