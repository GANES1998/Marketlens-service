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
public class StockRepository {

    @Autowired
    EntityManager entityManager;

    private void setQueryParams(Query query, Object... params) {
        List<Object> paramsList = Lists.newArrayList(params);

        Iterator<Object> paramsIterator = paramsList.stream().iterator();

        while (paramsIterator.hasNext()) {
            String key = paramsIterator.next().toString();

            Object value = null;

            if (paramsIterator.hasNext()) {
                value = paramsIterator.next();
            }

            query.setParameter(key, value);
        }
    }

    public List<Stock> getStockBetween(String symbol, Date from, Date to) {
        Query stockQuery = entityManager.createNativeQuery(
                StockQueries.GET_QUOTES_BETWEEN,
                Stock.class
        );

        setQueryParams(stockQuery, "symbol", symbol, "from", from, "to", to);

        List<Stock> resultList = (List<Stock>) stockQuery.getResultList();

        return resultList;
    }

}
