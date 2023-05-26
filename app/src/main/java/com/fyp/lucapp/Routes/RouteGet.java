package com.fyp.lucapp.Routes;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.fyp.lucapp.Helper.GzipRequest;
import com.fyp.lucapp.Interface.InterfaceApi;

public class RouteGet {

    private final Context context;
    private final InterfaceApi interfaceApi;

    public RouteGet(Context context, InterfaceApi interfaceApi) {
        this.context = context;
        this.interfaceApi = interfaceApi;
    }

    public void get(String url) {
        RequestQueue requestQueue = Volley.
                newRequestQueue(context);

        GzipRequest jsonObjectRequest = new GzipRequest(Request.Method.GET,
                url,
                null, interfaceApi::onSuccess,
                interfaceApi::onError);

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }
}
