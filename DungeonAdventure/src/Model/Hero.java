package Model;

public abstract class Hero extends DungeonCharacter {
   private double myChanceToBlock;
   private int mySpecialAttackDamage;
   private double myChanceForSpecialAttack;

    Hero(String theName, int theHealthPoints, int theHitPoints, int theAttackSpeed, int theDamageRange,
         double theChanceToHit, int theMaxDamage, int theMinDamage) {
        super(theName, theHealthPoints, theHitPoints, theAttackSpeed, theDamageRange, theChanceToHit, theMaxDamage, theMinDamage);
        myChanceToBlock = 0.1;
        mySpecialAttackDamage = 10;
        myChanceForSpecialAttack = 0.1;

    }
    @Override
    void attack(DungeonCharacter theOther) {
        int numberOfAttacks = theOther.getMyAttackSpeed()/super.getMyAttackSpeed();
        while(numberOfAttacks>0) {
            double randomForHit = Math.random() +0.1;
            double randomForBlock =  Math.random() +0.1;
            boolean canHit = randomForHit <= super.getMyChanceToHit();
            boolean canBlock = randomForBlock <= myChanceToBlock;
            if (canHit && !canBlock) {
                int damage = (int) (Math.random() * (super.getMyMaxDamage() - super.getMyMinDamage() + 1)) + super.getMyMinDamage();
                theOther.setMyHealthPoints(theOther.getMyHealthPoints() - damage);
                System.out.println("Successfully attacked the monster for " + damage + " points!"); //edit this to display on gui
            } else if(canBlock){
                System.out.println("Attack successfully blocked!"); //edit this to display on gui
            }else {
                System.out.println("Attack missed!"); //edit this to display on gui
            }
            numberOfAttacks--;
        }
        specialAttack(theOther);
    }
    abstract void specialAttack(DungeonCharacter theOther);

    public double getMyChanceForSpecialAttack() {
        return myChanceForSpecialAttack;
    }

    public void setMyChanceForSpecialAttack(double theChanceForSpecialAttack) {
        myChanceForSpecialAttack = theChanceForSpecialAttack;
    }

    public int getMySpecialAttackDamage() {
        return mySpecialAttackDamage;
    }

    public void setMySpecialAttackDamage(int theSpecialAttackDamage) {
        mySpecialAttackDamage = theSpecialAttackDamage;
    }

    public void setMyChanceToBlock(double theChanceToBlock){
        myChanceToBlock = theChanceToBlock;
    }

    public double getMyChanceToBlock() {
        return myChanceToBlock;
    }

}
