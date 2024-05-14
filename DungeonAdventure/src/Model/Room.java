package Model;

import java.util.Random;

public class Room {
    private int myHealingPotion;
    private int myPit;
    private boolean myEntrance;
    private boolean myExit;
    private String myPillar;
    private boolean myVisionPotion;
    private int myX;
    private int myY;
    private boolean myHasEntrance;
    private boolean myHasExit;
    private String myItem;



    public Room() {
        setUpRoom();
    }

    private void setUpRoom() {
        double randomValue = Math.random();
        double chanceForHealingPotion = Math.random();
        double chanceForPit = Math.random();
        double chanceForVisionPotion = Math.random();

        if (chanceForHealingPotion <= 0.15 && chanceForPit <= 0.15 && chanceForVisionPotion <= 0.15) {
            // 15% chance for multiple items (healing potion, pit, and vision potion)
            setUpMultipleItems();
        } else if (randomValue < 0.05) {
            // 5% chance for entrance
            myEntrance = true;
            myHasEntrance = true;
        } else if (randomValue < 0.10) {
            // 5% chance for exit
            myExit = true;
            myHasExit = true;
        } else if (randomValue < 0.20) {
            // 10% chance for pillar
            setUpPillar();
        } else if (randomValue < 0.35) {
            // 15% chance for vision potion
            myVisionPotion = true;
            myItem = "V";
        } else if (randomValue < 0.55) {
            // 20% chance for pit
            myPit = (int) (Math.random() * 20) + 1;
            myItem = "X";
        } else {
            // 45% chance for healing potion
            myHealingPotion = (int) (Math.random() * 11) + 10;
            myItem = "H";
        }

        setLocation();
    }

    private void setLocation() {
        if (myHasEntrance) {
            myX = 0;
            myY = 0;
        } else if (myHasExit) {
            myX = 16;
            myY = 16;
        } else {
            do {
                myX = (int) (Math.random() * 17);
                myY = (int) (Math.random() * 17);
            } while ((myX == 0 && myY == 0) || (myX == 16 && myY == 16));
        }
    }

    private void setUpPillar() {
        String[]pillars = new String[]{"Polymorphism","Encapsulation","Abstraction","Inheritance"};
        int randomIndex = (int) (Math.random() * pillars.length);
        myPillar = pillars[randomIndex];
        myItem = "P";
    }

    private void setUpMultipleItems() {
        myHealingPotion = (int) (Math.random() * 11) + 10;
        myPit = (int) (Math.random() * 20) + 10;
        myVisionPotion = true;
        myItem = "M";
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
            if(myX == 0){
                sb.append("***").append("\n");
            } else{
                sb.append("*_*").append("\n");
            }
            if(myHasEntrance){
                sb.append("*").append("i").append("|").append("\n");
            } else if(myHasExit){
                sb.append("|").append("o").append("*").append("\n");
            } else{
                sb.append("|").append(myItem).append("|").append("\n");
            }
            if(myY==16){
                sb.append("***");
            } else{
                sb.append("*_*");
            }
            return sb.toString();

    }

    public int getMyHealingPotion() {
        return myHealingPotion;
    }

    public void setMyHealingPotion(final int theHealingPotion) {
        myHealingPotion = theHealingPotion;
    }

    public int getMyPit() {
        return myPit;
    }

    public void setMyPit(final int thePit) {
        myPit = thePit;
    }

    public boolean isMyEntrance() {
        return myEntrance;
    }

    public void setMyEntrance(final boolean theEntrance) {
        myEntrance = theEntrance;
    }

    public boolean isMyExit() {
        return myExit;
    }

    public void setExit(final boolean theExit) {
        myExit = theExit;
    }


    public String getMyPillar() {
        return myPillar;
    }

    public void setMyPillar(final String thePillar) {
        myPillar = thePillar;
    }

    public boolean ismyHasEntrance() {
        return myHasEntrance;
    }

    public String getMyItem() {
        return myItem;
    }

    public void setMyItem(String theItem) {
        myItem = theItem;
    }

    public boolean ismyHasExit() {
        return myHasExit;
    }

    public void setmyHasExit(boolean themyHasExit) {
        myHasExit = themyHasExit;
    }

    public void setmyHasEntrance(boolean themyHasEntrance) {
        myHasEntrance = themyHasEntrance;
    }

    public int getMyX() {
        return myX;
    }

    public int getMyY() {
        return myY;
    }

    public void setMyY(int theY) {
        myY = theY;
    }

    public void setMyX(int theX) {
        myX = theX;
    }

    public boolean isMyVisionPotion() {
        return myVisionPotion;
    }

    public void setMyExit(boolean theExit) {
        myExit = theExit;
    }

    public void setMyVisionPotion(final boolean theVisionPotion) {
        myVisionPotion = theVisionPotion;
    }
    public static void main(String[] args){
        for (int i = 0; i < 10; i++) {
            Room room = new Room();
            System.out.println(room);
        }
    }
}

