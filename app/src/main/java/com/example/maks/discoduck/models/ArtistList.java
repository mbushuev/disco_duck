package com.example.maks.discoduck.models;


import com.example.maks.discoduck.api.response.ArtistResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArtistList {
    @SerializedName("artist_list")
    private List<ArtistResponse> artistList;

    public List<ArtistResponse> getArtistList() {
        return artistList;
    }
}
