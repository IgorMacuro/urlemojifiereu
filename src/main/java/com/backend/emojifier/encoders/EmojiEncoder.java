package com.backend.emojifier.encoders;


import java.util.Collections;
import java.util.stream.Collectors;

import com.backend.emojifier.entities.Url;
import com.backend.emojifier.repositories.UrlRepository;
import com.backend.emojifier.services.UrlService;
import com.vdurmont.emoji.EmojiManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

@Service
@Configurable
public class EmojiEncoder {
    private Url url;
    private int attempts_num = 10;
    public static final Logger log = LoggerFactory.getLogger(EmojiEncoder.class);

    public void setUrl(String url){
        this.url = new Url();
        this.url.setUrl(url);
    }

    @Autowired
    UrlService urlService;



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
        .forEach(em -> sb.append(em.getHtmlDecimal()));
        this.url.setEncodedUrl(sb.toString());
    }

    private void save(){
        log.info("SAVE");
        log.info(this.url.toString());
        log.info(urlService == null ? "service is null" : "service is fine");

        urlService.save(this.url);
    }
    public void encodeUrl(){
        encode();
        save();
    }

    public String getEncodedUrl(){
        return this.url.getEncodedUrl();
    }
}
