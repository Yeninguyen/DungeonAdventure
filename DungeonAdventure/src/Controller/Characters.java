package Controller;

import Model.Priestess;
import Model.Thief;
import Model.Warrior;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class Characters {


    private boolean isMoving = false;

    private int playerSpeed = 4;
    private Rectangle myHealthBarRect;
    private Rectangle myOuterHealthBarRect;

    private final int screenY;
    private final int screenX;

    private final GameUI myGameUI;

    private  Warrior myWarrior;
    private  Thief myThief;
    private  Priestess myPriestess;

    private final BufferedImage myWarriorCurrentImage;
    private final Map<Integer, BufferedImage> myThiefImages;
    private final BufferedImage myThiefCurrentImage;
    private final BufferedImage myPriestessCurrentImage;

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
    }



    public void initHeroes(){
        int x = myGameUI.getMyDungeonPanel().getMyTileSize() * myGameUI.getMyTileManager().entranceCol;
        int y = myGameUI.getMyDungeonPanel().getMyTileSize() * myGameUI.getMyTileManager().entranceRow;
        System.out.println(myGameUI.getMyTileManager().entranceCol);

        int width = 40;
        int height = 40;

            myWarrior = Model.Warrior.getInstance();
            myWarrior.setMyY(y);
            myWarrior.setMyX(x);
            myWarrior.setMyHitBox(new Rectangle( myWarrior.getMyX() + 10,  myWarrior.getMyY()+20, width, height));


            myThief = Model.Thief.getMyUniqueInstance();
            myThief.setMyY(y);
            myThief.setMyX(x);
            myThief.setMyHitBox(new Rectangle( myThief.getMyX() + 10,  myThief.getMyY()+20, width, height));

            myPriestess = Model.Priestess.getMyUniqueInstance();
            myPriestess.setMyY(y);
            myPriestess.setMyX(x);
            myPriestess.setMyHitBox(new Rectangle((int) myPriestess.getMyX() + 10,  myPriestess.getMyY()+20, width, height));

            myHealthBarRect = new Rectangle(screenX - 2, screenY - 12, 45, 10);
            myOuterHealthBarRect = new Rectangle(screenX - 2, screenY - 12, 46, 11);


    }


    public void drawPlayer(final Graphics2D theGraphics) {
        //drawPillar(theGraphics);
        if(myGameUI.getMyGameControls().isMyWarriorSelected()){
            theGraphics.drawImage(myHeroCurrentImage, screenX, screenY, 40, 40, null);
        }else if (myGameUI.getMyGameControls().isMyThiefSelected()){
            theGraphics.drawImage(myHeroCurrentImage, screenX - 10, screenY - 10, 70, 70, null);
        }else if (myGameUI.getMyGameControls().isMyPriestessSelected()){
            theGraphics.drawImage(myHeroCurrentImage, screenX - 20, screenY - 30, 70, 70, null);
        }
//        theGraphics.setColor(Color.RED);
//        theGraphics.fill(myHealthBarRect);
//        theGraphics.setColor(Color.BLACK);
//        theGraphics.draw(myOuterHealthBarRect);

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
                newX = myThief.getMyX();
                newY = myThief.getMyY();
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
                myThief.getMyHitBox().x = newX;
                myThief.getMyHitBox().y = newY;
                if (!myGameUI.getMyTileManager().isTileCollision(myThief.getMyHitBox())) {
                    myThief.setMyX(newX);
                    myThief.setMyY(newY);
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
            //checkMonsterEncounter();
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
            else if (myGameUI.getMyGameControls().isMyPriestessSelected()) {
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
            int playerX = (int) myThief.getMyX();
            int playerY = (int) myThief.getMyY();
            myThief.getMyHitBox().x = playerX;
            myThief.getMyHitBox().y = playerY;
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
            return myThief.getMyX();
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
            return myThief.getMyY();
        } else if (myGameUI.getMyGameControls().isMyPriestessSelected()) {
            return myPriestess.getMyY();
        }else{
            return  0;
        }
    }

    public BufferedImage getMyPriestessCurrentImage() {
        return myPriestessCurrentImage;
    }
//    public void checkMonsterEncounter() {
//        int playerRow = getMyY() / myGameUI.getMyDungeonPanel().getMyTileSize();
//        int playerCol = getMyX() / myGameUI.getMyDungeonPanel().getMyTileSize();
//        String roomCoordinate = playerRow + "," + playerCol;
//
//        if (myGameUI.getMyTileManager().monsterRooms.containsKey(roomCoordinate)) {
//            String monsterType = myGameUI.getMyTileManager().monsterRooms.get(roomCoordinate);
//            handleMonsterEncounter(monsterType);
//        }
//    }
//    private void handleMonsterEncounter(String monsterType) {
//        // Show a popup or dialog indicating the monster encounter
//        // You can also trigger a battle sequence or implement other logic here
//        JOptionPane.showMessageDialog(null, "You encountered a " + monsterType + "!");
//    }

    public Rectangle getHitBox(){
        if (myGameUI.getMyGameControls().isMyWarriorSelected()) {
            return myWarrior.getMyHitBox();
        } else if (myGameUI.getMyGameControls().isMyThiefSelected()) {
            return myThief.getMyHitBox();
        } else if (myGameUI.getMyGameControls().isMyPriestessSelected()) {
            return myPriestess.getMyHitBox();
        }else{
            return  null;
        }
    }
}
