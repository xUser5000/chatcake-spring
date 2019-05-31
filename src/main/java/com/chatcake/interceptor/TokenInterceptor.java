package com.chatcake.interceptor;

import com.chatcake.Repository.InvalidTokenRepository;
import com.chatcake.exception.BadRequestException;
import com.chatcake.exception.UnAuthorizedException;
import com.chatcake.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired private TokenUtil tokenUtil;
    @Autowired private InvalidTokenRepository invalidTokenRepository;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authorization");

        if (token == null || token.equals(""))
            throw new BadRequestException("Token was not provided");

        if (!tokenUtil.verifyToken(token) || invalidTokenRepository.findToken(token))
            throw new UnAuthorizedException("Token is not valid");
        return true;
    }


}
