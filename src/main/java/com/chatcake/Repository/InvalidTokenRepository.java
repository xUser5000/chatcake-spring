package com.chatcake.Repository;

import com.chatcake.model.database.InvalidToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;


@Repository
public class InvalidTokenRepository extends BaseRepository {

    @Autowired
    public InvalidTokenRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void saveToken (String token) {
        mongoTemplate.save(new InvalidToken(token));
    }

    public void deleteToken (String token) {
        mongoTemplate.remove(generateQuery(Criteria.where("content").is(token)), InvalidToken.class);
    }

    public boolean findToken (String token) {
        return mongoTemplate.findOne(generateQuery(Criteria.where("content").is(token)), InvalidToken.class) != null;
    }
}
