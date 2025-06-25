package environment;

import main.GamePanel;

import java.awt.*;

public class EnvironmentManage {
    GamePanel gp;
    Lighting lighting;

    public EnvironmentManage(GamePanel gp) {
        this.gp = gp;

    }

    public void setup() {
        lighting = new Lighting(gp, 500);

    }
    public void draw(Graphics2D g2) {

        lighting.draw(g2);

    }

}
