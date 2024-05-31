package Model;

public class Thief extends Hero{
    private static Thief myUniqueInstance;
    private Thief(){
        super("Thief",75,6,0.8,40,20,10,10,
                0.4,20,40, 0.4);
    }


    @Override
    public void special(DungeonCharacter theOther) {
        //40 percent chance for surprise attack
        if(getMyRandomForSpecial() <= getMyChanceForSpecial()) {
            for (int i = 0; i < 2; i++) {
                int damage = (int) (Math.random() * (getMySpecialMax() - getMySpecialMin() + 1)) + getMySpecialMin();
                theOther.setMyHitPoints(theOther.getMyHitPoints() - damage);
                System.out.println("Surprise attack was successful for " + damage + " damage!");
            }

        //20 percent chance surprise attack is caught
        }else if(getMyRandomForSpecial() >= 0.8) {
            System.out.println("Surprise Attack was caught, better luck next time");
        //40 percent chance for one singular attack
        }else {
            int damage = (int) (Math.random() * (getMySpecialMax() - getMySpecialMin() + 1)) + getMySpecialMin();
            theOther.setMyHitPoints(theOther.getMyHitPoints() - damage);
            System.out.println("Surprise Attack was successful for " + damage + " damage!");
        }
      }

    public static Thief getMyUniqueInstance() {
        if (myUniqueInstance == null) {
            myUniqueInstance = new Thief();
        }
        return myUniqueInstance;
    }
    public static Thief Test_getMyUniqueInstance() {
        return new Thief();
    }
}

