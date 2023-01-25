CREATE TABLE STOCK_INDEX
(
    -- Date is part of composite primary key, if not given take it as the current date
    DAY              Date DEFAULT (CURRENT_DATE),
    -- It is part of composite primary key, Since it is primary key, it can't be null.
    -- So, no need for explicit non nukk
    STOCK_INDEX_NAME VARCHAR(100),
    -- Low value of stock for the day. Can be null
    LOW              DECIMAL NULL,
    -- High value of stock for the day. Can be null
    HIGH             DECIMAL NULL,
    -- Open Price of the stock for the day. Cannot be null as it needs to inserted.
    OPEN             DECIMAL NOT NULL,
    -- Close Price of the stock for the day. Can be null
    CLOSE            DECIMAL NULL,

    VOLUME           DECIMAL NULL,
    -- Composite Primary Key
    PRIMARY KEY (DAY, STOCK_INDEX_NAME)
)