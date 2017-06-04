package com.example.maks.discoduck.utils;


import android.support.annotation.NonNull;

import com.example.maks.discoduck.api.response.TopArtistsResponse;
import com.example.maks.discoduck.api.response.TopTracksResponse;
import com.example.maks.discoduck.api.response.ArtistResponse;
import com.example.maks.discoduck.models.CatalogItem;
import com.example.maks.discoduck.models.TopArtistTracks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum DataManager {
    INSTANCE;

    private List<CatalogItem> catalogItems;

    private TopArtistsResponse topArtists;
    private TopTracksResponse topTracks;

    public void getData(DataListener<List<CatalogItem>> listener) {
        if (null != listener) {
            if (null != catalogItems) {
                listener.onSuccess(catalogItems);
            } else {
                requestNewDataFromServer(listener);
            }
        }
    }

    public void requestNewDataFromServer(final DataListener<List<CatalogItem>> getDataListener) {
        if (null != getDataListener) {
            requestArtistData(getDataListener);
        }
    }

    private void requestArtistData(final DataListener<List<CatalogItem>> getDataListener) {
        NetworkManager.getTopArtists(new DataListener<TopArtistsResponse>() {
            @Override
            public void onSuccess(TopArtistsResponse data) {
                if (null != data && null != data.getArtistList()) {
                    Collections.sort(data.getArtistList());
                    topArtists = data;
                    requestTrackData(getDataListener);
                } else {
                    getDataListener.onError();
                }
            }

            @Override
            public void onError() {
                getDataListener.onError();
            }
        });
    }

    private void requestTrackData(final DataListener<List<CatalogItem>> getDataListener) {
        NetworkManager.getTopTracks(new DataListener<TopTracksResponse>() {
            @Override
            public void onSuccess(TopTracksResponse data) {
                if (null != data && null != data.getTracks()) {
                    Collections.sort(data.getTracks());
                    topTracks = data;
                    processData(getDataListener);
                } else {
                    getDataListener.onError();
                }
            }

            @Override
            public void onError() {
                getDataListener.onError();
            }
        });
    }

    private synchronized void processData(final DataListener<List<CatalogItem>> getDataListener) {
        if (null != topArtists && null != topArtists.getArtistList() && null != topTracks) {
            checkCatalog();
            int artistPosition = 1;
            for (ArtistResponse artistResponse : topArtists.getArtistList()) {
                if (null != artistResponse && null != artistResponse.getArtist()) {
                    TopArtistTracks topArtistTracks = processArtistTrack(artistPosition, artistResponse);
                    artistPosition++;
                    catalogItems.addAll(topArtistTracks.getCatalogFormatList());
                }
            }
            getDataListener.onSuccess(catalogItems);
        } else {
            getDataListener.onError();
        }
    }

    private void checkCatalog() {
        if (null == catalogItems) {
            catalogItems = new ArrayList<>();
        } else {
            catalogItems.clear();
        }
    }

    @NonNull
    private TopArtistTracks processArtistTrack(int artistPosition, ArtistResponse artistResponse) {
        TopArtistTracks topArtistTracks = new TopArtistTracks(artistResponse.getArtist());
        topArtistTracks.setOwnTracks(topTracks.getTracks());
        topArtistTracks.setArtistPosition(artistPosition);
        return topArtistTracks;
    }

    public boolean isDataLoaded() {
        return null != catalogItems;
    }
}
