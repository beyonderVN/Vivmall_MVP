package com.brothers.admin.kill_some_time.presentation.ui.Main.haivlfragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brothers.admin.kill_some_time.R;
import com.brothers.admin.kill_some_time.domain.Item;
import com.brothers.admin.kill_some_time.presentation.MainApplication;
import com.brothers.admin.kill_some_time.presentation.coremvp.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HaiVL_Fragment extends BaseFragment<HaiVLModel,HaiVlView,HaiVlPresenter> implements HaiVlView {

@BindView(R.id.recylerview)
    RecyclerView recyclerView;
    public HaiVL_Fragment() {
        // Required empty public constructor
    }

    HaiVlAdapter haiVlAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hai_vl_, container, false);
        ButterKnife.bind(this,view);
        haiVlAdapter = new HaiVlAdapter(getContext());
        return view;
    }
    private void setupRecyclerView(){
        recyclerView.setAdapter(haiVlAdapter);
    }

    @Override
    protected void performFieldInection() {
        MainApplication.getMainComponent().inject(this);
    }

    @NonNull
    @Override
    protected HaiVLModel createPresentationModel() {
        return new HaiVLModel();
    }


    public class HaiVlAdapter extends RecyclerView.Adapter<HaiVlAdapter.ViewHolder>{

        List<Item> items = new ArrayList<>();
        Context context;

        public HaiVlAdapter(List<Item> items, Context context) {
            this.items = items;
            this.context = context;
        }

        public HaiVlAdapter(Context context) {
            this.context = context;
        }

        public void setItemList(List<Item> itemList){
            items = itemList;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }
    }


}
