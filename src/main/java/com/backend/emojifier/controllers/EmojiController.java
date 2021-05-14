package com.backend.emojifier.controllers;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.backend.emojifier.model.Match;
import com.backend.emojifier.repositories.MatchRepository;

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

}
