package com.example.gitviewer.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Raveen S on 18-12-2016.
 */

public class GitViewerUserFeedModel
{
    public String mRepoName;
    public String mRepoEventDetail;
    public String mRepoURL;

    public GitViewerUserFeedModel(JSONObject repoObj)
    {
        mRepoName   =   repoObj.optString("name");
        mRepoURL    =   repoObj.optString("url");
    }

}
