package com.vinhsang.vivmall.presentation.ui.activity.shotdetail;

import com.vinhsang.vivmall.data.dribbble.model.Comment;
import com.vinhsang.vivmall.presentation.coremvp.MVPView;

import java.util.List;

/**
 * Created by Long on 9/22/2016.
 */

public interface ShotView extends MVPView {

    void showProgress();

    void hideProgress();

    void showComments(List<Comment> comments);

    void showError();

    void showEmptyComments();

    void showCommentsTitle(boolean hasComments);
}
