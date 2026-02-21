package com.scm.all.export;

import com.scm.all.pfunc.TokenCall;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间戳类
 */
public class URLDateTimeUtils {

    // 时区常量（北京时间）
    private static final ZoneId BEIJING_ZONE = ZoneId.of("Asia/Shanghai");
    // 时间格式常量（匹配2026-01-20 20:53:42）
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // 定义默认编码（兜底用）
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    // 匹配Content-Type中的编码正则
    private static final Pattern CHARSET_PATTERN = Pattern.compile("charset=([^;]+)", Pattern.CASE_INSENSITIVE);


    /**
     * @return 返回网络时间
     * @throws Exception 抛出异常由上层处理
     */
    public static String getDatTime() throws Exception {
        String result = ""; // 用于返回最终的目标URL内容
        String USER_AGENT = "mozilla/5.0 (linux; u; android 4.1.2; zh-cn; mi-one plus build/jzo54k) applewebkit/534.30 (khtml, like gecko) version/4.0 mobile safari/534.30 micromessenger/5.0.1.352";

        // 1. 第一步请求：获取中间URL
        String mainPageContent = fetchUrlContent("https://www.ntsc.ac.cn", "text/html", USER_AGENT);
        String targetUrl = SystemUtils.textCenter(mainPageContent, " url: '", "',");
        // 2. 第二步请求：访问中间URL并输出内容
        if (targetUrl != null && !targetUrl.isEmpty()) {
            result = fetchUrlContent(targetUrl, "application/json", USER_AGENT);
        }
        return SystemUtils.textCenter(result,"\",\"sysTime2\":\"","\"}"); // 原代码返回空字符串，改为返回实际内容更有意义
    }

    /**
     * 封装通用的URL请求方法（修复乱码核心：自动检测响应编码）
     * @param urlStr 要请求的URL
     * @param contentType 请求的Content-Type（区分html/json）
     * @param USER_AGENT 请求头User-Agent
     * @return URL返回的文本内容（无乱码）
     * @throws Exception 抛出异常由上层处理
     */
    private static String fetchUrlContent(String urlStr, String contentType, String USER_AGENT) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // 核心配置
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(30000);
        conn.setReadTimeout(30000);
        conn.setDoInput(true);

        // 设置请求头
        conn.addRequestProperty("Accept", "*/*");
        conn.addRequestProperty("Accept-Language", "zh-CN,zh;q=0.9");
        conn.addRequestProperty("Connection", "keep-alive");
        conn.addRequestProperty("Content-Type", contentType);
        conn.addRequestProperty("User-Agent", USER_AGENT);

        // 修复乱码核心步骤1：获取响应的真实编码
        Charset responseCharset = getResponseCharset(conn);
        // 修复乱码核心步骤2：使用真实编码读取响应
        try (InputStream is = conn.getInputStream();
             InputStreamReader isr = new InputStreamReader(is, responseCharset); // 关键：用检测到的编码
             BufferedReader br = new BufferedReader(isr)) {

            StringBuilder content = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator()); // 保留换行符，避免内容拼接丢失格式
            }
            return content.toString();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    /**
     * 从HttpURLConnection中提取响应的字符编码
     * @param conn HTTP连接对象
     * @return 响应的字符编码（优先从Content-Type取，取不到用默认UTF-8）
     */
    private static Charset getResponseCharset(HttpURLConnection conn)throws Exception {
        // 1. 获取响应头中的Content-Type
        String contentType = conn.getContentType();
        if (contentType != null && !contentType.isEmpty()) {
            Matcher matcher = CHARSET_PATTERN.matcher(contentType);
            if (matcher.find()) {
                String charsetName = matcher.group(1).trim();
                // 验证编码是否有效，有效则返回
                return Charset.forName(charsetName);
            }
        }
        // 2. 未检测到编码，返回默认UTF-8
        return DEFAULT_CHARSET;
    }

    /**
     * 将指定格式的时间字符串转换为高精度时间戳（毫秒级，13位）
     * @param timeStr 时间字符串，格式必须为：yyyy-MM-dd HH:mm:ss（如2026-01-20 20:53:42）
     * @return 13位毫秒级高精度时间戳（与输入时间完全一致，毫秒部分为0）
     * @throws Exception 时间字符串格式错误时抛出
     */
    public static long convertToHighPrecisionTimestamp(String timeStr) throws Exception {
        // 1. 校验入参
        if (timeStr == null || timeStr.trim().isEmpty()) {
            throw new IllegalArgumentException("时间字符串不能为空！");
        }

        // 2. 解析时间字符串（严格匹配格式+北京时间）
        LocalDateTime targetTime = LocalDateTime.parse(timeStr.trim(), TIME_FORMATTER);

        // 3. 强制毫秒/纳秒为0，确保与输入时间完全一致
        targetTime = targetTime.with(ChronoField.MILLI_OF_SECOND, 0)
                .with(ChronoField.NANO_OF_SECOND, 0);

        // 4. 转换为13位毫秒级高精度时间戳
        return targetTime.atZone(BEIJING_ZONE)
                .toInstant()
                .toEpochMilli();
    }
    /**
     * 获取网络当前时间
     * @return 13位毫秒级高精度时间戳（与输入时间完全一致，毫秒部分为0）
     * @throws Exception 时间字符串格式错误时抛出
     */
    public static long convertToHighPrecisionTimestamp()throws Exception{

        // 2. 解析时间字符串（严格匹配格式+北京时间）
        LocalDateTime targetTime = LocalDateTime.parse(getDatTime().trim(), TIME_FORMATTER);

        // 3. 强制毫秒/纳秒为0，确保与输入时间完全一致
        targetTime = targetTime.with(ChronoField.MILLI_OF_SECOND, 0)
                .with(ChronoField.NANO_OF_SECOND, 0);

        // 4. 转换为13位毫秒级高精度时间戳
        return targetTime.atZone(BEIJING_ZONE)
                .toInstant()
                .toEpochMilli();
    }

    /**
     * 重载方法：获取当前系统时间的高精度时间戳（毫秒级）
     * @return 当前北京时间的13位毫秒级时间戳
     */
    private static long getCurrentHighPrecisionTimestamp() {
        return LocalDateTime.now(BEIJING_ZONE)
                .atZone(BEIJING_ZONE)
                .toInstant()
                .toEpochMilli();
    }


    /**
     *
     * @param date 2026-01-20 20:53:42
     * @param call 回调
     * @return 返回时间
     */
    public static int getToken(String date, TokenCall call) {
        try {
            long longTime1 = convertToHighPrecisionTimestamp();
            long longTime2 = convertToHighPrecisionTimestamp(date);
            long time = longTime2 - longTime1;
            if(longTime1 < longTime2){
                call.callback(0,longTime1,longTime2,time);
                return 0;
            }else {
                call.callback(-1,longTime1,longTime2,time);
                System.exit(0);
                return -1;
            }
        }catch (Exception e){
            call.callback(-1,-1,-1,-1);
            return -1;
        }
    }
}
