package environment;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lighting {
    GamePanel gp;
    BufferedImage darknessFilter;

   public int dayCounter;
  public  float filterAlpha = 0f;
    public  final int day = 0;
    public final int dusk = 1;//黄昏
    public final int night = 2;
    public  final int dawn = 3;//黎明

    public int dayState = day;

    public Lighting(GamePanel gp) {

        this.gp = gp;
        setLightSource();

    }

    public void setLightSource() {
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();

        //  Area screenArea = new Area(new Rectangle2D.Double(0, 0, gp.screenWidth, gp.screenHeight));
//
        if (gp.player.currentLight == null) {
            g2.setColor(new Color(0, 0, 0.1f, 0.98f));
        } else {
            int centerX = gp.player.screenX + (gp.tileSize) / 2;
            int centerY = gp.player.screenY + (gp.tileSize) / 2;
        /*double x = centerX - (circleSize / 2);
        double y = centerY - (circleSize / 2);
//Create a light circle shape
        Shape circleShape = new Ellipse2D.Double(x, y, circleSize, circleSize);

        Area lightArea = new Area(circleShape);//减去圆形

        screenArea.subtract(lightArea);//
*/
            Color color[] = new Color[12];
            float fraction[] = new float[12];
//颜色的等级
            color[0] = new Color(0, 0, 0.1f, 0.1f);
            color[1] = new Color(0, 0, 0.1f, 0.42f);
            color[2] = new Color(0, 0, 0.1f, 0.52f);
            color[3] = new Color(0, 0, 0.1f, 0.61f);
            color[4] = new Color(0, 0, 0.1f, 0.69f);
            color[5] = new Color(0, 0, 0.1f, 0.76f);
            color[6] = new Color(0, 0, 0.1f, 0.82f);
            color[7] = new Color(0, 0, 0.1f, 0.87f);
            color[8] = new Color(0, 0, 0.1f, 0.92f);
            color[9] = new Color(0, 0, 0.1f, 0.96f);
            color[10] = new Color(0, 0, 0.1f, 0.98f);
            color[11] = new Color(0, 0, 0.1f, 0.98f);


//亮度的等级
            fraction[0] = 0f;
            fraction[1] = 0.4f;
            fraction[2] = 0.5f;
            fraction[3] = 0.6f;
            fraction[4] = 0.65f;
            fraction[5] = 0.7f;
            fraction[6] = 0.75f;
            fraction[7] = 0.80f;
            fraction[8] = 0.85f;
            fraction[9] = 0.9f;
            fraction[10] = 0.95f;
            fraction[11] = 1f;
//创建一个径向渐变
            RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, gp.player.currentLight.lightRadius, fraction, color);
//填充
            g2.setPaint(gPaint);
        }

      /*  //填充saw
        g2.fill(lightArea);
        //  g2.setColor(new Color(0, 0, 0, 0.95f));

        g2.fill(screenArea);
*/
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.dispose();
    }

    public void update() {

        if (gp.player.lightUpdated == true) {
            setLightSource();
            gp.player.lightUpdated = false;
        }
        if (dayState == day) {
            dayCounter++;
            if (dayCounter > 1200) {
                dayState = dusk;
                dayCounter = 0;
            }
        }
        if (dayState == dusk) {
            filterAlpha += 0.001f;

            if (filterAlpha > 1f) {

                filterAlpha = 1f;
                dayState = night;
            }
        }
            if (dayState == night) {
                dayCounter++;
                if (dayCounter > 1200) {
                    dayState = dawn;
                    dayCounter = 0;
                }
            }

            if (dayState == dawn) {

                filterAlpha -= 0.001f;
                if (filterAlpha < 0f) {
                    filterAlpha = 0;
                    dayState = day;
                }
            }
    }

    public void draw(Graphics2D g2) {


        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
        g2.drawImage(darknessFilter, 0, 0, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        String situation = "";
        switch (dayState) {
            case day:
                situation = "day";
                break;
            case dusk:
                situation = "dusk";
                break;
            case night:
                situation = "night";
                break;
            case dawn:
                situation = "dawn";
                break;
        }
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(50f));
        g2.drawString(situation, 800, 500);
    }


}
