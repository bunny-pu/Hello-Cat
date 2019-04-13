package com.example.hellocat.mvp;

import com.example.hellocat.model.CatBreedModel;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * @author gegeding
 */
public class CatBreedPresenter {
    private ICatBreedBiz catBreedBiz;
    private ICatBreedView catBreedView;

    public CatBreedPresenter(ICatBreedView catBreedView) {
        this.catBreedView = catBreedView;
        catBreedBiz = new CatBreedBiz();
    }

    public Disposable searchForBreeds() {
        List<CatBreedModel> catBreedModels = catBreedBiz.getCatBreeds();
        if (catBreedModels != null && catBreedModels.size() != 0) {
            catBreedView.setCatBreedList(catBreedModels);
            return null;
        }
        catBreedView.showLoading();
        return catBreedBiz.searchForBreed(new ICatBreedBiz.DataFeedbackListener() {
            @Override
            public void onBreedDataSuccess(List<CatBreedModel> catBreedModels) {
                catBreedView.hideLoading();
                catBreedView.setCatBreedList(catBreedModels);
                catBreedBiz.saveCatBreeds(catBreedModels);
            }

            @Override
            public void onBreedDataFail() {
                catBreedView.hideLoading();
                catBreedView.dataFail();
            }
        });
    }
}
