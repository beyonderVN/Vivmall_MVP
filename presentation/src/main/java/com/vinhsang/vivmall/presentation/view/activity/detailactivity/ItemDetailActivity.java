package com.vinhsang.vivmall.presentation.view.activity.detailactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.vinhsang.vivmall.presentation.R;
import com.vinhsang.vivmall.presentation.model.ItemProductModel;
import com.vinhsang.vivmall.presentation.view.activity.detailactivity.adapter.ItemDetalAdapter;
import com.vinhsang.vivmall.presentation.widget.ElasticDragDismissFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemDetailActivity extends AppCompatActivity {
    private static final String TAG = "ItemDetailActivity";
    private static final String ITEM_ID = "ITEM_ID";
    @BindView(R.id.comments_container)
    ElasticDragDismissFrameLayout draggableFrame;
    private ElasticDragDismissFrameLayout.SystemChromeFader chromeFader;
    @BindView(R.id.container)
    RecyclerView recyclerView;
    ItemDetalAdapter itemDetalAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ButterKnife.bind(this);
        chromeFader = new ElasticDragDismissFrameLayout.SystemChromeFader(this);
        draggableFrame.addListener(
                new ElasticDragDismissFrameLayout.SystemChromeFader(this) {
                    @Override
                    public void onDragDismissed() {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            finishAfterTransition();
                        }
                    }
                });

        getSupportActionBar().hide();

        ///
        ItemProductModel itemProductModel = (ItemProductModel)getIntent().getSerializableExtra("ITEM_ID");
        Log.d(TAG, "onCreate: itemProductModel.getProduct_id: "+itemProductModel.getProduct_id());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        itemDetalAdapter = new ItemDetalAdapter(this,itemProductModel);
        recyclerView.setAdapter(itemDetalAdapter);
        itemDetalAdapter.init();
    }



    public static Intent getCallingIntent(Context context, ItemProductModel itemId) {
        Intent intent = new Intent(context, ItemDetailActivity.class);
        intent.putExtra(ITEM_ID,itemId);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // clean up after any fab expansion
        draggableFrame.addListener(chromeFader);
    }
    @Override
    protected void onPause() {
        draggableFrame.removeListener(chromeFader);
        super.onPause();
    }



}
