package com.example.hellocat.network;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.anye.greendao.gen.DaoMaster;
import com.anye.greendao.gen.DaoSession;
import com.example.hellocat.model.CatBreedModel;
import com.example.hellocat.model.CatCertainCategoryModel;
import com.example.hellocat.model.CatFavoritesModel;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkDataManager {
    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();

    private Context context;

    public NetworkDataManager(Context context) {
        this.context = context;
        initGreenDao();
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("https://api.thecatapi.com/v1/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
        }
        return retrofit;
    }

    private static DaoSession daoSession;

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "cat.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public static void saveCatBreed(List<CatBreedModel> catBreedModelList) {
        daoSession.getCatBreedModelDao().deleteAll();
        daoSession.getCatBreedModelDao().saveInTx(catBreedModelList);
    }

    public static List<CatBreedModel> getBreedModels() {
        return daoSession.getCatBreedModelDao().loadAll();
    }

    public static void saveCatCertaincategories(List<CatCertainCategoryModel> catCertainCategoryModels) {
        daoSession.getCatCertainCategoryModelDao().deleteAll();
        daoSession.getCatCertainCategoryModelDao().saveInTx(catCertainCategoryModels);
    }

    public static List<CatCertainCategoryModel> getCatCertainCategory() {
        return daoSession.getCatCertainCategoryModelDao().loadAll();
    }

    public static void saveFavorite(List<CatFavoritesModel> catFavoritesModels) {
        daoSession.getCatFavoritesModelDao().deleteAll();
        daoSession.getCatFavoritesModelDao().saveInTx(catFavoritesModels);
    }

    public static List<CatFavoritesModel> getFavoriteModels() {
        return daoSession.getCatFavoritesModelDao().loadAll();

    }
}
