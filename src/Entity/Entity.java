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
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);//这是所有实体的默认矩形
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String dialogues[] = new String[20];
    public int worldX, worldY;//这个是world01的绝对坐标

    public String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    boolean attacking = false;

    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;//这个是hp条的开关

    public int spriteCounter = 0;
    public int actionLockCounter = 0; //  这个是实体动作计数器，用来控制实体的动作
    public int invincibleCounter = 0;
    //STATE
    int dyingCounter = 0;

    int hpBarOnCounter = 0;//这个是hp条的开关


    public String name;
    public int type; // 0 是player 1 = npc 2 = monster
    public int maxLife;
    public int life;
    public int speed;
    public int hpBarCounter;

    // public Object solidArea;
    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {

    }
public void damageReaction() {

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
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);//这段代码的意思是
        // 当实体与玩家发生碰撞时，如果玩家没有被保护，则玩家会损失生命值
        if (this.type == 2 && contactPlayer == true) {//这段代码的意思是当实体与玩家发生碰撞时
            // ，如果玩家没有被保护，则玩家会损失生命值
            if (gp.player.invincible == false) {
                gp.playSE(6);
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }

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
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
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
            //Monster HP bar
            if (type == 2  && hpBarOn == true){//绘制Monster HP bar,当type等于2时才绘制

                double oneScale = (double) gp.tileSize / maxLife;//计算一个比例
                double hpBarValue = oneScale * life;//计算Monster HP bar的值

                g2.setColor(new Color(35,35,35));
                g2.fillRect(screenX, screenY - 16, gp.tileSize+2, 12);
                g2.setColor(new Color(255,0,30));
                g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);
                //这段代码是绘制Monster HP bar的代码

                hpBarCounter++;
                if (hpBarCounter > 600){
                    hpBarCounter =0;
                    hpBarOn = false;
                }
            }
            if (invincible == true) {//如果玩家处于被保护状态，则绘制Monster HP bar
                hpBarOn = true;
                hpBarCounter = 0;
                    changeAlpha(g2,0.4F);
                   }
            if (dying == true) {
                dyingAnimation(g2);
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

             changeAlpha(g2,1F);

        }
    }

    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;
        int i = 5;
        if (dyingCounter <= i) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i && dyingCounter <= i * 2) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 2 && dyingCounter <= i * 3) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 3 && dyingCounter <= i * 4) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 4 && dyingCounter <= i * 5) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 5 && dyingCounter <= i * 6) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 6 && dyingCounter <= i * 7) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 7 && dyingCounter <= i * 8) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 8) {

            dying = false;
            alive = false;

        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath, int width, int height) {//这个方法用来获取图片
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);//这段代码是用来获取图片的，并且缩放到指定的大小。
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
