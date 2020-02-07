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

package com.rapido.rapidotest.mvp.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rapido.rapidotest.R;
import com.rapido.rapidotest.mvp.presenter.VideoListPresenter;
import com.rapido.rapidotest.mvp.view.adapters.BaseListAdapter;
import com.rapido.rapidotest.mvp.view.adapters.VideoListAdapter;
import com.rapido.rapidotest.mvp.view.renderer.VideoListRenderer;
import com.rapido.rapidotest.mvp.view.viewmodel.ListItem;
import com.rapido.rapidotest.mvp.view.viewmodel.VideoListWrapperViewModel;
import com.rapido.rapidotest.mvp.view.viewmodel.VideoViewModel;
import com.rapido.rapidotest.utils.ClickListener;
import com.rapido.rapidotest.utils.Constants;
import com.rapido.rapidotest.utils.PIPManager;
import com.rapido.rapidotest.utils.pagination.PaginationScrollListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class VideoListFragment extends BaseFragment<VideoListPresenter, VideoListRenderer, VideoListAdapter> implements VideoListRenderer, ClickListener {

    public VideoListFragment(){
        layoutId = R.layout.fragment_video_list;
    }

    @Override
    protected void loadData(VideoListPresenter presenter) {
        long lastFetchTime = sharedPreferences.getLong(Constants.PREF_CURRENT_TIME,0);
        if(adapter!=null && adapter.getItemCount()==0){
            stringObjectHashMap.put("part","snippet,statistics");
            stringObjectHashMap.put("chart","mostPopular");
            stringObjectHashMap.put("maxResults",10);
            stringObjectHashMap.put("key", Constants.API_KEY);
            if(stringObjectHashMap.containsKey("pageToken"))
                stringObjectHashMap.remove("pageToken");
            if((System.currentTimeMillis() - lastFetchTime) > 300000)
                presenter.loadData(stringObjectHashMap);
            else{
                List<ListItem> listItems = getCachedListData(Constants.PREF_CACHE_DATA, new TypeToken<List<VideoViewModel>>(){}.getType());
                currentPage   = sharedPreferences.getInt(Constants.PREF_CURRENT_PAGE, 1);
                nextPageToken = sharedPreferences.getString(Constants.PREF_NEXT_PAGE_TOKEN,"");
                totalPages    = sharedPreferences.getInt(Constants.PREF_TOTAL_PAGES, 0);
                if(listItems!=null){
                    adapter.addItems(listItems);
                    switchUI(true, false, false);
                }
                else
                    switchUI(false, false, true);
            }
        }else{
            switchUI(true, false, false);
        }
    }

    @Override
    protected RecyclerView getRecyclerView() {
        return recyclerViewVideos;
    }

    @Override
    protected VideoListAdapter getAdapter() {
        videoListAdapter = new VideoListAdapter(new ArrayList(), this);
        return videoListAdapter;
    }


    @Override
    public void renderVideoList(VideoListWrapperViewModel videoListWrapperViewModel) {
        Log.d(TAG, videoListWrapperViewModel.toString());
        isLoading = false;
        BaseListAdapter baseListAdapter = (BaseListAdapter) recyclerViewVideos.getAdapter();
        baseListAdapter.addItems(videoListWrapperViewModel.getVideoViewModels());
        if(currentPage<videoListWrapperViewModel.getTotalPages())
            baseListAdapter.addLoadingItem();
        else
            baseListAdapter.removeLoadingItem();
        switchUI(true, false, false);
        sharedPreferences.edit().putLong(Constants.PREF_CURRENT_TIME, System.currentTimeMillis()).apply();
        Gson gson = new Gson();
        String json = gson.toJson(adapter.getList());
        Log.d(TAG,"SAVING DATA: "+json);
        sharedPreferences.edit().putString(Constants.PREF_CACHE_DATA, json).apply();
        nextPageToken = videoListWrapperViewModel.getNextPageToken();
        sharedPreferences.edit().putString(Constants.PREF_NEXT_PAGE_TOKEN, nextPageToken).apply();
        if(totalPages == 0){
            totalPages = videoListWrapperViewModel.getTotalPages();
            sharedPreferences.edit().putInt(Constants.PREF_TOTAL_PAGES, totalPages).apply();
        }
    }


    @Override
    public void onClick(ListItem listItem) {
        VideoViewModel videoViewModel = (VideoViewModel)listItem;
        Log.d(TAG,"ITEM CLICKED: "+(videoViewModel.toString()));
        if(clBottomSheet.getVisibility() == View.GONE)
            clBottomSheet.setVisibility(View.VISIBLE);
        videoFragment = (VideoPlayerFragment)YouTubePlayerSupportFragment.instantiate(getContext(),VideoPlayerFragment.class.getName());
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(clBottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//        ResizeAnimation resizeSmallAnimation = new ResizeAnimation(playerContainer, 60, 330, displayMetrics.widthPixels, 610,true);
//        resizeSmallAnimation.setDuration(100);
//        ResizeAnimation resizeBigAnimation = new ResizeAnimation(playerContainer, 750, 40, displayMetrics.widthPixels, 0, false);
//        resizeBigAnimation.setDuration(100);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                Log.d("BOTTOM_SHEET_STATE: ",""+i);
                switch(i){
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        if(expandCounter>0)
                            expandCounter--;
                        imgPlayPause.setVisibility(View.VISIBLE);
                        imgHideBtmSheet.setVisibility(View.VISIBLE);
                        imgHideBtmSheet.setImageResource(R.drawable.ic_cancel);
                        if(videoFragment.getPlayer().isPlaying())
                            imgPlayPause.setImageResource(R.drawable.ic_pause);
                        else
                            imgPlayPause.setImageResource(R.drawable.ic_play);
//                        playerContainer.startAnimation(resizeSmallAnimation);
                        if(collapseCounter == 0){
                            collapseCounter++;
                            pipManager.collapse();
                        }
                        videoFragment.getPlayer().setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        if(collapseCounter>0)
                            collapseCounter--;
                        if(expandCounter == 0){
                            expandCounter++;
                            pipManager.expand(displayMetrics.widthPixels);
                        }
//                        playerContainer.startAnimation(resizeBigAnimation);
                        imgPlayPause.setVisibility(View.GONE);
                        imgHideBtmSheet.setVisibility(View.GONE);
                        videoFragment.getPlayer().setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                Log.d("BOTTOM_SHEET_SLIDE: ",""+v);
            }
        });
        textViewTitle.setText(videoViewModel.getVideoTitle());
        textViewDesc.setMovementMethod(new ScrollingMovementMethod());
        textViewDesc.setText(videoViewModel.getVideoDescription());
        Log.d("VIDEO_FRAGMENT: ", "INSTANCE_OF: "+(videoFragment instanceof VideoPlayerFragment));
        Bundle bundle = new Bundle();
        bundle.putString(Constants.VIDEO_ID, (videoViewModel.getVideoId()));
        videoFragment.setArguments(bundle);
        getChildFragmentManager().beginTransaction().replace(R.id.player_container,videoFragment).commit();
    }

    @Override
    protected void setupPIPManager() {
        this.pipManager = new PIPManager(playerContainer);
    }

    @Override
    protected void setupPagination(RecyclerView recyclerView){
        recyclerView.addOnScrollListener(new PaginationScrollListener((LinearLayoutManager) recyclerViewVideos.getLayoutManager()) {
            @Override
            protected void loadMoreItems() {
                if(currentPage<totalPages){
                    currentPage++;
                    sharedPreferences.edit().putInt(Constants.PREF_CURRENT_PAGE, currentPage).apply();
                    stringObjectHashMap.put("pageToken", nextPageToken);
                    isLoading = true;
                    presenter.loadData(stringObjectHashMap);
                }else{
                    isLastPage = true;
                    adapter.setLoading(false);
                }
            }

            @Override
            public int getTotalPageCount() {
                return totalPages;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    @Override
    public void renderError() {
        switchUI(false, false, true);
    }
    
    @OnClick(R.id.img_play_pause)
    void onPlayPauseClick(View v){
        if(videoFragment.getPlayer().isPlaying()){
            videoFragment.getPlayer().pause();
            imgPlayPause.setImageResource(R.drawable.ic_play);
        }else{
            imgPlayPause.setImageResource(R.drawable.ic_pause);
            videoFragment.getPlayer().play();
        }
    }

    @OnClick(R.id.img_hide)
    void onHideClick(View v){
        videoFragment.getPlayer().release();
        imgPlayPause.setVisibility(View.GONE);
        imgHideBtmSheet.setVisibility(View.GONE);
        clBottomSheet.setVisibility(View.GONE);
    }



    @BindView(R.id.img_play_pause)
    ImageView imgPlayPause;

    @BindView(R.id.img_hide)
    ImageView imgHideBtmSheet;
    
    @BindView(R.id.rv_list)
    RecyclerView recyclerViewVideos;

    @BindView(R.id.player_container)
    FrameLayout playerContainer;

    @BindView(R.id.txt_title)
    TextView textViewTitle;

    @BindView(R.id.txt_description)
    TextView textViewDesc;

    @BindView(R.id.btm_sheet_container)
    ConstraintLayout clBottomSheet;

    Map<String, Object> stringObjectHashMap = new HashMap<>();
    private VideoPlayerFragment videoFragment;
    private PIPManager pipManager;
    private VideoListAdapter videoListAdapter;
    private boolean isLastPage, isLoading;
    private String nextPageToken;
    private int currentPage = 1, collapseCounter = 0, expandCounter = 0, totalPages;

}
