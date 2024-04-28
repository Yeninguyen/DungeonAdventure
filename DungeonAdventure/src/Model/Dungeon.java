package Model;

import java.util.Arrays;

public class Dungeon {
    private Room[][] myDungeon;
    private int myAdventurerLocation;

    public Dungeon() {
    }

    private boolean isTransversable(Room[][] theRoom){
        return false;
    }

    public Room[][] getMyDungeon() {
        return myDungeon;
    }

    public void setMyDungeon(final Room[][] theDungeon) {
        myDungeon = theDungeon;
    }

    public int getMyAdventurerLocation() {
        return myAdventurerLocation;
    }

    public void setMyAdventurerLocation(final int theAdventurerLocation) {
        myAdventurerLocation = theAdventurerLocation;
    }

    private void placeExit(){

    }

    private void placeEntrance(){

    }

    private void placePillars(){

    }

    @Override
    public String toString() {
        return "Dungeon{" +
                "myDungeon=" + Arrays.toString(myDungeon) +
                ", myAdventurerLocation=" + myAdventurerLocation +
                '}';
    }
}
