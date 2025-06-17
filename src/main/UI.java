package main;

import Entity.Entity;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class UI {
    BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank;//创建血槽（生命值）对象

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
/*    public String message = "";
    public int messageCounter = 0;*/
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0;

    public int slotCol = 0;//鼠标点击的格子列数
    public int slotRow = 0;//鼠标点击的格子行数


    public UI(GamePanel gp) {
        this.gp = gp;
        // cambriaz = new Font("Cambria", Font.PLAIN, 40);
        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //CREATE HUB OBJECT 创建血槽（生命值）对象
        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

        Entity crystal = new OBJ_ManaCrystal(gp);
        crystal_full = crystal.image;//创建魔力水晶对象
        crystal_blank = crystal.image2;//创建魔力水晶对象


    }

    public void addMessage(String text) {//这个方法用来添加消息
      /*  message = text;
        messageOn = true;*/
        message.add(text);
        messageCounter.add(0);

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
            drawMessage();
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
        if (gp.gameState == gp.characterState) {
            drawPlayerLife();
            drawCharacterScreen();
            drawInventory();
        }
    }

    public void drawInventory() {
        //绘制玩家库存
        int frameX = gp.tileSize * 9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        final int slotXstart = frameX + 20;//绘制库存的X坐标
        final int slotYstart = frameY + 20;//绘制库存的Y坐标
//SLOT这段代码是绘制库存
        int slotX = slotXstart;
        int slotY = slotYstart;

        int slotSize = gp.tileSize + 3;

//DRAW  PLAYER INVENTORY ITEM
        for (int i = 0; i < gp.player.inventory.size(); i++) {//绘制库存

            if (gp.player.inventory.get(i) == gp.player.currentWeapon//当前武器或者当前盾牌
                    || gp.player.inventory.get(i) == gp.player.currentShield//当前盾牌
            ) {
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }
            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
            slotX += gp.tileSize;//换列
            if (i == 4 || i == 9 || i == 14) {//每行有5个物品
                slotX = slotXstart;//换列
                slotY += slotSize;//换行
            }
        }
        //CURSOR这段代码是绘制库存游标
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
//SLOT这段代码是绘制库存游标
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));//绘制库存游标的边框
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
//这段代码是绘制库存游标

        //绘制库存中游标所对应的物品的描述对话框
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize * 3;

//DRAW DESCRIPTION TEXT

        int textX = frameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));

        int itemIndex = getItemIndexOnSlot();//获取库存物品的索引

        if (itemIndex < gp.player.inventory.size()) {
            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
            //如果索引小于库存物品的数量，则绘制库存物品的描述
            for (String line : gp.player.inventory.get(itemIndex).description.split("\n")) {
                //通过循环绘制库存物品的描述，已达到换行
                g2.drawString(line, textX, textY);
                textY += 32;//换行
            }
        }

    }

    public int getItemIndexOnSlot() {//获取库存物品的索引,以实现库存物品的描述

        int itemIndex = slotCol + (slotRow * 5);//获取库存物品的索引
        return itemIndex;//返回库存物品的索引

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
        //DRAW CURRENT HEARTS 绘制玩家的生命值
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
        //绘制玩家的魔法值
        x = (gp.tileSize / 2)-5;
        y = (int) (gp.tileSize * 2);
        i = 0;
        while (i < gp.player.maxMana){
            g2.drawImage(crystal_blank, x, y, null);
            i++;
            x +=35;//绘制
        }
        x = (gp.tileSize / 2)-5;
        y = (int) (gp.tileSize * 2);
        i = 0;
        while (i < gp.player.mana){
            g2.drawImage(crystal_full, x, y, null);
            i++;
            x +=35;//绘制
        }
    }

    public void drawMessage() {//绘制攻击消息
        int messageX = gp.tileSize;
        int messageY = gp.tileSize * 4;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

        for (int i = 0; i < message.size(); i++) {//绘制消息
            if (message.get(i) != null) {//这段代码的逻辑是，
                // 如果消息不为空，就绘制消息

                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX + 2, messageY + 2);

                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);//set the counter to the arrages
                messageY += 50;
                if (messageCounter.get(i) > 180) {
                    message.remove(i);//这样做的原因是，当消息显示的时间超过180秒时，
                    // 就删除该消息，以保证消息不会占用太多空间，出而实现屏幕空间
                    messageCounter.remove(i);
                }
            }
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

            text = "Power by java";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28F));
            g2.drawString(text, x, y);
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

    public void drawCharacterScreen() {

        //Create A Frame
        final int frameX = gp.tileSize * 2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);//绘制对话框
        //TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX, textY);
        textY += lineHeight + 10;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 15;
        g2.drawString("Shield", textX, textY);


        //VALUES
        int tailX = (frameX + frameWidth) - 30;//绘制数值
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;
        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 24, null);
        textY += lineHeight;
        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 24, null);

    }

    public int getXforAlignToRightText(String text, int tailX) {//获取文本的X坐标
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;

        return x;
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        //绘制对话框的边框
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
