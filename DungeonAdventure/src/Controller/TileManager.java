package Controller;

import Model.Dungeon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TileManager {


    public int pillarARow;
    public int pillarACol;
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
    private final int row = Model.Dungeon.getInstance().getSIZE() * 5;
    private final int col = Model.Dungeon.getInstance().getSIZE() * 5 ;
    GameUI myGameUi;
    private Map<String, Tile> myTile;

    private final String[][] mapTileNums;

    public TileManager(final GameUI theGameUi) {
        myGameUi = theGameUi;
        //  myTiles = new Tile[3];
        myTile = new HashMap<>();
        // mapTileNum = new int[row][col];
        mapTileNums = new String[row][col];
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

//            Tile pillarP = new Tile();
//            pillarP.setMyTileImage((ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Objects/PillarP.png")))));

            myTile.put("*", rock);
            myTile.put("-", earth);
            myTile.put("D", door);
            //myTile.put("P", pillarP);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void drawTiles(Graphics2D theGraphics){
        int row = 0;
        int col = 0;


        while (row < this.row && col < this.col) {
            String title = mapTileNums[row][col];

            switch (title) {
                case myHealthPotion, myVisionPotion, myEntrance, myPillarP, myPillarI,
                     myPillarE, myExit, myMultiple, myPit, myEmpty, myPillarA -> title = "-";
            }

            int bound = myGameUi.getMyDungeonPanel().getMyTileSize();

            int x = col * myGameUi.getMyDungeonPanel().getMyTileSize();
            int y = row * myGameUi.getMyDungeonPanel().getMyTileSize();

            int screenX = x - myGameUi.getMyCharacter().getMyWarrior().getMyX() + myGameUi.getMyCharacter().getScreenX();
            int screenY = y - myGameUi.getMyCharacter().getMyWarrior().getMyY() + myGameUi.getMyCharacter().getScreenY();

            if(x + bound > myGameUi.getMyCharacter().getMyWarrior().getMyX() - myGameUi.getMyCharacter().getScreenX() &&
                    x  - bound < myGameUi.getMyCharacter().getMyWarrior().getMyX() + myGameUi.getMyCharacter().getScreenX() &&
                    y  + bound> myGameUi.getMyCharacter().getMyWarrior().getMyY() - myGameUi.getMyCharacter().getScreenY() &&
                    y  - bound< myGameUi.getMyCharacter().getMyWarrior().getMyY() + myGameUi.getMyCharacter().getScreenY()){
                theGraphics.drawImage(myTile.get(title).getMyTileImage(), screenX, screenY, myGameUi.getMyDungeonPanel().getMyTileSize(), myGameUi.getMyDungeonPanel().getMyTileSize(), null);
            }
            row++;
            if(row == this.row){
                row = 0;
                col++;
            }
        }
    }

    public void loadMap(){
        try {
            Model.Dungeon.getInstance();
            InputStream is = getClass().getResourceAsStream("/Maps/Maze.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            for(int row = 0; row < this.row; row++) {
                String line = reader.readLine();
                for(int col = 0; col < this.col; col++) {
                    String[] numbers = line.split(" ");
                    String s = numbers[col];
                    if(s.equals("A")){
                       pillarARow = row;
                       pillarACol = col;
                    }
                    mapTileNums[row][col] = s;
                }
            }
            reader.close();
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
        return isTileSolid;

    }



}