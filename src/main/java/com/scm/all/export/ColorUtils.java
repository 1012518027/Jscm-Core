package com.scm.all.export;

import java.awt.*;

/**
 * 颜色工具转换类
 */
public class ColorUtils {
    /**
     * RGB转RGBA 颜色转换
     * @param rgb 颜色例如： #0088FF
     * @param alpha 透明度
     * @return 返回十进制颜色值
     */
    public static long RGBA(String rgb,int alpha){
        String alphaHex = ByteUtils.intToHex(alpha);
        String data ="";
        if(rgb.toUpperCase().indexOf("#")==0){
            data = rgb.toUpperCase().substring(1);
            String hexR = data.toUpperCase().substring(0,2);
            String hexG = data.toUpperCase().substring(2,4);
            String hexB = data.toUpperCase().substring(4,6);
            String sub = alphaHex + hexB+hexG+hexR;
            return (ByteUtils.hexStrToInt2(hexR)+ByteUtils.hexStrToInt2(hexG)* 256L +ByteUtils.hexStrToInt2(hexB)* 65536L | (long) ByteUtils.hexStrToInt2(alphaHex) <<24);
        }else {
            System.err.println("颜色值不对,前面要带 # 号");
            return  0;
        }
    }
    /**
     * 标准化HSB转RGB（返回Long类型）
     * @param hue 色相 (0-360)
     * @param saturation 饱和度 (0-100)
     * @param brightness 亮度 (0-100)
     * @return long类型的RGB值（格式：0xRRGGBB，无Alpha通道）
     * @throws IllegalArgumentException 参数超出范围时抛出异常
     */
    public static long hsbToRgbLong(int hue, int saturation, int brightness) {
        // 参数合法性校验
        if (hue < 0 || hue > 360) {
            throw new IllegalArgumentException("色相(hue)必须在0-360之间，当前值：" + hue);
        }
        if (saturation < 0 || saturation > 100) {
            throw new IllegalArgumentException("饱和度(saturation)必须在0-100之间，当前值：" + saturation);
        }
        if (brightness < 0 || brightness > 100) {
            throw new IllegalArgumentException("亮度(brightness)必须在0-100之间，当前值：" + brightness);
        }

        // 转换为Color.HSBtoRGB要求的0-1浮点型参数
        float h = hue / 360.0f;
        float s = saturation / 100.0f;
        float b = brightness / 100.0f;

        // 调用内置方法转换为RGB整数（格式：0xFFRRGGBB，Alpha=255）
        int rgbInt = Color.HSBtoRGB(h, s, b);

        // 转换为long并去除Alpha通道（只保留RRGGBB）
        // 方式1：保留完整的0xAARRGGBB格式（含Alpha）
        // long rgbLong = rgbInt & 0xFFFFFFFFL;
        // 方式2：仅保留RRGGBB（无Alpha，更常用）

        return rgbInt & 0x00FFFFFFL;
    }
    /**
     * RGB转HSV（返回int数组）
     * @param r 红色通道 (0-255)
     * @param g 绿色通道 (0-255)
     * @param b 蓝色通道 (0-255)
     * @return int[] {H(0-360), S(0-100), V(0-100)}
     * @throws IllegalArgumentException RGB参数超出范围时抛出异常
     */
    public static int[] rgbToHsv(long r, long g, long b) {
        // 1. 参数合法性校验
        if (!isValidRgb(r) || !isValidRgb(g) || !isValidRgb(b)) {
            throw new IllegalArgumentException("RGB值必须在0-255之间，当前输入：R=" + r + ", G=" + g + ", B=" + b);
        }

        // 2. 将RGB转换为0.0-1.0的浮点型
        float rf = r / 255.0f;
        float gf = g / 255.0f;
        float bf = b / 255.0f;

        // 3. 计算最大值、最小值和差值
        float max = Math.max(Math.max(rf, gf), bf);
        float min = Math.min(Math.min(rf, gf), bf);
        float delta = max - min;

        // 初始化HSV变量
        float h = 0.0f; // 色相（0-360°）
        float s = 0.0f; // 饱和度（0-100%）
        float v = max;  // 明度（0-100%）

        // 4. 计算色相H
        if (delta != 0) { // 非灰度色（有色彩）
            if (max == rf) {
                // 红色为最大值，H=(G-B)/Δ mod 6
                h = ((gf - bf) / delta) % 6;
            } else if (max == gf) {
                // 绿色为最大值，H=(B-R)/Δ + 2
                h = ((bf - rf) / delta) + 2;
            } else if (max == bf) {
                // 蓝色为最大值，H=(R-G)/Δ + 4
                h = ((rf - gf) / delta) + 4;
            }
            // 转换为0-360°
            h *= 60;
            // 处理负数（mod运算可能产生负数）
            if (h < 0) {
                h += 360;
            }
        } else {
            // 灰度色，色相无意义，默认设为0
            h = 0;
        }

        // 5. 计算饱和度S（0-100%）
        if (max != 0) {
            s = (delta / max) * 100;
        } else {
            // 黑色，饱和度为0
            s = 0;
        }

        // 6. 计算明度V（0-100%）
        v *= 100;

        // 7. 四舍五入为整数，返回int数组
        return new int[]{
                Math.round(h),   // H: 0-360
                Math.round(s),   // S: 0-100
                Math.round(v)    // V: 0-100
        };
    }


    /**
     * 方式1：基于Java内置Color类（推荐，简洁高效）
     * @param r 红色通道 (0-255)
     * @param g 绿色通道 (0-255)
     * @param b 蓝色通道 (0-255)
     * @return float[] {H(0.0-1.0), S(0.0-1.0), B(0.0-1.0)}
     * @throws IllegalArgumentException RGB参数超出范围时抛出异常
     */
    public static float[] rgbToHsb(float r, float g, float b) {
        // 参数校验
        if (!isValidRgb(r) || !isValidRgb(g) || !isValidRgb(b)) {
            throw new IllegalArgumentException("RGB值必须在0-255之间，当前输入：R=" + r + ", G=" + g + ", B=" + b);
        }
        // 内置API直接转换（返回float[3]：H/S/B）
        return Color.RGBtoHSB((int) r, (int) g, (int) b, null);
    }

    /**
     * 重载方法：int型RGB转HSB（更常用）
     * @param r 红色通道 (0-255)
     * @param g 绿色通道 (0-255)
     * @param b 蓝色通道 (0-255)
     * @return float[] {H(0.0-1.0), S(0.0-1.0), B(0.0-1.0)}
     */
    public static float[] rgbToHsb(int r, int g, int b) {
        if (!isValidRgb(r) || !isValidRgb(g) || !isValidRgb(b)) {
            throw new IllegalArgumentException("RGB值必须在0-255之间，当前输入：R=" + r + ", G=" + g + ", B=" + b);
        }
        return Color.RGBtoHSB(r, g, b, null);
    }

    /**
     * 方式2：手动实现RGB转HSB（无AWT依赖，适配Android等环境）
     * @param r 红色通道 (0-255)
     * @param g 绿色通道 (0-255)
     * @param b 蓝色通道 (0-255)
     * @return float[] {H(0.0-1.0), S(0.0-1.0), B(0.0-1.0)}
     */
    public static float[] rgbToHsbManual(int r, int g, int b) {
        if (!isValidRgb(r) || !isValidRgb(g) || !isValidRgb(b)) {
            throw new IllegalArgumentException("RGB值必须在0-255之间，当前输入：R=" + r + ", G=" + g + ", B=" + b);
        }

        // 归一化到0.0-1.0
        float rf = r / 255.0f;
        float gf = g / 255.0f;
        float bf = b / 255.0f;

        float max = Math.max(Math.max(rf, gf), bf);
        float min = Math.min(Math.min(rf, gf), bf);
        float delta = max - min;

        float h = 0.0f;
        float s = 0.0f;
        float brightness = max; // B = max

        // 计算色相H（0.0-1.0）
        if (delta != 0) {
            if (max == rf) {
                h = ((gf - bf) / delta) % 6;
            } else if (max == gf) {
                h = ((bf - rf) / delta) + 2;
            } else if (max == bf) {
                h = ((rf - gf) / delta) + 4;
            }
            h /= 6; // 转换为0.0-1.0
            if (h < 0) {
                h += 1.0f;
            }
        }

        // 计算饱和度S（0.0-1.0）
        if (max != 0) {
            s = delta / max;
        }

        return new float[]{h, s, brightness};
    }

    /**
     * 辅助方法：将浮点型HSB转换为整数型（H:0-360, S:0-100, B:0-100）
     * @param hsb 浮点型HSB数组（0.0-1.0）
     * @return int[] {H(0-360), S(0-100), B(0-100)}
     */
    public static int[] hsbFloatToInt(float[] hsb) {
        if (hsb == null || hsb.length != 3) {
            throw new IllegalArgumentException("HSB数组必须为3个元素");
        }
        int h = Math.round(hsb[0] * 360); // 0-360°
        int s = Math.round(hsb[1] * 100); // 0-100%
        int b = Math.round(hsb[2] * 100); // 0-100%
        return new int[]{h, s, b};
    }

    /**
     * 校验RGB通道值是否合法
     */
    private static boolean isValidRgb(float channel) {
        return channel >= 0 && channel <= 255;
    }

    private static boolean isValidRgb(int channel) {
        return channel >= 0 && channel <= 255;
    }

    /**
     * 校验单个RGB通道值是否合法（0-255）
     * @param channel RGB通道值（R/G/B）
     * @return 合法返回true，否则false
     */
    private static boolean isValidRgb(long channel) {
        return channel >= 0 && channel <= 255;
    }

    /**
     * 原生浮点型HSB转RGB（返回Long类型）
     * @param h 色相 (0.0f - 1.0f)
     * @param s 饱和度 (0.0f - 1.0f)
     * @param b 亮度 (0.0f - 1.0f)
     * @return long类型的RGB值（格式：0xRRGGBB）
     */
    public static long hsbToRgbLong(float h, float s, float b) {
        int rgbInt = Color.HSBtoRGB(h, s, b);
        return rgbInt & 0x00FFFFFFL;
    }
    // HSV转RGB（核心配色算法）
    public static int[] HSVToRGB(long h, long s, long v) {
        float c=v*s;
        float x=c*(1-Math.abs(Math.floorMod(h/60,2)-1));
        float m=v-c;
        float r=0,g=0,b=0;
        if(h>=0&&h<60){r=c;g=x;b=0;}
        else if(h>=60&&h<120){r=x;g=c;b=0;}
        else if(h>=120&&h<180){r=0;g=c;b=x;}
        else if(h>=180&&h<240){r=0;g=x;b=c;}
        else if(h>=240&&h<300){r=x;g=0;b=c;}
        else if(h>=300&&h<=360){r=c;g=0;b=x;}
        int[] rgb = calculateRGB(
                r / 255.0,
                g / 255.0,
                b / 255.0,
                m / 255.0  // 偏移量按 255 比例缩放（比如 m=50 表示提亮 50/255）
        );
        return rgb;
    }

    /**
     * RGB转HSV Long
     * HSV转Long
     * @param rgb HSV 颜色
     * @return 颜色值
     */
    public static long RGBArraysToLong(int[] rgb) {
        // 提取 R、G、B 分量（确保在 0-255 范围，避免异常值）
        int red = Math.max(0, Math.min(255, rgb[0]));
        int green = Math.max(0, Math.min(255, rgb[1]));
        int blue = Math.max(0, Math.min(255, rgb[2]));
// 转换为 long 类型（关键：用 0xFF 确保分量是无符号8位，避免符号扩展）
        long rgbLong = ((long) (red & 0xFF) << 16)  // R 左移 16 位（占高8位）
                | ((long) (green & 0xFF) << 8) // G 左移 8 位（占中8位）
                | ((long) (blue & 0xFF));     // B 占低8位
        return rgbLong;
    }
    public static int[] calculateRGB(double r, double g, double b, double m) {
        int red = clamp((r + m) * 255);
        int green = clamp((g + m) * 255);
        int blue = clamp((b + m) * 255);
        return new int[]{red, green, blue};
    }

    private static int clamp(double value) {
        return (int) Math.max(0, Math.min(255, Math.round(value)));
    }

    /**
     * 长整数值转Color类
     * @param colorValue 将颜色值转成COLOR 类
     * @return 返回COLOR 类
     */
    public static Color longColorToColorClass(long colorValue){
        int red = (int) ((colorValue >> 16) & 0xFF);
        int green = (int) ((colorValue >> 8) & 0xFF);
        int blue = (int) (colorValue & 0xFF);
        Color color = new Color(red, green, blue);
        return color;
    }

}
