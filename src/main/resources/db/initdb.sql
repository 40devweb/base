-- Init database users, schema and permissions

-- Role: baseadmin
-- DROP ROLE IF EXISTS baseadmin;

CREATE ROLE baseadmin WITH
  LOGIN
  NOSUPERUSER
  INHERIT
  NOCREATEDB
  NOCREATEROLE
  NOREPLICATION
  PASSWORD 'baseadmin';

ALTER ROLE baseadmin IN DATABASE basedb SET search_path TO baseadmin, public;

COMMENT ON ROLE baseadmin IS 'base user with ddl privileges';


-- Role: baseuser
-- DROP ROLE IF EXISTS baseuser;

CREATE ROLE baseuser WITH
  LOGIN
  NOSUPERUSER
  INHERIT
  NOCREATEDB
  NOCREATEROLE
  NOREPLICATION
  PASSWORD 'baseuser';

ALTER ROLE baseuser IN DATABASE basedb SET search_path TO baseadmin, public;

COMMENT ON ROLE baseuser IS 'base user with dml privileges';


-- SCHEMA: baseadmin

-- DROP SCHEMA IF EXISTS baseadmin ;

CREATE SCHEMA IF NOT EXISTS baseadmin
    AUTHORIZATION baseadmin;

GRANT ALL ON SCHEMA baseadmin TO baseadmin;

GRANT USAGE ON SCHEMA baseadmin TO baseuser;

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA baseadmin
GRANT DELETE, UPDATE, SELECT, INSERT ON TABLES TO baseuser;

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA baseadmin
GRANT ALL ON SEQUENCES TO baseuser;

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA baseadmin
GRANT EXECUTE ON FUNCTIONS TO baseuser;

ALTER DEFAULT PRIVILEGES FOR ROLE postgres IN SCHEMA baseadmin
GRANT USAGE ON TYPES TO baseuser;

