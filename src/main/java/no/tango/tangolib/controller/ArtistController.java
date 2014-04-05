package no.tango.tangolib.controller;

import no.tango.tangolib.controller.dto.ArtistExternalInformation;
import no.tango.tangolib.dao.ArtistRepository;
import no.tango.tangolib.dao.PerformanceContributionRepository;
import no.tango.tangolib.model.Artist;
import no.tango.tangolib.model.PerformanceContribution;
import no.tango.tangolib.model.SoundFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private PerformanceContributionRepository performanceContributionRepository;

    @Transactional(readOnly = true)
    @RequestMapping(method = RequestMethod.GET)
    public List<Artist> artists() {
        return artistRepository.findAll();
    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Artist artist(@PathVariable Long id) {
        return artistRepository.findOne(id);
    }

    @Transactional(readOnly = false)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        artistRepository.delete(id);
    }

    @Transactional(readOnly = false)
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Artist create(@RequestBody Artist artist) {
        return artistRepository.save(artist);
    }

    @Transactional(readOnly = false)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Artist update(@RequestBody Artist artist) {
        return artistRepository.save(artist);
    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "/{id}/withContributions", method = RequestMethod.GET)
    public Artist artistWithContributions(@PathVariable Long id) {
        return artistRepository.findOneWithContributions(id);
    }

    @Transactional(readOnly = true)
    @RequestMapping(value = "/{id}/contribution", method = RequestMethod.GET)
    public List<PerformanceContribution> artistContributions(@PathVariable Long id) {
        return performanceContributionRepository.findByArtistId(id);
    }

    @RequestMapping(value = "{id}/fetch", method = RequestMethod.GET)
    public List<ArtistExternalInformation> externalInformation(@PathVariable Long id) {

        List<ArtistExternalInformation> liste = new ArrayList<ArtistExternalInformation>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD");

        // dummy data
        ArtistExternalInformation info1 = new ArtistExternalInformation();
        info1.setSource("DUMMY");
        try {
            info1.setBirth(dateFormat.parse("1903-01-07"));
            info1.setDeath(dateFormat.parse("1960-01-12"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        info1.setName("Carlos DiDi Sarli");
        info1.setOtherNames(Collections.singletonList("Cayetano Di Sarli"));
        liste.add(info1);

        ArtistExternalInformation info2 = new ArtistExternalInformation();
        info2.setSource("DUMMY2");
        try {
            info2.setDeath(dateFormat.parse("1960-01-12"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        info2.setName("Carlos di Sarli");
        info2.setOtherNames(Arrays.asList("Cayetano Di Sarli", "The piano man"));
        liste.add(info2);
        return liste;
    }
}
