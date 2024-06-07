
package View;

import Controller.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class DungeonPanel extends JPanel implements Runnable {
    //Screen Setting
    private static final int myOriginalTileSize = 16; // 16px   // 704 x
    public static final int myTileSize = myOriginalTileSize * 4; // 64 pixel
    private final int myMaxScreenCol = 16; // 16


    private final int myMaxScreenRow = 12; // 12
    private final int myWidth = myTileSize * myMaxScreenCol; // 1024
    private final int myHeight = myTileSize * myMaxScreenRow; // 768
    private RoundRectangle2D myStartOverRectangle;
    private final static int FPS = 60;
    private int gameState;

    private final int myBeginningState = 0;

    private final int playState = 2;
    private final int selectionState = 1;
    private final int pauseState = 3;

    private final int gameOverState = 4;
    private final GameUI myGameUi;
    private final GameSounds myGameSounds;
    private Lighting myLighting;
    private Thread gameThread;

    public List<SuperItems> myItems;
    public List<Monsters> myMonsters;
    public Map<String, SuperItems> myDefaultItems;

    private final int miniMapSize = 300;
    private final float miniMapScale = 0.1f;
    private BufferedImage miniMapImage;

    private final Map<String, Monsters> myDefaultMonsters;

    private static DungeonPanel MY_INSTANCE;


    private DungeonPanel() {
        gameState = myBeginningState;
        this.setPreferredSize(new Dimension(myWidth, myHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        myDefaultItems = new HashMap<>();
        myGameUi = new GameUI(this);
        myItems = new ArrayList<>();
        myGameSounds = new GameSounds();
        myMonsters = new ArrayList<>();
        myDefaultMonsters = new HashMap<>();
        setLighting();
        initMiniMap();
        startGameThread();
    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double interval = 1000000000.0 / FPS;
        double delta = 0;
        long prevTime = System.nanoTime();
        long currTime;
        while (gameThread != null) {
            //      System.out.println(gameState);
            currTime = System.nanoTime();
            delta += (currTime - prevTime) / interval;

            prevTime = currTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }


    public void update() {
        if (gameState == playState) {
            myGameUi.updatePlayerLocation();
            myLighting.update();
            myGameUi.updateInventory();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        if (gameState == playState) {
            myGameUi.drawPlayer(graphics2D);
            for (SuperItems myPillarItem : myItems) {
                if (myPillarItem != null) {
                    myPillarItem.draw(graphics2D);
                }
            }
            for (Monsters monster : myMonsters) {
                if (monster != null) {
                    monster.draw(graphics2D);
                }
            }
            if(myGameUi.getMyTileManager().isMyIsPlayerWin()){
                drawGameOverScreen(graphics2D, "You win!");
            }
            if(myGameUi.getMyTileManager().isMyIsGameOver()){
                drawGameOverScreen(graphics2D, "Game Over!");
            }
            myLighting.draw(graphics2D);
            updateMiniMap();
            graphics2D.drawImage(miniMapImage, getWidth() - miniMapSize - 10, 10, null);

        } else if (gameState == selectionState) {
            myGameUi.drawCharacterSelection(graphics2D);
        } else if (gameState == myBeginningState) {
            myGameUi.drawTitleScreen(graphics2D);
        }
        graphics2D.dispose();
    }


    public void initMiniMap() {
        miniMapImage = new BufferedImage(miniMapSize, miniMapSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = miniMapImage.createGraphics();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, miniMapSize, miniMapSize);
        g2d.dispose();
    }

    private void updateMiniMap() {
        Graphics2D g2d = miniMapImage.createGraphics();

        // Clear the mini-map
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, miniMapSize, miniMapSize);

        // Draw dungeon elements (e.g., walls, items) on the mini-map
        for (SuperItems item : myItems) {
            int x = (int) (item.getWorldX() * miniMapScale);
            int y = (int) (item.getMyY() * miniMapScale);
            g2d.setColor(Color.BLUE); // Set color for items
            g2d.fillRect(x, y, 4, 4); // Draw item as a small square
        }

        for(Monsters monsters : myMonsters){
            int x = (int) (monsters.getMyX() * miniMapScale);
            int y = (int) (monsters.getMyY() * miniMapScale);
            g2d.setColor(Color.RED);
            g2d.fillRect(x, y, 4, 4);
        }

        // Draw player position on the mini-map
        int playerX = (int) (myGameUi.getMyCharacter().getMyX() * miniMapScale);
        int playerY = (int) (myGameUi.getMyCharacter().getMyY() * miniMapScale);
        g2d.setColor(Color.BLACK); // Set color for the player
        g2d.fillRect(playerX, playerY, 6, 6); // Draw player as a small square

        g2d.dispose();
    }

    public void setObjects() {
        BufferedImage ogreImg = null;
        BufferedImage gremlinImg = null;
        BufferedImage skeletonImg = null;
        BufferedImage visionPotionImg = null;
        BufferedImage healthPotionImg = null;
        BufferedImage exitChestImg = null;
        SuperItems pillarP = new SuperItems(myGameUi);
        SuperItems pillarA = new SuperItems(myGameUi);
        SuperItems pillarE = new SuperItems(myGameUi);
        SuperItems pillarI = new SuperItems(myGameUi);
        SuperItems multipleItems = new SuperItems(myGameUi);
        SuperItems exitChest = new SuperItems(myGameUi);
        pillarP.setMyName("P");
        pillarA.setMyName("A");
        pillarE.setMyName("E");
        pillarI.setMyName("I");
        multipleItems.setMyName("M");
        exitChest.setMyName("o");


        try {
            pillarP.setMyImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Objects/PillarP.png"))));
            pillarA.setMyImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Objects/PillarA.png"))));
            pillarE.setMyImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Objects/PillarE.png"))));
            pillarI.setMyImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Objects/PillarI.png"))));
            visionPotionImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Objects/VisionPotion.png")));
            healthPotionImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Objects/HealthPotion.png")));
            exitChest.setMyImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Objects/ExitChest.png"))));
            multipleItems.setMyImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Objects/MultipleItems.png"))));
            gremlinImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/MonsterImages/Gremlin.png")));
            ogreImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/MonsterImages/Ogre.png")));
            skeletonImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/MonsterImages/Skeleton.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        myDefaultItems.put(pillarE.getMyName(), pillarE);
        myDefaultItems.put(pillarI.getMyName(), pillarI);
        myDefaultItems.put(pillarP.getMyName(), pillarP);
        myDefaultItems.put(pillarA.getMyName(), pillarA);



        if(myGameUi.getMyTileManager().getMyExitCoordinates() != null){
            exitChest.setMyY(myGameUi.getMyTileManager().getMyExitCoordinates()[0] * myTileSize);
            exitChest.setWorldX(myGameUi.getMyTileManager().getMyExitCoordinates()[1] * myTileSize);
            exitChest.solidArea.x = exitChest.getWorldX() + exitChest.getMySolidAreaDefaultX();
            exitChest.solidArea.y = exitChest.getMyY() + exitChest.getMySolidAreaDefaultY();
            exitChest.solidArea.width = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            exitChest.solidArea.height = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            myItems.add(exitChest);
        }

        if (myGameUi.getMyTileManager().getMyMultipleCoordinates() != null) {
            multipleItems.setMyY(myGameUi.getMyTileManager().getMyMultipleCoordinates()[0] * myTileSize);
            multipleItems.setWorldX(myGameUi.getMyTileManager().getMyMultipleCoordinates()[1] * myTileSize);
            multipleItems.solidArea.x = multipleItems.getWorldX() + multipleItems.getMySolidAreaDefaultX();
            multipleItems.solidArea.y = multipleItems.getMyY() + multipleItems.getMySolidAreaDefaultY();
            multipleItems.solidArea.width = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            multipleItems.solidArea.height = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            myItems.add(multipleItems);
        }

        if (myGameUi.getMyTileManager().getMyPillarPCoordinates() != null) {
            pillarP.setMyY(myGameUi.getMyTileManager().getMyPillarPCoordinates()[0] * myTileSize);
            pillarP.setWorldX(myGameUi.getMyTileManager().getMyPillarPCoordinates()[1] * myTileSize);
            pillarP.solidArea.x = pillarP.getWorldX() + pillarP.getMySolidAreaDefaultX();
            pillarP.solidArea.y = pillarP.getMyY() + pillarP.getMySolidAreaDefaultY();
            pillarP.solidArea.width = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            pillarP.solidArea.height = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            myItems.add(pillarP);
        }

        if (myGameUi.getMyTileManager().getMyPillarACoordinates() != null) {
            pillarA.setMyY(myGameUi.getMyTileManager().getMyPillarACoordinates()[0] * myTileSize);
            pillarA.setWorldX(myGameUi.getMyTileManager().getMyPillarACoordinates()[1] * myTileSize);
            pillarA.solidArea.x = pillarA.getWorldX() + pillarA.getMySolidAreaDefaultX();
            pillarA.solidArea.y = pillarA.getMyY() + pillarA.getMySolidAreaDefaultY();
            pillarA.solidArea.width = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            pillarA.solidArea.height = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            myItems.add(pillarA);
        }

        if (myGameUi.getMyTileManager().getMyPillarECoordinates() != null) {
            pillarE.setMyY(myGameUi.getMyTileManager().getMyPillarECoordinates()[0] * myTileSize);
            pillarE.setWorldX(myGameUi.getMyTileManager().getMyPillarECoordinates()[1] * myTileSize);
            pillarE.solidArea.x = pillarE.getWorldX() + pillarE.getMySolidAreaDefaultX();
            pillarE.solidArea.y = pillarE.getMyY() + pillarE.getMySolidAreaDefaultY();
            pillarE.solidArea.width = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            pillarE.solidArea.height = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            myItems.add(pillarE);
        }

        if (myGameUi.getMyTileManager().getMyPillarICoordinates() != null) {
            pillarI.setMyY(myGameUi.getMyTileManager().getMyPillarICoordinates()[0] * myTileSize);
            pillarI.setWorldX(myGameUi.getMyTileManager().getMyPillarICoordinates()[1] * myTileSize);
            pillarI.solidArea.x = pillarI.getWorldX() + pillarI.getMySolidAreaDefaultX();
            pillarI.solidArea.y = pillarI.getMyY() + pillarI.getMySolidAreaDefaultY();
            pillarI.solidArea.width = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            pillarI.solidArea.height = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            myItems.add(pillarI);
        }

        for (int i = 0; i < myGameUi.getMyTileManager().getMyVisionPotionCoordinatesList().size(); i += 2) {
            SuperItems visionPotion = new SuperItems(myGameUi);
            visionPotion.setMyName("V");
            visionPotion.setMyImage(visionPotionImg);
            visionPotion.setMyY(myGameUi.getMyTileManager().getMyVisionPotionCoordinatesList().get(i) * 64);
            visionPotion.setWorldX(myGameUi.getMyTileManager().getMyVisionPotionCoordinatesList().get(i + 1) * 64);
            visionPotion.solidArea.x = visionPotion.getWorldX() + visionPotion.getMySolidAreaDefaultX();
            visionPotion.solidArea.y = visionPotion.getMyY() + visionPotion.getMySolidAreaDefaultY();
            visionPotion.solidArea.width = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            visionPotion.solidArea.height = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            myItems.add(visionPotion);
            myDefaultItems.put("V", visionPotion);
        }
        for (int i = 0; i < myGameUi.getMyTileManager().getMyHealthPotionCoordinatesList().size(); i += 2) {
            SuperItems healthPotion = new SuperItems(myGameUi);
            healthPotion.setMyName("H");
            healthPotion.setMyImage(healthPotionImg);
            healthPotion.setMyY(myGameUi.getMyTileManager().getMyHealthPotionCoordinatesList().get(i) * 64);
            healthPotion.setWorldX(myGameUi.getMyTileManager().getMyHealthPotionCoordinatesList().get(i + 1) * 64);
            healthPotion.solidArea.x = healthPotion.getWorldX() + healthPotion.getMySolidAreaDefaultX();
            healthPotion.solidArea.y = healthPotion.getMyY() + healthPotion.getMySolidAreaDefaultY();
            healthPotion.solidArea.width = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            healthPotion.solidArea.height = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            myItems.add(healthPotion);
            myDefaultItems.put("H", healthPotion);
        }


        for (int i = 0; i < myGameUi.getMyTileManager().getMyOgreCoordinatesList().size(); i += 2) {
            Monsters ogre = new Monsters(myGameUi, "Ogre");
            ogre.setMonsterType("Ogre");
            ogre.setMyMonsterImage(ogreImg);
            ogre.setMyY(myGameUi.getMyTileManager().getMyOgreCoordinatesList().get(i) * 64);
            ogre.setMyX(myGameUi.getMyTileManager().getMyOgreCoordinatesList().get(i + 1) * 64);
            ogre.solidArea.x = ogre.getMyX();
            ogre.solidArea.y = ogre.getMyY();
            ogre.solidArea.width = myGameUi.getMyDungeonPanel().getMyTileSize() * 3;
            ogre.solidArea.height = myGameUi.getMyDungeonPanel().getMyTileSize() * 3;
            myMonsters.add(ogre);
            // myItems.add(ogre);
            myDefaultMonsters.put("Ogre", ogre);
        }

        for (int i = 0; i < myGameUi.getMyTileManager().getMyGremlinCoordinatesList().size(); i += 2) {
            Monsters gremlin = new Monsters(myGameUi, "Gremlin");
            gremlin.setMyMonsterImage(gremlinImg);
            gremlin.setMonsterType("Gremlin");
            gremlin.setMyY(myGameUi.getMyTileManager().getMyGremlinCoordinatesList().get(i) * 64);
            gremlin.setMyX(myGameUi.getMyTileManager().getMyGremlinCoordinatesList().get(i + 1) * 64);
            gremlin.solidArea.x = gremlin.getMyX();
            gremlin.solidArea.y = gremlin.getMyY();
            gremlin.solidArea.width = myGameUi.getMyDungeonPanel().getMyTileSize() * 3;
            gremlin.solidArea.height = myGameUi.getMyDungeonPanel().getMyTileSize() * 3;
            //myItems.add(gremlin);
            myDefaultMonsters.put("Gremlin", gremlin);
            myMonsters.add(gremlin);
        }


        for (int i = 0; i < myGameUi.getMyTileManager().getMySkeletonCoordinatesList().size(); i += 2) {
            Monsters skeleton = new Monsters(myGameUi, "Skeleton");
            skeleton.setMonsterType("Skeleton");
            skeleton.setMyMonsterImage(skeletonImg);
            skeleton.setMyY(myGameUi.getMyTileManager().getMySkeletonCoordinatesList().get(i) * 64);
            skeleton.setMyX(myGameUi.getMyTileManager().getMySkeletonCoordinatesList().get(i + 1) * 64);
            skeleton.solidArea.x = skeleton.getMyX();
            skeleton.solidArea.y = skeleton.getMyY();
            skeleton.solidArea.width = myGameUi.getMyDungeonPanel().getMyTileSize() * 3;
            skeleton.solidArea.height = myGameUi.getMyDungeonPanel().getMyTileSize() * 3;
            //myItems.add(skeleton);
            myDefaultMonsters.put("Skeleton", skeleton);
            myMonsters.add(skeleton);
        }


    }

    public void drawGameOverScreen(Graphics2D theGraphics, String theTitle) {

        String text = theTitle;
        theGraphics.setFont(theGraphics.getFont().deriveFont(Font.BOLD, 35F));
        theGraphics.setColor(Color.WHITE);
        int x = getXforCenteredText();
        int y = myHeight / 2;
        theGraphics.setColor(new Color(0, 0, 0, 30));
        theGraphics.fillRoundRect(0, 0, myWidth, myHeight, 35, 35);

        theGraphics.setColor(new Color(255, 255, 255));
        theGraphics.setStroke(new BasicStroke(5));
        theGraphics.drawRoundRect(5,  5, myWidth - 10, myHeight - 10, 25, 25);
        theGraphics.drawString(text, x, y);
        theGraphics.setFont(theGraphics.getFont().deriveFont(Font.BOLD, 20F));


        myStartOverRectangle = new RoundRectangle2D.Double(x + 10, getMyTileSize() + y, getMyTileSize() * 3, getMyTileSize(), 20, 20);
        theGraphics.fill(myStartOverRectangle);
        theGraphics.setColor(Color.BLACK);
        theGraphics.drawString("Start Over", (int) (myStartOverRectangle.getX() + 50), (int) (myStartOverRectangle.getY() +  35));

    }

    public int getXforCenteredText() {
        return myHeight / 2;
    }




    public int getMyBeginningState() {
        return myBeginningState;
    }

    public int getSelectionState() {
        return selectionState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public int getPauseState() {
        return pauseState;
    }

    public int getGameState() {
        return gameState;
    }

    public int getPlayState() {
        return playState;
    }

    public int getMyWidth() {
        return myWidth;
    }

    public int getMyHeight() {
        return myHeight;
    }

    public int getMyTileSize() {
        return myTileSize;
    }
    public int getMyMaxScreenCol() {
        return myMaxScreenCol;
    }

    public int getMyMaxScreenRow() {
        return myMaxScreenRow;
    }


    public static DungeonPanel getMyInstance() {
        if (MY_INSTANCE == null){
            MY_INSTANCE = new DungeonPanel();
        }
        return MY_INSTANCE;
    }

    public GameSounds getMyGameSounds() {
        return myGameSounds;
    }

    public void setLighting() {
        myLighting = new Lighting(myGameUi);
    }

    public Lighting getMyLighting() {
        return myLighting;
    }

    public Map<String, Monsters> getMyDefaultMonsters() {
        return myDefaultMonsters;
    }

    public int getGameOverState() {
        return gameOverState;
    }

    public RoundRectangle2D getMyStartOverRectangle() {
        return myStartOverRectangle;
    }
}