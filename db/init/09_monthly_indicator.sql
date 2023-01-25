CREATE TABLE MONTHLY_INDICATOR
(
    -- Year to which the Indicator corresponds to
    YEAR      DECIMAL(4, 0),
    -- Month to which the indicator corresponds to. (1, 2, 3,.., 12) for (Jan to Dec respectively)
    MONTH     DECIMAL(2, 0),
    -- Indicator - The economic Indicator. E.g RETAIL_SALES, GDP_PER_CAPITA, UNEMPLOYMENT
    INDICATOR VARCHAR(100),
    -- Value of the economic Indicator.
    VALUE     DECIMAL,
    -- Composite Primary Key
    PRIMARY KEY (YEAR, MONTH, INDICATOR)
)