# Employee Management System (EMS) - Setup Guide

## 🚀 Project Overview
This is a full-stack Employee Management System built with:
- **Backend**: Java (Servlets, JDBC)
- **Frontend**: HTML5, CSS3 (Glassmorphism), JavaScript
- **Database**: MySQL

## 📋 Prerequisites
1.  **JDK 8+** installed.
2.  **Apache Tomcat 9+** installed.
3.  **MySQL Server** installed and running.

## 🛠️ Setup Steps

### 1. Database Setup
1.  Open your MySQL Client (Workbench or Command Line).
2.  Run the script located at: `d:/xyz/EMS/database/schema.sql`.
    ```sql
    source d:/xyz/EMS/database/schema.sql;
    ```
    *(This creates the `ems_db` database and necessary tables)*.

### 2. Build the Application
1.  Navigate to the project folder: `d:/xyz/EMS`.
2.  Double-click `build.bat` or run it from command line.
    - If it complains about `servlet-api.jar`, you might need to copy that jar from your Tomcat `lib` folder to the project folder and update the script, OR just ensure your classpath is set.
    - **Alternative**: You can import this folder (`d:/xyz/EMS`) into Eclipse/IntelliJ as a "Dynamic Web Project" and it will handle the build for you.

### 3. Deployment
1.  Copy the generated `build` folder (or the `WebContent` folder if you didn't build classes physically) to your Tomcat `webapps` directory.
2.  Rename the folder to `EMS`.
3.  Start Tomcat: `startup.bat` (inside Tomcat/bin).

## 🔑 Default Login Credentials
- **Admin**:
    - Username: `admin`
    - Password: `admin123`
- **Employee**:
    - You need to add an employee via the database or Admin dashboard first.
