package com.example.hellocat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hellocat.adapter.CatBreedAdapter;
import com.example.hellocat.model.CatBreedModel;
import com.example.hellocat.network.CatBreedApi;
import com.example.hellocat.network.NetworkDataManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CatBreedFragment extends BaseFragment {
    @BindView(R.id.swipeRefreshLayout_breed)
    SwipeRefreshLayout swipeRefreshLayoutBreed;

    @BindView(R.id.rec_linear_list)
    RecyclerView linearRecyclerView;

    private CatBreedAdapter catBreedAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cat_breed, container, false);
        ButterKnife.bind(this, view);
        linearRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        linearRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL));
        catBreedAdapter = new CatBreedAdapter(getActivity().getApplicationContext());
        linearRecyclerView.setAdapter(catBreedAdapter);
        swipeRefreshLayoutBreed.setEnabled(false);
        return view;
    }

    @Override
    public void fetchData() {
        swipeRefreshLayoutBreed.setRefreshing(true);
        searchForBreeds();
    }

    void searchForBreeds() {
        disposable = NetworkDataManager.getRetrofit().create(CatBreedApi.class)
                .getCatBreed()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CatBreedModel>>() {
                    @Override
                    public void accept(List<CatBreedModel> catBreedModels) throws Exception {
                        swipeRefreshLayoutBreed.setRefreshing(false);
                        catBreedAdapter.setList(catBreedModels);
                        NetworkDataManager.saveCatBreed(catBreedModels);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        swipeRefreshLayoutBreed.setRefreshing(false);
                        List<CatBreedModel> catBreedModels = NetworkDataManager.getBreedModels();
                        if (catBreedModels == null || catBreedModels.size() == 0) {
                            Toast.makeText(getActivity().getApplicationContext(), R.string.loading_fail, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        catBreedAdapter.setList(catBreedModels);
                    }
                });
    }

    @Override
    protected int geTitleRes() {
        return R.string.title_breed;
    }
}
