package com.example.gitviewer.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.gitviewer.GitViewerApplication;
import com.example.gitviewer.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by Raveen S on 17-12-2016.
 */

public class GitViewerNetworkManager
{
    public interface CallerAdNetworkManagerListener
    {
        void onRequestStatusReceived(int statusCode, String response);
    }

    private CallerAdNetworkManagerListener mListener;
    private Context mContext;

    public GitViewerNetworkManager(Context context)
    {
        mContext = context;
    }

    public void makeJSonObjectRequest(int method, String url, JSONObject reqObject, final CallerAdNetworkManagerListener listener)
    {
       // mListener = listener;

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(method,url, reqObject,
                new Response.Listener<JSONObject>()
                {

                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Log.d(TAG, response.toString());
                        listener.onRequestStatusReceived(GitViewerNetworkStatusCode.OK,response.toString());

                    }
                },
                new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        Log.e("signup","error is "+error.getMessage());
                        listener.onRequestStatusReceived(GitViewerNetworkStatusCode.ERROR, null);

                    }
                });
//        {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json");
//                headers.put("Authorization",mContext.getResources().getString(R.string.git_client_accesstoken));
//
//                return headers;
//            }
//        };

        //set retry policy
        int socketTimeout = 60000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjReq.setRetryPolicy(policy);

        RequestQueue requestQueue = GitViewerApplication.getInstance().getmRequestQueue(mContext);
        requestQueue.add(jsonObjReq);

    }


    public void makeJSonArrayRequest(int method, String url, JSONArray reqObject, final CallerAdNetworkManagerListener listener)
    {
        // mListener = listener;

        JsonArrayRequest jsonObjReq = new JsonArrayRequest(method,url, reqObject,
                new Response.Listener<JSONArray>()
                {

                    @Override
                    public void onResponse(JSONArray response)
                    {
                        Log.d(TAG, response.toString());
                        listener.onRequestStatusReceived(GitViewerNetworkStatusCode.OK,response.toString());

                    }
                },
                new Response.ErrorListener()
                {

                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        Log.e("signup","error is "+error.getMessage());
                        listener.onRequestStatusReceived(GitViewerNetworkStatusCode.ERROR, null);

                    }
                });
//        {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json");
//                headers.put("Authorization",mContext.getResources().getString(R.string.git_client_accesstoken));
//
//                return headers;
//            }
//        };

        //set retry policy
        int socketTimeout = 60000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjReq.setRetryPolicy(policy);

        RequestQueue requestQueue = GitViewerApplication.getInstance().getmRequestQueue(mContext);
        requestQueue.add(jsonObjReq);

    }
}
