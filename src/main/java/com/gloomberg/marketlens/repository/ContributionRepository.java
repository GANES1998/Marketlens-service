package com.gloomberg.marketlens.repository;

import com.gloomberg.marketlens.dto.contribution.ContributionResult;
import com.gloomberg.marketlens.query.contribution.Contribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ContributionRepository extends AbstractRepository {


    @Autowired
    EntityManager entityManager;

    public List<ContributionResult> getContributionResult(int minYear, int maxYear, String sector, int n) {

        System.out.println("minY - "+ minYear + " - maxY" + maxYear + " - sect - " + sector + " - n - " + n);

        Query query = entityManager.createNativeQuery(
                Contribution.CONTRIBUTION_QUERY,
                "CONTRIBUTION_RESULT_SET"
        );

        setQueryParams(query, "minYear", minYear, "maxYear", maxYear, "sector", sector, "n", n);

        List<ContributionResult> resultList = (List<ContributionResult>) query.getResultList();

        return resultList;
    }

}
