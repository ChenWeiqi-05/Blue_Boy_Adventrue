package main;

import Entity.NPC_OldMan;
import InteractiveTile.IT_DryTree;
import monster.MON_GreenSlime;
import object.*;

import java.util.Random;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        /*gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;
        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize;
        gp.obj[2] = new OBJ_Key(gp);
        gp.obj[2].worldX = 37 * gp.tileSize;
        gp.obj[2].worldY = 7 * gp.tileSize;
        gp.obj[3] = new OBJ_Door(gp);
        gp.obj[3].worldX = 10 * gp.tileSize;
        gp.obj[3].worldY = 11 * gp.tileSize;
        gp.obj[4] = new OBJ_Door(gp);
        gp.obj[4].worldX = 8 * gp.tileSize;
        gp.obj[4].worldY = 28 * gp.tileSize;
        gp.obj[5] = new OBJ_Door(gp);
        gp.obj[5].worldX = 12 * gp.tileSize;
        gp.obj[5].worldY = 22 * gp.tileSize;
        gp.obj[6] = new OBJ_Chest(gp);
        gp.obj[6].worldX = 10 * gp.tileSize;
        gp.obj[6].worldY = 7 * gp.tileSize;

        gp.obj[7] = new OBJ_Boots(gp);
        gp.obj[7].worldX = 37 * gp.tileSize;
        gp.obj[7].worldY = 42 * gp.tileSize;*/

        int mapNum  = 0;
        int i = 0;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 25;
        gp.obj[mapNum][i].worldY = gp.tileSize * 23;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 21;
        gp.obj[mapNum][i].worldY = gp.tileSize * 19;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 26;
        gp.obj[mapNum][i].worldY = gp.tileSize * 21;
        i++;
        gp.obj[mapNum][i] = new OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 25;
        gp.obj[mapNum][i].worldY = gp.tileSize * 21;

        i++;
        gp.obj[mapNum][i] = new OBJ_Shield_Blue(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 35;
        gp.obj[mapNum][i].worldY = gp.tileSize * 21;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 22;
        gp.obj[mapNum][i].worldY = gp.tileSize * 27;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 10;
        gp.obj[mapNum][i].worldY = gp.tileSize * 8;
        i++;
        gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 26;
        gp.obj[mapNum][i].worldY = gp.tileSize * 22;

        i++;
        gp.obj[mapNum][i] = new OBJ_Heart(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 22;
        gp.obj[mapNum][i].worldY = gp.tileSize * 28;

        i++;
        gp.obj[mapNum][i] = new OBJ_ManaCrystal(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize * 22;
        gp.obj[mapNum][i].worldY = gp.tileSize * 29;

    }

    public void setNPC() {
        int mapNum  = 0;
        gp.npc[mapNum][0] = new NPC_OldMan(gp);
        gp.npc[mapNum][0].worldX = gp.tileSize * 21;//npc的在世界的x位置
        gp.npc[mapNum][0].worldY = gp.tileSize * 21;//npc的在世界的y位置
    /*  gp.npc[0] = new NPC_OldMan(gp);
     gp.npc[0].worldX = gp.tileSize * 9;//npc的在世界的x位置
       gp.npc[0].worldY = gp.tileSize * 10;//npc的在世界的y位置

*/
    }

    public void setMonster() {

     /*   int i = 0;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 23;
        gp.monster[i].worldY = gp.tileSize * 36;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 23;
        gp.monster[i].worldY = gp.tileSize * 42;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 24;
        gp.monster[i].worldY = gp.tileSize * 37;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 34;
        gp.monster[i].worldY = gp.tileSize * 42;
        i++;
        gp.monster[i] = new MON_GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize * 38;
        gp.monster[i].worldY = gp.tileSize * 42;*/

       /* Random random = new Random();
        // 仅初始化部分史莱姆(例如10个)，保留空间用于后续生成
        int initialCount = 1000;
        for (int i = 0; i < initialCount && i < gp.monster.length; i++) {
            gp.monster[i] = new MON_GreenSlime(gp);
            gp.monster[i].worldX = gp.tileSize * (1 + random.nextInt(gp.maxWorldCol - 1));
            gp.monster[i].worldY = gp.tileSize * (1 + random.nextInt(gp.maxWorldRow - 1));
        }*/
        int mapNum  = 0;
        int i = 0;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 11;
        gp.monster[mapNum][i].worldY = gp.tileSize * 9;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 11;
        gp.monster[mapNum][i].worldY = gp.tileSize * 10;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 23;
        gp.monster[mapNum][i].worldY = gp.tileSize * 40;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 22;
        gp.monster[mapNum][i].worldY = gp.tileSize * 41;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 23;
        gp.monster[mapNum][i].worldY = gp.tileSize * 35;
        i++;
        gp.monster[mapNum][i] = new MON_GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 11;
        gp.monster[mapNum][i].worldY = gp.tileSize * 10;

    }

    public void setInteractiveTile() {
        int mapNum  = 0;
        int i = 0;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,18,40);
       i++;
       /* gp.iTile[mapNum][i] = new IT_DryTree(gp,28,12);
         i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,29,12);
         i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,30,12);
         i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,31,12);
         i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,32,12);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,33,12);*/
        gp.iTile[mapNum][i] = new IT_DryTree(gp,17,40);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,16,40);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,15,40);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,14,40);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,13,40);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,13,41);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,12,41);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,11,41);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,10,41);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp,10,40);
        i++;

    }
}
