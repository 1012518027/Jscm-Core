#pragma once

// Windows 拖放核心API头文件（提供IDropTarget、DragQueryFile等）
#include <Windows.h>
// Windows 外壳API头文件（提供拖放相关接口定义）
#include <ShlObj.h>
// C++11+ 函数对象（封装回调函数）
#include <functional>
// 线程安全互斥锁（保护共享资源）
#include <mutex>
// 原子变量（轻量级线程安全标记）
#include <atomic>
// 字符串处理（存储文件路径）
#include <string>
// 动态数组（存储多文件路径）
#include <vector>

// 自动链接拖放必需的系统库（无需手动配置）
#pragma comment(lib, "ole32.lib")   // COM组件支持库
#pragma comment(lib, "shell32.lib") // 外壳API支持库

// 拖放状态枚举（供回调通知状态变化）
enum class DragDropStatus
{
    DragEnter,  // 鼠标拖入窗口
    DragOver,   // 鼠标在窗口内拖动
    DragLeave,  // 鼠标拖离窗口
    Drop        // 鼠标释放（文件落下）
};

// 拖放回调接口（外部实现，接收拖放事件）
struct IDragDropCallback
{
    // 析构函数虚函数化（确保子类正确析构）
    virtual ~IDragDropCallback() = default;

    // 文件拖放完成回调（参数：拖入的文件路径列表）
    virtual void OnFilesDropped(const std::vector<std::wstring>& filePaths) = 0;

    // 拖放状态变化回调（可选实现，参数：状态+鼠标位置）
    virtual void OnDragStatusChanged(DragDropStatus status, const POINT& mousePos) {}
};

// 简化版拖放工具类（单例模式，专注核心功能）
class DragDropSimple
{
public:
    // 获取单例实例（全局唯一）
    static DragDropSimple& GetInstance()
    {
        static DragDropSimple instance; // 静态局部变量，自动保证线程安全（C++11+）
        return instance;
    }

    // 禁止拷贝构造（避免单例被复制）
    DragDropSimple(const DragDropSimple&) = delete;
    // 禁止赋值运算符（避免单例被赋值）
    DragDropSimple& operator=(const DragDropSimple&) = delete;

    /**
     * @brief 注册窗口拖放功能
     * @param hWnd 目标窗口句柄（必须有效）
     * @param callback 回调接口实例（生命周期需长于拖放注册期）
     * @return 成功返回true，失败返回false
     */
    bool Register(HWND hWnd, IDragDropCallback* callback)
    {
        // 入参合法性检查（窗口句柄和回调不能为空）
        if (!hWnd || !callback) return false;

        // 加锁保护共享资源（避免多线程同时操作上下文列表）
        std::lock_guard<std::mutex> lock(m_mutex);

        // 检查窗口是否已注册（避免重复注册）
        for (const auto& ctx : m_contexts)
        {
            if (ctx.hWnd == hWnd) return false;
        }

        // 初始化COM库（首次注册时执行，进程级初始化）
        if (!InitCOM()) return false;

        // 创建拖放上下文（存储窗口和回调关联信息）
        DragDropContext ctx;
        ctx.hWnd = hWnd;
        ctx.callback = callback;
        // 创建拖放目标对象（实现IDropTarget接口，处理拖放事件）
        ctx.dropTarget = new DropTargetImpl(ctx, *this);

        // 调用系统API注册窗口拖放功能
        HRESULT hr = ::RegisterDragDrop(hWnd, ctx.dropTarget);
        if (FAILED(hr))
        {
            // 注册失败，释放拖放目标对象
            ctx.dropTarget->Release();
            return false;
        }

        // 注册成功，添加上下文到列表
        m_contexts.push_back(ctx);
        return true;
    }

    /**
     * @brief 注销窗口拖放功能
     * @param hWnd 目标窗口句柄
     * @return 成功返回true，失败返回false
     */
    bool Unregister(HWND hWnd)
    {
        // 入参合法性检查（窗口句柄不能为空）
        if (!hWnd) return false;

        // 加锁保护共享资源
        std::lock_guard<std::mutex> lock(m_mutex);

        // 查找窗口对应的拖放上下文
        auto it = std::find_if(m_contexts.begin(), m_contexts.end(),
            [hWnd](const DragDropContext& ctx) { return ctx.hWnd == hWnd; });

        // 未找到对应上下文（未注册或已注销）
        if (it == m_contexts.end()) return false;

        // 调用系统API注销拖放功能
        ::RevokeDragDrop(hWnd);
        // 释放拖放目标对象（减少引用计数）
        it->dropTarget->Release();
        // 从列表中移除上下文
        m_contexts.erase(it);

        return true;
    }

    // 启用拖放功能（默认启用）
    void Enable() { m_enabled = true; }
    // 禁用拖放功能（禁用后不响应拖放事件）
    void Disable() { m_enabled = false; }
    // 检查拖放功能是否启用
    bool IsEnabled() const { return m_enabled; }

private:
    // 私有构造函数（单例模式，禁止外部创建）
    DragDropSimple() : m_comInited(false), m_enabled(true) {}

    // 析构函数（程序退出时释放资源）
    ~DragDropSimple()
    {
        // 加锁保护，注销所有窗口的拖放功能
        std::lock_guard<std::mutex> lock(m_mutex);
        for (auto& ctx : m_contexts)
        {
            ::RevokeDragDrop(ctx.hWnd);  // 注销拖放
            ctx.dropTarget->Release();   // 释放拖放目标对象
        }
        m_contexts.clear(); // 清空上下文列表

        UninitCOM(); // 释放COM库
    }

    /**
     * @brief 初始化COM库（拖放功能依赖COM）
     * @return 成功返回true，失败返回false
     */
    bool InitCOM()
    {
        // 已初始化则直接返回成功
        if (m_comInited) return true;

        // 初始化COM库（单线程公寓模式，DLL/EXE通用）
        HRESULT hr = CoInitializeEx(nullptr, COINIT_APARTMENTTHREADED);
        if (FAILED(hr)) return false;

        // 标记COM已初始化（原子变量，线程安全）
        m_comInited = true;
        return true;
    }

    /**
     * @brief 释放COM库
     */
    void UninitCOM()
    {
        // 未初始化则直接返回
        if (!m_comInited) return;

        // 释放COM库（进程级，与CoInitializeEx成对调用）
        CoUninitialize();
        m_comInited = false;
    }

    // 拖放上下文结构（关联窗口、回调、拖放目标）
    struct DragDropContext
    {
        HWND hWnd;                  // 目标窗口句柄
        IDragDropCallback* callback;// 回调接口实例
        IDropTarget* dropTarget;    // 拖放目标对象（实现IDropTarget）
    };

    // 拖放目标实现类（核心：处理系统拖放事件）
    class DropTargetImpl : public IDropTarget
    {
    public:
        // 构造函数（绑定上下文和工具类实例）
        DropTargetImpl(DragDropContext& ctx, DragDropSimple& helper)
            : m_ctx(ctx), m_helper(helper), m_refCount(1), m_isFileDrop(false)
        {}

        // 析构函数（释放资源）
        ~DropTargetImpl() override {}

        // ------------------------------
        // IUnknown接口实现（COM组件必需）
        // ------------------------------
        // 查询接口（用于COM组件类型识别）
        HRESULT STDMETHODCALLTYPE QueryInterface(REFIID riid, void** ppvObj) override
        {
            // 检查输出指针合法性
            if (!ppvObj) return E_POINTER;

            // 支持IUnknown和IDropTarget接口查询
            if (riid == IID_IUnknown || riid == IID_IDropTarget)
            {
                *ppvObj = static_cast<IDropTarget*>(this); // 类型转换
                AddRef(); // 增加引用计数
                return S_OK; // 成功
            }

            *ppvObj = nullptr; // 不支持的接口
            return E_NOINTERFACE; // 失败
        }

        // 增加引用计数（COM组件生命周期管理）
        ULONG STDMETHODCALLTYPE AddRef() override
        {
            return ++m_refCount; // 原子变量自增，线程安全
        }

        // 减少引用计数（COM组件生命周期管理）
        ULONG STDMETHODCALLTYPE Release() override
        {
            ULONG ref = --m_refCount; // 原子变量自减
            if (ref == 0) delete this; // 引用计数为0时释放对象
            return ref;
        }

        // ------------------------------
        // IDropTarget接口实现（拖放事件）
        // ------------------------------
        /**
         * @brief 鼠标拖入窗口时触发
         * @param pDataObj 拖放数据载体（存储拖放的文件/文本等）
         * @param grfKeyState 键盘状态（Ctrl/Shift等）
         * @param pt 鼠标位置（屏幕坐标）
         * @param pdwEffect 拖放效果（复制/移动/无）
         */
        HRESULT STDMETHODCALLTYPE DragEnter(IDataObject* pDataObj, DWORD, POINTL pt, DWORD* pdwEffect) override
        {
            // 检查工具类是否启用、参数合法性
            if (!m_helper.IsEnabled() || !pDataObj || !pdwEffect)
                return E_INVALIDARG;

            // 检查拖放数据是否为文件类型
            m_isFileDrop = IsFileData(pDataObj);
            // 设置拖放效果：文件拖放则支持复制，否则不支持
            *pdwEffect = m_isFileDrop ? DROPEFFECT_COPY : DROPEFFECT_NONE;

            // 转换鼠标位置（屏幕坐标→窗口客户区坐标）
            POINT mousePos = ConvertPoint(pt);
            // 通知回调：拖入状态
            m_ctx.callback->OnDragStatusChanged(DragDropStatus::DragEnter, mousePos);

            return S_OK;
        }

        /**
         * @brief 鼠标在窗口内拖动时触发（持续调用）
         */
        HRESULT STDMETHODCALLTYPE DragOver(DWORD, POINTL pt, DWORD* pdwEffect) override
        {
            // 检查工具类是否启用、参数合法性
            if (!m_helper.IsEnabled() || !pdwEffect)
                return E_INVALIDARG;

            // 保持拖放效果与DragEnter一致
            *pdwEffect = m_isFileDrop ? DROPEFFECT_COPY : DROPEFFECT_NONE;

            // 转换鼠标位置，通知回调：拖动中状态
            POINT mousePos = ConvertPoint(pt);
            m_ctx.callback->OnDragStatusChanged(DragDropStatus::DragOver, mousePos);

            return S_OK;
        }

        /**
         * @brief 鼠标拖离窗口时触发
         */
        HRESULT STDMETHODCALLTYPE DragLeave() override
        {
            // 检查工具类是否启用
            if (!m_helper.IsEnabled())
                return E_INVALIDARG;

            // 通知回调：拖离状态（鼠标位置设为(0,0)）
            m_ctx.callback->OnDragStatusChanged(DragDropStatus::DragLeave, {0, 0});
            // 重置文件拖放标记
            m_isFileDrop = false;

            return S_OK;
        }

        /**
         * @brief 鼠标释放（文件落下）时触发
         */
        HRESULT STDMETHODCALLTYPE Drop(IDataObject* pDataObj, DWORD, POINTL pt, DWORD* pdwEffect) override
        {
            // 检查工具类是否启用、参数合法性
            if (!m_helper.IsEnabled() || !pDataObj || !pdwEffect)
                return E_INVALIDARG;

            // 默认拖放效果：无
            *pdwEffect = DROPEFFECT_NONE;

            // 仅处理文件拖放
            if (m_isFileDrop)
            {
                // 从数据载体中提取文件路径列表
                std::vector<std::wstring> filePaths = ExtractFilePaths(pDataObj);
                if (!filePaths.empty())
                {
                    // 通知回调：文件拖放完成
                    m_ctx.callback->OnFilesDropped(filePaths);
                    // 设置拖放效果：复制成功
                    *pdwEffect = DROPEFFECT_COPY;
                }
            }

            // 转换鼠标位置，通知回调：落下状态
            POINT mousePos = ConvertPoint(pt);
            m_ctx.callback->OnDragStatusChanged(DragDropStatus::Drop, mousePos);
            // 重置文件拖放标记
            m_isFileDrop = false;

            return S_OK;
        }

    private:
        /**
         * @brief 检查拖放数据是否为文件类型
         * @param pDataObj 拖放数据载体
         * @return 是文件拖放返回true，否则返回false
         */
        bool IsFileData(IDataObject* pDataObj)
        {
            // 定义文件拖放数据格式（CF_HDROP：文件拖放格式）
            FORMATETC fe = {CF_HDROP, nullptr, DVASPECT_CONTENT, -1, TYMED_HGLOBAL};
            // 查询数据载体是否支持该格式
            return SUCCEEDED(pDataObj->QueryGetData(&fe));
        }

        /**
         * @brief 从数据载体中提取文件路径列表
         * @param pDataObj 拖放数据载体
         * @return 提取到的文件路径列表
         */
        std::vector<std::wstring> ExtractFilePaths(IDataObject* pDataObj)
        {
            std::vector<std::wstring> paths; // 存储文件路径
            // 定义文件拖放数据格式
            FORMATETC fe = {CF_HDROP, nullptr, DVASPECT_CONTENT, -1, TYMED_HGLOBAL};
            STGMEDIUM sm; // 存储数据的介质

            // 从数据载体中获取数据
            if (SUCCEEDED(pDataObj->GetData(&fe, &sm)))
            {
                // 锁定全局内存，获取HDROP句柄（文件拖放数据句柄）
                HDROP hDrop = static_cast<HDROP>(GlobalLock(sm.hGlobal));
                if (hDrop)
                {
                    // 获取拖放的文件数量
                    UINT fileCount = DragQueryFile(hDrop, 0xFFFFFFFF, nullptr, 0);
                    for (UINT i = 0; i < fileCount; ++i)
                    {
                        // 获取第i个文件的路径长度（含终止符）
                        UINT pathLen = DragQueryFile(hDrop, i, nullptr, 0) + 1;
                        // 分配内存存储路径（宽字符）
                        std::wstring path(pathLen, 0);
                        // 提取文件路径
                        if (DragQueryFile(hDrop, i, &path[0], pathLen))
                        {
                            path.resize(pathLen - 1); // 移除终止符
                            paths.push_back(std::move(path)); // 添加到列表
                        }
                    }
                    GlobalUnlock(sm.hGlobal); // 解锁全局内存
                }
                ReleaseStgMedium(&sm); // 释放数据介质
            }

            return paths;
        }

        /**
         * @brief 转换POINTL（屏幕坐标）到POINT（窗口客户区坐标）
         * @param pt 屏幕坐标（POINTL）
         * @return 窗口客户区坐标（POINT）
         */
        POINT ConvertPoint(const POINTL& pt)
        {
            POINT p;
            p.x = pt.x; // 横坐标赋值
            p.y = pt.y; // 纵坐标赋值
            ::ScreenToClient(m_ctx.hWnd, &p); // 屏幕坐标→客户区坐标
            return p;
        }

    private:
        DragDropContext& m_ctx;    // 关联的拖放上下文
        DragDropSimple& m_helper;  // 工具类实例（用于检查启用状态）
        std::atomic<ULONG> m_refCount; // 引用计数（COM组件必需）
        bool m_isFileDrop;         // 是否为文件拖放标记
    };

private:
    std::mutex m_mutex;                  // 线程安全锁（保护上下文列表）
    std::vector<DragDropContext> m_contexts; // 拖放上下文列表（多窗口支持）
    std::atomic<bool> m_comInited;       // COM库初始化状态（原子变量，线程安全）
    std::atomic<bool> m_enabled;         // 拖放功能启用状态（原子变量，线程安全）
};