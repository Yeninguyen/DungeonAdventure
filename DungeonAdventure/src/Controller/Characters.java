package Controller;

import Model.Warrior;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class Characters {

    private int myX;
    private int myY;
    private BufferedImage myImg;
    private BufferedImage[] myIdleAnimations;
    private BufferedImage[] myIdleAnimationsUpDown;
    private BufferedImage[] myWarriorRunLeft;
    private BufferedImage[] myWarriorRunRight;
    private BufferedImage[] myWarriorRunDown;
    private BufferedImage[] myWarriorRunUp;
    private BufferedImage[] myWarriorAttackLeft;
    private BufferedImage[] myWarriorAttackRight;
    private BufferedImage[] myWarriorAttackUp;
    private BufferedImage[] myWarriorAttackDown;
    private BufferedImage[] myWarriorDeathRight;

    private final String myWarriorPath = "/WarriorImages/Warrior";
    private final String myPriestessPath = "/Priestess/Priestess";
    private final String myThiefPath = "/Thief/Thief";


    private boolean isAttacking = false;
    private boolean isMoving = false;

    private int playerSpeed = 4;
    private final int screenX;
    private final int screenY;




    private final GameUI myGameUI;

    private final Warrior myWarrior;




    private int myAnimationTick;
    private int myAnimationIndex;
    private int myAnimationSpeed = 10;
    private BufferedImage[] myCurrentAnimation;


    private String directions = "idle";

    private final Map<String, BufferedImage[]> myAnimations;

    public Characters(GameUI theGameUI) {
        myGameUI = theGameUI;
        myWarrior = Model.Warrior.getInstance();
        myAnimations = new HashMap<>();
        loadImage(1);
        myCurrentAnimation = myIdleAnimations;
        myWarrior.setMyY(200);
        myWarrior.setMyX(200);
        screenX = myGameUI.getMyDungeonPanel().getMyWidth()/2-myGameUI.getMyDungeonPanel().getMyTileSize()/2;
        screenY = myGameUI.getMyDungeonPanel().getMyHeight()/2-myGameUI.getMyDungeonPanel().getMyTileSize()/2;
    }



    private BufferedImage[] loadIdleAnimationsLeftRight(int theCols, boolean theFlag) {
        int numRows = 1;
        int sheetWidth = myImg.getWidth();
        int sheetHeight = myImg.getHeight();

        int frameWidth = sheetWidth / theCols;
        int frameHeight = sheetHeight / numRows;
        BufferedImage[] theAnimation = new BufferedImage[theCols];

        if (theFlag) {

            for (int i = theAnimation.length - 1; i >= 0; i--) {
                int startX = i * frameWidth;
                int startY = 0;
                theAnimation[theAnimation.length - 1 - i] = myImg.getSubimage(startX, startY, frameWidth, frameHeight);
            }
        } else {
            for (int i = 0; i < theAnimation.length; i++) {
                int startX = i * frameWidth;
                int startY = 0;
                theAnimation[i] = myImg.getSubimage(startX, startY, frameWidth, frameHeight);
            }
        }
        return theAnimation;
    }

    private BufferedImage[] loadIdleAnimationsUpDown(int theCols) {
        int col = 1;

        int sheetWidth = myImg.getWidth();
        int sheetHeight = myImg.getHeight();

        int frameWidth = sheetWidth / col;
        int frameHeight = sheetHeight / theCols;
        BufferedImage[] theAnimation = new BufferedImage[theCols];

            for (int row = theCols - 1; row >= 0; row--) {
                int startX = 0;
                int startY = row * frameHeight;
                theAnimation[theCols - 1 - row] = myImg.getSubimage(startX, startY, frameWidth, frameHeight);
            }
        return theAnimation;
    }

    public void drawAnimations(Graphics2D theGraphics){
        if(myAnimationIndex < myCurrentAnimation.length) {
            theGraphics.drawImage(myCurrentAnimation[myAnimationIndex], myWarrior.getMyX(), myWarrior.getMyY(), 150, 150, null);
            //theGraphics.drawRect(screenX, screenY, 20, 20);
        }
    }


    public int[] calculateXY(int x, int y){
        if(directions.equals("right") || directions.equals("left")){
            y += 64;
            x -= 64;
        }else{
            y -= 64;
            x += 64;
        }
        myWarrior.setMyX(x);
        myWarrior.setMyY(y);
        return new int [] {x, y};
    }


    public void updatePlayerLocation() {
        if(isMoving) {
            if (myGameUI.getMyGameControls().isMyUpArrow()) {
                int y = myWarrior.getMyY();
                int x = myWarrior.getMyX();
                if(directions.equals("right") || directions.equals("left")){
                    y += 64;
                    x -= 64;
                    myWarrior.setMyX(x);
                    myWarrior.setMyY(y);
                }
                directions = "up";
                myCurrentAnimation = myAnimations.get("RU");
                 y -= playerSpeed;
                myWarrior.setMyY(y);
            } else if (myGameUI.getMyGameControls().isMyDownArrow()) {
                int y = myWarrior.getMyY();
                int x = myWarrior.getMyX();
                if(directions.equals("left") || directions.equals("right")){
                    y += 64;
                    x -= 64;
                    myWarrior.setMyX(x);
                    myWarrior.setMyY(y);
                }

                directions = "down";
                myCurrentAnimation = myAnimations.get("RD");
                y += playerSpeed;
                myWarrior.setMyY(y);
            } else if (myGameUI.getMyGameControls().isMyLeftArrow()) {
                int y = myWarrior.getMyY();
                int x = myWarrior.getMyX();
                if(directions.equals("down") || directions.equals("up")){
                    y -= 64;
                    x += 64;
                    myWarrior.setMyX(x);
                    myWarrior.setMyY(y);
                }

                directions = "left";
                myCurrentAnimation = myAnimations.get("RL");
                x -= playerSpeed;
                myWarrior.setMyX(x);
            } else if (myGameUI.getMyGameControls().isMyRightArrow()) {
                int y = myWarrior.getMyY();
                int x = myWarrior.getMyX();
                if(directions.equals("up") || directions.equals("down")){
                    y -= 64;
                    x += 64;
                    myWarrior.setMyX(x);
                    myWarrior.setMyY(y);
                }
                directions = "right";
                myCurrentAnimation = myAnimations.get("RR");
                x += playerSpeed;
                myWarrior.setMyX(x);
            }
            else{
                if(directions.equals("left") || directions.equals("right")) {
                    myCurrentAnimation = myAnimations.get("Idle1");
                }else{
                     myCurrentAnimation = myAnimations.get("Idle2");
                }
            }
        }
        if(isAttacking) {
             switch (directions) {
                case "right" -> myCurrentAnimation = myAnimations.get("AR");
                case "left" -> myCurrentAnimation = myAnimations.get("AL");
                case "up" -> myCurrentAnimation = myAnimations.get("AU");
                case "down" -> myCurrentAnimation = myAnimations.get("AD");
            }
        }
    }


    public void updateAnimation() {
        myAnimationTick++;
        if (myAnimationTick >= myAnimationSpeed) {
            myAnimationTick = 0;
            myAnimationIndex++;
            if (myAnimationIndex >= myCurrentAnimation.length) {
                myAnimationIndex = 0;
                isAttacking = false;
            }
        }
    }

    private void loadImage(int theNumber){
        String path = "";
        switch (theNumber){
            case 1 -> path = myWarriorPath;
            case 2 -> path = myPriestessPath;
            case 3 -> path = myThiefPath;
        }
        try {
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/" + path + "Idle.png")));
            myIdleAnimations = loadIdleAnimationsLeftRight(5, false);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/" + path + "RunRight.png")));
            myWarriorRunRight = loadIdleAnimationsLeftRight(8, false);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/" + path + "RunLeft.png")));
            myWarriorRunLeft = loadIdleAnimationsLeftRight(8, true);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/" + path + "RunDown.png")));
            myWarriorRunDown = loadIdleAnimationsUpDown(8);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/" + path + "RunUp.png")));
            myWarriorRunUp = loadIdleAnimationsUpDown(8);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/" + path + "AttackRight.png")));
            myWarriorAttackRight = loadIdleAnimationsLeftRight(7, false);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/" + path + "AttackLeft.png")));
            myWarriorAttackLeft = loadIdleAnimationsLeftRight(7, true);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/" + path + "AttackDown.png")));
            myWarriorAttackDown = loadIdleAnimationsUpDown(7);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/" + path + "AttackUp.png")));
            myWarriorAttackUp = loadIdleAnimationsUpDown(7);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/" + path + "IdleUpDown.png")));
            myIdleAnimationsUpDown = loadIdleAnimationsUpDown(5);
            myAnimations.put("Idle1", myIdleAnimations);
            myAnimations.put("Idle2", myIdleAnimationsUpDown);
            myAnimations.put("RR", myWarriorRunRight);
            myAnimations.put("RL", myWarriorRunLeft);
            myAnimations.put("RD", myWarriorRunDown);
            myAnimations.put("RU", myWarriorRunUp);
            myAnimations.put("AL", myWarriorAttackLeft);
            myAnimations.put("AR", myWarriorAttackRight);
            myAnimations.put("AD", myWarriorAttackDown);
            myAnimations.put("AU", myWarriorAttackUp);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public BufferedImage[] getMyIdleAnimations() {
        return myIdleAnimations;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public BufferedImage[] getMyWarriorAttackLeft() {
        return myWarriorAttackLeft;
    }

    public int getMyAnimationIndex() {
        return myAnimationIndex;
    }
    public Warrior getMyWarrior() {
        return myWarrior;
    }

    public int getScreenX() {
        return screenX;
    }
    public int getScreenY() {
        return screenY;
    }
}
