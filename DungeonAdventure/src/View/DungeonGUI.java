package View;

import javax.swing.*;



public class DungeonGUI extends JFrame {
    public DungeonGUI(){
        setTitle("DungeonAdventure");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        DungeonPanel dungeonPanel = new DungeonPanel();
        add(dungeonPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
