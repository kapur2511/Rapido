/*
 * Copyright (C) 14-May-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.rapido.rapidotest.utils;

import android.util.Log;
import android.view.View;

public class PIPManager {

    private View view;

    public PIPManager(View view){
        this.view = view;
    }

    public void collapse() {
        int requiredHeight = 0;
        int requiredWidth  = 0;

        final int initialHeight = view.getMeasuredHeight();
        final int initialWidth  = view.getMeasuredWidth();

        //720p phone
        if(initialWidth == 720){
            requiredHeight = 230;
            requiredWidth  = 410;
        }else if(initialWidth == 1080){
            if(initialHeight>=700)
                requiredHeight = 330;
            else if(initialHeight>=650)
                requiredHeight = 300;
            else
                requiredHeight = 290;
            requiredWidth  = 610;
        }

        final int targetHeight = initialHeight - (initialHeight - requiredHeight);
        final int targetWidth  = initialWidth  - (initialWidth  - requiredWidth);

        view.getLayoutParams().height = targetHeight;
        view.getLayoutParams().width = targetWidth;
        view.requestLayout();

        Log.d("INITIAL HEIGHT: ",initialHeight+"");
        Log.d("INITIAL WIDTH: ",initialWidth+"");

        Log.d("TARGET HEIGHT: ",targetHeight+"");
        Log.d("TARGET WIDTH: ",targetWidth+"");
    }

    public void expand(int requiredWidth) {
        int requiredHeight = 0;

        //720p phone
        if(requiredWidth == 720){
            requiredHeight = 500;
        }else if(requiredWidth == 1080){
            requiredHeight = 700;
        }

        view.getLayoutParams().height = requiredHeight;
        view.getLayoutParams().width = requiredWidth;
        view.requestLayout();

        Log.d("TARGET HEIGHT: ",requiredHeight+"");
        Log.d("TARGET WIDTH: ",requiredWidth+"");
    }
}
