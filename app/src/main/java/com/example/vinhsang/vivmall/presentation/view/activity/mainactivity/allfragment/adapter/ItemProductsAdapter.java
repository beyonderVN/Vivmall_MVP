package com.example.vinhsang.vivmall.presentation.view.activity.mainactivity.allfragment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinhsang.vivmall.R;
import com.example.vinhsang.vivmall.presentation.helper.recyclerviewhelper.DynamicHeightImageView;
import com.example.vinhsang.vivmall.domain.ItemProduct;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Long on 7/11/2016.
 */

public class ItemProductsAdapter extends RecyclerView.Adapter<ItemProductsAdapter.ViewHolder>{
    private static final String TAG = "ItemProductsAdapter";
    List<ItemProduct> mItemProducts = new ArrayList<>();



    public ItemProductsAdapter(List<ItemProduct> mItemProducts) {
        this.mItemProducts = mItemProducts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.layout_itemproduct,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    int count = 0;
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ItemProduct itemProduct = mItemProducts.get(position);
        holder.setProductImage(itemProduct.getProduct_image(),position);
        holder.setProductName(itemProduct.getProduct_name());
        holder.setProductPrice(itemProduct.getProduct_price());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), itemProduct.getProduct_name(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);

    }



    @Override
    public int getItemCount() {
        return mItemProducts.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        private static final String TAG = "ViewHolder";
        @BindView(R.id.image_product)
        DynamicHeightImageView imageView;
        @BindView(R.id.product_name)
        TextView productName;
        @BindView(R.id.product_price)
        TextView productPrice;
        Context context;
        public ViewHolder(View itemView) {

            super(itemView);
            Log.d(TAG, "ViewHolder: "+itemView.getMeasuredWidth());
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();

        }
        public void setProductImage(String productImage, int position ){

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
            s = s.substring(0,s.length()-3);
            s ="đ"+s+"k";
            return s;
        }
    }

}
