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

package com.rapido.rapidotest.api;

import com.rapido.rapidotest.mvp.model.VideoListWrapperModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface VideoApi {

    //Video List API
    @GET("videos/")
    Observable<Response<VideoListWrapperModel>> loadVideoList(@QueryMap Map<String, Object> params);

}
