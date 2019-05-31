package com.chatcake.component.User;

import com.chatcake.Repository.RoomRepository;
import com.chatcake.Repository.UserRepository;
import com.chatcake.exception.NotFoundException;
import com.chatcake.model.database.Room;
import com.chatcake.model.database.User;
import com.chatcake.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class UserService {

    @Autowired private RoomRepository roomRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private TokenUtil tokenUtil;

    User getUserInfo (String username) {
        User user = userRepository.getUserInfo(username);
        if (user == null)
            throw new NotFoundException("User was not found");
        user.setRooms(new ArrayList<>());
        return user;
    }

    List<Room> getRooms (String token) {
        String username = tokenUtil.getUsernameFromToken(token);

        List<Room> rooms = new ArrayList<>();

        for (String roomId: userRepository.getRooms(username)) {
            System.out.println("Length: " + userRepository.getRooms(username).size());
            Room room = new Room();
            room.setId(roomId);
            System.out.println(roomRepository.getRoomName(roomId));
            room.setName(roomRepository.getRoomName(roomId));
            rooms.add(room);
        }

        return rooms;
    }
}
