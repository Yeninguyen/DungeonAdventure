package Model;

import java.util.ArrayList;
import java.util.Arrays;

public class Room {
    private int myHealingPotionAmount;
    private int myPitAmount;
    private boolean myEntrance;
    private boolean myExit;
    private int myX;
    private int myY;
    private int myBorderSize;
    private char myItem;
    private boolean myHasVisionPotion;
    private boolean myHasPillar;
    private double myRandom;
    private double myChanceForMonster;
    private boolean myHasMonster;
    private String myMonsterName;
    private final static ArrayList<String> MONSTER_NAMES = new ArrayList<>(Arrays.asList("Ogre","Gremlin","Skeleton"));

    public void setMyMonsterName(String theMonsterName) {
        myMonsterName = theMonsterName;
    }

    public void setMyHasMonster(boolean theHasMonster) {
        myHasMonster = theHasMonster;
    }

    public String getMyMonsterName() {
        return myMonsterName;
    }

    public boolean getMyHasMonster() {
        return myHasMonster;
    }

    public double getMyChanceForMonster() {
        return myChanceForMonster;
    }

    public Room(int theBorderSize) {
        setMyChanceForMonster(Math.random());
        setMyRandom(Math.random());
        setMyBorderSize(theBorderSize);
        setUpRoom();

    }

    private void setMyChanceForMonster(double theChanceForMonster) {
        myChanceForMonster = theChanceForMonster;
    }

    private void setMyRandom(double theRandom) {
        myRandom = theRandom;
    }

    private void setUpRoom() {
        if (myRandom < 0.10) {
            // 10% chance for multiple items (healing potion, pit, and vision potion)
            setUpMultipleItems();
        }  else if (myRandom < 0.35) {
            // 10% chance for vision potion
            setMyVisionPotion(true);
            myItem = 'V';
        } else if (myRandom < 0.55) {
            // 20% chance for pit
            myPitAmount = (int) (Math.random() * 20) + 1;
            myItem = 'X';
        } else if(myRandom < 0.80){
            // 25% chance for healing potion
            myHealingPotionAmount = (int) (Math.random() * 11) + 10;
            myItem = 'H';
        } else{
            //20 percent chance for empty room
            myItem = 'N';
        }
        if(myChanceForMonster < 0.15 && !isMyEntrance()) {
            setMyHasMonster(true);
            setMyMonsterName(MONSTER_NAMES.get((int) (Math.random() * MONSTER_NAMES.size())));

        }


    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();


        if(myY == 0){
            sb.append("* * * * * ").append("\n");
        } else{
                sb.append("* * D * * ").append("\n");

        }
        if(myX == 0){
            sb.append("* - ").append(myItem).append(" - D ").append("\n");
        }else if(myX == myBorderSize - 1){
            sb.append("D - ").append(myItem).append(" - * ").append("\n");
        }else{
            sb.append("D - ").append(myItem).append(" - D ").append("\n");
        }
        if(myY== myBorderSize - 1){
            sb.append("* * * * * ").append("\n");
        } else{
                sb.append("* * D * * ").append("\n");
        }
        return sb.toString();

    }

    private void setUpMultipleItems() {
        myHealingPotionAmount = (int) (Math.random() * 11) + 10;
        myPitAmount = (int) (Math.random() * 20) + 10;
        myHasVisionPotion = true;
        setMyItem('M');
    }

    public void setMyHasPillar(final boolean theHasPillar) {
        myHasPillar = theHasPillar;
    }

    public void setMyEntrance(final boolean theEntrance) {
        myEntrance = theEntrance;
        myItem = 'i';
    }

    public void setMyItem(final char theItem) {
            myItem = theItem;
    }

    public void setMyY(final int theY) {
        if(theY < 0){
            throw new IllegalArgumentException("The Y value cannot be negative.");
        }else{
            myY = theY;
        }
    }

    public void setMyX(final int theX) {
        if(theX < 0){
            throw new IllegalArgumentException("The X value cannot be negative.");
        }else{
            myX = theX;
        }
    }

    public void setMyExit(final boolean theExit) {
        myExit = theExit;
        myItem = 'o';
    }

    public void setMyVisionPotion(final boolean theHasVisionPotion) {
        myHasVisionPotion = theHasVisionPotion;
    }
    public void setMyBorderSize(int theBorderSize) {
        if(theBorderSize < 0){
            throw new IllegalArgumentException("The border size cannot be negative.");
        }else {
            myBorderSize = theBorderSize;
        }
    }

    public boolean isMyEntrance() {
        return myEntrance;
    }


    public boolean isMyExit() {
        return myExit;
    }

    public char getMyItem() {
        return myItem;
    }
    public boolean getMyHasPillar(){
        return myHasPillar;
    }

    public int getMyX() {
        return myX;
    }

    public int getMyY() {
        return myY;
    }
    public boolean getMyHasVisionPotion() {
        return myHasVisionPotion;
    }

    public int getMyPitAmount() {
        return myPitAmount;
    }

    public int getMyHealingPotionAmount() {
        return myHealingPotionAmount;
    }
    public int getMyBorderSize() {
        return myBorderSize;
    }

    public String getPartOfTheRoom(Room theRoom, int thePart) {
        String roomString = theRoom.toString();
        String[] lines = roomString.split("\n");

        if (thePart >= 0 && thePart < lines.length) {
            return lines[thePart];
        } else {
            return "Invalid part number";
        }
    }

}
