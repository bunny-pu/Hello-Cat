package com.example.hellocat.network;

import com.example.hellocat.model.CatBreedModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface CatBreedApi {
    @Headers("x-api-key:f219d9a3-0d02-4aed-8015-608075c7fa5a")
    @GET("breeds")
    Observable<List<CatBreedModel>> getCatBreed();
}
