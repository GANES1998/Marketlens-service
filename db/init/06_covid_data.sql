CREATE TABLE COVID_DATA
(
    -- Date is part of composite primary key, if not given take it as the current date
    DAY          Date DEFAULT (CURRENT_DATE) PRIMARY KEY,
    -- New cases for the day.
    NEW_CASES    INTEGER NULL,
    -- Active cases for the end of day.
    ACTIVE_CASES INTEGER NULL,
    -- Covid Deaths for the day.
    DEATH        INTEGER NULL
)