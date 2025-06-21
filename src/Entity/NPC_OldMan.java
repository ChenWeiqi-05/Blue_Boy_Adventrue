package Entity;

import main.GamePanel;

import java.util.Random;

public class NPC_OldMan extends Entity {

    public NPC_OldMan(GamePanel gp) {
        super(gp);//这个代码的意思是继承父类的属性
        direction = "down";
        speed = 1;
        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("/npc/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/oldman_right_2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {

        dialogues[0] = "A mysterious older:   Boy, I've never met you, but I know \n you're looking for your father..";
        dialogues[1] = "A mysterious older:   You're the 78 th teenager to come\n here looking for your father, and my gut tells me you are\none of them..";
        dialogues[2] = "A mysterious older:   In the before time that begin your\n adventure please help me  kill the  dirty slime..";
        dialogues[3] = "A mysterious older:   oh... I'm not your father, don't ask\n me I can't  provide any information about your mysterious \nfather..";

    }

    public void setAction() {//npc的ai移动

        if (onPath == true) {
            int goalCol = (gp.player.worldX+gp.player.solidArea.x)/gp.tileSize;
            int goalRow =(gp.player.worldY+gp.player.solidArea.y)/gp.tileSize;

            searchPath(goalCol, goalRow);
        } else {
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
        }
    }


    public void speak() {

        super.speak();

        onPath = true;

    }




}
