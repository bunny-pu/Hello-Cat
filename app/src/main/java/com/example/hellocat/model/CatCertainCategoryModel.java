package com.example.hellocat.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * @author gegeding
 */
@Entity
public class CatCertainCategoryModel {

    @Id(autoincrement = true)
    public Long c_id;
    public String url;
    public String id;
    public int height;
    public int width;
    @Generated(hash = 1934100132)
    public CatCertainCategoryModel(Long c_id, String url, String id, int height,
            int width) {
        this.c_id = c_id;
        this.url = url;
        this.id = id;
        this.height = height;
        this.width = width;
    }
    @Generated(hash = 313564299)
    public CatCertainCategoryModel() {
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getHeight() {
        return this.height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getWidth() {
        return this.width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public Long getC_id() {
        return this.c_id;
    }
    public void setC_id(Long c_id) {
        this.c_id = c_id;
    }
}
