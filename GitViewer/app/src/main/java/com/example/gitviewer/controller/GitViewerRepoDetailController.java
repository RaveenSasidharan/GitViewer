package com.example.gitviewer.controller;

import android.content.Context;

import com.android.volley.Request;
import com.example.gitviewer.model.GitViewerUserFeedModel;
import com.example.gitviewer.model.GitViewerUserRepoModel;
import com.example.gitviewer.network.GitViewerNetworkManager;
import com.example.gitviewer.network.GitViewerNetworkStatusCode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Raveen S on 18-12-2016.
 */

public class GitViewerRepoDetailController
{
    public interface RepoDetailControllerListener
    {
        void onRepoDetailReceived(GitViewerUserRepoModel repo);
    }

    private Context mContext;
    private GitViewerNetworkManager mGitViewerNetworkManager;
    private RepoDetailControllerListener mListener;


    public GitViewerRepoDetailController(Context context, RepoDetailControllerListener listener)
    {
        mContext = context;
        mGitViewerNetworkManager = new GitViewerNetworkManager(context);
        mListener = listener;
    }

    public void hitRepoDetailApi(String repoUrl)
    {
        mGitViewerNetworkManager.makeJSonObjectRequest(Request.Method.GET, repoUrl, null, new GitViewerNetworkManager.CallerAdNetworkManagerListener() {
            @Override
            public void onRequestStatusReceived(int statusCode, String response)
            {
                JSONObject respoJsonObject = null;

                if(GitViewerNetworkStatusCode.OK == statusCode)
                {
                    try
                    {
                        respoJsonObject = new JSONObject(response);

                    }
                    catch (JSONException jse)
                    {
                        jse.printStackTrace();
                    }
                    GitViewerUserRepoModel repoModel = new GitViewerUserRepoModel(respoJsonObject);

                    if(null != mListener)
                    {
                        mListener.onRepoDetailReceived(repoModel);
                    }

                }

            }
        });

    }
}
