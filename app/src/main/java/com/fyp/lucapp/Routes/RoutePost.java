package com.fyp.lucapp.Routes;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.fyp.lucapp.Helper.GzipRequest;
import com.fyp.lucapp.Interface.InterfaceApi;

import org.json.JSONObject;

public class RoutePost {

    private final Context context;
    private final InterfaceApi interfaceApi;

    public RoutePost(Context context, InterfaceApi interfaceApi) {
        this.context = context;
        this.interfaceApi = interfaceApi;
    }

    public void post(String url, JSONObject jsonObject) {
        RequestQueue requestQueue = Volley.
                newRequestQueue(context);

        GzipRequest jsonObjectRequest = new GzipRequest(Request.Method.POST,
                url,
                jsonObject, interfaceApi::onSuccess,
                interfaceApi::onError);

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }
}
