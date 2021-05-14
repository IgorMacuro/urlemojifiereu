package com.backend.emojifier.repositories;

import java.util.List;

import com.backend.emojifier.entities.Url;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UrlRepository extends CrudRepository<Url, Long> {
    List<Url> findAll (Pageable pageable);
    Url findByEncodedUrl(String encodedUrl);
    
}
