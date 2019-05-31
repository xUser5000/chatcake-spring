package com.chatcake.component.Room;

import com.chatcake.Repository.RoomRepository;
import com.chatcake.Repository.UserRepository;
import com.chatcake.exception.ForbiddenException;
import com.chatcake.exception.NotFoundException;
import com.chatcake.model.database.Room;
import com.chatcake.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class RoomService {

    @Autowired private RoomRepository roomRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private TokenUtil tokenUtil;

    Room getRoomInfo (String roomId, String token) {

        Room room = roomRepository.getRoomInfo(roomId);
        String username = tokenUtil.getUsernameFromToken(token);

        // assert the room exists
        if (room == null)
            throw new NotFoundException("Room was not found");

        // assert the user is either an admin or a usual member in the room
        if (!room.getAdmin().equals(username) && !room.getMembers().contains(username))
            throw new ForbiddenException("You are not a member in the room");

        return room;
    }

    Room createRoom (String token, String roomName) {
        String username = tokenUtil.getUsernameFromToken(token);
        String roomId = roomRepository.createRoom(username, roomName);
        userRepository.addRoom(username, roomId);
        Room room = new Room();
        room.setName(roomName);
        room.setId(roomId);
        room.setAdmin(username);
        return room;
    }

    void addMember (String username, String roomId, String token) {

        // check if the room exists
        if (!roomRepository.checkRoom(roomId))
            throw new NotFoundException("Room was not found");

        // check if the username exists
        if (!userRepository.checkUsername(username))
            throw new NotFoundException("User was not found");

        String admin = tokenUtil.getUsernameFromToken(token);
        Room room = roomRepository.getRoomInfo(roomId);

        // assert that the username is an admin
        if (!room.getAdmin().equals(admin))
            throw new ForbiddenException("You are not an admin");

        // assert the user is not in the room
        if (room.getMembers().contains(username))
            throw new ForbiddenException("User is already a member in the room");

        roomRepository.addMember(username, roomId);
        userRepository.addRoom(username, roomId);
    }

}