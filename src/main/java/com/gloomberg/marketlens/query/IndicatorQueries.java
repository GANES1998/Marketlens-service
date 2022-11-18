package com.gloomberg.marketlens.query;

public interface IndicatorQueries {

    String GET_INDICATOR_QUERIES = "SELECT *\n" +
            "FROM (SELECT INDICATOR\n" +
            "      FROM MONTHLY_INDICATOR\n" +
            "      UNION\n" +
            "      SELECT INDICATOR\n" +
            "      FROM QUARTERLY_INDICATOR)";

}
