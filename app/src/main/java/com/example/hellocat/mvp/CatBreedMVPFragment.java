package com.example.hellocat.mvp;

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

import com.example.hellocat.BaseFragment;
import com.example.hellocat.R;
import com.example.hellocat.adapter.CatBreedAdapter;
import com.example.hellocat.model.CatBreedModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author gegeding
 */
public class CatBreedMVPFragment extends BaseFragment implements ICatBreedView {

    @BindView(R.id.swipeRefreshLayout_breed)
    SwipeRefreshLayout swipeRefreshLayoutBreed;

    @BindView(R.id.rec_linear_list)
    RecyclerView linearRecyclerView;

    private CatBreedPresenter catBreedPresenter;

    private CatBreedAdapter catBreedAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cat_breed, container, false);
        ButterKnife.bind(this, view);
        catBreedPresenter = new CatBreedPresenter(this);
        linearRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        linearRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL));
        catBreedAdapter = new CatBreedAdapter(getActivity().getApplicationContext());
        linearRecyclerView.setAdapter(catBreedAdapter);
        swipeRefreshLayoutBreed.setEnabled(false);
        return view;
    }

    @Override
    public void fetchData() {
        disposable = catBreedPresenter.searchForBreeds();
    }

    @Override
    protected int geTitleRes() {
        return R.string.title_breed;
    }

    @Override
    public void dataFail() {
        Toast.makeText(getActivity().getApplicationContext(), R.string.loading_fail, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setCatBreedList(List<CatBreedModel> catBreedList) {
        catBreedAdapter.setList(catBreedList);
    }

    @Override
    public void showLoading() {
        swipeRefreshLayoutBreed.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayoutBreed.setRefreshing(false);
    }
}
