package com.gloomberg.marketlens.repository;

import com.gloomberg.marketlens.dto.news.NewsInfluenceResult;
import com.gloomberg.marketlens.entity.NewsEvent;
import com.gloomberg.marketlens.query.news.NewsInfluenceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class NewsInfluenceRepository extends AbstractRepository {

    @Autowired
    EntityManager entityManager;

    public List<NewsInfluenceResult> getNewsInfluenceResult(String event) {

        Query query = entityManager.createNativeQuery(
                NewsInfluenceQuery.NEWS_INFLUENCE_QUERY,
                "NEWS_INFLUENCE_RESULT_SET_MAPPING"
        );

        setQueryParams(query, "event", event);

        List<NewsInfluenceResult> resultList = (List<NewsInfluenceResult>) query.getResultList();

        return resultList;
    }

    public List<NewsEvent> getAllNewsEvents() {

        Query query = entityManager.createNativeQuery(
                NewsInfluenceQuery.ALL_NEWS_EVENTS_QUERY,
                NewsEvent.class
        );

        List<NewsEvent> resultList = (List<NewsEvent>) query.getResultList();

        return resultList;
    }

}
