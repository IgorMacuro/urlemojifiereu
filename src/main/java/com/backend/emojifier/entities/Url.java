package com.backend.emojifier.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Url {
    @Id
    private Long id;
    private String url;
    private String encodedUrl;
    
    public Url(String url){
        this.url = url;
    }

}
