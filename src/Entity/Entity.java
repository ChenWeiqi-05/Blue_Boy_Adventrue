package Entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    GamePanel gp;
    // public int worldX, worldY;
    public int worldX, worldY;//这个是world01的绝对坐标
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);//这是所有实体的默认矩形
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0; //  这个是实体动作计数器，用来控制实体的动作

    String dialogues[] = new String[20];
    int dialogueIndex = 0;


    // public Object solidArea;
    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {

    }

    public void speak() {
        if (dialogues[dialogueIndex] == null) {//防止dialogues数组的空指针异常
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
//本段代码实现让npc对话时面向玩家
        switch (gp.player.direction) {

            case "up":
                direction = "down";
                break;

            case "down":
                direction = "up";
                break;

            case "left":
                direction = "right";
                break;

            case "right":
                direction = "left";
                break;

        }
    }

    ///这个是更新实体的代码，用来控制实体的移动
    public void update() {
        setAction();//这个是设置实体的动作的代码，用来控制实体的移动
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);
        if (collisionOn == false) {//检测实体是否发生碰撞
            switch (direction) {
                case "up":
                    //  System.out.println("向上碰撞");
                    worldY -= speed;
                    break;
                case "down":
                    //System.out.println("向下碰撞");
                    worldY += speed;
                    break;
                case "left":
                    //System.out.println("向左碰撞");
                    worldX -= speed;
                    break;
                case "right":
                    // System.out.println("向右碰撞");
                    worldX += speed;
                    break;
            }
        }
        gp.cChecker.checkTile(this);
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&//这是一段优化代码，用来判断屏幕是否在屏幕范围内
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&//
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
        ) {
            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;

                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }

                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }

                    break;
                case "left":
                    if (spriteNum == 1) {

                        image = left1;
                    }
                    if (spriteNum == 2) {

                        image = left2;
                    }

                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    break;
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }

    }

    public BufferedImage setup(String imagePath) {//这个方法用来获取图片
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {

            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);//这段代码是用来获取图片的，并且缩放到指定的大小。

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
