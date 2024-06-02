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


    private int myX;
    private int myY;


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
    private Map<Integer, BufferedImage> myThiefImages;
    private BufferedImage myThiefCurrentImage;
    private BufferedImage myPriestessCurrentImage;

    private BufferedImage myHeroCurrentImage;

    private final Map<Integer, BufferedImage> myWarriorImage;
    private final Map<Integer, BufferedImage> myPriestessImages;


    private Direction direction = Direction.EAST;

    public static final int RUNNING_RIGHT = 1;
    public static final int RUNNING_LEFT = 2;
    public static final int RUNNING_DOWN = 3;
    public static final int RUNNING_UP = 4;


    public Characters(final GameUI theGameUI) {
        myGameUI = theGameUI;
        myWarriorImage = new HashMap<>();
        myThiefImages = new HashMap<>();
        myPriestessImages = new HashMap<>();

        screenX = myGameUI.getMyDungeonPanel().getMyWidth() / 2 - (myGameUI.getMyDungeonPanel().getMyTileSize() / 2);
        screenY = myGameUI.getMyDungeonPanel().getMyHeight() / 2 - (myGameUI.getMyDungeonPanel().getMyTileSize() / 2);
        load();
        myWarriorCurrentImage = myWarriorImage.get(RUNNING_LEFT);
        myThiefCurrentImage = myThiefImages.get(RUNNING_LEFT);
        myPriestessCurrentImage = myPriestessImages.get(RUNNING_LEFT);
        initHeroes();


    }


    public void initHeroes(){
        int x = myGameUI.getMyDungeonPanel().getMyTileSize() * myGameUI.getMyTileManager().entranceCol;
        int y = myGameUI.getMyDungeonPanel().getMyTileSize() * myGameUI.getMyTileManager().entranceRow;

        int width = 40;
        int height = 40;




        myWarrior = Model.Warrior.getInstance();
        myWarrior.setMyY(y);
        myWarrior.setMyX(x);
        myWarrior.setMyHitBox(new Rectangle( myWarrior.getMyX() + 10,  myWarrior.getMyY()+20, width, height));


        myTheif = Model.Thief.getMyUniqueInstance();
        myTheif.setMyY(y);
        myTheif.setMyX(x);
        myTheif.setMyHitBox(new Rectangle( myTheif.getMyX() + 10,  myTheif.getMyY()+20, width, height));

        myPriestess = Model.Priestess.getMyUniqueInstance();
        myPriestess.setMyY(y);
        myPriestess.setMyX(x);
        myPriestess.setMyHitBox(new Rectangle((int) myPriestess.getMyX() + 10,  myPriestess.getMyY()+20, width, height));


    }


    public void drawPlayer(Graphics2D theGraphics) {
        //drawPillar(theGraphics);
        if(myGameUI.getMyGameControls().isMyWarriorSelected()){
            theGraphics.drawImage(myHeroCurrentImage, screenX, screenY, 40, 40, null);
        }else if (myGameUI.getMyGameControls().isMyThiefSelected()){
            theGraphics.drawImage(myHeroCurrentImage, screenX - 10, screenY - 10, 70, 70, null);
        }else if (myGameUI.getMyGameControls().isMyPriestessSelected()){
            theGraphics.drawImage(myHeroCurrentImage, screenX - 20, screenY - 30, 70, 70, null);
        }

        //drawHitBox(theGraphics);
        // System.out.println(myGameUI.getMyTileManager().pillarARow);

    }



    public void updatePlayerLocation() {
        if (myGameUI.getMyGameControls().isMyWarriorSelected()) {
            myHeroCurrentImage = myWarriorCurrentImage;
        } else if (myGameUI.getMyGameControls().isMyThiefSelected()) {
            myHeroCurrentImage = myThiefCurrentImage;
        }else if(myGameUI.getMyGameControls().isMyPriestessSelected()){
            myHeroCurrentImage = myPriestessCurrentImage;
        }

        if (isMoving) {
            if (myGameUI.getMyGameControls().isMyUpArrow()) {
                direction = Direction.NORTH;
                if (myGameUI.getMyGameControls().isMyThiefSelected()) {
                    myHeroCurrentImage = myThiefImages.get(RUNNING_UP);
                } else if (myGameUI.getMyGameControls().isMyWarriorSelected()) {
                    myHeroCurrentImage = myWarriorImage.get(RUNNING_UP);
                } else if(myGameUI.getMyGameControls().isMyPriestessSelected()){
                    myHeroCurrentImage = myPriestessImages.get(RUNNING_UP);
                }
            } else if (myGameUI.getMyGameControls().isMyDownArrow()) {
                direction = Direction.SOUTH;
                if (myGameUI.getMyGameControls().isMyThiefSelected()) {
                    myHeroCurrentImage = myThiefImages.get(RUNNING_DOWN);
                } else if (myGameUI.getMyGameControls().isMyWarriorSelected()) {
                    myHeroCurrentImage = myWarriorImage.get(RUNNING_DOWN);
                }
                else if(myGameUI.getMyGameControls().isMyPriestessSelected()){
                    myHeroCurrentImage = myPriestessImages.get(RUNNING_DOWN);
                }
            } else if (myGameUI.getMyGameControls().isMyLeftArrow()) {
                direction = Direction.WEST;
                if (myGameUI.getMyGameControls().isMyThiefSelected()) {
                    myHeroCurrentImage = myThiefImages.get(RUNNING_LEFT);
                } else if (myGameUI.getMyGameControls().isMyWarriorSelected()) {
                    myHeroCurrentImage = myWarriorImage.get(RUNNING_LEFT);
                }
                else if(myGameUI.getMyGameControls().isMyPriestessSelected()){
                    myHeroCurrentImage = myPriestessImages.get(RUNNING_LEFT);
                }
            } else if (myGameUI.getMyGameControls().isMyRightArrow()) {
                direction = Direction.EAST;
                if (myGameUI.getMyGameControls().isMyThiefSelected()) {
                    myHeroCurrentImage = myThiefImages.get(RUNNING_RIGHT);
                } else if (myGameUI.getMyGameControls().isMyWarriorSelected()) {
                    myHeroCurrentImage = myWarriorImage.get(RUNNING_RIGHT);
                }
                else if(myGameUI.getMyGameControls().isMyPriestessSelected()){
                    myHeroCurrentImage = myPriestessImages.get(RUNNING_RIGHT);
                }
            }

            int newX = 0;
            int newY = 0;

            if (myGameUI.getMyGameControls().isMyWarriorSelected()) {
                newX = myWarrior.getMyX();
                newY = myWarrior.getMyY();
            }
            if (myGameUI.getMyGameControls().isMyThiefSelected()) {
                newX = myTheif.getMyX();
                newY = myTheif.getMyY();
            }
            if (myGameUI.getMyGameControls().isMyPriestessSelected()) {
                newX = myPriestess.getMyX();
                newY = myPriestess.getMyY();
            }

            switch (direction) {
                case NORTH -> newY -= playerSpeed;
                case SOUTH -> newY += playerSpeed;
                case WEST -> newX -= playerSpeed;
                case EAST -> newX += playerSpeed;
            }

            if (myGameUI.getMyGameControls().isMyThiefSelected()) {
                myTheif.getMyHitBox().x = newX;
                myTheif.getMyHitBox().y = newY;
                if (!myGameUI.getMyTileManager().isTileCollision(myTheif.getMyHitBox())) {
                    myTheif.setMyX(newX);
                    myTheif.setMyY(newY);
                }
            }
            if (myGameUI.getMyGameControls().isMyWarriorSelected()) {
                myWarrior.getMyHitBox().x = newX;
                myWarrior.getMyHitBox().y = newY;
                if (!myGameUI.getMyTileManager().isTileCollision(myWarrior.getMyHitBox())) {
                    myWarrior.setMyX(newX);
                    myWarrior.setMyY(newY);
                }
            }
            if (myGameUI.getMyGameControls().isMyPriestessSelected()) {
                myPriestess.getMyHitBox().x = newX;
                myPriestess.getMyHitBox().y = newY;
                if (!myGameUI.getMyTileManager().isTileCollision(myPriestess.getMyHitBox())) {
                    myPriestess.setMyX(newX);
                    myPriestess.setMyY(newY);
                }
            }

            updateHitBox();
        } else {
            // Set the appropriate image when not moving
            if (myGameUI.getMyGameControls().isMyThiefSelected()) {
                switch (direction) {
                    case NORTH -> myHeroCurrentImage = myThiefImages.get(RUNNING_UP);
                    case SOUTH -> myHeroCurrentImage = myThiefImages.get(RUNNING_DOWN);
                    case WEST -> myHeroCurrentImage = myThiefImages.get(RUNNING_LEFT);
                    case EAST -> myHeroCurrentImage = myThiefImages.get(RUNNING_RIGHT);
                }
            } else if (myGameUI.getMyGameControls().isMyWarriorSelected()) {
                switch (direction) {
                    case NORTH -> myHeroCurrentImage = myWarriorImage.get(RUNNING_UP);
                    case SOUTH -> myHeroCurrentImage = myWarriorImage.get(RUNNING_DOWN);
                    case WEST -> myHeroCurrentImage = myWarriorImage.get(RUNNING_LEFT);
                    case EAST -> myHeroCurrentImage = myWarriorImage.get(RUNNING_RIGHT);
                }
            }
            else if (myGameUI.getMyGameControls().isMyWarriorSelected()) {
                switch (direction) {
                    case NORTH -> myHeroCurrentImage = myPriestessImages.get(RUNNING_UP);
                    case SOUTH -> myHeroCurrentImage = myPriestessImages.get(RUNNING_DOWN);
                    case WEST -> myHeroCurrentImage = myPriestessImages.get(RUNNING_LEFT);
                    case EAST -> myHeroCurrentImage = myPriestessImages.get(RUNNING_RIGHT);
                }
            }
        }
    }


    public void load() {
        try {
            myWarriorImage.put(RUNNING_LEFT, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorLeft.png"))));
            myWarriorImage.put(RUNNING_RIGHT, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorRight.png"))));
            myWarriorImage.put(RUNNING_UP, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorUp.png"))));
            myWarriorImage.put(RUNNING_DOWN, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorDown.png"))));

            myThiefImages.put(RUNNING_LEFT, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Thief/ThiefLeft.png"))));
            myThiefImages.put(RUNNING_RIGHT, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Thief/ThiefRight.png"))));
            myThiefImages.put(RUNNING_UP, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Thief/ThiefUp.png"))));
            myThiefImages.put(RUNNING_DOWN, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Thief/ThiefDown.png"))));


            myPriestessImages.put(RUNNING_LEFT, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Priestess/PriestessLeft.png"))));
            myPriestessImages.put(RUNNING_RIGHT, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Priestess/PriestessRight.png"))));
            myPriestessImages.put(RUNNING_UP, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Priestess/PriestessUp.png"))));
            myPriestessImages.put(RUNNING_DOWN, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Priestess/PriestessDown.png"))));

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

    public BufferedImage getMyThiefCurrentImage() {
        return myThiefCurrentImage;
    }

    public int getMyX() {
        if(myGameUI.getMyGameControls().isMyWarriorSelected()){
            return myWarrior.getMyX();
        }else if (myGameUI.getMyGameControls().isMyThiefSelected()){
            return myTheif.getMyX();
        }else if (myGameUI.getMyGameControls().isMyPriestessSelected()) {
            return myPriestess.getMyX();
        }else{
            return  0;
        }

    }

    public int getMyY() {
        if (myGameUI.getMyGameControls().isMyWarriorSelected()) {
            return myWarrior.getMyY();
        } else if (myGameUI.getMyGameControls().isMyThiefSelected()) {
            return myTheif.getMyY();
        } else if (myGameUI.getMyGameControls().isMyPriestessSelected()) {
            return myPriestess.getMyY();
        }else{
            return  0;
        }
    }

    public BufferedImage getMyPriestessCurrentImage() {
        return myPriestessCurrentImage;
    }
}