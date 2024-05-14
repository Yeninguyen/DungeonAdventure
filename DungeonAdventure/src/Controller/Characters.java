package Controller;

import Model.Warrior;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class Characters {
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

    private boolean isAttacking = false;
    private boolean isMoving = false;

    private int playerSpeed = 4;




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
        loadImage();
        myCurrentAnimation = myIdleAnimations;
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
        }
    }

    public void updatePlayerLocation() {
        if(isMoving) {
            if (myGameUI.getMyGameControls().isMyUpArrow()) {
                directions = "up";
                int y = myWarrior.getMyY();
                y -= playerSpeed;
                myCurrentAnimation = myAnimations.get("RU");
                myWarrior.setMyY(y);
            } else if (myGameUI.getMyGameControls().isMyDownArrow()) {
                directions = "down";
                int y = myWarrior.getMyY();
                myCurrentAnimation = myAnimations.get("RD");
                y += playerSpeed;
                myWarrior.setMyY(y);
            } else if (myGameUI.getMyGameControls().isMyLeftArrow()) {
                directions = "left";
                int x = myWarrior.getMyX();
                myCurrentAnimation = myAnimations.get("RL");
                x -= playerSpeed;
                myWarrior.setMyX(x);
            } else if (myGameUI.getMyGameControls().isMyRightArrow()) {
                directions = "right";
                int x = myWarrior.getMyX();
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

    private void loadImage(){
        try {
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorIdle.png")));
            myIdleAnimations = loadIdleAnimationsLeftRight(5, false);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorRunRight.png")));
            myWarriorRunRight = loadIdleAnimationsLeftRight(8, false);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorRunLeft.png")));
            myWarriorRunLeft = loadIdleAnimationsLeftRight(8, true);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorRunDown.png")));
            myWarriorRunDown = loadIdleAnimationsUpDown(8);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorRunUp.png")));
            myWarriorRunUp = loadIdleAnimationsUpDown(8);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorAttackRight.png")));
            myWarriorAttackRight = loadIdleAnimationsLeftRight(7, false);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorAttackLeft.png")));
            myWarriorAttackLeft = loadIdleAnimationsLeftRight(7, true);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorAttackDown.png")));
            myWarriorAttackDown = loadIdleAnimationsUpDown(7);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorAttackUp.png")));
            myWarriorAttackUp = loadIdleAnimationsUpDown(7);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorIdleUpDown.png")));
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
}
