CREATE TABLE IF NOT EXISTS USERS (
    USER_ID SERIAL PRIMARY KEY,
    EMAIL VARCHAR(255) NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    IS_VERIFIED CHAR(1) NOT NULL,
    IS_ACTIVE CHAR(1) NOT NULL,
    JOINED_DATE TIMESTAMP NOT NULL
);