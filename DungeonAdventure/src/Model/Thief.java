package Model;

public class Thief extends Hero{
    private static Thief myUniqueInstance;
    private Thief(){
        super("Thief",75,6,0.8,40,20,10,10,
                0.4,20,40, 0.4);
    }


    @Override
    protected void special(DungeonCharacter theOther) {
        double random = Math.random() + 0.1;
        //40 percent chance for surprise attack
        if (random <= getMyChanceForSpecial()) {
            for (int i = 0; i < 2; i++) {
                int damage = (int) (Math.random() * (getMySpecialMax() - getMySpecialMin() + 1)) + getMySpecialMin();
                theOther.setMyHitPoints(theOther.getMyHitPoints() - damage);
            }

            //40 percent chance surprise attack is caught
        } else if (random >= 0.8) {
            int damage = (int) (Math.random() * (getMySpecialMax() - getMySpecialMin() + 1)) + getMySpecialMin();
            theOther.setMyHitPoints(theOther.getMyHitPoints() - damage);


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

