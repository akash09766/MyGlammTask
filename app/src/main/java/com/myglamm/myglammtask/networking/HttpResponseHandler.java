package com.myglamm.myglammtask.networking;

import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by Akash Wangalwar on 27-10-2016.
 */
public class HttpResponseHandler extends AsyncHttpResponseHandler {

    private boolean abort;
    private Context mContext;

    public boolean isAbort() {
        return abort;
    }

    public void setAbort(boolean abort) {
        this.abort = abort;
    }

    public HttpResponseHandler(Context mContext) {
        super();
        this.mContext = mContext;
    }

    public HttpResponseHandler() {
        super();
    }

    @Override
    public void onFailure(Throwable e, String message) {
        super.onFailure(e, message);
        if (mContext != null) {
            if (Network_Available.hasConnection(mContext)) {
                //	Toast.makeText(mContext, "Your Internet connection is too slow", 500).show();
            } else {
                Toast.makeText(mContext, "No Internet connection", Toast.LENGTH_LONG).show();
            }
        }
    }

}

