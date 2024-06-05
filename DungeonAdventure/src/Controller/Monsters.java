package Controller;

import Model.DungeonCharacter;
import Model.Monster;
import Model.MonsterDatabase;




import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;


public class Monsters {



    private int x, y;
    private double speed;
    private String monsterType;
    private final GameUI gameUI;

    private long encounterTime;

    private Monster myMonster;

    private boolean isAlive;

    public Rectangle solidArea = new Rectangle(0,0,40,40);

    private BufferedImage myMonsterImage;

    private boolean collision = false;

    private static Monsters myInstance;




    public Monsters(GameUI gameUI, String theMonsterType) {
        this.gameUI = gameUI;
        monsterType = theMonsterType;
        init();
    }

    public void draw(Graphics2D theGraphics2D) {
        int bound = gameUI.getMyDungeonPanel().getMyTileSize();
        int screenX = x - gameUI.getMyCharacter().getMyX() + gameUI.getMyCharacter().getScreenX();
        int screenY = y - gameUI.getMyCharacter().getMyY() + gameUI.getMyCharacter().getScreenY();

        int solidAreaScreenX = screenX - solidArea.x;
        int solidAreaScreenY = screenY - solidArea.y;

        if (x + bound > gameUI.getMyCharacter().getMyX() - gameUI.getMyCharacter().getScreenX() &&
                x - bound < gameUI.getMyCharacter().getMyX() + gameUI.getMyCharacter().getScreenX() &&
                y + bound > gameUI.getMyCharacter().getMyY() - gameUI.getMyCharacter().getScreenY() &&
                y - bound < gameUI.getMyCharacter().getMyY() + gameUI.getMyCharacter().getScreenY()) {
            theGraphics2D.drawImage(myMonsterImage, screenX, screenY, gameUI.getMyDungeonPanel().getMyTileSize(), gameUI.getMyDungeonPanel().getMyTileSize(), null);

        }
        theGraphics2D.setColor(Color.RED); // Set the color for the solid area rectangle
        theGraphics2D.drawRect(solidAreaScreenX, solidAreaScreenY, solidArea.width, solidArea.height);
    }

    public void handleMonsterEncounter(Monsters monster) {
        monster.encounterTime = System.currentTimeMillis();
        // Add any additional logic or effects you want for the monster encounter
    }

    private void init()  {
        isAlive = true;
        // Create a Monster instance from the database
        try {
            myMonster = MonsterDatabase.getMonster(monsterType);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public int getSpeed() {
        return (int) speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getMonsterType() {
        return monsterType;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Monster getMyMonster() {
        System.out.println(myMonster.getMyHitPoints());
        return myMonster;
    }

    public void setMyMonsterImage(BufferedImage myMonsterImage) {
        this.myMonsterImage = myMonsterImage;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setMonsterType(String monsterType) {
        this.monsterType = monsterType;
    }
}