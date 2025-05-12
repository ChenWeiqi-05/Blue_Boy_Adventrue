package main;

import Entity.Player;
import main.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
 public    final int maxScreenCol = 16;
 public    final int maxScreenRow = 12;
    public  final int screenWidth = tileSize * maxScreenCol;//
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 50;

    public final int maxWorldRow = 50;

    public  final int worldWidth = tileSize * maxWorldCol;//

    public  final int worldHeight = tileSize * maxWorldRow;//576


    int FPS = 60;


    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
  public   Player player = new Player(this, keyH);
    int playerX = 100;
    int playerY = 100;

    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();

    }

    /*  @Override
  public void run() {
//GameLoop

        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

          //  System.out.println("The game loop is running");
          //  long currentTime = System.nanoTime();
           // System.out.println(currentTime);



            update();


            repaint();


            try {

                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }*/
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }

        }
    }

    public void update() {
//        if (keyH.upPressed == true) {
//            playerY -= playerSpeed;
//        } else if (keyH.downPressed == true) {
//            playerY += playerSpeed;
//        } else if (keyH.leftPressed == true) {
//            playerX -= playerSpeed;
//        } else if (keyH.rightPressed == true) {
//            playerX += playerSpeed;
//        }

        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        player.draw(g2);

        g2.dispose();
    }
}
