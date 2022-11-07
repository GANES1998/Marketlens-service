package com.gloomberg.marketlens.repository;

import com.gloomberg.marketlens.query.SectorQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class SectorRepository {

    @Autowired
    EntityManager entityManager;


    public List<String> getAllSectors() {
        Query query = entityManager.createNativeQuery(
                SectorQueries.GET_ALL_SECTORS
        );

        List<String> allSectors = (List<String>) query.getResultList();

        return allSectors;
    }


}
