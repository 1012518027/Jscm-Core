package com.scm.all.export;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scm.all.export.powerUtils.PowerShell;
import com.scm.all.pfunc.WebChromeDriverCallBack;
import com.scm.all.struct.TagRegisTryGroupInfo;
import com.scm.x64.export.RegistryOperationUtilsX64;
import com.scm.x86.export.RegistryOperationUtilsX86;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 自动填表工具类 ，他会针对你的google版本去下载对应的驱动，剩下的就是你使用
 * 相应的jar包自己去配置    谷歌运行的时候加上  chrome.exe --remote-debugging-port=9222
 */
public class WebDriverAutoJsonUtils {

    // 定义默认编码（兜底用）
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    // 匹配Content-Type中的编码正则
    private static final Pattern CHARSET_PATTERN = Pattern.compile("charset=([^;]+)", Pattern.CASE_INSENSITIVE);
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 获取所有chrome驱动接口下载版本信息
     * @param callback 回调接口
     */
    public static void downloadJson(WebChromeDriverCallBack callback) {
        try {
            String json = fetchUrlContent("https://googlechromelabs.github.io/chrome-for-testing/known-good-versions-with-downloads.json", "application/json", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.90 Safari/537.36 Edg/89.0.774.54");
            // Step 2: 解析 JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);
            JsonNode versions = root.path("versions");

            for (JsonNode versionNode : versions) {
                String version = versionNode.path("version").asText();
                JsonNode chromedrivers = versionNode.path("downloads").path("chromedriver");
                if (chromedrivers.isArray()) {
                    for (JsonNode chromedriver : chromedrivers){
                        if (StringUtils.contains(chromedriver.path("platform").asText(), "win64") || StringUtils.contains(chromedriver.path("platform").asText(), "win32")){
                            if(callback.callback(version,chromedriver.path("platform").asText(),chromedriver.path("url").asText())){
                                return;
                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        conn.setConnectTimeout(60000);
        conn.setReadTimeout(60000);
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
     * 获取google chrome版本
     * @return 返回google chrome版本
     */
    public static String getChromeVersionX64(){
        return ByteUtils.bytesArrayToUtf8WideString(RegistryOperationUtilsX64.readByte("HKEY_CURRENT_USER\\SOFTWARE\\Google\\Chrome\\BLBeacon"));
    }

    /**
     * 获取google chrome版本
     * @return 返回google chrome版本
     */
    public static String getChromeVersionX32(){
        return ByteUtils.bytesArrayToUtf8WideString(RegistryOperationUtilsX86.readByte("HKEY_CURRENT_USER\\SOFTWARE\\Google\\Chrome\\BLBeacon"));
    }

    /**
     * 下载chromeDriver
     * @param url 压缩包地址
     * @return 是否成功
     */
    public static boolean downloadChromeDriver(String url){

        if (PowerShell.downLoadPowerShell_Win(url,PathFileJSCM.getJarPath()+"ChromeDriver.zip")==0){
            try {
                String path = unzip(PathFileJSCM.getJarPath()+"ChromeDriver.zip",PathFileJSCM.getJarPath())+"chromedriver.exe";
                System.out.println(path);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 智能解压，并返回根目录
     * @param zipFilePath 压缩包路径
     * @param destDir 解压到哪个目录
     * @return 解压后的根路径（有文件夹返回文件夹名/，无则返回 /）
     * @throws Exception 异常
     */
    private static String unzip(String zipFilePath, String destDir) throws Exception {
        File dest = new File(destDir);
        if (!dest.exists()) dest.mkdirs();

        Set<String> rootSet = new HashSet<>();

        // ========== 第一步：先遍历，判断是否有统一根文件夹 ==========
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath), Charset.forName("GBK"))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                String name = entry.getName();
                if (entry.isDirectory() || name.contains("/")) {
                    int slash = name.indexOf('/');
                    if (slash > 0) {
                        String root = name.substring(0, slash);
                        rootSet.add(root);
                    }
                }
                zis.closeEntry();
            }
        }

        // ========== 第二步：正式解压 ==========
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath), Charset.forName("GBK"))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File file = new File(dest, entry.getName());

                if (!file.getCanonicalPath().startsWith(dest.getCanonicalPath())) {
                    throw new SecurityException("非法路径");
                }

                if (entry.isDirectory()) {
                    file.mkdirs();
                    continue;
                }

                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
                    byte[] buf = new byte[8192];
                    int len;
                    while ((len = zis.read(buf)) != -1) {
                        bos.write(buf, 0, len);
                    }
                }
                zis.closeEntry();
            }
        }

        // ========== 第三步：判断返回结果 ==========
        if (rootSet.size() == 1) {
            // 只有一个根文件夹 → 返回它
            return rootSet.iterator().next() + "/";
        } else {
            // 没有统一根文件夹 → 返回 /
            return "/";
        }
    }
}
