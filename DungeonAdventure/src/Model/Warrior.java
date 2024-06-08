package Model;

/**
 * The Warrior class represents a hero character specializing in melee combat.
 * It extends from the Hero class.
 */
public class Warrior extends Hero{
    /**
     * The unique instance field of the Warrior.
     */
    private static Warrior MyUniqueInstance;

    /**
     * Private constructor to create a new Warrior instance.
     */
    private Warrior(){
        super("Warrior",125,4,0.8,60,35,10,10,0.2, 75, 175,0.4);

    }

    /**
     * This is the method performs the special attack specific to the Warrior.
     * @param theOther The DungeonCharacter receiving the special attack.
     */
    @Override
    public void special(DungeonCharacter theOther) {
        double random = Math.random();
        if(random <= getMyChanceForSpecial()){
            int damage = (int) (Math.random() * (getMySpecialMax() - getMySpecialMin() + 1)) + getMySpecialMin();
            theOther.setMyHitPoints(theOther.getMyHitPoints()-damage);
            setMySpecialAmount(damage);
            setMySpecialSuccess(true);
            //turn this print statement into a gui component
        }
    }

    /**
     * This is a method retrieves the instance of the Warrior class.
     * If the instance does not exist, a new instance is created.
     * @return The instance of the Warrior class.
     */
    public static Warrior getInstance(){
        if(MyUniqueInstance == null){
            MyUniqueInstance = new Warrior();
        }
        return MyUniqueInstance;
    }
    /**
     * This is a method retrieves a new instance of the Warrior class for testing purposes.
     * @return A new instance of the Warrior class.
     */
    public static Warrior TEST_getInstance(){
        return new Warrior();
    }
}