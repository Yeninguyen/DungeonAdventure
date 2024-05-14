package Model;

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
        double chanceForHealingPotion = Math.random() + 0.1;
        double chanceForPit = Math.random() + 0.1;
        double chanceForEntrance = Math.random() + 0.1;
        double chanceForPillar = Math.random() + 0.1;
        double chanceForVisionPotion = Math.random() + 0.1;
        double chanceForExit = Math.random() + 0.1;
        if(chanceForHealingPotion <= 0.15 && chanceForPit <= 0.15
            && chanceForVisionPotion <= 0.15){
            setUpMultipleItems();
        } else if(chanceForExit <= 0.1){
            myExit = true;
            myHasExit = true;
        } else if(chanceForEntrance <= 0.1){
            myEntrance = true;
            myHasEntrance = true;
        } else if(chanceForPillar <= 0.1){
            setUpPillar();
        } else if(chanceForVisionPotion <= 0.25){
            myVisionPotion = true;
            myItem = "V";
        } else if(chanceForPit <= 0.25){
           myPit = (int) (Math.random() * 20) + 1;
           myItem = "X";
        }else if(chanceForHealingPotion <= 0.25){
            myHealingPotion = (int) (Math.random() * 11) + 10;
            myItem = "H";
        } else{
            myItem = " ";
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
                sb.append("*").append(myItem).append("|").append("\n");
            } else if(myHasExit){
                sb.append("|").append(myItem).append("*").append("\n");
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

