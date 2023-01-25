CREATE TABLE COMPANY
(
    -- Symbol with which a company is traded
    SYMBOL       VARCHAR(100),
    -- Reported EPS - Earnings Per Share reported by the company.
    -- Can be null, if its not yet reported for the period
    REPORTED_EPS DECIMAL       NULL,
    -- Expected EPS - EPS expected.
    -- Cannot be null.
    EXPECTED_EPS DECIMAL       NULL,
    -- Year of the data
    YEAR         DECIMAL(4, 0) NOT NULL,
    -- Quarter of the data
    QUARTER      DECIMAL(2, 0) NOT NULL,

    PRIMARY KEY (SYMBOL, YEAR, QUARTER)
)