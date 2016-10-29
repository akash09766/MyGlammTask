package com.myglamm.myglammtask.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.myglamm.myglammtask.R;
import com.myglamm.myglammtask.adapter.AvailableArtistAdapter;
import com.myglamm.myglammtask.adapter.ConciergeArtistAdapter;
import com.myglamm.myglammtask.interfaces.GetAvailableArtistInterface;
import com.myglamm.myglammtask.model.ArtistData;
import com.myglamm.myglammtask.model.ConciergeArtist;
import com.myglamm.myglammtask.networking.NetworkManager;
import com.myglamm.myglammtask.networking.Network_Available;
import com.myglamm.myglammtask.util.GConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ArtistActivity extends BaseActivity implements GetAvailableArtistInterface, View.OnClickListener {

    private static final String TAG = ArtistActivity.class.getSimpleName();
    private ProgressDialog mProgressDialog;
    private RecyclerView mArtistList;
    private TextView mErrorMsg;
    private Button mRetryBut;
    private Button mGotoSettins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_artist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.Artist_activity_title));

        setIdsToViews();
        setListenerToViews();
        getData();
    }

    private void setIdsToViews() {
        mErrorMsg = (TextView) findViewById(R.id.error_msg_tv_id);
        mRetryBut = (Button) findViewById(R.id.try_again_but_id);
        mGotoSettins = (Button) findViewById(R.id.setting_but_id);
        mArtistList = (RecyclerView) findViewById(R.id.mArtist_listview_id);
    }

    private void setListenerToViews() {
        mArtistList.setLayoutManager(new LinearLayoutManager(this));

        mRetryBut.setOnClickListener(this);
        mGotoSettins.setOnClickListener(this);
    }

    private void getData() {

        if (Network_Available.hasConnection(this)) {
            mGotoSettins.setVisibility(View.GONE);
            enableRetyView(false, "");
        } else {
            enableRetyView(true, getString(R.string.ntw_unvailable_msg));
            mGotoSettins.setVisibility(View.VISIBLE);
            return;
        }

        JSONArray serviceIds = new JSONArray();
        serviceIds.put(41);
        serviceIds.put(42);
        serviceIds.put(53);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = calendar.getTime();

        String tommDate = dateFormat.format(tomorrow);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("pinCode", "400015"); // can be get via google maps api (as of now i am hardcoding it )
            jsonObject.accumulate("latitude", "18.9927476"); // can be get via google maps api (as of now i am hardcoding it )
            jsonObject.accumulate("longitude", "72.8310679"); // can be get via google maps api (as of now i am hardcoding it )
//            jsonObject.accumulate("date", "2016-10-31 16:30:00");
            jsonObject.accumulate("date",tommDate); // always will return tommorow , just to avoid api bad resonse
            jsonObject.accumulate("gender", "1"); // can be retrived depend on the gender of User (get from profile data)
            jsonObject.accumulate("serviceIds", serviceIds);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        mProgressDialog = getProgressDialog("Loading....");
        mProgressDialog.show();
        new NetworkManager(this).getAvailableArtist(jsonObject, this);
    }

    @Override
    public void onSuccess(ArtistData successResponse) {
        mProgressDialog.dismiss();
        enableRetyView(false, "");

        if (successResponse != null && successResponse.getCode().equalsIgnoreCase(GConstants.BAD_REQUEST)) {
            showLongSnackBar(successResponse.getMessage());
            return;
        }
        if (successResponse != null &&
                successResponse.getCode().equalsIgnoreCase(GConstants.VALID_REQUEST)) {
            if(successResponse.isConciergeMode()){

                ArrayList<ConciergeArtist> list =  new ArrayList<>();
                        list.add(successResponse.getConciergeArtists());
                mArtistList.setAdapter(new ConciergeArtistAdapter(this,list));
            }else{
                mArtistList.setAdapter(new AvailableArtistAdapter(this,
                        successResponse.getAvailableArtists()));
            }
            return;
        }
    }

    @Override
    public void onfailure(ArtistData failureResponse) {
        mProgressDialog.dismiss();
        enableRetyView(true, getString(R.string.retry_msg));
        if (failureResponse != null && failureResponse.getCode().equalsIgnoreCase(GConstants.BAD_REQUEST)) {

            showLongSnackBar(failureResponse.getMessage());
        }
    }

    @Override
    public void onfailure(String failure) {
        mProgressDialog.dismiss();
        enableRetyView(true, getString(R.string.retry_msg));

        if (failure != null && failure.isEmpty()) {

            showLongSnackBar(getResources().getString(R.string.networking_error_msg));
        }
    }

    @Override
    public void onClick(View view) {

        if (view == mRetryBut) {
            getData();
            return;
        }

        if (view == mGotoSettins) {
            moveTONetworkPage();
            return;
        }
    }

    private void enableRetyView(boolean status, String msg) {

        if (status) {
            mErrorMsg.setVisibility(View.VISIBLE);
            mErrorMsg.setText(msg);
            mRetryBut.setVisibility(View.VISIBLE);
        } else {
            mErrorMsg.setVisibility(View.GONE);
            mRetryBut.setVisibility(View.GONE);
        }
    }

    private void moveTONetworkPage() {
        startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
