package Model;


public class Monster extends DungeonCharacter{

    private double myChanceToHeal;
    private int myMinHealPoints;
    private int myMaxHealPoints;



    protected Monster(final String theName, final int theHitPoints, final int theAttackSpeed, final double theChanceToHit,
                    final int theMaxDamage, final int theMinDamage, final double theChanceToHeal, final int theMinHealPoints,
                      final int theMaxHealPoints, final int theX, final int theY){
        super(theName,theHitPoints,theAttackSpeed,theChanceToHit,theMaxDamage,theMinDamage,theX, theY);
        setMyChanceToHeal(theChanceToHeal);
        myMinHealPoints = theMinHealPoints;
        myMaxHealPoints = theMaxHealPoints;


    }
    void setMyMaxHealPoints(final int theMaxHealPoints){
        if(theMaxHealPoints < 0){
            throw new IllegalArgumentException("The max heal points cannot be less than zero.");
        } else{
            myMaxHealPoints = theMaxHealPoints;
        }
    }

    void setMyMinHealPoints(final int theMinHealPoints){
        if(theMinHealPoints < 0){
            throw new IllegalArgumentException("The minimum heal points cannot be less than zero.");
        } else{
            myMinHealPoints = theMinHealPoints;
        }
    }

    void setMyChanceToHeal(final double theChanceToHeal){
        if(theChanceToHeal < 0.0 || theChanceToHeal > 1.0){
            throw new IllegalArgumentException("The chance to heal must be between 0.0 and 1.0");
        }else{
            myChanceToHeal = theChanceToHeal;
        }
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

    public void heal(){
        double random = Math.random();
        if(random <= myChanceToHeal){
            int healAmount =  (int) (Math.random() * (myMaxHealPoints - myMinHealPoints + 1)) + myMinHealPoints;
            setMyHitPoints(getMyHitPoints()+healAmount);
            System.out.println(getMyName() + " Successfully healed for " + healAmount + " points");
        }
    }
public static Monster testMonsterInstance(String name, int hitPoints, int attackSpeed, double chanceToHit,
                                          int maxDamage, int minDamage, double chanceToHeal, int minHealPoints, int maxHealPoints){
        return new Monster(name, hitPoints, attackSpeed, chanceToHit,
     maxDamage, minDamage, chanceToHeal, minHealPoints, maxHealPoints,10,10);

}
}
