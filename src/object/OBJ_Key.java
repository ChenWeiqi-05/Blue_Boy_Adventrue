package object;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

public class OBJ_Key extends SuperObject{

    public OBJ_Key()
    {
        name = "Key";
        try {
         image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
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
