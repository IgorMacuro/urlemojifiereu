package com.backend.emojifier.controllers;



import java.net.URLEncoder;

import com.backend.emojifier.encoders.EmojiEncoder;
import com.backend.emojifier.repositories.UrlRepository;
import com.backend.emojifier.services.UrlService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "https://emojifiereu.herokuapp.com:80")
public class EmojiController {
    public static final Logger log = LoggerFactory.getLogger(EmojiController.class);
    private UrlService urlService;
    private EmojiEncoder ee;
    public EmojiController(UrlService urlService, EmojiEncoder emojiEncoder){
        this.urlService = urlService;
        this.ee = emojiEncoder;
    }
    
    @GetMapping(path = "/")
    public @ResponseBody String decode(){
        return urlService.list().toString();
    }

    @GetMapping(path = "/{url}")
    public @ResponseBody String decode(@PathVariable String url){
        return "input url: " + urlService.findByEncodedUrl(url);
    }

    @GetMapping (path = "/encode/{url}")
    public @ResponseBody String encode (@PathVariable String url) {
        log.info("controller encode");
        log.info(ee == null ? "EmojiEncoder is null" : "EmojiEncoder is fine");
        ee.setUrl(url);
        ee.encodeUrl();
        return ee.getEncodedUrl();
    }
}
