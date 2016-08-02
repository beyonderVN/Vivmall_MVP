package com.vinhsang.vivmall.presentation.view.activity.base;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.vinhsang.vivmall.presentation.R;
import com.vinhsang.vivmall.presentation.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long on 7/11/2016.
 */

public abstract class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final String TAG = "BaseAdapter";

    protected static final int TYPE_LOADING_MORE = -1;
    protected static final int TYPE_NO_MORE = -2;

    List<BaseModel> mDataItems = new ArrayList<>();


    public BaseAdapter(List<BaseModel> items) {
        mDataItems = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_LOADING_MORE:
                return createLoadingMoreHolder(parent);
            case TYPE_NO_MORE:
                return createNoMoreHolder(parent);
        }
        return createDataItemHolder(viewType,parent);

    }



    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {

            case TYPE_LOADING_MORE:
                bindLoadingViewHolder((LoadingMoreHolder) holder, position);
                break;
            case TYPE_NO_MORE:
                bindNoMoreViewHolder((NoMoreHolder) holder, position);
                break;
        }

        bindDataItemViewHolder(holder, position);

    }




    public boolean isLoadingMore() {
        return isLoadingMore;
    }

    public void setLoadingMore(boolean loadingMore) {
        this.isLoadingMore = loadingMore;
    }

    boolean isLoadingMore = false;
    int count = 0;

    public void dataStartedLoading() {
        count++;
        Log.d(TAG, "dataStartedLoading: "+count);

        if (isLoadingMore) return;
        isLoadingMore = true;
        notifyItemInserted(getLoadingMoreItemPosition());
    }



    public void dataFinishedLoading() {
        if(!isNoMore()) {
            count--;
            Log.d(TAG, "dataFinishedLoading: "+count);
            if (!isLoadingMore) return;
            final int loadingPos = getLoadingMoreItemPosition();
            isLoadingMore = false;
            notifyItemRemoved(loadingPos);
        }else{
            notifyItemChanged(getLoadingMoreItemPosition());
        }
    }


    protected int getLoadingMoreItemPosition() {
        return isLoadingMore ? getItemCount() - 1 : RecyclerView.NO_POSITION;
    }
    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);

    }


    @Override
    public int getItemCount() {
        return getDataItemCount() + (isLoadingMore ? 1 : 0);
    }

    public int getDataItemCount() {
        return mDataItems.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (position < getDataItemCount()
                && getDataItemCount() > 0) {
            return getDataItemViewType(position);
        }

        if (isNoMore()) {
            return TYPE_NO_MORE;
        } else {
            return TYPE_LOADING_MORE;
        }


    }
    protected abstract boolean isNoMore();

    protected abstract int getDataItemViewType(int position) ;

    protected abstract RecyclerView.ViewHolder createDataItemHolder(int viewType, ViewGroup parent);

    protected abstract void bindDataItemViewHolder(RecyclerView.ViewHolder holder, int position);

    protected BaseModel getItem(int position) {
        return mDataItems.get(position);
    }





    ////////////////////////////////LoadingMoreHolder////////////////////////////////////////////////////////

    /* package */ protected static class LoadingMoreHolder extends RecyclerView.ViewHolder {

        ProgressBar progress;

        public LoadingMoreHolder(View itemView) {
            super(itemView);
            progress = (ProgressBar) itemView;
        }

    }

    protected LoadingMoreHolder createLoadingMoreHolder(ViewGroup parent) {
        return new LoadingMoreHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.infinite_loading, parent, false));
    }

    protected void bindLoadingViewHolder(LoadingMoreHolder holder, int position) {
        // only show the infinite load progress spinner if there are already items in the
        // grid i.e. it's not the first item & data is being loaded
//        holder.progress.setVisibility((position > 0)
//                ? View.VISIBLE : View.INVISIBLE);
        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        layoutParams.setFullSpan(true);
        holder.progress.setVisibility(View.VISIBLE );
    }

    ///////////////////////////////NoMoreHolder/////////////////////////////////////////////////////////

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
