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
    private double myRandom;



    protected DungeonCharacter(final String theName, final int theHitPoints, final int theAttackSpeed,
                               final double theChanceToHit, final int theMaxDamage, final int theMinDamage,
                               final int theX, final int theY){
        setMyName(theName);
        setMyHitPoints(theHitPoints);
        setMyAttackSpeed(theAttackSpeed);
        setMyChanceToHit(theChanceToHit);
        setMyMaxDamage(theMaxDamage);
        setMyMinDamage(theMinDamage);
        setMyRandom(Math.random());

    }

    public void setMyChanceToHit(final double theChanceToHit) {
        if (myChanceToHit < 0.0 || myChanceToHit > 1.0) {
            throw new IllegalArgumentException("Chance to hit must be between 0.0 and 1.0");
        }else {
            myChanceToHit = theChanceToHit;
        }
    }

    public void setMyY(final int theY) {
      if(theY < 0 ) {
         throw new IllegalArgumentException("Y must be greater than 0");
       }else{
            myY = theY;
      }
    }

    public void setMyX(final int theX) {
      if(theX < 0) {
      throw new IllegalArgumentException("X must be greater than 0");
      }else{
           myX = theX;
       }
    }

    public void setMyMinDamage(final int theMinDamage) {
        if(theMinDamage < 0 ) {
            throw new IllegalArgumentException("Min damage must be greater than 0");
        }
        myMinDamage = theMinDamage;
    }
   public void setMyMaxDamage(final int theMaxDamage) {
        if(theMaxDamage < 0 ) {
            throw new IllegalArgumentException("Max damage must be greater than 0");
        }
        myMaxDamage = theMaxDamage;
    }

    public void setMyAttackSpeed(final int theAttackSpeed) {
        if(theAttackSpeed < 0){
            throw  new IllegalArgumentException("AttackSpeed must be greater than 0");
        }
        myAttackSpeed = theAttackSpeed;
    }

    public void setMyHitPoints(final int theHealthPoints) {
        if(theHealthPoints < 0) {
            throw new IllegalArgumentException("Health points must be greater than 0");
        }else {
            myHitPoints = theHealthPoints;
        }
    }

    public void setMyName(final String theName) {
        if(theName == null) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        myName = theName;
    }
    public void setMyRandom(final double theRandom) {
        if(theRandom < 0.0 || theRandom > 1.0) {
            throw new IllegalArgumentException("Random must be between 0.0 and 1.0");
        } else {
            myRandom = theRandom;
        }
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
    public double getMyRandom() {
        return myRandom;
    }
    public void attack(final DungeonCharacter theOther){
        if(myRandom <= myChanceToHit) {
            int damage = (int) (Math.random() * (myMaxDamage - myMinDamage + 1)) + myMinDamage;
            theOther.setMyHitPoints(theOther.getMyHitPoints()-damage);
        }
    }
}
