CREATE TABLE SECTOR
(
    -- Name of the sector. Primary Key.
    NAME  VARCHAR(100)  NOT NULL PRIMARY KEY,
    -- Is important sector. NUMBER(1) is used to represent boolean - 1 - ture and 0 - false
    IMPORTANT TINYINT(1) DEFAULT 0 NOT NULL
);