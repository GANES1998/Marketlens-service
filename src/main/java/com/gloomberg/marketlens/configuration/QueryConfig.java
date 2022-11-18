package com.gloomberg.marketlens.configuration;

import com.gloomberg.marketlens.query.GrowthQueries;
import com.google.common.collect.ImmutableMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class QueryConfig {

    @Bean(name = "investment_growth_queries_map")
    public Map<String, String> getInvestmentGrowthQueriesMap() {

        Map<String, String> resultMap = new HashMap<>();

        resultMap.put("STOCK", GrowthQueries.GROWTH_VALUE_STOCK);
        resultMap.put("COMMODITY", GrowthQueries.GROWTH_VALUE_COMMODITY);
        resultMap.put("CRYPTO_CURRENCY", GrowthQueries.GROWTH_VALUE_CRYPTO_CURRENCY);
        resultMap.put("COVID", GrowthQueries.GROWTH_VALUE_COVID);
        resultMap.put("ECON", GrowthQueries.GROWTH_VALUE_ECONOMIC_INDICATOR);
        resultMap.put("TREASURY", GrowthQueries.GROWTH_VALUE_TREASURY);

        resultMap.put("STOCKS", GrowthQueries.GROWTH_VALUE_STOCK);
        resultMap.put("COMMODITIES", GrowthQueries.GROWTH_VALUE_COMMODITY);
        resultMap.put("CRYPTOS", GrowthQueries.GROWTH_VALUE_CRYPTO_CURRENCY);
        resultMap.put("COVID_DATA", GrowthQueries.GROWTH_VALUE_COVID);
        resultMap.put("INDICATORS", GrowthQueries.GROWTH_VALUE_ECONOMIC_INDICATOR);
        resultMap.put("BONDS", GrowthQueries.GROWTH_VALUE_TREASURY);

        return resultMap;
    }

}
