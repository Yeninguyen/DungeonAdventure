package Model;

public class Warrior extends Hero{
    private static Warrior MyUniqueInstance;

    private Warrior(){
       super("Warrior",125,4,0.8,60,35,10,10,0.2, 75, 175,0.4);

    }
    @Override
    protected void specialAttack(DungeonCharacter theOther) {
     double random = Math.random() +0.1;
     if(random <= getMyChanceForSpecialAttack()){
         int damage = (int) (Math.random() * (super.getMySpecialAttackMaxDamage() - super.getMySpecialAttackMinDamage() + 1)) + super.getMySpecialAttackMinDamage();
         theOther.setMyHitPoints(theOther.getMyHitPoints()-damage);
         //turn this print statement into a gui component
         System.out.println("Successfully landed special attack Crushing Blow for: " + damage + " points!");
     }
    }
    public static Warrior getInstance(){
        if(MyUniqueInstance == null){
            MyUniqueInstance = new Warrior();
        }
        return MyUniqueInstance;
    }
    public static Warrior TEST_getInstance(){
        return new Warrior();
    }
}
