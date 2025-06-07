package main;

import object.OBJ_Heart;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {
    BufferedImage heart_full, heart_half, heart_blank;

    public int commamdNum;
    public boolean gameFinish = false;
    BufferedImage keyImage;

    public GamePanel gp;
    Font maruMonica, cambriaz;
    Graphics2D g2;


    public boolean messageOn = false;

    double playTime;

    DecimalFormat dFormat = new DecimalFormat("#0.00");
    // 我们已经不再需要这个时间计算器了


    public String message = "";

    public int messageCounter;
    public String currentDialogue = "";

    public int commandNum = 0;

    public int titleScreenState = 0;


    public UI(GamePanel gp) {
        this.gp = gp;
        // cambriaz = new Font("Cambria", Font.PLAIN, 40);
        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxZorque.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        //CREATE HUB OBJECT 创建血槽（生命值）对象
        SuperObject heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

    }


    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
       /*
       //*******这里是寻宝游戏的界面*********
       if (gameFinish == true) {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            String text;
            int textLength = 0;
            int x;
            int y;

            text = "YOU WIN!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;//屏幕宽度/2-tileSize*2
            y = gp.screenHeight / 2 - (gp.tileSize * 3);//屏幕高度/2-tileSize*3
            g2.drawString(text, x, y);

            text = "you time is :" + dFormat.format(playTime)+ "!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;//屏幕宽度/2-tileSize*2
            y = gp.screenHeight / 2 + (gp.tileSize * 4);//屏幕高度/2-tileSize*3
            g2.drawString(text, x, y);

            g2.setFont(arial_40);
            g2.setColor(Color.yellow);
            text = "Congratulation!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - textLength / 2;
            y = gp.screenHeight / 2 + (gp.tileSize * 3);
            g2.drawString(text, x, y);
            gp.gameThread = null;
        } else {
            g2.setFont(arial_40);
            g2.setColor(Color.white);
            // g2.drawString("key = " +gp.player.hasKey, 25, 50);
            // BufferedImage keyImage = null;
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x = " + gp.player.hasKey, 74, 65);
        *//*if (gp.gameState == gp.titleState) {

            int commandNum = 0;
            drawTitleScreen(commandNum);

        }*//*

            playTime += (double)1/60;
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*11, 64);

            //   g2.drawString("x= " + gp.pl, 74, 65);
            // boolean messageOn = false;
            if (messageOn == true) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

                messageCounter++;

                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
//*******这里是寻宝游戏的界面*********
        }*/
        this.g2 = g2;
        g2.setFont(maruMonica);
        //g2.setFont(cambriaz);//这里可以设置字体
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        if (gp.gameState == gp.titleState) {
            //gp.playMusic(0);！！！把这个方法放在这里音乐会非常cool
            drawTitleScreen();

        }
        if (gp.gameState == gp.playState) {

            drawPlayerLife();

        }
       if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
          drawPauseScreen();
            System.out.println("draw pause screen");

        }
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
            System.out.println("draw dialogue screen");

        }
    }

    public void drawPlayerLife() {

        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

//DRAW BLANK HEARTS
        while (i < gp.player.maxLife / 2) {//这个代码的逻辑是，如果生命值是偶数
            // ，就绘制一个完整的生命值，如果生命值是奇数，就绘制一个完整的生命值和半个生命值
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;

        }
        //reset
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;
        //DRAW CURRENT HEARTS
        while (i < gp.player.life) {//这段代码的逻辑是，
            // 如果生命值是偶数，就绘制一个完整的生命值，如果生命值是奇数，就绘制一个完整的生命值和半个生命值

            g2.drawImage(heart_half, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }

    }



    public void drawTitleScreen() {

        if (titleScreenState == 0) {

            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 86F));
            String text = "Weiqi Adventure";


            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;

            g2.setColor(Color.gray);
            g2.drawString(text, x + 5, y + 5);
            //MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
            y += gp.tileSize * 2;

            g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
//  COMMAND
            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3;
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
        } else if (titleScreenState == 1) {
//*************TITLE_SCREEN_2****************
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(48F));

            String text = "Select you job!!!";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Fighter";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "Thief";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "Sorcerer";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
            text = "Back";
            x = getXforCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        }
    }

    public void drawDialogueScreen() {
        //  DIALOGUE WINDOW
        System.out.println("call draw dialogue screen command");
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 2);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);//绘制对话框

        //DRAW DIALOGUE TEXT
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize + 10;

        for (String line : currentDialogue.split("\n")) {//遍历文本
            g2.drawString(line, x, y);
            y += 40;

        }

    }

    public void drawSubWindow(int x, int y, int width, int height) {//绘制

        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);//绘制对话框

        //设置对话边框
        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);


    }


    //*************TITLE_SCREEN_2****************
    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

    }

    public int getXforCenteredText(String text) {

        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;

        return x;
    }
}
