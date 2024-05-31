package Model;


import java.awt.*;


public abstract class Hero extends DungeonCharacter {

   private Rectangle myHitBox;
   private double myChanceToBlock;
   private int mySpecialMin;
   private int mySpecialMax;
   private double myChanceForSpecial;
   private double myRandomForSpecial;



    protected Hero(final String theName, final int theHitPoints, final int theAttackSpeed,
                   final double theChanceToHit, final int theMaxDamage, final int theMinDamage, final int theX,
                   final int theY, final double theChanceToBlock, final int theSpecialMin,
                   final int theSpecialMax, final double theChanceForSpecial) {

         super(theName, theHitPoints, theAttackSpeed, theChanceToHit, theMaxDamage, theMinDamage, theX, theY);
         setMyChanceToBlock(theChanceToBlock);
         setMySpecialMax(theSpecialMax);
         setMySpecialMin(theSpecialMin);
         setMyChanceForSpecial(theChanceForSpecial);
         setMyRandomForSpecial(Math.random());


    }

    public void setMyRandomForSpecial(final double theRandomForSpecial) {
        if (theRandomForSpecial < 0.0 || theRandomForSpecial > 1.0) {
            throw new IllegalArgumentException("The random for special must be between 0.0 and 1.0");
        } else {
            myRandomForSpecial = theRandomForSpecial;
        }
    }

    public void setMyChanceForSpecial(final double theChanceForSpecial) {
        // Check if the input value is a valid double
        if ( theChanceForSpecial < 0.0 || theChanceForSpecial > 1.0 ) {
            // Handle invalid input
            throw new IllegalArgumentException("Invalid value for chance of special attack chance. It must be a double between 0.0 and 1.0");
        } else {
            myChanceForSpecial = theChanceForSpecial;
        }
    }

    public void setMyChanceToBlock(final double theChanceToBlock){
        if ( theChanceToBlock < 0.0 || theChanceToBlock > 1.0) {
            throw new IllegalArgumentException("Invalid value for chance of chance to block. It must be a double between 0.0 and 1.0");
        } else {
            myChanceToBlock = theChanceToBlock;
        }
    }
    public void setMySpecialMax(final int theSpecialMax) {
        if (theSpecialMax < 0) {
            throw new IllegalArgumentException("Invalid input for max special attack damage. It must be a non-negative integer.");
        } else {
            mySpecialMax = theSpecialMax;
        }
    }

    public void setMySpecialMin(final int theSpecialMin) {
        if (theSpecialMin < 0) {
            throw new IllegalArgumentException("Invalid input for min special attack damage. It must be a non-negative integer.");
        } else {
            mySpecialMin = theSpecialMin;
        }
    }

    public void setMyHitBox(Rectangle theHitBox) {
        myHitBox = theHitBox;
    }

    public double getMyChanceToBlock() {
        return myChanceToBlock;
    }

    public int getMySpecialMin() {
        return mySpecialMin;
    }

    public int getMySpecialMax() {
        return mySpecialMax;
    }

    public double getMyChanceForSpecial() {
        return myChanceForSpecial;
    }

    public Rectangle getMyHitBox() {
        return myHitBox;
    }
    public double getMyRandomForSpecial() {
        return myRandomForSpecial;
    }


    public abstract void special(final DungeonCharacter theOther);

}
