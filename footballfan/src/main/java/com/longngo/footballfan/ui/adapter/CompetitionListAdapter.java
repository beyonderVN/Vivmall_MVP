package com.longngo.footballfan.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.longngo.footballfan.ui.adapter.viewholder.BaseViewHolder;
import com.longngo.footballfan.ui.viewmodel.vmfactory.TypeFactoryForListVM;
import com.longngo.footballfan.ui.viewmodel.vmfactory.VMTypeFactory;
import com.longngo.footballfan.ui.viewmodel.vmfactory.Visitable;

import java.util.List;

/**
 * Created by Long on 10/5/2016.
 */

public class CompetitionListAdapter extends RecyclerView.Adapter<BaseViewHolder<Visitable>> {
    private VMTypeFactory vmTypeFactory = new TypeFactoryForListVM();
    List<Visitable> visitables;

    public CompetitionListAdapter(List<Visitable> visitables) {
        this.visitables = visitables;
    }



    @Override
    public BaseViewHolder<Visitable> onCreateViewHolder(ViewGroup parent, int viewType) {
        if (parent != null) {
            View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
            return vmTypeFactory.createHolder(viewType, view);
        }
        throw new RuntimeException("Parent is null");
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<Visitable> holder, int position) {
        if(holder!=null){
            holder.bind(visitables.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return visitables.size();
    }

    @Override
    public int getItemViewType(int position) {
        return visitables.get(position).getVMType(vmTypeFactory);
    }
}
