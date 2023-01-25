package com.gloomberg.marketlens.query.contribution;

public interface Contribution {

    String CONTRIBUTION_QUERY = "WITH STOCK_EXTRACTED AS (\n" +
            "    SELECT\n" +
            "        SYMBOL,\n" +
            "        EXTRACT(YEAR FROM DAY)                       AS YEAR,\n" +
            "        EXTRACT(MONTH FROM DAY)                      AS MONTH,\n" +
            "        1 + (EXTRACT(MONTH FROM DAY) - 1) DIV 4      AS QUARTER,\n" +
            "        CLOSE AS VALUE\n" +
            "    FROM STOCK\n" +
            "    WHERE STOCK_SECTOR = :sector\n" +
            "),\n" +
            "    STOCK_AVERAGED AS (\n" +
            "        SELECT\n" +
            "            SYMBOL,\n" +
            "            YEAR,\n" +
            "            MONTH,\n" +
            "            AVG(VALUE) AS VALUE\n" +
            "        FROM STOCK_EXTRACTED\n" +
            "        WHERE YEAR >= :minYear and YEAR <= :maxYear\n" +
            "        GROUP BY SYMBOL, YEAR, MONTH\n" +
            "    ),\n" +
            "    STOCK_MCAP AS (\n" +
            "        SELECT\n" +
            "            SA.SYMBOL,\n" +
            "            SA.YEAR,\n" +
            "            SA.MONTH AS SUB_YEAR,\n" +
            "            SA.VALUE * CS.SHARES AS MARKET_CAP\n" +
            "        FROM STOCK_AVERAGED SA\n" +
            "            JOIN COMPANY_SHARES CS ON SA.SYMBOL = CS.SYMBOL\n" +
            "    ),\n" +
            "    SUMED_MCAPS AS (\n" +
            "        SELECT\n" +
            "            SYMBOL,\n" +
            "            SUM(MARKET_CAP) AS MARKET_CAP\n" +
            "        FROM STOCK_MCAP\n" +
            "        GROUP BY SYMBOL\n" +
            "    ),\n" +
            "    TOP_N_MARKET_CAPS AS (\n" +
            "        SELECT\n" +
            "            SYMBOL\n" +
            "        FROM SUMED_MCAPS\n" +
            "        ORDER BY MARKET_CAP DESC\n" +
            "        LIMIT :n\n" +
            "    ),\n" +
            "    RESULT AS (\n" +
            "        SELECT * FROM STOCK_MCAP\n" +
            "         WHERE SYMBOL in (\n" +
            "                SELECT SYMBOL FROM TOP_N_MARKET_CAPS\n" +
            "        )\n" +
            "    )\n" +
            "SELECT\n" +
            "    COMPANY_NAME.NAME AS SYMBOL,\n" +
            "    RESULT.YEAR,\n" +
            "    RESULT.SUB_YEAR,\n" +
            "    RESULT.MARKET_CAP\n" +
            "FROM RESULT\n" +
            "    JOIN COMPANY_NAME ON RESULT.SYMBOL = COMPANY_NAME.SYMBOL\n" +
            "ORDER BY YEAR, SUB_YEAR, MARKET_CAP";

}
