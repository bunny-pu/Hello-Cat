package com.example.hellocat;

import android.app.Application;
import com.example.hellocat.network.NetworkDataManager;

public class CatApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new NetworkDataManager(this);
    }
}