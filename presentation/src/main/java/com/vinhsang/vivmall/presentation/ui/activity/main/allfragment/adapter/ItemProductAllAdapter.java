package com.vinhsang.vivmall.presentation.ui.activity.main.allfragment.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vinhsang.vivmall.presentation.R;
import com.vinhsang.vivmall.presentation.model.BaseModel;
import com.vinhsang.vivmall.presentation.model.ItemProductModel;
import com.vinhsang.vivmall.presentation.model.ItemProductModel2;
import com.vinhsang.vivmall.presentation.navigation.Navigator;
import com.vinhsang.vivmall.presentation.ui.activity.base.BaseAdapter;
import com.vinhsang.vivmall.presentation.ui.activity.main.allfragment.AllPresentationModel;
import com.vinhsang.vivmall.presentation.ui.widget.DynamicHeightImageView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Long on 7/11/2016.
 */

public class ItemProductAllAdapter extends BaseAdapter {
    private static final String TAG = "ItemProductAllAdapter";
    AllPresentationModel allPresentationModel;
    protected static final int TYPE_ITEMPRODUCT = BaseModel.ModelType.ITEM_PRODUCT;
    protected static final int TYPE_ITEMPRODUCT2 = BaseModel.ModelType.ITEM_PRODUCT2;
    Activity activity;
    Navigator navigator;
    @Inject
    public ItemProductAllAdapter (Activity activity,AllPresentationModel allPresentationModel,Navigator navigator){
        super(allPresentationModel.getmItemProducts());
        this.allPresentationModel = allPresentationModel;
        this.activity = activity;
        this.navigator = navigator;
    }
    @Override
    protected boolean isNoMore() {
        return allPresentationModel.isNoMore();
    }

    @Override
    protected int getDataItemViewType(int position) {
        return getItem(position).getModelType();
    }

    @Override
    protected RecyclerView.ViewHolder createDataItemHolder(int viewType, ViewGroup parent) {
        switch (viewType) {
            case TYPE_ITEMPRODUCT:
                return createItemProductHolder(parent);
            case TYPE_ITEMPRODUCT2:
                return createItemProductHolder2(parent);
        }
        return null;
    }

    @Override
    protected void bindDataItemViewHolder(RecyclerView.ViewHolder holder,int position) {
        switch (getItemViewType(position)) {
            case TYPE_ITEMPRODUCT:
                bindItemProductView((ItemProductModel) getItem(position), (ItemProductViewHolder) holder);
                break;
            case TYPE_ITEMPRODUCT2:
                bindItemProductView2((ItemProductModel2) getItem(position), (ItemProductViewHolder2) holder);
                break;
        }
    }

    protected ItemProductViewHolder createItemProductHolder(ViewGroup parent) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.layout_itemproduct,parent,false);
        final ItemProductViewHolder viewHolder = new ItemProductViewHolder(v);

        return viewHolder;
    }

    protected void bindItemProductView(final ItemProductModel item, ItemProductViewHolder holder) {
        holder.setProductImage(item.getProduct_image());
        holder.setProductName(item.getProduct_name());
        holder.setProductPrice(item.getProduct_price());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigator.navigateToItemDetails(activity,item);
            }
        });
    }

    /* package */ protected static  class ItemProductViewHolder extends RecyclerView.ViewHolder{

        private static final String TAG = "SectionViewHolder";
        @BindView(R.id.image_product)
        DynamicHeightImageView imageView;
        @BindView(R.id.product_name)
        TextView productName;
        @BindView(R.id.product_price)
        TextView productPrice;
        Context context;
        public ItemProductViewHolder(View itemView) {

            super(itemView);
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

            if(s!=null){
                int l =s.length();
                s = s.substring(0,l-3);
                s ="Đ "+s+"k";
            }

            return s;
        }
    }

    /* package */ protected static  class ItemProductViewHolder2 extends RecyclerView.ViewHolder{

        private static final String TAG = "SectionViewHolder";
        @BindView(R.id.image_product)
        DynamicHeightImageView imageView;
        @BindView(R.id.product_name)
        TextView productName;
        @BindView(R.id.product_price)
        TextView productPrice;
        Context context;
        public ItemProductViewHolder2(View itemView) {

            super(itemView);
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

            if(s!=null){
                int l =s.length();
                s = s.substring(0,l-3);
                s ="đ"+s+"k";
            }

            return s;
        }
    }
    protected ItemProductViewHolder2 createItemProductHolder2(ViewGroup parent) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.layout_itemproduct2,parent,false);
        ItemProductViewHolder2 viewHolder = new ItemProductViewHolder2(v);
        return viewHolder;
    }

    protected void bindItemProductView2(final ItemProductModel2 item, ItemProductViewHolder2 holder) {
        holder.setProductImage(item.getProduct_image());
        holder.setProductName(item.getProduct_name());
        holder.setProductPrice(item.getProduct_price());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigator.navigateToItemDetails(activity,new ItemProductModel(item.getProduct_id(),
                        item.getProduct_price(),item.getProduct_name(),item.getProduct_image(),
                        item.getProduct_des(),item.getMore_information(),item.getProduct_amount()));
            }
        });
    }



}
