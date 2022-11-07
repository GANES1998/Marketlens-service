package com.gloomberg.marketlens.query;

import org.springframework.stereotype.Component;

@Component
public interface CryptoQueries {

    String GET_ALL_CRYPTO_CURRENCIES = "SELECT DISTINCT(CURRENCY) FROM CRYPTO_CURRENCY";

}
