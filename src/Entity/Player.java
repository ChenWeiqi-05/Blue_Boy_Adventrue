package Entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    // public int hasKey = 0;
    public boolean attackCanceled = false;
    public boolean lightUpdated = false;
    int standTime = 0;


    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.width = 32;
        solidArea.height = 32;

       /* attackArea.width = 36;
        attackArea.height = 36;*/

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
        setDialogue();
    }

    public void setItems() {
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentShield);
        // inventory.add(new OBJ_Shield_Wood(gp));
        //inventory.add(new OBJ_Sword_Normal(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Lantern(gp));
    }

    public void setDefaultValues() {
        setDefaultPositions();
     /*  worldX = gp.tileSize * 13;

       worldY = gp.tileSize * 14;*/
    /*  worldX = gp.tileSize * 12;
        worldY = gp.tileSize * 13;*/
      /*  worldX = gp.tileSize * 12;

        worldY = gp.tileSize * 12;
        */

        defaultSpeed = 4;
        gp.currentMap = 0;
        speed = defaultSpeed;

// PLAYER STATUS
        maxLife = 6;
        life = maxLife;
        maxMana = 4;
        mana = maxMana;
        ammo = 10;//玩家石头的弹容量
        level = 1;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 1000;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);//添加盾牌
        projectile = new OBJ_Fireball(gp);//添加火球投掷物
        //projectile = new OBJ_Rock(gp);//添加石头投掷物

        attack = getAttack();
        defense = getDefense();
    }

    public void setDefaultPositions() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        /*worldX = gp.tileSize * 12;
        worldY = gp.tileSize * 13;*/
        direction = "down";
    }

    public void setDialogue() {
        dialogues[0][0] = "You are level " + level + " now!\n" + "You feel stronger!";

    }

    public void restoreLifeAndMana() {
        life = maxLife;
        mana = maxMana;
        invincible = false;
    }

    public int getAttack() {

        attackArea = currentWeapon.attackArea;
        motion1_duration = currentWeapon.motion1_duration;
        motion2_duration = currentWeapon.motion2_duration;


        return attack = strength * currentWeapon.attackValue;
    }

    public int getDefense() {
        return defense = dexterity * currentShield.attackValue;
    }

    public void getPlayerImage() {
        up1 = setup("/player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/player/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/player/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/player/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/player/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/player/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/player/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/player/boy_right_2", gp.tileSize, gp.tileSize);
    }

    public void getSleepingImage(BufferedImage image) {
        up1 = image;
        up2 = image;
        down1 = image;
        down2 = image;
        left1 = image;
        left2 = image;
        right1 = image;
        right2 = image;

    }

    public void getPlayerAttackImage() {
        if (currentWeapon.type == type_sword) {
            attackUp1 = setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setup("/player/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setup("/player/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setup("/player/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("/player/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
        }
        if (currentWeapon.type == type_axe) {
            attackUp1 = setup("/player/boy_axe_up_1", gp.tileSize, gp.tileSize * 2);
            attackUp2 = setup("/player/boy_axe_up_2", gp.tileSize, gp.tileSize * 2);
            attackDown1 = setup("/player/boy_axe_down_1", gp.tileSize, gp.tileSize * 2);
            attackDown2 = setup("/player/boy_axe_down_2", gp.tileSize, gp.tileSize * 2);
            attackLeft1 = setup("/player/boy_axe_left_1", gp.tileSize * 2, gp.tileSize);
            attackLeft2 = setup("/player/boy_axe_left_2", gp.tileSize * 2, gp.tileSize);
            attackRight1 = setup("/player/boy_axe_right_1", gp.tileSize * 2, gp.tileSize);
            attackRight2 = setup("/player/boy_axe_right_2", gp.tileSize * 2, gp.tileSize);
        }


    }

    /*  private BufferedImage loadImage(String path) throws IOException {
          InputStream is = getClass().getResourceAsStream(path);
          if (is == null) {
              throw new IOException("无法找到资源文件: " + path);
          }
          return ImageIO.read(is);
      }*/
    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);//这段代码是用来获取图片的，并且缩放到指定的大小。

        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {

        if (attacking == true) {
            attacking();
        } else if (keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";
                //System.out.println("向上");
                //  worldY -= speed; // 添加向上移动的逻辑
            } else if (keyH.downPressed == true) {
                direction = "down";
                //System.out.println("向下");
                // worldY += speed; // 添加向下移动的逻辑
            } else if (keyH.leftPressed == true) {
                direction = "left";
                //System.out.println("向左");
                //worldX -= speed; // 添加向左移动的逻辑
            } else if (keyH.rightPressed == true) {
                direction = "right";
                // System.out.println("向右");
                //worldX += speed;
            }
            //这段代码用来检测obj是否发生碰撞
            collisionOn = false;

            gp.cChecker.checkTile(this);

            //这段代码用于检测obj是否发生碰撞
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            interactMonster(monsterIndex);
            contactMonster(monsterIndex);

            int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
            // 这段代码的目的是检查实体是否与 interactiveTile 发生碰撞

            //  CHECK COLLISION
            gp.eHandler.checkEvent();
            if (collisionOn == false && keyH.enterPressed == false) {
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
            if (keyH.enterPressed == true && attackCanceled == false) {
                //这段代码的意思是，如果玩家按下enter键，并且没有取消攻击，那么就执行攻击逻辑。
                gp.playSE(7);

                attacking = true;
                spriteCounter = 0;
            }
            attackCanceled = false;
            gp.keyH.enterPressed = false;//
            //  gp.cChecker.checkTile(this);
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        if (gp.keyH.shotKeyPressed == true &&
                projectile.alive == false &&
                shotAvailCounter == 30 &&
                projectile.haveResource(this) == true) {
            //这个代码的意思是，如果玩家按下f键，并且没有取消攻击，那么就执行攻击逻辑。
            // 这段代码的意思是，设置投掷物的属性。
            projectile.set(worldX, worldY, direction, true, this);

            //减少玩家的魔法值
            projectile.subtractResource(this);

            //gp.projectileList.add(projectile);

            for (int i = 0; i < gp.projectile[1].length; i++) {
                if (gp.projectile[gp.currentMap][i] == null) {
                    gp.projectile[gp.currentMap][i] = projectile;
                    break;
                }
            }

            shotAvailCounter = 0;//这段代码是用来确保玩家可以连续攻击

            gp.playSE(10);
        }
        //this needs to be outside of key if statement
        //这段代码的玩家攻击后恢复
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if (shotAvailCounter < 30) {//确保玩家可以连续攻击
            shotAvailCounter++;
        }
        if (life > maxLife) {//确保玩家生命值不能超过最大生命值
            life = maxLife;
        }
        if (mana > maxMana) {//确保玩家生命值不能超过最大生命值
            mana = maxMana;
        }
        if (life <= 0) {
            gp.gameState = gp.gameOverState;
            gp.ui.commandNum = -1;//这段代码的意思是，
            // 当玩家生命值小于等于0时，游戏状态切换为游戏结束状态，
            // 并且commandNum设置为-1，表示当前没有选中任何命令。
            gp.playSE(12);
            gp.stopMusic();
        }

    }


    public void damageProjectile(int i) {

        if (i != 999) {
            Entity projectile = gp.projectile[gp.currentMap][i];
            projectile.alive = false;
            generateParticle(projectile, projectile);

        }

    }

    /*  public void knockBack(Entity entity, int knockBackPower) {
          entity.direction = direction;
          entity.speed += knockBackPower;
          entity.knockBack = true;
      }
  */
    public void damageInteractiveTile(int i) {//碰撞检测
        if (i != 999 && gp.iTile[gp.currentMap][i].destructible == true && gp.iTile[gp.currentMap][i].isCorrectItem(this) == true
                && gp.iTile[gp.currentMap][i].invincible == false
        ) {//检测是否可破坏

            gp.iTile[gp.currentMap][i].playSE();

            gp.iTile[gp.currentMap][i].life--;//枯树的生命值

            gp.iTile[gp.currentMap][i].invincible = true;
            //树被攻击后，树将不再可破坏,防止玩家单次攻击造成多次伤害，让游戏更加真实可行

            generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);//树被攻击后，生成树碎片
            if (gp.iTile[gp.currentMap][i].life == 0) {
                gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();//获取被破坏的方块
            }

        }
    }

    public void damageMonster(int i, Entity attacker, int attack, int knockBackPower) {//攻击方法
        if (i != 999) {
            if (gp.monster[gp.currentMap][i].invincible == false) {
                gp.playSE(5);

                if (knockBackPower > 0) {
                    setKnockBack(gp.monster[gp.currentMap][i], attacker, knockBackPower);
                }
                int damage = attack - gp.monster[gp.currentMap][i].defense;//攻击力减去防御力
                if (damage < 0) {
                    damage = 0;
                }
                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.addMessage(damage + " damage!");
                gp.player.life += 2;
                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();//当玩家攻击史莱姆后，史莱姆会触发退后方法
                if (gp.monster[gp.currentMap][i].life <= 0) {//当史莱姆的血量小于等于0时，会触发死亡方法
                    gp.monster[gp.currentMap][i].dying = true;//死亡

                    gp.ui.addMessage("Killed the " + gp.monster[gp.currentMap][i].name + "!");
                    gp.ui.addMessage("Exp + " + gp.monster[gp.currentMap][i].exp + "!");
                    exp += gp.monster[gp.currentMap][i].exp;
                    CheckLevelUp();
                }
            }
        }
    }

    public void CheckLevelUp() {
        if (exp >= nextLevelExp) {//当经验值大于等于下一级经验值时，触发升级
            level++;
            nextLevelExp = nextLevelExp * 2;
            maxLife += 2;

            strength++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();

            gp.playSE(8);
            gp.gameState = gp.dialogueState;

            startDialogue(this, 0);

        }
    }

    public void contactMonster(int i) {//这段代码的意思是
        if (i != 999) {
            if (invincible == false && gp.monster[gp.currentMap][i].dying == false) {
                gp.playSE(6);
                int damage = gp.monster[gp.currentMap][i].attack - defense;//攻击力减去防御力
                if (damage < 0) {
                    damage = 0;
                }
                life -= damage;
                invincible = true;//
            }
        }
    }

    public void interactMonster(int monsterIndex) {

    }

    public void pickUpObject(int i) {
        if (i != 999) {
            // 检查对象是否可交互
            if (gp.obj[gp.currentMap][i].type == type_pickupOnly) {
                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;
            } else if (gp.obj[gp.currentMap][i].type == type_obstacle) {
                if (keyH.enterPressed == true) {

                    attackCanceled = true;
                    gp.obj[gp.currentMap][i].interact();
                }
            } else {
                String text;
                if (canObtainItem(gp.obj[gp.currentMap][i]) == true) {
                    //inventory.add(gp.obj[gp.currentMap][i]);
                    gp.playSE(1);
                    text = "You got " + gp.obj[gp.currentMap][i].name + "!";
                } else {
                    text = "You cannot carry anymore!";
                }
                gp.ui.addMessage(text);
                gp.obj[gp.currentMap][i] = null;
            }
        }
    }//这个方法可改变捡起道具后的数值属性

    public void interactNPC(int i) {//这个方法可改变与npc的交互逻辑

        if (gp.keyH.enterPressed == true) {
            if (i != 999) {
                //这段代码enter  键被按下时，会触发npc的speak方法，并进入对话状态。
                //System.out.println("you are hitting an npc");
                attackCanceled = true;
                // gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }/* else {
                gp.playSE(7);
                attacking = true;
            }*/
        }
    }

    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow);

        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex);
            if (selectedItem.type == type_sword || selectedItem.type == type_axe) {

                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerAttackImage();
            }
            if (selectedItem.type == type_shield) {
                currentShield = selectedItem;
                defense = getDefense();
            }
            if (selectedItem.type == type_light) {
                if (currentLight == selectedItem) {
                    currentLight = null;
                } else {
                    currentLight = selectedItem;
                }
                lightUpdated = true;

            }
            if (selectedItem.type == type_consumable) {//检测物品是否可消耗
                if (selectedItem.use(this) == true) {

                    if (selectedItem.amount > 1) {
                        selectedItem.amount--;
                    }
                    inventory.remove(itemIndex);
                } else {
                    inventory.remove(itemIndex);
                }
                //这段代码的作用是删除物品列表中的物品，以便
                // 绘制下一个物品，从而绘制下一个物品，以达到。
            }
        }
    }

    public int searchItemInInventory(String itemName) {

        int itemIndex = 999;

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).name.equals(itemName)) {
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }

    public boolean canObtainItem(Entity item) {
        boolean canObtain = false;

        if (item.stackable == true) {
            int index = searchItemInInventory(item.name);

            if (index != 999) {
                inventory.get(index).amount++;
                canObtain = true;
            } else {
                if (inventory.size() != maxInventorySize) {
                    inventory.add(item);
                    canObtain = true;
                }
            }
        } else {
            if (inventory.size() != maxInventorySize) {
                inventory.add(item);
                canObtain = true;
            }
        }
        return canObtain;
    }

    /*    public void pickupObject(int i) {

             if (i != 999) {
             String objectName = gp.obj[i].name;
             switch (objectName) {
                 case "key":
                     hasKey++;
                     gp.obj[i] = null;
                     System.out.println("You have " + hasKey + " key(s)");
                     break;
                     case "Chest":
                         break;
                 case "Door":
                     if (hasKey > 0) {
                         gp.obj[i] = null;
                         hasKey--;
                         System.out.println("You have " + hasKey + " key(s)");

                     }
                     break;
             }
             }
         }*/
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        int tempScreenX = screenX;
        int tempScreenY = screenY;
        switch (direction) {
            case "up":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                }
                if (attacking == true) {
                    tempScreenY = screenY - gp.tileSize;
                    if (spriteNum == 1) {
                        image = attackUp1;
                    }
                    if (spriteNum == 2) {
                        image = attackUp2;
                    }
                }
                break;
            case "down":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                }
                if (attacking == true) {
                    if (spriteNum == 1) {
                        image = attackDown1;
                    }
                    if (spriteNum == 2) {
                        image = attackDown2;
                    }
                }

                break;
            case "left":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                }
                if (attacking == true) {
                    tempScreenX = screenX - gp.tileSize;//这段代码的
                    // 意思是让玩家攻击时，图片向左移动一个tileSize。
                    if (spriteNum == 1) {
                        image = attackLeft1;
                    }
                    if (spriteNum == 2) {
                        image = attackLeft2;
                    }
                }

                break;
            case "right":
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                }
                if (attacking == true) {
                    //这段代码的意思是让玩家攻击时，
                    // 图片向右移动一个tileSize。
                    if (spriteNum == 1) {
                        image = attackRight1;
                    }
                    if (spriteNum == 2) {
                        image = attackRight2;
                    }
                }
                break;
        }
        if (invincible == true) {//透明
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        // g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        g2.drawImage(image, tempScreenX, tempScreenY, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        /*    g2.setFont(new Font("x12y16pxMaruMonica", Font.PLAIN, 26));
        g2.setColor(Color.white);
        g2.drawString("Invincible :"+invincibleCounter,10,400);
  */
    }

}