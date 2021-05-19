package com.backend.emojifier.services;

import com.backend.emojifier.entities.Url;
import com.backend.emojifier.repositories.UrlRepository;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Service
@Log4j
public class UrlService {
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Iterable<Url> list() {
        return urlRepository.findAll();
    }

    public Url save(Url url) {
        return urlRepository.save(url);
    }

    public Url findByEncodedUrl(String encodedUrl) throws UnsupportedEncodingException {
        String decoded = URLDecoder.decode(encodedUrl, "UTF-8");
        log.info("decoded url: " + decoded);
        String decodedParsedToHtml = EmojiParser.parseToHtmlHexadecimal(decoded);
        log.info("Parsed to html: " + decodedParsedToHtml);
        Url found = urlRepository.findByEncodedUrl(decodedParsedToHtml);

        if (found != null) {
            log.info("Plain url found: " + found);
        }
        return found;
    }
}
