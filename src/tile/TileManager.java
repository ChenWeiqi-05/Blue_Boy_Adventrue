package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];

    boolean drawPath = true;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        //   tile = new Tile[10];
        tile = new Tile[50];
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/worldV3.txt", 0);
        loadMap("/maps/interior01.txt", 1);
    }

    public void getTileImage()//这段代码是用来获取图片的
    {
       /*setup(0, "grass", false);
        setup(1, "wall", true);
        setup(2, "water", true);
        setup(3, "earth", false);
        setup(4, "tree", true);
        setup(5, "sand", false);*/

        setup(0, "grass00", false);
        setup(1, "grass00", false);
        setup(2, "grass00", false);
        setup(3, "grass00", false);
        setup(4, "grass00", false);
        setup(5, "grass00", false);
        setup(6, "grass00", false);
        setup(7, "grass00", false);
        setup(8, "grass00", false);
        setup(9, "grass00", false);
        setup(10, "grass00", false);
        setup(11, "grass01", false);

        setup(12, "water00", true);
        setup(13, "water01", true);
        setup(14, "water02", true);
        setup(15, "water03", true);
        setup(16, "water04", true);
        setup(17, "water05", true);
        setup(18, "water06", true);
        setup(19, "water07", true);
        setup(20, "water08", true);
        setup(21, "water09", true);
        setup(22, "water10", true);
        setup(23, "water11", true);
        setup(24, "water12", true);
        setup(25, "water13", true);


        setup(26, "road00", false);
        setup(27, "road01", false);
        setup(28, "road02", false);
        setup(29, "road03", false);
        setup(30, "road04", false);
        setup(31, "road05", false);
        setup(32, "road06", false);
        setup(33, "road07", false);
        setup(34, "road08", false);
        setup(35, "road09", false);
        setup(36, "road10", false);
        setup(37, "road11", false);
        setup(38, "road12", false);

        setup(39, "earth", false);
        setup(40, "wall", true);
        setup(41, "tree", true);

        setup(42, "hut", false);
        setup(43, "floor01", false);
        setup(44, "table01", true);
    }

    public void setup(int index, String imageName, boolean collision)//这段代码用来设置地图
    {
        UtilityTool uTool = new UtilityTool();
        try {
            tile[index] = new Tile();
            //    tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles2/" + imageName + ".png"));
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void loadMap(String filePath, int map)//这段代码用来加载地图
    {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {//这段代码用来读取地图

                String line = br.readLine();

                while (col < gp.maxWorldCol) {//当读取到一行数据时，将数据存储在二维数组中，并继续读取下一行数据
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);//将字符串转换为整数
                    mapTileNum[map][col][row] = num;
                    col++;
                }//不要把下面的if放在前面的括号里面,不然程序会报错！！！！！！
                if (col == gp.maxWorldCol) {//当读取到一行数据时，将数据存储在二维数组中，并继续读取下一行数据
                    col = 0;
                    row++;//重置行和列
                }

            }
            br.close();
        } catch (Exception e) {
        }

    }

    public void draw(Graphics2D g2)//这段代码用来绘制砖块图片
    {
        //      g2.drawImage(tile[0].image,0,0,gp.tileSize,gp.tileSize,null);//绘制草砖块

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {//这段代码用来绘制地图
            //这个while循环开始绘制地图的所有图块，但是这样会带来额外的性能开销，所以我们优化了图块的绘制。
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;

            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&//这是一段优化代码，用来判断屏幕是否在屏幕范围内
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&//
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY
            ) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            }
            worldCol++;

            if (worldCol == gp.maxWorldCol) {//这条代码实现了循环绘制地图
                worldCol = 0;//重置列

                worldRow++;//重置行

            }
        }
        if (drawPath == true) {
            g2.setColor(new Color(255, 0, 0, 70));
            for (int i = 0; i < gp.pFinder.pathList.size(); i++) {

                int worldX = gp.pFinder.pathList.get(i).col*gp.tileSize;
                int worldY = gp.pFinder.pathList.get(i).row*gp.tileSize;

                int screenX = worldX - gp.player.worldX + gp.player.screenX;
                int screenY = worldY - gp.player.worldY + gp.player.screenY;

               g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
            }

        }
    }
}
