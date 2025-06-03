package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

public class OBJ_Key extends SuperObject{

    GamePanel  gp;
    public OBJ_Key(GamePanel gp)
    {
        this.gp = gp;
        name = "Key";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
            uTool.scaleImage( image,gp.tileSize,gp.tileSize);

//            InputStream is = getClass().getResourceAsStream("/object/key.png");
//            if (is == null) {
//                throw new IllegalArgumentException("Resource not found: /object/key.png");
//            }
//            image = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;

    }
}
