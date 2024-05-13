package com.gloomberg.marketlens.query;

public interface GrowthQueries {

    String GROWTH_VALUE_STOCK = "SELECT SYMBOL, YEAR, SUB_YEAR, VALUE, (VALUE - PREV_VALUE) / PREV_VALUE * 100 AS PERCENT FROM (SELECT SYMBOL,\n" +
            "                                                                                    YEAR,\n" +
            "                                                                                    SUB_YEAR,\n" +
            "                                                                                    LAG(VALUE, 1, 0) OVER ( ORDER BY YEAR, SUB_YEAR) AS PREV_VALUE,\n" +
            "                                                                                    VALUE\n" +
            "                                                                             FROM (SELECT SYMBOL, YEAR, $AGG_PARAM$ AS SUB_YEAR, AVG(VALUE) AS VALUE\n" +
            "                                                                                   FROM (SELECT SYMBOL,\n" +
            "                                                                                                EXTRACT(YEAR FROM DAY)                       AS YEAR,\n" +
            "                                                                                                EXTRACT(MONTH FROM DAY)                      AS MONTH,\n" +
            "                                                                                                TRUNC((EXTRACT(MONTH FROM DAY) - 1) / 3) + 1 AS QUARTER,\n" +
            "                                                                                                CLOSE                                        AS VALUE\n" +
            "                                                                                         FROM STOCK\n" +
            "                                                                                         WHERE SYMBOL = :symbol) AS ALIAS1:aliasSymbol\n" +
            "                                                                                   WHERE YEAR >= :minYear\n" +
            "                                                                                     and YEAR <= :maxYear\n" +
            "                                                                                   GROUP BY SYMBOL, YEAR, $AGG_PARAM$\n" +
            "                                                                                   ORDER BY SYMBOL, YEAR, SUB_YEAR) AS ALIAS2:aliasSymbol) AS ALIAS3:aliasSymbol\n" +
            "WHERE PREV_VALUE > 0";

    String GROWTH_VALUE_SECTOR = "SELECT SECTOR AS SYMBOL,\n" +
            "    YEAR,\n" +
            "    SUB_YEAR,\n" +
            "    AVG(VALUE) AS VALUE,\n" +
            "    AVG(PERCENT) AS PERCENT\n" +
            "FROM (\n" +
            "    SELECT SYMBOL, SECTOR, YEAR, SUB_YEAR, VALUE, (VALUE - PREV_VALUE) / PREV_VALUE * 100 AS PERCENT FROM (SELECT SYMBOL,\n" +
            "                                                                                                      SECTOR,\n" +
            "                                                                                YEAR,\n" +
            "                                                                                SUB_YEAR,\n" +
            "                                                                                LAG(VALUE, 1, 0) OVER ( ORDER BY YEAR, SUB_YEAR) AS PREV_VALUE,\n" +
            "                                                                                VALUE\n" +
            "                                                                         FROM (SELECT SYMBOL, SECTOR, YEAR, $AGG_PARAM$ AS SUB_YEAR, AVG(VALUE) AS VALUE\n" +
            "                                                                               FROM (SELECT SYMBOL,\n" +
            "                                                                                            STOCK_SECTOR                                 AS SECTOR,\n" +
            "                                                                                            EXTRACT(YEAR FROM DAY)                       AS YEAR,\n" +
            "                                                                                            EXTRACT(MONTH FROM DAY)                      AS MONTH,\n" +
            "                                                                                            TRUNC((EXTRACT(MONTH FROM DAY) - 1) / 3) + 1 AS QUARTER,\n" +
            "                                                                                            CLOSE                                        AS VALUE\n" +
            "                                                                                     FROM STOCK\n" +
            "                                                                                     WHERE SYMBOL IN (Select DISTINCT SYMBOL FROM STOCK WHERE STOCK_SECTOR = :symbol)) AS ALIAS2:aliasSymbol\n" +
            "                                                                               WHERE YEAR >= :minYear\n" +
            "                                                                                 and YEAR <= :maxYear\n" +
            "                                                                               GROUP BY SYMBOL, SECTOR, YEAR, $AGG_PARAM$\n" +
            "                                                                               ORDER BY SYMBOL, SECTOR, YEAR, SUB_YEAR) AS ALIAS3:aliasSymbol) AS ALIAS4:aliasSymbol\n" +
            "    WHERE PREV_VALUE > 0\n" +
            ") AS ALIAS5:aliasSymbol\n" +
            "GROUP BY SECTOR, YEAR, SUB_YEAR\n";

    String GROWTH_VALUE_STOCK_INDEX = "SELECT SYMBOL, YEAR, SUB_YEAR, VALUE, (VALUE - PREV_VALUE) / PREV_VALUE * 100 AS PERCENT FROM (SELECT SYMBOL,\n" +
            "                                                                                    YEAR,\n" +
            "                                                                                    SUB_YEAR,\n" +
            "                                                                                    LAG(VALUE, 1, 0) OVER ( ORDER BY YEAR, SUB_YEAR) AS PREV_VALUE,\n" +
            "                                                                                    VALUE\n" +
            "                                                                             FROM (SELECT SYMBOL, YEAR, $AGG_PARAM$ AS SUB_YEAR, AVG(VALUE) AS VALUE\n" +
            "                                                                                   FROM (SELECT STOCK_INDEX_NAME                             AS SYMBOL,\n" +
            "                                                                                                EXTRACT(YEAR FROM DAY)                       AS YEAR,\n" +
            "                                                                                                EXTRACT(MONTH FROM DAY)                      AS MONTH,\n" +
            "                                                                                                TRUNC((EXTRACT(MONTH FROM DAY) - 1) / 3) + 1 AS QUARTER,\n" +
            "                                                                                                CLOSE                                        AS VALUE\n" +
            "                                                                                         FROM STOCK_INDEX\n" +
            "                                                                                         WHERE STOCK_INDEX_NAME = :symbol) AS ALIAS1:aliasSymbol\n" +
            "                                                                                   WHERE YEAR >= :minYear\n" +
            "                                                                                     and YEAR <= :maxYear\n" +
            "                                                                                   GROUP BY SYMBOL, YEAR, $AGG_PARAM$\n" +
            "                                                                                   ORDER BY SYMBOL, YEAR, SUB_YEAR) AS ALIAS2:aliasSymbol) AS ALIAS3:aliasSymbol\n" +
            "WHERE PREV_VALUE > 0";

    String GROWTH_VALUE_COMMODITY = "SELECT SYMBOL, YEAR, SUB_YEAR, VALUE, (VALUE - PREV_VALUE) / PREV_VALUE * 100 AS PERCENT\n" +
            "FROM (SELECT SYMBOL,\n" +
            "             YEAR,\n" +
            "             SUB_YEAR,\n" +
            "             LAG(VALUE, 1, 0) OVER ( ORDER BY YEAR, SUB_YEAR) AS PREV_VALUE,\n" +
            "             VALUE\n" +
            "      FROM (SELECT SYMBOL, YEAR, $AGG_PARAM$ AS SUB_YEAR, AVG(VALUE) AS VALUE\n" +
            "            FROM (SELECT NAME                                         AS SYMBOL,\n" +
            "                         EXTRACT(YEAR FROM DAY)                       AS YEAR,\n" +
            "                         EXTRACT(MONTH FROM DAY)                      AS MONTH,\n" +
            "                         TRUNC((EXTRACT(MONTH FROM DAY) - 1) / 3) + 1 AS QUARTER,\n" +
            "                         CLOSE                                        AS VALUE\n" +
            "                  FROM COMMODITY\n" +
            "                  WHERE NAME = :symbol) AS ALIAS1:aliasSymbol\n" +
            "            WHERE YEAR >= :minYear\n" +
            "              and YEAR <= :maxYear\n" +
            "            GROUP BY SYMBOL, YEAR, $AGG_PARAM$\n" +
            "            ORDER BY SYMBOL, YEAR, SUB_YEAR) AS ALIAS2:aliasSymbol) AS ALIAS3:aliasSymbol\n" +
            "WHERE PREV_VALUE > 0\n" +
            "\n";

    String GROWTH_VALUE_CRYPTO_CURRENCY = "SELECT SYMBOL, YEAR, SUB_YEAR, VALUE, (VALUE - PREV_VALUE) / PREV_VALUE * 100 AS PERCENT FROM (SELECT SYMBOL,\n" +
            "                                                                                    YEAR,\n" +
            "                                                                                    SUB_YEAR,\n" +
            "                                                                                    LAG(VALUE, 1, 0) OVER ( ORDER BY YEAR, SUB_YEAR) AS PREV_VALUE,\n" +
            "                                                                                    VALUE\n" +
            "                                                                             FROM (SELECT SYMBOL, YEAR, $AGG_PARAM$ AS SUB_YEAR, AVG(VALUE) AS VALUE\n" +
            "                                                                                   FROM (SELECT CURRENCY                                     AS SYMBOL,\n" +
            "                                                                                                EXTRACT(YEAR FROM DAY)                       AS YEAR,\n" +
            "                                                                                                EXTRACT(MONTH FROM DAY)                      AS MONTH,\n" +
            "                                                                                                TRUNC((EXTRACT(MONTH FROM DAY) - 1) / 3) + 1 AS QUARTER,\n" +
            "                                                                                                CLOSE                                        AS VALUE\n" +
            "                                                                                         FROM CRYPTO_CURRENCY\n" +
            "                                                                                         WHERE CURRENCY = :symbol) AS ALIAS1:aliasSymbol\n" +
            "                                                                                   WHERE YEAR >= :minYear\n" +
            "                                                                                     and YEAR <= :maxYear\n" +
            "                                                                                   GROUP BY SYMBOL, YEAR, $AGG_PARAM$\n" +
            "                                                                                   ORDER BY SYMBOL, YEAR, SUB_YEAR) AS ALIAS2:aliasSymbol) AS ALIAS3:aliasSymbol\n" +
            "WHERE PREV_VALUE > 0";


    String GROWTH_VALUE_TREASURY = "SELECT SYMBOL, YEAR, SUB_YEAR, VALUE, (VALUE - PREV_VALUE) / PREV_VALUE * 100 AS PERCENT\n" +
            "FROM (SELECT SYMBOL,\n" +
            "             YEAR,\n" +
            "             SUB_YEAR,\n" +
            "             LAG(VALUE, 1, 0) OVER ( ORDER BY YEAR, SUB_YEAR) AS PREV_VALUE,\n" +
            "             VALUE FROM (\n" +
            "                     SELECT SYMBOL, YEAR, $AGG_PARAM$ AS SUB_YEAR, AVG(VALUE) AS VALUE\n" +
            "                     FROM (SELECT TO_CHAR(TENURE)                              AS SYMBOL,\n" +
            "                                  EXTRACT(YEAR FROM DAY)                       AS YEAR,\n" +
            "                                  EXTRACT(MONTH FROM DAY)                      AS MONTH,\n" +
            "                                  TRUNC((EXTRACT(MONTH FROM DAY) - 1) / 3) + 1 AS QUARTER,\n" +
            "                                  YIELD_RATE                                   AS VALUE\n" +
            "                           FROM TREASURY_BOND\n" +
            "                           WHERE TENURE = :symbol) AS ALIAS1:aliasSymbol\n" +
            "                     WHERE YEAR >= :minYear\n" +
            "                         and YEAR <= :maxYear\n" +
            "                     GROUP BY SYMBOL, YEAR, $AGG_PARAM$\n" +
            "                     ORDER BY SYMBOL, YEAR, SUB_YEAR) AS ALIAS2:aliasSymbol) AS ALIAS3:aliasSymbol\n" +
            "WHERE PREV_VALUE > 0\n";

    String GROWTH_VALUE_COVID = "SELECT SYMBOL, YEAR, SUB_YEAR, VALUE, (VALUE - PREV_VALUE) / PREV_VALUE * 100 AS PERCENT\n" +
            "FROM (SELECT SYMBOL,\n" +
            "             YEAR,\n" +
            "             SUB_YEAR,\n" +
            "             LAG(VALUE, 1, 0) OVER ( ORDER BY YEAR, SUB_YEAR) AS PREV_VALUE,\n" +
            "             VALUE\n" +
            "      FROM (SELECT SYMBOL, YEAR, $AGG_PARAM$ AS SUB_YEAR, AVG(VALUE) AS VALUE\n" +
            "            FROM (SELECT 'COVID'                                      AS SYMBOL,\n" +
            "                         EXTRACT(YEAR FROM DAY)                       AS YEAR,\n" +
            "                         EXTRACT(MONTH FROM DAY)                      AS MONTH,\n" +
            "                         TRUNC((EXTRACT(MONTH FROM DAY) - 1) / 3) + 1 AS QUARTER,\n" +
            "                         ACTIVE_CASES                                 AS VALUE\n" +
            "                  FROM COVID_DATA) AS ALIAS1:aliasSymbol\n" +
            "            WHERE YEAR >= :minYear\n" +
            "              and YEAR <= :maxYear\n" +
            "            GROUP BY SYMBOL, YEAR, $AGG_PARAM$\n" +
            "            ORDER BY SYMBOL, YEAR, SUB_YEAR) AS ALIAS2:aliasSymbol) AS ALIAS3:aliasSymbol\n" +
            "WHERE PREV_VALUE > 0";

    String GROWTH_VALUE_ECONOMIC_INDICATOR = "SELECT SYMBOL, YEAR, SUB_YEAR, VALUE, (VALUE - PREV_VALUE) / PREV_VALUE * 100 AS PERCENT\n" +
            "FROM (SELECT SYMBOL,\n" +
            "             YEAR,\n" +
            "             SUB_YEAR,\n" +
            "             LAG(VALUE, 1, 0) OVER ( ORDER BY YEAR, SUB_YEAR) AS PREV_VALUE,\n" +
            "             VALUE FROM (SELECT SYMBOL, YEAR, $AGG_PARAM$ AS SUB_YEAR, AVG(VALUE) AS VALUE\n" +
            "                         FROM (SELECT INDICATOR AS SYMBOL, YEAR, MONTH, TRUNC((MONTH - 1) / 3) + 1 AS QUARTER, VALUE\n" +
            "                               FROM MONTHLY_INDICATOR\n" +
            "                               UNION\n" +
            "                               SELECT INDICATOR, YEAR, QUARTER * 3 AS MONTH, QUARTER, VALUE\n" +
            "                               FROM QUARTERLY_INDICATOR) AS ALIAS1:aliasSymbol\n" +
            "                         WHERE YEAR >= :minYear\n" +
            "                           and YEAR <= :maxYear\n" +
            "                           and SYMBOL = :symbol\n" +
            "                         GROUP BY SYMBOL, YEAR, $AGG_PARAM$\n" +
            "                         ORDER BY SYMBOL, YEAR, SUB_YEAR) AS ALIAS2:aliasSymbol) AS ALIAS3:aliasSymbol\n" +
            "WHERE PREV_VALUE > 0";

    String ORDER_BY_CLAUSE = "\nORDER BY SYMBOL, YEAR, SUB_YEAR";

}
