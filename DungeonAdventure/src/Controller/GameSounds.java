package Controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class GameSounds {

    private String myButtonClickedSound = "clicksound.wav";
    private String myWarriorAttackSound = "warriorAttack.wav";
    public GameSounds() {

    }



    public void playClickSound(int theSoundNumber){
        String filePath = "";
        switch (theSoundNumber) {
            case 1 -> filePath = myButtonClickedSound;
            case 2 -> filePath = myWarriorAttackSound;
        }
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("DungeonAdventure/src/Sounds/" + filePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}