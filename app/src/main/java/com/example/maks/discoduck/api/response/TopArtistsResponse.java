package com.example.maks.discoduck.api.response;

import com.example.maks.discoduck.models.ArtistList;

import java.util.List;

public class TopArtistsResponse extends AbstractResponse<ArtistList> {
    public List<ArtistResponse> getArtistList() {
        return isValidArtistList() ? getMessage().getBody().getArtistList() : null;
    }

    private boolean isValidArtistList() {
        return null != getMessage() && null != getMessage().getBody() && null != getMessage().getBody().getArtistList();
    }
}
