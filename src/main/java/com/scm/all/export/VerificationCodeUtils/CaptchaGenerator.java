package com.scm.all.export.VerificationCodeUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * 验证码生成器
 */
public class CaptchaGenerator {

    public static int WIDTH = 200;
    public static int HEIGHT = 50;
    public static int FONT_SIZE = 36;
    public static int X = 50;
    public static int Y = 40;
    public static String CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new Random();

    /**
     * 设置图片的长度
     * @param length 设置的长度
     * @return 返回验证码文本
     */
    public static String generateRandomText(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        String text = sb.toString();
        sb = null;
        return text;
    }
    /**
     * 设置随机字符串
     * @param text 设置字符集 转图片
     * @return 返回图片 BufferedImage
     */
    public static BufferedImage generateCaptchaImage(String text) {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // 背景颜色
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        // 文字颜色
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("微软雅黑", Font.BOLD, FONT_SIZE));

        // 绘制文字
        g2d.drawString(text, X, Y);

        // 添加干扰线
        for (int i = 0; i < 5; i++) {
            g2d.setColor(new Color(RANDOM.nextInt(256), RANDOM.nextInt(256), RANDOM.nextInt(256)));
            g2d.drawLine(RANDOM.nextInt(WIDTH), RANDOM.nextInt(HEIGHT), RANDOM.nextInt(WIDTH), RANDOM.nextInt(HEIGHT));
        }

        g2d.dispose();
        return image;
    }
    /**
     * 将BufferedImage转换为字节数组（推荐PNG格式，无压缩失真，适合验证码）
     * @param bufferedImage 验证码图片对象
     * @return 图片字节数组
     */
    public static byte[] bufferedImageToByteArray(BufferedImage bufferedImage)  {
        // 1. 创建内存字节输出流
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            // 2. 将BufferedImage写入流（格式：PNG，也可改为JPG/GIF，推荐PNG）
            boolean isWriteSuccess = ImageIO.write(bufferedImage, "PNG", outputStream);
            if (!isWriteSuccess) {
                throw new IOException("图片写入流失败，不支持的图片格式：PNG");
            }
            // 3. 转换为字节数组并返回
            return outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
