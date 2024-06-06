package Controller;

import Model.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;

public class DungeonAdventure implements PropertyChangeListener {
    private Dungeon myDungeon;
    private final GameUI myGameUi;


    //private DungeonCharacter myCharacter;
    DungeonAdventure(final GameUI theGameUi) {
        myGameUi = theGameUi;
        myDungeon = myGameUi.getMyDungeon();
       // setMyCharacter(myGameUi.getMyCharacter().getCharacterType());
    }

    public void battle(DungeonCharacter theCharacter, DungeonCharacter theOther){
        int numberOfAttacks = Math.min(theCharacter.getMyAttackSpeed()/ theOther.getMyAttackSpeed(),1);
        while(numberOfAttacks > 0){
            theCharacter.attack(theOther);
            numberOfAttacks--;
        }
    }
//    public void battle(DungeonCharacter theOther, Monster theMonster){
//        int numberOfAttacks = Math.min(theMonster.getMyAttackSpeed()/ theOther.getMyAttackSpeed(),1);
//        while(numberOfAttacks > 0){
//            theMonster.attack(theOther);
//            numberOfAttacks--;
//        }
//    }

//    public void setMyCharacter(DungeonCharacter theHero) {
//        myCharacter = theHero;
//    }
//    public void printCurrentRoom(){
//        Room currentRoom = myDungeon.getMaze()[myCharacter.getMyX()][myCharacter.getMyY()];
//        System.out.println(currentRoom);
//    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}