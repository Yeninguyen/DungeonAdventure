package Model;



public abstract class Hero extends DungeonCharacter {


   private double myChanceToBlock;
   private int mySpecialAttackMinDamage;
   private int mySpecialAttackMaxDamage;
   private double myChanceForSpecialAttack;


    protected Hero(String theName, int theHitPoints, int theAttackSpeed,
                   double theChanceToHit, int theMaxDamage, int theMinDamage, int theX, int theY, double theChanceToBlock,
                   int theSpecialAttackMinDamage, int theSpecialAttackMaxDamage, double theChanceForSpecialAttack) {

         super(theName, theHitPoints, theAttackSpeed, theChanceToHit, theMaxDamage, theMinDamage, theX, theY);

         myChanceToBlock = theChanceToBlock;
         mySpecialAttackMaxDamage = theSpecialAttackMaxDamage;
         mySpecialAttackMinDamage = theSpecialAttackMinDamage;
         myChanceForSpecialAttack = theChanceForSpecialAttack;

    }
    @Override
    protected void attack(DungeonCharacter theOther) {
       int numberOfAttacks = theOther.getMyAttackSpeed() / super.getMyAttackSpeed();

        while(numberOfAttacks > 0) {
            double randomForHit = Math.random() +0.1;
            double randomForBlock =  Math.random() +0.1;
            boolean myCanHit = randomForHit <= super.getMyChanceToHit();
            boolean myCanBlock = randomForBlock <= myChanceToBlock;
            if (myCanHit && !myCanBlock) {
                int damage = (int) (Math.random() * (super.getMyMaxDamage() - super.getMyMinDamage() + 1)) + super.getMyMinDamage();
                theOther.setMyHitPoints(theOther.getMyHitPoints() - damage);
                System.out.println("Successfully attacked the monster for " + damage + " points!"); //edit this to display on gui
            } else if(myCanBlock){
                System.out.println("Attack successfully blocked!"); //edit this to display on gui
            }else {
                System.out.println("Attack missed!"); //edit this to display on gui
            }
            numberOfAttacks--;
        }

        specialAttack(theOther);
    }



    public void setMyChanceForSpecialAttack(double theChanceForSpecialAttack) {
        // Check if the input value is a valid double
        if (Double.isNaN(theChanceForSpecialAttack) || theChanceForSpecialAttack < 0 || theChanceForSpecialAttack > 1) {
            // Handle invalid input
            throw new IllegalArgumentException("Invalid value for chance of special attack chance. It must be a double between 0 and 1.");
        } else {
            myChanceForSpecialAttack = theChanceForSpecialAttack;
        }
    }

    public void setMyChanceToBlock(double theChanceToBlock){
        if (Double.isNaN(theChanceToBlock) || theChanceToBlock < 0.0 || theChanceToBlock > 1.0) {
            throw new IllegalArgumentException("Invalid value for chance of chance to block. It must be a double between 0 and 1.");
        } else {
            myChanceToBlock = theChanceToBlock;
        }
    }

    public double getMyChanceToBlock() {
        return myChanceToBlock;
    }

    public int getMySpecialAttackMinDamage() {
        return mySpecialAttackMinDamage;
    }

    public int getMySpecialAttackMaxDamage() {
        return mySpecialAttackMaxDamage;
    }

    public double getMyChanceForSpecialAttack() {
        return myChanceForSpecialAttack;
    }

    public void setMySpecialAttackMaxDamage(int theSpecialAttackMaxDamage) {
        if (theSpecialAttackMaxDamage < 0) {
            throw new IllegalArgumentException("Invalid input for max special attack damage. It must be a non-negative integer.");
        } else {
            this.mySpecialAttackMaxDamage = theSpecialAttackMaxDamage;
        }
    }

    public void setMySpecialAttackMinDamage(int theSpecialAttackMinDamage) {
        if (theSpecialAttackMinDamage < 0) {
            throw new IllegalArgumentException("Invalid input for min special attack damage. It must be a non-negative integer.");
        } else {
            this.mySpecialAttackMinDamage = theSpecialAttackMinDamage;
        }
    }
    protected abstract void specialAttack(DungeonCharacter theOther);

}
