package com.practice;

public class Main {

    public static void main(String[] args) throws Exception {

        EmployeeDao dao = new EmployeeDao();

        // Insert into MASTER
//        dao.insertEmployee("Abhijeet", 500000);
//        dao.insertEmployee("Kamal", 100000);
//        dao.insertEmployee("Prakriti", 200000);

        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                dao.fetchEmployees();
            }).start();
//        }


        Thread.sleep(2000); // wait for replication

//        // Read (from SLAVE normally)
//        dao.fetchEmployees();
    }
}
