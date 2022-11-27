package com.gloomberg.marketlens.repository;

import com.gloomberg.marketlens.dto.econinfluence.EconInfluenceResult;
import com.gloomberg.marketlens.query.econinfluecne.EconInfluenceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EconInfluenceRepository extends AbstractRepository {

    @Autowired
    EntityManager entityManager;

    public List<EconInfluenceResult> getResult(
            List<String> indicators,
            List<String> sectors,
            int minYear,
            int maxYear
    ) {

        Query econInfluenceResultSet = entityManager.createNativeQuery(
                EconInfluenceQuery.GET_ECON_INFLUENCE,
                "ECON_INFLUENCE_RESULT_SET"
        );

        setQueryParams(econInfluenceResultSet, "indicators", indicators, "sectors", sectors,
                "minYear", minYear, "maxYear", maxYear);

        List<EconInfluenceResult> resultList = econInfluenceResultSet.getResultList();

        return resultList;
    }

}
