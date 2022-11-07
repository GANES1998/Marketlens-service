package com.gloomberg.marketlens.repository;

import com.gloomberg.marketlens.query.CommodityQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CommodityRepository extends AbstractRepository {

    @Autowired
    EntityManager entityManager;

    public List<String> getAllCommodities() {
        Query query = entityManager.createNativeQuery(
                CommodityQueries.GET_ALL_COMMODITIES
        );

        List<String> resultList = query.getResultList();

        return resultList;
    }
}
