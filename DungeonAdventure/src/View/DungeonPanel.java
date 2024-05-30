package View;

import Controller.GameSounds;
import Controller.GameUI;
import Controller.Items;
import Controller.SuperItems;

import javax.swing.*;
import java.awt.*;


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

    private SuperItems obj[] = new SuperItems[6];


    public DungeonPanel() {
        gameState = myBeginningState;
        this.setPreferredSize(new Dimension(myWidth, myHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        myGameUi = new GameUI(this);
        myGameSounds = new GameSounds();
        setObjects();
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
        } else if (gameState == selectionState) {
            myGameUi.drawCharacterSelection(graphics2D);
        } else if (gameState == myBeginningState) {
            myGameUi.drawTitleScreen(graphics2D);
        }

        for (int i = 0; i < obj.length; i++)

    {
        if (obj[i] != null) {
            obj[i].draw(graphics2D);
        }
    }
        graphics2D.dispose();
    }

    public void setObjects() {
        obj[0] = new Items();
        obj[0].setGameUI(myGameUi);  // Ensure this is called before any draw method
        obj[0].worldX = 38 * myTileSize;
        obj[0].worldY = 7 * myTileSize;

//        obj[1].worldX = 2 * myTileSize;
//        obj[1].worldY = 7 * myTileSize;

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
