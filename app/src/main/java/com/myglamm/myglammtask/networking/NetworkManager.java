package com.myglamm.myglammtask.networking;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.myglamm.myglammtask.config.MyGlammConfig;
import com.myglamm.myglammtask.interfaces.GetAvailableArtistInterface;
import com.myglamm.myglammtask.model.ArtistData;
import com.myglamm.myglammtask.util.GConstants;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import static android.R.attr.duration;

/**
 * Created by Akash Wangalwar on 27-10-2016.
 */
public class NetworkManager {

    private static final String TAG = NetworkManager.class.getSimpleName();
    private static final int TIMEOUT = 60000;
    private final Gson gson;
    private final Context mContext;
    private final AsyncHttpClient mAsynHttp;

    public NetworkManager(Context context) {
        mContext = context;
        mAsynHttp = new AsyncHttpClient();
        mAsynHttp.setTimeout(TIMEOUT);
        mAsynHttp.addHeader("Content-Type","application/json");
        gson = new Gson();
    }

    public void getAvailableArtist(JSONObject payLoad,
                                   final GetAvailableArtistInterface artistInterface) {

        try {
            //below parameter should be user as header for every api .
            payLoad.accumulate("deviceId",getMacAddress());
            payLoad.accumulate("appVersion",getAppVersion());
            payLoad.accumulate("deviceType", GConstants.DEVICE_TYPE);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringEntity entity = null;
        try {
            entity = new StringEntity(payLoad.toString());
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Log.d(TAG, "getAvailableArtist() : " + payLoad.toString());
       mAsynHttp.post(mContext, MyGlammConfig.AVAILABLE_ARTIST,
                entity, "application/json", new HttpResponseHandler(mContext) {
                    @Override
                    public void onSuccess(int statusCode, String response) {

                        Log.d(TAG, "onSuccess: Response : "+response);
                        try {
                            ArtistData successResponse = gson.fromJson(response,
                                    ArtistData.class);
                            artistInterface.onSuccess(successResponse);

                        } catch (Exception e) {
                            // TODO: handle exception
                            Log.e(TAG, "getAvailableArtist() error response :" + e.getMessage());
                            artistInterface.onfailure("");
                        }
                    }

                    @Override
                    public void onFailure(Throwable e, String response) {
                        super.onFailure(e, response);
                        Log.e(TAG, "getAvailableArtist() error response :" + response);

                        try {
                            ArtistData failureResponse = gson.fromJson(response,
                                    ArtistData.class);
                            artistInterface.onfailure(failureResponse);
                        } catch (Exception e1) {
                            // TODO: handle exception
                            Log.e(TAG, "getAvailableArtist() error response :" + e1.getMessage());
                            artistInterface.onfailure("");
                        }
                    }
                });
    }



    private String getAppVersion() {
        PackageInfo pInfo = null;
        try {
            pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = pInfo.versionName;
        Log.d("App_User_Agent", "getAppVersion() : version : " + version);

        return version;
    }

    private String getMacAddress() {
        WifiManager wifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wInfo = wifiManager.getConnectionInfo();
        String macAddress = wInfo.getMacAddress();
        Log.d(TAG, "getMacAddress() MAC ADDRESS :" + macAddress);
        return macAddress;
    }
}
