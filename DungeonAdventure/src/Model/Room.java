package Model;



public class Room {
    private int myHealingPotionAmount;
    private int myPitAmount;
    private boolean myEntrance;
    private boolean myExit;
    private String myPillar;
    private int myX;
    private int myY;
    private String myItem;
    private boolean myVisionPotionAmount;


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
        } else if (randomValue < 0.10) {
            // 5% chance for exit
            myExit = true;
        } else if (randomValue < 0.20) {
            // 10% chance for pillar
            setUpPillar();
        } else if (randomValue < 0.35) {
            // 15% chance for vision potion
            myVisionPotionAmount = true;
            myItem = "V";
        } else if (randomValue < 0.55) {
            // 20% chance for pit
            myPitAmount = (int) (Math.random() * 20) + 1;
            myItem = "X";
        } else {
            // 45% chance for healing potion
            myHealingPotionAmount = (int) (Math.random() * 11) + 10;
            myItem = "H";
        }

        setLocation();
    }

    private void setLocation() {
        if (myEntrance) {
            myX = 0;
            myY = 0;
        } else if (myExit) {
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
        myHealingPotionAmount = (int) (Math.random() * 11) + 10;
        myPitAmount = (int) (Math.random() * 20) + 10;
        myVisionPotionAmount = true;
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
            if(myEntrance){
                sb.append("*").append("i").append("|").append("\n");
            } else if(myExit){
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



    public void setMyHealingPotionAmount(final int theHealingPotion) {
        myHealingPotionAmount = theHealingPotion;
    }
    public void setMyPitAmount(final int thePit) {
        myPitAmount = thePit;
    }

    public void setMyEntrance(boolean theEntrance) {
        myEntrance = theEntrance;
    }

    public void setExit(boolean theExit) {
        myExit = theExit;
    }

    public void setMyPillar(final String thePillar) {
        myPillar = thePillar;
    }

    public void setMyItem(String theItem) {
        myItem = theItem;
    }


    public void setMyY(int theY) {
        myY = theY;
    }

    public void setMyX(int theX) {
        myX = theX;
    }
    public void setMyExit(boolean theExit) {
        myExit = theExit;
    }

    public void setMyVisionPotionAmount(final boolean theVisionPotion) {
        myVisionPotionAmount = theVisionPotion;
    }

    public int getMyHealingPotionAmount() {
        return myHealingPotionAmount;
    }

    public int getMyPitAmount() {
        return myPitAmount;
    }

    public boolean isMyEntrance() {
        return myEntrance;
    }


    public boolean isMyExit() {
        return myExit;
    }


    public String getMyPillar() {
        return myPillar;
    }

    public String getMyItem() {
        return myItem;
    }


    public int getMyX() {
        return myX;
    }

    public int getMyY() {
        return myY;
    }


    public boolean isMyVisionPotionAmount() {
        return myVisionPotionAmount;
    }

    public static void main(final String[] args){
        for (int i = 0; i < 10; i++) {
            Room room = new Room();
            System.out.println(room);
        }
    }
}

