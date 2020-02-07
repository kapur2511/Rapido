/*
 * Copyright (C) 11-May-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.rapido.rapidotest.mvp.model;

public class StatModel {

    private String dislikeCount;

    private String likeCount;

    private String viewCount;

    private String favoriteCount;

    private String commentCount;

    public String getDislikeCount ()
    {
        return dislikeCount;
    }

    public void setDislikeCount (String dislikeCount)
    {
        this.dislikeCount = dislikeCount;
    }

    public String getLikeCount ()
    {
        return likeCount;
    }

    public void setLikeCount (String likeCount)
    {
        this.likeCount = likeCount;
    }

    public String getViewCount ()
    {
        return viewCount;
    }

    public void setViewCount (String viewCount)
    {
        this.viewCount = viewCount;
    }

    public String getFavoriteCount ()
    {
        return favoriteCount;
    }

    public void setFavoriteCount (String favoriteCount)
    {
        this.favoriteCount = favoriteCount;
    }

    public String getCommentCount ()
    {
        return commentCount;
    }

    public void setCommentCount (String commentCount)
    {
        this.commentCount = commentCount;
    }

    @Override
    public String toString()
    {
        return "StatPojo [dislikeCount = "+dislikeCount+", likeCount = "+likeCount+", viewCount = "+viewCount+", favoriteCount = "+favoriteCount+", commentCount = "+commentCount+"]";
    }
}
