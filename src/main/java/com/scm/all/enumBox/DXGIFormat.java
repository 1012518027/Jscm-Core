package com.scm.all.enumBox;

/**
 * DXGI_FORMAT 枚举 Java 映射版
 * 对应原生定义：typedef enum DXGI_FORMAT
 * 核心：数值与原生严格一致，补充常用格式的业务注释，支持 JNI 双向交互
 */
public enum DXGIFormat {
    // ========== 基础未知格式 ==========
    DXGI_FORMAT_UNKNOWN(0),

    // ========== 32位 RGBA 系列 ==========
    DXGI_FORMAT_R32G32B32A32_TYPELESS(1),
    DXGI_FORMAT_R32G32B32A32_FLOAT(2),
    DXGI_FORMAT_R32G32B32A32_UINT(3),
    DXGI_FORMAT_R32G32B32A32_SINT(4),

    // ========== 32位 RGB 系列 ==========
    DXGI_FORMAT_R32G32B32_TYPELESS(5),
    DXGI_FORMAT_R32G32B32_FLOAT(6),
    DXGI_FORMAT_R32G32B32_UINT(7),
    DXGI_FORMAT_R32G32B32_SINT(8),

    // ========== 16位 RGBA 系列 ==========
    DXGI_FORMAT_R16G16B16A16_TYPELESS(9),
    DXGI_FORMAT_R16G16B16A16_FLOAT(10),
    DXGI_FORMAT_R16G16B16A16_UNORM(11),
    DXGI_FORMAT_R16G16B16A16_UINT(12),
    DXGI_FORMAT_R16G16B16A16_SNORM(13),
    DXGI_FORMAT_R16G16B16A16_SINT(14),

    // ========== 32位 RG 系列 ==========
    DXGI_FORMAT_R32G32_TYPELESS(15),
    DXGI_FORMAT_R32G32_FLOAT(16),
    DXGI_FORMAT_R32G32_UINT(17),
    DXGI_FORMAT_R32G32_SINT(18),

    // ========== 深度/模板 系列 ==========
    DXGI_FORMAT_R32G8X24_TYPELESS(19),
    DXGI_FORMAT_D32_FLOAT_S8X24_UINT(20),
    DXGI_FORMAT_R32_FLOAT_X8X24_TYPELESS(21),
    DXGI_FORMAT_X32_TYPELESS_G8X24_UINT(22),

    // ========== 10/11位 高色深系列 ==========
    DXGI_FORMAT_R10G10B10A2_TYPELESS(23),
    DXGI_FORMAT_R10G10B10A2_UNORM(24), // 常用：高色深不透明纹理
    DXGI_FORMAT_R10G10B10A2_UINT(25),
    DXGI_FORMAT_R11G11B10_FLOAT(26),

    // ========== 8位 RGBA 系列（Direct2D 最常用） ==========
    DXGI_FORMAT_R8G8B8A8_TYPELESS(27),
    DXGI_FORMAT_R8G8B8A8_UNORM(28), // 核心：Direct2D 默认像素格式（B8G8R8A8_UNORM 等价）
    DXGI_FORMAT_R8G8B8A8_UNORM_SRGB(29), // 带 SRGB 伽马校正
    DXGI_FORMAT_R8G8B8A8_UINT(30),
    DXGI_FORMAT_R8G8B8A8_SNORM(31),
    DXGI_FORMAT_R8G8B8A8_SINT(32),

    // ========== 16位 RG 系列 ==========
    DXGI_FORMAT_R16G16_TYPELESS(33),
    DXGI_FORMAT_R16G16_FLOAT(34),
    DXGI_FORMAT_R16G16_UNORM(35),
    DXGI_FORMAT_R16G16_UINT(36),
    DXGI_FORMAT_R16G16_SNORM(37),
    DXGI_FORMAT_R16G16_SINT(38),

    // ========== 32位 单通道系列 ==========
    DXGI_FORMAT_R32_TYPELESS(39),
    DXGI_FORMAT_D32_FLOAT(40),
    DXGI_FORMAT_R32_FLOAT(41),
    DXGI_FORMAT_R32_UINT(42),
    DXGI_FORMAT_R32_SINT(43),

    // ========== 24位深度+8位模板 ==========
    DXGI_FORMAT_R24G8_TYPELESS(44),
    DXGI_FORMAT_D24_UNORM_S8_UINT(45),
    DXGI_FORMAT_R24_UNORM_X8_TYPELESS(46),
    DXGI_FORMAT_X24_TYPELESS_G8_UINT(47),

    // ========== 8位 RG 系列 ==========
    DXGI_FORMAT_R8G8_TYPELESS(48),
    DXGI_FORMAT_R8G8_UNORM(49),
    DXGI_FORMAT_R8G8_UINT(50),
    DXGI_FORMAT_R8G8_SNORM(51),
    DXGI_FORMAT_R8G8_SINT(52),

    // ========== 16位 单通道系列 ==========
    DXGI_FORMAT_R16_TYPELESS(53),
    DXGI_FORMAT_R16_FLOAT(54),
    DXGI_FORMAT_D16_UNORM(55),
    DXGI_FORMAT_R16_UNORM(56),
    DXGI_FORMAT_R16_UINT(57),
    DXGI_FORMAT_R16_SNORM(58),
    DXGI_FORMAT_R16_SINT(59),

    // ========== 8位 单通道系列 ==========
    DXGI_FORMAT_R8_TYPELESS(60),
    DXGI_FORMAT_R8_UNORM(61),
    DXGI_FORMAT_R8_UINT(62),
    DXGI_FORMAT_R8_SNORM(63),
    DXGI_FORMAT_R8_SINT(64),
    DXGI_FORMAT_A8_UNORM(65),
    DXGI_FORMAT_R1_UNORM(66),

    // ========== 特殊格式 ==========
    DXGI_FORMAT_R9G9B9E5_SHAREDEXP(67), // 共享指数浮点格式
    DXGI_FORMAT_R8G8_B8G8_UNORM(68),
    DXGI_FORMAT_G8R8_G8B8_UNORM(69),

    // ========== BC 压缩纹理系列 ==========
    DXGI_FORMAT_BC1_TYPELESS(70),
    DXGI_FORMAT_BC1_UNORM(71),
    DXGI_FORMAT_BC1_UNORM_SRGB(72),
    DXGI_FORMAT_BC2_TYPELESS(73),
    DXGI_FORMAT_BC2_UNORM(74),
    DXGI_FORMAT_BC2_UNORM_SRGB(75),
    DXGI_FORMAT_BC3_TYPELESS(76),
    DXGI_FORMAT_BC3_UNORM(77),
    DXGI_FORMAT_BC3_UNORM_SRGB(78),
    DXGI_FORMAT_BC4_TYPELESS(79),
    DXGI_FORMAT_BC4_UNORM(80),
    DXGI_FORMAT_BC4_SNORM(81),
    DXGI_FORMAT_BC5_TYPELESS(82),
    DXGI_FORMAT_BC5_UNORM(83),
    DXGI_FORMAT_BC5_SNORM(84),

    // ========== 5/6位 低色深系列 ==========
    DXGI_FORMAT_B5G6R5_UNORM(85),
    DXGI_FORMAT_B5G5R5A1_UNORM(86),

    // ========== BGR 系列（Direct2D 常用） ==========
    DXGI_FORMAT_B8G8R8A8_UNORM(87), // 核心：Direct2D 绘图常用（对应 B8G8R8A8_UNORM）
    DXGI_FORMAT_B8G8R8X8_UNORM(88),
    DXGI_FORMAT_R10G10B10_XR_BIAS_A2_UNORM(89),
    DXGI_FORMAT_B8G8R8A8_TYPELESS(90),
    DXGI_FORMAT_B8G8R8A8_UNORM_SRGB(91),
    DXGI_FORMAT_B8G8R8X8_TYPELESS(92),
    DXGI_FORMAT_B8G8R8X8_UNORM_SRGB(93),

    // ========== BC6H/BC7 高压缩比系列 ==========
    DXGI_FORMAT_BC6H_TYPELESS(94),
    DXGI_FORMAT_BC6H_UF16(95),
    DXGI_FORMAT_BC6H_SF16(96),
    DXGI_FORMAT_BC7_TYPELESS(97),
    DXGI_FORMAT_BC7_UNORM(98),
    DXGI_FORMAT_BC7_UNORM_SRGB(99),

    // ========== YUV 视频格式系列 ==========
    DXGI_FORMAT_AYUV(100),
    DXGI_FORMAT_Y410(101),
    DXGI_FORMAT_Y416(102),
    DXGI_FORMAT_NV12(103), // 常用：视频渲染
    DXGI_FORMAT_P010(104),
    DXGI_FORMAT_P016(105),
    DXGI_FORMAT_420_OPAQUE(106),
    DXGI_FORMAT_YUY2(107),
    DXGI_FORMAT_Y210(108),
    DXGI_FORMAT_Y216(109),
    DXGI_FORMAT_NV11(110),

    // ========== 索引色/4位格式 ==========
    DXGI_FORMAT_AI44(111),
    DXGI_FORMAT_IA44(112),
    DXGI_FORMAT_P8(113),
    DXGI_FORMAT_A8P8(114),
    DXGI_FORMAT_B4G4R4A4_UNORM(115),

    // ========== 扩展视频格式 ==========
    DXGI_FORMAT_P208(130),
    DXGI_FORMAT_V208(131),
    DXGI_FORMAT_V408(132),

    // ========== 采样器反馈格式 ==========
    DXGI_FORMAT_SAMPLER_FEEDBACK_MIN_MIP_OPAQUE(189),
    DXGI_FORMAT_SAMPLER_FEEDBACK_MIP_REGION_USED_OPAQUE(190),

    // ========== 4位 RGBA 格式 ==========
    DXGI_FORMAT_A4B4G4R4_UNORM(191),

    // ========== 占位值 ==========
    DXGI_FORMAT_FORCE_UINT(0xFFFFFFFF);

    // 存储原生数值（32位int覆盖所有值，兼容位运算/枚举值传递）
    private final int value;

    /**
     * 构造方法：绑定枚举与原生数值
     * @param value 原生 DXGI_FORMAT 的整数值
     */
    DXGIFormat(int value) {
        this.value = value;
    }

    /**
     * 获取原生数值（JNI 交互核心）
     * @return 枚举对应的原生整数值
     */
    public int getValue() {
        return value;
    }

    /**
     * 从原生数值反向解析枚举（快速查找）
     * @param value 原生 DXGI_FORMAT 数值
     * @return 匹配的枚举，无匹配返回 null
     */
    public static DXGIFormat fromValue(int value) {
        // 缓存枚举映射（避免每次遍历，提升性能）
        for (DXGIFormat format : values()) {
            if (format.value == value) {
                return format;
            }
        }
        return null;
    }

    /**
     * 快捷方法：获取 Direct2D 绘图默认像素格式（B8G8R8A8_UNORM）
     * 对应原生：DXGI_FORMAT_B8G8R8A8_UNORM = 87
     * @return 默认像素格式枚举
     */
    public static DXGIFormat getDefaultD2DPixelFormat() {
        return DXGI_FORMAT_B8G8R8A8_UNORM;
    }

    /**
     * 快捷方法：获取 Direct2D 常用 RGBA 格式（R8G8B8A8_UNORM）
     * 对应原生：DXGI_FORMAT_R8G8B8A8_UNORM = 28
     * @return 常用 RGBA 格式枚举
     */
    public static DXGIFormat getCommonRGBAFormat() {
        return DXGI_FORMAT_R8G8B8A8_UNORM;
    }
}
