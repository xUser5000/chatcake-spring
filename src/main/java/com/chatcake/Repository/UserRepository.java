package com.chatcake.Repository;

import com.chatcake.model.database.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository extends BaseRepository {

    @Autowired
    public UserRepository (MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public User getUserInfo (String username) {
        return mongoTemplate.findOne(generateQuery(Criteria.where("username").is(username)), User.class);
    }

    public void saveUser (User user) {
        mongoTemplate.save(user);
    }

    public User findUserByUsername (String username) {
        return mongoTemplate.findOne(generateQuery(Criteria.where("username").is(username)), User.class);
    }

    public void addRoom (String username, String roomId) {
        Query query = generateQuery(Criteria.where("username").is(username));
        Update update = new Update().push("rooms", roomId);
        mongoTemplate.updateFirst(query, update, User.class);
    }

    public void removeRoom (String username, String roomId) {
        Query query = generateQuery(Criteria.where("username").is(username));
        Update update = new Update().pull("rooms", roomId);
        mongoTemplate.updateFirst(query, update, User.class);
    }

    public boolean checkUsername (String username) {
        return mongoTemplate.findOne(generateQuery(Criteria.where("username").is(username)), User.class) != null;
    }

    public List<String> getRooms (String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        query.fields().include("rooms");
        User user = mongoTemplate.findOne(query, User.class);
        return user.getRooms();
    }

    // search for a user in the database using name or username
    public List<User> searchUser (String queryString) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matching(queryString);
        Query query = TextQuery.queryText(criteria).sortByScore().with(PageRequest.of(0, 5));
        query.fields().exclude("rooms");
        return mongoTemplate.find(query, User.class);
    }
}