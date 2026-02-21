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
// Debug Output Macro
// ========================================
#define DEBUG_OUT(msg) OutputDebugStringA(msg)
#define DEBUG_OUTF(fmt, ...) do { char _buf[512]; sprintf(_buf, fmt, __VA_ARGS__); OutputDebugStringA(_buf); } while(0)

// ========================================
// 1. Manual definition of CLSID/IID constants
// ========================================
// Report CLSID
const CLSID CLSID_GridppReport = { 0xF9364159, 0x6AED, 0x4F9C, { 0x8B, 0xAF, 0xD7, 0xC7, 0xED, 0x61, 0x60, 0xA8 } };
// Report Events IID
const IID IID__IGridppReportEvents = { 0x330F80F5, 0x4568, 0x4F70, { 0xBF, 0xCB, 0x68, 0x3D, 0x3B, 0x90, 0xFE, 0xBB } };

// Print Viewer CLSID
const CLSID CLSID_GRPrintViewer = { 0x44CBB5DE, 0x5AFB, 0x4C3D, { 0x8F, 0x3F, 0x0F, 0x70, 0xCA, 0x53, 0x72, 0xAD } };
// Print Viewer Events IID
const IID IID__IGRPrintViewerEvents = { 0xA9E920A1, 0xC722, 0x4A81, { 0x9F, 0xCF, 0x4D, 0x5E, 0xBF, 0xFA, 0x21, 0xF4 } };

// Display Viewer CLSID
const CLSID CLSID_GRDisplayViewer = { 0x1B5EA181, 0xA38D, 0x4F42, { 0x88, 0xB2, 0x6A, 0xF7, 0x4C, 0xF6, 0xD6, 0xC0 } };
// Display Viewer Events IID
const IID IID__IGRDisplayViewerEvents = { 0x2564DCE8, 0xFEDB, 0x4EB6, { 0xB7, 0xF9, 0x50, 0x26, 0xF7, 0xF1, 0x04, 0x1E } };

// Report Designer CLSID (backup)
const CLSID CLSID_GRDesigner = { 0x6EDD80CB, 0x9F08, 0x4C71, { 0xB4, 0x06, 0x47, 0x9E, 0x5C, 0xB8, 0x0F, 0xCE } };
// Report Designer Events IID (backup)
const IID IID__IGRDesignerEvents = { 0xB89F92F4, 0x5E58, 0x413E, { 0x8E, 0x73, 0x4A, 0x0B, 0x28, 0xB9, 0xE9, 0xBF } };

// ========================================
// Encapsulate UnknownObject
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
// OLE client site implementation
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

    // IOleClientSite interface
    STDMETHODIMP SaveObject() { return E_NOTIMPL; }
    STDMETHODIMP GetMoniker(DWORD, DWORD, IMoniker**) { return E_NOTIMPL; }
    STDMETHODIMP GetContainer(IOleContainer**) { return E_NOTIMPL; }
    STDMETHODIMP ShowObject() { return S_OK; }
    STDMETHODIMP OnShowWindow(BOOL) { return S_OK; }
    STDMETHODIMP RequestNewObjectLayout() { return E_NOTIMPL; }

    // IOleInPlaceSite interface
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

    // IOleInPlaceFrame interface
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
// Event definition classes
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
    // RequestData: Event triggered when the report requests data
    // pReport: Report object (IUnknown, can be QueryInterface to IGridppReport)
    virtual void RequestData(IUnknown* pReport) {}

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
// Event implementation classes
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
        
        DEBUG_OUTF("[Grid++] Report Invoke: DISPID=%d, cArgs=%d\n", dispIdMember, pDispParams ? pDispParams->cArgs : 0);
        
        if (!m_pEvents || !pDispParams) return E_FAIL;

        switch (dispIdMember) {
        // 1: Initialize
        case 1: 
            DEBUG_OUT("[Grid++] Report: OnInitialize\n");
            m_pEvents->OnInitialize();
            break;
        // 2: FetchRecord
        case 2: 
            DEBUG_OUT("[Grid++] Report: OnFetchRecord\n");
            m_pEvents->OnFetchRecord();
            break;
        // 3: BeforePostRecord
        case 3: 
            DEBUG_OUT("[Grid++] Report: OnBeforePostRecord\n");
            m_pEvents->OnBeforePostRecord();
            break;
        // 4: BeforeSort
        case 4: 
            DEBUG_OUT("[Grid++] Report: OnBeforeSort\n");
            if (pDispParams->cArgs >= 1) {
                m_pEvents->OnBeforeSort(V_BSTR(&pDispParams->rgvarg[0]));
            }
            break;
        // 5: RuntimeError
        case 5: 
            DEBUG_OUT("[Grid++] Report: OnRuntimeError\n");
            if (pDispParams->cArgs >= 2) {
                long nErrID = V_I4(&pDispParams->rgvarg[1]);
                const wchar_t* sErrMsg = V_BSTR(&pDispParams->rgvarg[0]);
                m_pEvents->OnRuntimeError(nErrID, sErrMsg);
            }
            break;
        // 20: FieldGetDisplayText
        case 20: {
            DEBUG_OUT("[Grid++] Report: OnFieldGetDisplayText\n");
            void* pSender = NULL;
            if (pDispParams->cArgs >= 1) {
                VARIANT* parg = &pDispParams->rgvarg[0];
                if (V_VT(parg) == VT_UNKNOWN && V_UNKNOWN(parg) != NULL) pSender = (void*)V_UNKNOWN(parg);
                else if (V_VT(parg) == VT_DISPATCH && V_DISPATCH(parg) != NULL) pSender = (void*)V_DISPATCH(parg);
            }
            m_pEvents->OnFieldGetDisplayText(pSender);
            break;
        }
        // 22: TextBoxGetDisplayText
        case 22: {
            DEBUG_OUT("[Grid++] Report: OnTextBoxGetDisplayText\n");
            void* pSender = NULL;
            if (pDispParams->cArgs >= 1) {
                VARIANT* parg = &pDispParams->rgvarg[0];
                if (V_VT(parg) == VT_UNKNOWN && V_UNKNOWN(parg) != NULL) pSender = (void*)V_UNKNOWN(parg);
                else if (V_VT(parg) == VT_DISPATCH && V_DISPATCH(parg) != NULL) pSender = (void*)V_DISPATCH(parg);
            }
            m_pEvents->OnTextBoxGetDisplayText(pSender);
            break;
        }
        // 23: SectionFormat
        case 23: {
            DEBUG_OUT("[Grid++] Report: OnSectionFormat\n");
            void* pSender = NULL;
            if (pDispParams->cArgs >= 1) {
                VARIANT* parg = &pDispParams->rgvarg[0];
                if (V_VT(parg) == VT_UNKNOWN && V_UNKNOWN(parg) != NULL) pSender = (void*)V_UNKNOWN(parg);
                else if (V_VT(parg) == VT_DISPATCH && V_DISPATCH(parg) != NULL) pSender = (void*)V_DISPATCH(parg);
            }
            m_pEvents->OnSectionFormat(pSender);
            break;
        }
        // 24: ControlCustomDraw
        case 24: {
            DEBUG_OUT("[Grid++] Report: OnControlCustomDraw\n");
            void* pSender = NULL;
            void* pGraphics = NULL;
            if (pDispParams->cArgs >= 2) {
                VARIANT* parg0 = &pDispParams->rgvarg[0];
                VARIANT* parg1 = &pDispParams->rgvarg[1];
                if (V_VT(parg0) == VT_UNKNOWN && V_UNKNOWN(parg0) != NULL) pSender = (void*)V_UNKNOWN(parg0);
                else if (V_VT(parg0) == VT_DISPATCH && V_DISPATCH(parg0) != NULL) pSender = (void*)V_DISPATCH(parg0);
                if (V_VT(parg1) == VT_UNKNOWN && V_UNKNOWN(parg1) != NULL) pGraphics = (void*)V_UNKNOWN(parg1);
                else if (V_VT(parg1) == VT_DISPATCH && V_DISPATCH(parg1) != NULL) pGraphics = (void*)V_DISPATCH(parg1);
            }
            m_pEvents->OnControlCustomDraw(pSender, pGraphics);
            break;
        }
        // 25: ChartRequestData
        case 25: {
            DEBUG_OUT("[Grid++] Report: OnChartRequestData\n");
            void* pSender = NULL;
            if (pDispParams->cArgs >= 1) {
                VARIANT* parg = &pDispParams->rgvarg[0];
                if (V_VT(parg) == VT_UNKNOWN && V_UNKNOWN(parg) != NULL) pSender = (void*)V_UNKNOWN(parg);
                else if (V_VT(parg) == VT_DISPATCH && V_DISPATCH(parg) != NULL) pSender = (void*)V_DISPATCH(parg);
            }
            m_pEvents->OnChartRequestData(pSender);
            break;
        }
        // 30: ProcessBegin
        case 30: 
            DEBUG_OUT("[Grid++] Report: OnProcessBegin\n");
            m_pEvents->OnProcessBegin();
            break;
        // 31: ProcessEnd
        case 31: 
            DEBUG_OUT("[Grid++] Report: OnProcessEnd\n");
            m_pEvents->OnProcessEnd();
            break;
        // 32: GroupBegin
        case 32: 
            DEBUG_OUT("[Grid++] Report: OnGroupBegin\n");
            m_pEvents->OnGroupBegin(NULL);
            break;
        // 33: GroupEnd
        case 33: 
            DEBUG_OUT("[Grid++] Report: OnGroupEnd\n");
            m_pEvents->OnGroupEnd(NULL);
            break;
        // 34: ProcessRecord
        case 34: 
            DEBUG_OUT("[Grid++] Report: OnProcessRecord\n");
            m_pEvents->OnProcessRecord();
            break;
        // 35: PageProcessRecord
        case 35: 
            DEBUG_OUT("[Grid++] Report: OnPageProcessRecord\n");
            m_pEvents->OnPageProcessRecord();
            break;
        // 36: PageStart
        case 36: 
            DEBUG_OUT("[Grid++] Report: OnPageStart\n");
            m_pEvents->OnPageStart();
            break;
        // 37: PageEnd
        case 37: 
            DEBUG_OUT("[Grid++] Report: OnPageEnd\n");
            m_pEvents->OnPageEnd();
            break;
        // 38: GeneratePagesBegin
        case 38: 
            DEBUG_OUT("[Grid++] Report: OnGeneratePagesBegin\n");
            m_pEvents->OnGeneratePagesBegin();
            break;
        // 39: GeneratePagesEnd
        case 39: 
            DEBUG_OUT("[Grid++] Report: OnGeneratePagesEnd\n");
            m_pEvents->OnGeneratePagesEnd();
            break;
        // 40: PrintBegin
        case 40: 
            DEBUG_OUT("[Grid++] Report: OnPrintBegin\n");
            m_pEvents->OnPrintBegin();
            break;
        // 41: PrintEnd
        case 41: 
            DEBUG_OUT("[Grid++] Report: OnPrintEnd\n");
            m_pEvents->OnPrintEnd();
            break;
        // 42: PrintPage
        case 42: 
            DEBUG_OUT("[Grid++] Report: OnPrintPage\n");
            if (pDispParams->cArgs >= 1) {
                m_pEvents->OnPrintPage(V_I4(&pDispParams->rgvarg[0]));
            }
            break;
        // 43: PrintAborted
        case 43: 
            DEBUG_OUT("[Grid++] Report: OnPrintAborted\n");
            m_pEvents->OnPrintAborted();
            break;
        // 50: ExportBegin
        case 50: 
            DEBUG_OUT("[Grid++] Report: OnExportBegin\n");
            m_pEvents->OnExportBegin(NULL);
            break;
        // 51: ExportEnd
        case 51: 
            DEBUG_OUT("[Grid++] Report: OnExportEnd\n");
            m_pEvents->OnExportEnd(NULL);
            break;
        // 60: ShowPreviewWnd
        case 60: 
            DEBUG_OUT("[Grid++] Report: OnShowPreviewWnd\n");
            m_pEvents->OnShowPreviewWnd(NULL);
            break;
        // 61: ShowDesignerWnd
        case 61: 
            DEBUG_OUT("[Grid++] Report: OnShowDesignerWnd\n");
            m_pEvents->OnShowDesignerWnd(NULL);
            break;
        // 65: HyperlinkClick
        case 65: {
            DEBUG_OUT("[Grid++] Report: OnHyperlinkClick\n");
            const wchar_t* HyperlinkText = L"";
            int FromPreviewPage = 0;
            if (pDispParams->cArgs >= 3) {
                HyperlinkText = V_BSTR(&pDispParams->rgvarg[1]);
                FromPreviewPage = V_I4(&pDispParams->rgvarg[0]);
            }
            m_pEvents->OnHyperlinkClick(NULL, HyperlinkText, FromPreviewPage);
            break;
        }
        default:
            DEBUG_OUTF("[Grid++] Report Invoke: Unknown DISPID=%d\n", dispIdMember);
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
        
        DEBUG_OUTF("[Grid++] DisplayViewer Invoke: DISPID=%d, cArgs=%d\n", dispIdMember, pDispParams ? pDispParams->cArgs : 0);
        
        if (!m_pEvents || !pDispParams) return E_FAIL;

        switch (dispIdMember) {
        // -600 ~ -607: Basic mouse and keyboard events
        case -600: 
            DEBUG_OUT("[Grid++] DisplayViewer: Click\n");
            m_pEvents->Click();
            break;
        case -601: 
            DEBUG_OUT("[Grid++] DisplayViewer: DblClick\n");
            m_pEvents->DblClick();
            break;
        case -602: 
            DEBUG_OUT("[Grid++] DisplayViewer: KeyDown\n");
            if (pDispParams->cArgs >= 2) {
                m_pEvents->KeyDown(V_I4(&pDispParams->rgvarg[1]), V_I4(&pDispParams->rgvarg[0]));
            }
            break;
        case -603: 
            DEBUG_OUT("[Grid++] DisplayViewer: KeyPress\n");
            if (pDispParams->cArgs >= 1) {
                m_pEvents->KeyPress((char)V_I1(&pDispParams->rgvarg[0]));
            }
            break;
        case -604: 
            DEBUG_OUT("[Grid++] DisplayViewer: KeyUp\n");
            if (pDispParams->cArgs >= 2) {
                m_pEvents->KeyUp(V_I4(&pDispParams->rgvarg[1]), V_I4(&pDispParams->rgvarg[0]));
            }
            break;
        case -605: 
            DEBUG_OUT("[Grid++] DisplayViewer: MouseDown\n");
            if (pDispParams->cArgs >= 4) {
                m_pEvents->MouseDown(V_I4(&pDispParams->rgvarg[3]), V_I4(&pDispParams->rgvarg[2]), V_I4(&pDispParams->rgvarg[1]), V_I4(&pDispParams->rgvarg[0]));
            }
            break;
        case -606: 
            DEBUG_OUT("[Grid++] DisplayViewer: MouseMove\n");
            if (pDispParams->cArgs >= 3) {
                m_pEvents->MouseMove(V_I4(&pDispParams->rgvarg[2]), V_I4(&pDispParams->rgvarg[1]), V_I4(&pDispParams->rgvarg[0]));
            }
            break;
        case -607: 
            DEBUG_OUT("[Grid++] DisplayViewer: MouseUp\n");
            if (pDispParams->cArgs >= 4) {
                m_pEvents->MouseUp(V_I4(&pDispParams->rgvarg[3]), V_I4(&pDispParams->rgvarg[2]), V_I4(&pDispParams->rgvarg[1]), V_I4(&pDispParams->rgvarg[0]));
            }
            break;
        // 1-14: Control events
        case 1: {
            DEBUG_OUT("[Grid++] DisplayViewer: ControlClick\n");
            void* pSender = NULL;
            if (pDispParams->cArgs >= 1) {
                VARIANT* parg = &pDispParams->rgvarg[0];
                if (V_VT(parg) == VT_UNKNOWN && V_UNKNOWN(parg) != NULL) pSender = (void*)V_UNKNOWN(parg);
                else if (V_VT(parg) == VT_DISPATCH && V_DISPATCH(parg) != NULL) pSender = (void*)V_DISPATCH(parg);
            }
            m_pEvents->ControlClick(pSender);
            break;
        }
        case 2: {
            DEBUG_OUT("[Grid++] DisplayViewer: ControlDblClick\n");
            void* pSender = NULL;
            if (pDispParams->cArgs >= 1) {
                VARIANT* parg = &pDispParams->rgvarg[0];
                if (V_VT(parg) == VT_UNKNOWN && V_UNKNOWN(parg) != NULL) pSender = (void*)V_UNKNOWN(parg);
                else if (V_VT(parg) == VT_DISPATCH && V_DISPATCH(parg) != NULL) pSender = (void*)V_DISPATCH(parg);
            }
            m_pEvents->ControlDblClick(pSender);
            break;
        }
        case 3: {
            DEBUG_OUT("[Grid++] DisplayViewer: SectionClick\n");
            void* pSender = NULL;
            if (pDispParams->cArgs >= 1) {
                VARIANT* parg = &pDispParams->rgvarg[0];
                if (V_VT(parg) == VT_UNKNOWN && V_UNKNOWN(parg) != NULL) pSender = (void*)V_UNKNOWN(parg);
                else if (V_VT(parg) == VT_DISPATCH && V_DISPATCH(parg) != NULL) pSender = (void*)V_DISPATCH(parg);
            }
            m_pEvents->SectionClick(pSender);
            break;
        }
        case 4: {
            DEBUG_OUT("[Grid++] DisplayViewer: SectionDblClick\n");
            void* pSender = NULL;
            if (pDispParams->cArgs >= 1) {
                VARIANT* parg = &pDispParams->rgvarg[0];
                if (V_VT(parg) == VT_UNKNOWN && V_UNKNOWN(parg) != NULL) pSender = (void*)V_UNKNOWN(parg);
                else if (V_VT(parg) == VT_DISPATCH && V_DISPATCH(parg) != NULL) pSender = (void*)V_DISPATCH(parg);
            }
            m_pEvents->SectionDblClick(pSender);
            break;
        }
        case 5: {
            DEBUG_OUT("[Grid++] DisplayViewer: ContentCellClick\n");
            void* pSender = NULL;
            if (pDispParams->cArgs >= 1) {
                VARIANT* parg = &pDispParams->rgvarg[0];
                if (V_VT(parg) == VT_UNKNOWN && V_UNKNOWN(parg) != NULL) pSender = (void*)V_UNKNOWN(parg);
                else if (V_VT(parg) == VT_DISPATCH && V_DISPATCH(parg) != NULL) pSender = (void*)V_DISPATCH(parg);
            }
            m_pEvents->ContentCellClick(pSender);
            break;
        }
        case 6: {
            DEBUG_OUT("[Grid++] DisplayViewer: ContentCellDblClick\n");
            void* pSender = NULL;
            if (pDispParams->cArgs >= 1) {
                VARIANT* parg = &pDispParams->rgvarg[0];
                if (V_VT(parg) == VT_UNKNOWN && V_UNKNOWN(parg) != NULL) pSender = (void*)V_UNKNOWN(parg);
                else if (V_VT(parg) == VT_DISPATCH && V_DISPATCH(parg) != NULL) pSender = (void*)V_DISPATCH(parg);
            }
            m_pEvents->ContentCellDblClick(pSender);
            break;
        }
        case 7: {
            DEBUG_OUT("[Grid++] DisplayViewer: TitleCellClick\n");
            void* pSender = NULL;
            if (pDispParams->cArgs >= 1) {
                VARIANT* parg = &pDispParams->rgvarg[0];
                if (V_VT(parg) == VT_UNKNOWN && V_UNKNOWN(parg) != NULL) pSender = (void*)V_UNKNOWN(parg);
                else if (V_VT(parg) == VT_DISPATCH && V_DISPATCH(parg) != NULL) pSender = (void*)V_DISPATCH(parg);
            }
            m_pEvents->TitleCellClick(pSender);
            break;
        }
        case 8: {
            DEBUG_OUT("[Grid++] DisplayViewer: TitleCellDblClick\n");
            void* pSender = NULL;
            if (pDispParams->cArgs >= 1) {
                VARIANT* parg = &pDispParams->rgvarg[0];
                if (V_VT(parg) == VT_UNKNOWN && V_UNKNOWN(parg) != NULL) pSender = (void*)V_UNKNOWN(parg);
                else if (V_VT(parg) == VT_DISPATCH && V_DISPATCH(parg) != NULL) pSender = (void*)V_DISPATCH(parg);
            }
            m_pEvents->TitleCellDblClick(pSender);
            break;
        }
        case 9: 
            DEBUG_OUT("[Grid++] DisplayViewer: ColumnLayoutChange\n");
            m_pEvents->ColumnLayoutChange(); 
            break;
        case 10: 
            DEBUG_OUT("[Grid++] DisplayViewer: SelectionCellChange\n");
            if (pDispParams->cArgs >= 2) { 
                m_pEvents->SelectionCellChange(V_I4(&pDispParams->rgvarg[1]), V_I4(&pDispParams->rgvarg[0])); 
            } 
            break;
        case 15: 
            DEBUG_OUT("[Grid++] DisplayViewer: BatchFetchRecord\n");
            m_pEvents->BatchFetchRecord(); 
            break;
        case 22: 
            DEBUG_OUT("[Grid++] DisplayViewer: StatusChange\n");
            m_pEvents->StatusChange(); 
            break;
        case 29: 
            DEBUG_OUT("[Grid++] DisplayViewer: CloseButtonClick\n");
            m_pEvents->CloseButtonClick(); 
            break;
        case 30: 
            DEBUG_OUT("[Grid++] DisplayViewer: CustomButtonClick\n");
            if (pDispParams->cArgs >= 1) { m_pEvents->CustomButtonClick(V_I4(&pDispParams->rgvarg[0])); } 
            break;
        case 31: 
            DEBUG_OUT("[Grid++] DisplayViewer: ToolbarCommandClick\n");
            if (pDispParams->cArgs >= 1) { m_pEvents->ToolbarCommandClick(V_I4(&pDispParams->rgvarg[0])); } 
            break;
        // 32: RequestData
        case 32: {
            DEBUG_OUT("[Grid++] DisplayViewer: RequestData\n");
            IUnknown* pReport = NULL;
            if (pDispParams && pDispParams->cArgs > 0 && pDispParams->rgvarg) {
                // Extract IUnknown* parameter from the event
                if (pDispParams->rgvarg[0].vt == VT_UNKNOWN) {
                    pReport = pDispParams->rgvarg[0].punkVal;
                    DEBUG_OUTF("[Grid++] DisplayViewer: RequestData pReport=0x%p\n", pReport);
                }
            }
            m_pEvents->RequestData(pReport);
            break;
        }
        default:
            DEBUG_OUTF("[Grid++] DisplayViewer Invoke: Unknown DISPID=%d\n", dispIdMember);
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
        
        DEBUG_OUTF("[Grid++] PrintViewer Invoke: DISPID=%d, cArgs=%d\n", dispIdMember, pDispParams ? pDispParams->cArgs : 0);
        
        if (!m_pEvents || !pDispParams) return E_FAIL;

        switch (dispIdMember) {
        // -600 ~ -607: Basic mouse and keyboard events
        case -600: 
            DEBUG_OUT("[Grid++] PrintViewer: Click\n");
            m_pEvents->Click();
            break;
        case -601: 
            DEBUG_OUT("[Grid++] PrintViewer: DblClick\n");
            m_pEvents->DblClick();
            break;
        case -602: 
            DEBUG_OUT("[Grid++] PrintViewer: KeyDown\n");
            if (pDispParams->cArgs >= 2) {
                m_pEvents->KeyDown(V_I4(&pDispParams->rgvarg[1]), V_I4(&pDispParams->rgvarg[0]));
            }
            break;
        case -603: 
            DEBUG_OUT("[Grid++] PrintViewer: KeyPress\n");
            if (pDispParams->cArgs >= 1) {
                m_pEvents->KeyPress((char)V_I1(&pDispParams->rgvarg[0]));
            }
            break;
        case -604: 
            DEBUG_OUT("[Grid++] PrintViewer: KeyUp\n");
            if (pDispParams->cArgs >= 2) {
                m_pEvents->KeyUp(V_I4(&pDispParams->rgvarg[1]), V_I4(&pDispParams->rgvarg[0]));
            }
            break;
        case -605: 
            DEBUG_OUT("[Grid++] PrintViewer: MouseDown\n");
            if (pDispParams->cArgs >= 4) {
                m_pEvents->MouseDown(V_I4(&pDispParams->rgvarg[3]), V_I4(&pDispParams->rgvarg[2]), V_I4(&pDispParams->rgvarg[1]), V_I4(&pDispParams->rgvarg[0]));
            }
            break;
        case -606: 
            DEBUG_OUT("[Grid++] PrintViewer: MouseMove\n");
            if (pDispParams->cArgs >= 3) {
                m_pEvents->MouseMove(V_I4(&pDispParams->rgvarg[2]), V_I4(&pDispParams->rgvarg[1]), V_I4(&pDispParams->rgvarg[0]));
            }
            break;
        case -607: 
            DEBUG_OUT("[Grid++] PrintViewer: MouseUp\n");
            if (pDispParams->cArgs >= 4) {
                m_pEvents->MouseUp(V_I4(&pDispParams->rgvarg[3]), V_I4(&pDispParams->rgvarg[2]), V_I4(&pDispParams->rgvarg[1]), V_I4(&pDispParams->rgvarg[0]));
            }
            break;
        // 21-31: PrintViewer specific events
        case 21: 
            DEBUG_OUT("[Grid++] PrintViewer: CurPageChange\n");
            if (pDispParams->cArgs >= 1) {
                m_pEvents->CurPageChange(V_I4(&pDispParams->rgvarg[0]));
            }
            break;
        case 22: 
            DEBUG_OUT("[Grid++] PrintViewer: StatusChange\n");
            m_pEvents->StatusChange();
            break;
        case 23: 
            DEBUG_OUT("[Grid++] PrintViewer: RulerClick\n");
            if (pDispParams->cArgs >= 1) {
                m_pEvents->RulerClick(V_I4(&pDispParams->rgvarg[0]));
            }
            break;
        case 24: 
            DEBUG_OUT("[Grid++] PrintViewer: RulerDblClick\n");
            if (pDispParams->cArgs >= 1) {
                m_pEvents->RulerDblClick(V_I4(&pDispParams->rgvarg[0]));
            }
            break;
        case 27: 
            DEBUG_OUT("[Grid++] PrintViewer: PageClick\n");
            m_pEvents->PageClick();
            break;
        case 28: 
            DEBUG_OUT("[Grid++] PrintViewer: PageDblClick\n");
            m_pEvents->PageDblClick();
            break;
        case 29: 
            DEBUG_OUT("[Grid++] PrintViewer: CloseButtonClick\n");
            m_pEvents->CloseButtonClick();
            break;
        case 30: 
            DEBUG_OUT("[Grid++] PrintViewer: CustomButtonClick\n");
            if (pDispParams->cArgs >= 1) {
                m_pEvents->CustomButtonClick(V_I4(&pDispParams->rgvarg[0]));
            }
            break;
        case 31: 
            DEBUG_OUT("[Grid++] PrintViewer: ToolbarCommandClick\n");
            if (pDispParams->cArgs >= 1) {
                m_pEvents->ToolbarCommandClick(V_I4(&pDispParams->rgvarg[0]));
            }
            break;
        default:
            DEBUG_OUTF("[Grid++] PrintViewer Invoke: Unknown DISPID=%d\n", dispIdMember);
            return DISP_E_MEMBERNOTFOUND;
        }
        return S_OK;
    }
};

// ========================================
// Core wrapper classes
// ========================================
class CIGridppReport
{
private:
    UnknownObject m_unkObject;

public:
    // Event handler member variable
    CIGridppReportEvents* m_pDispatch;

    CIGridppReport() : m_unkObject(), m_pDispatch(NULL) {}
    ~CIGridppReport() {}

    void SetUnknown(IUnknown* pUnk) {
        m_unkObject.SetUnknown(pUnk);
    }

    UnknownObject& GetUnknownObject() {
        return m_unkObject;
    }

    BOOL BindEvent(CIGridppReportEvents* pEvent) {
        // Save event handler to m_pDispatch if provided
        if (pEvent) {
            m_pDispatch = pEvent;
        }
        
        // Check if event handler is valid
        if (!m_pDispatch) {
            DEBUG_OUT("[Grid++] Report BindEvent: m_pDispatch is NULL!\n");
            return FALSE;
        }

        if (!m_unkObject.IsValid()) {
            DEBUG_OUT("[Grid++] Report BindEvent: m_unkObject invalid!\n");
            return FALSE;
        }

        CComPtr<IConnectionPointContainer> pCPC;
        HRESULT hr = m_unkObject.QueryInterface(&pCPC);
        if (FAILED(hr)) {
            DEBUG_OUTF("[Grid++] Report BindEvent: QueryInterface failed hr=0x%08X\n", hr);
            return FALSE;
        }

        CGridppReportEventImpl* pEventImpl = new CGridppReportEventImpl;
        pEventImpl->m_pEvents = m_pDispatch;

        CComPtr<IConnectionPoint> pCP;
        hr = pCPC->FindConnectionPoint(IID__IGridppReportEvents, &pCP);
        if (FAILED(hr)) {
            DEBUG_OUTF("[Grid++] Report BindEvent: FindConnectionPoint failed hr=0x%08X\n", hr);
            pEventImpl->Release();
            return FALSE;
        }

        DWORD dwCookie = 0;
        hr = pCP->Advise(pEventImpl, &dwCookie);
        if (FAILED(hr)) {
            DEBUG_OUTF("[Grid++] Report BindEvent: Advise failed hr=0x%08X\n", hr);
            pEventImpl->Release();
            return FALSE;
        }

        DEBUG_OUT("[Grid++] Report BindEvent: SUCCESS!\n");
        return TRUE;
    }

    // SetObjectPos - Adjust the position and size of the OCX window
    // Parameters:
    //   pUnk - IUnknown pointer of the OCX object
    //   x, y - Position coordinates
    //   cx, cy - Width and height
    void SetObjectPos(IUnknown* pUnk, int x, int y, int cx, int cy) {
        if (!pUnk) return;

        // Query IOleInPlaceObject interface
        IOleInPlaceObject* pInPlaceObj = NULL;
        if (SUCCEEDED(pUnk->QueryInterface(IID_IOleInPlaceObject, (void**)&pInPlaceObj))) {
            // Construct position rectangle
            RECT rcPos = {x, y, x + cx, y + cy};
            RECT rcClip = rcPos;

            // Notify OLE container to update object position
            pInPlaceObj->SetObjectRects(&rcPos, &rcClip);

            // Get and move the physical window
            HWND hWndControl = NULL;
            if (SUCCEEDED(pInPlaceObj->GetWindow(&hWndControl)) && hWndControl != NULL) {
                ::SetWindowPos(hWndControl, NULL, x, y, cx, cy, SWP_NOZORDER | SWP_NOACTIVATE);
            }

            pInPlaceObj->Release();
        }
    }
};

class CIGRDisplayViewer
{
private:
    UnknownObject m_unkObject;

public:
    // Event handler member variable
    CIGRDisplayViewerEvents* m_pDispatch;

    CIGRDisplayViewer() : m_unkObject(), m_pDispatch(NULL) {}
    ~CIGRDisplayViewer() {}

    void SetUnknown(IUnknown* pUnk) {
        m_unkObject.SetUnknown(pUnk);
    }

    UnknownObject& GetUnknownObject() {
        return m_unkObject;
    }

    BOOL BindEvent(CIGRDisplayViewerEvents* pEvent) {
        // Save event handler to m_pDispatch if provided
        if (pEvent) {
            m_pDispatch = pEvent;
        }
        
        // Check if event handler is valid
        if (!m_pDispatch) {
            DEBUG_OUT("[Grid++] DisplayViewer BindEvent: m_pDispatch is NULL!\n");
            return FALSE;
        }

        if (!m_unkObject.IsValid()) {
            DEBUG_OUT("[Grid++] DisplayViewer BindEvent: m_unkObject invalid!\n");
            return FALSE;
        }

        CComPtr<IConnectionPointContainer> pCPC;
        HRESULT hr = m_unkObject.QueryInterface(&pCPC);
        if (FAILED(hr)) {
            DEBUG_OUTF("[Grid++] DisplayViewer BindEvent: QueryInterface failed hr=0x%08X\n", hr);
            return FALSE;
        }

        CGRDisplayViewerEventImpl* pEventImpl = new CGRDisplayViewerEventImpl;
        pEventImpl->m_pEvents = m_pDispatch;

        CComPtr<IConnectionPoint> pCP;
        hr = pCPC->FindConnectionPoint(IID__IGRDisplayViewerEvents, &pCP);
        if (FAILED(hr)) {
            DEBUG_OUTF("[Grid++] DisplayViewer BindEvent: FindConnectionPoint failed hr=0x%08X\n", hr);
            pEventImpl->Release();
            return FALSE;
        }

        DWORD dwCookie = 0;
        hr = pCP->Advise(pEventImpl, &dwCookie);
        if (FAILED(hr)) {
            DEBUG_OUTF("[Grid++] DisplayViewer BindEvent: Advise failed hr=0x%08X\n", hr);
            pEventImpl->Release();
            return FALSE;
        }

        DEBUG_OUT("[Grid++] DisplayViewer BindEvent: SUCCESS!\n");
        return TRUE;
    }

    // SetObjectPos - Adjust the position and size of the OCX window
    // Parameters:
    //   pUnk - IUnknown pointer of the OCX object
    //   x, y - Position coordinates
    //   cx, cy - Width and height
    void SetObjectPos(IUnknown* pUnk, int x, int y, int cx, int cy) {
        if (!pUnk) return;

        // Query IOleInPlaceObject interface
        IOleInPlaceObject* pInPlaceObj = NULL;
        if (SUCCEEDED(pUnk->QueryInterface(IID_IOleInPlaceObject, (void**)&pInPlaceObj))) {
            // Construct position rectangle
            RECT rcPos = {x, y, x + cx, y + cy};
            RECT rcClip = rcPos;

            // Notify OLE container to update object position
            pInPlaceObj->SetObjectRects(&rcPos, &rcClip);

            // Get and move the physical window
            HWND hWndControl = NULL;
            if (SUCCEEDED(pInPlaceObj->GetWindow(&hWndControl)) && hWndControl != NULL) {
                ::SetWindowPos(hWndControl, NULL, x, y, cx, cy, SWP_NOZORDER | SWP_NOACTIVATE);
            }

            pInPlaceObj->Release();
        }
    }
};

class CIGRPrintViewer
{
private:
    UnknownObject m_unkObject;

public:
    // Event handler member variable
    CIGRPrintViewerEvents* m_pDispatch;

    CIGRPrintViewer() : m_unkObject(), m_pDispatch(NULL) {}
    ~CIGRPrintViewer() {}

    void SetUnknown(IUnknown* pUnk) {
        m_unkObject.SetUnknown(pUnk);
    }

    UnknownObject& GetUnknownObject() {
        return m_unkObject;
    }

    BOOL BindEvent(CIGRPrintViewerEvents* pEvent) {
        // Save event handler to m_pDispatch if provided
        if (pEvent) {
            m_pDispatch = pEvent;
        }
        
        // Check if event handler is valid
        if (!m_pDispatch) {
            DEBUG_OUT("[Grid++] PrintViewer BindEvent: m_pDispatch is NULL!\n");
            return FALSE;
        }

        if (!m_unkObject.IsValid()) {
            DEBUG_OUT("[Grid++] PrintViewer BindEvent: m_unkObject invalid!\n");
            return FALSE;
        }

        CComPtr<IConnectionPointContainer> pCPC;
        HRESULT hr = m_unkObject.QueryInterface(&pCPC);
        if (FAILED(hr)) {
            DEBUG_OUTF("[Grid++] PrintViewer BindEvent: QueryInterface failed hr=0x%08X\n", hr);
            return FALSE;
        }

        CGRPrintViewerEventImpl* pEventImpl = new CGRPrintViewerEventImpl;
        pEventImpl->m_pEvents = m_pDispatch;

        CComPtr<IConnectionPoint> pCP;
        hr = pCPC->FindConnectionPoint(IID__IGRPrintViewerEvents, &pCP);
        if (FAILED(hr)) {
            DEBUG_OUTF("[Grid++] PrintViewer BindEvent: FindConnectionPoint failed hr=0x%08X\n", hr);
            pEventImpl->Release();
            return FALSE;
        }

        DWORD dwCookie = 0;
        hr = pCP->Advise(pEventImpl, &dwCookie);
        if (FAILED(hr)) {
            DEBUG_OUTF("[Grid++] PrintViewer BindEvent: Advise failed hr=0x%08X\n", hr);
            pEventImpl->Release();
            return FALSE;
        }

        DEBUG_OUT("[Grid++] PrintViewer BindEvent: SUCCESS!\n");
        return TRUE;
    }

    // SetObjectPos - Adjust the position and size of the OCX window
    // Parameters:
    //   pUnk - IUnknown pointer of the OCX object
    //   x, y - Position coordinates
    //   cx, cy - Width and height
    void SetObjectPos(IUnknown* pUnk, int x, int y, int cx, int cy) {
        if (!pUnk) return;

        // Query IOleInPlaceObject interface
        IOleInPlaceObject* pInPlaceObj = NULL;
        if (SUCCEEDED(pUnk->QueryInterface(IID_IOleInPlaceObject, (void**)&pInPlaceObj))) {
            // Construct position rectangle
            RECT rcPos = {x, y, x + cx, y + cy};
            RECT rcClip = rcPos;

            // Notify OLE container to update object position
            pInPlaceObj->SetObjectRects(&rcPos, &rcClip);

            // Get and move the physical window
            HWND hWndControl = NULL;
            if (SUCCEEDED(pInPlaceObj->GetWindow(&hWndControl)) && hWndControl != NULL) {
                ::SetWindowPos(hWndControl, NULL, x, y, cx, cy, SWP_NOZORDER | SWP_NOACTIVATE);
            }

            pInPlaceObj->Release();
        }
    }
};

// ========================================
// Designer Events Definition
// ========================================
class CIGRDesignerEvents
{
public:
    virtual void LayoutPanelContextMenu() {}
    virtual void InspectorContextMenu() {}
    virtual void ExplorerContextMenu() {}
    virtual void InspectorChange() {}
    virtual void DataChange() {}
    virtual void OpenReport() {}
    virtual void SaveReport() {}
    // BeforeDoAction: Event triggered before each task action is executed in the report
    // ActionEnum: Action enumeration (101:Undo, 102:Redo, 103:Cut, 104:Paste, 105:Copy, 106:Delete, 107:SelectAll, etc.)
    // Return value: 0 to allow action, non-zero to cancel action
    virtual int BeforeDoAction(int ActionEnum) { return 0; }
    // RequestData: Event triggered when the report requests data
    // pReport: Report object (IUnknown, can be QueryInterface to IGridppReport)
    virtual void RequestData(IUnknown* pReport) {}

    virtual ~CIGRDesignerEvents() {}
};

// ========================================
// Designer Events Implementation
// ========================================
class CGRDesignerEventImpl : public IDispatch
{
public:
    CGRDesignerEventImpl() : m_nRefCount(0), m_pEvents(NULL) {}
    ~CGRDesignerEventImpl() {}

    LONG m_nRefCount;
    CIGRDesignerEvents* m_pEvents;

    STDMETHODIMP QueryInterface(REFIID riid, void** ppvObject) {
        if (!ppvObject) return E_POINTER;
        *ppvObject = NULL;
        if (riid == IID_IUnknown || riid == IID_IDispatch || 
            riid == IID__IGRDesignerEvents) {
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

    STDMETHODIMP GetTypeInfo(UINT, LCID, ITypeInfo**) {
        return E_NOTIMPL;
    }

    STDMETHODIMP GetIDsOfNames(REFIID, LPOLESTR*, UINT, LCID, DISPID*) {
        return E_NOTIMPL;
    }

    STDMETHODIMP Invoke(DISPID dispIdMember, REFIID, LCID, WORD,
        DISPPARAMS* pDispParams, VARIANT*, EXCEPINFO*, UINT*) {
        
        DEBUG_OUTF("[Grid++] Designer Invoke: DISPID=%d, cArgs=%d\n", dispIdMember, pDispParams ? pDispParams->cArgs : 0);
        
        // Safety check: prevent null pointer access
        if (!m_pEvents) {
            DEBUG_OUT("[Grid++] Designer Invoke: m_pEvents is NULL, skipping event!\n");
            return S_OK;
        }
        
        if (!pDispParams) {
            DEBUG_OUT("[Grid++] Designer Invoke: pDispParams is NULL!\n");
            return E_FAIL;
        }

        switch (dispIdMember) {
        // 1: LayoutPanelContextMenu
        case 1:
            DEBUG_OUT("[Grid++] Designer: LayoutPanelContextMenu\n");
            m_pEvents->LayoutPanelContextMenu();
            break;
        // 2: InspectorContextMenu
        case 2:
            DEBUG_OUT("[Grid++] Designer: InspectorContextMenu\n");
            m_pEvents->InspectorContextMenu();
            break;
        // 3: ExplorerContextMenu
        case 3:
            DEBUG_OUT("[Grid++] Designer: ExplorerContextMenu\n");
            m_pEvents->ExplorerContextMenu();
            break;
        // 4: InspectorChange
        case 4:
            DEBUG_OUT("[Grid++] Designer: InspectorChange\n");
            m_pEvents->InspectorChange();
            break;
        // 5: DataChange
        case 5:
            DEBUG_OUT("[Grid++] Designer: DataChange\n");
            m_pEvents->DataChange();
            break;
        // 6: OpenReport
        case 6:
            DEBUG_OUT("[Grid++] Designer: OpenReport\n");
            m_pEvents->OpenReport();
            break;
        // 7: SaveReport
        case 7:
            DEBUG_OUT("[Grid++] Designer: SaveReport\n");
            m_pEvents->SaveReport();
            break;
        // 8: BeforeDoAction
        case 8: {
            DEBUG_OUT("[Grid++] Designer: BeforeDoAction\n");
            int actionEnum = 0;
            if (pDispParams && pDispParams->cArgs > 0 && pDispParams->rgvarg) {
                // Extract integer parameter from the event
                actionEnum = pDispParams->rgvarg[0].intVal;
                DEBUG_OUTF("[Grid++] Designer: BeforeDoAction actionEnum=%d\n", actionEnum);
            }
            int result = m_pEvents->BeforeDoAction(actionEnum);
            // Store result if needed (for DISPID 8)
            break;
        }
        // 9: RequestData
        case 9: {
            DEBUG_OUT("[Grid++] Designer: RequestData\n");
            IUnknown* pReport = NULL;
            if (pDispParams && pDispParams->cArgs > 0 && pDispParams->rgvarg) {
                // Extract IUnknown* parameter from the event
                if (pDispParams->rgvarg[0].vt == VT_UNKNOWN) {
                    pReport = pDispParams->rgvarg[0].punkVal;
                    DEBUG_OUTF("[Grid++] Designer: RequestData pReport=0x%p\n", pReport);
                }
            }
            m_pEvents->RequestData(pReport);
            break;
        }
        }  // End of switch statement
        return S_OK;
    }
};

// ========================================
// Designer Core Wrapper Class
// ========================================
class CIGRDesigner
{
private:
    UnknownObject m_unkObject;

public:
    // Event handler member variable
    CIGRDesignerEvents* m_pDispatch;

    CIGRDesigner() : m_unkObject(), m_pDispatch(NULL) {}
    ~CIGRDesigner() {}

    void SetUnknown(IUnknown* pUnk) {
        m_unkObject.SetUnknown(pUnk);
    }

    UnknownObject& GetUnknownObject() {
        return m_unkObject;
    }

    BOOL BindEvent(CIGRDesignerEvents* pEvent) {
        // Save event handler to m_pDispatch if provided
        if (pEvent) {
            m_pDispatch = pEvent;
        }
        
        // Check if event handler is valid
        if (!m_pDispatch) {
            DEBUG_OUT("[Grid++] Designer BindEvent: m_pDispatch is NULL!\n");
            return FALSE;
        }

        if (!m_unkObject.IsValid()) {
            DEBUG_OUT("[Grid++] Designer BindEvent: m_unkObject invalid!\n");
            return FALSE;
        }

        CComPtr<IConnectionPointContainer> pCPC;
        HRESULT hr = m_unkObject.QueryInterface(&pCPC);
        if (FAILED(hr)) {
            DEBUG_OUTF("[Grid++] Designer BindEvent: QueryInterface failed hr=0x%08X\n", hr);
            return FALSE;
        }

        CGRDesignerEventImpl* pEventImpl = new CGRDesignerEventImpl;
        
        // Safety check: prevent null pointer assignment
        if (m_pDispatch) {
            pEventImpl->m_pEvents = m_pDispatch;
        } else {
            DEBUG_OUT("[Grid++] Designer BindEvent: m_pDispatch is NULL, events will be ignored!\n");
        }

        CComPtr<IConnectionPoint> pCP;
        hr = pCPC->FindConnectionPoint(IID__IGRDesignerEvents, &pCP);
        if (FAILED(hr)) {
            DEBUG_OUTF("[Grid++] Designer BindEvent: FindConnectionPoint failed hr=0x%08X\n", hr);
            pEventImpl->Release();
            return FALSE;
        }

        DWORD dwCookie = 0;
        hr = pCP->Advise(pEventImpl, &dwCookie);
        if (FAILED(hr)) {
            DEBUG_OUTF("[Grid++] Designer BindEvent: Advise failed hr=0x%08X\n", hr);
            pEventImpl->Release();
            return FALSE;
        }

        DEBUG_OUT("[Grid++] Designer BindEvent: SUCCESS!\n");
        return TRUE;
    }

    // SetObjectPos - Adjust the position and size of the OCX window
    // Parameters:
    //   pUnk - IUnknown pointer of the OCX object
    //   x, y - Position coordinates
    //   cx, cy - Width and height
    void SetObjectPos(IUnknown* pUnk, int x, int y, int cx, int cy) {
        if (!pUnk) return;

        // Query IOleInPlaceObject interface
        IOleInPlaceObject* pInPlaceObj = NULL;
        if (SUCCEEDED(pUnk->QueryInterface(IID_IOleInPlaceObject, (void**)&pInPlaceObj))) {
            // Construct position rectangle
            RECT rcPos = {x, y, x + cx, y + cy};
            RECT rcClip = rcPos;

            // Notify OLE container to update object position
            pInPlaceObj->SetObjectRects(&rcPos, &rcClip);

            // Get and move the physical window
            HWND hWndControl = NULL;
            if (SUCCEEDED(pInPlaceObj->GetWindow(&hWndControl)) && hWndControl != NULL) {
                ::SetWindowPos(hWndControl, NULL, x, y, cx, cy, SWP_NOZORDER | SWP_NOACTIVATE);
            }

            pInPlaceObj->Release();
        }
    }
};

// ========================================
// OCX embedding functions
// ========================================
// EmbedGRReportOCX - Create and embed a Report OCX control
// Parameters:
//   hWndParent - Parent window handle
//   rcPos - Position and size of the control
// Return value:
//   IUnknown* - Pointer to the created OCX object, NULL if failed
inline IUnknown* EmbedGRReportOCX(HWND hWndParent, RECT rcPos) {
    if (!hWndParent) return NULL;

    OleInitialize(NULL);

    COleClientSiteMy* pClientSite = new COleClientSiteMy(hWndParent, rcPos.left, rcPos.top, 
        rcPos.right - rcPos.left, rcPos.bottom - rcPos.top);
    if (!pClientSite) return NULL;

    CComPtr<IUnknown> pUnkOCX;
    // Use manually defined Report CLSID to create instance
    HRESULT hr = CoCreateInstance(CLSID_GridppReport, NULL, CLSCTX_INPROC_SERVER, 
        IID_IUnknown, (void**)&pUnkOCX);
    if (FAILED(hr)) {
        pClientSite->Release();
        return NULL;
    }

    CComPtr<IOleObject> pOleObj;
    hr = pUnkOCX->QueryInterface(IID_IOleObject, (void**)&pOleObj);
    if (FAILED(hr)) {
        pClientSite->Release();
        return NULL;
    }

    hr = pOleObj->SetClientSite(pClientSite);
    if (FAILED(hr)) {
        pClientSite->Release();
        return NULL;
    }

    hr = pOleObj->DoVerb(OLEIVERB_INPLACEACTIVATE, NULL, pClientSite, 0, hWndParent, &rcPos);
    if (FAILED(hr)) {
        pClientSite->Release();
        return NULL;
    }

    pClientSite->Release();
    return pUnkOCX.Detach();
}

// ========================================
// Display Viewer OCX embedding function
// ========================================
// EmbedGRDisplayViewerOCX - Create and embed a Display Viewer OCX control
// Parameters:
//   hWndParent - Parent window handle
//   rcPos - Position and size of the control
// Return value:
//   IUnknown* - Pointer to the created OCX object, NULL if failed
inline IUnknown* EmbedGRDisplayViewerOCX(HWND hWndParent, RECT rcPos) {
    if (!hWndParent) return NULL;

    OleInitialize(NULL);

    COleClientSiteMy* pClientSite = new COleClientSiteMy(hWndParent, rcPos.left, rcPos.top, 
        rcPos.right - rcPos.left, rcPos.bottom - rcPos.top);
    if (!pClientSite) return NULL;

    CComPtr<IUnknown> pUnkOCX;
    // Use manually defined Display Viewer CLSID to create instance
    HRESULT hr = CoCreateInstance(CLSID_GRDisplayViewer, NULL, CLSCTX_INPROC_SERVER, 
        IID_IUnknown, (void**)&pUnkOCX);
    if (FAILED(hr)) {
        pClientSite->Release();
        return NULL;
    }

    CComPtr<IOleObject> pOleObj;
    hr = pUnkOCX->QueryInterface(IID_IOleObject, (void**)&pOleObj);
    if (FAILED(hr)) {
        pClientSite->Release();
        return NULL;
    }

    hr = pOleObj->SetClientSite(pClientSite);
    if (FAILED(hr)) {
        pClientSite->Release();
        return NULL;
    }

    hr = pOleObj->DoVerb(OLEIVERB_INPLACEACTIVATE, NULL, pClientSite, 0, hWndParent, &rcPos);
    if (FAILED(hr)) {
        pClientSite->Release();
        return NULL;
    }

    pClientSite->Release();
    return pUnkOCX.Detach();
}

// ========================================
// Print Viewer OCX embedding function
// ========================================
// EmbedGRPrintViewerOCX - Create and embed a Print Viewer OCX control
// Parameters:
//   hWndParent - Parent window handle
//   rcPos - Position and size of the control
// Return value:
//   IUnknown* - Pointer to the created OCX object, NULL if failed
inline IUnknown* EmbedGRPrintViewerOCX(HWND hWndParent, RECT rcPos) {
    if (!hWndParent) return NULL;

    OleInitialize(NULL);

    COleClientSiteMy* pClientSite = new COleClientSiteMy(hWndParent, rcPos.left, rcPos.top, 
        rcPos.right - rcPos.left, rcPos.bottom - rcPos.top);
    if (!pClientSite) return NULL;

    CComPtr<IUnknown> pUnkOCX;
    // Use manually defined Print Viewer CLSID to create instance
    HRESULT hr = CoCreateInstance(CLSID_GRPrintViewer, NULL, CLSCTX_INPROC_SERVER, 
        IID_IUnknown, (void**)&pUnkOCX);
    if (FAILED(hr)) {
        pClientSite->Release();
        return NULL;
    }

    CComPtr<IOleObject> pOleObj;
    hr = pUnkOCX->QueryInterface(IID_IOleObject, (void**)&pOleObj);
    if (FAILED(hr)) {
        pClientSite->Release();
        return NULL;
    }

    hr = pOleObj->SetClientSite(pClientSite);
    if (FAILED(hr)) {
        pClientSite->Release();
        return NULL;
    }

    hr = pOleObj->DoVerb(OLEIVERB_INPLACEACTIVATE, NULL, pClientSite, 0, hWndParent, &rcPos);
    if (FAILED(hr)) {
        pClientSite->Release();
        return NULL;
    }

    pClientSite->Release();
    return pUnkOCX.Detach();
}

// ========================================
// Designer OCX embedding function
// ========================================
// EmbedGRDesignerOCX - Create and embed a Report Designer OCX control
// Parameters:
//   hWndParent - Parent window handle
//   rcPos - Position and size of the control
// Return value:
//   IUnknown* - Pointer to the created OCX object, NULL if failed
inline IUnknown* EmbedGRDesignerOCX(HWND hWndParent, RECT rcPos) {
    if (!hWndParent) return NULL;

    OleInitialize(NULL);

    COleClientSiteMy* pClientSite = new COleClientSiteMy(hWndParent, rcPos.left, rcPos.top, 
        rcPos.right - rcPos.left, rcPos.bottom - rcPos.top);
    if (!pClientSite) return NULL;

    CComPtr<IUnknown> pUnkOCX;
    // Use manually defined Designer CLSID to create instance
    HRESULT hr = CoCreateInstance(CLSID_GRDesigner, NULL, CLSCTX_INPROC_SERVER, 
        IID_IUnknown, (void**)&pUnkOCX);
    if (FAILED(hr)) {
        pClientSite->Release();
        return NULL;
    }

    CComPtr<IOleObject> pOleObj;
    hr = pUnkOCX->QueryInterface(IID_IOleObject, (void**)&pOleObj);
    if (FAILED(hr)) {
        pClientSite->Release();
        return NULL;
    }

    hr = pOleObj->SetClientSite(pClientSite);
    if (FAILED(hr)) {
        pClientSite->Release();
        return NULL;
    }

    hr = pOleObj->DoVerb(OLEIVERB_INPLACEACTIVATE, NULL, pClientSite, 0, hWndParent, &rcPos);
    if (FAILED(hr)) {
        pClientSite->Release();
        return NULL;
    }

    pClientSite->Release();
    return pUnkOCX.Detach();
}