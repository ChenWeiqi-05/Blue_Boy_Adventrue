package monster;

import Entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

import java.util.Random;

public class MON_Orc extends Entity {
    GamePanel gp;
    public MON_Orc(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_monster;
        name = "Orc";

        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 10;
        life = maxLife;
        attack = 2;
        defense = 2;
        exp = 10;

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;

        attackArea.width = 40;
        attackArea.height = 40;

        motion1_duration = 40;
        motion2_duration = 85;

        getImage();
        getAttackImage();
    }

    public void getImage() {
        up1 = setup("/monster/orc_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/orc_up_2", gp.tileSize, gp.tileSize);

        down1 = setup("/monster/orc_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/orc_down_2", gp.tileSize, gp.tileSize);

        left1 = setup("/monster/orc_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/orc_left_2", gp.tileSize, gp.tileSize);

        right1 = setup("/monster/orc_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/orc_right_2", gp.tileSize, gp.tileSize);
    }
    /*public void update() {
        super.update();

    }*/
    /*   int xDistance = Math.abs(worldX - gp.player.worldX);
            int yDistance = Math.abs(worldY - gp.player.worldY);
            int tileDistance = (xDistance + yDistance) / gp.tileSize;
            if (onPath == false && tileDistance < 5) {

                int i = new Random().nextInt(100) + 1;
                if (i < 50) {
                    onPath = true;
                }
            }
    */
    public void getAttackImage() {
        attackUp1 = setup("/monster/orc_attack_up_1", gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/monster/orc_attack_up_2", gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/monster/orc_attack_down_1", gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/monster/orc_attack_down_2", gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/monster/orc_attack_left_1", gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/monster/orc_attack_left_2", gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/monster/orc_attack_right_1", gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/monster/orc_attack_right_2", gp.tileSize * 2, gp.tileSize);
    }
    public void setAction() {//monster的ai移动
        /*int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance) / gp.tileSize;*/
        if (onPath == true){
            checkStopChasingOrNot(gp.player, 15, 100);
           /* if (tileDistance > 20){
                onPath = false;
            }*/
          /*  int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
*/
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
           /* int i = new Random().nextInt(100) + 1;
            if (i > 197 && projectile.alive == false && shotAvailCounter == 30) {//如果技能可用，则释放技能

                projectile.set(worldX, worldY, direction, true, this);//创建技能
                //  gp.projectileList.add(projectile);//添加技能

                for (int i1 = 0; i1 < gp.projectile[1].length; i1++){

                    if (gp.projectile[gp.currentMap][i1]==null){
                        gp.projectile[gp.currentMap][i1] = projectile;
                        break;
                    }
                }

                shotAvailCounter = 0;//技能可用
            }*/
         //   checkShootOrNot(200,30);
        }
        else {
            checkStartChasingOrNot(gp.player,5,100);
         /*   actionLockCounter++;
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
            }*/
            getRandomDirection();
           /* int i = new Random().nextInt(100)+1;
            if (i > 99 && projectile.alive == false && shotAvailCounter == 30){//如果技能可用，则释放技能

                projectile.set(worldX, worldY, direction, true, this);//创建技能
                //  gp.projectile.add(projectile);//添加技能
                for (int i1 = 0; i1 < gp.projectile[1].length; i1++){

                    if (gp.projectile[gp.currentMap][i1]==null){
                        gp.projectile[gp.currentMap][i1] = projectile;
                        break;
                    }
                }
                shotAvailCounter = 0;//技能可用
            }*/
        }
        if (attacking == false){
            checkAttackOrNot(30, gp.tileSize*4, gp.tileSize);
        }
        /*   if (onPath == true) {
         int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;

            searchPath(goalCol, goalRow);
            int i = new Random().nextInt(100) + 1;
            if (i > 197 && projectile.alive == false && shotAvailCounter == 30) {//如果技能可用，则释放技能

                projectile.set(worldX, worldY, direction, true, this);//创建技能
           //  gp.projectileList.add(projectile);//添加技能

                for (int i1 = 0; i1 < gp.projectile[1].length; i1++){

                    if (gp.projectile[gp.currentMap][i1]==null){
                        gp.projectile[gp.currentMap][i1] = projectile;
                        break;
                    }
                }

                shotAvailCounter = 0;//技能可用
            }
        } else {

        }*/
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
