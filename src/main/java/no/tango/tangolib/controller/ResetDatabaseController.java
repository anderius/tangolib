package no.tango.tangolib.controller;

import no.tango.tangolib.dao.*;
import no.tango.tangolib.model.*;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/reset")
public class ResetDatabaseController {

    @Autowired
    private SoundFileRepository soundFileRepository;

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private PerformanceContributionRepository performanceContributionRepository;

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private FlacFileDao flacFileDao;

    @Transactional(readOnly = false)
    @RequestMapping(method = RequestMethod.GET)
    public void reset() throws ReadOnlyFileException, IOException, TagException, InvalidAudioFrameException, CannotReadException {

        soundFileRepository.deleteAll();
        performanceRepository.deleteAll();
        workRepository.deleteAll();
        artistRepository.deleteAll();

        List<AudioFile> files = flacFileDao.getAllFiles();
        for (AudioFile audioFile : files) {

            SoundFile soundFile = new SoundFile();
            soundFile.setPath(audioFile.getFile().getAbsolutePath());
            soundFile.setLength((long) audioFile.getAudioHeader().getTrackLength());

            Tag tag = audioFile.getTag();

            Work work = getOrCreateWork(tag);

            Artist leader = getOrCreateArtist(tag);

            PerformanceContribution performanceContribution = getOrCreatePerformanceContribution(work, leader, PerformanceContributionType.LEADER);

            soundFile.setPerformance(performanceContribution.getPerformance());
            soundFile = soundFileRepository.save(soundFile);


            String singerNameFelt = tag.getFirst("SINGER");

            if (StringUtils.hasText(singerNameFelt)) {

                String[] singerNames = singerNameFelt.split(",");

                for (String singerName : singerNames) {

                    Artist singer = getOrCreateArtist(singerName);
                    PerformanceContribution singerContribution = getOrCreatePerformanceContribution(work, singer, PerformanceContributionType.SINGER);

                }
            }
        }
    }

    private PerformanceContribution getOrCreatePerformanceContribution(Work work, Artist artist, PerformanceContributionType type) {

        List<PerformanceContribution> performances =
                performanceContributionRepository.findByArtistAndPerformanceWorkAndType(artist, work, type);

        PerformanceContribution performanceContribution;

        if (performances.isEmpty()) {

            Performance performance = new Performance();
            performance.setWork(work);
            performance = performanceRepository.save(performance);

            performanceContribution = new PerformanceContribution();
            performanceContribution.setArtist(artist);
            performanceContribution.setType(type);
            performanceContribution.setPerformance(performance);
            performanceContribution = performanceContributionRepository.save(performanceContribution);
        } else if (performances.size() == 1) {
            performanceContribution = performances.get(0);
        } else {
            throw new RuntimeException("Multiple performance-contributions for the same work and leader");
        }
        return performanceContribution;
    }

    private Artist getOrCreateArtist(Tag tag) {
        String artistName = tag.getFirst(FieldKey.ARTIST);
        return getOrCreateArtist(artistName);
    }

    private Artist getOrCreateArtist(String artistName) {
        List<Artist> artists = artistRepository.findByName(artistName);

        Artist artist;

        if (artists.isEmpty()) {
            artist = new Artist();
            artist.setName(artistName);
            artist = artistRepository.save(artist);
        } else if (artists.size() == 1) {
            artist = artists.get(0);
        } else {
            throw new RuntimeException("Multiple artists with same name!! " + artistName);
        }
        return artist;
    }

    private Work getOrCreateWork(Tag tag) {
        String title = tag.getFirst(FieldKey.TITLE);

        List<Work> works = workRepository.findByTitle(title);

        Work work;

        if (works.isEmpty()) {
            work = new Work();
            work.setTitle(title);
            work = workRepository.save(work);
        } else if (works.size() == 1) {
            work = works.get(0);
        } else {
            throw new RuntimeException("Multiple works with same name!! " + title);
        }
        return work;
    }

}
