package com.gloomberg.marketlens.query;

import java.util.Date;

public interface StockQueries {

    String GET_QUOTES_BETWEEN = "SELECT * FROM STOCK WHERE SYMBOL = :symbol and DAY >= :from and DAY <= :to";

    String GET_ALL_STOCK_SYMBOLS = "SELECT DISTINCT(SYMBOL) FROM STOCK";

}
