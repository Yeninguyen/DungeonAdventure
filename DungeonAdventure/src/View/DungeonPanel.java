package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DungeonPanel extends JPanel implements Runnable{
    //Screen Setting
    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    public final int titleState = 0;

    // GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;


    Thread gameThread;

    public DungeonPanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        startGameThread();
        gameState = playState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        while(gameThread != null){
            System.out.println("Running");
        }
    }

    public void update(){
        if(gameState == playState){
            // player update
        }

        if(gameState == titleState){

        }
        if(gameState == pauseState){
            // pause for later.
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        drawTitleScreen(graphics2D);
    }

    public void drawTitleScreen(Graphics2D g2) {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60F));
        String title = "DUNGEON ADVENTURE";
        int x = tileSize;
        int y = tileSize * 2;

        g2.setColor(Color.gray);
        g2.drawString(title, x + 5, y + 5);
        g2.setColor(Color.WHITE);
        g2.drawString(title, x, y);

        createButton("NEW GAME", tileSize * 5);
        createButton( "LOAD GAME", tileSize * 7);
        createButton("QUIT GAME", tileSize * 9);
    }

    private void createButton(String text, int y) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBounds(tileSize * 6, y, 192, tileSize);
        this.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if(text.equals("NEW GAME")){
                   gameState = playState;
               }
               else if(text.equals("LOAD GAME")){

               }
               else{
                   System.exit(0);
               }
            }
        });
        add(button);
    }


    public static void main(final String... theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DungeonGUI();
            }
        });
    }
}
