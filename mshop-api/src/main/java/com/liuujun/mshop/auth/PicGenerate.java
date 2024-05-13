package com.liuujun.mshop.auth;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

/**
 * @author zhouyi
 */
public class PicGenerate {

    private static int FONT_SIZE = 36;
    private static int MARGIN = 5;
    private static int LINE_SIZE = 10;

    public static String generate(String code) throws IOException {
        int length = code.length();
        int width = length * MARGIN * 2 + length * FONT_SIZE;
        int height = MARGIN*2 + FONT_SIZE;
        char[] charArray = code.toCharArray();


        Random random = new Random();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0, width, height);


        int x = MARGIN*2;
        int y = FONT_SIZE;
        for (char c : charArray) {
            graphics.setColor(randomColor());
            graphics.setFont(randomFont());
            graphics.drawString(String.valueOf(c), x,y);
            x += MARGIN*2+FONT_SIZE;
        }

        for (int i = 0; i < LINE_SIZE; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);

            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);

            graphics.setColor(randomColor());
            graphics.drawLine(x1, y1, x2, y2);
        }
        if (random.nextBoolean()){
            int cols = random.nextInt(length) + 1;
            int colWidth = width / cols;
            for (int i = 1; i < cols; i++) {
                int x1 = colWidth * i;
                int y1 = 0;
                int y2 = height;

                graphics.setColor(randomColor());
                graphics.drawLine(x1, y1, x1, y2);
            }
        }

        graphics.dispose();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.getEncoder().encodeToString(byteArray);
    }

    private static Color randomColor(){
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r, g, b);
    }

    private static Font randomFont(){
        Random random = new Random();
        int style = random.nextInt(3);
        Font font = new Font("Arial", style, FONT_SIZE);
        return font;
    }

    public static void main(String[] args) throws IOException {
        generate("7888");
    }
}
