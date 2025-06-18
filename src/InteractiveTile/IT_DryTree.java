package InteractiveTile;

import Entity.Entity;
import main.GamePanel;

public class IT_DryTree extends InteractiveTile {

    GamePanel gp;

    public IT_DryTree(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;


        down1 = setup("/tiles_interactive/drytree", gp.tileSize, gp.tileSize);
        destructible = true;

        life = 3;
    }

    public boolean isCorrectItem(Entity entity) {// 这个方法用来判断这个物品是否是正确的物品

        boolean isCorrenctItem = false;// 初始值为false

        if (entity.currentWeapon.type == type_axe) {//如果枯树检测到砍伐它的是斧头，就执行下面的代码

            isCorrenctItem = true;
        }
        return isCorrenctItem;
    }

    public void playSE() {
        gp.playSE(11);
    }

    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = new  IT_Trunk(gp,worldX/gp.tileSize,worldY/gp.tileSize);
        return tile;
    }

}
