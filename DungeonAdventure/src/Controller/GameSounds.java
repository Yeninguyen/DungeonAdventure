package Controller;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class GameSounds {

    private final String myButtonClickedSound = "clicksound.wav";
    private final String myWarriorAttackSound = "warriorAttack.wav";
    private final String myLoseSound = "loser.wav";
    private final String myWinSound = "winner.wav";
    public GameSounds() {

    }

    public void playClickSound(final int theSoundNumber){
        String filePath = "";
        switch (theSoundNumber) {
            case 1 -> filePath = myButtonClickedSound;
            case 2 -> filePath = myWarriorAttackSound;
            case 3 -> filePath = myWinSound;
            case 4 -> filePath = myLoseSound;
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