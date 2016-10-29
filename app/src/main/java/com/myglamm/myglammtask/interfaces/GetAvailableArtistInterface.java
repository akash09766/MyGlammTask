package com.myglamm.myglammtask.interfaces;

import com.myglamm.myglammtask.model.ArtistData;

/**
 * Created by Akash Wangalwar on 27-10-2016.
 */
public interface GetAvailableArtistInterface {
    public void onSuccess(ArtistData successResponse);
    public void onfailure(ArtistData failureResponse);
    public void onfailure(String failure);
}
