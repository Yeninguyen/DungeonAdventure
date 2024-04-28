package Model;

public class DungeonCharacter {
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
    void attack(DungeonCharacter theOther){
        int numberOfAttacks = theOther.getMyAttackSpeed()/myAttackSpeed;
        int randomNumber = (int) (Math.random() * 100)+1;
        while(numberOfAttacks>0) {
            boolean canHit = randomNumber <= myChanceToHit;
            if (canHit) {
                int damage = (int) (Math.random() * (myMaxDamage - myMinDamage + 1)) + myMinDamage;
                theOther.setMyHealthPoints(theOther.getMyHealthPoints() - damage);
                System.out.println("Successfully attacked the monster for " + damage + " points."); //edit this to display on gui
            } else {
                System.out.println("Attack missed!"); //edit this to display on gui
            }
            numberOfAttacks--;
        }
    }
}
