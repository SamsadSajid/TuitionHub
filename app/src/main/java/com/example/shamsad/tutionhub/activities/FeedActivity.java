package com.example.shamsad.tutionhub.activities;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.shamsad.tutionhub.utilities.DrawerUtil;
import com.example.shamsad.tutionhub.models.InfiniteFeedInfo;
import com.example.shamsad.tutionhub.views.ItemView;
import com.example.shamsad.tutionhub.views.LoadMoreView;
import com.example.shamsad.tutionhub.R;
import com.example.shamsad.tutionhub.utilities.JsonUtils;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.mindorks.placeholderview.InfinitePlaceHolderView;

import java.util.ArrayList;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    private InfinitePlaceHolderView mLoadMoreView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        Toolbar toolbar = findViewById(R.id.tbHome);
        toolbar.setTitle(R.string.feed);
        setSupportActionBar(toolbar);

        // Navigation Drawer
        DrawerUtil.getDrawer(this,toolbar);

        mLoadMoreView = (InfinitePlaceHolderView)findViewById(R.id.loadMoreView);

        setupView();
    }

    private void setupView(){

        final List<InfiniteFeedInfo> feedList = JsonUtils.loadInfiniteFeeds(this.getApplicationContext());
        Log.d("DEBUG", "LoadMoreView.LOAD_VIEW_SET_COUNT " + LoadMoreView.LOAD_VIEW_SET_COUNT);
        for(int i = 0; i < LoadMoreView.LOAD_VIEW_SET_COUNT; i++){
            mLoadMoreView.addView(new ItemView(this, feedList.get(i)));
        }
        mLoadMoreView.setLoadMoreResolver(new LoadMoreView(mLoadMoreView, feedList));

//        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                feedList.clear();
//                mLoadMoreView.removeAllViews();
//            }
//        });
    }

}
