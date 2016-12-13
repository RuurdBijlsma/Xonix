package xonix.Model;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Ruurd on 13-12-2016.
 */
public class SoundManager {
    private static SoundManager instance = null;
    private HashMap<Sound, String> soundStringHashMap;

    private SoundManager() {
        initializeDictionary();
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    private void initializeDictionary() {
        soundStringHashMap = new HashMap<>();
        soundStringHashMap.put(Sound.CARHIT, "sound/hit.wav");
        soundStringHashMap.put(Sound.LEVELUP, "sound/levelup.wav");
        soundStringHashMap.put(Sound.GAMEOVER, "sound/gameover.wav");
        soundStringHashMap.put(Sound.TIMETICKET, "sound/timeticket.wav");
    }

    public void play(Sound sound) {
        String fileString = soundStringHashMap.get(sound);
        File file = new File(fileString);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }
}
