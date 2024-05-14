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




    private final GameUI myGameUI;

    private final Warrior myWarrior;

    private int myAnimationTick;
    private int myAnimationIndex;
    private int myAnimationSpeed = 5;
    private BufferedImage[] myCurrentAnimation;

    private final Map<String, BufferedImage[]> myAnimations;
    public Characters(GameUI theGameUI) {
        myGameUI = theGameUI;
        myWarrior = Model.Warrior.getInstance();
        myAnimations = new HashMap<>();
        loadImage();
        myCurrentAnimation = myIdleAnimations;
    }



    private BufferedImage[] loadIdleAnimations(int theCols, boolean theFlag) {
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

    private BufferedImage[] loadIdleAnimations(int theCols) {
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
            theGraphics.drawImage(myCurrentAnimation[myAnimationIndex], myWarrior.getMyX(), myWarrior.getMyY(), 112, 112, null);
        }
    }

    public void updatePlayerLocation() {
        if(isMoving) {
            if (myGameUI.getMyGameControls().isMyUpArrow()) {
                int y = myWarrior.getMyY();
                y -= 4;
                myCurrentAnimation = myAnimations.get("RU");
                myWarrior.setMyY(y);
            } else if (myGameUI.getMyGameControls().isMyDownArrow()) {
                int y = myWarrior.getMyY();
                myCurrentAnimation = myAnimations.get("RD");
                y += 4;
                myWarrior.setMyY(y);
            } else if (myGameUI.getMyGameControls().isMyLeftArrow()) {
                int x = myWarrior.getMyX();
                myCurrentAnimation = myAnimations.get("RL");
                x -= 4;
                myWarrior.setMyX(x);
            } else if (myGameUI.getMyGameControls().isMyRightArrow()) {
                int x = myWarrior.getMyX();
                myCurrentAnimation = myAnimations.get("RR");
                x += 4;
                myWarrior.setMyX(x);
            }
            else{
                myCurrentAnimation = myAnimations.get("Idle");
            }
        }
        if(isAttacking){
                myCurrentAnimation = myAnimations.get("AR");
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
            myIdleAnimations = loadIdleAnimations(5, false);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorRunRight.png")));
            myWarriorRunRight = loadIdleAnimations(8, false);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorRunLeft.png")));
            myWarriorRunLeft = loadIdleAnimations(8, true);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorRunDown.png")));
            myWarriorRunDown = loadIdleAnimations(8);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorRunUp.png")));
            myWarriorRunUp = loadIdleAnimations(8);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorAttackRight.png")));
            myWarriorAttackRight = loadIdleAnimations(7, false);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorAttackLeft.png")));
            myWarriorAttackLeft = loadIdleAnimations(7, false);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorAttackDown.png")));
            myWarriorAttackDown = loadIdleAnimations(7, true);
            myImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/WarriorImages/WarriorAttackUp.png")));
            myWarriorAttackUp = loadIdleAnimations(7, true);
            myAnimations.put("Idle", myIdleAnimations);
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
}
