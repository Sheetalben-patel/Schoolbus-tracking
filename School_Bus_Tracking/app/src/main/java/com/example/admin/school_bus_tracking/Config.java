package com.example.admin.school_bus_tracking;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Admin on 19/03/2017.
 */
public class Config {
    private static Config mInstance;
    private RequestQueue reqQue;
    private static Context mCtx;

    private Config(Context context) {
        this.mCtx = context;
        reqQue = getReqQue();
    }

    public RequestQueue getReqQue() {
        if (reqQue == null) {
            reqQue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return reqQue;
    }

    public static synchronized Config getmInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Config(context);
        }
        return mInstance;
    }

    public <T> void addReq(Request<T> request) {
        reqQue.add(request);
    }
}



