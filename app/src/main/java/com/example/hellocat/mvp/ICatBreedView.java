package com.example.hellocat.mvp;

import com.example.hellocat.model.CatBreedModel;

import java.util.List;

public interface ICatBreedView {
    void dataFail();
    void setCatBreedList(List<CatBreedModel> catBreedList);
    void showLoading();
    void hideLoading();
}
