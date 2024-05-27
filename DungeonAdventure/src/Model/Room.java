package Model;

public class Room {
    private int myHealingPotionAmount;
    private int myPitAmount;
    private boolean myEntrance;
    private boolean myExit;
    private int myX;
    private int myY;
    private char myItem;
    private boolean myHasVisionPotion;
    private boolean myHasPillar;


    public Room() {
        setUpRoom();
    }

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


    }


    private void setUpMultipleItems() {
        myHealingPotionAmount = (int) (Math.random() * 11) + 10;
        myPitAmount = (int) (Math.random() * 20) + 10;
        myHasVisionPotion = true;
        setMyItem('M');
    }

    public void setMyHasPillar(boolean theHasPillar) {
        myHasPillar = theHasPillar;
    }

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
        }else if(myX == Dungeon.SIZE - 1){
            sb.append("D - ").append(myItem).append(" - * ").append("\n");
        }else{
            sb.append("D - ").append(myItem).append(" - D ").append("\n");
        }
        if(myY== Dungeon.SIZE - 1){
            sb.append("* * * * * ").append("\n");
        } else{
                sb.append("* * D * * ").append("\n");
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
        myItem = 'i';
    }


    public void setMyItem(char theItem) {
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
        myItem = 'o';
    }

    public void setMyVisionPotion(final boolean theHasVisionPotion) {
        myHasVisionPotion = theHasVisionPotion;
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
