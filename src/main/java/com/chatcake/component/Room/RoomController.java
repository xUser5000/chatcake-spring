package com.chatcake.component.Room;

import com.chatcake.model.database.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired private RoomService roomService;

    @GetMapping("/getInfo/{roomId}")
    public Room getRoomInfo (@NotEmpty @PathVariable String roomId, @RequestHeader("Authorization") String token) {
        return roomService.getRoomInfo(roomId, token);
    }

    @PostMapping("/create/{roomName}")
    public Room createRoom (@NotEmpty @PathVariable String roomName, @RequestHeader("Authorization") String token) {
        return roomService.createRoom(token, roomName);
    }

    @PutMapping("/addMember/{roomId}/{username}")
    public void addMember (
            @NotEmpty @PathVariable String roomId,
            @NotEmpty @PathVariable String username,
            @NotEmpty @RequestHeader("Authorization") String token
    ) {
        roomService.addMember(username, roomId, token);
    }

}