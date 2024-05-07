package Model;

public class Room {
    private int myHealingPotion;
    private int myPit;
    private boolean myEntrance;

    private boolean myExit;

    private String myPillar;
    private boolean myVisionPotion;


    public Room(final int theHealingPotion, final int thePit, final boolean theEntrance,
                String thePillar, final boolean theVisionPotion) {

        myHealingPotion = theHealingPotion;
        myPit = thePit;
        myEntrance = theEntrance;
        myPillar = thePillar;
        myVisionPotion = theVisionPotion;
        myHealingPotion = theHealingPotion;
        myPit = thePit;
        myEntrance = theEntrance;
        myPillar = thePillar;
        myVisionPotion = theVisionPotion;

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

    public boolean isMyVisionPotion() {
        return myVisionPotion;
    }

    public void setMyVisionPotion(final boolean theVisionPotion) {
        myVisionPotion = theVisionPotion;
    }
}
