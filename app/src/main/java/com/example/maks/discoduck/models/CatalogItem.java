package com.example.maks.discoduck.models;


public interface CatalogItem {
    int TYPE_ARTIST = 100;
    int TYPE_TRACK = 101;

    int getType();
}
