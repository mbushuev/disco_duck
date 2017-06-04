package com.example.maks.discoduck.models;

import com.example.maks.discoduck.api.response.TrackResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrackList {
    @SerializedName("track_list")
    private List<TrackResponse> trackList;

    public List<TrackResponse> getTrackList() {
        return trackList;
    }
}
