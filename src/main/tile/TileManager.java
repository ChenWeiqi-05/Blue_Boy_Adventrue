package main.tile;

import main.GamePanel;

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
        this.gp = gp;// 初始化GamePanel

        tile = new Tile[10];

        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        // 在访问 mapTileNum 前，先检查 entityTopRow 和 entityLeftCol 的合法性


        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
           // tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadMap(String filePath) {

        try {

            InputStream is = getClass().getResourceAsStream(filePath);// 读取文件流

            BufferedReader br = new BufferedReader(new InputStreamReader(is));// 创建缓冲读取流

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {// 循环读取mapTileNum数组

                String line = br.readLine();// 读取一行字符串

                while (col < gp.maxScreenCol) {// 循环读取一行字符串

                    String numbers[] = line.split(" ");// 将字符串数组按照空格分割

                    int num = Integer.parseInt(numbers[col]);// 将字符串转换为整数

                    mapTileNum[col][row] = num; //  将数字保存到mapTileNum数组中

                    col++;
                }
                if (col == gp.maxScreenCol) {//  判断是否读取完一行

                    col = 0;
                    row++;
                }
            }
            br.close();// 关闭流

        } catch (IOException e) {

            System.err.println("Error loading map: " + e.getMessage());
        }


    }


    public void draw(Graphics2D g2) {//  绘制地图

//        int worldCol = 0;//  列
//        int worldRow = 0;//  行

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;


        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {// 循环绘制地图

            int tileNum = mapTileNum[col][row];// 获取当前位置的tile编号
//
//            int worldX = worldCol * gp.tileSize;// 计算当前位置的x坐标
//
//            int worldY = worldCol * gp.tileSize;// 计算当前位置的y坐标
//
//            int screenX = worldX - gp.player.worldX + gp.player.screenX;// 计算当前位置的x坐标
//
//            int screenY = worldY - gp.player.worldY + gp.player.screenY; // 计算当前位置的y坐标
//

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);//  绘制tile

//
//            if (worldX > gp.player.screenX - gp.player.screenX &&
//                    worldX < gp.player.worldX + gp.player.screenX &&
//                    worldY > gp.player.screenY - gp.player.screenY &&
//                    worldY < gp.player.worldY + gp.player.screenY
//
//            ) {
//
//            }
            col++;//  列加1

            x += gp.tileSize;
            if (col == gp.maxScreenCol) {////  判断是否绘制完一行
                col = 0;//  列归零
                x = 0;
                row++;//  行加1
                y += gp.tileSize;
            }
        }
    }

}
