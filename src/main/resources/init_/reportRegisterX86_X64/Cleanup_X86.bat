@echo off
chcp 65001 >nul
setlocal enabledelayedexpansion

echo ==========================================
echo    Grid++Report Cleanup - X86 (32-bit)
echo ==========================================
echo.

:: Check admin rights
net session >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Please run as Administrator!
    pause
    exit /b 1
)

echo [Step 1] Unregister X86 DLLs...
echo.

regsvr32 /u /s "%SystemRoot%\System32\gregn6.dll" 2>nul
regsvr32 /u /s "gregn6.dll" 2>nul
regsvr32 /u /s ".\gregn6.dll" 2>nul
regsvr32 /u /s "%SystemRoot%\System32\grdes6.dll" 2>nul
regsvr32 /u /s "grdes6.dll" 2>nul
regsvr32 /u /s ".\grdes6.dll" 2>nul

echo [Step 2] Delete Registry Entries...
echo.

reg delete "HKEY_CLASSES_ROOT\CLSID\{F9364159-6AED-4F9C-8BAF-D7C7ED6160A8}" /f 2>nul
reg delete "HKEY_CLASSES_ROOT\CLSID\{1B5EA181-A38D-4F42-88B2-6AF74CF6D6C0}" /f 2>nul
reg delete "HKEY_CLASSES_ROOT\CLSID\{44CBB5DE-5AFB-4C3D-8F3F-0F70CA5372AD}" /f 2>nul
reg delete "HKEY_CLASSES_ROOT\CLSID\{6EDD80CB-9F08-4C71-B406-479E5CB80FCE}" /f 2>nul
reg delete "HKEY_CLASSES_ROOT\CLSID\{7FD5DC62-DED0-4138-9C48-55F0A0FE7B66}" /f 2>nul
reg delete "HKEY_CLASSES_ROOT\CLSID\{6CA58CB2-2AD1-4AD0-B3CC-5F5C000BBDEE}" /f 2>nul
reg delete "HKEY_CLASSES_ROOT\gregn.GridppReport.6" /f 2>nul
reg delete "HKEY_CLASSES_ROOT\gregn.GRDisplayViewer.6" /f 2>nul
reg delete "HKEY_CLASSES_ROOT\gregn.GRDisplayViewerProps.6" /f 2>nul
reg delete "HKEY_CLASSES_ROOT\gregn.GRPrintViewer.6" /f 2>nul
reg delete "HKEY_CLASSES_ROOT\gregn.GRPrintViewerProps.6" /f 2>nul
reg delete "HKEY_CLASSES_ROOT\grdes.GRDesigner.6" /f 2>nul
reg delete "HKEY_CLASSES_ROOT\TypeLib\{4018F953-1BFE-441E-8A04-DC8BA1FF060E}" /f 2>nul
reg delete "HKEY_CLASSES_ROOT\TypeLib\{C5A16330-A084-48C9-BC0F-0D0B37A14123}" /f 2>nul

echo [Step 3] Delete DLL Files...
echo.

del /f /q "%SystemRoot%\System32\gregn6.dll" 2>nul
del /f /q "%SystemRoot%\System32\grdes6.dll" 2>nul
::del /f /q "gregn6.dll" 2>nul
::del /f /q "grdes6.dll" 2>nul

echo.
echo ==========================================
echo    Cleanup X86 Complete!
echo ==========================================
pause
