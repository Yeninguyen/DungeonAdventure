/**
 * Team members: Pham Nguyen, Ali Mohamed and Faisal Nur
 * Class: TCSS360
 * Date: Jun 7, 2024
 * @version 1.0
 */
package Model;


/**
 * The Monster class represents a monster character in the dungeon.
 * It includes additional attributes such as chance to heal,
 * healing points range, and a random factor for healing.
 */
public class Monster extends DungeonCharacter{

    /**
     * The chance to heal.
     */
    private double myChanceToHeal;

    /**
     * The minimum healing points the monster can heal.
     */
    private int myMinHealPoints;

    /**
     * The maximum healing points the monster can heal.
     */
    private int myMaxHealPoints;

    /**
     * A random heal for the monsters.
     */
    private double myRandomToHeal;

    /**
     * A heal instance for the monsters.
     */
    private boolean myHealed;

    /**
     * The Monsters amount of healing.
     */
    private int myHealedAmount;

    /**
     * This is a constructor of a new Monster with the specified attributes.
     * @param theName The name of the monster.
     * @param theHitPoints The hit points of the monster.
     * @param theAttackSpeed The attack speed of the monster.
     * @param theChanceToHit The chance to hit of the monster.
     * @param theMaxDamage The maximum damage the monster can inflict.
     * @param theMinDamage The minimum damage the monster can inflict.
     * @param theChanceToHeal The chance for the monster to heal.
     * @param theMinHealPoints The minimum healing points of the monster.
     * @param theMaxHealPoints The maximum healing points of the monster.
     * @param theX The x-coordinate of the monster's position.
     * @param theY The y-coordinate of the monster's position.
     */
    protected Monster(final String theName, final int theHitPoints, final int theAttackSpeed, final double theChanceToHit,
                      final int theMaxDamage, final int theMinDamage, final double theChanceToHeal, final int theMinHealPoints,
                      final int theMaxHealPoints, final int theX, final int theY){
        super(theName,theHitPoints,theAttackSpeed,theChanceToHit,theMaxDamage,theMinDamage,theX, theY);
        setMyChanceToHeal(theChanceToHeal);
        setMyMinHealPoints(theMinHealPoints);
        setMyMaxHealPoints(theMaxHealPoints);
        setMyRandomToHeal(Math.random());
        setMyHealed(false);
        setMyHealedAmount(0);



    }

    /**
     * This method sets the flag indicating whether the hero was healed or not.
     * @param theHealed true if the hero was healed, false otherwise.
     */
    public void setMyHealed(boolean theHealed) {
        myHealed = theHealed;
    }

    /**
     * This method sets the amount of healing received by the hero.
     * @param theHealedAmount The amount of healing received by the hero.
     * @throws IllegalArgumentException if the specified amount is negative.
     */
    private void setMyHealedAmount(int theHealedAmount) {
        myHealedAmount = theHealedAmount;
    }

    /**
     * This is a method sets the maximum healing points.
     * @param theMaxHealPoints The maximum healing points.
     * @throws IllegalArgumentException if the maximum healing points are less than zero.
     */
    public void setMyMaxHealPoints(final int theMaxHealPoints){
        if(theMaxHealPoints < 0){
            throw new IllegalArgumentException("The max heal points cannot be less than zero.");
        } else{
            myMaxHealPoints = theMaxHealPoints;
        }
    }

    /**
     * This is a method sets the minimum healing points.
     * @param theMinHealPoints The minimum healing points.
     * @throws IllegalArgumentException if the minimum healing points are less than zero.
     */
    public void setMyMinHealPoints(final int theMinHealPoints){
        if(theMinHealPoints < 0){
            throw new IllegalArgumentException("The minimum heal points cannot be less than zero.");
        } else{
            myMinHealPoints = theMinHealPoints;
        }
    }

    /**
     * This is a method sets the chance to heal.
     * @param theChanceToHeal The chance to heal.
     * @throws IllegalArgumentException if the chance to heal is not between 0.0 and 1.0.
     */
    public void setMyChanceToHeal(final double theChanceToHeal){
        if(theChanceToHeal < 0.0 || theChanceToHeal > 1.0){
            throw new IllegalArgumentException("The chance to heal must be between 0.0 and 1.0");
        }else{
            myChanceToHeal = theChanceToHeal;
        }
    }

    /**
     * This is a method sets the random factor for healing.
     * @param theRandomToHeal The random factor for healing.
     * @throws IllegalArgumentException if the random factor is not between 0.0 and 1.0.
     */
    public void setMyRandomToHeal(final double theRandomToHeal) {
        if(theRandomToHeal < 0.0 || theRandomToHeal > 1.0){
            throw new IllegalArgumentException("The random to heal must be between 0.0 and 1.0");
        } else{
            myRandomToHeal = theRandomToHeal;
        }
    }

    /**
     * This method retrieves whether the hero was healed or not.
     * @return true if the hero was healed, false otherwise.
     */
    public boolean getMyHealed() {
        return myHealed;
    }

    /**
     * This method retrieves the amount of healing received by the hero.
     * @return The amount of healing received by the hero.
     */
    public int getMyHealedAmount() {
        return myHealedAmount;
    }

    /**
     * This is a method gets the chance to heal.
     * @return The chance to heal.
     */
    public double getMyChanceToHeal() {
        return myChanceToHeal;
    }

    /**
     * This is a method gets the maximum healing points.
     * @return The maximum healing points.
     */
    public int getMyMaxHealPoints() {
        return myMaxHealPoints;
    }

    /**
     * This is a method gets the minimum healing points.
     * @return The minimum healing points.
     */
    public int getMyMinHealPoints() {
        return myMinHealPoints;
    }

    /**
     * This is a method gets the random factor for healing.
     * @return The random factor for healing.
     */
    public double getRandomToHeal() {
        return myRandomToHeal;
    }

    /**
     * This is the heal method attempts to heal the monster based on its chance to heal.
     * If the random factor is less than or equal to the chance to heal,
     * the monster heals a random amount of hit points.
     */
    public void heal(){
        if(myRandomToHeal <= myChanceToHeal){
            int healAmount =  (int) (Math.random() * (myMaxHealPoints - myMinHealPoints + 1)) + myMinHealPoints;
            setMyHitPoints(getMyHitPoints()+healAmount);
            System.out.println(getMyName() + " Successfully healed for " + healAmount + " points");
        }
    }

}
