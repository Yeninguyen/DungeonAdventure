package Controller;

import View.DungeonPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperItems {
    public BufferedImage image;
    public String name;
    private boolean collision = false;
    public int worldX, worldY;
    GameUI myGameUI;

    public void setGameUI(GameUI theGameUI) {
        if (theGameUI == null) {
            throw new IllegalArgumentException("GameUI cannot be null");
        }
        myGameUI = theGameUI;
    }

    public void draw(Graphics2D theGraphics2D) {
        int bound = myGameUI.getMyDungeonPanel().getMyTileSize();
        int screenX = worldX - myGameUI.getMyCharacter().getMyWarrior().getMyX() + myGameUI.getMyCharacter().getScreenX();
        int screenY = worldY - myGameUI.getMyCharacter().getMyWarrior().getMyY() + myGameUI.getMyCharacter().getScreenY();

        if(worldX + bound > myGameUI.getMyCharacter().getMyWarrior().getMyX() - myGameUI.getMyCharacter().getScreenX() &&
                worldX - bound < myGameUI.getMyCharacter().getMyWarrior().getMyX() + myGameUI.getMyCharacter().getScreenX() &&
                worldY  + bound> myGameUI.getMyCharacter().getMyWarrior().getMyY() - myGameUI.getMyCharacter().getScreenY() &&
                worldY  - bound< myGameUI.getMyCharacter().getMyWarrior().getMyY() + myGameUI.getMyCharacter().getScreenY()){
            theGraphics2D.drawImage(image, screenX, screenY, myGameUI.getMyDungeonPanel().getMyTileSize(), myGameUI.getMyDungeonPanel().getMyTileSize(), null);
        }
    }


}
