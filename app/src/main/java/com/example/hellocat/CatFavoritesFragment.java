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

import com.example.hellocat.adapter.CatFavoritesAdapter;
import com.example.hellocat.model.CatFavoritesModel;
import com.example.hellocat.network.CatFavoritesApi;
import com.example.hellocat.network.NetworkDataManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CatFavoritesFragment extends BaseFragment {

    @BindView(R.id.swiperef_favorite)
    SwipeRefreshLayout swipeReFavorite;

    @BindView(R.id.rv_favorites)
    RecyclerView rvFavorites;

    CatFavoritesAdapter catFavoritesAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cat_favorites, container, false);
        ButterKnife.bind(this, view);
        catFavoritesAdapter = new CatFavoritesAdapter();
        rvFavorites.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        rvFavorites.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL));
        rvFavorites.setAdapter(catFavoritesAdapter);
        swipeReFavorite.setEnabled(false);
        return view;
    }

    @Override
    public void fetchData() {
        getFavorites();
    }

    @Override
    protected int geTitleRes() {
        return R.string.titke_favorities;
    }

    private void getFavorites() {
        List<CatFavoritesModel> catFavoritesModels = NetworkDataManager.getFavoriteModels();
        if (catFavoritesModels != null && catFavoritesModels.size() != 0) {
            catFavoritesAdapter.setCatFavoritesModels(catFavoritesModels);
            return;
        }
        swipeReFavorite.setRefreshing(true);
        disposable = NetworkDataManager.getRetrofit()
                .create(CatFavoritesApi.class)
                .getFavorites()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CatFavoritesModel>>() {
                    @Override
                    public void accept(List<CatFavoritesModel> catFavoritesModels) {
                        swipeReFavorite.setRefreshing(false);
                        NetworkDataManager.saveFavorite(catFavoritesModels);
                        catFavoritesAdapter.setCatFavoritesModels(catFavoritesModels);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        swipeReFavorite.setRefreshing(false);
                        List<CatFavoritesModel> catFavoritesModels = NetworkDataManager.getFavoriteModels();
                        if (catFavoritesModels == null || catFavoritesModels.size() == 0) {
                            Toast.makeText(getActivity().getApplicationContext(), R.string.favorites_loading_fail, Toast.LENGTH_SHORT).show();
                            return;
                        }
                        catFavoritesAdapter.setCatFavoritesModels(catFavoritesModels);

                    }
                });
    }
}
