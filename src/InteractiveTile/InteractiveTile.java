package InteractiveTile;

import Entity.Entity;
import main.GamePanel;

public class InteractiveTile extends Entity {

    GamePanel gp;
    public boolean destructible = false;// 这段代码的意思是这个类不是可破坏的

    public InteractiveTile(GamePanel gp, int col, int row) {
        super(gp);
        this.gp = gp;
    }

    public boolean isCorrectItem(Entity entity) {// 这个方法用来判断这个物品是否是正确的物品

        boolean isCorrenctItem = false;// 初始值为false
        return isCorrenctItem;
    }

    public void playSE() {

    }

    public InteractiveTile getDestroyedForm() {
        InteractiveTile tile = null;

        return tile;
    }

    public void update() {

        if (invincible == true) {//这段代码的意思就是，如果玩家处于无敌状态，
            // 那么就会让invincibleCounter加1，
            // 然后判断invincibleCounter的值是否大于20，
            // 如果大于20，那么就会让invincibleCounter归零，然后让invincible的值变为false，
            // 这样玩家就可以再次被攻击了。
            invincibleCounter++;
            if (invincibleCounter > 20) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
}
