package Controller;

import View.DungeonPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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


    private final DungeonPanel myDungeonPanel;
    private final GameControls myGameControls;
    private final String myStartGameTitle = "NEW GAME";
    private final String myLoadGameTitle = "LOAD GAME";
    private final String myQuitGameTitle = "QUIT GAME";



    private BufferedImage myPlayerImage;
    private BufferedImage selection;

    private BufferedImage wall;
    private BufferedImage floor;

    private Map<BufferedImage, Boolean> myTiles;

    private Characters myCharacter;



    public GameUI(DungeonPanel theDungeonPanel) {
        myDungeonPanel = theDungeonPanel;
        myGameControls = new GameControls(this);
        theDungeonPanel.addKeyListener(myGameControls);
        theDungeonPanel.addMouseListener(myGameControls);
        theDungeonPanel.setFocusable(true);
        myCharacter = new Characters(this);
        loadImages();
    }



    public void drawPlayer(Graphics2D theGraphics) {
         map(theGraphics);
         myCharacter.drawAnimations(theGraphics);
    }


    public void loadImages() {
        try {
            myPlayerImage = myCharacter.getMyIdleAnimations()[0];
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Backgrounds/StartGameBackground.png")));
            selection = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Backgrounds/CharacterSelection.png")));
            wall = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Tiles/wall.png")));
            floor = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Tiles/earth.png")));

            myTiles = new HashMap<>();
            myTiles.put(wall, false);
            myTiles.put(floor, true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawTitleScreen(Graphics2D g2) {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
        String title = "DUNGEON ADVENTURE";
        int x = (int) (myDungeonPanel.getMyTileSize() * 2.5);
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
        String chooseHeroTitle = "Choose Your Hero";
        String ChooseDifficultyTitle = "Choose difficulty";
        String selectDoneTitle = "DONE";
        theGraphics2D.setColor(Color.WHITE);

        Rectangle chooseHeroRectangle = new Rectangle(myDungeonPanel.getMyTileSize() * 2, myDungeonPanel.getMyTileSize() * 6);
        theGraphics2D.drawImage(selection, 0, 0, myDungeonPanel.getMyWidth(), myDungeonPanel.getMyHeight(), null);
        theGraphics2D.setColor(new Color(0, 0, 0, 200));
        theGraphics2D.fillRoundRect(myDungeonPanel.getMyTileSize() * 2, myDungeonPanel.getMyTileSize() * 2, myDungeonPanel.getMyTileSize() * 6, myDungeonPanel.getMyTileSize() * 6, 35, 35);
        theGraphics2D.fillRoundRect(myDungeonPanel.getMyTileSize() * 9, myDungeonPanel.getMyTileSize() * 2, myDungeonPanel.getMyTileSize() * 6, myDungeonPanel.getMyTileSize() * 6, 35, 35);
        theGraphics2D.fillRoundRect((int) (myDungeonPanel.getMyTileSize() * 7.5), (int) (myDungeonPanel.getMyTileSize() * 9.2), myDungeonPanel.getMyTileSize() * 2, myDungeonPanel.getMyTileSize(), 25, 25);


        theGraphics2D.setStroke(new BasicStroke(5));
        theGraphics2D.setColor(new Color(255, 255, 255));
        theGraphics2D.drawRoundRect(myDungeonPanel.getMyTileSize() * 2, myDungeonPanel.getMyTileSize() * 2, myDungeonPanel.getMyTileSize() * 6, myDungeonPanel.getMyTileSize() * 6, 20, 20);
        theGraphics2D.drawRoundRect(myDungeonPanel.getMyTileSize() * 9, myDungeonPanel.getMyTileSize() * 2, myDungeonPanel.getMyTileSize() * 6, myDungeonPanel.getMyTileSize() * 6, 20, 20);
        theGraphics2D.drawString(chooseHeroTitle, (int) (chooseHeroRectangle.getWidth() * 2) - myDungeonPanel.getMyTileSize() / 2, (int) (myDungeonPanel.getMyTileSize() * 2.5));
        theGraphics2D.drawString(ChooseDifficultyTitle, (int) (myDungeonPanel.getMyTileSize() * 10.5), (int) (myDungeonPanel.getMyTileSize() * 2.5));
        theGraphics2D.drawString(selectDoneTitle, (int) (myDungeonPanel.getMyTileSize() * 8.1), (int) (myDungeonPanel.getMyTileSize() * 9.8));


        theGraphics2D.setFont(theGraphics2D.getFont().deriveFont(Font.PLAIN, 16F));
        theGraphics2D.drawString("Warrior", (myDungeonPanel.getMyTileSize() * 3), (int) (myDungeonPanel.getMyTileSize() * 3.4));
        theGraphics2D.drawString("Thief", (myDungeonPanel.getMyTileSize() * 3), (int) (myDungeonPanel.getMyTileSize() * 4.4));
        theGraphics2D.drawString("Priestess", (myDungeonPanel.getMyTileSize() * 3), (int) (myDungeonPanel.getMyTileSize() * 5.4));


        theGraphics2D.drawString("Easy", (myDungeonPanel.getMyTileSize() * 10), (int) (myDungeonPanel.getMyTileSize() * 3.4));
        theGraphics2D.drawString("Medium", (myDungeonPanel.getMyTileSize() * 10), (int) (myDungeonPanel.getMyTileSize() * 4.4));
        theGraphics2D.drawString("Hard", (myDungeonPanel.getMyTileSize() * 10), (int) (myDungeonPanel.getMyTileSize() * 5.4));

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

        updateSelectionScreen(theGraphics2D);

    }

    private void updateCheckboxSelection(Graphics2D g2d, Rectangle checkbox) {
        g2d.drawLine(checkbox.x + 1, checkbox.y + 1, checkbox.x + 17, checkbox.y + 17);
        g2d.drawLine(checkbox.x + 17, checkbox.y + 1, checkbox.x + 1, checkbox.y + 17);
    }




    private void updateSelectionScreen(Graphics2D theGraphics2D) {
        int width = myDungeonPanel.getMyTileSize() * 4;
        int height = myDungeonPanel.getMyTileSize() * 4;
        if (myGameControls.isMyWarriorSelected()) {
            updateCheckboxSelection(theGraphics2D, myWarriorCheckBox);
            theGraphics2D.drawImage(myPlayerImage, myDungeonPanel.getMyTileSize() * 4, (myDungeonPanel.getMyTileSize() * 2), width, height, null);
        }

        if (myGameControls.isMyPriestessSelected()) {
            updateCheckboxSelection(theGraphics2D, myPriestessCheckBox);
            theGraphics2D.drawImage(myPlayerImage, myDungeonPanel.getMyTileSize() * 4, (myDungeonPanel.getMyTileSize() * 2), width, height, null);
        }

        if (myGameControls.isMyThiefSelected()) {
            updateCheckboxSelection(theGraphics2D, myThiefCheckBox);
            theGraphics2D.drawImage(myPlayerImage, myDungeonPanel.getMyTileSize() * 4, (myDungeonPanel.getMyTileSize() * 2), width, height, null);
        }

        if (myGameControls.isMyEasySelected()) {
            updateCheckboxSelection(theGraphics2D, myEasyCheckBox);
        }
        if (myGameControls.isMyMediumSelected()) {
            updateCheckboxSelection(theGraphics2D, myMediumCheckBox);
        }
        if (myGameControls.isMyHardSelected()) {
            updateCheckboxSelection(theGraphics2D, myHardCheckBox);
        }

        if (myGameControls.isMySelection()) {
            myDungeonPanel.setGameState(myDungeonPanel.getPlayState());
        }
    }

    public void map(Graphics2D theGraphics){
        for (int i = 0; i < myDungeonPanel.getMyMaxScreenCol(); i++) {
            for (int j = 0; j < myDungeonPanel.getMyMaxScreenRow(); j++) {
                theGraphics.drawImage(floor, myDungeonPanel.getMyTileSize() * i, myDungeonPanel.getMyTileSize() * j,myDungeonPanel.getMyTileSize(), myDungeonPanel.getMyTileSize(),  null);
            }
        }
    }



    private void drawRoundedButton(Graphics2D g2d, RoundRectangle2D button, String buttonText) {
        g2d.setColor(Color.WHITE);
        g2d.fill(button);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 20F));
        g2d.setColor(Color.BLACK);
        g2d.drawString(buttonText, (int)(button.getX() + button.getWidth() / 4), (int)button.getCenterY() + myDungeonPanel.getMyTileSize() / 5);
    }

    private void createButton(Graphics2D theGraphics) {
        myStartRectangle = new RoundRectangle2D.Double(myDungeonPanel.getMyTileSize() * 6, myDungeonPanel.getMyTileSize() * 5, myDungeonPanel.getMyTileSize() * 4, myDungeonPanel.getMyTileSize(), 20, 20);
        drawRoundedButton(theGraphics, myStartRectangle, myStartGameTitle);

        myQuitRectangle = new RoundRectangle2D.Double(myDungeonPanel.getMyTileSize() * 6, myDungeonPanel.getMyTileSize() * 9, myDungeonPanel.getMyTileSize() * 4, myDungeonPanel.getMyTileSize(), 20, 20);
        drawRoundedButton(theGraphics, myQuitRectangle, myQuitGameTitle);

        myLoadRectangle = new RoundRectangle2D.Double(myDungeonPanel.getMyTileSize() * 6, myDungeonPanel.getMyTileSize() * 7, myDungeonPanel.getMyTileSize() * 4, myDungeonPanel.getMyTileSize(), 20, 20);
        drawRoundedButton(theGraphics, myLoadRectangle, myLoadGameTitle);

        if (myGameControls.isStartGameClicked()) {
            myDungeonPanel.setGameState(myDungeonPanel.getSelectionState());
        }

        if (myGameControls.isQuitGameClicked()) {
            System.exit(0);
        }
    }


    public void updatePlayerLocation() {
        myCharacter.updatePlayerLocation();
        myCharacter.updateAnimation();
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

    public GameControls getMyGameControls() {
        return myGameControls;
    }

    public Characters getMyCharacter() {
        return myCharacter;
    }
}