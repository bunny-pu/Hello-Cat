package com.example.hellocat.network;

import com.example.hellocat.model.CatCertainCategoryModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CatCertainCategoryApi {
    @Headers("x-api-key:f219d9a3-0d02-4aed-8015-608075c7fa5a")
    @GET("images/search")
    Observable<List<CatCertainCategoryModel>> searchCertainCategory(@Query("category_ids") int id, @Query("limit") int limit);
}
