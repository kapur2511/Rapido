/*
 * Copyright (C) 11-May-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.rapido.rapidotest.mvp.model;

import com.google.gson.annotations.SerializedName;

public class ThumbnailListModel {

    private ThumbnailModel standard;

    @SerializedName("default")
    private ThumbnailModel defaultThumb;

    private ThumbnailModel high;

    private ThumbnailModel maxres;

    private ThumbnailModel medium;


    public ThumbnailModel getStandard() {
        return standard;
    }

    public void setStandard(ThumbnailModel standard) {
        this.standard = standard;
    }

    public ThumbnailModel getDefaultThumb() {
        return defaultThumb;
    }

    public void setDefaultThumb(ThumbnailModel defaultThumb) {
        this.defaultThumb = defaultThumb;
    }

    public ThumbnailModel getHigh() {
        return high;
    }

    public void setHigh(ThumbnailModel high) {
        this.high = high;
    }

    public ThumbnailModel getMaxres() {
        return maxres;
    }

    public void setMaxres(ThumbnailModel maxres) {
        this.maxres = maxres;
    }

    public ThumbnailModel getMedium() {
        return medium;
    }

    public void setMedium(ThumbnailModel medium) {
        this.medium = medium;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [standard = "+standard+", default = "+defaultThumb+", high = "+high+", maxres = "+maxres+", medium = "+medium+"]";
    }
}
