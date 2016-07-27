package com.vinhsang.vivmall.presentation.view.activity.mainactivity.allfragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vinhsang.vivmall.domain.ItemProduct;
import com.vinhsang.vivmall.presentation.R;
import com.vinhsang.vivmall.presentation.helper.recyclerviewhelper.DynamicHeightImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Long on 7/11/2016.
 */

public class ItemProductsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final String TAG = "ItemProductsAdapter";
    List<ItemProduct> mItemProducts = new ArrayList<>();
    private static final int TYPE_ITEMPRODUCT = 0;
    private static final int TYPE_LOADING_MORE = -1;


    public ItemProductsAdapter(List<ItemProduct> mItemProducts) {
        this.mItemProducts = mItemProducts;
    }

    public ItemProductsAdapter() {

    }

    public void setmItemProducts(List<ItemProduct> mItemProducts) {
        this.mItemProducts = mItemProducts;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        switch (viewType) {
            case TYPE_ITEMPRODUCT:
                return createItemProductHolder(parent);
            case TYPE_LOADING_MORE:
                return new LoadingMoreHolder(
                        LayoutInflater
                                .from(parent.getContext())
                                .inflate(R.layout.infinite_loading, parent, false));
        }
        return null;


    }

    private ItemProductViewHolder createItemProductHolder(ViewGroup parent) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.layout_itemproduct,parent,false);
        ItemProductViewHolder viewHolder = new ItemProductViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {

            case TYPE_ITEMPRODUCT:
                bindItemProductView((ItemProduct) getItem(position), (ItemProductViewHolder) holder);
                break;
            case TYPE_LOADING_MORE:
                bindLoadingViewHolder((LoadingMoreHolder) holder, position);
                break;
        }

    }
    private void bindLoadingViewHolder(LoadingMoreHolder holder, int position) {
        // only show the infinite load progress spinner if there are already items in the
        // grid i.e. it's not the first item & data is being loaded
//        holder.progress.setVisibility((position > 0)
//                ? View.VISIBLE : View.INVISIBLE);
        holder.progress.setVisibility(View.VISIBLE );
    }
    private void bindItemProductView(final ItemProduct item, ItemProductViewHolder holder) {
        holder.setProductImage(item.getProduct_image());
        holder.setProductName(item.getProduct_name());
        holder.setProductPrice(item.getProduct_price());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), item.getProduct_name(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    boolean showLoadingMore = false;

    public void dataStartedLoading() {
        Log.d(TAG, "dataStartedLoading: ");
        if (showLoadingMore) return;
        showLoadingMore = true;
        notifyItemInserted(getLoadingMoreItemPosition());
    }


    public void dataFinishedLoading() {
        if (!showLoadingMore) return;
        final int loadingPos = getLoadingMoreItemPosition();
        showLoadingMore = false;
        notifyItemRemoved(loadingPos);
    }
    private int getLoadingMoreItemPosition() {
        return showLoadingMore ? getItemCount() - 1 : RecyclerView.NO_POSITION;
    }
    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);

    }



    @Override
    public int getItemCount() {
        return mItemProducts.size();
    }

    public int getDataItemCount() {
        return mItemProducts.size();
    }
    @Override
    public int getItemViewType(int position) {
        Log.d(TAG, "getItemViewType: "+position);
        if (position < getDataItemCount()
                && getDataItemCount() > 0) {
            ItemProduct item = getItem(position);
            if (item instanceof ItemProduct) {
                return TYPE_ITEMPRODUCT;
            }
        }
        return TYPE_LOADING_MORE;
    }

    private ItemProduct getItem(int position) {
        return mItemProducts.get(position);
    }

    /* package */ static  class ItemProductViewHolder extends RecyclerView.ViewHolder{

        private static final String TAG = "ItemProductViewHolder";
        @BindView(R.id.image_product)
        DynamicHeightImageView imageView;
        @BindView(R.id.product_name)
        TextView productName;
        @BindView(R.id.product_price)
        TextView productPrice;
        Context context;
        public ItemProductViewHolder(View itemView) {

            super(itemView);
            Log.d(TAG, "ItemProductViewHolder: "+itemView.getMeasuredWidth());
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();

        }
        public void setProductImage(String productImage ){

            if(imageView!=null){

                Picasso.with(context).load(productImage).into(imageView);

            }
        }
        public void setProductName(String _productName ){

            if(productName!=null){
                productName.setText(_productName);

            }
        }
        public void setProductPrice(String _productPrice ){

            if(productPrice!=null){
                productPrice.setText(formatNumber(_productPrice));
            }
        }
        String formatNumber(String s){
            int l =0;
            if(s!=null){
                l=s.length();
                s = s.substring(0,l-3);
                s ="đ"+s+"k";
            }

            return s;
        }
    }

    /* package */ static class LoadingMoreHolder extends RecyclerView.ViewHolder {

        ProgressBar progress;

        public LoadingMoreHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "LoadingMoreHolder: ");
            progress = (ProgressBar) itemView;
        }

    }

}
