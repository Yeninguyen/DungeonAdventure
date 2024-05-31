package Controller;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Items extends SuperItems {

    public Items() {
        name = "P";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Images/Objects/PillarP.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
