CREATE TABLE QUARTERLY_INDICATOR
(
    -- Year to which the Indicator corresponds to
    YEAR      DECIMAL(4, 0),
    -- Quarter to which the indicator corresponds to. (1, 2, 3, 4)
    QUARTER   DECIMAL(1, 0),
    -- Indicator - The economic Indicator. E.g RETAIL_SALES, GDP_PER_CAPITA, UNEMPLOYMENT
    INDICATOR VARCHAR(100),
    -- Value of the economic Indicator.
    VALUE     DECIMAL,
    -- Composite Primary Key
    PRIMARY KEY (YEAR, QUARTER, INDICATOR)
)