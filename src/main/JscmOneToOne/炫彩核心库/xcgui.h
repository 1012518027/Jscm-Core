#include "xcgui_event.h"
/**************************************************************************\
*
* Copyright (c)  �Ųʽ����. All Rights Reserved.
*
* �Ųʽ���� - �ٷ���վ : http://www.xcgui.com
*
\**************************************************************************/
#pragma once


////////////�������///////////////////
#ifndef  __in
#define  __in
#endif

#ifndef  __out
#define  __out
#endif

#ifndef  __in_out
#define  __in_out
#endif


#define  in_buffer_  //��ʶΪ�ڴ�ָ��, �Ǳ���ָ��
#define  out_buffer_

#define  XC_API extern "C"

#define  XC_NAME1   L"name1"
#define  XC_NAME2   L"name2"
#define  XC_NAME3   L"name3"
#define  XC_NAME4   L"name4"
#define  XC_NAME5   L"name5"
#define  XC_NAME6   L"name6"

#ifndef  GetAValue
#define  GetAValue(rgba)         (LOBYTE((rgba)>>24))
#endif

#ifndef  RGBA
#define  RGBA(r, g, b, a)        ((BYTE)a<<24 | (BYTE)r | (BYTE)g<<8 | (BYTE)b<<16)
#endif

//@����{
#define  COLORREF_MAKE2(rgb,a)        ((BYTE)a<<24 | GetRValue(rgb) | GetGValue(rgb)<<8 | GetBValue(rgb)<<16)
#define  COLORREF_SET_RGB(color,rgb)  ((color & 0xFF000000) | (rgb & 0xFFFFFF))
#define  COLORREF_SET_A(color,a)      ((color & 0xFFFFFF) | ((BYTE)a<<24))
#define  COLORREF_GET_A(color)        ((BYTE)(color>>24))
//@����}

#ifdef _WIN64
typedef  __int64  vint;
#else
typedef  int      vint;
#endif

//��������ظ�����POINTF,��ע�͵����涨��POINTF
#ifndef __IOleControlSite_INTERFACE_DEFINED__
struct POINTF
{
	float x;
	float y;
};
#endif

//@����ö��ǰ׺

/*@����
#define SW_HIDE             0   //@���� ������ʾ��ʶ_����
#define SW_SHOWNORMAL       1   //@���� ������ʾ��ʶ_��ʾ�����ԭ
#define SW_SHOWMINIMIZED    2	//@���� ������ʾ��ʶ_��ʾ����С��
#define SW_SHOWMAXIMIZED    3	//@���� ������ʾ��ʶ_��ʾ�����
#define SW_SHOWNOACTIVATE   4	//@���� ������ʾ��ʶ_��ʾ������
#define SW_SHOW             5	//@���� ������ʾ��ʶ_��ʾ
#define SW_MINIMIZE         6	//@���� ������ʾ��ʶ_��С��
#define SW_SHOWMINNOACTIVE  7	//@���� ������ʾ��ʶ_��С��������
#define SW_SHOWNA           8	//@���� ������ʾ��ʶ_��ʾ������2
#define SW_RESTORE          9	//@���� ������ʾ��ʶ_��ԭ
#define SW_SHOWDEFAULT      10	//@���� ������ʾ��ʶ_������ʼֵ
#define SW_FORCEMINIMIZE    11	//@���� ������ʾ��ʶ_ǿ����С��
*/
/////////////////////////////////////////////////////////////////////
/////////////////////������Ͷ���//////////////////////////////////////
/////////////////////////////////////////////////////////////////////
#define DECLARE_HANDLEX(name) struct name##__ { int unused; }; typedef struct name##__ *name

/// groupHandle   �������
/// @{

//@����  �Ųʾ��
typedef void* HXCGUI;       ///<��Դ���
//@����  ���ھ��
DECLARE_HANDLEX(HWINDOW);     ///<������Դ���
//@����  Ԫ�ؾ��
DECLARE_HANDLEX(HELE);        ///<Ԫ����Դ���
//@����  �˵����
DECLARE_HANDLEX(HMENUX);      ///<�˵���Դ���
//@����  ��ͼ���
DECLARE_HANDLEX(HDRAW);       ///<ͼ�λ�����Դ���
//@����  ͼƬ���
DECLARE_HANDLEX(HIMAGE);      ///<ͼƬ��Դ���
//@����  ������
DECLARE_HANDLEX(HFONTX);      ///<�Ų�������
//@����  �������
DECLARE_HANDLEX(HBKM);        ///<�������ݹ��������
//@����  ģ����
DECLARE_HANDLEX(HTEMP);       ///<ģ����
//@����  SVG���
DECLARE_HANDLEX(HSVG);        ///<SVG���
///@}

#ifndef _INC_SHELLAPI
DECLARE_HANDLEX(HDROP);
#endif

/////////////////////////////////////////////////////////////////////
/////////////////////�Ųʶ������ͼ���ʽ/////////////////////////////////
/////////////////////////////////////////////////////////////////////

//�������;�������
//������ʽ�������
/// group_type_style_  �������ͼ���ʽ
/// @{

/// group_ObjectType ����������(XC_OBJECT_TYPE)
/// @{
enum XC_OBJECT_TYPE  //@����  �Ųʶ�������
{
	//@����  ����
	XC_ERROR = -1,   ///<��������
	XC_NOTHING = 0,    ///<ɶҲ����
	//@����  ����
	XC_WINDOW = 1,    ///<����
	//@����  ģ̬����
	XC_MODALWINDOW = 2,    ///<ģ̬����
	//@����  ��ܴ���
	XC_FRAMEWND = 3,    ///<��ܴ���
	//@����  ��������
	XC_FLOATWND = 4,    ///<��������
	//@����  ��Ͽ���������
	XC_COMBOBOXWINDOW = 11,   ///<��Ͽ򵯳������б��� comboBoxWindow_
	//@����  �˵�������
	XC_POPUPMENUWINDOW = 12,  ///<�˵������� popupMenuWindow_
	//@����  �˵��Ӵ���
	XC_POPUPMENUCHILDWINDOW = 13,   ///<�˵��Ӵ��� popupMenuChildWindow_
	//@����  ���Ӷ���
	XC_OBJECT_UI = 19,   ///<���Ӷ���
	//@����  �������
	XC_WIDGET_UI = 20,   ///<�������
	//@����  ����Ԫ��
	XC_ELE = 21,   ///<����Ԫ��
	//@����  ����Ԫ��
	XC_ELE_LAYOUT = 53,   ///<����Ԫ��
	//@����  ���ֿ��
	XC_LAYOUT_FRAME = 54,   ///<���ֿ��,��ʽ����
	//@����  ��ť
	XC_BUTTON = 22,   ///<��ť
	//@����  �༭��
	XC_EDIT = 45,   ///<�༭��
	//@����  ����༭��
	XC_EDITOR = 46,   ///<����༭��
	XC_RICHEDIT = 23,   ///<���ı��༭��(�ѷ���), ��ʹ��XC_EDIT
	//@����  ������Ͽ�
	XC_COMBOBOX = 24,   ///<������Ͽ�
	//@����  ������
	XC_SCROLLBAR = 25,   ///<������
	//@����  ������ͼ
	XC_SCROLLVIEW = 26,   ///<������ͼ
	//@����  �б�
	XC_LIST = 27,   ///<�б�
	//@����  �б��
	XC_LISTBOX = 28,   ///<�б��
	//@����  �б���ͼ
	XC_LISTVIEW = 29,   ///<�б���ͼ,��ͼ��
	//@����  �б���
	XC_TREE = 30,   ///<�б���
	//@����  �˵���
	XC_MENUBAR = 31,   ///<�˵���
	//@����  ������
	XC_SLIDERBAR = 32,   ///<������
	//@����  ������
	XC_PROGRESSBAR = 33,   ///<������
	//@����  ������
	XC_TOOLBAR = 34,   ///<������
	//@����  ������Ƭ
	XC_MONTHCAL = 35,   ///<������Ƭ
	//@����  ����ʱ��
	XC_DATETIME = 36,   ///<����ʱ��
	//@����  ��������
	XC_PROPERTYGRID = 37,   ///<��������
	//@����  ��ɫѡ���
	XC_EDIT_COLOR = 38,   ///<��ɫѡ���
	//@����  ���ñ༭��
	XC_EDIT_SET = 39,   ///<���ñ༭��
	//@����  TAB��
	XC_TABBAR = 40,   ///<tab��
	//@����  �ı����Ӱ�ť
	XC_TEXTLINK = 41,   ///<�ı����Ӱ�ť
	//@����  ����
	XC_PANE = 42,   ///<����
	//@����  ����ָ���
	XC_PANE_SPLIT = 43,   ///<�����϶��ָ���
	//@����  �˵����ϰ�ť
	XC_MENUBAR_BUTTON = 44,   ///<�˵����ϵİ�ť
//	XC_TOOLBAR_BUTTON       =45,   ///<�������ϰ�ť
//	XC_PROPERTYPAGE_LABEL   =46,   ///<����ҳ��ǩ��ť
//	XC_PIER                 =47,   ///<����ͣ����ͷ
//	XC_BUTTON_MENU          =48,   ///<�˵���ť
//	XC_VIRTUAL_ELE          =49,   ///<����Ԫ��
	//@����  �ļ�ѡ��༭��
	XC_EDIT_FILE = 50,   ///<EditFile �ļ�ѡ��༭��
	//@����  �ļ���ѡ��༭��
	XC_EDIT_FOLDER = 51,   ///<EditFolder  �ļ���ѡ��༭��
	//@����  �б�ͷԪ��
	XC_LIST_HEADER = 52,   ///<�б�ͷԪ��
	//@����  ��״����
	XC_SHAPE = 61,    ///<��״����
	//@����  ��״�����ı�
	XC_SHAPE_TEXT = 62,    ///<��״����-�ı�
	//@����  ��״����ͼƬ
	XC_SHAPE_PICTURE = 63,    ///<��״����-ͼƬ
	//@����  ��״�������
	XC_SHAPE_RECT = 64,    ///<��״����-����
	//@����  ��״����Բ��
	XC_SHAPE_ELLIPSE = 65,    ///<��״����-Բ��
	//@����  ��״����ֱ��
	XC_SHAPE_LINE = 66,    ///<��״����-ֱ��
	//@����  ��״�������
	XC_SHAPE_GROUPBOX = 67,    ///<��״����-���
	//@����  ��״����GIF
	XC_SHAPE_GIF = 68,    ///<��״����-GIF
	//@����  ��״������
	XC_SHAPE_TABLE = 69,    ///<��״����-���

	//@����  �˵�
	XC_MENU = 81,   ///<�˵�
	//@����  ͼƬԴ
	XC_IMAGE_TEXTURE = 82,   ///<ͼƬ����,ͼƬԴ,ͼƬ�ز�
	//@����  ��ͼ
	XC_HDRAW = 83,   ///<��ͼ����
	//@����  ����
	XC_FONT = 84,   ///<�Ų�����
//	XC_FLASH             =85,   ///<flash
//	XC_WEB               =87,   ///<...
	//@����  ͼƬ֡
	XC_IMAGE_FRAME = 88,   ///<ͼƬ֡,ָ��ͼƬ����Ⱦ����
	//@����  SVG
	XC_SVG = 89,   ///<SVGʸ��ͼ��

	XC_LAYOUT_OBJECT = 101, ///<���ֶ���LayoutObject, �ѷ���
	//@����  ����������
	XC_ADAPTER = 102, ///<����������Adapter
	//@����  ������������
	XC_ADAPTER_TABLE = 103, ///<����������AdapterTable
	//@����  ������������
	XC_ADAPTER_TREE = 104, ///<����������AdapterTree
	//@����  �����������б���ͼ
	XC_ADAPTER_LISTVIEW = 105, ///<����������AdapterListView
	//@����  ����������MAP
	XC_ADAPTER_MAP = 106, ///<����������AdapterMap
	//@����  ����������
	XC_BKINFOM = 116, ///<����������

	//��ʵ�����,ֻ�������жϲ���
	XC_LAYOUT_LISTVIEW = 111,  ///<�ڲ�ʹ��
	XC_LAYOUT_LIST = 112,  ///<�ڲ�ʹ��
	XC_LAYOUT_OBJECT_GROUP = 113,  ///<�ڲ�ʹ��
	XC_LAYOUT_OBJECT_ITEM = 114,  ///<�ڲ�ʹ��
	XC_LAYOUT_PANEL = 115,  ///<�ڲ�ʹ��

	//��ʵ�����,ֻ�������ж�����
	//@����  ���ֺ���
	XC_LAYOUT_BOX = 124,      ///<���ֺ���,��������

	//@����  ��������
	XC_ANIMATION_SEQUENCE = 131,   ///<��������
	//@����  ������
	XC_ANIMATION_GROUP = 132,   ///<����ͬ����
	//@����  ������
	XC_ANIMATION_ITEM = 133,   ///<������
};
///@}

//@����ö��ǰ׺

/// group_ObjectTypeEx   ������չ����(XC_OBJECT_TYPE_EX,������չ)
/// @{
enum XC_OBJECT_TYPE_EX  //@����  �Ųʶ�����չ����
{
	xc_ex_error = -1,        ///<��������
	//@���� ��ť����_Ĭ��
	button_type_default = 0, ///<Ĭ������
	//@���� ��ť����_��ѡ
	button_type_radio,     ///<��ѡ��ť
	//@���� ��ť����_��ѡ
	button_type_check,     ///<��ѡ��ť
	//@���� ��ť����_�ر�
	button_type_close,     ///<���ڹرհ�ť
	//@���� ��ť����_��С��
	button_type_min,       ///<������С����ť
	//@���� ��ť����_���
	button_type_max,       ///<������󻯻�ԭ��ť

	element_type_layout,   ///<����Ԫ��,���ò��ֹ��ܵ�Ԫ��
};
///@}

/// group_ObjectStyle ������ʽ(XC_OBJECT_STYLE,�����������)
/// @{
enum  XC_OBJECT_STYLE    //@����   �Ųʶ�����ʽ
{
	xc_style_default = 0,
	//@����  ��ť��ʽ_Ĭ��
	button_style_default = xc_style_default,  ///<Ĭ�Ϸ��
	//@����  ��ť��ʽ_��ѡ
	button_style_radio,                 ///<��ѡ��ť
	//@����  ��ť��ʽ_��ѡ
	button_style_check,                 ///<��ѡ��ť
	//@����  ��ť��ʽ_ͼ��
	button_style_icon,                  ///<ͼ�갴ť
	//@����  ��ť��ʽ_չ��
	button_style_expand,                ///<չ����ť
	//@����  ��ť��ʽ_�ر�
	button_style_close,			        ///<�رհ�ť
	//@����  ��ť��ʽ_���
	button_style_max,			        ///<��󻯰�ť
	//@����  ��ť��ʽ_��С��
	button_style_min,			        ///<��С����ť

	button_style_scrollbar_left,         ///<ˮƽ������-��ť
	button_style_scrollbar_right,        ///<ˮƽ������-�Ұ�ť
	button_style_scrollbar_up,           ///<��ֱ������-�ϰ�ť
	button_style_scrollbar_down,         ///<��ֱ������-�°�ť
	button_style_scrollbar_slider_h,     ///<ˮƽ������-����
	button_style_scrollbar_slider_v,     ///<��ֱ������-����

	button_style_tabBar,                 ///<Tab��-��ť
	button_style_slider,                 ///<������-����

	button_style_toolBar,                ///<������-��ť
	button_style_toolBar_left,           ///<������-�������ť
	button_style_toolBar_right,          ///<������-�ҹ�����ť

	button_style_pane_close,             ///<����-�رհ�ť
	button_style_pane_lock,              ///<����-������ť
	button_style_pane_menu,              ///<����-�˵���ť

	button_style_pane_dock_left,         ///<����-��ͷ��ť��
	button_style_pane_dock_top,          ///<����-��ͷ��ť��
	button_style_pane_dock_right,        ///<����-��ͷ��ť��
	button_style_pane_dock_bottom,       ///<����-��ͷ��ť��

	element_style_frameWnd_dock_left,    ///<��ܴ���-ͣ����ͷ��
	element_style_frameWnd_dock_top,     ///<��ܴ���-ͣ����ͷ��
	element_style_frameWnd_dock_right,   ///<��ܴ���-ͣ����ͷ��
	element_style_frameWnd_dock_bottom,  ///<��ܴ���-ͣ����ͷ��

	element_style_toolBar_separator,     ///<������-�ָ���
	listBox_style_comboBox,              ///<��Ͽ�-�����б��  ,������Ͽ򵯳���ListBox
};
///@}

//@����ö��ǰ׺

/// group_WindowStyle  ������ʽ(window_style_)
/// @{
enum  window_style_ //@����  �Ųʴ�����ʽ
{
	//@����  ��
	window_style_nothing = 0x0000,   ///<ʲôҲû��
	//@����  ������
	window_style_caption = 0x0001,   ///<������
	//@����  �߿�
	window_style_border = 0x0002,   ///<�߿�,���û��ָ��,��ô�߿��СΪ0
	//@����  ����
	window_style_center = 0x0004,   ///<���ھ���
	//@����  �϶��߿�
	window_style_drag_border = 0x0008,   ///<�϶����ڱ߿�
	//@����  �϶�����
	window_style_drag_window = 0x0010,   ///<�϶�����
	//@����  �������
	window_style_allow_maxWindow = 0x0020,   ///<���������

	//@����  ͼ��
	window_style_icon = 0x0040,   ///<ͼ��
	//@����  ����
	window_style_title = 0x0080,   ///<����
	//@����  ���ư�ť��С��
	window_style_btn_min = 0x0100,   ///<���ư�ť-��С��
	//@����  ���ư�ť���
	window_style_btn_max = 0x0200,   ///<���ư�ť-���
	//@����  ���ư�ť�ر�
	window_style_btn_close = 0x0400,   ///<���ư�ť-�ر�

	///������ʽ-���ư�ť: ���� ͼ��, ����, �رհ�ť, ��󻯰�ť, ��С����ť
	//@����  Ĭ��
	window_style_default = (window_style_caption | window_style_border | window_style_center | \
	window_style_drag_border | window_style_allow_maxWindow | window_style_icon | \
		window_style_title | window_style_btn_min | window_style_btn_max | window_style_btn_close),

	///������ʽ-��: ����
	//@����  ��
	window_style_simple = (window_style_caption | window_style_border | window_style_center | window_style_drag_border | window_style_allow_maxWindow),

	///������ʽ-��������: ͼ��, ����, �رհ�ť
	//@����  ����
	window_style_pop = (window_style_caption | window_style_border | window_style_center | \
		window_style_drag_border | window_style_allow_maxWindow | window_style_icon | \
		window_style_title | window_style_btn_close),

	///ģ̬������ʽ-���ư�ť: ����, ͼ��, ����, �رհ�ť
	//@����  ģ̬
	window_style_modal = (window_style_caption | window_style_border | window_style_center | \
		window_style_icon | window_style_title | window_style_btn_close),

	///ģ̬������ʽ-��: ����
	//@����  ģ̬��
	window_style_modal_simple = (window_style_caption | window_style_border | window_style_center),
};

///@}
///@}

/////////////////////////////////////////////////////////////////////
/////////////////////�궨��///////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
/// group_macro_def  �궨��
/// @{

#ifdef _DEBUG
#define XTRACEA   _xtrace
#define XTRACE    _xtracew
#define XERRORA   XTRACEA
#define XERROR    XTRACE
XC_API void WINAPI _xtrace(const char* pFormat, ...); //֧�ֶ��߳�
XC_API void WINAPI _xtracew(const wchar_t* pFormat, ...); //֧�ֶ��߳�
#else
#define XTRACEA
#define XTRACE
#define XERROR
#define XERRORW
#endif

// #define  XC_GetObjectByUID_(T)  XC_GetObjectByUID(XRes_GetIDValue(L#T))
// #define  GetLayoutFile()        GetLayoutFile()
// #define  XC_LAYOUT_FILE(file)   const wchar_t*  GetLayoutFile(){ return file; };
// #define  XC_EVENT_DECLARE_MARK
// #define  XC_EVENT_REGISTER_MARK
// #define  XC_EVENT_IMPLEMENT_MARK(T)

//@����{
#define  CLOUDUI_flag_openUrl       1
#define  CLOUDUI_flag_downloadFile  2
#define  CLOUDUI_flag_downloadFileComplete      3
#define  CLOUDUI_flag_complete      4
//@����}

/// @name  ����ID
/// @{
#define  XC_ID_ROOT            0   ///<���ڵ�
#define  XC_ID_ERROR          -1   ///<ID����
#define  XC_ID_FIRST          -2   ///<���뿪ʼλ��(��ǰ��)
#define  XC_ID_LAST           -3   ///<����ĩβλ��(��ǰ��)
/// @}


//@����{
///@name �˵�ID , ��ǰδʹ��
///@{
#define  IDM_CLIP          1000000000    ///<����
#define  IDM_COPY          1000000001    ///<����
#define  IDM_PASTE         1000000002    ///<ճ��
#define  IDM_DELETE        1000000003    ///<ɾ��
#define  IDM_SELECTALL     1000000004    ///<ȫѡ
#define  IDM_DELETEALL     1000000005    ///<���
///@}

//����˵� ��ǰδʹ��
#define  IDM_LOCK          1000000006    ///<����
#define  IDM_DOCK          1000000007    ///<ͣ��
#define  IDM_FLOAT         1000000008    ///<����
#define  IDM_HIDE          1000000009    ///<����
//@����}

//#define  edit_style_no        0  ///<��Ч��ʽ
#define  edit_style_default   1    ///<edit Ĭ����ʽ

//@���� �ı�����������С
#define   TEXT_BUFFER_SIZE     10240  ///<�����ı���������С
/// @}

/////////////////////////////////////////////////////////////////////
/////////////////////�����¼�/////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
//@����{  �����¼�

//������Ϣ
/**
@addtogroup wndMSG

<hr>
<h2>Windows ��׼��Ϣ</h2>
- @ref WM_PAINT ���ڻ�����Ϣ
- @ref WM_CLOSE ���ڹر���Ϣ.
- @ref WM_DESTROY ����������Ϣ.
- @ref WM_NCDESTROY ���ڷǿͻ���������Ϣ.
- @ref WM_LBUTTONDOWN ����������������Ϣ
- @ref WM_LBUTTONUP ����������������Ϣ.
- @ref WM_RBUTTONDOWN ��������Ҽ�������Ϣ.
- @ref WM_RBUTTONUP ��������Ҽ�������Ϣ.
- @ref WM_LBUTTONDBLCLK ����������˫����Ϣ.
- @ref WM_RBUTTONDBLCLK ��������Ҽ�˫����Ϣ.
- @ref WM_MOUSEMOVE ��������ƶ���Ϣ.
- @ref WM_MOUSEHOVER ���������ͣ��Ϣ
- @ref WM_MOUSELEAVE ��������뿪��Ϣ.
- @ref WM_MOUSEWHEEL ���������ֹ�����Ϣ.
- @ref WM_CAPTURECHANGED ������겶��ı���Ϣ.
- @ref WM_KEYDOWN ���ڼ��̰�����Ϣ.
- @ref WM_KEYUP ���ڼ��̵�����Ϣ.
- @ref WM_CHAR       �����ַ���Ϣ.
- @ref WM_SIZE ���ڴ�С�ı���Ϣ.
- @ref WM_EXITSIZEMOVE �����˳��ƶ��������Сģʽѭ���ģ�����μ�MSDN.
- @ref WM_TIMER ���ڶ�ʱ����Ϣ.
- @ref WM_SETFOCUS ���ڻ�ý���.
- @ref WM_KILLFOCUS ����ʧȥ����.
- @ref WM_SETCURSOR  �������������.
- @ref WM_DROPFILES  �϶��ļ�������.
- @ref other ����Windowsϵͳ��Ϣ,�������Զ����Windows��Ϣ.

<hr>
@anchor WM_PAINT WM_PAINT ���ڻ�����Ϣ
@code   int CALLBACK OnWndDrawWindow(HDRAW hDraw,BOOL *pbHandled) @endcode
@param  hDraw   ͼ�λ��ƾ��.

<hr>
@anchor WM_CLOSE WM_CLOSE ���ڹر���Ϣ.
@code   int CALLBACK OnWndClose(BOOL *pbHandled) @endcode

<hr>
@anchor WM_DESTROY WM_DESTROY ����������Ϣ.
@code   int CALLBACK OnWndDestroy(BOOL *pbHandled)  @endcode

<hr>
@anchor WM_NCDESTROY WM_NCDESTROY ���ڷǿͻ���������Ϣ.
@code   int CALLBACK OnWndNCDestroy(BOOL *pbHandled) @endcode

<hr>
@anchor WM_LBUTTONDOWN WM_LBUTTONDOWN ����������������Ϣ
@code   int CALLBACK OnWndLButtonDown(UINT nFlags,POINT *pPt,BOOL *pbHandled) @endcode
@param  flags   ��μ�MSDN WM_LBUTTONDOWN.
@param  pPt     ��������.

<hr>
@anchor WM_LBUTTONUP WM_LBUTTONUP ����������������Ϣ.
@code   int CALLBACK OnWndLButtonUp(UINT nFlags,POINT *pPt,BOOL *pbHandled) @endcode
@param  flags   ��μ�MSDN WM_LBUTTONUP.
@param  pPt     ��������.

<hr>
@anchor WM_RBUTTONDOWN WM_RBUTTONDOWN ��������Ҽ�������Ϣ.
@code   int CALLBACK OnWndRButtonDown(UINT nFlags,POINT *pPt,BOOL *pbHandled) @endcode
@param  flags   ��μ�MSDN WM_RBUTTONDOWN.
@param  pPt     ��������.

<hr>
@anchor WM_RBUTTONUP WM_RBUTTONUP ��������Ҽ�������Ϣ.
@code   int CALLBACK OnWndRButtonUp(UINT nFlags,POINT *pPt,BOOL *pbHandled) @endcode
@param  flags   ��μ�MSDN WM_RBUTTONUP.
@param  pPt     ��������.

<hr>
@anchor WM_LBUTTONDBLCLK WM_LBUTTONDBLCLK ����������˫����Ϣ.
@code   int CALLBACK OnWndLButtonDBClick(UINT nFlags,POINT *pPt,BOOL *pbHandled) @endcode
@param  flags   ��μ�MSDN WM_LBUTTONDBLCLK.
@param  pPt     ��������.

<hr>
@anchor WM_RBUTTONDBLCLK WM_RBUTTONDBLCLK ��������Ҽ�˫����Ϣ.
@code   int CALLBACK OnWndRButtonDBClick(UINT nFlags,POINT *pPt,BOOL *pbHandled) @endcode
@param  flags   ��μ�MSDN WM_RBUTTONDBLCLK.
@param  pPt     ��������.

<hr>
@anchor WM_MOUSEMOVE WM_MOUSEMOVE ��������ƶ���Ϣ.
@code   int CALLBACK OnWndMouseMove(UINT nFlags,POINT *pPt,BOOL *pbHandled)  @endcode
@param  flags   ��μ�MSDN WM_MOUSEMOVE wParam����.
@param  pPt     ��������.

<hr>
@anchor WM_MOUSEHOVER WM_MOUSEHOVER ���������ͣ��Ϣ.
@code   int CALLBACK OnWndMouseHover(UINT nFlags,POINT *pPt,BOOL *pbHandled) @endcode
@param  flags  ��μ�MSDN WM_MOUSEHOVER��ϢwParam����.
@param  pPt    ���λ��

<hr>
@anchor WM_MOUSELEAVE WM_MOUSELEAVE ��������뿪��Ϣ.
@code   int CALLBACK OnWndMouseLeave(BOOL *pbHandled) @endcode

<hr>
@anchor WM_MOUSEWHEEL WM_MOUSEWHEEL ���������ֹ�����Ϣ.
@code   int CALLBACK OnWndMouseWheel(UINT nFlags,POINT *pPt,BOOL *pbHandled) @endcode
@param  flags   ��μ�MSDN WM_MOUSEWHEEL��ϢwParam����.
@param  pPt     ��������.

<hr>
@anchor WM_CAPTURECHANGED WM_CAPTURECHANGED ������겶��ı���Ϣ.
@code   int CALLBACK OnWndCaptureChanged(HWND hWnd,BOOL *pbHandled) @endcode
@param  hWnd    �����겶��Ĵ��ھ��.

<hr>
@anchor WM_KEYDOWN WM_KEYDOWN ���ڼ��̰�����Ϣ.
@code   int CALLBACK OnWndKeyDown(WPARAM wParam,LPARAM lParam,BOOL *pbHandled) @endcode
@note   wParam,lParam:��μ�MSDN WM_KEYDOWN.
<hr>
@anchor WM_KEYUP WM_KEYUP ���ڼ��̵�����Ϣ.
@code   int CALLBACK OnWndKeyUp(WPARAM wParam,LPARAM lParam,BOOL *pbHandled) @endcode
@note   wParam,lParam:��μ�MSDN WM_KEYUP.

<hr>
@anchor  WM_CHAR WM_CHAR �����ַ�������Ϣ.
@code    int CALLBACK OnWndChar(WPARAM wParam,LPARAM lParam,BOOL *pbHandled) @endcode
@note    wParam,lParam:��μ�MSDN WM_CHAR.

<hr>
@anchor WM_SIZE WM_SIZE ���ڴ�С�ı���Ϣ.
@code   int CALLBACK OnWndSize(UINT nFlags,SIZE *pSize,BOOL *pbHandled) @endcode
@param  flags   ��μ�MSDN WM_SIZE��ϢwParam����.
@param  pSize   ���ڴ�С.

<hr>
@anchor WM_EXITSIZEMOVE WM_EXITSIZEMOVE �����˳��ƶ��������Сģʽѭ��������μ�MSDN.
@code   int CALLBACK OnWndExitSizeMove(BOOL *pbHandled) @endcode

<hr>
@anchor WM_TIMER WM_TIMER ���ڶ�ʱ����Ϣ.
@code   int CALLBACK OnWndTimer(UINT nIDEvent,BOOL *pbHandled) @endcode
@param  nIDEnent ��ʱ����ʾ��.
@param  uElapse  ָ����������ϵͳ���������ĺ�����,������GetTickCount�����ķ���ֵ.

<hr>
@anchor WM_SETFOCUS WM_SETFOCUS ���ڻ�ý���.
@code   int CALLBACK OnWndSetFocus(BOOL *pbHandled) @endcode

<hr>
@anchor WM_KILLFOCUS WM_KILLFOCUS ����ʧȥ����.
@code   int CALLBACK OnWndKillFocus(BOOL *pbHandled) @endcode

<hr>
@anchor  WM_SETCURSOR WM_SETCURSOR �������������.
@code    int CALLBACK OnWndSetCursor(WPARAM wParam,LPARAM lParam,BOOL *pbHandled) @endcode
@note    wParam,lParam:��μ�MSDN WM_SETCURSOR.

<hr>
@anchor  WM_DROPFILES WM_DROPFILES �϶��ļ���������Ϣ,������:XWnd_EnableDragFiles()
@code    int CALLBACK OnDropFiles(HDROP hDropInfo , BOOL *pbHandled) @endcode
@note    ��μ�MSDN WM_DROPFILES.

<hr>
@anchor other ����Windowsϵͳ��Ϣ,�������Զ����Windows��Ϣ.
@code   int CALLBACK OnWndOther(WPARAM wParam,LPARAM lParam,BOOL *pbHandled) @endcode
@param  wParam  ��Ϣ����.
@param  lParam  ��Ϣ����.
@note   ������Ϣ��μ�MSDN.
@{
*/

//������Ϣ-����ϵͳ�ǿͻ�����Ϣ
#define  XWM_EVENT_ALL        WM_APP+1000 //�¼�Ͷ�� -------������-------����Ҫע��

//wParam:left-top�������; lParam:right-bottom�������; �����2������Ϊ��,��ô�ػ���������
//#define  XWM_REDRAW           WM_APP+1007  //�����ػ���ʱ ----������-----�ڲ��Զ�����Ϣ

//�ػ�Ԫ��,�ڲ�ʹ��
#define  XWM_REDRAW_ELE       0x7000+1 //�ػ�Ԫ�� wParam:Ԫ�ؾ��, lParam:RECT*���ڴ�������

///@brief ������Ϣ����
///@code  int CALLBACK OnWndProc(UINT message, WPARAM wParam, LPARAM lParam, BOOL *pbHandled)  @endcode
#define  XWM_WINDPROC         0x7000+2
#define  XWM_DRAW_T           0x7000+3    //���ڻ���,�ڲ�ʹ��, wParam:0, lParam:0

#define  XWM_TIMER_T          0x7000+4    //�ڲ�ʹ��, wParam:hXCGUI, lParam:ID

/// @brief  �Ųʶ�ʱ��,��ϵͳ��ʱ��,ע����ϢXWM_TIMER����
/// @code  int CALLBACK OnWndXCTimer(UINT nTimerID,BOOL *pbHandled)  @endcode
/// @param nTimerID   ��ʱ��ID
#define  XWM_XC_TIMER         0x7000+5

#define  XWM_CLOUDUI_DOWNLOADFILE_COMPLETE   0x7000+6  //�ڲ�ʹ��

#define  XWM_CLOUNDUI_OPENURL_WAIT    0x7000+7 //�ڲ�ʹ��

#define  XWM_CALL_UI_THREAD   0x7000+8     //�ڲ�ʹ��


/// @brief ָ��Ԫ�ػ�ý���
/// @code  int CALLBACK OnWndSetFocusEle(HELE hEle,BOOL *pbHandled)  @endcode
#define  XWM_SETFOCUS_ELE         0x7000+9

/// @brief  ����ͼ���¼�
/// @code  int CALLBACK OnWndTrayIcon(WPARAM wParam, LPARAM lParam, BOOL *pbHandled)  @endcode
#define  XWM_TRAYICON         0x7000+10

/// @brief �˵�����
///@code  int CALLBACK OnWndMenuPopup(HMENUX hMenu, BOOL *pbHandled) @endcode
#define  XWM_MENU_POPUP       0x7000+11

/// @brief �˵���������
///@code  int CALLBACK OnWndMenuPopupWnd(HMENUX hMenu,menu_popupWnd_ *pInfo,BOOL *pbHandled) @endcode
#define  XWM_MENU_POPUP_WND     0x7000+12

/// @brief �˵�ѡ��
///@code  int CALLBACK OnWndMenuSelect(int nID,BOOL *pbHandled) @endcode
#define  XWM_MENU_SELECT       0x7000+13

/// @brief �˵��˳�
///@code  int CALLBACK OnWndMenuExit(BOOL *pbHandled) @endcode
#define  XWM_MENU_EXIT         0x7000+14

/// @brief ���Ʋ˵�����, ���øù�����Ҫ����XMenu_EnableDrawBackground().
///@code  int CALLBACK OnWndMenuDrawBackground(HDRAW hDraw,menu_drawBackground_ *pInfo,BOOL *pbHandled) @endcode
#define  XWM_MENU_DRAW_BACKGROUND   0x7000+15

/// @brief ���Ʋ˵����¼�, ���øù�����Ҫ����XMenu_EnableDrawItem().
/// @code  int CALLBACK OnMenuDrawItem(HDRAW hDraw,menu_drawItem_* pInfo,BOOL *pbHandled) @endcode
#define  XWM_MENU_DRAWITEM             0x7000+16

#define  XWM_COMBOBOX_POPUP_DROPLIST   0x7000+17  //������������б�,�ڲ�ʹ��

/// @brief ��������
///@code  int CALLBACK OnWndFloatPane(HWINDOW hFloatWnd, HELE hPane, BOOL *pbHandled) @endcode
#define  XWM_FLOAT_PANE               0x7000+18  //@���� �����¼�_��������  //��������, ����ӿ�ܴ����е���,��ɸ�������

/// @brief ���ڻ��������Ϣ
///@code   int CALLBACK OnWndDrawWindowEnd(HDRAW hDraw,BOOL *pbHandled) @endcode
#define  XWM_PAINT_END               0x7000+19

///@brief ���ڻ�����ɲ����Ѿ���ʾ����Ļ
///@code   int CALLBACK OnWndDrawWindowDisplay(BOOL *pbHandled) @endcode
#define  XWM_PAINT_DISPLAY           0x7000+20

/// @brief  ��ܴ�����ͷ��������  ���û������ͷ�ϵİ�ťʱ,��ʾ��Ӧ�Ĵ���,��ʧȥ����ʱ�Զ����ش���
///@param  hWindowDock �������񴰿ھ��
///@param  hPane       ������
///@code   int CALLBACK OnWndDocPopup(HWINDOW hWindowDock,  HELE hPane, BOOL *pbHandled) @endcode
#define  XWM_DOCK_POPUP              0x7000+21

/// @brief  ���������϶�   �û��϶����������ƶ�,��ʾͣ����ʾ
///@param  hFloatWnd �϶��ĸ������ھ��
///@param  hArray    HWINDOW array[6],����ͣ����ʾ���ھ������,��6����Ա,�ֱ�Ϊ:[0]�м�ʮ��, [1]���,[2]����,[3]�Ҳ�,[4]�ײ�, [5]ͣ��λ��Ԥ��
///@code   int CALLBACK OnWndFloatWndDrag(HWINDOW hFloatWnd, HWINDOW* hArray, BOOL *pbHandled) @endcode
#define  XWM_FLOATWND_DRAG           0x7000+22

/// @brief  ������ʾ����
///@code int CALLBACK OnWndPaneShow(HELE hPane, BOOL bShow, BOOL *pbHandled); @endcode
#define  XWM_PANE_SHOW              0x7000+23

/// @brief  ��ܴ�������ͼ����ı�, �������ͼû�а�Ԫ��, ��ô������ı�ʱ�������¼�
///@code int CALLBACK OnWndLayoutViewRect(int width, int height, BOOL *pbHandled); @endcode
#define  XWM_BODYVIEW_RECT          0x7000+24
///@}
//@����}

/////////////////////////////////////////////////////////////////////
/////////////////Ԫ���¼�/////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
//@����{  Ԫ���¼�
/// @addtogroup eleEvents
/// @{


/// @brief Ԫ�ش�������¼�.
/// @code  int CALLBACK OnEventProc(UINT nEvent, WPARAM wParam, LPARAM lParam, BOOL *pbHandled) @endcode
#define  XE_ELEPROCE         1

/// @brief Ԫ�ػ����¼�
/// @code  int CALLBACK OnDraw(HDRAW hDraw,BOOL *pbHandled)  @endcode
#define  XE_PAINT            2

/// @brief ��Ԫ�ؼ���Ԫ�ػ�������¼�.���øù�����Ҫ����XEle_EnableEvent_XE_PAINT_END()
/// @code  int CALLBACK OnPaintEnd(HDRAW hDraw,BOOL *pbHandled) @endcode
#define  XE_PAINT_END        3

/// @brief ������ͼ�����¼�.
/// @code int CALLBACK OnDrawScrollView(HDRAW hDraw,BOOL *pbHandled) @endcode
#define  XE_PAINT_SCROLLVIEW   4

/// @brief Ԫ������ƶ��¼�.
/// @code  int CALLBACK OnMouseMove(UINT nFlags, POINT *pPt, BOOL *pbHandled)  @endcode
#define  XE_MOUSEMOVE        5

/// @brief Ԫ���������¼�.
/// @code  int CALLBACK OnMouseStay(BOOL *pbHandled) @endcode
#define  XE_MOUSESTAY        6

/// @brief Ԫ�������ͣ�¼�.
/// @code  int CALLBACK OnMouseHover(UINT nFlags, POINT *pPt, BOOL *pbHandled) @endcode
#define  XE_MOUSEHOVER       7

/// @brief Ԫ������뿪�¼�.
/// @param  hEleStay ��������ͣ��״̬��Ԫ��.
/// @code  int CALLBACK OnMouseLeave(HELE hEleStay,BOOL *pbHandled) @endcode
#define  XE_MOUSELEAVE       8

/// @brief Ԫ�������ֹ����¼�. ����ǹ�����ͼ��Ҫ���� XEle_EnableEvent_XE_MOUSEWHEEL()
/// @code  int CALLBACK OnMouseWheel(UINT nFlags,POINT *pPt,BOOL *pbHandled) @endcode
/// @param flags      ��MSDN��WM_MOUSEWHEEL��ϢwParam����˵��.
#define  XE_MOUSEWHEEL         9

/// @brief �����������¼�.
/// @code  int CALLBACK OnLButtonDown(UINT nFlags, POINT *pPt,BOOL *pbHandled)  @endcode
#define  XE_LBUTTONDOWN        10

/// @brief �����������¼�.
/// @code  int CALLBACK OnLButtonUp(UINT nFlags, POINT *pPt,BOOL *pbHandled) @endcode
#define  XE_LBUTTONUP          11

/// @brief ����Ҽ������¼�.
/// @code  int CALLBACK OnRButtonDown(UINT nFlags, POINT *pPt,BOOL *pbHandled)  @endcode
#define  XE_RBUTTONDOWN        12

/// @brief ����Ҽ������¼�.
/// @code int CALLBACK OnRButtonUp(UINT nFlags, POINT *pPt,BOOL *pbHandled)  @endcode
#define  XE_RBUTTONUP          13

/// @brief ������˫���¼�.
/// @code   int CALLBACK OnLButtonDBClick(UINT nFlags, POINT *pPt,BOOL *pbHandled) @endcode
#define  XE_LBUTTONDBCLICK     14


//#define  XE_RBUTTONDBCLICK     15


/// @brief �Ųʶ�ʱ��,��ϵͳ��ʱ��,��ʱ����Ϣ XM_TIMER.
/// @code int CALLBACK OnEleXCTimer(UINT nTimerID,BOOL *pbHandled) @endcode
/// @param hEle        Ԫ�ؾ��.
/// @param nTimerID    ��ʱ��ID.
#define  XE_XC_TIMER             16

/// @brief ���������¼�. ��ͣʹ��
/// @code int CALLBACK OnAdjustLayout(int nFlags, UINT nAdjustNo, BOOL *pbHandled) @endcode
#define  XE_ADJUSTLAYOUT         17

/// @brief ������������¼�.
/// @code int CALLBACK OnAdjustLayoutEnd(int nFlags, UINT nAdjustNo, BOOL *pbHandled) @endcode
#define  XE_ADJUSTLAYOUT_END     18

/// @brief Ԫ�ع�����ʾ�����¼�.
/// @code int CALLBACK OnTooltipPopup(HWINDOW hWindowTooltip, const wchar_t* pText, BOOL *pbHandled) @endcode
#define  XE_TOOLTIP_POPUP        19

/// @brief Ԫ�ػ�ý����¼�.
/// @code int CALLBACK OnSetFocus(BOOL *pbHandled) @endcode
#define  XE_SETFOCUS           31

/// @brief Ԫ��ʧȥ�����¼�.
/// @code int CALLBACK OnKillFocus(BOOL *pbHandled) @endcode
#define  XE_KILLFOCUS          32

/// @brief Ԫ�ؼ��������¼�. �������Ӷ���֮ǰ����
/// @code int CALLBACK OnDestroy(BOOL *pbHandled) @endcode
#define  XE_DESTROY            33

/// @brief Ԫ����������¼�. �������Ӷ���֮�󴥷�
/// @code int CALLBACK OnDestroyeEnd(BOOL *pbHandled) @endcode
#define  XE_DESTROY_END        42

/// @brief Ԫ�ش�С�ı��¼�.
/// @code int CALLBACK OnSize(int nFlags, UINT nAdjustNo, BOOL *pbHandled) @endcode
/// @param  nFlags  adjustLayout_
#define  XE_SIZE               36

/// @brief Ԫ����ʾ�����¼�.
/// @code  int CALLBACK OnShow(BOOL bShow,BOOL *pbHandled) @endcode
#define  XE_SHOW               37

/// @brief Ԫ�����������¼�.
/// @code  int CALLBACK OnSetFont(BOOL *pbHandled) @endcode
#define  XE_SETFONT           38

/// @brief Ԫ�ذ����¼�.
/// @code  int CALLBACK OnKeyDown(WPARAM wParam,LPARAM lParam,BOOL *pbHandled) @endcode
/// @note  wParam,lParam:��μ�MSDN WM_KEYDOWN.
#define  XE_KEYDOWN            39

/// @brief Ԫ�ذ����¼�.
/// @code  int CALLBACK OnKeyUp(WPARAM wParam,LPARAM lParam,BOOL *pbHandled) @endcode
/// @note  wParam,lParam:��μ�MSDN WM_KEYDOWN.
#define  XE_KEYUP             40

/// @brief ͨ��TranslateMessage����������ַ��¼�.
/// @code  int CALLBACK OnChar(WPARAM wParam,LPARAM lParam,BOOL *pbHandled) @endcode
/// @note   wParam,lParam:��μ�MSDN WM_KEYDOWN.
#define  XE_CHAR               41

#define  XE_SYSKEYDOWN     42

#define  XE_SYSKEYUP       43

/// @brief Ԫ��������겶��.
/// @code int CALLBACK OnSetCapture(BOOL *pbHandled) @endcode
#define  XE_SETCAPTURE      51

/// @brief Ԫ��ʧȥ��겶��.
/// @code int CALLBACK OnKillCapture(BOOL *pbHandled) @endcode
#define  XE_KILLCAPTURE     52

/// @brief ���������
/// @code int CALLBACK OnSetCursor(WPARAM wParam,LPARAM lParam,BOOL *pbHandled) @endcode
#define  XE_SETCURSOR            53

///@brief �˵�����
///@code  int CALLBACK OnMenuPopup(HMENUX hMenu, BOOL *pbHandled) @endcode
#define  XE_MENU_POPUP       57

///@brief �˵���������
///@code  int CALLBACK OnMenuPopupWnd(HMENUX hMenu,menu_popupWnd_* pInfo,BOOL *pbHandled) @endcode
#define  XE_MENU_POPUP_WND     58

/// @brief �˵���ѡ���¼�.
/// @code  int CALLBACK OnMenuSelect(int nItem,BOOL *pbHandled) @endcode
/// @param nItem          �˵���id.
#define  XE_MENU_SELECT      59

///@brief ���Ʋ˵�����, ���øù�����Ҫ����XMenu_EnableDrawBackground().
///@code  int CALLBACK OnMenuDrawBackground(HDRAW hDraw,menu_drawBackground_ *pInfo,BOOL *pbHandled) @endcode
#define  XE_MENU_DRAW_BACKGROUND   60

/// @brief ���Ʋ˵����¼�, ���øù�����Ҫ����XMenu_EnableDrawItem().
/// @code  int CALLBACK OnMenuDrawItem(HDRAW hDraw,menu_drawItem_* pInfo,BOOL *pbHandled) @endcode
#define  XE_MENU_DRAWITEM    61

/// @brief �˵��˳��¼�.
/// @code  int CALLBACK OnMenuExit(BOOL *pbHandled) @endcode
#define  XE_MENU_EXIT        62

/// @brief ��ť����¼�.
/// @code int CALLBACK OnBtnClick(BOOL *pbHandled) @endcode
#define  XE_BNCLICK            34

/// @brief ��ťѡ���¼�
/// @code int CALLBACK OnButtonCheck(BOOL bCheck,BOOL *pbHandled) @endcode
#define  XE_BUTTON_CHECK       35

/// @brief ������ͼԪ��ˮƽ�����¼�,������ͼ����.
/// @code  int CALLBACK OnScrollViewScrollH(int pos,BOOL *pbHandled) @endcode
/// @param pos         ��ǰ������.
#define  XE_SCROLLVIEW_SCROLL_H    54

/// @brief ������ͼԪ�ش�ֱ�����¼�,������ͼ����.
/// @code  int CALLBACK OnScrollViewScrollV(int pos,BOOL *pbHandled) @endcode
/// @param pos         ��ǰ������.
#define  XE_SCROLLVIEW_SCROLL_V    55

/// @brief ������Ԫ�ع����¼�,����������.
/// @code  int CALLBACK OnSBarScroll(int pos,BOOL *pbHandled) @endcode
/// @param pos   ��ǰ������.
#define  XE_SBAR_SCROLL        56

/// @brief ������Ԫ��,����λ�øı��¼�.
/// @code  int CALLBACK OnSliderBarChange(int pos,BOOL *pbHandled) @endcode
#define  XE_SLIDERBAR_CHANGE   63

/// @brief ������Ԫ��,���ȸı��¼�.
/// @code  int CALLBACK OnProgressBarChange(int pos,BOOL *pbHandled) @endcode
#define  XE_PROGRESSBAR_CHANGE  64

/// @brief ��Ͽ������б���ѡ���¼�.
/// @code  int CALLBACK OnComboBoxSelect(int iItem,BOOL *pbHandled)  @endcode
#define  XE_COMBOBOX_SELECT       71

/// @brief ��Ͽ������б���ѡ������¼�,�༭�������Ѿ��ı�.
/// @code  int CALLBACK OnComboBoxSelectEnd(int iItem,BOOL *pbHandled)  @endcode
#define  XE_COMBOBOX_SELECT_END   74

/// @brief ��Ͽ������б����¼�.
/// @code  int CALLBACK OnComboBoxPopupList(HWINDOW hWindow,HELE hListBox,BOOL *pbHandled)  @endcode
#define  XE_COMBOBOX_POPUP_LIST   72

/// @brief ��Ͽ������б��˳��¼�.
/// @code  int CALLBACK OnComboBoxExitList(BOOL *pbHandled)  @endcode
#define  XE_COMBOBOX_EXIT_LIST    73

/// @brief �б��Ԫ��-��ģ�崴���¼�, ģ�帴�û�����������; �滻ģ����Ч�ж�nFlag,��Ϊ�ڲ�����ģ���Ƿ�ı�,���õ����ظ�
/// @code int CALLBACK OnListBoxTemplateCreate(listBox_item_* pItem, int nFlag, BOOL *pbHandled) @endcode
/// @param nFlag  0:״̬�ı�; 1:��ģ��ʵ��; 2:��ģ�帴��
#define  XE_LISTBOX_TEMP_CREATE     81

/// @brief �б��Ԫ��-��ģ�崴������¼�,ģ�帴�û�����������;�������½����Ǹ���,����Ҫ��������, ��Ϊ����ʱ��Ҫע���¼������ظ�ע��
/// @code int CALLBACK OnListBoxTemplateCreateEnd(listBox_item_* pItem, int nFlag, BOOL *pbHandled) @endcode
/// @param nFlag  0:״̬�ı�(����); 1:��ģ��ʵ��; 2:��ģ�帴��
#define  XE_LISTBOX_TEMP_CREATE_END     82
#define  XE_LISTBOX_TEMP_UPDATE   XE_LISTBOX_TEMP_CREATE_END

/// @brief �б��Ԫ��,��ģ������.
/// @code int CALLBACK OnListBoxTemplateDestroy(listBox_item_* pItem, int nFlag, BOOL *pbHandled) @endcode
/// @param  nFlag   0:��������;  1:�ƶ�������(���ᱻ����,��ʱ���汸��,����Ҫʱ������)
#define  XE_LISTBOX_TEMP_DESTROY    83

/// @brief �б��Ԫ��,��ģ���������. ��ͣ��
/// @code  int CALLBACK OnListBoxTemplateAdjustCoordinate(listBox_item_* pItem, BOOL *pbHandled) @endcode
#define  XE_LISTBOX_TEMP_ADJUST_COORDINATE  84

/// @brief �б��Ԫ��,������¼�.
/// @code  int CALLBACK OnListBoxDrawItem(HDRAW hDraw,listBox_item_* pItem,BOOL *pbHandled) @endcode
#define  XE_LISTBOX_DRAWITEM     85

/// @brief �б��Ԫ��,��ѡ���¼�.
/// @code  int CALLBACK OnListBoxSelect(int iItem,BOOL *pbHandled)  @endcode
#define  XE_LISTBOX_SELECT       86

/// @brief �б�Ԫ��-��ģ�崴���¼�,ģ�帴�û�����������;�滻ģ����Ч�ж�nFlag,��Ϊ�ڲ�����ģ���Ƿ�ı�,���õ����ظ�
/// @code  int CALLBACK OnListTemplateCreate(list_item_* pItem,int nFlag, BOOL *pbHandled) @endcode
/// @param nFlag  0:״̬�ı�; 1:��ģ��ʵ��; 2:��ģ�帴��
#define  XE_LIST_TEMP_CREATE     101

/// @brief �б�Ԫ��-��ģ�崴������¼�,ģ�帴�û�����������;�������½����Ǹ���,����Ҫ��������, ��Ϊ����ʱ��Ҫע���¼������ظ�ע��
/// @code  int CALLBACK OnListTemplateCreateEnd(list_item_* pItem, int nFlag, BOOL *pbHandled) @endcode
/// @param nFlag  0:״̬�ı�(����); 1:��ģ��ʵ��; 2:��ģ�帴��
#define  XE_LIST_TEMP_CREATE_END     102
#define  XE_LIST_TEMP_UPDATE  XE_LIST_TEMP_CREATE_END

/// @brief �б�Ԫ��,��ģ������.
/// @code int CALLBACK OnListTemplateDestroy(list_item_* pItem, int nFlag, BOOL *pbHandled) @endcode
/// @param  nFlag   0:��������;  1:�ƶ�������(���ᱻ����,��ʱ���汸��,����Ҫʱ������)
#define  XE_LIST_TEMP_DESTROY    103

/// @brief �б�Ԫ��,��ģ���������. ��ͣ��
/// @code  typedef int CALLBACK OnListTemplateAdjustCoordinate(list_item_* pItem,BOOL *pbHandled) @endcode
#define  XE_LIST_TEMP_ADJUST_COORDINATE  104

/// @brief �б�Ԫ��,������.
/// @code  int CALLBACK OnListDrawItem(HDRAW hDraw,list_item_* pItem,BOOL *pbHandled) @endcode
#define  XE_LIST_DRAWITEM                105

/// @brief �б�Ԫ��,��ѡ���¼�.
/// @code  int CALLBACK OnListSelect(int iItem,BOOL *pbHandled) @endcode
#define  XE_LIST_SELECT                  106

/// @brief �б�Ԫ�ػ����б�ͷ��.
/// @code  int CALLBACK OnListHeaderDrawItem(HDRAW hDraw, list_header_item_* pItem, BOOL *pbHandled) @endcode
#define  XE_LIST_HEADER_DRAWITEM         107

/// @brief �б�Ԫ��,�б�ͷ�����¼�.
/// @code  int CALLBACK OnListHeaderClick(int iItem, BOOL *pbHandled) @endcode
#define  XE_LIST_HEADER_CLICK            108

/// @brief �б�Ԫ��,�б�ͷ���ȸı��¼�.
/// @code  int CALLBACK OnListHeaderItemWidthChange(int iItem, int nWidth BOOL *pbHandled) @endcode
#define  XE_LIST_HEADER_WIDTH_CHANGE     109

/// @brief �б�Ԫ��,�б�ͷ��ģ�崴��.
/// @code  int CALLBACK OnListHeaderTemplateCreate(list_header_item_* pItem,BOOL *pbHandled) @endcode
#define  XE_LIST_HEADER_TEMP_CREATE          110

/// @brief �б�Ԫ��,�б�ͷ��ģ�崴������¼�.
/// @code  int CALLBACK OnListHeaderTemplateCreateEnd(list_header_item_* pItem,BOOL *pbHandled) @endcode
#define  XE_LIST_HEADER_TEMP_CREATE_END      111

/// @brief �б�Ԫ��,�б�ͷ��ģ������.
/// @code int CALLBACK OnListHeaderTemplateDestroy(list_header_item_* pItem,BOOL *pbHandled) @endcode
#define  XE_LIST_HEADER_TEMP_DESTROY          112

/// @brief �б�Ԫ��,�б�ͷ��ģ���������. ��ͣ��
/// @code  typedef int CALLBACK OnListHeaderTemplateAdjustCoordinate(list_header_item_* pItem,BOOL *pbHandled) @endcode
#define  XE_LIST_HEADER_TEMP_ADJUST_COORDINATE  113


/// @brief �б���Ԫ��-��ģ�崴��,ģ�帴�û�����������; �滻ģ����Ч�ж�nFlag,��Ϊ�ڲ�����ģ���Ƿ�ı�,���õ����ظ�
/// @code  int CALLBACK OnTreeTemplateCreate(tree_item_* pItem,int nFlag, BOOL *pbHandled) @endcode
/// @param nFlag  0:״̬�ı�; 1:��ģ��ʵ��; 2:��ģ�帴��
#define  XE_TREE_TEMP_CREATE             121

/// @brief �б���Ԫ��-��ģ�崴�����,ģ�帴�û�����������; �������½����Ǹ���,����Ҫ��������, ��Ϊ����ʱ��Ҫע���¼������ظ�ע��
/// @code  int CALLBACK OnTreeTemplateCreateEnd(tree_item_* pItem, int nFlag, BOOL *pbHandled) @endcode
/// @param nFlag  0:״̬�ı�(����); 1:��ģ��ʵ��; 2:��ģ�帴��
#define  XE_TREE_TEMP_CREATE_END         122
#define  XE_TREE_TEMP_UPDATE   XE_TREE_TEMP_CREATE_END

/// @brief �б���Ԫ��-��ģ������,ģ�帴�û�����������;
/// @code  int CALLBACK OnTreeTemplateDestroy(tree_item_* pItem, int nFlag, BOOL *pbHandled) @endcode
/// @param  nFlag   0:��������;  1:�ƶ�������(���ᱻ����,��ʱ���汸��,����Ҫʱ������)
#define  XE_TREE_TEMP_DESTROY            123

/// @brief ��Ԫ��,��ģ��,����������. ��ͣ��
/// @code  int CALLBACK OnTreeTemplateAdjustCoordinate(tree_item_* pItem,BOOL *pbHandled) @endcode
#define  XE_TREE_TEMP_ADJUST_COORDINATE  124

/// @brief ��Ԫ��,������.
/// @code  int CALLBACK OnTreeDrawItem(HDRAW hDraw,tree_item_* pItem,BOOL *pbHandled) @endcode
#define  XE_TREE_DRAWITEM                125

/// @brief ��Ԫ��,��ѡ���¼�.
/// @code  int CALLBACK OnTreeSelect(int nItemID,BOOL *pbHandled) @endcode
/// @param nItemID  ��ID.
#define  XE_TREE_SELECT                 126

/// @brief ��Ԫ��,��չ�������¼�.
/// @code int CALLBACK OnTreeExpand(int id,BOOL bExpand,BOOL *pbHandled) @endcode
#define  XE_TREE_EXPAND                 127

/// @brief ��Ԫ��,�û������϶���, �ɶԲ���ֵ�޸�.
/// @code int CALLBACK OnTreeDragItemIng(tree_drag_item_* pInfo, BOOL *pbHandled) @endcode
#define  XE_TREE_DRAG_ITEM_ING           128

/// @brief ��Ԫ��,�϶����¼�.
/// @code int CALLBACK OnTreeDragItem(tree_drag_item_* pInfo, BOOL *pbHandled) @endcode
#define  XE_TREE_DRAG_ITEM               129

/// @brief �б���Ԫ��-��ģ�崴���¼�,ģ�帴�û�����������; �滻ģ����Ч�ж�nFlag,��Ϊ�ڲ�����ģ���Ƿ�ı�,���õ����ظ�
/// @code  int CALLBACK OnListViewTemplateCreate(listView_item_* pItem,int nFlag, BOOL *pbHandled) @endcode
/// @param nFlag  0:״̬�ı�(��ǰδʹ��); 1��ģ��ʵ��; 2��ģ�帴��
#define  XE_LISTVIEW_TEMP_CREATE           141

/// @brief �б���Ԫ��-��ģ�崴������¼�,ģ�帴�û�����������; �������½����Ǹ���,����Ҫ��������, ��Ϊ����ʱ��Ҫע���¼������ظ�ע��
/// @code  int CALLBACK OnListViewTemplateCreateEnd(listView_item_* pItem,int nFlag, BOOL *pbHandled) @endcode
/// @param nFlag  0:״̬�ı�(����,��ǰδʹ��); 1:��ģ��ʵ��; 2:��ģ�帴��
#define  XE_LISTVIEW_TEMP_CREATE_END           142
#define  XE_LISTVIEW_TEMP_UPDATE     XE_LISTVIEW_TEMP_CREATE_END

/// @brief �б���Ԫ��-��ģ������, ģ�帴�û�����������;
/// @code  int CALLBACK OnListViewTemplateDestroy(listView_item_* pItem, int nFlag, BOOL *pbHandled) @endcode
/// @param  nFlag   0:��������;  1:�ƶ��������б�(���ᱻ����, ��ʱ���汸��, ����Ҫʱ������)
#define  XE_LISTVIEW_TEMP_DESTROY           143

/// @brief �б���Ԫ��,��ģ���������.��ͣ��
/// @code  int CALLBACK OnListViewTemplateAdjustCoordinate(listView_item_* pItem,BOOL *pbHandled) @endcode
#define  XE_LISTVIEW_TEMP_ADJUST_COORDINATE   144

/// @brief �б���Ԫ��,�Ի���.
/// @code int CALLBACK OnListViewDrawItem(HDRAW hDraw,listView_item_* pItem,BOOL *pbHandled)  @endcode
#define  XE_LISTVIEW_DRAWITEM              145

/// @brief �б���Ԫ��,��ѡ���¼�.
/// @code int CALLBACK OnListViewSelect(int iGroup,int iItem,BOOL *pbHandled) @endcode
#define  XE_LISTVIEW_SELECT            146

/// @brief �б���Ԫ��,��չ�������¼�.
/// @code int CALLBACK OnListViewExpand(int iGroup,BOOL bExpand,BOOL *pbHandled) @endcode
#define  XE_LISTVIEW_EXPAND         147

/// @brief ��������Ԫ�� ��ֵ�ı��¼�
/// @code   int CALLBACK OnPGridValueChange(int nItemID,BOOL *pbHandled) @endcode
/// @param  nItemID  ��ID.
#define  XE_PGRID_VALUE_CHANGE     151

/// @code int CALLBACK OnPGridItemSet(int nItemID, BOOL *pbHandled)  @endcode
#define  XE_PGRID_ITEM_SET          152

/// @code int CALLBACK OnPGridItemSelect(int nItemID, BOOL *pbHandled)  @endcode
#define  XE_PGRID_ITEM_SELECT         153

/// @code int CALLBACK OnPGridItemAdjustCoordinate(propertyGrid_item_* pItem, BOOL *pbHandled)  @endcode
#define  XE_PGRID_ITEM_ADJUST_COORDINATE  154

/// @code int CALLBACK OnPGridItemDestroy(int nItemID, BOOL *pbHandled)  @endcode
#define  XE_PGRID_ITEM_DESTROY   155

/// @code int CALLBACK OnPGridItemExpand(int nItemID, BOOL bExpand, BOOL *pbHandled) @endcode
#define  XE_PGRID_ITEM_EXPAND    156


/// @code int CALLBACK OnEditSet(BOOL *pbHandled)  @endcode
#define  XE_EDIT_SET           180

/// @code int CALLBACK OnEditDrawRow(HDRAW hDraw, int iRow, BOOL *pbHandled)  @endcode
#define  XE_EDIT_DRAWROW    181   //��δʹ��

/// @code int CALLBACK OnEditChanged(BOOL *pbHandled)  @endcode
#define  XE_EDIT_CHANGED   182

/// @code int CALLBACK OnEditPosChanged(int iPos, BOOL *pbHandled)  @endcode
#define  XE_EDIT_POS_CHANGED    183

/// @code int CALLBACK OnEditStyleChanged(int iStyle, BOOL *pbHandled)  @endcode
#define  XE_EDIT_STYLE_CHANGED  184

/// @code int CALLBACK OnEditEnterGetTabAlign(BOOL *pbHandled)  @endcode
#define  XE_EDIT_ENTER_GET_TABALIGN    185  //�س�TAB����,������ҪTAB����

/// @code int CALLBACK OnEditSwapRow(int iRow, int bArrowUp, BOOL *pbHandled)  @endcode
#define  XE_EDIT_SWAPROW     186


/// @brief �������ݸı��¼� ����:����ע�Ͳ���, ������������, �����ʽ��
/// @code int CALLBACK OnEditChangeRows(int iRow, int nRows, BOOL *pbHandled)  @endcode
/// @param iRow   ��ʼ��
/// @param nRows  �ı�������
#define  XE_EDITOR_MODIFY_ROWS         190  //���������޸��¼�

/// @code int CALLBACK OnEditorSetBreakpoint(int iRow, BOOL bCheck, BOOL *pbHandled)  @endcode
#define  XE_EDITOR_SETBREAKPOINT       191 //���öϵ�

/// @code int CALLBACK OnEditorRemoveBreakpoint(int iRow, BOOL *pbHandled)  @endcode
#define  XE_EDITOR_REMOVEBREAKPOINT    192 //�Ƴ��ϵ�

// iRow: �����п�ʼλ������,  if(nChangeRows>0) iEnd= iRow + nChangeRows
// nChangeRows: �ı�����, ���������, ����ɾ����
/// @code int CALLBACK OnEditorBreakpointChanged(int iRow, int nChangeRows, BOOL *pbHandled)  @endcode
#define  XE_EDIT_ROW_CHANGED  193 //�ɶԶϵ�λ���޸�

/// @code int CALLBACK OnEditorAutoMatchSelect(int iRow, int nRows, BOOL *pbHandled)  @endcode
#define  XE_EDITOR_AUTOMATCH_SELECT  194

/// @brief δ����, ���λ�øı�, ��ʽ������
/// @code int CALLBACK OnEditorFormatCodeTest(int iRow, int iCol, BOOL *pbHandled)  @endcode
#define  XE_EDITOR_FORMATCODE_TEST    187

/// @brief  TabBar��ǩ��ťѡ��ı��¼�
/// @code   int CALLBACK OnTabBarSelect(int iItem, BOOL *pbHandled) @endcode
/// @param  iItem  ��ǩλ������.
#define  XE_TABBAR_SELECT    221

/// @brief  TabBar��ǩ��ťɾ���¼�
/// @code   int CALLBACK OnTabBarDelete(int iItem, BOOL *pbHandled) @endcode
/// @param  iItem  ��ǩλ������.
#define  XE_TABBAR_DELETE    222

/// @brief  ����Ԫ�����ڸı��¼�
/// @code   int CALLBACK OnCalendarChange(BOOL *pbHandled) @endcode
#define  XE_MONTHCAL_CHANGE   231

/// @brief  ����ʱ��Ԫ��,���ݸı��¼�
/// @code   int CALLBACK OnDateTimeChange(BOOL *pbHandled) @endcode
#define  XE_DATETIME_CHANGE    241

/// @brief  ����ʱ��Ԫ��,����������Ƭ�¼�
/// @code   int CALLBACK OnDateTimePopupMonthCal(HWINDOW hMonthCalWnd,HELE hMonthCal,BOOL *pbHandled) @endcode
#define  XE_DATETIME_POPUP_MONTHCAL     242

/// @brief  ����ʱ��Ԫ��,������������Ƭ�˳��¼�
/// @code   int CALLBACK OnDateTimeExitMonthCal(HWINDOW hMonthCalWnd,HELE hMonthCal,BOOL *pbHandled) @endcode
#define  XE_DATETIME_EXIT_MONTHCAL      243

/// @brief  �ļ��Ϸ��¼�, ��������:XWnd_EnableDragFiles()
/// @code   int CALLBACK OnDropFiles(HDROP hDropInfo, BOOL *pbHandled) @endcode
#define  XE_DROPFILES                 250

//#define  XE_LISTVIEW_DRAG_INSERT
//#define  XE_PANE_LOACK
//#define  XE_PANE_DOCK
//#define  XE_PANE_FLOAT

/// @code   int CALLBACK OnEditColorChange(COLORREF color, BOOL *pbHandled) @endcode
#define  XE_EDIT_COLOR_CHANGE        260
///@}
//@����}

//////////////////////////////////////////////////////////////////
//////////////////ö�ٶ���/////////////////////////////////////////
//////////////////////////////////////////////////////////////////

///  group_enum   ö������
/// @{

/// groupWindow_position ����λ��(window_position_)
/// @{
enum   window_position_
{
	//@����  ����
	window_position_error = -1,  ///<����
	//@����  ����
	window_position_top = 0,     ///<top
	//@����  �ײ�
	window_position_bottom,    ///<bottom
	//@����  ���
	window_position_left,      ///<left
	//@����  �ұ�
	window_position_right,     ///<right
	//@����  ������
	window_position_body,      ///<body
	//@����  ��������
	window_position_window,    ///<window ��������
};
///@}

/// groupElement_position UIԪ��λ��(element_position_)
/// @{
enum element_position_  //@����  UIԪ��λ��
{
	//@����  ��
	element_position_no = 0x00,     ///<��Ч
	//@����  ���
	element_position_left = 0x01,     ///<���
	//@����  �ϱ�
	element_position_top = 0x02,		///<�ϱ�
	//@����  �ұ�
	element_position_right = 0x04,		///<�ұ�
	//@����  �±�
	element_position_bottom = 0X08,		///<�±�
};
///@}

/// group_position λ�ñ�ʶ(element_position_)
/// @{
enum position_flag_  //@����  λ�ñ�ʶ
{
	//@����  ��
	position_flag_left,          ///<��
	//@����  ��
	position_flag_top,           ///<��
	//@����  ��
	position_flag_right,         ///<��
	//@����  ��
	position_flag_bottom,        ///<��
	//@����  ���Ͻ�
	position_flag_leftTop,       ///<���Ͻ�
	//@����  ���½�
	position_flag_leftBottom,    ///<���½�
	//@����  ���Ͻ�
	position_flag_rightTop,      ///<���Ͻ�
	//@����  ���½�
	position_flag_rightBottom,   ///<���½�
	//@����  ����
	position_flag_center,        ///<����
};
///@}

//͸������
/// groupWindowTransparent ����͸����ʶ(window_transparent_)
/// @{
enum  window_transparent_  //@����  ����͸����ʶ
{
	//@����  ��͸��
	window_transparent_false = 0,   ///<Ĭ�ϴ���,��͸��
	//@����  ͸��
	window_transparent_shaped,    ///<͸������,��͸��ͨ��,����
	//@����  ��Ӱ
	window_transparent_shadow,    ///<��Ӱ����,��͸��ͨ��,�߿���Ӱ,����͸�����͸��
	window_transparent_simple,    ///<͸������,����͸��ͨ��,ָ����͸����,ָ��͸��ɫ
	window_transparent_win7,      ///<WIN7��������,��ҪWIN7������Ч,��ǰδ����,��ǰδ����.
};
///@}


/// groupMenu �˵�(menu)
/// @{

///@name �˵����ʶ(menu_item_flag_)
///@{
enum   menu_item_flag_  //@����  �˵����ʶ
{
	//@����  ����
	menu_item_flag_normal = 0x00,   ///<����
	//@����  ѡ��
	menu_item_flag_select = 0x01,   ///<ѡ������ͣ��
	//@����  ͣ��
	menu_item_flag_stay = 0x01,   ///<ѡ������ͣ�� ���� menu_item_flag_select
	//@����  ��ѡ
	menu_item_flag_check = 0x02,   ///<��ѡ
	//@����  ����
	menu_item_flag_popup = 0x04,   ///<����
	//@����  �ָ���
	menu_item_flag_separator = 0x08,   ///<�ָ��� ID������,ID�ű�����
	//@����  ����
	menu_item_flag_disable = 0x10,   ///<����
};
///@}

///@name �˵���������(menu_popup_position_)
///@{
enum  menu_popup_position_  //@����  �˵���������
{
	//@����  ���Ͻ�
	menu_popup_position_left_top = 0,      ///<���Ͻ�
	//@����  ���½�
	menu_popup_position_left_bottom,     ///<���½�
	//@����  ���Ͻ�
	menu_popup_position_right_top,       ///<���Ͻ�
	//@����  ���½�
	menu_popup_position_right_bottom,    ///<���½�
	//@����  �����
	menu_popup_position_center_left,     ///<�����
	//@����  �Ͼ���
	menu_popup_position_center_top,      ///<�Ͼ���
	//@����  �Ҿ���
	menu_popup_position_center_right,    ///<�Ҿ���
	//@����  �¾���
	menu_popup_position_center_bottom,   ///<�¾���
};
///@}
///@}

/// groupImageDrawType ͼƬ��������(image_draw_type_)
/// @{
enum  image_draw_type_  //@����  ͼƬ��������
{
	//@����  Ĭ��
	image_draw_type_default = 0,     ///<Ĭ��
	//@����  ����
	image_draw_type_stretch,       ///<����
	//@����  �Ź���
	image_draw_type_adaptive,      ///<����Ӧ,�Ź���
	//@����  ƽ��
	image_draw_type_tile,          ///<ƽ��
	//@����  �̶�����
	image_draw_type_fixed_ratio,   ///<�̶�����,��ͼƬ������ʾ��Χʱ,����ԭʼ����ѹ����ʾͼƬ
	//@����  �Ź�����Χ
	image_draw_type_adaptive_border,  ///<�Ź��񲻻����м�����
};
///@}


//״̬--------------------------------------
/// groupCommonState3 ��ͨ����״̬(common_state3_)
/// @{
enum  common_state3_   //@����  ��ͨ����״̬
{
	//@����  �뿪
	common_state3_leave = 0,  ///<�뿪
	//@����  ͣ��
	common_state3_stay,     ///<ͣ��
	//@����  ����
	common_state3_down,     ///<����
};

///@}

/// groupButtonState ��ť״̬(button_state_)
/// @{
enum  button_state_  //@����  ��ť״̬
{
	//@����  �뿪
	button_state_leave = 0,   ///<�뿪״̬
	//@����  ͣ��
	button_state_stay,      ///<ͣ��״̬
	//@����  ����
	button_state_down,      ///<����״̬
	//@����  ѡ��
	button_state_check,     ///<ѡ��״̬
	//@����  ����
	button_state_disable,   ///<����״̬
};
///@}

///   groupComboBoxState ��Ͽ�״̬(comboBox_state_)
/// @{
enum  comboBox_state_ //@����  ��Ͽ�״̬
{
	//@����  ����뿪
	comboBox_state_leave = 0,   ///<����뿪״̬
	//@����  ���ͣ��
	comboBox_state_stay = 1,    ///<���ͣ��״̬
	//@����  ����
	comboBox_state_down = 2,    ///<����״̬
};
///@}

/// groupListItemState �б���״̬(list_item_state_)
/// ������(�б�,�б��,�б���ͼ)
/// @{
enum  list_item_state_   //@����  �б���״̬
{
	//@����  ����뿪
	list_item_state_leave = 0,   ///<������뿪״̬
	//@����  ���ͣ��
	list_item_state_stay = 1,    ///<�����ͣ��״̬
	//@����  ��ѡ��
	list_item_state_select = 2,  ///<��ѡ��״̬
	//@����  �������
	list_item_state_cache = 3,   ///<�������
};
///@}


/// groupTreeItemState  �б�����״̬(tree_item_state_)
/// @{
enum  tree_item_state_  //@����  �б�����״̬
{
	//@����  ����뿪
	tree_item_state_leave = 0,   ///<������뿪״̬
	//@����  ���ͣ��
	tree_item_state_stay = 1,    ///<�����ͣ��״̬
	//@����  ��ѡ��
	tree_item_state_select = 2,  ///<��ѡ��״̬
};
///@}


//��ťͼ����뷽ʽ
/// groupButtonIconAlign ��ťͼ����뷽ʽ(button_icon_align_)
/// @{
enum  button_icon_align_  //@����  ��ťͼ����뷽ʽ
{
	//@����  ���
	button_icon_align_left = 0,  ///<ͼ�������
	//@����  ����
	button_icon_align_top,     ///<ͼ���ڶ���
	//@����  �ұ�
	button_icon_align_right,   ///<ͼ�����ұ�
	//@����  �ײ�
	button_icon_align_bottom,  ///<ͼ���ڵײ�
};
///@}

///  groupListDrawItemBkFlag  ������Ʊ�ʶ(List,ListBox,ListView,Tree)
/// @{
enum  list_drawItemBk_flag_  //@����  ������Ʊ�ʶ
{
	//@����  ������
	list_drawItemBk_flag_nothing = 0x000,     ///<������
	//@����  ����뿪
	list_drawItemBk_flag_leave = 0x001,       ///<��������뿪״̬���
	//@����  ���ͣ��
	list_drawItemBk_flag_stay = 0x002,        ///<�������ͣ��״̬���
	//@����  ��ѡ��
	list_drawItemBk_flag_select = 0x004,      ///<�������ѡ��״̬���
	//@����  ������뿪
	list_drawItemBk_flag_group_leave = 0x008, ///<��������뿪״̬�鱳��,����Ϊ��ʱ
	//@����  �����ͣ��
	list_drawItemBk_flag_group_stay = 0x010,  ///<�������ͣ��״̬�鱳��,����Ϊ��ʱ
	//@����  ˮƽ�ָ���
	list_drawItemBk_flag_line = 0x020,        ///<�б����ˮƽ�ָ���
	//@����  ��ֱ�ָ���
	list_drawItemBk_flag_lineV = 0x040,        ///<�б���ƴ�ֱ�ָ���

};
/// @}


//������Ϣ������
/// groupMessageBox ������Ϣ��(messageBox_flag_)
/// @{
enum  messageBox_flag_  //@����  ������Ϣ���ʶ
{
	//@����  ����
	messageBox_flag_other = 0x00,    ///<����
	//@����  ȷ����ť
	messageBox_flag_ok = 0x01,    ///<ȷ����ť
	//@����  ȡ����ť
	messageBox_flag_cancel = 0x02,    ///<ȡ����ť
	//@����  ͼ��Ӧ�ó���
	messageBox_flag_icon_appicon = 0x01000,  ///<ͼ�� Ӧ�ó���  IDI_APPLICATION
	//@����  ͼ����Ϣ
	messageBox_flag_icon_info = 0x02000,  ///<ͼ�� ��Ϣ     IDI_ASTERISK
	//@����  ͼ����ѯ
	messageBox_flag_icon_qustion = 0x04000,  ///<ͼ�� ��ѯ/����/����   IDI_QUESTION
	//@����  ͼ�����
	messageBox_flag_icon_error = 0x08000,  ///<ͼ�� ����/�ܾ�/��ֹ  IDI_ERROR
	//@����  ͼ�꾯��
	messageBox_flag_icon_warning = 0x10000,  ///<ͼ�� ����       IDI_WARNING
	//@����  ͼ�갲ȫ
	messageBox_flag_icon_shield = 0x20000,  ///<ͼ�� ����/��ȫ   IDI_SHIELD
};
///@}

///GroupPropertyGrid_item_type    ��������������(propertyGrid_item_type_)
///@{
enum  propertyGrid_item_type_  //@����  ��������������
{
	//@����  �ı�
	propertyGrid_item_type_text = 0,      ///<Ĭ��,�ַ�������
	//@����  �༭��
	propertyGrid_item_type_edit,        ///<�༭��
	//@����  ��ɫѡ��
	propertyGrid_item_type_edit_color,  ///<��ɫѡ��Ԫ��
	//@����  �ļ�ѡ��
	propertyGrid_item_type_edit_file,   ///<�ļ�ѡ��༭��
	//@����  ����
	propertyGrid_item_type_edit_set,    ///<����
	//@����  ��Ͽ�
	propertyGrid_item_type_comboBox,    ///<��Ͽ�
	//@����  ��
	propertyGrid_item_type_group,       ///<��
	//@����  ���
	propertyGrid_item_type_panel,       ///<���
};
///@}

/// GroupZOrder    Z��λ��(zorder_)
///@{
enum zorder_  //@����  Z��λ��
{
	//@����  ������
	zorder_top,    ///<������
	//@����  ������
	zorder_bottom, ///<������
	//@����  ָ��Ŀ������
	zorder_before, ///<ָ��Ŀ������
	//@����  ָ��Ŀ������
	zorder_after,  ///<ָ��Ŀ������
};
///@}

///Group_pane_align_  �������(pane_align_)
///@{
enum pane_align_  //@����  �������
{
	//@����  ����
	pane_align_error = -1,  ///<����
	//@����  ���
	pane_align_left = 0,    ///<���
	//@����  ����
	pane_align_top,       ///<����
	//@����  �Ҳ�
	pane_align_right,     ///<�Ҳ�
	//@����  �ײ�
	pane_align_bottom,    ///<�ײ�
	//@����  ����
	pane_align_center,    ///<����
};
///@}

///Group_layout_align_  ���ֶ���(layout_align_)
///@{
enum  layout_align_  //@����  ���ֶ���
{
	//@����  ���
	layout_align_left = 0,		  ///<���
	//@����  ����
	layout_align_top,			  ///<����
	//@����  �Ҳ�
	layout_align_right,			  ///<�Ҳ�
	//@����  �ײ�
	layout_align_bottom,		  ///<�ײ�
	//@����  ����
	layout_align_center,		  ///<����
	//@����  �Ⱦ�
	layout_align_equidistant,     ///<�Ⱦ�
};
///@}

/// groupLayoutSize ���ִ�С����(layout_size_)
/// @{
enum  layout_size_ //@����  ���ִ�С����
{
	//@����  �̶�
	layout_size_fixed = 0, ///<�̶���С
	//@����  ��丸
	layout_size_fill,      ///<fill ��丸
	//@����  �Զ�
	layout_size_auto,      ///<auto �Զ���С,�������ݼ����С
	//@����  ����
	layout_size_weight,    ///<weight ����,���ձ�������ʣ��ռ�
	//@����  �ٷֱ�
	layout_size_percent,   ///<�ٷֱ�
	//@����  ����
	layout_size_disable,   ///<disable ��ʹ��
};
///@}

///  groupLayoutAlignAxis ���������(layout_align_axis_)
/// @{
enum layout_align_axis_  //@����  ���������
{
	//@����  ��
	layout_align_axis_auto = 0,   ///<��
	//@����  ��ʼ
	layout_align_axis_start,      ///<ˮƽ����(����), ��ֱ����(���)
	//@����  ����
	layout_align_axis_center,     ///<����
	//@����  ĩβ
	layout_align_axis_end,        ///<ˮƽ����(�ײ�), ��ֱ����(�Ҳ�)
};
///@}

///Group_edit_textAlign_flag_  �༭���ı�����(edit_textAlign_flag_)
///@{
enum edit_textAlign_flag_ //@����  �༭���ı�����
{
	//@����  ���
	edit_textAlign_flag_left = 0x0,   ///<���
	//@����  �Ҳ�
	edit_textAlign_flag_right = 0x1,   ///<�Ҳ�
	//@����  ˮƽ����
	edit_textAlign_flag_center = 0x2,   ///<ˮƽ����
	//@����  ����
	edit_textAlign_flag_top = 0x0,  ///<����
	//@����  �ײ�
	edit_textAlign_flag_bottom = 0x4,  ///<�ײ�
	//@����  ��ֱ����
	edit_textAlign_flag_center_v = 0x8,  ///<��ֱ����
};
///@}

///Group_pane_state_  ����״̬(pane_state_)
///@{
enum pane_state_  //@����   ����״̬
{
	//@����  ����
	pane_state_error = -1,
	//@����  ����
	pane_state_any = 0,
	//@����  ����
	pane_state_lock,   ///<����
	//@����  ͣ����ͷ
	pane_state_dock,   ///<ͣ����ͷ
	//@����  ��������
	pane_state_float,  ///<��������
};
///@}

///Group_textFormatFlag_    �ı�����(textFormatFlag_)
///@{
enum  textFormatFlag_  //@����   �ı�����
{
	//@����  �����
	textAlignFlag_left = 0,      ///<�����
	//@����  ������
	textAlignFlag_top = 0,      ///<��ֱ������
	textAlignFlag_left_top = 0x4000, ///<�ڲ�����
	//@����  ˮƽ����
	textAlignFlag_center = 0x1,    ///<ˮƽ����
	//@����  �Ҷ���
	textAlignFlag_right = 0x2,    ///<�Ҷ���.
	//@����  ��ֱ����
	textAlignFlag_vcenter = 0x4,    ///<��ֱ����
	//@����  �׶���
	textAlignFlag_bottom = 0x8,    ///<��ֱ�׶���
	//@����  ��������
	textFormatFlag_DirectionRightToLeft = 0x10,   ///<��������˳����ʾ�ı�
	//@����  ��ֹ����
	textFormatFlag_NoWrap = 0x20,   ///<��ֹ����
	//@����  ��ֱ��ʾ
	textFormatFlag_DirectionVertical = 0x40,   ///<��ֱ��ʾ�ı�
	//@����  ��������
	textFormatFlag_NoFitBlackBox = 0x80,   ///<�������ַ�������ַ����Ĳ��־��Ρ�Ĭ������£������¶�λ�ַ��Ա����κ�����
	//@����  ��ʾ�����ַ�
	textFormatFlag_DisplayFormatControl = 0x100,  ///<�����ַ���������ұ�ǣ�����д����Եı�־����һ����ʾ������С�
	//@����  ��ֹ��������
	textFormatFlag_NoFontFallback = 0x200,      ///<��������������в�֧�ֵ��ַ������û��˵���ѡ���塣ȱʧ���κ��ַ�����ȱʧ��־���ŵ�������ʾ��ͨ����һ���յķ���
	//@����  ���������ո�
	textFormatFlag_MeasureTrailingSpaces = 0x400, ///<����ÿһ�н�β����β��ո���Ĭ������£�MeasureString �������صı߿򶼽��ų�ÿһ�н�β���Ŀո����ô˱���Ա��ڲⶨʱ���ո������ȥ
	//@����  ��ֹ�����и�
	textFormatFlag_LineLimit = 0x800,      ///<���������ʾ�߶Ȳ���һ��,��ô����ʾ
	//@����  ��ֹ�ü�
	textFormatFlag_NoClip = 0x1000,     ///<������ʾ��־���ŵ�������ֺ����쵽�߿����δ�����ı�����Ĭ������£����쵽�߿����������ı��ͱ�־���Ų��ֶ�������

	//textTrimming_None              = 0,        ///<��ʹ��ȥβ
	//@����  ���ַ�Ϊ��λȥβ
	textTrimming_Character = 0x40000,  ///<���ַ�Ϊ��λȥβ
	//@����  �Ե���Ϊ��λȥβ
	textTrimming_Word = 0x80000,  ///<�Ե���Ϊ��λȥβ
	//@����  ���ַ�Ϊ��λȥβ��ʡ�Ժ�
	textTrimming_EllipsisCharacter = 0x8000,   ///<���ַ�Ϊ��λȥβ,ʡ�Բ���ʹ�����Ժű�ʾ
	//@����  �Ե���Ϊ��λȥβ��ʡ�Ժ�
	textTrimming_EllipsisWord = 0x10000,  ///<�Ե���Ϊ��λȥβ,ʡ�Բ���ʹ��ʡ�Ժű�ʾ
	//@����  ʡ���м䲿��
	textTrimming_EllipsisPath = 0x20000,  ///<��ȥ�ַ����м䲿�֣���֤�ַ�����β���ܹ���ʾ
};
///@}

///Group_textFormatFlag_dwrite_    D2D�ı���Ⱦģʽ(XC_DWRITE_RENDERING_MODE)
///@{
enum XC_DWRITE_RENDERING_MODE  //@���� D2D�ı���Ⱦģʽ
{
	//@����  Ĭ��
	XC_DWRITE_RENDERING_MODE_DEFAULT = 0,                    ///<ָ����������ʹ�С�Զ�ȷ������ģʽ��
	//@����  �������
	XC_DWRITE_RENDERING_MODE_ALIASED,						 ///<ָ����ִ�п���ݡ� ÿ������Ҫô����Ϊ�ı���ǰ��ɫ��Ҫô������������ɫ��
	//@����  CLEARTYPE_GDI_CLASSIC
	XC_DWRITE_RENDERING_MODE_CLEARTYPE_GDI_CLASSIC,			 ///<ʹ��������ı���ͬ�Ķ���ָ�� ClearType ���֡� ����ֻ�ܶ�λ���������صı߽��ϡ�
	//@����  CLEARTYPE_GDI_NATURAL
	XC_DWRITE_RENDERING_MODE_CLEARTYPE_GDI_NATURAL,			 ///<ʹ��ʹ�� CLEARTYPE_NATURAL_QUALITY ���������壬ʹ����ʹ�� GDI ���ı�������ͬ��ָ��ָ�� ClearType ���֡� ��ʹ�ñ����ı���ȣ����ζ������ӽ�������ֵ����������Ȼλ���������صı߽��ϡ�
	//@����  CLEARTYPE_NATURAL
	XC_DWRITE_RENDERING_MODE_CLEARTYPE_NATURAL,				 ///<����ˮƽά����ָ�����п���ݹ��ܵ� ClearType ��Ⱦ����ͨ��������С�����С����� 16 ppem����
	//@����  CLEARTYPE_NATURAL_SYMMETRIC
	XC_DWRITE_RENDERING_MODE_CLEARTYPE_NATURAL_SYMMETRIC,	 ///<ָ����ˮƽ�ʹ�ֱά���Ͼ��п���ݵ� ClearType ��Ⱦ����ͨ�����ڽϴ�ĳߴ磬��ʹ���ߺͶԽ��߿�������ƽ������������һЩ��Ͷȡ�
	//@����  OUTLINE
	XC_DWRITE_RENDERING_MODE_OUTLINE,                        ///<ָ����ȾӦ�ƹ���դ������ֱ��ʹ�������� ��ͨ�����ڷǳ���ĳߴ硣
};
///@}

///Group_listItemTemp_type_    �б���ģ������(listItemTemp_type_)
///@{
enum listItemTemp_type_  //@���� �б���ģ������
{
	//@���� �б���
	listItemTemp_type_tree = 0x01,					  ///<tree
	//@���� �б��
	listItemTemp_type_listBox = 0x02,				  ///<listBox
	//@���� �б�ͷ
	listItemTemp_type_list_head = 0x04,				  ///<list �б�ͷ
	//@���� �б���
	listItemTemp_type_list_item = 0x08,				  ///<list �б���
	//@���� �б���ͼ��
	listItemTemp_type_listView_group = 0x10,		  ///<listView �б�����
	//@���� �б���ͼ��
	listItemTemp_type_listView_item = 0x20,			  ///<listView �б�����
	//@���� �б�ͷ���б���
	listItemTemp_type_list = listItemTemp_type_list_head | listItemTemp_type_list_item,  ///<list (�б�ͷ)��(�б���)���
	//@���� �б���ͼ�����
	listItemTemp_type_listView = listItemTemp_type_listView_group | listItemTemp_type_listView_item, ///<listView (�б�����)��(�б�����)���
};
///@}

///Group_adjustLayout    �������ֱ�ʶλ(adjustLayout_)
///@{
enum adjustLayout_  //@���� �������ֱ�ʶ
{
	//@����  ������
	adjustLayout_no = 0x00,  ///<����������
	//@����  ǿ�Ƶ���ȫ��
	adjustLayout_all = 0x01,  ///<ǿ�Ƶ���������Ӷ��󲼾�.
	//@����  ֻ��������
	adjustLayout_self = 0x02,  ///<ֻ����������,�������Ӷ��󲼾�.
	//xc_adjustLayout_free = 0x03   ��������,��ǿ����, ֻ��������ı�Ķ���
};
///@}

/// group_edit_macro �༭������(edit_type_)
/// @{
enum edit_type_     //@���� �༭������
{
	//@����  ��ͨ
	edit_type_none = 0,   ///<��ͨ�༭��,   ÿ�еĸ߶���ͬ
	//@����  ����
	edit_type_editor,     ///<����༭��,   ÿ�еĸ߶���ͬ,  ���ܼ̳���ͨ�༭��
	//@����  ���ı�
	edit_type_richedit,   ///<���ı��༭��,  ÿ�еĸ߶ȿ��ܲ�ͬ
	//@����  ��������
	edit_type_chat,       ///<��������,     ÿ�еĸ߶ȿ��ܲ�ͬ, ���ܼ̳и��ı��༭��
	//@����  ������
	edit_type_codeTable,  ///<������,�ڲ�ʹ��,  ÿ�еĸ߶���ͬ, δʹ��
};

enum edit_style_type_  //@���� �༭����ʽ����
{
	//@����  ����
	edit_style_type_font_color = 1,  ///<����
	//@����  ͼƬ
	edit_style_type_image,           ///<ͼƬ
	//@����  UI����
	edit_style_type_obj,             ///<UI����
};

///Edit ���������б�ʶ
enum chat_flag_  //@����  ���������б�ʶ
{
	//@����  ���
	chat_flag_left = 0x1,    ///<���
	//@����  �Ҳ�
	chat_flag_right = 0x2,   ///<�Ҳ�
	//@����  �м�
	chat_flag_center = 0x4,  ///<�м�
	//@����  ��һ����ʾ����
	chat_flag_next_row_bubble = 0x8,     ///<��һ����ʾ����
	//@����  ������
	chat_flag_chat = 0x10000, ///<����(������)
};
///@}

/// group_table  ��״����ʶ(table_flag_)
/// @{

///@name  ��״����ʶ(table_flag_)
///@{
enum  table_flag_  //@����  ��״����ʶ
{
	//@����  ����
	table_flag_full = 0,   ///<������ϵ�Ԫ��
	//@����  ����
	table_flag_none,       ///<������С��Ԫ��
};
///@}

///@name  ��״����߱�ʶ(table_line_flag_)
///@{
enum  table_line_flag_   //@����  ��״����߱�ʶ
{
	table_line_flag_left = 0x1,   ///<������
	table_line_flag_top = 0x2,   ///<������
	table_line_flag_right = 0x4,	 ///<������
	table_line_flag_bottom = 0x8,   ///<������
	table_line_flag_left2 = 0x10,  ///<������
	table_line_flag_top2 = 0x20,	 ///<������
	table_line_flag_right2 = 0x40,	 ///<������
	table_line_flag_bottom2 = 0x80,	 ///<������
};
///@}
///@}

/// group_monthCal_button_type_    ����Ԫ���ϵİ�ť����(monthCal_button_type_)
/// @{
enum monthCal_button_type_  //@���� ������Ƭ��ť����
{
	//@����  ����
	monthCal_button_type_today = 0,  ///< ���찴ť
	//@����  ��һ��
	monthCal_button_type_last_year,  ///< ��һ��
	//@����  ��һ��
	monthCal_button_type_next_year,  ///< ��һ��
	//@����  ��һ��
	monthCal_button_type_last_month, ///< ��һ��
	//@����  ��һ��
	monthCal_button_type_next_month, ///< ��һ��
};
///@}

/// group_fontStyle_  ������ʽ(fontStyle_)
///@{
enum fontStyle_  //@���� ������ʽ
{
	//@����  ����
	fontStyle_regular = 0,     ///<����
	//@����  ����
	fontStyle_bold = 1,        ///<����
	//@����  б��
	fontStyle_italic = 2,      ///<б��
	//@����  ��б��
	fontStyle_boldItalic = 3,  ///<��б��
	//@����  �»���
	fontStyle_underline = 4,   ///<�»���
	//@����  ɾ����
	fontStyle_strikeout = 8    ///<ɾ����
};
///@}

/// group_adapter_date_type_    ������������������(adapter_date_type_)
///@{
enum  adapter_date_type_  //@����  ������������������
{
	//@����  ����
	adapter_date_type_error = -1,
	//@����  ����
	adapter_date_type_int = 0,     ///<����
	//@����  ������
	adapter_date_type_float = 1,   ///<������
	//@����  �ַ���
	adapter_date_type_string = 2,  ///<�ַ���
	//@����  ͼƬ
	adapter_date_type_image = 3,   ///<ͼƬ
};
///@}

/// group_ease_type_  ��������(ease_type_)
/// @{
enum ease_type_ //@����  ��������
{
	//@����  ��������
	easeIn,      ///<��������
	//@����  �ӿ쵽��
	easeOut,     ///<�ӿ쵽��
	//@����  ���������ٵ���
	easeInOut,   ///<���������ٵ���
};
///@}

///  group_ease_flag_  ������ʶ(ease_flag_)
///@{
enum ease_flag_  //@����  ������ʶ
{
	//@����  ����
	ease_flag_linear,			///<����, ֱ��
	//@����  ���η�����
	ease_flag_quad,			    ///<���η�����
	//@����  ���η�����
	ease_flag_cubic,			///<���η�����, Բ��
	//@����  �Ĵη�����
	ease_flag_quart,			///<�Ĵη�����
	//@����  ��η�����
	ease_flag_quint,			///<��η�����
	//@����  ����
	ease_flag_sine,				///<����, ��ĩ�˱仯
	//@����  ͻ��
	ease_flag_expo,			    ///<ͻ��, ͻȻһ��
	//@����  Բ��
	ease_flag_circ,		        ///<Բ��, �ñ��ƹ�һ��Բ��
	//@����  ǿ���ص�
	ease_flag_elastic,		    ///<ǿ���ص�
	//@����  �ص�
	ease_flag_back,				///<�ص�, �Ƚϻ���
	//@����  ����
	ease_flag_bounce,		    ///<����, ģ��С����ص���
	//@����  ��������
	ease_flag_in = 0x010000, ///<��������
	//@����  �ӿ쵽��
	ease_flag_out = 0x020000, ///<�ӿ쵽��
	//@����  ���������ٵ���
	ease_flag_inOut = 0x030000, ///<���������ٵ���
};
///@}

///  group_notifyMsg_skin_  ֪ͨ��Ϣ���(notifyMsg_skin_)
///@{
enum  notifyMsg_skin_  //@���� ֪ͨ��Ϣ���
{
	//@����  Ĭ��
	notifyMsg_skin_no,         ///<Ĭ��
	//@����  �ɹ�
	notifyMsg_skin_success,    ///<�ɹ�
	//@����  ����
	notifyMsg_skin_warning,	   ///<����
	//@����  ��Ϣ
	notifyMsg_skin_message,	   ///<��Ϣ
	//@����  ����
	notifyMsg_skin_error,	   ///<����
};
///@}

///group_animation_move_  �����ƶ���ʶ(animation_move_)
///@{
enum animation_move_  //@���� �����ƶ���ʶ
{
	//@���� X���ƶ�
	animation_move_x = 0x01,   ///<X���ƶ�
	//@���� Y���ƶ�
	animation_move_y = 0x02,   ///<Y���ƶ�
};
///@}

///group_bkInfo_align_flag_  ����������뷽ʽ(bkObject_align_flag_)
///@{
enum bkObject_align_flag_  //@���� ����������뷽ʽ
{
	//@����  ��
	bkObject_align_flag_no = 0x000,    ///<��
	//@����  �����
	bkObject_align_flag_left = 0x001,    ///<�����, �����ô˱�ʶʱ,����(margin.left)���������; ��rightδ����ʱ,��ô����(margin.right)������;
	//@����  ������
	bkObject_align_flag_top = 0x002,    ///<������, �����ô˱�ʶʱ,����(margin.top)���������; ��bottomδ����ʱ,��ô����(margin.bottom)����߶�;
	//@����  �Ҷ���
	bkObject_align_flag_right = 0x004,    ///<�Ҷ���, �����ô˱�ʶʱ,����(margin.right)�����Ҳ���; ��leftδ����ʱ,��ô����(margin.left)������;
	//@����  �׶���
	bkObject_align_flag_bottom = 0x008,    ///<�׶���, �����ô˱�ʶʱ,����(margin.bottom)����ײ����; ��topδ����ʱ,��ô����(margin.top)����߶�;
	//@����  ˮƽ����
	bkObject_align_flag_center = 0x010,    ///<ˮƽ����, �����ô˱�ʶʱ,����(margin.left)������;
	//@����  ��ֱ����
	bkObject_align_flag_center_v = 0x020,  ///<��ֱ����, �����ô˱�ʶʱ,����(margin.top)����߶�;
};
///@}


///group_frameWnd_cell_type_  ��ܴ��ڵ�Ԫ������(frameWnd_cell_type_)
enum frameWnd_cell_type_  //@����  ��ܴ��ڵ�Ԫ������
{
	//@����  ��
	frameWnd_cell_type_no = 0,   ///<��
	//@����  ����
	frameWnd_cell_type_pane = 1,   ///<����
	//@����  ������
	frameWnd_cell_type_group = 2,   ///<������
	//@����  ����ͼ��
	frameWnd_cell_type_bodyView = 3,   ///<����ͼ��
	//@����  ���²���
	frameWnd_cell_type_top_bottom = 4,   ///<���²���
	//@����  ���Ҳ���
	frameWnd_cell_type_left_right = 5,   ///<���Ҳ���
};

///group_trayIcon_flag_  ����ͼ���ʶ(trayIcon_flag_)
enum  trayIcon_flag_   //@����   ����ͼ���ʶ
{
	//@����  ��ͼ��
	trayIcon_flag_icon_none = 0x0,      ///<��ͼ�� NIIF_NONE
	//@����  ��Ϣͼ��
	trayIcon_flag_icon_info = 0x1,     ///<��Ϣͼ�� NIIF_INFO
	//@����  ����ͼ��
	trayIcon_flag_icon_warning = 0x2,   ///<����ͼ�� NIIF_WARNING
	//@����  ����ͼ��
	trayIcon_flag_icon_error = 0x3,     ///<����ͼ�� NIIF_ERROR
	//@����  �û�ͼ��
	trayIcon_flag_icon_user = 0x4,      ///<�û�ָ����ͼ�� NIIF_USER
	//@����  ��ֹ��������
	trayIcon_flag_nosound = 0x10,       ///<��ֹ������������ NIIF_NOSOUND
};

///@}  //ö������

/////////////////////////////////////////////////////////////////////
//////////////�Ų����״̬/////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////

//@����{ ���״̬

/// group_combo_StateFlag  ���״̬
/// @{

///group_window_state_flag_  ����״̬(window_state_flag_)
///@{
enum  window_state_flag_  //@����  ����״̬
{
	//@����  ��
	window_state_flag_nothing = 0x0000,  ///<��
	//@����  ��������
	window_state_flag_leave = 0x0001,  ///<��������
	//@����  ������
	window_state_flag_body_leave = 0x0002,  ///<����-body
	//@����  ����
	window_state_flag_top_leave = 0x0004,  ///<����-top
	//@����  �ײ�
	window_state_flag_bottom_leave = 0x0008,  ///<����-bottom
	//@����  ���
	window_state_flag_left_leave = 0x0010,  ///<����-left
	//@����  �Ҳ�
	window_state_flag_right_leave = 0x0020,  ///<����-right
	//@����  ����������
	window_state_flag_layout_body = 0x20000000, ///<����������
};
///@}

/// group_element_state_flag_ Ԫ��״̬(element_state_flag_)
///@{
enum  element_state_flag_  //@����  Ԫ��״̬
{
	//@����  ��
	element_state_flag_nothing = window_state_flag_nothing,  ///<��
	//@����  ����
	element_state_flag_enable = 0x0001,  ///<����
	//@����  ����
	element_state_flag_disable = 0x0002,  ///<����
	//@����  ����
	element_state_flag_focus = 0x0004,  ///<����
	//@����  �޽���
	element_state_flag_focus_no = 0x0008,  ///<�޽���
	//@����  ������չ
	element_state_flag_focusEx = 0x40000000,  ///<��Ԫ�ػ��Ԫ�ص���Ԫ��ӵ�н���
	//@����  �޽�����չ
	element_state_flag_focusEx_no = 0x80000000,  ///<�޽���Ex
	//@����  ����������
	layout_state_flag_layout_body = window_state_flag_layout_body, ///<����������
	//@����  ����뿪
	element_state_flag_leave = 0x0010,
	//@����  ���ͣ��
	element_state_flag_stay = 0x0020,
	//@����  ��갴��
	element_state_flag_down = 0x0040,
};
///@}

/// group_button_state_flag_ ��ť״̬��ʶ(button_state_flag_)
///@{
enum  button_state_flag_  //@����  ��ť״̬��ʶ
{
	//@����  ����뿪
	button_state_flag_leave = element_state_flag_leave,  ///<����뿪
	//@����  ���ͣ��
	button_state_flag_stay = element_state_flag_stay,   ///<���ͣ��
	//@����  ��갴��
	button_state_flag_down = element_state_flag_down,   ///<��갴��
	//@����  ѡ��
	button_state_flag_check = 0x0080, ///<ѡ��
	//@����  δѡ��
	button_state_flag_check_no = 0x0100, ///<δѡ��
	//@����  ���ڻ�ԭ
	button_state_flag_WindowRestore = 0x0200, ///<���ڻ�ԭ
	//@����  �������
	button_state_flag_WindowMaximize = 0x0400, ///<�������
};
///@}

///  group_comboBox_state_flag_  ��Ͽ�״̬��ʶ(comboBox_state_flag_)
///@{
enum comboBox_state_flag_   //@����   ��Ͽ�״̬��ʶ
{
	//@����  ����뿪
	comboBox_state_flag_leave = element_state_flag_leave, ///<����뿪
	//@����  ���ͣ��
	comboBox_state_flag_stay = element_state_flag_stay,  ///<���ͣ��
	//@����  ��갴��
	comboBox_state_flag_down = element_state_flag_down,  ///<��갴��
};
///@}

///  group_listBox_state_flag_  �б��״̬(listBox_state_flag_)
///@{
enum listBox_state_flag_  //@����  �б��״̬
{
	//@����  ������뿪
	listBox_state_flag_item_leave = 0x0080, ///<������뿪
	//@����  �����ͣ��
	listBox_state_flag_item_stay = 0x0100, ///<�����ͣ��
	//@����  ��ѡ��
	listBox_state_flag_item_select = 0x0200, ///<��ѡ��
	//@����  ��δѡ��
	listBox_state_flag_item_select_no = 0x0400, ///<��δѡ��
};
///@}

///  group_list_state_flag_  �б�״̬(list_state_flag_)
///@{
enum list_state_flag_  //@����  �б�״̬
{
	//@����  ������뿪
	list_state_flag_item_leave = 0x0080, ///<������뿪
	//@����  �����ͣ��
	list_state_flag_item_stay = 0x0100, ///<�����ͣ��
	//@����  ��ѡ��
	list_state_flag_item_select = 0x0200, ///<��ѡ��
	//@����  ��δѡ��
	list_state_flag_item_select_no = 0x0400, ///<��δѡ��
};
///@}

///  group_listHeader_state_flag_  �б�ͷ״̬(listHeader_state_flag_)
///@{
enum listHeader_state_flag_  //@����  �б�ͷ״̬
{
	//@����  ������뿪
	listHeader_state_flag_item_leave = 0x0080, ///<������뿪
	//@����  �����ͣ��
	listHeader_state_flag_item_stay = 0x0100, ///<�����ͣ��
	//@����  ����갴��
	listHeader_state_flag_item_down = 0x0200, ///<����갴��
};
///@}

///  group_listView_state_flag_ �б���ͼ״̬(listView_state_flag_)
///@{
enum listView_state_flag_  //@����  �б���ͼ״̬
{
	//@����  ������뿪
	listView_state_flag_item_leave = 0x0080,  ///<������뿪
	//@����  �����ͣ��
	listView_state_flag_item_stay = 0x0100,  ///<�����ͣ��
	//@����  ��ѡ��
	listView_state_flag_item_select = 0x0200,  ///<��ѡ��
	//@����  ��δѡ��
	listView_state_flag_item_select_no = 0x0400,  ///<��δѡ��
	//@����  ������뿪
	listView_state_flag_group_leave = 0x0800,  ///<������뿪
	//@����  �����ͣ��
	listView_state_flag_group_stay = 0x1000,  ///<�����ͣ��
	//@����  ��ѡ��
	listView_state_flag_group_select = 0x2000,  ///<��ѡ��
	//@����  ��δѡ��
	listView_state_flag_group_select_no = 0x4000,  ///<��δѡ��
};
///@}

///  group_tree_state_flag_ �б���״̬(tree_state_flag_)
///@{
enum tree_state_flag_  //@����  �б���״̬
{
	//@����  ������뿪
	tree_state_flag_item_leave = 0x0080,  ///<������뿪
	//@����  �����ͣ��
	tree_state_flag_item_stay = 0x0100,  ///<�����ͣ��,����ֵ, ��δʹ��
	//@����  ��ѡ��
	tree_state_flag_item_select = 0x0200,  ///<��ѡ��
	//@����  ��δѡ��
	tree_state_flag_item_select_no = 0x0400,  ///<��δѡ��
	//@����  ��Ϊ��
	tree_state_flag_group = 0x0800,  ///<��Ϊ��
	//@����  �Ϊ��
	tree_state_flag_group_no = 0x1000,  ///<�Ϊ��
};
///@}

///  group_monthCal_state_flag_  ������Ƭ״̬(monthCal_state_flag_)
///@{
enum monthCal_state_flag_     //@����  ������Ƭ״̬
{
	//@����  ����뿪
	monthCal_state_flag_leave = element_state_flag_leave,  ///<�뿪״̬
	//@����  ������뿪
	monthCal_state_flag_item_leave = 0x0080,     ///< ��-�뿪
	//@����  �����ͣ��
	monthCal_state_flag_item_stay = 0x0100,     ///< ��-ͣ��
	//@����  ����갴��
	monthCal_state_flag_item_down = 0x0200,     ///< ��-����
	//@����  ��ѡ��
	monthCal_state_flag_item_select = 0x0400,     ///< ��-ѡ��
	//@����  ��δѡ��
	monthCal_state_flag_item_select_no = 0x0800,     ///< ��-δѡ��
	//@����  �����
	monthCal_state_flag_item_today = 0x1000,     ///< ��-����
	//@����  ������
	monthCal_state_flag_item_last_month = 0x2000,     ///< ��-����
	//@����  ���
	monthCal_state_flag_item_cur_month = 0x4000,     ///< ��-����
	//@����  ������
	monthCal_state_flag_item_next_month = 0x8000,     ///< ��-����
};
///@}

///  group_propertyGrid_state_flag_  ��������״̬(propertyGrid_state_flag_)
///@{
enum propertyGrid_state_flag_      //@����   ��������״̬
{
	//@����  ������뿪
	propertyGrid_state_flag_item_leave = 0x0080,  	 ///<�뿪
	//@����  �����ͣ��
	propertyGrid_state_flag_item_stay = 0x0100,	 ///<ͣ��
	//@����  ��ѡ��
	propertyGrid_state_flag_item_select = 0x0200,	 ///<ѡ��
	//@����  ��δѡ��
	propertyGrid_state_flag_item_select_no = 0x0400,	 ///<δѡ��
	//@����  ������뿪
	propertyGrid_state_flag_group_leave = 0x0800,	 ///<���뿪
	//@����  ��չ��
	propertyGrid_state_flag_group_expand = 0x1000,	 ///<��չ��
	//@����  ��δչ��
	propertyGrid_state_flag_group_expand_no = 0x2000,	 ///<��δչ��
};
///@}

///  group_pane_state_flag_  ����״̬��ʶ(pane_state_flag_)
///@{
enum pane_state_flag_      //@����  ����״̬��ʶ
{
	//@����  ����뿪
	pane_state_flag_leave = element_state_flag_leave,  ///<�뿪
	//@����  ���ͣ��
	pane_state_flag_stay = element_state_flag_stay,   ///<ͣ��
	//@����  ������
	pane_state_flag_caption = 0x0080,  ///<����
	//@����  ������
	pane_state_flag_body = 0x0100,  ///<������
};
///@}

///  group_layout_state_flag_  ����״̬(layout_state_flag_)
///@{
enum layout_state_flag_  //@����  ����״̬
{
	//@����  ��
	layout_state_flag_nothing = window_state_flag_nothing,  ///<��
	//@����  ��������
	layout_state_flag_full = 0x0001,   ///<��������
	//@����  ������
	layout_state_flag_body = 0x0002,   ///<��������, �������ߴ�С
};
///@}
///@}

//@����} //���״̬

/////////////////////////////////////////////////////////////////////
//////////////����ṹ��///////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////

/// group_struct_  �ṹ�嶨��
///@{

typedef   struct lua_State lua_State;

struct  RECTF
{
	float  left;
	float  top;
	float  right;
	float  bottom;
};

//@���� �ߴ�С
struct borderSize_  //4���ߵĴ�С
{
	int  leftSize;   ///<��ߴ�С
	int  topSize;    ///<�ϱߴ�С
	int  rightSize;  ///<�ұߴ�С
	int  bottomSize; ///<�±ߴ�С
};
typedef  borderSize_  spaceSize_, paddingSize_, marginSize_;

///λ�õ�
struct  position_
{
	int  iRow;    ///<������
	int  iColumn; ///<������
};

///ListBox �б������Ϣ
//@����  �б����ṹ
struct listBox_item_
{
	//@����  ������
	int     index;      ///<������
	//@����  �û�����
	vint    nUserData;  ///<�û�������
	//@����  ��Ĭ�ϸ߶�
	int     nHeight;    ///<��Ĭ�ϸ߶�
	//@����  ��ѡ��ʱ�߶�
	int     nSelHeight; ///<��ѡ��ʱ�߶�
	//@����  ״̬
	list_item_state_  nState;  ///<״̬
	//@����  ������
	RECT    rcItem;     ///<������
	//@����  ����Ԫ��
	HELE    hLayout;    ///<����Ԫ��
	//@����  ��ģ��
	HTEMP   hTemp;      ///<�б���ģ��
};

///ListBox �б������Ϣ
//@����  �б������Ϣ�ṹ
struct listBox_item_info_
{
	//@����  �û�����
	vint    nUserData;  ///<�û�������
	//@����  ��Ĭ�ϸ߶�
	int     nHeight;    ///<��߶�, -1ʹ��Ĭ�ϸ߶�
	//@����  ��ѡ��ʱ�߶�
	int     nSelHeight; ///<��ѡ��ʱ�߶�, -1ʹ��Ĭ�ϸ߶�
};

///ListView �б�����ID
//@����  �б���ͼ��ID�ṹ
struct  listView_item_id_
{
	//@����  ������
	int  iGroup;   ///<������
	//@����  ������
	int  iItem;    ///<������
};

///List �б�����Ϣ
//@����  �б���ṹ
struct list_item_
{
	//@����  ������
	int     index;             ///<������
	//@����  ��������
	int     iSubItem;          ///<��������(������)
	//@����  �û�����
	vint    nUserData;         ///<�û�����
	//@����  ״̬
	list_item_state_  nState;  ///<״̬
	//@����  ������
	RECT    rcItem;     ///<δʹ��
	//@����  ����Ԫ��
	HELE    hLayout;    ///<����Ԫ��
	//@����  ��ģ��
	HTEMP   hTemp;      ///<�б���ģ��
};

///List �б�ͷ����Ϣ
//@���� �б�ͷ��ṹ
struct list_header_item_
{
	//@����  ������
	int     index;           ///<������
	//@����  �û�����
	vint    nUserData;       ///<�û�����
	//@����  �Ƿ�����
	BOOL    bSort;           ///<�Ƿ�֧������
	//@����  ����ʽ
	int     nSortType;       ///<����ʽ,0��Ч,1����,2����
	//@����  ����������������
	int     iColumnAdapter;  ///<��Ӧ�����������е�������
	//@����  ״̬
	common_state3_  nState;  ///<״̬
	//@����  ������
	RECT    rcItem;        ///<����
	//@����  ����Ԫ��
	HELE    hLayout;       ///<����Ԫ��
	//@����  ��ģ��
	HTEMP   hTemp;         ///<�б���ģ��
};

///Tree ������Ϣ
//@����  �б�����ṹ
struct tree_item_
{
	//@����  ��ID
	int     nID;				 ///<��ID
	//@����  ���
	int     nDepth;				 ///<���
	//@����  ��߶�
	int     nHeight;			 ///<��߶�
	//@����  ��ѡ��ʱ�߶�
	int     nSelHeight;			 ///<��ѡ��״̬�߶�
	//@����  �û�����
	vint    nUserData;			 ///<�û�����
	//@����  չ��
	BOOL    bExpand;			 ///<չ��
	//@����  ״̬
	tree_item_state_  nState;	 ///<״̬
	//@����  ������
	RECT    rcItem;              ///<����
	//@����  ����Ԫ��
	HELE    hLayout;             ///<����Ԫ��
	//@����  ��ģ��
	HTEMP   hTemp;               ///<�б���ģ��
};

///ListView �б�������Ϣ
//@����  �б���ͼ��ṹ
struct listView_item_
{
	//@����  ������
	int     iGroup;            ///<������������ -1û����
	//@����  ������
	int     iItem;             ///<��������λ������,�������Ϊ-1,��ôΪ��
	//@����  �û�����
	vint    nUserData;         ///<�û�������
	//@����  ״̬
	list_item_state_  nState;  ///<״̬
	//@����  ������
	RECT    rcItem;            ///<��������,�����߿�
	//@����  ����Ԫ��
	HELE    hLayout;           ///<����Ԫ��
	//@����  ��ģ��
	HTEMP   hTemp;             ///<�б���ģ��
};

/// group_menu_macro Menu�˵�
/// @{

///�˵�-����������Ϣ

//@����  �˵��������ڽṹ
struct  menu_popupWnd_
{
	//@����  ���ھ��
	HWINDOW hWindow;    ///<���ھ��
	//@����  ����ID
	int     nParentID;  ///<����ID
};

///�˵������Ի�ṹ
//@����  �˵��������ƽṹ
struct menu_drawBackground_
{
	//@����  �˵����
	HMENUX  hMenu;      ///<�˵����
	//@���� ���ھ��
	HWINDOW hWindow;    ///<��ǰ�˵���Ĵ��ھ��
	//@���� ����ID
	int     nParentID;  ///<����ID
};

///�˵����Ի�ṹ
//@���� �˵�����ƽṹ
struct  menu_drawItem_
{
	//@����  �˵����
	HMENUX     hMenu;       ///<�˵����
	//@����  ���ھ��
	HWINDOW    hWindow;     ///<��ǰ�˵���Ĵ��ھ��
	//@����  ��ID
	int        nID;         ///<ID
	//@����  ״̬
	int        nState;	    ///<״̬ @ref menu_item_flag_
	//@����  �Ҳ��ݼ�ռλ���
	int        nShortcutKeyWidth; ///<�Ҳ��ݼ�ռλ���
	//@����  ������
	RECT       rcItem;      ///<����
	//@����  ��ͼ��
	HIMAGE     hIcon;       ///<�˵���ͼ��
	//@����  �ı�
	const wchar_t* pText;   ///<�ı�
};
///@}

///��UIԪ���϶���
//@���� �б����϶���ṹ
struct tree_drag_item_
{
	//@����  �϶���ID
	int  nDragItem;  ///< �϶���ID
	//@����  Ŀ����ID
	int  nDestItem;  ///< Ŀ����ID
	//@����  Ŀ��λ��
	int  nType;      ///< ͣ�����Ŀ��λ��,0:(��)ͣ�ŵ�Ŀ�������, 1:(��)ͣ�ŵ�Ŀ�������, 3:(��)ͣ�ŵ�Ŀ��ĵ�����,
};

///������Ϣ
//@���� ������Ϣ�ṹ
struct  font_info_
{
	//@����  �����С
	int   nSize;                 ///<�����С,��λ(pt,��).
	//@����  ������ʽ
	int   nStyle;                ///<������ʽ fontStyle_
	//@����  ��������
	wchar_t  name[LF_FACESIZE];  ///<��������
};

///PGrid ������������Ϣ
//@���� ����������ṹ
struct propertyGrid_item_
{
	//@����  ����
	propertyGrid_item_type_ nType; ///<����
	//@����  ��ID
	int   nID;           ///<��ID
	//@����  ���
	int   nDepth;        ///<���
	//@����  �û�����
	vint  nUserData;     ///<�û�����
	//@����  �����п��
	int   nNameColWidth; ///<�����п��
	//@����  ������
	RECT  rcItem;        ///<����
	//@����  չ����ť����
	RECT  rcExpand;      ///<չ��
	//@����  �Ƿ�չ��
	BOOL  bExpand;       ///<�Ƿ�չ��
	//@����  �Ƿ�ɼ�
	BOOL  bShow;         ///<�Ƿ�ɼ�
};

///Edit ��ʽ��Ϣ
//@����  �༭����ʽ�ṹ
struct edit_style_info_
{
	//@����  ����
	USHORT   type;              ///<��ʽ����
	//@����  ���ü���
	USHORT   nRef;              ///<���ü���
	//@����  ���
	HXCGUI   hFont_image_obj;   ///<���(����,ͼƬ,UI����)
	//@����  ��ɫ
	COLORREF color;             ///<��ɫ
	//@����  �Ƿ�ʹ����ɫ
	BOOL     bColor;            ///<�Ƿ�ʹ����ɫ
};

///Edit ���ݸ���-��ʽ
//@����  �༭�����ݸ�����ʽ�ṹ
struct edit_data_copy_style_
{
	//@����  ���
	UINT     hFont_image_obj; ///<���(����,ͼƬ,UI����), ʹ��UINTĿ�ĵ�64λʱ���Խ�Լ4�ֽ��ڴ�
	//@����  ��ɫ
	COLORREF color;           ///<��ɫ
	//@����  �Ƿ�ʹ����ɫ
	BOOL     bColor;          ///<�Ƿ�ʹ����ɫ
};

///Edit ���ݸ���
//@����  �༭�����ݸ��ƽṹ
struct edit_data_copy_
{
	//@����  ��������
	int  nCount;       ///<��������
	//@����  ��ʽ����
	int  nStyleCount;  ///<��ʽ����
	//@����  ��ʽ����
	edit_data_copy_style_* pStyle; ///<��ʽ����
	//@����  ����
	UINT* pData;       ///<�������� ��λ2�ֽ�:��ʽ����, ��λ2�ֽ�:ֵ
};

///Editor ��ɫ��Ϣ
struct editor_color_
{
	BOOL       bAlignLineArrow;            ///<������ - �Ƿ���ʾ��ͷ
	BOOL       bAlignLineBtnIndent;        ///<չ��������ť, �ͷ�����
	BOOL       bTabFillColor;              ///<�������TAB����
	BOOL       bAlignLineColor7;           ///<�Ƿ�ʹ�òʺ���
	COLORREF   clrMargin1;                 ///<����� - ����ɫ1, ��ʾ�ϵ�
	COLORREF   clrMargin2;                 ///<����� - ����ɫ2, ��ʾ�к�
	COLORREF   clrMargin_text;             ///<����� - �ı�ɫ - �к���ɫ
	COLORREF   clrMargin_breakpoint;       ///<����� - �ϵ�ɫ
	COLORREF   clrMargin_breakpointBorder; ///<����� - �ϵ����ɫ
	COLORREF   clrMargin_runRowArrow;      ///<����� - ����λ�ü�ͷ
	COLORREF   clrMargin_curRow;           ///<����� - ��ǰ��ָʾɫ,���������
	COLORREF   clrMargin_error;            ///<����� - ����ָʾɫ

	COLORREF   clrCurRowFull;       ///<ͻ����ʾ��ǰ����ɫ
	COLORREF   clrMatchSel;         ///<ƥ��ѡ���ı�����ɫ
// 	COLORREF   clrAlignLine;        ///<������1
// 	COLORREF   clrAlignLine2;       ///<������2
// 	COLORREF   clrAlignLine3;       ///<������3
// 	COLORREF   clrAlignLine4;       ///<������4
// 	COLORREF   clrAlignLine5;       ///<������5
// 	COLORREF   clrAlignLine6;       ///<������6
// 	COLORREF   clrAlignLine7;       ///<������7

	COLORREF   clrAlignLines[7];    ///<�ʺ���

	COLORREF   clrAlignLineSel;     ///<������ - ѡ�����ݿ�
	COLORREF   clrFunSplitLine;     ///<�����ָ�����ɫ new

	COLORREF   clrIndentTab;        ///<����TAB
	COLORREF   clrIndentSpace;      ///<���������ո�

	int        funSplitLineMode;    ///<�����ָ���-���ģʽ: 0:��, 1:��, 2:���
	int        codeIndent;          ///<��������ģʽ(TAB)  ��������  �̶�����

	//ѡ���ı����� ͨ��API����
	//�������ɫ   ͨ��API����

	int       styleSys;             ///<ϵͳ�ؼ���  return, break, for
	int       styleFunction;        ///<����
	int       styleVar;             ///<����
	int       styleDataType;        ///<������������  int, byte, char
	int       styleClass;           ///<��  class
	int       styleMacro;           ///<��
	int       styleEnum;            ///<ö��   new
	int       styleNumber;          ///<����
	int       styleString;          ///<�ַ���
	int       styleComment;         ///<ע��
	int       StylePunctuation;     ///<������  new
};

/// ����Ԫ��������
struct monthCal_item_ //@����  ������Ƭ��ṹ
{
	//@����  ����
	int  nDay;     ///< ����
	//@����  ����
	int  nType;    ///< 1����,2����,3����
	//@����  ״̬
	int  nState;   ///< ���״̬ monthCal_state_flag_
	//@����  ������
	RECT rcItem;   ///< ������
};

///@}


//@����{

/// group_func_  �Ųʻص���������
///@{

typedef void (CALLBACK* funDebugError)(const char* pInfo);       //����ص�
typedef BOOL(CALLBACK* funLoadFile)(const wchar_t* pFileName);  //ͼƬ��Դ�ļ����ػص�
typedef void (CALLBACK* funCloudEvent)(const wchar_t* pFileName, int nEvent, HXCGUI hXCGUI);  //��UI�¼��ص�
typedef vint(CALLBACK* funCallUiThread)(vint data);
typedef void (CALLBACK* funIdle)();
typedef void (CALLBACK* funExit)();

///@brief �����ص�
///@param hAnimation �������л򶯻�����
///@param flag       ��ǰ����
typedef  void(CALLBACK* funAnimation)(HXCGUI hAnimation, int flag);

///@brief ������ص�
///@param hAnimation ��������
///@param pos        ��ǰ����(0.0f-1.0f)
typedef  void(CALLBACK* funAnimationItem)(HXCGUI hAnimation, float pos);

///@}

//@����}

///////////////////////////////////////////////////////////////////////////////////
/////////////�¼�ע��//////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////
//@����{  �¼�ע��
//@����{

class bind_event_cpp
{
public:
	virtual BOOL  Equal(bind_event_cpp* p) = 0;
	virtual void* GetThis() = 0;
	virtual void* GetMFun(int& nSize) = 0;
	virtual int HandleEvent(HELE hEle, BOOL bHnadle, UINT nEvent, WPARAM wParam, LPARAM lParam, BOOL* pbHandled) = 0;
	virtual int HandleEventWnd(HWINDOW hWindow, BOOL bHnadle, UINT nEvent, WPARAM wParam, LPARAM lParam, BOOL* pbHandled) = 0;
};

template<class R, class C, class F>
class bind_event_cpp_ : public bind_event_cpp
{
public:
	bind_event_cpp_(C* c, F f) :_c(c), _f(f) { }
	virtual BOOL Equal(bind_event_cpp* p) {
		if (_c != p->GetThis())
			return FALSE;
		int size = 0;
		void* f = p->GetMFun(size);
		if (sizeof(F) == size)
		{
			if (0 == memcmp(f, &_f, size))
				return TRUE;
		}
		return FALSE;
	}
	virtual void* GetThis() { return (void*)_c; }
	virtual void* GetMFun(int& nSize) { nSize = sizeof(F); return &_f; }

	template<class H, class A1>
	int CallT(int (C::* f)(A1), H hEleOrWindow, BOOL bHnadle, UINT nEvent, WPARAM wParam, LPARAM lParam, BOOL* pbHandled)
	{
		return (_c->*f)(pbHandled);
	}
	template<class H, class A1, class A2>
	int CallT(int (C::* f)(A1, A2), H hEleOrWindow, BOOL bHnadle, UINT nEvent, WPARAM wParam, LPARAM lParam, BOOL* pbHandled)
	{
		return (_c->*f)((A1)wParam, pbHandled);
	}
	template<class H, class A1, class A2, class A3>
	int CallT(int (C::* f)(A1, A2, A3), H hEleOrWindow, BOOL bHnadle, UINT nEvent, WPARAM wParam, LPARAM lParam, BOOL* pbHandled)
	{
		return (_c->*f)((A1)wParam, (A2)lParam, pbHandled);
	}
	template<class H, class A1, class A2, class A3, class A4>
	int CallT(int (C::* f)(A1, A2, A3, A4), H hEleOrWindow, BOOL bHnadle, UINT nEvent, WPARAM wParam, LPARAM lParam, BOOL* pbHandled)
	{
		return (_c->*f)((A1)nEvent, (A2)wParam, (A3)lParam, pbHandled);
	}
	template<class H, class A1, class A2, class A3, class A4, class A5>
	int CallT(int (C::* f)(A1, A2, A3, A4, A5), H hEleOrWindow, BOOL bHnadle, UINT nEvent, WPARAM wParam, LPARAM lParam, BOOL* pbHandled)
	{
		return (_c->*f)((A1)hEleOrWindow, (A2)nEvent, (A3)wParam, (A4)lParam, pbHandled);
	}
	virtual int HandleEvent(HELE hEle, BOOL bHnadle, UINT nEvent, WPARAM wParam, LPARAM lParam, BOOL* pbHandled)
	{
		return CallT(_f, hEle, bHnadle, nEvent, wParam, lParam, pbHandled);
	}
	virtual int HandleEventWnd(HWINDOW hWindow, BOOL bHnadle, UINT nEvent, WPARAM wParam, LPARAM lParam, BOOL* pbHandled)
	{
		return CallT(_f, hWindow, bHnadle, nEvent, wParam, lParam, pbHandled);
	}
	C* _c;
	F   _f;
};

template<class R, class C, class F>
class bind_event_cpp2_ : public bind_event_cpp
{
public:
	bind_event_cpp2_(C* c, F f) :_c(c), _f(f) { }
	virtual BOOL Equal(bind_event_cpp* p) {
		if (_c != p->GetThis())
			return FALSE;
		int size = 0;
		void* f = p->GetMFun(size);
		if (sizeof(F) == size)
		{
			if (0 == memcmp(f, &_f, size))
				return TRUE;
		}
		return FALSE;
	}
	virtual void* GetThis() { return (void*)_c; }
	virtual void* GetMFun(int& nSize) { nSize = sizeof(F); return &_f; }

	template<class H, class A1, class A2>
	int CallT(int (C::* f)(A1, A2), H hEleOrWindow, BOOL bHnadle, UINT nEvent, WPARAM wParam, LPARAM lParam, BOOL* pbHandled)
	{
		return (_c->*f)((A1)hEleOrWindow, pbHandled);
	}
	template<class H, class A1, class A2, class A3>
	int CallT(int (C::* f)(A1, A2, A3), H hEleOrWindow, BOOL bHnadle, UINT nEvent, WPARAM wParam, LPARAM lParam, BOOL* pbHandled)
	{
		return (_c->*f)((A1)hEleOrWindow, (A2)wParam, pbHandled);
	}
	template<class H, class A1, class A2, class A3, class A4>
	int CallT(int (C::* f)(A1, A2, A3, A4), H hEleOrWindow, BOOL bHnadle, UINT nEvent, WPARAM wParam, LPARAM lParam, BOOL* pbHandled)
	{
		return (_c->*f)((A1)hEleOrWindow, (A2)wParam, (A3)lParam, pbHandled);
	}
	template<class H, class A1, class A2, class A3, class A4, class A5>
	int CallT(int (C::* f)(A1, A2, A3, A4, A5), H hEleOrWindow, BOOL bHnadle, UINT nEvent, WPARAM wParam, LPARAM lParam, BOOL* pbHandled)
	{
		return (_c->*f)((A1)hEleOrWindow, (A2)nEvent, (A3)wParam, (A4)lParam, pbHandled);
	}
	virtual int HandleEvent(HELE hEle, BOOL bHnadle, UINT nEvent, WPARAM wParam, LPARAM lParam, BOOL* pbHandled)
	{
		return CallT(_f, hEle, bHnadle, nEvent, wParam, lParam, pbHandled);
	}
	virtual int HandleEventWnd(HWINDOW hWindow, BOOL bHnadle, UINT nEvent, WPARAM wParam, LPARAM lParam, BOOL* pbHandled)
	{
		return CallT(_f, hWindow, bHnadle, nEvent, wParam, lParam, pbHandled);
	}
	C* _c;
	F   _f;
};

struct bind_event_c
{
	enum MyEnum
	{
		event_c_ex,
		event_lua_ex,
		event_js_ex,
	} type;
	virtual ~bind_event_c() {}
	virtual BOOL Equal(bind_event_c* p) { return FALSE; };
	virtual void* GetFunc() { return NULL; };
	virtual int HandleEvent(HELE hEle, BOOL bHandle, UINT nEvent, WPARAM wParam, LPARAM lParam, BOOL* pbHandled) { return 0; };
	virtual int HandleEventWnd(HWINDOW hWindow, BOOL bHandle, UINT nEvent, WPARAM wParam, LPARAM lParam, BOOL* pbHandled) { return 0; };
};

struct xc_event
{
	xc_event(int regType, const char* pFunName) {
		m_pBind = 0;
		m_pFunC = 0;
		m_name[0] = 0;
		m_regType = regType;
		if (pFunName) strcpy_s(m_name, pFunName);
	}
	int            m_regType;     //ע������, 0:CPP,1:CPP1,2:CPP2
	bind_event_cpp* m_pBind;     //C++�¼��ص�
	bind_event_c* m_pFunC;     //C�¼��ص�
	char           m_name[MAX_PATH]; //������

	BOOL Equal(xc_event* pEvent) {
		if (m_pBind && pEvent->m_pBind)
			return (m_pBind->Equal(pEvent->m_pBind));
		if (m_pFunC && pEvent->m_pFunC)
			return (m_pFunC->Equal(pEvent->m_pFunC));
		return FALSE;
	}
	BOOL Equal(void* pFunc) {
		if (m_pFunC) {
			if (m_pFunC->GetFunc() == pFunc)
				return TRUE;
		}
		return FALSE;
	}
	int HandleEvent(HELE hEle, UINT nEvent, WPARAM wParam, LPARAM lParam, BOOL* pbHandled)
	{
		if (m_pBind)
			return m_pBind->HandleEvent(hEle, m_regType, nEvent, wParam, lParam, pbHandled);
		if (m_pFunC)
			return m_pFunC->HandleEvent(hEle, m_regType, nEvent, wParam, lParam, pbHandled);
		MessageBoxA(NULL, "HandleEvent()", "error", 0);
		return 0;
	}
	int HandleEventWnd(HWINDOW hWindow, UINT nEvent, WPARAM wParam, LPARAM lParam, BOOL* pbHandled)
	{
		if (m_pBind)
			return m_pBind->HandleEventWnd(hWindow, m_regType, nEvent, wParam, lParam, pbHandled);
		if (m_pFunC)
			return m_pFunC->HandleEventWnd(hWindow, m_regType, nEvent, wParam, lParam, pbHandled);
		MessageBoxA(NULL, "HandleEventWnd()", "error", 0);
		return 0;
	}
	char* GetFunName() { return m_name; }
	virtual void Release() {
		if (m_pBind) delete m_pBind;
		if (m_pFunC) delete m_pFunC;
		delete this;
	}
};

XC_API BOOL WINAPI _XWnd_RegEvent(HWINDOW hWindow, UINT nEvent, xc_event* pEvent);
XC_API BOOL WINAPI _XWnd_RemoveEvent(HWINDOW hWindow, UINT nEvent, xc_event* pEvent);
XC_API BOOL WINAPI _XEle_RegEvent(HELE hEle, UINT nEvent, xc_event* pEvent);
XC_API BOOL WINAPI _XEle_RemoveEvent(HELE hEle, UINT nEvent, xc_event* pEvent);


static BOOL XEle_RegEventCPP_tt2(HXCGUI hXCGUI, UINT nEvent, int retType, BOOL bEle, xc_event* pEvent) {
	BOOL bResult = TRUE;
	if (-1 == retType) {
		if (bEle)
			bResult = _XEle_RemoveEvent((HELE)hXCGUI, nEvent, pEvent);
		else
			bResult = _XWnd_RemoveEvent((HWINDOW)hXCGUI, nEvent, pEvent);
		pEvent->Release();
	} else if (bEle) {
		if (FALSE == _XEle_RegEvent((HELE)hXCGUI, nEvent, pEvent))
		{
			pEvent->Release();
			bResult = FALSE;
		}
	} else if (FALSE == _XWnd_RegEvent((HWINDOW)hXCGUI, nEvent, pEvent)) {
		pEvent->Release();
		bResult = FALSE;
	}
	return bResult;
}

template<class R, class C, class F>
BOOL XEle_RegEventCPP_tt(HXCGUI hXCGUI, UINT nEvent, int retType, BOOL bEle, C* c, F f, const char* pFunName) {
	xc_event* pEvent = new xc_event(retType, pFunName);
	pEvent->m_pBind = new bind_event_cpp_<R, C, F>(c, f);
	return XEle_RegEventCPP_tt2(hXCGUI, nEvent, retType, bEle, pEvent);
}

template<class R, class C, class F>
BOOL XEle_RegEventCPP1_tt(HXCGUI hXCGUI, UINT nEvent, int retType, BOOL bEle, C* c, F f, const char* pFunName) {
	xc_event* pEvent = new xc_event(retType, pFunName);
	pEvent->m_pBind = new bind_event_cpp2_<R, C, F>(c, f);
	return XEle_RegEventCPP_tt2(hXCGUI, nEvent, retType, bEle, pEvent);
}

template<class R, class C, class CT, class A1>
BOOL XCGUI_RegEventCPP_t(HXCGUI hXCGUI, UINT nEvent, int regType, BOOL bEle, C* c, R(CT::* f)(A1), const char* pFunName) {
	return XEle_RegEventCPP_tt<R>(hXCGUI, nEvent, regType, bEle, (CT*)c, f, pFunName);
}

template<class R, class C, class CT, class A1, class A2>
BOOL XCGUI_RegEventCPP_t(HXCGUI hXCGUI, UINT nEvent, int regType, BOOL bEle, C* c, R(CT::* f)(A1, A2), const char* pFunName) {
	return XEle_RegEventCPP_tt<R>(hXCGUI, nEvent, regType, bEle, (CT*)c, f, pFunName);
}

template<class R, class C, class CT, class A1, class A2, class A3>
BOOL XCGUI_RegEventCPP_t(HXCGUI hXCGUI, UINT nEvent, int regType, BOOL bEle, C* c, R(CT::* f)(A1, A2, A3), const char* pFunName) {
	return XEle_RegEventCPP_tt<R>(hXCGUI, nEvent, regType, bEle, (CT*)c, f, pFunName);
}

template<class R, class C, class CT, class A1, class A2, class A3, class A4>
BOOL XCGUI_RegEventCPP_t(HXCGUI hXCGUI, UINT nEvent, int regType, BOOL bEle, C* c, R(CT::* f)(A1, A2, A3, A4), const char* pFunName) {
	return XEle_RegEventCPP_tt<R>(hXCGUI, nEvent, regType, bEle, (CT*)c, f, pFunName);
}

template<class R, class C, class CT, class A1, class A2, class A3, class A4, class A5>
BOOL XCGUI_RegEventCPP_t(HXCGUI hXCGUI, UINT nEvent, int regType, BOOL bEle, C* c, R(CT::* f)(A1, A2, A3, A4, A5), const char* pFunName) {
	return XEle_RegEventCPP_tt<R>(hXCGUI, nEvent, regType, bEle, (CT*)c, f, pFunName);
}

//---------------
template<class R, class C, class CT, class A1>
BOOL XCGUI_RegEventCPP1_t(HXCGUI hXCGUI, UINT nEvent, int regType, BOOL bEle, C* c, R(CT::* f)(A1), const char* pFunName) {
	return XEle_RegEventCPP1_tt<R>(hXCGUI, nEvent, regType, bEle, (CT*)c, f, pFunName);
}

template<class R, class C, class CT, class A1, class A2>
BOOL XCGUI_RegEventCPP1_t(HXCGUI hXCGUI, UINT nEvent, int regType, BOOL bEle, C* c, R(CT::* f)(A1, A2), const char* pFunName) {
	return XEle_RegEventCPP1_tt<R>(hXCGUI, nEvent, regType, bEle, (CT*)c, f, pFunName);
}

template<class R, class C, class CT, class A1, class A2, class A3>
BOOL XCGUI_RegEventCPP1_t(HXCGUI hXCGUI, UINT nEvent, int regType, BOOL bEle, C* c, R(CT::* f)(A1, A2, A3), const char* pFunName) {
	return XEle_RegEventCPP1_tt<R>(hXCGUI, nEvent, regType, bEle, (CT*)c, f, pFunName);
}

template<class R, class C, class CT, class A1, class A2, class A3, class A4>
BOOL XCGUI_RegEventCPP1_t(HXCGUI hXCGUI, UINT nEvent, int regType, BOOL bEle, C* c, R(CT::* f)(A1, A2, A3, A4), const char* pFunName) {
	return XEle_RegEventCPP1_tt<R>(hXCGUI, nEvent, regType, bEle, (CT*)c, f, pFunName);
}

template<class R, class C, class CT, class A1, class A2, class A3, class A4, class A5>
BOOL XCGUI_RegEventCPP1_t(HXCGUI hXCGUI, UINT nEvent, int regType, BOOL bEle, C* c, R(CT::* f)(A1, A2, A3, A4, A5), const char* pFunName) {
	return XEle_RegEventCPP1_tt<R>(hXCGUI, nEvent, regType, bEle, (CT*)c, f, pFunName);
}


#define XEle_RegEventCPP(hEle,Event,mFun)    XCGUI_RegEventCPP_t(hEle,Event,0,TRUE,this,mFun,#mFun)
#define XEle_RegEventCPP1(hEle,Event,mFun)   XCGUI_RegEventCPP1_t(hEle,Event,1,TRUE,this,mFun,#mFun)
#define XWnd_RegEventCPP(hWindow,Event,mFun)    XCGUI_RegEventCPP_t(hWindow,Event,0,FALSE,this,mFun,#mFun)
#define XWnd_RegEventCPP1(hWindow,Event,mFun)   XCGUI_RegEventCPP1_t(hWindow,Event,1,FALSE,this,mFun,#mFun)

//@����}

//@��ע ȡ����ע���Ԫ���¼�����
//     ����: Ԫ��_�Ƴ��¼�CPP(Ԫ�ؾ��, Ԫ���¼�_��ť���, &�ҵ�������::��ť�����Ӧ����)
//@���� Ԫ��_�Ƴ��¼�CPP(Ԫ�ؾ��, �¼�����, �¼�����)
#define XEle_RemoveEventCPP(hEle,Event,mFun)  XCGUI_RegEventCPP_t(hEle,Event,-1,TRUE,this,mFun,#mFun)

//@��ע ȡ����ע��Ĵ����¼�����
//     ����: XWnd_RemoveEventCPP(���ھ��, �����¼�_����������, &�ҵ�������::�������������°�����Ӧ����)
//@���� ����_�Ƴ��¼�CPP(���ھ��, �¼�����, �¼�����)
#define XWnd_RemoveEventCPP(hWindow,Event,mFun) XCGUI_RegEventCPP_t(hWindow,Event,-1,FALSE,this,mFun,#mFun)


/*@����

//@��ע ע��Ԫ���¼�, �����Ա������Ϊ�¼���Ӧ����, ����ʡ��Ԫ��������"Ԫ�ؾ��"; �¼���ʽ���ĵ����Ӧ�¼�˵��
//  ����: Ԫ��_ע���¼�CPP(Ԫ�ؾ��, Ԫ���¼�_��ť���, &�ҵ�������::��ť�����Ӧ����)
//@����  Ԫ�ؾ��
//@����  �¼�����
//@����  �¼���Ӧ����, ��ĳ�Ա����
//@����  Ԫ��_ע���¼�CPP(Ԫ�ؾ��, �¼�����, �¼���Ӧ����)
void XEle_RegEventCPP(HELE hEle, int event, void* callback);

//@��ע ע��Ԫ���¼�, �����Ա������Ϊ�¼���Ӧ����, ������ʡ�Բ���"Ԫ�ؾ��"; �¼���ʽ���ĵ����Ӧ�¼�˵��
//  ����: Ԫ��_ע���¼�CPP1(Ԫ�ؾ��, Ԫ���¼�_��ť���, &�ҵ�������::��ť�����Ӧ����)
//@����  Ԫ�ؾ��
//@����  �¼�����
//@����  �¼���Ӧ����, ��ĳ�Ա����
//@����  Ԫ��_ע���¼�CPP1(Ԫ�ؾ��, �¼�����, �¼���Ӧ����)
void XEle_RegEventCPP1(HELE hEle, int event, void* callback);

//@��ע ע�ᴰ���¼�,�����Ա������Ϊ�¼���Ӧ����, ����ʡ�Բ�������������"���ھ��"; �¼���ʽ���ĵ����Ӧ�¼�˵��
//  ����: ����_ע���¼�CPP(���ھ��, �����¼�_����������, &�ҵ�������::�������������°�����Ӧ����)
//@����  Ԫ�ؾ��
//@����  �¼�����
//@����  �¼���Ӧ����, ��ĳ�Ա����
//@����  ����_ע���¼�CPP(���ھ��, �¼�����, �¼���Ӧ����)
void XWnd_RegEventCPP(HWINDOW hWindow, int event, void* callback);

//@��ע ע�ᴰ���¼�,�����Ա������Ϊ�¼���Ӧ����, ������ʡ�Բ���"���ھ��"; �¼���ʽ���ĵ����Ӧ�¼�˵��
//  ����: ����_ע���¼�CPP1(���ھ��, �����¼�_����������, &�ҵ�������::�������������°�����Ӧ����)
//@����  Ԫ�ؾ��
//@����  �¼�����
//@����  �¼���Ӧ����, ��ĳ�Ա����
//@����  ����_ע���¼�CPP1(���ھ��, �¼�����, �¼���Ӧ����)
void XWnd_RegEventCPP1(HWINDOW hWindow, int event, void* callback);

*/
//@����}

//@����{  ϵͳ������ʽ
/*@����

#define WS_OVERLAPPED       0x00000000L	  //@��ע  �������ص��Ĵ��ڡ� �ص��Ĵ��ڴ��б������ͱ߿� �� WS_TILED ��ʽ��ͬ��
#define WS_POPUP            0x80000000L	  //@��ע  �����ǵ������ڡ� ����ʽ������ WS_CHILD ��ʽһ��ʹ�á�
#define WS_CHILD            0x40000000L	  //@��ע  �������Ӵ��ڡ� ���д���ʽ�Ĵ��ڲ����в˵����� ����ʽ������ WS_POPUP ��ʽһ��ʹ�á�
#define WS_MINIMIZE         0x20000000L	  //@��ע  ���������С���� �� WS_ICONIC ��ʽ��ͬ��
#define WS_VISIBLE          0x10000000L	  //@��ע  �ô�������ǿɼ��ġ�����ʹ�� ShowWindow �� SetWindowPos �����򿪺͹رմ���ʽ��
#define WS_DISABLED         0x08000000L	  //@��ע  ����������ڽ���״̬�� �ѽ��õĴ����޷������û������롣 ��Ҫ�ڴ������ں���Ĵ����ã���ʹ�� EnableWindow ������
#define WS_CLIPSIBLINGS     0x04000000L	  //@��ע  ����ڱ˴˼����Ӵ���;Ҳ����˵�����ض��Ӵ����յ� WM_PAINT ��Ϣʱ�� WS_CLIPSIBLINGS ��ʽ�Ὣ���������ص����Ӵ��ڼ��õ��Ӵ��ڵ�����֮����и��¡� ���δָ�� WS_CLIPSIBLINGS ���Ӵ����ص��������Ӵ��ڵĹ������ڻ�ͼʱ�������������Ӵ��ڵĹ������ڻ��ơ�
#define WS_CLIPCHILDREN     0x02000000L	  //@��ע  �ų��ڸ������ڽ��л���ʱ�Ӵ���ռ�õ����� ����������ʱʹ�ô���ʽ��
#define WS_MAXIMIZE         0x01000000L	  //@��ע  �����������󻯵ġ�
#define WS_CAPTION          0x00C00000L   //@��ע  ���ھ��б�����, (���� WS_BORDER ��ʽ)
#define WS_BORDER           0x00800000L   //@��ע  ���ھ���ϸ�߱߿�
#define WS_DLGFRAME         0x00400000L	  //@��ע  ���ھ���ͨ����Ի���һ��ʹ�õ���ʽ�ı߿� ���д���ʽ�Ĵ��ڲ��ܾ��б�������
#define WS_VSCROLL          0x00200000L	  //@��ע  ���ھ��д�ֱ��������
#define WS_HSCROLL          0x00100000L	  //@��ע  ���ھ���ˮƽ��������
#define WS_SYSMENU          0x00080000L	  //@��ע  ���ڵı���������һ�����ڲ˵��� ������ָ�� WS_CAPTION ��ʽ��
#define WS_THICKFRAME       0x00040000L	  //@��ע  ���ھ��е�����С�߿� �� WS_SIZEBOX ��ʽ��ͬ��
#define WS_GROUP            0x00020000L	  //@��ע  ������һ��ؼ��ĵ�һ���ؼ��� ���ɵ�һ���ؼ������������пؼ���ɣ��Լ����� WS_GROUP ��ʽ����һ���ؼ��� ÿ�����еĵ�һ���ؼ�ͨ������ WS_TABSTOP ��ʽ���Ա��û���������֮���ƶ��� �û�������ʹ�÷���������̽�������е�һ���ؼ�����Ϊ���е���һ���ؼ���
//���Դ򿪺͹رմ���ʽ�Ը��ĶԻ��򵼺��� ��Ҫ�ڴ������ں���Ĵ���ʽ����ʹ�� SetWindowLong ������

#define WS_TABSTOP          0x00010000L	  //@��ע  ������һ���ؼ������û����� TAB ��ʱ���ÿؼ����Խ��ռ��̽��㡣 �� Tab �������̽������Ϊ���� WS_TABSTOP ��ʽ����һ���ؼ���
//���Դ򿪺͹رմ���ʽ�Ը��ĶԻ��򵼺��� ��Ҫ�ڴ������ں���Ĵ���ʽ����ʹ�� SetWindowLong ������ ��Ҫʹ�û������Ĵ��ں���ģʽ�Ի���ʹ���Ʊ�λ���������Ϣѭ���Ե��� IsDialogMessage ������

#define WS_MINIMIZEBOX      0x00020000L	  //@��ע  ������һ����С����ť�� ������ WS_EX_CONTEXTHELP ��ʽ���ʹ�á� ������ָ�� WS_SYSMENU ��ʽ��
#define WS_MAXIMIZEBOX      0x00010000L	  //@��ע  ������һ������󻯡���ť�� ������ WS_EX_CONTEXTHELP ��ʽ���ʹ�á� ������ָ�� WS_SYSMENU ��ʽ��

#define WS_TILED            WS_OVERLAPPED        //@��ע  �ô�����һ���ص��Ĵ��ڡ� �ص��Ĵ��ڴ��б������ͱ߿� �� WS_OVERLAPPED ��ʽ��ͬ��
#define WS_ICONIC           WS_MINIMIZE			 //@��ע  ���������С���� �� WS_MINIMIZE ��ʽ��ͬ��
#define WS_SIZEBOX          WS_THICKFRAME		 //@��ע  ���ھ��д�С�����߿� �� WS_THICKFRAME ��ʽ��ͬ��
#define WS_TILEDWINDOW      WS_OVERLAPPEDWINDOW	 //@��ע  �ô�����һ���ص��Ĵ��ڡ� �� WS_OVERLAPPEDWINDOW ��ʽ��ͬ��

*/
//@����}


/*@����
//@����{ �ص�������ʽ

//@��ע  �����ص�
//@����  hAnimation �������л򶯻�����
//@����  flag       ��ǰ����
//@����  �����ص�(�������л򶯻�����, ��ǰ����)
typedef  void(CALLBACK* funAnimation)(HXCGUI hAnimation, int flag);

//@��ע  ������ص�
//@����  hAnimation ��������
//@����  pos        ��ǰ����(0.0f-1.0f)
//@����  ������ص�(��������, ��ǰ����)
typedef  void(CALLBACK* funAnimationItem)(HXCGUI hAnimation, float pos);

//@��ע  XC_CallUiThread() ����Ҫ�Ļص�������ʽ
//@����  data  �û�����
//@����  UI�̻߳ص�(�û�����)
typedef vint(CALLBACK* funCallUiThread)(vint data);

//@����}
*/


//////////////////////////////////////////////////////////////////////
/////////////////////API//////////////////////////////////////////////

//@����{  �Ųʻ���

//@��ע ��ȡ������������.
//@���� hXCGUI ������
//@���� ���ض�������.
//@���� �Ųʶ���_ȡ����()
XC_API XC_OBJECT_TYPE WINAPI XObj_GetType(HXCGUI hXCGUI);
//@��ע ��ȡ����Ļ�������,
//XC_ERROR, XC_WINDOW, XC_ELE, XC_SHAPE, XC_ADAPTER
//@���� hXCGUI ������
//@���� ���ض�������, ��������֮һ:
//@���� �Ųʶ���_ȡ��������()
XC_API XC_OBJECT_TYPE WINAPI XObj_GetTypeBase(HXCGUI hXCGUI);
//@��ע ��ȡ������չ����.
//@���� hXCGUI ������.
//@���� ���ض�����չ����.
//@���� �Ųʶ���_ȡ������չ()
XC_API XC_OBJECT_TYPE_EX WINAPI XObj_GetTypeEx(HXCGUI hXCGUI);
//@��ע ����ǰ�ť, ��ʹ�ð�ť����ǿ�ӿ� XBtn_SetTypeEx()
//@���� hXCGUI ������.
//@���� nType ��չ����,�μ��궨��.
//@���� �Ųʶ���_��������չ()
XC_API void WINAPI XObj_SetTypeEx(HXCGUI hXCGUI, XC_OBJECT_TYPE_EX nType);
//@����}
//@����{  ���Ӷ���

//@��ע ����UI������ʽ
//@���� hXCGUI ������.
//@���� nStyle ��ʽֵ.
//@���� ���Ӷ���_����ʽ()
XC_API void WINAPI XUI_SetStyle(HXCGUI hXCGUI, XC_OBJECT_STYLE nStyle);
//@��ע ��ȡUI������ʽ
//@���� hXCGUI ������.
//@���� ����UI������ʽ.
//@���� ���Ӷ���_ȡ��ʽ()
XC_API XC_OBJECT_STYLE WINAPI XUI_GetStyle(HXCGUI hXCGUI);
//@��ע ���û������ʽ, ���Ҹ�����Ƕ��Ԫ������,  ����:������ͼ�ϵĹ�����, �������ϵİ�ť
//@���� hXCGUI ������.
//@���� bEnable �Ƿ�����.
//@���� ���Ӷ���_����CSS()
XC_API void WINAPI XUI_EnableCSS(HXCGUI hXCGUI, BOOL bEnable);
//@��ע ���û������ʽ, ��������������, ��������Ƕ��Ԫ������,  ����:������ͼ�ϵĹ�����, �������ϵİ�ť
//@���� hXCGUI ������.
//@���� bEnable �Ƿ�����.
//@���� ���Ӷ���_����CSS��չ()
XC_API void WINAPI XUI_EnableCssEx(HXCGUI hXCGUI, BOOL bEnable);
//@��ע ����CSS[������ʽ]����
//@���� hXCGUI ������.
//@���� pName ������ʽ����.
//@���� ���Ӷ���_��CSS����()
XC_API void WINAPI XUI_SetCssName(HXCGUI hXCGUI, const wchar_t* pName);
//@��ע ��ȡCSS��ʽ����
//@���� hXCGUI ������.
//@���� ����CSS��ʽ����.
//@���� ���Ӷ���_ȡCSS����()
XC_API const wchar_t* WINAPI XUI_GetCssName(HXCGUI hXCGUI);
//@����}
//@����{  �������

//@��ע �ж�UI�����Ƿ���ʾ
//@���� hXCGUI ������.
//@���� ��ʾ����TRUE,���򷵻�FALSE.
//@���� �������_�Ƿ���ʾ()
XC_API BOOL WINAPI XWidget_IsShow(HXCGUI hXCGUI);
//@���� hXCGUI ������
//@���� bShow �Ƿ���ʾ
//@���� �������_��ʾ()
XC_API void WINAPI XWidget_Show(HXCGUI hXCGUI, BOOL bShow);
//@��ע �ö����Ƿ��ܲ��ֿ���
//@���� hXCGUI ������
//@���� bEnable �Ƿ�����
//@���� �������_���ò��ֿ���()
XC_API void WINAPI XWidget_EnableLayoutControl(HXCGUI hXCGUI, BOOL bEnable);
//@���� hXCGUI
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� �������_�Ƿ񲼾ֿ���()
XC_API BOOL WINAPI XWidget_IsLayoutControl(HXCGUI hXCGUI);
//@���� hXCGUI ������.
//@���� Ԫ�ؾ��.
//@���� �������_ȡ��Ԫ��()
XC_API HELE WINAPI XWidget_GetParentEle(HXCGUI hXCGUI);
//@��ע ��ȡ������,��������Ԫ�ػ򴰿�,ͨ���˺������Լ���Ƿ��и�
//@���� hXCGUI ������.
//@���� ������.
//@���� �������_ȡ�����()
XC_API HXCGUI WINAPI XWidget_GetParent(HXCGUI hXCGUI);
//@��ע ��ȡHWND���
//@���� hXCGUI ������.
//@���� HWND���.
//@���� �������_ȡ���ھ��ϵͳ()
XC_API HWND WINAPI XWidget_GetHWND(HXCGUI hXCGUI);
//@��ע ��ȡHWINDOW���
//@���� hXCGUI ������.
//@���� HWINDOW���.
//@���� �������_ȡ���ھ��()
XC_API HWINDOW WINAPI XWidget_GetHWINDOW(HXCGUI hXCGUI);
//@��ע ����Ԫ��ID
//@���� hXCGUI ������
//@���� nID IDֵ.
//@���� �������_��ID()
XC_API void WINAPI XWidget_SetID(HXCGUI hXCGUI, int nID);
//@��ע ��ȡԪ��ID
//@���� hXCGUI ������
//@���� ����Ԫ��ID.
//@���� �������_ȡID()
XC_API int WINAPI XWidget_GetID(HXCGUI hXCGUI);
//@��ע ����Ԫ��UID, ȫ��Ψһ��ʶ��
//@���� hXCGUI ������
//@���� nUID UIDֵ.
//@���� �������_��UID()
XC_API void WINAPI XWidget_SetUID(HXCGUI hXCGUI, int nUID);
//@��ע ��ȡԪ��UID,ȫ��Ψһ��ʶ��
//@���� hXCGUI ������
//@���� ����Ԫ��UID.
//@���� �������_ȡUID()
XC_API int WINAPI XWidget_GetUID(HXCGUI hXCGUI);
//@��ע ����Ԫ��name
//@���� hXCGUI ������
//@���� pName nameֵ,�ַ���ָ��.
//@���� �������_������()
XC_API void WINAPI XWidget_SetName(HXCGUI hXCGUI, const wchar_t* pName);
//@��ע ��ȡԪ��name
//@���� hXCGUI ������
//@���� ����name.
//@���� �������_ȡ����()
XC_API const wchar_t* WINAPI XWidget_GetName(HXCGUI hXCGUI);
//@��ע ǿ�ƻ���
//@���� hXCGUI UI������
//@���� bWrap �Ƿ���
//@���� �������_������_���û���()
XC_API void WINAPI XWidget_LayoutItem_EnableWrap(HXCGUI hXCGUI, BOOL bWrap);
//@��ע ����ˮƽ��ֱ���ֱ任, ��������(���,�߶�,��С���,��С�߶�)
//@���� hXCGUI UI������
//@���� bEnable �Ƿ�����
//@���� �������_������_���ý���()
XC_API void WINAPI XWidget_LayoutItem_EnableSwap(HXCGUI hXCGUI, BOOL bEnable);
//@��ע �򷴷������
//@���� hXCGUI UI������
//@���� bFloat �Ƿ񸡶�
//@���� �������_������_���ø���()
XC_API void WINAPI XWidget_LayoutItem_EnableFloat(HXCGUI hXCGUI, BOOL bFloat);
//@���� hXCGUI UI������
//@���� nType ����
//@���� nWidth ���
//@���� �������_������_�ÿ��()
XC_API void WINAPI XWidget_LayoutItem_SetWidth(HXCGUI hXCGUI, layout_size_ nType, int nWidth);
//@���� hXCGUI UI������
//@���� nType ����
//@���� nHeight �߶�
//@���� �������_������_�ø߶�()
XC_API void WINAPI XWidget_LayoutItem_SetHeight(HXCGUI hXCGUI, layout_size_ nType, int nHeight);
//@���� hXCGUI UI������
//@���� pType ����
//@���� pWidth ����ֵ
//@���� �������_������_ȡ���()
XC_API void WINAPI XWidget_LayoutItem_GetWidth(HXCGUI hXCGUI, layout_size_* pType, int* pWidth);
//@���� hXCGUI UI������
//@���� pType ����
//@���� pHeight ����ֵ
//@���� �������_������_ȡ�߶�()
XC_API void WINAPI XWidget_LayoutItem_GetHeight(HXCGUI hXCGUI, layout_size_* pType, int* pHeight);
//@��ע ����ˮƽ��ֱ��仯����
//@���� hXCGUI UI������
//@���� nAlign ���뷽ʽ
//@���� �������_������_�ö���()
XC_API void WINAPI XWidget_LayoutItem_SetAlign(HXCGUI hXCGUI, layout_align_axis_ nAlign);
//@���� hXCGUI UI������
//@���� left ��ߴ�С
//@���� top �ϱߴ�С
//@���� right �ұߴ�С
//@���� bottom �±ߴ�С
//@���� �������_������_������()
XC_API void WINAPI XWidget_LayoutItem_SetMargin(HXCGUI hXCGUI, int left, int top, int right, int bottom);
//@���� hXCGUI UI������
//@���� pMargin ���շ���
//@���� �������_������_ȡ����()
XC_API void WINAPI XWidget_LayoutItem_GetMargin(HXCGUI hXCGUI, marginSize_* pMargin);
//@��ע ���ƴ�С�����������Ч(�Զ�, ��丸, ����, �ٷֱ�)
//@���� hXCGUI UI������
//@���� width ��С���
//@���� height ��С�߶�
//@���� �������_������_����С��С()
XC_API void WINAPI XWidget_LayoutItem_SetMinSize(HXCGUI hXCGUI, int width, int height);
//@��ע ���λ��, ֵ���ڵ���0��Ч
//@���� hXCGUI UI������
//@���� left ��߾���
//@���� top �ϱ߾���
//@���� right �ұ߾���
//@���� bottom �±߾���
//@���� �������_������_��λ��()
XC_API void WINAPI XWidget_LayoutItem_SetPosition(HXCGUI hXCGUI, int left, int top, int right, int bottom);
//@����}
//@����{  ����

//@��ע ��������
//@���� x �������Ͻ�x����.
//@���� y �������Ͻ�y����.
//@���� cx ���ڿ��.
//@���� cy ���ڸ߶�.
//@���� pTitle ���ڱ���.
//@���� hWndParent ������.
//@���� XCStyle GUI�ⴰ����ʽ,��ʽ��μ��궨�� @ref window_style_.
//@���� GUI�ⴰ����Դ���.
//@���� ����_����()
XC_API HWINDOW WINAPI XWnd_Create(int x, int y, int cx, int cy, const wchar_t* pTitle, HWND hWndParent=NULL, int XCStyle=window_style_default);
//@��ע ��������,��ǿ����.
//@���� dwExStyle ������չ��ʽ.
//@���� dwStyle ������ʽ
//@���� lpClassName ��������.
//@���� x �������Ͻ�x����.
//@���� y �������Ͻ�y����.
//@���� cx ���ڿ��.
//@���� cy ���ڸ߶�.
//@���� pTitle ������.
//@���� hWndParent ������.
//@���� XCStyle GUI�ⴰ����ʽ,��ʽ��μ��궨�� @ref window_style_.
//@���� GUI�ⴰ����Դ���.
//@���� ����_������չ()
XC_API HWINDOW WINAPI XWnd_CreateEx(DWORD dwExStyle, DWORD dwStyle, const wchar_t* lpClassName, int x, int y, int cx, int cy, const wchar_t* pTitle, HWND hWndParent=NULL, int XCStyle=window_style_default);
//@���� hWnd Ҫ���ӵ��ⲿ���ھ��
//@���� XCStyle GUI�ⴰ����ʽ,��ʽ��μ��궨�� @ref window_style_
//@���� ���ش��ھ��
//@���� ����_���Ӵ���()
XC_API HWINDOW WINAPI XWnd_Attach(HWND hWnd, int XCStyle);
//@��ע ע���¼�����C��ʽ,�¼�����ʡ������HWINDOW���.
//@���� hWindow ���ھ��.
//@���� nEvent �¼�����.
//@���� pFun �¼�����.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ����_ע���¼�C()
XC_API BOOL WINAPI XWnd_RegEventC(HWINDOW hWindow, int nEvent, void* pFun);
//@��ע ע���¼�����C��ʽ,�¼�������ʡ������HWINDOW���.
//@���� hWindow ���ھ��.
//@���� nEvent �¼�����.
//@���� pFun �¼�����.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ����_ע���¼�C1()
XC_API BOOL WINAPI XWnd_RegEventC1(HWINDOW hWindow, int nEvent, void* pFun);
//@��ע �Ƴ��¼�����.
//@���� hWindow ���ھ��.
//@���� nEvent �¼�����.
//@���� pFun �¼�����.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ����_�Ƴ��¼�C()
XC_API BOOL WINAPI XWnd_RemoveEventC(HWINDOW hWindow, int nEvent, void* pFun);
//@��ע ����Ӷ��󵽴���
//@���� hWindow ���ھ��
//@���� hChild Ҫ��ӵĶ�����.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ����_����Ӷ���()
XC_API BOOL WINAPI XWnd_AddChild(HWINDOW hWindow, HXCGUI hChild);
//@��ע �����Ӷ���ָ��λ��.
//@���� hWindow ���ھ��.
//@���� hChild Ҫ����Ķ�����.
//@���� index ����λ������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ����_�����Ӷ���()
XC_API BOOL WINAPI XWnd_InsertChild(HWINDOW hWindow, HXCGUI hChild, int index);
//@��ע �ػ洰��.
//@���� hWindow ������Դ���.
//@���� bUpdate �Ƿ������ػ�,Ĭ��Ϊ��.
//@���� ����_�ػ�()
XC_API void WINAPI XWnd_Redraw(HWINDOW hWindow, BOOL bUpdate=FALSE);
//@��ע �ػ洰��ָ������.
//@���� hWindow ������Դ���.
//@���� pRect ��Ҫ�ػ����������.
//@���� bUpdate TRUE�����ػ�,FALSE������Ϣ�����ӳ��ػ�.
//@���� ����_�ػ�ָ������()
XC_API void WINAPI XWnd_RedrawRect(HWINDOW hWindow, RECT* pRect, BOOL bUpdate=FALSE);
//@��ע ���ý���Ԫ��.
//@���� hWindow ������Դ���.
//@���� hFocusEle ����ý����Ԫ��.
//@���� ����_�ý���()
XC_API void WINAPI XWnd_SetFocusEle(HWINDOW hWindow, HELE hFocusEle);
//@��ע ���ӵ�����뽹���Ԫ��.
//@���� hWindow ������Դ���.
//@���� Ԫ�ؾ��.
//@���� ����_ȡ����()
XC_API HELE WINAPI XWnd_GetFocusEle(HWINDOW hWindow);
//@��ע ��ȡ��ǰ�����ͣ��Ԫ��.
//@���� hWindow ������Դ���.
//@���� �������ͣ��Ԫ�ؾ��.
//@���� ����_ȡ���ͣ��Ԫ��()
XC_API HELE WINAPI XWnd_GetStayEle(HWINDOW hWindow);
//@��ע ���Ի��¼�������,�û��ֶ����û��ƴ���,�Ա���ƻ���˳��.
//@���� hWindow ������Դ���.
//@���� hDraw ͼ�λ��ƾ��.
//@���� ����_����()
XC_API void WINAPI XWnd_DrawWindow(HWINDOW hWindow, HDRAW hDraw);
//@��ע ���д���.
//@���� hWindow ������Դ���.
//@���� ����_����()
XC_API void WINAPI XWnd_Center(HWINDOW hWindow);
//@��ע ���д���.
//@���� hWindow ������Դ���.
//@���� width ���ڿ��
//@���� height ���ڸ߶�
//@���� ����_������չ()
XC_API void WINAPI XWnd_CenterEx(HWINDOW hWindow, int width, int height);
//@��ע ���ô��������.
//@���� hWindow ���ھ��.
//@���� hCursor �������.
//@���� ����_�ù��()
XC_API void WINAPI XWnd_SetCursor(HWINDOW hWindow, HCURSOR hCursor);
//@��ע ��ȡ���ڵ������.
//@���� hWindow ���ھ��.
//@���� �������.
//@���� ����_ȡ���()
XC_API HCURSOR WINAPI XWnd_GetCursor(HWINDOW hWindow);
//@��ע ��ȡHWND���.
//@���� hWindow ���ھ��.
//@���� HWND���.
//@���� ����_ȡHWND()
XC_API HWND WINAPI XWnd_GetHWND(HWINDOW hWindow);
//@��ע �����϶����ڱ߿�.
//@���� hWindow ���ھ��.
//@���� bEnable �Ƿ�����.
//@���� ����_�����϶��߿�()
XC_API void WINAPI XWnd_EnableDragBorder(HWINDOW hWindow, BOOL bEnable);
//@��ע �����϶�����.
//@���� hWindow ���ھ��.
//@���� bEnable �Ƿ�����.
//@���� ����_�����϶�����()
XC_API void WINAPI XWnd_EnableDragWindow(HWINDOW hWindow, BOOL bEnable);
//@��ע �����϶����ڱ�����.
//@���� hWindow ���ھ��.
//@���� bEnable �Ƿ�����.
//@���� ����_�����϶�������()
XC_API void WINAPI XWnd_EnableDragCaption(HWINDOW hWindow, BOOL bEnable);
//@��ע �Ƿ���ƴ��ڱ���.
//@���� hWindow ���ھ��.
//@���� bEnable �Ƿ�����.
//@���� ����_���û��Ʊ���()
XC_API void WINAPI XWnd_EnableDrawBk(HWINDOW hWindow, BOOL bEnable);
//@��ע �������������Ƿ��ý���.
//@���� hWindow ���ھ��.
//@���� bEnable �Ƿ�����.
//@���� ����_�����Զ�����()
XC_API void WINAPI XWnd_EnableAutoFocus(HWINDOW hWindow, BOOL bEnable);
//@��ע ���������.
//@���� hWindow ���ھ��.
//@���� bEnable �Ƿ�����.
//@���� ����_�����������()
XC_API void WINAPI XWnd_EnableMaxWindow(HWINDOW hWindow, BOOL bEnable);
//@��ע ���ƴ�����С�����ߴ�
//@���� hWindow ���ھ��
//@���� bEnable �Ƿ�����
//@���� ����_�������ƴ��ڴ�С()
XC_API void WINAPI XWnd_EnableLimitWindowSize(HWINDOW hWindow, BOOL bEnable);
//@���� hWindow ���ھ��
//@���� bEnable �Ƿ�����
//@���� ����_�����Ϸ��ļ�()
XC_API void WINAPI XWnd_EnableDragFiles(HWINDOW hWindow, BOOL bEnable);
//@��ע ���ò��ֹ���.
//@���� hWindow ���ھ��.
//@���� bEnable �Ƿ�����.
//@���� ����_���ò���()
XC_API void WINAPI XWnd_EnableLayout(HWINDOW hWindow, BOOL bEnable);
//@���� hWindow ���ھ��
//@���� bEnable �Ƿ�����
//@���� ����_���ò��ָ��Ǳ߿�()
XC_API void WINAPI XWnd_EnableLayoutOverlayBorder(HWINDOW hWindow, BOOL bEnable);
//@��ע ��ʾ���ֱ߽�.
//@���� hWindow ���ھ��.
//@���� bEnable �Ƿ�����.
//@���� ����_��ʾ���ֱ߽�()
XC_API void WINAPI XWnd_ShowLayoutFrame(HWINDOW hWindow, BOOL bEnable);
//@��ע �ж��Ƿ����ò���.
//@���� hWindow ���ھ��
//@���� ����ɹ�����TRUE,���򷵻�FALSE.
//@���� ����_�Ƿ����ò���()
XC_API BOOL WINAPI XWnd_IsEnableLayout(HWINDOW hWindow);
//@���� hWindow ���ھ��
//@���� ���������󻯷���TRUE,���򷵻�FALSE
//@���� ����_�Ƿ����()
XC_API BOOL WINAPI XWnd_IsMaxWindow(HWINDOW hWindow);
//@���� hWindow ���ھ��
//@���� ���ڱ߿���϶�����TRUE,���򷵻�FALSE
//@���� ����_�Ƿ���϶��߿�()
XC_API BOOL WINAPI XWnd_IsDragBorder(HWINDOW hWindow);
//@���� hWindow ���ھ��
//@���� �������������϶�����TRUE,���򷵻�FALSE
//@���� ����_�Ƿ���϶�����()
XC_API BOOL WINAPI XWnd_IsDragWindow(HWINDOW hWindow);
//@���� hWindow ���ھ��
//@���� ���ڱ�����϶�����TRUE,���򷵻�FALSE
//@���� ����_�Ƿ���϶�������()
XC_API BOOL WINAPI XWnd_IsDragCaption(HWINDOW hWindow);
//@��ע ������겶��Ԫ��.
//@���� hWindow ���ھ��.
//@���� hEle Ԫ�ؾ��.
//@���� ����_����겶��Ԫ��()
XC_API void WINAPI XWnd_SetCaptureEle(HWINDOW hWindow, HELE hEle);
//@��ע ��ȡ��ǰ��겶��Ԫ��.
//@���� hWindow ���ھ��.
//@���� Ԫ�ؾ��.
//@���� ����_ȡ��겶��Ԫ��()
XC_API HELE WINAPI XWnd_GetCaptureEle(HWINDOW hWindow);
//@��ע ��ȡ�ػ�����.
//@���� hWindow ���ھ��.
//@���� pRcPaint �ػ���������.
//@���� ����_ȡ���ƾ���()
XC_API void WINAPI XWnd_GetDrawRect(HWINDOW hWindow, RECT* pRcPaint);
//@��ע ��ʾ���ش���
//@���� hWindow ���ھ��.
//@���� bShow �Ƿ���ʾ
//@���� ����_��ʾ()
XC_API void WINAPI XWnd_Show(HWINDOW hWindow, BOOL bShow);
//@��ע ��ʾ���ؼ����ƴ�����󻯻�ԭ��
//@���� hWindow ���ھ��.
//@���� nCmdShow �μ�MSDN.
//@���� �μ�MSDN.
//@���� ����_��ʾ��չ()
XC_API BOOL WINAPI XWnd_ShowWindow(HWINDOW hWindow, int nCmdShow);
//@��ע ϵͳ����,���ô���������.
//@���� hWindow ���ھ��.
//@���� hCursor �����.
//@���� ������ǰ�Ĺ����.
//@���� ����_��ϵͳ���()
XC_API HCURSOR WINAPI XWnd_SetCursorSys(HWINDOW hWindow, HCURSOR hCursor);
//@��ע ���ô�������.
//@���� hWindow ���ھ��.
//@���� hFontx �Ų�������.
//@���� ����_������()
XC_API void WINAPI XWnd_SetFont(HWINDOW hWindow, HFONTX hFontx);
//@��ע �����ı���ɫ.
//@���� hWindow ���ھ��.
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� ����_���ı���ɫ()
XC_API void WINAPI XWnd_SetTextColor(HWINDOW hWindow, COLORREF color);
//@��ע ��ȡ�ı���ɫ.
//@���� hWindow ���ھ��.
//@���� �ı���ɫֵ.
//@���� ����_ȡ�ļ���ɫ()
XC_API COLORREF WINAPI XWnd_GetTextColor(HWINDOW hWindow);
//@��ע ��ȡ�ı���ɫ,���ȴ���Դ�л�ȡ.
//@���� hWindow ���ھ��.
//@���� �ı���ɫֵ.
//@���� ����_ȡ�ı���ɫ��չ()
XC_API COLORREF WINAPI XWnd_GetTextColorEx(HWINDOW hWindow);
//@��ע ���ô���ID.
//@���� hWindow ���ھ��.
//@���� nID IDֵ.
//@���� ����_��ID()
XC_API void WINAPI XWnd_SetID(HWINDOW hWindow, int nID);
//@��ע ��ȡ����ID.
//@���� hWindow ���ھ��.
//@���� ���ش���IDֵ.
//@���� ����_ȡID()
XC_API int WINAPI XWnd_GetID(HWINDOW hWindow);
//@��ע ���ô���name
//@���� hWindow ���ھ��
//@���� pName nameֵ,�ַ���ָ��.
//@���� ����_������()
XC_API void WINAPI XWnd_SetName(HWINDOW hWindow, const wchar_t* pName);
//@��ע ��ȡ����name
//@���� hWindow ���ھ��
//@���� ����name
//@���� ����_ȡ����()
XC_API const wchar_t* WINAPI XWnd_GetName(HWINDOW hWindow);
//@��ע ���ñߴ�С.
//@���� hWindow ���ھ��.
//@���� left ������ߴ�С.
//@���� top �����ϱߴ�С.
//@���� right �����ұߴ�С.
//@���� bottom ���ڵײ���С.
//@���� ����_�ñߴ�С()
XC_API void WINAPI XWnd_SetBorderSize(HWINDOW hWindow, int left, int top, int right, int bottom);
//@��ע ��ȡ�ߴ�С.
//@���� hWindow ���ھ��.
//@���� pBorder ���շ��رߴ�С.
//@���� ����_ȡ�ߴ�С()
XC_API void WINAPI XWnd_GetBorderSize(HWINDOW hWindow, borderSize_* pBorder);
//@���� hWindow ���ھ��.
//@���� left ��ߴ�С.
//@���� top �ϱߴ�С.
//@���� right �ұߴ�С.
//@���� bottom �±ߴ�С.
//@���� ����_��������С()
XC_API void WINAPI XWnd_SetPadding(HWINDOW hWindow, int left, int top, int right, int bottom);
//@��ע ���ô����϶��߿��С.
//@���� hWindow ���ھ��.
//@���� left ������ߴ�С.
//@���� top �����ϱߴ�С.
//@���� right �����ұߴ�С.
//@���� bottom ���ڵױߴ�С.
//@���� ����_���϶��߿��С()
XC_API void WINAPI XWnd_SetDragBorderSize(HWINDOW hWindow, int left, int top, int right, int bottom);
//@��ע ��ȡ�����϶��߿��С.
//@���� hWindow ���ھ��.
//@���� pSize �϶��߿��С.
//@���� ����_ȡ�϶��߿��С()
XC_API void WINAPI XWnd_GetDragBorderSize(HWINDOW hWindow, borderSize_* pSize);
//@��ע ���ñ�������(ͼ��,����,���ư�ť)����
//@���� hWindow ���ھ��
//@���� left ��߼��
//@���� top �ϱ߼��
//@���� right �ұ߼��
//@���� bottom �±߼��
//@���� ����_�ñ�������()
XC_API void WINAPI XWnd_SetCaptionMargin(HWINDOW hWindow, int left, int top, int right, int bottom);
//@��ע ���ô��ڵ���С��Ⱥ͸߶�.
//@���� hWindow ���ھ��.
//@���� width ��С���.
//@���� height ��С�߶�.
//@���� ����_����С��С()
XC_API void WINAPI XWnd_SetMinimumSize(HWINDOW hWindow, int width, int height);
//@��ע �������Ԫ��.
//@���� hWindow ���ھ��.
//@���� pPt ��ߵ�.
//@���� Ԫ�ؾ��.
//@���� ����_���Ե����Ԫ��()
XC_API HELE WINAPI XWnd_HitChildEle(HWINDOW hWindow, POINT* pPt);
//@��ע ��ȡ��ǰ���Ӷ�������,��������Ԫ�ص���Ԫ��.
//@���� hWindow ���ھ��.
//@���� ��Ԫ������.
//@���� ����_ȡ�Ӷ�������()
XC_API int WINAPI XWnd_GetChildCount(HWINDOW hWindow);
//@��ע ��ȡ��ǰ����Ԫ��ͨ������.
//@���� hWindow ���ھ��.
//@���� index Ԫ������.
//@���� Ԫ�ؾ��.
//@���� ����_ȡ�Ӷ��������()
XC_API HXCGUI WINAPI XWnd_GetChildByIndex(HWINDOW hWindow, int index);
//@��ע ��ȡ��ǰ���Ӷ���ͨ������ID.
//@���� hWindow ���ھ��.
//@���� nID Ԫ��ID,ID�������0.
//@���� ������.
//@���� ����_ȡ�Ӷ����ID()
XC_API HXCGUI WINAPI XWnd_GetChildByID(HWINDOW hWindow, int nID);
//@��ע ��ȡ�Ӷ���ͨ������ID,��������ڸô�������Ч.
//@���� hWindow ���ھ��.
//@���� nID ����ID,ID�������0.
//@���� Ԫ�ؾ��.
//@���� ����_ȡ�Ӷ���()
XC_API HXCGUI WINAPI XWnd_GetChild(HWINDOW hWindow, int nID);
//@��ע ���õ�ǰ����DPI, Ĭ��DPIΪ96
//@���� hWindow ���ھ��
//@���� nDPI DPIֵ
//@���� ����_��DPI()
XC_API void WINAPI XWnd_SetDPI(HWINDOW hWindow, int nDPI);
//@��ע ��ȡ��ǰ����������ʾ��DPI
//@���� hWindow ���ھ��
//@���� ���ش���DPI
//@���� ����_ȡDPI()
XC_API int WINAPI XWnd_GetDPI(HWINDOW hWindow);
//@���� hWindow ���ھ��
//@���� hImage ͼ����
//@���� ����_��ͼ��()
XC_API void WINAPI XWnd_SetIcon(HWINDOW hWindow, HIMAGE hImage);
//@��ע �Ųʴ��ڱ������ı�, ��ϵͳ���ڱ���
//@���� hWindow ���ھ��
//@���� pTitle �����ı�
//@���� ����_�ñ���()
XC_API void WINAPI XWnd_SetTitle(HWINDOW hWindow, const wchar_t* pTitle);
//@���� hWindow ���ھ��
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� ����_�ñ�����ɫ()
XC_API void WINAPI XWnd_SetTitleColor(HWINDOW hWindow, COLORREF color);
//@���� hWindow ���ھ��
//@���� nFlag ����ֵ: @ref window_style_btn_min , @ref window_style_btn_max , @ref window_style_btn_close
//@���� ���ذ�ť���
//@���� ����_ȡ���ư�ť()
XC_API HELE WINAPI XWnd_GetButton(HWINDOW hWindow, int nFlag);
//@���� hWindow ���ھ��
//@���� ����ͼ����
//@���� ����_ȡͼ��()
XC_API HIMAGE WINAPI XWnd_GetIcon(HWINDOW hWindow);
//@���� hWindow ���ھ��
//@���� ���ر����ı�
//@���� ����_ȡ����()
XC_API const wchar_t* WINAPI XWnd_GetTitle(HWINDOW hWindow);
//@���� hWindow ���ھ��
//@���� ������ɫֵ
//@���� ����_ȡ������ɫ()
XC_API COLORREF WINAPI XWnd_GetTitleColor(HWINDOW hWindow);
//@��ע �رմ���. ����WM_CLOSE��Ϣ�رմ���, ������WM_CLOSE��Ϣ����ֹ�رմ���
//@���� hWindow ���ھ��.
//@���� ����_�ر�()
XC_API void WINAPI XWnd_CloseWindow(HWINDOW hWindow);
//@��ע �������ٴ���, ����������ʱ����������
//@���� hWindow ���ھ��
//@���� ����_����()
XC_API void WINAPI XWnd_DestroyWindow(HWINDOW hWindow);
//@��ע �������ڲ���, ��������ʾ״̬����Ч.
//@���� hWindow ���ھ��.
//@���� ����_��������()
XC_API void WINAPI XWnd_AdjustLayout(HWINDOW hWindow);
//@��ע �������ڲ���, ��������ʾ״̬����Ч.
//@���� hWindow ���ھ��.
//@���� nFlags ������ʶ, @ref adjustLayout_
//@���� ����ɹ�����TRUE,���򷵻�FALSE.
//@���� ����_����������չ()
XC_API void WINAPI XWnd_AdjustLayoutEx(HWINDOW hWindow, int nFlags=adjustLayout_self);
//@��ע ���������,����Ԫ������.
//@���� hWindow ���ھ��.
//@���� hEle Ԫ�ؾ��.
//@���� x x����.
//@���� y y����.
//@���� width ���.
//@���� height �߶�.
//@���� ����_���������()
XC_API void WINAPI XWnd_CreateCaret(HWINDOW hWindow, HELE hEle, int x, int y, int width, int height);
//@��ע ���ò����λ��.
//@���� hWindow ���ھ��.
//@���� x x����.
//@���� y y����.
//@���� width ���.
//@���� height �߶�.
//@���� bUpdate �Ƿ���������UI
//@���� ����_�ò����λ��()
XC_API void WINAPI XWnd_SetCaretPos(HWINDOW hWindow, int x, int y, int width, int height, BOOL bUpdate=FALSE);
//@��ע ���ò������ɫ.
//@���� hWindow ���ھ��.
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� ����_�ò������ɫ()
XC_API void WINAPI XWnd_SetCaretColor(HWINDOW hWindow, COLORREF color);
//@��ע ��ʾ�����.
//@���� hWindow ���ھ��.
//@���� bShow �Ƿ���ʾ.
//@���� ����_��ʾ�����()
XC_API void WINAPI XWnd_ShowCaret(HWINDOW hWindow, BOOL bShow);
//@���� hWindow ���ھ��
//@���� ���ز����Ԫ��
//@���� ����_ȡ�����Ԫ��()
XC_API HELE WINAPI XWnd_GetCaretHELE(HWINDOW hWindow);
//@��ע ��ȡ�������Ϣ
//@���� hWindow ���ھ��
//@���� pX ���շ���x����
//@���� pY ���շ���y����
//@���� pWidth ���շ��ؿ��
//@���� pHeight ���շ��ظ߶�
//@���� �����Ԫ�ؾ��
//@���� ����_ȡ�������Ϣ()
XC_API HELE WINAPI XWnd_GetCaretInfo(HWINDOW hWindow, int* pX, int* pY, int* pWidth, int* pHeight);
//@��ע ���ٲ����.
//@���� hWindow ���ھ��.
//@���� ����_���ٲ����()
XC_API void WINAPI XWnd_DestroyCaret(HWINDOW hWindow);
//@��ע ��ȡ���ڿͻ�������;�������Ӱ����,��ô��������Ӱ����.
//@���� hWindow ���ھ��.
//@���� pRect ����.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ����_ȡ�ͻ�������()
XC_API BOOL WINAPI XWnd_GetClientRect(HWINDOW hWindow, RECT* pRect);
//@��ע ��ȡbody����.
//@���� hWindow ���ھ��.
//@���� pRect ����.
//@���� ����_ȡBody����()
XC_API void WINAPI XWnd_GetBodyRect(HWINDOW hWindow, RECT* pRect);
//@���� hWindow ���ھ��
//@���� pRect ���շ�������
//@���� ����_ȡ��������()
XC_API void WINAPI XWnd_GetLayoutRect(HWINDOW hWindow, RECT* pRect);
//@��ע �ƶ�����
//@���� hWindow ���ھ��
//@���� x X����
//@���� y Y����
//@���� ����_��λ��()
XC_API void WINAPI XWnd_SetPosition(HWINDOW hWindow, int x, int y);
//@��ע ��װ��ϵͳAPI: GetCursorPos(), �ڲ�����DPI����
//@���� hWindow ���ھ��
//@���� pPt ���շ��������
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ����_ȡ���λ��()
XC_API BOOL WINAPI XWnd_GetCursorPos(HWINDOW hWindow, POINT* pPt);
//@��ע ��װ��ϵͳAPI: ClientToScreen(), �ڲ�����DPI����
//@���� hWindow ���ھ��
//@���� pPt ���շ��������
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ����_�ͻ�������㵽��Ļ()
XC_API BOOL WINAPI XWnd_ClientToScreen(HWINDOW hWindow, POINT* pPt);
//@��ע ��װ��ϵͳAPI: ScreenToClient(), �ڲ�����DPI����
//@���� hWindow ���ھ��
//@���� pPt ���շ��������
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ����_��Ļ����㵽�ͻ���()
XC_API BOOL WINAPI XWnd_ScreenToClient(HWINDOW hWindow, POINT* pPt);
//@��ע ���ڿͻ�������ת�������ź�DPI����
//@���� hWindow ���ھ��
//@���� pRect ���շ�������
//@���� ����_����ת��DPI()
XC_API void WINAPI XWnd_RectToDPI(HWINDOW hWindow, RECT* pRect);
//@��ע ���ڿͻ��������ת�������ź�
//@���� hWindow ���ھ��
//@���� pPt ���շ��������
//@���� ����_�����ת��DPI()
XC_API void WINAPI XWnd_PointToDPI(HWINDOW hWindow, POINT* pPt);
//@���� hWindow ���ھ��
//@���� pRect ����
//@���� ����_ȡ����()
XC_API void WINAPI XWnd_GetRect(HWINDOW hWindow, RECT* pRect);
//@���� hWindow ���ھ��
//@���� pRect ����
//@���� ����_������()
XC_API void WINAPI XWnd_SetRect(HWINDOW hWindow, RECT* pRect);
//@���� hWindow ���ھ��
//@���� bTop   �Ƿ��ö�, TRUE�ö�, FALSEȡ���ö�
//@���� ����_�ö�()
XC_API void WINAPI XWnd_SetTop(HWINDOW hWindow, BOOL bTop = TRUE);
//@���� hWindow ���ھ��
//@���� bMaximize �Ƿ����
//@���� ����_���()
XC_API void WINAPI XWnd_MaxWindow(HWINDOW hWindow, BOOL bMaximize);
//@��ע ��װϵͳAPI SetWindowPos(), �ڲ�����DPI����
//@���� hWindow ���ھ��
//@���� hWndInsertAfter ǰ�洰��
//@���� X X����
//@���� Y Y����
//@���� cx ���
//@���� cy �߶�
//@���� uFlags ���ڴ�С�����Ͷ�λ��־, �����MSDN: https://learn.microsoft.com/zh-cn/windows/win32/api/winuser/nf-winuser-setwindowpos
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ����_�ô���λ��()
XC_API BOOL WINAPI XWnd_SetWindowPos(HWINDOW hWindow, HWND hWndInsertAfter, int X, int Y, int cx, int cy, UINT uFlags);
//@��ע ���ô��ڶ�ʱ��.
//@���� hWindow ���ھ��.
//@���� nIDEvent ��ʱ��ID.
//@���� uElapse ���ֵ,��λ����.
//@���� �μ�MSDN.
//@���� ����_�ö�ʱ��()
XC_API UINT WINAPI XWnd_SetTimer(HWINDOW hWindow, UINT nIDEvent, UINT uElapse);
//@��ע �رն�ʱ��.
//@���� hWindow ���ھ��.
//@���� nIDEvent ��ʱ��ID.
//@���� �μ�MSDN.
//@���� ����_�رն�ʱ��()
XC_API BOOL WINAPI XWnd_KillTimer(HWINDOW hWindow, UINT nIDEvent);
//@��ע �����Ųʴ��ڶ�ʱ��.
//@���� hWindow ���ھ��.
//@���� nIDEvent ��ʱ��ID.
//@���� uElapse ���ֵ,��λ����.
//@���� �ɹ�����TURE,���򷵻�FALSE.
//@���� ����_���Ųʶ�ʱ��()
XC_API BOOL WINAPI XWnd_SetXCTimer(HWINDOW hWindow, UINT nIDEvent, UINT uElapse);
//@��ע �ر��Ųʴ��ڶ�ʱ��.
//@���� hWindow ���ھ��.
//@���� nIDEvent ��ʱ��ID.
//@���� �ɹ�����TURE,���򷵻�FALSE.
//@���� ����_�ر��Ųʶ�ʱ��()
XC_API BOOL WINAPI XWnd_KillXCTimer(HWINDOW hWindow, UINT nIDEvent);
//@��ע ��ӱ������ݱ߿�
//@���� hWindow ���ھ��
//@���� nState ���״̬ �μ��ĵ�: API�ӿ�->���״̬
//@���� color ��ɫ.
//@���� width �߿�.
//@���� ����_��ӱ����߿�()
XC_API void WINAPI XWnd_AddBkBorder(HWINDOW hWindow, int nState, COLORREF color, int width);
//@��ע ��ӱ����������
//@���� hWindow ���ھ��
//@���� nState ���״̬ �μ��ĵ�: API�ӿ�->���״̬
//@���� color ��ɫ.
//@���� ����_��ӱ������()
XC_API void WINAPI XWnd_AddBkFill(HWINDOW hWindow, int nState, COLORREF color);
//@��ע ��ӱ�������ͼƬ
//@���� hWindow ���ھ��
//@���� nState ���״̬ �μ��ĵ�: API�ӿ�->���״̬
//@���� hImage ͼƬ���.
//@���� ����_��ӱ���ͼƬ()
XC_API void WINAPI XWnd_AddBkImage(HWINDOW hWindow, int nState, HIMAGE hImage);
//@���� hWindow ���ھ��
//@���� pText ���������ַ���
//@���� �������õı�����������
//@���� ����_�ñ���()
XC_API int WINAPI XWnd_SetBkInfo(HWINDOW hWindow, const wchar_t* pText);
//@��ע ��ȡ������������
//@���� hWindow ���ھ��
//@���� ���ر�����������.
//@���� ����_ȡ������������()
XC_API int WINAPI XWnd_GetBkInfoCount(HWINDOW hWindow);
//@��ע ��ձ�������; �������û������,��ʹ��ϵͳĬ������,�Ա㱣֤������ȷ
//@���� hWindow ���ھ��
//@���� ����_��ձ�������()
XC_API void WINAPI XWnd_ClearBkInfo(HWINDOW hWindow);
//@��ע ��ȡ����������.
//@���� hWindow ���ھ��.
//@���� ����������.
//@���� ����_ȡ����������()
XC_API HBKM WINAPI XWnd_GetBkManager(HWINDOW hWindow);
//@��ע ��ȡ����������,���ȴ���Դ�л�ȡ.
//@���� hWindow ���ھ��
//@���� ����������.
//@���� ����_ȡ������������չ()
XC_API HBKM WINAPI XWnd_GetBkManagerEx(HWINDOW hWindow);
//@���� hWindow ���ھ��
//@���� hBkInfoM ����������
//@���� ����_�ñ���������()
XC_API void WINAPI XWnd_SetBkMagager(HWINDOW hWindow, HBKM hBkInfoM);
//@��ע ����͸������,ͬʱ����ͨ���ú����ر�͸������;
//���������ֵĺ���Ӧ�÷��� XWnd_SetTransparentType() ֮�����.
//@���� hWindow ���ھ��.
//@���� nType ����͸������.
//@���� ����_��͸������()
XC_API void WINAPI XWnd_SetTransparentType(HWINDOW hWindow, window_transparent_ nType);
//@��ע ����͸�����ڵ�͸����,���ú�����ػ洰��API������.
//@���� hWindow ���ھ��.
//@���� alpha ����͸����,��Χ0-255֮��,0͸��,255��͸��.
//@���� ����_��͸����()
XC_API void WINAPI XWnd_SetTransparentAlpha(HWINDOW hWindow, BYTE alpha);
//@��ע ����͸�����ڵ�͸��ɫ.
//@���� hWindow ���ھ��.
//@���� color ����͸��ɫ.
//@���� ����_��͸��ɫ()
XC_API void WINAPI XWnd_SetTransparentColor(HWINDOW hWindow, COLORREF color);
//@��ע ���ô�����Ӱ��Ϣ.
//@���� hWindow ���ھ��.
//@���� nSize ��Ӱ��С
//@���� nDepth ��Ӱ���,0-255.
//@���� nAngeleSize Բ����Ӱ���մ�С.
//@���� bRightAngle �Ƿ�ǿ��ֱ��.
//@���� color ��Ӱ��ɫ.
//@���� ����_����Ӱ��Ϣ()
XC_API void WINAPI XWnd_SetShadowInfo(HWINDOW hWindow, int nSize, int nDepth, int nAngeleSize, BOOL bRightAngle, COLORREF color);
//@��ע ��ȡ������Ӱ��Ϣ.
//@���� hWindow ���ھ��.
//@���� pnSize ��Ӱ��С.
//@���� pnDepth ��Ӱ���(0-255), ��ɫ����ǳ
//@���� pnAngeleSize Բ����Ӱ���մ�С, ��ӰԲ�Ǵ�С
//@���� pbRightAngle �Ƿ�ǿ��ֱ��, Բ�ǻ�ֱ��
//@���� pColor ��Ӱ��ɫ.
//@���� ����_ȡ��Ӱ��Ϣ()
XC_API void WINAPI XWnd_GetShadowInfo(HWINDOW hWindow, int* pnSize, int* pnDepth, int* pnAngeleSize, BOOL* pbRightAngle, COLORREF* pColor);
//@��ע ��ȡ����͸������
//@���� hWindow ���ھ��.
//@���� ���ش���͸������.
//@���� ����_ȡ͸������()
XC_API window_transparent_ WINAPI XWnd_GetTransparentType(HWINDOW hWindow);
//@����}
//@����{  ģ̬����

//@��ע ����ģ̬����;��ģ̬���ڹر�ʱ,���Զ�����ģ̬������Դ���.
//@���� nWidth ���.
//@���� nHeight �߶�.
//@���� pTitle ���ڱ�������.
//@���� hWndParent �����ھ��.
//@���� XCStyle GUI�ⴰ����ʽ,��ʽ��μ��궨�� @ref window_style_.
//@���� ģ̬���ھ��.
//@���� ģ̬����_����()
XC_API HWINDOW WINAPI XModalWnd_Create(int nWidth, int nHeight, const wchar_t* pTitle, HWND hWndParent, int XCStyle=window_style_modal);
//@��ע ����ģ̬����,��ǿ����.
//@���� dwExStyle ������չ��ʽ.
//@���� dwStyle ������ʽ.
//@���� lpClassName ��������.
//@���� x �������Ͻ�x����.
//@���� y �������Ͻ�y����.
//@���� cx ���ڿ��.
//@���� cy ���ڸ߶�.
//@���� pTitle ������.
//@���� hWndParent ������.
//@���� XCStyle GUI�ⴰ����ʽ,��ʽ��μ��궨�� @ref window_style_.
//@���� GUI�ⴰ����Դ���.
//@���� ģ̬����_������չ()
XC_API HWINDOW WINAPI XModalWnd_CreateEx(DWORD dwExStyle, DWORD dwStyle, const wchar_t* lpClassName, int x, int y, int cx, int cy, const wchar_t* pTitle, HWND hWndParent, int XCStyle=window_style_modal);
//@���� hWnd Ҫ���ӵ��ⲿ���ھ��
//@���� XCStyle GUI�ⴰ����ʽ,��ʽ��μ��궨�� @ref window_style_
//@���� ���ش��ھ��
//@���� ģ̬����_���Ӵ���()
XC_API HWINDOW WINAPI XModalWnd_Attach(HWND hWnd, int XCStyle);
//@��ע �Ƿ��Զ��رմ���,������ʧȥ����ʱ.
//@���� hWindow ģ̬���ھ��.
//@���� bEnable ��������.
//@���� ģ̬����_�����Զ��ر�()
XC_API void WINAPI XModalWnd_EnableAutoClose(HWINDOW hWindow, BOOL bEnable);
//@��ע ���û���ESC��ʱ�Զ��ر�ģ̬����.
//@���� hWindow ģ̬���ھ��.
//@���� bEnable �Ƿ�����
//@���� ģ̬����_����ESC�ر�()
XC_API void WINAPI XModalWnd_EnableEscClose(HWINDOW hWindow, BOOL bEnable);
//@��ע ������ʾģ̬����,�����ڹر�ʱ����.
//@���� hWindow ģ̬���ھ��.
//@���� messageBox_flag_ok:���ȷ����ť�˳�.messageBox_flag_cancel:���ȡ����ť�˳�.messageBox_flag_other:������ʽ�˳�.
//@���� ģ̬����_����()
XC_API int WINAPI XModalWnd_DoModal(HWINDOW hWindow);
//@��ע ����ģ̬����.
//@���� hWindow ���ھ��.
//@���� nResult XModalWnd_DoModal() ����ֵ.
//@���� ģ̬����_����()
XC_API void WINAPI XModalWnd_EndModal(HWINDOW hWindow, int nResult);
//@����}
//@����{  ��ܴ���

//@��ע ������ܴ���
//@���� x �������Ͻ�x����.
//@���� y �������Ͻ�y����.
//@���� cx ���ڿ��.
//@���� cy ���ڸ߶�.
//@���� pTitle ���ڱ���.
//@���� hWndParent ������.
//@���� XCStyle GUI�ⴰ����ʽ,��ʽ��μ��궨�� @ref window_style_.
//@���� GUI�ⴰ����Դ���.
//@���� ��ܴ���_����()
XC_API HWINDOW WINAPI XFrameWnd_Create(int x, int y, int cx, int cy, const wchar_t* pTitle, HWND hWndParent, int XCStyle);
//@��ע ������ܴ���,��ǿ����.
//@���� dwExStyle ������չ��ʽ.
//@���� dwStyle ������ʽ
//@���� lpClassName ��������.
//@���� x �������Ͻ�x����.
//@���� y �������Ͻ�y����.
//@���� cx ���ڿ��.
//@���� cy ���ڸ߶�.
//@���� pTitle ������.
//@���� hWndParent ������.
//@���� XCStyle GUI�ⴰ����ʽ,��ʽ��μ��궨�� @ref window_style_.
//@���� GUI�ⴰ����Դ���.
//@���� ��ܴ���_������չ()
XC_API HWINDOW WINAPI XFrameWnd_CreateEx(DWORD dwExStyle, DWORD dwStyle, const wchar_t* lpClassName, int x, int y, int cx, int cy, const wchar_t* pTitle, HWND hWndParent, int XCStyle);
//@���� hWnd Ҫ���ӵ��ⲿ���ھ��
//@���� XCStyle GUI�ⴰ����ʽ,��ʽ��μ��궨�� @ref window_style_
//@���� ���ش��ھ��
//@���� ��ܴ���_���Ӵ���()
XC_API HWINDOW WINAPI XFrameWnd_Attach(HWND hWnd, int XCStyle);
//@��ע �������ִ������������,��������ͷ.
//@���� hWindow ���ھ��.
//@���� pRect ��������.
//@���� ��ܴ���_ȡ������������()
XC_API void WINAPI XFrameWnd_GetLayoutAreaRect(HWINDOW hWindow, RECT* pRect);
//@��ע ��ȡ��ܴ�������ͼ��������.
//@���� hWindow ���ھ��.
//@���� pRect ��������.
//@���� ��ܴ���_ȡ����ͼ����()
XC_API void WINAPI XFrameWnd_GetViewRect(HWINDOW hWindow, RECT* pRect);
//@��ע ��������ͼԪ��.
//@���� hWindow ���ھ��.
//@���� hEle Ԫ�ؾ��.
//@���� ��ܴ���_����ͼ()
XC_API void WINAPI XFrameWnd_SetView(HWINDOW hWindow, HELE hEle);
//@��ע ���ô���ָ�����ɫ.
//@���� hWindow ���ھ��
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� ��ܴ���_�ô���ָ�����ɫ()
XC_API void WINAPI XFrameWnd_SetPaneSplitBarColor(HWINDOW hWindow, COLORREF color);
//@���� hWindow ���ھ��
//@���� nWidth ���
//@���� ��ܴ���_�ô���ָ������()
XC_API void WINAPI XFrameWnd_SetPaneSplitBarWidth(HWINDOW hWindow, int nWidth);
//@���� hWindow ���ھ��
//@���� ���ظ�ָ������
//@���� ��ܴ���_ȡ����ָ������()
XC_API int WINAPI XFrameWnd_GetPaneSplitBarWidth(HWINDOW hWindow);
//@��ע ���ô�����TabBar�߶�
//@���� hWindow ���ھ��
//@���� nHeight �߶�
//@���� ��ܴ���_��TabBar���߶�()
XC_API void WINAPI XFrameWnd_SetTabBarHeight(HWINDOW hWindow, int nHeight);
//@���� hWindow ���ھ��
//@���� ���� �϶����� ������ܴ��ڵ�Ԫ���ʶ
//@���� ��ܴ���_ȡ�϶���������ͣ��λ�ñ�ʶ()
XC_API frameWnd_cell_type_ WINAPI XFrameWnd_GetDragFloatWndTopFlag(HWINDOW hWindow);
//@��ע ���ÿ�ܴ��� �������򲼾ֵ�����
//@���� hWindow ���ھ��
//@���� left ��߼��
//@���� top �ұ߼��
//@���� right �ұ߼��
//@���� bottom �ױ߼��
//@���� ��ܴ���_�ò�������()
XC_API void WINAPI XFrameWnd_SetLayoutMargin(HWINDOW hWindow, int left, int top, int right, int bottom);
//@��ע ���沼����Ϣ���ļ�.
//@���� hWindow ���ھ��.
//@���� pFileName �ļ���������ļ���Ϊ�գ���ʹ��Ĭ���ļ���frameWnd_layout.xml.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ��ܴ���_���沼�ֵ��ļ�()
XC_API BOOL WINAPI XFrameWnd_SaveLayoutToFile(HWINDOW hWindow, const wchar_t* pFileName);
//@��ע ���ز�����Ϣ�ļ�, ����ʧ��, ��Ҫ�ֶ���������ӵ���ܴ�����.
//@���� hWindow ���ھ��.
//@���� aPaneList ����������.
//@���� nPaneCount ��������.
//@���� pFileName �ļ���������ļ���Ϊ�գ���ʹ��Ĭ���ļ���frameWnd_layout.xml.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ��ܴ���_���ز�����Ϣ�ļ�()
XC_API BOOL WINAPI XFrameWnd_LoadLayoutFile(HWINDOW hWindow, HELE* aPaneList, int nEleCount, const wchar_t* pFileName);
//@��ע ��Ӵ��񵽿�ܴ���.
//@���� hWindow ���ھ��.
//@���� hPaneDest Ŀ�괰��.
//@���� hPaneNew ��ǰ����.
//@���� align ���뷽ʽ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ��ܴ���_��Ӵ���()
XC_API BOOL WINAPI XFrameWnd_AddPane(HWINDOW hWindow, HELE hPaneDest, HELE hPaneNew, pane_align_ align);
//@��ע �ϲ�����.
//@���� hWindow ���ھ��.
//@���� hPaneDest Ŀ�괰��.
//@���� hPaneNew ��ǰ����.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ��ܴ���_�ϲ�����()
XC_API BOOL WINAPI XFrameWnd_MergePane(HWINDOW hWindow, HELE hPaneDest, HELE hPaneNew);
//@����}
//@����{  �����˵�

//@��ע �����˵�.Ĭ�ϵ����˵����ڹرպ��Զ�����.
//@���� �˵����.
//@���� �˵�_����()
XC_API HMENUX WINAPI XMenu_Create();
//@��ע ��Ӳ˵���.
//@���� hMenu �˵����.
//@���� nID ��ID.
//@���� pText �ı�����.
//@���� nParentID ����ID.
//@���� nFlags ��ʶ�μ��궨�� @ref menu_item_flag_.
//@���� �˵�_�����()
XC_API void WINAPI XMenu_AddItem(HMENUX hMenu, int nID, const wchar_t* pText, int parentId=XC_ID_ROOT, int nFlags=0);
//@��ע ��Ӳ˵���.
//@���� hMenu �˵����.
//@���� nID ��ID.
//@���� pText �ı�����.
//@���� nParentID ����ID.
//@���� hIcon �˵���ͼ����.
//@���� nFlags ��ʶ�μ��궨�� @ref menu_item_flag_.
//@���� �˵�_�����ͼ��()
XC_API void WINAPI XMenu_AddItemIcon(HMENUX hMenu, int nID, const wchar_t* pText, int nParentID, HIMAGE hImage, int nFlags=0);
//@��ע ����˵���.
//@���� hMenu �˵����.
//@���� nID ��ID.
//@���� pText �ı�����.
//@���� nFlags ��ʶ�μ��궨�� @ref menu_item_flag_.
//@���� insertID ����λ��ID.
//@���� �˵�_������()
XC_API void WINAPI XMenu_InsertItem(HMENUX hMenu, int nID, const wchar_t* pText, int nFlags, int insertID);
//@��ע ����˵���.
//@���� hMenu �˵����.
//@���� nID ��ID.
//@���� pText �ı�����.
//@���� hIcon �˵���ͼ����.
//@���� nFlags ��ʶ�μ��궨�� @ref menu_item_flag_.
//@���� insertID ����λ��ID.
//@���� �˵�_������ͼ��()
XC_API void WINAPI XMenu_InsertItemIcon(HMENUX hMenu, int nID, const wchar_t* pText, HIMAGE hIcon, int nFlags, int insertID);
//@��ע ��ȡ��һ������.
//@���� hMenu �˵����.
//@���� nID ��ID.
//@���� ������ID.
//@���� �˵�_ȡ��һ������()
XC_API int WINAPI XMenu_GetFirstChildItem(HMENUX hMenu, int nID);
//@��ע ��ȡĩβ����.
//@���� hMenu �˵����.
//@���� nID ��ID.
//@���� ������ID.
//@���� �˵�_ȡĩβ����()
XC_API int WINAPI XMenu_GetEndChildItem(HMENUX hMenu, int nID);
//@��ע ��ȡ��һ���ֵ���.
//@���� hMenu �˵����.
//@���� nID ��ID.
//@���� ������ID.
//@���� �˵�_ȡ��һ���ֵ���()
XC_API int WINAPI XMenu_GetPrevSiblingItem(HMENUX hMenu, int nID);
//@��ע ��ȡ��һ���ֵ���.
//@���� hMenu �˵����.
//@���� nID ��ID.
//@���� ������ID.
//@���� �˵�_ȡ��һ���ֵ���()
XC_API int WINAPI XMenu_GetNextSiblingItem(HMENUX hMenu, int nID);
//@��ע ��ȡ����.
//@���� hMenu �˵����.
//@���� nID ��ID.
//@���� ������ID.
//@���� �˵�_ȡ����()
XC_API int WINAPI XMenu_GetParentItem(HMENUX hMenu, int nID);
//@��ע ��ȡ�˵������˵���.
//@���� hMenu �˵����
//@���� ���ز˵������
//@���� �˵�_ȡ�˵���()
XC_API HELE WINAPI XMenu_GetMenuBar(HMENUX hMenu);
//@��ע �����Ƿ��Զ����ٲ˵�.
//@���� hMenu �˵����.
//@���� bAuto �Ƿ��Զ�����.
//@���� �˵�_���Զ�����()
XC_API void WINAPI XMenu_SetAutoDestroy(HMENUX hMenu, BOOL bAuto);
//@��ע �Ƿ����û����Ʋ˵�����,�������XWM_MENU_DRAW_BACKGROUND��XE_MENU_DRAW_BACKGROUND�¼���Ч.
//@���� hMenu �˵����.
//@���� bEnable �Ƿ�����.
//@���� �˵�_�����û����Ʊ���()
XC_API void WINAPI XMenu_EnableDrawBackground(HMENUX hMenu, BOOL bEnable);
//@��ע �Ƿ����û����Ʋ˵���,�������XWM_MENU_DRAWITEM��XE_MENU_DRAWITEM�¼���Ч.
//@���� hMenu �˵����.
//@���� bEnable �Ƿ�����.
//@���� �˵�_�����û�������()
XC_API void WINAPI XMenu_EnableDrawItem(HMENUX hMenu, BOOL bEnable);
//@��ע �����˵�.
//������hParentWnd���ڽ��ղ˵�����Ϣ�¼�
//@���� hMenu �˵����.
//@���� hParentWnd �����ھ��.
//@���� x x����.
//@���� y y����.
//@���� hParentEle ��Ԫ�ؾ��,�����ֵ��ΪNULL,hParentEleԪ�ؽ����ղ˵���Ϣ�¼�,
//@���� nPosition ����λ��,�μ��궨��.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �˵�_����()
XC_API BOOL WINAPI XMenu_Popup(HMENUX hMenu, HWND hParentWnd, int x, int y, HELE hParentEle=NULL, menu_popup_position_ nPosition=menu_popup_position_left_top);
//@��ע ���ٲ˵�.
//@���� hMenu �˵����.
//@���� �˵�_����()
XC_API void WINAPI XMenu_DestroyMenu(HMENUX hMenu);
//@��ע �رղ˵�.
//@���� hMenu �˵����.
//@���� �˵�_�ر�()
XC_API void WINAPI XMenu_CloseMenu(HMENUX hMenu);
//@��ע ���ò˵�����ͼƬ.
//@���� hMenu �˵����.
//@���� hImage ͼƬ���.
//@���� �˵�_�ñ���ͼƬ()
XC_API void WINAPI XMenu_SetBkImage(HMENUX hMenu, HIMAGE hImage);
//@��ע �������ı�.
//@���� hMenu �˵����.
//@���� nID ��ID.
//@���� pText �ı�����.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �˵�_�����ı�()
XC_API BOOL WINAPI XMenu_SetItemText(HMENUX hMenu, int nID, const wchar_t* pText);
//@��ע ��ȡ���ı�.
//@���� hMenu �˵����.
//@���� nID ��ID.
//@���� �����ı�����
//@���� �˵�_ȡ���ı�()
XC_API const wchar_t* WINAPI XMenu_GetItemText(HMENUX hMenu, int nID);
//@��ע ��ȡ���ı�����,�������ַ�������ֹ��.
//@���� hMenu �˵����.
//@���� nID ��ID.
//@���� ����,�ַ�Ϊ��λ.
//@���� �˵�_ȡ���ı�����()
XC_API int WINAPI XMenu_GetItemTextLength(HMENUX hMenu, int nID);
//@��ע ���ò˵���ͼ��.
//@���� hMenu �˵����.
//@���� nID ��ID.
//@���� hIcon �˵���ͼ����.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �˵�_����ͼ��()
XC_API BOOL WINAPI XMenu_SetItemIcon(HMENUX hMenu, int nID, HIMAGE hIcon);
//@��ע �������ʶ.
//@���� hMenu �˵����.
//@���� nID ��ID.
//@���� uFlags ��ʶ�μ��궨�� @ref menu_item_flag_.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �˵�_�����־()
XC_API BOOL WINAPI XMenu_SetItemFlags(HMENUX hMenu, int nID, int uFlags);
//@��ע ������߶�.
//@���� hMenu �˵����.
//@���� height �߶�.
//@���� �˵�_����߶�()
XC_API void WINAPI XMenu_SetItemHeight(HMENUX hMenu, int height);
//@��ע ��ȡ��߶�.
//@���� hMenu �˵����.
//@���� ������߶�.
//@���� �˵�_ȡ��߶�()
XC_API int WINAPI XMenu_GetItemHeight(HMENUX hMenu);
//@��ע �˿��Ϊ�ı���ʾ������, ����������������ı����
//@���� hMenu �˵����
//@���� nID ��ID
//@���� nWidth ָ���ı�������
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� �˵�_������()
XC_API BOOL WINAPI XMenu_SetItemWidth(HMENUX hMenu, int nID, int nWidth);
//@��ע ���ò˵��߿���ɫ.
//@���� hMenu �˵����.
//@���� crColor ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� �˵�_�ñ߿���ɫ()
XC_API void WINAPI XMenu_SetBorderColor(HMENUX hMenu, COLORREF crColor);
//@��ע ���õ����˵����ڱ߿��С.
//@���� hMenu �˵����.
//@���� nLeft �ߴ�С.
//@���� nTop �ߴ�С.
//@���� nRight �ߴ�С.
//@���� nBottom �ߴ�С.
//@���� �˵�_�ñ߿��С()
XC_API void WINAPI XMenu_SetBorderSize(HMENUX hMenu, int nLeft, int nTop, int nRight, int nBottom);
//@��ע ��ȡ���������.
//@���� hMenu �˵����.
//@���� �������������.
//@���� �˵�_ȡ�����()
XC_API int WINAPI XMenu_GetLeftWidth(HMENUX hMenu);
//@��ע ��ȡ�˵����ı�����.
//@���� hMenu �˵����.
//@���� ���ز˵����ļ�������С.
//@���� �˵�_ȡ����ı����()
XC_API int WINAPI XMenu_GetLeftSpaceText(HMENUX hMenu);
//@��ע ��ȡ�˵�������,�����Ӳ˵���.
//@���� hMenu �˵����.
//@���� �˵�������.
//@���� �˵�_ȡ������()
XC_API int WINAPI XMenu_GetItemCount(HMENUX hMenu);
//@��ע ���ò˵��ѡ״̬.
//@���� hMenu �˵����.
//@���� nID �˵���ID
//@���� bCheck ��ѡTRUE,�ǹ�ѡFALSE
//@���� �����ѡ����TRUE,���򷵻�FALSE.
//@���� �˵�_���ѡ()
XC_API BOOL WINAPI XMenu_SetItemCheck(HMENUX hMenu, int nID, BOOL bCheck);
//@��ע �жϲ˵����Ƿ�ѡ.
//@���� hMenu �˵����.
//@���� nID �˵���ID
//@���� �����ѡ����TRUE,���򷵻�FALSE.
//@���� �˵�_�Ƿ��ѡ()
XC_API BOOL WINAPI XMenu_IsItemCheck(HMENUX hMenu, int nID);
//@����}
//@����{  ���ֺ���

//@���� hLayoutBox ���ڻ򲼾�Ԫ�ػ򲼾ֿ�ܾ��
//@���� bEnable �Ƿ�����
//@���� ���ֺ���_����ˮƽ()
XC_API void WINAPI XLayoutBox_EnableHorizon(HXCGUI hLayoutBox, BOOL bEnable);
//@���� hLayoutBox ���ڻ򲼾�Ԫ�ػ򲼾ֿ�ܾ��
//@���� bEnable �Ƿ�����
//@���� ���ֺ���_�����Զ�����()
XC_API void WINAPI XLayoutBox_EnableAutoWrap(HXCGUI hLayoutBox, BOOL bEnable);
//@���� hLayoutBox ���ڻ򲼾�Ԫ�ػ򲼾ֿ�ܾ��
//@���� bEnable �Ƿ�����
//@���� ���ֺ���_�����������()
XC_API void WINAPI XLayoutBox_EnableOverflowHide(HXCGUI hLayoutBox, BOOL bEnable);
//@���� hLayoutBox ���ڻ򲼾�Ԫ�ػ򲼾ֿ�ܾ��
//@���� nAlign ���뷽ʽ
//@���� ���ֺ���_��ˮƽ����()
XC_API void WINAPI XLayoutBox_SetAlignH(HXCGUI hLayoutBox, layout_align_ nAlign);
//@���� hLayoutBox ���ڻ򲼾�Ԫ�ػ򲼾ֿ�ܾ��
//@���� nAlign ���뷽ʽ
//@���� ���ֺ���_�ô�ֱ����()
XC_API void WINAPI XLayoutBox_SetAlignV(HXCGUI hLayoutBox, layout_align_ nAlign);
//@���� hLayoutBox ���ڻ򲼾�Ԫ�ػ򲼾ֿ�ܾ��
//@���� nAlign ���뷽ʽ
//@���� ���ֺ���_�ö������()
XC_API void WINAPI XLayoutBox_SetAlignBaseline(HXCGUI hLayoutBox, layout_align_axis_ nAlign);
//@���� hLayoutBox ���ڻ򲼾�Ԫ�ػ򲼾ֿ�ܾ��
//@���� nSpace �����С
//@���� ���ֺ���_�ü��()
XC_API void WINAPI XLayoutBox_SetSpace(HXCGUI hLayoutBox, int nSpace);
//@���� hLayoutBox ���ڻ򲼾�Ԫ�ػ򲼾ֿ�ܾ��
//@���� nSpace �м���С
//@���� ���ֺ���_���о�()
XC_API void WINAPI XLayoutBox_SetSpaceRow(HXCGUI hLayoutBox, int nSpace);
//@����}
//@����{  ����Ԫ��

//@��ע ��������Ԫ��.
//@���� x Ԫ��x����.
//@���� y Ԫ��y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ��Ϊ���ھ����Ԫ�ؾ��.
//@���� Ԫ�ؾ��.
//@���� Ԫ��_����()
XC_API HELE WINAPI XEle_Create(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@��ע ע���¼�C��ʽ,�¼�����ʡ��������.
//@���� hEle Ԫ�ؾ��.
//@���� nEvent �¼�����.
//@���� pFun �¼�����ָ��.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� Ԫ��_ע���¼�C()
XC_API BOOL WINAPI XEle_RegEventC(HELE hEle, int nEvent, void* pFun);
//@��ע ע���¼�C1��ʽ,�¼���������������.
//@���� hEle Ԫ�ؾ��.
//@���� nEvent �¼�����.
//@���� pFun �¼�����ָ��.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� Ԫ��_ע���¼�C1()
XC_API BOOL WINAPI XEle_RegEventC1(HELE hEle, int nEvent, void* pFun);
//@��ע �Ƴ��¼�����C��ʽ.
//@���� hEle Ԫ�ؾ��.
//@���� nEvent �¼�����.
//@���� pFun �¼�����ָ��.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� Ԫ��_�Ƴ��¼�C()
XC_API BOOL WINAPI XEle_RemoveEventC(HELE hEle, int nEvent, void* pFun);
//@��ע �����¼�.
//@���� hEle Ŀ��Ԫ�ؾ��.
//@���� nEvent �¼�����.
//@���� wParam ����.
//@���� lParam ����.
//@���� �¼�����ֵ.
//@���� Ԫ��_�����¼�()
XC_API int WINAPI XEle_SendEvent(HELE hEle, int nEvent, WPARAM wParam, LPARAM lParam);
//@��ע POST�¼�.
//@���� hEle Ԫ�ؾ��.
//@���� nEvent �¼�����.
//@���� wParam ����.
//@���� lParam ����.
//@���� �¼�����ֵ.
//@���� Ԫ��_Ͷ���¼�()
XC_API BOOL WINAPI XEle_PostEvent(HELE hEle, int nEvent, WPARAM wParam, LPARAM lParam);
//@��ע ��ȡԪ������.
//@���� hEle Ԫ�ؾ��.
//@���� pRect ����.
//@���� Ԫ��_ȡ����()
XC_API void WINAPI XEle_GetRect(HELE hEle, RECT* pRect);
//@��ע ��ȡԪ������,�߼�����,����������ͼƫ��.
//@���� hEle Ԫ�ؾ��.
//@���� pRect ����.
//@���� Ԫ��_ȡ�߼�����()
XC_API void WINAPI XEle_GetRectLogic(HELE hEle, RECT* pRect);
//@��ע ��ȡԪ�ؿͻ�������.
//@���� hEle Ԫ�ؾ��.
//@���� pRect ����.
//@���� Ԫ��_ȡ�ͻ�������()
XC_API void WINAPI XEle_GetClientRect(HELE hEle, RECT* pRect);
//@��ע ���ÿ��
//@���� hEle Ԫ�ؾ��
//@���� nWidth ���
//@���� Ԫ��_�ÿ��()
XC_API void WINAPI XEle_SetWidth(HELE hEle, int nWidth);
//@��ע ���ø߶�
//@���� hEle Ԫ�ؾ��
//@���� nHeight �߶�
//@���� Ԫ��_�ø߶�()
XC_API void WINAPI XEle_SetHeight(HELE hEle, int nHeight);
//@��ע ��ȡԪ�ؿ��.
//@���� hEle Ԫ�ؾ��.
//@���� ���.
//@���� Ԫ��_ȡ���()
XC_API int WINAPI XEle_GetWidth(HELE hEle);
//@��ע ��ȡԪ�ظ߶�.
//@���� hEle Ԫ�ؾ��.
//@���� �߶�.
//@���� Ԫ��_ȡ�߶�()
XC_API int WINAPI XEle_GetHeight(HELE hEle);
//@��ע ���ڿͻ�������ת����Ԫ�ؿͻ�������.
//@���� hEle Ԫ�ؾ��.
//@���� pRect ����.
//@���� Ԫ��_���ڿͻ������굽Ԫ�ؿͻ���()
XC_API void WINAPI XEle_RectWndClientToEleClient(HELE hEle, RECT* pRect);
//@��ע ���ڿͻ�������ת����Ԫ�ؿͻ�������.
//@���� hEle Ԫ�ؾ��.
//@���� pPt ����.
//@���� Ԫ��_���ڿͻ����㵽Ԫ�ؿͻ���()
XC_API void WINAPI XEle_PointWndClientToEleClient(HELE hEle, POINT* pPt);
//@��ע Ԫ�ؿͻ�������ת�������ڿͻ�������.
//@���� hEle Ԫ�ؾ��.
//@���� pRect ����.
//@���� Ԫ��_�ͻ������굽���ڿͻ���()
XC_API void WINAPI XEle_RectClientToWndClient(HELE hEle, RECT* pRect);
//@��ע ���ź�����
//@���� hEle Ԫ�ؾ��
//@���� pRect ���շ�������
//@���� Ԫ��_�ͻ������굽���ڿͻ���DPI()
XC_API void WINAPI XEle_RectClientToWndClientDPI(HELE hEle, RECT* pRect);
//@��ע Ԫ�ؿͻ�������ת�������ڿͻ�������.
//@���� hEle Ԫ�ؾ��.
//@���� pPt ���շ��������.
//@���� Ԫ��_�ͻ����㵽���ڿͻ���()
XC_API void WINAPI XEle_PointClientToWndClient(HELE hEle, POINT* pPt);
//@��ע ���ź������
//@���� hEle Ԫ�ؾ��
//@���� pPt ���շ��������
//@���� Ԫ��_�ͻ����㵽���ڿͻ���DPI()
XC_API void WINAPI XEle_PointClientToWndClientDPI(HELE hEle, POINT* pPt);
//@��ע Ԫ��ȡ���ڿͻ�������, 100%��������
//@���� hEle Ԫ�ؾ��
//@���� pRect ��������
//@���� Ԫ��_ȡ���ڿͻ�������()
XC_API void WINAPI XEle_GetWndClientRect(HELE hEle, RECT* pRect);
//@��ע ����DPI���ź������
//@���� hEle Ԫ�ؾ��
//@���� pRect ��������
//@���� Ԫ��_ȡ���ڿͻ�������DPI()
XC_API void WINAPI XEle_GetWndClientRectDPI(HELE hEle, RECT* pRect);
//@��ע ��ȡԪ�������.
//@���� hEle Ԫ�ؾ��.
//@���� ���ع����.
//@���� Ԫ��_ȡ���()
XC_API HCURSOR WINAPI XEle_GetCursor(HELE hEle);
//@��ע ����Ԫ�������.
//@���� hEle Ԫ�ؾ��.
//@���� hCursor �����.
//@���� Ԫ��_�ù��()
XC_API void WINAPI XEle_SetCursor(HELE hEle, HCURSOR hCursor);
//@��ע ����Ӷ���.
//@���� hEle Ԫ�ؾ��.
//@���� hChild Ҫ��ӵ���Ԫ�ؾ������״������.
//@���� ����ɹ�����TRUE,�����෴.
//@���� Ԫ��_����Ӷ���()
XC_API BOOL WINAPI XEle_AddChild(HELE hEle, HXCGUI hChild);
//@��ע �����Ӷ���ָ��λ��.
//@���� hEle Ԫ�ؾ��.
//@���� hChild Ҫ�����Ԫ�ؾ������״������.
//@���� index ����λ������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� Ԫ��_�����Ӷ���()
XC_API BOOL WINAPI XEle_InsertChild(HELE hEle, HXCGUI hChild, int index);
//@��ע ����Ԫ������.
//@���� hEle Ԫ�ؾ��.
//@���� pRect ����.
//@���� bRedraw �Ƿ��ػ�.
//@���� nFlags �������ֱ�ʶλ, @ref adjustLayout_
//@���� nAdjustNo ����������ˮ��
//@���� �������0����û�иı�,�����С�ı䷵��2(����XE_SIZE), ���򷵻�1(���ı�left,top,û�иı��С).
//@���� Ԫ��_������()
XC_API int WINAPI XEle_SetRect(HELE hEle, RECT* pRect, BOOL bRedraw=FALSE, int nFlags=adjustLayout_all, UINT nAdjustNo=0);
//@��ע ����Ԫ������.
//@���� hEle Ԫ�ؾ��.
//@���� x X����.
//@���� y Y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� bRedraw �Ƿ��ػ�.
//@���� nFlags �������ֱ�ʶλ, @ref adjustLayout_
//@���� nAdjustNo ����������ˮ��
//@���� �������δ�ı䷵��0,�����С�ı䷵��2(����XE_SIZE), ���򷵻�1.
//@���� Ԫ��_��������չ()
XC_API int WINAPI XEle_SetRectEx(HELE hEle, int x, int y, int cx, int cy, BOOL bRedraw=FALSE, int nFlags=adjustLayout_all, UINT nAdjustNo=0);
//@��ע ����Ԫ������,�߼�����,����������ͼƫ��.
//@���� hEle Ԫ�ؾ��.
//@���� pRect ����.
//@���� bRedraw �Ƿ��ػ�.
//@���� nFlags ������������ XE_SIZE ,XE_ADJUSTLAYOUT �¼��ص�.@ref adjustLayout_
//@���� nAdjustNo ����������ˮ��
//@���� �������δ�ı䷵��0,�����С�ı䷵��2(����XE_SIZE), ���򷵻�1.
//@���� Ԫ��_���߼�����()
XC_API int WINAPI XEle_SetRectLogic(HELE hEle, RECT* pRect, BOOL bRedraw=FALSE, int nFlags=adjustLayout_all, UINT nAdjustNo=0);
//@��ע �ƶ�Ԫ������
//@���� hEle Ԫ�ؾ��.
//@���� x X����.
//@���� y Y����.
//@���� bRedraw �Ƿ��ػ�.
//@���� nFlags �������ֱ�ʶλ, @ref adjustLayout_
//@���� nAdjustNo ����������ˮ��
//@���� �������δ�ı䷵��0,�����С�ı䷵��2(����XE_SIZE), ���򷵻�1.
//@���� Ԫ��_��λ��()
XC_API int WINAPI XEle_SetPosition(HELE hEle, int x, int y, BOOL bRedraw=FALSE, int nFlags=adjustLayout_all, UINT nAdjustNo=0);
//@��ע �ƶ�Ԫ������;  �߼�����,����������ͼƫ��
//@���� hEle Ԫ�ؾ��.
//@���� x X����.
//@���� y Y����.
//@���� bRedraw �Ƿ��ػ�.
//@���� nFlags �������ֱ�ʶλ, @ref adjustLayout_
//@���� nAdjustNo ����������ˮ��
//@���� �������δ�ı䷵��0,�����С�ı䷵��2(����XE_SIZE), ���򷵻�1.
//@���� Ԫ��_��λ���߼�()
XC_API int WINAPI XEle_SetPositionLogic(HELE hEle, int x, int y, BOOL bRedraw=FALSE, int nFlags=adjustLayout_all, UINT nAdjustNo=0);
//@���� hEle Ԫ�ؾ��
//@���� pOutX ����X����
//@���� pOutY ����Y����
//@���� Ԫ��_ȡλ��()
XC_API void WINAPI XEle_GetPosition(HELE hEle, int* pOutX, int* pOutY);
//@���� hEle Ԫ�ؾ��
//@���� nWidth ���
//@���� nHeight �߶�
//@���� bRedraw �Ƿ��ػ�
//@���� nFlags �������ֱ�ʶλ, @ref adjustLayout_
//@���� nAdjustNo ����������ˮ��
//@���� Ԫ��_�ô�С()
XC_API int WINAPI XEle_SetSize(HELE hEle, int nWidth, int nHeight, BOOL bRedraw=FALSE, int nFlags=adjustLayout_all, UINT nAdjustNo=0);
//@���� hEle Ԫ�ؾ��
//@���� pOutWidth ���ؿ��
//@���� pOutHeight ���ظ߶�
//@���� Ԫ��_ȡ��С()
XC_API void WINAPI XEle_GetSize(HELE hEle, int* pOutWidth, int* pOutHeight);
//@��ע Ԫ���Ƿ���ƽ���.
//@���� hEle Ԫ�ؾ��.
//@���� ������ƽ��㷵��TRUE���򷵻�FALSE.
//@���� Ԫ��_�Ƿ���ƽ���()
XC_API BOOL WINAPI XEle_IsDrawFocus(HELE hEle);
//@��ע Ԫ���Ƿ�Ϊ����״̬.
//@���� hEle Ԫ�ؾ��.
//@���� �������״̬����TRUE���򷵻�FALSE.
//@���� Ԫ��_�Ƿ�����()
XC_API BOOL WINAPI XEle_IsEnable(HELE hEle);
//@��ע Ԫ���Ƿ����ý���.
//@���� hEle Ԫ�ؾ��.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� Ԫ��_�Ƿ����ý���()
XC_API BOOL WINAPI XEle_IsEnableFocus(HELE hEle);
//@��ע Ԫ���Ƿ�������괩͸.
//@���� hEle Ԫ�ؾ��.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� Ԫ��_�Ƿ���괩͸()
XC_API BOOL WINAPI XEle_IsMouseThrough(HELE hEle);
//@��ע ������������Ԫ��,������Ԫ�ص���Ԫ��.
//@���� hEle Ԫ�ؾ��.
//@���� pPt �����.
//@���� �ɹ�����Ԫ�ؾ��,���򷵻�NULL.
//@���� Ԫ��_���Ե��Ԫ��()
XC_API HELE WINAPI XEle_HitChildEle(HELE hEle, POINT* pPt);
//@��ע �Ƿ񱳾�͸��.
//@���� hEle Ԫ�ؾ��.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� Ԫ��_�Ƿ񱳾�͸��()
XC_API BOOL WINAPI XEle_IsBkTransparent(HELE hEle);
//@��ע �Ƿ���XE_PAINT_END���¼�.
//@���� hEle Ԫ�ؾ��.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� Ԫ��_�Ƿ������¼�_XE_PAINT_END()
XC_API BOOL WINAPI XEle_IsEnableEvent_XE_PAINT_END(HELE hEle);
//@��ע �Ƿ����Tab������; ����: XRichEdit, XEdit
//@���� hEle Ԫ�ؾ��
//@���� �Ƿ���TRUE���򷵻�FALSE
//@���� Ԫ��_�Ƿ����TAB()
XC_API BOOL WINAPI XEle_IsKeyTab(HELE hEle);
//@��ע �Ƿ����ͨ�������л�����(�����,TAB��).
//@���� hEle Ԫ�ؾ��.
//@���� �Ƿ���TRUE���򷵻�FALSE.
//@���� Ԫ��_�Ƿ�����л�����()
XC_API BOOL WINAPI XEle_IsSwitchFocus(HELE hEle);
//@��ע �ж��Ƿ������������¼�,���������ô�¼��ᷢ�͸����ĸ�Ԫ��.
//@���� hEle Ԫ�ؾ��.
//@���� �Ƿ���TRUE���򷵻�FALSE.
//@���� Ԫ��_�Ƿ�����_XE_MOUSEWHEEL()
XC_API BOOL WINAPI XEle_IsEnable_XE_MOUSEWHEEL(HELE hEle);
//@��ע �ж�hChildEle�Ƿ�ΪhEle����Ԫ��,
//@���� hEle Ԫ�ؾ��.
//@���� hChildEle ��Ԫ�ؾ��
//@���� �Ƿ���TRUE���򷵻�FALSE.
//@���� Ԫ��_�Ƿ�Ϊ��Ԫ��()
XC_API BOOL WINAPI XEle_IsChildEle(HELE hEle, HELE hChildEle);
//@��ע �ж��Ƿ����û���,
//@���� hEle Ԫ�ؾ��.
//@���� �Ƿ���TRUE���򷵻�FALSE.
//@���� Ԫ��_�Ƿ����û���()
XC_API BOOL WINAPI XEle_IsEnableCanvas(HELE hEle);
//@��ע �ж��Ƿ�ӵ�н���.
//@���� hEle Ԫ�ؾ��.
//@���� ���ӵ�н��㷵��TRUE,���򷵻�FALSE.
//@���� Ԫ��_�Ƿ񽹵�()
XC_API BOOL WINAPI XEle_IsFocus(HELE hEle);
//@��ע �жϸ�Ԫ�ػ��Ԫ�ص���Ԫ���Ƿ�ӵ�н���
//@���� hEle Ԫ�ؾ��.
//@���� ���ӵ�н��㷵��TRUE,���򷵻�FALSE.
//@���� Ԫ��_�Ƿ񽹵���չ()
XC_API BOOL WINAPI XEle_IsFocusEx(HELE hEle);
//@��ע ���û����Ԫ��.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable ���û����.
//@���� Ԫ��_����()
XC_API void WINAPI XEle_Enable(HELE hEle, BOOL bEnable);
//@��ע ���ý���.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� Ԫ��_���ý���()
XC_API void WINAPI XEle_EnableFocus(HELE hEle, BOOL bEnable);
//@��ע ���û��ƽ���.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� Ԫ��_���û��ƽ���()
XC_API void WINAPI XEle_EnableDrawFocus(HELE hEle, BOOL bEnable);
//@��ע ���û���û���Ĭ�ϱ߿�.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� Ԫ��_���û��Ʊ߿�()
XC_API void WINAPI XEle_EnableDrawBorder(HELE hEle, BOOL bEnable);
//@��ע ���û���ñ�������;���������ô�������ڸ��Ļ���֮��,Ҳ����˵��û���Լ��Ļ���.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� Ԫ��_���û���()
XC_API void WINAPI XEle_EnableCanvas(HELE hEle, BOOL bEnable);
//@��ע ����XE_PAINT_END�¼�.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� Ԫ��_�����¼�_XE_PAINT_END()
XC_API void WINAPI XEle_EnableEvent_XE_PAINT_END(HELE hEle, BOOL bEnable);
//@��ע ���ñ���͸��.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� Ԫ��_���ñ���͸��()
XC_API void WINAPI XEle_EnableBkTransparent(HELE hEle, BOOL bEnable);
//@��ע ������괩͸, �������,��ô��Ԫ�ز��ܽ��յ�����¼�,����������Ԫ�ز���Ӱ��,��Ȼ���Խ�������¼�.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� Ԫ��_������괩͸()
XC_API void WINAPI XEle_EnableMouseThrough(HELE hEle, BOOL bEnable);
//@��ע ���ý���Tab����.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� Ԫ��_���ý���TAB()
XC_API void WINAPI XEle_EnableKeyTab(HELE hEle, BOOL bEnable);
//@��ע ���ý���ͨ�������л�����.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� Ԫ��_�����л�����()
XC_API void WINAPI XEle_EnableSwitchFocus(HELE hEle, BOOL bEnable);
//@��ע ���ý����������¼�,���������ô�¼��ᴫ�ݸ���Ԫ��.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� Ԫ��_�����¼�_XE_MOUSEWHEEL()
XC_API void WINAPI XEle_EnableEvent_XE_MOUSEWHEEL(HELE hEle, BOOL bEnable);
//@��ע �Ƴ�Ԫ��,��������.
//@���� hEle Ԫ�ؾ��.
//@���� Ԫ��_�Ƴ�()
XC_API void WINAPI XEle_Remove(HELE hEle);
//@��ע ����Ԫ��Z��.
//@���� hEle Ԫ�ؾ��.
//@���� index λ������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� Ԫ��_��Z��()
XC_API BOOL WINAPI XEle_SetZOrder(HELE hEle, int index);
//@��ע ����Ԫ��Z��.
//@���� hEle Ԫ�ؾ��.
//@���� hDestEle Ŀ��Ԫ��.
//@���� nType ����.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� Ԫ��_��Z����չ()
XC_API BOOL WINAPI XEle_SetZOrderEx(HELE hEle, HELE hDestEle, zorder_ nType);
//@��ע ��ȡԪ��Z������, λ������.
//@���� hEle Ԫ�ؾ��.
//@���� �ɹ���������ֵ,���򷵻� XC_ID_ERROR.
//@���� Ԫ��_ȡZ��()
XC_API int WINAPI XEle_GetZOrder(HELE hEle);
//@��ע ����Ԫ���ö�.
//@���� hEle Ԫ�ؾ��.
//@���� bTopmost �Ƿ��ö���ʾ
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� Ԫ��_�����ö�()
XC_API BOOL WINAPI XEle_EnableTopmost(HELE hEle, BOOL bTopmost);
//@��ע �ػ�Ԫ��.
//@���� hEle Ԫ�ؾ��.
//@���� bImmediate �Ƿ������ػ�,Ĭ��Ϊ��.
//@���� Ԫ��_�ػ�()
XC_API void WINAPI XEle_Redraw(HELE hEle, BOOL bImmediate=FALSE);
//@��ע �ػ�Ԫ��ָ������.
//@���� hEle Ԫ�ؾ��.
//@���� pRect �����Ԫ�ؿͻ�������.
//@���� bImmediate �Ƿ������ػ�,Ĭ��Ϊ��.
//@���� Ԫ��_�ػ�ָ������()
XC_API void WINAPI XEle_RedrawRect(HELE hEle, RECT* pRect, BOOL bImmediate=FALSE);
//@��ע ��ȡ�Ӷ���(UIԪ�غ���״����)����,ֻ��⵱ǰ���Ӷ���.
//@���� hEle Ԫ�ؾ��.
//@���� ��Ԫ������.
//@���� Ԫ��_ȡ�Ӷ�������()
XC_API int WINAPI XEle_GetChildCount(HELE hEle);
//@��ע ��ȡ�Ӷ���ͨ������,ֻ��⵱ǰ���Ӷ���.
//@���� hEle Ԫ�ؾ��.
//@���� index ����.
//@���� ������.
//@���� Ԫ��_ȡ�Ӷ��������()
XC_API HXCGUI WINAPI XEle_GetChildByIndex(HELE hEle, int index);
//@��ע ��ȡ�Ӷ���ͨ��ID,ֻ��⵱ǰ���Ӷ���.
//@���� hEle Ԫ�ؾ��.
//@���� nID Ԫ��ID.
//@���� ������.
//@���� Ԫ��_ȡ�Ӷ����ID()
XC_API HXCGUI WINAPI XEle_GetChildByID(HELE hEle, int nID);
//@��ע ���ñ߿��С.
//@���� hEle Ԫ�ؾ��.
//@���� left ��ߴ�С.
//@���� top �ϱߴ�С.
//@���� right �ұߴ�С.
//@���� bottom �±ߴ�С.
//@���� Ԫ��_�ñ߿��С()
XC_API void WINAPI XEle_SetBorderSize(HELE hEle, int left, int top, int right, int bottom);
//@��ע ��ȡ�߿��С.
//@���� hEle Ԫ�ؾ��.
//@���� pBorder ��С.
//@���� Ԫ��_ȡ�߿��С()
XC_API void WINAPI XEle_GetBorderSize(HELE hEle, borderSize_* pBorder);
//@��ע ���ñ�����С.
//@���� hEle Ԫ�ؾ��.
//@���� left ��ߴ�С.
//@���� top �ϱߴ�С.
//@���� right �ұߴ�С.
//@���� bottom �±ߴ�С.
//@���� Ԫ��_��������С()
XC_API void WINAPI XEle_SetPadding(HELE hEle, int left, int top, int right, int bottom);
//@��ע ��ȡ������С.
//@���� hEle Ԫ�ؾ��.
//@���� pPadding ��С.
//@���� Ԫ��_ȡ������С()
XC_API void WINAPI XEle_GetPadding(HELE hEle, paddingSize_* pPadding);
//@��ע �����϶��߿�.
//@���� hEle Ԫ�ؾ��.
//@���� nFlags �߿�λ�����. element_position_
//@���� Ԫ��_���϶��߿�()
XC_API void WINAPI XEle_SetDragBorder(HELE hEle, int nFlags);
//@��ע �����϶��߿��Ԫ��, ���� @ref XEle_SetDragBorder() ���ö�Ӧ�ı�, ���϶��߿�ʱ, �Զ�������Ԫ�صĴ�С, ÿ���߿ɰ�һ��Ԫ��.
//@���� hEle Ԫ�ؾ��.
//@���� nFlags �߿�λ�ñ�ʶ,�������.  element_position_
//@���� hBindEle ��Ԫ��.
//@���� nSpace Ԫ�ؼ����С
//@���� Ԫ��_���϶��߿��Ԫ��()
XC_API void WINAPI XEle_SetDragBorderBindEle(HELE hEle, int nFlags, HELE hBindEle, int nSpace);
//@��ע ����Ԫ����С��С. ���û�����϶�Ԫ�ر߿�ʱ���ƴ�С, ��ͬ�ڲ��������С��С
//@���� hEle Ԫ�ؾ��.
//@���� nWidth ��С���
//@���� nHeight ��С�߶�.
//@���� Ԫ��_����С��С()
XC_API void WINAPI XEle_SetMinSize(HELE hEle, int nWidth, int nHeight);
//@��ע ����Ԫ������С. ���û�����϶�Ԫ�ر߿�ʱ���ƴ�С
//@���� hEle Ԫ�ؾ��.
//@���� nWidth �����.
//@���� nHeight ���߶�.
//@���� Ԫ��_������С()
XC_API void WINAPI XEle_SetMaxSize(HELE hEle, int nWidth, int nHeight);
//@��ע ��������Ԫ��, ʹ���ڹ�����ͼ�н�ֹ�������, �������TRUE�����������.
//@���� hEle Ԫ�ؾ��.
//@���� bHorizon �Ƿ�����ˮƽ����.
//@���� bVertical �Ƿ�������ֱ����.
//@���� Ԫ��_����������()
XC_API void WINAPI XEle_SetLockScroll(HELE hEle, BOOL bHorizon, BOOL bVertical);
//@��ע �����ı���ɫ.
//@���� hEle Ԫ�ؾ��.
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� Ԫ��_���ı���ɫ()
XC_API void WINAPI XEle_SetTextColor(HELE hEle, COLORREF color);
//@��ע ��ȡ�ı���ɫ.
//@���� hEle Ԫ�ؾ��.
//@���� �ı���ɫֵ.
//@���� Ԫ��_ȡ�ı���ɫ()
XC_API COLORREF WINAPI XEle_GetTextColor(HELE hEle);
//@��ע ��ȡ�ı���ɫ,���ȴ���Դ�л�ȡ.
//@���� hEle Ԫ�ؾ��.
//@���� �ı���ɫֵ.
//@���� Ԫ��_ȡ�ı���ɫ��չ()
XC_API COLORREF WINAPI XEle_GetTextColorEx(HELE hEle);
//@��ע ���ý���߿���ɫ.
//@���� hEle Ԫ�ؾ��.
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� Ԫ��_�ý���߿���ɫ()
XC_API void WINAPI XEle_SetFocusBorderColor(HELE hEle, COLORREF color);
//@��ע ��ȡ����߿���ɫ.
//@���� hEle Ԫ�ؾ��.
//@���� ������ɫֵ
//@���� Ԫ��_ȡ����߿���ɫ()
XC_API COLORREF WINAPI XEle_GetFocusBorderColor(HELE hEle);
//@��ע ����Ԫ������.
//@���� hEle Ԫ�ؾ��.
//@���� hFontx �Ų�����.
//@���� Ԫ��_������()
XC_API void WINAPI XEle_SetFont(HELE hEle, HFONTX hFontx);
//@��ע ��ȡԪ������.
//@���� hEle Ԫ�ؾ��.
//@���� �����Ų�������.
//@���� Ԫ��_ȡ����()
XC_API HFONTX WINAPI XEle_GetFont(HELE hEle);
//@��ע ��ȡԪ������,���ȴ���Դ�л�ȡ.
//@���� hEle Ԫ�ؾ��.
//@���� �����Ų�������.
//@���� Ԫ��_ȡ������չ()
XC_API HFONTX WINAPI XEle_GetFontEx(HELE hEle);
//@��ע ����Ԫ��͸����.
//@���� hEle Ԫ�ؾ��.
//@���� alpha ͸����.
//@���� Ԫ��_��͸����()
XC_API void WINAPI XEle_SetAlpha(HELE hEle, BYTE alpha);
//@���� hEle Ԫ�ؾ��
//@���� ����͸����
//@���� Ԫ��_ȡ͸����()
XC_API BYTE WINAPI XEle_GetAlpha(HELE hEle);
//@��ע ����Ԫ��.
//@���� hEle Ԫ�ؾ��.
//@���� Ԫ��_����()
XC_API void WINAPI XEle_Destroy(HELE hEle);
//@��ע ��ӱ������ݱ߿�.
//@���� hEle Ԫ�ؾ��.
//@���� nState ���״̬ �μ��ĵ�: API�ӿ�->���״̬
//@���� color ��ɫ.
//@���� width �߿�.
//@���� Ԫ��_��ӱ����߿�()
XC_API void WINAPI XEle_AddBkBorder(HELE hEle, int nState, COLORREF color, int width);
//@��ע ��ӱ����������.
//@���� hEle Ԫ�ؾ��.
//@���� nState ���״̬ �μ��ĵ�: API�ӿ�->���״̬
//@���� color ��ɫ.
//@���� Ԫ��_��ӱ������()
XC_API void WINAPI XEle_AddBkFill(HELE hEle, int nState, COLORREF color);
//@��ע ��ӱ�������ͼƬ.
//@���� hEle Ԫ�ؾ��.
//@���� nState ���״̬ �μ��ĵ�: API�ӿ�->���״̬
//@���� hImage ͼƬ���.
//@���� Ԫ��_��ӱ���ͼƬ()
XC_API void WINAPI XEle_AddBkImage(HELE hEle, int nState, HIMAGE hImage);
//@��ע ���ñ�������
//@���� hEle Ԫ�ؾ��
//@���� pText ���������ַ���
//@���� �������õı�����������
//@���� Ԫ��_�ñ���()
XC_API int WINAPI XEle_SetBkInfo(HELE hEle, const wchar_t* pText);
//@��ע ��ȡ������������.
//@���� hEle Ԫ�ؾ��.
//@���� ���ر�����������.
//@���� Ԫ��_ȡ������������()
XC_API int WINAPI XEle_GetBkInfoCount(HELE hEle);
//@��ע ��ձ�������; �������û������,��ʹ��ϵͳĬ������,�Ա㱣֤������ȷ.
//@���� hEle Ԫ�ؾ��.
//@���� Ԫ��_��ձ�������()
XC_API void WINAPI XEle_ClearBkInfo(HELE hEle);
//@��ע ��ȡԪ�ر���������.
//@���� hEle Ԫ�ؾ��.
//@���� ����������.
//@���� Ԫ��_ȡ����������()
XC_API HBKM WINAPI XEle_GetBkManager(HELE hEle);
//@��ע ��ȡԪ�ر���������,���ȴ���Դ�л�ȡ.
//@���� hEle Ԫ�ؾ��.
//@���� ����������.
//@���� Ԫ��_ȡ������������չ()
XC_API HBKM WINAPI XEle_GetBkManagerEx(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� hBkInfoM ����������
//@���� Ԫ��_�ñ���������()
XC_API void WINAPI XEle_SetBkManager(HELE hEle, HBKM hBkInfoM);
//@��ע ��ȡ���״̬.
//@���� hEle Ԫ�ؾ��.
//@���� ����Ԫ�����״̬  @ref element_state_flag_
//@���� Ԫ��_ȡ״̬()
XC_API int WINAPI XEle_GetStateFlags(HELE hEle);
//@��ע ����Ԫ�ؽ���.
//@���� hEle Ԫ�ؾ��.
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������.
//@���� ���Ƴɹ�����TRUE,�������Ҫ���ƽ��㷵��FALSE.
//@���� Ԫ��_���ƽ���()
XC_API BOOL WINAPI XEle_DrawFocus(HELE hEle, HDRAW hDraw, RECT* pRect);
//@��ע ���Ի��¼�������,�û��ֶ����û���Ԫ��,�Ա���ƻ���˳��.
//@���� hEle Ԫ�ؾ��.
//@���� hDraw ͼ�λ��ƾ��.
//@���� Ԫ��_����()
XC_API void WINAPI XEle_DrawEle(HELE hEle, HDRAW hDraw);
//@��ע �����û�����.
//@���� hEle Ԫ�ؾ��.
//@���� nData �û�����.
//@���� Ԫ��_���û�����()
XC_API void WINAPI XEle_SetUserData(HELE hEle, vint nData);
//@��ע ��ȡ�û�����.
//@���� hEle Ԫ�ؾ��.
//@���� �û�����.
//@���� Ԫ��_ȡ�û�����()
XC_API vint WINAPI XEle_GetUserData(HELE hEle);
//@��ע ��ȡ���ݴ�С.
//@���� hEle Ԫ�ؾ��.
//@���� bHorizon ˮƽ��ֱ,  �������Խ�������
//@���� cx ���
//@���� cy �߶�
//@���� pSize ���ش�С.
//@���� Ԫ��_ȡ���ݴ�С()
XC_API void WINAPI XEle_GetContentSize(HELE hEle, BOOL bHorizon, int cx, int cy, SIZE* pSize);
//@��ע ������겶��.
//@���� hEle Ԫ�ؾ��.
//@���� b TRUE����,FALSEȡ��.
//@���� Ԫ��_����겶��()
XC_API void WINAPI XEle_SetCapture(HELE hEle, BOOL b);
//@��ע ���û�ر�Ԫ��͸��ͨ��,�������,��ǿ������Ԫ�ر�����͸��,Ĭ��Ϊ����,�˹�����Ϊ�˼���GDI��֧��͸��ͨ������.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable ���û�ر�.
//@���� Ԫ��_����͸��ͨ��()
XC_API void WINAPI XEle_EnableTransparentChannel(HELE hEle, BOOL bEnable);
//@��ע ����Ԫ�ض�ʱ��.
//@���� hEle Ԫ�ؾ��.
//@���� nIDEvent �¼�ID.
//@���� uElapse ��ʱ����.
//@���� ����ɹ�����TRUE,���򷵻�FALSE.
//@���� Ԫ��_���Ųʶ�ʱ��()
XC_API BOOL WINAPI XEle_SetXCTimer(HELE hEle, UINT nIDEvent, UINT uElapse);
//@��ע �ر�Ԫ�ض�ʱ��.
//@���� hEle Ԫ�ؾ��.
//@���� nIDEvent �¼�ID.
//@���� ����ɹ�����TRUE,���򷵻�FALSE.
//@���� Ԫ��_�ر��Ųʶ�ʱ��()
XC_API BOOL WINAPI XEle_KillXCTimer(HELE hEle, UINT nIDEvent);
//@��ע ���ù�����ʾ����.
//@���� hEle Ԫ�ؾ��.
//@���� pText ������ʾ����.
//@���� Ԫ��_�ù�����ʾ()
XC_API void WINAPI XEle_SetToolTip(HELE hEle, const wchar_t* pText);
//@��ע ���ù�����ʾ����.
//@���� hEle Ԫ�ؾ��.
//@���� pText ������ʾ����.
//@���� nTextAlign �ı����뷽ʽ @ref textFormatFlag_
//@���� Ԫ��_�ù�����ʾ��չ()
XC_API void WINAPI XEle_SetToolTipEx(HELE hEle, const wchar_t* pText, int nTextAlign);
//@��ע ��ȡ������ʾ����.
//@���� hEle Ԫ�ؾ��.
//@���� �����ı�����
//@���� Ԫ��_ȡ������ʾ()
XC_API const wchar_t* WINAPI XEle_GetToolTip(HELE hEle);
//@��ע ����������ʾ.
//@���� hEle Ԫ�ؾ��.
//@���� x X����.
//@���� y Y����.
//@���� Ԫ��_����������ʾ()
XC_API void WINAPI XEle_PopupToolTip(HELE hEle, int x, int y);
//@��ע ��������.
//@���� hEle Ԫ�ؾ��.
//@���� nAdjustNo ����������ˮ��
//@���� Ԫ��_��������()
XC_API void WINAPI XEle_AdjustLayout(HELE hEle, UINT nAdjustNo=0);
//@��ע ��������.
//@���� hEle Ԫ�ؾ��.
//@���� nFlags ������ʶ,  adjustLayout_
//@���� nAdjustNo ����������ˮ��
//@���� Ԫ��_����������չ()
XC_API void WINAPI XEle_AdjustLayoutEx(HELE hEle, int nFlags=adjustLayout_self, UINT nAdjustNo=0);
//@����}
//@����{  ����

//@��ע ��������Ԫ��.
//@���� x Ԫ��x����.
//@���� y Ԫ��y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ��Ϊ���ھ����Ԫ�ؾ��.
//@���� Ԫ�ؾ��.
//@���� ����_����()
XC_API HELE WINAPI XLayout_Create(int x, int y, int cx, int cy, HXCGUI hParent);
//@��ע ��������Ԫ��.
//@���� hParent ��Ϊ���ھ����Ԫ�ؾ��.
//@���� Ԫ�ؾ��.
//@���� ����_������չ()
XC_API HELE WINAPI XLayout_CreateEx(HXCGUI hParent);
//@��ע ���ò��ֹ���.
//@���� hEle Ԫ�ؾ��
//@���� bEnable �Ƿ�����.
//@���� ����_����()
XC_API void WINAPI XLayout_EnableLayout(HELE hEle, BOOL bEnable);
//@��ע �Ƿ��Ѿ����ò��ֹ���
//@���� hEle Ԫ�ؾ��
//@���� ������ò��ַ���TRUE,���򷵻�FALSE
//@���� ����_�Ƿ�����()
XC_API BOOL WINAPI XLayout_IsEnableLayout(HELE hEle);
//@��ע ��ʾ���ֱ߽�.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ���ʾ
//@���� ����_��ʾ���ֱ߽�()
XC_API void WINAPI XLayout_ShowLayoutFrame(HELE hEle, BOOL bEnable);
//@��ע ��ȡ���,�������ڱ߾��С
//@���� hEle Ԫ�ؾ��
//@���� ���ؿ��
//@���� ����_ȡ�ڿ��()
XC_API int WINAPI XLayout_GetWidthIn(HELE hEle);
//@��ע ��ȡ�߶�,�������ڱ߾��С
//@���� hEle Ԫ�ؾ��
//@���� ���ظ߶�
//@���� ����_ȡ�ڸ߶�()
XC_API int WINAPI XLayout_GetHeightIn(HELE hEle);
//@����}
//@����{  ���ֿ��

//@���� x Ԫ��x����
//@���� y Ԫ��y����
//@���� cx ���
//@���� cy �߶�
//@���� hParent ��Ϊ���ھ����Ԫ�ؾ��.
//@���� Ԫ�ؾ��
//@���� ���ֿ��_����()
XC_API HELE WINAPI XLayoutFrame_Create(int x, int y, int cx, int cy, HXCGUI hParent);
//@���� hParent ��Ϊ���ھ����Ԫ�ؾ��
//@���� Ԫ�ؾ��.
//@���� ���ֿ��_������չ()
XC_API HELE WINAPI XLayoutFrame_CreateEx(HXCGUI hParent);
//@��ע ���ò��ֹ���
//@���� hEle Ԫ�ؾ��
//@���� bEnable �Ƿ�����
//@���� ���ֿ��_����()
XC_API void WINAPI XLayoutFrame_EnableLayout(HELE hEle, BOOL bEnable);
//@��ע �Ƿ��Ѿ����ò��ֹ���
//@���� hEle Ԫ�ؾ��
//@���� ������ò��ַ���TRUE,���򷵻�FALSE
//@���� ���ֿ��_�Ƿ�����()
XC_API BOOL WINAPI XLayoutFrame_IsEnableLayout(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� bEnable �Ƿ���ʾ
//@���� ���ֿ��_��ʾ���ֱ߽�()
XC_API void WINAPI XLayoutFrame_ShowLayoutFrame(HELE hEle, BOOL bEnable);
//@��ע ��ȡ���,�������ڱ߾��С
//@���� hEle Ԫ�ؾ��
//@���� ���ؿ��
//@���� ���ֿ��_ȡ�ڿ��()
XC_API int WINAPI XLayoutFrame_GetWidthIn(HELE hEle);
//@��ע ��ȡ�߶�,�������ڱ߾��С
//@���� hEle Ԫ�ؾ��
//@���� ���ظ߶�
//@���� ���ֿ��_ȡ�ڸ߶�()
XC_API int WINAPI XLayoutFrame_GetHeightIn(HELE hEle);
//@����}
//@����{  ������ͼ

//@��ע ����������ͼԪ��.
//�����Ԫ����Դ���������ӵ�Ԫ��.
//@���� x Ԫ��x����.
//@���� y Ԫ��y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ���Ǵ�����Դ�����UIԪ����Դ���.����Ǵ�����Դ���������ӵ�����,
//@���� Ԫ�ؾ��.
//@���� ������_����()
XC_API HELE WINAPI XSView_Create(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@��ע �������ݴ�С.
//@���� hEle Ԫ�ؾ��.
//@���� cx ���, �˿�Ȳ������߿�������
//@���� cy �߶�, �˸߶Ȳ������߿�������
//@���� ������ݸı䷵��TRUE���򷵻�FALSE.
//@���� ������_����ͼ��С()
XC_API BOOL WINAPI XSView_SetTotalSize(HELE hEle, int cx, int cy);
//@��ע ��ȡ�����ܴ�С.
//@���� hEle Ԫ�ؾ��.
//@���� pSize ��С, �������߿�������
//@���� ������_ȡ��ͼ��С()
XC_API void WINAPI XSView_GetTotalSize(HELE hEle, SIZE* pSize);
//@��ע ���ù�����λ��С.
//@���� hEle Ԫ�ؾ��.
//@���� nWidth ���.
//@���� nHeight �߶�.
//@���� ������ݸı䷵��TRUE���򷵻�FALSE.
//@���� ������_�ù�����λ��С()
XC_API BOOL WINAPI XSView_SetLineSize(HELE hEle, int nWidth, int nHeight);
//@��ע ��ȡ������λ��С.
//@���� hEle Ԫ�ؾ��.
//@���� pSize ���ش�С.
//@���� ������_ȡ������λ��С()
XC_API void WINAPI XSView_GetLineSize(HELE hEle, SIZE* pSize);
//@��ע ���ù�������С.
//@���� hEle Ԫ�ؾ��.
//@���� size ��������С.
//@���� ������_�ù�������С()
XC_API void WINAPI XSView_SetScrollBarSize(HELE hEle, int size);
//@��ע ��ȡ�ӿ�ԭ��X����.
//@���� hEle Ԫ�ؾ��.
//@���� �ӿ�ԭ��X����.
//@���� ������_ȡ�ӿ�ԭ��X()
XC_API int WINAPI XSView_GetViewPosH(HELE hEle);
//@��ע ��ȡ�ӿ�ԭ��Y����.
//@���� hEle Ԫ�ؾ��.
//@���� �ӿ�ԭ��Y����.
//@���� ������_ȡ�ӿ�ԭ��Y()
XC_API int WINAPI XSView_GetViewPosV(HELE hEle);
//@��ע ��ȡ�ӿڿ��.
//@���� hEle Ԫ�ؾ��.
//@���� �����ӿڿ��.
//@���� ������_ȡ�ӿڿ��()
XC_API int WINAPI XSView_GetViewWidth(HELE hEle);
//@��ע ��ȡ�ӿڸ߶�.
//@���� hEle Ԫ�ؾ��.
//@���� �����ӿڸ߶�.
//@���� ������_ȡ�ӿڸ߶�()
XC_API int WINAPI XSView_GetViewHeight(HELE hEle);
//@��ע ��ȡ�ӿ�����.
//@���� hEle Ԫ�ؾ��.
//@���� pRect ����.
//@���� ������_ȡ�ӿ�����()
XC_API void WINAPI XSView_GetViewRect(HELE hEle, RECT* pRect);
//@��ע ��ȡˮƽ������.
//@���� hEle Ԫ�ؾ��.
//@���� ���������.
//@���� ������_ȡˮƽ������()
XC_API HELE WINAPI XSView_GetScrollBarH(HELE hEle);
//@��ע ��ȡ��ֱ������.
//@���� hEle Ԫ�ؾ��.
//@���� ��ֱ���������.
//@���� ������_ȡ��ֱ������()
XC_API HELE WINAPI XSView_GetScrollBarV(HELE hEle);
//@��ע ˮƽ������,������ָ��λ�õ�.
//@���� hEle Ԫ�ؾ��.
//@���� pos λ�õ�.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������_ˮƽ����()
XC_API BOOL WINAPI XSView_ScrollPosH(HELE hEle, int pos);
//@��ע ��ֱ������,������ָ��λ�õ�.
//@���� hEle Ԫ�ؾ��.
//@���� pos λ�õ�.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������_��ֱ����()
XC_API BOOL WINAPI XSView_ScrollPosV(HELE hEle, int pos);
//@��ע ˮƽ������,������ָ������.
//@���� hEle Ԫ�ؾ��.
//@���� posX X����.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������_ˮƽ������X()
XC_API BOOL WINAPI XSView_ScrollPosXH(HELE hEle, int posX);
//@��ע ��ֱ������,������ָ������.
//@���� hEle Ԫ�ؾ��.
//@���� posY Y����.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������_��ֱ������Y()
XC_API BOOL WINAPI XSView_ScrollPosYV(HELE hEle, int posY);
//@��ע ��ʾˮƽ������.
//@���� hEle Ԫ�ؾ��.
//@���� bShow �Ƿ���ʾ.
//@���� ������_��ʾˮƽ������()
XC_API void WINAPI XSView_ShowSBarH(HELE hEle, BOOL bShow);
//@��ע ��ʾ��ֱ������.
//@���� hEle Ԫ�ؾ��.
//@���� bShow �Ƿ���ʾ.
//@���� ������_��ʾ��ֱ������()
XC_API void WINAPI XSView_ShowSBarV(HELE hEle, BOOL bShow);
//@��ע �����Զ���ʾ������.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� ������_�����Զ���ʾ������()
XC_API void WINAPI XSView_EnableAutoShowScrollBar(HELE hEle, BOOL bEnable);
//@��ע �������.
//@���� hEle Ԫ�ؾ��.
//@���� ����ɹ�����TRUE,�����෴.
//@���� ������_�������()
XC_API BOOL WINAPI XSView_ScrollLeftLine(HELE hEle);
//@��ע ���ҹ���.
//@���� hEle Ԫ�ؾ��.
//@���� ����ɹ�����TRUE,�����෴.
//@���� ������_���ҹ���()
XC_API BOOL WINAPI XSView_ScrollRightLine(HELE hEle);
//@��ע ���Ϲ���.
//@���� hEle Ԫ�ؾ��.
//@���� ����ɹ�����TRUE,�����෴.
//@���� ������_���Ϲ���()
XC_API BOOL WINAPI XSView_ScrollTopLine(HELE hEle);
//@��ע ���¹���.
//@���� hEle Ԫ�ؾ��.
//@���� ����ɹ�����TRUE,�����෴.
//@���� ������_���¹���()
XC_API BOOL WINAPI XSView_ScrollBottomLine(HELE hEle);
//@��ע ˮƽ���������.
//@���� hEle Ԫ�ؾ��.
//@���� ����ɹ�����TRUE,�����෴.
//@���� ������_���������()
XC_API BOOL WINAPI XSView_ScrollLeft(HELE hEle);
//@��ע ˮƽ�������Ҳ�.
//@���� hEle Ԫ�ؾ��.
//@���� ����ɹ�����TRUE,�����෴.
//@���� ������_�������Ҳ�()
XC_API BOOL WINAPI XSView_ScrollRight(HELE hEle);
//@��ע ��ֱ����������.
//@���� hEle Ԫ�ؾ��.
//@���� ����ɹ�����TRUE,�����෴.
//@���� ������_����������()
XC_API BOOL WINAPI XSView_ScrollTop(HELE hEle);
//@��ע ��ֱ�������ײ�.
//@���� hEle Ԫ�ؾ��.
//@���� ����ɹ�����TRUE,�����෴.
//@���� ������_�������ײ�()
XC_API BOOL WINAPI XSView_ScrollBottom(HELE hEle);
//@����}
//@����{  ��ť

//@��ע ������ťԪ��
//@���� x ��ťx����
//@���� y ��ťy����
//@���� cx ���
//@���� cy �߶�
//@���� pName ����
//@���� hParent ��Ϊ���ھ����Ԫ�ؾ��.
//@���� ��ťԪ�ؾ��.
//@���� ��ť_����()
XC_API HELE WINAPI XBtn_Create(int x, int y, int cx, int cy, const wchar_t* pName, HXCGUI hParent=NULL);
//@��ע �Ե�ǰ�ı����ݴ���, ��&���ź����һ���ַ������»���
//@���� hEle Ԫ�ؾ��
//@���� bEnable �Ƿ�����
//@���� ��ť_�����ȼ�ǰ׺()
XC_API void WINAPI XBtn_EnableHotkeyPrefix(HELE hEle, BOOL bEnable);
//@��ע �Ƿ�ѡ��״̬.
//@���� hEle Ԫ�ؾ��.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ��ť_�Ƿ�ѡ��()
XC_API BOOL WINAPI XBtn_IsCheck(HELE hEle);
//@��ע ����ѡ��״̬.
//@���� hEle Ԫ�ؾ��.
//@���� bCheck �Ƿ�ѡ��.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ��ť_��ѡ��()
XC_API BOOL WINAPI XBtn_SetCheck(HELE hEle, BOOL bCheck);
//@��ע ���ð�ť״̬.
//@���� hEle Ԫ�ؾ��.
//@���� nState ��ť״̬���궨��.
//@���� ��ť_��״̬()
XC_API void WINAPI XBtn_SetState(HELE hEle, common_state3_ nState);
//@��ע ��ȡ��ť״̬
//@���� hEle Ԫ�ؾ��.
//@���� ���ذ�ť״̬.
//@���� ��ť_ȡ��̬()
XC_API common_state3_ WINAPI XBtn_GetState(HELE hEle);
//@��ע ��ȡ��ť״̬
//@���� hEle Ԫ�ؾ��.
//@���� ���ذ�ť״̬.
//@���� ��ť_ȡ��ť״̬()
XC_API button_state_ WINAPI XBtn_GetStateEx(HELE hEle);
//@��ע ���ð�ť���Ͳ��Զ��޸���ʽ���ı����뷽ʽ.
//@���� hEle Ԫ�ؾ��.
//@���� nType ��ť����,�μ��궨��.
//@���� ��ť_��������չ()
XC_API void WINAPI XBtn_SetTypeEx(HELE hEle, XC_OBJECT_TYPE_EX nType);
//@��ע ������ID.
//@���� hEle Ԫ�ؾ��.
//@���� nID ��ID.
//@���� ��ť_����ID()
XC_API void WINAPI XBtn_SetGroupID(HELE hEle, int nID);
//@��ע ��ȡ��ID.
//@���� hEle Ԫ�ؾ��.
//@���� ��ID.
//@���� ��ť_ȡ��ID()
XC_API int WINAPI XBtn_GetGroupID(HELE hEle);
//@��ע ���ð�Ԫ��.
//@���� hEle Ԫ�ؾ��.
//@���� hBindEle ��Ҫ�󶨵�Ԫ��.
//@���� ��ť_�ð�Ԫ��()
XC_API void WINAPI XBtn_SetBindEle(HELE hEle, HELE hBindEle);
//@��ע ��ȡ�󶨵�Ԫ��.
//@���� hEle Ԫ�ؾ��.
//@���� �󶨵�Ԫ�ؾ��.
//@���� ��ť_ȡ��Ԫ��()
XC_API HELE WINAPI XBtn_GetBindEle(HELE hEle);
//@��ע �����ı����뷽ʽ.
//@���� hEle Ԫ�ؾ��.
//@���� nFlags ���뷽ʽ  @ref textFormatFlag_ .
//@���� ��ť_���ı�����()
XC_API void WINAPI XBtn_SetTextAlign(HELE hEle, int nFlags);
//@��ע ��ȡ�ı����뷽ʽ.
//@���� hEle Ԫ�ؾ��.
//@���� �ı����뷽ʽ @ref textFormatFlag_ .
//@���� ��ť_ȡ�ı�����()
XC_API int WINAPI XBtn_GetTextAlign(HELE hEle);
//@��ע ����ͼ�����.
//@���� hEle Ԫ�ؾ��.
//@���� align ���뷽ʽ.
//@���� ��ť_��ͼ�����()
XC_API void WINAPI XBtn_SetIconAlign(HELE hEle, button_icon_align_ align);
//@��ע ���ð�ť�ı�����ƫ����.
//@���� hEle Ԫ�ؾ��.
//@���� x ƫ����.
//@���� y ƫ����.
//@���� ��ť_��ƫ��()
XC_API void WINAPI XBtn_SetOffset(HELE hEle, int x, int y);
//@��ע ���ð�ťͼ������ƫ����.
//@���� hEle Ԫ�ؾ��.
//@���� x ƫ����.
//@���� y ƫ����.
//@���� ��ť_��ͼ��ƫ��()
XC_API void WINAPI XBtn_SetOffsetIcon(HELE hEle, int x, int y);
//@��ע ����ͼ�����ı������С.
//@���� hEle Ԫ�ؾ��.
//@���� size �����С.
//@���� ��ť_��ͼ����()
XC_API void WINAPI XBtn_SetIconSpace(HELE hEle, int size);
//@��ע �����ı�����.
//@���� hEle Ԫ�ؾ��.
//@���� pName �ı�����.
//@���� ��ť_���ı�()
XC_API void WINAPI XBtn_SetText(HELE hEle, const wchar_t* pName);
//@��ע ��ȡ�ı�����.
//@���� hEle Ԫ�ؾ��.
//@���� �����ı�
//@���� ��ť_ȡ�ı�()
XC_API const wchar_t* WINAPI XBtn_GetText(HELE hEle);
//@��ע ����ͼ��.
//@���� hEle Ԫ�ؾ��.
//@���� hImage ͼƬ���.
//@���� ��ť_��ͼ��()
XC_API void WINAPI XBtn_SetIcon(HELE hEle, HIMAGE hImage);
//@��ע ����ͼ�����״̬.
//@���� hEle Ԫ�ؾ��.
//@���� hImage ͼƬ���.
//@���� ��ť_�ý���ͼ��()
XC_API void WINAPI XBtn_SetIconDisable(HELE hEle, HIMAGE hImage);
//@��ע ��ȡ�û����õ�ͼ��.
//@���� hEle Ԫ�ؾ��.
//@���� nType ͼ������, 0:Ĭ��ͼ��,1:����״̬ͼ��.
//@���� ����ͼ����.
//@���� ��ť_ȡͼ��()
XC_API HIMAGE WINAPI XBtn_GetIcon(HELE hEle, int nType);
//@��ע ��Ӷ���֡.
//@���� hEle Ԫ�ؾ��.
//@���� hImage ͼƬ���
//@���� uElapse ͼƬ֡��ʱ,��λ����.
//@���� ��ť_��Ӷ���֡()
XC_API void WINAPI XBtn_AddAnimationFrame(HELE hEle, HIMAGE hImage, UINT uElapse);
//@��ע ��ʼ��ر�ͼƬ�����Ĳ���.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable ��ʼ���Ŷ���TRUE,�رղ��Ŷ���FALSE.
//@���� bLoopPlay �Ƿ�ѭ������.
//@���� ��ť_���ö���()
XC_API void WINAPI XBtn_EnableAnimation(HELE hEle, BOOL bEnable, BOOL bLoopPlay=FALSE);
//@����}
//@����{  �༭��

//@��ע ����
//@���� x Ԫ��x����
//@���� y Ԫ��y����
//@���� cx ���
//@���� cy �߶�
//@���� hParent ��Ϊ���ھ����Ԫ�ؾ��
//@���� Ԫ�ؾ��
//@���� �༭��_����()
XC_API HELE WINAPI XEdit_Create(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@��ע ����
//@���� x Ԫ��x����
//@���� y Ԫ��y����
//@���� cx ���
//@���� cy �߶�
//@���� type ����
//@���� hParent ��Ϊ���ھ����Ԫ�ؾ��
//@���� Ԫ�ؾ��
//@���� �༭��_������չ()
XC_API HELE WINAPI XEdit_CreateEx(int x, int y, int cx, int cy, edit_type_ type, HXCGUI hParent=NULL);
//@��ע �����Զ�����
//@���� hEle Ԫ�ؾ��
//@���� bEnable �Ƿ�����
//@���� �༭��_�����Զ�����()
XC_API void WINAPI XEdit_EnableAutoWrap(HELE hEle, BOOL bEnable);
//@���� hEle Ԫ�ؾ��
//@���� bEnable �Ƿ�����
//@���� �༭��_����ֻ��()
XC_API void WINAPI XEdit_EnableReadOnly(HELE hEle, BOOL bEnable);
//@���� hEle
//@���� bEnable
//@���� �༭��_���ö���()
XC_API void WINAPI XEdit_EnableMultiLine(HELE hEle, BOOL bEnable);
//@��ע ��������ģʽ(ֻ֧��Ĭ�����ͱ༭��
//@���� hEle Ԫ�ؾ��
//@���� bEnable �Ƿ�����
//@���� �༭��_��������()
XC_API void WINAPI XEdit_EnablePassword(HELE hEle, BOOL bEnable);
//@��ע ����ý���ʱ,�Զ�ѡ����������
//@���� hEle Ԫ�ؾ��
//@���� bEnable �Ƿ�����
//@���� �༭��_�����Զ�ѡ��()
XC_API void WINAPI XEdit_EnableAutoSelAll(HELE hEle, BOOL bEnable);
//@��ע ��ʧȥ����ʱ�Զ�ȡ��ѡ��
//@���� hEle Ԫ�ؾ��
//@���� bEnable �Ƿ�����
//@���� �༭��_�����Զ�ȡ��ѡ��()
XC_API void WINAPI XEdit_EnableAutoCancelSel(HELE hEle, BOOL bEnable);
//@���� hEle Ԫ�ؾ��
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_�Ƿ�ֻ��()
XC_API BOOL WINAPI XEdit_IsReadOnly(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_�Ƿ����()
XC_API BOOL WINAPI XEdit_IsMultiLine(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_�Ƿ�����()
XC_API BOOL WINAPI XEdit_IsPassword(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_�Ƿ��Զ�����()
XC_API BOOL WINAPI XEdit_IsAutoWrap(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� ���Ϊ�շ���TRUE���򷵻�FALSE.
//@���� �༭��_�Ƿ�Ϊ��()
XC_API BOOL WINAPI XEdit_IsEmpty(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� iCol ������
//@���� �����ѡ�������ڷ���TRUE,���򷵻�FALSE
//@���� �༭��_�Ƿ���ѡ������()
XC_API BOOL WINAPI XEdit_IsInSelect(HELE hEle, int iRow, int iCol);
//@���� hEle Ԫ�ؾ��
//@���� ����������
//@���� �༭��_ȡ������()
XC_API int WINAPI XEdit_GetRowCount(HELE hEle);
//@��ע �����Զ���������
//@���� hEle Ԫ�ؾ��
//@���� ����������
//@���� �༭��_ȡ��������չ()
XC_API int WINAPI XEdit_GetRowCountEx(HELE hEle);
//@��ע �����ı�����ı�����
//@���� hEle Ԫ�ؾ��
//@���� �������ݽṹ
//@���� �༭��_ȡ����()
XC_API edit_data_copy_* WINAPI XEdit_GetData(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� pData ���ݽṹ
//@���� styleTable ��ʽ��
//@���� nStyleCount ��ʽ����
//@���� �༭��_�������()
XC_API void WINAPI XEdit_AddData(HELE hEle, edit_data_copy_* pData, USHORT* styleTable, int nStyleCount);
//@���� pData ���ݽṹ
//@���� �༭��_�ͷ�����()
XC_API void WINAPI XEdit_FreeData(edit_data_copy_* pData);
//@��ע ������Ϊ��ʱ,��ʾĬ���ı�
//@���� hEle Ԫ�ؾ��
//@���� pString �ı�����
//@���� �༭��_��Ĭ���ı�()
XC_API void WINAPI XEdit_SetDefaultText(HELE hEle, const wchar_t* pString);
//@���� hEle Ԫ�ؾ��
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� �༭��_��Ĭ���ı���ɫ()
XC_API void WINAPI XEdit_SetDefaultTextColor(HELE hEle, COLORREF color);
//@���� hEle Ԫ�ؾ��
//@���� ch �ַ�
//@���� �༭��_�������ַ�()
XC_API void WINAPI XEdit_SetPasswordCharacter(HELE hEle, wchar_t ch);
//@��ע ����ģʽ����Ч
//@���� hEle Ԫ�ؾ��
//@���� align ���뷽ʽ @ref edit_textAlign_flag_
//@���� �༭��_���ı�����()
XC_API void WINAPI XEdit_SetTextAlign(HELE hEle, int align);
//@��ע ����������;  ���������, �����������ַ�ʱʹ�ú�����, �����֧�����ĵ���������
//@���� hEle Ԫ�ؾ��
//@���� hFont ����
//@���� �༭��_�ú�����()
XC_API void WINAPI XEdit_SetBackFont(HELE hEle, HFONTX hFont);
//@��ע TABռ�ո�����,  TAB��ʾ��С = �ո��С * TABռ�ո�����
//@���� hEle Ԫ�ؾ��
//@���� nSpace �ո�����
//@���� �༭��_��TAB�ո�()
XC_API void WINAPI XEdit_SetTabSpace(HELE hEle, int nSpace);
//@��ע TAB��ʾ��С = �ո��С * TABռ�ո�����
//@���� hEle Ԫ�ؾ��
//@���� size �ո��С
//@���� �༭��_�ÿո��С()
XC_API void WINAPI XEdit_SetSpaceSize(HELE hEle, int size);
//@���� hEle Ԫ�ؾ��
//@���� size Ӣ���ַ�����С
//@���� sizeZh �����ַ�����С
//@���� �༭��_���ַ����()
XC_API void WINAPI XEdit_SetCharSpaceSize(HELE hEle, int size, int sizeZh);
//@���� hEle Ԫ�ؾ��
//@���� pString �ַ���
//@���� �༭��_���ı�()
XC_API void WINAPI XEdit_SetText(HELE hEle, const wchar_t* pString);
//@���� hEle Ԫ�ؾ��
//@���� nValue ����ֵ
//@���� �༭��_���ı�����()
XC_API void WINAPI XEdit_SetTextInt(HELE hEle, int nValue);
//@��ע ���������ı�����
//@���� hEle Ԫ�ؾ��
//@���� pOut �����ı��ڴ�ָ��
//@���� nOutlen �ڴ��С, �ַ�Ϊ��λ
//@���� ����ʵ�ʽ����ı�����
//@���� �༭��_ȡ�ı�()
XC_API int WINAPI XEdit_GetText(HELE hEle, wchar_t* pOut, int nOutlen);
//@��ע ���������ı�����
//@���� hEle Ԫ�ؾ��
//@���� ������ʱ�ı�ָ��, ��ʱ��������С @ref TEXT_BUFFER_SIZE
//@���� �༭��_ȡ�ı�_��ʱ()
XC_API const wchar_t* WINAPI XEdit_GetText_Temp(HELE hEle);
//@��ע ��ȡָ�����ı�����
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� pOut �����ı��ڴ�ָ��
//@���� nOutlen �����ı��ڴ�鳤��,�ַ�Ϊ��λ
//@���� ����ʵ�ʽ����ı�����
//@���� �༭��_ȡ�ı���()
XC_API int WINAPI XEdit_GetTextRow(HELE hEle, int iRow, wchar_t* pOut, int nOutlen);
//@��ע �������ı�����
//@���� hEle Ԫ�ؾ��
//@���� �������ݳ���
//@���� �༭��_ȡ���ݳ���()
XC_API int WINAPI XEdit_GetLength(HELE hEle);
//@��ע �������ı�����
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� �������ݳ���
//@���� �༭��_ȡ���ݳ�����()
XC_API int WINAPI XEdit_GetLengthRow(HELE hEle, int iRow);
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� iCol ������
//@���� ����ָ��λ���ַ�
//@���� �༭��_ȡ�ַ�()
XC_API wchar_t WINAPI XEdit_GetAt(HELE hEle, int iRow, int iCol);
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� iCol ������
//@���� pString �ַ���
//@���� �༭��_�����ı�()
XC_API void WINAPI XEdit_InsertText(HELE hEle, int iRow, int iCol, const wchar_t* pString);
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� iCol ������
//@���� pString �ַ���
//@���� iStyle ��ʽ
//@���� �༭��_�����ı���չ()
XC_API void WINAPI XEdit_InsertTextEx(HELE hEle, int iRow, int iCol, const wchar_t* pString, int iStyle);
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� iCol ������
//@���� hObj ������
//@���� �༭��_�������()
XC_API void WINAPI XEdit_InsertObject(HELE hEle, int iRow, int iCol, HXCGUI hObj);
//@��ע �ڵ�ǰ����λ������ı�, ���Բ�һ���������ĩβ, ����ͨ�� XEdit_MoveEnd() �ƶ�����λ�õ�ĩβ
//@���� hEle Ԫ�ؾ��
//@���� pString �ַ���
//@���� �༭��_����ı�()
XC_API void WINAPI XEdit_AddText(HELE hEle, const wchar_t* pString);
//@��ע �Զ�ˢ��UI, ֧�ֳ���/�ָ�
//@���� hEle Ԫ�ؾ��
//@���� pString �ַ���
//@���� �༭��_����ı�ģ���û�����()
XC_API void WINAPI XEdit_AddTextUser(HELE hEle, const wchar_t* pString);
//@��ע �ڵ�ǰ����λ������ı�, ���Բ�һ���������ĩβ, ����ͨ�� XEdit_MoveEnd() �ƶ�����λ�õ�ĩβ
//@���� hEle Ԫ�ؾ��
//@���� pString �ַ���
//@���� iStyle ��ʽ����
//@���� �༭��_����ı���չ()
XC_API void WINAPI XEdit_AddTextEx(HELE hEle, const wchar_t* pString, int iStyle);
//@��ע ����: ����, ͼƬ, UI����
//@���� hEle Ԫ�ؾ��
//@���� hObj ������
//@���� ������ʽ����
//@���� �༭��_��Ӷ���()
XC_API int WINAPI XEdit_AddObject(HELE hEle, HXCGUI hObj);
//@��ע ����ʽΪͼƬʱ��Ч
//@���� hEle Ԫ�ؾ��
//@���� iStyle ��ʽ����
//@���� �༭��_��Ӷ������ʽ()
XC_API void WINAPI XEdit_AddByStyle(HELE hEle, int iStyle);
//@���� hEle Ԫ�ؾ��
//@���� hFont_image_Obj ����,ͼƬ��UI����
//@���� color ��ɫ
//@���� bColor �Ƿ�ʹ����ɫ
//@���� ������ʽ����
//@���� �༭��_�����ʽ()
XC_API int WINAPI XEdit_AddStyle(HELE hEle, HXCGUI hFont_image_Obj, COLORREF color, BOOL bColor);
//@���� hEle Ԫ�ؾ��
//@���� fontName ��������
//@���� fontSize �����С
//@���� fontStyle ������ʽ @ref fontStyle_  �˽ӿ�֧��ָ�� �»���, ɾ����, ��Ϊ�ڲ����˴���
//@���� color ��ɫ
//@���� bColor �Ƿ�ʹ����ɫ
//@���� ������ʽ����
//@���� �༭��_�����ʽ��չ()
XC_API int WINAPI XEdit_AddStyleEx(HELE hEle, const wchar_t* fontName, int fontSize, int fontStyle, COLORREF color, BOOL bColor);
//@���� hEle Ԫ�ؾ��
//@���� iStyle ��ʽ����
//@���� hFont ������
//@���� color ��ɫ
//@���� bColor �Ƿ�ʹ����ɫ
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_�޸���ʽ()
XC_API BOOL WINAPI XEdit_ModifyStyle(HELE hEle, int iStyle, HFONTX hFont, COLORREF color, BOOL bColor);
//@���� hEle Ԫ�ؾ��
//@���� iStyle ��ʽ
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_�ͷ���ʽ()
XC_API BOOL WINAPI XEdit_ReleaseStyle(HELE hEle, int iStyle);
//@���� hEle Ԫ�ؾ��
//@���� iStyle ��ʽ����
//@���� info ������ʽ��Ϣ
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_ȡ��ʽ��Ϣ()
XC_API BOOL WINAPI XEdit_GetStyleInfo(HELE hEle, int iStyle, edit_style_info_* info);
//@���� hEle Ԫ�ؾ��
//@���� iStyle ��ʽ����
//@���� �༭��_�õ�ǰ��ʽ()
XC_API void WINAPI XEdit_SetCurStyle(HELE hEle, int iStyle);
//@���� hEle Ԫ�ؾ��
//@���� iStyle ��ʽ����
//@���� �༭��_��ѡ���ı���ʽ()
XC_API void WINAPI XEdit_SetSelectTextStyle(HELE hEle, int iStyle);
//@���� hEle Ԫ�ؾ��
//@���� color ��ɫ
//@���� �༭��_�ò������ɫ()
XC_API void WINAPI XEdit_SetCaretColor(HELE hEle, COLORREF color);
//@���� hEle Ԫ�ؾ��
//@���� nWidth ���
//@���� �༭��_�ò�������()
XC_API void WINAPI XEdit_SetCaretWidth(HELE hEle, int nWidth);
//@���� hEle Ԫ�ؾ��
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� �༭��_��ѡ�񱳾���ɫ()
XC_API void WINAPI XEdit_SetSelectBkColor(HELE hEle, COLORREF color);
//@���� hEle Ԫ�ؾ��
//@���� nHeight �и�
//@���� �༭��_��Ĭ���и�()
XC_API void WINAPI XEdit_SetRowHeight(HELE hEle, int nHeight);
//@��ע ������Ϊ edit_type_richedit ֧��ָ����ͬ�и�
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� nHeight �߶�
//@���� �༭��_��ָ���и߶�()
XC_API void WINAPI XEdit_SetRowHeightEx(HELE hEle, int iRow, int nHeight);
//@��ע �����м����С,����ģʽ��Ч
//@���� hEle Ԫ�ؾ��
//@���� nSpace �м����С
//@���� �༭��_���м��()
XC_API void WINAPI XEdit_SetRowSpace(HELE hEle, int nSpace);
//@���� hEle Ԫ�ؾ��
//@���� pos λ��
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_�õ�ǰλ��()
XC_API BOOL WINAPI XEdit_SetCurPos(HELE hEle, int pos);
//@���� hEle Ԫ�ؾ��
//@���� ����λ��
//@���� �༭��_ȡ��ǰλ��()
XC_API int WINAPI XEdit_GetCurPos(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� iCol ������
//@���� �༭��_�õ�ǰλ����չ()
XC_API void WINAPI XEdit_SetCurPosEx(HELE hEle, int iRow, int iCol);
//@���� hEle Ԫ�ؾ��
//@���� iRow ����������
//@���� iCol ����������
//@���� �༭��_ȡ��ǰλ����չ()
XC_API void WINAPI XEdit_GetCurPosEx(HELE hEle, int* iRow, int* iCol);
//@���� hEle Ԫ�ؾ��
//@���� ����������
//@���� �༭��_ȡ��ǰ��()
XC_API int WINAPI XEdit_GetCurRow(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� ����������
//@���� �༭��_ȡ��ǰ��()
XC_API int WINAPI XEdit_GetCurCol(HELE hEle);
//@��ע ��������Ƶ���ĩβ
//@���� hEle Ԫ�ؾ��
//@���� �༭��_�ƶ���ĩβ()
XC_API void WINAPI XEdit_MoveEnd(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� iCol ������
//@���� pOut ���շ��������
//@���� �༭��_ȡ�����()
XC_API void WINAPI XEdit_GetPoint(HELE hEle, int iRow, int iCol, POINT* pOut);
//@��ע ��ͼ�Զ���������ǰ�����λ��
//@���� hEle Ԫ�ؾ��
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_�Զ�����()
XC_API BOOL WINAPI XEdit_AutoScroll(HELE hEle);
//@��ע ��ͼ�Զ�������ָ��λ��
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� iCol ������
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_�Զ�������չ()
XC_API BOOL WINAPI XEdit_AutoScrollEx(HELE hEle, int iRow, int iCol);
//@��ע ת��λ�õ㵽����
//@���� hEle Ԫ�ؾ��
//@���� iPos λ�õ�
//@���� pInfo ����
//@���� �༭��_λ�õ�����()
XC_API void WINAPI XEdit_PosToRowCol(HELE hEle, int iPos, position_* pInfo);
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� iCol ������
//@���� λ�õ�
//@���� �༭��_���е�λ��()
XC_API int WINAPI XEdit_RowColToPos(HELE hEle, int iRow, int iCol);
//@���� hEle Ԫ�ؾ��
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_ѡ��ȫ��()
XC_API BOOL WINAPI XEdit_SelectAll(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_ȡ��ѡ��()
XC_API BOOL WINAPI XEdit_CancelSelect(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_ɾ��ѡ������()
XC_API BOOL WINAPI XEdit_DeleteSelect(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� iStartRow ��ʼ������
//@���� iStartCol ��ʼ��������
//@���� iEndRow ����������
//@���� iEndCol ������������
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_��ѡ��()
XC_API BOOL WINAPI XEdit_SetSelect(HELE hEle, int iStartRow, int iStartCol, int iEndRow, int iEndCol);
//@��ע ���������ı�����
//@���� hEle Ԫ�ؾ��
//@���� pOut ���շ����ı�����
//@���� nOutLen �����ڴ��С,�ַ�Ϊ��λ
//@���� ���ؽ����ı�����ʵ�ʳ���
//@���� �༭��_ȡѡ���ı�()
XC_API int WINAPI XEdit_GetSelectText(HELE hEle, wchar_t* pOut, int nOutLen);
//@��ע ���������ı�����
//@���� hEle Ԫ�ؾ��
//@���� �����ı����ݳ���
//@���� �༭��_ȡѡ���ı�����()
XC_API int WINAPI XEdit_GetSelectTextLength(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� pBegin ��ʼλ��,��Ϊ��,��Ϊ��ʱ�����ѡ�����ݷ���TRUE
//@���� pEnd ����λ��,��Ϊ��,��Ϊ��ʱ�����ѡ�����ݷ���TRUE
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_ȡѡ�����ݷ�Χ()
XC_API BOOL WINAPI XEdit_GetSelectRange(HELE hEle, position_* pBegin, position_* pEnd);
//@���� hEle Ԫ�ؾ��
//@���� piStart ��ʼ������
//@���� piEnd ����������
//@���� �༭��_ȡ�����з�Χ()
XC_API void WINAPI XEdit_GetVisibleRowRange(HELE hEle, int* piStart, int* piEnd);
//@��ע ɾ��ָ����Χ����; ɾ��ȫ����ʹ�� XEdit_SetText(hEdit,L"")
//@���� hEle Ԫ�ؾ��
//@���� iStartRow ��ʼ������
//@���� iStartCol ��ʼ��������
//@���� iEndRow ����������
//@���� iEndCol ������������
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_ɾ��()
XC_API BOOL WINAPI XEdit_Delete(HELE hEle, int iStartRow, int iStartCol, int iEndRow, int iEndCol);
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_ɾ����()
XC_API BOOL WINAPI XEdit_DeleteRow(HELE hEle, int iRow);
//@���� hEle Ԫ�ؾ��
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_���������()
XC_API BOOL WINAPI XEdit_ClipboardCut(HELE hEle);
//@��ע ����ѡ������
//@���� hEle Ԫ�ؾ��
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_�����帴��ѡ��()
XC_API BOOL WINAPI XEdit_ClipboardCopy(HELE hEle);
//@��ע ����ȫ������
//@���� hEle Ԫ�ؾ��
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_�����帴��()
XC_API BOOL WINAPI XEdit_ClipboardCopyAll(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_������ճ��()
XC_API BOOL WINAPI XEdit_ClipboardPaste(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_����()
XC_API BOOL WINAPI XEdit_Undo(HELE hEle);
//@��ע �ָ�/����
//@���� hEle Ԫ�ؾ��
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �༭��_�ָ�()
XC_API BOOL WINAPI XEdit_Redo(HELE hEle);
//@��ע ��ǰ�п�ʼ
//@���� hEle Ԫ�ؾ��
//@���� hImageAvatar ͷ��
//@���� hImageBubble ���ݱ���
//@���� nFlag ��־ @ref chat_flag_
//@���� �༭��_�������ݿ�ʼ()
XC_API void WINAPI XEdit_InsertChatBegin(HELE hEle, HIMAGE hImageAvatar, HIMAGE hImageBubble, int nFlag);
//@��ע ��ǰ�п�ʼ
//@���� hEle Ԫ�ؾ��
//@���� hImageAvatar ͷ��
//@���� hImageBubble ���ݱ���
//@���� nFlag ��־ @ref chat_flag_
//@���� �༭��_������ݿ�ʼ()
XC_API void WINAPI XEdit_AddChatBegin(HELE hEle, HIMAGE hImageAvatar, HIMAGE hImageBubble, int nFlag);
//@��ע ��ǰ�н���
//@���� hEle Ԫ�ؾ��
//@���� �༭��_������ݽ���()
XC_API void WINAPI XEdit_AddChatEnd(HELE hEle);
//@��ע ��������������������
//@���� hEle Ԫ�ؾ��
//@���� nIndentation ����ֵ
//@���� �༭��_����������()
XC_API void WINAPI XEdit_SetChatIndentation(HELE hEle, int nIndentation);
//@��ע ��ֵΪ0ʱ�������ƿ��
//@���� hEle Ԫ�ؾ��
//@���� nWidth �����
//@���� �༭��_�����������()
XC_API void WINAPI XEdit_SetChatMaxWidth(HELE hEle, int nWidth);
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� �����б�ʶ @ref chat_flag_
//@���� �༭��_ȡָ�������ݱ�ʶ()
XC_API int WINAPI XEdit_GetChatFlags(HELE hEle, int iRow);
//@����}
//@����{  ��Ͽ�

//@��ע ����������Ͽ�Ԫ��.
//�����Ԫ����Դ���������ӵ�Ԫ��.
//@���� x Ԫ��x����.
//@���� y Ԫ��y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ���Ǵ�����Դ�����UIԪ����Դ���.����Ǵ�����Դ���������ӵ�����,
//@���� Ԫ�ؾ��.
//@���� ��Ͽ�_����()
XC_API HELE WINAPI XComboBox_Create(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@��ע ����ѡ����.
//@���� hEle Ԫ�ؾ��.
//@���� iIndex ������.
//@���� �ɹ����ط��򷵻�FALSE.
//@���� ��Ͽ�_��ѡ����()
XC_API BOOL WINAPI XComboBox_SetSelItem(HELE hEle, int iIndex);
//@��ע ������������������, ���ݰ󶨵���ģ���ʼ����������������(�ֶ���);
//�����������洢����, UI������ݰ󶨵��ֶ�����ʾ�����������ж�Ӧ������;
//@���� hEle Ԫ�ؾ��.
//@���� �����������������
//@���� ��Ͽ�_��������������()
XC_API HXCGUI WINAPI XComboBox_CreateAdapter(HELE hEle);
//@��ע ������������.
//@���� hEle Ԫ�ؾ��.
//@���� hAdapter ���������.
//@���� ��Ͽ�_������������()
XC_API void WINAPI XComboBox_BindAdapter(HELE hEle, HXCGUI hAdapter);
//@��ע ��ȡ�󶨵�����������.
//@���� hEle Ԫ�ؾ��.
//@���� ��������������.
//@���� ��Ͽ�_ȡ����������()
XC_API HXCGUI WINAPI XComboBox_GetAdapter(HELE hEle);
//@��ע ���ð������������ֶ���.
//@���� hEle Ԫ�ؾ��.
//@���� pName �ֶ���
//@���� ��Ͽ�_�ð�����()
XC_API void WINAPI XComboBox_SetBindName(HELE hEle, const wchar_t* pName);
//@��ע ��ȡ������ť����.
//@���� hEle Ԫ�ؾ��.
//@���� pRect ����.
//@���� ��Ͽ�_ȡ������ť����()
XC_API void WINAPI XComboBox_GetButtonRect(HELE hEle, RECT* pRect);
//@��ע ����������ť��С.
//@���� hEle Ԫ�ؾ��.
//@���� size ��С.
//@���� ��Ͽ�_��������ť��С()
XC_API void WINAPI XComboBox_SetButtonSize(HELE hEle, int size);
//@��ע ���������б���������߶�, @ref XComboBox_EnableDropHeightFixed ,
//@���� hEle Ԫ�ؾ��.
//@���� height �߶�, -1�Զ�����߶�
//@���� ��Ͽ�_�������б�߶�()
XC_API void WINAPI XComboBox_SetDropHeight(HELE hEle, int height);
//@��ע ��ȡ�����б�߶�.
//@���� hEle Ԫ�ؾ��.
//@���� �����б�߶�.
//@���� ��Ͽ�_ȡ�����б�߶�()
XC_API int WINAPI XComboBox_GetDropHeight(HELE hEle);
//@��ע ���������б���ģ���ļ�
//@���� hEle Ԫ�ؾ��.
//@���� pXmlFile ��ģ���ļ�.
//@���� ��Ͽ�_����ģ���ļ�()
XC_API void WINAPI XComboBox_SetItemTemplateXML(HELE hEle, const wchar_t* pXmlFile);
//@��ע ���������б���ģ��.
//@���� hEle Ԫ�ؾ��.
//@���� pStringXML �ַ���ָ��.
//@���� ��Ͽ�_����ģ����ַ���()
XC_API void WINAPI XComboBox_SetItemTemplateXMLFromString(HELE hEle, const char* pStringXML);
//@���� hEle Ԫ�ؾ��
//@���� data �ڴ��ַ
//@���� length �ڴ��С, �ֽ�Ϊ��λ
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ��Ͽ�_����ģ����ڴ�()
XC_API BOOL WINAPI XComboBox_SetItemTemplateXMLFromMem(HELE hEle, void* data, int length);
//@��ע RC��Դ���ͱ���Ϊ:"RT_RCDATA"
//@���� hEle Ԫ�ؾ��
//@���� id RC��ԴID
//@���� pFileName �ļ���
//@���� pPassword zip����
//@���� hModule ģ����
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ��Ͽ�_����ģ�����ԴZIP()
XC_API BOOL WINAPI XComboBox_SetItemTemplateXMLFromZipRes(HELE hEle, int id, const wchar_t* pFileName, const wchar_t* pPassword=NULL, HMODULE hModule=NULL);
//@���� hEle Ԫ�ؾ��
//@���� hTemp ��ģ����
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ��Ͽ�_����ģ��()
XC_API BOOL WINAPI XComboBox_SetItemTemplate(HELE hEle, HTEMP hTemp);
//@���� hEle Ԫ�ؾ��
//@���� ������ģ����
//@���� ��Ͽ�_ȡ��ģ��()
XC_API HTEMP WINAPI XComboBox_GetItemTemplate(HELE hEle);
//@��ע �Ƿ����������ť.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ����.
//@���� ��Ͽ�_���û���������ť()
XC_API void WINAPI XComboBox_EnableDrawButton(HELE hEle, BOOL bEnable);
//@��ע ���ÿɱ༭��ʾ���ı�����.
//@���� hEle Ԫ�ؾ��.
//@���� bEdit TRUE�ɱ༭,�����෴.
//@���� ��Ͽ�_���ñ༭()
XC_API void WINAPI XComboBox_EnableEdit(HELE hEle, BOOL bEdit);
//@��ע ����/�ر������б�߶ȹ̶���С.
//@���� hEle Ԫ�ؾ��
//@���� bEnable �Ƿ�����
//@���� ��Ͽ�_���������б�߶ȹ̶���С()
XC_API void WINAPI XComboBox_EnableDropHeightFixed(HELE hEle, BOOL bEnable);
//@���� hEle Ԫ�ؾ��
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ��Ͽ�_���������б�()
XC_API void WINAPI XComboBox_PopupDropList(HELE hEle);
//@��ע ��ȡ��Ͽ������б���ѡ��������.
//@���� hEle Ԫ�ؾ��.
//@���� ����������.
//@���� ��Ͽ�_ȡѡ����()
XC_API int WINAPI XComboBox_GetSelItem(HELE hEle);
//@��ע ��ȡ״̬.
//@���� hEle Ԫ�ؾ��.
//@���� ״̬.
//@���� ��Ͽ�_ȡ״̬()
XC_API comboBox_state_ WINAPI XComboBox_GetState(HELE hEle);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XComboBox_CreateAdapter() \ ��Ͽ�_��������������()
//@���� hEle Ԫ�ؾ��
//@���� pText
//@���� ����������
//@���� ��Ͽ�_������ı�()
XC_API int WINAPI XComboBox_AddItemText(HELE hEle, const wchar_t* pText);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XComboBox_CreateAdapter() \ ��Ͽ�_��������������()
//@���� hEle Ԫ�ؾ��
//@���� pName �ֶ���
//@���� pText �ı�
//@���� ����������
//@���� ��Ͽ�_������ı���չ()
XC_API int WINAPI XComboBox_AddItemTextEx(HELE hEle, const wchar_t* pName, const wchar_t* pText);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XComboBox_CreateAdapter() \ ��Ͽ�_��������������()
//@���� hEle Ԫ�ؾ��
//@���� hImage ͼƬ���
//@���� ����������
//@���� ��Ͽ�_�����ͼƬ()
XC_API int WINAPI XComboBox_AddItemImage(HELE hEle, HIMAGE hImage);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XComboBox_CreateAdapter() \ ��Ͽ�_��������������()
//@���� hEle Ԫ�ؾ��
//@���� pName �ֶ���
//@���� hImage ͼƬ���
//@���� ����������
//@���� ��Ͽ�_�����ͼƬ��չ()
XC_API int WINAPI XComboBox_AddItemImageEx(HELE hEle, const wchar_t* pName, HIMAGE hImage);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XComboBox_CreateAdapter() \ ��Ͽ�_��������������()
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� pText �ı�
//@���� ����������
//@���� ��Ͽ�_�������ı�()
XC_API int WINAPI XComboBox_InsertItemText(HELE hEle, int iItem, const wchar_t* pValue);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XComboBox_CreateAdapter() \ ��Ͽ�_��������������()
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� pName �ֶ���
//@���� pText �ı�
//@���� ����������
//@���� ��Ͽ�_�������ı���չ()
XC_API int WINAPI XComboBox_InsertItemTextEx(HELE hEle, int iItem, const wchar_t* pName, const wchar_t* pValue);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XComboBox_CreateAdapter() \ ��Ͽ�_��������������()
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� hImage ͼƬ���
//@���� ����������
//@���� ��Ͽ�_������ͼƬ()
XC_API int WINAPI XComboBox_InsertItemImage(HELE hEle, int iItem, HIMAGE hImage);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XComboBox_CreateAdapter() \ ��Ͽ�_��������������()
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� pName �ֶ���
//@���� hImage ͼƬ���
//@���� ����������
//@���� ��Ͽ�_������ͼƬ��չ()
XC_API int WINAPI XComboBox_InsertItemImageEx(HELE hEle, int iItem, const wchar_t* pName, HIMAGE hImage);
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� iColumn ������
//@���� pText �ı�
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ��Ͽ�_�����ı�()
XC_API BOOL WINAPI XComboBox_SetItemText(HELE hEle, int iItem, int iColumn, const wchar_t* pText);
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� pName �ֶ���
//@���� pText �ı�
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ��Ͽ�_�����ı���չ()
XC_API BOOL WINAPI XComboBox_SetItemTextEx(HELE hEle, int iItem, const wchar_t* pName, const wchar_t* pText);
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� iColumn ������
//@���� hImage ͼƬ���
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ��Ͽ�_����ͼƬ()
XC_API BOOL WINAPI XComboBox_SetItemImage(HELE hEle, int iItem, int iColumn, HIMAGE hImage);
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� pName �ֶ���
//@���� hImage ͼƬ���
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ��Ͽ�_����ͼƬ��չ()
XC_API BOOL WINAPI XComboBox_SetItemImageEx(HELE hEle, int iItem, const wchar_t* pName, HIMAGE hImage);
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� iColumn ������
//@���� nValue ����ֵ
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ��Ͽ�_��������ֵ()
XC_API BOOL WINAPI XComboBox_SetItemInt(HELE hEle, int iItem, int iColumn, int nValue);
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� pName �ֶ���
//@���� nValue ����ֵ
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ��Ͽ�_����ָ��ֵ��չ()
XC_API BOOL WINAPI XComboBox_SetItemIntEx(HELE hEle, int iItem, const wchar_t* pName, int nValue);
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� iColumn ������
//@���� fFloat ������
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ��Ͽ�_�����ֵ()
XC_API BOOL WINAPI XComboBox_SetItemFloat(HELE hEle, int iItem, int iColumn, float fFloat);
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� pName �ֶ���
//@���� fFloat ������
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ��Ͽ�_�����ֵ��չ()
XC_API BOOL WINAPI XComboBox_SetItemFloatEx(HELE hEle, int iItem, const wchar_t* pName, float fFloat);
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� iColumn ������
//@���� �����ı�����
//@���� ��Ͽ�_ȡ���ı�()
XC_API const wchar_t* WINAPI XComboBox_GetItemText(HELE hEle, int iItem, int iColumn);
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� pName �ֶ���
//@���� �����ı�����
//@���� ��Ͽ�_ȡ���ı���չ()
XC_API const wchar_t* WINAPI XComboBox_GetItemTextEx(HELE hEle, int iItem, const wchar_t* pName);
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� iColumn ������
//@���� ����ͼƬ���
//@���� ��Ͽ�_ȡ��ͼƬ()
XC_API HIMAGE WINAPI XComboBox_GetItemImage(HELE hEle, int iItem, int iColumn);
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� pName �ֶ���
//@���� ����ͼƬ���
//@���� ��Ͽ�_ȡ��ͼƬ��չ()
XC_API HIMAGE WINAPI XComboBox_GetItemImageEx(HELE hEle, int iItem, const wchar_t* pName);
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� iColumn ������
//@���� pOutValue ���շ�������ֵ
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ��Ͽ�_ȡ������ֵ()
XC_API BOOL WINAPI XComboBox_GetItemInt(HELE hEle, int iItem, int iColumn, int* pOutValue);
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� pName �ֶ���
//@���� pOutValue ���շ�������ֵ
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ��Ͽ�_ȡ������ֵ��չ()
XC_API BOOL WINAPI XComboBox_GetItemIntEx(HELE hEle, int iItem, const wchar_t* pName, int* pOutValue);
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� iColumn ������
//@���� pOutValue ���շ��ظ���ֵ
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ��Ͽ�_ȡ���ֵ()
XC_API BOOL WINAPI XComboBox_GetItemFloat(HELE hEle, int iItem, int iColumn, float* pOutValue);
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� pName �ֶ���
//@���� pOutValue ���շ��ظ���ֵ
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ��Ͽ�_ȡ���ֵ��չ()
XC_API BOOL WINAPI XComboBox_GetItemFloatEx(HELE hEle, int iItem, const wchar_t* pName, float* pOutValue);
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ��Ͽ�_ɾ����()
XC_API BOOL WINAPI XComboBox_DeleteItem(HELE hEle, int iItem);
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� nCount ɾ������
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ��Ͽ�_ɾ������չ()
XC_API BOOL WINAPI XComboBox_DeleteItemEx(HELE hEle, int iItem, int nCount);
//@���� hEle Ԫ�ؾ��
//@���� ��Ͽ�_ɾ����ȫ��()
XC_API void WINAPI XComboBox_DeleteItemAll(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� ��Ͽ�_ɾ����ȫ��()
XC_API void WINAPI XComboBox_DeleteColumnAll(HELE hEle);
//@���� hEle
//@���� ����������
//@���� ��Ͽ�_ȡ������()
XC_API int WINAPI XComboBox_GetCount(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� ����������
//@���� ��Ͽ�_ȡ������()
XC_API int WINAPI XComboBox_GetCountColumn(HELE hEle);
//@����}
//@����{  �б��

//@��ע �����б��Ԫ��.
//�����Ԫ����Դ���������ӵ�Ԫ��.
//@���� x Ԫ��x����.
//@���� y Ԫ��y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ���Ǵ�����Դ�����UIԪ����Դ���.����Ǵ�����Դ���������ӵ�����,
//@���� Ԫ�ؾ��.
//@���� �б��_����()
XC_API HELE WINAPI XListBox_Create(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@��ע �����б��Ԫ��; ʹ��������ģ��, �Զ���������������
//�����Ԫ����Դ���������ӵ�Ԫ��.
//@���� x Ԫ��x����.
//@���� y Ԫ��y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ���Ǵ�����Դ�����UIԪ����Դ���.����Ǵ�����Դ���������ӵ�����,
//@���� Ԫ�ؾ��.
//@���� �б��_������չ()
XC_API HELE WINAPI XListBox_CreateEx(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@��ע ע��:Ϊ���������,Ĭ��ʹ���б���ȫ����߶�
//@���� hEle Ԫ�ؾ��
//@���� bEnable �Ƿ�����
//@���� �б��_���ù̶��и�()
XC_API void WINAPI XListBox_EnableFixedRowHeight(HELE hEle, BOOL bEnable);
//@���� hEle Ԫ�ؾ��
//@���� bEnable �Ƿ�����
//@���� �б��_����ģ�帴��()
XC_API void WINAPI XListBox_EnableTemplateReuse(HELE hEle, BOOL bEnable);
//@���� hEle Ԫ�ؾ��
//@���� bEnable �Ƿ�����
//@���� �б��_�������()
XC_API void WINAPI XListBox_EnableVirtualTable(HELE hEle, BOOL bEnable);
//@���� hEle Ԫ�ؾ��
//@���� nRowCount ����
//@���� �б��_���������()
XC_API void WINAPI XListBox_SetVirtualRowCount(HELE hEle, int nRowCount);
//@��ע �����Ƿ����ָ��״̬����ı���.
//@���� hEle Ԫ�ؾ��.
//@���� nFlags ��־λ @ref list_drawItemBk_flag_.
//@���� �б��_��������Ʊ�־()
XC_API void WINAPI XListBox_SetDrawItemBkFlags(HELE hEle, int nFlags);
//@���� hEle Ԫ�ؾ��
//@���� color ��ɫֵ
//@���� �б��_�÷ָ�����ɫ()
XC_API void WINAPI XListBox_SetSplitLineColor(HELE hEle, COLORREF color);
//@��ע �������û�����.
//@���� hEle Ԫ�ؾ��.
//@���� iItem ������.
//@���� nUserData �û�����.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б��_��������()
XC_API BOOL WINAPI XListBox_SetItemData(HELE hEle, int iItem, vint nUserData);
//@��ע ��ȡ���û�����.
//@���� hEle Ԫ�ؾ��.
//@���� iItem ������.
//@���� �û�����.
//@���� �б��_ȡ������()
XC_API vint WINAPI XListBox_GetItemData(HELE hEle, int iItem);
//@��ע ��������Ϣ.(�ѹ�ʱ�ӿڲ��Ƽ�ʹ��)
//@���� hEle Ԫ�ؾ��.
//@���� iItem ������.
//@���� pItem ����Ϣ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б��_������Ϣ()
XC_API BOOL WINAPI XListBox_SetItemInfo(HELE hEle, int iItem, listBox_item_info_* pItem);
//@��ע ��ȡ����Ϣ.(�ѹ�ʱ�ӿڲ��Ƽ�ʹ��)
//@���� hEle Ԫ�ؾ��.
//@���� iItem ������.
//@���� pItem ����Ϣ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б��_ȡ����Ϣ()
XC_API BOOL WINAPI XListBox_GetItemInfo(HELE hEle, int iItem, listBox_item_info_* pItem);
//@��ע ע��:Ϊ���������,Ĭ��ʹ���б���ȫ����߶�, �����Ҫָ������߶�, ��Ҫ�رչ̶��и� @ref XListBox_EnableFixedRowHeight
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� nHeight ��߶�
//@���� nSelHeight ��ѡ��ʱ�߶�
//@���� �б��_����߶�()
XC_API void WINAPI XListBox_SetItemHeight(HELE hEle, int iItem, int nHeight, int nSelHeight);
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� pHeight ��߶�
//@���� pSelHeight ��ѡ��ʱ�߶�
//@���� �б��_ȡ��߶�()
XC_API void WINAPI XListBox_GetItemHeight(HELE hEle, int iItem, int* pHeight, int* pSelHeight);
//@��ע ����ѡ��ѡ.
//@���� hEle Ԫ�ؾ��.
//@���� iItem ������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б��_��ѡ����()
XC_API BOOL WINAPI XListBox_SetSelectItem(HELE hEle, int iItem);
//@��ע ��ȡѡ����.
//@���� hEle Ԫ�ؾ��.
//@���� ������.
//@���� �б��_ȡѡ����()
XC_API int WINAPI XListBox_GetSelectItem(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� iItem ������
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б��_���ѡ����()
XC_API BOOL WINAPI XListBox_AddSelectItem(HELE hEle, int iItem);
//@��ע ȡ��ѡ��ָ����.
//@���� hEle Ԫ�ؾ��.
//@���� iItem ������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б��_ȡ��ѡ����()
XC_API BOOL WINAPI XListBox_CancelSelectItem(HELE hEle, int iItem);
//@��ע ȡ������ѡ�е���.
//@���� hEle Ԫ�ؾ��.
//@���� ���֮ǰ��ѡ��״̬�����TRUE,��ʱ���Ը���UI,���򷵻�FALSE.
//@���� �б��_ȡ��ѡ��ȫ��()
XC_API BOOL WINAPI XListBox_CancelSelectAll(HELE hEle);
//@��ע ��ȡ����ѡ����.
//@���� hEle Ԫ�ؾ��.
//@���� pArray ���黺����.
//@���� nArraySize �����С(�����Ա��).
//@���� ���ؽ�������.
//@���� �б��_ȡȫ��ѡ��()
XC_API int WINAPI XListBox_GetSelectAll(HELE hEle, int* pArray, int nArraySize);
//@��ע ��ȡѡ��������.
//@���� hEle Ԫ�ؾ��.
//@���� ��������.
//@���� �б��_ȡѡ��������()
XC_API int WINAPI XListBox_GetSelectCount(HELE hEle);
//@��ע ��ȡ���ͣ����.
//@���� hEle Ԫ�ؾ��.
//@���� �������������.
//@���� �б��_ȡ���ͣ����()
XC_API int WINAPI XListBox_GetItemMouseStay(HELE hEle);
//@��ע ѡ��������.
//@���� hEle Ԫ�ؾ��.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б��_ѡ��ȫ����()
XC_API BOOL WINAPI XListBox_SelectAll(HELE hEle);
//@��ע ������ͼ��ָ����ɼ�
//@���� hEle Ԫ�ؾ��.
//@���� iItem ������.
//@���� �б��_��ʾָ����()
XC_API void WINAPI XListBox_VisibleItem(HELE hEle, int iItem);
//@��ע ��ȡ��ǰ�ɼ��з�Χ.
//@���� hEle Ԫ�ؾ��
//@���� piStart ��ʼ������
//@���� piEnd ����������
//@���� �б��_ȡ�����з�Χ()
XC_API void WINAPI XListBox_GetVisibleRowRange(HELE hEle, int* piStart, int* piEnd);
//@��ע ������Ĭ�ϸ߶�.
//@���� hEle Ԫ�ؾ��.
//@���� nHeight ��߶�.
//@���� nSelHeight ѡ����߶�.
//@���� �б��_����Ĭ�ϸ߶�()
XC_API void WINAPI XListBox_SetItemHeightDefault(HELE hEle, int nHeight, int nSelHeight);
//@��ע ��ȡ��Ĭ�ϸ߶�.
//@���� hEle Ԫ�ؾ��.
//@���� pHeight �߶�.
//@���� pSelHeight ѡ��ʱ�߶�.
//@���� �б��_ȡ��Ĭ�ϸ߶�()
XC_API void WINAPI XListBox_GetItemHeightDefault(HELE hEle, int* pHeight, int* pSelHeight);
//@��ע ��ȡ��ǰ��������ģ��ʵ��,�����б�����һ����(��).
//@���� hEle Ԫ�ؾ��.
//@���� hXCGUI ������, UIԪ�ؾ������״������.
//@���� �ɹ�����������, ���򷵻�@ref XC_ID_ERROR.
//@���� �б��_ȡ����������()
XC_API int WINAPI XListBox_GetItemIndexFromHXCGUI(HELE hEle, HXCGUI hXCGUI);
//@��ע �����м��.
//@���� hEle Ԫ�ؾ��.
//@���� nSpace ����С.
//@���� �б��_���м��()
XC_API void WINAPI XListBox_SetRowSpace(HELE hEle, int nSpace);
//@��ע ��ȡ�м���С.
//@���� hEle Ԫ�ؾ��.
//@���� �����м���С.
//@���� �б��_ȡ�м��()
XC_API int WINAPI XListBox_GetRowSpace(HELE hEle);
//@��ע ��������������.
//@���� hEle Ԫ�ؾ��.
//@���� pPt �����.
//@���� ����������.
//@���� �б��_���Ե����()
XC_API int WINAPI XListBox_HitTest(HELE hEle, POINT* pPt);
//@��ע ��������������,�Զ���ӹ�����ͼƫ����.
//@���� hEle Ԫ�ؾ��.
//@���� pPt �����.
//@���� ������.
//@���� �б��_���Ե������չ()
XC_API int WINAPI XListBox_HitTestOffset(HELE hEle, POINT* pPt);
//@��ע �����б���ģ���ļ�.
//@���� hEle Ԫ�ؾ��.
//@���� pXmlFile �ļ���.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б��_����ģ���ļ�()
XC_API BOOL WINAPI XListBox_SetItemTemplateXML(HELE hEle, const wchar_t* pXmlFile);
//@��ע �����б���ģ��.
//@���� hEle Ԫ�ؾ��
//@���� hTemp ģ����
//@���� ����ɹ�����TRUE,���򷵻�FALSE.
//@���� �б��_����ģ��()
XC_API BOOL WINAPI XListBox_SetItemTemplate(HELE hEle, HTEMP hTemp);
//@��ע ��ȡ�б���ģ��
//@���� hEle Ԫ�ؾ��
//@���� ������ģ����
//@���� �б��_ȡ��ģ��()
XC_API HTEMP WINAPI XListBox_GetItemTemplate(HELE hEle);
//@��ע ������ģ���ļ�.
//@���� hEle Ԫ�ؾ��.
//@���� pStringXML �ַ���ָ��.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б��_����ģ����ַ���()
XC_API BOOL WINAPI XListBox_SetItemTemplateXMLFromString(HELE hEle, const char* pStringXML);
//@��ע ������ģ���ļ�
//@���� hEle Ԫ�ؾ��.
//@���� data �ڴ��ַ.
//@���� length �ڴ��С,�ֽ�Ϊ��λ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б��_����ģ����ڴ�()
XC_API BOOL WINAPI XListBox_SetItemTemplateXMLFromMem(HELE hEle, void* data, int length);
//@��ע ������ģ���ļ�, RC��Դ���ͱ���Ϊ:"RT_RCDATA"
//@���� hEle Ԫ�ؾ��.
//@���� id RC��ԴID
//@���� pFileName ��ģ���ļ���
//@���� pPassword zip����
//@���� hModule ģ����
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б��_����ģ�����ԴZIP()
XC_API BOOL WINAPI XListBox_SetItemTemplateXMLFromZipRes(HELE hEle, int id, const wchar_t* pFileName, const wchar_t* pPassword=NULL, HMODULE hModule=NULL);
//@��ע ͨ��ģ����ID,��ȡʵ����ģ����ID��Ӧ�Ķ�����.
//@���� hEle Ԫ�ؾ��.
//@���� iItem ������.
//@���� nTempItemID ģ����ID.
//@���� �ɹ����ض�����,���򷵻�NULL.
//@���� �б��_ȡģ�����()
XC_API HXCGUI WINAPI XListBox_GetTemplateObject(HELE hEle, int iItem, int nTempItemID);
//@��ע �Ƿ����ö���ѡ����.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� �б��_���ö�ѡ()
XC_API void WINAPI XListBox_EnableMultiSel(HELE hEle, BOOL bEnable);
//@��ע ������������������,���ݰ󶨵���ģ���ʼ����������������(�ֶ���);
//�����������洢����, UI������ݰ󶨵��ֶ�����ʾ�����������ж�Ӧ������;
//@���� hEle Ԫ�ؾ��.
//@���� �������������.
//@���� �б��_��������������()
XC_API HXCGUI WINAPI XListBox_CreateAdapter(HELE hEle);
//@��ע ������������.
//@���� hEle Ԫ�ؾ��.
//@���� hAdapter ������������� XAdTable.
//@���� �б��_������������()
XC_API void WINAPI XListBox_BindAdapter(HELE hEle, HXCGUI hAdapter);
//@��ע ��ȡ�󶨵�����������.
//@���� hEle Ԫ�ؾ��.
//@���� �����������������.
//@���� �б��_ȡ����������()
XC_API HXCGUI WINAPI XListBox_GetAdapter(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� color ��ɫֵ ��ʹ�ú�: RGBA()
//@���� width �߿��
//@���� �б��_���϶�������ɫ()
XC_API void WINAPI XListBox_SetDragRectColor(HELE hEle, COLORREF color, int width);
//@��ע ����.
//@���� hEle Ԫ�ؾ��.
//@���� iColumnAdapter ��Ҫ���������������������������������.
//@���� bAscending ����(TRUE)����(FALSE).
//@���� �б��_����()
XC_API void WINAPI XListBox_Sort(HELE hEle, int iColumnAdapter, BOOL bAscending);
//@��ע �޸����ݺ�,ˢ��������ģ��,�Ա�������ݵ�ģ��(�����ɼ�).
//@���� hEle Ԫ�ؾ��.
//@���� �б��_ˢ������()
XC_API void WINAPI XListBox_RefreshData(HELE hEle);
//@��ע �޸����ݺ�,ˢ��ָ����ģ��,�Ա�������ݵ�ģ��(�����ǰ��ɼ�).
//@���� hEle Ԫ�ؾ��.
//@���� iItem ������.
//@���� �б��_ˢ��ָ����()
XC_API void WINAPI XListBox_RefreshItem(HELE hEle, int iItem);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������, XListBox_CreateAdapter() \ �б��_��������������()
//@���� hEle
//@���� pText
//@���� ����������
//@���� �б��_������ı�()
XC_API int WINAPI XListBox_AddItemText(HELE hEle, const wchar_t* pText);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XListBox_CreateAdapter() \ �б��_��������������()
//@���� hEle
//@���� pName
//@���� pText
//@���� �б��_������ı���չ()
XC_API int WINAPI XListBox_AddItemTextEx(HELE hEle, const wchar_t* pName, const wchar_t* pText);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XListBox_CreateAdapter() \ �б��_��������������()
//@���� hEle
//@���� hImage
//@���� �б��_�����ͼƬ()
XC_API int WINAPI XListBox_AddItemImage(HELE hEle, HIMAGE hImage);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XListBox_CreateAdapter() \ �б��_��������������()
//@���� hEle
//@���� pName
//@���� hImage
//@���� �б��_�����ͼƬ��չ()
XC_API int WINAPI XListBox_AddItemImageEx(HELE hEle, const wchar_t* pName, HIMAGE hImage);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XListBox_CreateAdapter() \ �б��_��������������()
//@���� hEle
//@���� iItem
//@���� pValue
//@���� �б��_�������ı�()
XC_API int WINAPI XListBox_InsertItemText(HELE hEle, int iItem, const wchar_t* pValue);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XListBox_CreateAdapter() \ �б��_��������������()
//@���� hEle
//@���� iItem
//@���� pName
//@���� pValue
//@���� �б��_�������ı���չ()
XC_API int WINAPI XListBox_InsertItemTextEx(HELE hEle, int iItem, const wchar_t* pName, const wchar_t* pValue);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XListBox_CreateAdapter() \ �б��_��������������()
//@���� hEle
//@���� iItem
//@���� hImage
//@���� �б��_������ͼƬ()
XC_API int WINAPI XListBox_InsertItemImage(HELE hEle, int iItem, HIMAGE hImage);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XListBox_CreateAdapter() \ �б��_��������������()
//@���� hEle
//@���� iItem
//@���� pName
//@���� hImage
//@���� �б��_������ͼƬ��չ()
XC_API int WINAPI XListBox_InsertItemImageEx(HELE hEle, int iItem, const wchar_t* pName, HIMAGE hImage);
//@���� hEle
//@���� iItem
//@���� iColumn
//@���� pText
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б��_�����ı�()
XC_API BOOL WINAPI XListBox_SetItemText(HELE hEle, int iItem, int iColumn, const wchar_t* pText);
//@���� hEle
//@���� iItem
//@���� pName
//@���� pText
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б��_�����ı���չ()
XC_API BOOL WINAPI XListBox_SetItemTextEx(HELE hEle, int iItem, const wchar_t* pName, const wchar_t* pText);
//@���� hEle
//@���� iItem
//@���� iColumn
//@���� hImage
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б��_����ͼƬ()
XC_API BOOL WINAPI XListBox_SetItemImage(HELE hEle, int iItem, int iColumn, HIMAGE hImage);
//@���� hEle
//@���� iItem
//@���� pName
//@���� hImage
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б��_����ͼƬ��չ()
XC_API BOOL WINAPI XListBox_SetItemImageEx(HELE hEle, int iItem, const wchar_t* pName, HIMAGE hImage);
//@���� hEle
//@���� iItem
//@���� iColumn
//@���� nValue
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б��_��������ֵ()
XC_API BOOL WINAPI XListBox_SetItemInt(HELE hEle, int iItem, int iColumn, int nValue);
//@���� hEle
//@���� iItem
//@���� pName
//@���� nValue
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б��_��������ֵ��չ()
XC_API BOOL WINAPI XListBox_SetItemIntEx(HELE hEle, int iItem, const wchar_t* pName, int nValue);
//@���� hEle
//@���� iItem
//@���� iColumn
//@���� fFloat
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б��_�����ֵ()
XC_API BOOL WINAPI XListBox_SetItemFloat(HELE hEle, int iItem, int iColumn, float fFloat);
//@���� hEle
//@���� iItem
//@���� pName
//@���� fFloat
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б��_�����ֵ��չ()
XC_API BOOL WINAPI XListBox_SetItemFloatEx(HELE hEle, int iItem, const wchar_t* pName, float fFloat);
//@���� hEle
//@���� iItem
//@���� iColumn
//@���� �����ı�����
//@���� �б��_ȡ���ı�()
XC_API const wchar_t* WINAPI XListBox_GetItemText(HELE hEle, int iItem, int iColumn);
//@���� hEle
//@���� iItem
//@���� pName
//@���� �����ı�����
//@���� �б��_ȡ���ı���չ()
XC_API const wchar_t* WINAPI XListBox_GetItemTextEx(HELE hEle, int iItem, const wchar_t* pName);
//@���� hEle
//@���� iItem
//@���� iColumn
//@���� �б��_ȡ��ͼƬ()
XC_API HIMAGE WINAPI XListBox_GetItemImage(HELE hEle, int iItem, int iColumn);
//@���� hEle
//@���� iItem
//@���� pName
//@���� �б��_ȡ��ͼƬ��չ()
XC_API HIMAGE WINAPI XListBox_GetItemImageEx(HELE hEle, int iItem, const wchar_t* pName);
//@���� hEle
//@���� iItem
//@���� iColumn
//@���� pOutValue
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б��_ȡ������ֵ()
XC_API BOOL WINAPI XListBox_GetItemInt(HELE hEle, int iItem, int iColumn, int* pOutValue);
//@���� hEle
//@���� iItem
//@���� pName
//@���� pOutValue
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б��_ȡ������ֵ��չ()
XC_API BOOL WINAPI XListBox_GetItemIntEx(HELE hEle, int iItem, const wchar_t* pName, int* pOutValue);
//@���� hEle
//@���� iItem
//@���� iColumn
//@���� pOutValue
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б��_ȡ���ֵ()
XC_API BOOL WINAPI XListBox_GetItemFloat(HELE hEle, int iItem, int iColumn, float* pOutValue);
//@���� hEle
//@���� iItem
//@���� pName
//@���� pOutValue
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б��_ȡ���ֵ��չ()
XC_API BOOL WINAPI XListBox_GetItemFloatEx(HELE hEle, int iItem, const wchar_t* pName, float* pOutValue);
//@���� hEle
//@���� iItem
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б��_ɾ����()
XC_API BOOL WINAPI XListBox_DeleteItem(HELE hEle, int iItem);
//@���� hEle
//@���� iItem
//@���� nCount
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б��_ɾ������չ()
XC_API BOOL WINAPI XListBox_DeleteItemEx(HELE hEle, int iItem, int nCount);
//@���� hEle
//@���� �б��_ɾ����ȫ��()
XC_API void WINAPI XListBox_DeleteItemAll(HELE hEle);
//@���� hEle
//@���� �б��_ɾ����ȫ��()
XC_API void WINAPI XListBox_DeleteColumnAll(HELE hEle);
//@���� hEle
//@���� �б��_ȡ������AD()
XC_API int WINAPI XListBox_GetCount_AD(HELE hEle);
//@���� hEle
//@���� �б��_ȡ������AD()
XC_API int WINAPI XListBox_GetCountColumn_AD(HELE hEle);
//@����}
//@����{  �б�

//@��ע �����б�Ԫ��.
//�����Ԫ����Դ���������ӵ�Ԫ��.
//@���� x Ԫ��x����.
//@���� y Ԫ��y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ���Ǵ�����Դ�����UIԪ����Դ���.����Ǵ�����Դ���������ӵ�����,
//@���� Ԫ�ؾ��.
//@���� �б�_����()
XC_API HELE WINAPI XList_Create(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@��ע �����б�Ԫ��, ʹ��������ģ��, �Զ���������������
//�����Ԫ����Դ���������ӵ�Ԫ��.
//@���� x Ԫ��x����.
//@���� y Ԫ��y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ���Ǵ�����Դ�����UIԪ����Դ���.����Ǵ�����Դ���������ӵ�����,
//@���� col_extend_count ������; ����:����ģ����1��, ���������5��, ��ô�˲�����5
//@���� Ԫ�ؾ��.
//@���� �б�_������չ()
XC_API HELE WINAPI XList_CreateEx(int x, int y, int cx, int cy, HXCGUI hParent, int col_extend_count);
//@��ע ������.
//@���� hEle Ԫ�ؾ��.
//@���� width �п��.
//@���� ����λ������.
//@���� �б�_������()
XC_API int WINAPI XList_AddColumn(HELE hEle, int width);
//@��ע ������.
//@���� hEle Ԫ�ؾ��.
//@���� width �п��.
//@���� iCol ����λ������.
//@���� ���ز���λ������.
//@���� �б�_������()
XC_API int WINAPI XList_InsertColumn(HELE hEle, int width, int iCol);
//@��ע ���û�رն�ѡ����.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� �б�_���ö�ѡ()
XC_API void WINAPI XList_EnableMultiSel(HELE hEle, BOOL bEnable);
//@��ע �����϶��ı��п��.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� �б�_�����϶������п�()
XC_API void WINAPI XList_EnableDragChangeColumnWidth(HELE hEle, BOOL bEnable);
//@��ע ���ô�ֱ��������������.
//@���� hEle Ԫ�ؾ��.
//@���� bTop �Ƿ�����.
//@���� �б�_���ô�ֱ��������������()
XC_API void WINAPI XList_EnableVScrollBarTop(HELE hEle, BOOL bTop);
//@��ע �����б�������ģʽ.
//@���� hEle Ԫ�ؾ��.
//@���� bFull �Ƿ���������.
//@���� �б�_�����б�������()
XC_API void WINAPI XList_EnableRowBkFull(HELE hEle, BOOL bFull);
//@���� hEle Ԫ�ؾ��
//@���� bEnable �Ƿ�����
//@���� �б�_���ù̶��и�()
XC_API void WINAPI XList_EnableFixedRowHeight(HELE hEle, BOOL bEnable);
//@���� hEle Ԫ�ؾ��
//@���� bEnable �Ƿ�����
//@���� �б�_����ģ�帴��()
XC_API void WINAPI XList_EnableTemplateReuse(HELE hEle, BOOL bEnable);
//@���� hEle Ԫ�ؾ��
//@���� bEnable �Ƿ�����
//@���� �б�_�������()
XC_API void WINAPI XList_EnableVirtualTable(HELE hEle, BOOL bEnable);
//@���� hEle Ԫ�ؾ��
//@���� nRowCount ����
//@���� �б�_���������()
XC_API void WINAPI XList_SetVirtualRowCount(HELE hEle, int nRowCount);
//@��ע ������������.
//@���� hEle Ԫ�ؾ��.
//@���� iColumn ������.
//@���� iColumnAdapter ��Ҫ����������������������е�������.
//@���� bEnable �Ƿ�����������.
//@���� �б�_������()
XC_API void WINAPI XList_SetSort(HELE hEle, int iColumn, int iColumnAdapter, BOOL bEnable);
//@��ע �����Ƿ����ָ��״̬���еı���.
//@���� hEle Ԫ�ؾ��.
//@���� nFlags ��־λ @ref list_drawItemBk_flag_.
//@���� �б�_���б������Ʊ�־()
XC_API void WINAPI XList_SetDrawRowBkFlags(HELE hEle, int style);
//@���� hEle Ԫ�ؾ��
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� �б�_�÷ָ�����ɫ()
XC_API void WINAPI XList_SetSplitLineColor(HELE hEle, COLORREF color);
//@��ע �����п��.
//@���� hEle Ԫ�ؾ��.
//@���� iRow ������.
//@���� width ���.
//@���� �б�_���п�()
XC_API void WINAPI XList_SetColumnWidth(HELE hEle, int iRow, int width);
//@��ע ��������С���.
//@���� hEle Ԫ�ؾ��.
//@���� iRow ������.
//@���� width ���.
//@���� �б�_������С���()
XC_API void WINAPI XList_SetColumnMinWidth(HELE hEle, int iRow, int width);
//@��ע ����ָ���п�ȹ̶�.
//@���� hEle Ԫ�ؾ��.
//@���� iColumn ������.
//@���� bFixed �Ƿ�̶����.
//@���� �б�_���п�ȹ̶�()
XC_API void WINAPI XList_SetColumnWidthFixed(HELE hEle, int iColumn, BOOL bFixed);
//@��ע ��ȡָ���п��.
//@���� hEle Ԫ�ؾ��.
//@���� iColumn ������.
//@���� ����ָ���п��.
//@���� �б�_ȡ�п��()
XC_API int WINAPI XList_GetColumnWidth(HELE hEle, int iColumn);
//@��ע ��ȡ������.
//@���� hEle Ԫ�ؾ��.
//@���� ����������.
//@���� �б�_ȡ������()
XC_API int WINAPI XList_GetColumnCount(HELE hEle);
//@��ע �������û�����.
//@���� hEle Ԫ�ؾ��.
//@���� iRow ������.
//@���� iColumn ������.
//@���� data �û�����.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б�_��������()
XC_API BOOL WINAPI XList_SetItemData(HELE hEle, int iRow, int iColumn, vint data);
//@��ע ��ȡ���û�����.
//@���� hEle Ԫ�ؾ��.
//@���� iRow ������.
//@���� iColumn ������.
//@���� �����û�����.
//@���� �б�_ȡ������()
XC_API vint WINAPI XList_GetItemData(HELE hEle, int iRow, int iColumn);
//@��ע ����ѡ����.
//@���� hEle Ԫ�ؾ��.
//@���� iRow ������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б�_��ѡ����()
XC_API BOOL WINAPI XList_SetSelectRow(HELE hEle, int iRow);
//@��ע ��ȡѡ����.
//@���� hEle Ԫ�ؾ��.
//@���� ������.
//@���� �б�_ȡѡ����()
XC_API int WINAPI XList_GetSelectRow(HELE hEle);
//@��ע ��ȡѡ��������.
//@���� hEle Ԫ�ؾ��.
//@���� ����ѡ��������.
//@���� �б�_ȡѡ��������()
XC_API int WINAPI XList_GetSelectRowCount(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б�_���ѡ����()
XC_API BOOL WINAPI XList_AddSelectRow(HELE hEle, int iRow);
//@��ע ѡ��ȫ����.
//@���� hEle Ԫ�ؾ��.
//@���� �б�_��ѡ��ȫ��()
XC_API void WINAPI XList_SetSelectAll(HELE hEle);
//@��ע ��ȡȫ��ѡ�����.
//@���� hEle Ԫ�ؾ��.
//@���� pArray ��������������.
//@���� nArraySize �����С(�����Ա��).
//@���� ����������.
//@���� �б�_ȡȫ��ѡ��()
XC_API int WINAPI XList_GetSelectAll(HELE hEle, int* pArray, int nArraySize);
//@��ע ������ͼ��ָ���пɼ�
//@���� hEle Ԫ�ؾ��.
//@���� iRow ������.
//@���� �б�_��ʾָ����()
XC_API void WINAPI XList_VisibleRow(HELE hEle, int iRow);
//@��ע ȡ��ѡ��ָ����(�������������Ϊ��).
//@���� hEle Ԫ�ؾ��.
//@���� iRow ������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б�_ȡ��ѡ����()
XC_API BOOL WINAPI XList_CancelSelectRow(HELE hEle, int iRow);
//@��ע ȡ��ѡ��������(�������������Ϊ��).
//@���� hEle Ԫ�ؾ��.
//@���� �б�_ȡ��ȫ��ѡ����()
XC_API void WINAPI XList_CancelSelectAll(HELE hEle);
//@��ע ��ȡ�б�ͷԪ��.
//@���� hEle Ԫ�ؾ��.
//@���� �����б�ͷԪ�ؾ��.
//@���� �б�_ȡ�б�ͷ()
XC_API HELE WINAPI XList_GetHeaderHELE(HELE hEle);
//@��ע ������������.
//@���� hEle Ԫ�ؾ��.
//@���� hAdapter ������������� XAdTable.
//@���� �б�_������������()
XC_API void WINAPI XList_BindAdapter(HELE hEle, HXCGUI hAdapter);
//@��ע �б�ͷ������������.
//@���� hEle Ԫ�ؾ��.
//@���� hAdapter ������������� XAdMap.
//@���� �б�_�б�ͷ������������()
XC_API void WINAPI XList_BindAdapterHeader(HELE hEle, HXCGUI hAdapter);
//@��ע �������������������ݰ󶨵���ģ���ʼ����������������(�ֶ���);
//�����������洢����, UI������ݰ󶨵��ֶ�����ʾ�����������ж�Ӧ������;
//����Ĭ��ģ����1��, ����������5��,��ô��������5
//@���� hEle Ԫ�ؾ��.
//@���� colExtend_count ������-Ԥ���б�������, Ĭ��ֵ0; ����������췶Χ, ���ⳬ����Χ, ���Ӳ���Ҫ���ֶ�.
//@���� �������������.
//@���� �б�_��������������()
XC_API HXCGUI WINAPI XList_CreateAdapter(HELE hEle, int col_extend_count=3);
//@��ע �������������������ݰ󶨵���ģ���ʼ����������������(�ֶ���);
//�����������洢����, UI������ݰ󶨵��ֶ�����ʾ�����������ж�Ӧ������;
//@���� hEle Ԫ�ؾ��.
//@���� �������������.
//@���� �б�_�б�ͷ��������������()
XC_API HXCGUI WINAPI XList_CreateAdapterHeader(HELE hEle);
//@��ע �������������������ݰ󶨵���ģ���ʼ����������������(�ֶ���);
//�����������洢����, UI������ݰ󶨵��ֶ�����ʾ�����������ж�Ӧ������;
//�˽ӿ��Ǽ򻯽ӿ�, �ϲ��� XList_CreateAdapter() �� XList_CreateAdapterHeader() �ӿ�;
//����Ĭ��ģ����1��, ����������5��,��ô��������5
//@���� hEle Ԫ�ؾ��.
//@���� col_extend_count ������-Ԥ���б�������, Ĭ��ֵ0; ����������췶Χ, ���ⳬ����Χ, ���Ӳ���Ҫ���ֶ�.
//@���� ����ɹ�����TRUE, ���򷵻�FALSE.
//@���� �б�_��������������2()
XC_API BOOL WINAPI XList_CreateAdapters(HELE hEle, int col_extend_count);
//@��ע ��ȡ����������.
//@���� hEle Ԫ�ؾ��.
//@���� �������������.
//@���� �б�_ȡ����������()
XC_API HXCGUI WINAPI XList_GetAdapter(HELE hEle);
//@��ע ��ȡ�б�ͷ����������.
//@���� hEle Ԫ�ؾ��.
//@���� �����������������.
//@���� �б�_�б�ͷ��ȡ����������()
XC_API HXCGUI WINAPI XList_GetAdapterHeader(HELE hEle);
//@��ע �������ģ���ļ�.
//@���� hEle Ԫ�ؾ��.
//@���� pXmlFile �ļ���.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б�_����ģ���ļ�()
XC_API BOOL WINAPI XList_SetItemTemplateXML(HELE hEle, const wchar_t* pXmlFile);
//@���� hEle Ԫ�ؾ��
//@���� data �ڵص�ַ
//@���� length �ڴ��С, �ֽ�Ϊ��λ
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� �б�_����ģ����ڴ�()
XC_API BOOL WINAPI XList_SetItemTemplateXMLFromMem(HELE hEle, void* data, int length);
//@��ע ��RC��ԴZIP����, RC��Դ���ͱ���Ϊ:"RT_RCDATA"
//@���� hEle Ԫ�ؾ��
//@���� id RC��ԴID
//@���� pFileName ��ģ���ļ���
//@���� pPassword zip����
//@���� hModule ģ����
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� �б�_����ģ�����ԴZIP()
XC_API BOOL WINAPI XList_SetItemTemplateXMLFromZipRes(HELE hEle, int id, const wchar_t* pFileName, const wchar_t* pPassword=NULL, HMODULE hModule=NULL);
//@��ע �������ģ���ļ�.
//@���� hEle Ԫ�ؾ��.
//@���� pStringXML �ַ���ָ��.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б�_����ģ����ַ���()
XC_API BOOL WINAPI XList_SetItemTemplateXMLFromString(HELE hEle, const char* pStringXML);
//@��ע �����б���ģ��.
//@���� hEle Ԫ�ؾ��.
//@���� hTemp ģ����, ֧���б�ͷ���б���ģ����(�ֱ�����һ��, �����԰�ͷ������ý�ȥ), �ڲ����Զ�ʶ��ģ������
//@���� ����ɹ�����TRUE,���򷵻�FALSE.
//@���� �б�_����ģ��()
XC_API BOOL WINAPI XList_SetItemTemplate(HELE hEle, HTEMP hTemp);
//@���� hEle Ԫ�ؾ��
//@���� �����б���ģ����
//@���� �б�_ȡ��ģ��()
XC_API HTEMP WINAPI XList_GetItemTemplate(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� �����б�ͷ��ģ����
//@���� �б�_ȡ��ģ���б�ͷ()
XC_API HTEMP WINAPI XList_GetItemTemplateHeader(HELE hEle);
//@��ע ͨ��ģ����ID,��ȡʵ����ģ����ID��Ӧ�Ķ�����.
//@���� hEle Ԫ�ؾ��.
//@���� iRow ������.
//@���� iColumn ������.
//@���� nTempItemID ģ����itemID.
//@���� �ɹ����ض�����,���򷵻�NULL.
//@���� �б�_ȡģ�����()
XC_API HXCGUI WINAPI XList_GetTemplateObject(HELE hEle, int iRow, int iColumn, int nTempItemID);
//@��ע ��ȡ��ǰ��������ģ��ʵ��,�����б�����һ��.
//@���� hEle Ԫ�ؾ��.
//@���� hXCGUI ������, UIԪ�ؾ������״������.
//@���� �ɹ�����������, ���򷵻�@ref XC_ID_ERROR.
//@���� �б�_ȡ����������()
XC_API int WINAPI XList_GetRowIndexFromHXCGUI(HELE hEle, HXCGUI hXCGUI);
//@��ע �б�ͷ,ͨ��ģ����ID,��ȡʵ����ģ����ID��Ӧ�Ķ�����.
//@���� hEle Ԫ�ؾ��.
//@���� iColumn �б�ͷ ������.
//@���� nTempItemID ģ����ID.
//@���� �ɹ����ض�����,���򷵻�NULL.
//@���� �б�_ȡ�б�ͷģ�����()
XC_API HXCGUI WINAPI XList_GetHeaderTemplateObject(HELE hEle, int iColumn, int nTempItemID);
//@��ע �б�ͷ,��ȡ��ǰ��������ģ��ʵ��,�����б�ͷ����һ����.
//@���� hEle Ԫ�ؾ��.
//@���� hXCGUI ������.
//@���� �ɹ�����������, ���򷵻�@ref XC_ID_ERROR.
//@���� �б�_ȡ�б�ͷ����������()
XC_API int WINAPI XList_GetHeaderColumnIndexFromHXCGUI(HELE hEle, HXCGUI hXCGUI);
//@��ע �����б�ͷ�߶�.
//@���� hEle Ԫ�ؾ��.
//@���� height �߶�.
//@���� �б�_���б�ͷ�߶�()
XC_API void WINAPI XList_SetHeaderHeight(HELE hEle, int height);
//@��ע ��ȡ�б�ͷ�߶�.
//@���� hEle Ԫ�ؾ��.
//@���� �����б�ͷ�߶�.
//@���� �б�_ȡ�б�ͷ�߶�()
XC_API int WINAPI XList_GetHeaderHeight(HELE hEle);
//@��ע ��ȡ��ǰ�ɼ��з�Χ.
//@���� hEle Ԫ�ؾ��.
//@���� piStart ��ʼ������.
//@���� piEnd ����������.
//@���� �б�_ȡ�����з�Χ()
XC_API void WINAPI XList_GetVisibleRowRange(HELE hEle, int* piStart, int* piEnd);
//@��ע ������Ĭ�ϸ߶�.
//@���� hEle Ԫ�ؾ��.
//@���� nHeight �߶�.
//@���� nSelHeight ѡ��ʱ�߶�.
//@���� �б�_����Ĭ�ϸ߶�()
XC_API void WINAPI XList_SetRowHeightDefault(HELE hEle, int nHeight, int nSelHeight);
//@��ע ��ȡ��Ĭ�ϸ߶�.
//@���� hEle Ԫ�ؾ��.
//@���� pHeight �߶�.
//@���� pSelHeight ѡ��ʱ�߶�.
//@���� �б�_ȡ��Ĭ�ϸ߶�()
XC_API void WINAPI XList_GetRowHeightDefault(HELE hEle, int* pHeight, int* pSelHeight);
//@��ע �����и߶�.
//@���� hEle Ԫ�ؾ��.
//@���� iRow ������
//@���� nHeight �߶�.
//@���� nSelHeight ѡ��ʱ�߶�.
//@���� �б�_���и߶�()
XC_API void WINAPI XList_SetRowHeight(HELE hEle, int iRow, int nHeight, int nSelHeight);
//@��ע ��ȡ�и߶�.
//@���� hEle Ԫ�ؾ��.
//@���� iRow ������
//@���� pHeight �߶�.
//@���� pSelHeight ѡ��ʱ�߶�.
//@���� �б�_ȡ�и߶�()
XC_API void WINAPI XList_GetRowHeight(HELE hEle, int iRow, int* pHeight, int* pSelHeight);
//@��ע �����м��.
//@���� hEle Ԫ�ؾ��.
//@���� nSpace �м���С.
//@���� �б�_���м��()
XC_API void WINAPI XList_SetRowSpace(HELE hEle, int nSpace);
//@��ע ��ȡ�м���С.
//@���� hEle Ԫ�ؾ��.
//@���� �����м���С.
//@���� �б�_ȡ�м��()
XC_API int WINAPI XList_GetRowSpace(HELE hEle);
//@��ע ������,������������зֽ�������.
//@���� hEle Ԫ�ؾ��.
//@���� iColumn ������,-1��������.
//@���� �б�_�����������()
XC_API void WINAPI XList_SetLockColumnLeft(HELE hEle, int iColumn);
//@��ע ������,�����Ҳ������зֽ�������
//@���� hEle Ԫ�ؾ��.
//@���� iColumn ������, -1��������. ��ʱֻ֧������ĩβ��
//@���� �б�_���������Ҳ�()
XC_API void WINAPI XList_SetLockColumnRight(HELE hEle, int iColumn);
//@��ע �����Ƿ�����ĩβ��.
//@���� hEle Ԫ�ؾ��.
//@���� bLock �Ƿ�����.
//@���� �б�_�������еײ�()
XC_API void WINAPI XList_SetLockRowBottom(HELE hEle, BOOL bLock);
//@���� hEle Ԫ�ؾ��
//@���� bOverlap �Ƿ��ص�
//@���� �б�_�������еײ��ص�()
XC_API void WINAPI XList_SetLockRowBottomOverlap(HELE hEle, BOOL bOverlap);
//@���� hEle Ԫ�ؾ��
//@���� color ��ɫֵ ��ʹ�ú�: RGBA()
//@���� width �߿��
//@���� �б�_���϶�������ɫ()
XC_API void WINAPI XList_SetDragRectColor(HELE hEle, COLORREF color, int width);
//@��ע ��������������.
//@���� hEle Ԫ�ؾ��.
//@���� pPt �����.
//@���� piRow ������.
//@���� piColumn ������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б�_���Ե����()
XC_API BOOL WINAPI XList_HitTest(HELE hEle, POINT* pPt, int* piRow, int* piColumn);
//@��ע ��������������,�Զ���ӹ�����ͼƫ����.
//@���� hEle Ԫ�ؾ��.
//@���� pPt �����.
//@���� piRow ������.
//@���� piColumn ������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б�_���Ե������չ()
XC_API BOOL WINAPI XList_HitTestOffset(HELE hEle, POINT* pPt, int* piRow, int* piColumn);
//@��ע �޸����ݺ�,ˢ��������ģ��,�Ա�������ݵ�ģ��(�����ɼ�).
//@���� hEle Ԫ�ؾ��.
//@���� �б�_ˢ������()
XC_API void WINAPI XList_RefreshData(HELE hEle);
//@��ע �޸����ݺ�,ˢ��ָ����ģ��,�Ա�������ݵ�ģ��(�����ǰ��ɼ�).
//@���� hEle Ԫ�ؾ��.
//@���� iRow ������.
//@���� �б�_ˢ��ָ����()
XC_API void WINAPI XList_RefreshRow(HELE hEle, int iRow);
//@���� hEle  Ԫ�ؾ��
//@���� �б�_ˢ�������б�ͷ()
XC_API void WINAPI XList_RefreshDataHeader(HELE hEle);
//@��ע ɾ����.
//�б�ͷ-ɾ��ָ����, �б���ֲ���, �������������ֲ���, ������б�ͷ��
//@���� hEle Ԫ�ؾ��.
//@���� iColumn ������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б�_ɾ����()
XC_API BOOL WINAPI XList_DeleteColumn(HELE hEle, int iColumn);
//@��ע �б�ͷ-ɾ�����е���, �б���ֲ���, �������������ֲ���, ������б�ͷ��
//@���� hEle Ԫ�ؾ��.
//@���� �б�_ɾ����ȫ��()
XC_API void WINAPI XList_DeleteColumnAll(HELE hEle);
//@��ע �򻯽ӿ�
//�״��������ʱ, ��Ҫ�ȴ�������������  XList_CreateAdapters() \ �б�_��������������2()
//@���� hEle Ԫ�ؾ��
//@���� nWidth �п��
//@���� pText �����ı�
//@���� ����������
//@���� �б�_������ı�2()
XC_API int WINAPI XList_AddColumnText2(HELE hEle, int nWidth, const wchar_t* pText);
//@��ע �򻯽ӿ�
//�״��������ʱ, ��Ҫ�ȴ�������������  XList_CreateAdapters() \ �б�_��������������2()
//@���� hEle Ԫ�ؾ��
//@���� nWidth �п��
//@���� hImage ͼƬ���
//@���� ����������
//@���� �б�_�����ͼƬ2()
XC_API int WINAPI XList_AddColumnImage2(HELE hEle, int nWidth, HIMAGE hImage);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XList_CreateAdapters() \ �б�_��������������2()
//@���� hEle Ԫ�ؾ��
//@���� nWidth
//@���� pName
//@���� pText
//@���� ������
//@���� �б�_������ı�()
XC_API int WINAPI XList_AddColumnText(HELE hEle, int nWidth, const wchar_t* pName, const wchar_t* pText);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XList_CreateAdapters() \ �б�_��������������2()
//@���� hEle Ԫ�ؾ��
//@���� nWidth
//@���� pName
//@���� hImage
//@���� ������
//@���� �б�_�����ͼƬ()
XC_API int WINAPI XList_AddColumnImage(HELE hEle, int nWidth, const wchar_t* pName, HIMAGE hImage);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XList_CreateAdapters() \ �б�_��������������2()
//@���� hEle Ԫ�ؾ��
//@���� pText
//@���� ������
//@���� �б�_������ı�()
XC_API int WINAPI XList_AddRowText(HELE hEle, const wchar_t* pText);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XList_CreateAdapters() \ �б�_��������������2()
//@���� hEle Ԫ�ؾ��
//@���� pName
//@���� pText
//@���� ������
//@���� �б�_������ı���չ()
XC_API int WINAPI XList_AddRowTextEx(HELE hEle, const wchar_t* pName, const wchar_t* pText);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XList_CreateAdapters() \ �б�_��������������2()
//@���� hEle Ԫ�ؾ��
//@���� hImage
//@���� ������
//@���� �б�_�����ͼƬ()
XC_API int WINAPI XList_AddRowImage(HELE hEle, HIMAGE hImage);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XList_CreateAdapters() \ �б�_��������������2()
//@���� hEle Ԫ�ؾ��
//@���� pName
//@���� hImage
//@���� ������
//@���� �б�_�����ͼƬ��չ()
XC_API int WINAPI XList_AddRowImageEx(HELE hEle, const wchar_t* pName, HIMAGE hImage);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XList_CreateAdapters() \ �б�_��������������2()
//@���� hEle Ԫ�ؾ��
//@���� iRow
//@���� pValue
//@���� ������
//@���� �б�_�������ı�()
XC_API int WINAPI XList_InsertRowText(HELE hEle, int iRow, const wchar_t* pValue);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XList_CreateAdapters() \ �б�_��������������2()
//@���� hEle Ԫ�ؾ��
//@���� iRow
//@���� pName
//@���� pValue
//@���� ������
//@���� �б�_�������ı���չ()
XC_API int WINAPI XList_InsertRowTextEx(HELE hEle, int iRow, const wchar_t* pName, const wchar_t* pValue);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XList_CreateAdapters() \ �б�_��������������2()
//@���� hEle Ԫ�ؾ��
//@���� iRow
//@���� hImage
//@���� ������
//@���� �б�_������ͼƬ()
XC_API int WINAPI XList_InsertRowImage(HELE hEle, int iRow, HIMAGE hImage);
//@��ע �״��������ʱ, ��Ҫ�ȴ�������������  XList_CreateAdapters() \ �б�_��������������2()
//@���� hEle Ԫ�ؾ��
//@���� iRow
//@���� pName
//@���� hImage
//@���� ������
//@���� �б�_������ͼƬ��չ()
XC_API int WINAPI XList_InsertRowImageEx(HELE hEle, int iRow, const wchar_t* pName, HIMAGE hImage);
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� iColumn �������������е�������
//@���� pText
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б�_�����ı�()
XC_API BOOL WINAPI XList_SetItemText(HELE hEle, int iRow, int iColumn, const wchar_t* pText);
//@���� hEle Ԫ�ؾ��
//@���� iRow
//@���� pName
//@���� pText
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б�_�����ı���չ()
XC_API BOOL WINAPI XList_SetItemTextEx(HELE hEle, int iRow, const wchar_t* pName, const wchar_t* pText);
//@���� hEle Ԫ�ؾ��
//@���� iRow
//@���� iColumn
//@���� hImage
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б�_����ͼƬ()
XC_API BOOL WINAPI XList_SetItemImage(HELE hEle, int iRow, int iColumn, HIMAGE hImage);
//@���� hEle Ԫ�ؾ��
//@���� iRow
//@���� pName
//@���� hImage
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б�_����ͼƬ��չ()
XC_API BOOL WINAPI XList_SetItemImageEx(HELE hEle, int iRow, const wchar_t* pName, HIMAGE hImage);
//@���� hEle Ԫ�ؾ��
//@���� iRow
//@���� iColumn
//@���� nValue
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б�_����ָ��ֵ()
XC_API BOOL WINAPI XList_SetItemInt(HELE hEle, int iRow, int iColumn, int nValue);
//@���� hEle Ԫ�ؾ��
//@���� iRow
//@���� pName
//@���� nValue
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б�_��������ֵ��չ()
XC_API BOOL WINAPI XList_SetItemIntEx(HELE hEle, int iRow, const wchar_t* pName, int nValue);
//@���� hEle Ԫ�ؾ��
//@���� iRow
//@���� iColumn
//@���� fFloat
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б�_�����ֵ()
XC_API BOOL WINAPI XList_SetItemFloat(HELE hEle, int iRow, int iColumn, float fFloat);
//@���� hEle Ԫ�ؾ��
//@���� iRow
//@���� pName
//@���� fFloat
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б�_�����ֵ��չ()
XC_API BOOL WINAPI XList_SetItemFloatEx(HELE hEle, int iRow, const wchar_t* pName, float fFloat);
//@���� hEle Ԫ�ؾ��
//@���� iRow
//@���� iColumn
//@���� �����ı�����
//@���� �б�_ȡ���ı�()
XC_API const wchar_t* WINAPI XList_GetItemText(HELE hEle, int iRow, int iColumn);
//@���� hEle Ԫ�ؾ��
//@���� iRow
//@���� pName
//@���� �����ı�����
//@���� �б�_ȡ���ı���չ()
XC_API const wchar_t* WINAPI XList_GetItemTextEx(HELE hEle, int iRow, const wchar_t* pName);
//@���� hEle Ԫ�ؾ��
//@���� iRow
//@���� iColumn
//@���� �б�_ȡ��ͼƬ()
XC_API HIMAGE WINAPI XList_GetItemImage(HELE hEle, int iRow, int iColumn);
//@���� hEle Ԫ�ؾ��
//@���� iRow
//@���� pName
//@���� �б�_ȡ��ͼƬ��չ()
XC_API HIMAGE WINAPI XList_GetItemImageEx(HELE hEle, int iRow, const wchar_t* pName);
//@���� hEle Ԫ�ؾ��
//@���� iRow
//@���� iColumn
//@���� pOutValue
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б�_ȡ������ֵ()
XC_API BOOL WINAPI XList_GetItemInt(HELE hEle, int iRow, int iColumn, int* pOutValue);
//@���� hEle Ԫ�ؾ��
//@���� iRow
//@���� pName
//@���� pOutValue
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б�_ȡ������ֵ��չ()
XC_API BOOL WINAPI XList_GetItemIntEx(HELE hEle, int iRow, const wchar_t* pName, int* pOutValue);
//@���� hEle Ԫ�ؾ��
//@���� iRow
//@���� iColumn
//@���� pOutValue
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б�_ȡ���ֵ()
XC_API BOOL WINAPI XList_GetItemFloat(HELE hEle, int iRow, int iColumn, float* pOutValue);
//@���� hEle Ԫ�ؾ��
//@���� iRow
//@���� pName
//@���� pOutValue
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б�_ȡ���ֵ��չ()
XC_API BOOL WINAPI XList_GetItemFloatEx(HELE hEle, int iRow, const wchar_t* pName, float* pOutValue);
//@��ע ����������-ɾ��ָ���� �� �б�ɾ��ָ����, �б�ͷ���ֲ���, ������б���
//@���� hEle Ԫ�ؾ��
//@���� iRow
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б�_ɾ����()
XC_API BOOL WINAPI XList_DeleteRow(HELE hEle, int iRow);
//@��ע ����������-ɾ��ָ���������� �� �б�ɾ��ָ��������, �б�ͷ���ֲ���, ������б���
//@���� hEle Ԫ�ؾ��
//@���� iRow
//@���� nCount
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б�_ɾ������չ()
XC_API BOOL WINAPI XList_DeleteRowEx(HELE hEle, int iRow, int nCount);
//@��ע ����������-ɾ��ȫ���� �� �б�ɾ��ȫ����, �б�ͷ���ֲ���, ������б���
//@���� hEle Ԫ�ؾ��
//@���� �б�_ɾ����ȫ��()
XC_API void WINAPI XList_DeleteRowAll(HELE hEle);
//@��ע ����������-������������� �� �б�ɾ��ȫ����, �б�ͷ���ֲ���, ������б���
//@���� hEle Ԫ�ؾ��
//@���� �б�_ɾ����ȫ��AD()
XC_API void WINAPI XList_DeleteColumnAll_AD(HELE hEle);
//@��ע ����������-��ȡ������
//@���� hEle Ԫ�ؾ��
//@���� ����������
//@���� �б�_ȡ������AD()
XC_API int WINAPI XList_GetCount_AD(HELE hEle);
//@��ע ����������-��ȡ������
//@���� hEle Ԫ�ؾ��
//@���� ����������
//@���� �б�_ȡ������AD()
XC_API int WINAPI XList_GetCountColumn_AD(HELE hEle);
//@����}
//@����{  �б���ͼ

//@��ע �����б���ͼԪ��.
//�����Ԫ����Դ���������ӵ�Ԫ��.
//@���� x Ԫ��x����.
//@���� y Ԫ��y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ���Ǵ�����Դ�����U IԪ����Դ���.����Ǵ�����Դ���������ӵ�����,
//@���� Ԫ�ؾ��.
//@���� �б���_����()
XC_API HELE WINAPI XListView_Create(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@��ע �����б���ͼԪ��. ʹ��������ģ��, �Զ���������������
//�����Ԫ����Դ���������ӵ�Ԫ��.
//@���� x Ԫ��x����.
//@���� y Ԫ��y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ���Ǵ�����Դ�����U IԪ����Դ���.����Ǵ�����Դ���������ӵ�����,
//@���� Ԫ�ؾ��.
//@���� �б���_������չ()
XC_API HELE WINAPI XListView_CreateEx(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@��ע �������������������ݰ󶨵���ģ���ʼ����������������(�ֶ���);
//�����������洢����, UI������ݰ󶨵��ֶ�����ʾ�����������ж�Ӧ������;
//@���� hEle Ԫ�ؾ��.
//@���� �������������.
//@���� �б���_��������������()
XC_API HXCGUI WINAPI XListView_CreateAdapter(HELE hEle);
//@��ע ������������.
//@���� hEle Ԫ�ؾ��.
//@���� hAdapter ���������� XAdListView.
//@���� �б���_������������()
XC_API void WINAPI XListView_BindAdapter(HELE hEle, HXCGUI hAdapter);
//@��ע ��ȡ����������.
//@���� hEle Ԫ�ؾ��.
//@���� ��������������.
//@���� �б���_ȡ����������()
XC_API HXCGUI WINAPI XListView_GetAdapter(HELE hEle);
//@��ע ����ģ���ļ�.
//@���� hEle Ԫ�ؾ��.
//@���� pXmlFile �ļ���.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б���_����ģ���ļ�()
XC_API BOOL WINAPI XListView_SetItemTemplateXML(HELE hEle, const wchar_t* pXmlFile);
//@��ע �����ģ��.
//@���� hEle Ԫ�ؾ��.
//@���� pStringXML �ַ���ָ��.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б���_����ģ����ַ���()
XC_API BOOL WINAPI XListView_SetItemTemplateXMLFromString(HELE hEle, const char* pStringXML);
//@���� hEle Ԫ�ؾ��
//@���� data �ڴ��ַ
//@���� length �ڴ��С, �ֽ�Ϊ��λ
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� �б���_����ģ����ڴ�()
XC_API BOOL WINAPI XListView_SetItemTemplateXMLFromMem(HELE hEle, void* data, int length);
//@��ע RC��Դ���ͱ���Ϊ:"RT_RCDATA"
//@���� hEle Ԫ�ؾ��
//@���� id RC��ԴID
//@���� pFileName �ļ���
//@���� pPassword zip����
//@���� hModule ģ����
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� �б���_����ģ�����ԴZIP()
XC_API BOOL WINAPI XListView_SetItemTemplateXMLFromZipRes(HELE hEle, int id, const wchar_t* pFileName, const wchar_t* pPassword, HMODULE hModule);
//@��ע ���б���ģ��.
//@���� hEle Ԫ�ؾ��.
//@���� hTemp ģ����, ֧�������ģ����(�ֱ�����һ��, �����԰��������ý�ȥ), �ڲ����Զ�ʶ��ģ������
//@���� ����ɹ�����TRUE,���򷵻�FALSE.
//@���� �б���_����ģ��()
XC_API BOOL WINAPI XListView_SetItemTemplate(HELE hEle, HTEMP hTemp);
//@���� hEle Ԫ�ؾ��
//@���� ������ģ����
//@���� �б���_ȡ��ģ��()
XC_API HTEMP WINAPI XListView_GetItemTemplate(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� ������ģ������
//@���� �б���_ȡ��ģ����()
XC_API HTEMP WINAPI XListView_GetItemTemplateGroup(HELE hEle);
//@��ע ͨ��ģ����ID,��ȡʵ����ģ����ID��Ӧ�Ķ�����.
//@���� hEle Ԫ�ؾ��.
//@���� iGroup ������.
//@���� iItem ������.
//@���� nTempItemID ģ����ID.
//@���� �ɹ����ض�����,���򷵻�NULL.
//@���� �б���_ȡģ�����()
XC_API HXCGUI WINAPI XListView_GetTemplateObject(HELE hEle, int iGroup, int iItem, int nTempItemID);
//@��ע ͨ��ģ����ID,��ȡʵ����ģ����ID��Ӧ�Ķ�����.
//@���� hEle Ԫ�ؾ��.
//@���� iGroup ������.
//@���� nTempItemID ģ����ID.
//@���� �ɹ����ض�����,���򷵻�NULL.
//@���� �б���_ȡģ�������()
XC_API HXCGUI WINAPI XListView_GetTemplateObjectGroup(HELE hEle, int iGroup, int nTempItemID);
//@��ע ��ȡ��ǰ��������ģ��ʵ��,�����б�������һ����.
//@���� hEle Ԫ�ؾ��.
//@���� hXCGUI ������, UIԪ�ؾ������״������.
//@���� piGroup ����������.
//@���� piItem ����������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б���_ȡ����������()
XC_API BOOL WINAPI XListView_GetItemIDFromHXCGUI(HELE hEle, HXCGUI hXCGUI, int* piGroup, int* piItem);
//@��ע ��������������.
//@���� hEle Ԫ�ؾ��.
//@���� pPt �����.
//@���� pOutGroup ����������.
//@���� pOutItem ����������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б���_���Ե����()
XC_API BOOL WINAPI XListView_HitTest(HELE hEle, POINT* pPt, int* pOutGroup, int* pOutItem);
//@��ע ��������������,�Զ���ӹ�����ͼƫ����.
//@���� hEle Ԫ�ؾ��.
//@���� pPt �����.
//@���� pOutGroup ����������.
//@���� pOutItem ����������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б���_���Ե������չ()
XC_API BOOL WINAPI XListView_HitTestOffset(HELE hEle, POINT* pPt, int* pOutGroup, int* pOutItem);
//@��ע ���ö�ѡ.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� �б���_���ö�ѡ()
XC_API void WINAPI XListView_EnableMultiSel(HELE hEle, BOOL bEnable);
//@���� hEle Ԫ�ؾ��
//@���� bEnable �Ƿ�����
//@���� �б���_����ģ�帴��()
XC_API void WINAPI XListView_EnableTemplateReuse(HELE hEle, BOOL bEnable);
//@���� hEle Ԫ�ؾ��
//@���� bEnable �Ƿ�����
//@���� �б���_�������()
XC_API void WINAPI XListView_EnableVirtualTable(HELE hEle, BOOL bEnable);
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� nCount ������
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б���_�����������()
XC_API BOOL WINAPI XListView_SetVirtualItemCount(HELE hEle, int iGroup, int nCount);
//@��ע ���Ƿ����ָ��״̬����ı���.
//@���� hEle Ԫ�ؾ��.
//@���� nFlags ��־λ  @ref list_drawItemBk_flag_.
//@���� �б���_��������Ʊ�־()
XC_API void WINAPI XListView_SetDrawItemBkFlags(HELE hEle, int nFlags);
//@��ע ��ѡ����.
//@���� hEle Ԫ�ؾ��.
//@���� iGroup ������.
//@���� iItem ������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б���_��ѡ����()
XC_API BOOL WINAPI XListView_SetSelectItem(HELE hEle, int iGroup, int iItem);
//@��ע ��ȡѡ����.
//@���� hEle Ԫ�ؾ��.
//@���� piGroup ����������.
//@���� piItem ����������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б���_ȡѡ����()
XC_API BOOL WINAPI XListView_GetSelectItem(HELE hEle, int* piGroup, int* piItem);
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� iItem ������
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б���_���ѡ����()
XC_API BOOL WINAPI XListView_AddSelectItem(HELE hEle, int iGroup, int iItem);
//@��ע ������ͼ��ָ����ɼ�
//@���� hEle Ԫ�ؾ��.
//@���� iGroup ������.
//@���� iItem ������.
//@���� �б���_��ʾָ����()
XC_API void WINAPI XListView_VisibleItem(HELE hEle, int iGroup, int iItem);
//@��ע ��ȡ��ǰ�ɼ��Χ.
//@���� hEle Ԫ�ؾ��
//@���� piGroup1 ���ӿ�ʼ��, ��ɼ�
//@���� piGroup2 ���ӽ�����, ��ɼ�
//@���� piStartGroup ���ӿ�ʼ��
//@���� piStartItem ���ӿ�ʼ��
//@���� piEndGroup ���ӽ�����
//@���� piEndItem ���ӽ�����
//@���� �б���_ȡ�����Χ()
XC_API void WINAPI XListView_GetVisibleItemRange(HELE hEle, int* piGroup1, int* piGroup2, int* piStartGroup, int* piStartItem, int* piEndGroup, int* piEndItem);
//@��ע ��ȡѡ��������.
//@���� hEle Ԫ�ؾ��.
//@���� ����ѡ��������.
//@���� �б���_ȡѡ��������()
XC_API int WINAPI XListView_GetSelectItemCount(HELE hEle);
//@��ע ��ȡѡ�����ID.
//@���� hEle Ԫ�ؾ��.
//@���� pArray ����,��������ѡ����ID.
//@���� nArraySize �����С(�����Ա��).
//@���� ���ؽ���������.
//@���� �б���_ȡѡ����ȫ��()
XC_API int WINAPI XListView_GetSelectAll(HELE hEle, listView_item_id_* pArray, int nArraySize);
//@��ע ѡ�����е���.
//@���� hEle Ԫ�ؾ��.
//@���� �б���_��ѡ����ȫ��()
XC_API void WINAPI XListView_SetSelectAll(HELE hEle);
//@��ע ȡ��ѡ��������.
//@���� hEle Ԫ�ؾ��.
//@���� �б���_ȡ��ѡ����ȫ��()
XC_API void WINAPI XListView_CancelSelectAll(HELE hEle);
//@��ע ���м����С.
//@���� hEle Ԫ�ؾ��.
//@���� space �����С.
//@���� �б���_���м��()
XC_API void WINAPI XListView_SetColumnSpace(HELE hEle, int space);
//@��ע ���м����С.
//@���� hEle Ԫ�ؾ��.
//@���� space �����С.
//@���� �б���_���м��()
XC_API void WINAPI XListView_SetRowSpace(HELE hEle, int space);
//@��ע �����С.
//@���� hEle Ԫ�ؾ��.
//@���� width ���.
//@���� height �߶�.
//@���� �б���_�����С()
XC_API void WINAPI XListView_SetItemSize(HELE hEle, int width, int height);
//@��ע ��ȡ���С.
//@���� hEle Ԫ�ؾ��.
//@���� pSize ���շ��ش�С.
//@���� �б���_ȡ���С()
XC_API void WINAPI XListView_GetItemSize(HELE hEle, SIZE* pSize);
//@��ע ����߶�.
//@���� hEle Ԫ�ؾ��.
//@���� height �߶�.
//@���� �б���_����߶�()
XC_API void WINAPI XListView_SetGroupHeight(HELE hEle, int height);
//@��ע ��ȡ��߶�.
//@���� hEle Ԫ�ؾ��.
//@���� ������߶�.
//@���� �б���_ȡ��߶�()
XC_API int WINAPI XListView_GetGroupHeight(HELE hEle);
//@��ע �����û�����.
//@���� hEle Ԫ�ؾ��.
//@���� iGroup ������.
//@���� nData ����.
//@���� �б���_�����û�����()
XC_API void WINAPI XListView_SetGroupUserData(HELE hEle, int iGroup, vint nData);
//@��ע �������û�����.
//@���� hEle Ԫ�ؾ��.
//@���� iGroup ������.
//@���� iItem ������.
//@���� nData ����.
//@���� �б���_�����û�����()
XC_API void WINAPI XListView_SetItemUserData(HELE hEle, int iGroup, int iItem, vint nData);
//@��ע ��ȡ���û�����.
//@���� hEle Ԫ�ؾ��.
//@���� iGroup ������.
//@���� �����û�����.
//@���� �б���_ȡ���û�����()
XC_API vint WINAPI XListView_GetGroupUserData(HELE hEle, int iGroup);
//@��ע ��ȡ���û�����.
//@���� hEle Ԫ�ؾ��.
//@���� iGroup ������.
//@���� iItem ������.
//@���� �����û�����.
//@���� �б���_ȡ���û�����()
XC_API vint WINAPI XListView_GetItemUserData(HELE hEle, int iGroup, int iItem);
//@���� hEle Ԫ�ؾ��
//@���� color ��ɫֵ ��ʹ�ú�: RGBA()
//@���� width �߿��
//@���� �б���_���϶�������ɫ()
XC_API void WINAPI XListView_SetDragRectColor(HELE hEle, COLORREF color, int width);
//@��ע �޸����ݺ�,ˢ��������ģ��,�Ա�������ݵ�ģ��(�����ɼ�).
//@���� hEle Ԫ�ؾ��.
//@���� �б���_ˢ������()
XC_API void WINAPI XListView_RefreshData(HELE hEle);
//@��ע �޸����ݺ�,ˢ��ָ����ģ��,�Ա�������ݵ�ģ��(�����ǰ��ɼ�).
//@���� hEle Ԫ�ؾ��.
//@���� iGroup ������.
//@���� iItem ������, ���Ϊ-1,����Ϊ��.
//@���� �б���_ˢ��ָ����()
XC_API void WINAPI XListView_RefreshItem(HELE hEle, int iGroup, int iItem);
//@��ע չ����.
//@���� hEle Ԫ�ؾ��.
//@���� iGroup ������.
//@���� bExpand �Ƿ�չ��.
//@���� �ɹ�����TRUE���򷵻�FALSE,���״̬û�иı䷵��FALSE.
//@���� �б���_չ����()
XC_API BOOL WINAPI XListView_ExpandGroup(HELE hEle, int iGroup, BOOL bExpand);
//@��ע �״��������ʱ, ��Ҫ�ȴ������������� XListView_CreateAdapter() \ �б���_��������������()
//@���� hEle Ԫ�ؾ��
//@���� pName �ֶγ�
//@���� ����������
//@���� �б���_�������()
XC_API int WINAPI XListView_Group_AddColumn(HELE hEle, const wchar_t* pName);
//@��ע �״��������ʱ, ��Ҫ�ȴ������������� XListView_CreateAdapter() \ �б���_��������������()
//@���� hEle Ԫ�ؾ��
//@���� pValue ֵ
//@���� iPos ����λ��, -1��ӵ�ĩβ
//@���� ����������
//@���� �б���_��������ı�()
XC_API int WINAPI XListView_Group_AddItemText(HELE hEle, const wchar_t* pValue, int iPos);
//@��ע �״��������ʱ, ��Ҫ�ȴ������������� XListView_CreateAdapter() \ �б���_��������������()
//@���� hEle Ԫ�ؾ��
//@���� pName �ֶγ�
//@���� pValue ֵ
//@���� iPos ����λ��, -1��ӵ�ĩβ
//@���� ����������
//@���� �б���_��������ı���չ()
XC_API int WINAPI XListView_Group_AddItemTextEx(HELE hEle, const wchar_t* pName, const wchar_t* pValue, int iPos);
//@��ע �״��������ʱ, ��Ҫ�ȴ������������� XListView_CreateAdapter() \ �б���_��������������()
//@���� hEle Ԫ�ؾ��
//@���� hImage ͼƬ���
//@���� iPos ����λ��, -1��ӵ�ĩβ
//@���� ����������
//@���� �б���_�������ͼƬ()
XC_API int WINAPI XListView_Group_AddItemImage(HELE hEle, HIMAGE hImage, int iPos);
//@��ע �״��������ʱ, ��Ҫ�ȴ������������� XListView_CreateAdapter() \ �б���_��������������()
//@���� hEle Ԫ�ؾ��
//@���� pName �ֶγ�
//@���� hImage ͼƬ���
//@���� iPos ����λ��, -1��ӵ�ĩβ
//@���� ����������
//@���� �б���_�������ͼƬ��չ()
XC_API int WINAPI XListView_Group_AddItemImageEx(HELE hEle, const wchar_t* pName, HIMAGE hImage, int iPos);
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� iColumn ������
//@���� pValue ֵ
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б���_�����ı�()
XC_API BOOL WINAPI XListView_Group_SetText(HELE hEle, int iGroup, int iColumn, const wchar_t* pValue);
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� pName �ֶ���
//@���� pValue ֵ
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б���_�����ı���չ()
XC_API BOOL WINAPI XListView_Group_SetTextEx(HELE hEle, int iGroup, const wchar_t* pName, const wchar_t* pValue);
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� iColumn ������
//@���� hImage ͼƬ���
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б���_����ͼƬ()
XC_API BOOL WINAPI XListView_Group_SetImage(HELE hEle, int iGroup, int iColumn, HIMAGE hImage);
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� pName �ֶ���
//@���� hImage ͼƬ���
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б���_����ͼƬ��չ()
XC_API BOOL WINAPI XListView_Group_SetImageEx(HELE hEle, int iGroup, const wchar_t* pName, HIMAGE hImage);
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� iColumn ������
//@���� �����ı�����
//@���� �б���_��ȡ�ı�()
XC_API const wchar_t* WINAPI XListView_Group_GetText(HELE hEle, int iGroup, int iColumn);
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� pName �ֶ�����
//@���� �����ı�����
//@���� �б���_��ȡ�ı���չ()
XC_API const wchar_t* XListView_Group_GetTextEx(HELE hEle, int iGroup, const wchar_t* pName);
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� iColumn ������
//@���� ����ͼƬ���
//@���� �б���_��ȡͼƬ()
XC_API HIMAGE WINAPI XListView_Group_GetImage(HELE hEle, int iGroup, int iColumn);
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� pName �ֶ�����
//@���� ����ͼƬ���
//@���� �б���_��ȡͼƬ��չ()
XC_API HIMAGE WINAPI XListView_Group_GetImageEx(HELE hEle, int iGroup, const wchar_t* pName);
//@���� hEle Ԫ�ؾ��
//@���� ����������
//@���� �б���_���ȡ����()
XC_API int WINAPI XListView_Group_GetCount(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� �ɹ�����������,���򷵻� @ref XC_ID_ERROR.
//@���� �б���_��ȡ����()
XC_API int WINAPI XListView_Item_GetCount(HELE hEle, int iGroup);
//@��ע �״��������ʱ, ��Ҫ�ȴ������������� XListView_CreateAdapter() \ �б���_��������������()
//@���� hEle Ԫ�ؾ��
//@���� pName �ֶ���
//@���� ����������
//@���� �б���_�������()
XC_API int WINAPI XListView_Item_AddColumn(HELE hEle, const wchar_t* pName);
//@��ע �״��������ʱ, ��Ҫ�ȴ������������� XListView_CreateAdapter() \ �б���_��������������()
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� pValue ֵ
//@���� iPos ����λ��, -1��ӵ�ĩβ
//@���� ����������
//@���� �б���_������ı�()
XC_API int WINAPI XListView_Item_AddItemText(HELE hEle, int iGroup, const wchar_t* pValue, int iPos);
//@��ע �״��������ʱ, ��Ҫ�ȴ������������� XListView_CreateAdapter() \ �б���_��������������()
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� pName �ֶ���
//@���� pValue ֵ
//@���� iPos ����λ��, -1��ӵ�ĩβ
//@���� ����������
//@���� �б���_������ı���չ()
XC_API int WINAPI XListView_Item_AddItemTextEx(HELE hEle, int iGroup, const wchar_t* pName, const wchar_t* pValue, int iPos);
//@��ע �״��������ʱ, ��Ҫ�ȴ������������� XListView_CreateAdapter() \ �б���_��������������()
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� hImage ͼƬ���
//@���� iPos ����λ��, -1��ӵ�ĩβ
//@���� ����������
//@���� �б���_�����ͼƬ()
XC_API int WINAPI XListView_Item_AddItemImage(HELE hEle, int iGroup, HIMAGE hImage, int iPos);
//@��ע �״��������ʱ, ��Ҫ�ȴ������������� XListView_CreateAdapter() \ �б���_��������������()
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� pName �ֶ���
//@���� hImage ͼƬ���
//@���� iPos ����λ��, -1��ӵ�ĩβ
//@���� ����������
//@���� �б���_�����ͼƬ��չ()
XC_API int WINAPI XListView_Item_AddItemImageEx(HELE hEle, int iGroup, const wchar_t* pName, HIMAGE hImage, int iPos);
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� iItem ������
//@���� iColumn ������
//@���� pValue ֵ
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б���_�����ı�()
XC_API BOOL WINAPI XListView_Item_SetText(HELE hEle, int iGroup, int iItem, int iColumn, const wchar_t* pValue);
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� iItem ������
//@���� pName �ֶ���
//@���� pValue ֵ
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б���_�����ı���չ()
XC_API BOOL WINAPI XListView_Item_SetTextEx(HELE hEle, int iGroup, int iItem, const wchar_t* pName, const wchar_t* pValue);
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� iItem ������
//@���� iColumn ������
//@���� hImage ͼƬ���
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б���_����ͼƬ()
XC_API BOOL WINAPI XListView_Item_SetImage(HELE hEle, int iGroup, int iItem, int iColumn, HIMAGE hImage);
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� iItem ������
//@���� pName ������
//@���� hImage ͼƬ���
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б���_����ͼƬ��չ()
XC_API BOOL WINAPI XListView_Item_SetImageEx(HELE hEle, int iGroup, int iItem, const wchar_t* pName, HIMAGE hImage);
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б���_��ɾ����()
XC_API BOOL WINAPI XListView_Group_DeleteItem(HELE hEle, int iGroup);
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� �б���_��ɾ��ȫ������()
XC_API void WINAPI XListView_Group_DeleteAllChildItem(HELE hEle, int iGroup);
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� iItem ������
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б���_��ɾ��()
XC_API BOOL WINAPI XListView_Item_DeleteItem(HELE hEle, int iGroup, int iItem);
//@���� hEle Ԫ�ؾ��
//@���� �б���_ɾ��ȫ��()
XC_API void WINAPI XListView_DeleteAll(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� �б���_ɾ��ȫ����()
XC_API void WINAPI XListView_DeleteAllGroup(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� �б���_ɾ��ȫ����()
XC_API void WINAPI XListView_DeleteAllItem(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� iColumn ������
//@���� �б���_��ɾ����()
XC_API void WINAPI XListView_DeleteColumnGroup(HELE hEle, int iColumn);
//@���� hEle Ԫ�ؾ��
//@���� iColumn ������
//@���� �б���_��ɾ����()
XC_API void WINAPI XListView_DeleteColumnItem(HELE hEle, int iColumn);
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� iItem ������
//@���� iColumn ������
//@���� �����ı�����
//@���� �б���_��ȡ�ı�()
XC_API const wchar_t* WINAPI XListView_Item_GetText(HELE hEle, int iGroup, int iItem, int iColumn);
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� iItem ������
//@���� pName �ֶγ�
//@���� �����ı�����
//@���� �б���_��ȡ�ı���չ()
XC_API const wchar_t* WINAPI XListView_Item_GetTextEx(HELE hEle, int iGroup, int iItem, const wchar_t* pName);
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� iItem ������
//@���� iColumn ������
//@���� ����ͼƬ���
//@���� �б���_��ȡͼƬ()
XC_API HIMAGE WINAPI XListView_Item_GetImage(HELE hEle, int iGroup, int iItem, int iColumn);
//@���� hEle Ԫ�ؾ��
//@���� iGroup ������
//@���� iItem ������
//@���� pName �ֶγ�
//@���� ����ͼƬ���
//@���� �б���_��ȡͼƬ��չ()
XC_API HIMAGE WINAPI XListView_Item_GetImageEx(HELE hEle, int iGroup, int iItem, const wchar_t* pName);
//@����}
//@����{  �˵���

//@��ע �����˵���Ԫ��;���ָ���˸�Ϊ����,Ĭ�ϵ���XWnd_AddMenuBar()����,���˵�����ӵ����ڷǿͻ���.
//�����Ԫ����Դ���������ӵ�Ԫ��.
//@���� x Ԫ��x����.
//@���� y Ԫ��y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ���Ǵ�����Դ�����U IԪ����Դ���.����Ǵ�����Դ���������ӵ�����,
//@���� Ԫ�ؾ��.
//@���� �˵���_����()
XC_API HELE WINAPI XMenuBar_Create(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@��ע ��ӵ����˵���ť.
//@���� hEle Ԫ�ؾ��.
//@���� pText �ı�����.
//@���� ���ز˵���ť����.
//@���� �˵���_��Ӱ�ť()
XC_API int WINAPI XMenuBar_AddButton(HELE hEle, const wchar_t* pText);
//@��ע ���ò˵���ť�߶�.(�ѷ���)��ʹ����������Ƹ߶�
//@���� hEle Ԫ�ؾ��.
//@���� height �߶�.
//@���� �˵���_�ð�ť�߶�()
XC_API void WINAPI XMenuBar_SetButtonHeight(HELE hEle, int height);
//@��ע ��ȡ�˵�.
//@���� hEle Ԫ�ؾ��.
//@���� nIndex �˵����ϲ˵���ť������.
//@���� ���ز˵����.
//@���� �˵���_ȡ�˵�()
XC_API HMENUX WINAPI XMenuBar_GetMenu(HELE hEle, int nIndex);
//@���� hEle Ԫ�ؾ��
//@���� nIndex �˵����ϲ˵���ť������
//@���� ���ذ�ť���
//@���� �˵���_ȡ�˵���ť()
XC_API HELE WINAPI XMenuBar_GetButton(HELE hEle, int nIndex);
//@��ע �˵�����ǰѡ����, ��ǰ�����˵��İ�ť����
//@���� hEle Ԫ�ؾ��
//@���� ����ѡ��������
//@���� �˵���_ȡѡ����()
XC_API int WINAPI XMenuBar_GetSelect(HELE hEle);
//@��ע ɾ���˵����ϵĲ˵���ť,ͬʱ�ð�ť�µĵ����˵�Ҳ������.
//@���� hEle Ԫ�ؾ��.
//@���� nIndex �˵�����ť����.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �˵���_ɾ����ť()
XC_API BOOL WINAPI XMenuBar_DeleteButton(HELE hEle, int nIndex);
//@��ע ���������Զ��������
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� �˵���_�����Զ����()
XC_API void WINAPI XMenuBar_EnableAutoWidth(HELE hEle, BOOL bEnable);
//@����}
//@����{  ����

//@��ע ��������Ԫ��.
//@���� pName �������.
//@���� nWidth ���.
//@���� nHeight �߶�.
//@���� hFrameWnd ��ܴ���.
//@���� Ԫ�ؾ��.
//@���� ����_����()
XC_API HELE WINAPI XPane_Create(const wchar_t* pName, int nWidth, int nHeight, HWINDOW hFrameWnd=NULL);
//@��ע ���ô�����ͼԪ��.
//@���� hEle Ԫ�ؾ��.
//@���� hView ����ͼԪ��.
//@���� ����_����ͼ()
XC_API void WINAPI XPane_SetView(HELE hEle, HELE hView);
//@��ע ���ñ����ı�.
//@���� hEle Ԫ�ؾ��.
//@���� pTitle �ı�����.
//@���� ����_�ñ���()
XC_API void WINAPI XPane_SetTitle(HELE hEle, wchar_t* pTitle);
//@��ע ��ȡ�����ı�.
//@���� hEle Ԫ�ؾ��.
//@���� �����ı�����
//@���� ����_ȡ����()
XC_API const wchar_t* WINAPI XPane_GetTitle(HELE hEle);
//@��ע ���ñ������߶�.
//@���� hEle Ԫ�ؾ��.
//@���� nHeight �߶�.
//@���� ����_�ñ������߶�()
XC_API void WINAPI XPane_SetCaptionHeight(HELE hEle, int nHeight);
//@��ע ��ȡ�������߶�.
//@���� hEle Ԫ�ؾ��.
//@���� ���ر������߶�.
//@���� ����_ȡ�������߶�()
XC_API int WINAPI XPane_GetCaptionHeight(HELE hEle);
//@��ע �жϴ����Ƿ���ʾ.
//@���� hEle Ԫ�ؾ��.
//@���� �����ʾ����TRUE���򷵻�FALSE.
//@���� ����_�Ƿ���ʾ()
XC_API BOOL WINAPI XPane_IsShowPane(HELE hEle);
//@��ע �жϴ����Ƿ񼤻�, ��Ϊ���Աʱ��Ч
//@���� hEle Ԫ�ؾ��
//@���� ���Ϊ��������ʾ���TRUE,���򷵻�FALSE
//@���� ����_�Ƿ񼤻�()
XC_API BOOL WINAPI XPane_IsGroupActivate(HELE hEle);
//@��ע ���ô����С.
//@���� hEle Ԫ�ؾ��.
//@���� nWidth ���.
//@���� nHeight �߶�.
//@���� ����_�ô�С()
XC_API void WINAPI XPane_SetSize(HELE hEle, int nWidth, int nHeight);
//@��ע ��ȡ����ͣ��״̬.
//@���� hEle Ԫ�ؾ��.
//@���� ����״̬��ʶ @ref pane_state_.
//@���� ����_ȡ״̬()
XC_API pane_state_ WINAPI XPane_GetState(HELE hEle);
//@��ע ��ȡ������ͼ����.
//@���� hEle Ԫ�ؾ��.
//@���� pRect ���շ��ص�����ֵ.
//@���� ����_ȡ��ͼ����()
XC_API void WINAPI XPane_GetViewRect(HELE hEle, RECT* pRect);
//@��ע ���ش���.
//@���� hEle Ԫ�ؾ��.
//@���� bGroupDelay ��Ϊ�������Աʱ, �ӳٴ��������Ա������л�
//@���� ����_����()
XC_API void WINAPI XPane_HidePane(HELE hEle, BOOL bGroupDelay=FALSE);
//@��ע ��ʾ����.
//@���� hEle Ԫ�ؾ��.
//@���� bGroupActivate ����Ǵ������Ա,��ô�������л���ǰ����Ϊ��ʾ״̬
//@���� ����_��ʾ()
XC_API void WINAPI XPane_ShowPane(HELE hEle, BOOL bGroupActivate);
//@��ע ����ͣ������ͷ.
//@���� hEle Ԫ�ؾ��.
//@���� ����_ͣ��()
XC_API void WINAPI XPane_DockPane(HELE hEle);
//@��ע ��������.
//@���� hEle Ԫ�ؾ��.
//@���� ����_����()
XC_API void WINAPI XPane_LockPane(HELE hEle);
//@��ע ��������.
//@���� hEle Ԫ�ؾ��.
//@���� ����_����()
XC_API void WINAPI XPane_FloatPane(HELE hEle);
//@��ע �ֶ����øú������ƴ���, �Ա���ƻ���˳��.
//@���� hEle Ԫ�ؾ��.
//@���� hDraw ͼ�λ��ƾ��.
//@���� ����_����()
XC_API void WINAPI XPane_DrawPane(HELE hEle, HDRAW hDraw);
//@��ע ������������Ա,����ѡ�е�ǰ����ɼ�
//@���� hEle Ԫ�ؾ��
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ����_��ѡ��()
XC_API BOOL WINAPI XPane_SetSelect(HELE hEle);
//@����}
//@����{  ������

//@��ע ����������Ԫ��.
//�����Ԫ����Դ���������ӵ�Ԫ��.
//@���� x Ԫ��x����.
//@���� y Ԫ��y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ���Ǵ�����Դ�����UIԪ����Դ���.����Ǵ�����Դ���������ӵ�����,
//@���� Ԫ�ؾ��.
//@���� ������_����()
XC_API HELE WINAPI XProgBar_Create(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@��ע ��ʾ����ֵ�ı�
//@���� hEle Ԫ���
//@���� bShow �Ƿ�����
//@���� ������_���ý����ı�()
XC_API void WINAPI XProgBar_EnableShowText(HELE hEle, BOOL bShow);
//@��ע ���Ž�����ͼΪ��ǰ��������(��ǰ��������ʾ����),����Ϊ����100%��������
//@���� hEle Ԫ���
//@���� bStretch ����
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ������_��������()
XC_API void WINAPI XProgBar_EnableStretch(HELE hEle, BOOL bStretch);
//@��ע ���÷�Χ.
//@���� hEle Ԫ���.
//@���� range ��Χ.
//@���� ������_�÷�Χ()
XC_API void WINAPI XProgBar_SetRange(HELE hEle, int range);
//@��ע ��ȡ��Χ.
//@���� hEle Ԫ���.
//@���� ���ط�Χ.
//@���� ������_ȡ��Χ()
XC_API int WINAPI XProgBar_GetRange(HELE hEle);
//@��ע ���ý�����ͼ.
//@���� hEle Ԫ���.
//@���� hImage ͼƬ���.
//@���� ������_�ý���ͼƬ()
XC_API void WINAPI XProgBar_SetImageLoad(HELE hEle, HIMAGE hImage);
//@��ע ���ý�����ɫ
//@���� hEle Ԫ���
//@���� color ��ɫֵ @ref RGBA()
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ������_�ý�����ɫ()
XC_API void WINAPI XProgBar_SetColorLoad(HELE hEle, COLORREF color);
//@��ע ����λ�õ�.
//@���� hEle Ԫ���.
//@���� pos λ�õ�.
//@���� ������_�ý���()
XC_API void WINAPI XProgBar_SetPos(HELE hEle, int pos);
//@��ע ��ȡ��ǰλ�õ�.
//@���� hEle Ԫ���.
//@���� ���ص�ǰλ�õ�.
//@���� ������_ȡ����()
XC_API int WINAPI XProgBar_GetPos(HELE hEle);
//@��ע ����ˮƽ��ֱ.
//@���� hEle Ԫ���.
//@���� bHorizon ˮƽ��ֱ.
//@���� ������_����ˮƽ()
XC_API void WINAPI XProgBar_EnableHorizon(HELE hEle, BOOL bHorizon);
//@����}
//@����{  ������

//@��ע ����������Ԫ��.
//�����Ԫ����Դ���������ӵ�Ԫ��.
//@���� x Ԫ��x����.
//@���� y Ԫ��y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ���Ǵ�����Դ�����UIԪ����Դ���.����Ǵ�����Դ���������ӵ�����,
//@���� Ԫ�ؾ��.
//@���� ������_����()
XC_API HELE WINAPI XSBar_Create(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@��ע ���ù�����Χ.
//@���� hEle Ԫ�ؾ��.
//@���� range ��Χ.
//@���� ������_�÷�Χ()
XC_API void WINAPI XSBar_SetRange(HELE hEle, int range);
//@��ע ��ȡ������Χ.
//@���� hEle Ԫ�ؾ��.
//@���� ������Χ.
//@���� ������_ȡ��Χ()
XC_API int WINAPI XSBar_GetRange(HELE hEle);
//@��ע ��ʾ���ع��������°�ť.
//@���� hEle Ԫ�ؾ��.
//@���� bShow �Ƿ���ʾ.
//@���� ������_��ʾ���°�ť()
XC_API void WINAPI XSBar_ShowButton(HELE hEle, BOOL bShow);
//@��ע ���û��鳤��.
//@���� hEle Ԫ�ؾ��.
//@���� length ����.
//@���� ������_�û��鳤��()
XC_API void WINAPI XSBar_SetSliderLength(HELE hEle, int length);
//@��ע ���û�����С����.
//@���� hEle Ԫ�ؾ��.
//@���� minLength ����.
//@���� ������_�û�����С����()
XC_API void WINAPI XSBar_SetSliderMinLength(HELE hEle, int minLength);
//@��ע ���û������˵ļ����С.
//@���� hEle Ԫ�ؾ��.
//@���� nPadding �����С.
//@���� ������_�û������˼��()
XC_API void WINAPI XSBar_SetSliderPadding(HELE hEle, int nPadding);
//@��ע ����ˮƽ���ߴ�ֱ.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable ˮƽ��ֱ.
//@���� ����ı䷵��TRUE���򷵻�FALSE.
//@���� ������_����ˮƽ()
XC_API BOOL WINAPI XSBar_EnableHorizon(HELE hEle, BOOL bHorizon);
//@��ע ��ȡ������󳤶�.
//@���� hEle Ԫ�ؾ��.
//@���� ����.
//@���� ������_ȡ������󳤶�()
XC_API int WINAPI XSBar_GetSliderMaxLength(HELE hEle);
//@��ע ���Ϲ���.
//@���� hEle Ԫ�ؾ��.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������_���Ϲ���()
XC_API BOOL WINAPI XSBar_ScrollUp(HELE hEle);
//@��ע ���¹���.
//@���� hEle Ԫ�ؾ��.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������_���¹���()
XC_API BOOL WINAPI XSBar_ScrollDown(HELE hEle);
//@��ע ����������.
//@���� hEle Ԫ�ؾ��.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������_����������()
XC_API BOOL WINAPI XSBar_ScrollTop(HELE hEle);
//@��ע �������ײ�.
//@���� hEle Ԫ�ؾ��.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������_�������ײ�()
XC_API BOOL WINAPI XSBar_ScrollBottom(HELE hEle);
//@��ע ������ָ��λ�õ� ,�����¼� @ref XE_SBAR_SCROLL ,
//@���� hEle Ԫ�ؾ��.
//@���� pos λ�õ�.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������_������ָ��λ��()
XC_API BOOL WINAPI XSBar_ScrollPos(HELE hEle, int pos);
//@��ע ��ȡ�ϰ�ť.
//@���� hEle Ԫ�ؾ��.
//@���� ���ذ�ť���.
//@���� ������_ȡ�ϰ�ť()
XC_API HELE WINAPI XSBar_GetButtonUp(HELE hEle);
//@��ע ��ȡ�°�ť.
//@���� hEle Ԫ�ؾ��.
//@���� ���ذ�ť���.
//@���� ������_ȡ�°�ť()
XC_API HELE WINAPI XSBar_GetButtonDown(HELE hEle);
//@��ע ��ȡ������ť.
//@���� hEle Ԫ�ؾ��.
//@���� ���ذ�ť���.
//@���� ������_ȡ����()
XC_API HELE WINAPI XSBar_GetButtonSlider(HELE hEle);
//@����}
//@����{  ������

//@��ע ����������Ԫ��.
//�����Ԫ����Դ���������ӵ�Ԫ��.
//@���� x Ԫ��x����.
//@���� y Ԫ��y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ���Ǵ�����Դ�����UIԪ����Դ���.����Ǵ�����Դ���������ӵ�����,
//@���� Ԫ�ؾ��.
//@���� ������_����()
XC_API HELE WINAPI XSliderBar_Create(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@��ע ���û�����Χ.
//@���� hEle Ԫ�ؾ��.
//@���� range ��Χ.
//@���� ������_�÷�Χ()
XC_API void WINAPI XSliderBar_SetRange(HELE hEle, int range);
//@��ע ��ȡ������Χ.
//@���� hEle Ԫ�ؾ��.
//@���� ���ع�����Χ.
//@���� ������_ȡ��Χ()
XC_API int WINAPI XSliderBar_GetRange(HELE hEle);
//@��ע ���ý�����ͼ.
//@���� hEle Ԫ�ؾ��.
//@���� hImage ͼƬ���.
//@���� ������_�ý���ͼƬ()
XC_API void WINAPI XSliderBar_SetImageLoad(HELE hEle, HIMAGE hImage);
//@��ע ���û��鰴ť���.
//@���� hEle Ԫ�ؾ��.
//@���� width ���.
//@���� ������_�û�����()
XC_API void WINAPI XSliderBar_SetButtonWidth(HELE hEle, int width);
//@��ע ���û��鰴ť�߶�.
//@���� hEle Ԫ�ؾ��.
//@���� height �߶�.
//@���� ������_�û���߶�()
XC_API void WINAPI XSliderBar_SetButtonHeight(HELE hEle, int height);
//@��ע ���õ�ǰ���ȵ�.
//@���� hEle Ԫ�ؾ��.
//@���� pos ���ȵ�.
//@���� ������_�õ�ǰλ��()
XC_API void WINAPI XSliderBar_SetPos(HELE hEle, int pos);
//@��ע ��ȡ��ǰ���ȵ�.
//@���� hEle Ԫ�ؾ��.
//@���� ���ص�ǰ���ȵ�.
//@���� ������_ȡ��ǰλ��()
XC_API int WINAPI XSliderBar_GetPos(HELE hEle);
//@��ע ��ȡ���鰴ť.
//@���� hEle Ԫ�ؾ��.
//@���� ��ť���.
//@���� ������_ȡ����()
XC_API HELE WINAPI XSliderBar_GetButton(HELE hEle);
//@��ע ����ˮƽ��ֱ.
//@���� hEle Ԫ�ؾ��.
//@���� bHorizon ˮƽ��ֱ.
//@���� ������_����ˮƽ()
XC_API void WINAPI XSliderBar_EnableHorizon(HELE hEle, BOOL bHorizon);
//@����}
//@����{  Tab��

//@��ע ����tabBarԪ��.
//�����Ԫ����Դ���������ӵ�Ԫ��.
//@���� x Ԫ��x����.
//@���� y Ԫ��y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ���Ǵ�����Դ�����UIԪ����Դ���.����Ǵ�����Դ���������ӵ�����,
//@���� Ԫ�ؾ��.
//@���� TAB��_����()
XC_API HELE WINAPI XTabBar_Create(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@��ע ���һ����ǩ.
//@���� hEle Ԫ�ؾ��
//@���� pName ��ǩ�ı�����.
//@���� ��ǩ����.
//@���� TAB��_��ӱ�ǩ()
XC_API int WINAPI XTabBar_AddLabel(HELE hEle, const wchar_t* pName);
//@��ע ����һ����ǩ.
//@���� hEle Ԫ�ؾ��.
//@���� index ����λ��.
//@���� pName ��ǩ�ı�����.
//@���� ��ǩ����.
//@���� TAB��_�����ǩ()
XC_API int WINAPI XTabBar_InsertLabel(HELE hEle, int index, const wchar_t* pName);
//@���� hEle Ԫ�ؾ��.
//@���� iSrc Դλ������
//@���� iDest Ŀ��λ������
//@���� �ɹ�����TRUE����FALSE.
//@���� TAB��_�ƶ���ǩ()
XC_API BOOL WINAPI XTabBar_MoveLabel(HELE hEle, int iSrc, int iDest);
//@��ע ɾ��һ����ǩ.
//@���� hEle Ԫ�ؾ��.
//@���� index λ������.
//@���� �ɹ�����TRUE����FALSE.
//@���� TAB��_ɾ����ǩ()
XC_API BOOL WINAPI XTabBar_DeleteLabel(HELE hEle, int index);
//@��ע ɾ�����б�ǩ.
//@���� hEle Ԫ�ؾ��.
//@���� TAB��_ɾ��ȫ��()
XC_API void WINAPI XTabBar_DeleteLabelAll(HELE hEle);
//@��ע ��ȡ��ǩ��ťButton.
//@���� hEle Ԫ�ؾ��.
//@���� index λ������.
//@���� ��ť���.
//@���� TAB��_ȡ��ǩ()
XC_API HELE WINAPI XTabBar_GetLabel(HELE hEle, int index);
//@��ע ��ȡ��ǩ�Ϲرհ�ť.
//@���� hEle Ԫ�ؾ��.
//@���� index λ������.
//@���� ��ť���.
//@���� TAB��_ȡ��ǩ�ϵĹرհ�ť()
XC_API HELE WINAPI XTabBar_GetLabelClose(HELE hEle, int index);
//@��ע ��ȡ�������ť.
//@���� hEle Ԫ�ؾ��.
//@���� ���ذ�ť���.
//@���� TAB��_ȡ�������ť()
XC_API HELE WINAPI XTabBar_GetButtonLeft(HELE hEle);
//@��ע ��ȡ�ҹ�����ť.
//@���� hEle Ԫ�ؾ��.
//@���� ���ذ�ť���.
//@���� TAB��_ȡ�ҹ�����ť()
XC_API HELE WINAPI XTabBar_GetButtonRight(HELE hEle);
//@���� hEle Ԫ�ؾ��.
//@���� ���ذ�ť���.
//@���� TAB��_ȡ�����˵���ť���()
XC_API HELE WINAPI XTabBar_GetButtonDropMenu(HELE hEle);
//@��ע ��ȡѡ��ı�ǩ����.
//@���� hEle Ԫ�ؾ��.
//@���� ��ǩλ������.
//@���� TAB��_ȡ��ǰѡ��()
XC_API int WINAPI XTabBar_GetSelect(HELE hEle);
//@��ע ��ȡ��ǩ���, 0û�м��.
//@���� hEle Ԫ�ؾ��.
//@���� ��ǩ�����С.
//@���� TAB��_ȡ���()
XC_API int WINAPI XTabBar_GetLabelSpacing(HELE hEle);
//@��ע ��ȡ��ǩ������.
//@���� hEle Ԫ�ؾ��.
//@���� ��ǩ������.
//@���� TAB��_ȡ��ǩ����()
XC_API int WINAPI XTabBar_GetLabelCount(HELE hEle);
//@��ע ��ȡ��ǩ��ťλ������.
//@���� hEle Ԫ�ؾ��.
//@���� hLabel ��ǩ��ť���.
//@���� �ɹ���������ֵ,���򷵻� @ref XC_ID_ERROR.
//@���� TAB��_ȡ��ǩλ������()
XC_API int WINAPI XTabBar_GetindexByEle(HELE hEle, HELE hLabel);
//@��ע ���ñ�ǩ���, 0û�м��.
//@���� hEle Ԫ�ؾ��.
//@���� spacing ��ǩ�����С.
//@���� TAB��_�ü��()
XC_API void WINAPI XTabBar_SetLabelSpacing(HELE hEle, int spacing);
//@��ע ����ѡ���ǩ.
//@���� hEle Ԫ�ؾ��.
//@���� index ��ǩλ������.
//@���� TAB��_��ѡ��()
XC_API void WINAPI XTabBar_SetSelect(HELE hEle, int index);
//@��ע ��ť����.
//@���� hEle Ԫ�ؾ��.
//@���� TAB��_�����()
XC_API void WINAPI XTabBar_SetUp(HELE hEle);
//@��ע �Ұ�ť����.
//@���� hEle Ԫ�ؾ��.
//@���� TAB��_�ҹ���()
XC_API void WINAPI XTabBar_SetDown(HELE hEle);
//@��ע ƽ�̱�ǩ,ÿ����ǩ��ʾ��ͬ��С.
//@���� hEle Ԫ�ؾ��.
//@���� bTile �Ƿ�����.
//@���� TAB��_����ƽ��()
XC_API void WINAPI XTabBar_EnableTile(HELE hEle, BOOL bTile);
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� TAB��_���������˵���ť()
XC_API void WINAPI XTabBar_EnableDropMenu(HELE hEle, BOOL bEnable);
//@��ע ���ùرձ�ǩ����.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� TAB��_���ñ�ǩ���رհ�ť()
XC_API void WINAPI XTabBar_EnableClose(HELE hEle, BOOL bEnable);
//@��ע ���ùرհ�ť��С.
//@���� hEle Ԫ�ؾ��.
//@���� pSize ��Сֵ, ��Ⱥ͸߶ȿ���Ϊ-1,-1����Ĭ��ֵ.
//@���� TAB��_�ùرհ�ť��С()
XC_API void WINAPI XTabBar_SetCloseSize(HELE hEle, SIZE* pSize);
//@��ע ���÷�����ť��С.
//@���� hEle Ԫ�ؾ��.
//@���� pSize ��Сֵ, ��Ⱥ͸߶ȿ���Ϊ-1,-1����Ĭ��ֵ.
//@���� TAB��_�ù�����ť��С()
XC_API void WINAPI XTabBar_SetTurnButtonSize(HELE hEle, SIZE* pSize);
//@��ע ����ָ����ǩΪ�̶����.
//@���� hEle Ԫ�ؾ��.
//@���� index ����.
//@���� nWidth ���, ���ֵΪ-1,��ô�Զ�������.
//@���� TAB��_��ָ����ǩ�̶����()
XC_API void WINAPI XTabBar_SetLabelWidth(HELE hEle, int index, int nWidth);
//@��ע ��ʾ������ָ����ǩ.
//@���� hEle Ԫ�ؾ��.
//@���� index ��ǩ����.
//@���� bShow �Ƿ���ʾ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� TAB��_��ʾ��ǩ()
XC_API BOOL WINAPI XTabBar_ShowLabel(HELE hEle, int index, BOOL bShow);
//@����}
//@����{  �ı�����

//@��ע ������̬�ı�����Ԫ��.
//�����Ԫ����Դ���������ӵ�Ԫ��.
//@���� x Ԫ��x����.
//@���� y Ԫ��y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� pName �ı�����.
//@���� hParent ���Ǵ�����Դ�����UIԪ����Դ���.����Ǵ�����Դ���������ӵ�����,
//@���� Ԫ�ؾ��.
//@���� �ı�����_����()
XC_API HELE WINAPI XTextLink_Create(int x, int y, int cx, int cy, const wchar_t* pName, HXCGUI hParent=NULL);
//@��ע �����»���,����뿪״̬.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� �ı�����_�����뿪״̬�»���()
XC_API void WINAPI XTextLink_EnableUnderlineLeave(HELE hEle, BOOL bEnable);
//@��ע �����»���,���ͣ��״̬.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� �ı�����_ͣ��״̬�»���()
XC_API void WINAPI XTextLink_EnableUnderlineStay(HELE hEle, BOOL bEnable);
//@��ע �����ı���ɫ,���ͣ��״̬.
//@���� hEle Ԫ�ؾ��.
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� �ı�����_��ͣ��״̬�ı���ɫ()
XC_API void WINAPI XTextLink_SetTextColorStay(HELE hEle, COLORREF color);
//@��ע �����»�����ɫ,����뿪״̬.
//@���� hEle Ԫ�ؾ��.
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� �ı�����_���뿪״̬�»�����ɫ()
XC_API void WINAPI XTextLink_SetUnderlineColorLeave(HELE hEle, COLORREF color);
//@��ע �����»�����ɫ,���ͣ��״̬.
//@���� hEle Ԫ�ؾ��.
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� �ı�����_��ͣ��״̬�»�����ɫ()
XC_API void WINAPI XTextLink_SetUnderlineColorStay(HELE hEle, COLORREF color);
//@����}
//@����{  ������

//@��ע ����������Ԫ��;���ָ���˸�Ϊ����,Ĭ�ϵ���XWnd_AddToolBar()����,����������ӵ����ڷǿͻ���.
//�����Ԫ����Դ���������ӵ�Ԫ��.
//@���� x Ԫ��x����.
//@���� y Ԫ��y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ���Ǵ�����Դ�����UIԪ����Դ���.����Ǵ�����Դ���������ӵ�����,
//@���� Ԫ�ؾ��.
//@���� ������_����()
XC_API HELE WINAPI XToolBar_Create(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@��ע ����Ԫ�ص�������.
//@���� hEle Ԫ�ؾ��.
//@���� hNewEle ��Ҫ�����Ԫ��.
//@���� index ����λ������, (-1)����ĩβ..
//@���� ���ز���λ������.
//@���� ������_����Ԫ��()
XC_API int WINAPI XToolBar_InsertEle(HELE hEle, HELE hNewEle, int index=-1);
//@��ע ����ָ�����������.
//@���� hEle Ԫ�ؾ��.
//@���� index ����λ������, (-1)����ĩβ.
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� ���ز���λ������.
//@���� ������_����ָ���()
XC_API int WINAPI XToolBar_InsertSeparator(HELE hEle, int index=-1, COLORREF color=RGBA(128,128,128,255));
//@��ע ���������˵�,��ʾ���ص���.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� ������_���������˵�()
XC_API void WINAPI XToolBar_EnableButtonMenu(HELE hEle, BOOL bEnable);
//@��ע ��ȡ��������ָ��Ԫ��.
//@���� hEle Ԫ�ؾ��.
//@���� index ����ֵ.
//@���� ����Ԫ�ؾ��.
//@���� ������_ȡԪ��()
XC_API HELE WINAPI XToolBar_GetEle(HELE hEle, int index);
//@��ע ��ȡ�������ť.
//@���� hEle Ԫ�ؾ��.
//@���� ���ذ�ť���.
//@���� ������_ȡ�������ť()
XC_API HELE WINAPI XToolBar_GetButtonLeft(HELE hEle);
//@��ע ��ȡ�ҹ�����ť.
//@���� hEle Ԫ�ؾ��.
//@���� ���ذ�ť���.
//@���� ������_ȡ�ҹ�����ť()
XC_API HELE WINAPI XToolBar_GetButtonRight(HELE hEle);
//@��ע ��ȡ�˵���ť.
//@���� hEle Ԫ�ؾ��.
//@���� ���ز˵���ť���.
//@���� ������_ȡ�˵���ť()
XC_API HELE WINAPI XToolBar_GetButtonMenu(HELE hEle);
//@��ע ���ö���֮��ļ��.
//@���� hEle Ԫ�ؾ��.
//@���� nSize ����С.
//@���� ������_�ü��()
XC_API void WINAPI XToolBar_SetSpace(HELE hEle, int nSize);
//@��ע ɾ��Ԫ��,��������.
//@���� hEle Ԫ�ؾ��.
//@���� index ����ֵ.
//@���� ������_ɾ��Ԫ��()
XC_API void WINAPI XToolBar_DeleteEle(HELE hEle, int index);
//@��ע ɾ������Ԫ��,��������.
//@���� hEle Ԫ�ؾ��.
//@���� ������_ɾ��ȫ��()
XC_API void WINAPI XToolBar_DeleteAllEle(HELE hEle);
//@����}
//@����{  �б���

//@��ע ������Ԫ��.
//�����Ԫ����Դ���������ӵ�Ԫ��.
//@���� x Ԫ��x����.
//@���� y Ԫ��y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ���Ǵ�����Դ�����UIԪ����Դ���.����Ǵ�����Դ���������ӵ�����,
//@���� Ԫ�ؾ��.
//@���� �б���_����()
XC_API HELE WINAPI XTree_Create(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@��ע ������Ԫ��. ʹ��������ģ��, �Զ���������������
//�����Ԫ����Դ���������ӵ�Ԫ��.
//@���� x Ԫ��x����.
//@���� y Ԫ��y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ���Ǵ�����Դ�����UIԪ����Դ���.����Ǵ�����Դ���������ӵ�����,
//@���� Ԫ�ؾ��.
//@���� �б���_������չ()
XC_API HELE WINAPI XTree_CreateEx(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@��ע �����϶����.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� �б���_�����϶���()
XC_API void WINAPI XTree_EnableDragItem(HELE hEle, BOOL bEnable);
//@��ע ���û������ʾ���������.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� bSolid ʵ�߻�����; TRUE:ʵ��, FALSE:����.
//@���� �б���_����������()
XC_API void WINAPI XTree_EnableConnectLine(HELE hEle, BOOL bEnable, BOOL bSolid);
//@��ע ������ر�Ĭ��չ������,��������²������Զ�չ��.
//@���� hEle Ԫ�ؾ��.
//@���� bEnable �Ƿ�����.
//@���� �б���_����չ��()
XC_API void WINAPI XTree_EnableExpand(HELE hEle, BOOL bEnable);
//@���� hEle Ԫ�ؾ��
//@���� bEnable �Ƿ�����
//@���� �б���_����ģ�帴��()
XC_API void WINAPI XTree_EnableTemplateReuse(HELE hEle, BOOL bEnable);
//@��ע ��������������ɫ.
//@���� hEle Ԫ�ؾ��.
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� �б���_����������ɫ()
XC_API void WINAPI XTree_SetConnectLineColor(HELE hEle, COLORREF color);
//@��ע ����չ����ťռ�ÿռ��С.
//@���� hEle Ԫ�ؾ��.
//@���� nWidth ���.
//@���� nHeight �߶�.
//@���� �б���_��չ����ť��С()
XC_API void WINAPI XTree_SetExpandButtonSize(HELE hEle, int nWidth, int nHeight);
//@��ע �������߻��Ƴ���,չ����ť��������֮�������.
//@���� hEle Ԫ�ؾ��.
//@���� nLength ���߻��Ƴ���.
//@���� �б���_�������߳���()
XC_API void WINAPI XTree_SetConnectLineLength(HELE hEle, int nLength);
//@��ע �����϶������λ����ɫ��ʾ.
//@���� hEle Ԫ�ؾ��.
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� �б���_���϶������λ����ɫ()
XC_API void WINAPI XTree_SetDragInsertPositionColor(HELE hEle, COLORREF color);
//@��ע ������ģ���ļ�.
//@���� hEle Ԫ�ؾ��.
//@���� pXmlFile �ļ���.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б���_����ģ���ļ�()
XC_API BOOL WINAPI XTree_SetItemTemplateXML(HELE hEle, const wchar_t* pXmlFile);
//@��ע ������ģ���ļ�,��ѡ��״̬.
//@���� hEle Ԫ�ؾ��.
//@���� pXmlFile �ļ���.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б���_��ѡ����ģ���ļ�()
XC_API BOOL WINAPI XTree_SetItemTemplateXMLSel(HELE hEle, const wchar_t* pXmlFile);
//@��ע �����б���ģ��.
//@���� hEle Ԫ�ؾ��.
//@���� hTemp ģ����.
//@���� ����ɹ�����TRUE,���򷵻�FALSE.
//@���� �б���_����ģ��()
XC_API BOOL WINAPI XTree_SetItemTemplate(HELE hEle, HTEMP hTemp);
//@��ע �����б���ģ��,��ѡ��״̬.
//@���� hEle Ԫ�ؾ��.
//@���� hTemp ģ����.
//@���� ����ɹ�����TRUE,���򷵻�FALSE.
//@���� �б���_��ѡ����ģ��()
XC_API BOOL WINAPI XTree_SetItemTemplateSel(HELE hEle, HTEMP hTemp);
//@��ע ������ģ���ļ�.
//@���� hEle Ԫ�ؾ��.
//@���� pStringXML �ַ���ָ��.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б���_����ģ����ַ���()
XC_API BOOL WINAPI XTree_SetItemTemplateXMLFromString(HELE hEle, const char* pStringXML);
//@��ע ������ģ���ļ�,��ѡ��״̬.
//@���� hEle Ԫ�ؾ��.
//@���� pStringXML �ַ���ָ��.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б���_��ѡ����ģ����ַ���()
XC_API BOOL WINAPI XTree_SetItemTemplateXMLSelFromString(HELE hEle, const char* pStringXML);
//@���� hEle Ԫ�ؾ��
//@���� data �ڴ��ַ
//@���� length �ڴ��С, �ֽ�Ϊ��λ
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� �б���_����ģ����ڴ�()
XC_API BOOL WINAPI XTree_SetItemTemplateXMLFromMem(HELE hEle, void* data, int length);
//@��ע RC��Դ���ͱ���Ϊ:"RT_RCDATA"
//@���� hEle Ԫ�ؾ��
//@���� id RC��ԴID
//@���� pFileName �ļ���
//@���� pPassword zip����
//@���� hModule ģ����
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� �б���_����ģ�����ԴZIP()
XC_API BOOL WINAPI XTree_SetItemTemplateXMLFromZipRes(HELE hEle, int id, const wchar_t* pFileName, const wchar_t* pPassword=NULL, HMODULE hModule=NULL);
//@���� hEle Ԫ�ؾ��
//@���� ������ģ����
//@���� �б���_ȡ��ģ��()
XC_API HTEMP WINAPI XTree_GetItemTemplate(HELE hEle);
//@��ע �����Ƿ����ָ��״̬����ı���.
//@���� hEle Ԫ�ؾ��.
//@���� nFlags ��־λ @ref list_drawItemBk_flag_.
//@���� �б���_��������Ʊ�־()
XC_API void WINAPI XTree_SetDrawItemBkFlags(HELE hEle, int nFlags);
//@���� hEle Ԫ�ؾ��
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� �б���_�÷ָ�����ɫ()
XC_API void WINAPI XTree_SetSplitLineColor(HELE hEle, COLORREF color);
//@��ע �������û�����.
//@���� hEle Ԫ�ؾ��.
//@���� nID ��ID.
//@���� nUserData �û�����.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б���_��������()
XC_API BOOL WINAPI XTree_SetItemData(HELE hEle, int nID, vint nUserData);
//@��ע ��ȡ���û�����.
//@���� hEle Ԫ�ؾ��.
//@���� nID ��ID.
//@���� ���û�����.
//@���� �б���_ȡ������()
XC_API vint WINAPI XTree_GetItemData(HELE hEle, int nID);
//@��ע ����ѡ����.
//@���� hEle Ԫ�ؾ��.
//@���� nID ��ID.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б���_��ѡ����()
XC_API BOOL WINAPI XTree_SetSelectItem(HELE hEle, int nID);
//@��ע ��ȡѡ����.
//@���� hEle Ԫ�ؾ��.
//@���� ��ID.
//@���� �б���_ȡѡ����()
XC_API int WINAPI XTree_GetSelectItem(HELE hEle);
//@��ע ������ͼ��ָ����ɼ�
//@���� hEle Ԫ�ؾ��.
//@���� nID ������.
//@���� �б���_����ָ����()
XC_API void WINAPI XTree_VisibleItem(HELE hEle, int nID);
//@��ע �ж����Ƿ�չ��.
//@���� hEle Ԫ�ؾ��.
//@���� nID ��ID.
//@���� ���չ������TRUE���򷵻�FALSE.
//@���� �б���_�Ƿ�չ��()
XC_API BOOL WINAPI XTree_IsExpand(HELE hEle, int nID);
//@��ע չ����.
//@���� hEle Ԫ�ؾ��.
//@���� nID ��ID.
//@���� bExpand �Ƿ�չ��.
//@���� �ɹ�����TRUE,������Ѿ�չ����ʧ�ܷ���FALSE.
//@���� �б���_չ����()
XC_API BOOL WINAPI XTree_ExpandItem(HELE hEle, int nID, BOOL bExpand);
//@��ע չ�����е�����.
//@���� hEle Ԫ�ؾ��.
//@���� nID ��ID.
//@���� bExpand �Ƿ�չ��.
//@���� �ɹ�����TRUE,ʧ�ܷ���FALSE.
//@���� �б���_չ��ȫ������()
XC_API BOOL WINAPI XTree_ExpandAllChildItem(HELE hEle, int nID, BOOL bExpand);
//@��ע ��������������.
//@���� hEle Ԫ�ؾ��.
//@���� pPt �����.
//@���� ��ID.
//@���� �б���_���Ե����()
XC_API int WINAPI XTree_HitTest(HELE hEle, POINT* pPt);
//@��ע ��������������,�Զ���ӹ�����ͼƫ������.
//@���� hEle Ԫ�ؾ��.
//@���� pPt �����.
//@���� ��ID.
//@���� �б���_���Ե������չ()
XC_API int WINAPI XTree_HitTestOffset(HELE hEle, POINT* pPt);
//@��ע ��ȡ��һ������.
//@���� hEle Ԫ�ؾ��.
//@���� nID ��ID.
//@���� ������ID,ʧ�ܷ���XC_ID_ERROR.
//@���� �б���_ȡ��һ������()
XC_API int WINAPI XTree_GetFirstChildItem(HELE hEle, int nID);
//@��ע ��ȡĩβ����.
//@���� hEle Ԫ�ؾ��.
//@���� nID ��ID.
//@���� ����ĩβ����ID,ʧ�ܷ���XC_ID_ERROR.
//@���� �б���_ȡĩβ����()
XC_API int WINAPI XTree_GetEndChildItem(HELE hEle, int nID);
//@��ע ��ȡ��һ���ֵ���.
//@���� hEle Ԫ�ؾ��.
//@���� nID ��ID.
//@���� ������һ���ֵ���ID, ʧ�ܷ��� XC_ID_ERROR.
//@���� �б���_ȡ��һ���ֵ���()
XC_API int WINAPI XTree_GetPrevSiblingItem(HELE hEle, int nID);
//@��ע ��ȡ��һ���ֵ���.
//@���� hEle Ԫ�ؾ��.
//@���� nID ��ID.
//@���� ������һ���ֵ���ID.
//@���� �б���_ȡ��һ���ֵ���()
XC_API int WINAPI XTree_GetNextSiblingItem(HELE hEle, int nID);
//@��ע ��ȡ����.
//@���� hEle Ԫ�ؾ��.
//@���� nID ��ID.
//@���� ���ظ���ID,���󷵻�-1.
//@���� �б���_ȡ����()
XC_API int WINAPI XTree_GetParentItem(HELE hEle, int nID);
//@��ע �������������������ݰ󶨵���ģ���ʼ����������������(�ֶ���);
//�����������洢����, UI������ݰ󶨵��ֶ�����ʾ�����������ж�Ӧ������;
//@���� hEle Ԫ�ؾ��.
//@���� �������������.
//@���� �б���_��������������()
XC_API HXCGUI WINAPI XTree_CreateAdapter(HELE hEle);
//@��ע ������������.
//@���� hEle Ԫ�ؾ��.
//@���� hAdapter �������������, XAdTree.
//@���� �б���_������������()
XC_API void WINAPI XTree_BindAdapter(HELE hEle, HXCGUI hAdapter);
//@��ע ��ȡ����������.
//@���� hEle Ԫ�ؾ��.
//@���� �����������������.
//@���� �б���_ȡ������Ƶ��()
XC_API HXCGUI WINAPI XTree_GetAdapter(HELE hEle);
//@��ע �޸����ݺ�,ˢ��������ģ��,�Ա�������ݵ�ģ��(�����ɼ�).
//@���� hEle Ԫ�ؾ��.
//@���� �б���_ˢ������()
XC_API void WINAPI XTree_RefreshData(HELE hEle);
//@��ע �޸����ݺ�,ˢ��ָ����ģ��,�Ա�������ݵ�ģ��(�����ǰ��ɼ�).
//@���� hEle Ԫ�ؾ��.
//@���� nID ��ID.
//@���� �б���_ˢ��ָ����()
XC_API void WINAPI XTree_RefreshItem(HELE hEle, int nID);
//@��ע ����������С.
//@���� hEle Ԫ�ؾ��.
//@���� nWidth �������.
//@���� �б���_������()
XC_API void WINAPI XTree_SetIndentation(HELE hEle, int nWidth);
//@��ע ��ȡ����ֵ.
//@���� hEle Ԫ�ؾ��.
//@���� ��������ֵ��С.
//@���� �б���_ȡ����()
XC_API int WINAPI XTree_GetIndentation(HELE hEle);
//@��ע ������Ĭ�ϸ߶�.
//@���� hEle Ԫ�ؾ��.
//@���� nHeight �߶�.
//@���� nSelHeight ѡ��ʱ�߶�.
//@���� �б���_����Ĭ�ϸ߶�()
XC_API void WINAPI XTree_SetItemHeightDefault(HELE hEle, int nHeight, int nSelHeight);
//@��ע ��ȡ��Ĭ�ϸ߶�.
//@���� hEle Ԫ�ؾ��.
//@���� pHeight ���շ��ظ߶�.
//@���� pSelHeight ���շ���ֵ,����ѡ��ʱ�ĸ߶�.
//@���� �б���_ȡ��Ĭ�ϸ߶�()
XC_API void WINAPI XTree_GetItemHeightDefault(HELE hEle, int* pHeight, int* pSelHeight);
//@��ע ����ָ����߶�.
//@���� hEle Ԫ�ؾ��.
//@���� nID ��ID.
//@���� nHeight �߶�.
//@���� nSelHeight ѡ��ʱ�߶�.
//@���� �б���_����߶�()
XC_API void WINAPI XTree_SetItemHeight(HELE hEle, int nID, int nHeight, int nSelHeight);
//@��ע ��ȡָ����߶�.
//@���� hEle Ԫ�ؾ��.
//@���� nID ��ID.
//@���� pHeight ���շ��ظ߶�.
//@���� pSelHeight ���շ���ֵ,����ѡ��ʱ�ĸ߶�.
//@���� �б���_ȡ��߶�()
XC_API void WINAPI XTree_GetItemHeight(HELE hEle, int nID, int* pHeight, int* pSelHeight);
//@��ע �����м����С.
//@���� hEle Ԫ�ؾ��.
//@���� nSpace �м����С.
//@���� �б���_���м��()
XC_API void WINAPI XTree_SetRowSpace(HELE hEle, int nSpace);
//@��ע ��ȡ�м���С.
//@���� hEle Ԫ�ؾ��.
//@���� �����м���С.
//@���� �б���_ȡ�м��()
XC_API int WINAPI XTree_GetRowSpace(HELE hEle);
//@��ע �ƶ����λ��.
//@���� hEle Ԫ�ؾ��.
//@���� nMoveItem Ҫ�ƶ�����ID.
//@���� nDestItem Ŀ����ID, ����λ��.
//@���� nFlag 0:Ŀ��ǰ��, 1:Ŀ�����, 2:Ŀ��������, 3:Ŀ������β
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �б���_�ƶ���()
XC_API BOOL WINAPI XTree_MoveItem(HELE hEle, int nMoveItem, int nDestItem, int nFlag);
//@��ע ͨ��ģ����ID,��ȡʵ����ģ����ID��Ӧ�Ķ�����.
//@���� hEle Ԫ�ؾ��.
//@���� nID ����ID.
//@���� nTempItemID ģ����ID.
//@���� �ɹ����ض�����,���򷵻�NULL.
//@���� �б���_ȡģ�����()
XC_API HXCGUI WINAPI XTree_GetTemplateObject(HELE hEle, int nID, int nTempItemID);
//@��ע ��ȡ��ǰ��������ģ��ʵ��,�����б�������һ����.
//@���� hEle Ԫ�ؾ��.
//@���� hXCGUI ������, UIԪ�ؾ������״������..
//@���� �ɹ�������ID, ���򷵻�@ref XC_ID_ERROR.
//@���� �б���_ȡ����������()
XC_API int WINAPI XTree_GetItemIDFromHXCGUI(HELE hEle, HXCGUI hXCGUI);
//@��ע �״��������ʱ, ��Ҫ�ȴ������������� XTree_CreateAdapter() \ �б���_��������������()
//@���� hEle
//@���� pValue
//@���� nParentID
//@���� insertID
//@���� �б���_�������ı�()
XC_API int WINAPI XTree_InsertItemText(HELE hEle, const wchar_t* pValue, int nParentID=XC_ID_ROOT, int insertID=XC_ID_LAST);
//@��ע �״��������ʱ, ��Ҫ�ȴ������������� XTree_CreateAdapter() \ �б���_��������������()
//@���� hEle
//@���� pName
//@���� pValue
//@���� nParentID
//@���� insertID
//@���� �б���_�������ı���չ()
XC_API int WINAPI XTree_InsertItemTextEx(HELE hEle, const wchar_t* pName, const wchar_t* pValue, int nParentID=XC_ID_ROOT, int insertID=XC_ID_LAST);
//@��ע �״��������ʱ, ��Ҫ�ȴ������������� XTree_CreateAdapter() \ �б���_��������������()
//@���� hEle
//@���� hImage
//@���� nParentID
//@���� insertID
//@���� �б���_������ͼƬ()
XC_API int WINAPI XTree_InsertItemImage(HELE hEle, HIMAGE hImage, int nParentID=XC_ID_ROOT, int insertID=XC_ID_LAST);
//@��ע �״��������ʱ, ��Ҫ�ȴ������������� XTree_CreateAdapter() \ �б���_��������������()
//@���� hEle
//@���� pName
//@���� hImage
//@���� nParentID
//@���� insertID
//@���� �б���_������ͼƬ��չ()
XC_API int WINAPI XTree_InsertItemImageEx(HELE hEle, const wchar_t* pName, HIMAGE hImage, int nParentID=XC_ID_ROOT, int insertID=XC_ID_LAST);
//@���� hEle
//@���� �б���_ȡ������()
XC_API int WINAPI XTree_GetCount(HELE hEle);
//@���� hEle
//@���� �б���_ȡ������()
XC_API int WINAPI XTree_GetCountColumn(HELE hEle);
//@���� hEle
//@���� nID
//@���� iColumn
//@���� pValue
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б���_�����ı�()
XC_API BOOL WINAPI XTree_SetItemText(HELE hEle, int nID, int iColumn, const wchar_t* pValue);
//@���� hEle
//@���� nID
//@���� pName
//@���� pValue
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б���_�����ı���չ()
XC_API BOOL WINAPI XTree_SetItemTextEx(HELE hEle, int nID, const wchar_t* pName, const wchar_t* pValue);
//@���� hEle
//@���� nID
//@���� iColumn
//@���� hImage
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б���_����ͼƬ()
XC_API BOOL WINAPI XTree_SetItemImage(HELE hEle, int nID, int iColumn, HIMAGE hImage);
//@���� hEle
//@���� nID
//@���� pName
//@���� hImage
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б���_����ͼƬ��չ()
XC_API BOOL WINAPI XTree_SetItemImageEx(HELE hEle, int nID, const wchar_t* pName, HIMAGE hImage);
//@���� hEle
//@���� nID
//@���� iColumn
//@���� �����ı�����
//@���� �б���_ȡ���ı�()
XC_API const wchar_t* WINAPI XTree_GetItemText(HELE hEle, int nID, int iColumn);
//@���� hEle
//@���� nID
//@���� pName
//@���� �����ı�����
//@���� �б���_ȡ���ı���չ()
XC_API const wchar_t* WINAPI XTree_GetItemTextEx(HELE hEle, int nID, const wchar_t* pName);
//@���� hEle
//@���� nID
//@���� iColumn
//@���� �б���_ȡ��ͼƬ()
XC_API HIMAGE WINAPI XTree_GetItemImage(HELE hEle, int nID, int iColumn);
//@���� hEle
//@���� nID
//@���� pName
//@���� �б���_ȡ��ͼƬ��չ()
XC_API HIMAGE WINAPI XTree_GetItemImageEx(HELE hEle, int nID, const wchar_t* pName);
//@��ע XAdTree_DeleteItem
//@���� hEle
//@���� nID
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �б���_ɾ����()
XC_API BOOL WINAPI XTree_DeleteItem(HELE hEle, int nID);
//@��ע XAdTree_DeleteItemAll
//@���� hEle
//@���� �б���_ɾ��ȫ����()
XC_API void WINAPI XTree_DeleteItemAll(HELE hEle);
//@��ע XAdTree_DeleteColumnAll
//@���� hEle
//@���� �б���_ɾ����ȫ��()
XC_API void WINAPI XTree_DeleteColumnAll(HELE hEle);
//@����}
//@����{  ����ʱ��

//@��ע ��������ʱ��Ԫ��
//@���� x x����
//@���� y y����
//@���� cx ���
//@���� cy �߶�
//@���� hParent ��Ϊ���ھ����Ԫ�ؾ��.
//@���� Ԫ�ؾ��.
//@���� ����_����()
XC_API HELE WINAPI XDateTime_Create(int x, int y, int cx, int cy, HXCGUI hParent);
//@��ע ������ʽ.
//@���� hEle Ԫ�ؾ��.
//@���� nStyle ��ʽ, 0Ϊ����Ԫ��,1Ϊʱ��Ԫ��.
//@���� ����_����ʽ()
XC_API void WINAPI XDateTime_SetStyle(HELE hEle, int nStyle);
//@��ע ��ȡ��ʽ.
//@���� hEle Ԫ�ؾ��.
//@���� Ԫ����ʽ.
//@���� ����_ȡ��ʽ()
XC_API int WINAPI XDateTime_GetStyle(HELE hEle);
//@��ע �л��ָ���Ϊ:б�߻����.
//@���� hEle Ԫ�ؾ��.
//@���� bSlash TRUE:б��, FALSE:����.
//@���� ����_���÷ָ���Ϊб��()
XC_API void WINAPI XDateTime_EnableSplitSlash(HELE hEle, BOOL bSlash);
//@��ע ��ȡ�ڲ���ťԪ��.
//@���� hEle Ԫ�ؾ��.
//@���� nType ��ť����, 0:����������ť, 1:�ϼ�ͷ��ť, 2:�¼�ͷ��ť
//@���� Ԫ����ʽ.
//@���� ����_ȡ�ڲ���ť()
XC_API HELE WINAPI XDateTime_GetButton(HELE hEle, int nType);
//@��ע ��ȡ��ѡ�����ֵı�����ɫ.
//@���� hEle Ԫ�ؾ��.
//@���� Ԫ����ʽ.
//@���� ����_ȡѡ�����ڱ�����ɫ()
XC_API COLORREF WINAPI XDateTime_GetSelBkColor(HELE hEle);
//@��ע ���ñ�ѡ�����ֵı�����ɫ.
//@���� hEle Ԫ�ؾ��.
//@���� crSelectBk ���ֱ�ѡ�б���ɫ, ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� ����_��ѡ�����ڱ�����ɫ()
XC_API void WINAPI XDateTime_SetSelBkColor(HELE hEle, COLORREF crSelectBk);
//@��ע ��ȡ��ǰ����.
//@���� hEle Ԫ�ؾ��.
//@���� pnYear ��.[OUT]
//@���� pnMonth ��.[OUT]
//@���� pnDay ��.[OUT]
//@���� ����_ȡ��ǰ����()
XC_API void WINAPI XDateTime_GetDate(HELE hEle, int* pnYear, int* pnMonth, int* pnDay);
//@��ע ���õ�ǰ����.
//@���� hEle Ԫ�ؾ��.
//@���� nYear ��.
//@���� nMonth ��.
//@���� nDay ��.
//@���� ����_�õ�ǰ����()
XC_API void WINAPI XDateTime_SetDate(HELE hEle, int nYear, int nMonth, int nDay);
//@��ע ��ȡ��ǰʱ��.
//@���� hEle Ԫ�ؾ��.
//@���� pnHour ʱ.[OUT]
//@���� pnMinute ��.[OUT]
//@���� pnSecond ��.[OUT]
//@���� ����_ȡ��ǰʱ��()
XC_API void WINAPI XDateTime_GetTime(HELE hEle, int* pnHour, int* pnMinute, int* pnSecond);
//@��ע ���õ�ǰʱ����.
//@���� hEle Ԫ�ؾ��.
//@���� nHour ʱ.
//@���� nMinute ��.
//@���� nSecond ��.
//@���� ����_�õ�ǰʱ��()
XC_API void WINAPI XDateTime_SetTime(HELE hEle, int nHour, int nMinute, int nSecond);
//@��ע ����������Ƭ
//@���� hEle Ԫ�ؾ��
//@���� ����_����()
XC_API void WINAPI XDateTime_Popup(HELE hEle);
//@����}
//@����{  ������Ƭ

//@��ע ��������ʱ��Ԫ��
//@���� x x����
//@���� y y����
//@���� cx ���
//@���� cy �߶�
//@���� hParent ��Ϊ���ھ����Ԫ�ؾ��.
//@���� Ԫ�ؾ��.
//@���� ����_����()
XC_API HELE WINAPI XMonthCal_Create(int x, int y, int cx, int cy, HXCGUI hParent);
//@��ע ��ȡ�ڲ���ťԪ��.
//@���� hEle Ԫ�ؾ��.
//@���� nType ��ť����.
//@���� Ԫ����ʽ.
//@���� ����_ȡ�ڲ���ť()
XC_API HELE WINAPI XMonthCal_GetButton(HELE hEle, monthCal_button_type_ nType);
//@��ע ����������ǰ������.
//@���� hEle Ԫ�ؾ��.
//@���� nYear ��.
//@���� nMonth ��.
//@���� nDay ��.
//@���� ����_�õ�ǰ����()
XC_API void WINAPI XMonthCal_SetToday(HELE hEle, int nYear, int nMonth, int nDay);
//@��ע ��ȡ������ǰ������.
//@���� hEle Ԫ�ؾ��.
//@���� pnYear ��.[INT,OUT]
//@���� pnMonth ��.[INT,OUT]
//@���� pnDay ��.[INT,OUT]
//@���� ����_ȡ��ǰ����()
XC_API void WINAPI XMonthCal_GetToday(HELE hEle, int* pnYear, int* pnMonth, int* pnDay);
//@��ע ��ȡ����ѡ�е�������.
//@���� hEle Ԫ�ؾ��.
//@���� pnYear ��.[INT,OUT]
//@���� pnMonth ��.[INT,OUT]
//@���� pnDay ��.[INT,OUT]
//@���� ����_ȡѡ������()
XC_API void WINAPI XMonthCal_GetSelDate(HELE hEle, int* pnYear, int* pnMonth, int* pnDay);
//@���� hEle Ԫ�ؾ��
//@���� nFlag 1:����,����������ɫ, 2:�������ֵ���ɫ;  ������������ɫ, ʹ��Ԫ��������ɫ
//@���� color ��ɫֵ
//@���� ����_���ı���ɫ()
XC_API void WINAPI XMonthCal_SetTextColor(HELE hEle, int nFlag, COLORREF color);
//@����}
//@����{  ��״����

//@��ע �Ӹ�UIԪ�ػ򴰿�,�͸����ֶ������Ƴ�.
//@���� hShape ��״������.
//@���� ��״_�Ƴ�()
XC_API void WINAPI XShape_RemoveShape(HXCGUI hShape);
//@��ע ��ȡ��״����Z��.
//@���� hShape ��״������.
//@���� �ɹ���������ֵ,���򷵻� XC_ID_ERROR.
//@���� ��״_ȡZ��()
XC_API int WINAPI XShape_GetZOrder(HXCGUI hShape);
//@��ע �ػ���״����.
//@���� hShape ��״������.
//@���� ��״_�ػ�()
XC_API void WINAPI XShape_Redraw(HXCGUI hShape);
//@��ע ��ȡ���ݿ��.
//@���� hShape ��״������.
//@���� �������ݿ��.
//@���� ��״_ȡ���()
XC_API int WINAPI XShape_GetWidth(HXCGUI hShape);
//@��ע ��ȡ���ݸ߶�.
//@���� hShape ��״������.
//@���� �������ݸ߶�.
//@���� ��״_ȡ�߶�()
XC_API int WINAPI XShape_GetHeight(HXCGUI hShape);
//@��ע �ƶ�λ��
//@���� hShape ��״������
//@���� x x����
//@���� y y����
//@���� ��״_��λ��()
XC_API void WINAPI XShape_SetPosition(HXCGUI hShape, int x, int y);
//@���� hShape ��״������
//@���� pOutX ����X����
//@���� pOutY ����Y����
//@���� ��״_ȡλ��()
XC_API void WINAPI XShape_GetPosition(HXCGUI hShape, int* pOutX, int* pOutY);
//@���� hShape ��״������
//@���� nWidth ���
//@���� nHeight �߶�
//@���� ��״_�ô�С()
XC_API void WINAPI XShape_SetSize(HXCGUI hShape, int nWidth, int nHeight);
//@���� hShape ��״������
//@���� pOutWidth ���ؿ��
//@���� pOutHeight ���ظ߶�
//@���� ��״_ȡ��С()
XC_API void WINAPI XShape_GetSize(HXCGUI hShape, int* pOutWidth, int* pOutHeight);
//@���� hShape ��״������
//@���� alpha ͸����
//@���� ��״_��͸����()
XC_API void WINAPI XShape_SetAlpha(HXCGUI hShape, BYTE alpha);
//@���� hShape ��״������
//@���� ����͸����
//@���� ��״_ȡ͸����()
XC_API BYTE WINAPI XShape_GetAlpha(HXCGUI hShape);
//@��ע ��ȡ����.
//@���� hShape ��״������.
//@���� pRect ���շ�������.
//@���� ��״_ȡ����()
XC_API void WINAPI XShape_GetRect(HXCGUI hShape, RECT* pRect);
//@��ע ��������.
//@���� hShape ��״������.
//@���� pRect ����.
//@���� ��״_������()
XC_API void WINAPI XShape_SetRect(HXCGUI hShape, RECT* pRect);
//@��ע ����Ԫ������,�߼�����,����������ͼƫ��.
//@���� hShape ��״������.
//@���� pRect ����.
//@���� bRedraw �Ƿ��ػ�.
//@���� ����ɹ�����TRUE, ���򷵻�FALSE.
//@���� ��״_���߼�����()
XC_API BOOL WINAPI XShape_SetRectLogic(HXCGUI hShape, RECT* pRect, BOOL bRedraw);
//@��ע ��ȡԪ������,�߼�����,����������ͼƫ��.
//@���� hShape ��״������.
//@���� pRect ����.
//@���� ��״_ȡ�߼�����()
XC_API void WINAPI XShape_GetRectLogic(HXCGUI hShape, RECT* pRect);
//@��ע ���ڴ��ڿͻ�������.
//@���� hShape ��״������.
//@���� pRect ����.
//@���� ��״_ȡ���ڴ��ڿͻ�������()
XC_API void WINAPI XShape_GetWndClientRect(HXCGUI hShape, RECT* pRect);
//@��ע ��������Ч����, ��丸, Ȩ���������������޷�����.
//@���� hShape ��״������.
//@���� pSize ���շ������ݴ�Сֵ.
//@���� ��״_ȡ���ݴ�С()
XC_API void WINAPI XShape_GetContentSize(HXCGUI hShape, SIZE* pSize);
//@��ע �Ƿ���ʾ���ֱ߽�.
//@���� hShape ��״������.
//@���� bShow �Ƿ���ʾ.
//@���� ��״_��ʾ���ֱ߽�()
XC_API void WINAPI XShape_ShowLayout(HXCGUI hShape, BOOL bShow);
//@��ע ��������.
//@���� hShape ��״������.
//@���� ��״_��������()
XC_API void WINAPI XShape_AdjustLayout(HXCGUI hShape);
//@��ע ������״����.
//@���� hShape ��״������.
//@���� ��״_����()
XC_API void WINAPI XShape_Destroy(HXCGUI hShape);
//@����}
//@����{  ��״�ı�

//@��ע ������״�����ı�.
//@���� x X����.
//@���� y Y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� pName �ı�����.
//@���� hParent ��������.
//@���� �����ı�����.
//@���� ��״�ı�_����()
XC_API HXCGUI WINAPI XShapeText_Create(int x, int y, int cx, int cy, const wchar_t* pName, HXCGUI hParent=NULL);
//@��ע �����ı�����.
//@���� hTextBlock ��״�����ı����.
//@���� pName �ı�����.
//@���� ��״�ı�_���ı�()
XC_API void WINAPI XShapeText_SetText(HXCGUI hTextBlock, const wchar_t* pName);
//@��ע ��ȡ�ı�����.
//@���� hTextBlock ��״�����ı����.
//@���� ��״�ı�_ȡ�ı�()
XC_API const wchar_t* WINAPI XShapeText_GetText(HXCGUI hTextBlock);
//@��ע ��ȡ�ı�����.
//@���� hTextBlock ��״�����ı����.
//@���� �ı�����.
//@���� ��״�ı�_ȡ�ı�����()
XC_API int WINAPI XShapeText_GetTextLength(HXCGUI hTextBlock);
//@��ע ��������.
//@���� hTextBlock ��״�����ı����.
//@���� hFontx ������.
//@���� ��״�ı�_������()
XC_API void WINAPI XShapeText_SetFont(HXCGUI hTextBlock, HFONTX hFontx);
//@��ע ��ȡ����.
//@���� hTextBlock ��״�����ı����.
//@���� ����������.
//@���� ��״�ı�_ȡ����()
XC_API HFONTX WINAPI XShapeText_GetFont(HXCGUI hTextBlock);
//@��ע �����ı���ɫ.
//@���� hTextBlock ��״�����ı����.
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� ��״�ı�_���ı���ɫ()
XC_API void WINAPI XShapeText_SetTextColor(HXCGUI hTextBlock, COLORREF color);
//@��ע ��ȡ�ı���ɫ.
//@���� hTextBlock ��״�����ı����.
//@���� ��ɫֵ.
//@���� ��״�ı�_ȡ�ı���ɫ()
XC_API COLORREF WINAPI XShapeText_GetTextColor(HXCGUI hTextBlock);
//@��ע �����ı����뷽ʽ.
//@���� hTextBlock ��״�����ı����.
//@���� align �ı����뷽ʽ; @ref textFormatFlag_ .
//@���� ��״�ı�_���ı�����()
XC_API void WINAPI XShapeText_SetTextAlign(HXCGUI hTextBlock, int align);
//@��ע ��������ƫ��.
//@���� hTextBlock ��״�����ı����.
//@���� x X����.
//@���� y Y����.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ��״�ı�_��ƫ��()
XC_API void WINAPI XShapeText_SetOffset(HXCGUI hTextBlock, int x, int y);
//@����}
//@����{  ��״ͼƬ

//@��ע ������״����-ͼƬ.
//@���� x x����.
//@���� y y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ��������.
//@���� �ɹ�����ͼƬ������,���򷵻�NULL.
//@���� ��״ͼƬ_����()
XC_API HXCGUI WINAPI XShapePic_Create(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@��ע ����ͼƬ.
//@���� hShape ��״������.
//@���� hImage ͼƬ���.
//@���� ��״ͼƬ_��ͼƬ()
XC_API void WINAPI XShapePic_SetImage(HXCGUI hShape, HIMAGE hImage);
//@��ע ��ȡͼƬ���
//@���� hShape ��״������
//@���� ����ͼƬ���.
//@���� ��״ͼƬ_ȡͼƬ()
XC_API HIMAGE WINAPI XShapePic_GetImage(HXCGUI hShape);
//@����}
//@����{  ��״GIF

//@��ע ������״����GIF.
//@���� x X����.
//@���� y Y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ��������.
//@���� �ɹ�������״����GIF���,���򷵻�NULL.
//@���� ��״GIF_����()
XC_API HXCGUI WINAPI XShapeGif_Create(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@��ע ����GIFͼƬ.
//@���� hShape ��״������.
//@���� hImage ͼƬ���.
//@���� ��״GIF_��ͼƬ()
XC_API void WINAPI XShapeGif_SetImage(HXCGUI hShape, HIMAGE hImage);
//@��ע ��ȡͼƬ���.
//@���� hShape ��״������
//@���� ����ͼƬ���.
//@���� ��״GIF_ȡͼƬ()
XC_API HIMAGE WINAPI XShapeGif_GetImage(HXCGUI hShape);
//@����}
//@����{  ��״����

//@��ע ����������״����.
//@���� x X����.
//@���� y Y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ��������.
//@���� ���ؾ��.
//@���� ��״����_����()
XC_API HXCGUI WINAPI XShapeRect_Create(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@��ע ���ñ߿���ɫ.
//@���� hShape ��״������.
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� ��״����_�ñ߿�ɫ()
XC_API void WINAPI XShapeRect_SetBorderColor(HXCGUI hShape, COLORREF color);
//@��ע ���������ɫ.
//@���� hShape ��״������.
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� ��״����_�����ɫ()
XC_API void WINAPI XShapeRect_SetFillColor(HXCGUI hShape, COLORREF color);
//@��ע ����Բ�Ǵ�С.
//@���� hShape ��״������.
//@���� nWidth Բ�ǿ��.
//@���� nHeight Բ�Ǹ߶�.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ��״����_��Բ�Ǵ�С()
XC_API void WINAPI XShapeRect_SetRoundAngle(HXCGUI hShape, int nWidth, int nHeight);
//@��ע ��ȡԲ�Ǵ�С.
//@���� hShape ��״������.
//@���� pWidth Բ�ǿ��.
//@���� pHeight Բ�Ǹ߶�.
//@���� ��״����_ȡԲ�Ǵ�С()
XC_API void WINAPI XShapeRect_GetRoundAngle(HXCGUI hShape, int* pWidth, int* pHeight);
//@��ע ���û��ƾ��α߿�.
//@���� hShape ��״������.
//@���� bEnable �Ƿ�����.
//@���� ��״����_���ñ߿�()
XC_API void WINAPI XShapeRect_EnableBorder(HXCGUI hShape, BOOL bEnable);
//@��ע ����������.
//@���� hShape ��״������.
//@���� bEnable �Ƿ�����.
//@���� ��״����_�������()
XC_API void WINAPI XShapeRect_EnableFill(HXCGUI hShape, BOOL bEnable);
//@��ע ����Բ��.
//@���� hShape ��״������.
//@���� bEnable �Ƿ�����.
//@���� ��״����_����Բ��()
XC_API void WINAPI XShapeRect_EnableRoundAngle(HXCGUI hShape, BOOL bEnable);
//@����}
//@����{  ��״Բ

//@��ע ����Բ����״����.
//@���� x X����.
//@���� y Y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ��������.
//@���� ���ؾ��.
//@���� ��״Բ_����()
XC_API HXCGUI WINAPI XShapeEllipse_Create(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@��ע ���ñ߿���ɫ.
//@���� hShape ��״������.
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� ��״Բ_�ñ߿�ɫ()
XC_API void WINAPI XShapeEllipse_SetBorderColor(HXCGUI hShape, COLORREF color);
//@��ע ���������ɫ.
//@���� hShape ��״������.
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� ��״Բ_�����ɫ()
XC_API void WINAPI XShapeEllipse_SetFillColor(HXCGUI hShape, COLORREF color);
//@��ע ���û���Բ�߿�.
//@���� hShape ��״������.
//@���� bEnable �Ƿ�����.
//@���� ��״Բ_���ñ߿�()
XC_API void WINAPI XShapeEllipse_EnableBorder(HXCGUI hShape, BOOL bEnable);
//@��ע �������Բ.
//@���� hShape ��״������.
//@���� bEnable �Ƿ�����.
//@���� ��״Բ_�������()
XC_API void WINAPI XShapeEllipse_EnableFill(HXCGUI hShape, BOOL bEnable);
//@����}
//@����{  ��״���

//@��ע ���������״����.
//@���� x X����.
//@���� y Y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� pName ����.
//@���� hParent ��������.
//@���� ���ؾ��.
//@���� ��״���_����()
XC_API HXCGUI WINAPI XShapeGroupBox_Create(int x, int y, int cx, int cy, const wchar_t* pName, HXCGUI hParent=NULL);
//@��ע ���ñ߿���ɫ.
//@���� hShape ��״������.
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� ��״���_�ñ߿���ɫ()
XC_API void WINAPI XShapeGroupBox_SetBorderColor(HXCGUI hShape, COLORREF color);
//@��ע �����ı���ɫ.
//@���� hShape ��״������.
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� ��״���_���ı���ɫ()
XC_API void WINAPI XShapeGroupBox_SetTextColor(HXCGUI hShape, COLORREF color);
//@��ע ��������.
//@���� hShape ��״������.
//@���� hFontX �Ų�����.
//@���� ��״���_������()
XC_API void WINAPI XShapeGroupBox_SetFontX(HXCGUI hShape, HFONTX hFontX);
//@��ע �����ı�ƫ����.
//@���� hShape ��״������.
//@���� offsetX ˮƽƫ��.
//@���� offsetY ��ֱƫ��.
//@���� ��״���_���ı�ƫ��()
XC_API void WINAPI XShapeGroupBox_SetTextOffset(HXCGUI hShape, int offsetX, int offsetY);
//@��ע ����Բ�Ǵ�С.
//@���� hShape ��״������.
//@���� nWidth Բ�ǿ��.
//@���� nHeight Բ�Ǹ߶�.
//@���� ��״���_��Բ�Ǵ�С()
XC_API void WINAPI XShapeGroupBox_SetRoundAngle(HXCGUI hShape, int nWidth, int nHeight);
//@��ע �����ı�����.
//@���� hShape ��״������.
//@���� pText �ı�����.
//@���� ��״���_���ı�()
XC_API void WINAPI XShapeGroupBox_SetText(HXCGUI hShape, const wchar_t* pText);
//@��ע ��ȡ�ı�ƫ����.
//@���� hShape ��״������.
//@���� pOffsetX X����ƫ����.
//@���� pOffsetY Y����ƫ����.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ��״���_ȡ�ı�ƫ��()
XC_API void WINAPI XShapeGroupBox_GetTextOffset(HXCGUI hShape, int* pOffsetX, int* pOffsetY);
//@��ע ��ȡԲ�Ǵ�С.
//@���� hShape ��״������.
//@���� pWidth ����Բ�ǿ��.
//@���� pHeight ����Բ�Ǹ߶�.
//@���� ��״���_ȡԲ�Ǵ�С()
XC_API void WINAPI XShapeGroupBox_GetRoundAngle(HXCGUI hShape, int* pWidth, int* pHeight);
//@��ע ����Բ��.
//@���� hShape ��״������.
//@���� bEnable �Ƿ�����.
//@���� ��״���_����Բ��()
XC_API void WINAPI XShapeGroupBox_EnableRoundAngle(HXCGUI hShape, BOOL bEnable);
//@����}
//@����{  ���

//@���� x ��ťx����
//@���� y ��ťy����
//@���� cx ���
//@���� cy �߶�
//@���� hParent ��Ϊ���ھ����Ԫ�ؾ��.
//@���� �����.
//@���� ���_����()
XC_API HXCGUI WINAPI XTable_Create(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@���� hShape ��״������
//@���� nRow ������
//@���� nCol ������
//@���� ���_����()
XC_API void WINAPI XTable_Reset(HXCGUI hShape, int nRow, int nCol);
//@���� hShape ��״������
//@���� iRow ������
//@���� iCol ������
//@���� count ����
//@���� ���_�����()
XC_API void WINAPI XTable_ComboRow(HXCGUI hShape, int iRow, int iCol, int count);
//@���� hShape ��״������
//@���� iRow ������
//@���� iCol ������
//@���� count ����
//@���� ���_�����()
XC_API void WINAPI XTable_ComboCol(HXCGUI hShape, int iRow, int iCol, int count);
//@���� hShape ��״������
//@���� iCol ������
//@���� width ���
//@���� ���_���п�()
XC_API void WINAPI XTable_SetColWidth(HXCGUI hShape, int iCol, int width);
//@���� hShape ��״������
//@���� iRow ������
//@���� height �߶�
//@���� ���_���и�()
XC_API void WINAPI XTable_SetRowHeight(HXCGUI hShape, int iRow, int height);
//@���� hShape ��״������
//@���� color ��ɫ
//@���� ���_�ñ���ɫ()
XC_API void WINAPI XTable_SetBorderColor(HXCGUI hShape, COLORREF color);
//@���� hShape ��״������
//@���� color ��ɫ
//@���� ���_���ı���ɫ()
XC_API void WINAPI XTable_SetTextColor(HXCGUI hShape, COLORREF color);
//@���� hShape ��״������
//@���� hFont ������
//@���� ���_������()
XC_API void WINAPI XTable_SetFont(HXCGUI hShape, HFONTX hFont);
//@���� hShape ��״������
//@���� leftSize ������С
//@���� topSize ������С
//@���� rightSize ������С
//@���� bottomSize ������С
//@���� ���_���������()
XC_API void WINAPI XTable_SetItemPadding(HXCGUI hShape, int leftSize, int topSize, int rightSize, int bottomSize);
//@���� hShape ��״������
//@���� iRow ������
//@���� iCol ������
//@���� pText �ı�
//@���� ���_�����ı�()
XC_API void WINAPI XTable_SetItemText(HXCGUI hShape, int iRow, int iCol, const wchar_t* pText);
//@���� hShape ��״������
//@���� iRow ������
//@���� iCol ������
//@���� hFont ������
//@���� ���_��������()
XC_API void WINAPI XTable_SetItemFont(HXCGUI hShape, int iRow, int iCol, HFONTX hFont);
//@���� hShape ��״������
//@���� iRow ������
//@���� iCol ������
//@���� nAlign ���뷽ʽ  @ref textFormatFlag_
//@���� ���_�����ı�����()
XC_API void WINAPI XTable_SetItemTextAlign(HXCGUI hShape, int iRow, int iCol, int nAlign);
//@���� hShape ��״������
//@���� iRow ������
//@���� iCol ������
//@���� color ��ɫ
//@���� bColor �Ƿ�ʹ��
//@���� ���_�����ı�ɫ()
XC_API void WINAPI XTable_SetItemTextColor(HXCGUI hShape, int iRow, int iCol, COLORREF color, BOOL bColor);
//@���� hShape ��״������
//@���� iRow ������
//@���� iCol ������
//@���� color ��ɫ
//@���� bColor �Ƿ�ʹ��
//@���� ���_�����ɫ()
XC_API void WINAPI XTable_SetItemBkColor(HXCGUI hShape, int iRow, int iCol, COLORREF color, BOOL bColor);
//@���� hShape ��״������
//@���� iRow1 ������1
//@���� iCol1 ������1
//@���� iRow2 ������2
//@���� iCol2 ������2
//@���� nFlag ��ʶ  @ref  table_line_flag_
//@���� color ��ɫ
//@���� ���_������()
XC_API void WINAPI XTable_SetItemLine(HXCGUI hShape, int iRow1, int iCol1, int iRow2, int iCol2, int nFlag, COLORREF color);
//@���� hShape ��״������
//@���� iRow ������
//@���� iCol ������
//@���� flag ��ʶ   @ref table_flag_
//@���� ���_�����ʶ()
XC_API void WINAPI XTable_SetItemFlag(HXCGUI hShape, int iRow, int iCol, int flag);
//@���� hShape ��״������
//@���� iRow ������
//@���� iCol ������
//@���� pRect ���շ�������
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ���_ȡ������()
XC_API BOOL WINAPI XTable_GetItemRect(HXCGUI hShape, int iRow, int iCol, RECT* pRect);
//@����}
//@����{  ����������

//@���� hAdapter �������������
//@���� ���ص�ǰ���ü���
//@���� ����������_�������ü���()
XC_API int WINAPI XAd_AddRef(HXCGUI hAdapter);
//@���� hAdapter �������������
//@���� ���ص�ǰ���ü���
//@���� ����������_�ͷ����ü���()
XC_API int WINAPI XAd_Release(HXCGUI hAdapter);
//@���� hAdapter �������������
//@���� ���ص�ǰ���ü���
//@���� ����������_ȡ���ü���()
XC_API int WINAPI XAd_GetRefCount(HXCGUI hAdapter);
//@��ע ��������������
//@���� hAdapter �������������
//@���� ����������_����()
XC_API void WINAPI XAd_Destroy(HXCGUI hAdapter);
//@���� hAdapter �������������
//@���� bEnable �Ƿ�����
//@���� ����������_�����Զ�����()
XC_API void WINAPI XAd_EnableAutoDestroy(HXCGUI hAdapter, BOOL bEnable);
//@����}
//@����{  �����������б���ͼ

//@��ע �����б���Ԫ������������.
//�����������洢����, UI������ݰ󶨵��ֶ�����ʾ�����������ж�Ӧ������;
//@���� �����������������.
//@���� �����������б���_����()
XC_API HXCGUI WINAPI XAdListView_Create();
//@��ע �����,���������.
//@���� hAdapter �������������.
//@���� pName �ֶ�����.
//@���� ����������.
//@���� �����������б���_�������()
XC_API int WINAPI XAdListView_Group_AddColumn(HXCGUI hAdapter, const wchar_t* pName);
//@��ע �����,�����,����Ĭ����䵽��һ��.
//@���� hAdapter �������������.
//@���� pValue ֵ.
//@���� iPos ����λ������, -1��ӵ�ĩβ
//@���� ����������.
//@���� �����������б���_������ı�()
XC_API int WINAPI XAdListView_Group_AddItemText(HXCGUI hAdapter, const wchar_t* pValue, int iPos=-1);
//@��ע �����,�����,�������ָ����.
//@���� hAdapter �������������.
//@���� pName �ֶ�����.
//@���� pValue ֵ.
//@���� iPos ����λ��, -1��ӵ�ĩβ
//@���� ����������.
//@���� �����������б���_������ı���չ()
XC_API int WINAPI XAdListView_Group_AddItemTextEx(HXCGUI hAdapter, const wchar_t* pName, const wchar_t* pValue, int iPos=-1);
//@��ע �����,�����,����Ĭ������һ��.
//@���� hAdapter �������������.
//@���� hImage ͼƬ���.
//@���� iPos ����λ��, -1��ӵ�ĩβ
//@���� ����������.
//@���� �����������б���_�����ͼƬ()
XC_API int WINAPI XAdListView_Group_AddItemImage(HXCGUI hAdapter, HIMAGE hImage, int iPos=-1);
//@��ע �����,�����,�������ָ����.
//@���� hAdapter �������������.
//@���� pName �ֶ�����.
//@���� hImage ͼƬ���.
//@���� iPos ����λ��, -1��ӵ�ĩβ
//@���� ����������.
//@���� �����������б���_�����ͼƬ��չ()
XC_API int WINAPI XAdListView_Group_AddItemImageEx(HXCGUI hAdapter, const wchar_t* pName, HIMAGE hImage, int iPos=-1);
//@��ע �����,����ָ��������.
//@���� hAdapter �������������.
//@���� iGroup ������.
//@���� iColumn ������.
//@���� pValue ֵ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �����������б���_�������ı�()
XC_API BOOL WINAPI XAdListView_Group_SetText(HXCGUI hAdapter, int iGroup, int iColumn, const wchar_t* pValue);
//@��ע �����,����ָ��������.
//@���� hAdapter �������������.
//@���� iGroup ������.
//@���� pName �ֶ���.
//@���� pValue ֵ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �����������б���_�������ı���չ()
XC_API BOOL WINAPI XAdListView_Group_SetTextEx(HXCGUI hAdapter, int iGroup, const wchar_t* pName, const wchar_t* pValue);
//@��ע �����,����ָ��������.
//@���� hAdapter �������������.
//@���� iGroup ������.
//@���� iColumn ������.
//@���� hImage ͼƬ���.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �����������б���_������ͼƬ()
XC_API BOOL WINAPI XAdListView_Group_SetImage(HXCGUI hAdapter, int iGroup, int iColumn, HIMAGE hImage);
//@��ע �����,����ָ��������.
//@���� hAdapter �������������.
//@���� iGroup ������.
//@���� pName �ֶ���.
//@���� hImage ͼƬ���.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �����������б���_������ͼƬ��չ()
XC_API BOOL WINAPI XAdListView_Group_SetImageEx(HXCGUI hAdapter, int iGroup, const wchar_t* pName, HIMAGE hImage);
//@���� hAdapter �������������
//@���� iGroup ������
//@���� iColumn ������
//@���� �����ı�����
//@���� �����������б���_��ȡ�ı�()
XC_API const wchar_t* WINAPI XAdListView_Group_GetText(HXCGUI hAdapter, int iGroup, int iColumn);
//@���� hAdapter �������������
//@���� iGroup ������
//@���� pName �ֶ�����
//@���� �����ı�����
//@���� �����������б���_��ȡ�ı���չ()
XC_API const wchar_t* XAdListView_Group_GetTextEx(HXCGUI hAdapter, int iGroup, const wchar_t* pName);
//@���� hAdapter �������������
//@���� iGroup ������
//@���� iColumn ������
//@���� ����ͼƬ���
//@���� �����������б���_��ȡͼƬ()
XC_API HIMAGE WINAPI XAdListView_Group_GetImage(HXCGUI hAdapter, int iGroup, int iColumn);
//@���� hAdapter �������������
//@���� iGroup ������
//@���� pName �ֶ�����
//@���� ����ͼƬ���
//@���� �����������б���_��ȡͼƬ��չ()
XC_API HIMAGE WINAPI XAdListView_Group_GetImageEx(HXCGUI hAdapter, int iGroup, const wchar_t* pName);
//@��ע �����,�����.
//@���� hAdapter �������������.
//@���� pName �ֶ�����.
//@���� ����������.
//@���� �����������б���_�������()
XC_API int WINAPI XAdListView_Item_AddColumn(HXCGUI hAdapter, const wchar_t* pName);
//@��ע �����,��ȡ������.
//@���� hAdapter �������������.
//@���� ����������.
//@���� �����������б���_ȡ������()
XC_API int WINAPI XAdListView_Group_GetCount(HXCGUI hAdapter);
//@��ע �����,��ȡָ������������.
//@���� hAdapter �������������.
//@���� iGroup ������.
//@���� �ɹ�����������,���򷵻� @ref XC_ID_ERROR.
//@���� �����������б���_ȡ����������()
XC_API int WINAPI XAdListView_Item_GetCount(HXCGUI hAdapter, int iGroup);
//@��ע �����,�����.
//@���� hAdapter �������������.
//@���� iGroup ������.
//@���� pValue ֵ.
//@���� iPos ����λ��, -1��ӵ�ĩβ
//@���� ����������.
//@���� �����������б���_������ı�()
XC_API int WINAPI XAdListView_Item_AddItemText(HXCGUI hAdapter, int iGroup, const wchar_t* pValue, int iPos=-1);
//@��ע �����,�����,�������ָ����.
//@���� hAdapter �������������.
//@���� iGroup ������.
//@���� pName �ֶ�����.
//@���� pValue ֵ.
//@���� iPos ����λ��, -1��ӵ�ĩβ
//@���� ����������.
//@���� �����������б���_������ı���չ()
XC_API int WINAPI XAdListView_Item_AddItemTextEx(HXCGUI hAdapter, int iGroup, const wchar_t* pName, const wchar_t* pValue, int iPos=-1);
//@��ע �����,�����.
//@���� hAdapter �������������.
//@���� iGroup ������.
//@���� hImage ͼƬ���.
//@���� iPos ����λ��, -1��ӵ�ĩβ
//@���� ����������.
//@���� �����������б���_�����ͼƬ()
XC_API int WINAPI XAdListView_Item_AddItemImage(HXCGUI hAdapter, int iGroup, HIMAGE hImage, int iPos=-1);
//@��ע �����,�����,���ָ��������.
//@���� hAdapter �������������.
//@���� iGroup ������.
//@���� pName �ֶ�����.
//@���� hImage ͼƬ���.
//@���� iPos ����λ��, -1��ӵ�ĩβ
//@���� ����������.
//@���� �����������б���_�����ͼƬ��չ()
XC_API int WINAPI XAdListView_Item_AddItemImageEx(HXCGUI hAdapter, int iGroup, const wchar_t* pName, HIMAGE hImage, int iPos=-1);
//@��ע ɾ����,�Զ�ɾ������.
//@���� hAdapter �������������.
//@���� iGroup ������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �����������б���_��ɾ����()
XC_API BOOL WINAPI XAdListView_Group_DeleteItem(HXCGUI hAdapter, int iGroup);
//@��ע ɾ��ָ�������������.
//@���� hAdapter �������������.
//@���� iGroup ������.
//@���� �����������б���_ɾ��ȫ������()
XC_API void WINAPI XAdListView_Group_DeleteAllChildItem(HXCGUI hAdapter, int iGroup);
//@��ע ɾ����.
//@���� hAdapter �������������.
//@���� iGroup ������.
//@���� iItem ������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �����������б���_ɾ����()
XC_API BOOL WINAPI XAdListView_Item_DeleteItem(HXCGUI hAdapter, int iGroup, int iItem);
//@��ע ɾ������(��,��,��).
//@���� hAdapter �������������.
//@���� �����������б���_ɾ��ȫ��()
XC_API void WINAPI XAdListView_DeleteAll(HXCGUI hAdapter);
//@��ע ɾ�����е���.
//@���� hAdapter �������������.
//@���� �����������б���_ɾ��ȫ����()
XC_API void WINAPI XAdListView_DeleteAllGroup(HXCGUI hAdapter);
//@��ע ɾ�����е���.
//@���� hAdapter �������������.
//@���� �����������б���_ɾ��ȫ����()
XC_API void WINAPI XAdListView_DeleteAllItem(HXCGUI hAdapter);
//@��ע ɾ�������.
//@���� hAdapter �������������.
//@���� iColumn ������.
//@���� �����������б���_��ɾ����()
XC_API void WINAPI XAdListView_DeleteColumnGroup(HXCGUI hAdapter, int iColumn);
//@��ע ɾ�������.
//@���� hAdapter �������������.
//@���� iColumn ������.
//@���� �����������б���_��ɾ����()
XC_API void WINAPI XAdListView_DeleteColumnItem(HXCGUI hAdapter, int iColumn);
//@��ע �����,��ȡ���ı�����.
//@���� hAdapter �������������.
//@���� iGroup ������.
//@���� iItem ������.
//@���� iColumn ������.
//@���� �����ı�����
//@���� �����������б���_��ȡ�ı�()
XC_API const wchar_t* WINAPI XAdListView_Item_GetText(HXCGUI hAdapter, int iGroup, int iItem, int iColumn);
//@��ע �����,��ȡ���ı�����.
//@���� hAdapter �������������.
//@���� iGroup ������.
//@���� iItem ������.
//@���� pName �ֶ�����.
//@���� �����ı�����
//@���� �����������б���_��ȡ�ı���չ()
XC_API const wchar_t* WINAPI XAdListView_Item_GetTextEx(HXCGUI hAdapter, int iGroup, int iItem, const wchar_t* pName);
//@��ע �����,��ȡ��ͼƬ���.
//@���� hAdapter �������������.
//@���� iGroup ������.
//@���� iItem ������.
//@���� iColumn ������.
//@���� ����ͼƬ���.
//@���� �����������б���_��ȡͼƬ()
XC_API HIMAGE WINAPI XAdListView_Item_GetImage(HXCGUI hAdapter, int iGroup, int iItem, int iColumn);
//@��ע �����,��ȡ��ͼƬ���.
//@���� hAdapter �������������.
//@���� iGroup ������.
//@���� iItem ������.
//@���� pName �ֶγ�.
//@���� ����ͼƬ���.
//@���� �����������б���_��ȡͼƬ��չ()
XC_API HIMAGE WINAPI XAdListView_Item_GetImageEx(HXCGUI hAdapter, int iGroup, int iItem, const wchar_t* pName);
//@��ע �����,�������ָ����.
//@���� hAdapter �������������.
//@���� iGroup ������.
//@���� iItem ������.
//@���� iColumn ������.
//@���� pValue ֵ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �����������б���_�����ı�()
XC_API BOOL WINAPI XAdListView_Item_SetText(HXCGUI hAdapter, int iGroup, int iItem, int iColumn, const wchar_t* pValue);
//@��ע �����,�������ָ����.
//@���� hAdapter �������������.
//@���� iGroup ������.
//@���� iItem ������.
//@���� pName �ֶγ�.
//@���� pValue ֵ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �����������б���_�����ı���չ()
XC_API BOOL WINAPI XAdListView_Item_SetTextEx(HXCGUI hAdapter, int iGroup, int iItem, const wchar_t* pName, const wchar_t* pValue);
//@��ע �����,�������ָ����.
//@���� hAdapter �������������.
//@���� iGroup ������.
//@���� iItem ������.
//@���� iColumn ������.
//@���� hImage ͼƬ���.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �����������б���_����ͼƬ()
XC_API BOOL WINAPI XAdListView_Item_SetImage(HXCGUI hAdapter, int iGroup, int iItem, int iColumn, HIMAGE hImage);
//@��ע �����,�������ָ����.
//@���� hAdapter �������������.
//@���� iGroup ������.
//@���� iItem ������.
//@���� pName �ֶγ�.
//@���� hImage ͼƬ���.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �����������б���_����ͼƬ��չ()
XC_API BOOL WINAPI XAdListView_Item_SetImageEx(HXCGUI hAdapter, int iGroup, int iItem, const wchar_t* pName, HIMAGE hImage);
//@����}
//@����{  �������������

//@��ע �����б��Ԫ������������.
//�����������洢����, UI������ݰ󶨵��ֶ�����ʾ�����������ж�Ӧ������;
//@���� �����������������.
//@���� ������������_����()
XC_API HXCGUI WINAPI XAdTable_Create();
//@��ע �����ݽ�������.
//@���� hAdapter �������������.
//@���� iColumn Ҫ�������������
//@���� bAscending �Ƿ�������ʽ����.
//@���� ������������_����()
XC_API void WINAPI XAdTable_Sort(HXCGUI hAdapter, int iColumn, BOOL bAscending);
//@��ע ��ȡ����������.
//@���� hAdapter �������������.
//@���� iRow ������.
//@���� iColumn ������.
//@���� �������������� @ref adapter_date_type_ .
//@���� ������������_ȡ����������()
XC_API adapter_date_type_ WINAPI XAdTable_GetItemDataType(HXCGUI hAdapter, int iRow, int iColumn);
//@��ע ��ȡ����������.
//@���� hAdapter �������������.
//@���� iRow ������.
//@���� pName �ֶγ�.
//@���� �������������� @ref adapter_date_type_ .
//@���� ������������_ȡ������������չ()
XC_API adapter_date_type_ WINAPI XAdTable_GetItemDataTypeEx(HXCGUI hAdapter, int iRow, const wchar_t* pName);
//@��ע ���������.
//@���� hAdapter �������������.
//@���� pName �ֶγ�.
//@���� ����������.
//@���� ������������_�����()
XC_API int WINAPI XAdTable_AddColumn(HXCGUI hAdapter, const wchar_t* pName);
//@��ע ������.
//@���� hAdapter �������������.
//@���� pColName ����,��������ö��ŷֿ�.
//@���� ����������.
//@���� ������������_����()
XC_API int WINAPI XAdTable_SetColumn(HXCGUI hAdapter, const wchar_t* pColName);
//@��ע ���������,Ĭ��ֵ�ŵ���һ��.
//@���� hAdapter �������������.
//@���� pValue ֵ.
//@���� ����������ֵ.
//@���� ������������_������ı�()
XC_API int WINAPI XAdTable_AddRowText(HXCGUI hAdapter, const wchar_t* pValue);
//@��ע ���������,���ָ��������.
//@���� hAdapter �������������.
//@���� pName �ֶγ�.
//@���� pValue ֵ.
//@���� ����������.
//@���� ������������_������ı���չ()
XC_API int WINAPI XAdTable_AddRowTextEx(HXCGUI hAdapter, const wchar_t* pName, const wchar_t* pValue);
//@��ע ���������,Ĭ��ֵ�ŵ���һ��.
//@���� hAdapter �������������.
//@���� hImage ͼƬ���.
//@���� ����������ֵ.
//@���� ������������_�����ͼƬ()
XC_API int WINAPI XAdTable_AddRowImage(HXCGUI hAdapter, HIMAGE hImage);
//@��ע ���������,�����ָ��������.
//@���� hAdapter �������������.
//@���� pName �ֶγ�.
//@���� hImage ͼƬ���.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������������_�����ͼƬ��չ()
XC_API int WINAPI XAdTable_AddRowImageEx(HXCGUI hAdapter, const wchar_t* pName, HIMAGE hImage);
//@��ע ����������,����һ������.
//@���� hAdapter �������������.
//@���� iRow ����λ��������.
//@���� pValue ֵ.
//@���� ����������.
//@���� ������������_�������ı�()
XC_API int WINAPI XAdTable_InsertRowText(HXCGUI hAdapter, int iRow, const wchar_t* pValue);
//@��ע ����������,�����ָ��������.
//@���� hAdapter �������������.
//@���� iRow ����λ��������.
//@���� pName �ֶγ�.
//@���� pValue ֵ.
//@���� ����������.
//@���� ������������_�������ı���չ()
XC_API int WINAPI XAdTable_InsertRowTextEx(HXCGUI hAdapter, int iRow, const wchar_t* pName, const wchar_t* pValue);
//@��ע ����������,����һ������.
//@���� hAdapter �������������.
//@���� iRow ����λ��������.
//@���� hImage ͼƬ���.
//@���� ����������.
//@���� ������������_������ͼƬ()
XC_API int WINAPI XAdTable_InsertRowImage(HXCGUI hAdapter, int iRow, HIMAGE hImage);
//@��ע ����������,�����ָ��������.
//@���� hAdapter �������������.
//@���� iRow ����λ��������.
//@���� pName �ֶγ�.
//@���� hImage ͼƬ���.
//@���� ����������.
//@���� ������������_������ͼƬ��չ()
XC_API int WINAPI XAdTable_InsertRowImageEx(HXCGUI hAdapter, int iRow, const wchar_t* pName, HIMAGE hImage);
//@��ע ����������.
//@���� hAdapter �������������.
//@���� iRow ������.
//@���� iColumn ������.
//@���� pValue ֵ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������������_�����ı�()
XC_API BOOL WINAPI XAdTable_SetItemText(HXCGUI hAdapter, int iRow, int iColumn, const wchar_t* pValue);
//@��ע ����������.
//@���� hAdapter �������������.
//@���� iRow ������.
//@���� pName �ֶγ�.
//@���� pValue ֵ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������������_�����ı���չ()
XC_API BOOL WINAPI XAdTable_SetItemTextEx(HXCGUI hAdapter, int iRow, const wchar_t* pName, const wchar_t* pValue);
//@��ע ����������.
//@���� hAdapter �������������.
//@���� iRow ������.
//@���� iColumn ������.
//@���� nValue ֵ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������������_��������ֵ()
XC_API BOOL WINAPI XAdTable_SetItemInt(HXCGUI hAdapter, int iRow, int iColumn, int nValue);
//@��ע ����������.
//@���� hAdapter �������������.
//@���� iRow ������.
//@���� pName �ֶγ�.
//@���� nValue ֵ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������������_��������ֵ��չ()
XC_API BOOL WINAPI XAdTable_SetItemIntEx(HXCGUI hAdapter, int iRow, const wchar_t* pName, int nValue);
//@��ע ����������.
//@���� hAdapter �������������.
//@���� iRow ������.
//@���� iColumn ������.
//@���� fValue ֵ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������������_�����ֵ()
XC_API BOOL WINAPI XAdTable_SetItemFloat(HXCGUI hAdapter, int iRow, int iColumn, float nValue);
//@��ע ����������.
//@���� hAdapter �������������.
//@���� iRow ������.
//@���� pName �ֶγ�.
//@���� fValue ֵ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������������_�����ֵ��չ()
XC_API BOOL WINAPI XAdTable_SetItemFloatEx(HXCGUI hAdapter, int iRow, const wchar_t* pName, float nValue);
//@��ע ����������.
//@���� hAdapter �������������.
//@���� iRow ������.
//@���� iColumn ������.
//@���� hImage ͼƬ���.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������������_����ͼƬ()
XC_API BOOL WINAPI XAdTable_SetItemImage(HXCGUI hAdapter, int iRow, int iColumn, HIMAGE hImage);
//@��ע ����������.
//@���� hAdapter �������������.
//@���� iRow ������.
//@���� pName �ֶγ�.
//@���� hImage ͼƬ���.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������������_����ͼƬ��չ()
XC_API BOOL WINAPI XAdTable_SetItemImageEx(HXCGUI hAdapter, int iRow, const wchar_t* pName, HIMAGE hImage);
//@��ע ɾ����.
//@���� hAdapter �������������.
//@���� iRow ������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������������_ɾ����()
XC_API BOOL WINAPI XAdTable_DeleteRow(HXCGUI hAdapter, int iRow);
//@��ע ɾ�������.
//@���� hAdapter �������������.
//@���� iRow ����ʼ����.
//@���� nCount ɾ��������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������������_ɾ������չ()
XC_API BOOL WINAPI XAdTable_DeleteRowEx(HXCGUI hAdapter, int iRow, int nCount);
//@��ע ɾ��������.
//@���� hAdapter �������������.
//@���� ������������_ɾ����ȫ��()
XC_API void WINAPI XAdTable_DeleteRowAll(HXCGUI hAdapter);
//@��ע ɾ��������,���������������.
//@���� hAdapter �������������.
//@���� ������������_ɾ����ȫ��()
XC_API void WINAPI XAdTable_DeleteColumnAll(HXCGUI hAdapter);
//@��ע ��ȡ������.
//@���� hAdapter �������������.
//@���� ����������.
//@���� ������������_ȡ������()
XC_API int WINAPI XAdTable_GetCountRow(HXCGUI hAdapter);
//@��ע ��ȡ������.
//@���� hAdapter �������������.
//@���� ����������.
//@���� ������������_ȡ������()
XC_API int WINAPI XAdTable_GetCountColumn(HXCGUI hAdapter);
//@��ע ��ȡ���ı�����.
//@���� hAdapter �������������
//@���� iRow ������
//@���� iColumn ������
//@���� �����ı�����
//@���� ������������_ȡ���ı�()
XC_API const wchar_t* WINAPI XAdTable_GetItemText(HXCGUI hAdapter, int iRow, int iColumn);
//@��ע ��ȡ���ı�����.
//@���� hAdapter �������������.
//@���� iRow ������.
//@���� pName �ֶγ�.
//@���� �����ı�����
//@���� ������������_ȡ���ı���չ()
XC_API const wchar_t* WINAPI XAdTable_GetItemTextEx(HXCGUI hAdapter, int iRow, const wchar_t* pName);
//@��ע ��ȡ��ͼƬ���.
//@���� hAdapter �������������.
//@���� iRow ������.
//@���� iColumn ������.
//@���� ����ͼƬ���.
//@���� ������������_ȡ��ͼƬ()
XC_API HIMAGE WINAPI XAdTable_GetItemImage(HXCGUI hAdapter, int iRow, int iColumn);
//@��ע ��ȡ��ͼƬ���.
//@���� hAdapter �������������.
//@���� iRow ������.
//@���� pName �ֶγ�.
//@���� ����ͼƬ���.
//@���� ������������_ȡ��ͼƬ��չ()
XC_API HIMAGE WINAPI XAdTable_GetItemImageEx(HXCGUI hAdapter, int iRow, const wchar_t* pName);
//@��ע ��ȡ��ֵ.
//@���� hAdapter �������������.
//@���� iRow ������.
//@���� iColumn ������.
//@���� pOutValue ���շ���ֵ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������������_ȡ������ֵ()
XC_API BOOL WINAPI XAdTable_GetItemInt(HXCGUI hAdapter, int iRow, int iColumn, int* pOutValue);
//@��ע ��ȡ��ֵ.
//@���� hAdapter �������������.
//@���� iRow ������.
//@���� pName �ֶγ�.
//@���� pOutValue ���շ���ֵ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������������_ȡ������ֵ��չ()
XC_API BOOL WINAPI XAdTable_GetItemIntEx(HXCGUI hAdapter, int iRow, const wchar_t* pName, int* pOutValue);
//@��ע ��ȡ��ֵ.
//@���� hAdapter �������������.
//@���� iRow ������.
//@���� iColumn ������.
//@���� pOutValue ���շ���ֵ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������������_ȡ���ֵ()
XC_API BOOL WINAPI XAdTable_GetItemFloat(HXCGUI hAdapter, int iRow, int iColumn, float* pOutValue);
//@��ע ��ȡ��ֵ.
//@���� hAdapter �������������.
//@���� iRow ������.
//@���� pName �ֶγ�.
//@���� pOutValue ���շ���ֵ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������������_ȡ���ֵ��չ()
XC_API BOOL WINAPI XAdTable_GetItemFloatEx(HXCGUI hAdapter, int iRow, const wchar_t* pName, float* pOutValue);
//@����}
//@����{  ����������MAP

//@��ע ��������������,��������.
//�����������洢����, UI������ݰ󶨵��ֶ�����ʾ�����������ж�Ӧ������;
//@���� �����������������.
//@���� ����������MAP_����()
XC_API HXCGUI WINAPI XAdMap_Create();
//@��ע ����������.
//@���� hAdapter �������������.
//@���� pName �ֶ�����.
//@���� pValue ֵ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ����������MAP_������ı�()
XC_API BOOL WINAPI XAdMap_AddItemText(HXCGUI hAdapter, const wchar_t* pName, const wchar_t* pValue);
//@��ע ����������.
//@���� hAdapter �������������.
//@���� pName �ֶ�����.
//@���� hImage ͼƬ���.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ����������MAP_�����ͼƬ()
XC_API BOOL WINAPI XAdMap_AddItemImage(HXCGUI hAdapter, const wchar_t* pName, HIMAGE hImage);
//@��ע ɾ��������.
//@���� hAdapter �������������.
//@���� pName �ֶ�����.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ����������MAP_ɾ����()
XC_API BOOL WINAPI XAdMap_DeleteItem(HXCGUI hAdapter, const wchar_t* pName);
//@��ע ��ȡ������.
//@���� hAdapter �������������.
//@���� ����������.
//@���� ����������MAP_ȡ������()
XC_API int WINAPI XAdMap_GetCount(HXCGUI hAdapter);
//@��ע ��ȡ������,�������Ϊ�ı�.
//@���� hAdapter �������������.
//@���� pName �ֶ�����.
//@���� �����ı�����.
//@���� ����������MAP_ȡ���ı�()
XC_API const wchar_t* WINAPI XAdMap_GetItemText(HXCGUI hAdapter, const wchar_t* pName);
//@��ע ��ȡ������,�������ΪͼƬ���.
//@���� hAdapter �������������.
//@���� pName �ֶ�����.
//@���� ����ͼƬ���.
//@���� ����������MAP_ȡ��ͼƬ()
XC_API HIMAGE WINAPI XAdMap_GetItemImage(HXCGUI hAdapter, const wchar_t* pName);
//@��ע ����������.
//@���� hAdapter �������������.
//@���� pName �ֶ�����.
//@���� pValue ֵ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ����������MAP_�����ı�()
XC_API BOOL WINAPI XAdMap_SetItemText(HXCGUI hAdapter, const wchar_t* pName, const wchar_t* pValue);
//@��ע ����������.
//@���� hAdapter �������������.
//@���� pName �ֶ�����.
//@���� hImage ֵ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ����������MAP_����ͼƬ()
XC_API BOOL WINAPI XAdMap_SetItemImage(HXCGUI hAdapter, const wchar_t* pName, HIMAGE hImage);
//@����}
//@����{  ������������

//@��ע ������Ԫ������������.
//�����������洢����, UI������ݰ󶨵��ֶ�����ʾ�����������ж�Ӧ������;
//@���� �����������������.
//@���� ������������_����()
XC_API HXCGUI WINAPI XAdTree_Create();
//@��ע �����.
//@���� hAdapter �������������.
//@���� pName �ֶ�����.
//@���� ��������ֵ.
//@���� ������������_�����()
XC_API int WINAPI XAdTree_AddColumn(HXCGUI hAdapter, const wchar_t* pName);
//@��ע ������.
//@���� hAdapter �������������.
//@���� pColName ����,��������ö��ŷֿ�.
//@���� ����������.
//@���� ������������_����()
XC_API int WINAPI XAdTree_SetColumn(HXCGUI hAdapter, const wchar_t* pColName);
//@��ע ������,������䵽��һ��.
//@���� hAdapter �������������.
//@���� pValue ֵ.
//@���� nParentID ��ID.
//@���� insertID ����λ��ID.
//@���� ������IDֵ.
//@���� ������������_�������ı�()
XC_API int WINAPI XAdTree_InsertItemText(HXCGUI hAdapter, const wchar_t* pValue, int nParentID=XC_ID_ROOT, int insertID=XC_ID_LAST);
//@��ע ������,������䵽ָ����.
//@���� hAdapter �������������.
//@���� pName �ֶ�����.
//@���� pValue ֵ.
//@���� nParentID ��ID.
//@���� insertID ����λ��ID.
//@���� ������IDֵ.
//@���� ������������_�������ı���չ()
XC_API int WINAPI XAdTree_InsertItemTextEx(HXCGUI hAdapter, const wchar_t* pName, const wchar_t* pValue, int nParentID=XC_ID_ROOT, int insertID=XC_ID_LAST);
//@��ע ������,������䵽��һ��.
//@���� hAdapter �������������.
//@���� hImage ͼƬ���.
//@���� nParentID ��ID.
//@���� insertID ����λ��ID.
//@���� ������IDֵ.
//@���� ������������_������ͼƬ()
XC_API int WINAPI XAdTree_InsertItemImage(HXCGUI hAdapter, HIMAGE hImage, int nParentID=XC_ID_ROOT, int insertID=XC_ID_LAST);
//@��ע ������,������䵽ָ����.
//@���� hAdapter �������������.
//@���� pName �ֶ�����.
//@���� hImage ͼƬ���.
//@���� nParentID ��ID.
//@���� insertID ����λ��ID.
//@���� ������IDֵ.
//@���� ������������_������ͼƬ��չ()
XC_API int WINAPI XAdTree_InsertItemImageEx(HXCGUI hAdapter, const wchar_t* pName, HIMAGE hImage, int nParentID=XC_ID_ROOT, int insertID=XC_ID_LAST);
//@��ע ��ȡ������.
//@���� hAdapter �������������.
//@���� ��������.
//@���� ������������_ȡ������()
XC_API int WINAPI XAdTree_GetCount(HXCGUI hAdapter);
//@��ע ��ȡ������.
//@���� hAdapter �������������.
//@���� ����������.
//@���� ������������_ȡ������()
XC_API int WINAPI XAdTree_GetCountColumn(HXCGUI hAdapter);
//@��ע ����������.
//@���� hAdapter �������������.
//@���� nID ��ID.
//@���� iColumn ������.
//@���� pValue ֵ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������������_�����ı�()
XC_API BOOL WINAPI XAdTree_SetItemText(HXCGUI hAdapter, int nID, int iColumn, const wchar_t* pValue);
//@��ע �������ļ�����.
//@���� hAdapter �������������.
//@���� nID ��ID.
//@���� pName �ֶ�����.
//@���� pValue ֵ.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������������_�����ı���չ()
XC_API BOOL WINAPI XAdTree_SetItemTextEx(HXCGUI hAdapter, int nID, const wchar_t* pName, const wchar_t* pValue);
//@��ע ����������.
//@���� hAdapter �������������.
//@���� nID ��ID.
//@���� iColumn ������.
//@���� hImage ͼƬ���.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������������_����ͼƬ()
XC_API BOOL WINAPI XAdTree_SetItemImage(HXCGUI hAdapter, int nID, int iColumn, HIMAGE hImage);
//@��ע ����������.
//@���� hAdapter �������������.
//@���� nID ��ID.
//@���� pName �ֶ�����.
//@���� hImage ͼƬ���.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������������_����ͼƬ��չ()
XC_API BOOL WINAPI XAdTree_SetItemImageEx(HXCGUI hAdapter, int nID, const wchar_t* pName, HIMAGE hImage);
//@��ע ��ȡ���ı�����.
//@���� hAdapter �������������.
//@���� nID ��ID.
//@���� iColumn ������.
//@���� �����ı�����
//@���� ������������_ȡ���ı�()
XC_API const wchar_t* WINAPI XAdTree_GetItemText(HXCGUI hAdapter, int nID, int iColumn);
//@��ע ��ȡ���ı�����.
//@���� hAdapter �������������.
//@���� nID ��ID.
//@���� pName �ֶ�����.
//@���� �����ı�����
//@���� ������������_ȡ���ı���չ()
XC_API const wchar_t* WINAPI XAdTree_GetItemTextEx(HXCGUI hAdapter, int nID, const wchar_t* pName);
//@��ע ��ȡ������.
//@���� hAdapter �������������.
//@���� nID ��ID.
//@���� iColumn ������.
//@���� ����ͼƬ���.
//@���� ������������_ȡ��ͼƬ()
XC_API HIMAGE WINAPI XAdTree_GetItemImage(HXCGUI hAdapter, int nID, int iColumn);
//@��ע ��ȡ������.
//@���� hAdapter �������������.
//@���� nID ��ID.
//@���� pName �ֶ�����.
//@���� ����ͼƬ���.
//@���� ������������_ȡ��ͼƬ��չ()
XC_API HIMAGE WINAPI XAdTree_GetItemImageEx(HXCGUI hAdapter, int nID, const wchar_t* pName);
//@��ע ɾ����.
//@���� hAdapter �������������.
//@���� nID ��ID.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������������_ɾ����()
XC_API BOOL WINAPI XAdTree_DeleteItem(HXCGUI hAdapter, int nID);
//@��ע ɾ��������.
//@���� hAdapter �������������.
//@���� ������������_ɾ����ȫ��()
XC_API void WINAPI XAdTree_DeleteItemAll(HXCGUI hAdapter);
//@��ע ɾ��������,�����������.
//@���� hAdapter �������������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ������������_ɾ����ȫ��()
XC_API void WINAPI XAdTree_DeleteColumnAll(HXCGUI hAdapter);
//@����}
//@����{  ����������

//@��ע ��������������.
//@���� �������������.
//@���� ����_����()
XC_API HBKM WINAPI XBkM_Create();
//@��ע ����.
//@���� hBkInfoM �������������.
//@���� ����_����()
XC_API void WINAPI XBkM_Destroy(HBKM hBkInfoM);
//@��ע ��������, ����Ϊ�˼��ݾɰ�; ��ʹ�� @ref XBkM_SetInfo()
//@���� hBkInfoM �������������.
//@���� pText ���������ַ���.
//@���� �������õı�����������.
//@���� ����_������old()
XC_API int WINAPI XBkM_SetBkInfo(HBKM hBkInfoM, const wchar_t* pText);
//@��ע ���ñ�������.
//@���� hBkInfoM �������������.
//@���� pText ���������ַ���.
//@���� �������õı�����������.
//@���� ����_������()
XC_API int WINAPI XBkM_SetInfo(HBKM hBkInfoM, const wchar_t* pText);
//@��ע ��ӱ�������.
//@���� hBkInfoM �������������.
//@���� pText ���������ַ���.
//@���� ������ӵı�����������.
//@���� ����_�������()
XC_API int WINAPI XBkM_AddInfo(HBKM hBkInfoM, const wchar_t* pText);
//@��ע ��ӱ������ݱ߿�.
//@���� hBkInfoM �������������.
//@���� nState ���״̬  �μ��ĵ�: API�ӿ�->���״̬
//@���� color ��ɫ.
//@���� width �߿�.
//@���� id ��������ID, �ɺ���(0)
//@���� ����_��ӱ߿�()
XC_API void WINAPI XBkM_AddBorder(HBKM hBkInfoM, int nState, COLORREF color, int width, int id=0);
//@��ע ��ӱ����������.
//@���� hBkInfoM �������������.
//@���� nState ���״̬  �μ��ĵ�: API�ӿ�->���״̬
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� id ��������ID, �ɺ���(0)
//@���� ����_������()
XC_API void WINAPI XBkM_AddFill(HBKM hBkInfoM, int nState, COLORREF color, int id=0);
//@��ע ��ӱ�������ͼƬ.
//@���� hBkInfoM �������������.
//@���� nState ���״̬  �μ��ĵ�: API�ӿ�->���״̬
//@���� hImage ͼƬ���.
//@���� id ��������ID, �ɺ���(0)
//@���� ����_���ͼƬ()
XC_API void WINAPI XBkM_AddImage(HBKM hBkInfoM, int nState, HIMAGE hImage, int id=0);
//@��ע ��ȡ������������.
//@���� hBkInfoM �������������.
//@���� ������������.
//@���� ����_ȡ����()
XC_API int WINAPI XBkM_GetCount(HBKM hBkInfoM);
//@��ע ��ձ�������.
//@���� hBkInfoM �������������.
//@���� ����_���()
XC_API void WINAPI XBkM_Clear(HBKM hBkInfoM);
//@��ע ���Ʊ�������.
//@���� hBkInfoM �������������.
//@���� nState ���״̬  �μ��ĵ�: API�ӿ�->���״̬
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ����_����()
XC_API BOOL WINAPI XBkM_Draw(HBKM hBkInfoM, int nState, HDRAW hDraw, RECT* pRect);
//@��ע ���Ʊ�������, ��������.
//@���� hBkInfoM �������������.
//@���� nState ���״̬  �μ��ĵ�: API�ӿ�->���״̬
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������.
//@���� nStateEx ��(nState)�а���(nStateEx)�е�һ������״̬ʱ��Ч.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ����_������չ()
XC_API BOOL WINAPI XBkM_DrawEx(HBKM hBkInfoM, int nState, HDRAW hDraw, RECT* pRect, int nStateEx);
//@��ע �Ƿ��Զ�����
//@���� hBkInfoM �������������
//@���� bEnable �Ƿ�����
//@���� ����_�����Զ�����()
XC_API void WINAPI XBkM_EnableAutoDestroy(HBKM hBkInfoM, BOOL bEnable);
//@���� hBkInfoM �������������
//@���� ����_�������ü���()
XC_API void WINAPI XBkM_AddRef(HBKM hBkInfoM);
//@��ע �ͷ����ü���,�����ü���Ϊ0ʱ,�Զ�����
//@���� hBkInfoM �������������
//@���� ����_�ͷ����ü���()
XC_API void WINAPI XBkM_Release(HBKM hBkInfoM);
//@��ע ��ȡ���ü���
//@���� hBkInfoM �������������
//@���� �������ü���.
//@���� ����_ȡ���ü���()
XC_API int WINAPI XBkM_GetRefCount(HBKM hBkInfoM);
//@���� hBkInfoM �������������
//@���� nState ״̬
//@���� color ���շ�����ɫ
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ����_ȡָ��״̬�ı���ɫ()
XC_API BOOL WINAPI XBkM_GetStateTextColor(HBKM hBkInfoM, int nState, COLORREF* color);
//@���� hBkInfoM �������������
//@���� id ��������ID
//@���� ���ض�����
//@���� ����_ȡ��������()
XC_API vint WINAPI XBkM_GetObject(HBKM hBkInfoM, int id);
//@����}
//@����{  ��ͼ

//@��ע ����ͼ�λ���ģ��ʵ��.
//@���� hWindow ���ھ��
//@���� ͼ�λ���ģ��ʵ�����.
//@���� ����_����()
XC_API HDRAW WINAPI XDraw_Create(HWINDOW hWindow);
//@��ע ����ͼ�λ���ģ��ʵ��
//@���� hWindow ���ھ��
//@���� hdc hdc���
//@���� ͼ�λ���ģ��ʵ�����.
//@���� ����_����GDI()
XC_API HDRAW WINAPI XDraw_CreateGDI(HWINDOW hWindow, HDC hdc);
//@��ע ����ͼ�λ���ģ��ʵ�����.
//@���� hDraw ͼ�λ��ƾ��.
//@���� ����_����()
XC_API void WINAPI XDraw_Destroy(HDRAW hDraw);
//@��ע ��������ƫ����,X����ƫ��Ϊ����,����ƫ��Ϊ����.
//@���� hDraw ͼ�λ��ƾ��.
//@���� x X��ƫ����.
//@���� y Y��ƫ����.
//@���� ����_��ƫ��()
XC_API void WINAPI XDraw_SetOffset(HDRAW hDraw, int x, int y);
//@��ע ��ȡ����ƫ����,X����ƫ��Ϊ����,����ƫ��Ϊ����.
//@���� hDraw ͼ�λ��ƾ��.
//@���� pX ����X��ƫ����.
//@���� pY ����Y��ƫ����.
//@���� ����_ȡƫ��()
XC_API void WINAPI XDraw_GetOffset(HDRAW hDraw, int* pX, int* pY);
//@��ע ��ԭ״̬,�ͷ��û��󶨵�GDI����,���续ˢ,����
//@���� hDraw ͼ�λ��ƾ��.
//@���� ����_GDI_��ԭ״̬()
XC_API void WINAPI XDraw_GDI_RestoreGDIOBJ(HDRAW hDraw);
//@��ע SetBkMode() �μ�MSDN
//@���� hDraw ͼ�λ��ƾ��.
//@���� bTransparent �μ�MSDN.
//@���� �μ�MSDN.
//@���� ����_GDI_�ñ���ģʽ()
XC_API int WINAPI XDraw_GDI_SetBkMode(HDRAW hDraw, BOOL bTransparent);
//@��ע ѡ��һ��������Ϊ��ǰ�ü�����,ע��:�ú���ֻ��GDI��Ч
//NULLREGION Region is empty.\n
//SIMPLEREGION Region is a single rectangle.\n
//COMPLEXREGION Region is more than one rectangle.\n
//ERROR An error occurred. (The previous clipping region is unaffected).
//@���� hDraw ͼ�λ��ƾ��.
//@���� hRgn ������.
//@���� ����ֵָ�������ĸ����ԣ�����������ֵ֮һ.\n
//@���� ����_GDI_ѡ��ü�����()
XC_API int WINAPI XDraw_GDI_SelectClipRgn(HDRAW hDraw, HRGN hRgn);
//@��ע GDI��������ָ���Ĵ�ɫ�߼�ˢ
//@���� hDraw ͼ�λ��ƾ��.
//@���� crColor ��ˢ��ɫ.
//@���� ��������ɹ�,����ֵ��ʶһ���߼�ˢ,�������ʧ��,����ֵ��NULL.
//@���� ����_GDI_����ʵ�Ļ�ˢ()
XC_API HBRUSH WINAPI XDraw_GDI_CreateSolidBrush(HDRAW hDraw, COLORREF crColor);
//@��ע GDI����һ���߼���,ָ������ʽ,��Ⱥ���ɫ,���ıʿ���ѡ���豸������,���ڻ�������������
//@���� hDraw ͼ�λ��ƾ��
//@���� fnPenStyle ������ʽ, PS_SOLID:ʵ�� PS_DASH:���� PS_DOT:����  PS_DASHDOT:����_���� PS_DASHDOTDOT:����_��_�� PS_NULL:��  PS_INSIDEFRAME:ʵ��_�ʿ���������չ
//@���� width ���ʿ��
//@���� crColor ��ɫ
//@���� ��������ɹ�,����ֵ��һ�����,��ʶһ���߼���,�������ʧ��,����ֵ��NULL.
//@���� ����_GDI_��������()
XC_API HPEN WINAPI XDraw_GDI_CreatePen(HDRAW hDraw, int fnPenStyle, int width, COLORREF crColor);
//@��ע GDI������������
//@���� hDraw ͼ�λ��ƾ��.
//@���� nLeftRect ���Ͻ�X����.
//@���� nTopRect ���Ͻ�Y����.
//@���� nRightRect ���½�X����.
//@���� nBottomRect ���½�Y����.
//@���� �ɹ�����������,ʧ�ܷ���NULL.
//@���� ����_GDI_������������()
XC_API HRGN WINAPI XDraw_GDI_CreateRectRgn(HDRAW hDraw, int nLeftRect, int nTopRect, int nRightRect, int nBottomRect);
//@��ע GDI����һ��Բ�ǵľ�������
//@���� hDraw ͼ�λ��ƾ��.
//@���� nLeftRect X-��������Ͻ�.
//@���� nTopRect Y-�������Ͻ�����
//@���� nRightRect X-�������½�
//@���� nBottomRect Y-�������½�
//@���� nWidthEllipse ��Բ�Ŀ��.
//@���� nHeightEllipse ��Բ�ĸ߶�.
//@���� ��������ɹ�,����ֵ�Ǹ�����ľ��,�������ʧ��,����ֵ��NULL.
//@���� ����_GDI_����Բ�Ǿ�������()
XC_API HRGN WINAPI XDraw_GDI_CreateRoundRectRgn(HDRAW hDraw, int nLeftRect, int nTopRect, int nRightRect, int nBottomRect, int nWidthEllipse, int nHeightEllipse);
//@��ע GDI����һ�����������
//ALTERNATE Selects alternate mode (fills area between odd-numbered and even-numbered polygon sides on each scan line).\n
//WINDING Selects winding mode (fills any region with a nonzero winding value).
//@���� hDraw ͼ�λ��ƾ��.
//@���� pPt POINT����.
//@���� cPoints �����С(�����Ա��).
//@���� fnPolyFillMode ��������ģʽ,ָ������ȷ���ڸõ������������ģʽ,�����������������ֵ֮һ.\n
//@���� ��������ɹ�,����ֵ�Ǹ�����ľ��,�������ʧ��,����ֵ��NULL.
//@���� ����_GDI_�������������()
XC_API HRGN WINAPI XDraw_GDI_CreatePolygonRgn(HDRAW hDraw, POINT* pPt, int cPoints, int fnPolyFillMode);
//@��ע ���ƾ���,ʹ�õ�ǰ�Ļ�ˢ�ͻ���
//@���� hDraw ͼ�λ��ƾ��.
//@���� nLeftRect ���Ͻ�X����.
//@���� nTopRect ���Ͻ�Y����.
//@���� nRightRect ���½�X����.
//@���� nBottomRect ���½�Y����.
//@���� ��������ɹ�,���ط���ֵ,�������ʧ��,����ֵ����.
//@���� ����_GDI_����()
XC_API BOOL WINAPI XDraw_GDI_Rectangle(HDRAW hDraw, int nLeftRect, int nTopRect, int nRightRect, int nBottomRect);
//@��ע ͨ��ʹ��ָ���Ļ�ˢ���һ������
//@���� hDraw ͼ�λ��ƾ��.
//@���� hrgn ������.
//@���� hbr ��ˢ���.
//@���� ��������ɹ�,���ط���ֵ,�������ʧ��,����ֵ����.
//@���� ����_GID_�������()
XC_API BOOL WINAPI XDraw_GDI_FillRgn(HDRAW hDraw, HRGN hrgn, HBRUSH hbr);
//@���� hDraw ͼ�λ��ƾ��
//@���� pRect ��������
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ����_GDI_��Բ()
XC_API BOOL WINAPI XDraw_GDI_Ellipse(HDRAW hDraw, RECT* pRect);
//@��ע ���Ʊ߿�,ʹ��ָ���Ļ�ˢ����ָ��������ı߿�
//@���� hDraw ͼ�λ��ƾ��.
//@���� hrgn ������.
//@���� hbr ��ˢ���.
//@���� width �߿���,��ֱ��.
//@���� height �߿�߶�,ˮƽ��.
//@���� ��������ɹ�,���ط���ֵ,�������ʧ��,����ֵ����.
//@���� ����_GDI_�߿�����()
XC_API BOOL WINAPI XDraw_GDI_FrameRgn(HDRAW hDraw, HRGN hrgn, HBRUSH hbr, int width, int nHeight);
//@��ע ���µ�ǰλ�õ�ָ���㣬��������ǰ��λ��
//@���� hDraw ͼ�λ��ƾ��.
//@���� X ����.
//@���� Y ����.
//@���� pPoint ������ǰ�ĵ�ǰλ�õ�һ��POINT�ṹ��ָ��,������������NULLָ��,û�з���ԭ����λ��.
//@���� ��������ɹ�,���ط���ֵ,�������ʧ��,����ֵ����.
//@���� ����_GDI_�ƶ������()
XC_API BOOL WINAPI XDraw_GDI_MoveToEx(HDRAW hDraw, int X, int Y, POINT* pPoint=NULL);
//@��ע ��������һ���ߴӵ�ǰλ�õ�,��������ָ����
//@���� hDraw ͼ�λ��ƾ��.
//@���� nXEnd X����,�߽�����.
//@���� nYEnd Y����,�߽�����.
//@���� ��������ɹ�,���ط���ֵ,�������ʧ��,����ֵ����.
//@���� ����_GDI_���յ�()
XC_API BOOL WINAPI XDraw_GDI_LineTo(HDRAW hDraw, int nXEnd, int nYEnd);
//@��ע Polyline() �μ�MSDN
//@���� hDraw ͼ�λ��ƾ��.
//@���� pArrayPt �μ�MSDN.
//@���� arrayPtSize �μ�MSDN.
//@���� �μ�MSDN.
//@���� ����_GDI_����()
XC_API BOOL WINAPI XDraw_GDI_Polyline(HDRAW hDraw, POINT* pArrayPt, int arrayPtSize);
//@��ע ����ͼ��,DrawIconEx()�μ�MSDN
//DI_COMPAT��ʹ���� 16 λ Windows ��ͬ�Ļ��Ʒ�ʽ
//DI_DEFAULTSIZE��ʹ��ͼ���Ĭ�ϴ�С
//DI_IMAGE��������ͼ��
//DI_MASK������������
//DI_NORMAL������ͼ������루Ĭ�ϣ�
//@���� hDraw ͼ�λ��ƾ��
//@���� xLeft ͼ�����Ͻǵ� x ����
//@���� yTop ͼ�����Ͻǵ� y ����
//@���� hIcon Ҫ���Ƶ�ͼ��ľ��
//@���� cxWidth ͼ��Ŀ��
//@���� cyWidth ͼ��ĸ߶�
//@���� istepIfAniCur ��������֡����������Ǿ�̬ͼ��������Ϊ 0
//@���� hbrFlickerFreeDraw ������˸���ɵĻ�ˢ��ͨ������Ϊ NULL
//@���� diFlags ���Ʊ�־,
//@���� .
//@���� ����_GDI_ͼ����չ()
XC_API BOOL WINAPI XDraw_GDI_DrawIconEx(HDRAW hDraw, int xLeft, int yTop, HICON hIcon, int cxWidth, int cyWidth, UINT istepIfAniCur, HBRUSH hbrFlickerFreeDraw, UINT diFlags);
//@��ע BitBlt() �μ�MSDN
//@���� hDrawDest XX.
//@���� nXDest XX.
//@���� nYDest XX.
//@���� width XX.
//@���� height XX.
//@���� hdcSrc XX.
//@���� nXSrc XX.
//@���� nYSrc XX.
//@���� dwRop XX.
//@���� .
//@���� ����_GDI_����()
XC_API BOOL WINAPI XDraw_GDI_BitBlt(HDRAW hDrawDest, int nXDest, int nYDest, int width, int nHeight, HDC hdcSrc, int nXSrc, int nYSrc, DWORD dwRop);
//@��ע BitBlt() �μ�MSDN
//@���� hDrawDest XX.
//@���� nXDest XX.
//@���� nYDest XX.
//@���� width XX.
//@���� height XX.
//@���� hDrawSrc XX.
//@���� nXSrc XX.
//@���� nYSrc XX.
//@���� dwRop XX.
//@���� .
//@���� ����_GDI_����2()
XC_API BOOL WINAPI XDraw_GDI_BitBlt2(HDRAW hDrawDest, int nXDest, int nYDest, int width, int nHeight, HDRAW hDrawSrc, int nXSrc, int nYSrc, DWORD dwRop);
//@��ע AlphaBlend() �μ�MSDN
//@���� hDraw XX.
//@���� nXOriginDest XX.
//@���� nYOriginDest XX.
//@���� nWidthDest XX.
//@���� nHeightDest XX.
//@���� hdcSrc XX.
//@���� nXOriginSrc XX.
//@���� nYOriginSrc XX.
//@���� nWidthSrc XX.
//@���� nHeightSrc XX.
//@���� alpha XX.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� ����_GDI_��͸������()
XC_API BOOL WINAPI XDraw_GDI_AlphaBlend(HDRAW hDraw, int nXOriginDest, int nYOriginDest, int nWidthDest, int nHeightDest, HDC hdcSrc, int nXOriginSrc, int nYOriginSrc, int nWidthSrc, int nHeightSrc, int alpha);
//@��ע ����������ָ�������굽ָ������ɫ������
//@���� hDraw ͼ�λ��ƾ��.
//@���� X ����
//@���� Y ����
//@���� crColor ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� ��������ɹ�����RGBֵ,���ʧ�ܷ���-1.
//@���� ����_GDI_��������ɫ()
XC_API COLORREF WINAPI XDraw_GDI_SetPixel(HDRAW hDraw, int X, int Y, COLORREF crColor);
//@��ע ��ȡ�󶨵��豸������HDC.
//@���� hDraw ͼ�λ��ƾ��.
//@���� ����HDC���.
//@���� ����_ȡHDC()
XC_API HDC WINAPI XDraw_GetHDC(HDRAW hDraw);
//@���� hDraw ͼ�λ��ƾ��
//@���� ���� ID2D1Factory*
//@���� ����_ȡD2D����()
XC_API vint WINAPI XDraw_GetD2dFactory(HDRAW hDraw);
//@���� hDraw ͼ�λ��ƾ��
//@���� ���� IDWriteFactory*
//@���� ����_ȡD2DWrite����()
XC_API vint WINAPI XDraw_GetD2dWriteFactory(HDRAW hDraw);
//@���� hDraw ͼ�λ��ƾ��
//@���� ���� IWICImagingFactory*
//@���� ����_ȡD2DWIC����()
XC_API vint WINAPI XDraw_GetD2dWICFactory(HDRAW hDraw);
//@���� hDraw ͼ�λ��ƾ��
//@���� ���� ID2D1RenderTarget*
//@���� ����_ȡD2D��ȾĿ��()
XC_API vint WINAPI XDraw_GetD2dRenderTarget(HDRAW hDraw);
//@���� hDraw ͼ�λ��ƾ��
//@���� ����������
//@���� ����_ȡ����()
XC_API HFONTX WINAPI XDraw_GetFont(HDRAW hDraw);
//@���� hDraw ͼ�λ��ƾ��
//@���� mode ��Ⱦģʽ  @ref XC_DWRITE_RENDERING_MODE
//@���� ����_��D2D�ı���Ⱦģʽ()
XC_API void WINAPI XDraw_SetD2dTextRenderingMode(HDRAW hDraw, XC_DWRITE_RENDERING_MODE mode);
//@��ע �����ı���Ⱦ����GDI+
//@���� hDraw ͼ�λ��ƾ��
//@���� nType �μ�GDI+ TextRenderingHint ����.
//@���� ����_���ı��Ų�����()
XC_API void WINAPI XDraw_SetTextRenderingHint(HDRAW hDraw, int nType);
//@��ע ʹ��ָ����ɫ������
//@���� hDraw ͼ�λ��ƾ��
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� ����_D2D_����()
XC_API void WINAPI XDraw_D2D_Clear(HDRAW hDraw, COLORREF color);
//@��ע ���û�ˢ��ɫ.
//@���� hDraw ͼ�λ��ƾ��.
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� ����_�û�ˢ��ɫ()
XC_API void WINAPI XDraw_SetBrushColor(HDRAW hDraw, COLORREF color);
//@��ע �����ı���ֱ��ʾ.
//@���� hDraw ͼ�λ��ƾ��.
//@���� bVertical �Ƿ�ֱ��ʾ�ı�.
//@���� ����_���ı���ֱ()
XC_API void WINAPI XDraw_SetTextVertical(HDRAW hDraw, BOOL bVertical);
//@��ע �����ı�����.
//@���� hDraw ͼ�λ��ƾ��.
//@���� nFlags �����ʶ @ref textFormatFlag_ .
//@���� ����_���ı�����()
XC_API void WINAPI XDraw_SetTextAlign(HDRAW hDraw, int nFlag);
//@��ע ��������.
//@���� hDraw ͼ�λ��ƾ��.
//@���� hFontx �Ų�����.
//@���� ����_������()
XC_API void WINAPI XDraw_SetFont(HDRAW hDraw, HFONTX hFontx);
//@��ע �����߿�.
//@���� hDraw ͼ�λ��ƾ��.
//@���� width ���.
//@���� ����_���߿�()
XC_API void WINAPI XDraw_SetLineWidth(HDRAW hDraw, int width);
//@��ע �����߿�
//@���� hDraw ͼ�λ��ƾ��.
//@���� width ���.
//@���� ����_���߿�F()
XC_API void WINAPI XDraw_SetLineWidthF(HDRAW hDraw, float width);
//@��ע ���òü�����.
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������.
//@���� ����_�òü�����()
XC_API void WINAPI XDraw_SetClipRect(HDRAW hDraw, RECT* pRect);
//@��ע ����ü�����.
//@���� hDraw ͼ�λ��ƾ��.
//@���� ����_����ü�����()
XC_API void WINAPI XDraw_ClearClip(HDRAW hDraw);
//@��ע ����ƽ��ģʽ.
//@���� hDraw ͼ�λ��ƾ��.
//@���� bEnable �Ƿ�����.
//@���� ����_����ƽ��ģʽ()
XC_API void WINAPI XDraw_EnableSmoothingMode(HDRAW hDraw, BOOL bEnable);
//@��ע ������֮��,����GDI+����ʱ, �������alpha=255,���Զ��޸�Ϊ254, Ӧ��GDI+��bug, ����͸��ͨ���쳣
//@���� hDraw ͼ�λ��ƾ��
//@���� bTransparent �Ƿ�����
//@���� ����_���ô���͸���ж�()
XC_API void WINAPI XDraw_EnableWndTransparent(HDRAW hDraw, BOOL bTransparent);
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������.
//@���� ����_������()
XC_API void WINAPI XDraw_FillRect(HDRAW hDraw, RECT* pRect);
//@���� hDraw ͼ�λ��ƾ��
//@���� pRect ��������
//@���� ����_������F()
XC_API void WINAPI XDraw_FillRectF(HDRAW hDraw, RECTF* pRect);
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������.
//@���� color ��ɫ.
//@���� ����_������ָ����ɫ()
XC_API void WINAPI XDraw_FillRectColor(HDRAW hDraw, RECT* pRect, COLORREF color);
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������.
//@���� color ��ɫ.
//@���� ����_������ָ����ɫF()
XC_API void WINAPI XDraw_FillRectColorF(HDRAW hDraw, RECTF* pRect, COLORREF color);
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������
//@���� ����_�����Բ()
XC_API void WINAPI XDraw_FillEllipse(HDRAW hDraw, RECT* pRect);
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������
//@���� ����_�����ԲF()
XC_API void WINAPI XDraw_FillEllipseF(HDRAW hDraw, RECTF* pRect);
//@��ע ������Բ�߿�
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������.
//@���� ����_��Բ()
XC_API void WINAPI XDraw_DrawEllipse(HDRAW hDraw, RECT* pRect);
//@��ע ������Բ�߿�
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������.
//@���� ����_��ԲF()
XC_API void WINAPI XDraw_DrawEllipseF(HDRAW hDraw, RECTF* pRect);
//@��ע ���Բ�Ǿ���
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������.
//@���� width Բ�ǿ��.
//@���� height Բ�Ǹ߶�.
//@���� ����_���Բ�Ǿ���()
XC_API void WINAPI XDraw_FillRoundRect(HDRAW hDraw, RECT* pRect, int width, int nHeight);
//@��ע ���Բ�Ǿ���
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������.
//@���� width Բ�ǿ��.
//@���� height Բ�Ǹ߶�.
//@���� ����_���Բ�Ǿ���F()
XC_API void WINAPI XDraw_FillRoundRectF(HDRAW hDraw, RECTF* pRect, float width, float height);
//@��ע ����Բ�Ǿ��α߿�.
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������.
//@���� width Բ�ǿ��.
//@���� height Բ�Ǹ߶�.
//@���� ����_Բ�Ǿ���()
XC_API void WINAPI XDraw_DrawRoundRect(HDRAW hDraw, RECT* pRect, int width, int nHeight);
//@��ע ����Բ�Ǿ��α߿�
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������.
//@���� width Բ�ǿ��.
//@���� height Բ�Ǹ߶�.
//@���� ����_Բ�Ǿ���F()
XC_API void WINAPI XDraw_DrawRoundRectF(HDRAW hDraw, RECTF* pRect, float width, float height);
//@��ע ���Բ�Ǿ���.
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ����.
//@���� leftTop Բ�Ǵ�С.
//@���� rightTop Բ�Ǵ�С.
//@���� rightBottom Բ�Ǵ�С.
//@���� leftBottom Բ�Ǵ�С.
//@���� ����_���Բ�Ǿ�����չ()
XC_API void WINAPI XDraw_FillRoundRectEx(HDRAW hDraw, RECT* pRect, int leftTop, int rightTop, int rightBottom, int leftBottom);
//@��ע ���Բ�Ǿ���
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ����.
//@���� leftTop Բ�Ǵ�С.
//@���� rightTop Բ�Ǵ�С.
//@���� rightBottom Բ�Ǵ�С.
//@���� leftBottom Բ�Ǵ�С.
//@���� ����_���Բ�Ǿ�����չF()
XC_API void WINAPI XDraw_FillRoundRectExF(HDRAW hDraw, RECTF* pRect, float leftTop, float rightTop, float rightBottom, float leftBottom);
//@��ע ����Բ�Ǿ��α߿�
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ����.
//@���� leftTop Բ�Ǵ�С.
//@���� rightTop Բ�Ǵ�С.
//@���� rightBottom Բ�Ǵ�С.
//@���� leftBottom Բ�Ǵ�С.
//@���� ����_Բ�Ǿ�����չ()
XC_API void WINAPI XDraw_DrawRoundRectEx(HDRAW hDraw, RECT* pRect, int leftTop, int rightTop, int rightBottom, int leftBottom);
//@��ע ����Բ�Ǿ��α߿�
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ����.
//@���� leftTop Բ�Ǵ�С.
//@���� rightTop Բ�Ǵ�С.
//@���� rightBottom Բ�Ǵ�С.
//@���� leftBottom Բ�Ǵ�С.
//@���� ����_Բ�Ǿ�����չF()
XC_API void WINAPI XDraw_DrawRoundRectExF(HDRAW hDraw, RECTF* pRect, float leftTop, float rightTop, float rightBottom, float leftBottom);
//@��ע �������,��һ����ɫ���ɵ���һ����ɫ.
//GRADIENT_FILL_RECT_H ˮƽ��� .
//GRADIENT_FILL_RECT_V ��ֱ���.
//GRADIENT_FILL_TRIANGLE ������.
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������.
//@���� color1 ��ʼ��ɫ.
//@���� color2 ������ɫ.
//@���� mode ģʽ.
//@���� ����_�������2()
XC_API void WINAPI XDraw_GradientFill2(HDRAW hDraw, RECT* pRect, COLORREF color1, COLORREF color2, int mode);
//@��ע �������,��һ����ɫ���ɵ���һ����ɫ
//GRADIENT_FILL_RECT_H ˮƽ��� .
//GRADIENT_FILL_RECT_V ��ֱ���.
//GRADIENT_FILL_TRIANGLE ������.
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������.
//@���� color1 ��ʼ��ɫ.
//@���� color2 ������ɫ.
//@���� mode ģʽ.
//@���� ����_�������2F()
XC_API void WINAPI XDraw_GradientFill2F(HDRAW hDraw, RECTF* pRect, COLORREF color1, COLORREF color2, int mode);
//@��ע �������,��һ����ɫ���ɵ���һ����ɫ.
//GRADIENT_FILL_RECT_H ˮƽ���.
//GRADIENT_FILL_RECT_V ��ֱ���.
//GRADIENT_FILL_TRIANGLE ������.
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������.
//@���� color1 ��ʼ��ɫ.
//@���� color2 ������ɫ,�м�.
//@���� color3 ��ʼ��ɫ,�м�.
//@���� color4 ������ɫ.
//@���� mode ģʽ.
//@���� ����_�������4()
XC_API void WINAPI XDraw_GradientFill4(HDRAW hDraw, RECT* pRect, COLORREF color1, COLORREF color2, COLORREF color3, COLORREF color4, int mode);
//@��ע �������,��һ����ɫ���ɵ���һ����ɫ
//GRADIENT_FILL_RECT_H ˮƽ���.
//GRADIENT_FILL_RECT_V ��ֱ���.
//GRADIENT_FILL_TRIANGLE ������.
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������.
//@���� color1 ��ʼ��ɫ.
//@���� color2 ������ɫ,�м�.
//@���� color3 ��ʼ��ɫ,�м�.
//@���� color4 ������ɫ.
//@���� mode ģʽ.
//@���� ����_�������4F()
XC_API void WINAPI XDraw_GradientFill4F(HDRAW hDraw, RECTF* pRect, COLORREF color1, COLORREF color2, COLORREF color3, COLORREF color4, int mode);
//@��ע ���ƾ��α߿�
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������
//@���� ����_����()
XC_API void WINAPI XDraw_DrawRect(HDRAW hDraw, RECT* pRect);
//@��ע ���ƾ��α߿�
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������
//@���� ����_����F()
XC_API void WINAPI XDraw_DrawRectF(HDRAW hDraw, RECTF* pRect);
//@��ע ��������.
//@���� hDraw ͼ�λ��ƾ��.
//@���� x1 ����.
//@���� y1 ����.
//@���� x2 ����.
//@���� y2 ����.
//@���� ����_����()
XC_API void WINAPI XDraw_DrawLine(HDRAW hDraw, int x1, int y1, int x2, int y2);
//@��ע ��������
//@���� hDraw ͼ�λ��ƾ��.
//@���� x1 ����.
//@���� y1 ����.
//@���� x2 ����.
//@���� y2 ����.
//@���� ����_����F()
XC_API void WINAPI XDraw_DrawLineF(HDRAW hDraw, float x1, float y1, float x2, float y2);
//@��ע D2D��ʱ����
//@���� hDraw ͼ�λ��ƾ��.
//@���� points ���������
//@���� count �����С(�����Ա��)
//@���� tension ���ڻ����0.0F��ֵ��ָ�����ߵ�������D2D ���Դ˲���
//@���� ����_����()
XC_API void WINAPI XDraw_DrawCurve(HDRAW hDraw, POINT* points, int count, float tension);
//@��ע D2D��ʱ����
//@���� hDraw ͼ�λ��ƾ��.
//@���� points ���������
//@���� count �����С(�����Ա��)
//@���� tension ���ڻ����0.0F��ֵ��ָ�����ߵ�������D2D ���Դ˲���
//@���� ����_����F()
XC_API void WINAPI XDraw_DrawCurveF(HDRAW hDraw, POINTF* points, int count, float tension);
//@���� hDraw ͼ�λ��ƾ��
//@���� x ����
//@���� y ����
//@���� width ���
//@���� height �߶�
//@���� startAngle ��ʼ�Ƕ�
//@���� sweepAngle ���ƽǶ�, ����ʼ�Ƕȿ�ʼ����
//@���� ����_Բ��()
XC_API void WINAPI XDraw_DrawArc(HDRAW hDraw, int x, int y, int width, int nHeight, float startAngle, float sweepAngle);
//@���� hDraw ͼ�λ��ƾ��
//@���� x ����
//@���� y ����
//@���� width ���
//@���� height �߶�
//@���� startAngle ��ʼ�Ƕ�
//@���� sweepAngle ���ƽǶ�, ����ʼ�Ƕȿ�ʼ����
//@���� ����_Բ��F()
XC_API void WINAPI XDraw_DrawArcF(HDRAW hDraw, float x, float y, float width, float height, float startAngle, float sweepAngle);
//@��ע ���ƽ������.
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������.
//@���� ����_�������()
XC_API void WINAPI XDraw_FocusRect(HDRAW hDraw, RECT* pRect);
//@��ע ���ƽ������
//@���� hDraw ͼ�λ��ƾ��.
//@���� pRect ��������.
//@���� ����_�������F()
XC_API void WINAPI XDraw_FocusRectF(HDRAW hDraw, RECTF* pRect);
//@��ע ����ˮƽ��ֱ����.
//@���� hDraw ͼ�λ��ƾ��.
//@���� x1 ���x����.
//@���� y1 ���y����.
//@���� x2 ������x����.
//@���� y2 ������y����.
//@���� ����_����()
XC_API void WINAPI XDraw_Dottedline(HDRAW hDraw, int x1, int y1, int x2, int y2);
//@��ע ����ˮƽ��ֱ����
//@���� hDraw ͼ�λ��ƾ��.
//@���� x1 ���x����.
//@���� y1 ���y����.
//@���� x2 ������x����.
//@���� y2 ������y����.
//@���� ����_����F()
XC_API void WINAPI XDraw_DottedlineF(HDRAW hDraw, float x1, float y1, float x2, float y2);
//@��ע ���ƶ����.
//@���� hDraw ͼ�λ��ƾ��.
//@���� points ������������.
//@���� nCount ��������.
//@���� ����_�����()
XC_API void WINAPI XDraw_DrawPolygon(HDRAW hDraw, POINT* points, int nCount);
//@��ע ���ƶ����
//@���� hDraw ͼ�λ��ƾ��.
//@���� points ������������.
//@���� nCount ��������.
//@���� ����_�����F()
XC_API void WINAPI XDraw_DrawPolygonF(HDRAW hDraw, POINTF* points, int nCount);
//@��ע �������.
//@���� hDraw ͼ�λ��ƾ��.
//@���� points ������������.
//@���� nCount ��������.
//@���� ����_�������()
XC_API void WINAPI XDraw_FillPolygon(HDRAW hDraw, POINT* points, int nCount);
//@��ע �������
//@���� hDraw ͼ�λ��ƾ��.
//@���� points ������������.
//@���� nCount ��������.
//@���� ����_�������F()
XC_API void WINAPI XDraw_FillPolygonF(HDRAW hDraw, POINTF* points, int nCount);
//@��ע ����ͼƬ.
//@���� hDraw ͼ�λ��ƾ��.
//@���� hImageFrame ͼƬ���.
//@���� x x����.
//@���� y y����.
//@���� ����_ͼƬ()
XC_API void WINAPI XDraw_Image(HDRAW hDraw, HIMAGE hImageFrame, int x, int y);
//@��ע ����ͼƬ
//@���� hDraw ͼ�λ��ƾ��.
//@���� hImageFrame ͼƬ���.
//@���� x x����.
//@���� y y����.
//@���� ����_ͼƬF()
XC_API void WINAPI XDraw_ImageF(HDRAW hDraw, HIMAGE hImageFrame, float x, float y);
//@��ע ����ͼƬ
//@���� hDraw ͼ�λ��ƾ��.
//@���� hImageFrame ͼƬ���.
//@���� x x����.
//@���� y y����.
//@���� width ���.
//@���� height �߶�.
//@���� ����_ͼƬ��չ()
XC_API void WINAPI XDraw_ImageEx(HDRAW hDraw, HIMAGE hImageFrame, int x, int y, int width, int height);
//@��ע ����ͼƬ
//@���� hDraw ͼ�λ��ƾ��.
//@���� hImageFrame ͼƬ���.
//@���� x x����.
//@���� y y����.
//@���� width ���.
//@���� height �߶�.
//@���� ����_ͼƬ��չF()
XC_API void WINAPI XDraw_ImageExF(HDRAW hDraw, HIMAGE hImageFrame, float x, float y, float width, float height);
//@��ע ����ͼƬ.
//@���� hDraw ͼ�λ��ƾ��.
//@���� hImageFrame ͼƬ���.
//@���� pRect ����.
//@���� bOnlyBorder �Ƿ�ֻ���Ʊ�Ե����.
//@���� ����_ͼƬ����Ӧ()
XC_API void WINAPI XDraw_ImageAdaptive(HDRAW hDraw, HIMAGE hImageFrame, RECT* pRect, BOOL bOnlyBorder=FALSE);
//@��ע ����ͼƬ
//@���� hDraw ͼ�λ��ƾ��.
//@���� hImageFrame ͼƬ���.
//@���� pRect ����.
//@���� bOnlyBorder �Ƿ�ֻ���Ʊ�Ե����.
//@���� ����_ͼƬ����ӦF()
XC_API void WINAPI XDraw_ImageAdaptiveF(HDRAW hDraw, HIMAGE hImageFrame, RECTF* pRect, BOOL bOnlyBorder=FALSE);
//@��ע ����ͼƬ.
//@���� hDraw ͼ�λ��ƾ��.
//@���� hImageFrame ͼƬ���.
//@���� pRect ����.
//@���� flag ��ʶ, 0:�����Ͻǿ�ʼƽ��,1:�����½ǿ�ʼƽ��.
//@���� ����_ͼƬƽ��()
XC_API void WINAPI XDraw_ImageTile(HDRAW hDraw, HIMAGE hImageFrame, RECT* pRect, int flag=0);
//@��ע ����ͼƬ
//@���� hDraw ͼ�λ��ƾ��.
//@���� hImageFrame ͼƬ���.
//@���� pRect ����.
//@���� flag ��ʶ, 0:�����Ͻǿ�ʼƽ��,1:�����½ǿ�ʼƽ��.
//@���� ����_ͼƬƽ��F()
XC_API void WINAPI XDraw_ImageTileF(HDRAW hDraw, HIMAGE hImageFrame, RECTF* pRect, int flag=0);
//@��ע ����ͼƬ.
//@���� hDraw ͼ�λ��ƾ��.
//@���� hImageFrame ͼƬ���.
//@���� pRect ����.
//@���� bClip �Ƿ�ü�����.
//@���� ����_ͼƬ��ǿ()
XC_API void WINAPI XDraw_ImageSuper(HDRAW hDraw, HIMAGE hImageFrame, RECT* pRect, BOOL bClip=FALSE);
//@��ע ����ͼƬ
//@���� hDraw ͼ�λ��ƾ��.
//@���� hImageFrame ͼƬ���.
//@���� pRect ����.
//@���� bClip �Ƿ�ü�����.
//@���� ����_ͼƬ��ǿF()
XC_API void WINAPI XDraw_ImageSuperF(HDRAW hDraw, HIMAGE hImageFrame, RECTF* pRect, BOOL bClip=FALSE);
//@��ע ����ͼƬ.
//@���� hDraw ͼ�λ��ƾ��.
//@���� hImageFrame ͼƬ���.
//@���� pRcDest Ŀ������.
//@���� pRcSrc Դ����.
//@���� ����_ͼƬ��ǿ2()
XC_API void WINAPI XDraw_ImageSuperEx(HDRAW hDraw, HIMAGE hImageFrame, RECT* pRcDest, RECT* pRcSrc);
//@��ע ����ͼƬ
//@���� hDraw ͼ�λ��ƾ��.
//@���� hImageFrame ͼƬ���.
//@���� pRcDest Ŀ������.
//@���� pRcSrc Դ����.
//@���� ����_ͼƬ��ǿ��չF()
XC_API void WINAPI XDraw_ImageSuperExF(HDRAW hDraw, HIMAGE hImageFrame, RECTF* pRcDest, RECTF* pRcSrc);
//@��ע ���ƴ��ڸǵ�ͼƬ. D2D����
//@���� hDraw ͼ�λ��ƾ��.
//@���� hImageFrame ͼƬ���.
//@���� hImageFrameMask ͼƬ���,�ڸ�.
//@���� pRect ����.
//@���� pRectMask ����,�ڸ�.
//@���� bClip �Ƿ�ü�����.
//@���� ����_ͼƬ��ǿ�ڸ�()
XC_API void WINAPI XDraw_ImageSuperMask(HDRAW hDraw, HIMAGE hImageFrame, HIMAGE hImageFrameMask, RECT* pRect, RECT* pRectMask, BOOL bClip=FALSE);
//@��ע ���ƴ��ڸǵ�ͼƬ.D2D����
//@���� hDraw ͼ�λ��ƾ��.
//@���� hImageFrame ͼƬ���.
//@���� hImageFrameMask ͼƬ���,�ڸ�.
//@���� pRect ����.
//@���� x Ŀ��X����.
//@���� y Ŀ��Y����.
//@���� ����_ͼƬ�ڸ�()
XC_API void WINAPI XDraw_ImageMask(HDRAW hDraw, HIMAGE hImageFrame, HIMAGE hImageFrameMask, RECT* pRect, int x, int y);
//@��ע ʹ�þ�����Ϊ����
//@���� hDraw ͼ�λ��ƾ��
//@���� hImageFrame ͼƬ���
//@���� pRect ��������
//@���� pRcMask ��������
//@���� pRcRoundAngle ����Բ��
//@���� ����_ͼƬ�ڸǾ���()
XC_API void WINAPI XDraw_ImageMaskRect(HDRAW hDraw, HIMAGE hImageFrame, RECT* pRect, RECT* pRcMask, RECT* pRcRoundAngle);
//@��ע ʹ��Բ����Ϊ����
//@���� hDraw ͼ�λ��ƾ��
//@���� hImageFrame ͼƬ���
//@���� pRect ��������
//@���� pRcMask ��������
//@���� ����_ͼƬ�ڸ�Բ��()
XC_API void WINAPI XDraw_ImageMaskEllipse(HDRAW hDraw, HIMAGE hImageFrame, RECT* pRect, RECT* pRcMask);
//@���� hDraw ͼ�λ��ƾ��.
//@���� pString �ַ���.
//@���� nCount �ַ�������.�����-1�Զ�ȡ����
//@���� pRect ����.
//@���� ����_�ı�ָ������()
XC_API void WINAPI XDraw_DrawText(HDRAW hDraw, const wchar_t* pString, int nCount, RECT* pRect);
//@���� hDraw ͼ�λ��ƾ��.
//@���� pString �ַ���.
//@���� nCount �ַ�������.�����-1�Զ�ȡ����
//@���� pRect ����.
//@���� ����_�ı�ָ������F()
XC_API void WINAPI XDraw_DrawTextF(HDRAW hDraw, const wchar_t* pString, int nCount, RECTF* pRect);
//@��ע �μ�MSDN.
//@���� hDraw ͼ�λ��ƾ��.
//@���� pString �ַ���.
//@���� nCount �ַ�������.�����-1�Զ�ȡ����
//@���� pRect ����.
//@���� colorLine �»�����ɫ.
//@���� ����_�ı��»���()
XC_API void WINAPI XDraw_DrawTextUnderline(HDRAW hDraw, const wchar_t* pString, int nCount, RECT* pRect, COLORREF colorLine);
//@���� hDraw ͼ�λ��ƾ��.
//@���� pString �ַ���.
//@���� nCount �ַ�������.�����-1�Զ�ȡ����
//@���� pRect ����.
//@���� colorLine �»�����ɫ.
//@���� ����_�ı��»���F()
XC_API void WINAPI XDraw_DrawTextUnderlineF(HDRAW hDraw, const wchar_t* pString, int nCount, RECTF* pRect, COLORREF colorLine);
//@���� hDraw ͼ�λ��ƾ��.
//@���� xStart XX.
//@���� yStart XX.
//@���� pString XX.
//@���� cbString XX. �����-1�Զ�ȡ����
//@���� ����_�ı�()
XC_API void WINAPI XDraw_TextOut(HDRAW hDraw, int xStart, int yStart, const wchar_t* pString, int cbString);
//@���� hDraw ͼ�λ��ƾ��.
//@���� xStart XX.
//@���� yStart XX.
//@���� pString XX.
//@���� cbString XX.
//@���� ����_�ı�F()
XC_API void WINAPI XDraw_TextOutF(HDRAW hDraw, float xStart, float yStart, const wchar_t* pString, int cbString);
//@���� hDraw ͼ�λ��ƾ��.
//@���� xStart XX.
//@���� yStart XX.
//@���� pString XX.
//@���� ����_�ı���չ()
XC_API void WINAPI XDraw_TextOutEx(HDRAW hDraw, int xStart, int yStart, const wchar_t* pString);
//@���� hDraw ͼ�λ��ƾ��.
//@���� xStart XX.
//@���� yStart XX.
//@���� pString XX.
//@���� ����_�ı���չF()
XC_API void WINAPI XDraw_TextOutExF(HDRAW hDraw, float xStart, float yStart, const wchar_t* pString);
//@���� hDraw ͼ�λ��ƾ��.
//@���� xStart XX.
//@���� yStart XX.
//@���� pString XX.
//@���� ����_�ı�A()
XC_API void WINAPI XDraw_TextOutA(HDRAW hDraw, int xStart, int yStart, const char* pString);
//@���� hDraw ͼ�λ��ƾ��.
//@���� xStart XX.
//@���� yStart XX.
//@���� pString XX.
//@���� ����_�ı�AF()
XC_API void WINAPI XDraw_TextOutAF(HDRAW hDraw, float xStart, float yStart, const char* pString);
//@���� hDraw ͼ�λ��ƾ��
//@���� hSvg SVG���
//@���� ����_SVGԴ()
XC_API void WINAPI XDraw_DrawSvgSrc(HDRAW hDraw, HSVG hSvg);
//@���� hDraw ͼ�λ��ƾ��
//@���� hSvg SVG���
//@���� x x����
//@���� y y����
//@���� ����_SVG()
XC_API void WINAPI XDraw_DrawSvg(HDRAW hDraw, HSVG hSvg, int x, int y);
//@���� hDraw ͼ�λ��ƾ��
//@���� hSvg SVG���
//@���� x x����
//@���� y y����
//@���� nWidth ���
//@���� nHeight �߶�
//@���� ����_SVG��չ()
XC_API void WINAPI XDraw_DrawSvgEx(HDRAW hDraw, HSVG hSvg, int x, int y, int nWidth, int nHeight);
//@���� hDraw ͼ�λ��ƾ��
//@���� hSvg SVG���
//@���� nWidth ���
//@���� nHeight �߶�
//@���� ����_SVG��С()
XC_API void WINAPI XDraw_DrawSvgSize(HDRAW hDraw, HSVG hSvg, int nWidth, int nHeight);
//@����}
//@����{  ����

//@��ע �����Ų�����,����������Ԫ�ع�����,���Զ��ͷ�.
//@���� size �����С,�����С,��λ(pt,��).
//@���� ������.
//@���� ����_����()
XC_API HFONTX WINAPI XFont_Create(int size);
//@��ע �����Ų�����
//@���� pName ��������.
//@���� size �����С,��λ(pt,��).
//@���� style ������ʽ @ref fontStyle_ ;  ��Ϊedit��֧���»�������, �»���,ɾ����,��Ҫ��������,����ģ��ӿ�
//@���� ������.
//@���� ����_������չ()
XC_API HFONTX WINAPI XFont_CreateEx(const wchar_t* pName=L"����", int size=12, int style=fontStyle_regular);
//@��ע �����Ų����� D2D��֧�ִ˽ӿ�
//@���� pFontInfo ������Ϣ.
//@���� ������.
//@���� ����_������LOGFONT()
XC_API HFONTX WINAPI XFont_CreateFromLOGFONTW(LOGFONTW* pFontInfo);
//@��ע �����Ų����������HFONT����,  D2D��֧�ִ˽ӿ�
//@���� hFont ������.
//@���� �����Ų�����.
//@���� ����_������HFONT()
XC_API HFONTX WINAPI XFont_CreateFromHFONT(HFONT hFont);
//@��ע �����Ų������GDI+����(Font), D2D��֧�ִ˽ӿ�
//@���� pFont GDI+����ָ��(Font*).
//@���� �����Ų�������.
//@���� ����_������Font()
XC_API HFONTX WINAPI XFont_CreateFromFont(void* pFont);
//@��ע ����������ļ�.
//@���� pFontFile �����ļ���.
//@���� size �����С.
//@���� style ��ʽ, @ref fontStyle_ ;  ��Ϊedit��֧���»�������, �»���,ɾ����,��Ҫ��������,����ģ��ӿ�
//@���� �����Ų�������.
//@���� ����_�������ļ�()
XC_API HFONTX WINAPI XFont_CreateFromFile(const wchar_t* pFontFile, int size=12, int style=fontStyle_regular);
//@���� data �ڴ��ַ
//@���� length ����
//@���� fontSize �����С
//@���� style ������ʽ,  @ref fontStyle_ ;  ��Ϊedit��֧���»�������, �»���,ɾ����,��Ҫ��������,����ģ��ӿ�
//@���� �����Ų�������.
//@���� ����_�������ڴ�()
XC_API HFONTX WINAPI XFont_CreateFromMem(void* data, UINT length, int fontSize=12, int style=fontStyle_regular);
//@���� id ��ԴID
//@���� pType ��Դ����. ��rc��Դ�ļ���,��Դ������,����:xcgui.rc,�ü��±��򿪿��Կ�����Դ����; ����:BITMAP, PNG, FONT; �μ�MSDN
//@���� fontSize �����С
//@���� style ������ʽ,  @ref fontStyle_ ;  ��Ϊedit��֧���»�������, �»���,ɾ����,��Ҫ��������,����ģ��ӿ�
//@���� hModule ��ָ��ģ�����, ����:DLL, EXE; ���Ϊ��, �ӵ�ǰEXE����
//@���� �����Ų�������.
//@���� ����_��������Դ()
XC_API HFONTX WINAPI XFont_CreateFromRes(int id, const wchar_t* pType, int fontSize, int style, HMODULE hModule=NULL);
//@���� pZipFileName zip�ļ���
//@���� pFileName �����ļ���
//@���� pPassword zip����
//@���� fontSize �����С
//@���� style ������ʽ,  @ref fontStyle_ ;  ��Ϊedit��֧���»�������, �»���,ɾ����,��Ҫ��������,����ģ��ӿ�
//@���� �����Ų�������
//@���� ����_������ZIP()
XC_API HFONTX WINAPI XFont_CreateFromZip(const wchar_t* pZipFileName, const wchar_t* pFileName, const wchar_t* pPassword, int fontSize, int style);
//@���� data �ڴ��ָ��
//@���� length �ڴ���С,�ֽ�Ϊ��λ
//@���� pFileName �����ļ���
//@���� pPassword zip����
//@���� fontSize �����С
//@���� style ������ʽ,  @ref fontStyle_ ;  ��Ϊedit��֧���»�������, �»���,ɾ����,��Ҫ��������,����ģ��ӿ�
//@���� �����Ų�������
//@���� ����_�������ڴ�ZIP()
XC_API HFONTX WINAPI XFont_CreateFromZipMem(void* data, int length, const wchar_t* pFileName, const wchar_t* pPassword, int fontSize, int style);
//@��ע �Ƿ��Զ�����.
//@���� hFontX ������.
//@���� bEnable �Ƿ�����.
//@���� ����_�����Զ�����()
XC_API void WINAPI XFont_EnableAutoDestroy(HFONTX hFontX, BOOL bEnable);
//@��ע ����edit����ʹ��, ��Ϊedit��֧���»�������, ������Ҫ��������
//@���� hFontX ������
//@���� bUnderline �Ƿ������»���
//@���� bStrikeout �Ƿ�����ɾ����
//@���� ����_���»���()
XC_API void WINAPI XFont_SetUnderlineEdit(HFONTX hFontX, BOOL bUnderline, BOOL bStrikeout);
//@��ע ����edit����ʹ��, ��Ϊedit��֧���»�������, ������Ҫ��������
//@���� hFontX ������
//@���� bUnderline ���շ���ֵ, �Ƿ������»���
//@���� bStrikeout ���շ���ֵ, �Ƿ�����ɾ����
//@���� ����_ȡ�»���()
XC_API void WINAPI XFont_GetUnderlineEdit(HFONTX hFontX, BOOL* bUnderline, BOOL* bStrikeout);
//@��ע ��ȡ����,����GDI+ Fontָ��.
//@���� hFontX ������.
//@���� ����GDI+ Fontָ��.
//@���� ����_ȡFont()
XC_API void* WINAPI XFont_GetFont(HFONTX hFontX);
//@��ע ��ȡ������Ϣ.
//@���� hFontX ������.
//@���� pInfo ���շ��ص�������Ϣ.
//@���� ����_ȡ��Ϣ()
XC_API void WINAPI XFont_GetFontInfo(HFONTX hFontX, font_info_* pInfo);
//@��ע ��ȡ����LOGFONTW
//@���� hFontX ������
//@���� hdc hdc���
//@���� pOut ���շ�����Ϣ
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ����_ȡLOGFONTW()
XC_API BOOL WINAPI XFont_GetLOGFONTW(HFONTX hFontX, HDC hdc, LOGFONTW* pOut);
//@��ע ǿ�������Ų�����,����ʹ��, ����ʹ�� @ref XFont_Release() �ͷ�.
//@���� hFontX ������.
//@���� ����_����()
XC_API void WINAPI XFont_Destroy(HFONTX hFontX);
//@��ע �������ü���.
//@���� hFontX ������.
//@���� ����_�������ü���()
XC_API void WINAPI XFont_AddRef(HFONTX hFontX);
//@��ע ��ȡ���ü���.
//@���� hFontX ������.
//@���� �������ü���.
//@���� ����_ȡ���ü���()
XC_API int WINAPI XFont_GetRefCount(HFONTX hFontX);
//@��ע �ͷ����ü���,�����ü���Ϊ0ʱ,�Զ�����.
//@���� hFontX ������.
//@���� ����_�ͷ����ü���()
XC_API void WINAPI XFont_Release(HFONTX hFontX);
//@����}
//@����{  ͼƬԴ

//@��ע ����ͼƬ���ļ�.
//@���� pFileName ͼƬ�ļ�.
//@���� ͼƬ���.
//@���� ͼƬԴ_���ش��ļ�()
XC_API HIMAGE WINAPI XImgSrc_LoadFile(const wchar_t* pFileName);
//@��ע ����ͼƬ,ָ����λ�ü���С.
//@���� pFileName ͼƬ�ļ�.
//@���� x ����.
//@���� y ����.
//@���� cx ���.
//@���� cy �߶�.
//@���� ͼƬ���.
//@���� ͼƬԴ_���ش��ļ�ָ������()
XC_API HIMAGE WINAPI XImgSrc_LoadFileRect(const wchar_t* pFileName, int x, int y, int cx, int cy);
//@��ע ����ͼƬ����Դ.
//@���� id ��ԴID.
//@���� pType ��Դ����, ��rc��Դ�ļ���,��Դ������, ����:xcgui.rc, �ü��±��򿪿��Կ�����Դ����; ����:BITMAP, PNG; �μ�MSDN
//@���� hModule ��ָ��ģ�����, ���Ϊ�մӵ�ǰEXE����
//@���� ͼƬ���.
//@���� ͼƬԴ_���ش���Դ()
XC_API HIMAGE WINAPI XImgSrc_LoadRes(int id, const wchar_t* pType, HMODULE hModule);
//@��ע ����ͼƬ��ZIPѹ����.
//@���� pZipFileName ZIPѹ�����ļ���.
//@���� pFileName ͼƬ�ļ���.
//@���� pPassword ZIPѹ��������.
//@���� ͼƬ���.
//@���� ͼƬԴ_���ش�ZIP()
XC_API HIMAGE WINAPI XImgSrc_LoadZip(const wchar_t* pZipFileName, const wchar_t* pFileName, const wchar_t* pPassword=NULL);
//@��ע ����ZIPͼƬ,ָ����λ�ü���С.
//@���� pZipFileName ZIP�ļ�.
//@���� pFileName ͼƬ����
//@���� pPassword ����
//@���� x ����.
//@���� y ����.
//@���� cx ���.
//@���� cy �߶�.
//@���� ͼƬ���.
//@���� ͼƬԴ_���ش�ZIPָ������()
XC_API HIMAGE WINAPI XImgSrc_LoadZipRect(const wchar_t* pZipFileName, const wchar_t* pFileName, const wchar_t* pPassword, int x, int y, int cx, int cy);
//@���� data �ڴ��ָ��
//@���� length �ڴ���С,�ֽ�Ϊ��λ
//@���� pFileName ͼƬ����
//@���� pPassword zipѹ��������
//@���� ͼƬ���
//@���� ͼƬԴ_���ش��ڴ�ZIP()
XC_API HIMAGE WINAPI XImgSrc_LoadZipMem(const void* data, int length, const wchar_t* pFileName, const wchar_t* pPassword=NULL);
//@��ע ������ͼƬ,ָ����λ�ü���С.
//@���� pBuffer ͼƬ������
//@���� nSize ͼƬ��������С
//@���� ͼƬ���.
//@���� ͼƬԴ_���ش��ڴ�()
XC_API HIMAGE WINAPI XImgSrc_LoadMemory(const void* pBuffer, int nSize);
//@��ע ������ͼƬ,ָ����λ�ü���С.
//@���� pBuffer ͼƬ������
//@���� nSize ͼƬ��������С
//@���� x ����.
//@���� y ����.
//@���� cx ���.
//@���� cy �߶�.
//@���� ͼƬ���.
//@���� ͼƬԴ_���ش��ڴ�ָ������()
XC_API HIMAGE WINAPI XImgSrc_LoadMemoryRect(const void* pBuffer, int nSize, int x, int y, int cx, int cy);
//@��ע ����ͼƬ��GDI+��Image����.
//@���� pImage GDIͼƬ����ָ�� Bitmap*.
//@���� �ɹ������Ų�ͼƬ���,ʧ�ܷ���FALSE.
//@���� ͼƬԴ_���ش�Image()
XC_API HIMAGE WINAPI XImgSrc_LoadFromImage(const void* pImage);
//@��ע �����ļ�ͼ��,��һ��EXE�ļ���DLL�ļ���ͼ���ļ�;����:*.exe�ļ���ͼ��.
//@���� pFileName �ļ���.
//@���� �ɹ������Ų�ͼƬ���,ʧ�ܷ���FALSE.
//@���� ͼƬԴ_���ش�ģ��()
XC_API HIMAGE WINAPI XImgSrc_LoadFromExtractIcon(const wchar_t* pFileName);
//@��ע ����һ���Ų�ͼƬ���,��һ�����е�ͼ����HICON.
//@���� hIcon ͼ����,����㲻ʹ�ÿ����ͷ� DestroyIcon().
//@���� �ɹ������Ų�ͼƬ���,ʧ�ܷ���FALSE.
//@���� ͼƬԴ_���ش�HICON()
XC_API HIMAGE WINAPI XImgSrc_LoadFromHICON(HICON hIcon);
//@��ע ����һ���Ų�ͼƬ���,��һ�����е�λͼ���HBITMAP.
//@���� hBitmap λͼ���,����㲻ʹ�ÿ����ͷ� DeleteObject().
//@���� �ɹ������Ų�ͼƬ���,ʧ�ܷ���FALSE.
//@���� ͼƬԴ_���ش�HBITMAP()
XC_API HIMAGE WINAPI XImgSrc_LoadFromHBITMAP(HBITMAP hBitmap);
//@��ע ���û�ر��Զ�����,����UIԪ�ع���ʱ��Ч
//@���� hImage ͼƬ���.
//@���� bEnable �����Զ�����TRUE,�ر��Զ�����FALSE.
//@���� ͼƬԴ_�����Զ�����()
XC_API void WINAPI XImgSrc_EnableAutoDestroy(HIMAGE hImage, BOOL bEnable);
//@��ע ��ȡͼƬ���.
//@���� hImage ͼƬ���.
//@���� ͼƬ���.
//@���� ͼƬԴ_ȡ���()
XC_API int WINAPI XImgSrc_GetWidth(HIMAGE hImage);
//@��ע ��ȡͼƬ�߶�.
//@���� hImage ͼƬ���.
//@���� ͼƬ�߶�.
//@���� ͼƬԴ_ȡ�߶�()
XC_API int WINAPI XImgSrc_GetHeight(HIMAGE hImage);
//@��ע ��ȡͼƬ�ļ���
//@���� hImage ͼƬ���
//@���� �����ļ���
//@���� ͼƬԴ_ȡ�ļ���()
XC_API const wchar_t* WINAPI XImgSrc_GetFile(HIMAGE hImage);
//@��ע �������ü���.
//@���� hImage ͼƬ���.
//@���� ͼƬԴ_�������ü���()
XC_API void WINAPI XImgSrc_AddRef(HIMAGE hImage);
//@��ע �ͷ����ü���,�����ü���Ϊ0ʱ,�Զ�����.
//@���� hImage ͼƬ���.
//@���� ͼƬԴ_�ͷ����ü���()
XC_API void WINAPI XImgSrc_Release(HIMAGE hImage);
//@��ע ��ȡ���ü���.
//@���� hImage ͼƬ���.
//@���� �������ü���.
//@���� ͼƬԴ_ȡ���ü���()
XC_API int WINAPI XImgSrc_GetRefCount(HIMAGE hImage);
//@��ע ǿ������ͼƬ, ����ʹ��, ����ʹ�� @ref XImgSrc_Release() �ͷ�.
//@���� hImage ͼƬ���.
//@���� ͼƬԴ_����()
XC_API void WINAPI XImgSrc_Destroy(HIMAGE hImage);
//@����}
//@����{  ͼƬ

//@���� hImageSrc ͼƬԴ���
//@���� ����ͼƬ���
//@���� ͼƬ_���ش�ͼƬԴ()
XC_API HIMAGE WINAPI XImage_LoadSrc(HIMAGE hImageSrc);
//@��ע ����ͼƬ���ļ�; �����SVG�ļ�, �Զ�����SVG�ļ�����ͼƬ���
//@���� pFileName ͼƬ�ļ�.
//@���� ͼƬ���.
//@���� ͼƬ_���ش��ļ�()
XC_API HIMAGE WINAPI XImage_LoadFile(const wchar_t* pFileName);
//@��ע ����ͼƬ���ļ�,����ӦͼƬ.
//@���� pFileName ͼƬ�ļ�.
//@���� leftSize ����.
//@���� topSize ����.
//@���� rightSize ����.
//@���� bottomSize ����.
//@���� ͼƬ���.
//@���� ͼƬ_���ش��ļ�����Ӧ()
XC_API HIMAGE WINAPI XImage_LoadFileAdaptive(const wchar_t* pFileName, int leftSize, int topSize, int rightSize, int bottomSize);
//@��ע ����ͼƬ,ָ����λ�ü���С.
//@���� pFileName ͼƬ�ļ�.
//@���� x ����.
//@���� y ����.
//@���� cx ���.
//@���� cy �߶�.
//@���� ͼƬ���.
//@���� ͼƬ_���ش��ļ�ָ������()
XC_API HIMAGE WINAPI XImage_LoadFileRect(const wchar_t* pFileName, int x, int y, int cx, int cy);
//@��ע ����ͼƬ����Դ,����ӦͼƬ.
//@���� id ��ԴID.
//@���� pType ��Դ����. ��rc��Դ�ļ���,��Դ������, ����:xcgui.rc, �ü��±��򿪿��Կ�����Դ����; ����:BITMAP, PNG; �μ�MSDN
//@���� leftSize ����.
//@���� topSize ����.
//@���� rightSize ����.
//@���� bottomSize ����.
//@���� hModule ��ָ��ģ�����, ����:DLL, EXE; ���Ϊ��, �ӵ�ǰEXE����
//@���� ͼƬ���.
//@���� ͼƬ_���ش���Դ����Ӧ()
XC_API HIMAGE WINAPI XImage_LoadResAdaptive(int id, const wchar_t* pType, int leftSize, int topSize, int rightSize, int bottomSize, HMODULE hModule=NULL);
//@��ע ����ͼƬ����Դ. RC��Դ���ͱ���Ϊ:"RT_RCDATA"
//@���� id ��ԴID.
//@���� pType ��Դ����. ��rc��Դ�ļ���,��Դ������, ����:xcgui.rc, �ü��±��򿪿��Կ�����Դ����; ����:BITMAP, PNG; �μ�MSDN
//@���� hModule ��ָ��ģ�����, ����:DLL, EXE; ���Ϊ��, �ӵ�ǰEXE����
//@���� ͼƬ���.
//@���� ͼƬ_���ش���Դ()
XC_API HIMAGE WINAPI XImage_LoadRes(int id, const wchar_t* pType, HMODULE hModule=NULL);
//@��ע ����ͼƬ��ZIPѹ����.
//@���� pZipFileName ZIPѹ�����ļ���.
//@���� pFileName ͼƬ�ļ���.
//@���� pPassword ZIPѹ��������.
//@���� ͼƬ���.
//@���� ͼƬ_���ش�ZIP()
XC_API HIMAGE WINAPI XImage_LoadZip(const wchar_t* pZipFileName, const wchar_t* pFileName, const wchar_t* pPassword=NULL);
//@��ע ����ͼƬ��ZIPѹ����,����ӦͼƬ.
//@���� pZipFileName ZIPѹ�����ļ���.
//@���� pFileName ͼƬ�ļ���.
//@���� pPassword ZIPѹ��������,���û����NULL.
//@���� x1 ����.
//@���� x2 ����.
//@���� y1 ����.
//@���� y2 ����.
//@���� ͼƬ���.
//@���� ͼƬ_���ش�ZIP����Ӧ()
XC_API HIMAGE WINAPI XImage_LoadZipAdaptive(const wchar_t* pZipFileName, const wchar_t* pFileName, const wchar_t* pPassword, int x1, int x2, int y1, int y2);
//@��ע ����ZIPͼƬ,ָ����λ�ü���С.
//@���� pZipFileName ZIP�ļ�.
//@���� pFileName ͼƬ����
//@���� pPassword ����
//@���� x ����.
//@���� y ����.
//@���� cx ���.
//@���� cy �߶�.
//@���� ͼƬ���.
//@���� ͼƬ_���ش�ZIPָ������()
XC_API HIMAGE WINAPI XImage_LoadZipRect(const wchar_t* pZipFileName, const wchar_t* pFileName, const wchar_t* pPassword, int x, int y, int cx, int cy);
//@���� data �ڴ��ָ��
//@���� length �ڴ���С,�ֽ�Ϊ��λ
//@���� pFileName ͼƬ����
//@���� pPassword zipѹ��������
//@���� ͼƬ���
//@���� ͼƬ_���ش��ڴ�ZIP()
XC_API HIMAGE WINAPI XImage_LoadZipMem(const void* data, int length, const wchar_t* pFileName, const wchar_t* pPassword=NULL);
//@��ע RC��Դ���ͱ���Ϊ:"RT_RCDATA"
//@���� id RC��ԴID
//@���� pFileName ͼƬ����
//@���� pPassword zipѹ��������
//@���� hModule ģ����
//@���� ͼƬ���
//@���� ͼƬ_���ش���ԴZIP()
XC_API HIMAGE WINAPI XImage_LoadZipRes(int id, const wchar_t* pFileName, const wchar_t* pPassword=NULL, HMODULE hModule=NULL);
//@��ע ������ͼƬ,ָ����λ�ü���С.
//@���� pBuffer ͼƬ������
//@���� nSize ͼƬ��������С
//@���� ͼƬ���.
//@���� ͼƬ_���ش��ڴ�()
XC_API HIMAGE WINAPI XImage_LoadMemory(const void* pBuffer, int nSize);
//@��ע ������ͼƬ,ָ����λ�ü���С.
//@���� pBuffer ͼƬ������
//@���� nSize ͼƬ��������С
//@���� x ����.
//@���� y ����.
//@���� cx ���.
//@���� cy �߶�.
//@���� ͼƬ���.
//@���� ͼƬ_���ش��ڴ�ָ������()
XC_API HIMAGE WINAPI XImage_LoadMemoryRect(const void* pBuffer, int nSize, int x, int y, int cx, int cy);
//@��ע ������ͼƬѹ����,����ӦͼƬ(�Ź���).
//@���� pBuffer ͼƬ������
//@���� nSize ͼƬ��������С
//@���� leftSize ����.
//@���� topSize ����.
//@���� rightSize ����.
//@���� bottomSize ����.
//@���� ͼƬ���
//@���� ͼƬ_���ش��ڴ�����Ӧ()
XC_API HIMAGE WINAPI XImage_LoadMemoryAdaptive(const void* pBuffer, int nSize, int leftSize, int topSize, int rightSize, int bottomSize);
//@��ע ����ͼƬ��GDI+��Image����.
//@���� pImage GDIͼƬ����ָ�� Image*.
//@���� ͼƬ���
//@���� ͼƬ_���ش�Image()
XC_API HIMAGE WINAPI XImage_LoadFromImage(const void* pImage);
//@��ע �����ļ�ͼ��,��һ��EXE�ļ���DLL�ļ���ͼ���ļ�;����:*.exe�ļ���ͼ��.
//@���� pFileName �ļ���.
//@���� ͼƬ���
//@���� ͼƬ_�����ļ�ͼ��()
XC_API HIMAGE WINAPI XImage_LoadFromExtractIcon(const wchar_t* pFileName);
//@��ע ����һ���Ų�ͼƬ���,��һ�����е�ͼ����HICON.
//@���� hIcon ͼ����,����㲻ʹ�ÿ����ͷ� DestroyIcon().
//@���� ͼƬ���
//@���� ͼƬ_���ش�HICON()
XC_API HIMAGE WINAPI XImage_LoadFromHICON(HICON hIcon);
//@��ע ����һ���Ų�ͼƬ���,��һ�����е�λͼ���HBITMAP.
//@���� hBitmap λͼ���,����㲻ʹ�ÿ����ͷ� DeleteObject().
//@���� ͼƬ���
//@���� ͼƬ_���ش�HBITMAP()
XC_API HIMAGE WINAPI XImage_LoadFromHBITMAP(HBITMAP hBitmap);
//@��ע �Ƿ�Ϊ����ͼƬ���
//@���� hImage ͼƬ���.
//@���� �Ƿ�����.
//@���� ͼƬ_�Ƿ�����()
XC_API BOOL WINAPI XImage_IsStretch(HIMAGE hImage);
//@��ע �Ƿ�Ϊ����ӦͼƬ���
//@���� hImage ͼƬ���.
//@���� �Ƿ�����Ӧ
//@���� ͼƬ_�Ƿ�����Ӧ()
XC_API BOOL WINAPI XImage_IsAdaptive(HIMAGE hImage);
//@��ע �Ƿ�Ϊƽ��ͼƬ
//@���� hImage ͼƬ���.
//@���� �Ƿ�ƽ��
//@���� ͼƬ_�Ƿ�ƽ��()
XC_API BOOL WINAPI XImage_IsTile(HIMAGE hImage);
//@���� hSvg SVG���
//@���� ͼƬ���
//@���� ͼƬ_���ش�SVG()
XC_API HIMAGE WINAPI XImage_LoadSvg(HSVG hSvg);
//@��ע UTF8�ļ�
//@���� pFileName �ļ���
//@���� ͼƬ���
//@���� ͼƬ_���ش�SVG�ļ�()
XC_API HIMAGE WINAPI XImage_LoadSvgFile(const wchar_t* pFileName);
//@��ע ���ֽ��ַ���ANSI
//@���� pString �ַ���ָ��
//@���� ͼƬ���
//@���� ͼƬ_���ش�SVG�ַ���()
XC_API HIMAGE WINAPI XImage_LoadSvgString(const char* pString);
//@��ע UNICODE�ַ���
//@���� pString �ַ���ָ��
//@���� ͼƬ���
//@���� ͼƬ_���ش�SVG�ַ���W()
XC_API HIMAGE WINAPI XImage_LoadSvgStringW(const wchar_t* pString);
//@��ע UTF8�ַ���
//@���� pString �ַ���ָ��
//@���� ͼƬ���
//@���� ͼƬ_���ش�SVG�ַ���UTF8()
XC_API HIMAGE WINAPI XImage_LoadSvgStringUtf8(const char* pString);
//@���� hImage ͼƬ���
//@���� SVG���
//@���� ͼƬ_ȡSVG()
XC_API HSVG WINAPI XImage_GetSvg(HIMAGE hImage);
//@��ע ����ͼƬ��������
//@���� hImage ͼƬ���.
//@���� nType ͼƬ��������.
//@���� ����ɹ�����TRUE,�����෴.
//@���� ͼƬ_�û�������()
XC_API BOOL WINAPI XImage_SetDrawType(HIMAGE hImage, image_draw_type_ nType);
//@��ע ����ͼƬ����Ӧ(�Ź���)
//@���� hImage ͼƬ���.
//@���� leftSize ����.
//@���� topSize ����.
//@���� rightSize ����.
//@���� bottomSize ����.
//@���� ����ɹ�����TRUE,�����෴.
//@���� ͼƬ_�û�����������Ӧ()
XC_API BOOL WINAPI XImage_SetDrawTypeAdaptive(HIMAGE hImage, int leftSize, int topSize, int rightSize, int bottomSize);
//@��ע ָ��ͼƬ͸����ɫ. ��֧��GDI+ģʽ
//@���� hImage ͼƬ���.
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� ͼƬ_��͸��ɫ()
XC_API void WINAPI XImage_SetTranColor(HIMAGE hImage, COLORREF color);
//@��ע ָ��ͼƬ͸����ɫ��͸����.��֧��GDI+ģʽ
//@���� hImage ͼƬ���.
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� tranColor ͸��ɫ��͸����.
//@���� ͼƬ_��͸��ɫ��չ()
XC_API void WINAPI XImage_SetTranColorEx(HIMAGE hImage, COLORREF color, BYTE tranColor);
//@��ע ������ת�Ƕ�.
//@���� hImage ͼƬ���.
//@���� fAngle ѡ��Ƕ�.
//@���� ������ǰ�Ƕ�.
//@���� ͼƬ_����ת�Ƕ�()
XC_API float WINAPI XImage_SetRotateAngle(HIMAGE hImage, float fAngle);
//@���� hImage ͼƬ���
//@���� nCount �ȷ�����
//@���� iIndex ����
//@���� ͼƬ_�õȷ�()
XC_API void WINAPI XImage_SetSplitEqual(HIMAGE hImage, int nCount, int iIndex);
//@��ע �����������Ժ���Ч,ֵ����0��Ч, @ref XImage_SetDrawType() ����: ����, �Ź���
//@���� hImage ͼƬ���
//@���� width ���
//@���� height �߶�
//@���� ͼƬ_�����Ŵ�С()
XC_API void WINAPI XImage_SetScaleSize(HIMAGE hImage, int width, int height);
//@��ע ���û�ر�ͼƬ͸��ɫ.��֧��GDI+ģʽ
//@���� hImage ͼƬ���.
//@���� bEnable ����TRUE,�ر�FALSE.
//@���� ͼƬ_����͸��ɫ()
XC_API void WINAPI XImage_EnableTranColor(HIMAGE hImage, BOOL bEnable);
//@��ע ���û�ر��Զ�����,����UIԪ�ع���ʱ��Ч
//@���� hImage ͼƬ���.
//@���� bEnable �����Զ�����TRUE,�ر��Զ�����FALSE.
//@���� ͼƬ_�����Զ�����()
XC_API void WINAPI XImage_EnableAutoDestroy(HIMAGE hImage, BOOL bEnable);
//@��ע ���û�ر�ͼƬ������ʾ��Ĭ������ͼƬ��Ч��
//@���� hImage ͼƬ���.
//@���� bCenter �Ƿ������ʾ.
//@���� ͼƬ_���þ���()
XC_API void WINAPI XImage_EnableCenter(HIMAGE hImage, BOOL bCenter);
//@��ע �ж�ͼƬ�Ƿ������ʾ
//@���� hImage ͼƬ���.
//@���� ���������ʾ����TRUE�������෴.
//@���� ͼƬ_�Ƿ����()
XC_API BOOL WINAPI XImage_IsCenter(HIMAGE hImage);
//@��ע ��ȡͼƬ��������
//@���� hImage ͼƬ���.
//@���� ͼƬ��������.
//@���� ͼƬ_ȡ��������()
XC_API image_draw_type_ WINAPI XImage_GetDrawType(HIMAGE hImage);
//@��ע ��ȡͼƬ���.
//@���� hImage ͼƬ���.
//@���� ͼƬ���.
//@���� ͼƬ_ȡ���()
XC_API int WINAPI XImage_GetWidth(HIMAGE hImage);
//@��ע ��ȡͼƬ�߶�.
//@���� hImage ͼƬ���.
//@���� ͼƬ�߶�.
//@���� ͼƬ_ȡ�߶�()
XC_API int WINAPI XImage_GetHeight(HIMAGE hImage);
//@���� hImage ͼƬ���
//@���� ����ͼƬԴ���
//@���� ͼƬ_ȡͼƬԴ()
XC_API HIMAGE WINAPI XImage_GetImageSrc(HIMAGE hImage);
//@��ע �������ü���.
//@���� hImage ͼƬ���.
//@���� ͼƬ_�������ü���()
XC_API void WINAPI XImage_AddRef(HIMAGE hImage);
//@��ע �ͷ����ü���,�����ü���Ϊ0ʱ,�Զ�����.
//@���� hImage ͼƬ���.
//@���� ͼƬ_�ͷ����ü���()
XC_API void WINAPI XImage_Release(HIMAGE hImage);
//@��ע ��ȡ���ü���.
//@���� hImage ͼƬ���.
//@���� �������ü���.
//@���� ͼƬ_ȡ���ü���()
XC_API int WINAPI XImage_GetRefCount(HIMAGE hImage);
//@��ע ǿ������ͼƬ, ����ʹ��, ����ʹ�� @ref XImage_Release() �ͷ�.
//@���� hImage ͼƬ���.
//@���� ͼƬ_����()
XC_API void WINAPI XImage_Destroy(HIMAGE hImage);
//@��ע ��ͼƬ���ֽڼ�
//@���� �ֽڼ�, ͼƬ�ڴ�����
//@���� ͼƬ���
//@���� ͼƬ_���ش��ֽڼ�(�ֽڼ�)
static HIMAGE XImage_LoadBytes(CXBytes& bytes){
	return XImage_LoadMemory((void*)bytes.getPtr(), bytes.getSize());
}
//@����}
//@����{  ������Ч

//@��ע ���������붯��ϵͳ������
//@���� hAnimationEx �������л򶯻�����
//@���� hRedrawObjectUI ������UIʱ�ػ��UI��;  UI������: ���ھ��,Ԫ�ؾ��,��״���,SVG���
//@���� ����_����()
XC_API void WINAPI XAnima_Run(HXCGUI hAnimationEx, HXCGUI hRedrawObjectUI);
//@��ע �������Ӷ���ϵͳ���Ƴ�,�����Զ�����(��������Զ�����)
//@���� hAnimationEx �������л򶯻�����
//@���� bEnd �Ƿ�����ִ�е��յ�
//@���� ����_�ͷ�()
XC_API BOOL WINAPI XAnima_Release(HXCGUI hAnimationEx, BOOL bEnd=TRUE);
//@��ע �Ӷ���ϵͳ���Ƴ���ָ��UI������������ж���, �����Զ�����(��������Զ�����)
//@���� hObjectUI ָ��UI������
//@���� bEnd �Ƿ�����ִ�е��յ�
//@���� �����ͷŶ�������
//@���� ����_�ͷ���չ()
XC_API int WINAPI XAnima_ReleaseEx(HXCGUI hObjectUI, BOOL bEnd);
//@��ע ��˳��ִ�еĶ����б�
//@���� hObjectUI �󶨵�UI����,  UI������: ���ھ��,Ԫ�ؾ��,��״���,SVG���
//@���� nLoopCount ����ѭ������, 0:����ѭ��
//@���� ���ؾ��
//@���� ����_������������()
XC_API HXCGUI WINAPI XAnima_Create(HXCGUI hObjectUI, int nLoopCount=1);
//@��ע �ƶ���Ŀ��λ��, Ĭ����UI�������ĵ�Ϊ������ʽ,������������λ
//@���� hSequence �������о��
//@���� duration ����ʱ��
//@���� x �յ�λ��X(�������Ͻ�����)
//@���� y �յ�λ��Y(�������Ͻ�����)
//@���� nLoopCount ����ѭ������, 0:����ѭ��
//@���� ease_flag ������ʶ @ref ease_flag_
//@���� bGoBack �Ƿ񷵻�; �����ú�:���������,  ���->�յ�->���
//@���� ���ض�������
//@���� ����_�ƶ�()
XC_API HXCGUI WINAPI XAnima_Move(HXCGUI hSequence, UINT duration, float x, float y, int nLoopCount=1, int ease_flag=0, BOOL bGoBack=FALSE);
//@��ע ��ָ��λ���ƶ���Ŀ��λ��, Ĭ����UI�������ĵ�Ϊ������ʽ,������������λ
//@���� hSequence �������о��
//@���� duration ����ʱ��
//@���� from_x ���λ��X(�������Ͻ�����)
//@���� from_y ���λ��Y(�������Ͻ�����)
//@���� to_x �յ�λ��X(�������Ͻ�����)
//@���� to_y �յ�λ��Y(�������Ͻ�����)
//@���� nLoopCount ����ѭ������, 0:����ѭ��
//@���� ease_flag ������ʶ @ref ease_flag_
//@���� bGoBack �Ƿ񷵻�; �����ú�:���������,  ���->�յ�->���
//@���� ���ض�������
//@���� ����_�ƶ���չ()
XC_API HXCGUI WINAPI XAnima_MoveEx(HXCGUI hSequence, UINT duration, float from_x, float from_y, float to_x, float to_y, int nLoopCount=1, int ease_flag=0, BOOL bGoBack=FALSE);
//@��ע ��ת�Ƕ�֧�ָ���ֵ, ��Ϊ�������Կ��Ʒ�����ת
//@���� hSequence �������о��
//@���� duration ����ʱ��
//@���� angle �Ƕ�
//@���� nLoopCount ����ѭ������, 0:����ѭ��
//@���� ease_flag ������ʶ @ref ease_flag_
//@���� bGoBack �Ƿ񷵻�; �����ú�:���������,  ���->�յ�->���
//@���� ���ض�������
//@���� ����_��ת()
XC_API HXCGUI WINAPI XAnima_Rotate(HXCGUI hSequence, UINT duration, float angle, int nLoopCount=1, int ease_flag=0, BOOL bGoBack=FALSE);
//@��ע ָ�������յ�
//@���� hSequence �������о��
//@���� duration ����ʱ��
//@���� from ���Ƕ�
//@���� to �յ�Ƕ�
//@���� nLoopCount ����ѭ������, 0:����ѭ��
//@���� ease_flag ������ʶ @ref ease_flag_
//@���� bGoBack �Ƿ񷵻�; �����ú�:���������,  ���->�յ�->���
//@���� ���ض�������
//@���� ����_��ת��չ()
XC_API HXCGUI WINAPI XAnima_RotateEx(HXCGUI hSequence, UINT duration, float from, float to, int nLoopCount=1, int ease_flag=0, BOOL bGoBack=FALSE);
//@��ע ���Ŷ���   Ĭ��������Ϊ��������
//@���� hSequence �������о��
//@���� duration ����ʱ��
//@���� scaleX X�����ű���
//@���� scaleY Y�����ű���
//@���� nLoopCount ����ѭ������, 0:����ѭ��
//@���� ease_flag ������ʶ  @ref ease_flag_
//@���� bGoBack �Ƿ񷵻�; �����ú�:���������,  ���->�յ�->���
//@���� ���ض�������
//@���� ����_����()
XC_API HXCGUI WINAPI XAnima_Scale(HXCGUI hSequence, UINT duration, float scaleX, float scaleY, int nLoopCount=0, int ease_flag=0, BOOL bGoBack=TRUE);
//@��ע �޸�UI�����С,Ĭ����������
//@���� hSequence �������о��
//@���� duration ����ʱ��
//@���� width ���
//@���� height �߶�
//@���� nLoopCount ����ѭ������, 0:����ѭ��
//@���� ease_flag ������ʶ @ref ease_flag_
//@���� bGoBack �Ƿ񷵻�; �����ú�:���������,  ���->�յ�->���
//@���� ���ض�������
//@���� ����_���Ŵ�С()
XC_API HXCGUI WINAPI XAnima_ScaleSize(HXCGUI hSequence, UINT duration, float width, float height, int nLoopCount=1, int ease_flag=0, BOOL bGoBack=FALSE);
//@���� hSequence �������о��
//@���� duration ����ʱ��
//@���� alpha ͸����
//@���� nLoopCount ����ѭ������, 0:����ѭ��
//@���� ease_flag ������ʶ @ref ease_flag_
//@���� bGoBack �Ƿ񷵻�; �����ú�:���������,  ���->�յ�->���
//@���� ���ض�������
//@���� ����_͸����()
XC_API HXCGUI WINAPI XAnima_Alpha(HXCGUI hSequence, UINT duration, BYTE alpha, int nLoopCount=0, int ease_flag=0, BOOL bGoBack=FALSE);
//@��ע ��ָ��͸���ȵ�Ŀ��͸����
//@���� hSequence �������о��
//@���� duration ����ʱ��
//@���� from_alpha ��ʼ͸����
//@���� to_alpha ��ֹ͸����
//@���� nLoopCount ����ѭ������, 0:����ѭ��
//@���� ease_flag ������ʶ  @ref ease_flag_
//@���� bGoBack �Ƿ񷵻�; �����ú�:���������,  ���->�յ�->���
//@���� ���ض�������
//@���� ����_͸������չ()
XC_API HXCGUI WINAPI XAnima_AlphaEx(HXCGUI hSequence, UINT duration, BYTE from_alpha, BYTE to_alpha, int nLoopCount=0, int ease_flag=0, BOOL bGoBack=FALSE);
//@���� hSequence �������о��
//@���� duration ����ʱ��
//@���� color ��ɫ
//@���� nLoopCount ����ѭ������, 0:����ѭ��
//@���� ease_flag ������ʶ @ref ease_flag_
//@���� bGoBack �Ƿ񷵻�; �����ú�:���������,  ���->�յ�->���
//@���� ���ض�������
//@���� ����_��ɫ()
XC_API HXCGUI WINAPI XAnima_Color(HXCGUI hSequence, UINT duration, COLORREF color, int nLoopCount=0, int ease_flag=0, BOOL bGoBack=FALSE);
//@��ע ��ָ����ɫ��Ŀ����ɫ
//@���� hSequence �������о��
//@���� duration ����ʱ��
//@���� from �����ɫ
//@���� to �յ���ɫ
//@���� nLoopCount ����ѭ������, 0:����ѭ��
//@���� ease_flag ������ʶ @ref ease_flag_
//@���� bGoBack �Ƿ񷵻�; �����ú�:���������,  ���->�յ�->���
//@���� ���ض�������
//@���� ����_��ɫ��չ()
XC_API HXCGUI WINAPI XAnima_ColorEx(HXCGUI hSequence, UINT duration, COLORREF from, COLORREF to, int nLoopCount=0, int ease_flag=0, BOOL bGoBack=FALSE);
//@��ע �޸Ĳ��ֿ������
//@���� hSequence �������о��
//@���� duration ����ʱ��
//@���� nType ���ֿ������
//@���� width ���ֿ��
//@���� nLoopCount ����ѭ������, 0:����ѭ��
//@���� ease_flag ������ʶ  @ref ease_flag_
//@���� bGoBack �Ƿ񷵻�; �����ú�:���������,  ���->�յ�->���
//@���� ���ض�������
//@���� ����_���ֿ��()
XC_API HXCGUI WINAPI XAnima_LayoutWidth(HXCGUI hSequence, UINT duration, layout_size_ nType, float width, int nLoopCount=1, int ease_flag=0, BOOL bGoBack=FALSE);
//@��ע �޸Ĳ��ָ߶�����
//@���� hSequence �������о��
//@���� duration ����ʱ��
//@���� nType ���ָ߶�����
//@���� height ���ָ߶�
//@���� nLoopCount ����ѭ������, 0:����ѭ��
//@���� ease_flag ������ʶ  @ref ease_flag_
//@���� bGoBack �Ƿ񷵻�; �����ú�:���������,  ���->�յ�->���
//@���� ���ض�������
//@���� ����_���ָ߶�()
XC_API HXCGUI WINAPI XAnima_LayoutHeight(HXCGUI hSequence, UINT duration, layout_size_ nType, float height, int nLoopCount=1, int ease_flag=0, BOOL bGoBack=FALSE);
//@��ע �޸Ĳ��ֿ�Ⱥ͸߶�
//@���� hSequence �������о��
//@���� duration ����ʱ��
//@���� nWidthType ���ֿ������, @ref layout_size_disable : ���ÿ�ȶ���
//@���� width ���ֿ��
//@���� nHeightType ���ָ߶�����, @ref layout_size_disable : ���ø߶ȶ���
//@���� height ���ָ߶�
//@���� nLoopCount ����ѭ������, 0:����ѭ��
//@���� ease_flag ������ʶ  @ref ease_flag_
//@���� bGoBack �Ƿ񷵻�; �����ú�:���������,  ���->�յ�->���
//@���� ���ض�������
//@���� ����_���ִ�С()
XC_API HXCGUI WINAPI XAnima_LayoutSize(HXCGUI hSequence, UINT duration, layout_size_ nWidthType, float width, layout_size_ nHeightType, float height, int nLoopCount=0, int ease_flag=0, BOOL bGoBack=TRUE);
//@���� hSequence �������о��
//@���� duration ����ʱ��
//@���� ���ض�������
//@���� ����_�ӳ�()
XC_API HXCGUI WINAPI XAnima_Delay(HXCGUI hSequence, float duration);
//@��ע ������Ϊһ���ն���, Ȼ���ڻص��ﴦ���Լ����㷨
//@���� hSequence �������о��
//@���� duration ����ʱ��
//@���� nLoopCount ����ѭ������, 0:����ѭ��
//@���� ease_flag ������ʶ  @ref ease_flag_
//@���� bGoBack �Ƿ񷵻�; �����ú�:���������,  ���->�յ�->���
//@���� ���ض�������
//@���� ����_�ӳ���չ()
XC_API HXCGUI WINAPI XAnima_DelayEx(HXCGUI hSequence, float duration, int nLoopCount=1, int ease_flag=0, BOOL bGoBack=FALSE);
//@��ע ��ʾ������UI����
//@���� hSequence �������о��
//@���� duration ����ʱ��
//@���� bShow ��ʾ������
//@���� ���ض�������
//@���� ����_��ʾ()
XC_API HXCGUI WINAPI XAnima_Show(HXCGUI hSequence, float duration, BOOL bShow);
//@���� hSequence �������о��
//@���� duration ����ʱ��
//@���� ���ض�������
//@���� ����_����UI����()
XC_API HXCGUI WINAPI XAnima_DestroyObjectUI(HXCGUI hSequence, float duration);
//@��ע TRUE:�Զ����� FALSE:�ֶ�����
//@���� hAnimationEx �������л򶯻�����
//@���� bEnable �Ƿ�����
//@���� ����_�����Զ�����()
XC_API void WINAPI XAnima_EnableAutoDestroy(HXCGUI hAnimationEx, BOOL bEnable);
//@��ע ��ȡ����������UI����
//@���� hAnimationEx �������л򶯻���򶯻�����
//@���� ����UI������
//@���� ����_ȡUI����()
XC_API HXCGUI WINAPI XAnima_GetObjectUI(HXCGUI hAnimationEx);
//@���� hAnimationEx �������л򶯻�����
//@���� callback �ص�����
//@���� ����_�ûص�()
XC_API void WINAPI XAnima_SetCallback(HXCGUI hAnimationEx, funAnimation callback);
//@���� hAnimationEx �������л򶯻�����
//@���� nUserData ������
//@���� ����_���û�����()
XC_API void WINAPI XAnima_SetUserData(HXCGUI hAnimationEx, vint nUserData);
//@���� hAnimationEx �������л򶯻�����
//@���� �����û�����
//@���� ����_ȡ�û�����()
XC_API vint WINAPI XAnima_GetUserData(HXCGUI hAnimationEx);
//@���� hAnimationEx �������л򶯻�����
//@���� ����_ֹͣ()
XC_API void WINAPI XAnima_Stop(HXCGUI hAnimationEx);
//@���� hAnimationEx �������л򶯻�����
//@���� ����_��ʼ()
XC_API void WINAPI XAnima_Start(HXCGUI hAnimationEx);
//@��ע δʵ�ֹ���
//@���� hAnimationEx �������л򶯻�����
//@���� ����_��ͣ()
XC_API void WINAPI XAnima_Pause(HXCGUI hAnimationEx);
//@����}
//@����{  ������Ч

//@��ע ����ͬ����, �����ж�������ȫ����ɺ�, ���¿�ʼ; ����������ѭ����ʱ,ֱ������������ɺ���ֹѭ��,��������޷������յ�,�޷�����ͷ������ͬ��
//@���� nLoopCount ����ѭ������, 0:����ѭ��
//@���� ���ض�������
//@���� ������_����()
XC_API HXCGUI WINAPI XAnimaGroup_Create(int nLoopCount=1);
//@��ע ������������ӵ�����
//@���� hGroup ��������
//@���� hSequence �������о��
//@���� ������_�����()
XC_API void WINAPI XAnimaGroup_AddItem(HXCGUI hGroup, HXCGUI hSequence);
//@����}
//@����{  ������Ч

//@���� hAnimationItem ��������
//@���� bEnable �Ƿ�����
//@���� ������_�����Զ�����()
XC_API void WINAPI XAnimaItem_EnableAutoDestroy(HXCGUI hAnimationItem, BOOL bEnable);
//@��ע ����������ɺ��Զ��ͷ�
//����Զ���������н��н���ʽ�ӳ�, �ڶ�������ͷ�������ʱ��(ʱ���), ����ʱ�����ʱ�Զ��ͷ�, ��������ѭ�����γ�һ��ʱ���(��Ϊ�����ʱ���������,������Զ�޷�����ʱ��)
//@���� hAnimationItem ��������
//@���� bEnable �Ƿ�����
//@���� ������_��������ͷ�()
XC_API void WINAPI XAnimaItem_EnableCompleteRelease(HXCGUI hAnimationItem, BOOL bEnable);
//@���� hAnimationItem ��������
//@���� callback �ص�����
//@���� ������_�ûص�()
XC_API void WINAPI XAnimaItem_SetCallback(HXCGUI hAnimationItem, funAnimationItem callback);
//@���� hAnimationItem ��������
//@���� nUserData �û�����
//@���� ������_���û�����()
XC_API void WINAPI XAnimaItem_SetUserData(HXCGUI hAnimationItem, vint nUserData);
//@���� hAnimationItem ��������
//@���� �����û�����
//@���� ������_ȡ�û�����()
XC_API vint WINAPI XAnimaItem_GetUserData(HXCGUI hAnimationItem);
//@����}
//@����{  ������Ч

//@��ע ������ת���ĵ�����
//@���� hAnimationRotate ������ת����
//@���� x ����X
//@���� y ����Y
//@���� bOffset TRUE:������������ĵ�ƫ��, FALSE:��������
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ������ת_������()
XC_API void WINAPI XAnimaRotate_SetCenter(HXCGUI hAnimationRotate, float x, float y, BOOL bOffset=FALSE);
//@����}
//@����{  ������Ч

//@��ע �����������, ȷ�����췽��
//@���� hAnimationScale ������������
//@���� position λ��
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ��������_������λ��()
XC_API void WINAPI XAnimaScale_SetPosition(HXCGUI hAnimationScale, position_flag_ position);
//@����}
//@����{  ������Ч

//@��ע �˽ӿڿɶ�������x���ƶ���y���ƶ�
//@���� hAnimationMove �����ƶ�����
//@���� flags @ref animation_move_x : X���ƶ�, @ref animation_move_y : Y���ƶ�, �����ʹ��, @ref animation_move_
//@���� �����ƶ�_�ñ�ʶ()
XC_API void WINAPI XAnimaMove_SetFlag(HXCGUI hAnimationMove, int flags);
//@����}
//@����{  ����ͼ��

//@��ע ��������,  ��δ��ӵ�ϵͳ����״̬�ſɵ���
//@���� ����ͼ��_����()
XC_API void WINAPI XTrayIcon_Reset();
//@��ע ��ͼ����ӵ�ϵͳ����
//@���� hWindow �������ھ��
//@���� id ����ͼ��Ψһ��ʶ��
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ����ͼ��_���()
XC_API BOOL WINAPI XTrayIcon_Add(HWINDOW hWindow, int id);
//@��ע ��ϵͳ����ɾ��
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ����ͼ��_ɾ��()
XC_API BOOL WINAPI XTrayIcon_Del();
//@��ע �޸�����ͼ��
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ����ͼ��_�޸�()
XC_API BOOL WINAPI XTrayIcon_Modify();
//@��ע ����ͼ��
//@���� hIcon ͼ����
//@���� ����ͼ��_��ͼ��()
XC_API void WINAPI XTrayIcon_SetIcon(HICON hIcon);
//@��ע ���������û�ϵͳ����
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ����ͼ��_�ý���()
XC_API BOOL WINAPI XTrayIcon_SetFocus();
//@��ע ���ù�����ʾ����
//@���� pTips ��ʾ�ı�����, ���Ȳ��ܳ���127���ַ�
//@���� ����ͼ��_����ʾ�ı�()
XC_API void WINAPI XTrayIcon_SetTips(const wchar_t* pTips);
//@��ע ���õ���������Ϣ
//@���� pTitle �������ݱ���
//@���� pText ������������
//@���� hBalloonIcon ����ͼ��
//@���� flags ��ʶ, ������Ĭ��ͼ������, ��������,  @ref trayIcon_flag_
//@���� ����ͼ��_�õ�������()
XC_API void WINAPI XTrayIcon_SetPopupBalloon(const wchar_t* pTitle, const wchar_t* pText, HICON hBalloonIcon=NULL, int flags=0);
//@��ע �����û��Զ���Ļص���Ϣ����, �����¼���, ϵͳ�ᷢ�͵�����Ϣ
//@���� user_message �û��Զ�����Ϣ, �����Ĭ�϶�����ϢΪ @ref XWM_TRAYICON
//@���� ����ͼ��_�ûص���Ϣ()
XC_API void WINAPI XTrayIcon_SetCallbackMessage(UINT user_message);
//@����}
//@����{  ȫ��API

//@��ע Unicodeת��Ansi����,
//@���� pIn ָ���ת����Unicode�ַ���ָ��.
//@���� inLen pIn�ַ�����.
//@���� pOut ָ�����ת�����Ansi�ַ���������ָ��.
//@���� outLen pOut��������С,�ֽڵ�λ.
//@���� ����ɹ�,����д����ջ������ֽ�����.
//@���� �Ų�_U2A()
XC_API int WINAPI XC_UnicodeToAnsi(const wchar_t* pIn, int inLen, char* pOut, int outLen);
//@��ע Ansiת��Unicode����,
//@���� pIn ָ���ת����Ansi�ַ���ָ��.
//@���� inLen pIn�ַ�����.
//@���� pOut ָ�����ת�����Unicode�ַ���������ָ��.
//@���� outLen pOut��������С,�ַ�wchar_t��λ.
//@���� ����ɹ�,����д����ջ������ַ�wchar_t����.
//@���� �Ų�_A2U()
XC_API int WINAPI XC_AnsiToUnicode(const char* pIn, int inLen, wchar_t* pOut, int outLen);
//@���� hWindow ���ھ��
//@���� msg
//@���� wParam
//@���� lParam
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �Ų�_���ʹ�����Ϣ()
XC_API LRESULT WINAPI XC_SendMessage(HWINDOW hWindow, UINT msg, WPARAM wParam, LPARAM lParam);
//@���� hWindow ���ھ��
//@���� msg
//@���� wParam
//@���� lParam
//@���� �Ų�_Ͷ�ݴ�����Ϣ()
XC_API BOOL WINAPI XC_PostMessage(HWINDOW hWindow, UINT msg, WPARAM wParam, LPARAM lParam);
//@��ע ����UI�߳�, ���ûص�����,�ڻص����������UI,ͨ��SendMessage()ʵ��,����ģʽ
//@���� pCall �ص����� funCallUiThread ; vint CALLBACK funCallUiThread(vint data)
//@���� data �û��Զ�������
//@���� �ص���������ֵ
//@���� �Ų�_���ý����߳�()
XC_API vint WINAPI XC_CallUiThread(funCallUiThread pCall, vint data);
//@��ע ת������, ���ؽ����󳤶� TEXT_BUFFER_SIZE = 10240.
//@���� nValue ����.
//@���� ���ؽ��.
//@���� �Ų�_�������ı�A()
XC_API const char* WINAPI XC_itoa(int nValue);
//@��ע ת������, ���ؽ����󳤶� TEXT_BUFFER_SIZE = 10240, ʹ�ù�����,����Ҫ�ͷ�.
//@���� nValue ����.
//@���� ���ؽ��.
//@���� �Ų�_�������ı�W()
XC_API const wchar_t* WINAPI XC_itow(int nValue);
//@��ע ת������, ���ؽ����󳤶� TEXT_BUFFER_SIZE = 10240, ʹ�ù�����,����Ҫ�ͷ�.
//@���� fValue ����.
//@���� ���ؽ��.
//@���� �Ų�_���������ı�A()
XC_API const char* WINAPI XC_ftoa(float fValue);
//@��ע ת������, ���ؽ����󳤶� TEXT_BUFFER_SIZE = 10240, ʹ�ù�����,����Ҫ�ͷ�.
//@���� fValue ����.
//@���� ���ؽ��.
//@���� �Ų�_���������ı�W()
XC_API const wchar_t* WINAPI XC_ftow(float fValue);
//@��ע ת������, ���ؽ����󳤶� TEXT_BUFFER_SIZE = 10240, ʹ�ù�����,����Ҫ�ͷ�.
//@���� pValue ����.
//@���� ���ؽ��.
//@���� �Ų�_A2W()
XC_API const wchar_t* WINAPI XC_atow(const char* pValue);
//@��ע ת������, ���ؽ����󳤶� TEXT_BUFFER_SIZE = 10240, ʹ�ù�����,����Ҫ�ͷ�.
//@���� pValue ����.
//@���� ���ؽ��.
//@���� �Ų�_W2A()
XC_API const char* WINAPI XC_wtoa(const wchar_t* pValue);
//@��ע ת������, ���ؽ����󳤶� TEXT_BUFFER_SIZE = 10240, ʹ�ù�����,����Ҫ�ͷ�.
//@���� pUtf8 ����.
//@���� ���ؽ��.
//@���� �Ų�_UTF8���ı�W()
XC_API const wchar_t* WINAPI XC_utf8tow(const char* pUtf8);
//@��ע ת������, ���ؽ����󳤶� TEXT_BUFFER_SIZE = 10240, ʹ�ù�����,����Ҫ�ͷ�.
//@���� pUtf8 utf8�ַ���ָ��.
//@���� length utf8�ַ�������.
//@���� ���ؽ��.
//@���� �Ų�_UTF8���ı�W��չ()
XC_API const wchar_t* WINAPI XC_utf8towEx(const char* pUtf8, int length);
//@��ע ת������, ���ؽ����󳤶�10240, ʹ�ù�����,����Ҫ�ͷ�.
//@���� pUtf8 utf8�ַ���ָ��.
//@���� ���ؽ��.
//@���� �Ų�_UTF8���ı�A()
XC_API const char* WINAPI XC_utf8toa(const char* pUtf8);
//@��ע ת������, ���ؽ����󳤶� TEXT_BUFFER_SIZE = 10240, ʹ�ù�����,����Ҫ�ͷ�.
//@���� pValue ����.
//@���� ���ؽ��.
//@���� �Ų�_�ı�A��UTF8()
XC_API const char* WINAPI XC_atoutf8(const char* pValue);
//@��ע ת������, ���ؽ����󳤶� TEXT_BUFFER_SIZE = 10240,, ʹ�ù�����,����Ҫ�ͷ�.
//@���� pValue �ַ���ָ��
//@���� ���ؽ��
//@���� �Ų�_�ı�W��UTF8()
XC_API const char* WINAPI XC_wtoutf8(const wchar_t* pValue);
//@��ע ת������, ���ؽ����󳤶� TEXT_BUFFER_SIZE = 10240, ʹ�ù�����,����Ҫ�ͷ�.
//@���� pValue �ַ���ָ��
//@���� length �ַ�������
//@���� ���ؽ��
//@���� �Ų�_�ı�W��UTF8��չ()
XC_API const char* WINAPI XC_wtoutf8Ex(const wchar_t* pValue, int length);
//@��ע ��ӡ������Ϣ���ļ�xcgui_debug.txt.
//@���� pInfo
//@���� �Ų�_���������Ϣ���ļ�()
XC_API void WINAPI XC_DebugToFileInfo(const char* pInfo);
//@��ע �ж��Ƿ�ΪԪ�ؾ��.
//@���� hEle Ԫ�ؾ��.
//@���� �ɹ�����TRUE,�����෴.
//@���� �Ų�_�Ƿ�Ԫ��()
XC_API BOOL WINAPI XC_IsHELE(HXCGUI hEle);
//@��ע �ж��Ƿ�Ϊ���ھ��.
//@���� hWindow ���ھ��.
//@���� �ɹ�����TRUE,�����෴.
//@���� �Ų�_�Ƿ񴰿�()
XC_API BOOL WINAPI XC_IsHWINDOW(HXCGUI hWindow);
//@��ע �ж��Ƿ�Ϊ��״����.
//@���� hShape ��״������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �Ų�_�Ƿ���״����()
XC_API BOOL WINAPI XC_IsShape(HXCGUI hShape);
//@��ע �жϾ���Ƿ�ӵ�и�����.
//@���� hXCGUI �Ųʾ��.
//@���� nType �������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �Ų�_�Ƿ�����������()
XC_API BOOL WINAPI XC_IsHXCGUI(HXCGUI hXCGUI, XC_OBJECT_TYPE nType);
//@��ע ͨ������HWND�����ȡHWINDOW���.
//@���� hWnd ����HWND���.
//@���� ����HWINDOW���.
//@���� �Ų�_ת��HWND��HWINDOW()
XC_API HWINDOW WINAPI XC_hWindowFromHWnd(HWND hWnd);
//@��ע ���ǰ�������ϲ㴰��
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �Ų�_�����()
XC_API BOOL WINAPI XC_SetActivateTopWindow();
//@��ע ���ö�������.
//@���� hXCGUI ������.
//@���� pName ������.
//@���� pValue ����ֵ, �������Դ, ��Ҫ����"@"����, ����: "@��Դ��"
//@���� ����ɹ�����TRUE,���򷵻�FALSE.
//@���� �Ų�_������()
XC_API BOOL WINAPI XC_SetProperty(HXCGUI hXCGUI, const wchar_t* pName, const wchar_t* pValue);
//@��ע ��ȡ��������.
//@���� hXCGUI ������.
//@���� pName ������.
//@���� ��������ֵ, ���û�з��ؿ�.
//@���� �Ų�_ȡ����()
XC_API const wchar_t* WINAPI XC_GetProperty(HXCGUI hXCGUI, const wchar_t* pName);
//@��ע ע�ᴰ������
//���Ų��˳�ʱ, ���Զ�ע������; ��������û��ע��, DLLж�غ�, ������ָ��Ĵ��ڹ��̵�ַʧЧ, �ٴ�ʹ�ô�����, ��ɳ������
//@���� pClassName ����.
//@���� ����ɹ�����TRUE,���򷵻�FALSE.
//@���� �Ų�_ע�ᴰ������()
XC_API BOOL WINAPI XC_RegisterWindowClassName(const wchar_t* pClassName);
//@��ע �ж�Ԫ���Ƿ�ӹ�����ͼԪ����չ����Ԫ��,����������ͼԪ��.
//@���� hEle Ԫ�ؾ��.
//@���� ����ɹ�����TRUE,�����෴.
//@���� �Ų�_�Ƿ������ͼ��չԪ��()
XC_API BOOL WINAPI XC_IsSViewExtend(HELE hEle);
//@��ע ��ȡ�������.
//@���� hXCGUI �Ųʶ�����.
//@���� ���ؾ������.
//@���� �Ų�_ȡ��������()
XC_API XC_OBJECT_TYPE WINAPI XC_GetObjectType(HXCGUI hXCGUI);
//@��ע ͨ��ID��ȡ������,���������ڶ���.
//@���� hWindow �������ھ��,����������κδ�����NULL.
//@���� nID IDֵ.
//@���� �ɹ����ؾ��,���򷵻�NULL.
//@���� �Ų�_ȡ�����ID()
XC_API HXCGUI WINAPI XC_GetObjectByID(HWINDOW hWindow, int nID);
//@��ע ͨ��ID���ƻ�ȡ������.
//@���� hWindow �������ھ��,����������κδ�����NULL.
//@���� pName ID����.
//@���� �ɹ����ؾ��,���򷵻�NULL.
//@���� �Ų�_ȡ�����ID����()
XC_API HXCGUI WINAPI XC_GetObjectByIDName(HWINDOW hWindow, const wchar_t* pName);
//@��ע ͨ��UID��ȡ������,���������ڶ���.
//@���� nUID UIDֵ.
//@���� �ɹ����ؾ��,���򷵻�NULL.
//@���� �Ų�_ȡ�����UID()
XC_API HXCGUI WINAPI XC_GetObjectByUID(int nUID);
//@��ע ͨ��UID���ƻ�ȡ������.
//@���� pName UID����.
//@���� �ɹ����ؾ��,���򷵻�NULL.
//@���� �Ų�_ȡ�����UID����()
XC_API HXCGUI WINAPI XC_GetObjectByUIDName(const wchar_t* pName);
//@��ע ͨ��name��ȡ������.
//@���� pName name����.
//@���� �ɹ����ؾ��,���򷵻�NULL.
//@���� �Ų�_ȡ���������()
XC_API HXCGUI WINAPI XC_GetObjectByName(const wchar_t* pName);
//@��ע ��ȡ��ǰ��ʹ�õľ��������
//@���� ���ص�ǰ��ʹ�õľ��������
//@���� �Ų�_ȡ�������()
XC_API int WINAPI XC_GetHandleCount();
//@��ע ����UI����С�ػ�Ƶ��.
//@���� nMilliseconds �ػ���Сʱ����,��λ����.
//@���� �Ų�_�û���Ƶ��()
XC_API void WINAPI XC_SetPaintFrequency(UINT nMilliseconds);
//@��ע �����ı���Ⱦ����GDI+.
//@���� nType �μ�GDI+ TextRenderingHint ����.
//@���� �Ų�_���ı��Ų�����()
XC_API void WINAPI XC_SetTextRenderingHint(int nType);
//@���� mode ��Ⱦģʽ  @ref XC_DWRITE_RENDERING_MODE
//@���� �Ų�_��D2D�ı���Ⱦģʽ()
XC_API void WINAPI XC_SetD2dTextRenderingMode(XC_DWRITE_RENDERING_MODE mode);
//@��ע ��������͸��ʱ, ��Ҫʹ�ûҶȿ���ݻ򲻿����, ��ΪClearTypeģʽ ����͸��ͨ�����������
//@���� mode 0:ʹ��ϵͳĬ�ϵĿ����ģʽ, 1:ʹ�� ClearType ����ݼ���, 2:ʹ�ûҶȿ����, 3:��ʹ�ÿ����
//@���� �Ų�_��D2D�ı������ģʽ()
XC_API void WINAPI XC_SetD2dTextAntialiasMode(int mode);
//@��ע ��Ӱ�쵽���º���: XDraw_TextOut XDraw_TextOutEx XDraw_TextOutA
//@���� bEnable �Ƿ�����
//@���� �Ų�_����GDI�����ı�()
XC_API void WINAPI XC_EnableGdiDrawText(BOOL bEnable);
//@��ע ���޸�UI���Զ������ػ溯������UI, ���簴ť: XBtn_SetText(), ���Զ����� XEle_Redraw() ����UI
//@���� bEnable �Ƿ�����
//@���� �Ų�_�����Զ��ػ�UI()
XC_API void WINAPI XC_EnableAutoRedrawUI(BOOL bEnable);
//@��ע �ж����������Ƿ��ཻ���ص�.
//@���� pRect1 ����1.
//@���� pRect2 ����2.
//@���� ������������ཻ����TRUE,�����෴.
//@���� �Ų�_�Ƿ�����ཻ()
XC_API BOOL WINAPI XC_RectInRect(RECT* pRect1, RECT* pRect2);
//@��ע ���������������.
//@���� pDest �µľ�������.
//@���� pSrc1 Դ����1.
//@���� pSrc2 Դ����2.
//@���� �Ų�_��Ͼ���()
XC_API void WINAPI XC_CombineRect(RECT* pDest, RECT* pSrc1, RECT* pSrc2);
//@��ע ��ʾ���ֶ���߽�.
//@���� bShow �Ƿ���ʾ.
//@���� �Ų�_��ʾ���ֱ߽�()
XC_API void WINAPI XC_ShowLayoutFrame(BOOL bShow);
//@��ע ��������
//@���� bShow �Ƿ���ʾ
//@���� �Ų�_��ʾ�߽�()
XC_API void WINAPI XC_ShowSvgFrame(BOOL bShow);
//@��ע ����debug�ļ�.
//@���� bEnable �Ƿ�����.
//@���� �Ų�_����debug�ļ�()
XC_API void WINAPI XC_EnableDebugFile(BOOL bEnable);
//@��ע ������Դ������.
//@���� bEnable �Ƿ�����.
//@���� �Ų�_������Դ������()
XC_API void WINAPI XC_EnableResMonitor(BOOL bEnable);
//@��ע ���ò��ֱ߿���ɫ.
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� �Ų�_�ò��ֱ߽���ɫ()
XC_API void WINAPI XC_SetLayoutFrameColor(COLORREF color);
//@��ע ���ô��󵯳�,ͨ���ýӿڿ��������������ش���ʱ��������Ϣ��ʾ��.
//@���� bEnable �Ƿ�����.
//@���� �Ų�_���ô��󵯴�()
XC_API void WINAPI XC_EnableErrorMessageBox(BOOL bEnable);
//@��ע ����DPI���ַ�ʽ: 1.����Ŀ����������DPI, 2.ʹ���嵥�ļ�, 3.���ô˺���, 4.�Լ�����DPI����,
//�ο�MSDN: https://learn.microsoft.com/zh-cn/windows/win32/hidpi/setting-the-default-dpi-awareness-for-a-process
//@���� bEnable �Ƿ�����.
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� �Ų�_����DPI()
XC_API BOOL WINAPI XC_EnableDPI(BOOL bEnable);
//@��ע Ĭ�Ͻ���; �����ú�, ��������ʱ�Զ����DPI����UI����, ����DPI�ı���Ϣ; ���ú�,��DPI�ı�,��Ҫ�ֶ����ô���DPI
//@���� bEnable �Ƿ�����
//@���� �Ų�_�����Զ�DPI()
XC_API void WINAPI XC_EnableAutoDPI(BOOL bEnable);
//@��ע ����������Զ��˳�����,����⵽�����û������Ĵ��ڶ��ر�ʱ,�Զ��˳�����;
//�ɵ��� XC_PostQuitMessage() �ֶ��˳�����
//@���� bEnable �Ƿ�����.
//@���� �Ų�_�����Զ��˳�����()
XC_API void WINAPI XC_EnableAutoExitApp(BOOL bEnable);
//@��ע �ж��Ƿ�������D2D
//@���� �������D2D����TRUE,�����෴
//@���� �Ų�_�Ƿ�����D2D()
XC_API BOOL WINAPI XC_IsEnableD2D();
//@��ע ��ȡ�ı����ƴ�С.
//@���� pString �ַ���.
//@���� length �ַ�������
//@���� hFontX ����.
//@���� pOutSize ���շ��ش�С.
//@���� �Ų�_ȡ�ı����ƴ�С()
XC_API void WINAPI XC_GetTextSize(const wchar_t* pString, int length, HFONTX hFontX, SIZE* pOutSize);
//@��ע ��ȡ�ı���ʾ��С.
//@���� pString �ַ���.
//@���� length �ַ�������
//@���� hFontX ����.
//@���� pOutSize ���շ��ش�С.
//@���� �Ų�_ȡ�ı���ʾ��С()
XC_API void WINAPI XC_GetTextShowSize(const wchar_t* pString, int length, HFONTX hFontX, SIZE* pOutSize);
//@���� pString �ַ���.
//@���� length �ַ�������
//@���� hFontX ����.
//@���� nTextAlign �ı����뷽ʽ @ref textFormatFlag_
//@���� pOutSize ���շ��ش�С.
//@���� �Ų�_ȡ�ı���ʾ��С��չ()
XC_API void WINAPI XC_GetTextShowSizeEx(const wchar_t* pString, int length, HFONTX hFontX, int nTextAlign, SIZE* pOutSize);
//@���� pString �ַ���.
//@���� length �ַ�������
//@���� hFontX ����.
//@���� nTextAlign �ı�����  @ref textFormatFlag_
//@���� width �����
//@���� pOutSize ���շ��ش�С.
//@���� �Ų�_ȡ�ı���ʾ����()
XC_API void WINAPI XC_GetTextShowRect(const wchar_t* pString, int length, HFONTX hFontX, int nTextAlign, int width, SIZE* pOutSize);
//@��ע ��ȡĬ������.
//@���� ����Ĭ��������.
//@���� �Ų�_ȡĬ������()
XC_API HFONTX WINAPI XC_GetDefaultFont();
//@��ע ����Ĭ������.
//@���� hFontX �Ų�������.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �Ų�_��Ĭ������()
XC_API void WINAPI XC_SetDefaultFont(HFONTX hFontX);
//@��ע ����ļ�����·��, Ĭ��·ΪexeĿ¼�ͳ���ǰ����Ŀ¼.
//@���� pPath �ļ���, Ŀ¼, ·��.
//@���� �Ų�_�������·��()
XC_API void WINAPI XC_AddFileSearchPath(const wchar_t* pPath);
//@��ע ��ʼ��LOGFONTW�ṹ��.
//@���� pFont LOGFONTW�ṹ��ָ��.
//@���� pName ��������.
//@���� size �����С.
//@���� bBold �Ƿ�Ϊ����.
//@���� bItalic �Ƿ�Ϊб��.
//@���� bUnderline �Ƿ����»���.
//@���� bStrikeOut �Ƿ���ɾ����.
//@���� �Ų�_��ʼ������()
XC_API void WINAPI XC_InitFont(LOGFONTW* pFont, wchar_t* pName, int size, BOOL bBold=FALSE, BOOL bItalic=FALSE, BOOL bUnderline=FALSE, BOOL bStrikeOut=FALSE);
//@��ע ��UI���������ڴ�.
//@���� size ��С,�ֽ�Ϊ��λ.
//@���� �ڴ��׵�ַ.
//@���� �Ų�_�����ڴ�()
XC_API void* WINAPI XC_Malloc(int size);
//@��ע ��UI�����ͷ��ڴ�.
//@���� p �ڴ��׵�ַ.
//@���� �Ų�_�ͷ��ڴ�()
XC_API void WINAPI XC_Free(void* p);
//@��ע ������ʾ��.
//@���� pTitle ��ʾ�����
//@���� pText ��ʾ����
//@���� �Ų�_����()
XC_API void WINAPI XC_Alert(const wchar_t* pText, const wchar_t* pTitle);
//@��ע �μ�ϵͳAPI ShellExecute()
//@���� hwnd
//@���� lpOperation
//@���� lpFile
//@���� lpParameters
//@���� lpDirectory
//@���� nShowCmd
//@���� ִ�гɹ��᷵��Ӧ�ó�����
//@���� �Ų�_ϵͳ_ShellExecute()
XC_API HINSTANCE WINAPI XC_Sys_ShellExecute(HWND hwnd, const wchar_t* lpOperation, const wchar_t* lpFile, const wchar_t* lpParameters, const wchar_t* lpDirectory, int nShowCmd);
//@��ע ϵͳAPI LoadLibrary
//@���� lpFileName �ļ���
//@���� ���ض�̬��ģ����
//@���� �Ų�_���붯̬��()
XC_API HMODULE WINAPI XC_LoadLibrary(const wchar_t* lpFileName);
//@��ע ϵͳAPI GetProcAddress
//@���� hModule ��̬��ģ����
//@���� lpProcName ������
//@���� ���غ�����ַ
//@���� �Ų�_ȡ��̬���к�����ַ()
XC_API FARPROC WINAPI XC_GetProcAddress(HMODULE hModule, const char* lpProcName);
//@��ע ϵͳAPI FreeLibrary
//@���� hModule ��̬��ģ����
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �Ų�_�ͷŶ�̬��()
XC_API BOOL WINAPI XC_FreeLibrary(HMODULE hModule);
//@��ע ����ָ��DLL,���ҵ���DLL�к���LoadDll(),  DLL�е���������ʽ: int WINAPI LoadDll()
//@���� pDllFileName DLL�ļ���
//@���� ����DLLģ����
//@���� �Ų�_����DLL()
XC_API HMODULE WINAPI XC_LoadDll(const wchar_t* pDllFileName);
//@��ע ���ز����ļ�.
//@���� pFileName �����ļ���.
//@���� hParent ��������,���ھ����UIԪ�ؾ��.
//@���� hAttachWnd ���ӵ�ָ���Ĵ���HWND,���δָ������
//@���� ���ش��ھ����Ԫ�ؾ��
//@���� �Ų�_���ز����ļ�()
XC_API HXCGUI WINAPI XC_LoadLayout(const wchar_t* pFileName, HXCGUI hParent=NULL, HWND hAttachWnd=NULL);
//@��ע ���ز����ļ���zipѹ������.
//@���� pZipFileName zip�ļ���.
//@���� pFileName �����ļ���.
//@���� pPassword zip����.
//@���� hParent ��������,���ھ����UIԪ�ؾ��.
//@���� hAttachWnd ���Ӵ��ھ��, ���ӵ�ָ���Ĵ���,���δָ������
//@���� ���ش��ھ����Ԫ�ؾ��
//@���� �Ų�_���ز����ļ�ZIP()
XC_API HXCGUI WINAPI XC_LoadLayoutZip(const wchar_t* pZipFileName, const wchar_t* pFileName, const wchar_t* pPassword=NULL, HXCGUI hParent=NULL, HWND hAttachWnd=NULL);
//@��ע ���ز����ļ���zipѹ������.
//@���� data �ڴ��ָ��
//@���� length �ڴ���С,�ֽ�Ϊ��λ
//@���� pFileName �����ļ���
//@���� pPassword zip����
//@���� hParent ��������,���ھ����UIԪ�ؾ��.
//@���� hAttachWnd ���Ӵ��ھ��, ���ӵ�ָ���Ĵ���,���δָ������
//@���� ���ش��ھ����Ԫ�ؾ��
//@���� �Ų�_���ز����ļ��ڴ�ZIP()
XC_API HXCGUI WINAPI XC_LoadLayoutZipMem(void* data, int length, const wchar_t* pFileName, const wchar_t* pPassword=NULL, HXCGUI hParent=NULL, HWND hAttachWnd=NULL);
//@��ע ���ز����ļ����ڴ��ַ���.
//@���� pStringXML �ַ���ָ��.
//@���� hParent ������,���ھ����UIԪ�ؾ��.
//@���� hAttachWnd ���Ӵ��ھ��, ���ӵ�ָ���Ĵ���,���δָ������
//@���� ���ش��ھ����Ԫ�ؾ��
//@���� �Ų�_���ز����ļ����ַ���()
XC_API HXCGUI WINAPI XC_LoadLayoutFromString(const char* pStringXML, HXCGUI hParent=NULL, HWND hAttachWnd=NULL);
//@��ע ���ز����ļ����ڴ��ַ���.
//@���� pStringXML �ַ���ָ��.
//@���� hParent ������,���ھ����UIԪ�ؾ��.
//@���� hAttachWnd ���Ӵ��ھ��, ���ӵ�ָ���Ĵ���,���δָ������
//@���� ���ش��ھ����Ԫ�ؾ��
//@���� �Ų�_���ز����ļ����ַ���UTF8()
XC_API HXCGUI WINAPI XC_LoadLayoutFromStringUtf8(const char* pStringXML, HXCGUI hParent=NULL, HWND hAttachWnd=NULL);
//@��ע ���ز����ļ�
//@���� pFileName �����ļ���
//@���� pPrefixName ����(name)ǰ׺, ��ѡ����; ����ǰ�����ļ�������name��������ǰ׺, ��ôname����ֵΪ: ǰ׺ + name;
//@���� hParent ��������,���ھ����UIԪ�ؾ��
//@���� hParentWnd �����ھ��HWND, �ṩ������������ʹ��
//@���� hAttachWnd ���ӵ�ָ���Ĵ���HWND,���δָ������
//@���� ���ش��ھ����Ԫ�ؾ��
//@���� �Ų�_���ز����ļ���չ()
XC_API HXCGUI WINAPI XC_LoadLayoutEx(const wchar_t* pFileName, const wchar_t* pPrefixName=NULL, HXCGUI hParent=NULL, HWND hParentWnd=NULL, HWND hAttachWnd=NULL);
//@��ע ���ز����ļ���zipѹ������
//@���� pZipFileName zip�ļ���
//@���� pFileName �����ļ���
//@���� pPassword zip����
//@���� pPrefixName ����(name)ǰ׺, ��ѡ����; ����ǰ�����ļ�������name��������ǰ׺, ��ôname����ֵΪ: ǰ׺ + name;
//@���� hParent ��������,���ھ����UIԪ�ؾ��.
//@���� hParentWnd �����ھ��HWND, �ṩ������������ʹ��
//@���� hAttachWnd ���Ӵ��ھ��, ���ӵ�ָ���Ĵ���,���δָ������
//@���� ���ش��ھ����Ԫ�ؾ��
//@���� �Ų�_���ز����ļ�ZIP��չ()
XC_API HXCGUI WINAPI XC_LoadLayoutZipEx(const wchar_t* pZipFileName, const wchar_t* pFileName, const wchar_t* pPassword=NULL, const wchar_t* pPrefixName=NULL, HXCGUI hParent=NULL, HWND hParentWnd=NULL, HWND hAttachWnd=NULL);
//@��ע ���ز����ļ���zipѹ������
//@���� data �ڴ��ָ��
//@���� length �ڴ���С,�ֽ�Ϊ��λ
//@���� pFileName �����ļ���
//@���� pPassword zip����
//@���� pPrefixName ����(name)ǰ׺, ��ѡ����; ����ǰ�����ļ�������name��������ǰ׺, ��ôname����ֵΪ: ǰ׺ + name;
//@���� hParent ��������,���ھ����UIԪ�ؾ��
//@���� hParentWnd �����ھ��HWND, �ṩ������������ʹ��
//@���� hAttachWnd ���Ӵ��ھ��, ���ӵ�ָ���Ĵ���,���δָ������
//@���� ���ش��ھ����Ԫ�ؾ��
//@���� �Ų�_���ز����ļ��ڴ�ZIP��չ()
XC_API HXCGUI WINAPI XC_LoadLayoutZipMemEx(void* data, int length, const wchar_t* pFileName, const wchar_t* pPassword=NULL, const wchar_t* pPrefixName=NULL, HXCGUI hParent=NULL, HWND hParentWnd=NULL, HWND hAttachWnd=NULL);
//@��ע ���ز����ļ���RC��Դzipѹ������, RC��Դ���ͱ���Ϊ:"RT_RCDATA"
//@���� id RC��ԴID
//@���� pFileName �����ļ���
//@���� pPassword zip����
//@���� pPrefixName ����(name)ǰ׺, ��ѡ����; ����ǰ�����ļ�������name��������ǰ׺, ��ôname����ֵΪ: ǰ׺ + name;
//@���� hParent ��������,���ھ����UIԪ�ؾ��
//@���� hParentWnd �����ھ��HWND, �ṩ������������ʹ��
//@���� hAttachWnd ���Ӵ��ھ��, ���ӵ�ָ���Ĵ���,���δָ������
//@���� hModule ģ����
//@���� ���ش��ھ����Ԫ�ؾ��
//@���� �Ų�_���ز����ļ���ԴZIP��չ()
XC_API HXCGUI WINAPI XC_LoadLayoutZipResEx(int id, const wchar_t* pFileName, const wchar_t* pPassword=NULL, const wchar_t* pPrefixName=NULL, HXCGUI hParent=NULL, HWND hParentWnd=NULL, HWND hAttachWnd=NULL, HMODULE hModule=NULL);
//@��ע ���ز����ļ����ڴ��ַ���.
//@���� pStringXML �ַ���ָ��
//@���� pPrefixName ����(name)ǰ׺, ��ѡ����; ����ǰ�����ļ�������name��������ǰ׺, ��ôname����ֵΪ: ǰ׺ + name;
//@���� hParent ������,���ھ����UIԪ�ؾ��
//@���� hParentWnd �����ھ��HWND, �ṩ������������ʹ��
//@���� hAttachWnd ���Ӵ��ھ��, ���ӵ�ָ���Ĵ���,���δָ������
//@���� ���ش��ھ����Ԫ�ؾ��
//@���� �Ų�_���ز����ļ����ַ�����չ()
XC_API HXCGUI WINAPI XC_LoadLayoutFromStringEx(const char* pStringXML, const wchar_t* pPrefixName=NULL, HXCGUI hParent=NULL, HWND hParentWnd=NULL, HWND hAttachWnd=NULL);
//@��ע ���ز����ļ����ڴ��ַ���
//@���� pStringXML �ַ���ָ��
//@���� pPrefixName ����(name)ǰ׺, ��ѡ����; ����ǰ�����ļ�������name��������ǰ׺, ��ôname����ֵΪ: ǰ׺ + name;
//@���� hParent ������,���ھ����UIԪ�ؾ��
//@���� hParentWnd �����ھ��HWND, �ṩ������������ʹ��
//@���� hAttachWnd ���Ӵ��ھ��, ���ӵ�ָ���Ĵ���,���δָ������
//@���� ���ش��ھ����Ԫ�ؾ��
//@���� �Ų�_���ز����ļ����ַ���UTF8��չ()
XC_API HXCGUI WINAPI XC_LoadLayoutFromStringUtf8Ex(const char* pStringXML, const wchar_t* pPrefixName=NULL, HXCGUI hParent=NULL, HWND hParentWnd=NULL, HWND hAttachWnd=NULL);
//@��ע ������ʽ�ļ�.
//@���� pFileName ��ʽ�ļ�����.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �Ų�_������ʽ�ļ�()
XC_API BOOL WINAPI XC_LoadStyle(const wchar_t* pFileName);
//@���� pZipFile ZIP�ļ���
//@���� pFileName �ļ���
//@���� pPassword ����
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �Ų�_������ʽ�ļ�ZIP()
XC_API BOOL WINAPI XC_LoadStyleZip(const wchar_t* pZipFile, const wchar_t* pFileName, const wchar_t* pPassword=NULL);
//@���� data �ڴ��ָ��
//@���� length �ڴ���С,�ֽ�Ϊ��λ
//@���� pFileName �ļ���
//@���� pPassword ����
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �Ų�_������ʽ�ļ����ڴ�ZIP()
XC_API BOOL WINAPI XC_LoadStyleZipMem(void* data, int length, const wchar_t* pFileName, const wchar_t* pPassword=NULL);
//@��ע ��RC��Դ�е�ZIP����, ������ʽ�ļ�, RC��Դ���ͱ���Ϊ:"RT_RCDATA"
//@���� id RC��ԴID
//@���� pFileName �ļ���
//@���� pPassword ����
//@���� hModule ģ����
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� �Ų�_������ʽ�ļ�����ԴZIP()
XC_API BOOL WINAPI XC_LoadStyleZipRes(int id, const wchar_t* pFileName, const wchar_t* pPassword=NULL, HMODULE hModule=NULL);
//@���� pString �ַ���
//@���� pFileName ��ʽ�ļ���, ���ڴ�ӡ�����ļ��Ͷ�λ������Դ�ļ�λ��
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� �Ų�_������ʽ�ļ����ַ���()
XC_API BOOL WINAPI XC_LoadStyleFromString(const char* pString, const wchar_t* pFileName);
//@���� pString �ַ���
//@���� pFileName ��ʽ�ļ���, ���ڴ�ӡ�����ļ��Ͷ�λ������Դ�ļ�λ��
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� �Ų�_������ʽ�ļ����ַ���UTF8()
XC_API BOOL WINAPI XC_LoadStyleFromStringUtf8(const char* pString, const wchar_t* pFileName);
//@���� pFileName ��ʽ�ļ���, ���ڴ�ӡ�����ļ��Ͷ�λ������Դ�ļ�λ��
//@���� pString �ַ���
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� �Ų�_������ʽ�ļ����ַ���W()
XC_API BOOL WINAPI XC_LoadStyleFromStringW(const wchar_t* pString, const wchar_t* pFileName);
//@��ע ������Դ�ļ�.
//@���� pFileName ��Դ�ļ���.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �Ų�_������Դ�ļ�()
XC_API BOOL WINAPI XC_LoadResource(const wchar_t* pFileName);
//@��ע ������Դ�ļ���zipѹ������.
//@���� pZipFileName zip�ļ���.
//@���� pFileName ��Դ�ļ���.
//@���� pPassword zipѹ��������.
//@���� ����ɹ�����TRUE,���򷵻�FALSE.
//@���� �Ų�_������Դ�ļ�ZIP()
XC_API BOOL WINAPI XC_LoadResourceZip(const wchar_t* pZipFileName, const wchar_t* pFileName, const wchar_t* pPassword=NULL);
//@��ע ������Դ�ļ����ڴ�zipѹ������
//@���� data �ڴ��ָ��
//@���� length �ڴ���С,�ֽ�Ϊ��λ
//@���� pFileName ��Դ�ļ���
//@���� pPassword zipѹ��������
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� �Ų�_������Դ�ļ��ڴ�ZIP()
XC_API BOOL WINAPI XC_LoadResourceZipMem(void* data, int length, const wchar_t* pFileName, const wchar_t* pPassword=NULL);
//@���� id RC��ԴID
//@���� pFileName ��Դ�ļ���
//@���� pPassword zipѹ��������
//@���� hModule ģ����
//@���� ����ɹ�����TRUE,���򷵻�FALSE
XC_API BOOL WINAPI XC_LoadResourceZipRes(int id, const wchar_t* pFileName, const wchar_t* pPassword=NULL, HMODULE hModule=NULL);
//@��ע ������Դ�ļ����ڴ��ַ���.
//@���� pStringXML �ַ���ָ��.
//@���� pFileName ��Դ�ļ���,����:'resource1'; �������ֲ�ͬ����Դ�ļ�,��������ظ�, ��ô�滻��ǰ����Դ�ļ�.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �Ų�_������Դ�ļ����ַ���()
XC_API BOOL WINAPI XC_LoadResourceFromString(const char* pStringXML, const wchar_t* pFileName);
//@��ע ������Դ�ļ����ڴ��ַ���.
//@���� pStringXML �ַ���ָ��.
//@���� pFileName ��Դ�ļ���,����:'resource1'; �������ֲ�ͬ����Դ�ļ�,��������ظ�, ��ô�滻��ǰ����Դ�ļ�.
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �Ų�_������Դ�ļ����ַ���UTF8()
XC_API BOOL WINAPI XC_LoadResourceFromStringUtf8(const char* pStringXML, const wchar_t* pFileName);
//@���� ���� ID2D1Factory*
XC_API vint WINAPI XC_GetD2dFactory();
//@��ע ����D2D��Ч
//@���� ���� IWICImagingFactory*
//@���� �Ų�_ȡWIC����()
XC_API vint WINAPI XC_GetWicFactory();
//@��ע ����D2D��Ч
//@���� ���� IDWriteFactory*
//@���� �Ų�_ȡDWrite����()
XC_API vint WINAPI XC_GetDWriteFactory();
//@��ע �Ų���չ�ӿ�
//@���� hXCGUI �Ųʶ�����
//@���� nType ����
//@���� �Ų�_������()
XC_API void WINAPI _XC_SetType(HXCGUI hXCGUI, XC_OBJECT_TYPE nType);
//@��ע �Ų���չ�����ӿ�
//@���� hXCGUI �Ųʶ�����
//@���� nType ����
//@���� �Ų�_�������()
XC_API void WINAPI _XC_AddType(HXCGUI hXCGUI, XC_OBJECT_TYPE nType);
//@��ע �Ų���չ�����ӿ�
//@���� hXCGUI �Ųʶ�����
//@���� data ������
//@���� �Ų�_������()
XC_API void WINAPI _XC_BindData(HXCGUI hXCGUI, vint data);
//@��ע �Ų���չ�����ӿ�
//@���� hXCGUI �Ųʶ�����
//@���� ���ذ�����
//@���� �Ų�_ȡ������()
XC_API vint WINAPI _XC_GetBindData(HXCGUI hXCGUI);
//@��ע ��ʼ�������.
//@���� bD2D �Ƿ�����D2D, ������ý�ʹ��D2D����ͼ��,����ʹ��GDI+����ͼ��
//@���� �ɹ�����TRUE���򷵻�FALSE.
//@���� �Ų�_��ʼ��()
XC_API BOOL WINAPI XInitXCGUI(BOOL bD2D);

//@��ע  �жϽ�����Ƿ��ѳ�ʼ��
//@����  ���������ѳ�ʼ������TRUE, ���򷵻�FALSE
//@����  �Ų�_�Ƿ��ʼ��()
XC_API BOOL WINAPI XC_IsInit();

//@��ע ������Ϣѭ��,���Ųʴ�������Ϊ0ʱ�˳�.
//@���� �޷���
//@���� �Ų�_����()
XC_API void WINAPI XRunXCGUI();
//@��ע �˳�������ͷ���Դ.
//������dllmain()��ж��, ���������
//@���� �޷���
//@���� �Ų�_�˳�()
XC_API void WINAPI XExitXCGUI();
//@��ע ����WM_QUIT��Ϣ�˳���Ϣѭ��,����μ�ϵͳAPI PostQuitMessage().
//@���� nExitCode �˳���.
//@���� �Ų�_PostQuitMessage()
XC_API void WINAPI XC_PostQuitMessage(int nExitCode);
//@��ע ȫ�ִ���ͼ��, ����δ����ͼ��Ĵ���,����ʹ�ô�Ĭ��ͼ��
//@���� hImage ͼ����
//@���� �Ų�_�ô���ͼ��()
XC_API void WINAPI XC_SetWindowIcon(HIMAGE hImage);
//@��ע ����������ʶ�������� @ref bkObject_align_flag_
//��ˮƽ����ʱleft������;
//����ֱ����ʱtop����߶�;
//������leftʱright������;
//������rightʱleft������;
//������topʱbottom������;
//������bottomʱtop������;
//@���� hObj ����������
//@���� left ��߼��
//@���� top �������
//@���� right �ұ߼��
//@���� bottom �ײ����
//@���� ��������_������()
XC_API void WINAPI XBkObj_SetMargin(vint hObj, int left, int top, int right, int bottom);
//@���� hObj ����������
//@���� nFlags ���뷽ʽ @ref bkObject_align_flag_
//@���� ��������_�ö���()
XC_API void WINAPI XBkObj_SetAlign(vint hObj, int nFlags);
//@���� hObj ����������
//@���� hImage ͼƬ���
//@���� ��������_��ͼƬ()
XC_API void WINAPI XBkObj_SetImage(vint hObj, HIMAGE hImage);
//@���� hObj ����������
//@���� angle ��ת�Ƕ�
//@���� ��������_����ת()
XC_API void WINAPI XBkObj_SetRotate(vint hObj, float angle);
//@���� hObj ����������
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� ��������_�������ɫ()
XC_API void WINAPI XBkObj_SetFillColor(vint hObj, COLORREF color);
//@���� hObj ����������
//@���� width ���
//@���� ��������_�ñ߿���()
XC_API void WINAPI XBkObj_SetBorderWidth(vint hObj, int width);
//@���� hObj ����������
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� ��������_�ñ߿���ɫ()
XC_API void WINAPI XBkObj_SetBorderColor(vint hObj, COLORREF color);
//@���� hObj ����������
//@���� leftTop ���Ͻ�
//@���� leftBottom ���½�
//@���� rightTop ���Ͻ�
//@���� rightBottom ���½�
//@���� ��������_�þ���Բ��()
XC_API void WINAPI XBkObj_SetRectRoundAngle(vint hObj, int leftTop, int leftBottom, int rightTop, int rightBottom);
//@��ע ���û������
//@���� hObj ����������
//@���� bEnable �Ƿ�����
//@���� ��������_�������()
XC_API void WINAPI XBkObj_EnableFill(vint hObj, BOOL bEnable);
//@��ע ���û��Ʊ߿�
//@���� hObj ����������
//@���� bEnable �Ƿ�����
//@���� ��������_���ñ߿�()
XC_API void WINAPI XBkObj_EnableBorder(vint hObj, BOOL bEnable);
//@���� hObj ����������
//@���� pText �ı��ַ���
//@���� ��������_���ı�()
XC_API void WINAPI XBkObj_SetText(vint hObj, const wchar_t* pText);
//@���� hObj ����������
//@���� hFont ������
//@���� ��������_������()
XC_API void WINAPI XBkObj_SetFont(vint hObj, HFONTX hFont);
//@���� hObj ����������
//@���� nAlign �ı����뷽ʽ  @ref textFormatFlag_
//@���� ��������_���ı�����()
XC_API void WINAPI XBkObj_SetTextAlign(vint hObj, int nAlign);
//@��ע ��ˮƽ����ʱleft������;
//����ֱ����ʱtop����߶�;
//������leftʱright������;
//������rightʱleft������;
//������topʱbottom������;
//������bottomʱtop������;
//@���� hObj ����������
//@���� pMargin ���շ�������
//@���� ��������_ȡ����()
XC_API void WINAPI XBkObj_GetMargin(vint hObj, marginSize_* pMargin);
//@���� hObj ����������
//@���� ���ض����ʶ @ref bkObject_align_flag_
//@���� ��������_ȡ����()
XC_API int WINAPI XBkObj_GetAlign(vint hObj);
//@���� hObj ����������
//@���� ����ͼƬ���
//@���� ��������_ȡͼƬ()
XC_API HIMAGE WINAPI XBkObj_GetImage(vint hObj);
//@���� hObj ����������
//@���� ������ת�Ƕ�
//@���� ��������_ȡ��ת�Ƕ�()
XC_API int WINAPI XBkObj_GetRotate(vint hObj);
//@���� hObj ����������
//@���� �������ɫ
//@���� ��������_ȡ���ɫ()
XC_API COLORREF WINAPI XBkObj_GetFillColor(vint hObj);
//@���� hObj ����������
//@���� ���ر߿�ɫ
//@���� ��������_ȡ�߿�ɫ()
XC_API COLORREF WINAPI XBkObj_GetBorderColor(vint hObj);
//@���� hObj ����������
//@���� ���ر߿���
//@���� ��������_ȡ�߿���()
XC_API int WINAPI XBkObj_GetBorderWidth(vint hObj);
//@���� hObj ����������
//@���� pRect ���շ���Բ�Ǵ�С
//@���� ��������_ȡ����Բ��()
XC_API void WINAPI XBkObj_GetRectRoundAngle(vint hObj, RECT* pRect);
//@���� hObj ����������
//@���� ��䷵��TRUE,���򷵻�FALSE
//@���� ��������_�Ƿ����()
XC_API BOOL WINAPI XBkObj_IsFill(vint hObj);
//@���� hObj ����������
//@���� ���Ʊ߿򷵻�TRUE,���򷵻�FALSE
//@���� ��������_�Ƿ�߿�()
XC_API BOOL WINAPI XBkObj_IsBorder(vint hObj);
//@���� hObj ����������
//@���� �����ı�
//@���� ��������_ȡ�ı�()
XC_API const wchar_t* WINAPI XBkObj_GetText(vint hObj);
//@���� hObj ����������
//@���� ��������
//@���� ��������_ȡ����()
XC_API HFONTX WINAPI XBkObj_GetFont(vint hObj);
//@���� hObj ����������
//@���� �����ı����뷽ʽ @ref textFormatFlag_
//@���� ��������_ȡ�ı�����()
XC_API int WINAPI XBkObj_GetTextAlign(vint hObj);
//@��ע ����
//@���� pos λ��, 0.0f - 1.0f
//@���� ���ؼ�����
//@���� ����_Linear()
XC_API float WINAPI XEase_Linear(float pos);
//@��ע ���η�����
//@���� pos λ��, 0.0f - 1.0f
//@���� flag ������ʽ
//@���� ���ؼ�����
//@���� ����_Quad()
XC_API float WINAPI XEase_Quad(float pos, ease_type_ flag);
//@��ע ���η�����  Բ��
//@���� pos λ��, 0.0f - 1.0f
//@���� flag ������ʽ
//@���� ���ؼ�����
//@���� ����_Cubic()
XC_API float WINAPI XEase_Cubic(float pos, ease_type_ flag);
//@��ע �ķ�����
//@���� pos λ��, 0.0f - 1.0f
//@���� flag ������ʽ
//@���� ���ؼ�����
//@���� ����_Quart()
XC_API float WINAPI XEase_Quart(float pos, ease_type_ flag);
//@��ע ��η�����
//@���� pos λ��, 0.0f - 1.0f
//@���� flag ������ʽ
//@���� ���ؼ�����
//@���� ����_Quint()
XC_API float WINAPI XEase_Quint(float pos, ease_type_ flag);
//@��ע ��������, ��ĩ�˱仯
//@���� pos λ��, 0.0f - 1.0f
//@���� flag ������ʽ
//@���� ���ؼ�����
//@���� ����_Sine()
XC_API float WINAPI XEase_Sine(float pos, ease_type_ flag);
//@��ע ͻ������, ͻȻһ��
//@���� pos λ��, 0.0f - 1.0f
//@���� flag ������ʽ
//@���� ���ؼ�����
//@���� ����_Expo()
XC_API float WINAPI XEase_Expo(float pos, ease_type_ flag);
//@��ע Բ��, �ñ��ƹ�һ��Բ��
//@���� pos λ��, 0.0f - 1.0f
//@���� flag ������ʽ
//@���� ���ؼ�����
//@���� ����_Circ()
XC_API float WINAPI XEase_Circ(float pos, ease_type_ flag);
//@��ע ǿ���ص�
//@���� pos λ��, 0.0f - 1.0f
//@���� flag ������ʽ
//@���� ���ؼ�����
//@���� ����_Elastic()
XC_API float WINAPI XEase_Elastic(float pos, ease_type_ flag);
//@��ע �ص�, �Ƚϻ���
//@���� pos λ��, 0.0f - 1.0f
//@���� flag ������ʽ
//@���� ���ؼ�����
//@���� ����_Back()
XC_API float WINAPI XEase_Back(float pos, ease_type_ flag);
//@��ע ����  ģ��С����ص���
//@���� pos λ��, 0.0f - 1.0f
//@���� flag ������ʽ
//@���� ���ؼ�����
//@���� ����_Bounce()
XC_API float WINAPI XEase_Bounce(float pos, ease_type_ flag);
//@��ע ȫ����������
//@���� pos λ��, 0.0f - 1.0f
//@���� flag ������ʽ @ref ease_flag_
//@���� ���ؼ�����
//@���� ����_��չ()
XC_API float WINAPI XEase_Ex(float pos, int flag);
//@���� x Ԫ��x����.
//@���� y Ԫ��y����.
//@���� cx ���.
//@���� cy �߶�.
//@���� hParent ��Ϊ���ھ����Ԫ�ؾ��.
//@���� Ԫ�ؾ��.
//@���� ����༭��_����()
XC_API HELE WINAPI XEditor_Create(int x, int y, int cx, int cy, HXCGUI hParent=NULL);
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ����༭��_�Ƿ�ϵ�()
XC_API BOOL WINAPI XEditor_IsBreakpoint(HELE hEle, int iRow);
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� bActivate �Ƿ񼤻�
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ����༭��_�öϵ�()
XC_API BOOL WINAPI XEditor_SetBreakpoint(HELE hEle, int iRow, BOOL bActivate=TRUE);
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ����༭��_�Ƴ��ϵ�()
XC_API BOOL WINAPI XEditor_RemoveBreakpoint(HELE hEle, int iRow);
//@���� hEle Ԫ�ؾ��
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ����༭��_��նϵ�()
XC_API void WINAPI XEditor_ClearBreakpoint(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ����༭��_�õ�ǰ����()
XC_API BOOL WINAPI XEditor_SetRunRow(HELE hEle, int iRow);
//@���� hEle Ԫ�ؾ��
//@���� pInfo ��ɫ��Ϣ�ṹ��ָ��
//@���� ����༭��_ȡ��ɫ��Ϣ()
XC_API void WINAPI XEditor_GetColor(HELE hEle, editor_color_* pInfo);
//@���� hEle Ԫ�ؾ��
//@���� pInfo ��ɫ��Ϣ�ṹ��ָ��
//@���� ����༭��_����ɫ()
XC_API void WINAPI XEditor_SetColor(HELE hEle, editor_color_* pInfo);
//@���� hEle Ԫ�ؾ��
//@���� ���ضϵ�����
//@���� ����༭��_ȡ�ϵ�����()
XC_API int WINAPI XEditor_GetBreakpointCount(HELE hEle);
//@���� hEle Ԫ�ؾ��
//@���� nDelay �ӳ�ֵ����
//@���� ����༭��_����ʾ��Ϣ�ӳ�()
XC_API void WINAPI XEditor_SetTipsDelay(HELE hEle, int nDelay);
//@���� hEle Ԫ�ؾ��
//@���� mode 0:�س�ѡ��, 1:�ո�ѡ��, 3:tab��ѡ��
//@���� ����༭��_���Զ�ƥ��ѡ��ģʽ()
XC_API void WINAPI XEditor_SetAutoMatchSelectMode(HELE hEle, int mode);
//@���� hEle Ԫ�ؾ��
//@���� mode 0:��Ӣ��(��������), 1:��Ӣ��(����Ӣ��), 2:����(��������), 3:Ӣ��(����Ӣ��)
//@���� ����༭��_���Զ�ƥ������ʾģʽ()
XC_API void WINAPI XEditor_SetAutoMatchMode(HELE hEle, int mode);
//@���� hEle Ԫ�ؾ��
//@���� aPoints ���նϵ�����
//@���� nCount �����С(�����Ա��)
//@���� ����ʵ�ʻ�ȡ�ϵ�����
//@���� ����༭��_ȡȫ���ϵ�()
XC_API int WINAPI XEditor_GetBreakpoints(HELE hEle, int* aPoints, int nCount);
//@��ע ����������
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� ����༭��_���õ�ǰ��()
XC_API void WINAPI XEditor_SetCurRow(HELE hEle, int iRow);
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ����༭��_��ȡ���()
XC_API int WINAPI XEditor_GetDepth(HELE hEle, int iRow);
//@��ע ����������
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� ����չ��������
//@���� ����༭��_ת����չ����()
XC_API int WINAPI XEditor_ToExpandRow(HELE hEle, int iRow);
//@��ע ��ȫչ��ָ����,����:�а������۵�������,����չ��
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� ����༭��_չ����չ()
XC_API void WINAPI XEditor_ExpandEx(HELE hEle, int iRow);
//@���� hEle Ԫ�ؾ��
//@���� bExpand �Ƿ�չ��
//@���� ����༭��_չ��ȫ��()
XC_API void WINAPI XEditor_ExpandAll(HELE hEle, BOOL bExpand);
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� bExpand �Ƿ�չ��
//@���� ����༭��_չ��ָ����()
XC_API void WINAPI XEditor_Expand(HELE hEle, int iRow, BOOL bExpand);
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� ����༭��_չ������ָ����()
XC_API void WINAPI XEditor_ExpandSwitch(HELE hEle, int iRow);
//@��ע ����Ѵ���,��ô��������ʽ
//@���� hEle Ԫ�ؾ��
//@���� pKey �ַ���
//@���� iStyle ��ʽ
//@���� ����༭��_��ӹؼ���()
XC_API void WINAPI XEditor_AddKeyword(HELE hEle, const wchar_t* pKey, int iStyle);
//@���� hEle Ԫ�ؾ��
//@���� pKey �ַ���
//@���� ����༭��_����Զ�ƥ���ַ���()
XC_API void WINAPI XEditor_AddConst(HELE hEle, const wchar_t* pKey);
//@���� hEle Ԫ�ؾ��
//@���� pKey �ַ���
//@���� ����༭��_����Զ�ƥ�亯��()
XC_API void WINAPI XEditor_AddFunction(HELE hEle, const wchar_t* pKey);
//@��ע �ų���������Ĺؼ���, �����ų��������, ��Ϊ������������Զ�ƥ��;
//�˹ؼ��ֲ������Զ�ƥ��,�������ų��������
//@���� hEle Ԫ�ؾ��
//@���� pKeyword �ַ���
//@���� ����༭��_����ų���������ؼ���()
XC_API void WINAPI XEditor_AddExcludeDefVarKeyword(HELE hEle, const wchar_t* pKeyword);
//@��ע �б���ģ���ļ�����
//@���� nType ģ������,֧������: \n
//@���� pFileName �ļ���.
//@���� ����ģ����Ϣ.
//@���� ��ģ��_���ش��ļ�()
XC_API HTEMP WINAPI XTemp_Load(listItemTemp_type_ nType, const wchar_t* pFileName);
//@��ע �����б���ģ���zipѹ������
//@���� nType ģ������,֧������: \n
//@���� pZipFile zip�ļ�
//@���� pFileName �ļ���
//@���� pPassword zip����
//@���� ����ģ����.
//@���� ��ģ��_���ش�ZIP()
XC_API HTEMP WINAPI XTemp_LoadZip(listItemTemp_type_ nType, const wchar_t* pZipFile, const wchar_t* pFileName, const wchar_t* pPassword=NULL);
//@��ע �����б���ģ����ڴ�zipѹ������
//@���� nType ģ������,֧������, ֻ��ѡһ��: \n
//@���� data �ڴ��ָ��
//@���� length �ڴ���С,�ֽ�Ϊ��λ
//@���� pFileName �ļ���
//@���� pPassword zip����
//@���� ����ģ����.
//@���� ��ģ��_���ش��ڴ�ZIP()
XC_API HTEMP WINAPI XTemp_LoadZipMem(listItemTemp_type_ nType, void* data, int length, const wchar_t* pFileName, const wchar_t* pPassword=NULL);
//@��ע �����б���ģ����ļ�
//@���� nType ģ������, ֧������, ֻ��ѡһ��: \n
//@���� pFileName �ļ���
//@���� pOutTemp1 ����ģ����1, ��ģ��
//@���� pOutTemp2 ����ģ����2, �б�ͷģ����б�����ģ��
//@���� ����ɹ�����TRUE,���򷵻�FALSE.
//@���� ��ģ��_���ش��ļ���չ()
XC_API BOOL WINAPI XTemp_LoadEx(listItemTemp_type_ nType, const wchar_t* pFileName, HTEMP* pOutTemp1, HTEMP* pOutTemp2);
//@��ע �����б���ģ���zipѹ������
//@���� nType ģ������, ֧������: \n
//@���� pZipFile zip�ļ�
//@���� pFileName �ļ���
//@���� pPassword zip����
//@���� pOutTemp1 ����ģ����1, ��ģ��
//@���� pOutTemp2 ����ģ����2, �б�ͷģ����б�����ģ��
//@���� ����ɹ�����TRUE,���򷵻�FALSE.
//@���� ��ģ��_���ش�ZIP��չ()
XC_API BOOL WINAPI XTemp_LoadZipEx(listItemTemp_type_ nType, const wchar_t* pZipFile, const wchar_t* pFileName, const wchar_t* pPassword, HTEMP* pOutTemp1, HTEMP* pOutTemp2);
//@��ע �����б���ģ����ڴ�zipѹ������
//@���� nType ģ������, ֧������: \n
//@���� data �ڴ��ָ��
//@���� length �ڴ���С,�ֽ�Ϊ��λ
//@���� pFileName �ļ���
//@���� pPassword zip����
//@���� pOutTemp1 ����ģ����1, ��ģ��
//@���� pOutTemp2 ����ģ����2, �б�ͷģ����б�����ģ��
//@���� ����ɹ�����TRUE,���򷵻�FALSE.
//@���� ��ģ��_���ش��ڴ�ZIP��չ()
XC_API BOOL WINAPI XTemp_LoadZipMemEx(listItemTemp_type_ nType, void* data, int length, const wchar_t* pFileName, const wchar_t* pPassword, HTEMP* pOutTemp1, HTEMP* pOutTemp2);
//@��ע �����б���ģ���ļ���RC��ԴZIP, RC��Դ���ͱ���Ϊ:"RT_RCDATA"
//@���� nType ģ������,֧������: \n
//@���� id RC��ԴID
//@���� pFileName ģ���ļ���
//@���� pPassword ZIP����
//@���� hModule ģ����
//@���� ����ģ����Ϣ.
//@���� ��ģ��_���ش���ԴZIP()
XC_API HTEMP WINAPI XTemp_LoadZipRes(listItemTemp_type_ nType, int id, const wchar_t* pFileName, const wchar_t* pPassword=NULL, HMODULE hModule=NULL);
//@��ע �����б���ģ���RC��ԴZIP, RC��Դ���ͱ���Ϊ:"RT_RCDATA"
//@���� nType ģ������, ֧������: \n
//@���� id RC��ԴID
//@���� pFileName �ļ���
//@���� pPassword zip����
//@���� pOutTemp1 ����ģ����1, ��ģ��
//@���� pOutTemp2 ����ģ����2, �б�ͷģ����б�����ģ��
//@���� hModule ģ����
//@���� ����ɹ�����TRUE,���򷵻�FALSE.
//@���� ��ģ��_���ش���ԴZIP��չ()
XC_API BOOL WINAPI XTemp_LoadZipResEx(listItemTemp_type_ nType, int id, const wchar_t* pFileName, const wchar_t* pPassword, HTEMP* pOutTemp1, HTEMP* pOutTemp2, HMODULE hModule=NULL);
//@��ע �����б���ģ���ļ����ڴ��ַ���
//@���� nType ģ������,֧������: \n
//@���� pStringXML �ַ���ָ��.
//@���� ����ģ����Ϣ.
//@���� ��ģ��_���ش��ַ���()
XC_API HTEMP WINAPI XTemp_LoadFromString(listItemTemp_type_ nType, const char* pStringXML);
//@��ע �����б���ģ����ַ���
//@���� nType ģ������, ֧������: \n
//@���� pStringXML �ַ�������
//@���� pOutTemp1 ����ģ����1, ��ģ��
//@���� pOutTemp2 ����ģ����2, �б�ͷģ����б�����ģ��
//@���� ����ɹ�����TRUE,���򷵻�FALSE.
//@���� ��ģ��_���ش��ַ�����չ()
XC_API BOOL WINAPI XTemp_LoadFromStringEx(listItemTemp_type_ nType, const char* pStringXML, HTEMP* pOutTemp1, HTEMP* pOutTemp2);
//@��ע �����б���ģ���ļ����ڴ�
//@���� nType ģ������,֧������: \n
//@���� data �ڴ��ַ
//@���� length �ڴ��С, �ֽ�Ϊ��λ
//@���� ����ģ����Ϣ.
//@���� ��ģ��_���ش��ڴ�()
XC_API HTEMP WINAPI XTemp_LoadFromMem(listItemTemp_type_ nType, void* data, int length);
//@��ע �����б���ģ����ڴ�
//@���� nType ģ������, ֧������: \n
//@���� data �ڴ��ָ��
//@���� length �ڴ���С,�ֽ�Ϊ��λ
//@���� pOutTemp1 ����ģ����1, ��ģ��
//@���� pOutTemp2 ����ģ����2, �б�ͷģ����б�����ģ��
//@���� ����ɹ�����TRUE,���򷵻�FALSE.
//@���� ��ģ��_���ش��ڴ���չ()
XC_API BOOL WINAPI XTemp_LoadFromMemEx(listItemTemp_type_ nType, void* data, int length, HTEMP* pOutTemp1, HTEMP* pOutTemp2);
//@��ע ��ȡ�б���ģ������
//@���� hTemp �б���ģ����.
//@���� ����ģ������.
//@���� ��ģ��_ȡ����()
XC_API listItemTemp_type_ WINAPI XTemp_GetType(HTEMP hTemp);
//@��ע ��ģ������
//@���� hTemp ��ģ����.
//@���� ��ģ��_����()
XC_API BOOL WINAPI XTemp_Destroy(HTEMP hTemp);
//@��ע ����һ���µ���ģ��
//@���� hTemp �б���ģ����
//@���� ����ģ����
//@���� ��ģ��_��¡()
XC_API HTEMP WINAPI XTemp_Clone(HTEMP hTemp);
//@��ע ������ģ��
//@���� nType ģ������
//@���� ����ģ����.
//@���� ��ģ��_����()
XC_API HTEMP WINAPI XTemp_Create(listItemTemp_type_ nType);
//@��ע ��Ӹ��ڵ�
//@���� hTemp ��ģ����.
//@���� pNode �ڵ�ָ��.
//@���� �ɹ�����TRUE,���򷵻�FALSE.
//@���� ��ģ��_��Ӹ��ڵ�()
XC_API BOOL WINAPI XTemp_AddNodeRoot(HTEMP hTemp, void* pNode);
//@���� hTemp �б���ģ����
//@���� index ����λ������
//@���� pNode �ڵ�ָ��
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ��ģ��_�б�_����ڵ�()
XC_API BOOL WINAPI XTemp_List_InsertNode(HTEMP hTemp, int index, void* pNode);
//@���� hTemp �б���ģ����
//@���� index ����λ������
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ��ģ��_�б�_ɾ���ڵ�()
XC_API BOOL WINAPI XTemp_List_DeleteNode(HTEMP hTemp, int index);
//@��ע ȡ�ӽڵ�����, ֻ��ǰ���ӽڵ�
//@���� hTemp �б���ģ����
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ��ģ��_�б�_ȡ����()
XC_API int WINAPI XTemp_List_GetCount(HTEMP hTemp);
//@��ע ����ӽڵ�
//@���� pParentNode ���ڵ�ָ��.
//@���� pNode �ڵ�ָ��.
//@���� �ɹ�����TRUE,���򷵻�FALSE.
//@���� ��ģ��_����ӽڵ�()
XC_API BOOL WINAPI XTemp_AddNode(void* pParentNode, void* pNode);
//@��ע �����ڵ�
//@���� nType ��������.
//@���� �ɹ����ؽڵ�ָ��,���򷵻�NULL.
//@���� ��ģ��_�����ڵ�()
XC_API void* WINAPI XTemp_CreateNode(XC_OBJECT_TYPE nType);
//@��ע ��������
//@���� pNode �ڵ�ָ��.
//@���� pName ������.
//@���� pAttr ����ֵ.
//@���� �ɹ�����TRUE,���򷵻�FALSE.
//@���� ��ģ��_�ýڵ�����()
XC_API BOOL WINAPI XTemp_SetNodeAttribute(void* pNode, const wchar_t* pName, const wchar_t* pAttr);
//@��ע ���ýڵ�����
//@���� pNode �ڵ�ָ��
//@���� itemID ģ����ID
//@���� pName ������
//@���� pAttr ����ֵ
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ��ģ��_�ýڵ�������չ()
XC_API BOOL WINAPI XTemp_SetNodeAttributeEx(void* pNode, int itemID, const wchar_t* pName, const wchar_t* pAttr);
//@��ע ��ȡ�б��еĽڵ�
//@���� hTemp ģ����
//@���� index �ڵ�λ������
//@���� ���ط��ؽڵ�ָ��
//@���� ��ģ��_ȡ�б��еĽڵ�()
XC_API void* WINAPI XTemp_List_GetNode(HTEMP hTemp, int index);
//@��ע ��ָ�����ƶ���Ŀ��λ��
//@���� hTemp �б���ģ����
//@���� iColSrc Դ������
//@���� iColDest Ŀ��������
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ��ģ��_�б�_�ƶ���()
XC_API BOOL WINAPI XTemp_List_MoveColumn(HTEMP hTemp, int iColSrc, int iColDest);
//@��ע ��ȡ�ڵ�, ����itemID
//@���� pNode �ڵ�ָ��
//@���� itemID ID.
//@���� ����itemID��Ӧ�Ľڵ�ָ��
//@���� ��ģ��_ȡ�ڵ�()
XC_API void* WINAPI XTemp_GetNode(void* pNode, int itemID);
//@��ע ��¡һ���ڵ�
//@���� pNode �ڵ�ָ��
//@���� ���ؿ�¡�Ľڵ�
//@���� ��ģ��_��¡�ڵ�()
XC_API void* WINAPI XTemp_CloneNode(void* pNode);
//@��ע �˴�����һ��ģ̬����
//@���� pTitle ����, �޸���ɫͨ�� ���ñ�����ɫ
//@���� pText �����ı�, �޸���ɫͨ�� ���ô�����ɫ
//@���� nFlags ��ʶ @ref messageBox_flag_
//@���� hWndParent �����ھ��
//@���� XCStyle GUI�ⴰ����ʽ,��ʽ��μ��궨�� @ref window_style_
//@���� messageBox_flag_ok:���ȷ����ť�˳�, messageBox_flag_cancel:���ȡ����ť�˳�, messageBox_flag_other:������ʽ�˳�
//@���� �Ų�_��Ϣ��()
XC_API int WINAPI XC_MessageBox(const wchar_t* pTitle, const wchar_t* pText, int nFlags=messageBox_flag_ok | messageBox_flag_icon_info, HWND hWndParent=0, int XCStyle=window_style_modal);
//@��ע �������ڵ��� @ref XModalWnd_DoModal() , �˴�����һ��ģ̬����,
//@���� pTitle ����, �޸���ɫͨ�� ���ñ�����ɫ
//@���� pText �����ı�, �޸���ɫͨ�� ���ô�����ɫ
//@���� nFlags ��ʶ @ref messageBox_flag_
//@���� hWndParent �����ھ��
//@���� XCStyle GUI�ⴰ����ʽ,��ʽ��μ��궨�� @ref window_style_
//@���� ���ش��ھ��, ���� @ref XModalWnd_DoModal() ����ֵ���ж��û������ĸ���ť�˳�
//@���� �Ų���Ϣ��_����()
XC_API HWINDOW WINAPI XMsg_Create(const wchar_t* pTitle, const wchar_t* pText, int nFlags=messageBox_flag_ok | messageBox_flag_icon_info, HWND hWndParent=0, int XCStyle=window_style_modal);
//@��ע �˴�����һ��ģ̬����
//@���� dwExStyle ������չ��ʽ
//@���� dwStyle ������ʽ
//@���� lpClassName ��������
//@���� pTitle ����, �޸���ɫͨ�� ���ñ�����ɫ
//@���� pText �����ı�, �޸���ɫͨ�� ���ô�����ɫ
//@���� nFlags ��ʶ @ref messageBox_flag_
//@���� hWndParent �����ھ��
//@���� XCStyle GUI�ⴰ����ʽ,��ʽ��μ��궨�� @ref window_style_
//@���� ���ش��ھ��,  Ȼ����� @ref XModalWnd_DoModal() ��ʾ, ���� @ref XModalWnd_DoModal() ����ֵ���ж��û������ĸ���ť�˳�
//@���� �Ų���Ϣ��_������չ()
XC_API HWINDOW WINAPI XMsg_CreateEx(DWORD dwExStyle, DWORD dwStyle, const wchar_t* lpClassName, const wchar_t* pTitle, const wchar_t* pText, int nFlags=messageBox_flag_ok | messageBox_flag_icon_info, HWND hWndParent=0, int XCStyle=window_style_modal);
//@��ע ���ú�,����������(��״�ı�)�͹رհ�ť
//@���� hWindow ���ھ��
//@���� bEnable
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� ��������_���ñ���������()
XC_API BOOL WINAPI XFloatWnd_EnableCaptionContent(HWINDOW hWindow, BOOL bEnable);
//@��ע ���ñ�����������Ч
//@���� hWindow ���ھ��
//@���� ������״�ı����
//@���� ��������_ȡ������״�ı�()
XC_API HXCGUI WINAPI XFloatWnd_GetCaptionShapeText(HWINDOW hWindow);
//@��ע ���ñ�����������Ч
//@���� hWindow ���ھ��
//@���� ���عرհ�ť���
//@���� ��������_ȡ����رհ�ť()
XC_API HELE WINAPI XFloatWnd_GetCaptionButtonClose(HWINDOW hWindow);
//@��ע ���ñ�����������Ч
//@���� hWindow ���ھ��
//@���� pTitle �����ı�
//@���� ��������_�ñ���()
XC_API void WINAPI XFloatWnd_SetTitle(HWINDOW hWindow, const wchar_t* pTitle);
//@��ע ���ñ�����������Ч
//@���� hWindow ���ھ��
//@���� �����ı�
//@���� ��������_ȡ����()
XC_API const wchar_t* WINAPI XFloatWnd_GetTitle(HWINDOW hWindow);
//@��ע �����ӳټ���;ͼƬ�ļ�,�б���ģ���ļ�.
//@���� bEnable �Ƿ�����.
//@���� ��Դ_�����ӳټ���()
XC_API void WINAPI XRes_EnableDelayLoad(BOOL bEnable);
//@��ע �����ļ����ػص�����.
//@���� pFun �ص�����.
//@���� ��Դ_���ļ����ػص�()
XC_API void WINAPI XRes_SetLoadFileCallback(funLoadFile pFun);
//@��ע ��ȡ��ԴID����ֵ.
//@���� pName ��ԴID����.
//@���� ��������ֵ.
//@���� ��Դ_ȡIDֵ()
XC_API int WINAPI XRes_GetIDValue(const wchar_t* pName);
//@��ע ������ԴͼƬ.
//@���� pName ��Դ����.
//@���� ����ͼƬ���.
//@���� ��Դ_ȡͼƬ()
XC_API HIMAGE WINAPI XRes_GetImage(const wchar_t* pName);
//@��ע ��ָ������Դ�ļ��в���ͼƬ.
//@���� pFileName ��Դ�ļ���.
//@���� pName ��Դ����.
//@���� ����ͼƬ���.
//@���� ��Դ_ȡͼƬ��չ()
XC_API HIMAGE WINAPI XRes_GetImageEx(const wchar_t* pFileName, const wchar_t* pName);
//@��ע ����Դ�в�����ɫ.
//@���� pName ��Դ����.
//@���� ������ɫֵ.
//@���� ��Դ_ȡ��ɫ()
XC_API COLORREF WINAPI XRes_GetColor(const wchar_t* pName);
//@��ע ����Դ�в�������.
//@���� pName ��Դ����.
//@���� ����������.
//@���� ��Դ_ȡ����()
XC_API HFONTX WINAPI XRes_GetFont(const wchar_t* pName);
//@��ע ����Դ�в��ұ���
//@���� pName ��Դ����.
//@���� ���ر������������.
//@���� ��Դ_ȡ����������()
XC_API HBKM WINAPI XRes_GetBkM(const wchar_t* pName);
//@��ע ����������״����.
//@���� x1 ����.
//@���� y1 ����.
//@���� x2 ����.
//@���� y2 ����.
//@���� hParent ��������.
//@���� ���ؾ��.
//@���� ��״��_����()
XC_API HXCGUI WINAPI XShapeLine_Create(int x1, int y1, int x2, int y2, HXCGUI hParent=NULL);
//@��ע ����λ��.
//@���� hShape ��״������.
//@���� x1 ����.
//@���� y1 ����.
//@���� x2 ����.
//@���� y2 ����.
//@���� ��״��_��λ��()
XC_API void WINAPI XShapeLine_SetPosition(HXCGUI hShape, int x1, int y1, int x2, int y2);
//@��ע ����ֱ����ɫ.
//@���� hShape ��״������.
//@���� color ��ɫֵ, ��ʹ�ú�: RGBA()
//@���� ��״��_����ɫ()
XC_API void WINAPI XShapeLine_SetColor(HXCGUI hShape, COLORREF color);
//@��ע UTF8�ļ�
//@���� pFileName �ļ���
//@���� SVG���
//@���� SVG_���ش��ļ�()
XC_API HSVG WINAPI XSvg_LoadFile(const wchar_t* pFileName);
//@��ע ���ֽ��ַ���ANSI
//@���� pString �ַ���ָ��
//@���� SVG���
//@���� SVG_���ش��ַ���()
XC_API HSVG WINAPI XSvg_LoadString(const char* pString);
//@��ע UNICODE�ַ���
//@���� pString �ַ���ָ��
//@���� SVG���
//@���� SVG_���ش��ַ���W()
XC_API HSVG WINAPI XSvg_LoadStringW(const wchar_t* pString);
//@��ע UTF8�ַ���
//@���� pString �ַ���ָ��
//@���� SVG���
//@���� SVG_���ش��ַ���UTF8()
XC_API HSVG WINAPI XSvg_LoadStringUtf8(const char* pString);
//@���� pZipFileName zip�ļ���
//@���� pFileName svg�ļ���
//@���� pPassword zip����
//@���� SVG���
//@���� SVG_���ش�ZIP()
XC_API HSVG WINAPI XSvg_LoadZip(const wchar_t* pZipFileName, const wchar_t* pFileName, const wchar_t* pPassword=NULL);
//@���� data �ڴ��ָ��
//@���� length �ڴ���С,�ֽ�Ϊ��λ
//@���� pFileName svg�ļ���
//@���� pPassword zip����
//@���� SVG���
//@���� SVG_���ش��ڴ�ZIP()
XC_API HSVG WINAPI XSvg_LoadZipMem(void* data, int length, const wchar_t* pFileName, const wchar_t* pPassword=NULL);
//@��ע RC��Դ���ͱ���Ϊ:"RT_RCDATA"
//@���� id RC��ԴID
//@���� pFileName svg�ļ���
//@���� pPassword zip����
//@���� hModule ģ����
//@���� SVG���
//@���� SVG_���ش���ԴZIP()
XC_API HSVG WINAPI XSvg_LoadZipRes(int id, const wchar_t* pFileName, const wchar_t* pPassword=NULL, HMODULE hModule=NULL);
//@���� id ��ԴID
//@���� pType ��Դ����. ��rc��Դ�ļ���,��Դ������, ����:xcgui.rc, �ü��±��򿪿��Կ�����Դ����; ����:BITMAP, PNG; �μ�MSDN
//@���� hModule ��ָ��ģ�����, ����:DLL, EXE; ���Ϊ��, �ӵ�ǰEXE����
//@���� SVG���
//@���� SVG_���ش���Դ()
XC_API HSVG WINAPI XSvg_LoadRes(int id, const wchar_t* pType, HMODULE hModule=NULL);
//@���� hSvg SVG���
//@���� nWidth ���, �������0,��ô��ԭ��ʼ���
//@���� nHeight �߶�, �������0,��ô��ԭ��ʼ�߶�
//@���� SVG_�ô�С()
XC_API void WINAPI XSvg_SetSize(HSVG hSvg, int nWidth, int nHeight);
//@���� hSvg SVG���
//@���� pWidth ���շ��ؿ��
//@���� pHeight ���շ��ظ߶�
//@���� SVG_ȡ��С()
XC_API void WINAPI XSvg_GetSize(HSVG hSvg, int* pWidth, int* pHeight);
//@���� hSvg SVG���
//@���� ���ؿ��
//@���� SVG_ȡ���()
XC_API int WINAPI XSvg_GetWidth(HSVG hSvg);
//@���� hSvg SVG���
//@���� ���ظ߶�
//@���� SVG_ȡ�߶�()
XC_API int WINAPI XSvg_GetHeight(HSVG hSvg);
//@���� hSvg SVG���
//@���� x x��ƫ��
//@���� y y��ƫ��
//@���� SVG_��ƫ��()
XC_API void WINAPI XSvg_SetPosition(HSVG hSvg, int x, int y);
//@���� hSvg SVG���
//@���� pX x��ƫ��
//@���� pY y��ƫ��
//@���� SVG_ȡƫ��()
XC_API void WINAPI XSvg_GetPosition(HSVG hSvg, int* pX, int* pY);
//@���� hSvg SVG���
//@���� pViewBox ���շ�����ͼ��
//@���� SVG_ȡ��ͼ��()
XC_API void WINAPI XSvg_GetViewBox(HSVG hSvg, RECT* pViewBox);
//@���� hSvg SVG���
//@���� alpha ͸����
//@���� SVG_��͸����()
XC_API void WINAPI XSvg_SetAlpha(HSVG hSvg, BYTE alpha);
//@���� hSvg SVG���
//@���� ����͸����
//@���� SVG_ȡ͸����()
XC_API BYTE WINAPI XSvg_GetAlpha(HSVG hSvg);
//@��ע �û���ɫ������Ĭ����ʽ
//@���� hSvg SVG���
//@���� color ��ɫ
//@���� bEnable �Ƿ���Ч
//@���� SVG_���û������ɫ()
XC_API void WINAPI XSvg_SetUserFillColor(HSVG hSvg, COLORREF color, BOOL bEnable);
//@��ע �û���ɫ������Ĭ����ʽ
//@���� hSvg SVG���
//@���� color ��ɫ
//@���� strokeWidth �ʴ����
//@���� bEnable �Ƿ���Ч
//@���� SVG_���û��ʴ���ɫ()
XC_API void WINAPI XSvg_SetUserStrokeColor(HSVG hSvg, COLORREF color, float strokeWidth, BOOL bEnable);
//@���� hSvg SVG���
//@���� pColor ������ɫֵ
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� SVG_ȡ�û������ɫ()
XC_API BOOL WINAPI XSvg_GetUserFillColor(HSVG hSvg, COLORREF* pColor);
//@���� hSvg SVG���
//@���� pColor ������ɫֵ
//@���� pStrokeWidth
//@���� ����ɹ�����TRUE,���򷵻�FALSE
//@���� SVG_ȡ�û��ʴ���ɫ()
XC_API BOOL WINAPI XSvg_GetUserStrokeColor(HSVG hSvg, COLORREF* pColor, float* pStrokeWidth);
//@��ע Ĭ�����������ĵ���ת, ����ת�Ƕ�Ϊ0ʱ��Ч
//@���� hSvg SVG���
//@���� angle ת�Ƕ�
//@���� SVG_����ת�Ƕ�()
XC_API void WINAPI XSvg_SetRotateAngle(HSVG hSvg, float angle);
//@��ע Ĭ�����������ĵ���ת
//@���� hSvg SVG���
//@���� ������ת�Ƕ�
//@���� SVG_ȡ��ת�Ƕ�()
XC_API float WINAPI XSvg_GetRotateAngle(HSVG hSvg);
//@��ע ����ת�Ƕ�Ϊ0ʱ��Ч
//@���� hSvg SVG���
//@���� angle �Ƕ�
//@���� x ��ת���ĵ�Xƫ��
//@���� y ��ת���ĵ�Yƫ��
//@���� bCenter TRUE:��ת���ĵ��������������ƫ��, FALSE:ʹ�þ�������
//@���� SVG_����ת()
XC_API void WINAPI XSvg_SetRotate(HSVG hSvg, float angle, float x, float y, BOOL bCenter=TRUE);
//@���� hSvg SVG���
//@���� pAngle ���� �Ƕ�
//@���� pX ���� ��ת���ĵ�Xƫ��
//@���� pY ���� ��ת���ĵ�Yƫ��
//@���� pbOffset ���� TRUE:��ת���ĵ��������������ƫ��, FALSE:ʹ�þ�������
//@���� SVG_ȡ��ת()
XC_API void WINAPI XSvg_GetRotate(HSVG hSvg, float* pAngle, float* pX, float* pY, BOOL* pbCenter);
//@��ע ��ʾ������
//@���� hSvg SVG���
//@���� bShow �Ƿ���ʾ
//@���� SVG_��ʾ()
XC_API void WINAPI XSvg_Show(HSVG hSvg, BOOL bShow);
//@���� hSvg SVG���
//@���� bEnable �Ƿ��Զ�����
//@���� SVG_�����Զ�����()
XC_API void WINAPI XSvg_EnableAutoDestroy(HSVG hSvg, BOOL bEnable);
//@���� hSvg SVG���
//@���� SVG_�������ü���()
XC_API void WINAPI XSvg_AddRef(HSVG hSvg);
//@���� hSvg SVG���
//@���� SVG_�ͷ����ü���()
XC_API void WINAPI XSvg_Release(HSVG hSvg);
//@���� hSvg SVG���
//@���� �������ü���
//@���� SVG_ȡ���ü���()
XC_API int WINAPI XSvg_GetRefCount(HSVG hSvg);
//@��ע ǿ������
//@���� hSvg SVG���
//@���� SVG_����()
XC_API void WINAPI XSvg_Destroy(HSVG hSvg);
//@��ע ʹ�û���Ԫ����Ϊ���,����һ��֪ͨ��Ϣ,���������, ͨ���˾���ɶ������
//@���� hWindow ���ھ��
//@���� position λ��; @ref position_flag_top : ����, @ref position_flag_right : �Ҳ�,
//@���� pTitle ����
//@���� pText ����
//@���� hIcon ͼ��
//@���� skin �������
//@���� ����Ԫ�ؾ��
//@���� ֪ͨ��Ϣ_�����е���()
XC_API HELE WINAPI XNotifyMsg_WindowPopup(HWINDOW hWindow, position_flag_ position, const wchar_t* pTitle, const wchar_t* pText, HIMAGE hIcon=NULL, notifyMsg_skin_ skin=notifyMsg_skin_no);
//@��ע ʹ�û���Ԫ����Ϊ���,����һ��֪ͨ��Ϣ,���������, ͨ���˾���ɶ������; ��ָ���Զ����Ⱥ͸߶�
//@���� hWindow ���ھ��
//@���� position λ��;  position_flag_top:����, position_flag_right:�Ҳ�,
//@���� pTitle ����
//@���� pText ����
//@���� hIcon ͼ��
//@���� skin �������
//@���� bBtnClose �Ƿ����ùرհ�ť
//@���� bAutoClose �Ƿ��Զ��ر�
//@���� nWidth �Զ�����, -1(ʹ��Ĭ��ֵ)
//@���� nHeight �Զ���߶�, -1(ʹ��Ĭ��ֵ)
//@���� ����Ԫ�ؾ��
//@���� ֪ͨ��Ϣ_�����е�����չ()
XC_API HELE WINAPI XNotifyMsg_WindowPopupEx(HWINDOW hWindow, position_flag_ position, const wchar_t* pTitle, const wchar_t* pText, HIMAGE hIcon=NULL, notifyMsg_skin_ skin=notifyMsg_skin_no, BOOL bBtnClose=TRUE, BOOL bAutoClose=TRUE, int nWidth=-1, int nHeight=-1);
//@��ע δʵ��,Ԥ���ӿ�
//@���� position
//@���� pTitle
//@���� pText
//@���� hIcon
//@���� skin
//@���� ���ش��ھ��
//@���� ֪ͨ��Ϣ_����()
XC_API HWINDOW WINAPI XNotifyMsg_Popup(position_flag_ position, const wchar_t* pTitle, const wchar_t* pText, HIMAGE hIcon, notifyMsg_skin_ skin=notifyMsg_skin_no);
//@��ע δʵ��,Ԥ���ӿ�
//@���� position
//@���� pTitle
//@���� pText
//@���� hIcon
//@���� skin
//@���� bBtnClose
//@���� bAutoClose
//@���� nWidth
//@���� nHeight
//@���� ���ش��ھ��
//@���� ֪ͨ��Ϣ_������չ()
XC_API HWINDOW WINAPI XNotifyMsg_PopupEx(position_flag_ position, const wchar_t* pTitle, const wchar_t* pText, HIMAGE hIcon=NULL, notifyMsg_skin_ skin=notifyMsg_skin_no, BOOL bBtnClose=TRUE, BOOL bAutoClose=TRUE, int nWidth=-1, int nHeight=-1);
//@���� hWindow ֪ͨ��Ϣ�������ھ��, ���δָ����ô��Ϊ������֪ͨ��Ϣ
//@���� duration ����ʱ��
//@���� ֪ͨ��Ϣ_�ó���ʱ��()
XC_API void WINAPI XNotifyMsg_SetDuration(HWINDOW hWindow, UINT duration);
//@���� hWindow ֪ͨ��Ϣ�������ھ��, ���δָ����ô��Ϊ������֪ͨ��Ϣ
//@���� nHeight �߶�
//@���� ֪ͨ��Ϣ_�ñ���߶�()
XC_API void WINAPI XNotifyMsg_SetCaptionHeight(HWINDOW hWindow, int nHeight);
//@��ע ����Ĭ�Ͽ��
//@���� hWindow ֪ͨ��Ϣ�������ھ��, ���δָ����ô��Ϊ������֪ͨ��Ϣ
//@���� nWidth ���
//@���� ֪ͨ��Ϣ_�ÿ��()
XC_API void WINAPI XNotifyMsg_SetWidth(HWINDOW hWindow, int nWidth);
//@���� hWindow ֪ͨ��Ϣ�������ھ��, ���δָ����ô��Ϊ������֪ͨ��Ϣ
//@���� nSpace �����С
//@���� ֪ͨ��Ϣ_�ü��()
XC_API void WINAPI XNotifyMsg_SetSpace(HWINDOW hWindow, int nSpace);
//@��ע ����֪ͨ��Ϣ���ߴ�С
//@���� hWindow ֪ͨ��Ϣ�������ھ��, ���δָ����ô��Ϊ������֪ͨ��Ϣ
//@���� left ����
//@���� top ����
//@���� right �ұ�
//@���� bottom �ױ�
//@���� ֪ͨ��Ϣ_�ñߴ�С()
XC_API void WINAPI XNotifyMsg_SetBorderSize(HWINDOW hWindow, int left, int top, int right, int bottom);
//@��ע ����֪ͨ��Ϣ�븸������ı߼��
//@���� hWindow ֪ͨ��Ϣ�������ھ��, ���δָ����ô��Ϊ������֪ͨ��Ϣ
//@���� left �����, δʵ��Ԥ������
//@���� top �������
//@���� right �Ҳ���
//@���� bottom �ײ����, δʵ��Ԥ������
//@���� ֪ͨ��Ϣ_�ø��߾�()
XC_API void WINAPI XNotifyMsg_SetParentMargin(HWINDOW hWindow, int left, int top, int right, int bottom);

//@��ע �ж�ָ�����Ƿ�Ϊ��,���п��ܰ����ո��TAB
//@���� hEle Ԫ�ؾ��
//@���� iRow ������
//@���� �ɹ�����TRUE,���򷵻�FALSE
//@���� ����༭��_�Ƿ����()
XC_API BOOL WINAPI XEditor_IsEmptyRow(HELE hEle, int iRow);
//@����}

#endif
