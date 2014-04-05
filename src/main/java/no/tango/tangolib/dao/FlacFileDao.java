package no.tango.tangolib.dao;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FlacFileDao {

    private File rootFolder = new File("c:/tangolib");
    private File[] files = rootFolder.listFiles();

    public List<AudioFile> getAllFiles() throws TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException, IOException {
        List<AudioFile> soundFiles = new ArrayList<AudioFile>(files.length);

        for (File file : files) {
            AudioFile f = AudioFileIO.read(file);
            soundFiles.add(f);
        }
        return soundFiles;
    }

}
