package com.gloomberg.marketlens.query;

import java.util.Date;

public interface StockQueries {

    public static final String GET_QUOTES_BETWEEN = "SELECT * FROM STOCK WHERE SYMBOL = :symbol and DAY >= :from and DAY <= :to";

}
