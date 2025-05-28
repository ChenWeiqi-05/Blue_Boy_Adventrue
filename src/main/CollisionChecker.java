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

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }

                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }

                 break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }

                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }

                break;

        }

    }
}

