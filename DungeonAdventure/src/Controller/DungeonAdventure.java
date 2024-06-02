package Controller;

import Model.Dungeon;
import Model.DungeonCharacter;
import Model.MonsterDatabase;
import Model.Room;

import java.sql.SQLException;

public class DungeonAdventure {
    private Dungeon myDungeon;
    private DungeonCharacter myHero;
    private DungeonAdventure() {
        myDungeon = Dungeon.getInstance();
    }
    public void setMyHero(DungeonCharacter theHero) {
        myHero = theHero;
    }
    public void battle(DungeonCharacter theOther){
        int numberOfAttacks = myHero.getMyAttackSpeed()/ theOther.getMyAttackSpeed();
        while(numberOfAttacks > 0){
            myHero.attack(theOther);
            numberOfAttacks--;
        }
    }
    public void placeMonsters() throws SQLException {
        MonsterDatabase monsterDatabase = MonsterDatabase.getMyUniqueInstance();
        String[] monsters = new String[]{"Gremlin","Ogre","Skeleton"};
        int monsterIndex = (int) (Math.random() * monsters.length);
        monsterDatabase.getMonster(monsters[monsterIndex]);
    }
    public void printCurrentRoom(){
        Room currentRoom = myDungeon.getMaze()[myHero.getMyX()][myHero.getMyY()];
        System.out.println(currentRoom);
    }
}