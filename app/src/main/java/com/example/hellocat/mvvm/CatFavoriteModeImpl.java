package com.example.hellocat.mvvm;

import com.example.hellocat.model.CatFavoritesModel;
import com.example.hellocat.network.CatFavoritesApi;
import com.example.hellocat.network.NetworkDataManager;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CatFavoriteModeImpl implements IBaseModel {
    @Override
    public void loadCatFavorites(IBaseListener<CatFavoritesModel> catBaseListener) {
        NetworkDataManager.getRetrofit()
                .create(CatFavoritesApi.class)
                .getFavorites()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CatFavoritesModel>>() {
                    @Override
                    public void accept(List<CatFavoritesModel> catFavoritesModels) {
                        catBaseListener.loadSuccess(catFavoritesModels);
                        catBaseListener.loadComplete();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                       catBaseListener.loadFailure();
                       catBaseListener.loadComplete();

                    }
                });
    }
}
