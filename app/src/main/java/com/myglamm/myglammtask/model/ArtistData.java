package com.myglamm.myglammtask.model;

import java.util.ArrayList;

/**
 * Created by Akash Wangalwar on 27-10-2016.
 */
public class ArtistData {

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isConciergeMode() {
        return conciergeMode;
    }

    public void setConciergeMode(boolean conciergeMode) {
        this.conciergeMode = conciergeMode;
    }

    public boolean isAutoAssignArtistMode() {
        return autoAssignArtistMode;
    }

    public void setAutoAssignArtistMode(boolean autoAssignArtistMode) {
        this.autoAssignArtistMode = autoAssignArtistMode;
    }

    public int getSearchResultCount() {
        return searchResultCount;
    }

    public void setSearchResultCount(int searchResultCount) {
        this.searchResultCount = searchResultCount;
    }

    public ArrayList<AvailableArtists> getAvailableArtists() {
        return availableArtists;
    }

    public void setAvailableArtists(ArrayList<AvailableArtists> availableArtists) {
        this.availableArtists = availableArtists;
    }

    public ConciergeArtist getConciergeArtists() {
        return conciergeArtist;
    }

    public void setConciergeArtists(ConciergeArtist conciergeArtists) {
        this.conciergeArtist = conciergeArtists;
    }

    private String code;
    private String message;
    private boolean conciergeMode;
    private boolean autoAssignArtistMode;
    private int searchResultCount;
    private ArrayList<AvailableArtists> availableArtists;
    private ConciergeArtist conciergeArtist;
}
