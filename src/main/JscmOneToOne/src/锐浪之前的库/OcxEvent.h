#pragma once

// 包含 Grid++Report 类型库定义
#include "gregn.tlh"

class CGridppReportEventImpl : public IDispatch
{
public:
	CGridppReportEventImpl() {
		m_event = NULL; m_nRefCount = 0;
	}
	~CGridppReportEventImpl() {
	}
	int   m_nRefCount;
	IDispatch*   m_event;
	virtual HRESULT STDMETHODCALLTYPE QueryInterface(/* [in] */ REFIID riid,/* [iid_is][out] */ _COM_Outptr_ void __RPC_FAR* __RPC_FAR* ppvObject)
	{
		*ppvObject = NULL;
		if(riid == IID_IUnknown)
			*ppvObject = this;
		else if (riid == IID_IDispatch)
			*ppvObject = this;

		if (*ppvObject)
		{
			AddRef();
			return S_OK;
		}
		return E_NOINTERFACE;
	}
	virtual ULONG STDMETHODCALLTYPE AddRef(void) {
		m_nRefCount++;
		return m_nRefCount;
	}
	virtual ULONG STDMETHODCALLTYPE Release(void) {
		m_nRefCount--;
		if (m_nRefCount <= 0)
		{
			delete this;
			return 0;
		}
		return m_nRefCount;
	}
	virtual HRESULT STDMETHODCALLTYPE GetTypeInfoCount(
		/* [out] */ __RPC__out UINT* pctinfo) {
		return E_NOTIMPL;
	}
	virtual HRESULT STDMETHODCALLTYPE GetTypeInfo(
		/* [in] */ UINT iTInfo,
		/* [in] */ LCID lcid,
		/* [out] */ __RPC__deref_out_opt ITypeInfo** ppTInfo) {
		return E_NOTIMPL;
	}
	virtual HRESULT STDMETHODCALLTYPE GetIDsOfNames(
		/* [in] */ __RPC__in REFIID riid,
		/* [size_is][in] */ __RPC__in_ecount_full(cNames) LPOLESTR* rgszNames,
		/* [range][in] */ __RPC__in_range(0, 16384) UINT cNames,
		/* [in] */ LCID lcid,
		/* [size_is][out] */ __RPC__out_ecount_full(cNames) DISPID* rgDispId) {
		return E_NOTIMPL;
	}
	virtual /* [local] */ HRESULT STDMETHODCALLTYPE Invoke(
		/* [in] */ DISPID dispIdMember,
		/* [in] */ REFIID riid,
		/* [in] */ LCID lcid,
		/* [in] */ WORD wFlags,
		/* [out][in] */ DISPPARAMS* pDispParams,
		/* [out] */ VARIANT* pVarResult,
		/* [out] */ EXCEPINFO* pExcepInfo,
		/* [out] */ UINT* puArgErr);
};

////////////////////////////////////////////////////////////
class CGRDisplayViewerEventImpl : public IDispatch
{
public:
	CGRDisplayViewerEventImpl() {
		m_event = NULL; m_nRefCount = 0;
	}
	~CGRDisplayViewerEventImpl() {
	}
	int   m_nRefCount;
	IDispatch* m_event;
	virtual HRESULT STDMETHODCALLTYPE QueryInterface(/* [in] */ REFIID riid,/* [iid_is][out] */ _COM_Outptr_ void __RPC_FAR* __RPC_FAR* ppvObject)
	{
		*ppvObject = NULL;
		if (riid == IID_IUnknown)
			*ppvObject = this;
		else if (riid == IID_IDispatch)
			*ppvObject = this;
		if (*ppvObject)
		{
			AddRef();
			return S_OK;
		}
		return E_NOINTERFACE;
	}
	virtual ULONG STDMETHODCALLTYPE AddRef(void) {
		m_nRefCount++;
		return m_nRefCount;
	}
	virtual ULONG STDMETHODCALLTYPE Release(void) {
		m_nRefCount--;
		if (m_nRefCount <= 0)
		{
			delete this;
			return 0;
		}
		return m_nRefCount;
	}
	virtual HRESULT STDMETHODCALLTYPE GetTypeInfoCount(
		/* [out] */ __RPC__out UINT* pctinfo) {
		return E_NOTIMPL;
	}
	virtual HRESULT STDMETHODCALLTYPE GetTypeInfo(
		/* [in] */ UINT iTInfo,
		/* [in] */ LCID lcid,
		/* [out] */ __RPC__deref_out_opt ITypeInfo** ppTInfo) {
		return E_NOTIMPL;
	}
	virtual HRESULT STDMETHODCALLTYPE GetIDsOfNames(
		/* [in] */ __RPC__in REFIID riid,
		/* [size_is][in] */ __RPC__in_ecount_full(cNames) LPOLESTR* rgszNames,
		/* [range][in] */ __RPC__in_range(0, 16384) UINT cNames,
		/* [in] */ LCID lcid,
		/* [size_is][out] */ __RPC__out_ecount_full(cNames) DISPID* rgDispId) {
		return E_NOTIMPL;
	}
	virtual /* [local] */ HRESULT STDMETHODCALLTYPE Invoke(
		/* [in] */ DISPID dispIdMember,
		/* [in] */ REFIID riid,
		/* [in] */ LCID lcid,
		/* [in] */ WORD wFlags,
		/* [out][in] */ DISPPARAMS* pDispParams,
		/* [out] */ VARIANT* pVarResult,
		/* [out] */ EXCEPINFO* pExcepInfo,
		/* [out] */ UINT* puArgErr);
};

////////////////////////////////////////////////////////////
class CGRPrintViewerEventImpl : public IDispatch
{
public:
	CGRPrintViewerEventImpl() {
		m_event = NULL; m_nRefCount = 0;
	}
	~CGRPrintViewerEventImpl() {
	}
	int   m_nRefCount;
	IDispatch* m_event;
	virtual HRESULT STDMETHODCALLTYPE QueryInterface(/* [in] */ REFIID riid,/* [iid_is][out] */ _COM_Outptr_ void __RPC_FAR* __RPC_FAR* ppvObject)
	{
		*ppvObject = NULL;
		if (riid == IID_IUnknown)
			*ppvObject = this;
		else if (riid == IID_IDispatch)
			*ppvObject = this;

		if (*ppvObject)
		{
			AddRef();
			return S_OK;
		}
		return E_NOINTERFACE;
	}
	virtual ULONG STDMETHODCALLTYPE AddRef(void) {
		m_nRefCount++;
		return m_nRefCount;
	}
	virtual ULONG STDMETHODCALLTYPE Release(void) {
		m_nRefCount--;
		if (m_nRefCount <= 0)
		{
			delete this;
			return 0;
		}
		return m_nRefCount;
	}
	virtual HRESULT STDMETHODCALLTYPE GetTypeInfoCount(
		/* [out] */ __RPC__out UINT* pctinfo) {
		return E_NOTIMPL;
	}
	virtual HRESULT STDMETHODCALLTYPE GetTypeInfo(
		/* [in] */ UINT iTInfo,
		/* [in] */ LCID lcid,
		/* [out] */ __RPC__deref_out_opt ITypeInfo** ppTInfo) {
		return E_NOTIMPL;
	}
	virtual HRESULT STDMETHODCALLTYPE GetIDsOfNames(
		/* [in] */ __RPC__in REFIID riid,
		/* [size_is][in] */ __RPC__in_ecount_full(cNames) LPOLESTR* rgszNames,
		/* [range][in] */ __RPC__in_range(0, 16384) UINT cNames,
		/* [in] */ LCID lcid,
		/* [size_is][out] */ __RPC__out_ecount_full(cNames) DISPID* rgDispId) {
		return E_NOTIMPL;
	}
	virtual /* [local] */ HRESULT STDMETHODCALLTYPE Invoke(
		/* [in] */ DISPID dispIdMember,
		/* [in] */ REFIID riid,
		/* [in] */ LCID lcid,
		/* [in] */ WORD wFlags,
		/* [out][in] */ DISPPARAMS* pDispParams,
		/* [out] */ VARIANT* pVarResult,
		/* [out] */ EXCEPINFO* pExcepInfo,
		/* [out] */ UINT* puArgErr);
};
