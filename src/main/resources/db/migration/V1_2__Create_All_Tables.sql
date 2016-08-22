CREATE TABLE IF NOT EXISTS USER_DETAILS (
    DETAIL_ID SERIAL PRIMARY KEY,
    USER_ID INTEGER NOT NULL,
    JOB_EXPERIENCE INTEGER NOT NULL,
    PRIMARY_AREAS VARCHAR(255),
    IS_CURRENTLY_EMPLOYED CHAR(1) NOT NULL,
    IS_PREVIOUSLY_EMPLOYED CHAR(1) NOT NULL
);

CREATE TABLE IF NOT EXISTS EMPLOYER (
    EMPLOYER_ID SERIAL PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    LOCATION VARCHAR(255) NOT NULL,
    WEBSITE VARCHAR(255) NOT NULL,
    IS_ACTIVE CHAR(1) NOT NULL,
    JOINED_DATE TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS JOB_POSTINGS (
    POSTING_ID SERIAL PRIMARY KEY,
    EMPLOYER_ID INTEGER NOT NULL,
    LOCATION VARCHAR(255) NOT NULL,
    JOB_DESCRIPTION VARCHAR(255) NOT NULL,
    PRIMARY_SKILLS VARCHAR(255) NOT NULL,
    POSTING_DATE TIMESTAMP NOT NULL
);