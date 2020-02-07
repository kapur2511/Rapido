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

package com.rapido.rapidotest.mvp.view.viewholders;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rapido.rapidotest.R;
import com.rapido.rapidotest.mvp.view.viewmodel.ListItem;
import com.rapido.rapidotest.mvp.view.viewmodel.VideoViewModel;
import com.rapido.rapidotest.utils.ClickListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

public class VideoViewHolder<H extends ListItem> extends BaseViewHolder<ListItem> implements View.OnClickListener {

    public VideoViewHolder(@NonNull View itemView, ClickListener clickListener) {
        super(itemView);
        this.clickListener = clickListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void setData(ListItem data) {
        this.listItem = data;
        VideoViewModel videoViewModel = (VideoViewModel) data;
        if(!videoViewModel.getThumbnailURL().isEmpty())
            Picasso.get().load(videoViewModel.getThumbnailURL()).fit().into(imageViewThumbnail);
        textViewTitle.setText(videoViewModel.getVideoTitle());
        textViewChannel.setText(videoViewModel.getChannelName()+" \u2022 "+ videoViewModel.getViews());
    }

    @Override
    public void onClick(View v) {
        clickListener.onClick(listItem);
    }


    private ClickListener clickListener;
    private ListItem listItem;

    @BindView(R.id.img_detail)
    ImageView imageViewThumbnail;

    @BindView(R.id.txt_duration)
    TextView textViewDuration;

    @BindView(R.id.txt_title)
    TextView textViewTitle;

    @BindView(R.id.txt_channel)
    TextView textViewChannel;

}
