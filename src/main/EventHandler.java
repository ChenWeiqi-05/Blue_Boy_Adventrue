package main;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][];

    int count = 0;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {


            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;

            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
//宇宙 世界末日  计算机存储  时间 历史 记忆 图书管理员  生命 性 萝莉 怀孕 社会压力

    }

    public void checkEvent() {
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int ydistance = Math.abs(gp.player.worldX - previousEventY);
        int distance = Math.max(xDistance, ydistance);
        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }
        if (canTouchEvent == true) {
            if (hit(27, 16, "right") == true) {
//  判断玩家是否与pit发生碰撞
                damagePit(27, 16, gp.dialogueState);

                System.out.println("call checkEvent");
            }
            if (hit(23, 19, "any") == true) {
                damagePit(27, 16, gp.dialogueState);
            }
            if (hit(23, 12, "up") == true) {
                healingPool(23, 12, gp.dialogueState);
            }
        }

        if (hit(27, 16, "right") == true) {
//  判断玩家是否与pit发生碰撞
            damagePit(27, 16, gp.dialogueState);
            count = count + 1;
            System.out.println("call checkEvent");
        }
        if (hit(23, 12, "up") == true) {
            count = count + 1;
            healingPool(23, 12, gp.dialogueState);
        }
    }

    public boolean hit(int col, int row, String reqDirection) {
        //  判断玩家是否与事件发生碰撞
        boolean hit = false;
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;
        if (gp.player.solidArea.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {//这段代码是用来判断玩家是否与事件发生碰撞的
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;

            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;
        return hit;
    }

    public void damagePit(int col, int row, int gameState) {//掉落事件


   /*  gp.gameState = gameState;

       gp.player.life -= 1;*/
        //   eventRect[col][row].eventDone = true;
        gp.ui.currentDialogue = "I fall into a pit ! ";
        canTouchEvent = false;
    }

    public void healingPool(int col, int row, int gameState) {
        if (gp.keyH.enterPressed == true) {
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.ui.currentDialogue = "You drink the water .\nYour life has been recovered !\n oh yeah! ";
            gp.player.life = gp.player.maxLife;
            gp.aSetter.setMonster();

        }

    }
}
