package com.practice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmployeeDao {

    // WRITE → MASTER
    public void insertEmployee(String name, double salary) {

        String sql = "INSERT INTO employee(name, salary) VALUES (?, ?)";

        try (Connection con = DBConfig.getMasterConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setDouble(2, salary);
            ps.executeUpdate();

            System.out.println("Inserted into MASTER");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // READ → SLAVE (fallback to MASTER)
    public void fetchEmployees() {

        try (Connection con = DBConfig.getSlaveConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM employee")) {

            System.out.println("Reading from SLAVE");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getDouble("salary")
                );
            }

        } catch (Exception e) {

            System.out.println("Slave failed. Reading from MASTER");

            try (Connection con = DBConfig.getMasterConnection();
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery("SELECT * FROM employee")) {

                while (rs.next()) {
                    System.out.println(
                            rs.getInt("id") + " | " +
                            rs.getString("name") + " | " +
                            rs.getDouble("salary")
                    );
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
