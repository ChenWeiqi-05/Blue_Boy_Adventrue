package InteractiveTile;

import Entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.awt.*;
import java.util.Random;

public class IT_DestructibleWall extends InteractiveTile{
    GamePanel gp;
    public IT_DestructibleWall(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/tiles_interactive/destructibleWall", gp.tileSize, gp.tileSize);
        destructible = true;

        life = 3;
    }

    public boolean isCorrectItem(Entity entity) {
        boolean isCorrenctItem = false;// 初始值为false
        if (entity.currentWeapon.type == type_pickaxe) {//如果枯树检测到砍伐它的是斧头，就执行下面的代码

            isCorrenctItem = true;
        }
        return isCorrenctItem;
    }
    public void playSE() {
        gp.playSE(11);
    }
    public InteractiveTile getDestroyedForm() {

        InteractiveTile tile = null;
        
        return tile;
    }
    public Color getParticleColor(){

        Color color = new Color(65, 65, 65);// gray
        return color;
    }
    public int getParticleSize(){
        int size = 6;
        return size;
    }
    public int getParticleSpeed(){
        int speed = 1;
        return speed;
    }
    public int getParticleMaxLife(){
        int maxLife = 20;
        return maxLife;
    }
   /* public void checkDrop() {//这段代码控制物品的随机掉落物品

        int i = new Random().nextInt(100) + 1;

        if (i < 50) {//当随机数字在0到50之间时，掉落金币
            dropItem(new OBJ_Coin_Bronze(gp));
        }
        if (i >= 50 && i < 75) {//当随机数字在50到75之间时，掉落生命
            dropItem(new OBJ_Heart(gp));
        }
        if (i >= 75 && i < 100) {//当随机数字在75到100之间时，掉落魔法水晶
            dropItem(new OBJ_ManaCrystal(gp));
        }
    }*/
}
