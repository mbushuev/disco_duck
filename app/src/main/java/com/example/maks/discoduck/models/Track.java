package com.example.maks.discoduck.models;


import com.google.gson.annotations.SerializedName;

public class Track implements CatalogItem {
    @SerializedName("track_id")
    private long trackId;

    @SerializedName("track_name")
    private String name;

    @SerializedName("track_rating")
    private int rating;

    @SerializedName("album_id")
    private long albumId;

    @SerializedName("album_name")
    private String albumName;

    @SerializedName("album_coverart_100x100")
    private String albumCover;

    @SerializedName("artist_id")
    private long artistId;

    @SerializedName("artist_name")
    private String artistName;

    @SerializedName("artist_mbid")
    private String artistMbid;
    private int position;

    public long getArtistId() {
        return artistId;
    }

    public long getTrackId() {
        return trackId;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public long getAlbumId() {
        return albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getAlbumCoverUrl() {
        return albumCover;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtistMbid() {
        return artistMbid;
    }

    @Override
    public int getType() {
        return CatalogItem.TYPE_TRACK;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
