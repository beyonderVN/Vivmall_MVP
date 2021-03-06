package com.vinhsang.vivmall.presentation.ui.activity.shotdetail;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vinhsang.vivmall.data.dribbble.model.Comment;
import com.vinhsang.vivmall.presentation.R;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private List<Comment> mComments;
    private ClickListener mClickListener;

    @Inject
    public CommentAdapter() {
        mComments = Collections.emptyList();
    }

    public void setComments(List<Comment> comments) {
        mComments = comments;
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        Comment comment = mComments.get(position);
        holder.mComment = comment;
        holder.userNameText.setText(comment.user.username);
        holder.commentText.setText(Html.fromHtml(comment.body));
        Picasso.with(holder.itemView.getContext())
                .load(comment.user.avatarUrl).into(holder.userImage);
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_avatar)
        ImageView userImage;
        @BindView(R.id.text_user_name)
        TextView userNameText;
        @BindView(R.id.text_time)
        TextView timeText;
        @BindView(R.id.text_comment)
        TextView commentText;

        public Comment mComment;

        public CommentViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickListener != null) mClickListener.onCommentClick(mComment);
                }
            });
            commentText.setMovementMethod(new TextViewLinkHandler() {
                @Override
                public void onLinkClick(String url) {
                    CustomTabUtil.open(itemView.getContext(), Uri.parse(url));
                }
            });

            userImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomTabUtil.open(itemView.getContext(), Uri.parse(mComment.user.htmlUrl));
                }
            });
        }
    }

    public interface ClickListener {
        void onCommentClick(Comment comment);
    }

}
