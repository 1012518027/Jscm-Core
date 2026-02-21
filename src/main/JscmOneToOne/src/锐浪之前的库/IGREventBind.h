////////////////////////////////////////////////////////////////////////////////
//
//  锐浪报表 - 事件绑定头文件
//  
//  说明: 定义三个类及其 BindEvent 方法实现
//  注意: 请确保 stdafx.h 中包含必要的头文件
//
////////////////////////////////////////////////////////////////////////////////

#pragma once

// 包含必要的头文件 (请确保在 stdafx.h 中按此顺序包含)
#include <atlbase.h>
#include <atlcom.h>
#include "gregn.tlh"
#include "OcxEvent.h"
#include "IGR_events.h"

////////////////////////////////////////////////////////////////////////////////
// 类定义
////////////////////////////////////////////////////////////////////////////////

class CIGridppReport
{
public:
    IDispatch* m_ptr;
    BOOL BindEvent(CIGridppReportEvents* pEvent);
};

class CIGRDisplayViewer
{
public:
    IDispatch* m_ptr;
    BOOL BindEvent(CIGRDisplayViewerEvents* pEvent);
};

class CIGRPrintViewer
{
public:
    IDispatch* m_ptr;
    BOOL BindEvent(CIGRPrintViewerEvents* pEvent);
};

////////////////////////////////////////////////////////////////////////////////
// 方法实现
////////////////////////////////////////////////////////////////////////////////

BOOL CIGridppReport::BindEvent(CIGridppReportEvents* pEvent)
{
    CComPtr<IConnectionPointContainer> pCPC;
    CComPtr<IConnectionPoint> pCP;
    HRESULT hr = m_ptr->QueryInterface(__uuidof(IConnectionPointContainer), (void**)&pCPC);
    ATLASSERT(SUCCEEDED(hr));

    CGridppReportEventImpl* event_ = new CGridppReportEventImpl;
    // 修复1：强制类型转换（reinterpret_cast）解决类型不匹配问题
    event_->m_event = reinterpret_cast<IDispatch*>(pEvent);
    // 修复2：使用正确的事件接口 GUID（_IGridppReportEvents 需确保在 gregn.tlh 中定义）
    hr = pCPC->FindConnectionPoint(__uuidof(_IGridppReportEvents), &pCP);
    ATLASSERT(SUCCEEDED(hr));

    DWORD  conn = 0;
    hr = pCP->Advise(event_, &conn);
    ATLASSERT(SUCCEEDED(hr));

    return TRUE;
}

BOOL CIGRDisplayViewer::BindEvent(CIGRDisplayViewerEvents* pEvent)
{
    CComPtr<IConnectionPointContainer> pCPC;
    CComPtr<IConnectionPoint> pCP;
    HRESULT hr = m_ptr->QueryInterface(__uuidof(IConnectionPointContainer), (void**)&pCPC);
    ATLASSERT(SUCCEEDED(hr));

    CGRDisplayViewerEventImpl* event_ = new CGRDisplayViewerEventImpl;
    // 修复1：强制类型转换（reinterpret_cast）解决类型不匹配问题
    event_->m_event = reinterpret_cast<IDispatch*>(pEvent);
    // 修复2：使用正确的事件接口 GUID（_IGRDisplayViewerEvents 需确保在 gregn.tlh 中定义）
    hr = pCPC->FindConnectionPoint(__uuidof(_IGRDisplayViewerEvents), &pCP);
    ATLASSERT(SUCCEEDED(hr));

    DWORD  conn = 0;
    hr = pCP->Advise(event_, &conn);
    ATLASSERT(SUCCEEDED(hr));

    return TRUE;
}

BOOL CIGRPrintViewer::BindEvent(CIGRPrintViewerEvents* pEvent)
{
    CComPtr<IConnectionPointContainer> pCPC;
    CComPtr<IConnectionPoint> pCP;
    HRESULT hr = m_ptr->QueryInterface(__uuidof(IConnectionPointContainer), (void**)&pCPC);
    ATLASSERT(SUCCEEDED(hr));

    CGRPrintViewerEventImpl* event_ = new CGRPrintViewerEventImpl;
    // 修复1：强制类型转换（reinterpret_cast）解决类型不匹配问题
    event_->m_event = reinterpret_cast<IDispatch*>(pEvent);
    // 修复2：使用正确的事件接口 GUID（_IGRPrintViewerEvents 需确保在 gregn.tlh 中定义）
    hr = pCPC->FindConnectionPoint(__uuidof(_IGRPrintViewerEvents), &pCP);
    ATLASSERT(SUCCEEDED(hr));

    DWORD  conn = 0;
    hr = pCP->Advise(event_, &conn);
    ATLASSERT(SUCCEEDED(hr));
    return TRUE;
}