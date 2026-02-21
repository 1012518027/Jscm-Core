#ifndef CAPSTONE_ENGINE_H
#define CAPSTONE_ENGINE_H

/* Capstone Disassembly Engine */
/* By Nguyen Anh Quynh <aquynh@gmail.com>, 2013-2016 */

#ifdef __cplusplus
extern "C" {
#endif

#include <stdarg.h>

#if defined(CAPSTONE_HAS_OSXKERNEL)
#include <libkern/libkern.h>
#else
#include <stdlib.h>
#include <stdio.h>
#endif

#include "platform.h"

#ifdef _MSC_VER
#pragma warning(disable:4201)
#pragma warning(disable:4100)
#define CAPSTONE_API __cdecl
#ifdef CAPSTONE_SHARED
#define CAPSTONE_EXPORT __declspec(dllexport)
#else    // defined(CAPSTONE_STATIC)
#define CAPSTONE_EXPORT
#endif
#else
#define CAPSTONE_API
#if defined(__GNUC__) && !defined(CAPSTONE_STATIC)
#define CAPSTONE_EXPORT __attribute__((visibility("default")))
#else    // defined(CAPSTONE_STATIC)
#define CAPSTONE_EXPORT
#endif
#endif

#ifdef __GNUC__
#define CAPSTONE_DEPRECATED __attribute__((deprecated))
#elif defined(_MSC_VER)
#define CAPSTONE_DEPRECATED __declspec(deprecated)
#else
#pragma message("WARNING: You need to implement CAPSTONE_DEPRECATED for this compiler")
#define CAPSTONE_DEPRECATED
#endif

// Capstone API版本
#define CS_API_MAJOR 4
#define CS_API_MINOR 0

// 版本的最前沿代码的Github的“下一个”分支。
// 如果您想要绝对最新的开发代码，请使用此方法。
// 每当我们有新的重大变化时，这个版本号就会被增加。
#define CS_NEXT_VERSION 5

// Capstone包版本
#define CS_VERSION_MAJOR CS_API_MAJOR
#define CS_VERSION_MINOR CS_API_MINOR
#define CS_VERSION_EXTRA 2

/// 宏创建可以比较的组合版本
/// result of cs_version() API.
#define CS_MAKE_VERSION(major, minor) ((major << 8) + minor)

/// 指令助记符字符串的最大大小。
#define CS_MNEMONIC_SIZE 32

// 处理所有API的使用
typedef size_t csh;

/// 建筑类型
typedef enum cs_arch {
	CS_ARCH_ARM = 0,	///< ARM架构(包括Thumb、Thumb-2)
	CS_ARCH_ARM64,		///< ARM-64，也叫AArch64
	CS_ARCH_MIPS,		///< Mips架构
	CS_ARCH_X86,		///< X86架构(包括X86 & X86 -64)
	CS_ARCH_PPC,		///< PowerPC架构
	CS_ARCH_SPARC,		///< Sparc体系结构
	CS_ARCH_SYSZ,		///< SystemZ架构
	CS_ARCH_XCORE,		///< XCore架构
	CS_ARCH_M68K,		///< 68 k架构
	CS_ARCH_TMS320C64X,	///< TMS320C64x架构
	CS_ARCH_M680X,		///< 680X 架构
	CS_ARCH_EVM,		///< Ethereum 架构
	CS_ARCH_MAX,
	CS_ARCH_ALL = 0xFFFF, // 所有体系结构——用于cs_support()
} cs_arch;

// 支持值验证发动机的饮食模式。
// 如果cs_support(CS_SUPPORT_DIET)返回True，则编译引擎已完成
// 在饮食模式下。
#define CS_SUPPORT_DIET (CS_ARCH_ALL + 1)

//验证引擎X86精简模式的支持值。

//如果cs_support(CS_SUPPORT_X86_REDUCE)返回True，表示引擎已编译

//在X86减少模式。
#define CS_SUPPORT_X86_REDUCE (CS_ARCH_ALL + 2)

/// Mode type
typedef enum cs_mode {
	CS_MODE_LITTLE_ENDIAN = 0,	///< 小端模式(默认模式)
	CS_MODE_ARM = 0,	///< 32-bit ARM
	CS_MODE_16 = 1 << 1,	///< 16位模式(X86)
	CS_MODE_32 = 1 << 2,	///< 32位模式(X86)
	CS_MODE_64 = 1 << 3,	///< 64位模式(X86,PPC)
	CS_MODE_THUMB = 1 << 4,	///< ARM的拇指模式，包括拇指-2
	CS_MODE_MCLASS = 1 << 5,	///< ARM的Cortex-M系列
	CS_MODE_V8 = 1 << 6,	///< ARMv8 A32 encoding ARM
	CS_MODE_MICRO = 1 << 4, ///< MicroMips模式(MIPS)
	CS_MODE_MIPS3 = 1 << 5, ///< Mips III ISA
	CS_MODE_MIPS32R6 = 1 << 6, ///< Mips32r6 ISA
	CS_MODE_MIPS2 = 1 << 7, ///< Mips II ISA
	CS_MODE_V9 = 1 << 4, ///< SparcV9模式(Sparc)
	CS_MODE_QPX = 1 << 4, ///< 四处理扩展模式(PPC)
	CS_MODE_M68K_000 = 1 << 1, ///< M68K 68000模式
	CS_MODE_M68K_010 = 1 << 2, ///< M68K 68010模式
	CS_MODE_M68K_020 = 1 << 3, ///< M68K 68020 模式
	CS_MODE_M68K_030 = 1 << 4, ///< M68K 68030 模式
	CS_MODE_M68K_040 = 1 << 5, ///< M68K 68040 模式
	CS_MODE_M68K_060 = 1 << 6, ///< M68K 68060 模式
	CS_MODE_BIG_ENDIAN = 1 << 31,	///<高位优先模式
	CS_MODE_MIPS32 = CS_MODE_32,	///< Mips32 ISA (Mips)
	CS_MODE_MIPS64 = CS_MODE_64,	///< Mips64 ISA (Mips)
	CS_MODE_M680X_6301 = 1 << 1, ///< M680X日立6301、6303模式
	CS_MODE_M680X_6309 = 1 << 2, ///< M680X Hitachi 6309 mode
	CS_MODE_M680X_6800 = 1 << 3, ///< M680X Motorola 6800,6802 mode
	CS_MODE_M680X_6801 = 1 << 4, ///< M680X Motorola 6801,6803 mode
	CS_MODE_M680X_6805 = 1 << 5, ///< M680X Motorola/Freescale 6805 mode
	CS_MODE_M680X_6808 = 1 << 6, ///< M680X Motorola/Freescale/NXP 68HC08 mode
	CS_MODE_M680X_6809 = 1 << 7, ///< M680X Motorola 6809 mode
	CS_MODE_M680X_6811 = 1 << 8, ///< M680X Motorola/Freescale/NXP 68HC11 mode
	CS_MODE_M680X_CPU12 = 1 << 9, ///< M680X Motorola/Freescale/NXP CPU12
					///< used on M68HC12/HCS12
	CS_MODE_M680X_HCS08 = 1 << 10, ///< M680X Freescale/NXP HCS08 mode
} cs_mode;

typedef void* (CAPSTONE_API *cs_malloc_t)(size_t size);
typedef void* (CAPSTONE_API *cs_calloc_t)(size_t nmemb, size_t size);
typedef void* (CAPSTONE_API *cs_realloc_t)(void *ptr, size_t size);
typedef void (CAPSTONE_API *cs_free_t)(void *ptr);
typedef int (CAPSTONE_API *cs_vsnprintf_t)(char *str, size_t size, const char *format, va_list ap);

///用户自定义动态内存相关函数:malloc/calloc/realloc/free/vsnprintf()

///默认情况下，Capstone使用系统的malloc()， calloc()， realloc()， free()和vsnprintf()。
typedef struct cs_opt_mem {
	cs_malloc_t malloc;
	cs_calloc_t calloc;
	cs_realloc_t realloc;
	cs_free_t free;
	cs_vsnprintf_t vsnprintf;
} cs_opt_mem;

///为具有可选名称的指令定制助记符。

///将现有的自定义指令重置为默认助记符，

///使用相同的@id和NULL值再次调用cs_option(CS_OPT_MNEMONIC)

///为@mnemonic。
typedef struct cs_opt_mnem {
	/// 定制的指示ID。
	unsigned int id;
	/// 自定义指令助记符。
	const char *mnemonic;
} cs_opt_mnem;

/// 拆卸发动机的运行时选项
typedef enum cs_opt_type {
	CS_OPT_INVALID = 0,	///<没有指定选项
	CS_OPT_SYNTAX,	///< 程序集输出语法
	CS_OPT_DETAIL,	///< 将指令结构分解成细节
	CS_OPT_MODE,	///< 在运行时更改引擎模式
	CS_OPT_MEM,	///< 用户定义的动态内存相关功能
	CS_OPT_SKIPDATA, ///< 拆卸时跳过数据。此时引擎处于SKIPDATA模式。
	CS_OPT_SKIPDATA_SETUP, ///< 为SKIPDATA选项设置用户定义函数
	CS_OPT_MNEMONIC, ///< 自定义指令助记符
	CS_OPT_UNSIGNED, ///< 以无符号形式打印立即操作数
} cs_opt_type;

/// 运行时选项值(与上面的选项类型相关联)
typedef enum cs_opt_value {
	CS_OPT_OFF = 0,  ///< 关闭一个选项——默认为CS_OPT_DETAIL, CS_OPT_SKIPDATA, CS_OPT_UNSIGNED。
	CS_OPT_ON = 3, ///< 打开一个选项(CS_OPT_DETAIL, CS_OPT_SKIPDATA)。
	CS_OPT_SYNTAX_DEFAULT = 0, ///< 默认asm语法(CS_OPT_SYNTAX)。
	CS_OPT_SYNTAX_INTEL, ///< X86 Intel asm语法- X86默认(CS_OPT_SYNTAX)。
	CS_OPT_SYNTAX_ATT,   ///< X86 ATT asm语法(CS_OPT_SYNTAX)。
	CS_OPT_SYNTAX_NOREGNAME, ///< 打印只带数字的寄存器名
	CS_OPT_SYNTAX_MASM, ///< X86 Intel Masm语法(CS_OPT_SYNTAX)。
} cs_opt_value;

/// 通用指令操作数类型——在所有体系结构中保持一致。
typedef enum cs_op_type {
	CS_OP_INVALID = 0,  ///< 未初始化/无效的操作数。
	CS_OP_REG,          ///< 寄存器操作数。
	CS_OP_IMM,          ///< 立即操作数。
	CS_OP_MEM,          ///< 内存操作数。
	CS_OP_FP,           ///< 浮点操作数。
} cs_op_type;

///通用指令操作数访问类型——在所有体系结构中保持一致。

///可以组合访问类型，例如:CS_AC_READ | CS_AC_WRITE
typedef enum cs_ac_type {
	CS_AC_INVALID = 0,        ///< 未初始化/无效的访问类型。
	CS_AC_READ    = 1 << 0,   ///< 从内存或寄存器读取操作数。
	CS_AC_WRITE   = 1 << 1,   ///< 操作数写入内存或寄存器。
} cs_ac_type;

/// 公共指令组——在所有体系结构中保持一致。
typedef enum cs_group_type {
	CS_GRP_INVALID = 0,  ///< 未初始化/无效组。
	CS_GRP_JUMP,    ///< 所有跳转指令(条件+直接+间接跳转)
	CS_GRP_CALL,    ///< 所有呼叫指示
	CS_GRP_RET,     ///< 所有退货指示
	CS_GRP_INT,     ///< 所有中断指令(int+syscall)
	CS_GRP_IRET,    ///< 所有中断返回指令
	CS_GRP_PRIVILEGE,    ///< 所有特权指示
	CS_GRP_BRANCH_RELATIVE, ///< 所有相关分支指令
} cs_group_type;

/*

SKIPDATA选项的用户定义回调函数。

有关演示此API的示例代码，请参阅tests / test_skiipdata.c。



@code:输入缓冲区包含要被分解的代码。

这是传递给cs_disasm()的相同缓冲区。

@code_size : 上述@code缓冲区的大小(以字节为单位)。

@offset:当前检查字节在输入中的位置

上面提到的缓冲区@代码。

@user_data:通过@user_data字段传递给cs_option()的用户数据

下面的Cs_opt_skipdata结构。



@return:返回要跳过的字节数，或0立即停止反汇编。

*/
typedef size_t (CAPSTONE_API *cs_skipdata_cb_t)(const uint8_t *code, size_t code_size, size_t offset, void *user_data);

/// SKIPDATA选项的用户自定义设置
typedef struct cs_opt_skipdata {
/// Capstone将跳过的数据视为特殊的“指令”。

///用户可以在这里指定该指令的“助记符”字符串。

///默认情况下(如果@mnemonic为NULL)，顶点石使用".byte"。
	const char *mnemonic;

	/// Capstone命中数据时调用的用户定义回调函数。

	///如果这个回调函数的返回值是正的(&gt;0)，则Capstone .

	///将跳过这个字节数并继续。否则,如果

	///回调函数返回0,Capstone停止分解并返回

	///立即从cs_disasm()

	///注意:如果这个回调指针是NULL, Capstone将跳过一个数字

	///字节取决于架构，如下所示:

	/// Arm: 2字节(拇指模式)或4字节。

	/// Arm64: 4字节。

	/// Mips: 4字节。

	/// M680x: 1字节。

	/// PowerPC: 4字节。

	/// Sparc: 4字节。

	/// SystemZ: 2字节。

	/// X86: 1字节。

	/// XCore: 2字节。

	/// EVM: 1字节。
	cs_skipdata_cb_t callback; 	// 默认值为NULL

	/// 用户定义的数据要传递给@callback函数指针。
	void *user_data;
} cs_opt_skipdata;


#include "arm.h"
#include "arm64.h"
#include "m68k.h"
#include "mips.h"
#include "ppc.h"
#include "sparc.h"
#include "systemz.h"
#include "x86.h"
#include "xcore.h"
#include "tms320c64x.h"
#include "m680x.h"
#include "evm.h"

///注意:只有当CS_OPT_DETAIL = CS_OPT_ON时，cs_detail中的所有信息才可用

///初始化为memset(。， 0, offset (cs_detail, ARCH)+sizeof(cs_ARCH))

///通过arch_gestruction在arch/ arch/ archdisassembly .c

///如果cs_detail发生变化，特别是如果在联合之后添加了一个字段，

///然后更新arch/ arch/ archdisassembly .c
typedef struct cs_detail {
	uint16_t regs_read[12]; ///< 由此insn读取的隐式寄存器列表
	uint8_t regs_read_count; ///< 此insn读取的隐式寄存器的数目

	uint16_t regs_write[20]; ///< 由此insn修改的隐式寄存器列表
	uint8_t regs_write_count; ///< 此insn修改的隐式寄存器的数目

	uint8_t groups[8]; ///< 此指令所属的组列表
	uint8_t groups_count; ///< 此insn所属的组数

	/// 特定于体系结构的指令信息
	union {
		cs_x86 x86;     ///< X86架构，包括16位、32位和64位模式
		cs_arm64 arm64; ///< ARM64 architecture (aka AArch64)
		cs_arm arm;     ///< ARM architecture (including Thumb/Thumb2)
		cs_m68k m68k;   ///< M68K architecture
		cs_mips mips;   ///< MIPS architecture
		cs_ppc ppc;	    ///< PowerPC architecture
		cs_sparc sparc; ///< Sparc architecture
		cs_sysz sysz;   ///< SystemZ architecture
		cs_xcore xcore; ///< XCore architecture
		cs_tms320c64x tms320c64x;  ///< TMS320C64x architecture
		cs_m680x m680x; ///< M680X architecture
		cs_evm evm;	    ///< Ethereum architecture
	};
} cs_detail;

/// Detail information of disassembled instruction
typedef struct cs_insn {
	/// Instruction ID (basically a numeric ID for the instruction mnemonic)
	/// Find the instruction id in the '[ARCH]_insn' enum in the header file
	/// of corresponding architecture, such as 'arm_insn' in arm.h for ARM,
	/// 'x86_insn' in x86.h for X86, etc...
	/// This information is available even when CS_OPT_DETAIL = CS_OPT_OFF
	/// NOTE: in Skipdata mode, "data" instruction has 0 for this id field.
	unsigned int id;

	/// Address (EIP) of this instruction
	/// This information is available even when CS_OPT_DETAIL = CS_OPT_OFF
	uint64_t address;

	/// Size of this instruction
	/// This information is available even when CS_OPT_DETAIL = CS_OPT_OFF
	uint16_t size;

	/// Machine bytes of this instruction, with number of bytes indicated by @size above
	/// This information is available even when CS_OPT_DETAIL = CS_OPT_OFF
	uint8_t bytes[16];

	/// Ascii text of instruction mnemonic
	/// This information is available even when CS_OPT_DETAIL = CS_OPT_OFF
	char mnemonic[CS_MNEMONIC_SIZE];

	/// Ascii text of instruction operands
	/// This information is available even when CS_OPT_DETAIL = CS_OPT_OFF
	char op_str[160];

	/// Pointer to cs_detail.
	/// NOTE: detail pointer is only valid when both requirements below are met:
	/// (1) CS_OP_DETAIL = CS_OPT_ON
	/// (2) Engine is not in Skipdata mode (CS_OP_SKIPDATA option set to CS_OPT_ON)
	///
	/// NOTE 2: when in Skipdata mode, or when detail mode is OFF, even if this pointer
	///     is not NULL, its content is still irrelevant.
	cs_detail *detail;
} cs_insn;


/// Calculate the offset of a disassembled instruction in its buffer, given its position
/// in its array of disassembled insn
/// NOTE: this macro works with position (>=1), not index
#define CS_INSN_OFFSET(insns, post) (insns[post - 1].address - insns[0].address)


/// All type of errors encountered by Capstone API.
/// These are values returned by cs_errno()
typedef enum cs_err {
	CS_ERR_OK = 0,   ///< No error: everything was fine
	CS_ERR_MEM,      ///< Out-Of-Memory error: cs_open(), cs_disasm(), cs_disasm_iter()
	CS_ERR_ARCH,     ///< Unsupported architecture: cs_open()
	CS_ERR_HANDLE,   ///< Invalid handle: cs_op_count(), cs_op_index()
	CS_ERR_CSH,      ///< Invalid csh argument: cs_close(), cs_errno(), cs_option()
	CS_ERR_MODE,     ///< Invalid/unsupported mode: cs_open()
	CS_ERR_OPTION,   ///< Invalid/unsupported option: cs_option()
	CS_ERR_DETAIL,   ///< Information is unavailable because detail option is OFF
	CS_ERR_MEMSETUP, ///< Dynamic memory management uninitialized (see CS_OPT_MEM)
	CS_ERR_VERSION,  ///< Unsupported version (bindings)
	CS_ERR_DIET,     ///< Access irrelevant data in "diet" engine
	CS_ERR_SKIPDATA, ///< Access irrelevant data for "data" instruction in SKIPDATA mode
	CS_ERR_X86_ATT,  ///< X86 AT&T syntax is unsupported (opt-out at compile time)
	CS_ERR_X86_INTEL, ///< X86 Intel syntax is unsupported (opt-out at compile time)
	CS_ERR_X86_MASM, ///< X86 Masm syntax is unsupported (opt-out at compile time)
} cs_err;

/**
 Return combined API version & major and minor version numbers.

 @major: major number of API version
 @minor: minor number of API version

 @return hexical number as (major << 8 | minor), which encodes both
	 major & minor versions.
	 NOTE: This returned value can be compared with version number made
	 with macro CS_MAKE_VERSION

 For example, second API version would return 1 in @major, and 1 in @minor
 The return value would be 0x0101

 NOTE: if you only care about returned value, but not major and minor values,
 set both @major & @minor arguments to NULL.
*/
CAPSTONE_EXPORT
unsigned int CAPSTONE_API cs_version(int *major, int *minor);


/**
 This API can be used to either ask for archs supported by this library,
 or check to see if the library was compile with 'diet' option (or called
 in 'diet' mode).

 To check if a particular arch is supported by this library, set @query to
 arch mode (CS_ARCH_* value).
 To verify if this library supports all the archs, use CS_ARCH_ALL.

 To check if this library is in 'diet' mode, set @query to CS_SUPPORT_DIET.

 @return True if this library supports the given arch, or in 'diet' mode.
*/
CAPSTONE_EXPORT
bool CAPSTONE_API cs_support(int query);

/**
 Initialize CS handle: this must be done before any usage of CS.

 @arch: architecture type (CS_ARCH_*)
 @mode: hardware mode. This is combined of CS_MODE_*
 @handle: pointer to handle, which will be updated at return time

 @return CS_ERR_OK on success, or other value on failure (refer to cs_err enum
 for detailed error).
*/
CAPSTONE_EXPORT
cs_err CAPSTONE_API cs_open(cs_arch arch, cs_mode mode, csh *handle);

/**
 Close CS handle: MUST do to release the handle when it is not used anymore.
 NOTE: this must be only called when there is no longer usage of Capstone,
 not even access to cs_insn array. The reason is the this API releases some
 cached memory, thus access to any Capstone API after cs_close() might crash
 your application.

 In fact,this API invalidate @handle by ZERO out its value (i.e *handle = 0).

 @handle: pointer to a handle returned by cs_open()

 @return CS_ERR_OK on success, or other value on failure (refer to cs_err enum
 for detailed error).
*/
CAPSTONE_EXPORT
cs_err CAPSTONE_API cs_close(csh *handle);

/**
 Set option for disassembling engine at runtime

 @handle: handle returned by cs_open()
 @type: type of option to be set
 @value: option value corresponding with @type

 @return: CS_ERR_OK on success, or other value on failure.
 Refer to cs_err enum for detailed error.

 NOTE: in the case of CS_OPT_MEM, handle's value can be anything,
 so that cs_option(handle, CS_OPT_MEM, value) can (i.e must) be called
 even before cs_open()
*/
CAPSTONE_EXPORT
cs_err CAPSTONE_API cs_option(csh handle, cs_opt_type type, size_t value);

/**
 Report the last error number when some API function fail.
 Like glibc's errno, cs_errno might not retain its old value once accessed.

 @handle: handle returned by cs_open()

 @return: error code of cs_err enum type (CS_ERR_*, see above)
*/
CAPSTONE_EXPORT
cs_err CAPSTONE_API cs_errno(csh handle);


/**
 Return a string describing given error code.

 @code: error code (see CS_ERR_* above)

 @return: returns a pointer to a string that describes the error code
	passed in the argument @code
*/
CAPSTONE_EXPORT
const char * CAPSTONE_API cs_strerror(cs_err code);

/**
 反汇编二进制代码，给定代码缓冲区、大小、地址和编号

需要解码的指令。

这个API动态分配内存来包含被分解的指令。

生成的指令将被放入@*insn中



注1:这个API将自动决定需要包含的内存

在@insn中输出反汇编指令。



注2:调用者必须自己释放分配的内存，以避免内存泄漏。



注3:对于需要动态分配内存的系统，如

操作系统内核或固件，API cs_disasm_iter()可能是一个更好的选择

cs_disasm()。原因是使用cs_disasm()，基于有限的可用性

内存，我们必须提前计算要分解多少个指令，

这就让事情变得复杂了。这对于@count=0的情况尤其麻烦，

当cs_disasm()不受控制地运行时(直到输入缓冲区的任何一端，或

当它遇到无效指令时)。



cs_open()返回的句柄

@code:包含原始二进制代码的缓冲区。

@code_size:上述代码缓冲区的大小。

@address:给定原始代码缓冲区中第一条指令的地址。

由这个API填充的指令数组。

注意:@insn将由这个函数分配，并且应该被释放

使用cs_free() API。

@count:要分解的指令数量，或者0表示得到所有指令



@return:成功分解指令的数量，

如果此函数未能分解给定的代码，则为0



失败时，调用cs_errno()获取错误代码。
*/
CAPSTONE_EXPORT
size_t CAPSTONE_API cs_disasm(csh handle,
		const uint8_t *code, size_t code_size,
		uint64_t address,
		size_t count,
		cs_insn **insn);

/**
  Deprecated function - to be retired in the next version!
  Use cs_disasm() instead of cs_disasm_ex()
*/
CAPSTONE_EXPORT
CAPSTONE_DEPRECATED
size_t CAPSTONE_API cs_disasm_ex(csh handle,
		const uint8_t *code, size_t code_size,
		uint64_t address,
		size_t count,
		cs_insn **insn);

/**
 Free memory allocated by cs_malloc() or cs_disasm() (argument @insn)

 @insn: pointer returned by @insn argument in cs_disasm() or cs_malloc()
 @count: number of cs_insn structures returned by cs_disasm(), or 1
     to free memory allocated by cs_malloc().
*/
CAPSTONE_EXPORT
void CAPSTONE_API cs_free(cs_insn *insn, size_t count);


/**
 Allocate memory for 1 instruction to be used by cs_disasm_iter().

 @handle: handle returned by cs_open()

 NOTE: when no longer in use, you can reclaim the memory allocated for
 this instruction with cs_free(insn, 1)
*/
CAPSTONE_EXPORT
cs_insn * CAPSTONE_API cs_malloc(csh handle);

/**
 Fast API to disassemble binary code, given the code buffer, size, address
 and number of instructions to be decoded.
 This API puts the resulting instruction into a given cache in @insn.
 See tests/test_iter.c for sample code demonstrating this API.

 NOTE 1: this API will update @code, @size & @address to point to the next
 instruction in the input buffer. Therefore, it is convenient to use
 cs_disasm_iter() inside a loop to quickly iterate all the instructions.
 While decoding one instruction at a time can also be achieved with
 cs_disasm(count=1), some benchmarks shown that cs_disasm_iter() can be 30%
 faster on random input.

 NOTE 2: the cache in @insn can be created with cs_malloc() API.

 NOTE 3: for system with scarce memory to be dynamically allocated such as
 OS kernel or firmware, this API is recommended over cs_disasm(), which
 allocates memory based on the number of instructions to be disassembled.
 The reason is that with cs_disasm(), based on limited available memory,
 we have to calculate in advance how many instructions to be disassembled,
 which complicates things. This is especially troublesome for the case
 @count=0, when cs_disasm() runs uncontrollably (until either end of input
 buffer, or when it encounters an invalid instruction).
 
 @handle: handle returned by cs_open()
 @code: buffer containing raw binary code to be disassembled
 @size: size of above code
 @address: address of the first insn in given raw code buffer
 @insn: pointer to instruction to be filled in by this API.

 @return: true if this API successfully decode 1 instruction,
 or false otherwise.

 On failure, call cs_errno() for error code.
*/
CAPSTONE_EXPORT
bool CAPSTONE_API cs_disasm_iter(csh handle,
	const uint8_t **code, size_t *size,
	uint64_t *address, cs_insn *insn);

/**
 Return friendly name of register in a string.
 Find the instruction id from header file of corresponding architecture (arm.h for ARM,
 x86.h for X86, ...)

 WARN: when in 'diet' mode, this API is irrelevant because engine does not
 store register name.

 @handle: handle returned by cs_open()
 @reg_id: register id

 @return: string name of the register, or NULL if @reg_id is invalid.
*/
CAPSTONE_EXPORT
const char * CAPSTONE_API cs_reg_name(csh handle, unsigned int reg_id);

/**
 Return friendly name of an instruction in a string.
 Find the instruction id from header file of corresponding architecture (arm.h for ARM, x86.h for X86, ...)

 WARN: when in 'diet' mode, this API is irrelevant because the engine does not
 store instruction name.

 @handle: handle returned by cs_open()
 @insn_id: instruction id

 @return: string name of the instruction, or NULL if @insn_id is invalid.
*/
CAPSTONE_EXPORT
const char * CAPSTONE_API cs_insn_name(csh handle, unsigned int insn_id);

/**
 Return friendly name of a group id (that an instruction can belong to)
 Find the group id from header file of corresponding architecture (arm.h for ARM, x86.h for X86, ...)

 WARN: when in 'diet' mode, this API is irrelevant because the engine does not
 store group name.

 @handle: handle returned by cs_open()
 @group_id: group id

 @return: string name of the group, or NULL if @group_id is invalid.
*/
CAPSTONE_EXPORT
const char * CAPSTONE_API cs_group_name(csh handle, unsigned int group_id);

/**
 Check if a disassembled instruction belong to a particular group.
 Find the group id from header file of corresponding architecture (arm.h for ARM, x86.h for X86, ...)
 Internally, this simply verifies if @group_id matches any member of insn->groups array.

 NOTE: this API is only valid when detail option is ON (which is OFF by default).

 WARN: when in 'diet' mode, this API is irrelevant because the engine does not
 update @groups array.

 @handle: handle returned by cs_open()
 @insn: disassembled instruction structure received from cs_disasm() or cs_disasm_iter()
 @group_id: group that you want to check if this instruction belong to.

 @return: true if this instruction indeed belongs to the given group, or false otherwise.
*/
CAPSTONE_EXPORT
bool CAPSTONE_API cs_insn_group(csh handle, const cs_insn *insn, unsigned int group_id);

/**
 Check if a disassembled instruction IMPLICITLY used a particular register.
 Find the register id from header file of corresponding architecture (arm.h for ARM, x86.h for X86, ...)
 Internally, this simply verifies if @reg_id matches any member of insn->regs_read array.

 NOTE: this API is only valid when detail option is ON (which is OFF by default)

 WARN: when in 'diet' mode, this API is irrelevant because the engine does not
 update @regs_read array.

 @insn: disassembled instruction structure received from cs_disasm() or cs_disasm_iter()
 @reg_id: register that you want to check if this instruction used it.

 @return: true if this instruction indeed implicitly used the given register, or false otherwise.
*/
CAPSTONE_EXPORT
bool CAPSTONE_API cs_reg_read(csh handle, const cs_insn *insn, unsigned int reg_id);

/**
 Check if a disassembled instruction IMPLICITLY modified a particular register.
 Find the register id from header file of corresponding architecture (arm.h for ARM, x86.h for X86, ...)
 Internally, this simply verifies if @reg_id matches any member of insn->regs_write array.

 NOTE: this API is only valid when detail option is ON (which is OFF by default)

 WARN: when in 'diet' mode, this API is irrelevant because the engine does not
 update @regs_write array.

 @insn: disassembled instruction structure received from cs_disasm() or cs_disasm_iter()
 @reg_id: register that you want to check if this instruction modified it.

 @return: true if this instruction indeed implicitly modified the given register, or false otherwise.
*/
CAPSTONE_EXPORT
bool CAPSTONE_API cs_reg_write(csh handle, const cs_insn *insn, unsigned int reg_id);

/**
 Count the number of operands of a given type.
 Find the operand type in header file of corresponding architecture (arm.h for ARM, x86.h for X86, ...)

 NOTE: this API is only valid when detail option is ON (which is OFF by default)

 @handle: handle returned by cs_open()
 @insn: disassembled instruction structure received from cs_disasm() or cs_disasm_iter()
 @op_type: Operand type to be found.

 @return: number of operands of given type @op_type in instruction @insn,
 or -1 on failure.
*/
CAPSTONE_EXPORT
int CAPSTONE_API cs_op_count(csh handle, const cs_insn *insn, unsigned int op_type);

/**
 Retrieve the position of operand of given type in <arch>.operands[] array.
 Later, the operand can be accessed using the returned position.
 Find the operand type in header file of corresponding architecture (arm.h for ARM, x86.h for X86, ...)

 NOTE: this API is only valid when detail option is ON (which is OFF by default)

 @handle: handle returned by cs_open()
 @insn: disassembled instruction structure received from cs_disasm() or cs_disasm_iter()
 @op_type: Operand type to be found.
 @position: position of the operand to be found. This must be in the range
			[1, cs_op_count(handle, insn, op_type)]

 @return: index of operand of given type @op_type in <arch>.operands[] array
 in instruction @insn, or -1 on failure.
*/
CAPSTONE_EXPORT
int CAPSTONE_API cs_op_index(csh handle, const cs_insn *insn, unsigned int op_type,
		unsigned int position);

/// Type of array to keep the list of registers
typedef uint16_t cs_regs[64];

/**
 Retrieve all the registers accessed by an instruction, either explicitly or
 implicitly.

 WARN: when in 'diet' mode, this API is irrelevant because engine does not
 store registers.

 @handle: handle returned by cs_open()
 @insn: disassembled instruction structure returned from cs_disasm() or cs_disasm_iter()
 @regs_read: on return, this array contains all registers read by instruction.
 @regs_read_count: number of registers kept inside @regs_read array.
 @regs_write: on return, this array contains all registers written by instruction.
 @regs_write_count: number of registers kept inside @regs_write array.

 @return CS_ERR_OK on success, or other value on failure (refer to cs_err enum
 for detailed error).
*/
CAPSTONE_EXPORT
cs_err CAPSTONE_API cs_regs_access(csh handle, const cs_insn *insn,
		cs_regs regs_read, uint8_t *regs_read_count,
		cs_regs regs_write, uint8_t *regs_write_count);

#ifdef __cplusplus
}
#endif

#endif
