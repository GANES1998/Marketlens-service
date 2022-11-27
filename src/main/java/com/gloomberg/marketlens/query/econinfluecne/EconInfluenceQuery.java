package com.gloomberg.marketlens.query.econinfluecne;

public interface EconInfluenceQuery {

    String GET_ECON_INFLUENCE = "WITH MON_INDIC AS (\n" +
            "    SELECT\n" +
            "       INDICATOR,\n" +
            "       YEAR,\n" +
            "       MONTH,\n" +
            "       TRUNC((MONTH - 1), 3) + 1 AS QUARTER,\n" +
            "       VALUE\n" +
            "    FROM MONTHLY_INDICATOR\n" +
            "), QUAR_IND AS (\n" +
            "    SELECT\n" +
            "       INDICATOR,\n" +
            "       YEAR,\n" +
            "       QUARTER * 3 AS MONTH,\n" +
            "       QUARTER,\n" +
            "       VALUE\n" +
            "    FROM QUARTERLY_INDICATOR\n" +
            "),\n" +
            "    INDICATORS AS (\n" +
            "        SELECT * FROM MON_INDIC\n" +
            "                 UNION ALL\n" +
            "        SELECT * FROM QUAR_IND\n" +
            "), FILTERED_ECON_IND AS (SELECT (SELECT 'ECON_IND' FROM DUAL) AS TYPE,\n" +
            "                                INDICATOR,\n" +
            "                                YEAR,\n" +
            "                                MONTH,\n" +
            "                                VALUE\n" +
            "                         FROM INDICATORS\n" +
            "                         WHERE INDICATOR in :indicators\n" +
            "                           AND YEAR >= :minYear\n" +
            "                           AND YEAR <= :maxYear),\n" +
            "    INDICATOR_MID_RESULT AS (\n" +
            "            SELECT\n" +
            "            TYPE,\n" +
            "            INDICATOR AS SYMBOL,\n" +
            "            YEAR,\n" +
            "            MONTH,\n" +
            "            AVG(VALUE) AS VALUE\n" +
            "        FROM FILTERED_ECON_IND\n" +
            "            GROUP BY TYPE, INDICATOR, YEAR, MONTH\n" +
            "        ORDER BY TYPE, INDICATOR, YEAR, MONTH\n" +
            "    ),\n" +
            "    INDICATOR_MIN_RESULT AS (\n" +
            "        SELECT\n" +
            "            TYPE,\n" +
            "            SYMBOL,\n" +
            "            YEAR,\n" +
            "            MONTH,\n" +
            "            VALUE\n" +
            "        FROM (\n" +
            "                SELECT\n" +
            "                ROW_NUMBER() over (PARTITION BY TYPE, SYMBOL ORDER BY YEAR, MONTH) AS ROWNUMBER,\n" +
            "                TYPE,\n" +
            "                SYMBOL,\n" +
            "                YEAR,\n" +
            "                MONTH,\n" +
            "                VALUE\n" +
            "            FROM INDICATOR_MID_RESULT\n" +
            "        ) WHERE ROWNUMBER = 1\n" +
            "    ), INDICATOR_RESULT AS (\n" +
            "        SELECT\n" +
            "            R.TYPE,\n" +
            "            R.SYMBOL,\n" +
            "            R.YEAR,\n" +
            "            R.MONTH AS SUBYEAR,\n" +
            "            (R.VALUE - MR.VALUE) / MR.VALUE * 100 AS VALUE\n" +
            "            FROM INDICATOR_MID_RESULT R\n" +
            "                 JOIN INDICATOR_MIN_RESULT MR\n" +
            "                     ON R.TYPE = MR.TYPE AND R.SYMBOL = MR.SYMBOL\n" +
            "    ), BASE_TABLE AS\n" +
            "    (SELECT SYMBOL, SECTOR, YEAR, MONTH, AVG(VALUE) AS VALUE\n" +
            "                    FROM (SELECT SYMBOL,\n" +
            "                                 STOCK_SECTOR AS SECTOR,\n" +
            "                                 EXTRACT(YEAR FROM DAY)                       AS YEAR,\n" +
            "                                 EXTRACT(MONTH FROM DAY)                      AS MONTH,\n" +
            "                                 1 + TRUNC((EXTRACT(MONTH FROM DAY) - 1) / 4) AS QUARTER,\n" +
            "                                 CLOSE                                        AS VALUE\n" +
            "                          FROM STOCK\n" +
            "                          UNION\n" +
            "                          SELECT\n" +
            "                              CURRENCY AS SYMBOL,\n" +
            "                              (SELECT 'CRYPTO_CURRENCY' AS SEC FROM DUAL)  AS SECTOR,\n" +
            "                              EXTRACT(YEAR FROM DAY)                       AS YEAR,\n" +
            "                              EXTRACT(MONTH FROM DAY)                      AS MONTH,\n" +
            "                              1 + TRUNC((EXTRACT(MONTH FROM DAY) - 1) / 4) AS QUARTER,\n" +
            "                              CLOSE                                        AS VALUE\n" +
            "                            FROM CRYPTO_CURRENCY\n" +
            "                          UNION\n" +
            "                          SELECT\n" +
            "                              STOCK_INDEX_NAME AS SYMBOL,\n" +
            "                              STOCK_INDEX_NAME AS SECTOR,\n" +
            "                              EXTRACT(YEAR FROM DAY)                       AS YEAR,\n" +
            "                              EXTRACT(MONTH FROM DAY)                      AS MONTH,\n" +
            "                              1 + TRUNC((EXTRACT(MONTH FROM DAY) - 1) / 4) AS QUARTER,\n" +
            "                              CLOSE                                        AS VALUE\n" +
            "                          FROM STOCK_INDEX\n" +
            "                          )\n" +
            "                    WHERE YEAR >= :minYear\n" +
            "                      and YEAR <= :maxYear\n" +
            "                      and SECTOR IN :sectors\n" +
            "                    GROUP BY SECTOR, SYMBOL, YEAR, MONTH\n" +
            "                    ORDER BY SECTOR, SYMBOL, YEAR, MONTH),\n" +
            "     MIN_TABLE AS (SELECT SECTOR, SYMBOL\n" +
            "                        , VALUE AS MIN_VALUE\n" +
            "                   FROM (SELECT ROW_NUMBER() over (PARTITION BY SECTOR, SYMBOL ORDER BY YEAR, MONTH) AS ROW_NUMBER\n" +
            "                              , SECTOR\n" +
            "                              , SYMBOL\n" +
            "                              , MONTH\n" +
            "                              , VALUE\n" +
            "                         FROM BASE_TABLE)\n" +
            "                   WHERE ROW_NUMBER = 1),\n" +
            "    SECTOR_COMPANIES_TREND AS (SELECT\n" +
            "                                      BASE_TABLE.SECTOR,\n" +
            "                                      BASE_TABLE.SYMBOL,\n" +
            "                                      BASE_TABLE.YEAR,\n" +
            "                                      BASE_TABLE.MONTH,\n" +
            "                                      ((BASE_TABLE.VALUE - MIN_TABLE.MIN_VALUE) / MIN_TABLE.MIN_VALUE) * 100 AS VALUE,\n" +
            "                                      BASE_TABLE.VALUE                                                       AS ORIGINAL_VALUE\n" +
            "                               FROM BASE_TABLE\n" +
            "                                        JOIN MIN_TABLE\n" +
            "                                             on BASE_TABLE.SYMBOL = MIN_TABLE.SYMBOL\n" +
            "                                                and BASE_TABLE.SECTOR = MIN_TABLE.SECTOR\n" +
            "                               ),\n" +
            "    SECTOR_RESULT AS (\n" +
            "        SELECT\n" +
            "            (SELECT 'SECTOR' FROM DUAL) AS TYPE,\n" +
            "            SECTOR AS SYMBOL,\n" +
            "            YEAR,\n" +
            "            MONTH AS SUBYEAR,\n" +
            "            VALUE\n" +
            "            FROM (\n" +
            "                    SELECT\n" +
            "                        SECTOR,\n" +
            "                        YEAR,\n" +
            "                        MONTH,\n" +
            "                        AVG(VALUE) AS VALUE\n" +
            "                    FROM SECTOR_COMPANIES_TREND\n" +
            "                    GROUP BY SECTOR, YEAR, MONTH\n" +
            "            )\n" +
            "    )\n" +
            "SELECT * FROM (\n" +
            "                SELECT * FROM INDICATOR_RESULT\n" +
            "                UNION ALL\n" +
            "                SELECT * FROM SECTOR_RESULT\n" +
            "              )\n" +
            "ORDER BY TYPE, SYMBOL, YEAR, SUBYEAR";

}
