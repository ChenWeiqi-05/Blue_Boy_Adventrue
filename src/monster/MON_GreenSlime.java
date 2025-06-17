package monster;

import Entity.Entity;
import main.GamePanel;
import object.OBJ_Fireball;
import object.OBJ_Rock;

import java.util.Random;

public class MON_GreenSlime extends Entity {

    GamePanel gp;

    public MON_GreenSlime(GamePanel gp) {
        super(gp);
        this.gp = gp;
      type = type_monster;
        name = "Green Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 2;
        projectile = new OBJ_Rock(gp);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;

        getImage();
    }

    public void getImage() {
        up1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);

        down1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);

        left1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);

        right1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
    }

    public void setAction() {//monster的ai移动
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i < 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i >= 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }

        int i = new Random().nextInt(100)+1;
        if (i > 99 && projectile.alive == false && shotAvailCounter == 30){//如果技能可用，则释放技能

            projectile.set(worldX, worldY, direction, true, this);//创建技能
            gp.projectileList.add(projectile);//添加技能

            shotAvailCounter = 0;//技能可用

        }
    }

    public void damageReaction() {//史莱姆受到攻击后的退后ai
        actionLockCounter = 0;
        direction = gp.player.direction1;
    }
}
