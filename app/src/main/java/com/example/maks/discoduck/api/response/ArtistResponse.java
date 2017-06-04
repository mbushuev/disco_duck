package com.example.maks.discoduck.api.response;


import android.support.annotation.NonNull;

import com.example.maks.discoduck.models.Artist;

public class ArtistResponse implements Comparable<ArtistResponse> {
    private Artist artist;

    public Artist getArtist() {
        return artist;
    }

    @Override
    public int compareTo(@NonNull ArtistResponse other) {
        return null != this.artist && null != other.artist ? this.artist.getRating() - other.artist.getRating() : 0;
    }
}
