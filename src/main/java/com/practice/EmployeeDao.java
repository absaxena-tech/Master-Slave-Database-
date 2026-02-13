package com.practice;

import java.sql.*;

public class EmployeeDao {

    // WRITE → MASTER DB
    public void insertEmployee(String name, double salary) throws Exception {
        Connection con = DBConfig.getMasterConnection();
        String sql = "INSERT INTO employee(name, salary) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ps.setDouble(2, salary);
        ps.executeUpdate();
        con.close();
        System.out.println("✅ Inserted into MASTER DB");
    }

    // READ → SLAVE DB
    public void fetchEmployees() throws Exception {
        Connection con = DBConfig.getSlaveConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM employee");

        System.out.println("📖 Reading from SLAVE DB:");
        while (rs.next()) {
            System.out.println(
                    rs.getInt("id") + " | " +
                            rs.getString("name") + " | " +
                            rs.getDouble("salary")
            );
        }
        con.close();
    }
}
