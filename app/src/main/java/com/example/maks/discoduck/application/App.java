package com.example.maks.discoduck.application;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;

import io.fabric.sdk.android.Fabric;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initFabric();
    }

    private void initFabric() {
        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Answers(), new Crashlytics())
                .debuggable(true)
                .build();
        Fabric.with(fabric);
    }
}
