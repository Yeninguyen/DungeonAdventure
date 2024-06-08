package Model;

public class Priestess extends Hero{
    private static Priestess myUniqueInstance;

    private Priestess(){
        super("Priestess",75,4,0.7,45,25,10,10,
                0.3,50,100,0.6);
    }
    @Override
    public void special(DungeonCharacter theOther) {
        double random = Math.random();
        if( random <= getMyChanceForSpecial()){
            int healedPoints = (int) (Math.random() * (getMySpecialMax() - getMySpecialMin() + 1)) + getMySpecialMin();
            //should not heal if already at max points
            if(getMyHitPoints() == 75){
                setMySpecialSuccess(false);
                // ensures priestess only heals to 75 at most
            } else if(getMyHitPoints() + healedPoints > 75){
                setMySpecialAmount(75-getMyHitPoints());
                setMySpecialSuccess(true);
                this.setMyHitPoints(getMyHitPoints() + getMySpecialAmount());
            } else {
                setMySpecialAmount(healedPoints);
                setMySpecialSuccess(true);
                this.setMyHitPoints(getMyHitPoints() + getMySpecialAmount());
            }

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