package com.example.maks.discoduck.api.response;


import android.support.annotation.NonNull;

import com.example.maks.discoduck.models.Track;

public class TrackResponse implements Comparable<TrackResponse> {
    private Track track;

    public Track getTrack() {
        return track;
    }

    @Override
    public int compareTo(@NonNull TrackResponse other) {
        return null != this.track && null != other.track ? this.track.getRating() - other.track.getRating() : 0;
    }
}
