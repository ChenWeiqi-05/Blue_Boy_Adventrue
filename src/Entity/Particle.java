package Entity;

import main.GamePanel;

import java.awt.*;

public class Particle extends Entity {//这个类是粒子类
//一下是粒子类中的属性
    Entity generator;
    Color color;
    int size;
    int xd;
    int yd;

    public Particle(GamePanel gp, Entity generator, Color color, int size, int speed, int maxLife, int xd, int yd) {
        super(gp);
        this.generator = generator;
        this.color = color;
        this.size = size;
        this.speed = speed;
        this.maxLife = maxLife;
        this.xd = xd;
        this.yd = yd;

        life = maxLife;
        // int offset = (int) (Math.random()*5);
        int offset =  (gp.tileSize/2)-(size/2);//粒子的偏移量
        worldX = generator.worldX+offset;
        worldY = generator.worldY+offset;
    }
    public void update(){
        //这段代码的作用是让粒子运动起来，
        // 并且让粒子的透明度逐渐变小。
         life--;

         if (life < maxLife/3){
             yd++;
         }
        worldX += xd* speed ;
        worldY += yd* speed;

        if (life ==  0){
         alive = false;
        }
    }
    public void draw(Graphics2D g2){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2.setColor(color);//绘制粒子
        g2.fillRect(screenX, screenY, size, size);//填充矩形

    }

}
