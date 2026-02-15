package com.practice;

public class Main {
    public static void main(String[] args) throws Exception {

        EmployeeDao dao = new EmployeeDao();

        // Write to MASTER
        dao.insertEmployee("Abhijeet", 500000);
        dao.insertEmployee("Kamal",100000);
        dao.insertEmployee("Prakriti", 200000);
        dao.insertEmployee("Sanjay",3000000);

        // wait for replication
        Thread.sleep(2000);

        // Read from SLAVE
        dao.fetchEmployees();
    }
}
