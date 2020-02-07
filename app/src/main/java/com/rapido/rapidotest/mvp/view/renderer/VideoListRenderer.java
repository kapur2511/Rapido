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

package com.rapido.rapidotest.mvp.view.renderer;

import com.rapido.rapidotest.mvp.view.viewmodel.VideoListWrapperViewModel;
import com.rapido.rapidotest.mvp.view.viewmodel.VideoViewModel;

import java.util.List;

public interface VideoListRenderer extends BaseRenderer{

    void renderVideoList(VideoListWrapperViewModel videoListWrapperViewModel);

    void renderError();
}
