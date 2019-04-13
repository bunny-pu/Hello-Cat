package com.example.hellocat.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hellocat.CatWikipediaActivity;
import com.example.hellocat.R;
import com.example.hellocat.model.CatBreedModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author gegeding
 */
public class CatBreedAdapter extends RecyclerView.Adapter {

    List<CatBreedModel> catBreedModels;
    Context context;
    View.OnClickListener onClickListener;

    public CatBreedAdapter(Context context) {
        this.context = context;
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWiki(v);
            }
        };
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = null;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cat_breed, viewGroup, false);
        return new CatBreedVioewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        CatBreedVioewHolder catBreedViewHolder = (CatBreedVioewHolder) viewHolder;
        CatBreedModel catBreedModel = catBreedModels.get(i);
        catBreedViewHolder.tvBreedItemName.setText("Name : " + catBreedModel.name);
        catBreedViewHolder.tvLifeSpan.setText("LifeSpan : " + catBreedModel.life_span);
        catBreedViewHolder.tvTemperament.setText("Temperament : " + catBreedModel.temperament);
        catBreedViewHolder.tvDescribe.setText("Description : " + catBreedModel.description);
        String wikiUrl = catBreedModel.wikipedia_url;
        catBreedViewHolder.tvGoWiki.setTag(wikiUrl);
        catBreedViewHolder.tvGoWiki.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return catBreedModels == null ? 0 : catBreedModels.size();
    }

    public void setList(List<CatBreedModel> catBreedModels) {
        this.catBreedModels = catBreedModels;
        notifyDataSetChanged();
    }

    private void goToWiki(View v) {
        Intent intent = new Intent(context, CatWikipediaActivity.class);
        intent.putExtra("cat_wiki", (String) v.getTag());
        context.startActivity(intent);
    }

    static class CatBreedVioewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_breed)
        TextView tvBreedItemName;

        @BindView(R.id.tv_life_span)
        TextView tvLifeSpan;

        @BindView(R.id.tv_temperament)
        TextView tvTemperament;

        @BindView(R.id.tv_describe)
        TextView tvDescribe;

        @BindView(R.id.tv_go_wiki)
        TextView tvGoWiki;


        public CatBreedVioewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
