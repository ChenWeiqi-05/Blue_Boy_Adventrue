package object;

import object.SuperObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class OBJ_Door extends SuperObject {

    public OBJ_Door(){
      name = "Door";
        try {

         image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));

        }catch (IOException e) {

            throw new RuntimeException(e);

        }

        collision = true;
    }
}
