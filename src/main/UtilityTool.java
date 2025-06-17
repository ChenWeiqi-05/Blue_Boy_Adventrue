package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {//这段代码的作用是缩放图片
    public BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);

        g2.dispose();

        return scaledImage;
    }
}
