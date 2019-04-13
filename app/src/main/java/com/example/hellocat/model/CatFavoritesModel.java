package com.example.hellocat.model;


import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.converter.PropertyConverter;
import org.greenrobot.greendao.annotation.Generated;


/**
 * @author gegeding
 */
@Entity
public class CatFavoritesModel {

    @Id(autoincrement = true)
    public Long f_id;

    public int id;

    public String created_at;

    @Convert(converter = CatFavoriteConvert.class, columnType = String.class)
    public CatFavoriteImage image;

    @Generated(hash = 1666963507)
    public CatFavoritesModel(Long f_id, int id, String created_at, CatFavoriteImage image) {
        this.f_id = f_id;
        this.id = id;
        this.created_at = created_at;
        this.image = image;
    }

    @Generated(hash = 2032595685)
    public CatFavoritesModel() {
    }

    public Long getF_id() {
        return this.f_id;
    }

    public void setF_id(Long f_id) {
        this.f_id = f_id;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public CatFavoriteImage getImage() {
        return this.image;
    }

    public void setImage(CatFavoriteImage image) {
        this.image = image;
    }


    public static class CatFavoriteImage {
        public String id;
        public String url;
    }

    public static class CatFavoriteConvert implements PropertyConverter<CatFavoriteImage, String> {

        private static Gson mGson = new Gson();

        @Override
        public CatFavoriteImage convertToEntityProperty(String databaseValue) {
            CatFavoriteImage catFavoriteImage = mGson.fromJson(databaseValue, CatFavoriteImage.class);

            return catFavoriteImage;
        }

        @Override
        public String convertToDatabaseValue(CatFavoriteImage entityProperty) {
            return mGson.toJson(entityProperty);
        }
    }

}
