package Entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player extends Entity {
    //either

    GamePanel gp;
    KeyHandler keyH;

  //  public final int screenX;
    //public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
       // screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        //screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        setDaultValues();
        getPlayerImage();
    }

    public void setDaultValues() {
//        worldX = gp.tileSize*23;
        worldX = 100;
//        worldY = gp.tileSize*21;
                worldY = 100;
        x = gp.tileSize * 23;
        y = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = loadImage("/player/boy_up_1.png");
            up2 = loadImage("/player/boy_up_2.png");
            down1 = loadImage("/player/boy_down_1.png");
            down2 = loadImage("/player/boy_down_2.png");
            left1 = loadImage("/player/boy_left_1.png");
            left2 = loadImage("/player/boy_left_2.png");
            right1 = loadImage("/player/boy_right_1.png");
            right2 = loadImage("/player/boy_right_2.png");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private BufferedImage loadImage(String path) throws IOException {
        InputStream is = getClass().getResourceAsStream(path);
        if (is == null) {
            throw new IOException("无法找到资源文件: " + path);
        }
        return ImageIO.read(is);
    }

    public void update() {
        if (keyH.upPressed == true || keyH.downPressed == true
                || keyH.leftPressed == true || keyH.rightPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";
                y -= speed;
            } else if (keyH.downPressed == true) {
                direction = "down";
                y += speed;
            } else if (keyH.leftPressed == true) {
                direction = "left";
                x -= speed;
            } else if (keyH.rightPressed == true) {
                direction = "right";
                x += speed;
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;

            }

        }
    }

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
        g2.drawImage(image, x, y ,gp.tileSize, gp.tileSize, null);

    }

}