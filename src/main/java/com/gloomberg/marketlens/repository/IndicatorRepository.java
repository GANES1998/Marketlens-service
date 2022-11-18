package com.gloomberg.marketlens.repository;

import com.gloomberg.marketlens.query.IndicatorQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class IndicatorRepository {

    @Autowired
    EntityManager entityManager;

    public List<String> getAllIndicators() {
        Query query = entityManager.createNativeQuery(
                IndicatorQueries.GET_INDICATOR_QUERIES
        );

        List<String> resultList = query.getResultList();

        return resultList;
    }

}
