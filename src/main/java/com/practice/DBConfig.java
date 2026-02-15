package com.practice;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {

    public static Connection getMasterConnection() throws Exception {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/companydb",
                "root",
                "root"
        );
    }

    public static Connection getSlaveConnection() throws Exception {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3308/companydb",
                "root",
                "root"
        );
    }
}
