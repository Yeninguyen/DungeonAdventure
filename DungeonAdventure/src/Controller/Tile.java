package Controller;

import java.awt.image.BufferedImage;

public class Tile {


    private static Tile myInstance;

    public static Tile getMyInstance() {
        if(myInstance == null){
            myInstance = new Tile();
        }
        return myInstance;
    }
    private boolean mySolid = false;
    private BufferedImage myTileImage;

    public boolean isSolid() {
        return mySolid;
    }

    public BufferedImage getMyTileImage() {
        return myTileImage;
    }

    public void setMySolid(boolean mySolid) {
        this.mySolid = mySolid;
    }



    public void setMyTileImage(BufferedImage myTileImage) {
        this.myTileImage = myTileImage;
    }
}
