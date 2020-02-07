/*
 * Copyright (C) 10-May-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

/*
 * @author: kshitiz.kapur
 */

package com.rapido.rapidotest.di.modules;

import com.rapido.rapidotest.mvp.view.fragments.VideoListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class VideoListActivityModule {

    @ContributesAndroidInjector()
    abstract VideoListFragment videoListFragment();
}
