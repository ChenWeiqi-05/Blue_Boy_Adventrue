package object;

import Entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Blue extends Entity {
public OBJ_Shield_Blue(GamePanel gp) {
        super(gp);

      type = type_shield;
        name = "Blue Shield";
        down1 = setup("/objects/shield_blue", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        description = "[" + name + "]\nA shield that can\nbe used to protect\nyour body.";

        price = 250;

    }
}
