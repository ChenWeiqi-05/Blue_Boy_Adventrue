package main;

import Entity.Entity;

public class CollisionChecker {
    public GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    /*  public void checkTile(Entity entity) {

          // 检查关键对象是否为空
          if (entity == null || entity.soildArea == null || gp == null) {
              return; // 或抛出明确的异常
          }

          // 原有计算逻辑..

          int entityLeftWorldX = entity.x + entity.soildArea.x;
          int entityRightWorldX = entity.x + entity.soildArea.x + entity.soildArea.width;

          int entityTopWorldY = entity.y + entity.soildArea.y;
          int entityBottomWorldY = entity.y + entity.soildArea.y + entity.soildArea.height;

          int entityLeftCol = entityLeftWorldX / gp.tileSize;
          int entityRightCol = entityRightWorldX / gp.tileSize;

          int entityTopRow = entityTopWorldY / gp.tileSize;
          int entityBottomRow = entityBottomWorldY / gp.tileSize;

          int tileNum1, tileNum2;

          switch (entity.direction) {
              case "up":
                  entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                  tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                  tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];

                  if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {

                      entity.collisoinOn = true;
                  }
                  break;
              case "down":
                  entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;

                  tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

                  tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                  if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                      entity.collisoinOn = true;
                  }
                  break;
              case "left":
                  entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                  tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                  tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                  if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true
                  ) {
                      entity.collisoinOn = true;
                  }

                  break;
              case "right":
                  entityRightCol = (entityRightWorldX - entity.speed) / gp.tileSize;
                  tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                  tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                  if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                      entity.collisoinOn = true;
                  }
                  break;
          }


      }
  */
   /* public int checkObject(Entity entity, boolean player) {
        int index = 999;
       *//*  for (int i = 0; i < gp.obj.length; i++) {

            if (gp.obj[i] != null) {
                entity.soliArea.x = entity.worldX + entity.soliArea.x;

                entity.soliArea.y = entity.worldY + entity.soliArea.y;
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.soliArea.y -= entity.speed;
                      if (entity){

                      }
                        break;
                }

            }

        }
*//*

        return index;

    }
*/
    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        // 在获取数组值前先检查索引是否有效

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }

                break;
            case "down"://这段代码的意义
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }

                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }

                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] != null) {

                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;

                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;

                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;

                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;

                        break;
                }
                if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                    System.out.println("right collision!");
                    if (gp.obj[gp.currentMap][i].collision == true) {
                        entity.collisionOn = true;
                    }
                    if (player == true) {
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
            }

        }
        return index;
    }

    public int checkEntity(Entity entity, Entity[][] target) {
        //这段代码通过遍历数组来检查实体与目标之间的碰撞。
        // 如果两个实体的碰撞区域相交，则返回目标数组中该实体的索引。

        int index = 999;
        for (int i = 0; i < target[1].length; i++) {
            if (target[gp.currentMap][i] != null) {

                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;

                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;

                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;

                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;

                        break;
                }
                if (entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
                    if (target[gp.currentMap][i] != entity) {
                        ///System.out.println("npc从你的上面碰到了你");
                        entity.collisionOn = true;
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity) {

        boolean contactPlayer = false;
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch (gp.player.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;

                break;
            case "down":
                entity.solidArea.y += entity.speed;

                break;
            case "left":
                entity.solidArea.x -= entity.speed;

                break;
            case "right":
                entity.solidArea.x += entity.speed;
        }
        if (entity.solidArea.intersects(gp.player.solidArea)) {

            entity.collisionOn = true;
            contactPlayer = true;

        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

       return contactPlayer;
    }

}

