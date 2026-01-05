@echo off
echo ========================================
echo       Starting EMS Website...
echo ========================================

:: 1. Compile & Start Server in Background (New Window)
start "EMS Server" cmd /k "d:\xyz\EMS\run_app.bat"

:: 2. Wait a few seconds for server to start
echo Waiting for server...
timeout /t 5 >nul

:: 3. Open Browser
echo Opening Website...
start http://localhost:9090

exit
