package com.vinhsang.vivmall.presentation.ui.activity.main.allfragment2.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vinhsang.vivmall.presentation.R;
import com.vinhsang.vivmall.presentation.model.BaseModel;
import com.vinhsang.vivmall.presentation.navigation.Navigator;
import com.vinhsang.vivmall.presentation.ui.activity.base.BaseAdapter;
import com.vinhsang.vivmall.presentation.ui.activity.main.allfragment2.AllPresentationModel2;
import com.vinhsang.vivmall.presentation.ui.activity.main.allfragment2.model.ShotModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Long on 7/11/2016.
 */

public class ItemProductAllAdapter2 extends BaseAdapter {
    private static final String TAG = "ItemProductAllAdapter2";
    AllPresentationModel2 allPresentationModel2;
    protected static final int TYPE_SHOT = BaseModel.ModelType.ITEM_SHOT;
    Activity activity;
    Navigator navigator;
    @Inject
    public ItemProductAllAdapter2(Activity activity, AllPresentationModel2 allPresentationModel2, Navigator navigator){
        super(allPresentationModel2.getmItemProducts());
        this.allPresentationModel2 = allPresentationModel2;
        this.activity = activity;
        this.navigator = navigator;
    }
    @Override
    protected boolean isNoMore() {
        return allPresentationModel2.isNoMore();
    }

    @Override
    protected int getDataItemViewType(int position) {
        return getItem(position).getModelType();
    }

    @Override
    protected RecyclerView.ViewHolder createDataItemHolder(int viewType, ViewGroup parent) {
        switch (viewType) {
            case TYPE_SHOT:
                return createShotProductHolder(parent);
        }
        return null;
    }

    @Override
    protected void bindDataItemViewHolder(RecyclerView.ViewHolder holder,int position) {
        switch (getItemViewType(position)) {
            case TYPE_SHOT:
                bindShotProductView((ShotModel) getItem(position), (ItemShotViewHolder) holder);
                break;
        }
    }

    protected ItemShotViewHolder createShotProductHolder(ViewGroup parent) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_shot,parent,false);
        final ItemShotViewHolder viewHolder = new ItemShotViewHolder(v);

        return viewHolder;
    }

    protected void bindShotProductView(final ShotModel item, ItemShotViewHolder holder) {

        holder.likeCountText.setText(item.likes_count);
        holder.viewCountText.setText(item.title);

        holder.setProductImage(item.images.normal);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigator.navigateToShotDetails(activity,item);
            }
        });
    }

    /* package */ protected static  class ItemShotViewHolder extends RecyclerView.ViewHolder{

        private static final String TAG = "ItemShotViewHolder";

        @BindView(R.id.image_shot)
        ImageView shotImage;
        @BindView(R.id.image_like) ImageView likeCountImage;
        @BindView(R.id.text_like_count)
        TextView likeCountText;
        @BindView(R.id.text_title) TextView viewCountText;
        @BindView(R.id.layout_header) View viewHeader;
        Context context;
        public ItemShotViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();

        }


        public ShotModel mShotModel;

        public void setProductImage(String image ){

            if(shotImage!=null){

                Picasso.with(context).load(image).into(shotImage);

            }
        }

    }




}
