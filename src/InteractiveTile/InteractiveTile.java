package InteractiveTile;

import Entity.Entity;
import main.GamePanel;

import java.awt.*;

public class InteractiveTile extends Entity {

    GamePanel gp;
    public boolean destructible = false;// 这段代码的意思是这个类不是可破坏的

    public InteractiveTile(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;
    }

    public boolean isCorrectItem(Entity entity) {// 这个方法用来判断这个物品是否是正确的物品

        boolean isCorrenctItem = false;// 初始值为false
        return isCorrenctItem;
    }

    public void playSE() {

    }

    public InteractiveTile getDestroyedForm() {// 这个方法用来获取被破坏的tile
        InteractiveTile tile = null;

        return tile;
    }

    public void update() {

        if (invincible == true) {//这段代码的意思就是，如果玩家处于无敌状态，
            // 那么就会让invincibleCounter加1，
            // 然后判断invincibleCounter的值是否大于20，
            // 如果大于20，那么就会让invincibleCounter归零，然后让invincible的值变为false，
            // 这样玩家就可以再次被攻击了。
            invincibleCounter++;
            if (invincibleCounter > 20) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2) {//这段代码防止玩家被攻击时，交互方块想怪物一样闪烁
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&//这是一段优化代码，用来判断屏幕是否在屏幕范围内
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&//
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
        ) {
            g2.drawImage(down1, screenX, screenY,  null);
        }
    }

}
