package monster;

import Entity.Entity;
import data.Progress;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Door_Iron;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.util.Random;

public class MON_SkeletonLord extends Entity {
    GamePanel gp;
    public static final String monName = "Skeleton Lord";

    public MON_SkeletonLord(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_monster;
        boss = true;
        name = monName;

        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 50;
        life = maxLife;
        attack = 10;
        defense = 2;
        exp = 50;
        knockBackPower = 5;
        sleep = true;

        int size = gp.tileSize * 5;
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - 48 * 2;
        solidArea.height = size - 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 170;
        attackArea.height = 170;

        motion1_duration = 25;
        motion2_duration = 50;

        getImage();
        getAttackImage();
        setDialogue();
    }

    public void getImage() {

        int i = 5;
        if (inRange == false) {
            up1 = setup("/monster/SkeletonLord_up_1", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("/monster/SkeletonLord_up_2", gp.tileSize * i, gp.tileSize * i);

            down1 = setup("/monster/SkeletonLord_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("/monster/SkeletonLord_down_2", gp.tileSize * i, gp.tileSize * i);

            left1 = setup("/monster/SkeletonLord_left_1", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("/monster/SkeletonLord_left_2", gp.tileSize * i, gp.tileSize * i);

            right1 = setup("/monster/SkeletonLord_right_1", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("/monster/SkeletonLord_right_2", gp.tileSize * i, gp.tileSize * i);
        }
        if (inRange == true) {
            up1 = setup("/monster/SkeletonLord_phase2_up_1", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("/monster/SkeletonLord_phase2_up_2", gp.tileSize * i, gp.tileSize * i);

            down1 = setup("/monster/SkeletonLord_phase2_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("/monster/SkeletonLord_phase2_down_2", gp.tileSize * i, gp.tileSize * i);

            left1 = setup("/monster/SkeletonLord_phase2_left_1", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("/monster/SkeletonLord_phase2_left_2", gp.tileSize * i, gp.tileSize * i);

            right1 = setup("/monster/SkeletonLord_phase2_right_1", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("/monster/SkeletonLord_phase2_right_2", gp.tileSize * i, gp.tileSize * i);

        }

    }
    public void setDialogue() {

    dialogues[0][0]= "No one can steal my treasure!";
    dialogues[0][1]= "You will die here!";
    dialogues[0][2]= "Welcome to your doom !";

    }

    public void getAttackImage() {
        int i = 5;
        if (inRange == false) {
            attackUp1 = setup("/monster/SkeletonLord_attack_up_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackUp2 = setup("/monster/SkeletonLord_attack_up_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown1 = setup("/monster/SkeletonLord_attack_down_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown2 = setup("/monster/SkeletonLord_attack_down_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackLeft1 = setup("/monster/SkeletonLord_attack_left_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackLeft2 = setup("/monster/SkeletonLord_attack_left_2", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight1 = setup("/monster/SkeletonLord_attack_right_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight2 = setup("/monster/SkeletonLord_attack_right_2", gp.tileSize * i * 2, gp.tileSize * i);

        }
        if (inRange == true) {
            attackUp1 = setup("/monster/SkeletonLord_phase2_attack_up_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackUp2 = setup("/monster/SkeletonLord_phase2_attack_up_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown1 = setup("/monster/SkeletonLord_phase2_attack_down_1", gp.tileSize * i, gp.tileSize * i * 2);
            attackDown2 = setup("/monster/SkeletonLord_phase2_attack_down_2", gp.tileSize * i, gp.tileSize * i * 2);
            attackLeft1 = setup("/monster/SkeletonLord_phase2_attack_left_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackLeft2 = setup("/monster/SkeletonLord_phase2_attack_left_2", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight1 = setup("/monster/SkeletonLord_phase2_attack_right_1", gp.tileSize * i * 2, gp.tileSize * i);
            attackRight2 = setup("/monster/SkeletonLord_phase2_attack_right_2", gp.tileSize * i * 2, gp.tileSize * i);

        }

    }

    public void setAction() {

        if (onPath == false && life < maxLife / 2) {
            inRange = true;
            getImage();
            getAttackImage();
            defaultSpeed++;
            speed = defaultSpeed;
            attack *= 2;

        }

        if (getTileDistance(gp.player) < 10) {
            moveTowardPlayer(60);
        } else {
            getRandomDirection(120);
        }
        if (attacking == false) {
            checkAttackOrNot(60, gp.tileSize * 7, gp.tileSize * 5);
        }
    }

    public void damageReaction() {//史莱姆受到攻击后的退后ai
        actionLockCounter = 0;
        // direction = gp.player.direction1;

    }

    public void checkDrop() {//这段代码控制物品的随机掉落物品


        gp.bossBattleOn = false;

        Progress.skeletonLordDefeated  = true;
        gp.stopMusic();
        gp.playMusic(19);

        for (int J = 0; J < gp.obj[1].length;J++) {

            if (gp.obj[gp.currentMap][J] != null && gp.obj[gp.currentMap][J].name.equals(OBJ_Door_Iron.objName)) {

                gp.playSE(21);
                gp.obj[gp.currentMap][J] = null;

            }

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
}
