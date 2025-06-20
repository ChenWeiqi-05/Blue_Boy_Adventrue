package main;

import Entity.Entity;
import Entity.Player;
import InteractiveTile.InteractiveTile;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;//48
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;//768
    public final int screenHeight = tileSize * maxScreenRow;//576
    public int maxWorldCol = 50;
    public int maxWorldRow = 50;

    public int currentMusic = 0;  // 当前播放的音乐编号

    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;//
    Graphics2D g2;//画笔

    public boolean fullScreenOn = false;
    int FPS = 60;
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);//这条代码用来设置键盘监听
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);

    public EventHandler eHandler = new EventHandler(this);

    Config config = new Config(this);
    Thread gameThread;

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public Player player = new Player(this, keyH);
    public Entity obj[] = new Entity[20];
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[100];

    public InteractiveTile iTile[] = new InteractiveTile[50];//创建一个 interactiveTile 数组
    public ArrayList<Entity> projectileList = new ArrayList<>();

    public ArrayList<Entity> particleList = new ArrayList<>();
    ArrayList<Entity> entityList = new ArrayList<>();//创建a一个实体列表

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int optionState = 5;

    public final int gameOverState = 6;

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

        aSetter.setInteractiveTile();

        //playMusic(0);
        gameState = titleState;//游戏状态的设置

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);//创建一个临时屏幕

        g2 = (Graphics2D) tempScreen.getGraphics();//创建一个Graphics2D对象

        if (fullScreenOn == true) {
            setFullScreen();

        }

    }

    public void retry() {
        player.setDefaultPositions();
        player.restoreLifeAndMana();
        aSetter.setNPC();
        aSetter.setMonster();

    }

    public void restart() {
        player.setDaultValues();
      /*  player.setDefaultPositions();
        player.restoreLifeAndMana();*/
        player.setItems();

        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();

        aSetter.setInteractiveTile();
    }

    public void setFullScreen() {
        //GET LOCAL SCREEN DEVICE
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(Main.window);

        //get Full screen width and height
        screenWidth2 = Main.window.getWidth();
        screenHeight2 = Main.window.getHeight();

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
                /* repaint();*/
                drawToTempScreen();//draw every thing to buffered image
                drawToScreen(); //draw the buffered image to screen
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
            for (int i = 0; i < monster.length; i++) {//循环遍历monster数组
                if (monster[i] != null) {//如果怪物不为空，则执行下面的代码
                    if (monster[i].alive == true && monster[i].dying == false) {//如果怪物存活且没有死亡，则执行下面的代码
                        monster[i].update();//绘制怪物
                    }
                    if (monster[i].alive == false) {//如果怪物死亡，则将其从数组中删除

                        monster[i].checkDrop();//掉落物
                        monster[i] = null;
                    }
                }
            }
            for (int i = 0; i < projectileList.size(); i++) {//循环遍历projectileList数组，以此绘制projectileList
                if (projectileList.get(i) != null) {
                    if (projectileList.get(i).alive == true) {
                        projectileList.get(i).update();//绘制projectileList
                    }
                    if (projectileList.get(i).alive == false) {
                        projectileList.remove(i);
                    }
                }
            }
            for (int i = 0; i < particleList.size(); i++) {//循环遍历 particleList数组，以此绘制particleList中的元素
                if (particleList.get(i) != null) {
                    if (particleList.get(i).alive == true) {
                        particleList.get(i).update();//绘制 particleList
                    }
                    if (particleList.get(i).alive == false) {
                        particleList.remove(i);
                    }
                }
            }


            for (int i = 0; i < iTile.length; i++) {
                if (iTile[i] != null) {

                    iTile[i].update();//更新 interactiveTile
                }
            }
        }
        if (gameState == pauseState) {

        }
    }

    public void drawToTempScreen() {

        long drawStart = 0;
        if (keyH.showDebugText == true) {
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

            for (int i = 0; i < iTile.length; i++) {//循环遍历 interactiveTile 数组，以此绘制 interactiveTile

                if (iTile[i] != null) {//如果 interactiveTile 不为空，则执行下面的代码
                    iTile[i].draw(g2);
                }
            }

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
            for (int i = 0; i < projectileList.size(); i++) {//循环遍历projectileList数组，以此绘制projectileList
                if (projectileList.get(i) != null) {
                    entityList.add(projectileList.get(i));
                }
            }
            for (int i = 0; i < particleList.size(); i++) {//循环遍历 particleList数组，以此绘制 particleList
                if (particleList.get(i) != null) {
                    entityList.add(particleList.get(i));
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
            entityList.clear();//清空实体列表
            ui.draw(g2);
            // 保持背景绘制
            // 确保UI绘制
        }

        if (keyH.showDebugText == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;

            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.white);
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WordX" + player.worldX, x, y);
            y += lineHeight;
            g2.drawString("WordY" + player.worldY, x, y);
            y += lineHeight;
            g2.drawString("Col" + (player.worldX + player.solidArea.x) / tileSize, x, y);
            y += lineHeight;
            g2.drawString("Row" + (player.worldY + player.solidArea.y) / tileSize, x, y);
            y += lineHeight;

            g2.drawString("Draw :" + passed, x, y);
        }

    }

    public void drawToScreen() {

        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
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
