CREATE TABLE TREASURY_BOND
(
--     -- Year to which the Treasury Bond data corresponds to
--     YEAR DECIMAL(4, 0),
--     -- Week to which the Treasury Bond data corresponds to
--     WEEK DECIMAL(2, 0),
    DAY        DATE,
    -- TENURE of the Bondy In Years
    TENURE     DECIMAL(2, 0),
    -- Yield Rate. Rate of Return on Investment of the Bond.
    YIELD_RATE DECIMAL(5, 3),
    -- COMPOSITE PRIMARY KEY
    PRIMARY KEY (DAY, TENURE)
)