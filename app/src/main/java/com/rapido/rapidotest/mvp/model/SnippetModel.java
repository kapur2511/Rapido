/*
 * Copyright (C) 11-May-2019 Cricbuzz.com
 * All rights reserved.
 *
 * http://www.cricbuzz.com
 * @author: kshitiz.kapur
 */

package com.rapido.rapidotest.mvp.model;

public class SnippetModel {

    private String publishedAt;

    private String defaultAudioLanguage;

    private LocalizedModel localized;

    private String description;

    private String title;

    private ThumbnailListModel thumbnails;

    private String channelId;

    private String categoryId;

    private String channelTitle;

    private String[] tags;

    private String liveBroadcastContent;

    public String getPublishedAt ()
    {
        return publishedAt;
    }

    public void setPublishedAt (String publishedAt)
    {
        this.publishedAt = publishedAt;
    }

    public String getDefaultAudioLanguage ()
    {
        return defaultAudioLanguage;
    }

    public void setDefaultAudioLanguage (String defaultAudioLanguage)
    {
        this.defaultAudioLanguage = defaultAudioLanguage;
    }

    public LocalizedModel getLocalized ()
    {
        return localized;
    }

    public void setLocalized (LocalizedModel localized)
    {
        this.localized = localized;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public ThumbnailListModel getThumbnails ()
    {
        return thumbnails;
    }

    public void setThumbnails (ThumbnailListModel thumbnails)
    {
        this.thumbnails = thumbnails;
    }

    public String getChannelId ()
    {
        return channelId;
    }

    public void setChannelId (String channelId)
    {
        this.channelId = channelId;
    }

    public String getCategoryId ()
    {
        return categoryId;
    }

    public void setCategoryId (String categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getChannelTitle ()
    {
        return channelTitle;
    }

    public void setChannelTitle (String channelTitle)
    {
        this.channelTitle = channelTitle;
    }

    public String[] getTags ()
    {
        return tags;
    }

    public void setTags (String[] tags)
    {
        this.tags = tags;
    }

    public String getLiveBroadcastContent ()
    {
        return liveBroadcastContent;
    }

    public void setLiveBroadcastContent (String liveBroadcastContent)
    {
        this.liveBroadcastContent = liveBroadcastContent;
    }

    @Override
    public String toString()
    {
        return "SnippetModel [publishedAt = "+publishedAt+", defaultAudioLanguage = "+defaultAudioLanguage+", localized = "+localized+", description = "+description+", title = "+title+", thumbnails = "+thumbnails+", channelId = "+channelId+", categoryId = "+categoryId+", channelTitle = "+channelTitle+", tags = "+tags+", liveBroadcastContent = "+liveBroadcastContent+"]";
    }
}
