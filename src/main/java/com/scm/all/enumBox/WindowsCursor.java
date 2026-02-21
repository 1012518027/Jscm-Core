package com.scm.all.enumBox;

/**
 * Windows 系统预定义光标（HCURSOR）枚举
 * 对应 Win32 API 中的 IDC_XXX 宏，封装了光标资源 ID、名称和描述
 *
 * @author 开发者
 * 2025-12-19
 */
public enum WindowsCursor {

    // ===================== 基础光标 =====================
    /**
     * 标准箭头光标（默认鼠标光标）
     */
    ARROW(32512, "IDC_ARROW", "标准箭头光标（默认）"),

    /**
     * 文本编辑I型光标（竖线）
     */
    IBEAM(32513, "IDC_IBEAM", "文本编辑时的I型光标"),

    /**
     * 等待/忙光标（沙漏/圆圈）
     */
    WAIT(32514, "IDC_WAIT", "等待/忙光标（沙漏/加载动画）"),

    /**
     * 十字线光标（绘图/选择）
     */
    CROSS(32515, "IDC_CROSS", "十字线选择光标"),

    /**
     * 向上箭头光标
     */
    UP_ARROW(32516, "IDC_UPARROW", "向上箭头光标"),

    // ===================== 调整大小光标 =====================
    /**
     * 四向调整大小光标（上下左右）
     */
    SIZE(32640, "IDC_SIZE", "四向调整大小光标"),

    /**
     * 图标光标（已废弃）
     */
    ICON(32641, "IDC_ICON", "图标光标（已废弃）"),

    /**
     * 西北-东南方向调整大小光标
     */
    SIZE_NWSE(32642, "IDC_SIZENWSE", "西北-东南方向调整大小光标"),

    /**
     * 东北-西南方向调整大小光标
     */
    SIZE_NESW(32643, "IDC_SIZENESW", "东北-西南方向调整大小光标"),

    /**
     * 水平（左右）调整大小光标
     */
    SIZE_WE(32644, "IDC_SIZEWE", "水平（左右）调整大小光标"),

    /**
     * 垂直（上下）调整大小光标
     */
    SIZE_NS(32645, "IDC_SIZENS", "垂直（上下）调整大小光标"),

    /**
     * 全方向调整大小光标（四向箭头）
     */
    SIZE_ALL(32646, "IDC_SIZEALL", "全方向调整大小光标"),

    // ===================== 功能光标 =====================
    /**
     * 禁止光标（红色斜杠圆圈）
     */
    NO(32648, "IDC_NO", "禁止操作光标"),

    /**
     * 手型光标（超链接/拖拽）
     */
    HAND(32649, "IDC_HAND", "手型光标（超链接/拖拽）"),

    /**
     * 应用程序启动光标（箭头+沙漏）
     */
    APP_STARTING(32650, "IDC_APPSTARTING", "应用程序启动光标（箭头+沙漏）"),

    /**
     * 帮助光标（箭头+问号）
     */
    HELP(32651, "IDC_HELP", "帮助光标（箭头+问号）");

    // ===================== 枚举属性 =====================
    /**
     * Windows 光标资源 ID（十进制数值，对应 MAKEINTRESOURCE 的底层值）
     */
    private final int resourceId;

    /**
     * 光标宏名称（如 IDC_ARROW）
     */
    private final String name;

    /**
     * 光标功能描述
     */
    private final String description;

    // ===================== 构造方法 =====================
    WindowsCursor(int resourceId, String name, String description) {
        this.resourceId = resourceId;
        this.name = name;
        this.description = description;
    }

    // ===================== 公共方法 =====================
    /**
     * 获取光标资源 ID（十进制）
     * @return 资源 ID 整数
     */
    public int getResourceId() {
        return resourceId;
    }

    /**
     * 获取光标宏名称
     * @return 宏名称（如 IDC_ARROW）
     */
    public String getName() {
        return name;
    }

    /**
     * 获取光标描述
     * @return 功能描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 获取光标句柄的数值表示（兼容 64 位系统，返回 long 类型）
     * 在 JNI 中，HCURSOR 可转换为 long 避免截断
     * @return 光标句柄的数值表示
     */
    public long getHandleValue() {
        // 对于系统预定义光标，resourceId 可直接作为句柄的标识值（实际句柄由系统分配，此处为简化）
        return resourceId;
    }

    /**
     * 根据资源 ID 查找对应的枚举
     * @param resourceId 资源 ID（十进制）
     * @return 对应的 WindowsCursor 枚举，未找到返回 null
     */
    public static WindowsCursor fromResourceId(int resourceId) {
        for (WindowsCursor cursor : values()) {
            if (cursor.resourceId == resourceId) {
                return cursor;
            }
        }
        return null;
    }

    /**
     * 根据宏名称查找对应的枚举（忽略大小写）
     * @param name 宏名称（如 IDC_ARROW）
     * @return 对应的 WindowsCursor 枚举，未找到返回 null
     */
    public static WindowsCursor fromName(String name) {
        for (WindowsCursor cursor : values()) {
            if (cursor.name.equalsIgnoreCase(name)) {
                return cursor;
            }
        }
        return null;
    }

    // ===================== 重写方法 =====================
    @Override
    public String toString() {
        return String.format("WindowsCursor{resourceId=%d, name='%s', description='%s'}",
                resourceId, name, description);
    }
}
