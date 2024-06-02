package Controller;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lighting {
    private BufferedImage darknessFilter;
    private GameUI gameUI;
    private int count;
    private float filterAlpha = 0f;

    private final int dawn = 0;
    private final int day = 1;
    private final int dusk = 2;
    private final int night = 3;

    private int currentPhase = dawn;
    private int transitionDuration = 600; // Duration of each transition phase in frames

    public Lighting(GameUI theGameUI, int theCircleSize) {
        this.gameUI = theGameUI;
        setGameLight(theCircleSize);
    }

    private void setGameLight(int theCircleSize) {
        // Buffer Image
        darknessFilter = new BufferedImage(gameUI.getMyDungeonPanel().getMyWidth(), gameUI.getMyDungeonPanel().getMyHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = darknessFilter.createGraphics();

        // Center X and Y of the light circle
        int centerX = gameUI.getMyCharacter().getScreenX() + gameUI.getMyDungeonPanel().getMyTileSize() / 2;
        int centerY = gameUI.getMyCharacter().getScreenY() + gameUI.getMyDungeonPanel().getMyTileSize() / 2;

        // Define colors and fractions for the radial gradient
        Color[] colors = {new Color(0, 0, 0, 0f),
                new Color(0, 0, 0, 0.25f),
                new Color(0, 0, 0, 0.5f),
                new Color(0, 0, 0, 0.75f),
                new Color(0, 0, 0, 0.98f)};
        float[] fractions = {0f, 0.25f, 0.5f, 0.75f, 1f};

        // Create and apply the radial gradient paint
        RadialGradientPaint gradientPaint = new RadialGradientPaint(centerX, centerY, theCircleSize / 2, fractions, colors);
        g2d.setPaint(gradientPaint);
        g2d.fillRect(0, 0, gameUI.getMyDungeonPanel().getMyWidth(), gameUI.getMyDungeonPanel().getMyHeight());
        g2d.dispose();
    }

    public void update() {
        count++;

        // Check if transition phase is complete
        if (count > transitionDuration) {
            count = 0;
            switch (currentPhase) {
                case day:
                    currentPhase = dusk;
                    break;
//                case dusk:
//                    currentPhase = night;
//                    break;
//                case night:
//                    currentPhase = dawn;
//                    break;
                case dawn:
                    currentPhase = day;
                    break;
            }
        }

        // Update filter alpha based on current phase
        switch (currentPhase) {
            case day:
                filterAlpha = 0f;
                break;
//            case dusk:
//                filterAlpha = (float) count / transitionDuration * 0.25f;
//                break;
//            case night:
//                filterAlpha = 0.25f + (float) count / transitionDuration * 0.5f;
//                break;
            case dawn:
                filterAlpha = 0.75f + (float) count / transitionDuration * 0.25f;
                break;
        }
    }

    public void draw(Graphics2D theG2d) {
        // Apply darkness filter
        theG2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
        theG2d.drawImage(darknessFilter, 0, 0, null);
        theG2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // Draw time of day indicator
        String timeOfDay = "";
        switch (currentPhase) {
            case day:
                timeOfDay = "Day";
                break;
//            case dusk:
//                timeOfDay = "Dusk";
//                break;
            case dawn:
                timeOfDay = "Dawn";
                break;
//            case night:
//                timeOfDay = "Night";
//                break;
        }
        theG2d.setColor(Color.WHITE);
        theG2d.setFont(theG2d.getFont().deriveFont(30f));
        theG2d.drawString(timeOfDay, 850, 700);
    }
}