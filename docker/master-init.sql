CREATE DATABASE IF NOT EXISTS companydb;

USE companydb;

CREATE TABLE employee (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(100),
                          salary DOUBLE
);

CREATE USER 'repl'@'%' IDENTIFIED WITH mysql_native_password BY 'replpass';
GRANT REPLICATION SLAVE ON *.* TO 'repl'@'%';
FLUSH PRIVILEGES;
