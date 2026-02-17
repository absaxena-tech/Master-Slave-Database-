-- Create a database named 'companydb'
-- IF NOT EXISTS prevents error if database already exists
CREATE DATABASE IF NOT EXISTS companydb;

-- Switch to using the 'companydb' database
-- All following table operations will happen inside this database
USE companydb;


-- Create a table named 'employee'
-- IF NOT EXISTS prevents duplicate creation error
CREATE TABLE IF NOT EXISTS employee (

    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    salary DOUBLE
    );


-- Create a special user named 'repl'
-- '%' means this user can connect from any host (including slave container)
-- mysql_native_password ensures compatibility
CREATE USER IF NOT EXISTS 'repl'@'%'
IDENTIFIED WITH mysql_native_password BY 'replpass';


-- Give the 'repl' user permission to read binary logs
-- Required for slave to replicate data from master
-- It does NOT give full database access
GRANT REPLICATION SLAVE ON *.* TO 'repl'@'%';


-- Reload MySQL privilege tables
-- Ensures new user and permissions become active immediately
FLUSH PRIVILEGES;
