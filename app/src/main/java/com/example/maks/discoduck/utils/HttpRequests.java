package com.example.maks.discoduck.utils;

import com.example.maks.discoduck.api.response.TopArtistsResponse;
import com.example.maks.discoduck.api.response.TopTracksResponse;

import retrofit2.Call;
import retrofit2.http.GET;

interface HttpRequests {
    String API_KEY = "&apikey=564e20fdf305be9fdc27cfdfbe0db89c";
    String BASE_URL = "http://api.musixmatch.com/ws/1.1/";

    String COUNTRY_RU = "ru";
    int PAGE_SIZE = 100;

    @GET("chart.artists.get?page=1&page_size=" + PAGE_SIZE + "&country=" + COUNTRY_RU + API_KEY)
    Call<TopArtistsResponse> getTopArtists();

    @GET("chart.tracks.get?page=1&page_size=" + PAGE_SIZE + "&country=" + COUNTRY_RU + API_KEY)
    Call<TopTracksResponse> getTopTracks();
}
