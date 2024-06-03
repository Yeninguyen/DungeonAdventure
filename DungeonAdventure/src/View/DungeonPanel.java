
package View;

import Controller.GameSounds;
import Controller.GameUI;
import Controller.SuperItems;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.http.HttpHeaders;
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




    private final static int FPS = 60;

    // Camera

    // GAME STATE
    private int gameState;

    private final int myBeginningState = 0;

    private final int playState = 2;
    private final int selectionState = 1;
    private final int pauseState = 3;

    private final GameUI myGameUi;

    private final GameSounds myGameSounds;


    private Thread gameThread;

    public List<SuperItems> myItems;
    public Map<String, SuperItems> myDefaultItems;
    private final SuperItems[] myPillarItems = new SuperItems[6];


    public DungeonPanel() {
        gameState = myBeginningState;
        this.setPreferredSize(new Dimension(myWidth, myHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        myDefaultItems = new HashMap<>();
        myGameUi = new GameUI(this);
        myItems = new ArrayList<>();
        setObjects();
        myGameSounds = new GameSounds();
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
        } else if (gameState == selectionState) {
            myGameUi.drawCharacterSelection(graphics2D);
        } else if (gameState == myBeginningState) {
            myGameUi.drawTitleScreen(graphics2D);
        }



        graphics2D.dispose();
    }

    public void setObjects() {
        BufferedImage ogreImg = null;
        BufferedImage gremlinImg = null;
        BufferedImage skeletonImg = null;
        BufferedImage visionPotionImg = null;
        BufferedImage healthPotionImg = null;
        SuperItems pillarP = new SuperItems(myGameUi);
        SuperItems pillarA = new SuperItems(myGameUi);
        SuperItems pillarE = new SuperItems(myGameUi);
        SuperItems pillarI = new SuperItems(myGameUi);
        SuperItems multipleItems = new SuperItems(myGameUi);
        pillarP.setName("P");
        pillarA.setName("A");
        pillarE.setName("E");
        pillarI.setName("I");
        multipleItems.setName("M");

        try {
            pillarP.setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Objects/PillarP.png"))));
            pillarA.setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Objects/PillarA.png"))));
            pillarE.setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Objects/PillarE.png"))));
            pillarI.setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Objects/PillarI.png"))));
            visionPotionImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Objects/VisionPotion.png")));
            healthPotionImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Objects/HealthPotion.png")));
            multipleItems.setImage(ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Objects/MultipleItems.png"))));
            gremlinImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/MonsterImages/Gremlin.png")));
            ogreImg = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/MonsterImages/Ogre.png")));
            skeletonImg =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/MonsterImages/Skeleton.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        myDefaultItems.put(pillarE.getName(), pillarE);
        myDefaultItems.put(pillarI.getName(), pillarI);
        myDefaultItems.put(pillarP.getName(), pillarP);
        myDefaultItems.put(pillarA.getName(), pillarA);

//        myDefaultItems.put(healthPotion.getName(), healthPotion);
//        myDefaultItems.put(visionPotion.getName(), visionPotion);

        if (myGameUi.getMyTileManager().getMyMultipleCoordinates() != null) {
            multipleItems.setWorldY(myGameUi.getMyTileManager().getMyMultipleCoordinates()[0] * myTileSize);
            multipleItems.setWorldX(myGameUi.getMyTileManager().getMyMultipleCoordinates()[1] * myTileSize);
            multipleItems.solidArea.x = multipleItems.getWorldX() + multipleItems.soldAreaDefaultX;
            multipleItems.solidArea.y = multipleItems.getWorldY() + multipleItems.solidAreaDefaultY;
            multipleItems.solidArea.width = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            multipleItems.solidArea.height = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            myItems.add(multipleItems);
        }

        if (myGameUi.getMyTileManager().getMyPillarPCoordinates() != null) {
            pillarP.setWorldY(myGameUi.getMyTileManager().getMyPillarPCoordinates()[0] * myTileSize);
            pillarP.setWorldX(myGameUi.getMyTileManager().getMyPillarPCoordinates()[1] * myTileSize);
            pillarP.solidArea.x = pillarP.getWorldX() + pillarP.soldAreaDefaultX;
            pillarP.solidArea.y = pillarP.getWorldY() + pillarP.solidAreaDefaultY;
            pillarP.solidArea.width = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            pillarP.solidArea.height = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            myItems.add(pillarP);
        }

        if (myGameUi.getMyTileManager().getMyPillarACoordinates() != null) {
            pillarA.setWorldY(myGameUi.getMyTileManager().getMyPillarACoordinates()[0] * myTileSize);
            pillarA.setWorldX(myGameUi.getMyTileManager().getMyPillarACoordinates()[1] * myTileSize);
            pillarA.solidArea.x = pillarA.getWorldX() + pillarA.soldAreaDefaultX;
            pillarA.solidArea.y = pillarA.getWorldY() + pillarA.solidAreaDefaultY;
            pillarA.solidArea.width = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            pillarA.solidArea.height = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            myItems.add(pillarA);
        }

        if (myGameUi.getMyTileManager().getMyPillarECoordinates() != null) {
            pillarE.setWorldY(myGameUi.getMyTileManager().getMyPillarECoordinates()[0] * myTileSize);
            pillarE.setWorldX(myGameUi.getMyTileManager().getMyPillarECoordinates()[1] * myTileSize);
            pillarE.solidArea.x = pillarE.getWorldX() + pillarE.soldAreaDefaultX;
            pillarE.solidArea.y = pillarE.getWorldY() + pillarE.solidAreaDefaultY;
            pillarE.solidArea.width = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            pillarE.solidArea.height = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            myItems.add(pillarE);
        }

        if (myGameUi.getMyTileManager().getMyPillarICoordinates() != null) {
            pillarI.setWorldY(myGameUi.getMyTileManager().getMyPillarICoordinates()[0] * myTileSize);
            pillarI.setWorldX(myGameUi.getMyTileManager().getMyPillarICoordinates()[1] * myTileSize);
            pillarI.solidArea.x = pillarI.getWorldX() + pillarI.soldAreaDefaultX;
            pillarI.solidArea.y = pillarI.getWorldY() + pillarI.solidAreaDefaultY;
            pillarI.solidArea.width = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            pillarI.solidArea.height = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            myItems.add(pillarI);
        }

        for (int i = 0; i < myGameUi.getMyTileManager().getMyVisionPotionCoordinatesList().size(); i += 2) {
            SuperItems visionPotion = new SuperItems(myGameUi);
            visionPotion.setName("V");
            visionPotion.setImage(visionPotionImg);
            visionPotion.setWorldY(myGameUi.getMyTileManager().getMyVisionPotionCoordinatesList().get(i) * 64);
            visionPotion.setWorldX(myGameUi.getMyTileManager().getMyVisionPotionCoordinatesList().get(i + 1) * 64);
            visionPotion.solidArea.x = visionPotion.getWorldX() + visionPotion.soldAreaDefaultX;
            visionPotion.solidArea.y = visionPotion.getWorldY() + visionPotion.solidAreaDefaultY;
            visionPotion.solidArea.width = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            visionPotion.solidArea.height = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            myItems.add(visionPotion);
            myDefaultItems.put("V", visionPotion);
        }
        for (int i = 0; i < myGameUi.getMyTileManager().getMyHealthPotionCoordinatesList().size(); i += 2) {
            SuperItems healthPotion = new SuperItems(myGameUi);
            healthPotion.setName("H");
            healthPotion.setImage(healthPotionImg);
            healthPotion.setWorldY(myGameUi.getMyTileManager().getMyHealthPotionCoordinatesList().get(i) * 64);
            healthPotion.setWorldX(myGameUi.getMyTileManager().getMyHealthPotionCoordinatesList().get(i + 1) * 64);
            healthPotion.solidArea.x = healthPotion.getWorldX() + healthPotion.soldAreaDefaultX;
            healthPotion.solidArea.y = healthPotion.getWorldY() + healthPotion.solidAreaDefaultY;
            healthPotion.solidArea.width = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            healthPotion.solidArea.height = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            myItems.add(healthPotion);
            myDefaultItems.put("H", healthPotion);
        }
        for (int i = 0; i < myGameUi.getMyTileManager().getMyOgreCoordinatesList().size(); i += 2) {
            SuperItems ogre = new SuperItems(myGameUi);
            ogre.setName("Ogre");
            ogre.setImage(ogreImg);
            ogre.setWorldY(myGameUi.getMyTileManager().getMyOgreCoordinatesList().get(i) * 64);
            ogre.setWorldX(myGameUi.getMyTileManager().getMyOgreCoordinatesList().get(i + 1) * 64);
            ogre.solidArea.x = ogre.getWorldX() + ogre.soldAreaDefaultX;
            ogre.solidArea.y = ogre.getWorldY() + ogre.solidAreaDefaultY;
            ogre.solidArea.width = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            ogre.solidArea.height = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            myItems.add(ogre);
            myDefaultItems.put("O", ogre);
        }

        for (int i = 0; i < myGameUi.getMyTileManager().getMyGremlinCoordinatesList().size(); i += 2) {
            SuperItems gremlin = new SuperItems(myGameUi);
            gremlin.setName("Gremlin");
            gremlin.setImage(gremlinImg);
            gremlin.setWorldY(myGameUi.getMyTileManager().getMyGremlinCoordinatesList().get(i) * 64);
            gremlin.setWorldX(myGameUi.getMyTileManager().getMyGremlinCoordinatesList().get(i + 1) * 64);
            gremlin.solidArea.x = gremlin.getWorldX() + gremlin.soldAreaDefaultX;
            gremlin.solidArea.y = gremlin.getWorldY() + gremlin.solidAreaDefaultY;
            gremlin.solidArea.width = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            gremlin.solidArea.height = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            myItems.add(gremlin);
            myDefaultItems.put("G", gremlin);
        }


        for (int i = 0; i < myGameUi.getMyTileManager().getMySkeletonCoordinatesList().size(); i += 2) {
            SuperItems skeleton = new SuperItems(myGameUi);
            skeleton.setName("Skeleton");
            skeleton.setImage(skeletonImg);
            skeleton.setWorldY(myGameUi.getMyTileManager().getMySkeletonCoordinatesList().get(i) * 64);
            skeleton.setWorldX(myGameUi.getMyTileManager().getMySkeletonCoordinatesList().get(i + 1) * 64);
            skeleton.solidArea.x = skeleton.getWorldX() + skeleton.soldAreaDefaultX;
            skeleton.solidArea.y = skeleton.getWorldY() + skeleton.solidAreaDefaultY;
            skeleton.solidArea.width = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            skeleton.solidArea.height = myGameUi.getMyDungeonPanel().getMyTileSize() - 20;
            myItems.add(skeleton);
            myDefaultItems.put("S", skeleton);
        }


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

    public static void main(final String... theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DungeonGUI();
            }
        });
    }

    public int getMyMaxScreenCol() {
        return myMaxScreenCol;
    }

    public int getMyMaxScreenRow() {
        return myMaxScreenRow;
    }


    public GameSounds getMyGameSounds() {
        return myGameSounds;
    }




}