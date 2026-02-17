package com.practice;

public class Main {

    public static void main(String[] args) throws Exception {

        EmployeeDao dao = new EmployeeDao();
//
//        dao.insertEmployee("Utkarsha",5000000);
//        dao.insertEmployee("Manoj",2000000);
//
//         dao.fetchEmployees();
        /*
        ============================================================
        NORMAL WORKING FLOW (WHEN EVERYTHING IS HEALTHY)
        ============================================================

         INSERT FLOW (Write Operation)

        dao.insertEmployee("Abhijeet", 500000);

        What happens internally:
        - Application connects to MASTER (port 3307)
        - INSERT executes on master
        - Master writes change to:
              → Database
              → Binary log (binlog)
        - Slave IO thread copies binlog
        - Slave SQL thread replays change
        - Slave now has same data

        Metrics during normal insert:
        - Binlog file size increases
        - GTID transaction count increases
        - Replication lag near 0 seconds
        */


        /*
        FETCH FLOW (Read Operation)

        dao.fetchEmployees();

        What happens internally:
        - Application connects to SLAVE (port 3308)
        - SELECT query runs
        - Slave returns data
        - If slave fails → fallback to MASTER

        Metrics during normal read:
        - Threads_connected increases temporarily
        - Queries_per_second increases
        - CPU usage slightly increases
        - Replication remains stable
        */


        /*
        ============================================================
       WAYS TO CRASH / OVERLOAD THE DATABASE
        ============================================================


        CONNECTION EXHAUSTION (Too Many Threads)

        How:
        - Create 10,000 threads
        - Slave has max_connections=5
        - More connections than allowed

        What Happens:
        - "Too many connections" error
        - Slave rejects connections
        - Fallback to master
        - Possible master overload

        Metrics That Spike:
        - Threads_connected
        - Connection_errors_max_connections
        - JVM thread count
        - CPU context switching





        MEMORY / LARGE RESULT SET OVERLOAD

        How:
        - Insert millions of rows
        - Run SELECT * without LIMIT
        - Fetch entire table every time

        What Happens:
        - High RAM usage
        - Buffer pool pressure
        - Disk I/O increases
        - Possible Java OutOfMemoryError

        Metrics That Spike:
        - RAM usage
        - InnoDB_buffer_pool_usage
        - Disk IOPS
        - JVM heap usage
        */


        /*
        ============================================================
        CURRENT TEST (Connection Exhaustion Simulation)
        ============================================================
        */

        for (int i = 0; i < 10000; i++) {
            new Thread(() -> {
                dao.fetchEmployees();
            }).start();
        }

        Thread.sleep(2000);
    }
}
