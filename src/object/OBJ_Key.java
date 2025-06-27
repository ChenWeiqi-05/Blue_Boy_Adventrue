package object;

import Entity.Entity;
import main.GamePanel;


public class OBJ_Key extends Entity {
    GamePanel gp;

    public OBJ_Key(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_consumable;
        name = "Key";
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\ni can open a door.";
        price = 100;
        stackable = true;
        startDialogue(this, 0);
    }

    public void setDialogue() {
        dialogues[0][0] ="You use the " + name + " and open the door.";
        dialogues[1][0] ="You can't use it.";
    }

    public boolean use(Entity entity) {

        int objIndex = getDetected(entity, gp.obj, "Door");

        if (objIndex != 999) {
            startDialogue(this, 0);
            gp.playSE(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        } else {
            startDialogue(this, 1);
            return false;
        }

    }
}
