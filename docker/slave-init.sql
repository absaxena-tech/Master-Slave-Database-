-- Configure the slave to connect to the master
CHANGE REPLICATION SOURCE TO

  -- Hostname of the master database.
  -- In Docker, this is the service name defined in docker-compose.
  SOURCE_HOST='mysql-master',

  -- Replication username created on master.
  -- This user has permission to read binary logs.
  SOURCE_USER='repl',

  -- Password for replication user.
  SOURCE_PASSWORD='replpass',

  -- Use GTID-based automatic positioning.
  -- Slave automatically figures out which transactions to pull.
  -- No need to manually specify binlog file or position.
  SOURCE_AUTO_POSITION=1;



-- Starts the replication process.
-- Slave begins:
-- 1. Connecting to master
-- 2. Downloading binary logs
-- 3. Replaying them locally
START REPLICA;
