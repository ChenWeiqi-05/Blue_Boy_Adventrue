package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Boots extends SuperObject{
    GamePanel gp;
    public OBJ_Boots( GamePanel gp)
    {
        try {
            this.gp = gp;
            name = "Boots";
            image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
            uTool.scaleImage( image,gp.tileSize,gp.tileSize);

        }catch (IOException e) {

            throw new RuntimeException(e);

        }

        collision = true;
    }
}
