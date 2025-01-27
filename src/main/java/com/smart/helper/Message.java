package com.smart.helper;

public class Message {

    private String content;
    private String type;

    public Message(String content, String type) {
        this.content = content;
        this.type = type;
    }
    


    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    
}
