package Controller;

import Model.Dungeon;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TileManager {


    public int entranceRow;
    public int entranceCol;

    public int pillarPRow;
    public int pillarPCol;

    private int[] myPillarACoordinates;
    private int[] myPillarPCoordinates;
    private int[] myPillarICoordinates;
    private int[] myPillarECoordinates;
    private int[] myHealthPotionCoordinates;
    private int[] myVisionPotionCoordinates;
    private ArrayList<Integer> myVisionPotionCoordinatesList;
    private ArrayList<Integer> myHealthPotionCoordinatesList;
    private ArrayList<Integer> myOgreCoordinatesList;
    private ArrayList<Integer> myGremlinCoordinatesList;
    private ArrayList<Integer> mySkeletonCoordinatesList;
    private int[] myMultipleCoordinates;

    private Map<String, Integer> myItemCollisionFrequency;


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
    private final int myRow = Model.Dungeon.getInstance().getSIZE() * 5;
    private final int myCol = Model.Dungeon.getInstance().getSIZE() * 5 ;
    GameUI myGameUi;
    private Map<String, Tile> myTile;

    private final String[][] mapTileNums;

    public TileManager(final GameUI theGameUi) {
        myGameUi = theGameUi;
      //  myTiles = new Tile[3];
        myTile = new HashMap<>();
       // mapTileNum = new int[row][col];
        generateDungeon();
        myVisionPotionCoordinatesList = new ArrayList<>();
        myHealthPotionCoordinatesList = new ArrayList<>();
        myOgreCoordinatesList = new ArrayList<>();
        myGremlinCoordinatesList = new ArrayList<>();
        mySkeletonCoordinatesList = new ArrayList<>();
        myItemCollisionFrequency = new HashMap<>();
        mapTileNums = new String[myRow][myCol];
        getTileImage();
        loadMap();
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
    }


    public void generateDungeon(){
        Dungeon dungeon = Model.Dungeon.getInstance();
        String path = "Maze.txt";
        dungeon.writeMazeToFile(path);
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
                    mapTileNums[row][col] = s;
                }

            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public boolean isTileCollision(Rectangle hitbox) {
        int x1 = hitbox.x / myGameUi.getMyDungeonPanel().getMyTileSize();
        int y1 = hitbox.y / myGameUi.getMyDungeonPanel().getMyTileSize();;
        int x2 = (hitbox.x + hitbox.width) / myGameUi.getMyDungeonPanel().getMyTileSize();
        int y2 = (hitbox.y + hitbox.height) / myGameUi.getMyDungeonPanel().getMyTileSize();
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
            if (item.solidArea.intersects(hitbox)) {
                // Handle collision with the item
                // You can perform actions based on the item's name or type

                System.out.println("Collision with item: " + item.getName());
                if(!item.getName().equals("M")) {
                    myGameUi.getMyDungeonPanel().myDefaultItems.get(item.getName()).setCollision(true);
                    myItemCollisionFrequency.put(item.getName(), myItemCollisionFrequency.getOrDefault(item.getName(), 0) + 1);
                }

                myGameUi.getMyDungeonPanel().myItems.remove(item);

            }
        }
            return isTileSolid;
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

    public int[] getMyHealthPotionCoordinates() {
        return myHealthPotionCoordinates;
    }

    public int[] getMyVisionPotionCoordinates() {
        return myVisionPotionCoordinates;
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

    public ArrayList<Integer> getMyOgreCoordinatesList() {
        return myOgreCoordinatesList;
    }

    public Map<String, Integer> getMyItemCollisionFrequency() {
        return myItemCollisionFrequency;
    }

    public ArrayList<Integer> getMySkeletonCoordinatesList() {
        return mySkeletonCoordinatesList;
    }

    public ArrayList<Integer> getMyGremlinCoordinatesList() {
        return myGremlinCoordinatesList;
    }
}
