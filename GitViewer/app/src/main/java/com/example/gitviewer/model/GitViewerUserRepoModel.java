package com.example.gitviewer.model;

import org.json.JSONObject;

/**
 * Created by Raveen S on 18-12-2016.
 */

public class GitViewerUserRepoModel
{
    public String mName;
    public String mDescription;
    public String mViewCount;
    public String mProfileImageLink;
    public String mStarCount;
    public String mOwnerName;

    public GitViewerUserRepoModel(JSONObject repoJSON)
    {
        mName               = repoJSON.optString("name");
        mDescription        = repoJSON.optString("description");
        mViewCount          = repoJSON.optString("watchers_count");
        mStarCount          = repoJSON.optString("stargazers_count");
        JSONObject owner    = repoJSON.optJSONObject("owner");
        mProfileImageLink   = owner.optString("avatar_url");
        mOwnerName          = owner.optString("login");
    }




}
