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
