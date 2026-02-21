#pragma once
#include <windows.h>
#include <ole2.h>
#include <olectl.h>

// 添加 ATL 头文件以支持 COM 接口
#include <atlbase.h>
#include <atlcom.h>

// 1. Class Definition (Updated to support 5-parameter constructor)
class COleClientSiteMy : public IOleClientSite, public IOleInPlaceSite, public IOleInPlaceFrame {
public:
    LONG m_nRefCount;
    HWND m_hWndParent;
    RECT m_rect;

    // Constructor 1: Standard 1-parameter
    COleClientSiteMy(HWND hWnd) : m_nRefCount(1), m_hWndParent(hWnd) {
        m_rect.left = 0; m_rect.top = 0; m_rect.right = 0; m_rect.bottom = 0;
    }

    // Constructor 2: 5-parameters (Matches your calling code)
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

    // IUnknown
    STDMETHODIMP QueryInterface(REFIID riid, void** ppv) {
        if (riid == IID_IUnknown || riid == IID_IOleClientSite) *ppv = (IOleClientSite*)this;
        else if (riid == IID_IOleInPlaceSite) *ppv = (IOleInPlaceSite*)this;
        else if (riid == IID_IOleInPlaceFrame || riid == IID_IOleInPlaceUIWindow) *ppv = (IOleInPlaceFrame*)this;
        else { *ppv = NULL; return E_NOINTERFACE; }
        AddRef(); return S_OK;
    }
    STDMETHODIMP_(ULONG) AddRef() { return InterlockedIncrement(&m_nRefCount); }
    STDMETHODIMP_(ULONG) Release() { LONG res = InterlockedDecrement(&m_nRefCount); if (res == 0) delete this; return res; }

    // IOleClientSite
    STDMETHODIMP SaveObject() { return E_NOTIMPL; }
    STDMETHODIMP GetMoniker(DWORD, DWORD, IMoniker**) { return E_NOTIMPL; }
    STDMETHODIMP GetContainer(IOleContainer**) { return E_NOTIMPL; }
    STDMETHODIMP ShowObject() { return S_OK; }
    STDMETHODIMP OnShowWindow(BOOL) { return S_OK; }
    STDMETHODIMP RequestNewObjectLayout() { return E_NOTIMPL; }

    // IOleInPlaceSite
    STDMETHODIMP GetWindow(HWND* phwnd) { *phwnd = m_hWndParent; return S_OK; }
    STDMETHODIMP ContextSensitiveHelp(BOOL) { return E_NOTIMPL; }
    STDMETHODIMP CanInPlaceActivate() { return S_OK; }
    STDMETHODIMP OnInPlaceActivate() { return S_OK; }
    STDMETHODIMP OnUIActivate() { return S_OK; }
    STDMETHODIMP GetWindowContext(IOleInPlaceFrame** ppFrame, IOleInPlaceUIWindow** ppDoc, LPRECT lprcPosRect, LPRECT lprcClipRect, LPOLEINPLACEFRAMEINFO lpFrameInfo) {
        *ppFrame = (IOleInPlaceFrame*)this; (*ppFrame)->AddRef(); *ppDoc = NULL;
        *lprcPosRect = m_rect; *lprcClipRect = m_rect;
        lpFrameInfo->cb = sizeof(OLEINPLACEFRAMEINFO); lpFrameInfo->fMDIApp = FALSE;
        lpFrameInfo->hwndFrame = m_hWndParent; lpFrameInfo->haccel = NULL; lpFrameInfo->cAccelEntries = 0;
        return S_OK;
    }
    STDMETHODIMP Scroll(SIZE) { return E_NOTIMPL; }
    STDMETHODIMP OnUIDeactivate(BOOL) { return S_OK; }
    STDMETHODIMP OnInPlaceDeactivate() { return S_OK; }
    STDMETHODIMP DiscardUndoState() { return S_OK; }
    STDMETHODIMP DeactivateAndUndo() { return S_OK; }
    STDMETHODIMP OnPosRectChange(LPCRECT) { return S_OK; }

    // IOleInPlaceFrame
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

