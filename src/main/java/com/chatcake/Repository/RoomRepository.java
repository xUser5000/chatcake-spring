package com.chatcake.Repository;

import com.chatcake.model.database.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class RoomRepository extends BaseRepository {

    @Autowired
    public RoomRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Room getRoomInfo (String roomId) {
        return mongoTemplate.findById(roomId, Room.class);
    }

    public String createRoom (String username, String roomName) {
        Room room = new Room(roomName, username);
        return mongoTemplate.save(room).getId();
    }

    public void addMember (String username, String roomId) {
        Query query = generateQuery(Criteria.where("id").is(roomId));
        Update update = new Update().push("members", username);
        mongoTemplate.updateFirst(query, update, Room.class);
    }

    public boolean checkRoom (String roomId) {
        return mongoTemplate.findById(roomId, Room.class) != null;
    }

    public String getRoomName (String roomId) {
        Query query = new Query().addCriteria(Criteria.where("id").is(roomId));
        query.fields().include("name");
        return mongoTemplate.findOne(query, Room.class).getName();
    }
}
