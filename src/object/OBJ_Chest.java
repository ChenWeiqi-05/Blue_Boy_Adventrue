package object;

import Entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity {

    GamePanel gp;
    Entity loot;

    boolean opened = false;

    public OBJ_Chest(GamePanel gp, Entity loot) {
        super(gp);
        this.gp = gp;
        this.loot = loot;

        type = type_obstacle;
        name = "Chest";
        image = setup("/objects/chest", gp.tileSize, gp.tileSize);
        image2 = setup("/objects/chest_opened", gp.tileSize, gp.tileSize);

        down1 = image;
        collision = true;

        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = "You open the chest and find a " + loot.name + "!\n...But you cannot carry any more!";
        dialogues[1][0] ="You open the chest and find a " + loot.name +"!\nYou obtain the" + loot.name + "!";
    dialogues[2][0] = "It's empty.";
    }

    public void interact() {

        if (opened == false) {
            gp.playSE(3);


            if (gp.player.canObtainItem(loot) == false) {
              startDialogue(this, 0);
            } else {
                startDialogue(this, 1);
                down1 = image2;
                opened = true;
            }
            dialogues[0][0] = sb.toString();
            startDialogue(this, 0);
        } else {
            dialogues[1][0] = "It's empty";
            startDialogue(this, 1);
        }

    }

}
