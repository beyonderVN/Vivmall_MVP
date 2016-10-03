package com.vinhsang.vivmall.presentation.ui.activity.shotdetail;

import android.util.Log;

import com.vinhsang.vivmall.data.dribbble.DataManager;
import com.vinhsang.vivmall.data.dribbble.model.Comment;
import com.vinhsang.vivmall.presentation.coremvp.SimpleMVPPresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.vinhsang.vivmall.data.datamanager.HTTP.TAG;

/**
 * Created by Long on 9/22/2016.
 */

public class ShotPresenter extends SimpleMVPPresenter<ShotView,ShotPresentationModel> {
    DataManager mDataManager;
    private Subscription mSubscription;

    // We'll handle pagination in the future...
    public static final int SHOT_COUNT = 10;
    public static final int SHOT_PAGE = 0;
    @Inject
    public ShotPresenter(DataManager dataManager) {
        mDataManager=dataManager;
    }

    @Override
    public void attachView(ShotView mvpView, ShotPresentationModel presentationModel) {
        super.attachView(mvpView, presentationModel);

    }

    private List<Comment> mComments;

    public void getComments(int id, int perPage, int page) {

        Single<List<Comment>> single;
        if (mComments == null) {
            single = mDataManager.getComments(id, perPage, page);
        } else {
            single = Single.just(mComments);
        }

        mSubscription = single
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleSubscriber<List<Comment>>() {
                    @Override
                    public void onSuccess(List<Comment> comments) {
                        mComments = comments;
                        getMvpView().hideProgress();
                        if (comments.isEmpty()) {
                            getMvpView().showEmptyComments();
                        } else {
                            getMvpView().showComments(comments);
                        }
                        getMvpView().showCommentsTitle(!comments.isEmpty());
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.e(TAG, "There was an error retrieving the comments", error);
                        getMvpView().hideProgress();
                        getMvpView().showError();
                    }
                });

    }

}
