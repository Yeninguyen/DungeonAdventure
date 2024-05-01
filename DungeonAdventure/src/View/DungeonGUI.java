package View;

import javax.swing.*;
import java.awt.*;

public class DungeonGUI extends JFrame {

    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    public DungeonGUI() {
        setTitle("DungeonAdventure");
//        setVisible(true);
//        setSize(600, 500);
//        setLocation(SCREEN_SIZE.width / 2 - getWidth() / 2,
//                SCREEN_SIZE.height / 2 - getHeight() / 2);
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setResizable(false);
        //setupGUI();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        DungeonPanel dungeonPanel = new DungeonPanel();
        add(dungeonPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }
}
