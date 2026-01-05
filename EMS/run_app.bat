@echo off
echo ========================================
echo       EMS Standalone Runner (H2 Edition)
echo ========================================

:: 1. Cleanup
if exist "out" rmdir /s /q "out"
mkdir out

:: 2. Find Database Driver
:: User must have H2 jar in lib folder
set DB_JAR=lib\h2-2.3.232.jar

if not exist "%DB_JAR%" (
    echo [ERROR] H2 Database JAR not found at %DB_JAR%
    echo Please download h2.jar from https://h2database.com and put it in 'lib' folder.
    echo Rename it to 'h2-2.3.232.jar' or update this script.
    pause
    exit /b
)

:: 3. Compile
echo [Compiling...]
javac -d out -sourcepath src -cp "%DB_JAR%" src\com\ems\server\*.java src\com\ems\util\*.java src\com\ems\model\*.java
if %ERRORLEVEL% NEQ 0 (
    echo [ERROR] Compilation failed.
    pause
    exit /b
)

:: 4. Run
echo.
echo [Running Server...]
echo Open http://localhost:9090 in your browser.
echo Press Ctrl+C to stop.
echo.
java -cp "out;%DB_JAR%" com.ems.server.ServerMain
pause
