package View;

import Controller.GameSounds;
import Controller.GameUI;

import javax.swing.*;
import java.awt.*;


public class DungeonPanel extends JPanel implements Runnable {
    //Screen Setting
    private final int myOriginalTileSize = 16; // 16px   // 704 x
    private final int myTileSize = myOriginalTileSize * 4; // 64 pixel
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

    public DungeonPanel() {
        gameState = myBeginningState;
        this.setPreferredSize(new Dimension(myWidth, myHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        myGameUi = new GameUI(this);
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
        }else {
            myGameUi.getMyCharacter().updateAnimation();
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
        graphics2D.dispose();
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
