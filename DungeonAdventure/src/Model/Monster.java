package Model;


public class Monster extends DungeonCharacter{

    private final double myChanceToHeal;
    private final int myMinHealPoints;
    private final int myMaxHealPoints;



    protected Monster(String theName, int theHitPoints, int theAttackSpeed, double theChanceToHit,
                    int theMaxDamage, int theMinDamage, double theChanceToHeal, int theMinHealPoints, int theMaxHealPoints){
        super(theName,theHitPoints,theAttackSpeed,theChanceToHit,theMaxDamage,theMinDamage,10,10);
        myChanceToHeal = theChanceToHeal;
        myMinHealPoints = theMinHealPoints;
        myMaxHealPoints = theMaxHealPoints;


    }


    public double getMyChanceToHeal() {
        return myChanceToHeal;
    }

    public int getMyMaxHealPoints() {
        return myMaxHealPoints;
    }

    public int getMyMinHealPoints() {
        return myMinHealPoints;
    }

    @Override
    protected void attack(DungeonCharacter theOther) {
        int numberOfAttacks = theOther.getMyAttackSpeed() / getMyAttackSpeed();


        while(numberOfAttacks > 0) {
            double random = Math.random() +0.1;
            int hitPointsBeforeAttack = getMyHitPoints();
            boolean myCanHit = random <= getMyChanceToHit();
            if (myCanHit) {
                int damage = (int) (Math.random() * (getMyMaxDamage() - getMyMinDamage() + 1)) + getMyMinDamage();
                theOther.setMyHitPoints(theOther.getMyHitPoints() - damage);
                System.out.println(getMyName() + "Successfully attacked player for " + damage + " points"); //edit this to display on gui
            }
            numberOfAttacks--;
            //chance to heal if monster loses hit points but has not fainted
            if(hitPointsBeforeAttack-getMyHitPoints() >= 0 && getMyHitPoints() > 0){
                heal();
            }
            }



    }
    public void heal(){
        double random = Math.random() + 0.1;
        if(random <= myChanceToHeal){
            int healAmount =  (int) (Math.random() * (myMaxHealPoints - myMinHealPoints + 1)) + myMinHealPoints;
            setMyHitPoints(getMyHitPoints()+healAmount);
            System.out.println(getMyName() + " Successfully healed for " + healAmount + " points");
        }
    }
public static Monster testMonsterInstance(String name, int hitPoints, int attackSpeed, double chanceToHit,
                                          int maxDamage, int minDamage, double chanceToHeal, int minHealPoints, int maxHealPoints){
        return new Monster(name, hitPoints, attackSpeed, chanceToHit,
     maxDamage, minDamage, chanceToHeal, minHealPoints, maxHealPoints);

}
}
