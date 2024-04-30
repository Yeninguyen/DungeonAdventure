package Model;

public abstract class DungeonCharacter {
    private String myName;
    private int myHealthPoints;
    private int myHitPoints;
    private int myAttackSpeed;
    private int myDamageRange;
    private double myChanceToHit;
    private int myMaxDamage;
    private int myMinDamage;

    DungeonCharacter(String theName, int theHealthPoints, int theHitPoints, int theAttackSpeed,
                            int theDamageRange, double theChanceToHit, int theMaxDamage, int theMinDamage){
        myName = theName;
        myHealthPoints = theHealthPoints;
        myHitPoints = theHitPoints;
        myAttackSpeed = theAttackSpeed;
        myDamageRange = theDamageRange;
        myChanceToHit = theChanceToHit;
        myMaxDamage = theMaxDamage;
        myMinDamage = theMinDamage;
    }

    public String getMyName() {
        return myName;
    }

    public int getMyHitPoints() {
        return myHitPoints;
    }

    public void setMyHitPoints(int theHitPoints) {
        myHitPoints = theHitPoints;
    }

    public int getMyMinDamage() {
        return myMinDamage;
    }

    public void setMyMinDamage(int  theMinDamage) {
        myMinDamage = theMinDamage;
    }

    public int getMyMaxDamage() {
        return myMaxDamage;
    }

    public void setMyMaxDamage(int theMaxDamage) {
        myMaxDamage = theMaxDamage;
    }

    public double getMyChanceToHit() {
        return myChanceToHit;
    }

    public void setMyChanceToHit(int theChanceToHit) {
        myChanceToHit = theChanceToHit;
    }

    public int getMyDamageRange() {
        return myDamageRange;
    }

    public void setMyDamageRange(int theDamageRange) {
        myDamageRange = theDamageRange;
    }

    public int getMyAttackSpeed() {
        return myAttackSpeed;
    }

    public void setMyAttackSpeed(int theAttackSpeed) {
        myAttackSpeed = theAttackSpeed;
    }

    public int getMyHealthPoints() {
        return myHealthPoints;
    }

    public void setMyHealthPoints(int theHealthPoints) {
        myHealthPoints = theHealthPoints;
    }

    public void setMyName(String theName) {
        myName = theName;
    }
    abstract void attack(DungeonCharacter theOther);
}
