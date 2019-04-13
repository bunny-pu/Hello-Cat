package com.example.hellocat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hellocat.adapter.CatCategoriesAdapter;
import com.example.hellocat.model.CatCategoriesModel;
import com.example.hellocat.model.CatCertainCategoryModel;
import com.example.hellocat.network.CatCategoriesApi;
import com.example.hellocat.network.CatCertainCategoryApi;
import com.example.hellocat.network.NetworkDataManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CatCategoriesFragment extends BaseFragment {

    @BindView(R.id.swipeRefreshLayout_categories)
    SwipeRefreshLayout categoriesSwipeRefreshLayout;

    @BindView(R.id.gridRv)
    RecyclerView gridRv;

    @BindView(R.id.radio_1)
    RadioGroup radioGroup1;

    @BindView(R.id.radio_2)
    RadioGroup radioGroup2;

    CatCategoriesAdapter catCategoriesAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmet_cat_categories, container, false);
        ButterKnife.bind(this, view);
        gridRv.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
        catCategoriesAdapter = new CatCategoriesAdapter();
        gridRv.setAdapter(catCategoriesAdapter);
        categoriesSwipeRefreshLayout.setEnabled(false);
        return view;
    }

    @Override
    public void fetchData() {
        searchForCategories();
    }

    @Override
    protected int geTitleRes() {
        return R.string.title_category;
    }

    @OnCheckedChanged({R.id.searchRb1, R.id.searchRb2, R.id.searchRb3, R.id.searchRb4})
    void onTagChecked(RadioButton radioButton, boolean checked) {
        if (checked) {
            radioGroup2.clearCheck();
            categoriesSwipeRefreshLayout.setRefreshing(true);
            switch (radioButton.getId()) {
                case R.id.searchRb1:
                    searchForCertainCategory(0);
                    break;
                case R.id.searchRb2:
                    searchForCertainCategory(1);
                    break;
                case R.id.searchRb3:
                    searchForCertainCategory(2);
                    break;
                case R.id.searchRb4:
                    searchForCertainCategory(3);
                    break;
                default:
                    searchForCertainCategory(0);
                    break;


            }
        }
    }

    @OnCheckedChanged({R.id.searchRb5, R.id.searchRb6, R.id.searchRb7})
    void onTagChecked2(RadioButton radioButton, boolean checked) {
        if (checked) {
            radioGroup1.clearCheck();
            categoriesSwipeRefreshLayout.setRefreshing(true);
            switch (radioButton.getId()) {
                case R.id.searchRb5:
                    searchForCertainCategory(4);
                    break;
                case R.id.searchRb6:
                    searchForCertainCategory(5);
                    break;
                case R.id.searchRb7:
                    searchForCertainCategory(6);
                    break;
                default:
                    searchForCertainCategory(4);
                    break;


            }
        }
    }

    List<CatCategoriesModel> catCategoriesModels;

    void searchForCategories() {
        disposable = NetworkDataManager.getRetrofit()
                .create(CatCategoriesApi.class)
                .getCatCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CatCategoriesModel>>() {
                    @Override
                    public void accept(List<CatCategoriesModel> catCategoriesModels) throws Exception {
                        CatCategoriesFragment.this.catCategoriesModels = catCategoriesModels;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getActivity().getApplicationContext(), R.string.categories_loading_fail, Toast.LENGTH_SHORT).show();
                    }
                });

    }

    void searchForCertainCategory(int position) {
        if (catCategoriesModels == null || catCategoriesModels.size() == 0) {
            Toast.makeText(getActivity().getApplicationContext(), R.string.categories_loading_fail, Toast.LENGTH_SHORT).show();
            categoriesSwipeRefreshLayout.setRefreshing(false);
            return;
        }
        int categoryId = catCategoriesModels.get(position).id;
        disposable = NetworkDataManager.getRetrofit()
                .create(CatCertainCategoryApi.class)
                .searchCertainCategory(categoryId, 100)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CatCertainCategoryModel>>() {
                    @Override
                    public void accept(List<CatCertainCategoryModel> catCertainCategoryModels) throws Exception {
                        catCategoriesAdapter.setImages(catCertainCategoryModels);
                        categoriesSwipeRefreshLayout.setRefreshing(false);
                        NetworkDataManager.saveCatCertaincategories(catCertainCategoryModels);
                        gridRv.scrollToPosition(0);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        categoriesSwipeRefreshLayout.setRefreshing(false);
                        List<CatCertainCategoryModel> catCertainCategoryModels = NetworkDataManager.getCatCertainCategory();
                        if (catCertainCategoryModels == null || catCertainCategoryModels.size() == 0) {
                            Toast.makeText(getActivity().getApplicationContext(), R.string.category_loading_fail, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        catCategoriesAdapter.setImages(catCertainCategoryModels);
                    }
                });

    }
}
