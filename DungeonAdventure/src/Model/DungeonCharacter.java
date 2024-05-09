package Model;

public abstract class DungeonCharacter {
    private String myName;
    private int myHitPoints;
    private int myAttackSpeed;
    private double myChanceToHit;
    private int myMaxDamage;
    private int myMinDamage;
    private int myX;
    private int myY;

    DungeonCharacter(String theName, int theHitPoints,  int theAttackSpeed,
                            double theChanceToHit, int theMaxDamage, int theMinDamage,
                     int theX, int theY){
        myName = theName;
        myHitPoints = theHitPoints;
        myAttackSpeed = theAttackSpeed;
        myChanceToHit = theChanceToHit;
        myMaxDamage = theMaxDamage;
        myMinDamage = theMinDamage;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyChanceToHit(double myChanceToHit) {
        this.myChanceToHit = myChanceToHit;
    }

    public int getMyY() {
        return myY;
    }

    public void setMyY(int myY) {
        this.myY = myY;
    }

    public int getMyX() {
        return myX;
    }

    public void setMyX(int myX) {
        this.myX = myX;
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

    public int getMyAttackSpeed() {
        return myAttackSpeed;
    }

    public void setMyAttackSpeed(int theAttackSpeed) {
        myAttackSpeed = theAttackSpeed;
    }

    public int getMyHitPoints() {
        return myHitPoints;
    }

    public void setMyHitPoints(int theHealthPoints) {
        myHitPoints = theHealthPoints;
    }

    public void setMyName(String theName) {
        myName = theName;
    }
    protected abstract void attack(DungeonCharacter theOther);
}
