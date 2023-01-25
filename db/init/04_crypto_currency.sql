CREATE TABLE CRYPTO_CURRENCY
(
    -- Date is part of composite primary key, if not given take it as the current date
    DAY      Date DEFAULT (CURRENT_DATE),
    -- Currency e.g BTC or ETH
    CURRENCY VARCHAR(3),
    -- Low Value for the day in USD
    LOW      DECIMAL NULL,
    -- High Value for the day in USD,
    HIGH     DECIMAL NULL,
    -- Morning Open value for the day in USD. Cannot be null
    OPEN     DECIMAL NOT NULL,
    -- CLose Value for the day
    CLOSE    DECIMAL NOT NULL,
    -- Composite Primary Key
    PRIMARY KEY (DAY, CURRENCY)
)