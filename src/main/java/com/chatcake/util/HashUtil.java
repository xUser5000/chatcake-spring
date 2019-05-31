package com.chatcake.util;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class HashUtil {

    private static final int COST = 12;

    public String hash (String str) {
        return BCrypt.withDefaults().hashToString(COST, str.toCharArray());
    }

    public boolean compare (String str, String bcryptHashString) {
        return BCrypt.verifyer().verify(str.toCharArray(), bcryptHashString).verified;
    }

}
