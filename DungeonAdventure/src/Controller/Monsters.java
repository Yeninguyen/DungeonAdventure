package Controller;

import Model.Monster;
import Model.MonsterDatabase;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Monsters {
    private int x, y;
    private double directionX, directionY;
    private double speed;
    private  String monsterType;
    private  GameUI gameUI;


    private Monster myMonster;
    private boolean isAlive;

    public int soldAreaDefaultX = 0, solidAreaDefaultY = 0;
    public Rectangle solidArea = new Rectangle(0,0,40,40);

    private BufferedImage myMonsterImage;

    private boolean collision = false;


    public Monsters(GameUI gameUI, String theMonsterType) {
        this.gameUI = gameUI;
        monsterType = theMonsterType;
        init();
    }

    public void draw(Graphics2D theGraphics2D) {
        int bound = gameUI.getMyDungeonPanel().getMyTileSize();
        int screenX = x - gameUI.getMyCharacter().getMyX() + gameUI.getMyCharacter().getScreenX();
        int screenY = y - gameUI.getMyCharacter().getMyY() + gameUI.getMyCharacter().getScreenY();

        if(x + bound > gameUI.getMyCharacter().getMyX() - gameUI.getMyCharacter().getScreenX() &&
                x - bound < gameUI.getMyCharacter().getMyX() + gameUI.getMyCharacter().getScreenX() &&
                y  + bound> gameUI.getMyCharacter().getMyY() - gameUI.getMyCharacter().getScreenY() &&
                y  - bound< gameUI.getMyCharacter().getMyY() + gameUI.getMyCharacter().getScreenY()){
            theGraphics2D.drawImage(myMonsterImage, screenX, screenY, gameUI.getMyDungeonPanel().getMyTileSize(), gameUI.getMyDungeonPanel().getMyTileSize(), null);
          //  theGraphics2D.drawRect(solidArea.x, solidArea.y, solidArea.width, solidArea.height);
        }

    }

    private void init()  {
        isAlive = true;
        // Create a Monster instance from the database
        myMonster = null;
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

    public int getDirectionX() {
        return (int) directionX;
    }

    public int getDirectionY() {
        return (int) directionY;
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
}