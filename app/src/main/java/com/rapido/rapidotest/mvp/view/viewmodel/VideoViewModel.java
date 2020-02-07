/*
 * Copyright (C) 10-May-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.rapido.rapidotest.mvp.view.viewmodel;

import com.rapido.rapidotest.mvp.model.VideoModel;

public class VideoViewModel implements ListItem{

    public VideoViewModel(VideoModel videoModel){
        if(videoModel.getSnippet().getThumbnails().getStandard()!=null)
            this.thumbnailURL = videoModel.getSnippet().getThumbnails().getStandard().getUrl();
        else
            this.thumbnailURL = videoModel.getSnippet().getThumbnails().getDefaultThumb().getUrl();
        this.videoTitle   = videoModel.getSnippet().getTitle();
        this.channelName  = videoModel.getSnippet().getChannelTitle();
        this.postedTime   = videoModel.getSnippet().getPublishedAt();
        this.views        = videoModel.getStatModel().getViewCount() + " views";
        this.videoId      = videoModel.getId();
        this.videoDescription = videoModel.getSnippet().getDescription();
    }

    private String thumbnailURL, videoTitle, channelName, views, postedTime, duration, videoId, videoDescription;

    public String getVideoDescription() {
        return videoDescription;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getDuration() {
        return duration;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getViews() {
        return views;
    }

    public String getPostedTime() {
        return postedTime;
    }


    @Override
    public String toString() {
        return "VideoViewModel{" +
//                "thumbnailURL='" + thumbnailURL + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
               /* ", channelName='" + channelName + '\'' +
                ", views='" + views + '\'' +
                ", postedTime='" + postedTime + '\'' +
                ", duration='" + duration + '\'' +*/
                '}';
    }
}
