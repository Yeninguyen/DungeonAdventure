package Model;


public class Warrior extends Hero {

    public Warrior() {
        super();
        setDefaultValues();
    }

    public void setDefaultValues(){
        super.setMyX(100);
        super.setMyY(100);
    }
}
