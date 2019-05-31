package com.chatcake.component.Auth;

import com.chatcake.Repository.InvalidTokenRepository;
import com.chatcake.Repository.RoomRepository;
import com.chatcake.Repository.UserRepository;
import com.chatcake.exception.ForbiddenException;
import com.chatcake.exception.UnAuthorizedException;
import com.chatcake.model.database.User;
import com.chatcake.model.request.LoginRequest;
import com.chatcake.model.request.RegisterRequest;
import com.chatcake.model.response.LoginResponse;
import com.chatcake.util.HashUtil;
import com.chatcake.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class AuthService {

    @Autowired private UserRepository userRepository;
    @Autowired private InvalidTokenRepository invalidTokenRepository;
    @Autowired private RoomRepository roomRepository;
    @Autowired private TokenUtil tokenUtil;
    @Autowired private HashUtil hashUtil;

    void register (RegisterRequest req) {
        // check if the username was use before
        if (userRepository.findUserByUsername(req.getUsername()) != null)
            throw new ForbiddenException("Username is already in use");
        User user = new User(req.getName(), req.getUsername(), hashUtil.hash(req.getPassword()));
        userRepository.saveUser(user);
    }

    LoginResponse login (LoginRequest req) {
        User user = userRepository.findUserByUsername(req.getUsername());

        if (user == null || !hashUtil.compare(req.getPassword(), user.getPassword()))
            throw new UnAuthorizedException("Username and password don't match");

        String token = tokenUtil.generateToken(user.getUsername());

        if (invalidTokenRepository.findToken(token))
            invalidTokenRepository.deleteToken(token);

        return new LoginResponse(token, user.getId(), user.getName(), user.getUsername());
    }

    void logout (String token) {
        invalidTokenRepository.saveToken(token);
    }

}
