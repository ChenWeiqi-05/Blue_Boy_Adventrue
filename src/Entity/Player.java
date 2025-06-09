package Entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    // public int hashKey = 0;
    //either

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    // public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.width = 32;
        solidArea.height = 32;
        setDaultValues();
        getPlayerImage();
    }

    public void setDaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;

//        worldX = gp.tileSize * 10;
//
//        worldY = gp.tileSize * 13;

        speed = 4;
        direction = "down";
// PLAYER STATUS
        maxLife = 6;
        life = maxLife;

    }

    public void getPlayerImage() {
        up1 = setup("/player/boy_up_1");
        up2 = setup("/player/boy_up_2");
        down1 = setup("/player/boy_down_1");
        down2 = setup("/player/boy_down_2");
        left1 = setup("/player/boy_left_1");
        left2 = setup("/player/boy_left_2");
        right1 = setup("/player/boy_right_1");
        right2 = setup("/player/boy_right_2");

    }

  /*  private BufferedImage loadImage(String path) throws IOException {
        InputStream is = getClass().getResourceAsStream(path);
        if (is == null) {
            throw new IOException("无法找到资源文件: " + path);
        }
        return ImageIO.read(is);
    }*/

    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);//这段代码是用来获取图片的，并且缩放到指定的大小。

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";
                System.out.println("向上");
                //  worldY -= speed; // 添加向上移动的逻辑
            } else if (keyH.downPressed == true) {
                direction = "down";
                System.out.println("向下");
                // worldY += speed; // 添加向下移动的逻辑
            } else if (keyH.leftPressed == true) {
                direction = "left";
                System.out.println("向左");
                //worldX -= speed; // 添加向左移动的逻辑
            } else if (keyH.rightPressed == true) {
                direction = "right";
                System.out.println("向右");
                //worldX += speed;
            }
            //这段代码用来检测obj是否发生碰撞
            collisionOn = false;
            gp.cChecker.checkTile(this);
            //这段代码用于检测obj是否发生碰撞
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            interactMonster(monsterIndex);

contactMonster(monsterIndex);
            //  CHECK COLLISION
            gp.eHandler.checkEvent();
            gp.keyH.enterPressed = false;//这段代码
            if (collisionOn == false) {
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
            //  gp.cChecker.checkTile(this);
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
    }

    public void contactMonster(int i) {//这段代码的意思是

        if (i != 999) {

            if (invincible == false){
                life -= 1;
                invincible = true;
            }

        }
    }

    public void interactMonster(int monsterIndex) {

    }

    public void pickUpObject(int i) {//这个方法可改变捡起道具后的数值属性

        if (i != 999) {//一下文本注释为寻宝小游戏的拾起逻辑的代码
            /*//999代表没有物体，并且0代表没有物体
         //   gp.obj[i].name = null;
            String objectName = gp.obj[i].name ;

            switch (objectName){
                case "Key"://注意此处单词首字母要大写！！！
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i]=null;
                    System.out.println("key：" +hasKey);
                    gp.ui.showMessage("You got "+hasKey+" key!"+"also have "+ (3 - hasKey)+ " key,should be found");
                    break;
                case "Door"://注意此处单词首字母要大写！！！
                    gp.playSE(3);
                    if (hasKey >0){
                        gp.obj[i] = null;
                        hasKey--;

                           gp.ui.showMessage("You open a door! ");

                    }else {
                        gp.ui.showMessage("You need a key!");
                    }
                    System.out.println("key：" +hasKey);
                    break;
                case "Boots":
                    gp.playSE(1);
                    speed +=3;
                    gp.obj[i] = null;
                    gp.ui.showMessage("speed up!GoGoGo！");
                    break;
                    case "Chest":
                        gp.ui.gameFinish = true;
                        gp.stopMusic();
                        gp.playSE(4);
                        break;
            }*/

        }
    }

    public void interactNPC(int i) {//这个方法可改变与npc的交互逻辑
        if (i != 999) {

            //这段代码enter  键被按下时，会触发npc的speak方法，并进入对话状态。
            if (gp.keyH.enterPressed == true) {

                System.out.println("you are hitting an npc");
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }

    /*    public void pickupObject(int i) {

             if (i != 999) {
             String objectName = gp.obj[i].name;
             switch (objectName) {
                 case "key":
                     hasKey++;
                     gp.obj[i] = null;
                     System.out.println("You have " + hasKey + " key(s)");
                     break;
                     case "Chest":
                         break;
                 case "Door":
                     if (hasKey > 0) {
                         gp.obj[i] = null;
                         hasKey--;
                         System.out.println("You have " + hasKey + " key(s)");

                     }
                     break;
             }
             }
         }*/
    public void draw(Graphics2D g2) {
//            g2.setColor(Color.white);
////
//  g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
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
        // g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}