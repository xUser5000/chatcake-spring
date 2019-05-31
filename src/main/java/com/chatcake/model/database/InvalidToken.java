package com.chatcake.model.database;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "invalidTokens")
public class InvalidToken {

    private String content;

    public InvalidToken(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
