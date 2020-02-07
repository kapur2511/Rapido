/*
 * Copyright (C) 10-May-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.rapido.rapidotest;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;

import com.rapido.rapidotest.di.component.ApplicationComponent;
import com.rapido.rapidotest.di.component.DaggerApplicationComponent;

import java.io.File;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class RapidoApplication extends MultiDexApplication implements HasActivityInjector {


    @Override
    public void onCreate() {
        super.onCreate();
        File cacheFile = new File(getCacheDir(), "responses");
        ApplicationComponent applicationComponent = DaggerApplicationComponent.builder()
                .cache(cacheFile).application(this)
                .build();
        applicationComponent.inject(this);
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityInjector;
    }

    @Inject
    DispatchingAndroidInjector<Activity> activityInjector;
}
