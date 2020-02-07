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

package com.rapido.rapidotest.mvp.presenter;

import java.util.Map;

public abstract class BasePresenter<V> {

    public void init(final V view){
        this.view = view;
    }

    public abstract void loadData(Map<String, Object> stringObjectHashMap);

    public V view;

    protected final String TAG = this.getClass().getSimpleName();
}
