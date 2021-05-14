package com.backend.emojifier.controllers;



import com.backend.emojifier.encoders.EmojiEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "https://emojifiereu.herokuapp.com:80")
public class EmojiController {
    
    @GetMapping(path = "/")
    public @ResponseBody String decode(){
        return "hello heroku";
    }

    @GetMapping(path = "/{url}")
    public @ResponseBody String decode(@PathVariable String url){
        return "input url: " + url;
    }

    @GetMapping (path = "/encode/{url}")
    public @ResponseBody String encode (@PathVariable String url) {
        EmojiEncoder ee = new EmojiEncoder(url);
        ee.encodeUrl();
        return ee.getEncodedUrl();
    }
}
