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

    // get info about a user
    @GetMapping("/getInfo/{username}")
    public User getUserInfo (@NotEmpty @PathVariable String username) {
        return userService.getUserInfo(username);
    }

    // get all rooms for a user
    @GetMapping("/getRooms")
    public List<Room> getRooms (@RequestHeader("Authorization") String token) {
        return userService.getRooms(token);
    }

    // search for user
    @GetMapping("/search/{query}")
    public List<User> searchUser (@PathVariable("query") String queryString) {
        System.out.println(queryString);
        return userService.searchUser(queryString);
    }

}
