package Controller;

import Model.Warrior;
import View.DungeonPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GameUI {

    private RoundRectangle2D myStartRectangle;
    private RoundRectangle2D myQuitRectangle;
    private RoundRectangle2D myLoadRectangle;

    private Rectangle myWarriorCheckBox;
    private Rectangle myThiefCheckBox;
    private Rectangle myPriestessCheckBox;

    private Rectangle myEasyCheckBox;
    private Rectangle myMediumCheckBox;

    private Rectangle mySelectButton;


    private Rectangle myHardCheckBox;


    private BufferedImage background;

    private final Warrior myWarrior;

    private final DungeonPanel myDungeonPanel;
    private final GameControls myGameControls;
    private final String myStartGameTitle = "NEW GAME";
    private final String myLoadGameTitle = "LOAD GAME";
    private final String myQuitGameTitle = "QUIT GAME";


    private BufferedImage img;
    private BufferedImage selection;


    public GameUI(DungeonPanel theDungeonPanel) {
        myDungeonPanel = theDungeonPanel;
        myGameControls = new GameControls(this);
        theDungeonPanel.addKeyListener(myGameControls);
        theDungeonPanel.addMouseListener(myGameControls);
        theDungeonPanel.setFocusable(true);
        myWarrior = Model.Warrior.getInstance();
        loadImages();
    }

    public void drawPlayer(Graphics2D theGraphics) {
        theGraphics.setColor(Color.WHITE);

        theGraphics.fillRect(myWarrior.getMyX(), myWarrior.getMyY(), myDungeonPanel.getMyTileSize(), myDungeonPanel.getMyTileSize());
        //  theGraphics.drawImage(img, myWarrior.getMyX(), myWarrior.getMyY(), myDungeonPanel.getMyTileSize(), myDungeonPanel.getMyTileSize(), null);
    }


    public void loadImages() {
        try {
            img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/big_kobold_new.png")));
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/blog__becoming-a-dm.png")));
            selection = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/CharacterSelection.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawTitleScreen(Graphics2D g2) {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
        String title = "DUNGEON ADVENTURE";
        int x = myDungeonPanel.getMyTileSize();
        int y = myDungeonPanel.getMyTileSize() * 2;

        g2.drawImage(background, 0, 0, myDungeonPanel.getMyWidth(), myDungeonPanel.getMyHeight(), null);

        g2.setColor(Color.BLACK);
        g2.drawString(title, x + 5, y + 5);
        g2.setColor(Color.WHITE);
        g2.drawString(title, x, y);
        createButton(g2);
    }


    public void drawCharacterSelection(Graphics2D theGraphics2D) {
        theGraphics2D.setFont(theGraphics2D.getFont().deriveFont(Font.PLAIN, 20F));
        String title = "Choose Your Hero";
        String title2 = "Choose difficulty";
        String title3 = "DONE";
        theGraphics2D.setColor(Color.WHITE);


        theGraphics2D.drawImage(selection, 0, 0, myDungeonPanel.getMyWidth(), myDungeonPanel.getMyHeight(), null);
        theGraphics2D.setColor(new Color(0, 0, 0, 200));
        theGraphics2D.fillRoundRect(myDungeonPanel.getMyTileSize() * 2, myDungeonPanel.getMyTileSize() * 2, myDungeonPanel.getMyTileSize() * 6, myDungeonPanel.getMyTileSize() * 6, 35, 35);
        theGraphics2D.fillRoundRect(myDungeonPanel.getMyTileSize() * 9, myDungeonPanel.getMyTileSize() * 2, myDungeonPanel.getMyTileSize() * 6, myDungeonPanel.getMyTileSize() * 6, 35, 35);
        theGraphics2D.fillRoundRect((int) (myDungeonPanel.getMyTileSize() * 7.5), (int) (myDungeonPanel.getMyTileSize() * 9.2), myDungeonPanel.getMyTileSize() * 2, myDungeonPanel.getMyTileSize(), 25, 25);


        theGraphics2D.setStroke(new BasicStroke(5));
        theGraphics2D.setColor(new Color(255, 255, 255));
        theGraphics2D.drawRoundRect(myDungeonPanel.getMyTileSize() * 2, myDungeonPanel.getMyTileSize() * 2, myDungeonPanel.getMyTileSize() * 6, myDungeonPanel.getMyTileSize() * 6, 20, 20);
        theGraphics2D.drawRoundRect(myDungeonPanel.getMyTileSize() * 9, myDungeonPanel.getMyTileSize() * 2, myDungeonPanel.getMyTileSize() * 6, myDungeonPanel.getMyTileSize() * 6, 20, 20);
        theGraphics2D.drawString(title, myDungeonPanel.getMyTileSize() * 3, (int) (myDungeonPanel.getMyTileSize() * 2.5));
        theGraphics2D.drawString(title2, myDungeonPanel.getMyTileSize() * 10, (int) (myDungeonPanel.getMyTileSize() * 2.5));
        theGraphics2D.drawString(title3, (int) (myDungeonPanel.getMyTileSize() * 7.9), (int) (myDungeonPanel.getMyTileSize() * 9.8));


        theGraphics2D.setFont(theGraphics2D.getFont().deriveFont(Font.PLAIN, 16F));
        theGraphics2D.drawString("Warrior", (myDungeonPanel.getMyTileSize() * 3), (int) (myDungeonPanel.getMyTileSize() * 3.5));
        theGraphics2D.drawString("Thief", (myDungeonPanel.getMyTileSize() * 3), (int) (myDungeonPanel.getMyTileSize() * 4.5));
        theGraphics2D.drawString("Priestess", (myDungeonPanel.getMyTileSize() * 3), (int) (myDungeonPanel.getMyTileSize() * 5.5));


        theGraphics2D.drawString("Easy", (myDungeonPanel.getMyTileSize() * 10), (int) (myDungeonPanel.getMyTileSize() * 3.5));
        theGraphics2D.drawString("Medium", (myDungeonPanel.getMyTileSize() * 10), (int) (myDungeonPanel.getMyTileSize() * 4.5));
        theGraphics2D.drawString("Hard", (myDungeonPanel.getMyTileSize() * 10), (int) (myDungeonPanel.getMyTileSize() * 5.5));

        myWarriorCheckBox = new Rectangle((int) (myDungeonPanel.getMyTileSize() * 2.3), (int) (myDungeonPanel.getMyTileSize() * 3.2), 20, 20);
        myThiefCheckBox = new Rectangle((int) (myDungeonPanel.getMyTileSize() * 2.3), (int) (myDungeonPanel.getMyTileSize() * 4.2), 20, 20);
        myPriestessCheckBox = new Rectangle((int) (myDungeonPanel.getMyTileSize() * 2.3), (int) (myDungeonPanel.getMyTileSize() * 5.2), 20, 20);

        myEasyCheckBox = new Rectangle((int) (myDungeonPanel.getMyTileSize() * 9.3), (int) (myDungeonPanel.getMyTileSize() * 3.2), 20, 20);
        myMediumCheckBox = new Rectangle((int) (myDungeonPanel.getMyTileSize() * 9.3), (int) (myDungeonPanel.getMyTileSize() * 4.2), 20, 20);
        myHardCheckBox = new Rectangle((int) (myDungeonPanel.getMyTileSize() * 9.3), (int) (myDungeonPanel.getMyTileSize() * 5.2), 20, 20);

        mySelectButton = new Rectangle((int) (myDungeonPanel.getMyTileSize() * 7.5), (int) (myDungeonPanel.getMyTileSize() * 9.2), myDungeonPanel.getMyTileSize() * 2, myDungeonPanel.getMyTileSize());


        theGraphics2D.draw(myWarriorCheckBox);
        theGraphics2D.draw(myThiefCheckBox);
        theGraphics2D.draw(myPriestessCheckBox);


        theGraphics2D.draw(myEasyCheckBox);
        theGraphics2D.draw(myMediumCheckBox);
        theGraphics2D.draw(myHardCheckBox);

        theGraphics2D.draw(mySelectButton);


        theGraphics2D.setStroke(new BasicStroke(3));
        theGraphics2D.setColor(Color.WHITE);

        if (myGameControls.isMyWarriorSelected()) {
            theGraphics2D.drawLine(myWarriorCheckBox.x + 1, myWarriorCheckBox.y + 1, myWarriorCheckBox.x + 17, myWarriorCheckBox.y + 17);
            theGraphics2D.drawLine(myWarriorCheckBox.x + 17, myWarriorCheckBox.y + 1, myWarriorCheckBox.x + 1, myWarriorCheckBox.y + 17);
            theGraphics2D.drawImage(img, myDungeonPanel.getMyTileSize() * 5, (int) (myDungeonPanel.getMyTileSize() * 3), 150, 150, null);
        }

        if (myGameControls.isMyPriestessSelected()) {
            theGraphics2D.drawLine(myPriestessCheckBox.x + 1, myPriestessCheckBox.y + 1, myPriestessCheckBox.x + 17, myPriestessCheckBox.y + 17);
            theGraphics2D.drawLine(myPriestessCheckBox.x + 17, myPriestessCheckBox.y + 1, myPriestessCheckBox.x + 1, myPriestessCheckBox.y + 17);
        }

        if (myGameControls.isMyThiefSelected()) {
            theGraphics2D.drawLine(myThiefCheckBox.x + 1, myThiefCheckBox.y + 1, myThiefCheckBox.x + 17, myThiefCheckBox.y + 17);
            theGraphics2D.drawLine(myThiefCheckBox.x + 17, myThiefCheckBox.y + 1, myThiefCheckBox.x + 1, myThiefCheckBox.y + 17);
        }

        if (myGameControls.isMyEasySelected()) {
            theGraphics2D.drawLine(myEasyCheckBox.x + 1, myEasyCheckBox.y + 1, myEasyCheckBox.x + 17, myEasyCheckBox.y + 17);
            theGraphics2D.drawLine(myEasyCheckBox.x + 17, myEasyCheckBox.y + 1, myEasyCheckBox.x + 1, myEasyCheckBox.y + 17);
        }
        if (myGameControls.isMyMediumSelected()) {
            theGraphics2D.drawLine(myMediumCheckBox.x + 1, myMediumCheckBox.y + 1, myMediumCheckBox.x + 17, myMediumCheckBox.y + 17);
            theGraphics2D.drawLine(myMediumCheckBox.x + 17, myMediumCheckBox.y + 1, myMediumCheckBox.x + 1, myMediumCheckBox.y + 17);
        }
        if (myGameControls.isMyHardSelected()) {
            theGraphics2D.drawLine(myHardCheckBox.x + 1, myHardCheckBox.y + 1, myHardCheckBox.x + 17, myHardCheckBox.y + 17);
            theGraphics2D.drawLine(myHardCheckBox.x + 17, myHardCheckBox.y + 1, myHardCheckBox.x + 1, myHardCheckBox.y + 17);
        }

        if (myGameControls.isMySelection()) {
            myDungeonPanel.setGameState(myDungeonPanel.getPlayState());
        }


        theGraphics2D.setColor(Color.WHITE);
        theGraphics2D.setFont(theGraphics2D.getFont().deriveFont(Font.PLAIN, 60F));


    }


    private void createButton(Graphics2D theGraphics) {

        myStartRectangle = new RoundRectangle2D.Double(myDungeonPanel.getMyTileSize() * 6, myDungeonPanel.getMyTileSize() * 5, myDungeonPanel.getMyTileSize() * 4, myDungeonPanel.getMyTileSize(), 20, 20);
        theGraphics.setColor(Color.WHITE);
        theGraphics.fill(myStartRectangle);
        theGraphics.setFont(theGraphics.getFont().deriveFont(Font.PLAIN, 20F));
        theGraphics.setColor(Color.BLACK);
        theGraphics.drawString(myStartGameTitle, myDungeonPanel.getMyTileSize() * 7, (int) myStartRectangle.getCenterY() + myDungeonPanel.getMyTileSize() / 5);
        //myQuitGameButton.setBounds(myDungeonPanel.getMyTileSize() * 6, myDungeonPanel.getMyTileSize() * 9, 192, myDungeonPanel.getMyTileSize());
        myQuitRectangle = new RoundRectangle2D.Double(myDungeonPanel.getMyTileSize() * 6, myDungeonPanel.getMyTileSize() * 9, myDungeonPanel.getMyTileSize() * 4, myDungeonPanel.getMyTileSize(), 20, 20);
        theGraphics.setColor(Color.WHITE);
        theGraphics.fill(myQuitRectangle);
        theGraphics.setFont(theGraphics.getFont().deriveFont(Font.PLAIN, 20F));
        theGraphics.setColor(Color.BLACK);
        theGraphics.drawString(myQuitGameTitle, myDungeonPanel.getMyTileSize() * 7, (int) myQuitRectangle.getCenterY() + myDungeonPanel.getMyTileSize() / 5);
        // myLoadGameButton.setBounds(myDungeonPanel.getMyTileSize() * 6, myDungeonPanel.getMyTileSize() * 7, 192, myDungeonPanel.getMyTileSize());
        myLoadRectangle = new RoundRectangle2D.Double(myDungeonPanel.getMyTileSize() * 6, myDungeonPanel.getMyTileSize() * 7, myDungeonPanel.getMyTileSize() * 4, myDungeonPanel.getMyTileSize(), 20, 20);
        theGraphics.setColor(Color.WHITE);
        theGraphics.fill(myLoadRectangle);
        theGraphics.setFont(theGraphics.getFont().deriveFont(Font.PLAIN, 20F));
        theGraphics.setColor(Color.BLACK);
        theGraphics.drawString(myLoadGameTitle, myDungeonPanel.getMyTileSize() * 7, (int) myLoadRectangle.getCenterY() + myDungeonPanel.getMyTileSize() / 5);


        if (myGameControls.isStartGameClicked()) {
            myDungeonPanel.setGameState(myDungeonPanel.getSelectionState());
        }

        if (myGameControls.isQuitGameClicked()) {
            System.exit(0);
        }

    }


    public void updatePlayerLocation() {
        // (x = 100, y = 96)
        if (myGameControls.isMyUpArrow()) {
            int y = myWarrior.getMyY();
            y -= 4;
            myWarrior.setMyY(y);
        } else if (myGameControls.isMyDownArrow()) {
            int y = myWarrior.getMyY();
            y += 4;
            myWarrior.setMyY(y);
        } else if (myGameControls.isMyLeftArrow()) {
            int x = myWarrior.getMyX();
            x -= 4;
            myWarrior.setMyX(x);
        } else if (myGameControls.isMyRightArrow()) {
            int x = myWarrior.getMyX();
            x += 4;
            myWarrior.setMyX(x);
        }
    }

    public DungeonPanel getMyDungeonPanel() {
        return myDungeonPanel;
    }


    public RoundRectangle2D getMyStartRectangle() {
        return myStartRectangle;
    }

    public RoundRectangle2D getMyQuitRectangle() {
        return myQuitRectangle;
    }

    public RoundRectangle2D getMyLoadRectangle() {
        return myLoadRectangle;
    }

    public Rectangle getMyWarriorCheckBox() {
        return myWarriorCheckBox;
    }

    public Rectangle getMyThiefCheckBox() {
        return myThiefCheckBox;
    }

    public Rectangle getMyPriestessCheckBox() {
        return myPriestessCheckBox;
    }

    public Rectangle getMyEasyCheckBox() {
        return myEasyCheckBox;
    }

    public Rectangle getMyMediumCheckBox() {
        return myMediumCheckBox;
    }

    public Rectangle getMyHardCheckBox() {
        return myHardCheckBox;
    }

    public Rectangle getMySelectButton() {
        return mySelectButton;
    }

}
