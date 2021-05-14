package com.backend.emojifier.encoders;


import java.util.Collections;
import java.util.stream.Collectors;

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
        .collect(Collectors.collectingAndThen(Collectors.toList(), finisher -> {
        Collections.shuffle(finisher);
        return finisher.stream();
    }))
        .limit(10)
        .forEach(em -> sb.append(em.getHtmlHexadecimal()));
        encodedUrl = sb.toString();
    }

    public String getEncodedUrl(){
        return this.encodedUrl;
    }
}
