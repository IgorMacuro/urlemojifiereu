package com.backend.emojifier.services;

import java.util.List;

import com.backend.emojifier.entities.Url;
import com.backend.emojifier.repositories.UrlRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    @Autowired
    private UrlRepository urlRepository;

    public Iterable<Url> list(){
        return urlRepository.findAll();
    }
    public Url save(Url url) {
        return urlRepository.save(url);
    }

    public Url findByEncodedUrl(String encodedUrl){
        return urlRepository.findByEncodedUrl(encodedUrl);
    }
}
