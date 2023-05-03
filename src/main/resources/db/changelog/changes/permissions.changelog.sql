-- liquibase formatted sql

-- changeset liquibase:3
GRANT SELECT, INSERT, UPDATE, DELETE
on all tables in schema baseadmin
to baseuser;
