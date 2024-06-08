package Model;


/**
 * The DungeonCharacter class represents a character in the dungeon,
 * with attributes such as name, hit points, attack speed, etc.
 * It serves as a base class for specific character types.
 */
public abstract class DungeonCharacter {

    /**
     * The name of the character.
     */
    private String myName;

    /**
     * The hit points of the character.
     */
    private int myHitPoints;

    /**
     * The attack speed of the character.
     */
    private int myAttackSpeed;

    /**
     * The chance to hit of the character.
     */
    private double myChanceToHit;

    /**
     * The maximum damage the character can inflict.
     */
    private int myMaxDamage;

    /**
     * The minimum damage the character can inflict.
     */
    private int myMinDamage;

    /**
     * The x-coordinate of the character's position.
     */
    private int myX;

    /**
     * The y-coordinate of the character's position.
     */
    private int myY;

    /**
     * This is the constructor of a DungeonCharacter with specified attributes.
     * @param theName        The name of the character.
     * @param theHitPoints   The hit points of the character.
     * @param theAttackSpeed The attack speed of the character.
     * @param theChanceToHit The chance to hit of the character.
     * @param theMaxDamage   The maximum damage the character can inflict.
     * @param theMinDamage   The minimum damage the character can inflict.
     * @param theX           The x-coordinate of the character's position.
     * @param theY           The y-coordinate of the character's position.
     */
    DungeonCharacter(final String theName, final int theHitPoints, final int theAttackSpeed,
                     final double theChanceToHit, final int theMaxDamage, final int theMinDamage,
                     final int theX, final int theY){
        setMyName(theName);
        setMyHitPoints(theHitPoints);
        setMyAttackSpeed(theAttackSpeed);
        setMyChanceToHit(theChanceToHit);
        setMyMaxDamage(theMaxDamage);
        setMyMinDamage(theMinDamage);

    }

    /**
     * Sets the chance to hit of the character.
     * @param theChanceToHit The chance to hit of the character.
     * @throws IllegalArgumentException If the chance to hit is not between 0.0 and 1.0.
     */
    protected void setMyChanceToHit(final double theChanceToHit) {
        if (myChanceToHit < 0.0 || myChanceToHit > 1.0) {
            throw new IllegalArgumentException("Chance to hit must be between 0.0 and 1.0");
        }else {
            myChanceToHit = theChanceToHit;
        }
    }

    /**
     * This is setter method, it sets the y-coordinate of the character's position.
     * @param theY The y-coordinate of the character's position.
     */
    public void setMyY(final int theY) {
        myY = theY;
    }

    /**
     * This is setter method, it sets the x-coordinate of the character's position.
     * @param theX The x-coordinate of the character's position.
     */
    public void setMyX(final int theX) {
        myX = theX;
    }

    /**
     * This is the method sets the minimum damage of the character.
     * @param theMinDamage The minimum damage of the character.
     * @throws IllegalArgumentException If the minimum damage is less than 0.
     */
    void setMyMinDamage(final int theMinDamage) {
        if(theMinDamage < 0 ) {
            throw new IllegalArgumentException("Min damage must be greater than 0");
        }
        myMinDamage = theMinDamage;
    }

    /**
     * This is the method sets the maximum damage of the character.
     * @param theMaxDamage The maximum damage of the character.
     * @throws IllegalArgumentException If the maximum damage is less than 0.
     */
    void setMyMaxDamage(final int theMaxDamage) {
        if(theMaxDamage < 0 ) {
            throw new IllegalArgumentException("Max damage must be greater than 0");
        }
        myMaxDamage = theMaxDamage;
    }

    /**
     * This is the method sets the attack speed of the character.
     * @param theAttackSpeed The attack speed of the character.
     * @throws IllegalArgumentException If the attack speed is less than 0.
     */
    void setMyAttackSpeed(final int theAttackSpeed) {
        if(theAttackSpeed < 0){
            throw  new IllegalArgumentException("AttackSpeed must be greater than 0");
        }
        myAttackSpeed = theAttackSpeed;
    }

    /**
     * This is the method sets the hit points of the character.
     * @param theHealthPoints The hit points of the character.
     */
    public void setMyHitPoints(final int theHealthPoints) {
        myHitPoints = theHealthPoints;
    }

    void setMyName(final String theName) {
        if(theName == null) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        myName = theName;
    }

    /**
     * This is a method that gets the hit points of the character.
     * @return The hit points of the character.
     */
    public int getMyHitPoints() {
        return myHitPoints;
    }

    /**
     * This is the method gets the attack speed of the character.
     * @return The attack speed of the character.
     */
    public int getMyAttackSpeed() {
        return myAttackSpeed;
    }

    /**
     * This is the method gets the chance to hit of the character.
     * @return The chance to hit of the character.
     */
    public double getMyChanceToHit() {
        return myChanceToHit;
    }

    /**
     * This is the method gets the maximum damage of the character.
     * @return The maximum damage of the character.
     */
    public int getMyMaxDamage() {
        return myMaxDamage;
    }

    /**
     * This is the method gets the name of the character.
     * @return The name of the character.
     */
    public String getMyName() {
        return myName;
    }

    /**
     * This is the method gets the minimum damage of the character.
     * @return The minimum damage of the character.
     */
    public int getMyMinDamage() {
        return myMinDamage;
    }

    /**
     * This is the method gets  the x-coordinate of the character's position.
     * @return The x-coordinate of the character's position.
     */
    public int getMyX() {
        return myX;
    }

    /**
     * This is the method gets the y-coordinate of the character's position.
     * @return The y-coordinate of the character's position.
     */
    public int getMyY() {
        return myY;
    }


    /**
     * This method performs an attack on another DungeonCharacter.
     * @param theOther The DungeonCharacter to attack.
     */
    public void attack(final DungeonCharacter theOther){
        double random = Math.random();
        if(random <= myChanceToHit) {
            int damage = (int) (Math.random() * (getMyMaxDamage() - getMyMinDamage() + 1)) + getMyMinDamage();
            int newHitPoint = theOther.getMyHitPoints()-damage;
            theOther.setMyHitPoints(newHitPoint);
        }
    }
}
