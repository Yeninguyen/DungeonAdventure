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

    private boolean myUsernameBoxSelected;

    private boolean mySpaceKeyPressed;

    private boolean mySelection;

    private boolean myInventorySelected = false;

    public boolean isMySelection() {
        return mySelection;
    }

    private final GameUI myGameUi;


    private final StringBuilder userName = new StringBuilder();

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
        if (myUsernameBoxSelected) {
            char keyChar = theKeyEvent.getKeyChar();
            if (Character.isLetterOrDigit(keyChar)) {
                userName.append(keyChar); // Append the typed character to the username
            } else if (keyChar == KeyEvent.VK_SPACE) {
                userName.append(" ");
            } else if (keyChar == KeyEvent.VK_BACK_SPACE && userName.length() > 0) {
                userName.deleteCharAt(userName.length() - 1); // Remove the last character if backspace is pressed
            }
        }
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
        if (keyCode == KeyEvent.VK_SPACE) {
            mySpaceKeyPressed = true;
        }
        if(keyCode == KeyEvent.VK_ENTER){
            myInventorySelected = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent theKeyEvent) {
        int keyCode = theKeyEvent.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            myUpArrow = false;
            myGameUi.getMyCharacter().setMoving(false);
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            myDownArrow = false;
            myGameUi.getMyCharacter().setMoving(false);
        }
        if (keyCode == KeyEvent.VK_LEFT) {
            myLeftArrow = false;
            myGameUi.getMyCharacter().setMoving(false);
        }
        if (keyCode == KeyEvent.VK_RIGHT) {
            myRightArrow = false;
            myGameUi.getMyCharacter().setMoving(false);
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            mySpaceKeyPressed = false;
        }
        if(keyCode == KeyEvent.VK_ENTER){
            myInventorySelected = false;
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
                myGameUi.getMyDungeonPanel().getMyGameSounds().playClickSound(1);

            }

            if (myGameUi.getMyLoadRectangle().contains(e.getPoint())) {
                myStartGameClicked = false;
                myLoadGameClicked = true;
                myQuitGameClicked = false;
                myGameUi.getMyDungeonPanel().getMyGameSounds().playClickSound(1);

            }
            if (myGameUi.getMyQuitRectangle().contains(e.getPoint())) {
                myStartGameClicked = false;
                myLoadGameClicked = false;
                myQuitGameClicked = true;
                myGameUi.getMyDungeonPanel().getMyGameSounds().playClickSound(1);

            }
        } else {
            if (myGameUi.getMyWarriorCheckBox() != null && myGameUi.getMyWarriorCheckBox().contains(e.getPoint())) {
                myWarriorSelected = true;
                myThiefSelected = false;
                myPriestessSelected = false;
                myUsernameBoxSelected = false;
                myGameUi.getMyDungeonPanel().getMyGameSounds().playClickSound(1);
                myGameUi.getMyDungeonPanel().getMyGameSounds().playClickSound(2);
            }
            if (myGameUi.getMyUserNameBox().contains(e.getPoint())){
                myUsernameBoxSelected = true;
                myGameUi.getMyDungeonPanel().getMyGameSounds().playClickSound(1);
            }
            if (myGameUi.getMyThiefCheckBox().contains(e.getPoint())) {
                myWarriorSelected = false;
                myThiefSelected = true;
                myPriestessSelected = false;
                myUsernameBoxSelected = false;
                myGameUi.getMyDungeonPanel().getMyGameSounds().playClickSound(1);
            }
            if (myGameUi.getMyPriestessCheckBox().contains(e.getPoint())) {
                myWarriorSelected = false;
                myThiefSelected = false;
                myPriestessSelected = true;
                myUsernameBoxSelected = false;
                myGameUi.getMyDungeonPanel().getMyGameSounds().playClickSound(1);

            }

            if (myGameUi.getMyEasyCheckBox().contains(e.getPoint())) {
                myEasySelected = true;
                myMediumSelected = false;
                myHardSelected = false;
                myUsernameBoxSelected = false;
                myGameUi.getMyDungeonPanel().getMyGameSounds().playClickSound(1);

            }
            if (myGameUi.getMyMediumCheckBox().contains(e.getPoint())) {
                myEasySelected = false;
                myMediumSelected = true;
                myHardSelected = false;
                myUsernameBoxSelected = false;
                myGameUi.getMyDungeonPanel().getMyGameSounds().playClickSound(1);

            }
            if (myGameUi.getMyHardCheckBox().contains(e.getPoint())) {
                myEasySelected = false;
                myMediumSelected = false;
                myHardSelected = true;
                myUsernameBoxSelected = false;
                myGameUi.getMyDungeonPanel().getMyGameSounds().playClickSound(1);

            }
            if (myGameUi.getMySelectButton().contains(e.getPoint())) {
                mySelection = true;
                myUsernameBoxSelected = false;
                myGameUi.getMyDungeonPanel().getMyGameSounds().playClickSound(1);
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
    public boolean isMySpaceKeyPressed() {
        return mySpaceKeyPressed;
    }
    public String getUsername() {
        return userName.toString();
    }

    public boolean isMyUsernameBoxSelected() {
        return myUsernameBoxSelected;
    }

    public boolean isMyInventorySelected() {
        return myInventorySelected;
    }
}
