package com.example.gitviewer.controller;

import android.content.Context;

import com.example.gitviewer.network.GitViewerNetworkManager;

/**
 * Created by Raveen S on 18-12-2016.
 */

public class GitViewerSignUpControlller
{
    private Context mContext;
    private GitViewerNetworkManager mGitViewerNetworkManager;

    public GitViewerSignUpControlller(Context context)
    {
        mContext = context;
        mGitViewerNetworkManager = new GitViewerNetworkManager(context);
    }

    public void hitSignUpAPI(String userName)
    {
        String url = "https://api.github.com/users/"+userName;

    }
}
