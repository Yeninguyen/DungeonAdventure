package Controller;

import Model.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;

public class DungeonAdventure {


    DungeonAdventure() {
    }

    public void battle(DungeonCharacter theCharacter, DungeonCharacter theOther){
        int numberOfAttacks = Math.min(theCharacter.getMyAttackSpeed()/ theOther.getMyAttackSpeed(),1);
        while(numberOfAttacks > 0){
            theCharacter.attack(theOther);
            numberOfAttacks--;
        }
    }

}