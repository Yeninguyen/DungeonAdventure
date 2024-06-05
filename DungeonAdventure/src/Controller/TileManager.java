package Controller;

import Model.Dungeon;
import Model.Monster;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.MediaSize;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.*;
import java.util.List;

public class TileManager {



    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);


    private DungeonAdventure myDungeonAdventurer;

    public int entranceRow;
    public int entranceCol;

    public boolean gameOver = false;

    private boolean myWin = false;

    private Rectangle myCloseBattleWindow;
    private int[] myPillarACoordinates;
    private int[] myPillarPCoordinates;
    private int[] myPillarICoordinates;
    private int[] myPillarECoordinates;
    private int[] myExitCoordinates;

    private final ArrayList<Integer> myVisionPotionCoordinatesList;
    private final ArrayList<Integer> myHealthPotionCoordinatesList;
    private int[] myMultipleCoordinates;

    private final Map<String, Integer> myItemCollisionFrequency;

    private ArrayList<Integer> myOgreCoordinatesList;
    private ArrayList<Integer> myGremlinCoordinatesList;
    private ArrayList<Integer> mySkeletonCoordinatesList;






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
    GameUI myGameUi;
    private final Map<String, Tile> myTile;

    private Monsters monsterEncountered;
    private long monsterEncounterTime;



    private String[][] mapTileNums;

    Monsters myMonster;

    TileManager(final GameUI theGameUi) {
        myGameUi = theGameUi;
        myTile = new HashMap<>();
        myVisionPotionCoordinatesList = new ArrayList<>();
        myHealthPotionCoordinatesList = new ArrayList<>();
        myOgreCoordinatesList = new ArrayList<>();
        mySkeletonCoordinatesList = new ArrayList<>();
        myGremlinCoordinatesList = new ArrayList<>();
        myItemCollisionFrequency = new HashMap<>();
        myDungeonAdventurer = new DungeonAdventure(myGameUi);
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

    public void drawTiles(Graphics2D theGraphics){
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

            int screenX = x - myGameUi.getMyCharacter().getMyX() + myGameUi.getMyCharacter().getScreenX();
            int screenY = y - myGameUi.getMyCharacter().getMyY() + myGameUi.getMyCharacter().getScreenY();

            if(x + bound > myGameUi.getMyCharacter().getMyX() - myGameUi.getMyCharacter().getScreenX() &&
                    x  - bound < myGameUi.getMyCharacter().getMyX() + myGameUi.getMyCharacter().getScreenX() &&
                    y  + bound> myGameUi.getMyCharacter().getMyY() - myGameUi.getMyCharacter().getScreenY() &&
                    y  - bound< myGameUi.getMyCharacter().getMyY() + myGameUi.getMyCharacter().getScreenY()){
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

        myRow = myGameUi.size * 5;
        myCol = myGameUi.size * 5;
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
                    entranceRow = row;
                    entranceCol = col;
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
       // monsterCoordinates();
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

//        for(Monsters monster : monsters){
//            if(monster.solidArea.intersects(theHitBox)){
//                System.out.println("player collided with a " +  monster.getMonsterType());
//            }
//        }

        List<SuperItems> itemsCopy = new ArrayList<>(myGameUi.getMyDungeonPanel().myItems);
        for (SuperItems item : itemsCopy) {
            if (item.solidArea.intersects(theHitBox)) {
                // Handle collision with the item
                // You can perform actions based on the item's name or type
                System.out.println("Collision with item: " + item.getName() + " at row, col " + item.getWorldX() + " " + item.getWorldY() );
                if(!item.getName().equals("M")) {
                    if (item.getName().equals("o")) {
                        if (myItemCollisionFrequency.containsKey(myPillarA) && myItemCollisionFrequency.containsKey(myPillarE) &&
                                myItemCollisionFrequency.containsKey(myPillarI) && myItemCollisionFrequency.containsKey(myPillarP)) {
                            if (myItemCollisionFrequency.get(myPillarA) == 1 && myItemCollisionFrequency.get(myPillarE) == 1 &&
                                    myItemCollisionFrequency.get(myPillarI) == 1 && myItemCollisionFrequency.get(myPillarE) == 1) {
                                myWin = true;
                                myGameUi.getMyDungeonPanel().myItems.remove(item);
                            }
                        }

                    } else {
                        if (myGameUi.getMyDungeonPanel().myDefaultItems.containsKey(item.getName())) {
                            myGameUi.getMyDungeonPanel().myDefaultItems.get(item.getName()).setCollision(true);
                            myItemCollisionFrequency.put(item.getName(), myItemCollisionFrequency.getOrDefault(item.getName(), 0) + 1);
                        }
                        myGameUi.getMyDungeonPanel().myItems.remove(item);
                    }
                }



            }

            List<Monsters> monsterCopy = new ArrayList<>(myGameUi.getMyDungeonPanel().myMonsters);
            for (Monsters monster: monsterCopy) {
                if (monster.solidArea.intersects(theHitBox)) {
                    if (myGameUi.getMyDungeonPanel().getMyDefaultMonsters().containsKey(monster.getMonsterType())) {
                        myGameUi.getMyDungeonPanel().getMyDefaultMonsters().get(monster.getMonsterType()).setCollision(true);
                        System.out.println("Collided with monster " + monster.getMonsterType() + " at row, col " + monster.getX() + " " + monster.getY());
                        monsterEncountered = monster; // Set the monster encountered
                        monsterEncounterTime = System.currentTimeMillis(); // Set the encounter time
                        myGameUi.getMyDungeonPanel().myMonsters.remove(monster);
                    }
                }
            }
        }
            return isTileSolid;
    }

    public void drawMonsterEncounter(Graphics2D g2) {
        if (monsterEncountered != null && !myGameUi.getMyGameControls().isMyCloseBattleWindow()) {


            int x = myGameUi.getMyDungeonPanel().getMyWidth() / 2;
            int y = myGameUi.getMyDungeonPanel().getMyHeight() / 2 - 200;


            myGameUi.drawFrame(x, y, 350, 400, g2);
            myCloseBattleWindow = new Rectangle(x + 20, y + 20, 20, 20);
            g2.setColor(new Color(255, 255, 255)); // Set the color for the closeWindowRectangle outline
            g2.setStroke(new BasicStroke(5));

            myGameUi.updateCheckboxSelection(g2, myCloseBattleWindow);
            // Set the color to a fully transparent color for the closeWindowRectangle fill
            g2.setColor(new Color(0, 0, 0, 0)); // Transparent black color
            g2.fill(myCloseBattleWindow);
            int textX = x + 60;
            int textY = y + 40;


            g2.setColor(Color.WHITE);
            g2.drawString("You encountered an " + monsterEncountered.getMyMonster().getMyName(), textX , textY);

            double attackOrder = Math.random();
            int beforeBattle = 0;
            int afterBattle = 0;
            if(attackOrder < 0.5){
                myDungeonAdventurer.battle(myGameUi.getMyCharacter().getCharacterType(), monsterEncountered.getMyMonster());
                beforeBattle = (monsterEncountered.getMyMonster().getMyHitPoints());
                afterBattle = (monsterEncountered.getMyMonster().getMyHitPoints());

                if(beforeBattle < afterBattle){
                    g2.drawString("You successfully battled with " + monsterEncountered.getMonsterType() +
                            " you dealt " + (afterBattle - beforeBattle), textX , textY + 30);
                }else{
                    g2.drawString("You unsuccessfully battled with " + monsterEncountered.getMonsterType() +
                            " you dealt " + (beforeBattle - afterBattle), textX , textY + 50);
                }

            }else{
                myDungeonAdventurer.battle(monsterEncountered.getMyMonster(),  myGameUi.getMyCharacter().getCharacterType());
                beforeBattle = ( myGameUi.getMyCharacter().getCharacterType().getMyHitPoints());
                afterBattle = ( myGameUi.getMyCharacter().getCharacterType().getMyHitPoints());

                if(beforeBattle < afterBattle){
                    g2.drawString(monsterEncountered.getMonsterType() + " successfully battled with "  +  myGameUi.getMyCharacter().getCharacterType().getMyName()+
                             monsterEncountered.getMonsterType() + "  dealt " + (afterBattle - beforeBattle), textX , textY + 30);
                }else{
                    g2.drawString(monsterEncountered.getMonsterType() + " unsuccessfully battled with "  +  myGameUi.getMyCharacter().getCharacterType().getMyName()+
                            monsterEncountered.getMonsterType() + "  dealt " + (afterBattle - beforeBattle), textX , textY + 30);
                }

            }
//            Monster currMonster = ;




            //myDungeonAdventurer.battle(myGameUi.getMyCharacter().getCharacterType());

         //   myGameUi.getMyCharacter().getCharacterType().attack(monsterEncountered.getMyMonster());
            //changes.firePropertyChange("battle", myGameUi.);


            myGameUi.getMyCharacter().setMoving(false); // Set the character to frozen state
            myGameUi.getMyCharacter().setPlayerSpeed(0);

            } else {
                monsterEncountered = null;
                monsterEncounterTime = 0;
                if(!gameOver){
                    myGameUi.getMyCharacter().setPlayerSpeed(4);

                }
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

    public boolean isMyWin() {
        return myWin;
    }

    public Rectangle getMyCloseBattleWindow() {
        return myCloseBattleWindow;
    }
}
