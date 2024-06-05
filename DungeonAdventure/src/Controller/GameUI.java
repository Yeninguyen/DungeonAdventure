package Controller;

import Model.Dungeon;
import View.DungeonPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class GameUI {
    int size;
    private Map<String, Rectangle> itemRectangles;

    private Rectangle myCloseWindowRectangle;
    private RoundRectangle2D myInventoryRectangle;

    private RoundRectangle2D myStartRectangle;
    private RoundRectangle2D myQuitRectangle;
    private RoundRectangle2D myLoadRectangle;

    private Rectangle myWarriorCheckBox;
    private Rectangle myThiefCheckBox;
    private Rectangle myPriestessCheckBox;

    private Rectangle myEasyCheckBox;
    private Rectangle myMediumCheckBox;

    private Rectangle mySelectButton;
    private Rectangle myUserNameBox;


    private static GameUI myInstance;
    private Rectangle myHardCheckBox;




    private BufferedImage background;



    private final GameControls myGameControls;
    private final String myStartGameTitle = "NEW GAME";
    private final String myLoadGameTitle = "LOAD GAME";
    private final String myQuitGameTitle = "QUIT GAME";

    private String username = "";


    private BufferedImage selection;
    private BufferedImage gameOver;


    private final DungeonPanel myDungeonPanel;
    private  final Characters myCharacter;
    private final TileManager myTileManager = new TileManager(this);
    private Dungeon myDungeon;





    public GameUI(DungeonPanel theDungeonPanel) {
        myDungeonPanel = theDungeonPanel;
        itemRectangles = new HashMap<>();
        myGameControls = new GameControls(this);
        theDungeonPanel.addKeyListener(myGameControls);
        theDungeonPanel.addMouseListener(myGameControls);
        theDungeonPanel.setFocusable(true);
        myCharacter = new Characters(this);
        loadImages();
    }




    public void loadImages() {
        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Backgrounds/StartGameBackground.png")));
            selection = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Backgrounds/CharacterSelection.png")));
            gameOver = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Backgrounds/GameOver.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void drawPlayer(Graphics2D theGraphics) {
        myTileManager.drawTiles(theGraphics);
        myCharacter.drawPlayer(theGraphics);
        theGraphics.setColor(Color.WHITE);


        theGraphics.setFont(theGraphics.getFont().deriveFont(Font.BOLD, 15F));
        theGraphics.drawString(getMyGameControls().getUsername(), myCharacter.getScreenX() - 2, myCharacter.getScreenY() - 20);
        //myTileManager.updateAndRenderMonsters(theGraphics);
    }

    public void drawTitleScreen(Graphics2D theGraphics) {

        theGraphics.setFont(theGraphics.getFont().deriveFont(Font.PLAIN, 60F));
        String title = "DUNGEON ADVENTURE";
        int x = (int) (myDungeonPanel.getMyTileSize() * 2.5);
        int y = myDungeonPanel.getMyTileSize() * 2;

        theGraphics.drawImage(background, 0, 0, myDungeonPanel.getMyWidth(), myDungeonPanel.getMyHeight(), null);

        theGraphics.setColor(Color.BLACK);
        theGraphics.drawString(title, x + 5, y + 5);
        theGraphics.setColor(Color.WHITE);
        theGraphics.drawString(title, x, y);
        createButton(theGraphics);
    }


    public void drawCharacterSelection(Graphics2D theGraphics2D) {
        theGraphics2D.setFont(theGraphics2D.getFont().deriveFont(Font.PLAIN, 20F));
        String chooseHeroTitle = "Choose Your Hero";
        String ChooseDifficultyTitle = "Choose difficulty";
        String selectDoneTitle = "DONE";
        String userNameTitle = "Enter name: ";
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
        theGraphics2D.drawString(userNameTitle, (int) (myDungeonPanel.getMyTileSize() * 9.2), (int) (myDungeonPanel.getMyTileSize() * 6.2));

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

        myUserNameBox = new Rectangle((int) (myDungeonPanel.getMyTileSize() * 11.5), (int) (myDungeonPanel.getMyTileSize() * 5.8), myDungeonPanel.getMyTileSize() * 2, myDungeonPanel.getMyTileSize() / 2);


        theGraphics2D.draw(myWarriorCheckBox);
        theGraphics2D.draw(myThiefCheckBox);
        theGraphics2D.draw(myPriestessCheckBox);

        theGraphics2D.draw(myUserNameBox);

        theGraphics2D.draw(myEasyCheckBox);
        theGraphics2D.draw(myMediumCheckBox);
        theGraphics2D.draw(myHardCheckBox);

        theGraphics2D.draw(mySelectButton);


        theGraphics2D.setStroke(new BasicStroke(3));
        theGraphics2D.setColor(Color.WHITE);

        updateSelectionScreen(theGraphics2D);

    }

    public void updateCheckboxSelection(Graphics2D g2d, Rectangle checkbox) {
        g2d.setColor(Color.WHITE);
        g2d.drawLine(checkbox.x + 1, checkbox.y + 1, checkbox.x + 17, checkbox.y + 17);
        g2d.drawLine(checkbox.x + 17, checkbox.y + 1, checkbox.x + 1, checkbox.y + 17);
    }


    private void updateSelectionScreen(Graphics2D theGraphics2D) {
        int width = myDungeonPanel.getMyTileSize() * 3;
        int height = myDungeonPanel.getMyTileSize() * 3;

        if (myGameControls.isMySelection()) {
                if (myGameControls.isMyEasySelected()) {
                    myDungeon = Model.Dungeon.getInstance();
                    size = 3;
                    myDungeon.generateMaze(3);
                    myTileManager.generateDungeon();
                }
                if (myGameControls.isMyMediumSelected()) {
                    myDungeon = Model.Dungeon.getInstance();
                    size = 6;
                    myDungeon.generateMaze(6);
                    myTileManager.generateDungeon();
                }
                if (myGameControls.isMyHardSelected()) {
                    myDungeon = Model.Dungeon.getInstance();
                    size = 8;
                    myDungeon.generateMaze(8);
                    myTileManager.generateDungeon();
                }
                myDungeonPanel.setGameState(myDungeonPanel.getPlayState());
        }

        if (myGameControls.isMyWarriorSelected()) {
            updateCheckboxSelection(theGraphics2D, myWarriorCheckBox);
            theGraphics2D.drawImage((myCharacter.getMyWarriorCurrentImage()), myDungeonPanel.getMyTileSize() * 4, (myDungeonPanel.getMyTileSize() * 3), width, height, null);
        }

        if (myGameControls.isMyPriestessSelected()) {
            updateCheckboxSelection(theGraphics2D, myPriestessCheckBox);
            theGraphics2D.drawImage((myCharacter.getMyPriestessCurrentImage()), myDungeonPanel.getMyTileSize() * 4, (myDungeonPanel.getMyTileSize() * 3), width, height, null);

        }

        if (myGameControls.isMyThiefSelected()) {
            updateCheckboxSelection(theGraphics2D, myThiefCheckBox);
            theGraphics2D.drawImage((myCharacter.getMyThiefCurrentImage()), myDungeonPanel.getMyTileSize() * 4, (myDungeonPanel.getMyTileSize() * 3), width, height, null);
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

        if (myGameControls.isMyUsernameBoxSelected()) {
            username = myGameControls.getUsername();
        }
        theGraphics2D.drawString(username, (myDungeonPanel.getMyTileSize() * 12), (int) (myDungeonPanel.getMyTileSize() * 6.2));
    }


    public void drawInventory(Graphics2D theGraphics) {

        int x = 64 * 9;
        int y = 64;
        int width = 64 * 6;
        int height = 64 * 5;



        drawFrame(x, y, width, height, theGraphics);
        myCloseWindowRectangle = new Rectangle(x + 20, y + 20, 20, 20);
        theGraphics.setColor(new Color(255, 255, 255)); // Set the color for the closeWindowRectangle outline
        theGraphics.setStroke(new BasicStroke(5));

        updateCheckboxSelection(theGraphics, myCloseWindowRectangle);
        // Set the color to a fully transparent color for the closeWindowRectangle fill
        theGraphics.setColor(new Color(0, 0, 0, 0)); // Transparent black color
        theGraphics.fill(myCloseWindowRectangle);


        final int slotXStart = x + 40;
        final int slotYStart = y + 90;
        final int slotSize = 64; // Assuming each slot has a
        // size of 64 pixels

        int slotX = slotXStart;
        int slotY = slotYStart;

        int slotIndex = 0;
        int numSlotsPerRow = 3; // Assuming 3 slots per row


        theGraphics.setFont(theGraphics.getFont().deriveFont(Font.BOLD, 30F));
        theGraphics.drawString("Inventory", slotXStart + 70, slotYStart - 50);

        for (SuperItems item : myDungeonPanel.myDefaultItems.values()) {
            int cursorX = slotX;
            int cursorY = slotY;

            theGraphics.setStroke(new BasicStroke(3));
            theGraphics.setColor(Color.WHITE);
            theGraphics.drawRoundRect(cursorX, cursorY, slotSize + 30, slotSize, 10, 10);

            theGraphics.drawImage(item.getImage(), cursorX, cursorY, slotSize, slotSize, null);

            if (item.isCollision()) {
                int val = myTileManager.getMyItemCollisionFrequency().get(item.getName());
                theGraphics.setFont(theGraphics.getFont().deriveFont(Font.BOLD, 30F));
                theGraphics.drawString(String.valueOf(val), cursorX + 50, cursorY + 50);
                theGraphics.setFont(theGraphics.getFont().deriveFont(Font.BOLD, 20F));

                //theGraphics.drawString(str, cursorX + 70, cursorY + 50);
            } else {
                theGraphics.setFont(theGraphics.getFont().deriveFont(Font.BOLD, 30F));
                theGraphics.drawString("0", cursorX + 50, cursorY + 50);
                theGraphics.setFont(theGraphics.getFont().deriveFont(Font.BOLD, 20F));
                // theGraphics.drawString(str, cursorX + 70, cursorY + 50);
            }

            if(item.getName().equals("V") || item.getName().equals("H")){
                itemRectangles.put(item.getName(), new Rectangle(cursorX, cursorY, slotSize + 30, slotSize));
            }

            slotIndex++;
            if (slotIndex % numSlotsPerRow == 0) {
                // Move to the next row
                slotX = slotXStart;
                slotY += slotSize + 50;
            } else {
                // Move to the next column
                slotX += slotSize + 50; // Add extra spacing between slots if needed
            }
        }
    }


    public void updateInventory(){
        if(myTileManager.getMyItemCollisionFrequency() != null) {
            if(myGameControls.isMyVisionPotionSelected() && myTileManager.getMyItemCollisionFrequency().containsKey("V")) {
                int val = myTileManager.getMyItemCollisionFrequency().get("V");
                if(val > 0) {
                    val--;
                    myDungeonPanel.getMyLighting().setMyVisionPotionUsed(true);
                    myDungeonPanel.getMyLighting().setMyVisionTimer(System.currentTimeMillis()); // Set the timer
                    myTileManager.getMyItemCollisionFrequency().put("V", val);
                    myGameControls.setMyVisionPotionSelected(false);
                }
                // ... (remove the code that sets isMyVisionPotionUsed to false when val == 0)
            }
            if (myGameControls.isMyHealthPotionSelected() && myTileManager.getMyItemCollisionFrequency().containsKey("H")) {
                int val = myTileManager.getMyItemCollisionFrequency().get("H");
                if(val > 0) {
                    val --;
                    myTileManager.getMyItemCollisionFrequency().put("H", val);
                    myGameControls.setMyHealthPotionSelected(false);
                }

            }
        }
    }
    public void drawInventoryRectangle(Graphics2D theGraphics){
        int x = 64 * 11;

        myInventoryRectangle = new RoundRectangle2D.Double(x, 30, myDungeonPanel.getMyTileSize() * 2, 30, 10, 10);
        //drawRoundedButton(theGraphics, myInventoryRectangle, "Inventory");
        theGraphics.setColor(Color.WHITE);
        theGraphics.fill(myInventoryRectangle);
        theGraphics.setColor(Color.BLACK);
        theGraphics.setFont(theGraphics.getFont().deriveFont(Font.PLAIN, 15F));
        theGraphics.drawString("Inventory", (int) (myInventoryRectangle.getX() + myInventoryRectangle.getWidth() / 5), (int) ((int) myInventoryRectangle.getCenterY() + myDungeonPanel.getMyTileSize() / 7));
    }

    public void drawFrame(int theX, int theY, int theWidth, int theHeight, Graphics2D theGraphics) {

        theGraphics.setColor(new Color(0, 0, 0, 255));
        theGraphics.fillRoundRect(theX, theY, theWidth, theHeight, 35, 35);
        theGraphics.drawRoundRect(theX + 5, theY + 5, theWidth - 10, theHeight - 10, 25, 25);



    }


    private void drawRoundedButton(Graphics2D g2d, RoundRectangle2D button, String buttonText) {
        g2d.setColor(Color.WHITE);
        g2d.fill(button);
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 20F));
        g2d.setColor(Color.BLACK);
        g2d.drawString(buttonText, (int) (button.getX() + button.getWidth() / 4), (int) button.getCenterY() + myDungeonPanel.getMyTileSize() / 5);
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


    public TileManager getMyTileManager() {
        return myTileManager;
    }

    public Rectangle getMyUserNameBox() {
        return myUserNameBox;
    }

    public Dungeon getMyDungeon() {
        return myDungeon;
    }

    public RoundRectangle2D getMyInventoryRectangle() {
        return myInventoryRectangle;
    }

    public Map<String, Rectangle> getItemRectangles() {
        return itemRectangles;
    }


    public BufferedImage getGameOver() {
        return gameOver;
    }

    public Rectangle getMyCloseWindowRectangle() {
        return myCloseWindowRectangle;
    }

}