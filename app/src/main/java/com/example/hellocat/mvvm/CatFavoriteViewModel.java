package com.example.hellocat.mvvm;

import com.example.hellocat.adapter.CatFavoritesAdapter;
import com.example.hellocat.model.CatFavoritesModel;
import com.example.hellocat.network.NetworkDataManager;

import java.util.List;

public class CatFavoriteViewModel implements IBaseListener {
    IBaseView baseView;
    IBaseModel catModel;
    CatFavoritesAdapter adapter;

    public CatFavoriteViewModel(IBaseView baseView, CatFavoritesAdapter adapter) {
        this.baseView = baseView;
        this.adapter = adapter;
        catModel = new CatFavoriteModeImpl();
        loadCatsData();
    }

    private void loadCatsData() {
        catModel.loadCatFavorites(this);
    }
    @Override
    public void loadSuccess(List list) {
        NetworkDataManager.saveFavorite(list);
        baseView.loadComplete();
        adapter.setCatFavoritesModels(list);
    }

    @Override
    public void loadFailure() {
        List<CatFavoritesModel> catList =NetworkDataManager.getFavoriteModels();
        if (catList == null || catList.size() == 0) {
            baseView.loadFailure();
        } else {
            loadSuccess(catList);
        }

    }

    @Override
    public void loadStart() {
        baseView.loadStart();
    }

    @Override
    public void loadComplete() {
        baseView.loadComplete();
    }
}
