package com.vinhsang.vivmall.presentation.view.activity.mainactivity.cataloguefragment2.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.vinhsang.vivmall.presentation.R;
import com.vinhsang.vivmall.presentation.model.BaseModel;
import com.vinhsang.vivmall.presentation.model.ItemProductModel;
import com.vinhsang.vivmall.presentation.model.ItemProductModel2;
import com.vinhsang.vivmall.presentation.view.activity.base.BaseAdapter;
import com.vinhsang.vivmall.presentation.view.activity.mainactivity.cataloguefragment2.CataloguePresentationModel2;
import com.vinhsang.vivmall.presentation.view.widget.DynamicHeightImageView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.vinhsang.vivmall.presentation.MainApplication.mContext;

/**
 * Created by Long on 7/11/2016.
 */

public class ItemProductCatalogueAdapter2 extends BaseAdapter{
    private static final String TAG = "ItemProductCatalogu2";

    CataloguePresentationModel2 cataloguePresentationModel;
    protected static final int TYPE_SECTION = BaseModel.ModelType.SECTION;


    @Inject
    public ItemProductCatalogueAdapter2(CataloguePresentationModel2 cataloguePresentationModel){
        super(cataloguePresentationModel.getmBaseModels());
        this.cataloguePresentationModel = cataloguePresentationModel;
    }
    @Override
    protected boolean isNoMore() {
        return cataloguePresentationModel.isNoMore();
    }

    @Override
    protected int getDataItemViewType(int position) {
        return getItem(position).getModelType();
    }

    @Override
    protected RecyclerView.ViewHolder createDataItemHolder(int viewType, ViewGroup parent) {
        switch (viewType) {
            case TYPE_SECTION:
                return createSectionHolder(parent);

        }
        return null;
    }
    public void upDate(){
        super.notifyDataSetChanged();
        for (int i = 0; i < getmDataItems().size(); i++) {
            Log.d(TAG, "upDate: "+i+ " "+ ((SectionDataModel)getmDataItems().get(i)).getAllItemsInSection().size());
        }

    }
    @Override
    protected void bindDataItemViewHolder(RecyclerView.ViewHolder holder,int position) {
        switch (getItemViewType(position)) {
            case TYPE_SECTION:
                bindSectionView((SectionDataModel) getItem(position), (SectionViewHolder) holder);

                break;
        }
    }

    protected SectionViewHolder createSectionHolder(ViewGroup parent) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.layout_section,parent,false);
        SectionViewHolder viewHolder = new SectionViewHolder(v);
        return viewHolder;
    }

    protected void bindSectionView(final SectionDataModel item, SectionViewHolder holder) {
        final String sectionName = item.getHeaderTitle();

        List singleSectionItems = item.getAllItemsInSection();
        Log.d(TAG, "bindSectionView: "+singleSectionItems.size());
        holder.itemTitle.setText(sectionName);

        ItemProductAdapter itemListDataAdapter = new ItemProductAdapter(singleSectionItems);

        holder.recycler_view_list.setHasFixedSize(true);
        holder.recycler_view_list.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.recycler_view_list.setAdapter(itemListDataAdapter);


        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(v.getContext(), "click event on more, "+sectionName , Toast.LENGTH_SHORT).show();



            }
        });
    }

    /* package */ protected static  class SectionViewHolder extends RecyclerView.ViewHolder{

        private static final String TAG = "SectionViewHolder";
        protected TextView itemTitle;

        protected RecyclerView recycler_view_list;

        protected Button btnMore;



        public SectionViewHolder(View view) {
            super(view);
            this.itemTitle = (TextView) view.findViewById(R.id.itemTitle);
            this.recycler_view_list = (RecyclerView) view.findViewById(R.id.recycler_view_list);
            this.btnMore= (Button) view.findViewById(R.id.btnMore);


        }

    }


    public class ItemProductAdapter extends BaseAdapter{
        private static final String TAG = "ItemProductCatalogue";

        protected static final int TYPE_ITEMPRODUCT = BaseModel.ModelType.ITEM_PRODUCT;
        protected static final int TYPE_ITEMPRODUCT2 = BaseModel.ModelType.ITEM_PRODUCT2;


        public ItemProductAdapter(List<BaseModel> baseModels){
            super(baseModels);
        }
        @Override
        protected boolean isNoMore() {
            return cataloguePresentationModel.isNoMore();
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
                    .inflate(R.layout.layout_itemproduct_horizontal,parent,false);
            ItemProductViewHolder viewHolder = new ItemProductViewHolder(v);
            return viewHolder;
        }

        protected void bindItemProductView(final ItemProductModel item, ItemProductViewHolder holder) {
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

        /* package */ protected   class ItemProductViewHolder extends RecyclerView.ViewHolder{

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
                    s ="đ"+s+"k";
                }

                return s;
            }
        }

        /* package */ protected   class ItemProductViewHolder2 extends RecyclerView.ViewHolder{

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
                    .inflate(R.layout.layout_itemproduct_horizontal,parent,false);
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
                    Toast.makeText(view.getContext(), item.getProduct_name(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
