package com.example.maks.discoduck.models;


import com.google.gson.annotations.SerializedName;

public class Artist implements CatalogItem {
    @SerializedName("artist_id")
    private long id;

    @SerializedName("artist_mbid")
    private String mbid;

    @SerializedName("artist_name")
    private String name;

    @SerializedName("artist_rating")
    private int rating;

    @SerializedName("updated_time")
    private String dataUpdate;

    private int position;

    @Override
    public int getType() {
        return CatalogItem.TYPE_ARTIST;
    }

    public long getId() {
        return id;
    }

    public String getMbid() {
        return mbid;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getDataUpdate() {
        return dataUpdate;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
