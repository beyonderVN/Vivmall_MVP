package com.vinhsang.vivmall.presentation.ui.activity.detailactivity.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vinhsang.vivmall.presentation.R;
import com.vinhsang.vivmall.presentation.model.ItemProductModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Long on 8/4/2016.
 */

public class ItemDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "ItemDetailAdapter";
    private static final int ITEM_IMAGE = 0;
    private static final int TYPE_NO_COMMENTS = 1;
    private static final int TYPE_COMMENT = 2;
    private static final int TYPE_COMMENT_REPLY = 3;
    private static final int TYPE_FOOTER = 4;

    Activity activity;
    ItemProductModel itemProductModel;

    public ItemDetailAdapter(Activity activity, ItemProductModel itemProductModel) {
        this.activity = activity;
        this.itemProductModel = itemProductModel;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        switch (viewType) {
            case ITEM_IMAGE:
                return createImagesHolder(parent);
            case TYPE_NO_COMMENTS:
                return createImagesHolder(parent);
            case TYPE_COMMENT:
                return createImagesHolder(parent);
            case TYPE_COMMENT_REPLY:
                return createImagesHolder(parent);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case ITEM_IMAGE:
                bindImagesHolder((ImagesHolder) holder, itemProductModel.getProduct_image());
            case TYPE_NO_COMMENTS:
                bindImagesHolder((ImagesHolder) holder, itemProductModel.getProduct_image());
            case TYPE_COMMENT:
                bindImagesHolder((ImagesHolder) holder, itemProductModel.getProduct_image());
            case TYPE_COMMENT_REPLY:
                bindImagesHolder((ImagesHolder) holder, itemProductModel.getProduct_image());
        }
    }


    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void  init() {
        for (int i = 0; i < 3; i++) {
            notifyItemInserted(i);
        }
    }

    /* package */ protected static class ImagesHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.images_container)
        ImageView imageContainer;
        Context context;

        public ImagesHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        public void setImages(String imageUrl) {
            if (imageContainer != null) {
                Picasso.with(context).load(imageUrl).into(imageContainer);
            }
        }
    }

    @NonNull
    private ImagesHolder createImagesHolder(ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.layout_contain_images, parent, false);
        return new ImagesHolder(view);
    }

    private void bindImagesHolder(ImagesHolder holder, String imageUrl) {
        holder.setImages(imageUrl);
    }
}
