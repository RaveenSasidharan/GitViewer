package com.example.gitviewer.controller;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.example.gitviewer.model.GitViewerUserFeedModel;
import com.example.gitviewer.network.GitViewerNetworkManager;
import com.example.gitviewer.network.GitViewerNetworkStatusCode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raveen S on 18-12-2016.
 */

public class GitViewerHomeFeedController
{
    public interface HomeFeedControllerListener
    {
        void onFeedBackEventReceived(List<GitViewerUserFeedModel> userFeedList);
    }
    private Context mContext;
    private List<GitViewerUserFeedModel> mUserFeedList;
    private GitViewerNetworkManager mGitViewerNetworkManager;
    private HomeFeedControllerListener mListener;

    public GitViewerHomeFeedController(Context context, HomeFeedControllerListener listener)
    {
        mContext = context;
        mGitViewerNetworkManager = new GitViewerNetworkManager(context);
        mUserFeedList = new ArrayList<>();
        mListener = listener;
    }

    public void hitUserFeedAPI(String userName)
    {
        String url = "https://api.github.com/users/"+userName+"/events";
        mGitViewerNetworkManager.makeJSonArrayRequest(Request.Method.GET, url, null, new GitViewerNetworkManager.CallerAdNetworkManagerListener() {
            @Override
            public void onRequestStatusReceived(int statusCode, String response)
            {
                if(GitViewerNetworkStatusCode.OK == statusCode)
                {
                    try
                    {
                        Log.e("check","befor json array");
                        JSONArray feedArray = new JSONArray(response);
                        JSONObject currentObj;
                        JSONObject repoObect ;

                        Log.e("check","befor for loop");
                        Log.e("check","feedback length"+feedArray.length());


                        for (int index = 0; index < feedArray.length(); index++)
                        {
                            currentObj = feedArray.getJSONObject(index);
                            repoObect  = currentObj.optJSONObject("repo");
                            GitViewerUserFeedModel feedModel = new GitViewerUserFeedModel(repoObect);
                            feedModel.mRepoEventDetail = currentObj.optString("type");
                            mUserFeedList.add(feedModel);

                        }
                        Log.e("check","feedback list length"+mUserFeedList.size());

                        Log.e("check","end for loop");

                        if(null != mListener)
                        {
                            mListener.onFeedBackEventReceived(mUserFeedList);
                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }

            }
        });

    }
}
