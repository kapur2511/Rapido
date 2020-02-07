/*
 * Copyright (C) 11-May-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.rapido.rapidotest.mvp.view.viewmodel;

import java.util.List;

public class VideoListWrapperViewModel {

    private List<VideoViewModel> videoViewModels;
    private int totalPages;
    private String nextPageToken;

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public void setVideoViewModels(List<VideoViewModel> videoViewModels) {
        this.videoViewModels = videoViewModels;
    }

    public List<VideoViewModel> getVideoViewModels() {
        return videoViewModels;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    @Override
    public String toString() {
        return "VideoListWrapperViewModel{" +
                "videoViewModels=" + videoViewModels +
                ", nextPageToken='" + nextPageToken + '\'' +
                '}';
    }
}
