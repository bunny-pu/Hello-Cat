package com.example.hellocat.mvvm;

import com.example.hellocat.model.CatFavoritesModel;

public interface IBaseModel {
    void loadCatFavorites(IBaseListener<CatFavoritesModel> catBaseListener);
}
