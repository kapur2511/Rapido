/*
 * Copyright (C) 13-May-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.rapido.rapidotest.utils;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;

public class ResizeAnimation extends Animation {

    private final int targetHeight, targetWidth;
    private View view;
    private int startHeight, startWidth;
    private final boolean smallSizeAnimation;

    public ResizeAnimation(View view, int targetHeight, int startHeight, int targetWidth, int startWidth, boolean smallSizeAnimation) {
        this.view = view;
        this.targetHeight = targetHeight;
        this.startHeight = startHeight;
        this.targetWidth = targetWidth;
        this.startWidth  = startWidth;
        this.smallSizeAnimation = smallSizeAnimation;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        int newHeight = 0, newWidth = 0;
        if(interpolatedTime == 0.0){
            newHeight = (int) (startHeight+(targetHeight - startHeight) * interpolatedTime);
            newWidth =  (int) (startWidth + (targetWidth - startWidth) * interpolatedTime);
        }else if(interpolatedTime == 1.0){
            newHeight = (int) (startHeight+(targetHeight - startHeight) * interpolatedTime);
            newWidth =  (int) (startWidth + (targetWidth - startWidth) * interpolatedTime);
        }
//            int newHeight = (int) (startHeight + targetHeight * interpolatedTime);
//            int newWidth = (int) (startWidth + targetWidth * interpolatedTime);

            //to support decent animation, change new heigt as Nico S. recommended in comments

        view.getLayoutParams().height = newHeight;
        view.getLayoutParams().width  = newWidth;
        view.requestLayout();
        if(smallSizeAnimation){
            Animation animation = new TranslateAnimation(0, -40,0, 0);
            animation.setDuration(100);
            animation.setFillAfter(true);
            view.startAnimation(animation);
        }
        Log.d("RESIZE: ","NEW WIDTH: "+newWidth);
        Log.d("RESIZE: ","NEW HEIGHT: "+newHeight);

        Log.d("RESIZE: ","START HEIGHT: "+startHeight);
        Log.d("RESIZE: ","TARGET HEIGHT: "+targetHeight);
        Log.d("RESIZE: ","START WIDTH: "+startWidth);
        Log.d("RESIZE: ","TARGET WIDTH: "+targetWidth);
        Log.d("RESIZE: ","INTERPOLATED TIME: "+interpolatedTime);


    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }

    private boolean alreadyAnimated = false;
}
