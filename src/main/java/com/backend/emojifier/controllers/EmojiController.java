package com.backend.emojifier.controllers;


import com.backend.emojifier.encoders.EmojiEncoder;
import com.backend.emojifier.entities.Error;
import com.backend.emojifier.entities.Url;
import com.backend.emojifier.services.UrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
@CrossOrigin(origins = "https://emojifiereu.herokuapp.com:80")
public class EmojiController {
    public static final Logger log = LoggerFactory.getLogger(EmojiController.class);
    private UrlService urlService;
    private EmojiEncoder emojiEncoder;

    public EmojiController(UrlService urlService, EmojiEncoder emojiEncoder) {
        this.urlService = urlService;
        this.emojiEncoder = emojiEncoder;
    }

    @GetMapping(path = "/")
    public @ResponseBody
    String decode() {
        return urlService.list().toString();
    }

    @GetMapping(path = "/{url}")
    public @ResponseBody
    String decode(@PathVariable String url) {
        try {
            Url urlFound = urlService.findByEncodedUrl(url);
            if (urlFound != null) return urlFound.getUrl();
        } catch (UnsupportedEncodingException e) {
            log.error("fishy url", e);
            return Error.NOT_FOUND;
        }
        return Error.NOT_FOUND;
    }

    @GetMapping(path = "/encode/{url}")
    public @ResponseBody
    Url encode(@PathVariable String url) {
        log.info("controller encode: " + url);
        log.info(emojiEncoder == null ? "EmojiEncoder is null" : "EmojiEncoder is fine");
        emojiEncoder.setUrl(url);
        emojiEncoder.encodeUrl();
        return emojiEncoder.persist();
    }
}
