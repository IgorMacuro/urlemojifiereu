package com.backend.emojifier.repositories;

import com.backend.emojifier.entities.Url;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends CrudRepository<Url, Long> {
    List<Url> findAll(Pageable pageable);

    Url findByEncodedUrl(String encodedUrl);

}
