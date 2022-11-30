package com.gloomberg.marketlens.query.variance;

public interface SectorVarianceQuery {

    String SECTOR_VARIANCE_QUERY = "WITH\n" +
            "     BASE_TABLE AS (SELECT SYMBOL, SECTOR, YEAR, $AGG_PARAM$, AVG(VALUE) AS VALUE, STDDEV(VALUE) AS STD_DEV\n" +
            "                    FROM (SELECT SYMBOL,\n" +
            "                                 STOCK_SECTOR AS SECTOR,\n" +
            "                                 EXTRACT(YEAR FROM DAY)                       AS YEAR,\n" +
            "                                 EXTRACT(MONTH FROM DAY)                      AS MONTH,\n" +
            "                                 1 + TRUNC((EXTRACT(MONTH FROM DAY) - 1) / 4) AS QUARTER,\n" +
            "                                 EXTRACT(YEAR FROM DAY)                       AS YEAR2,\n" +
            "                                 CLOSE                                        AS VALUE\n" +
            "                          FROM STOCK\n" +
            "                          UNION\n" +
            "                          SELECT\n" +
            "                              CURRENCY AS SYMBOL,\n" +
            "                              (SELECT 'CRYPTO_CURRENCY' AS SEC FROM DUAL)  AS SECTOR,\n" +
            "                              EXTRACT(YEAR FROM DAY)                       AS YEAR,\n" +
            "                              EXTRACT(MONTH FROM DAY)                      AS MONTH,\n" +
            "                              1 + TRUNC((EXTRACT(MONTH FROM DAY) - 1) / 4) AS QUARTER,\n" +
            "                              EXTRACT(YEAR FROM DAY)                       AS YEAR2,\n" +
            "                              CLOSE                                        AS VALUE\n" +
            "                            FROM CRYPTO_CURRENCY\n" +
            "                          )\n" +
            "                    WHERE YEAR >= :minYear\n" +
            "                      and YEAR <= :maxYear\n" +
            "                      and SECTOR IN :sectors\n" +
            "                    GROUP BY SECTOR, SYMBOL, YEAR, $AGG_PARAM$\n" +
            "                    ORDER BY SECTOR, SYMBOL, YEAR, $AGG_PARAM$),\n" +
            "     MIN_TABLE AS (SELECT SECTOR, SYMBOL\n" +
            "                        , VALUE AS MIN_VALUE\n" +
            "                   FROM (SELECT ROW_NUMBER() over (PARTITION BY SECTOR, SYMBOL ORDER BY YEAR, $AGG_PARAM$) AS ROW_NUMBER\n" +
            "                              , SECTOR\n" +
            "                              , SYMBOL\n" +
            "                              , $AGG_PARAM$\n" +
            "                              , VALUE\n" +
            "                         FROM BASE_TABLE)\n" +
            "                   WHERE ROW_NUMBER = 1),\n" +
            "    SECTOR_COMPANIES_TREND AS (SELECT\n" +
            "                                      BASE_TABLE.SECTOR,\n" +
            "                                      BASE_TABLE.SYMBOL,\n" +
            "                                      BASE_TABLE.YEAR,\n" +
            "                                      $AGG_PARAM$,\n" +
            "                                      ((BASE_TABLE.VALUE - MIN_TABLE.MIN_VALUE) / MIN_TABLE.MIN_VALUE) * 100 AS VALUE,\n" +
            "                                      BASE_TABLE.VALUE                                                       AS ORIGINAL_VALUE,\n" +
            "                                      BASE_TABLE.STD_DEV\n" +
            "                               FROM BASE_TABLE\n" +
            "                                        JOIN MIN_TABLE\n" +
            "                                             on BASE_TABLE.SYMBOL = MIN_TABLE.SYMBOL\n" +
            "                                                and BASE_TABLE.SECTOR = MIN_TABLE.SECTOR\n" +
            "                               )\n" +
            "    SELECT\n" +
            "        SECTOR,\n" +
            "        YEAR,\n" +
            "        $AGG_PARAM$ AS SUBYEAR,\n" +
            "        AVG(VALUE) AS VALUE,\n" +
            "        AVG(STD_DEV) AS STD_DEV\n" +
            "    FROM SECTOR_COMPANIES_TREND\n" +
            "    GROUP BY SECTOR, YEAR, $AGG_PARAM$\n" +
            "    ORDER BY SECTOR, YEAR, $AGG_PARAM$";

}
