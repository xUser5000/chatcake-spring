package com.chatcake.Repository;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

class BaseRepository {

    MongoTemplate mongoTemplate;

    Query generateQuery (Criteria criteria) {
        return new Query().addCriteria(criteria);
    }

}
