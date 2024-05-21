package Controller;

import Model.Warrior;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static Controller.PlayersConstants.*;
import java.util.*;

public class Characters {


    private final String myWarriorPath = "/WarriorImages/Warrior";
    private final String myPriestessPath = "/Priestess/Priestess";
    private final String myThiefPath = "/Thief/Thief";


    private boolean isAttacking = false;
    private boolean isMoving = false;

    private int playerSpeed = 4;
    private Rectangle myHealthBar;

    private final int screenY;
    private final int screenX;

    private Rectangle mySolidArea;
    public boolean collisionOn = false;




    private final GameUI myGameUI;

    private Warrior myWarrior;

    private int myAnimationTick;
    private int myAnimationIndex;
    private int myAnimationSpeed = 15;
    private int myCurrentImageLength = 6;








    private Direction direction = Direction.EAST;

    private int myCurrentAnimationNumber = IDLE_RIGHT;

    private final Map<Integer, BufferedImage[]> myAnimations;
    public Characters(GameUI theGameUI) {
        myGameUI = theGameUI;
        myAnimations = new HashMap<>();
        myWarrior = Model.Warrior.getInstance();
        myWarrior.setMyY(500);
        myWarrior.setMyX(600);
        load(1);
        screenX = myGameUI.getMyDungeonPanel().getMyWidth() / 2 - (myGameUI.getMyDungeonPanel().getMyTileSize() / 2);
        screenY = myGameUI.getMyDungeonPanel().getMyHeight() / 2 - (myGameUI.getMyDungeonPanel().getMyTileSize() / 2);
        mySolidArea = new Rectangle(screenX, screenY);

    }





    public void drawPlayer(Graphics2D theGraphics){
        int width = (int) (myGameUI.getMyDungeonPanel().getMyTileSize() *2.5);
        int height = (int) (myGameUI.getMyDungeonPanel().getMyTileSize() *2.5);
            theGraphics.drawImage( myAnimations.get(myCurrentAnimationNumber)[myAnimationIndex],screenX, screenY, width , height, null);
            //theGraphics.drawRect(screenX + 50, screenY + 80, 28, 28);
            theGraphics.setColor(Color.BLACK);
            theGraphics.drawRect(screenX + 50, screenY + 50, 50, 10);
            theGraphics.setColor(Color.red);
            theGraphics.fill(new Rectangle(screenX + 50, screenY + 50, 49, 9));

    }




    public void updatePlayerLocation() {
        if(isMoving) {
            myCurrentImageLength = 6;
            if (myGameUI.getMyGameControls().isMyUpArrow()) {
                int y = myWarrior.getMyY();

                direction = Direction.NORTH;
                myCurrentAnimationNumber = RUNNING_UP;
                y -= playerSpeed;
                myWarrior.setMyY(y);
            } else if (myGameUI.getMyGameControls().isMyDownArrow()) {
                int y = myWarrior.getMyY();

                direction = Direction.SOUTH;
                myCurrentAnimationNumber = RUNNING_DOWN;
                y += playerSpeed;
                myWarrior.setMyY(y);
            } else if (myGameUI.getMyGameControls().isMyLeftArrow()) {
                int x = myWarrior.getMyX();


                direction = Direction.WEST;
                myCurrentAnimationNumber = RUNNING_LEFT;
                x -= playerSpeed;
                myWarrior.setMyX(x);
            } else if (myGameUI.getMyGameControls().isMyRightArrow()) {
                int x = myWarrior.getMyX();

                direction = Direction.EAST;
                myCurrentAnimationNumber = RUNNING_RIGHT;
                x += playerSpeed;
                myWarrior.setMyX(x);
            }
            else{
                switch (direction) {
                    case WEST->  myCurrentAnimationNumber = IDLE_LEFT;
                    case EAST ->  myCurrentAnimationNumber = IDLE_RIGHT;
                    case NORTH ->  myCurrentAnimationNumber = IDLE_UP;
                    case SOUTH ->  myCurrentAnimationNumber = IDLE_DOWN;
                }
            }
        }
        if(isAttacking) {
            myCurrentImageLength = 2;
             switch (direction) {
                 case EAST ->  myCurrentAnimationNumber = ATTACK_RIGHT;
                 case WEST ->  myCurrentAnimationNumber = ATTACK_LEFT;
                 case NORTH ->  myCurrentAnimationNumber = ATTACK_UP;
                 case SOUTH ->  myCurrentAnimationNumber = ATTACK_DOWN;
            }

        }
    }


    public void updateAnimation() {
        myAnimationTick++;
        if (myAnimationTick >= myAnimationSpeed) {

             myAnimationTick = 0;
            myAnimationIndex++;
            if (myAnimationIndex >= myCurrentImageLength) {
                myAnimationIndex = 0;
                isAttacking = false;
            }
        }
    }





    public void load(int theSelection) {
        String path = "";
       switch (theSelection){
           case 1 -> path = "/Images" + myWarriorPath + ".png";
       }


        InputStream is = getClass().getResourceAsStream(path);

        try {
            BufferedImage img = ImageIO.read(is);


            int subimageWidth = 48;
            int subimageHeight = 48;


            // Initialize myPlayerImages array with the correct size
            BufferedImage[][] myPlayerImages = new BufferedImage[10][6];
            for (int row = 0; row < myPlayerImages.length; row++) {
                for (int col = 0; col < myPlayerImages[row].length; col++) {
                    int x = col * subimageWidth;
                    int y = row * subimageHeight;
                    myPlayerImages[row][col] = img.getSubimage(x, y, subimageWidth, subimageHeight);
                }
            }

            myAnimations.put(IDLE_UP, myPlayerImages[2]);
            myAnimations.put(IDLE_DOWN, myPlayerImages[0]);
            myAnimations.put(IDLE_LEFT, flipHorizontal(myPlayerImages[1]));
            myAnimations.put(IDLE_RIGHT, myPlayerImages[1]);
            myAnimations.put(RUNNING_RIGHT, myPlayerImages[4]);
            myAnimations.put(RUNNING_LEFT, flipHorizontal(myPlayerImages[4]));
            myAnimations.put(RUNNING_DOWN, myPlayerImages[3]);
            myAnimations.put(RUNNING_UP, myPlayerImages[5]);
            myAnimations.put(ATTACK_LEFT, flipHorizontal(myPlayerImages[7]));
            myAnimations.put(ATTACK_RIGHT, myPlayerImages[7]);
            myAnimations.put(ATTACK_DOWN, myPlayerImages[6]);
            myAnimations.put(ATTACK_UP, myPlayerImages[8]);
            myAnimations.put(DEATH_RIGHT, myPlayerImages[9]);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public BufferedImage[] flipHorizontal(BufferedImage[] images) {
        BufferedImage[] flippedImages = new BufferedImage[images.length];
        for (int i = 0; i < images.length; i++) {
            if(images[i] != null) {
                flippedImages[i] = flipHorizontal(images[i]);
            }
        }
        return flippedImages;
    }

    public BufferedImage flipHorizontal(BufferedImage image) {
        BufferedImage flippedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(), 0);
        Graphics2D g2d = flippedImage.createGraphics();
        g2d.drawImage(image, tx, null);
        g2d.dispose();
        return flippedImage;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public int getMyAnimationIndex() {
        return myAnimationIndex;
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

    public BufferedImage[] getMyCurrentAnimation() {
        return myAnimations.get(ATTACK_RIGHT);
    }

    public void heroType(int i) {
        if(i == 1){
            myWarrior = Model.Warrior.getInstance();
            myWarrior.setMyY(500);
            myWarrior.setMyX(600);
        }
    }
}

//package Controller;
//
//import Model.Warrior;
//
//import javax.imageio.ImageIO;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.util.*;
//
//public class Characters {
//
//    private int myX;
//    private int myY;
//    private BufferedImage myImg;
//    private BufferedImage[] myIdleAnimations;
//    private BufferedImage[] myIdleAnimationsUpDown;
//    private BufferedImage[] myWarriorRunLeft;
//    private BufferedImage[] myWarriorRunRight;
//    private BufferedImage[] myWarriorRunDown;
//    private BufferedImage[] myWarriorRunUp;
//    private BufferedImage[] myWarriorAttackLeft;
//    private BufferedImage[] myWarriorAttackRight;
//    private BufferedImage[] myWarriorAttackUp;
//    private BufferedImage[] myWarriorAttackDown;
//    private BufferedImage[] myWarriorDeathRight;
//
//    private final String myWarriorPath = "/WarriorImages/Warrior";
//    private final String myPriestessPath = "/Priestess/Priestess";
//    private final String myThiefPath = "/Thief/Thief";
//
//
//    private boolean isAttacking = false;
//    private boolean isMoving = false;
//
//    private int playerSpeed = 1;
//
//
//
//
//    private final GameUI myGameUI;
//
//    private final Warrior myWarrior;
//
//    private int myAnimationTick;
//    private int myAnimationIndex;
//    private int myAnimationSpeed = 30;
//
//    private final int screenX;
//    private final int screenY;
//    private BufferedImage[] myCurrentAnimation;
//
//
//    private String directions = "idle";
//
//    private final Map<String, BufferedImage[]> myAnimations;
//    public Characters(GameUI theGameUI) {
//        myGameUI = theGameUI;
//        myWarrior = Model.Warrior.getInstance();
//        myAnimations = new HashMap<>();
//        loadImage(1);
//        myCurrentAnimation = myIdleAnimations;
//        screenX = myGameUI.getMyDungeonPanel().getMyWidth() / 2 - (myGameUI.getMyDungeonPanel().getMyTileSize() / 2);
//        screenY = myGameUI.getMyDungeonPanel().getMyHeight() / 2 - (myGameUI.getMyDungeonPanel().getMyTileSize() / 2);
//       // mySolidArea = new Rectangle(screenX, screenY);
//        myWarrior.setMyY(200);
//        myWarrior.setMyX(200);
//    }
//
//
//
//    private BufferedImage[] loadIdleAnimationsLeftRight(int theCols, boolean theFlag) {
//        int numRows = 1;
//        int sheetWidth = myImg.getWidth();
//        int sheetHeight = myImg.getHeight();
//
//        int frameWidth = sheetWidth / theCols;
//        int frameHeight = sheetHeight / numRows;
//        BufferedImage[] theAnimation = new BufferedImage[theCols];
//
//        if (theFlag) {
//
//            for (int i = theAnimation.length - 1; i >= 0; i--) {
//                int startX = i * frameWidth;
//                int startY = 0;
//                theAnimation[theAnimation.length - 1 - i] = myImg.getSubimage(startX, startY, frameWidth, frameHeight);
//            }
//        } else {
//            for (int i = 0; i < theAnimation.length; i++) {
//                int startX = i * frameWidth;
//                int startY = 0;
//                theAnimation[i] = myImg.getSubimage(startX, startY, frameWidth, frameHeight);
//            }
//        }
//        return theAnimation;
//    }
//
//    private BufferedImage[] loadIdleAnimationsUpDown(int theCols) {
//        int col = 1;
//
//        int sheetWidth = myImg.getWidth();
//        int sheetHeight = myImg.getHeight();
//
//        int frameWidth = sheetWidth / col;
//        int frameHeight = sheetHeight / theCols;
//        BufferedImage[] theAnimation = new BufferedImage[theCols];
//
//        for (int row = theCols - 1; row >= 0; row--) {
//            int startX = 0;
//            int startY = row * frameHeight;
//            theAnimation[theCols - 1 - row] = myImg.getSubimage(startX, startY, frameWidth, frameHeight);
//        }
//        return theAnimation;
//    }
//
//    public void drawAnimations(Graphics2D theGraphics){
//        if(myAnimationIndex < myCurrentAnimation.length) {
//            theGraphics.drawImage(myCurrentAnimation[myAnimationIndex], myWarrior.getMyX(), myWarrior.getMyY(), 150, 150, null);
//            theGraphics.drawRect(screenX, screenY, 20, 20);
//        }
//    }
//
//
//
//    public int[] calculateXY(int x, int y){
//        if(directions.equals("right") || directions.equals("left")){
//            y += 64;
//            x -= 64;
//        }else{
//            y -= 64;
//            x += 64;
//        }
//        myWarrior.setMyX(x);
//        myWarrior.setMyY(y);
//        return new int [] {x, y};
//    }
//
//
//    public void updatePlayerLocation() {
//        if(isMoving) {
//            if (myGameUI.getMyGameControls().isMyUpArrow()) {
//                int y = myWarrior.getMyY();
//                int x = myWarrior.getMyX();
//                if(directions.equals("right") || directions.equals("left")){
//                    y += 64;
//                    x -= 64;
//                    myWarrior.setMyX(x);
//                    myWarrior.setMyY(y);
//                }
//                directions = "up";
//                myCurrentAnimation = myAnimations.get("RU");
//                y -= playerSpeed;
//                myWarrior.setMyY(y);
//            } else if (myGameUI.getMyGameControls().isMyDownArrow()) {
//                int y = myWarrior.getMyY();
//                int x = myWarrior.getMyX();
//                if(directions.equals("left") || directions.equals("right")){
//                    y += 64;
//                    x -= 64;
//                    myWarrior.setMyX(x);
//                    myWarrior.setMyY(y);
//                }
//
//                directions = "down";
//                myCurrentAnimation = myAnimations.get("RD");
//                y += playerSpeed;
//                myWarrior.setMyY(y);
//            } else if (myGameUI.getMyGameControls().isMyLeftArrow()) {
//                int y = myWarrior.getMyY();
//                int x = myWarrior.getMyX();
//                if(directions.equals("down") || directions.equals("up")){
//                    y -= 64;
//                    x += 64;
//                    myWarrior.setMyX(x);
//                    myWarrior.setMyY(y);
//                }
//
//                directions = "left";
//                myCurrentAnimation = myAnimations.get("RL");
//                x -= playerSpeed;
//                myWarrior.setMyX(x);
//            } else if (myGameUI.getMyGameControls().isMyRightArrow()) {
//                int y = myWarrior.getMyY();
//                int x = myWarrior.getMyX();
//                if(directions.equals("up") || directions.equals("down")){
//                    y -= 64;
//                    x += 64;
//                    myWarrior.setMyX(x);
//                    myWarrior.setMyY(y);
//                }
//                directions = "right";
//                myCurrentAnimation = myAnimations.get("RR");
//                x += playerSpeed;
//                myWarrior.setMyX(x);
//            }
//            else{
//                if(directions.equals("left") || directions.equals("right")) {
//                    myCurrentAnimation = myAnimations.get("Idle1");
//                }else{
//                    myCurrentAnimation = myAnimations.get("Idle2");
//                }
//            }
//        }
//        if(isAttacking) {
//            switch (directions) {
//                case "right" -> myCurrentAnimation = myAnimations.get("AR");
//                case "left" -> myCurrentAnimation = myAnimations.get("AL");
//                case "up" -> myCurrentAnimation = myAnimations.get("AU");
//                case "down" -> myCurrentAnimation = myAnimations.get("AD");
//            }
//        }
//    }
//
//
//    public void updateAnimation() {
//        myAnimationTick++;
//        if (myAnimationTick >= myAnimationSpeed) {
//            myAnimationTick = 0;
//            myAnimationIndex++;
//            if (myAnimationIndex >= myCurrentAnimation.length) {
//                myAnimationIndex = 0;
//                isAttacking = false;
//            }
//        }
//    }
//
//    private void loadImage(int theNumber){
//        String path = "";
//        switch (theNumber){
//            case 1 -> path = myWarriorPath;
//            case 2 -> path = myPriestessPath;
//            case 3 -> path = myThiefPath;
//        }
//        try {
//            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/" + path + "Idle.png")));
//            myIdleAnimations = loadIdleAnimationsLeftRight(5, false);
//            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/" + path + "RunRight.png")));
//            myWarriorRunRight = loadIdleAnimationsLeftRight(8, false);
//            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/" + path + "RunLeft.png")));
//            myWarriorRunLeft = loadIdleAnimationsLeftRight(8, true);
//            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/" + path + "RunDown.png")));
//            myWarriorRunDown = loadIdleAnimationsUpDown(8);
//            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/" + path + "RunUp.png")));
//            myWarriorRunUp = loadIdleAnimationsUpDown(8);
//            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/" + path + "AttackRight.png")));
//            myWarriorAttackRight = loadIdleAnimationsLeftRight(7, false);
//            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/" + path + "AttackLeft.png")));
//            myWarriorAttackLeft = loadIdleAnimationsLeftRight(7, true);
//            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/" + path + "AttackDown.png")));
//            myWarriorAttackDown = loadIdleAnimationsUpDown(7);
//            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/" + path + "AttackUp.png")));
//            myWarriorAttackUp = loadIdleAnimationsUpDown(7);
//            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/" + path + "IdleUpDown.png")));
//            myIdleAnimationsUpDown = loadIdleAnimationsUpDown(5);
//            myAnimations.put("Idle1", myIdleAnimations);
//            myAnimations.put("Idle2", myIdleAnimationsUpDown);
//            myAnimations.put("RR", myWarriorRunRight);
//            myAnimations.put("RL", myWarriorRunLeft);
//            myAnimations.put("RD", myWarriorRunDown);
//            myAnimations.put("RU", myWarriorRunUp);
//            myAnimations.put("AL", myWarriorAttackLeft);
//            myAnimations.put("AR", myWarriorAttackRight);
//            myAnimations.put("AD", myWarriorAttackDown);
//            myAnimations.put("AU", myWarriorAttackUp);
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//
//    public BufferedImage[] getMyIdleAnimations() {
//        return myIdleAnimations;
//    }
//
//    public void setAttacking(boolean attacking) {
//        isAttacking = attacking;
//    }
//
//    public void setMoving(boolean moving) {
//        isMoving = moving;
//    }
//
//    public BufferedImage[] getMyWarriorAttackLeft() {
//        return myWarriorAttackLeft;
//    }
//
//    public int getMyAnimationIndex() {
//        return myAnimationIndex;
//    }
//
//    public Warrior getMyWarrior() {
//        return myWarrior;
//    }
//
//    public int getScreenX() {
//        return screenX;
//    }
//
//    public int getScreenY() {
//        return screenY;
//    }
//}