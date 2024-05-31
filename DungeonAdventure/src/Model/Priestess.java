package Model;

public class Priestess extends Hero{
    private static Priestess myUniqueInstance;

    private Priestess(){
        super("Priestess",75,4,0.7,45,25,10,10,
                0.3,50,100,0.6);
    }
    @Override
    public void special(DungeonCharacter theOther) {
        if(getMyRandomForSpecial() <= getMyChanceForSpecial()){
            int healedPoints = (int) (Math.random() * (getMySpecialMax() - getMySpecialMin() + 1)) + getMySpecialMin();
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