package com.backend.emojifier.encoders;

public class EmojiEncoder {
    private String url;

    public EmojiEncoder(String url){
        this.url = url;
    }
    public String encode(){
        if (this.url.isEmpty()) return "Error";
        
        return "encoded";
    }
}
