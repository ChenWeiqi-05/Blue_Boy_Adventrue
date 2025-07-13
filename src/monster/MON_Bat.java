package monster;

import Entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

import java.util.Random;

public class MON_Bat extends Entity {
    GamePanel gp;
    public MON_Bat(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_monster;
        name = "Bat";

        defaultSpeed = 4;
        speed = defaultSpeed;
        maxLife = 7;
        life = maxLife;
        attack = 7;
        defense = 0;
        exp = 7;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 21;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {
        up1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);

        down1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);

        left1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);

        right1 = setup("/monster/bat_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/bat_down_2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {//monster的ai移动
        if (onPath == true){
        }
        else {
            getRandomDirection(100);
        }
    }
    public void damageReaction() {//史莱姆受到攻击后的退后ai
        actionLockCounter = 0;
        // direction = gp.player.direction1;
        onPath = true;
    }
    public void checkDrop() {//这段代码控制物品的随机掉落物品

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
    }
}
