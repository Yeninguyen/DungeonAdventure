package Model;

public class Priestess extends Hero{
    private static Priestess myUniqueInstance;

    private Priestess(){
        super("Priestess",75,4,0.7,45,25,10,10,
                0.3,50,100,0.6);
    }
    @Override
    protected void specialAttack(DungeonCharacter theOther) {
        double random = Math.random() +0.1;
        if(random <= getMyChanceForSpecialAttack()){
            int healedPoints = (int) (Math.random() * (getMySpecialAttackMaxDamage() - getMySpecialAttackMinDamage() + 1)) + getMySpecialAttackMinDamage();
            this.setMyHitPoints(getMyHitPoints()+healedPoints);
            //turn this print statement into a gui component
            System.out.println("Successfully landed special skill to heal: " + healedPoints + " points!");
        }
    }

    public static Priestess getMyUniqueInstance() {
        if(myUniqueInstance == null) {
            myUniqueInstance = new Priestess();
        }
        return myUniqueInstance;
    }
    public static Priestess Test_getMyUniqueInstance(){
        return new Priestess();
    }
}
