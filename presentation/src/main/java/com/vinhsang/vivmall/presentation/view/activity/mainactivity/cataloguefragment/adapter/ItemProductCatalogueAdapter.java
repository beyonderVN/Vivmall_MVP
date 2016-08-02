package com.vinhsang.vivmall.presentation.view.activity.mainactivity.cataloguefragment.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vinhsang.vivmall.domain.ItemProduct;
import com.vinhsang.vivmall.presentation.R;
import com.vinhsang.vivmall.presentation.view.activity.mainactivity.cataloguefragment.CataloguePresentationModel;
import com.vinhsang.vivmall.presentation.view.recyclerviewhelper.adapter.ItemProductsAdapter;

/**
 * Created by Long on 7/11/2016.
 */

public class ItemProductCatalogueAdapter extends ItemProductsAdapter{
    private static final String TAG = "ItemProductCatalogue";
    CataloguePresentationModel cataloguePresentationModel;
    protected static final int TYPE_NO_MORE = -2;


    public ItemProductCatalogueAdapter(CataloguePresentationModel cataloguePresentationModel) {
        super(cataloguePresentationModel.getmItemProducts());
        this.cataloguePresentationModel = cataloguePresentationModel;
    }

    @Override
    public void dataFinishedLoading() {
        if(cataloguePresentationModel.isNoMore()==false) {
            super.dataFinishedLoading();
        }else{
            notifyItemChanged(getLoadingMoreItemPosition());
        }
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
        if (cataloguePresentationModel.isNoMore()) {
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
        switch (getItemViewType(position)) {
            case TYPE_NO_MORE:
                bindNoMoreViewHolder((NoMoreHolder) holder, position);
                break;
        }
    }

    /* package */ protected static class NoMoreHolder extends RecyclerView.ViewHolder {
        public NoMoreHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "NoMoreHolder: ");
        }

    }

    protected void bindNoMoreViewHolder(NoMoreHolder holder, int position) {
        // only show the infinite load progress spinner if there are already items in the
        // grid i.e. it's not the first item & data is being loaded
//        holder.progress.setVisibility((position > 0)
//                ? View.VISIBLE : View.INVISIBLE);
        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        layoutParams.setFullSpan(true);
    }
    protected NoMoreHolder createNoMoreHolder(ViewGroup parent) {
        return new NoMoreHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.infinite_no_more, parent, false));
    }
}
