package com.example.hellocat.mvp;

import com.example.hellocat.model.CatBreedModel;

import java.util.List;

import io.reactivex.disposables.Disposable;

public interface ICatBreedBiz {
    public Disposable searchForBreed(DataFeedbackListener dataFeedbackListener);
    public void saveCatBreeds(List<CatBreedModel> catBreedBizs);
    public List<CatBreedModel> getCatBreeds();

    interface DataFeedbackListener {
        void onBreedDataSuccess(List<CatBreedModel> catBreedModels);
        void onBreedDataFail();
    }
}
