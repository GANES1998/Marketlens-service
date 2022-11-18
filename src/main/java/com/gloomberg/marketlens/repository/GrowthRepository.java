package com.gloomberg.marketlens.repository;

import com.gloomberg.marketlens.entity.GrowthResult;
import com.gloomberg.marketlens.query.GrowthQueries;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Repository
public class GrowthRepository extends AbstractRepository {

    @Autowired
    @Qualifier("investment_growth_queries_map")
    Map<String, String> queriesMap;

    @Autowired
    EntityManager entityManager;
    public List<GrowthResult> getGrowth(List<String> symbols, int startYear, int endYear, String groupByParam) {

        String finalizedQuery = "";
        List<Object> params = Lists.newArrayList("minYear", startYear, "maxYear", endYear);

        List<String> queryComponents = Lists.newArrayList();

        int symbolNumber = 1;

        for(String symbol: symbols) {

            String symbolType = symbol.split(":")[0];
            String symbolValue = symbol.split(":")[1];

            String baseQuery = queriesMap.get(StringUtils.upperCase(symbolType));

            baseQuery = baseQuery.replaceAll("\\$AGG_PARAM\\$", StringUtils.upperCase(groupByParam));
            if (!StringUtils.equalsIgnoreCase(symbolType, "covid")) {
                String symbolReplacement = "symbol".concat("" + symbolNumber);
                baseQuery = baseQuery.replace(":symbol", ":"+symbolReplacement);

                params.add(symbolReplacement);
                params.add(symbolValue);
            }

            queryComponents.add(baseQuery);

            symbolNumber += 1;
        }

        String finalQuery = StringUtils.join(queryComponents, "\n UNION ALL \n");
        finalQuery = finalQuery + " \n "+ GrowthQueries.ORDER_BY_CLAUSE +" " ;

        System.out.println(finalQuery);

        Query query = entityManager.createNativeQuery(
                finalQuery,
                "growthResultMapping"
        );

        setQueryParams(query, params.toArray());

        List<GrowthResult> resultList = (List<GrowthResult>) query.getResultList();

        return resultList;
    }

}
