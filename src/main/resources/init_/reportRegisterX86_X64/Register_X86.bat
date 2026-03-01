@echo off
chcp 65001 >nul
setlocal enabledelayedexpansion

echo ==========================================
echo    Grid++Report Registration - X86 (32-bit)
echo ==========================================
echo.

:: Check admin rights
net session >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Please run as Administrator!
    pause
    exit /b 1
)

echo [Step 1] Check DLL Files...
echo.

if not exist "gregn6.dll" (
    echo [ERROR] gregn6.dll not found!
    echo Please put gregn6.dll in the same directory
    pause
    exit /b 1
)

if not exist "grdes6.dll" (
    echo [ERROR] grdes6.dll not found!
    echo Please put grdes6.dll in the same directory
    pause
    exit /b 1
)

echo Found: gregn6.dll
echo Found: grdes6.dll

echo.
echo [Step 2] Register DLLs...
echo.

echo Register gregn6.dll ...
regsvr32 /s "gregn6.dll"
if !errorlevel! equ 0 (
    echo   [OK] gregn6.dll
) else (
    echo   [FAIL] gregn6.dll (Error: !errorlevel!)
)

echo Register grdes6.dll ...
regsvr32 /s "grdes6.dll"
if !errorlevel! equ 0 (
    echo   [OK] grdes6.dll
) else (
    echo   [FAIL] grdes6.dll (Error: !errorlevel!)
)

echo.
echo [Step 3] Verify...
echo.

reg query "HKEY_CLASSES_ROOT\CLSID\{F9364159-6AED-4F9C-8BAF-D7C7ED6160A8}" >nul 2>&1
if %errorlevel% equ 0 (
    echo [OK] Registration successful!
) else (
    echo [WARNING] Registration may have failed
)

echo.
echo ==========================================
echo    Registration X86 Complete!
echo ==========================================
pause
