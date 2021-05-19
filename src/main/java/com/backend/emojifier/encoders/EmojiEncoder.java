package com.backend.emojifier.encoders;


import com.backend.emojifier.entities.Url;
import com.backend.emojifier.services.UrlService;
import com.vdurmont.emoji.EmojiManager;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
@Log4j
@Configurable
public class EmojiEncoder {
    private Url url;

    public void setUrl(String url) {
        this.url = new Url();
        //saving url as the base64 string (url is already base64 encoded from frontend)
        this.url.setUrl(url);
    }

    @Autowired
    UrlService urlService;


    private void encode() {
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

    private Url save() {
        log.info("SAVE");
        log.info(this.url.toString());
        log.info(urlService == null ? "service is null" : "service is fine");

        return urlService.save(this.url);
    }

    public void encodeUrl() {
        encode();
    }
    public Url persist(){
        return save();
    }

    public String getEncodedUrl() {
        return this.url.getEncodedUrl();
    }
}
