package com.example.user.fypdemo;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Eamon on 21/11/2017.
 */

public class RequestQueueInstance {
    private static RequestQueueInstance instance;
    private RequestQueue requestQueue;
    private static Context context;

    private RequestQueueInstance(Context context) {
        RequestQueueInstance.context = context;
        requestQueue = getRequestQueue();

    }

    public static synchronized RequestQueueInstance getInstance(Context context) {

        if (instance == null) {

            instance = new RequestQueueInstance(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {

        getRequestQueue().add(request);
    }

}