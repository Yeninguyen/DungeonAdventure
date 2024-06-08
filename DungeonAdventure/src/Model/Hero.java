package Model;


import java.awt.*;

public abstract class Hero extends DungeonCharacter {

    private Rectangle myHitBox;
    private double myChanceToBlock;
    private int mySpecialMin;
    private int mySpecialMax;
    private double myChanceForSpecial;
    private boolean mySpecialSuccess;
    private int mySpecialAmount;
    private boolean myBlockedAttack;


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



    void setMyChanceForSpecial(final double theChanceForSpecial) {
        // Check if the input value is a valid double
        if ( theChanceForSpecial < 0.0 || theChanceForSpecial > 1.0 ) {
            // Handle invalid input
            throw new IllegalArgumentException("Invalid value for chance of special attack chance. It must be a double between 0.0 and 1.0");
        } else {
            myChanceForSpecial = theChanceForSpecial;
        }
    }
    public void setMyHitBox(Rectangle theHitBox) {
        this.myHitBox = theHitBox;
    }


    void setMyChanceToBlock(final double theChanceToBlock){
        if ( theChanceToBlock < 0.0 || theChanceToBlock > 1.0) {
            throw new IllegalArgumentException("Invalid value for chance of chance to block. It must be a double between 0.0 and 1.0");
        } else {
            myChanceToBlock = theChanceToBlock;
        }
    }
    void setMySpecialMax(final int theSpecialMax) {
        if (theSpecialMax < 0) {
            throw new IllegalArgumentException("Invalid input for max special attack damage. It must be a non-negative integer.");
        } else {
            mySpecialMax = theSpecialMax;
        }
    }

    public void setMyBlockedAttack(boolean theBlockedAttack) {
        myBlockedAttack = theBlockedAttack;
    }

    void setMySpecialMin(final int theSpecialMin) {
        if (theSpecialMin < 0) {
            throw new IllegalArgumentException("Invalid input for min special attack damage. It must be a non-negative integer.");
        } else {
            mySpecialMin = theSpecialMin;
        }
    }
    public void setMySpecialSuccess(final boolean theSpecialSuccess) {
        mySpecialSuccess = theSpecialSuccess;
    }
    void setMySpecialAmount(final int theSpecialAmount) {
        if (theSpecialAmount < 0) {
            throw new IllegalArgumentException("The special amount must be a positive integer.");
        } else{
            mySpecialAmount = theSpecialAmount;
        }
    }

    public void block() {
        double random = Math.random();
        if(random <= myChanceToBlock){
            myBlockedAttack = true;
        }
    }

    public double getMyChanceToBlock() {
        return myChanceToBlock;
    }

    public boolean getMySpecialSuccess() {
        return mySpecialSuccess;
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

    public int getMySpecialAmount() {
        return mySpecialAmount;
    }

    public boolean getMyBlockedAttack() {
        return myBlockedAttack;
    }

    @Override
    public void attack(DungeonCharacter theOther) {
        int originalHealth = theOther.getMyHitPoints();
        super.attack(theOther);
        int afterAttackHealth = theOther.getMyHitPoints();
        if(theOther.getMyHitPoints() > 0) {
            if(originalHealth != afterAttackHealth){
                ((Monster) theOther).heal();
            }
            ((Hero) this).special(theOther);
        }
    }

    protected abstract void special(final DungeonCharacter theOther);

}