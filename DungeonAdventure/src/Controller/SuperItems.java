package Controller;

import Model.Monster;
import Model.MonsterDatabase;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

public class SuperItems {
    private BufferedImage image;
    private String name;
    private boolean collision = false;
    private int worldX, worldY;

    public int soldAreaDefaultX = 0, solidAreaDefaultY = 0;
    GameUI myGameUI;

    public Rectangle solidArea = new Rectangle(0,0,40,40);

    private Monster myMonster;

    public SuperItems(GameUI myGameUI) {
        this.myGameUI = myGameUI;
        init();
    }

    public void draw(Graphics2D theGraphics2D) {
        int bound = myGameUI.getMyDungeonPanel().getMyTileSize();
        int screenX = worldX - myGameUI.getMyCharacter().getMyX() + myGameUI.getMyCharacter().getScreenX();
        int screenY = worldY - myGameUI.getMyCharacter().getMyY() + myGameUI.getMyCharacter().getScreenY();

        if(worldX + bound > myGameUI.getMyCharacter().getMyX() - myGameUI.getMyCharacter().getScreenX() &&
                worldX - bound < myGameUI.getMyCharacter().getMyX() + myGameUI.getMyCharacter().getScreenX() &&
                worldY  + bound> myGameUI.getMyCharacter().getMyY() - myGameUI.getMyCharacter().getScreenY() &&
                worldY  - bound< myGameUI.getMyCharacter().getMyY() + myGameUI.getMyCharacter().getScreenY()){
            theGraphics2D.drawImage(image, screenX + 10, screenY + 10, myGameUI.getMyDungeonPanel().getMyTileSize() - 30, myGameUI.getMyDungeonPanel().getMyTileSize() - 30, null);
        }
    }

    private void init()  {

        // Create a Monster instance from the database
        myMonster = null;
        try {
            myMonster = MonsterDatabase.getMonster(name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public boolean isCollision() {
        return collision;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }


}