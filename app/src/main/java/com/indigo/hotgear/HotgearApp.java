package com.indigo.hotgear;

import android.app.Application;
import android.content.Context;
import android.util.JsonReader;

import com.google.gson.JsonObject;
import com.indigo.hotgear.network.API;

public class HotgearApp extends Application {
    private  static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        HotgearApp.context = getApplicationContext();
        API.getInstance().getRequestQueue();
    }
}
