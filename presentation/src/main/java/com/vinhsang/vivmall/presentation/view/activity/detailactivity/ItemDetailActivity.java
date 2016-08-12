package com.vinhsang.vivmall.presentation.view.activity.detailactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.vinhsang.vivmall.presentation.R;
import com.vinhsang.vivmall.presentation.model.ItemProductModel;
import com.vinhsang.vivmall.presentation.view.activity.detailactivity.adapter.ItemDetailAdapter;
import com.vinhsang.vivmall.presentation.view.widget.ElasticDragDismissFrameLayout;
import com.vinhsang.vivmall.presentation.view.widget.viewpager.viewpager.InkPageIndicator;
import com.vinhsang.vivmall.presentation.view.widget.viewpager.viewpager.ModelPagerAdapter;
import com.vinhsang.vivmall.presentation.view.widget.viewpager.viewpager.PagerModelManager;
import com.vinhsang.vivmall.presentation.view.widget.viewpager.viewpager.ScrollerViewPager;

import java.util.ArrayList;
import java.util.List;

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
    ItemDetailAdapter itemDetalAdapter;
    ItemProductModel itemProductModel;
    @BindView(R.id.view_pager)
    ScrollerViewPager viewPager;
    @BindView(R.id.back)
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        ButterKnife.bind(this);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");


        chromeFader = new ElasticDragDismissFrameLayout.SystemChromeFader(this);
        draggableFrame.addListener(
                new ElasticDragDismissFrameLayout.SystemChromeFader(this) {
                    @Override
                    public void onDragDismissed() {
                        supportFinishAfterTransition();
                    }
                });

        //getSupportActionBar().hide();

        itemProductModel = (ItemProductModel)getIntent().getSerializableExtra("ITEM_ID");
        Log.d(TAG, "onCreate: itemProductModel.getProduct_id: "+itemProductModel.getProduct_id());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        itemDetalAdapter = new ItemDetailAdapter(this,itemProductModel);
        recyclerView.setAdapter(itemDetalAdapter);
        itemDetalAdapter.init();
        recyclerView.setNestedScrollingEnabled(false);

        ////
        PagerModelManager manager = new PagerModelManager();
        manager.addCommonFragment(GuideFragment.class, getBgRes(), getTitles());
        ModelPagerAdapter adapter = new ModelPagerAdapter(getSupportFragmentManager(), manager);
        viewPager.setAdapter(adapter);
        viewPager.fixScrollSpeed();


        InkPageIndicator springIndicator = (InkPageIndicator) findViewById(R.id.indicator);
        // just set viewPager
        springIndicator.setViewPager(viewPager);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                supportFinishAfterTransition();
            }
        });
    }

    private List<String> getTitles(){
        ArrayList<String> list = new ArrayList<String>();
        return list;
    }

    private List<String> getBgRes(){
        ArrayList<String> list = new ArrayList<>();
        list.add(itemProductModel.getProduct_image());
        list.add(itemProductModel.getProduct_image());
        list.add(itemProductModel.getProduct_image());
        list.add(itemProductModel.getProduct_image());
        return list;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        switch (id){
            case R.id.action_cart :
                Toast.makeText(this, "R.id.action_cart", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_search:
                Toast.makeText(this, "R.id.action_search", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_favorite :
                Toast.makeText(this, "R.id.action_favorite", Toast.LENGTH_SHORT).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
