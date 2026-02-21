#pragma once
#define _ATL_CSTRING_EXPLICIT_CONSTRUCTORS
#define ATL_NO_ASSERT_ON_DESTROY_NONEXISTENT_WINDOW
#include <windows.h>
#include <ole2.h>
#include <olectl.h>
#include <atlbase.h>
#include <atlcom.h>
#include <atlstr.h>
#include <iostream>

// ========================================
// 1. 手动定义CLSID/IID常量（从火山程序转换）
// ========================================
// 报表CLSID
const CLSID CLSID_GridppReport = { 0xF9364159, 0x6AED, 0x4F9C, { 0x8B, 0xAF, 0xD7, 0xC7, 0xED, 0x61, 0x60, 0xA8 } };
// 报表事件IID
const IID IID__IGridppReportEvents = { 0x330F80F5, 0x4568, 0x4F70, { 0xBF, 0xCB, 0x68, 0x3D, 0x3B, 0x90, 0xFE, 0xBB } };

// 打印显示器CLSID
const CLSID CLSID_GRPrintViewer = { 0x44CBB5DE, 0x5AFB, 0x4C3D, { 0x8F, 0x3F, 0x0F, 0x70, 0xCA, 0x53, 0x72, 0xAD } };
// 打印显示器事件IID
const IID IID__IGRPrintViewerEvents = { 0xA9E920A1, 0xC722, 0x4A81, { 0x9F, 0xCF, 0x4D, 0x5E, 0xBF, 0xFA, 0x21, 0xF4 } };

// 查询显示器CLSID
const CLSID CLSID_GRDisplayViewer = { 0x1B5EA181, 0xA38D, 0x4F42, { 0x88, 0xB2, 0x6A, 0xF7, 0x4C, 0xF6, 0xD6, 0xC0 } };
// 查询显示器事件IID
const IID IID__IGRDisplayViewerEvents = { 0x2564DCE8, 0xFEDB, 0x4EB6, { 0xB7, 0xF9, 0x50, 0x26, 0xF7, 0xF1, 0x04, 0x1E } };

// 报表设计器CLSID（备用）
const CLSID CLSID_GRDesigner = { 0x6EDD80CB, 0x9F08, 0x4C71, { 0xB4, 0x06, 0x47, 0x9E, 0x5C, 0xB8, 0x0F, 0xCE } };
// 报表设计器事件IID（备用）
const IID IID__IGRDesignerEvents = { 0xB89F92F4, 0x5E58, 0x413E, { 0x8E, 0x73, 0x4A, 0x0B, 0x28, 0xB9, 0xE9, 0xBF } };

// ========================================
// 封装UnknownObject（无修改）
// ========================================
class UnknownObject {
private:
    IUnknown* m_pUnknown;

public:
    UnknownObject() : m_pUnknown(NULL) {}
    UnknownObject(IUnknown* pUnk) : m_pUnknown(pUnk) {
        if (m_pUnknown) m_pUnknown->AddRef();
    }

    UnknownObject(const UnknownObject& other) {
        m_pUnknown = other.m_pUnknown;
        if (m_pUnknown) m_pUnknown->AddRef();
    }

    UnknownObject& operator=(const UnknownObject& other) {
        if (this != &other) {
            if (m_pUnknown) m_pUnknown->Release();
            m_pUnknown = other.m_pUnknown;
            if (m_pUnknown) m_pUnknown->AddRef();
        }
        return *this;
    }

    ~UnknownObject() {
        if (m_pUnknown) {
            m_pUnknown->Release();
            m_pUnknown = NULL;
        }
    }

    IUnknown* GetUnknown() const {
        return m_pUnknown;
    }

    template <typename T>
    HRESULT QueryInterface(T** ppInterface) {
        if (!ppInterface || !m_pUnknown) return E_INVALIDARG;
        *ppInterface = NULL;
        return m_pUnknown->QueryInterface(__uuidof(T), (void**)ppInterface);
    }

    HRESULT QueryInterface(REFIID riid, void** ppv) {
        if (!ppv || !m_pUnknown) return E_INVALIDARG;
        *ppv = NULL;
        return m_pUnknown->QueryInterface(riid, ppv);
    }

    void SetUnknown(IUnknown* pUnk) {
        if (m_pUnknown) m_pUnknown->Release();
        m_pUnknown = pUnk;
        if (m_pUnknown) m_pUnknown->AddRef();
    }

    bool IsValid() const {
        return m_pUnknown != NULL;
    }

    void Release() {
        if (m_pUnknown) {
            m_pUnknown->Release();
            m_pUnknown = NULL;
        }
    }
};

// ========================================
// OLE客户端站点实现（无修改）
// ========================================
class COleClientSiteMy : public IOleClientSite, public IOleInPlaceSite, public IOleInPlaceFrame {
public:
    LONG m_nRefCount;
    HWND m_hWndParent;
    RECT m_rect;

    COleClientSiteMy(HWND hWnd) : m_nRefCount(1), m_hWndParent(hWnd) {
        ZeroMemory(&m_rect, sizeof(RECT));
    }

    COleClientSiteMy(HWND hWnd, int x, int y, int cx, int cy) : m_nRefCount(1), m_hWndParent(hWnd) {
        m_rect.left = x;
        m_rect.top = y;
        m_rect.right = x + cx;
        m_rect.bottom = y + cy;
    }

    virtual ~COleClientSiteMy() {}

    void SetRect(int x, int y, int cx, int cy) {
        m_rect.left = x;
        m_rect.top = y;
        m_rect.right = x + cx;
        m_rect.bottom = y + cy;
    }

    STDMETHODIMP QueryInterface(REFIID riid, void** ppv) {
        if (!ppv) return E_POINTER;
        *ppv = NULL;

        if (riid == IID_IUnknown) {
            *ppv = static_cast<IOleClientSite*>(this);
        }
        else if (riid == IID_IOleClientSite) {
            *ppv = static_cast<IOleClientSite*>(this);
        }
        else if (riid == IID_IOleInPlaceSite) {
            *ppv = static_cast<IOleInPlaceSite*>(this);
        }
        else if (riid == IID_IOleInPlaceFrame || riid == IID_IOleInPlaceUIWindow) {
            *ppv = static_cast<IOleInPlaceFrame*>(this);
        }
        else {
            return E_NOINTERFACE;
        }

        AddRef();
        return S_OK;
    }

    STDMETHODIMP_(ULONG) AddRef() {
        return InterlockedIncrement(&m_nRefCount);
    }

    STDMETHODIMP_(ULONG) Release() {
        LONG res = InterlockedDecrement(&m_nRefCount);
        if (res == 0) {
            delete this;
        }
        return res;
    }

    // IOleClientSite 接口
    STDMETHODIMP SaveObject() { return E_NOTIMPL; }
    STDMETHODIMP GetMoniker(DWORD, DWORD, IMoniker**) { return E_NOTIMPL; }
    STDMETHODIMP GetContainer(IOleContainer**) { return E_NOTIMPL; }
    STDMETHODIMP ShowObject() { return S_OK; }
    STDMETHODIMP OnShowWindow(BOOL) { return S_OK; }
    STDMETHODIMP RequestNewObjectLayout() { return E_NOTIMPL; }

    // IOleInPlaceSite 接口
    STDMETHODIMP GetWindow(HWND* phwnd) {
        if (!phwnd) return E_POINTER;
        *phwnd = m_hWndParent;
        return S_OK;
    }

    STDMETHODIMP ContextSensitiveHelp(BOOL) { return E_NOTIMPL; }
    STDMETHODIMP CanInPlaceActivate() { return S_OK; }
    STDMETHODIMP OnInPlaceActivate() { return S_OK; }
    STDMETHODIMP OnUIActivate() { return S_OK; }

    STDMETHODIMP GetWindowContext(IOleInPlaceFrame** ppFrame, IOleInPlaceUIWindow** ppDoc, 
        LPRECT lprcPosRect, LPRECT lprcClipRect, LPOLEINPLACEFRAMEINFO lpFrameInfo) {
        if (!ppFrame || !lprcPosRect || !lprcClipRect || !lpFrameInfo) return E_POINTER;

        *ppFrame = static_cast<IOleInPlaceFrame*>(this);
        (*ppFrame)->AddRef();
        *ppDoc = NULL;

        *lprcPosRect = m_rect;
        *lprcClipRect = m_rect;

        ZeroMemory(lpFrameInfo, sizeof(OLEINPLACEFRAMEINFO));
        lpFrameInfo->cb = sizeof(OLEINPLACEFRAMEINFO);
        lpFrameInfo->fMDIApp = FALSE;
        lpFrameInfo->hwndFrame = m_hWndParent;
        lpFrameInfo->haccel = NULL;
        lpFrameInfo->cAccelEntries = 0;

        return S_OK;
    }

    STDMETHODIMP Scroll(SIZE) { return E_NOTIMPL; }
    STDMETHODIMP OnUIDeactivate(BOOL) { return S_OK; }
    STDMETHODIMP OnInPlaceDeactivate() { return S_OK; }
    STDMETHODIMP DiscardUndoState() { return S_OK; }
    STDMETHODIMP DeactivateAndUndo() { return S_OK; }
    STDMETHODIMP OnPosRectChange(LPCRECT) { return S_OK; }

    // IOleInPlaceFrame 接口
    STDMETHODIMP GetBorder(LPRECT) { return E_NOTIMPL; }
    STDMETHODIMP RequestBorderSpace(LPCBORDERWIDTHS) { return E_NOTIMPL; }
    STDMETHODIMP SetBorderSpace(LPCBORDERWIDTHS) { return E_NOTIMPL; }
    STDMETHODIMP SetActiveObject(IOleInPlaceActiveObject*, LPCOLESTR) { return S_OK; }
    STDMETHODIMP InsertMenus(HMENU, LPOLEMENUGROUPWIDTHS) { return E_NOTIMPL; }
    STDMETHODIMP SetMenu(HMENU, HOLEMENU, HWND) { return E_NOTIMPL; }
    STDMETHODIMP RemoveMenus(HMENU) { return E_NOTIMPL; }
    STDMETHODIMP SetStatusText(LPCOLESTR) { return S_OK; }
    STDMETHODIMP EnableModeless(BOOL) { return S_OK; }
    STDMETHODIMP TranslateAccelerator(LPMSG, WORD) { return E_NOTIMPL; }
};

// ========================================
// 事件定义类（无修改）
// ========================================
class CIGridppReportEvents
{
public:
    virtual void OnInitialize() {}
    virtual void OnFetchRecord() {}
    virtual void OnBeforePostRecord() {}
    virtual void OnBeforeSort(const wchar_t* SortFields) { (void)SortFields; }
    virtual void OnRuntimeError(long ErrorID, const wchar_t* ErrorMsg) { (void)ErrorID; (void)ErrorMsg; }
    virtual void OnProcessBegin() {}
    virtual void OnProcessEnd() {}
    virtual void OnGroupBegin(void* pSender) { (void)pSender; }
    virtual void OnGroupEnd(void* pSender) { (void)pSender; }
    virtual void OnProcessRecord() {}
    virtual void OnGeneratePagesBegin() {}
    virtual void OnGeneratePagesEnd() {}
    virtual void OnPageProcessRecord() {}
    virtual void OnPageStart() {}
    virtual void OnPageEnd() {}
    virtual void OnSectionFormat(void* pSender) { (void)pSender; }
    virtual void OnFieldGetDisplayText(void* pSender) { (void)pSender; }
    virtual void OnTextBoxGetDisplayText(void* pSender) { (void)pSender; }
    virtual void OnControlCustomDraw(void* pSender, void* pGraphics) { (void)pSender; (void)pGraphics; }
    virtual void OnChartRequestData(void* pSender) { (void)pSender; }
    virtual void OnPrintBegin() {}
    virtual void OnPrintEnd() {}
    virtual void OnPrintPage(long PageNo) { (void)PageNo; }
    virtual void OnPrintAborted() {}
    virtual void OnExportBegin(void* pOptionObject) { (void)pOptionObject; }
    virtual void OnExportEnd(void* pOptionObject) { (void)pOptionObject; }
    virtual void OnShowPreviewWnd(void* pPrintViewer) { (void)pPrintViewer; }
    virtual void OnShowDesignerWnd(void* pDesigner) { (void)pDesigner; }
    virtual void OnHyperlinkClick(void* pSender, const wchar_t* HyperlinkText, int FromPreviewPage) {
        (void)pSender; (void)HyperlinkText; (void)FromPreviewPage;
    }

    virtual ~CIGridppReportEvents() {}
};

class CIGRDisplayViewerEvents
{
public:
    virtual void Click() {}
    virtual void DblClick() {}
    virtual void KeyDown(long Shift, long Key) { (void)Shift; (void)Key; }
    virtual void KeyPress(char Key) { (void)Key; }
    virtual void KeyUp(long Shift, long Key) { (void)Shift; (void)Key; }
    virtual void MouseDown(long Button, long Shift, long xPos, long yPos) {
        (void)Button; (void)Shift; (void)xPos; (void)yPos;
    }
    virtual void MouseUp(long Button, long Shift, long xPos, long yPos) {
        (void)Button; (void)Shift; (void)xPos; (void)yPos;
    }
    virtual void MouseMove(long Shift, long xPos, long yPos) {
        (void)Shift; (void)xPos; (void)yPos;
    }
    virtual void ControlClick(void* pSender) { (void)pSender; }
    virtual void ControlDblClick(void* pSender) { (void)pSender; }
    virtual void SectionClick(void* pSender) { (void)pSender; }
    virtual void SectionDblClick(void* pSender) { (void)pSender; }
    virtual void ContentCellClick(void* pSender) { (void)pSender; }
    virtual void ContentCellDblClick(void* pSender) { (void)pSender; }
    virtual void TitleCellClick(void* pSender) { (void)pSender; }
    virtual void TitleCellDblClick(void* pSender) { (void)pSender; }
    virtual void ColumnLayoutChange() {}
    virtual void SelectionCellChange(long OldRow, long OldCol) { (void)OldRow; (void)OldCol; }
    virtual void ChartClickSeries(void* pSender, long SeriesIndex, long GroupIndex) {
        (void)pSender; (void)SeriesIndex; (void)GroupIndex;
    }
    virtual void ChartDblClickSeries(void* pSender, long SeriesIndex, long GroupIndex) {
        (void)pSender; (void)SeriesIndex; (void)GroupIndex;
    }
    virtual void ChartClickLegend(void* pSender, long SeriesIndex, long GroupIndex) {
        (void)pSender; (void)SeriesIndex; (void)GroupIndex;
    }
    virtual void ChartDblClickLegend(void* pSender, long SeriesIndex, long GroupIndex) {
        (void)pSender; (void)SeriesIndex; (void)GroupIndex;
    }
    virtual void BatchFetchRecord() {}
    virtual long StatusChange() { return 0; }
    virtual void CloseButtonClick() {}
    virtual void CustomButtonClick(long BtnID) { (void)BtnID; }
    virtual void ToolbarCommandClick(long ControlType) { (void)ControlType; }

    virtual ~CIGRDisplayViewerEvents() {}
};

class CIGRPrintViewerEvents
{
public:
    virtual void Click() {}
    virtual void DblClick() {}
    virtual void KeyDown(long Shift, long Key) { (void)Shift; (void)Key; }
    virtual void KeyPress(char Key) { (void)Key; }
    virtual void KeyUp(long Shift, long Key) { (void)Shift; (void)Key; }
    virtual void MouseDown(long Button, long Shift, long xPos, long yPos) {
        (void)Button; (void)Shift; (void)xPos; (void)yPos;
    }
    virtual void MouseUp(long Button, long Shift, long xPos, long yPos) {
        (void)Button; (void)Shift; (void)xPos; (void)yPos;
    }
    virtual void MouseMove(long Shift, long xPos, long yPos) {
        (void)Shift; (void)xPos; (void)yPos;
    }
    virtual void CurPageChange(long NewCurPageNo) { (void)NewCurPageNo; }
    virtual long StatusChange() { return 0; }
    virtual void RulerClick(int IsHorzRuler) { (void)IsHorzRuler; }
    virtual void RulerDblClick(int IsHorzRuler) { (void)IsHorzRuler; }
    virtual void PageClick() {}
    virtual void PageDblClick() {}
    virtual void CloseButtonClick() {}
    virtual void CustomButtonClick(long BtnID) { (void)BtnID; }
    virtual void ToolbarCommandClick(long ControlType) { (void)ControlType; }

    virtual ~CIGRPrintViewerEvents() {}
};

// ========================================
// 事件实现类（修改：替换__uuidof为手动定义的IID）
// ========================================
class CGridppReportEventImpl : public IDispatch
{
public:
    CGridppReportEventImpl() : m_nRefCount(0), m_pEvents(NULL) {}
    ~CGridppReportEventImpl() {}

    LONG m_nRefCount;
    CIGridppReportEvents* m_pEvents;

    STDMETHODIMP QueryInterface(REFIID riid, void** ppvObject) {
        if (!ppvObject) return E_POINTER;
        *ppvObject = NULL;
        if (riid == IID_IUnknown || riid == IID_IDispatch || 
            riid == IID__IGridppReportEvents || riid == IID__IGRPrintViewerEvents || 
            riid == IID__IGRDisplayViewerEvents || riid == IID__IGRDesignerEvents) {
            *ppvObject = static_cast<IDispatch*>(this);
            AddRef();
            return S_OK;
        }
        return E_NOINTERFACE;
    }

    STDMETHODIMP_(ULONG) AddRef() {
        return InterlockedIncrement(&m_nRefCount);
    }

    STDMETHODIMP_(ULONG) Release() {
        LONG res = InterlockedDecrement(&m_nRefCount);
        if (res == 0) {
            delete this;
        }
        return res;
    }

    STDMETHODIMP GetTypeInfoCount(UINT* pctinfo) {
        if (!pctinfo) return E_POINTER;
        *pctinfo = 0;
        return S_OK;
    }

    STDMETHODIMP GetTypeInfo(UINT /*iTInfo*/, LCID /*lcid*/, ITypeInfo** /*ppTInfo*/) {
        return E_NOTIMPL;
    }

    STDMETHODIMP GetIDsOfNames(REFIID /*riid*/, LPOLESTR* /*rgszNames*/, UINT /*cNames*/, 
        LCID /*lcid*/, DISPID* /*rgDispId*/) {
        return E_NOTIMPL;
    }

    STDMETHODIMP Invoke(DISPID dispIdMember, REFIID /*riid*/, LCID /*lcid*/, WORD /*wFlags*/,
        DISPPARAMS* pDispParams, VARIANT* /*pVarResult*/, EXCEPINFO* /*pExcepInfo*/, UINT* /*puArgErr*/) {
        if (!m_pEvents || !pDispParams) return E_FAIL;

        switch (dispIdMember) {
        case 1: 
            m_pEvents->OnInitialize();
            break;
        case 2: 
            if (pDispParams->cArgs >= 2) {
                long nErrID = V_I4(&pDispParams->rgvarg[1]);
                const wchar_t* sErrMsg = V_BSTR(&pDispParams->rgvarg[0]);
                m_pEvents->OnRuntimeError(nErrID, sErrMsg);
            }
            break;
        default:
            return DISP_E_MEMBERNOTFOUND;
        }
        return S_OK;
    }
};

class CGRDisplayViewerEventImpl : public IDispatch
{
public:
    CGRDisplayViewerEventImpl() : m_nRefCount(0), m_pEvents(NULL) {}
    ~CGRDisplayViewerEventImpl() {}

    LONG m_nRefCount;
    CIGRDisplayViewerEvents* m_pEvents;

    STDMETHODIMP QueryInterface(REFIID riid, void** ppvObject) {
        if (!ppvObject) return E_POINTER;
        *ppvObject = NULL;
        if (riid == IID_IUnknown || riid == IID_IDispatch || 
            riid == IID__IGridppReportEvents || riid == IID__IGRPrintViewerEvents || 
            riid == IID__IGRDisplayViewerEvents || riid == IID__IGRDesignerEvents) {
            *ppvObject = static_cast<IDispatch*>(this);
            AddRef();
            return S_OK;
        }
        return E_NOINTERFACE;
    }

    STDMETHODIMP_(ULONG) AddRef() {
        return InterlockedIncrement(&m_nRefCount);
    }

    STDMETHODIMP_(ULONG) Release() {
        LONG res = InterlockedDecrement(&m_nRefCount);
        if (res == 0) {
            delete this;
        }
        return res;
    }

    STDMETHODIMP GetTypeInfoCount(UINT* pctinfo) {
        if (!pctinfo) return E_POINTER;
        *pctinfo = 0;
        return S_OK;
    }

    STDMETHODIMP GetTypeInfo(UINT /*iTInfo*/, LCID /*lcid*/, ITypeInfo** /*ppTInfo*/) {
        return E_NOTIMPL;
    }

    STDMETHODIMP GetIDsOfNames(REFIID /*riid*/, LPOLESTR* /*rgszNames*/, UINT /*cNames*/, 
        LCID /*lcid*/, DISPID* /*rgDispId*/) {
        return E_NOTIMPL;
    }

    STDMETHODIMP Invoke(DISPID dispIdMember, REFIID /*riid*/, LCID /*lcid*/, WORD /*wFlags*/,
        DISPPARAMS* pDispParams, VARIANT* /*pVarResult*/, EXCEPINFO* /*pExcepInfo*/, UINT* /*puArgErr*/) {
        if (!m_pEvents || !pDispParams) return E_FAIL;

        switch (dispIdMember) {
        case 1: 
            m_pEvents->Click();
            break;
        case 2: 
            if (pDispParams->cArgs >= 4) {
                long nButton = V_I4(&pDispParams->rgvarg[3]);
                long nShift = V_I4(&pDispParams->rgvarg[2]);
                long nX = V_I4(&pDispParams->rgvarg[1]);
                long nY = V_I4(&pDispParams->rgvarg[0]);
                m_pEvents->MouseDown(nButton, nShift, nX, nY);
            }
            break;
        default:
            return DISP_E_MEMBERNOTFOUND;
        }
        return S_OK;
    }
};

class CGRPrintViewerEventImpl : public IDispatch
{
public:
    CGRPrintViewerEventImpl() : m_nRefCount(0), m_pEvents(NULL) {}
    ~CGRPrintViewerEventImpl() {}

    LONG m_nRefCount;
    CIGRPrintViewerEvents* m_pEvents;

    STDMETHODIMP QueryInterface(REFIID riid, void** ppvObject) {
        if (!ppvObject) return E_POINTER;
        *ppvObject = NULL;
        if (riid == IID_IUnknown || riid == IID_IDispatch || 
            riid == IID__IGridppReportEvents || riid == IID__IGRPrintViewerEvents || 
            riid == IID__IGRDisplayViewerEvents || riid == IID__IGRDesignerEvents) {
            *ppvObject = static_cast<IDispatch*>(this);
            AddRef();
            return S_OK;
        }
        return E_NOINTERFACE;
    }

    STDMETHODIMP_(ULONG) AddRef() {
        return InterlockedIncrement(&m_nRefCount);
    }

    STDMETHODIMP_(ULONG) Release() {
        LONG res = InterlockedDecrement(&m_nRefCount);
        if (res == 0) {
            delete this;
        }
        return res;
    }

    STDMETHODIMP GetTypeInfoCount(UINT* pctinfo) {
        if (!pctinfo) return E_POINTER;
        *pctinfo = 0;
        return S_OK;
    }

    STDMETHODIMP GetTypeInfo(UINT /*iTInfo*/, LCID /*lcid*/, ITypeInfo** /*ppTInfo*/) {
        return E_NOTIMPL;
    }

    STDMETHODIMP GetIDsOfNames(REFIID /*riid*/, LPOLESTR* /*rgszNames*/, UINT /*cNames*/, 
        LCID /*lcid*/, DISPID* /*rgDispId*/) {
        return E_NOTIMPL;
    }

    STDMETHODIMP Invoke(DISPID dispIdMember, REFIID /*riid*/, LCID /*lcid*/, WORD /*wFlags*/,
        DISPPARAMS* pDispParams, VARIANT* /*pVarResult*/, EXCEPINFO* /*pExcepInfo*/, UINT* /*puArgErr*/) {
        if (!m_pEvents || !pDispParams) return E_FAIL;

        switch (dispIdMember) {
        case 1: 
            if (pDispParams->cArgs >= 1) {
                long nPage = V_I4(&pDispParams->rgvarg[0]);
                m_pEvents->CurPageChange(nPage);
            }
            break;
        case 2: 
            m_pEvents->CloseButtonClick();
            break;
        default:
            return DISP_E_MEMBERNOTFOUND;
        }
        return S_OK;
    }
};

// ========================================
// 核心封装类（修复：函数声明格式 + 成员作用域）
// ========================================
class CIGridppReport
{
private:
    UnknownObject m_unkObject; // 确保成员声明在函数之前

public:
    CIGridppReport() {}
    ~CIGridppReport() {}

    // 修复：明确函数返回值类型，移除非法的->语法
    void SetUnknown(IUnknown* pUnk) {
        m_unkObject.SetUnknown(pUnk);
    }

    UnknownObject& GetUnknownObject() {
        return m_unkObject;
    }

    BOOL BindEvent(CIGridppReportEvents* pEvent) {
        if (!m_unkObject.IsValid() || !pEvent) return FALSE;

        CComPtr<IConnectionPointContainer> pCPC;
        HRESULT hr = m_unkObject.QueryInterface(&pCPC);
        if (FAILED(hr)) return FALSE;

        CGridppReportEventImpl* pEventImpl = new CGridppReportEventImpl;
        pEventImpl->m_pEvents = pEvent;

        CComPtr<IConnectionPoint> pCP;
        // 改用手动定义的报表事件IID
        hr = pCPC->FindConnectionPoint(IID__IGridppReportEvents, &pCP);
        if (FAILED(hr)) {
            pEventImpl->Release();
            return FALSE;
        }

        DWORD dwCookie = 0;
        hr = pCP->Advise(pEventImpl, &dwCookie);
        if (FAILED(hr)) {
            pEventImpl->Release();
            return FALSE;
        }

        return TRUE;
    }
};

class CIGRDisplayViewer
{
private:
    UnknownObject m_unkObject; // 确保成员声明在函数之前

public:
    CIGRDisplayViewer() {}
    ~CIGRDisplayViewer() {}

    // 修复：明确函数返回值类型，移除非法的->语法
    void SetUnknown(IUnknown* pUnk) {
        m_unkObject.SetUnknown(pUnk);
    }

    UnknownObject& GetUnknownObject() {
        return m_unkObject;
    }

    BOOL BindEvent(CIGRDisplayViewerEvents* pEvent) {
        if (!m_unkObject.IsValid() || !pEvent) return FALSE;

        CComPtr<IConnectionPointContainer> pCPC;
        HRESULT hr = m_unkObject.QueryInterface(&pCPC);
        if (FAILED(hr)) return FALSE;

        CGRDisplayViewerEventImpl* pEventImpl = new CGRDisplayViewerEventImpl;
        pEventImpl->m_pEvents = pEvent;

        CComPtr<IConnectionPoint> pCP;
        // 改用手动定义的查询显示器事件IID
        hr = pCPC->FindConnectionPoint(IID__IGRDisplayViewerEvents, &pCP);
        if (FAILED(hr)) {
            pEventImpl->Release();
            return FALSE;
        }

        DWORD dwCookie = 0;
        hr = pCP->Advise(pEventImpl, &dwCookie);
        if (FAILED(hr)) {
            pEventImpl->Release();
            return FALSE;
        }

        return TRUE;
    }
};

class CIGRPrintViewer
{
private:
    UnknownObject m_unkObject; // 确保成员声明在函数之前

public:
    CIGRPrintViewer() {}
    ~CIGRPrintViewer() {}

    // 修复：明确函数返回值类型，移除非法的->语法
    void SetUnknown(IUnknown* pUnk) {
        m_unkObject.SetUnknown(pUnk);
    }

    UnknownObject& GetUnknownObject() {
        return m_unkObject;
    }

    BOOL BindEvent(CIGRPrintViewerEvents* pEvent) {
        std::cout << "[Debug] 开始绑定打印显示器事件..." << std::endl;
        if (!m_unkObject.IsValid()) {
            std::cout << "[Debug] 错误：m_unkObject 无效，请检查是否调用了 SetUnknown" << std::endl;
            return FALSE;
        }
        if (!pEvent) {
            std::cout << "[Debug] 错误：传入的 pEvent 指针为空" << std::endl;
            return FALSE;
        }

        CComPtr<IConnectionPointContainer> pCPC;
        HRESULT hr = m_unkObject.QueryInterface(&pCPC);
        if (FAILED(hr)) {
            std::cout << "[Debug] 错误：对象不支持 IConnectionPointContainer (hr=0x" << std::hex << hr << ")" << std::endl;
            return FALSE;
        }

        CGRPrintViewerEventImpl* pEventImpl = new CGRPrintViewerEventImpl;
        pEventImpl->m_pEvents = pEvent;

        CComPtr<IConnectionPoint> pCP;
        hr = pCPC->FindConnectionPoint(IID__IGRPrintViewerEvents, &pCP);
        if (FAILED(hr)) {
            std::cout << "[Debug] 错误：找不到打印显示器事件连接点 (hr=0x" << std::hex << hr << ")" << std::endl;
            pEventImpl->Release();
            return FALSE;
        }

        DWORD dwCookie = 0;
        hr = pCP->Advise(pEventImpl, &dwCookie);
        if (FAILED(hr)) {
            std::cout << "[Debug] 错误：Advise 绑定失败 (hr=0x" << std::hex << hr << ")" << std::endl;
            pEventImpl->Release();
            return FALSE;
        }

        std::cout << "[Debug] 打印显示器事件绑定成功！Cookie=" << std::dec << dwCookie << std::endl;
        return TRUE;
    }
};

// ========================================
// OCX嵌入函数（修改：CLSID改用手动定义）
// ========================================
HRESULT EmbedGRReportOCX(HWND hWndParent, RECT rcPos, UnknownObject& outUnkObject) {
    if (!hWndParent) return E_INVALIDARG;

    OleInitialize(NULL);

    COleClientSiteMy* pClientSite = new COleClientSiteMy(hWndParent, rcPos.left, rcPos.top, 
        rcPos.right - rcPos.left, rcPos.bottom - rcPos.top);
    if (!pClientSite) return E_OUTOFMEMORY;

    CComPtr<IUnknown> pUnkOCX;
    // 改用手动定义的报表CLSID创建实例
    HRESULT hr = CoCreateInstance(CLSID_GridppReport, NULL, CLSCTX_INPROC_SERVER, 
        IID_IUnknown, (void**)&pUnkOCX);
    if (FAILED(hr)) {
        pClientSite->Release();
        return hr;
    }

    CComPtr<IOleObject> pOleObj;
    hr = pUnkOCX->QueryInterface(IID_IOleObject, (void**)&pOleObj);
    if (FAILED(hr)) {
        pClientSite->Release();
        return hr;
    }

    hr = pOleObj->SetClientSite(pClientSite);
    if (FAILED(hr)) {
        pClientSite->Release();
        return hr;
    }

    hr = pOleObj->DoVerb(OLEIVERB_INPLACEACTIVATE, NULL, pClientSite, 0, hWndParent, &rcPos);
    if (FAILED(hr)) {
        pClientSite->Release();
        return hr;
    }

    outUnkObject.SetUnknown(pUnkOCX);
    pClientSite->Release();
    return S_OK;
}