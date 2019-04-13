package com.example.hellocat.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author gegeding
 */
@Entity
public class CatCategoriesModel {
    @Id(autoincrement = true)
    public Long c_id;
    public int id;
    public String name;
    @Generated(hash = 860539315)
    public CatCategoriesModel(Long c_id, int id, String name) {
        this.c_id = c_id;
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 76646431)
    public CatCategoriesModel() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getC_id() {
        return this.c_id;
    }
    public void setC_id(Long c_id) {
        this.c_id = c_id;
    }
}
