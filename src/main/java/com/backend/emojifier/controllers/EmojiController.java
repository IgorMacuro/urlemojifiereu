package com.backend.emojifier.controllers;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "hhttps://emojifiereu.herokuapp.com:80")

public class EmojiController {
    
    @GetMapping(path = "/")
    public @ResponseBody String decode(){
        return "hello heroku";
    }

    @GetMapping(path = "/{url}")
    public @ResponseBody String decode(@PathVariable String url){
        return "input url: " + url;
    }
}
