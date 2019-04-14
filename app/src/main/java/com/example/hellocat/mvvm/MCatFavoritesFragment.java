package com.example.hellocat.mvvm;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hellocat.R;
import com.example.hellocat.adapter.CatFavoritesAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MCatFavoritesFragment extends Fragment implements IBaseView {

    @BindView(R.id.swiperef_favorite)
    SwipeRefreshLayout swiperefFavorite;

    @BindView(R.id.rv_favorites)
    RecyclerView rvFavorites;

    private CatFavoritesAdapter catFavoritesAdapter;

    private CatFavoriteViewModel catFavoriteViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cat_favorites, container, false);
        ButterKnife.bind(this, view);
        catFavoritesAdapter = new CatFavoritesAdapter();
        rvFavorites.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        rvFavorites.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL));
        rvFavorites.setAdapter(catFavoritesAdapter);
        swiperefFavorite.setEnabled(false);
        catFavoriteViewModel = new CatFavoriteViewModel(this, catFavoritesAdapter);
        return view;
    }


    @Override
    public void loadStart() {
        swiperefFavorite.setRefreshing(true);
    }

    @Override
    public void loadComplete() {
        swiperefFavorite.setRefreshing(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void loadFailure() {
        Toast.makeText(getContext().getApplicationContext(), R.string.favorites_loading_fail, Toast.LENGTH_SHORT).show();
    }
}
