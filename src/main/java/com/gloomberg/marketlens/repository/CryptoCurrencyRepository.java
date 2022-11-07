package com.gloomberg.marketlens.repository;

import com.gloomberg.marketlens.query.CryptoQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CryptoCurrencyRepository extends AbstractRepository {

    @Autowired
    EntityManager entityManager;

    public List<String> getAllCryptoCurrencies() {
        Query query = entityManager.createNativeQuery(
                CryptoQueries.GET_ALL_CRYPTO_CURRENCIES
        );

        List<String> resultList = query.getResultList();

        return resultList;
    }

}
