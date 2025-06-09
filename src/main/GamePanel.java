package main;

import Entity.Entity;
import Entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;//48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;//
    public final int screenHeight = tileSize * maxScreenRow;
    public int maxWorldCol = 50;
    public int maxWorldRow = 50;

    public int currentMusic = 0;  // 当前播放的音乐编号

    int FPS = 60;
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);//这条代码用来设置键盘监听
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);

    public EventHandler eHandler = new EventHandler(this);

    Thread gameThread;

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public Player player = new Player(this, keyH);
    public Entity obj[] = new Entity[10];
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[20];
    ArrayList<Entity> entityList = new ArrayList<>();//创建一个实体列表

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int gameOverState = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        //playMusic(0);
        gameState = titleState;//游戏状态的设置
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {

        if (gameState == playState) {//如果游戏状态为游戏状态，则执行下面的代码
            player.update();
            for (int i = 0; i < npc.length; i++) {//循环遍历npc数组
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
            for (int i = 0; i < monster.length; i++){
                if  (monster[i] != null) {
                    monster[i].update();
                }
            }
        }
        if (gameState == pauseState) {

        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        long drawStart = 0;
        if (keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }
        //绘制对象
        //TITLE SCREEN
        if (gameState == titleState) {
            ui.draw(g2);
        }
        //PLAY SCREEN
        else {
            tileM.draw(g2);

            entityList.add(player);
            for (int i = 0; i < npc.length; i++) {//循环遍历npc数组,以此绘制npc
                if (npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }
            for (int i = 0; i < obj.length; i++) {//循环遍历obj数组，以此绘制obj

                if (obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }
            for (int i = 0; i < monster.length; i++) {//循环遍历monster数组，以此绘制monster
                if (monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }
            Collections.sort(entityList, new Comparator<Entity>() {//  创建一个比较器,用来排序实体列表
                @Override//  比较两个实体的绘制顺序
                public int compare(Entity e1, Entity e2) {

                    int result = Integer.compare(e1.worldY, e2.worldY);//此段代码的作用是把e1.worldY
                    // 和e2.worldY进行比较，并返回一个整数。如果e1.worldY小于e2.worldY，则返回负数。如果e1.worldY等于e2.worldY
                    return result;
                }
            });
            // 绘制实体列表
            for (int i = 0; i < entityList.size(); i++) {//绘制实体列表
                entityList.get(i).draw(g2);

            }
            for (int i = 0; i < entityList.size(); i++) {//这段代码的作用是删除实体列表中的实体，以便绘制下一个实体，从而绘制下一个实体。

                entityList.remove(i);

            }
            ui.draw(g2);
            // 保持背景绘制
            // 确保UI绘制
        }

        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);

            g2.drawString("Draw :" + passed, 10, 400);
            System.out.println(passed);
        }
        g2.dispose();
    }

    public void playMusic(int i) {
        currentMusic = i;  // 记录当前播放的音乐
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        music.setFile(i);
        music.play();
    }
}
