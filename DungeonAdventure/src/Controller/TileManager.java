package Controller;

import Model.Priestess;
import Model.Thief;
import Model.Warrior;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;

public class TileManager {


    private final DungeonAdventure myDungeonAdventurer;

    private int myEntranceRow;
    private int myEntranceCol;

    private boolean myIsGameOver = false;

    private boolean myIsPlayerWin = false;

    private String battleResultMessage;
    private boolean battleCalculated = false;

    private Rectangle myCloseBattleWindow;
    private int[] myPillarACoordinates;
    private int[] myPillarPCoordinates;
    private int[] myPillarICoordinates;
    private int[] myPillarECoordinates;
    private int[] myExitCoordinates;

    private final ArrayList<Integer> myVisionPotionCoordinatesList;
    private final ArrayList<Integer> myHealthPotionCoordinatesList;
    private int[] myMultipleCoordinates;
    private  ArrayList<Integer> myPitCoordinates;

    private final Map<String, Integer> myItemCollisionFrequency;

    private final ArrayList<Integer> myOgreCoordinatesList;
    private final ArrayList<Integer> myGremlinCoordinatesList;
    private final ArrayList<Integer> mySkeletonCoordinatesList;






    private final String myHealthPotion = "H";
    private final String myVisionPotion = "V";
    private final String myPillarP = "P";
    private final String myPillarI = "I";
    private final String myPillarE = "E";
    private final String myPillarA = "A";
    private final String myEntrance = "i";
    private final String myExit = "o";
    private final String myMultiple = "M";
    private final String myPit = "X";
    private final String myEmpty = "N";
    private final String myOgre = "O";
    private final String myGremlin = "G";
    private final String mySkeleton = "S";
    private  int myRow;
    private  int myCol;
    private final GameUI myGameUi;
    private final Map<String, Tile> myTile;

    private Monsters monsterEncountered;



    private String[][] mapTileNums;



    TileManager(final GameUI theGameUi) {
        myGameUi = theGameUi;
        myTile = new HashMap<>();
        myVisionPotionCoordinatesList = new ArrayList<>();
        myHealthPotionCoordinatesList = new ArrayList<>();
        myOgreCoordinatesList = new ArrayList<>();
        mySkeletonCoordinatesList = new ArrayList<>();
        myGremlinCoordinatesList = new ArrayList<>();
        myItemCollisionFrequency = new HashMap<>();
        myDungeonAdventurer = new DungeonAdventure();
        myPitCoordinates = new ArrayList<>();
        getTileImage();
    }


    public void getTileImage(){
        try{
            Tile rock = new Tile();
            rock.setMySolid(true);
            rock.setMyTileImage((ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Tiles/wall.png")))));

            Tile earth = new Tile();
            earth.setMyTileImage((ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Tiles/earth.png")))));

            Tile door = new Tile();
            door.setMyTileImage((ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Tiles/Door.png")))));

            myTile.put("*", rock);
            myTile.put("-", earth);
            myTile.put("D", door);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void drawTiles(final Graphics2D theGraphics){
        int row = 0;
        int col = 0;


        while (row < this.myRow && col < this.myCol) {
            String title = mapTileNums[row][col];

            switch (title) {
                case myHealthPotion, myVisionPotion, myEntrance, myPillarP, myPillarI,
                     myPillarE, myExit, myMultiple, myPit, myEmpty,
                     myPillarA, myOgre, myGremlin, mySkeleton -> title = "-";
            }

            int bound = myGameUi.getMyDungeonPanel().getMyTileSize();

            int x = col * myGameUi.getMyDungeonPanel().getMyTileSize();
            int y = row * myGameUi.getMyDungeonPanel().getMyTileSize();

            int screenX = x - myGameUi.getMyCharacter().getMyX() + myGameUi.getMyCharacter().getMyScreenX();
            int screenY = y - myGameUi.getMyCharacter().getMyY() + myGameUi.getMyCharacter().getMyScreenY();

            if(x + bound > myGameUi.getMyCharacter().getMyX() - myGameUi.getMyCharacter().getMyScreenX() &&
                    x  - bound < myGameUi.getMyCharacter().getMyX() + myGameUi.getMyCharacter().getMyScreenX() &&
                    y  + bound> myGameUi.getMyCharacter().getMyY() - myGameUi.getMyCharacter().getMyScreenY() &&
                    y  - bound< myGameUi.getMyCharacter().getMyY() + myGameUi.getMyCharacter().getMyScreenY()){
                theGraphics.drawImage(myTile.get(title).getMyTileImage(), screenX, screenY, myGameUi.getMyDungeonPanel().getMyTileSize() - 1, myGameUi.getMyDungeonPanel().getMyTileSize() - 1, null);
            }
            row++;
            if(row == this.myRow){
                row = 0;
                col++;
            }
        }
        //drawMonsterEncounter(theGraphics);
    }


    public void generateDungeon(){
        String path = "Maze.txt";

        myRow = myGameUi.getMyMazeSize() * 5;
        myCol = myGameUi.getMyMazeSize() * 5;
        mapTileNums = new String[myRow][myCol];
        myGameUi.getMyDungeon().writeMazeToFile(path);
        loadMap();
    }

    public void loadMap(){
        try (BufferedReader reader = new BufferedReader(new FileReader(("DungeonAdventure/src/Maps/Maze.txt")))){
            for(int row = 0; row < this.myRow; row++) { // 20
                String line = reader.readLine();
                for(int col = 0; col < this.myCol; col++) { // 20
                    String[] numbers = line.split(" "); // * * A *
                    String s = numbers[col];
                    if(s.equals("i")){
                        myEntranceRow = row;
                        myEntranceCol = col;
                    }
                    if(s.equals("M")){
                        myMultipleCoordinates = new int[2];
                        myMultipleCoordinates[0] = row;
                        myMultipleCoordinates[1] = col;
                    }
                    if(s.equals("A")){
                        myPillarACoordinates = new int[2];
                        myPillarACoordinates[0] = row;
                        myPillarACoordinates[1] = col;
                    }
                    if(s.equals("I")){
                        myPillarICoordinates = new int[2];
                        myPillarICoordinates[0] = row;
                        myPillarICoordinates[1] = col;
                    }

                    if(s.equals("E")){
                        myPillarECoordinates = new int[2];
                        myPillarECoordinates[0] = row;
                        myPillarECoordinates[1] = col;
                    }

                    if(s.equals("H")){
                        myHealthPotionCoordinatesList.add(row);
                        myHealthPotionCoordinatesList.add(col);
                    }
                    if(s.equals("V")){
                        myVisionPotionCoordinatesList.add(row);
                        myVisionPotionCoordinatesList.add(col);
                    }
                    if(s.equals("O")){
                        myOgreCoordinatesList.add(row);
                        myOgreCoordinatesList.add(col);
                    }
                    if(s.equals("G")){
                        myGremlinCoordinatesList.add(row);
                        myGremlinCoordinatesList.add(col);
                    }
                    if(s.equals("S")){
                        mySkeletonCoordinatesList.add(row);
                        mySkeletonCoordinatesList.add(col);
                    }
                    if(s.equals("X")){
                        myPitCoordinates.add(row);
                        myPitCoordinates.add(col);
                    }
                    if(s.equals("P")){
                        myPillarPCoordinates = new int[2];
                        myPillarPCoordinates[0] = row;
                        myPillarPCoordinates[1] = col;
                    }
                    if(s.equals("o")){
                        myExitCoordinates = new int[2];
                        myExitCoordinates[0] = row;
                        myExitCoordinates[1] = col;
                    }
                    mapTileNums[row][col] = s;
                }
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        myGameUi.getMyDungeonPanel().setObjects();
        myGameUi.getMyCharacter().initHeroes();

    }


    public boolean isTileCollision(final Rectangle theHitBox) {

        int x1 = theHitBox.x / myGameUi.getMyDungeonPanel().getMyTileSize();
        int y1 = theHitBox.y / myGameUi.getMyDungeonPanel().getMyTileSize();;
        int x2 = (theHitBox.x + theHitBox.width) / myGameUi.getMyDungeonPanel().getMyTileSize();
        int y2 = (theHitBox.y + theHitBox.height) / myGameUi.getMyDungeonPanel().getMyTileSize();
        boolean isTileSolid = false;
        for (int row = y1; row <= y2; row++) {
            for (int col = x1; col <= x2; col++) {
                String tile = mapTileNums[row][col];

                if(myTile.get(tile) != null && myTile.get(tile).isSolid()){
                    isTileSolid = true;
                }
            }
        }

        List<SuperItems> itemsCopy = new ArrayList<>(myGameUi.getMyDungeonPanel().myItems);
        for (SuperItems item : itemsCopy) {
            if (item.solidArea.intersects(theHitBox)) {
                // Handle collision with the item
                // You can perform actions based on the item's name or type
                System.out.println("Collision with item: " + item.getMyName() + " at row, col " + item.getWorldX() + " " + item.getMyY());
                if (!item.getMyName().equals("M")) {
                    if (item.getMyName().equals("o")) {
                        if (myItemCollisionFrequency.containsKey(myPillarA) && myItemCollisionFrequency.containsKey(myPillarE) &&
                                myItemCollisionFrequency.containsKey(myPillarI) && myItemCollisionFrequency.containsKey(myPillarP)) {
                            if (myItemCollisionFrequency.get(myPillarA) == 1 && myItemCollisionFrequency.get(myPillarE) == 1 &&
                                    myItemCollisionFrequency.get(myPillarI) == 1 && myItemCollisionFrequency.get(myPillarE) == 1) {
                                myIsPlayerWin = true;
                                myGameUi.getMyDungeonPanel().myItems.remove(item);
                            }
                        }

                    } else {
                        if (myGameUi.getMyDungeonPanel().myDefaultItems.containsKey(item.getMyName())) {
                            myGameUi.getMyDungeonPanel().myDefaultItems.get(item.getMyName()).setMyCollisoin(true);
                            myItemCollisionFrequency.put(item.getMyName(), myItemCollisionFrequency.getOrDefault(item.getMyName(), 0) + 1);
                        }
                        myGameUi.getMyDungeonPanel().myItems.remove(item);
                    }
                }


            }

            List<Monsters> monsterCopy = new ArrayList<>(myGameUi.getMyDungeonPanel().myMonsters);
            for (Monsters monster : monsterCopy) {
                if (monster.solidArea.intersects(theHitBox)) {
                    if (myGameUi.getMyDungeonPanel().getMyDefaultMonsters().containsKey(monster.getMonsterType())) {
                        myGameUi.getMyDungeonPanel().getMyDefaultMonsters().get(monster.getMonsterType()).setCollision(true);
                        System.out.println("Collided with monster " + monster.getMonsterType() + " at row, col " + monster.getMyX() + " " + monster.getMyY());
                        monsterEncountered = monster;
                        // Check if the player's character has defeated the monster
                        if ((myGameUi.getMyCharacter().getCharacterType().getMyHitPoints() > 0 && monsterEncountered.getMyMonster().getMyHitPoints() <= 0)) {
                            // Player has defeated the monster, remove it from the list
                            myGameUi.getMyDungeonPanel().myMonsters.remove(monster);
                        }else if(myGameUi.getMyCharacter().getCharacterType().getMyHitPoints() <= 0){
                            myIsGameOver = true;
                        }
                    }
                }
            }
        }
        return isTileSolid;
    }


    private void drawBattleWindow(final Graphics2D theGraphics) {
        int x = myGameUi.getMyDungeonPanel().getMyWidth() / 2;
        int y = myGameUi.getMyDungeonPanel().getMyHeight() / 2 - 200;

        myGameUi.drawFrame(x, y, 450, 600, theGraphics);
        myCloseBattleWindow = new Rectangle(x + 20, y + 20, 20, 20);
        theGraphics.setColor(new Color(255, 255, 255));
        theGraphics.setStroke(new BasicStroke(5));

        myGameUi.updateCheckboxSelection(theGraphics, myCloseBattleWindow);
        theGraphics.setColor(new Color(0, 0, 0, 0));
        theGraphics.fill(myCloseBattleWindow);
        int textX = x + 60;
        int textY = y + 40;

        theGraphics.setColor(Color.WHITE);
        theGraphics.drawString("You encountered an " + monsterEncountered.getMyMonster().getMyName(), textX, textY);
    }
    public void drawMonsterEncounter(final Graphics2D theGraphics) {
        if (monsterEncountered != null && !myGameUi.getMyGameControls().isMyCloseBattleWindow()) {
            // Use an off-screen image for double buffering
            BufferedImage offScreen = new BufferedImage(myGameUi.getMyDungeonPanel().getMyWidth(),
                    myGameUi.getMyDungeonPanel().getMyHeight(),
                    BufferedImage.TYPE_INT_ARGB);
            Graphics2D offScreenG2 = offScreen.createGraphics();

            // Draw the battle window on the off-screen image
            drawBattleWindow(offScreenG2);

            // Calculate the battle result only once
            if (!battleCalculated) {
                calculateBattleResult();
                battleCalculated = true;
            }

            // Display the stored battle result on the off-screen image
            drawBattleResult(offScreenG2);

            // Copy the off-screen image to the actual graphics context
            theGraphics.drawImage(offScreen, 0, 0, null);

            myGameUi.getMyCharacter().setMyPlayerIsMoving(false); // Set the character to frozen state
            myGameUi.getMyCharacter().setMyPlayerSpeed(0);
            offScreenG2.dispose();
        } else {
            monsterEncountered = null;
            if (!myIsGameOver) {
                myGameUi.getMyCharacter().setMyPlayerSpeed(4);
            }
            battleCalculated = false; // Reset the flag for the next encounter
        }
    }

    private void calculateBattleResult() {
        StringBuilder resultMessage = new StringBuilder();
        int beforeBattle, afterBattle;

        while (myGameUi.getMyCharacter().getCharacterType().getMyHitPoints() > 0 && monsterEncountered.getMyMonster().getMyHitPoints() > 0) {
            if ( Math.random() < 0.5) {
                // Character attacks
                beforeBattle = monsterEncountered.getMyMonster().getMyHitPoints();
                // boolean characterAttackSuccess = battle(myGameUi.getMyCharacter().getCharacterType(), monsterEncountered.getMyMonster());
                myDungeonAdventurer.battle(myGameUi.getMyCharacter().getCharacterType(), monsterEncountered.getMyMonster());
                afterBattle = monsterEncountered.getMyMonster().getMyHitPoints();

                boolean characterAttackSuccess = beforeBattle > afterBattle;
                if (characterAttackSuccess) {
                    resultMessage.append("You successfully battled with ")
                            .append(monsterEncountered.getMonsterType())
                            .append(" and dealt ")
                            .append(beforeBattle - afterBattle)
                            .append(" damage.")
                            .append("\n");
                } else {
                    resultMessage.append("You unsuccessfully battled with ")
                            .append(monsterEncountered.getMonsterType())
                            .append(".")
                            .append("\n");
                } if(myGameUi.getMyCharacter().getCharacterType() instanceof Warrior){
                    Warrior warrior = ((Warrior) myGameUi.getMyCharacter().getCharacterType());
                    if(warrior.getMySpecialSuccess()){
                        resultMessage.append("You successfully landed a")
                                .append(" Landing Blow for ")
                                .append(warrior.getMySpecialAmount())
                                .append(" damage!")
                                .append("\n");
                        warrior.setMySpecialSuccess(false);
                    }
                } else if(myGameUi.getMyCharacter().getCharacterType() instanceof Priestess){
                    Priestess priestess = ((Priestess) myGameUi.getMyCharacter().getCharacterType());
                    if(priestess.getMySpecialSuccess()){
                        resultMessage.append("You successfully used special")
                                .append(" healing for ")
                                .append(priestess.getMySpecialAmount())
                                .append(" hit points!")
                                .append("\n");
                        priestess.setMySpecialSuccess(false);
                    }
                } else if(myGameUi.getMyCharacter().getCharacterType() instanceof Thief){
                    Thief thief = ((Thief) myGameUi.getMyCharacter().getCharacterType());
                    if(thief.getMySpecialSuccess()){
                        resultMessage.append("You successfully landed a")
                                .append(" surprise attack for ")
                                .append(thief.getMySpecialAmount())
                                .append(" damage!")
                                .append("\n");
                        thief.setMySpecialSuccess(false);
                    }
                }
            } else {
                // Monster attacks
                beforeBattle = myGameUi.getMyCharacter().getCharacterType().getMyHitPoints();
                myDungeonAdventurer.battle(monsterEncountered.getMyMonster(), myGameUi.getMyCharacter().getCharacterType());
                afterBattle = myGameUi.getMyCharacter().getCharacterType().getMyHitPoints();

                boolean monsterAttackSuccess = beforeBattle > afterBattle;
                if (monsterAttackSuccess) {
                    resultMessage.append(monsterEncountered.getMonsterType())
                            .append(" successfully battled with ")
                            .append(myGameUi.getMyCharacter().getCharacterType().getMyName())
                            .append(" and dealt ")
                            .append(beforeBattle - afterBattle)
                            .append(" damage.")
                            .append("\n");
                } else {
                    resultMessage.append(monsterEncountered.getMonsterType())
                            .append(" unsuccessfully battled with ")
                            .append(myGameUi.getMyCharacter().getCharacterType().getMyName())
                            .append(".")
                            .append("\n");
                }
                if(monsterEncountered.getMyMonster().getMyHealed()){
                    resultMessage.append(monsterEncountered.getMonsterType())
                            .append(" Was able to heal")
                            .append(" for")
                            .append(monsterEncountered.getMyMonster().getMyHealedAmount())
                            .append(" hit points.")
                            .append("\n");
                }
            }

        }

        if (myGameUi.getMyCharacter().getCharacterType().getMyHitPoints() <= 0) {
            resultMessage.append("You have been defeated by ").append(monsterEncountered.getMonsterType()).append(".\n");
        } else if(monsterEncountered.getMyMonster().getMyHitPoints() <= 0) {
            resultMessage.append("You have defeated ").append(monsterEncountered.getMonsterType()).append(".\n");
            resultMessage.append("Your current hit point is ").append(myGameUi.getMyCharacter().getCharacterType().getMyHitPoints());
        }




        battleResultMessage = resultMessage.toString();
    }


    private void drawBattleResult(Graphics theGraphics) {
        int y = myGameUi.getMyDungeonPanel().getMyHeight() / 2 - 200;
        int textY = y + 70;
        int x = myGameUi.getMyDungeonPanel().getMyWidth() / 2;
        int textX =  x + 60;
        String[] messages = battleResultMessage.split("\n");
        for (String message : messages) {
            theGraphics.drawString(message, textX, textY);
            textY += 20; // Increase Y coordinate for the next message
        }
    }




    public int[] getMyPillarACoordinates() {
        return myPillarACoordinates;
    }

    public int[] getMyPillarPCoordinates() {
        return myPillarPCoordinates;
    }

    public int[] getMyPillarICoordinates() {
        return myPillarICoordinates;
    }

    public int[] getMyPillarECoordinates() {
        return myPillarECoordinates;
    }


    public int[] getMyMultipleCoordinates() {
        return myMultipleCoordinates;
    }

    public ArrayList<Integer> getMyVisionPotionCoordinatesList() {
        return myVisionPotionCoordinatesList;
    }

    public ArrayList<Integer> getMyHealthPotionCoordinatesList() {
        return myHealthPotionCoordinatesList;
    }

    public Map<String, Integer> getMyItemCollisionFrequency() {
        return myItemCollisionFrequency;
    }

    public ArrayList<Integer> getMyOgreCoordinatesList() {
        return myOgreCoordinatesList;
    }

    public ArrayList<Integer> getMyGremlinCoordinatesList() {
        return myGremlinCoordinatesList;
    }

    public ArrayList<Integer> getMySkeletonCoordinatesList() {
        return mySkeletonCoordinatesList;
    }

    public String[][] getMapTileNums() {
        return mapTileNums;
    }

    public int[] getMyExitCoordinates() {
        return myExitCoordinates;
    }

    public boolean isMyIsPlayerWin() {
        return myIsPlayerWin;
    }

    public Rectangle getMyCloseBattleWindow() {
        return myCloseBattleWindow;
    }

    public int getMyEntranceRow() {
        return myEntranceRow;
    }

    public boolean isMyIsGameOver() {
        return myIsGameOver;
    }

    public int getMyEntranceCol() {
        return myEntranceCol;
    }

    public ArrayList<Integer> getMyPitCoordinates() {
        return myPitCoordinates;
    }
}