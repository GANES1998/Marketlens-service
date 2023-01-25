CREATE TABLE COMMODITY
(
    -- Date is part of composite primary key, if not given take it as the current date
    DAY   Date DEFAULT (CURRENT_DATE),
    -- Name of the commodity. E.g Gold, Brunt Oil, etc.
    NAME  VARCHAR(3),
    -- Low Value for the day in USD
    LOW   DECIMAL NULL,
    -- High Value for the day in USD,
    HIGH  DECIMAL NULL,
    -- Morning Open value for the day in USD. Cannot be null
    OPEN  DECIMAL NOT NULL,
    -- CLose Value for the day
    CLOSE DECIMAL NOT NULL,
    -- Composite Primary Key
    PRIMARY KEY (DAY, NAME)
)