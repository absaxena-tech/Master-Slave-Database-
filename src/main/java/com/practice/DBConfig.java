package com.practice;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConfig {

    private static final String MASTER_URL = "jdbc:mysql://localhost:3307/companydb";
    private static final String SLAVE_URL  = "jdbc:mysql://localhost:3308/companydb";

    private static final String USER = "root";
    private static final String PASS = "root";

    public static Connection getMasterConnection() throws Exception {
        return DriverManager.getConnection(MASTER_URL, USER, PASS);
    }

    public static Connection getSlaveConnection() throws Exception {
        return DriverManager.getConnection(SLAVE_URL, USER, PASS);
    }
}
