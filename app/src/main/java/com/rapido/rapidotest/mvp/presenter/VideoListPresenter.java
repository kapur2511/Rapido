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

package com.rapido.rapidotest.mvp.presenter;

import android.util.Log;

import com.rapido.rapidotest.api.VideoApi;
import com.rapido.rapidotest.mvp.model.VideoListWrapperModel;
import com.rapido.rapidotest.mvp.model.VideoModel;
import com.rapido.rapidotest.mvp.view.renderer.VideoListRenderer;
import com.rapido.rapidotest.mvp.view.viewmodel.VideoListWrapperViewModel;
import com.rapido.rapidotest.mvp.view.viewmodel.VideoViewModel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class VideoListPresenter extends BasePresenter<VideoListRenderer> {

    @Inject
    public VideoListPresenter() {
    }

    @Override
    public void loadData(Map<String, Object> stringObjectHashMap) {
        Log.d(TAG,"Loading Videos");
        videoAPI.loadVideoList(stringObjectHashMap)
                .subscribeOn(Schedulers.io())
                .compose(new VideoTransformer())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new WeatherObserver());
    }

    private class VideoTransformer implements ObservableTransformer<Response<VideoListWrapperModel>, VideoListWrapperViewModel>{

        @Override
        public ObservableSource<VideoListWrapperViewModel> apply(Observable<Response<VideoListWrapperModel>> responseObservable) {
            return responseObservable.filter(new Predicate<Response<VideoListWrapperModel>>() {
                @Override
                public boolean test(Response<VideoListWrapperModel> videoListWrapperModelResponse) throws Exception {
                    return videoListWrapperModelResponse.body()!=null && videoListWrapperModelResponse.body().getItems()!=null && videoListWrapperModelResponse.body().getItems().size()>0;
                }
            }).flatMap(new Function<Response<VideoListWrapperModel>, ObservableSource<VideoListWrapperModel>>() {
                @Override
                public ObservableSource<VideoListWrapperModel> apply(Response<VideoListWrapperModel> videoListWrapperModelResponse) throws Exception {
                    return Observable.just(videoListWrapperModelResponse.body());
                }
            }).flatMapIterable(new Function<VideoListWrapperModel, Iterable<VideoModel>>() {
                @Override
                public Iterable<VideoModel> apply(VideoListWrapperModel videoListWrapperModel) throws Exception {
                    nextPageToken = videoListWrapperModel.getNextPageToken();
                    int totalVideos = videoListWrapperModel.getPageInfo().getTotalResults();
                    int videosPerPage = videoListWrapperModel.getPageInfo().getResultsPerPage();
                    totalPages = totalVideos/videosPerPage;
                    return videoListWrapperModel.getItems();
                }
            }).flatMap(new Function<VideoModel, ObservableSource<VideoViewModel>>() {
                @Override
                public ObservableSource<VideoViewModel> apply(VideoModel videoModel) throws Exception {
                    return Observable.just(new VideoViewModel(videoModel));
                }
            }).toList().flatMapObservable(new Function<List<VideoViewModel>, ObservableSource<VideoListWrapperViewModel>>() {
                @Override
                public ObservableSource<VideoListWrapperViewModel> apply(List<VideoViewModel> videoViewModels) throws Exception {
                    VideoListWrapperViewModel videoListWrapperViewModel = new VideoListWrapperViewModel();
                    videoListWrapperViewModel.setVideoViewModels(videoViewModels);
                    videoListWrapperViewModel.setNextPageToken(nextPageToken);
                    videoListWrapperViewModel.setTotalPages(totalPages);
                    return Observable.just(videoListWrapperViewModel);
                }
            });
        }
    }

    private class WeatherObserver implements Observer<VideoListWrapperViewModel>{

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(VideoListWrapperViewModel videoListWrapperViewModel) {
            Log.d("RESPONSE: ",videoListWrapperViewModel.toString());
            view.renderVideoList(videoListWrapperViewModel);
        }

        @Override
        public void onError(Throwable e) {
            Log.d("RESPONSE: ","==="+e.getMessage());
            view.renderError();
        }

        @Override
        public void onComplete() {

        }

    }

    private String nextPageToken;
    private int totalPages;

    @Inject
    VideoApi videoAPI;
}
