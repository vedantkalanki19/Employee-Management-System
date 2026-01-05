@echo off
echo ========================================
echo       EMS Build Script for Windows
echo ========================================

:: Set your JDK path here if not in PATH
:: set JAVA_HOME=C:\Program Files\Java\jdk-21

echo.
echo [1/4] Cleaning previous build...
if exist "build" rmdir /s /q "build"
mkdir build\WEB-INF\classes

echo.
echo [2/4] Compiling Java Sources...
:: Note: Assumes servlet-api.jar is provided or in CLASSPATH. 
:: Since we don't know where Tomcat is, this might fail if CLASSPATH isn't set.
:: We will attempt to compile assuming standard libraries or user must ensure env is set.
javac -d build\WEB-INF\classes -sourcepath src src\com\ems\model\*.java src\com\ems\util\*.java src\com\ems\service\*.java src\com\ems\controller\*.java
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo [ERROR] Compilation failed. Make sure servlet-api.jar is in your CLASSPATH.
    echo Suggestion: Copy servlet-api.jar from Tomcat/lib to his folder and run:
    echo set CLASSPATH=.;servlet-api.jar;%CLASSPATH%
    pause
    exit /b
)

echo.
echo [3/4] Copying Web Content...
xcopy /E /I /Y WebContent\* build\

echo.
echo [4/4] Build Complete!
echo.
echo ========================================
echo INSTRUCTIONS TO RUN:
echo 1. Copy the 'build' folder to your Tomcat 'webapps' directory.
echo 2. Rename 'build' to 'EMS' (optional, gives URL /EMS).
echo 3. Start Tomcat (bin\startup.bat).
echo 4. Open Browser: http://localhost:8080/EMS
echo ========================================
pause
