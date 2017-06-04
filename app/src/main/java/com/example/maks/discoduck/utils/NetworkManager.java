package com.example.maks.discoduck.utils;

import com.example.maks.discoduck.api.response.TopArtistsResponse;
import com.example.maks.discoduck.api.response.TopTracksResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


class NetworkManager {
    private static HttpRequests musicHttpRequests;

    private static OkHttpClient.Builder okHttpClient;
    private static Retrofit.Builder builder;

    private static void clearClient() {
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS);

        builder = new Retrofit.Builder()
                .baseUrl(HttpRequests.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
    }


    private static void createGetDataService() {
        musicHttpRequests = createService(HttpRequests.class);
    }

    private static <S> S createService(Class<S> serviceClass) {
        HttpLoggingInterceptor loggingHeader = new HttpLoggingInterceptor();
        loggingHeader.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        HttpLoggingInterceptor loggingBody = new HttpLoggingInterceptor();
        loggingBody.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient.addInterceptor(loggingHeader).addInterceptor(loggingBody);

        Retrofit retrofit = builder.client(okHttpClient.build()).build();
        return retrofit.create(serviceClass);
    }

    static private HttpRequests getHttpClient() {
        if (null == musicHttpRequests) {
            clearClient();
            createGetDataService();
        }
        return musicHttpRequests;
    }

    static void getTopArtists(DataListener<TopArtistsResponse> listener) {
        getHttpClient().getTopArtists().enqueue(new Callback<TopArtistsResponse>() {
            @Override
            public void onResponse(Call<TopArtistsResponse> call, Response<TopArtistsResponse> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<TopArtistsResponse> call, Throwable t) {
                listener.onError();
            }
        });
    }

    static void getTopTracks(DataListener<TopTracksResponse> listener) {
        getHttpClient().getTopTracks().enqueue(new Callback<TopTracksResponse>() {
            @Override
            public void onResponse(Call<TopTracksResponse> call, Response<TopTracksResponse> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<TopTracksResponse> call, Throwable t) {
                listener.onError();
            }
        });
    }
}