package com.gloomberg.marketlens.repository;

import com.gloomberg.marketlens.query.TreasuryQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TreasuryRepository {

    @Autowired
    EntityManager entityManager;

    public List<Integer> getAllTreasuries() {
        Query query = entityManager.createNativeQuery(
                TreasuryQueries.GET_ALL_TENURES
        );

        List<Integer> resultList = (List<Integer>) query.getResultList();

        return resultList;
    }

}
