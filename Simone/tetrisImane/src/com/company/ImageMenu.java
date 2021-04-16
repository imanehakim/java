package com.company;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageMenu {

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File("data" + path));

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;

    }
}
