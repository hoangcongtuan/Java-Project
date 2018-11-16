package com.tuanhc;

import org.w3c.dom.css.RGBColor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NenAnh extends JFrame {
    public static void main(String[] args) {
        try {
            new NenAnh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    BufferedImage image;

    public NenAnh() throws IOException {
        image = ImageIO.read(new File("sample.jpg"));

        int [][] data = new int[image.getWidth()* image.getHeight()][];
        int id = 0;
        for(int y = 0; y < image.getHeight(); y++) {
            for(int x = 0; x < image.getWidth(); x++) {
                int color = image.getRGB(x, y);
                int b = color & 0xFF;
                int g = (color >> 8) & 0xFF;
                int r = (color >> 16) & 0xFF;
                data[id] = new int[]{r, g, b};
                id++;
            }
        }


        K_Mean k_mean = new K_Mean(data, 4);

        int i = 0;
        for(int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int r = k_mean.c[k_mean.id[i]][0];
                int g = k_mean.c[k_mean.id[i]][1];
                int b = k_mean.c[k_mean.id[i]][2];
                int color = b + (g << 8) + (r << 16);
                image.setRGB(x, y, color);
                i++;
            }
        }

        this.setTitle("Nen Anh");
        this.setSize(image.getWidth(), image.getHeight());

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.paint(getGraphics());
    }

    @Override
    public void paint(Graphics g) {
//        super.paint(g);
        g.drawImage(image, 0, 0, null);
    }
}
