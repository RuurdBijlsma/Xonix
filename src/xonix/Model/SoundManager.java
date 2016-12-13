package xonix.Model;

/**
 * Created by Ruurd on 13-12-2016.
 */
public class SoundManager {
    private static SoundManager instance = null;
    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    private SoundManager(){

    }
}
