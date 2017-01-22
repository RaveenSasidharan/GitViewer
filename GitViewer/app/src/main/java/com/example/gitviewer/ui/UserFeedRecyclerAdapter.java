package com.example.gitviewer.ui;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gitviewer.R;
import com.example.gitviewer.model.GitViewerUserFeedModel;

import java.util.List;

/**
 * Created by Raveen S on 18-12-2016.
 */

public class UserFeedRecyclerAdapter extends RecyclerView.Adapter<UserFeedRecyclerAdapter.FeedListViewHolder>
{
    private Context mContext;
    private List<GitViewerUserFeedModel> mUserFeedList;

    public UserFeedRecyclerAdapter(Context context, List<GitViewerUserFeedModel> feedList)
    {
        mContext = context;
        mUserFeedList = feedList;
    }
    @Override
    public FeedListViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_list_item, parent, false);

        return new FeedListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final FeedListViewHolder holder, final int position)
    {
        holder.mRepoType.setText(mUserFeedList.get(position).mRepoName);
        holder.mEventType.setText(mUserFeedList.get(position).mRepoEventDetail);
        holder.mCurrentView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                final Activity activity = (Activity) mContext;
                Intent naviIntent = new Intent(mContext, GitViewerRepoDetailsActivty.class);
                naviIntent.putExtra("repo_link",mUserFeedList.get(position).mRepoURL);
                mContext.startActivity(naviIntent, ActivityOptions.makeSceneTransitionAnimation(activity, new Pair(holder.mProfileImageView,"profileRepoImage")).toBundle());

            }
        });
    }

    @Override
    public int getItemCount()
    {
        if(null == mUserFeedList)   return 0;
        return mUserFeedList.size();
    }


    ///
    //view holder for list item
    ///
    public class FeedListViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView mProfileImageView;
        public TextView mRepoType;
        public TextView mEventType;
        public View mCurrentView;
        public FeedListViewHolder(View view)
        {
            super(view);

            mCurrentView = view;
            mProfileImageView = (ImageView) view.findViewById(R.id.userImg);
            mRepoType         = (TextView) view.findViewById(R.id.repoName);
            mEventType        = (TextView) view.findViewById(R.id.eventType);

        }
    }
}
