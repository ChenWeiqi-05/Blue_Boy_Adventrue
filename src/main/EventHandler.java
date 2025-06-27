package main;

import Entity.Entity;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][][];

    int count = 0;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int map = 0;
        int col = 0;
        int row = 0;
        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {


            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;

            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;

                if (row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }


            }
        }
//宇宙 世界末日  计算机存储  时间 历史 记忆 图书管理员  生命 性 萝莉 怀孕 社会压力

    }

    public void checkEvent() {
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldX - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }
        if (canTouchEvent == true) {
            if (hit(0, 27, 16, "right") == true) {
//  判断玩家是否与pit发生碰撞
                damagePit(gp.dialogueState);
            }
            else if (hit(0, 23, 12, "up") == true) {
                damagePit(gp.dialogueState);
            }
            else  if (hit(0, 10, 39, "any") == true) {
                teleport(1, 12, 13);
            }
            else  if (hit(1, 12, 13, "any") == true) {
                teleport(0, 10, 39);
            }
            else if (hit(1, 12, 9, "up")){
                speak(gp.npc[1][0]);
            }

        }

        if (hit(0, 27, 16, "right") == true) {
//  判断玩家是否与pit发生碰撞
            damagePit(gp.dialogueState);
            count = count + 1;
            // System.out.println("call checkEvent");
        }
        if (hit(0, 23, 12, "up") == true) {
            count = count + 1;
            healingPool(gp.dialogueState);
        }
    }

    public  void speak(Entity entity) {
        if (gp.keyH.enterPressed == true){
            gp.gameState = gp.dialogueState;
            gp.player.attackCanceled = true;
            entity.speak();
        }
    }

    public void teleport(int map, int col, int row) {

        gp.currentMap = map;
        gp.player.worldX = gp.tileSize * col;
        gp.player.worldY = gp.tileSize * row-(gp.tileSize) ;

        previousEventX = gp.player.worldX;
        previousEventY = gp.player.worldY -(gp.tileSize);
        canTouchEvent = false;
        gp.playSE(13);
    }

    public boolean hit(int map, int col, int row, String reqDirection) {
        //  判断玩家是否与事件发生碰撞
        boolean hit = false;
        if (map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;
            if (gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {//这段代码是用来判断玩家是否与事件发生碰撞的
                if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                    hit = true;
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY ;

                }
            }
            gp.player.solidArea.x = gp.player.solidAreaDefaultX ;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        return hit;

    }

    public void damagePit(int gameState) {//掉落事件
   /*  gp.gameState = gameState;
       gp.player.life -= 1;*/
        //   eventRect[col][row].eventDone = true;
        gp.ui.currentDialogue = "I fall into a pit ! ";
        canTouchEvent = false;

    }

    public void healingPool(int gameState) {
        if (gp.keyH.enterPressed == true) {
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.playSE(9);

            gp.ui.currentDialogue = "You drink the water .\nYour life and mana have been recovered !\n oh yeah! ";
            gp.player.life = gp.player.maxLife;
            gp.player.mana = gp.player.maxMana;

            gp.aSetter.setMonster();
        }
    }
}
