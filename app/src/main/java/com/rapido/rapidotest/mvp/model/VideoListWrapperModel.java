/*
 * Copyright (C) 11-May-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.rapido.rapidotest.mvp.model;

import java.util.List;

public class VideoListWrapperModel {

    private String kind;

    private String nextPageToken;

    private PageInfoModel pageInfo;

    private String etag;

    private List<VideoModel> items;

    public String getKind ()
    {
        return kind;
    }

    public void setKind (String kind)
    {
        this.kind = kind;
    }

    public String getNextPageToken ()
    {
        return nextPageToken;
    }

    public void setNextPageToken (String nextPageToken)
    {
        this.nextPageToken = nextPageToken;
    }

    public PageInfoModel getPageInfo ()
    {
        return pageInfo;
    }

    public void setPageInfo (PageInfoModel pageInfo)
    {
        this.pageInfo = pageInfo;
    }

    public String getEtag ()
    {
        return etag;
    }

    public void setEtag (String etag)
    {
        this.etag = etag;
    }

    public List<VideoModel> getItems ()
    {
        return items;
    }

    public void setItems (List<VideoModel> items)
    {
        this.items = items;
    }

    @Override
    public String toString()
    {
        return "VideoListWrapper [kind = "+kind+", nextPageToken = "+nextPageToken+", pageInfo = "+pageInfo+", etag = "+etag+", items = "+items+"]";
    }
}
