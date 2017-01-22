package com.example.gitviewer;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Raveen S on 18-12-2016.
 */

public class GitViewerApplication extends Application
{
    private static GitViewerApplication instance;
    private RequestQueue mRequestQueue;
    @Override
    public void onCreate()
    {
        super.onCreate();

    }

    public static GitViewerApplication getInstance()
    {
        if(instance==null)
        {
            instance=new GitViewerApplication();
        }
        return instance;

    }


    public RequestQueue getmRequestQueue(Context context)
    {
        if(null == mRequestQueue)
        {
            mRequestQueue = Volley.newRequestQueue(context);
        }

        return mRequestQueue;
    }

}
