package Controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class GameSounds {
    public GameSounds() {

    }

    public void playClickSound(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/Sounds/clicksound.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
