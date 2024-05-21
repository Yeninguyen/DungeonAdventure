package Controller;

import java.awt.image.BufferedImage;

public class Tile {
    private boolean mySolid;
    private BufferedImage myTileImage;

    public boolean isSolid() {
        return mySolid;
    }

    public BufferedImage getMyTileImage() {
        return myTileImage;
    }
}
