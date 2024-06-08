/**
 * T
 */
package Model;


import java.awt.*;

/**
 * The Hero abstract class represents a hero character in the dungeon,
 * extending the DungeonCharacter class.
 * It includes additional attributes such as chance to block,
 * special attack damage range, and chance for special attack.
 */
public abstract class Hero extends DungeonCharacter {

    /**
     * The hitbox of the hero.
     */
    private Rectangle myHitBox;

    /**
     * The chance to block an attack.
     */
    private double myChanceToBlock;

    /**
     * The minimum damage the hero's special attack can inflict.
     */
    private int mySpecialMin;

    /**
     * The maximum damage the hero's special attack can inflict.
     */
    private int mySpecialMax;

    /**
     * The chance for the hero's special attack to succeed.
     */
    private double myChanceForSpecial;

    /**
     * indicating if the special attack was successful.
     */
    private boolean mySpecialSuccess;

    /**
     * The amount or result of the special attack.
     */
    private int mySpecialAmount;


    /**
     * This is the constructor for creating a Hero with specified attributes.
     * @param theName            The name of the hero.
     * @param theHitPoints       The hit points of the hero.
     * @param theAttackSpeed     The attack speed of the hero.
     * @param theChanceToHit     The chance to hit of the hero.
     * @param theMaxDamage       The maximum damage the hero can inflict.
     * @param theMinDamage       The minimum damage the hero can inflict.
     * @param theX               The x-coordinate of the hero's position.
     * @param theY               The y-coordinate of the hero's position.
     * @param theChanceToBlock   The chance to block an attack.
     * @param theSpecialMin      The minimum damage of the special attack.
     * @param theSpecialMax      The maximum damage of the special attack.
     * @param theChanceForSpecial The chance for a special attack.
     */
    protected Hero(final String theName, final int theHitPoints, final int theAttackSpeed,
                   final double theChanceToHit, final int theMaxDamage, final int theMinDamage, final int theX,
                   final int theY, final double theChanceToBlock, final int theSpecialMin,
                   final int theSpecialMax, final double theChanceForSpecial) {

        super(theName, theHitPoints, theAttackSpeed, theChanceToHit, theMaxDamage, theMinDamage, theX, theY);
        setMyChanceToBlock(theChanceToBlock);
        setMySpecialMax(theSpecialMax);
        setMySpecialMin(theSpecialMin);
        setMyChanceForSpecial(theChanceForSpecial);
        setMySpecialSuccess(false);
        setMySpecialAmount(0);

    }

    /**
     * This is the method ets the flag indicating whether the special attack was successful or not.
     * @param theSpecialSuccess Boolean value indicating the success of the special attack.
     */
    public void setMySpecialSuccess(final boolean theSpecialSuccess) {
        mySpecialSuccess = theSpecialSuccess;
    }

    /**
     * This is the method sets the amount or result of the special attack.
     * @param theSpecialAmount The amount or result of the special attack.
     * @throws IllegalArgumentException if theSpecialAmount is a negative integer.
     */
    void setMySpecialAmount(final int theSpecialAmount) {
        if (theSpecialAmount < 0) {
            throw new IllegalArgumentException("The special amount must be a positive integer.");
        } else{
            mySpecialAmount = theSpecialAmount;
        }
    }

    /**
     * This is the method sets the chance for a special attack.
     * @param theChanceForSpecial The chance for a special attack.
     * @throws IllegalArgumentException If the chance for a special attack is not between 0.0 and 1.0.
     */
    void setMyChanceForSpecial(final double theChanceForSpecial) {
        // Check if the input value is a valid double
        if ( theChanceForSpecial < 0.0 || theChanceForSpecial > 1.0 ) {
            // Handle invalid input
            throw new IllegalArgumentException("Invalid value for chance of special attack chance. It must be a double between 0.0 and 1.0");
        } else {
            myChanceForSpecial = theChanceForSpecial;
        }
    }

    /**
     * This is the method sets the chance to block an attack.
     * @param theChanceToBlock The chance to block an attack.
     * @throws IllegalArgumentException If the chance to block is not between 0.0 and 1.0.
     */
    void setMyChanceToBlock(final double theChanceToBlock){
        if ( theChanceToBlock < 0.0 || theChanceToBlock > 1.0) {
            throw new IllegalArgumentException("Invalid value for chance of chance to block. It must be a double between 0.0 and 1.0");
        } else {
            myChanceToBlock = theChanceToBlock;
        }
    }

    /**
     * This is the method sets the maximum damage of the special attack.
     * @param theSpecialMax The maximum damage of the special attack.
     * @throws IllegalArgumentException If the maximum special attack damage is less than 0.
     */
    void setMySpecialMax(final int theSpecialMax) {
        if (theSpecialMax < 0) {
            throw new IllegalArgumentException("Invalid input for max special attack damage. It must be a non-negative integer.");
        } else {
            mySpecialMax = theSpecialMax;
        }
    }

    /**
     * This is the method sets the minimum damage of the special attack.
     * @param theSpecialMin The minimum damage of the special attack.
     * @throws IllegalArgumentException If the minimum special attack damage is less than 0.
     */
    void setMySpecialMin(final int theSpecialMin) {
        if (theSpecialMin < 0) {
            throw new IllegalArgumentException("Invalid input for min special attack damage. It must be a non-negative integer.");
        } else {
            mySpecialMin = theSpecialMin;
        }
    }

    /**
     * This is the method gets the chance to block an attack.
     * @return The chance to block an attack.
     */
    public double getMyChanceToBlock() {
        return myChanceToBlock;
    }

    /**
     * This is the method gets the minimum damage of the special attack.
     * @return The minimum damage of the special attack.
     */
    public int getMySpecialMin() {
        return mySpecialMin;
    }

    /**
     * This is the method gets the maximum damage of the special attack.
     * @return The maximum damage of the special attack.
     */
    public int getMySpecialMax() {
        return mySpecialMax;
    }

    /**
     * This is the method gets the chance for a special attack.
     * @return The chance for a special attack.
     */
    public double getMyChanceForSpecial() {
        return myChanceForSpecial;
    }

    /**
     * This method retrieves the flag indicating whether the special attack was successful or not.
     * @return true if the special attack was successful, false otherwise.
     */
    public boolean getMySpecialSuccess() {
        return mySpecialSuccess;
    }

    /**
     * This method retrieves the amount or result of the special attack.
     * @return The amount or result of the special attack.
     */
    public int getMySpecialAmount() {
        return mySpecialAmount;
    }


    /**
     * This is the method gets the hit box of the hero.
     * @return The hit box of the hero.
     */
    public Rectangle getMyHitBox() {
        return myHitBox;
    }

    /**
     * This is the method sets the hit box of the hero.
     * @param theHitBox The hit box of the hero.
     */
    public void setMyHitBox(Rectangle theHitBox) {
        this.myHitBox = theHitBox;
    }

    /**
     *  This is the method defines the special attack for the hero.
     * @param theOther The character that is being attacked.
     */
    protected abstract void special(final DungeonCharacter theOther);

}