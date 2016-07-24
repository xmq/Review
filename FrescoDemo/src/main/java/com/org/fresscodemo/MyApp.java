package com.org.fresscodemo;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Administrator on 2016/4/5.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        Fresco.shutDown();
    }
}
