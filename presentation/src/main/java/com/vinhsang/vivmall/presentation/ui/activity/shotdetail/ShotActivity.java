package com.vinhsang.vivmall.presentation.ui.activity.shotdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vinhsang.vivmall.data.bourbon.model.Comment;
import com.vinhsang.vivmall.presentation.MainApplication;
import com.vinhsang.vivmall.presentation.R;
import com.vinhsang.vivmall.presentation.ui.activity.base.BaseActivity;
import com.vinhsang.vivmall.presentation.ui.activity.main.allfragment2.model.ShotModel;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShotActivity extends BaseActivity<ShotPresentationModel,ShotView,ShotPresenter> implements ShotView {
    private static final String TAG = "ShotActivity";

    public static String EXTRA_SHOT = "EXTRA_SHOT";


    @BindView(R.id.recycler_comments)
    RecyclerView mCommentsRecycler;
    @BindView(R.id.toolbar_shot)
    Toolbar mToolbar;
    @BindView(R.id.image_shot)
    ImageView mShotImage;
    @BindView(R.id.text_title)
    TextView mTitleText;
    @BindView(R.id.text_like_count) TextView mLikeText;
    @BindView(R.id.text_comments_title) TextView mCommentsTitleText;
    @BindView(R.id.progress)
    View mProgress;
    @BindView(R.id.text_error_message) View mErrorText;

    CommentAdapter mCommentsAdapter =new CommentAdapter();

    public static Intent getCallingIntent(Context context, ShotModel shot) {
        Intent intent = new Intent(context, ShotActivity.class);
        intent.putExtra(EXTRA_SHOT, (Serializable) shot);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shot);
        ButterKnife.bind(this);

        ShotModel shot = (ShotModel) getIntent().getSerializableExtra(EXTRA_SHOT);

        if (shot == null) {
            throw new IllegalArgumentException("Shot activity requires a shot instance!");
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        mCommentsRecycler.setLayoutManager(new LinearLayoutManager(this));
        mCommentsRecycler.setHasFixedSize(true);
        mCommentsRecycler.setAdapter(mCommentsAdapter);

        setupLayout(shot);

        presenter.getComments(shot.id, ShotPresenter.SHOT_COUNT, ShotPresenter.SHOT_PAGE);
    }

    @Override
    protected void performFieldInjection() {
        MainApplication.getMainComponent().inject(this);
    }

    @NonNull
    @Override
    protected ShotPresentationModel createPresentationModel() {
        return new ShotPresentationModel();
    }

    private void setupLayout(ShotModel shot) {
        Picasso.with(this).load(shot.images.normal)
                .into(mShotImage);
        mTitleText.setText(shot.title);
        mLikeText.setText(shot.likes_count);
    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void showComments(List<Comment> comments) {
        mCommentsAdapter.setComments(comments);
        mCommentsAdapter.notifyDataSetChanged();
        mCommentsRecycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCommentsTitle(boolean hasComments) {
        mCommentsTitleText.setText(hasComments ?
                "Recent" : "NO recent");
        mCommentsTitleText.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {
        mCommentsRecycler.setVisibility(View.GONE);
        mCommentsTitleText.setVisibility(View.GONE);
        mErrorText.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyComments() {
        mCommentsTitleText.setVisibility(View.VISIBLE);
        mCommentsRecycler.setVisibility(View.GONE);
    }
}
