package com.gloomberg.marketlens.query.news;

import org.springframework.stereotype.Component;


public interface NewsInfluenceQuery {

    String ALL_NEWS_EVENTS_QUERY = "SELECT * FROM NEWS_EVENTS";

    String NEWS_EVENT_LINKS = "SELECT LINK FROM EVENT_LINKS WHERE EVENT = :event";

    String NEWS_INFLUENCE_QUERY = "WITH EVENT_DETAILS AS (SELECT * FROM NEWS_EVENTS WHERE EVENT = :event),\n" +
            "     BASE_TABLE AS (SELECT SYMBOL, SECTOR, YEAR, MONTH, AVG(VALUE) AS VALUE\n" +
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
            "                          )\n" +
            "                    WHERE YEAR >= (SELECT START_YEAR FROM EVENT_DETAILS)\n" +
            "                      and YEAR <= (SELECT END_YEAR FROM EVENT_DETAILS)\n" +
            "                      and SECTOR IN (\n" +
            "                            SELECT SECTOR FROM NEWS_INFLUENCE WHERE EVENT = :event\n" +
            "                        )\n" +
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
            "                               )\n" +
            "    SELECT\n" +
            "        SECTOR,\n" +
            "        YEAR,\n" +
            "        MONTH AS SUB_YEAR,\n" +
            "        AVG(VALUE) AS VALUE\n" +
            "    FROM SECTOR_COMPANIES_TREND\n" +
            "    GROUP BY SECTOR, YEAR, MONTH\n" +
            "    ORDER BY SECTOR, YEAR, SUB_YEAR";

}
