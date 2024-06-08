package Model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Room class represents a room in the dungeon with various properties
 * such as items, monsters, entrance, and exit.
 */
public class Room {

    /**
     * The amount of healing potions in the room.
     */
    private int myHealingPotionAmount;

    /**
     *  The number of pits in the room.
     */
    private int myPitAmount;

    /**
     * Indicates if the room is an entrance.
     */
    private boolean myEntrance;

    /**
     * Indicates if the room is an exit.
     */
    private boolean myExit;

    /**
     * The x-coordinate of the room.
     */
    private int myX;

    /**
     * The y-coordinate of the room.
     */
    private int myY;

    /**
     * The type of item in the room.
     */
    private char myItem;

    /**
     * Indicates if the room has a vision potion.
     */
    private boolean myHasVisionPotion;

    /**
     * Indicates if the room has a pillar.
     */
    private boolean myHasPillar;

    /**
     * Indicates if the room has a monster.
     */
    private boolean myHasMonster;

    /**
     * The chance for a monster to appear in the room.
     */
    private double myChanceForMonster;

    /**
     * The name of the monster in the room.
     */
    private String myMonsterName;

    /**
     * The list of possible monster names.
     */
    private final static ArrayList<String> MONSTER_NAMES = new ArrayList<>(Arrays.asList("Ogre","Gremlin","Skeleton"));

    /**
     * The size of the room.
     */
    private int mySize;

    /**
     * The constructor for Room class.
     * @param theSize The size of the dungeon.
     */
    public Room(int theSize) {
        mySize = theSize;
        setMyChanceForMonster(Math.random());
        setUpRoom();

    }

    /**
     * This is the method sets up the room with random items and attributes.
     */
    private void setUpRoom() {
        double randomValue = Math.random();
        if (randomValue < 0.10) {
            // 10% chance for multiple items (healing potion, pit, and vision potion)
            setUpMultipleItems();
        }  else if (randomValue < 0.35) {
            // 10% chance for vision potion
            myHasVisionPotion = true;
            myItem = 'V';
        } else if (randomValue < 0.55) {
            // 20% chance for pit
            myPitAmount = (int) (Math.random() * 20) + 1;
            myItem = 'X';
        } else if(randomValue < 0.80){
            // 25% chance for healing potion
            myHealingPotionAmount = (int) (Math.random() * 11) + 10;
            myItem = 'H';
        } else{
            //20 percent chance for empty room
            myItem = ' ';
        }
        if(!myEntrance) {
            if (myChanceForMonster <= 0.15) {
                setMyHasMonster(true);
                setMyMonsterName(MONSTER_NAMES.get((int) (Math.random() * MONSTER_NAMES.size())));

            }
        }


    }

    /**
     * This is the method sets up the room with multiple items.
     */
    private void setUpMultipleItems() {
        myHealingPotionAmount = (int) (Math.random() * 11) + 10;
        myPitAmount = (int) (Math.random() * 20) + 10;
        myHasVisionPotion = true;
        setMyItem('M');
    }

    /**
     * This is the method sets the room as having a pillar.
     * @param theHasPillar Boolean indicating if the room has a pillar.
     */
    public void setMyHasPillar(boolean theHasPillar) {
        myHasPillar = theHasPillar;
    }

    /**
     * This is the method prints information of the room.
     * @return The string representation of the room.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if(myItem == ' '){
            myItem = 'N';
        }

        if(myY == 0){
            sb.append("* * * * * ").append("\n");
        } else{
                sb.append("* * D * * ").append("\n");

        }
        if(myX == 0){
            sb.append("* - ").append(myItem).append(" - D ").append("\n");
        }else if(myX == mySize - 1){
            sb.append("D - ").append(myItem).append(" - * ").append("\n");
        }else{
            sb.append("D - ").append(myItem).append(" - D ").append("\n");
        }
        if(myY== mySize- 1){
            sb.append("* * * * * ").append("\n");
        } else{
                sb.append("* * D * * ").append("\n");
        }
        return sb.toString();

    }


    /**
     * This is the method sets the amount of healing potions in the room.
     * @param theHealingPotion The amount of healing potions.
     */
    public void setMyHealingPotionAmount(final int theHealingPotion) {
        myHealingPotionAmount = theHealingPotion;
    }

    /**
     * This is the method sets the amount of pits in the room.
     * @param thePit The amount of pits.
     */
    public void setMyPitAmount(final int thePit) {
        myPitAmount = thePit;
    }

    /**
     * This is the method sets the room as an entrance.
     * @param theEntrance Boolean indicating if the room is an entrance.
     */
    public void setMyEntrance(boolean theEntrance) {
        myEntrance = theEntrance;
        myItem = 'i';
    }

    /**
     * This is the method sets the item in the room.
     * @param theItem The item character.
     */
    public void setMyItem(char theItem) {
        myItem = theItem;
    }

    /**
     * This is the method sets the Y coordinate of the room.
     * @param theY The Y coordinate.
     */
    public void setMyY(int theY) {
        myY = theY;
    }

    /**
     * This is the method sets the X coordinate of the room.
     * @param theX The X coordinate.
     */
    public void setMyX(int theX) {
        myX = theX;
    }

    /**
     * This is the method sets the room as an exit.
     * @param theExit Boolean indicating if the room is an exit.
     */
    public void setMyExit(boolean theExit) {
        myExit = theExit;
        myItem = 'o';
    }

    /**
     * This is the method sets whether the room has a vision potion.
     * @param theHasVisionPotion Boolean indicating if the room has a vision potion.
     */
    public void setMyVisionPotion(final boolean theHasVisionPotion) {
        myHasVisionPotion = theHasVisionPotion;
    }

    /**
     * This is the method gets the amount of healing potions in the room.
     * @return The amount of healing potions.
     */
    public int getMyHealingPotionAmount() {
        return myHealingPotionAmount;
    }

    /**
     * This is the method gets the amount of pits in the room.
     * @return The amount of pits.
     */
    public int getMyPitAmount() {
        return myPitAmount;
    }

    /**
     * This is a method checks if the room is an entrance.
     * @return Boolean indicating if the room is an entrance.
     */
    public boolean isMyEntrance() {
        return myEntrance;
    }

    /**
     * This is a method checks if the room is an exit.
     * @return Boolean indicating if the room is an exit.
     */
    public boolean isMyExit() {
        return myExit;
    }

    /**
     * This is a method gets the item in the room.
     * @return The item character.
     */
    public char getMyItem() {
        return myItem;
    }

    /**
     * This is a method checks if the room has a pillar.
     * @return Boolean indicating if the room has a pillar.
     */
    public boolean getMyHasPillar(){
        return myHasPillar;
    }

    /**
     * This is a method gets the X coordinate of the room.
     * @return The X coordinate.
     */
    public int getMyX() {
        return myX;
    }

    /**
     * This is a method gets the Y coordinate of the room.
     * @return The Y coordinate.
     */
    public int getMyY() {
        return myY;
    }

    /**
     * This is a method checks if the room has a vision potion.
     * @return Boolean indicating if the room has a vision potion.
     */
    public boolean getMyHasVisionPotion() {
        return myHasVisionPotion;
    }

    /**
     * This is a method gets a part of the room's string representation.
     * @param theRoom The room object.
     * @param thePart The part number.
     * @return The string representation of the specified part of the room.
     */
    public String getPartOfTheRoom(Room theRoom, int thePart) {
        String roomString = theRoom.toString();
        String[] lines = roomString.split("\n");

        if (thePart >= 0 && thePart < lines.length) {
            return lines[thePart];
        } else {
            return "Invalid part number";
        }
    }

    /**
     * This is a method sets the name of the monster in the room.
     * @param theMonsterName The name of the monster.
     */
    public void setMyMonsterName(String theMonsterName) {
        myMonsterName = theMonsterName;
    }

    /**
     * This is a method sets whether the room has a monster.
     * @param theHasMonster A boolean value indicating if the room has a monster.
     */
    public void setMyHasMonster(boolean theHasMonster) {
        myHasMonster = theHasMonster;
    }

    /**
     * This is a method retrieves the name of the monster in the room.
     * @return The name of the monster.
     */
    public String getMyMonsterName() {
        return myMonsterName;
    }

    /**
     * This is a method retrieves whether the room has a monster.
     * @return A boolean value indicating if the room has a monster.
     */
    public boolean getMyHasMonster() {
        return myHasMonster;
    }

    /**
     * This is a method retrieves the chance for a monster to appear in the room.
     * @return The chance for a monster to appear.
     */
    public double getMyChanceForMonster() {
        return myChanceForMonster;
    }

    /**
     * This is a method sets the chance for a monster to appear in the room.
     * @param theChanceForMonster The chance for a monster to appear.
     */
    public void setMyChanceForMonster(double theChanceForMonster) {
        myChanceForMonster = theChanceForMonster;
    }
}
