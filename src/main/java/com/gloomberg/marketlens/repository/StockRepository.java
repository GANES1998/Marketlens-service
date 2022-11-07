package com.gloomberg.marketlens.repository;

import com.gloomberg.marketlens.entity.Stock;
import com.gloomberg.marketlens.query.StockQueries;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Repository
public class StockRepository extends AbstractRepository {

    @Autowired
    EntityManager entityManager;

    public List<Stock> getStockBetween(String symbol, Date from, Date to) {
        Query stockQuery = entityManager.createNativeQuery(
                StockQueries.GET_QUOTES_BETWEEN,
                Stock.class
        );

        setQueryParams(stockQuery, "symbol", symbol, "from", from, "to", to);

        List<Stock> resultList = (List<Stock>) stockQuery.getResultList();

        return resultList;
    }

    public List<String> getAllStocks() {
        Query query = entityManager.createNativeQuery(
                StockQueries.GET_ALL_STOCK_SYMBOLS
        );

        List<String> stocks = (List<String>) query.getResultList();

        return stocks;
    }

}
