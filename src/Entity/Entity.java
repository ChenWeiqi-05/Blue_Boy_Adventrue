package Entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    GamePanel gp;
    // public int worldX, worldY;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);//这是所有实体的默认矩形
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String dialogues[] = new String[20];
    public int worldX, worldY;//这个是world01的绝对坐标
    public String direction = "down";
    public String direction1 = "up";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    boolean attacking = false;

    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;//这个是hp条的开关

    public boolean onPath = false;
    public int spriteCounter = 0;
    public int actionLockCounter = 0; //  这个是实体动作计数器，用来控制实体的动作
    public int invincibleCounter = 0;

    public int shotAvailCounter = 0;
    //STATE
    int dyingCounter = 0;

    int hpBarOnCounter = 0;//这个是hp条的开关

    public String name;

    public int maxLife;
    public int level;
    public int life;

    public int maxMana;//这个是实体的魔力值
    public int mana;//这个是实体的魔力值
    public int ammo;
    public int speed;
    public int hpBarCounter;
    public int strength;//这个是实体的攻击力
    public int dexterity;//这个是实体的敏捷
    public int attack;//这个是实体的攻击力
    public int defense;//这个是实体的防御力
    public int exp;//这个是实体的经验值
    public int nextLevelExp;
    public int coin;//这个是实体的金币数
    public Entity currentWeapon;//这个是实体的武器
    public Entity currentShield;//这个是实体的盾牌

    public Projectile projectile;

    public int value;
    public int attackValue;

    public int defenseValue;

    public String description = "";
    public int useCost;

    public int type; // 0 是player 1 = npc 2 = monster
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;

    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;

    // public Object solidArea;
    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {

    }

    public void damageReaction() {
    }

    public void speak() {
        if (dialogues[dialogueIndex] == null) {//防止dialogues数组的空指针异常
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
//本段代码实现让npc对话时面向玩家
        switch (gp.player.direction) {

            case "up":
                direction = "down";
                break;

            case "down":
                direction = "up";
                break;

            case "left":
                direction = "right";
                break;

            case "right":
                direction = "left";
                break;
        }
    }

    public void checkCollision() {
        collisionOn = false;//这个是检查地图块是否与实体发生碰触的代码
        gp.cChecker.checkTile(this);// 这个是检查地图块是否与实体发生碰
        gp.cChecker.checkObject(this, false);//这个是检查物体是否与实体发生碰撞
        gp.cChecker.checkEntity(this, gp.npc);//这个是检查npc是否与实体发生碰撞
        gp.cChecker.checkEntity(this, gp.monster);//这个是检查npc是否与实体发生碰撞
        gp.cChecker.checkEntity(this, gp.iTile);//这个是检查npc是否与实体发生碰撞的代码
        boolean contactPlayer = gp.cChecker.checkPlayer(this);//这段代码的意思是
        // 当实体与玩家发生碰撞时，如果玩家没有被保护，则玩家会损失生命值
        if (this.type == type_monster && contactPlayer == true) {//这段代码的意思是当实体与玩家发生碰撞时
            damagePlayer(attack);//玩家损失生命值
        }
    }
    ///这个是更新实体的代码，用来控制实体的移动
    public void update() {
        setAction();//这个是设置实体的动作的代码，用来控制实体的移动
        checkCollision();
        if (collisionOn == false) {//检测实体是否发生碰撞
            switch (direction) {
                case "up":
                    //  System.out.println("向上碰撞");
                    worldY -= speed;
                    break;
                case "down":
                    //System.out.println("向下碰撞");
                    worldY += speed;
                    break;
                case "left":
                    //System.out.println("向左碰撞");
                    worldX -= speed;
                    break;
                case "right":
                    // System.out.println("向右碰撞");
                    worldX += speed;
                    break;
            }
        }
        gp.cChecker.checkTile(this);
        spriteCounter++;
        if (spriteCounter > 12) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        if (invincible == true) {//这段代码的意思就是，如果玩家处于无敌状态，
            // 那么就会让invincibleCounter加1，
            // 然后判断invincibleCounter的值是否大于40，如果大于40，
            // 那么就会让invincibleCounter归零，然后让invincible的值变为false，
            // 这样玩家就可以再次被攻击了。
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (shotAvailCounter < 30) {//确保玩家可以连续攻击
            shotAvailCounter++;
        }
    }

    public void damagePlayer(int attack) {
        // ，如果玩家没有被保护，则玩家会损失生命值
        if (gp.player.invincible == false) {
            gp.playSE(6);
            int damage = attack - gp.player.defense;//攻击力减去防御力
            if (damage < 0) {
                damage = 0;
            }
            gp.player.life -= damage;
            gp.player.invincible = true;
        }

    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&//这是一段优化代码，用来判断屏幕是否在屏幕范围内
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&//
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
        ) {
            switch (direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;

                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }

                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }

                    break;
                case "left":
                    if (spriteNum == 1) {

                        image = left1;
                    }
                    if (spriteNum == 2) {

                        image = left2;
                    }

                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    break;
            }
            //Monster HP bar
            if (type == 2 && hpBarOn == true) {//绘制Monster HP bar,当type等于2时才绘制

                double oneScale = (double) gp.tileSize / maxLife;//计算一个比例
                double hpBarValue = oneScale * life;//计算Monster HP bar的值

                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX, screenY - 16, gp.tileSize + 2, 12);
                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 10);
                //这段代码是绘制Monster HP bar的代码

                hpBarCounter++;
                if (hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }
            if (invincible == true) {//如果玩家处于被保护状态，则绘制Monster HP bar
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4F);
            }
            if (dying == true) {
                dyingAnimation(g2);
            }

            g2.drawImage(image, screenX, screenY, null);//绘制玩家

            changeAlpha(g2, 1F);

        }
    }

    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;
        int i = 5;
        if (dyingCounter <= i) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i && dyingCounter <= i * 2) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 2 && dyingCounter <= i * 3) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 3 && dyingCounter <= i * 4) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 4 && dyingCounter <= i * 5) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 5 && dyingCounter <= i * 6) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 6 && dyingCounter <= i * 7) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 7 && dyingCounter <= i * 8) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 8) {
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public void use(Entity entity) {

    }

    public void checkDrop() {//掉落物

    }

    public void dropItem(Entity droppedItem) {//这段代码用来处理道具的掉落逻辑
        for (int i = 0; i < gp.obj[1].length; i++) {//遍历所有物体
            if (gp.obj[gp.currentMap][i] == null) {//如果物体为空，则将掉落物放入物体中
                gp.obj[gp.currentMap][i] = droppedItem;//将掉落物放入物体中
                gp.obj[gp.currentMap][i].worldX = worldX;//死去怪物的世界位置
                gp.obj[gp.currentMap][i].worldY = worldY;//死去怪物的世界位置
                break;
            }
        }
    }

    public BufferedImage setup(String imagePath, int width, int height) {//这个方法用来获取图片
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);//这段代码是用来获取图片的，并且缩放到指定的大小。
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public Color getParticleColor() {

        Color color = null;
        return color;
    }

    public int getParticleSize() {
        int size = 0;
        return size;
    }

    public int getParticleSpeed() {
        int speed = 0;
        return speed;
    }

    public int getParticleMaxLife() {
        int maxLife = 0;
        return maxLife;
    }

    public void generateParticle(Entity generator, Entity target) {//这段代码的含义为所有的实体生成粒子效果
        Color color = generator.getParticleColor();//获取粒子颜色
        int size = generator.getParticleSize();//获取粒子大小
        int speed = generator.getParticleSpeed();//获取粒子速度
        int maxLife = generator.getParticleMaxLife();//获取粒子最大生命值

        Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);//创建粒子对象
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);//
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);//
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);//

        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }

    public void searchPath(int goalCol, int goalRow) {

        int startCol = (worldX + solidArea.x) / gp.tileSize;
        int startRow = (worldY + solidArea.y) / gp.tileSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);

        if (gp.pFinder.search() == true) {//这段代码的作用是找到路径

            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
            } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            } else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {

                if (enLeftX > nextX) {
                    direction = "left";
                }
                if (enLeftX < nextX) {
                    direction = "right";
                }
            }
            else if (enTopY > nextY && enLeftX > nextX) {
                direction = "up";
                checkCollision();
                if (collisionOn == true) {
                    direction = "left";
                }
            }
            else if (enTopY < nextY && enLeftX < nextX) {
                direction = "up";
                checkCollision();
                if (collisionOn == true) {
                    direction = "right";
                }
            }
            else if (enTopY < nextY && enLeftX > nextX) {
                direction = "down";
                checkCollision();
                if (collisionOn == true) {
                    direction = "left";
                }
            }
            else if (enTopY > nextY && enLeftX < nextX) {
                direction = "down";
                checkCollision();
                if (collisionOn == true) {
                    direction = "right";
                }
            }

          /*  int nextCol = gp.pFinder.pathList.get(0).col;
            int nextRow = gp.pFinder.pathList.get(0).row;
            if (nextCol == goalCol && nextRow == goalRow){
                onPath = false;
            }*/
        }
    }
//如果我没有考到离家的学校，我还会这样想吗？
}
