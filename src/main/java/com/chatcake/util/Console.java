package com.chatcake.util;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class Console {
    private Log log = LogFactory.getLog(getClass());
    public void log (String msg) {
        log.info(msg);
    }
}
