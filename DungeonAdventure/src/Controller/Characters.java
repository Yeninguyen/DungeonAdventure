package Controller;

import Model.Warrior;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static Controller.PlayersConstants.*;
import java.util.*;

public class Characters {

    private boolean isMoving = false;

    private int playerSpeed = 4;
    private Rectangle myHealthBar;

    private final int screenY;
    private final int screenX;

    private final GameUI myGameUI;

    private final Warrior myWarrior;

    private BufferedImage myWarriorCurrentImage;


    private final Map<Integer, BufferedImage> myWarriorImage;


    private Direction direction = Direction.EAST;



    public Characters(GameUI theGameUI) {
        myGameUI = theGameUI;
        myWarrior = Model.Warrior.getInstance();
        myWarriorImage = new HashMap<>();
        myWarrior.setMyY(myGameUI.getMyDungeonPanel().getMyTileSize() * 2);
        myWarrior.setMyX(myGameUI.getMyDungeonPanel().getMyTileSize() * 2);
        load();

        screenX = myGameUI.getMyDungeonPanel().getMyWidth() / 2 - (myGameUI.getMyDungeonPanel().getMyTileSize() / 2);
        screenY = myGameUI.getMyDungeonPanel().getMyHeight() / 2 - (myGameUI.getMyDungeonPanel().getMyTileSize() / 2);
        myWarrior.setMyHitBox(new Rectangle((int) myWarrior.getMyX(),  myWarrior.getMyY(), 28, 28));
        myWarriorCurrentImage = myWarriorImage.get(RUNNING_LEFT);
    }


    public void drawPlayer(Graphics2D theGraphics) {
        theGraphics.drawImage(myWarriorCurrentImage, screenX, screenY, 48, 48, null);
    }

    public void updatePlayerLocation() {
        if (isMoving) {

            if (myGameUI.getMyGameControls().isMyUpArrow()) {
                direction = Direction.NORTH;
                myWarriorCurrentImage = myWarriorImage.get(RUNNING_UP);
            } else if (myGameUI.getMyGameControls().isMyDownArrow()) {
                direction = Direction.SOUTH;
                myWarriorCurrentImage = myWarriorImage.get(RUNNING_DOWN);
            } else if (myGameUI.getMyGameControls().isMyLeftArrow()) {
                direction = Direction.WEST;
                myWarriorCurrentImage = myWarriorImage.get(RUNNING_LEFT);
            } else if (myGameUI.getMyGameControls().isMyRightArrow()) {
                direction = Direction.EAST;
                myWarriorCurrentImage = myWarriorImage.get(RUNNING_RIGHT);
            }
            int newX = myWarrior.getMyX();
            int newY = myWarrior.getMyY();

            switch (direction) {
                case NORTH -> newY -= playerSpeed;
                case SOUTH -> newY += playerSpeed;
                case WEST -> newX -= playerSpeed;
                case EAST -> newX += playerSpeed;
            }

            myWarrior.getMyHitBox().x = newX;
            myWarrior.getMyHitBox().y = newY;
            if(!myGameUI.getMyTileManager().isTileCollision(myWarrior.getMyHitBox())) {
                myWarrior.setMyX(newX);
                myWarrior.setMyY(newY);
            }
            }

        updateHitBox();
    }





    public void load() {
        try {
           myWarriorImage.put(RUNNING_LEFT, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorLeft.png"))));
           myWarriorImage.put(RUNNING_RIGHT, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorRight.png"))));
           myWarriorImage.put(RUNNING_UP, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorUp.png"))));
           myWarriorImage.put(RUNNING_DOWN, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorDown.png"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setMoving(boolean moving) {
        isMoving = moving;
    }


    public int getScreenY() {
        return screenY;
    }

    public int getScreenX() {
        return screenX;
    }

    public Warrior getMyWarrior() {
        return myWarrior;
    }

    public Direction getDirection() {
        return direction;
    }


    public void updateHitBox() {
        int playerX = (int) myWarrior.getMyX();
        int playerY = (int) myWarrior.getMyY();
        myWarrior.getMyHitBox().x = playerX;
        myWarrior.getMyHitBox().y = playerY;
    }


    public void drawHitBox(Graphics2D theGraphics) {
        //debugging
        theGraphics.setColor(Color.RED);
        theGraphics.drawRect(screenX, screenY, myWarrior.getMyHitBox().width, myWarrior.getMyHitBox().height);
    }

    public BufferedImage getMyWarriorCurrentImage() {
        return myWarriorCurrentImage;
    }
}
