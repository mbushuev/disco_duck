package com.example.maks.discoduck.models;


import com.example.maks.discoduck.api.response.TrackResponse;

import java.util.ArrayList;
import java.util.List;

public class TopArtistTracks {
    private Artist artist;
    private List<Track> trackList;
    private List<CatalogItem> catalogFormatList;

    public TopArtistTracks(Artist artist) {
        this.artist = artist;
    }

    public List<CatalogItem> getCatalogFormatList() {
        if (null == catalogFormatList) {
            catalogFormatList = new ArrayList<>();
            if (null != artist) {
                catalogFormatList.add(artist);

                if (null != trackList && !trackList.isEmpty()) {
                    catalogFormatList.addAll(trackList);
                }
            }
        }

        return catalogFormatList;
    }

    public void setOwnTracks(List<TrackResponse> topTracks) {
        if (null != artist && null != topTracks) {
            clearTrackList();

            int trackPosition = 1;
            for (TrackResponse trackResponse : topTracks) {
                if (null != trackResponse && null != trackResponse.getTrack() && trackResponse.getTrack().getArtistId() == artist.getId()) {
                    trackResponse.getTrack().setPosition(trackPosition);
                    trackPosition++;
                    trackList.add(trackResponse.getTrack());
                }
            }
        }
    }

    private void clearTrackList() {
        if (null == trackList) {
            trackList = new ArrayList<>();
        } else {
            trackList.clear();
        }
    }

    public void setArtistPosition(int artistPosition) {
        if (null != artist) {
            artist.setPosition(artistPosition);
        }
    }
}
