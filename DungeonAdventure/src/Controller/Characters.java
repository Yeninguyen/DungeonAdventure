package Controller;

import Model.Priestess;
import Model.Thief;
import Model.Warrior;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.*;

public class Characters {


    private BufferedImage pillarP;
    private BufferedImage pillarA;
    private BufferedImage pillarI;
    private BufferedImage pillarE;

    private boolean isMoving = false;

    private int playerSpeed = 4;
    private Rectangle myHealthBar;

    private final int screenY;
    private final int screenX;

    private final GameUI myGameUI;

    private  Warrior myWarrior;
    private  Thief myTheif;
    private  Priestess myPriestess;

    private BufferedImage myWarriorCurrentImage;


    private final Map<Integer, BufferedImage> myWarriorImage;


    private Direction direction = Direction.EAST;

    public static final int RUNNING_RIGHT = 1;
    public static final int RUNNING_LEFT = 2;
    public static final int RUNNING_DOWN = 3;
    public static final int RUNNING_UP = 4;


    public Characters(final GameUI theGameUI) {
        myGameUI = theGameUI;
        myWarriorImage = new HashMap<>();

        load();

        screenX = myGameUI.getMyDungeonPanel().getMyWidth() / 2 - (myGameUI.getMyDungeonPanel().getMyTileSize() / 2);
        screenY = myGameUI.getMyDungeonPanel().getMyHeight() / 2 - (myGameUI.getMyDungeonPanel().getMyTileSize() / 2);
        myWarriorCurrentImage = myWarriorImage.get(RUNNING_LEFT);
        initHeroes();
    }

    public void initHeroes(){
        //should be random depending on the entrance
        int y = myGameUI.getMyDungeonPanel().getMyTileSize() * 2;
        int x = myGameUI.getMyDungeonPanel().getMyTileSize() * 2;

        int width = 40;
        int height = 40;

        myWarrior = Model.Warrior.getInstance();
        myWarrior.setMyY(y);
        myWarrior.setMyX(x);
        myWarrior.setMyHitBox(new Rectangle((int) myWarrior.getMyX() + 10,  myWarrior.getMyY()+20, width, height));

        myTheif = Model.Thief.getMyUniqueInstance();
        myTheif.setMyY(y);
        myTheif.setMyX(x);
        myTheif.setMyHitBox(new Rectangle((int) myWarrior.getMyX() + 10,  myWarrior.getMyY()+20, width, height));



        myPriestess = Model.Priestess.getMyUniqueInstance();
        myPriestess.setMyY(y);
        myPriestess.setMyX(x);
        myPriestess.setMyHitBox(new Rectangle((int) myWarrior.getMyX() + 10,  myWarrior.getMyY()+20, width, height));

    }


    public void drawPlayer(Graphics2D theGraphics) {
        //drawPillar(theGraphics);
        theGraphics.drawImage(myWarriorCurrentImage, screenX, screenY, 48, 48, null);
        //drawHitBox(theGraphics);
       // System.out.println(myGameUI.getMyTileManager().pillarARow);

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

//           pillarP = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Pillars/PillarP.png")));
//           pillarA = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Pillars/PillarA.png")));
//           pillarE = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Pillars/PillarE.png")));
//           pillarI = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Pillars/PillarI.png")));
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
        if(myGameUI.getMyGameControls().isMyWarriorSelected()) {
            int playerX = (int) myWarrior.getMyX();
            int playerY = (int) myWarrior.getMyY();
            myWarrior.getMyHitBox().x = playerX;
            myWarrior.getMyHitBox().y = playerY;
        }
        if(myGameUI.getMyGameControls().isMyPriestessSelected()) {
            int playerX = (int) myPriestess.getMyX();
            int playerY = (int) myPriestess.getMyY();
            myPriestess.getMyHitBox().x = playerX;
            myPriestess.getMyHitBox().y = playerY;
        }
        if(myGameUI.getMyGameControls().isMyThiefSelected()) {
            int playerX = (int) myTheif.getMyX();
            int playerY = (int) myTheif.getMyY();
            myTheif.getMyHitBox().x = playerX;
            myTheif.getMyHitBox().y = playerY;
        }
    }


    public void drawHitBox(Graphics2D theGraphics) {
        //debugging
        theGraphics.setColor(Color.RED);
        theGraphics.drawRect(screenX, screenY, myWarrior.getMyHitBox().width, myWarrior.getMyHitBox().height);
    }

    public BufferedImage getMyWarriorCurrentImage() {
        return myWarriorCurrentImage;
    }

    public void drawPillar(Graphics2D theGraphics){
        int x = 3 * 64;
        int y = 3 * 64;
        int bound = 64;
        int screenX = x - myGameUI.getMyCharacter().getMyWarrior().getMyX() + myGameUI.getMyCharacter().getScreenX();
        int screenY = y - myGameUI.getMyCharacter().getMyWarrior().getMyY() + myGameUI.getMyCharacter().getScreenY();

        if(x + bound > myGameUI.getMyCharacter().getMyWarrior().getMyX() - myGameUI.getMyCharacter().getScreenX() &&
                x  - bound < myGameUI.getMyCharacter().getMyWarrior().getMyX() + myGameUI.getMyCharacter().getScreenX() &&
                y  + bound> myGameUI.getMyCharacter().getMyWarrior().getMyY() - myGameUI.getMyCharacter().getScreenY() &&
                y  - bound< myGameUI.getMyCharacter().getMyWarrior().getMyY() + myGameUI.getMyCharacter().getScreenY()){
            theGraphics.drawImage(pillarA, screenX, screenY, 32, 32, null);
        }
    }

}
