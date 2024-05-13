package com.gloomberg.marketlens.repository;

import com.gloomberg.marketlens.query.TupleCountQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class TupleCountsRepository extends AbstractRepository {

    @Autowired
    EntityManager entityManager;

    private Long getRowCount(String query) {
        Query nativeQuery = entityManager.createNativeQuery(query);

        return ((BigInteger) nativeQuery.getSingleResult()).longValue();
    }

    public Map<String, Long> getTupleCounts() {

        Map<String, Long> tupleCountsQuery = new LinkedHashMap<>();
        long total = 0;

        for(String tableName : TupleCountQueries.TABLES) {

            String baseQuery = TupleCountQueries.GET_TUPLE_COUNTS;

            baseQuery = baseQuery.replaceAll("\\$TABLE\\$", tableName);

            Long tupleCount = getRowCount(baseQuery);

            total += tupleCount;

            tupleCountsQuery.put(tableName, tupleCount);
        }

        tupleCountsQuery.put("TOTAL", total);

        return tupleCountsQuery;
    }

}
