package com.gloomberg.marketlens.repository;

import com.gloomberg.marketlens.dto.variance.SectorVarianceResult;
import com.gloomberg.marketlens.query.variance.SectorVarianceQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class SectorVarianceRepository extends AbstractRepository {


    @Autowired
    EntityManager entityManager;

    public List<SectorVarianceResult> getSectorVarianceResult(
            List<String> sectors,
            int minYear,
            int maxYear,
            String aggBy
    ) {

        String baseQuery = StringUtils.replaceAll(
                SectorVarianceQuery.SECTOR_VARIANCE_QUERY,
                "\\$AGG_PARAM\\$",
                StringUtils.upperCase(aggBy)
        );

        Query query = entityManager.createNativeQuery(
                baseQuery,
                "SECTOR_VARIANCE_RESULT_SET"
        );

        setQueryParams(query, "sectors", sectors, "minYear", minYear, "maxYear", maxYear);

        List<SectorVarianceResult> resultList = (List<SectorVarianceResult>) query.getResultList();

        return resultList;
    }

}
