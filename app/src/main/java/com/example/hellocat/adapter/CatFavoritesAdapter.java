package com.example.hellocat.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hellocat.R;
import com.example.hellocat.model.CatFavoritesModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author gegeding
 */
public class CatFavoritesAdapter extends RecyclerView.Adapter  {

    final List<CatFavoritesModel> catFavoritesModels = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext().getApplicationContext()).inflate(R.layout.item_cat_favorite, viewGroup, false);
        return new CatFavoriteViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        CatFavoriteViewHolder catFavoriteViewholder = (CatFavoriteViewHolder) viewHolder;
        CatFavoritesModel catFavoritesModel = catFavoritesModels.get(position);
        CatFavoritesModel.CatFavoriteImage theImage = catFavoritesModel.image;
        Glide.with(viewHolder.itemView.getContext().getApplicationContext()).load(theImage.url).into(catFavoriteViewholder.ivFavorite);
        catFavoriteViewholder.tvFavoriteId.setText("Id : " + catFavoritesModel.id);
        catFavoriteViewholder.tvFavoriteCreateAt.setText("created at " + catFavoritesModel.created_at);
    }

    @Override
    public int getItemCount() {
        return catFavoritesModels == null ? 0 : catFavoritesModels.size();
    }

    public void setCatFavoritesModels(List<CatFavoritesModel> catFavoritesModels) {
        this.catFavoritesModels.addAll(catFavoritesModels);
        notifyDataSetChanged();
    }

    class CatFavoriteViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_favorite)
        ImageView ivFavorite;

        @BindView(R.id.tv_favorite_id)
        TextView tvFavoriteId;

        @BindView(R.id.tv_favorite_create_at)
        TextView tvFavoriteCreateAt;

        public CatFavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
