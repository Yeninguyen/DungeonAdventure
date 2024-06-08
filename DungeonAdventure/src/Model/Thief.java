package Model;


/**
 * The Thief class extends Hero class, it represents a type of Hero character,
 * specializing in agility and surprise attacks.
 */
public class Thief extends Hero{

    /**
     * This is the unique instance field of the Thief.
     */
    private static Thief myUniqueInstance;

    /**
     * Private constructor to enforce the singleton pattern.
     */
    private Thief(){
        super("Thief",75,6,0.8,40,20,10,10,
                0.4,20,40, 0.4);
    }

    /**
     * The special method overrides from the Hero class to perform a surprise attack.
     * @param theOther The enemy character to attack.
     */
    @Override
    protected void special(DungeonCharacter theOther) {
        double random = Math.random() +0.1;
        //40 percent chance for surprise attack
        if(random <= getMyChanceForSpecial()) {
            int startingHealth = theOther.getMyHitPoints();
            for (int i = 0; i < 2; i++) {
                int damage = (int) (Math.random() * (getMySpecialMax() - getMySpecialMin() + 1)) + getMySpecialMin();
                theOther.setMyHitPoints(theOther.getMyHitPoints() - damage);
                System.out.println("Surprise attack was successful for " + damage + " damage!");
            }
            setMySpecialAmount(startingHealth-theOther.getMyHitPoints());
            setMySpecialSuccess(true);
            //40 percent chance for single special attack
        }else if(random <= 0.8) {
            int damage = (int) (Math.random() * (getMySpecialMax() - getMySpecialMin() + 1)) + getMySpecialMin();
            theOther.setMyHitPoints(theOther.getMyHitPoints() - damage);
            System.out.println("Surprise Attack was successful for " + damage + " damage!");
            setMySpecialAmount(damage);
            setMySpecialSuccess(true);
        }
    }

    /**
     * Retrieves the unique instance of the Thief class, following the singleton pattern.
     * @return The unique instance of the Thief class.
     */
    public static Thief getMyUniqueInstance() {
        if (myUniqueInstance == null) {
            myUniqueInstance = new Thief();
        }
        return myUniqueInstance;
    }

    /**
     * This is a method for testing purposes to bypass singleton pattern.
     * @return A new instance of the Thief class.
     */
    public static Thief Test_getMyUniqueInstance() {
        return new Thief();
    }
}

