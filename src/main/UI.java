package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    public int commamdNum;
    BufferedImage keyImage;
    public GamePanel gp;
    Font arial_40;
    private Graphics2D g2;
    public int titleScreenState = 0;
    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);

        OBJ_Key key = new OBJ_Key();

        keyImage = key.image;


    }

    public void showMessage(String text) {

        String message = text;

        boolean messageOn = true;


    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        BufferedImage keyImage = null;
        g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);

        /*if (gp.gameState == gp.titleState) {

            int commandNum = 0;
            drawTitleScreen(commandNum);

        }*/
        g2.drawString("x= " + gp.player.hashKey, 74, 65);

        boolean messageOn = false;
        if (messageOn == true) {
            g2.setFont(g2.getFont().deriveFont(30F));
            String message = "You found a key!";
            g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
            int messageCounter = 0;
            messageCounter++;
            if (messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            }

        }


    }
   public void drawTitleScreen(int commandNum) {

        if (titleScreenState == 0) {
            g2.setColor(new Color(0, 0, 0));

            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));

            String text = "Adventure Game";

            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;

            g2.setColor(Color.gray);
            g2.drawString(text, x + 5, y + 5);


            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            x = gp.screenWidth / 2;
            y += gp.tileSize * 2;

            g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
            //menu
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3.5;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "QUIT";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize * 4;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        } else if (titleScreenState == 1) {

            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            String text = "请选择你的角色!";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "近卫";
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "法师";
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "医疗";
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "特种";
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
    }
    private int getXforCenteredText(String text) {

        return 0;

    }
}
