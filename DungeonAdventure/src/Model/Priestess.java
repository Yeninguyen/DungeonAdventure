package Model;

/**
 * The Priestess class extends Hero class, represents a hero character with unique healing abilities.
 * It is implemented as a Singleton to ensure only one instance of the class is created.
 */
public class Priestess extends Hero{

    /**
     * The unique instance field of the Priestess.
     */
    private static Priestess myUniqueInstance;

    /**
     * Private constructor to prevent instantiation.
     * Initializes the Priestess with specific attributes.
     */
    private Priestess(){
        super("Priestess",75,4,0.7,45,25,10,10,
                0.3,50,100,0.6);
    }

    /**
     * The special ability of the Priestess to heal herself.
     * Heals a random amount of hit points based on the special skill's range.
     * @param theOther The DungeonCharacter the Priestess is interacting with (not used in this implementation).
     */
    @Override
    protected void special(DungeonCharacter theOther) {
        double random = Math.random() ;
        if(random <= getMyChanceForSpecial()){
            int healedPoints = (int) (Math.random() * (getMySpecialMax() - getMySpecialMin() + 1)) + getMySpecialMin();
            this.setMyHitPoints(getMyHitPoints()+healedPoints);
            setMySpecialAmount(healedPoints);
            setMySpecialSuccess(true);
            //turn this print statement into a gui component
            System.out.println("Successfully landed special skill to heal: " + healedPoints + " points!");
        }
    }
    /**
     * This is a method gets the unique instance of the Priestess.
     * @return The unique instance of the Priestess.
     */
    public static Priestess getMyUniqueInstance() {
        if(myUniqueInstance == null) {
            myUniqueInstance = new Priestess();
        }
        return myUniqueInstance;
    }

    /**
     * This is a method gets a new instance of the Priestess for testing purposes.
     * @return A new instance of the Priestess.
     */
    public static Priestess Test_getMyUniqueInstance(){
        return new Priestess();
    }
}