package com.backend.emojifier.encoders;


import java.util.Collections;
import java.util.stream.Collectors;

import com.backend.emojifier.entities.Url;
import com.backend.emojifier.repositories.UrlRepository;
import com.vdurmont.emoji.EmojiManager;

import org.springframework.beans.factory.annotation.Autowired;

public class EmojiEncoder {
    private Url url;
    private int attempts_num = 10;

    @Autowired
    UrlRepository urlRepository;


    public EmojiEncoder(String url){

        this.url =  new Url(url);
    }

    private void encode(){
        if (this.url.getUrl().isEmpty()) return;
        StringBuilder sb = new StringBuilder();
        EmojiManager.getAll()
        .stream()
        .collect(Collectors.collectingAndThen(Collectors.toList(), finisher -> {
        Collections.shuffle(finisher);
        return finisher.stream();
    }))
        .limit(10)
        .forEach(em -> sb.append(em.getHtmlHexadecimal()));
        this.url.setEncodedUrl(sb.toString());
    }

    public void encodeUrl(){
        for (int i = 0; i < attempts_num; i++) {
            encode();
            if(urlRepository.findByEncodedUrl(url.getEncodedUrl()) == null) break;
        }
    }

    public String getEncodedUrl(){
        return this.url.getEncodedUrl();
    }
}
