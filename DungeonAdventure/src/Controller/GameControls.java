package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameControls implements KeyListener, MouseListener {
    private boolean myUpArrow;

    private boolean myDownArrow;
    private boolean myLeftArrow;
    private boolean myRightArrow;

    private boolean myStartGameClicked;
    private boolean myQuitGameClicked;
    private boolean myLoadGameClicked;

    private boolean myWarriorSelected;
    private boolean myThiefSelected;
    private boolean myPriestessSelected;

    private boolean myEasySelected;
    private boolean myMediumSelected;
    private boolean myHardSelected;

    public boolean spaceKeyProcessed = false;


    public boolean isMySpaceKeyPressed() {
        return mySpaceKeyPressed;
    }

    private boolean mySpaceKeyPressed;

    private boolean mySelection;

    public boolean isMySelection() {
        return mySelection;
    }

    private GameUI myGameUi;

    public GameControls(GameUI thePlayerUI) {
        myGameUi = thePlayerUI;
    }

    public boolean isMyUpArrow() {
        return myUpArrow;
    }

    public boolean isMyDownArrow() {
        return myDownArrow;
    }

    public boolean isMyLeftArrow() {
        return myLeftArrow;
    }

    public boolean isMyRightArrow() {
        return myRightArrow;
    }


    @Override
    public void keyTyped(KeyEvent theKeyEvent) {

    }

    // Character movement
    @Override
    public void keyPressed(KeyEvent theKeyEvent) {

        int keyCode = theKeyEvent.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            myUpArrow = true;
            myGameUi.getMyCharacter().setMoving(true);
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            myDownArrow = true;
            myGameUi.getMyCharacter().setMoving(true);
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            myLeftArrow = true;
            myGameUi.getMyCharacter().setMoving(true);

        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            myRightArrow = true;
            myGameUi.getMyCharacter().setMoving(true);
        }
        if (keyCode == KeyEvent.VK_SPACE && !spaceKeyProcessed) {
            mySpaceKeyPressed = true;
            myGameUi.getMyCharacter().setAttacking(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent theKeyEvent) {
        int keyCode = theKeyEvent.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            myUpArrow = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            myDownArrow = false;
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            myLeftArrow = false;
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            myRightArrow = false;
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            mySpaceKeyPressed = false;
            myGameUi.getMyCharacter().setAttacking(false);
            spaceKeyProcessed = false; // Reset flag when space key is released
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }


    @Override
    public void mousePressed(MouseEvent e) {
        if (myGameUi.getMyDungeonPanel().getGameState() == myGameUi.getMyDungeonPanel().getMyBeginningState()) {
            if (myGameUi.getMyStartRectangle().contains(e.getPoint())) {
                myStartGameClicked = true;
                myLoadGameClicked = false;
                myQuitGameClicked = false;
                myGameUi.getMyDungeonPanel().getMyGameSounds().playClickSound();

            }
            if (myGameUi.getMyLoadRectangle().contains(e.getPoint())) {
                myStartGameClicked = false;
                myLoadGameClicked = true;
                myQuitGameClicked = false;
                myGameUi.getMyDungeonPanel().getMyGameSounds().playClickSound();

            }
            if (myGameUi.getMyQuitRectangle().contains(e.getPoint())) {
                myStartGameClicked = false;
                myLoadGameClicked = false;
                myQuitGameClicked = true;
                myGameUi.getMyDungeonPanel().getMyGameSounds().playClickSound();

            }
        } else {
            if (myGameUi.getMyWarriorCheckBox().contains(e.getPoint())) {
                myWarriorSelected = true;
                myThiefSelected = false;
                myPriestessSelected = false;
                myGameUi.getMyDungeonPanel().getMyGameSounds().playClickSound();

            }
            if (myGameUi.getMyThiefCheckBox().contains(e.getPoint())) {
                myWarriorSelected = false;
                myThiefSelected = true;
                myPriestessSelected = false;
                myGameUi.getMyDungeonPanel().getMyGameSounds().playClickSound();
            }
            if (myGameUi.getMyPriestessCheckBox().contains(e.getPoint())) {
                myWarriorSelected = false;
                myThiefSelected = false;
                myPriestessSelected = true;
                myGameUi.getMyDungeonPanel().getMyGameSounds().playClickSound();

            }

            if (myGameUi.getMyEasyCheckBox().contains(e.getPoint())) {
                myEasySelected = true;
                myMediumSelected = false;
                myHardSelected = false;
                myGameUi.getMyDungeonPanel().getMyGameSounds().playClickSound();

            }
            if (myGameUi.getMyMediumCheckBox().contains(e.getPoint())) {
                myEasySelected = false;
                myMediumSelected = true;
                myHardSelected = false;
                myGameUi.getMyDungeonPanel().getMyGameSounds().playClickSound();

            }
            if (myGameUi.getMyHardCheckBox().contains(e.getPoint())) {
                myEasySelected = false;
                myMediumSelected = false;
                myHardSelected = true;
                myGameUi.getMyDungeonPanel().getMyGameSounds().playClickSound();

            }
            if (myGameUi.getMySelectButton().contains(e.getPoint())) {
                mySelection = true;
                myGameUi.getMyDungeonPanel().getMyGameSounds().playClickSound();

            }

        }
        myGameUi.getMyDungeonPanel().repaint();

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public boolean isStartGameClicked() {
        return myStartGameClicked;
    }

    public boolean isQuitGameClicked() {
        return myQuitGameClicked;
    }

    public boolean isLoadGameClicked() {
        return myLoadGameClicked;
    }

    public boolean isMyWarriorSelected() {
        return myWarriorSelected;
    }

    public boolean isMyThiefSelected() {
        return myThiefSelected;
    }

    public boolean isMyPriestessSelected() {
        return myPriestessSelected;
    }

    public boolean isMyEasySelected() {
        return myEasySelected;
    }

    public boolean isMyMediumSelected() {
        return myMediumSelected;
    }

    public boolean isMyHardSelected() {
        return myHardSelected;
    }

}
