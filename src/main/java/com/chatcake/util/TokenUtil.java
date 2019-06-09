package com.chatcake.util;

import com.chatcake.model.database.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class TokenUtil {

    private final Long EXPIRATION = 604800L;
    private final String TOkEN_SECRET = "3p9n5v923u-5i1x3;tk4pu634wu3jtoi3joitewhiun6";

    public String generateToken (String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(generateExpiration())
                .signWith(SignatureAlgorithm.HS512, TOkEN_SECRET.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    public boolean verifyToken (String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(TOkEN_SECRET.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public String getUsernameFromToken (String token) {
        return Jwts.parser()
                .setSigningKey(TOkEN_SECRET.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private Date generateExpiration () {
        return new Date(System.currentTimeMillis() + EXPIRATION * 1000);
    }

}
