package com.backend.emojifier.services;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.backend.emojifier.entities.Url;
import com.backend.emojifier.repositories.UrlRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    public static final Logger log = LoggerFactory.getLogger(UrlService.class);
    @Autowired
    private UrlRepository urlRepository;

    public Iterable<Url> list(){
        return urlRepository.findAll();
    }
    public Url save(Url url) {
        return urlRepository.save(url);
    }

    public Url findByEncodedUrl(String encodedUrl) throws UnsupportedEncodingException{
        String decoded = URLDecoder.decode(encodedUrl, "UTF-8");
        log.info("decoded url: " + decoded);
        Url found = urlRepository.findByEncodedUrl(decoded);
        log.info("Plain url found: " + found.toString());
        return found;
    }
}
