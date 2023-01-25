CREATE TABLE STOCK
(
    -- Date is part of composite primary key, if not given take it as the current date
    DAY          Date DEFAULT (CURRENT_DATE),
    -- It is part of composite primary key, Since it is primary key, it can't be null.
    -- So, no need for explicit non nukk
    SYMBOL       VARCHAR(100),
    -- Low value of stock for the day. Can be null
    LOW          DECIMAL        NULL,
    -- High value of stock for the day. Can be null
    HIGH         DECIMAL        NULL,
    -- Open Price of the stock for the day. Cannot be null as it needs to inserted.
    OPEN         DECIMAL        NOT NULL,
    -- Close Price of the stock for the day. Can be null
    CLOSE        DECIMAL        NULL,
    -- SECTOR ID to which the stock belongs to. Not null, A stock should belong to a sector.
    STOCK_SECTOR VARCHAR(100) NOT NULL,
    -- Composite Primary Key
    PRIMARY KEY (DAY, SYMBOL),
    -- SECTOR can be one of the values of name attribute in SECTOR table
    FOREIGN KEY (STOCK_SECTOR) REFERENCES SECTOR (NAME)
)