--liquibase formatted sql

--changeset dpshenin:homework8-0001

-- user_limit definition

-- Drop table

-- DROP TABLE user_limit;

CREATE TABLE IF NOT EXISTS users_limit (
    user_id BIGINT primary key,
    daily_limit DOUBLE PRECISION
);

-- max_limit_dictionary definition

-- Drop table

-- DROP TABLE max_limit_dictionary;

CREATE TABLE IF NOT EXISTS max_limit_dictionary (
    id BIGINT primary key,
    max_limit DOUBLE PRECISION
);
