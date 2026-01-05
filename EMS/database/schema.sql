CREATE DATABASE IF NOT EXISTS ems_db;

USE ems_db;

CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'EMPLOYEE') NOT NULL
);

CREATE TABLE IF NOT EXISTS employees (
    emp_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),
    type ENUM('FULL_TIME', 'PART_TIME') NOT NULL,
    basic_pay DECIMAL(10, 2) NOT NULL,
    allowances DECIMAL(10, 2) DEFAULT 0.00,
    join_date DATE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS attendance (
    id INT PRIMARY KEY AUTO_INCREMENT,
    emp_id INT,
    date DATE NOT NULL,
    status ENUM('PRESENT', 'ABSENT', 'LEAVE') NOT NULL,
    hours_worked DECIMAL(4, 2),
    FOREIGN KEY (emp_id) REFERENCES employees (emp_id)
);

CREATE TABLE IF NOT EXISTS salary_slips (
    id INT PRIMARY KEY AUTO_INCREMENT,
    emp_id INT,
    month VARCHAR(20) NOT NULL,
    year INT NOT NULL,
    gross_salary DECIMAL(10, 2),
    deduction DECIMAL(10, 2),
    net_salary DECIMAL(10, 2),
    generated_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (emp_id) REFERENCES employees (emp_id)
);

-- Default Admin
INSERT INTO
    users (username, password, role)
VALUES ('admin', 'admin123', 'ADMIN');