package com.gloomberg.marketlens.query.variance;

public interface SectorVarianceQuery {

    String SECTOR_VARIANCE_QUERY = "WITH STOCK_TABLE AS (SELECT STOCK_INDEX_NAME,\n" +
            "                           EXTRACT(YEAR FROM DAY)                       AS YEAR,\n" +
            "                           EXTRACT(MONTH FROM DAY)                      AS MONTH,\n" +
            "                           1 + TRUNC((EXTRACT(MONTH FROM DAY)) - 1 / 3) AS QUARTER,\n" +
            "                           EXTRACT(YEAR FROM DAY)                       AS YEAR2,\n" +
            "                           CLOSE                                        AS VALUE\n" +
            "                    FROM STOCK_INDEX),\n" +
            "    STOCK_GROUPED_RESULT AS (\n" +
            "        SELECT\n" +
            "            STOCK_INDEX_NAME,\n" +
            "            YEAR,\n" +
            "            $AGG_PARAM$ AS SUBYEAR,\n" +
            "            AVG(VALUE) AS VALUE,\n" +
            "            STDDEV(VALUE) AS STD_DEV\n" +
            "        FROM STOCK_TABLE\n" +
            "        WHERE YEAR >= :minYear and YEAR <= :maxYear\n" +
            "        GROUP BY STOCK_INDEX_NAME, YEAR, $AGG_PARAM$\n" +
            "    ),\n" +
            "    MIN_STOCK_TABLE AS (\n" +
            "        SELECT\n" +
            "            STOCK_INDEX_NAME,\n" +
            "            YEAR,\n" +
            "            SUBYEAR,\n" +
            "            VALUE\n" +
            "        FROM (\n" +
            "                SELECT\n" +
            "                    STOCK_INDEX_NAME,\n" +
            "                    YEAR,\n" +
            "                    SUBYEAR,\n" +
            "                    VALUE,\n" +
            "                    ROW_NUMBER() over (PARTITION BY STOCK_INDEX_NAME ORDER BY YEAR, SUBYEAR) AS ROWNUMBER\n" +
            "                FROM STOCK_GROUPED_RESULT\n" +
            "          )\n" +
            "        WHERE ROWNUMBER = 1\n" +
            "    ),\n" +
            "    STOCK_GROWTH_VALUE AS (\n" +
            "        SELECT\n" +
            "            ST.STOCK_INDEX_NAME AS SYMBOL,\n" +
            "            ST.YEAR,\n" +
            "            ST.SUBYEAR,\n" +
            "            (ST.VALUE - MST.VALUE) / (MST.VALUE) * 100 AS VALUE,\n" +
            "            ST.STD_DEV AS STD_DEV\n" +
            "        FROM STOCK_GROUPED_RESULT ST\n" +
            "            JOIN MIN_STOCK_TABLE MST ON\n" +
            "                ST.STOCK_INDEX_NAME = MST.STOCK_INDEX_NAME\n" +
            "    ),\n" +
            "    CRYPTO_DATA AS (\n" +
            "        SELECT\n" +
            "            CURRENCY                                     AS SYMBOL,\n" +
            "            EXTRACT(YEAR FROM DAY)                       AS YEAR,\n" +
            "            EXTRACT(MONTH FROM DAY)                      AS MONTH,\n" +
            "            1 + TRUNC((EXTRACT(MONTH FROM DAY)) - 1 / 3) AS QUARTER,\n" +
            "            EXTRACT(YEAR FROM DAY)                       AS YEAR2,\n" +
            "            CLOSE                                        AS VALUE\n" +
            "        FROM CRYPTO_CURRENCY\n" +
            "    ),\n" +
            "    CRYPTO_GROUPED_RESULT AS (\n" +
            "        SELECT\n" +
            "            SYMBOL,\n" +
            "            YEAR,\n" +
            "            $AGG_PARAM$ AS SUBYEAR,\n" +
            "            AVG(VALUE) AS VALUE,\n" +
            "            STDDEV(VALUE) AS STD_DEV\n" +
            "        FROM CRYPTO_DATA\n" +
            "        WHERE YEAR >= :minYear and YEAR <= :maxYear\n" +
            "        GROUP BY SYMBOL, YEAR, $AGG_PARAM$\n" +
            "    ),\n" +
            "    MIN_CRYPTO_GROUPED_RESULT AS (\n" +
            "            SELECT\n" +
            "                SYMBOL,\n" +
            "                YEAR,\n" +
            "                SUBYEAR,\n" +
            "                VALUE,\n" +
            "                STD_DEV\n" +
            "            FROM (\n" +
            "                SELECT\n" +
            "                SYMBOL,\n" +
            "                YEAR,\n" +
            "                SUBYEAR,\n" +
            "                VALUE,\n" +
            "                STD_DEV,\n" +
            "                ROW_NUMBER() over (PARTITION BY SYMBOL ORDER BY YEAR, SUBYEAR) AS ROWNUMBER\n" +
            "            FROM CRYPTO_GROUPED_RESULT\n" +
            "        ) WHERE ROWNUMBER = 1\n" +
            "    ),\n" +
            "    CRYPTO_CURRENCY_RESULTS AS (\n" +
            "        SELECT\n" +
            "            CGR.SYMBOL,\n" +
            "            CGR.YEAR,\n" +
            "            CGR.SUBYEAR,\n" +
            "            (CGR.VALUE - MCGR.VALUE) / MCGR.VALUE * 100 AS VALUE,\n" +
            "            CGR.STD_DEV AS STD_DEV\n" +
            "            FROM\n" +
            "                     CRYPTO_GROUPED_RESULT CGR JOIN MIN_CRYPTO_GROUPED_RESULT MCGR\n" +
            "            ON CGR.SYMBOL = MCGR.SYMBOL\n" +
            "\n" +
            "    ),\n" +
            "    CRYPTO_AGG_RESULTS AS (\n" +
            "        SELECT (SELECT 'CRYPTO_CURRENCY' FROM DUAL) AS SYMBOL,\n" +
            "        YEAR,\n" +
            "        SUBYEAR,\n" +
            "        AVG(VALUE) AS VALUE,\n" +
            "        AVG(STD_DEV) AS STD_DEV\n" +
            "        FROM CRYPTO_CURRENCY_RESULTS\n" +
            "        GROUP BY YEAR, SUBYEAR\n" +
            "    )\n" +
            "SELECT * FROM (\n" +
            "    SELECT * FROM STOCK_GROWTH_VALUE UNION SELECT * FROM CRYPTO_AGG_RESULTS\n" +
            ")\n" +
            "WHERE SYMBOL in :sectors\n" +
            "ORDER BY SYMBOL, YEAR, SUBYEAR\n" +
            "\n";

}
