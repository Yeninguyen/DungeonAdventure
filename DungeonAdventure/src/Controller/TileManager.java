package Controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TileManager {
    GameUI myGameUi;
    private Map<String, BufferedImage> myTiles;

    private String[][] mapTileNum;

    public TileManager(final GameUI theGameUi) {
        myGameUi = theGameUi;
        mapTileNum = new String[24][48];
        getTileImage();
        loadMap();
    }


    public void getTileImage(){
        try{
            myTiles = new HashMap<>();
            myTiles.put("*", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Tiles/wall.png"))));
            myTiles.put("-", ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/Images/Tiles/earth.png"))));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void drawTiles(Graphics2D theGraphics){
        int row = 0;
        int col = 0;

        while (row < 24 && col < 48) {

            String tileNum = mapTileNum[row][col]; // Corrected the indices

            int x = col * myGameUi.getMyDungeonPanel().getMyTileSize();
            int y = row * myGameUi.getMyDungeonPanel().getMyTileSize();

            int screenX = x - myGameUi.getMyCharacter().getMyWarrior().getMyX() + myGameUi.getMyCharacter().getScreenX();
            int screenY = y - myGameUi.getMyCharacter().getMyWarrior().getMyY() + myGameUi.getMyCharacter().getScreenY();

            if(x + 64 > myGameUi.getMyCharacter().getMyWarrior().getMyX() - myGameUi.getMyCharacter().getScreenX() &&
                    x  - 64 < myGameUi.getMyCharacter().getMyWarrior().getMyX() + myGameUi.getMyCharacter().getScreenX() &&
                    y  + 64> myGameUi.getMyCharacter().getMyWarrior().getMyY() - myGameUi.getMyCharacter().getScreenY() &&
                    y  - 64< myGameUi.getMyCharacter().getMyWarrior().getMyY() + myGameUi.getMyCharacter().getScreenY()){
                theGraphics.drawImage(myTiles.get(tileNum), screenX, screenY, myGameUi.getMyDungeonPanel().getMyTileSize(), myGameUi.getMyDungeonPanel().getMyTileSize(), null);

            }
            row++;
            if(row == 24){
                row = 0;
                col++;
            }
        }
    }

    public void loadMap(){
        try {
            InputStream is = getClass().getResourceAsStream("/Images/maps/map.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            for(int row = 0; row < 24; row++) {
                String line = reader.readLine();
                for(int col = 0; col < 48; col++) {
                    String[] numbers = line.split(" ");
                    mapTileNum[row][col] = numbers[col];
                }
            }
            System.out.println(mapTileNum.length);
            reader.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

}