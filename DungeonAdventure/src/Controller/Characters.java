package Controller;

import Model.Priestess;
import Model.Thief;
import Model.Warrior;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static Model.PlayersConstants.*;
import java.util.*;

public class Characters {

    private boolean isMoving = false;

    private int playerSpeed = 4;
    private Rectangle myHealthBar;

    private final int screenY;
    private final int screenX;

    private final GameUI myGameUI;

    private final Warrior myWarrior;
    private Priestess myPriestess;
    private Thief myThief;
    private BufferedImage myWarriorCurrentImage;
    private BufferedImage myPriestessCurrentImage;
    private BufferedImage myThiefCurrentImage;

    private final Map<Integer, BufferedImage> myWarriorImage;
    private final Map<Integer, BufferedImage> myPriestessImage;
    private Map<Integer, BufferedImage> myThieftImage;




    private Direction direction = Direction.EAST;


    public Characters(GameUI theGameUI) {
        myGameUI = theGameUI;

        myPriestessImage = new HashMap<>();
        myPriestess = Model.Priestess.getMyUniqueInstance();
        myPriestess.setMyX(myGameUI.getMyDungeonPanel().getMyTileSize() * 2);
        myPriestess.setMyY(myGameUI.getMyDungeonPanel().getMyTileSize() * 2);
        myWarrior = Model.Warrior.getInstance();
        myWarriorImage = new HashMap<>();
        myWarrior.setMyY(myGameUI.getMyDungeonPanel().getMyTileSize() * 12);
        myWarrior.setMyX(myGameUI.getMyDungeonPanel().getMyTileSize() * 12);
        myThief = Model.Thief.getMyUniqueInstance();
        myThieftImage = new HashMap<>();
        myThief.setMyY(myGameUI.getMyDungeonPanel().getMyTileSize() * 2);
        myThief.setMyX(myGameUI.getMyDungeonPanel().getMyTileSize() * 2);
        load();
        screenX = myGameUI.getMyDungeonPanel().getMyWidth() / 2 - (myGameUI.getMyDungeonPanel().getMyTileSize() / 2);
        screenY = myGameUI.getMyDungeonPanel().getMyHeight() / 2 - (myGameUI.getMyDungeonPanel().getMyTileSize() / 2);
        myWarrior.setMyHitBox(new Rectangle((int) myWarrior.getMyX(), myWarrior.getMyY(), 40, 40));
        myWarriorCurrentImage = myWarriorImage.get(RUNNING_LEFT);
        myPriestessCurrentImage = myPriestessImage.get(RUNNING_LEFT);
        myThiefCurrentImage = myThieftImage.get(RUNNING_LEFT);

    }





    public void drawPlayer(Graphics2D theGraphics) {
        if (myGameUI.getMyGameControls().isMyWarriorSelected()) {
            theGraphics.drawImage(myWarriorCurrentImage, screenX, screenY, 48, 48, null);
        } else if (myGameUI.getMyGameControls().isMyThiefSelected()) {
            theGraphics.drawImage(myThiefCurrentImage, screenX, screenY, 48, 48, null);
        } else {
            theGraphics.drawImage(myPriestessCurrentImage, screenX, screenY, 48, 48, null);
        }

    }

    public void updatePlayerLocation() {
        if (isMoving) {

            if (myGameUI.getMyGameControls().isMyUpArrow()) {
                direction = Direction.NORTH;
                myWarriorCurrentImage = myWarriorImage.get(RUNNING_UP);
                myPriestessCurrentImage = myPriestessImage.get(RUNNING_UP);
                myThiefCurrentImage = myThieftImage.get(RUNNING_UP);

            } else if (myGameUI.getMyGameControls().isMyDownArrow()) {
                direction = Direction.SOUTH;
                myWarriorCurrentImage = myWarriorImage.get(RUNNING_DOWN);
                myPriestessCurrentImage = myPriestessImage.get(RUNNING_DOWN);
                myThiefCurrentImage = myThieftImage.get(RUNNING_DOWN);
            } else if (myGameUI.getMyGameControls().isMyLeftArrow()) {
                direction = Direction.WEST;
                myWarriorCurrentImage = myWarriorImage.get(RUNNING_LEFT);
                myPriestessCurrentImage = myPriestessImage.get(RUNNING_LEFT);
                myThiefCurrentImage = myThieftImage.get(RUNNING_LEFT);
            } else if (myGameUI.getMyGameControls().isMyRightArrow()) {
                direction = Direction.EAST;
                myWarriorCurrentImage = myWarriorImage.get(RUNNING_RIGHT);
                myPriestessCurrentImage = myPriestessImage.get(RUNNING_RIGHT);
                myThiefCurrentImage = myThieftImage.get(RUNNING_RIGHT);
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
            if (!myGameUI.getMyTileManager().isTileCollision(myWarrior.getMyHitBox())) {
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
            myPriestessImage.put(RUNNING_LEFT, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Priestess/PriestessLeft.png"))));
            myPriestessImage.put(RUNNING_RIGHT, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Priestess/PriestessRight.png"))));
            myPriestessImage.put(RUNNING_UP, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Priestess/PriestessUp.png"))));
            myPriestessImage.put(RUNNING_DOWN, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Priestess/PriestessDown.png"))));
            myThieftImage.put(RUNNING_LEFT, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Thief/ThiefLeft.png"))));
            myThieftImage.put(RUNNING_RIGHT, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Thief/ThiefRight.png"))));
            myThieftImage.put(RUNNING_UP, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Thief/ThiefUp.png"))));
            myThieftImage.put(RUNNING_DOWN, ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Thief/ThiefDown.png"))));
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

    public BufferedImage getMyPriestessCurrentImage() {
        return myPriestessCurrentImage;
    }

    public BufferedImage getMyThiefCurrentImage() {
        return myThiefCurrentImage;
    }
}

//import Model.Hero;
//import Model.Priestess;
//import Model.Thief;
//import Model.Warrior;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.util.*;
//import java.util.List;
//
//import static Model.PlayersConstants.*;
//
//public class Characters {
//
//    private boolean isMoving = false;
//    private int playerSpeed = 4;
//    private Rectangle myHealthBar;
//
//    private final int screenY;
//    private final int screenX;
//
//    private final GameUI myGameUI;
//
//    private final Warrior myWarrior;
//    private Priestess myPriestess;
//    private Thief myThief;
//
//    private BufferedImage myWarriorCurrentImage;
//    private BufferedImage myPriestessCurrentImage;
//    private BufferedImage myThiefCurrentImage;
//
//    private final List<Hero> characters = new ArrayList<>();
//    private Hero currentCharacter;
//
//    private Direction direction = Direction.EAST;
//
//    public Characters(GameUI theGameUI) {
//        myGameUI = theGameUI;
//
//        screenX = myGameUI.getMyDungeonPanel().getMyWidth() / 2 - (myGameUI.getMyDungeonPanel().getMyTileSize() / 2);
//        screenY = myGameUI.getMyDungeonPanel().getMyHeight() / 2 - (myGameUI.getMyDungeonPanel().getMyTileSize() / 2);
//
//        myWarrior = Warrior.getInstance();
//        myPriestess = Priestess.getMyUniqueInstance();
//        myThief = Thief.getMyUniqueInstance();
//
//        initializeCharacters();
//
//        // Set initial current character
//        currentCharacter = characters.get(0);
//        myWarriorCurrentImage = myWarrior.getMyCurrentImage();
//        myPriestessCurrentImage = myPriestess.getMyCurrentImage();
//        myThiefCurrentImage = myThief.getMyCurrentImage();
////        myWarriorCurrentImage = myWarriorImage.get(RUNNING_LEFT);
////        myPriestessCurrentImage = myPriestessImage.get(RUNNING_LEFT);
////        myThiefCurrentImage = myThieftImage.get(RUNNING_LEFT);
//    }
//
//    private void initializeCharacters() {
//        myPriestess.setMyX(myGameUI.getMyDungeonPanel().getMyTileSize() * 2);
//        myPriestess.setMyY(myGameUI.getMyDungeonPanel().getMyTileSize() * 2);
//        myWarrior.setMyY(myGameUI.getMyDungeonPanel().getMyTileSize() * 2);
//        myWarrior.setMyX(myGameUI.getMyDungeonPanel().getMyTileSize() * 2);
//        myThief.setMyY(myGameUI.getMyDungeonPanel().getMyTileSize() * 2);
//        myThief.setMyX(myGameUI.getMyDungeonPanel().getMyTileSize() * 2);
//
//        characters.add(myWarrior);
//        characters.add(myPriestess);
//        characters.add(myThief);
//
//        loadCharacterImages();
//    }
//
//    private void loadCharacterImages() {
//        for (Hero character : characters) {
//            try {
//                character.loadImages();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void drawPlayer(Graphics2D theGraphics) {
//        theGraphics.drawImage(currentCharacter.getMyCurrentImage(), screenX, screenY, 48, 48, null);
//    }
//
//    public void updatePlayerLocation() {
//        if (isMoving) {
//            if (myGameUI.getMyGameControls().isMyUpArrow()) {
//                direction = Direction.NORTH;
//                currentCharacter.setMyCurrentImage(currentCharacter.getMyImages().get(RUNNING_UP));
//            } else if (myGameUI.getMyGameControls().isMyDownArrow()) {
//                direction = Direction.SOUTH;
//                currentCharacter.setMyCurrentImage(currentCharacter.getMyImages().get(RUNNING_DOWN));
//            } else if (myGameUI.getMyGameControls().isMyLeftArrow()) {
//                direction = Direction.WEST;
//                currentCharacter.setMyCurrentImage(currentCharacter.getMyImages().get(RUNNING_LEFT));
//            } else if (myGameUI.getMyGameControls().isMyRightArrow()) {
//                direction = Direction.EAST;
//                currentCharacter.setMyCurrentImage(currentCharacter.getMyImages().get(RUNNING_RIGHT));
//            }
//
//            int newX = currentCharacter.getMyX();
//            int newY = currentCharacter.getMyY();
//
//            switch (direction) {
//                case NORTH -> newY -= playerSpeed;
//                case SOUTH -> newY += playerSpeed;
//                case WEST -> newX -= playerSpeed;
//                case EAST -> newX += playerSpeed;
//            }
//
//            currentCharacter.getMyHitBox().x = newX;
//            currentCharacter.getMyHitBox().y = newY;
//            if (!myGameUI.getMyTileManager().isTileCollision(currentCharacter.getMyHitBox())) {
//                currentCharacter.setMyX(newX);
//                currentCharacter.setMyY(newY);
//            }
//        }
//        updateHitBox();
//    }
//
//    public void setMoving(boolean moving) {
//        isMoving = moving;
//    }
//
//    public int getScreenY() {
//        return screenY;
//    }
//
//    public int getScreenX() {
//        return screenX;
//    }
//
//    public Warrior getMyWarrior() {
//        return myWarrior;
//    }
//
//    public Direction getDirection() {
//        return direction;
//    }
//
//    public void updateHitBox() {
//        int playerX = currentCharacter.getMyX();
//        int playerY = currentCharacter.getMyY();
//        currentCharacter.getMyHitBox().x = playerX;
//        currentCharacter.getMyHitBox().y = playerY;
//    }
//
//    public void drawHitBox(Graphics2D theGraphics) {
//        // debugging
//        theGraphics.setColor(Color.RED);
//        theGraphics.drawRect(screenX, screenY, currentCharacter.getMyHitBox().width, currentCharacter.getMyHitBox().height);
//    }
//
//    public BufferedImage getMyWarriorCurrentImage() {
//        return myWarriorCurrentImage;
//    }
//
//    public BufferedImage getMyPriestessCurrentImage() {
//        return myPriestessCurrentImage;
//    }
//
//    public BufferedImage getMyThiefCurrentImage() {
//        return myThiefCurrentImage;
//    }
