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
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];

        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();

        loadMap("/maps/world01.txt");
    }

    public void getTileImage()//这段代码是用来获取图片的
    {
        setup(0,"grass",false);
        setup( 1,"wall",true);
        setup( 2,"water",true);
        setup( 3,"earth",false);
        setup( 4,"tree",true);
        setup( 5,"sand",false);

    }
    public void setup (int index,String imageName,boolean collision)//这段代码用来设置地图
    {
        UtilityTool uTool  = new UtilityTool();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imageName +".png"));
            tile[index].image = uTool.scaleImage(tile [index].image,gp.tileSize,gp.tileSize);
            tile[index].collision = collision;



        }catch (IOException e){
            e.printStackTrace();
        }

    }


    public void loadMap(String filePath)//这段代码用来加载地图
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
                    mapTileNum[col][row] = num;
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
            int tileNum = mapTileNum[worldCol][worldRow];

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
    }
}
