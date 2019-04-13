package com.example.hellocat.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hellocat.R;
import com.example.hellocat.model.CatCertainCategoryModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author gegeding
 */
public class CatCategoriesAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext().getApplicationContext()).inflate(R.layout.item_cat_categories, viewGroup, false);

        return new CategoriesViewHolder(view);
    }

    List<CatCertainCategoryModel> catCertainCategoryModels;
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        CategoriesViewHolder categoriesViewHolder = (CategoriesViewHolder) viewHolder;
        Glide.with(viewHolder.itemView.getContext().getApplicationContext())
                .load(catCertainCategoryModels.get(position).url)
                .into(categoriesViewHolder.ivCat);
    }

    @Override
    public int getItemCount() {
        return catCertainCategoryModels == null ? 0 : catCertainCategoryModels.size();
    }


    public void setImages(List<CatCertainCategoryModel> catCertainCategoryModels) {
        this.catCertainCategoryModels = catCertainCategoryModels;
        notifyDataSetChanged();
    }

    class CategoriesViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_cat)
        ImageView ivCat;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
