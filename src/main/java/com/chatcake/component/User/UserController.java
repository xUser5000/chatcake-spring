package com.chatcake.component.User;

import com.chatcake.model.database.Room;
import com.chatcake.model.database.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired private UserService userService;

    @GetMapping("/getInfo/{username}")
    public User getUserInfo (@NotEmpty @PathVariable String username) {
        return userService.getUserInfo(username);
    }

    @GetMapping("/getRooms")
    public List<Room> getRooms (@RequestHeader("Authorization") String token) {
        return userService.getRooms(token);
    }

}
