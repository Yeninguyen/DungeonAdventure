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

    DungeonCharacter(final String theName, final int theHitPoints,  final int theAttackSpeed,
                            final double theChanceToHit, final int theMaxDamage, final int theMinDamage,
                    final int theX, final int theY){
        setMyName(theName);
        setMyHitPoints(theHitPoints);
        setMyAttackSpeed(theAttackSpeed);
        setMyChanceToHit(theChanceToHit);
        setMyMaxDamage(theMaxDamage);
        setMyMinDamage(theMinDamage);
    }

    public String getMyName() {
        return myName;
    }
    public int getMyY() {
        return myY;
    }
    public int getMyX() {
        return myX;
    }

    public int getMyMinDamage() {
        return myMinDamage;
    }

    public int getMyMaxDamage() {
        return myMaxDamage;
    }

    public double getMyChanceToHit() {
        return myChanceToHit;
    }
    public int getMyAttackSpeed() {
        return myAttackSpeed;
    }

    public int getMyHitPoints() {
        return myHitPoints;
    }


    public void setMyChanceToHit(final double theChanceToHit) {
        if(theChanceToHit < 0.0 || theChanceToHit > 1.0){
            throw new IllegalArgumentException("Chance to hit can only be between 0.0 to 1.0, you passed: " + theChanceToHit);
        }else{
            myChanceToHit = theChanceToHit;
        }
    }



    public void setMyY(int theY) {
//        if(theY < 0 || theY > 768){
//            throw new IllegalArgumentException("The Y can only be between 0 to 768 you passed: " + theY);
//        }else{
//            myY = theY;
//        }
        myY = theY;

    }



    public void setMyX(int myX) {
        this.myX = myX;
    }



    public void setMyMinDamage(int  theMinDamage) {
        myMinDamage = theMinDamage;
    }


    public void setMyMaxDamage(int theMaxDamage) {
        myMaxDamage = theMaxDamage;
    }



    public void setMyChanceToHit(int theChanceToHit) {
        myChanceToHit = theChanceToHit;
    }



    public void setMyAttackSpeed(int theAttackSpeed) {
        myAttackSpeed = theAttackSpeed;
    }



    public void setMyHitPoints(int theHealthPoints) {
        myHitPoints = theHealthPoints;
    }

    public void setMyName(String theName) {
        myName = theName;
    }
    protected  void attack(DungeonCharacter theOther){

    }
}
