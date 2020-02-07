/*
 * Copyright (C) 10-May-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.rapido.rapidotest.mvp.view.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.rapido.rapidotest.mvp.view.fragments.VideoListFragment;

public class VideoListActivity extends BaseActivity {


    @Override
    protected Fragment viewFragment() {
        Bundle bundle = new Bundle();
        return buildFragment(bundle, VideoListFragment.class);
    }
}
