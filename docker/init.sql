CREATE DATABASE IF NOT EXISTS companydb;
USE companydb;

CREATE TABLE employee (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(100),
                          salary DOUBLE
);
