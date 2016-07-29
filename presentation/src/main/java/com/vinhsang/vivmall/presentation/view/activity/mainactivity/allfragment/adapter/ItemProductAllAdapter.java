package com.vinhsang.vivmall.presentation.view.activity.mainactivity.allfragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.vinhsang.vivmall.domain.ItemProduct;
import com.vinhsang.vivmall.presentation.view.activity.mainactivity.allfragment.AllPresentationModel;
import com.vinhsang.vivmall.presentation.view.recyclerviewhelper.adapter.ItemProductsAdapter;

import java.util.List;

/**
 * Created by Long on 7/11/2016.
 */

public class ItemProductAllAdapter extends ItemProductsAdapter {
    AllPresentationModel allPresentationModel;

    public ItemProductAllAdapter(List<ItemProduct> mItemProducts) {
        super(mItemProducts);
    }
    public ItemProductAllAdapter(AllPresentationModel allPresentationModel) {
        super(allPresentationModel.getmItemProducts());
        this.allPresentationModel = allPresentationModel;
    }

    @Override
    public void dataFinishedLoading() {
        super.dataFinishedLoading();
        notifyItemInserted(getLoadingMoreItemPosition());
    }
    @Override
    public int getItemViewType(int position) {
        if (position < getDataItemCount()
                && getDataItemCount() > 0) {
            ItemProduct item = getItem(position);
            if (item instanceof ItemProduct) {
                return TYPE_ITEMPRODUCT;
            }
        }
        if(allPresentationModel.isNoMore()){
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

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }
}
