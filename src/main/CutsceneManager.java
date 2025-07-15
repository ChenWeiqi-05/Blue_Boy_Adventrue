package main;

import Entity.PlayerDummy;
import monster.MON_SkeletonLord;
import object.OBJ_BlueHeart;
import object.OBJ_Door_Iron;

import java.awt.*;

public class CutsceneManager {
    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;
    int counter = 0;
    float alpha = 0f;
    int y;
    String endCredit;

    public final int NA = 0;
    public final int skeletonLord = 1;
    public final int ending = 2;


    public CutsceneManager(GamePanel gp) {
        this.gp = gp;

        endCredit = "Weiqi Adventure\n" +
                "Weiqi Adventure\n" +
                "Weiqi Adventure\n" +
                "Weiqi Adventure\n" +
                "Weiqi Adventure\n" +
                "Weiqi Adventure\n" +
                "Weiqi Adventure\n" +
                "Weiqi Adventure\n" +
                "Weiqi Adventure\n" +
                "Weiqi Adventure\n";
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        switch (sceneNum) {
            case skeletonLord:
                scene_skeletonLord();
                break;
            case ending:
                scene_ending();
                break;
        }
    }

    public void scene_skeletonLord() {

        if (scenePhase == 0) {
            gp.bossBattleOn = true;
            for (int i = 0; i < gp.obj[1].length; i++) {//创建怪物
                if (gp.obj[gp.currentMap][i] != null) {
                    gp.obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize * 25;
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize * 28;
                    gp.obj[gp.currentMap][i].temp = true;
                    gp.playSE(21);
                    break;
                }
            }
            for (int i = 0; i < gp.npc[1].length; i++) {
                if (gp.npc[gp.currentMap][i] == null) {
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }
            gp.player.drawing = false;
            scenePhase++;
        }
        if (scenePhase == 1) {

            gp.player.worldY -= 2;

            if (gp.player.worldY < gp.tileSize * 16) {
                scenePhase++;
            }

        }
        if (scenePhase == 2) {

            for (int i = 0; i < gp.monster[1].length; i++) {

                if (gp.monster[gp.currentMap][i] != null
                        && gp.monster[gp.currentMap][i].name == MON_SkeletonLord.monName
                ) {
                    gp.monster[gp.currentMap][i].sleep = false;
                    gp.ui.npc = gp.monster[gp.currentMap][i];//骷髅怪进入说话状态
                    scenePhase++;
                    break;
                }
            }
        }
        if (scenePhase == 3) {
            gp.ui.drawDialogueScreen();//让骷髅怪说话
        }
        if (scenePhase == 4) {
            for (int i = 0; i < gp.npc[1].length; i++) {

                if (gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)) {
                    gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                    gp.player.worldY = gp.npc[gp.currentMap][i].worldY;

                    gp.npc[gp.currentMap][i] = null;
                    break;
                }
            }


            gp.player.drawing = true;

            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;

            gp.stopMusic();
            gp.playMusic(22);
        }
        //开始渲染玩家

    }

    public void scene_ending() {
        if (scenePhase == 0) {
            gp.stopMusic();

            gp.ui.npc = new OBJ_BlueHeart(gp);
            scenePhase++;
        }
        if (scenePhase == 1) {
            gp.ui.drawDialogueScreen();
        }
        if (scenePhase == 2) {

            gp.playSE(4);
            scenePhase++;

        }
        if (scenePhase == 3) {
//直到bgm结束
            if (counterReached(300) == true) {
                scenePhase++;
            }

        }
        if (scenePhase == 4) {
//让屏幕变暗
            alpha += 0.005f;

            if (alpha > 1f) {
                alpha = 1f;
            }
            drawBlackground(alpha);

            if (alpha == 1f) {
                alpha = 0;
                scenePhase++;
            }

        }
        if (scenePhase == 5) {

            drawBlackground(1f);
            alpha += 0.005f;
            if (alpha > 1f) {
                alpha = 1f;
            }
            String text = "THE END";

             drawString(alpha, 38f, 200, text, 70);
            if (counterReached(600) == true) {
              gp.playMusic(23);
                scenePhase++;
            }
        }
        if (scenePhase == 6) {
            drawBlackground(1f);
            drawString(1f, 120f, gp.screenHeight / 2, "Weiqi Adventure", 40);
            if (counterReached(480) == true) {
                scenePhase++;
            }

        }
        if (scenePhase == 7) {
            drawBlackground(1f);
            y = gp.screenHeight / 2;
            drawString(1f, 38f, y, endCredit, 40);
            if (counterReached(480) == true) {
                scenePhase++;
            }

        }
        if (scenePhase == 8) {
            drawBlackground(1f);
            y--;
            drawString(1f, 38f, y, endCredit, 40);
            if (counterReached(480) == true) {
                scenePhase++;
            }

        }


    }

    public boolean counterReached(int target) {

        boolean counterReached = false;
        counter++;
        if (counter > target) {
            counterReached = true;
            counter = 0;
        }
        return counterReached;
    }

    public void drawBlackground(float alpha) {

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void drawString(float alpha, float fontSize, int y, String text, int lineheight) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(new Color(0, 0, 0));
        g2.setFont(g2.getFont().deriveFont(fontSize));

        for (String line : text.split("\n")) {
            int x = gp.ui.getXforCenteredText(line);
            g2.drawString(line, x, y);
            y += lineheight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }
}
