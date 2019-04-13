package com.example.hellocat.mvp;

import com.example.hellocat.model.CatBreedModel;
import com.example.hellocat.network.CatBreedApi;
import com.example.hellocat.network.NetworkDataManager;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author gegeding
 */
public class CatBreedBiz implements ICatBreedBiz {
    @Override
    public Disposable searchForBreed(DataFeedbackListener dataFeedbackListener) {
        return NetworkDataManager.getRetrofit().create(CatBreedApi.class)
                .getCatBreed()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CatBreedModel>>() {
                    @Override
                    public void accept(List<CatBreedModel> catBreedModels) throws Exception {
                        dataFeedbackListener.onBreedDataSuccess(catBreedModels);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        dataFeedbackListener.onBreedDataFail();
                    }
                });
    }

    @Override
    public void saveCatBreeds(List<CatBreedModel> catBreedModels) {
        NetworkDataManager.saveCatBreed(catBreedModels);
    }

    @Override
    public List<CatBreedModel> getCatBreeds() {
        return NetworkDataManager.getBreedModels();
    }
}
