package com.backend.emojifier.encoders;


import com.vdurmont.emoji.EmojiManager;

public class EmojiEncoder {
    private String url;
    private String encodedUrl;

    public EmojiEncoder(String url){
        this.url = url;
    }
    public void encode(){
        if (this.url.isEmpty()) return;
        StringBuilder sb = new StringBuilder();
        EmojiManager.getAll()
        .stream()
        .limit(10)
        .forEach(em -> sb.append(em.getHtmlHexadecimal()));
        encodedUrl = sb.toString();
    }

    public String getEncodedUrl(){
        return this.encodedUrl;
    }
}
