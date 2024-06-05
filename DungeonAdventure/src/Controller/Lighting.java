package Controller;

import View.DungeonPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lighting {
    private long myVisionTimer;
    private final long myVisionDuration = 10000;
    private BufferedImage darknessFilter;
    private final GameUI gameUI;
    private float filterAlpha = 1f; // Default to 1 (fully opaque) if no potion is used
    private final int dawn = 0;
    private final int day = 1;
    private double myScale = 1;
    private boolean myVisionPotionUsed;
    private boolean myHealthPotionUsed;

    public Lighting(GameUI theGameUI) {
        this.gameUI = theGameUI;
    }

    public void setGameLight(int theCircleSize) {
        // Buffer Image
        darknessFilter = new BufferedImage(gameUI.getMyDungeonPanel().getMyWidth(), gameUI.getMyDungeonPanel().getMyHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = darknessFilter.createGraphics();

        // Center X and Y of the light circle
        int centerX = gameUI.getMyCharacter().getScreenX() + gameUI.getMyDungeonPanel().getMyTileSize() / 2;
        int centerY = gameUI.getMyCharacter().getScreenY() + gameUI.getMyDungeonPanel().getMyTileSize() / 2;

        // Define colors and fractions for the radial gradient
        Color[] colors = {
                new Color(0, 0, 0, 0f), // Transparent at the center
                new Color(0, 0, 0, 0.25f), // Semi-transparent black
                new Color(0, 0, 0, 0.5f), // More opaque black
                new Color(0, 0, 0, 0.75f), // Nearly opaque black
                new Color(0, 0, 0, 1f)}; // Fully opaque black
        float[] fractions = {0f, 0.25f, 0.5f, 0.75f, 1f};

        // Create and apply the radial gradient paint
        int radius = (int) (theCircleSize * myScale); // Adjust the radius here
        RadialGradientPaint gradientPaint = new RadialGradientPaint(centerX, centerY, radius, fractions, colors);
        g2d.setPaint(gradientPaint);
        g2d.fillRect(0, 0, gameUI.getMyDungeonPanel().getMyWidth(), gameUI.getMyDungeonPanel().getMyHeight());
        g2d.dispose();
    }

    public void update() {
        filterAlpha = 1f;
        if (myVisionPotionUsed) {
              filterAlpha = 0f;
            myScale = 2;
            if (System.currentTimeMillis() - myVisionTimer >= myVisionDuration) {
                myVisionPotionUsed = false;
                filterAlpha = 1f;
                myScale = 1.5; // Reset the scale to 1 when the vision potion expires
            }
        }
        else {
            myScale = 1.5;

        }
    }



    public void draw(Graphics2D theG2d) {


        setGameLight((int) (64 * myScale));
        // Apply darkness filter
       theG2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
        theG2d.drawImage(darknessFilter, 0, 0, null);
        theG2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // Draw time of day indicator
        String timeOfDay = "Dawn";
        theG2d.setColor(Color.WHITE);
        theG2d.setFont(theG2d.getFont().deriveFont(30f));
        theG2d.drawString(timeOfDay, 850, 700);

        if (gameUI.getMyGameControls().isMyInventorySelected()) {

            if(!gameUI.getMyGameControls().closeWindow()) {
                gameUI.drawInventory(theG2d);
            }
        }
        gameUI.drawInventoryRectangle(theG2d);


        gameUI.getMyTileManager().drawMonsterEncounter(theG2d);


    }
    public boolean isMyVisionPotionUsed() {
        return myVisionPotionUsed;
    }

    public void setMyVisionPotionUsed(boolean myVisionPotionUsed) {
        this.myVisionPotionUsed = myVisionPotionUsed;
    }

    public long getMyVisionTimer() {
        return myVisionTimer;
    }

    public void setMyVisionTimer(long myVisionTimer) {
        this.myVisionTimer = myVisionTimer;
    }

    public double getMyScale() {
        return  myScale;
    }

    public void setMyScale(int myScale) {
        this.myScale = myScale;
    }

    public void setMyHealthPotionUsed(boolean theHealthPotionUsed) {
        myHealthPotionUsed = theHealthPotionUsed;
    }
}
