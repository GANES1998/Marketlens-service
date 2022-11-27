package com.gloomberg.marketlens.repository;

import com.gloomberg.marketlens.query.StockIndexQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class StockIndexRepository extends AbstractRepository {

    @Autowired
    EntityManager entityManager;

    public List<String> getAllStockIndices() {
        Query nativeQuery = entityManager.createNativeQuery(
                StockIndexQuery.GET_ALL_STOCK_INDICES
        );

        return (List<String>) nativeQuery.getResultList();
    }


}
