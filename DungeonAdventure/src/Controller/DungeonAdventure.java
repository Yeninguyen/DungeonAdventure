package Controller;

import Model.Dungeon;
import Model.DungeonCharacter;
import Model.MonsterDatabase;
import Model.Room;

import java.sql.SQLException;

public class DungeonAdventure {
    private Dungeon myDungeon;
    private DungeonCharacter myCharacter;
    private DungeonAdventure() {
        myDungeon = Model.Dungeon.getInstance();
    }

    public void battle(DungeonCharacter theOther){
        int numberOfAttacks = Math.min(myCharacter.getMyAttackSpeed()/ theOther.getMyAttackSpeed(),1);
        while(numberOfAttacks > 0){
            myCharacter.attack(theOther);
            numberOfAttacks--;
        }
    }
    public void setMyCharacter(DungeonCharacter theHero) {
        myCharacter = theHero;
    }
    public void placeMonsters() throws SQLException {
        MonsterDatabase monsterDatabase = MonsterDatabase.getMyUniqueInstance();
        String[] monsters = new String[]{"Gremlin","Ogre","Skeleton"};
        int monsterIndex = (int) (Math.random() * monsters.length);
        monsterDatabase.getMonster(monsters[monsterIndex]);
    }
    public void printCurrentRoom(){
        Room currentRoom = myDungeon.getMaze()[myCharacter.getMyX()][myCharacter.getMyY()];
        System.out.println(currentRoom);
    }
}
