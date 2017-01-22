package com.example.gitviewer.ui;

import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import com.example.gitviewer.R;
import com.example.gitviewer.controller.GitViewerHomeFeedController;
import com.example.gitviewer.controller.GitViewerHomeFeedController.HomeFeedControllerListener;
import com.example.gitviewer.model.GitViewerUserFeedModel;

import java.util.List;

public class GitViewerHomeFeedActivtiy extends AppCompatActivity {

    private String mUserName;
    private List<GitViewerUserFeedModel> mUserFeedList;
    private GitViewerHomeFeedController mHomeFeedController;

    private RecyclerView mRecyclerView;
    private UserFeedRecyclerAdapter mRecyclerAdapter;

    HomeFeedControllerListener mListener = new HomeFeedControllerListener()
    {
        @Override
        public void onFeedBackEventReceived(List<GitViewerUserFeedModel> userFeedList)
        {
            mUserFeedList = userFeedList;
            initRecyerView();
        }
    } ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobie_fit_home_feed_activtiy);
        mHomeFeedController = new GitViewerHomeFeedController(this,mListener);
        mUserName = getIntent().getStringExtra("user_name");
        //eg: mojombo
        mHomeFeedController.hitUserFeedAPI(mUserName);
        initFloatingButton();
    }

    private void initFloatingButton()
    {
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.fab);


    }

    private void initRecyerView()
    {
        mRecyclerView = (RecyclerView) findViewById(R.id.feedView);
        mRecyclerAdapter = new UserFeedRecyclerAdapter(this,mUserFeedList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mRecyclerAdapter);

    }


}
