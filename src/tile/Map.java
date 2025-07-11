package tile;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Map extends TileManager {
    GamePanel gp;
    BufferedImage WorldMap[];
    public boolean miniMapOn = false;

    public Map(GamePanel gp) {
        super(gp);
        this.gp = gp;
        createWorldMap();
    }

    public void createWorldMap() {
        WorldMap = new BufferedImage[gp.maxMap];
        int worldMapWidth = gp.tileSize * gp.maxWorldCol;
        int worldMapHeight = gp.tileSize * gp.maxWorldRow;
        for (int i = 0; i < gp.maxMap; i++) {//循环创建地图

            WorldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);

            Graphics2D g2 = WorldMap[i].createGraphics();
            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                int tileNum = mapTileNum[i][col][row];

                int x = gp.tileSize * col;
                int y = gp.tileSize * row;
                g2.drawImage(tile[tileNum].image, x, y, null);

                col++;
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }

            g2.dispose();
        }
    }

    public void drawFullMapScreen(Graphics2D g2) {

        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int width = 500;
        int height = 500;
        int x = gp.screenWidth / 2 - width / 2;
        int y = gp.screenHeight / 2 - height / 2;

        g2.drawImage(WorldMap[gp.currentMap], x, y, width, height, null);


        double scale = (double) (gp.tileSize * gp.maxWorldRow) / width;
        int playerX = (int) (x + gp.player.worldX / scale);
        int playerY = (int) (y + gp.player.worldY / scale);
        int playerSize = (int) (gp.tileSize * 2 / scale);

        g2.drawImage(gp.player.down1, playerX, playerY, playerSize, playerSize, null);
        g2.setFont(gp.ui.maruMonica.deriveFont(32F));
        g2.setColor(Color.white);

        g2.drawString("Press M to close " , 750, 550);
    }
    public void drawMiniMap(Graphics2D g2){
        if (miniMapOn  == true){

            int width = 300;
            int height = 300;
            int x = gp.screenWidth - width - 20;
            int y = 50;

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2.drawImage(WorldMap[gp.currentMap], x, y, width, height, null);

            double scale = (double) (gp.tileSize * gp.maxWorldCol) / width;
            int playerX = (int) (x + gp.player.worldX / scale);
            int playerY = (int) (y + gp.player.worldY / scale);
            int playerSize = (int) (gp.tileSize * 2 / scale);
            g2.drawImage(gp.player.down1, playerX, playerY, playerSize, playerSize, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        }
    }

}
