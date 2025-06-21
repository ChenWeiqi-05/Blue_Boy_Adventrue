package Entity;

import main.GamePanel;

public class Projectile extends Entity {
    Entity user;

    public Projectile(GamePanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {//设置投掷物的属性

        this.worldX = worldX;//投掷物位置
        this.worldY = worldY;//投掷物位置
        this.direction = direction;//投掷物方向
        this.alive = alive;//投掷物是否存活
        this.user = user;//投掷物的攻击者
        this.life = this.maxLife;//投掷物最大生命
    }

    public void update() {

        if (user == gp.player) {//如果使用投射物的对象是玩家，那么投掷物
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            //检测是否打中怪物
            if (monsterIndex != 999) {//投掷物是否击中了怪物
                gp.player.damageMonster(monsterIndex, attack);//击中怪物

              generateParticle(user.projectile,gp.monster[gp.currentMap][monsterIndex]);//玩家攻击后，在怪物身上创建粒子特效。

                alive = false;//投掷物消亡
            }

        }
        if (user != gp.player) {//如果使用投射物的对象不是玩家是怪物，那么创建粒子
            boolean contactPlayer = gp.cChecker.checkPlayer(this);
            if (gp.player.invincible == false && contactPlayer == true) {
                damagePlayer(attack);
                generateParticle(user.projectile, gp.player);

                alive = false;
            }
        }

        switch (direction) {
            case "up":
                worldY -= speed;
                break;
            case "down":
                worldY += speed;
                break;
            case "left":
                worldX -= speed;
                break;
            case "right":
                worldX += speed;
                break;
        }
        life--;
        if (life <= 0) {
            alive = false;

        }

        spriteCounter++;//帧数
        if (spriteCounter > 12) {//帧数超过12
            if (spriteNum == 1) {//帧数等于1
                spriteNum = 2;//帧数等于2
            } else if (spriteNum == 2) {//帧数等于2
                spriteNum = 1;   //帧数等于1
            }
            spriteCounter = 0;

        }
    }
    public boolean haveResource(Entity  user) {//它的子类OBJ_Fireball会重写此方法
        boolean haveResource = false;
        return haveResource;
    }
    public void subtractResource(Entity user) {//减去消耗的魔法值
       }
}
