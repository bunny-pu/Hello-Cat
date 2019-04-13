package com.example.hellocat.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * @author gegeding
 */
@Entity
public class CatBreedModel {
    

    public String name;
    public String life_span;
    public String temperament;
    public String description;
    public String wikipedia_url;
    @Generated(hash = 1320164262)
    public CatBreedModel(String name, String life_span, String temperament,
            String description, String wikipedia_url) {
        this.name = name;
        this.life_span = life_span;
        this.temperament = temperament;
        this.description = description;
        this.wikipedia_url = wikipedia_url;
    }
    @Generated(hash = 1153968875)
    public CatBreedModel() {
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLife_span() {
        return this.life_span;
    }
    public void setLife_span(String life_span) {
        this.life_span = life_span;
    }
    public String getTemperament() {
        return this.temperament;
    }
    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getWikipedia_url() {
        return this.wikipedia_url;
    }
    public void setWikipedia_url(String wikipedia_url) {
        this.wikipedia_url = wikipedia_url;
    }
}
