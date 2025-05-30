package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    // public int worldX, worldY;
    public int worldX, worldY;//这个是world01的绝对坐标
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;

    public boolean collisionOn = false;

   // public Object solidArea;
}
