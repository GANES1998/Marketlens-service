package com.gloomberg.marketlens.query;

import com.google.common.collect.Lists;

import java.util.List;

public interface TupleCountQueries {

    String GET_TUPLE_COUNTS = "SELECT COUNT(*) FROM $TABLE$";


    List<String> TABLES = Lists.newArrayList(
            "STOCK",
            "SECTOR",
            "STOCK_INDEX",
//            "STOCK_SECTOR",
            "CRYPTO_CURRENCY",
            "COMPANY_SHARES",
            "COMPANY_NAME",
            "COMMODITY",
            "COVID_DATA",
            "TREASURY_BOND",
            "MONTHLY_INDICATOR",
            "COMPANY",
            "NEWS_EVENTS",
            "EVENT_LINKS",
            "NEWS_INFLUENCE"
    );

}
