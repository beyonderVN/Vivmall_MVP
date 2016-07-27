package com.vinhsang.vivmall.presentation.view.activity.mainactivity.allfragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewAnimator;

import com.vinhsang.vivmall.presentation.MainApplication;
import com.vinhsang.vivmall.presentation.R;
import com.vinhsang.vivmall.presentation.view.activity.base.BaseFragment;
import com.vinhsang.vivmall.presentation.view.activity.mainactivity.allfragment.adapter.ItemProductsAdapter;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;


public class AllFragment extends BaseFragment<AllPresentationModel, AllView, AllPresenter>
        implements AllView {
    private static final String TAG = "AllFragment";
    private static final int POSITION_CONTENT_VIEW  = 0;
    private static final int POSITION_PROGRESS_VIEW = 1;

    private static final String ARG_POSITION = "position";


    View view;
    @BindView(R.id.recycler_detail)
    RecyclerView recyclerView;
    @BindInt(R.integer.column_num)
        int columnNum;
    @BindView(R.id.viewAnimator)
    ViewAnimator resultAnimator;
    ItemProductsAdapter itemProductsAdapter = new ItemProductsAdapter();

    public static AllFragment newInstance(int position) {
        AllFragment f = new AllFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    public static AllFragment newInstance() {
        AllFragment f = new AllFragment();

        return f;
    }

    public AllFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_main, container, false);
            ButterKnife.bind(this, view);
            setupRecyclerView(recyclerView);

        }

        return view;

    }
void setupRecyclerView(RecyclerView recyclerView){
    recyclerView.setAdapter(itemProductsAdapter);
    final StaggeredGridLayoutManager staggeredGridLayoutManagerVertical =
            new StaggeredGridLayoutManager(
                    columnNum, //The number of Columns in the grid
                    LinearLayoutManager.VERTICAL);
    staggeredGridLayoutManagerVertical.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
    //staggeredGridLayoutManagerVertical.invalidateSpanAssignments();
    RecyclerView.LayoutManager layoutGridManager = new GridLayoutManager(getActivity(), columnNum);
    layoutGridManager.setMeasurementCacheEnabled(true);

    recyclerView.setLayoutManager(staggeredGridLayoutManagerVertical);
    recyclerView.setHasFixedSize(true);
    RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
    itemAnimator.setAddDuration(3000);
    itemAnimator.setRemoveDuration(3000);
    recyclerView.setItemAnimator(itemAnimator);


    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
        // The minimum amount of items to have below your current scroll position
        // before loading more.
        private int visibleThreshold = 5;
        // The current offset index of data you have loaded
        private int currentPage = 0;
        // The total number of items in the dataset after the last load
        private int previousTotalItemCount = 0;
        // True if we are still waiting for the last set of data to load.
        private boolean loading = true;
        // Sets the starting page index
        private int startingPageIndex = 0;
        RecyclerView.LayoutManager mLayoutManager = staggeredGridLayoutManagerVertical;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int lastVisibleItemPosition = 0;
            int totalItemCount = mLayoutManager.getItemCount();
            int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) mLayoutManager).findLastVisibleItemPositions(null);
            // get maximum element within the list
            lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions);
            if (totalItemCount < previousTotalItemCount) {
                this.currentPage = this.startingPageIndex;
                this.previousTotalItemCount = totalItemCount;
                if (totalItemCount == 0) {
                    this.loading = true;
                }
            }
            // If it’s still loading, we check to see if the dataset count has
            // changed, if so we conclude it has finished loading and update the current page
            // number and total item count.
            if (loading && (totalItemCount > previousTotalItemCount)) {
                loading = false;
                previousTotalItemCount = totalItemCount;
            }

            // If it isn’t currently loading, we check to see if we have breached
            // the visibleThreshold and need to reload more data.
            // If we do need to reload some more data, we execute onUpdate to fetch the data.
            // threshold should reflect how many total columns there are too
            if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
                currentPage++;
                onLoadMore();
                loading = true;
            }
        }

        private void onLoadMore() {
            try {
                presenter.loadMore();
            } catch (Exception e) {
                e.getStackTrace();
            }

        }

        public int getLastVisibleItem(int[] lastVisibleItemPositions) {
            int maxSize = 0;
            for (int i = 0; i < lastVisibleItemPositions.length; i++) {
                if (i == 0) {
                    maxSize = lastVisibleItemPositions[i];
                } else if (lastVisibleItemPositions[i] > maxSize) {
                    maxSize = lastVisibleItemPositions[i];
                }
            }
            return maxSize;
        }
    });
}
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void showProgress() {
        resultAnimator.setDisplayedChild(POSITION_PROGRESS_VIEW);
    }

    @Override
    public void showContent() {
        itemProductsAdapter.setmItemProducts(presenter.getPresentationModel().getmItemProducts());
        itemProductsAdapter.notifyDataSetChanged();
        resultAnimator.setDisplayedChild(POSITION_CONTENT_VIEW);

    }
    @Override
    public void onUpdate() {
        itemProductsAdapter.dataFinishedLoading();
        itemProductsAdapter.notifyDataSetChanged();
    }
    @Override
    public void onConnected() {

    }

    @Override
    public void onDisconnected() {

    }

    @Override
    public void onFetchError() {

    }



    @Override
    public void showLoadingMore() {
        itemProductsAdapter.dataStartedLoading();
    }

    @Override
    protected void performFieldInection() {
        MainApplication.getMainComponent().inject(this);
    }

    @NonNull
    @Override
    protected AllPresentationModel createPresentationModel() {
        return new AllPresentationModel("All");
    }

    @Override
    public void onDestroyView() {
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }

        super.onDestroyView();
    }


}
