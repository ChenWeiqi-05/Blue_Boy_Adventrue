package main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent() {
        if (hit(27, 16, "right") == true) {
//  判断玩家是否与pit发生碰撞
            teleport(gp.dialogueState);

            System.out.println("call checkEvent");
        }
        if (hit(23, 12, "up") == true) {
            healingPool(gp.dialogueState);
        }
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        //  判断玩家是否与事件发生碰撞
        boolean hit = false;
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol * gp.tileSize + eventRect.x;
        eventRect.y = eventRow * gp.tileSize + eventRect.y;
        if (gp.player.solidArea.intersects(eventRect)) {//这段代码是用来判断玩家是否与事件发生碰撞的
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;
        return hit;
    }

    public void teleport(int gameState) {//这是一个传送门函数
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You have teleported !";
        gp.player.worldX = gp.tileSize * 37;
        gp.player.worldY = gp.tileSize * 10;
    }

    public void damagePit(int gameState) {

        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall into a pit !";

    }

    public void healingPool(int gameState) {
        if (gp.keyH.enterPressed == true) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You drink the water !\nYour life has been recovered !";
            gp.player.life = gp.player.maxLife;
        }

    }
}
