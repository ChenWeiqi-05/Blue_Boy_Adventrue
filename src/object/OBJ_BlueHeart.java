package object;

import Entity.Entity;
import main.GamePanel;

public class OBJ_BlueHeart extends Entity {
    GamePanel gp;
    public static final String objName = "Blue Heart";

    public OBJ_BlueHeart(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = type_pickupOnly;

        name = objName;
        down1 = setup("/objects/blueheart", gp.tileSize, gp.tileSize);
        setDialogue();
    }
    public void setDialogue() {
        dialogues[0][0] = "You pick up a beautifulss" +
                " Blue gem";
        dialogues[0][1] = "You feel a little better.";
    }
    public boolean use(Entity entity) {

        gp.gameState = gp.dialogueState;

        gp.csManager.sceneNum = gp.csManager.ending;

        return true;

    }

}
