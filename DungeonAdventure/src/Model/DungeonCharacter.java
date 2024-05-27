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


    protected void setMyChanceToHit(final double theChanceToHit) {
        if (myChanceToHit < 0.0 || myChanceToHit > 1.0) {
            throw new IllegalArgumentException("Chance to hit must be between 0.0 and 1.0");
        }else {
            myChanceToHit = theChanceToHit;
        }
    }

    public void setMyY(final int theY) {
      // if(theY < 0 || theY > 768) {
        //   throw new IllegalArgumentException("Y must be between 0 and 768");
      //  }else{
            myY = theY;
     //  }
    }

    public void setMyX(final int theX) {
      // if(theX < 0 || theX > 1024) {
      //    throw new IllegalArgumentException("X must be between 0 and 1024");
      // }else{
           myX = theX;
      // }
    }

    void setMyMinDamage(final int theMinDamage) {
        if(theMinDamage < 0 ) {
            throw new IllegalArgumentException("Min damage must be greater than 0");
        }
        myMinDamage = theMinDamage;
    }
   void setMyMaxDamage(final int theMaxDamage) {
        if(theMaxDamage < 0 ) {
            throw new IllegalArgumentException("Max damage must be greater than 0");
        }
        myMaxDamage = theMaxDamage;
    }

    void setMyAttackSpeed(final int theAttackSpeed) {
        if(theAttackSpeed < 0){
            throw  new IllegalArgumentException("AttackSpeed must be greater than 0");
        }
        myAttackSpeed = theAttackSpeed;
    }

    void setMyHitPoints(final int theHealthPoints) {
        myHitPoints = theHealthPoints;
    }

    void setMyName(final String theName) {
        if(theName == null) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        myName = theName;
    }
    public int getMyHitPoints() {
        return myHitPoints;
    }

    public int getMyAttackSpeed() {
        return myAttackSpeed;
    }

    public double getMyChanceToHit() {
        return myChanceToHit;
    }

    public int getMyMaxDamage() {
        return myMaxDamage;
    }

    public String getMyName() {
        return myName;
    }

    public int getMyMinDamage() {
        return myMinDamage;
    }

    public int getMyX() {
        return myX;
    }

    public int getMyY() {
        return myY;
    }
    public void attack(final DungeonCharacter theOther){
        double random = Math.random();
        if(random <= myChanceToHit) {
            int damage = (int) (Math.random() * (getMyMaxDamage() - getMyMinDamage() + 1)) + getMyMinDamage();
            theOther.setMyHitPoints(theOther.getMyHitPoints()-damage);
        }
    }
}
