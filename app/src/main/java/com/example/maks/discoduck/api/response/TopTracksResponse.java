package com.example.maks.discoduck.api.response;

import com.example.maks.discoduck.models.TrackList;

import java.util.List;

public class TopTracksResponse extends AbstractResponse<TrackList> {
    public List<TrackResponse> getTracks() {
        return isValidTrackList() ? getMessage().getBody().getTrackList() : null;
    }

    private boolean isValidTrackList() {
        return null != getMessage() && null != getMessage().getBody() && null != getMessage().getBody().getTrackList();
    }
}
