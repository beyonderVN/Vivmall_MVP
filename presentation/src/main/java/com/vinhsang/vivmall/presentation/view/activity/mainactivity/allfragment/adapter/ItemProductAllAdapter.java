package com.vinhsang.vivmall.presentation.view.activity.mainactivity.allfragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vinhsang.vivmall.domain.ItemProduct;
import com.vinhsang.vivmall.presentation.R;
import com.vinhsang.vivmall.presentation.view.activity.mainactivity.allfragment.AllPresentationModel;
import com.vinhsang.vivmall.presentation.view.recyclerviewhelper.adapter.ItemProductsAdapter;

import java.util.List;

/**
 * Created by Long on 7/11/2016.
 */

public class ItemProductAllAdapter extends ItemProductsAdapter {
    private static final String TAG = "ItemProductAllAdapter";
    AllPresentationModel allPresentationModel;
    protected static final int TYPE_NO_MORE = -2;
    public ItemProductAllAdapter(List<ItemProduct> mItemProducts) {
        super(mItemProducts);
    }
    public ItemProductAllAdapter(AllPresentationModel allPresentationModel) {
        super(allPresentationModel.getmItemProducts());
        this.allPresentationModel = allPresentationModel;
    }


    @Override
    public void dataFinishedLoading() {

        if(allPresentationModel.isNoMore()==false) {
            super.dataFinishedLoading();
        }else{
            notifyItemChanged(getLoadingMoreItemPosition());
        }
    }

    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "getItemViewType: "+position);
        Log.d(TAG, "getItemViewType: "+allPresentationModel.isNoMore());
        Log.d(TAG, "getItemViewType: "+allPresentationModel.getmItemProducts().size());
        if (position < getDataItemCount()
                && getDataItemCount() > 0) {
            ItemProduct item = getItem(position);
            if (item instanceof ItemProduct) {
                return TYPE_ITEMPRODUCT;
            }
        }
        if (allPresentationModel.isNoMore()) {
            return TYPE_NO_MORE;
        }
        return TYPE_LOADING_MORE;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        switch (viewType) {
            case TYPE_ITEMPRODUCT:
                return createItemProductHolder(parent);
            case TYPE_LOADING_MORE:
                return createLoadingMoreHolder(parent);
            case TYPE_NO_MORE:
                return createNoMoreHolder(parent);
        }
        return null;

    }

    private RecyclerView.ViewHolder createNoMoreHolder(ViewGroup parent) {
        return new NoMoreHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.infinite_no_more, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {

            case TYPE_ITEMPRODUCT:
                bindItemProductView((ItemProduct) getItem(position), (ItemProductViewHolder) holder);
                break;
            case TYPE_LOADING_MORE:
                bindLoadingViewHolder((LoadingMoreHolder) holder, position);
                break;
            case TYPE_NO_MORE:
                bindNoMoreViewHolder((NoMoreHolder) holder, position);
                break;
        }

    }

    private void bindNoMoreViewHolder(NoMoreHolder holder, int position) {
        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        layoutParams.setFullSpan(true);
    }

    /* package */ protected static class NoMoreHolder extends RecyclerView.ViewHolder {


        public NoMoreHolder(View itemView) {
            super(itemView);
        }

    }

}
