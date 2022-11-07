package com.gloomberg.marketlens.repository;

import com.google.common.collect.Lists;

import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;

public class AbstractRepository {

    protected void setQueryParams(Query query, Object... params) {
        List<Object> paramsList = Lists.newArrayList(params);

        Iterator<Object> paramsIterator = paramsList.stream().iterator();

        while (paramsIterator.hasNext()) {
            String key = paramsIterator.next().toString();

            Object value = null;

            if (paramsIterator.hasNext()) {
                value = paramsIterator.next();
            }

            query.setParameter(key, value);
        }
    }

}
