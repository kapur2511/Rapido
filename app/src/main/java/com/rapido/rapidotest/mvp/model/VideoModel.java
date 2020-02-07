/*
 * Copyright (C) 11-May-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.rapido.rapidotest.mvp.model;

import com.google.gson.annotations.SerializedName;

public class VideoModel {

    private SnippetModel snippet;

    private String kind;

    private String etag;

    private String id;

    @SerializedName("statistics")
    private StatModel statModel;

    public StatModel getStatModel() {
        return statModel;
    }

    public void setStatModel(StatModel statModel) {
        this.statModel = statModel;
    }

    public SnippetModel getSnippet ()
    {
        return snippet;
    }

    public void setSnippet (SnippetModel snippet)
    {
        this.snippet = snippet;
    }

    public String getKind ()
    {
        return kind;
    }

    public void setKind (String kind)
    {
        this.kind = kind;
    }

    public String getEtag ()
    {
        return etag;
    }

    public void setEtag (String etag)
    {
        this.etag = etag;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "VideoModel [snippet = "+snippet+", kind = "+kind+", etag = "+etag+", id = "+id+"]"+"stats: "+statModel;
    }
}

