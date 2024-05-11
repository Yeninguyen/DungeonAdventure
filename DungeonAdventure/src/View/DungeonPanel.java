package View;

import Controller.GameUI;

import javax.swing.*;
import java.awt.*;


public class DungeonPanel extends JPanel implements Runnable {
    //Screen Setting
    private final int myOriginalTileSize = 16; // 16px
    private final int myTileSize = myOriginalTileSize * 3; // 48 pixel
    private final int myMaxScreenCol = 16; // 16
    private final int myMaxScreenRow = 12; // 12
    private final int myWidth = myTileSize * myMaxScreenCol; // 768
    private final int myHeight = myTileSize * myMaxScreenRow; // 576


    private final int myTitleState = 0;

    // GAME STATE
    private int gameState;


    private final int playState = 2;
    private final int selectionState = 1;
    private final int pauseState = 3;

    private final GameUI myGameUi;


    private Thread gameThread;

    public DungeonPanel() {
        gameState = myTitleState;
        this.setPreferredSize(new Dimension(myWidth, myHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        myGameUi = new GameUI(this);
        startGameThread();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double interval = 1000000000 / 60;
        double delta = 0;
        long prevTime = System.nanoTime();
        long currTime;
        while (gameThread != null) {
            System.out.println(gameState);
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
        } else if (gameState == myTitleState) {
            myGameUi.drawTitleScreen(graphics2D);
        }
    }

    public int getMyTitleState() {
        return myTitleState;
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


}
