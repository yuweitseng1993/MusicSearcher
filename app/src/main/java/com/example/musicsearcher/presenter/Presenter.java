package com.example.musicsearcher.presenter;

import android.util.Log;

import com.example.musicsearcher.model.AlbumMatch;
import com.example.musicsearcher.model.AlbumResultPojo;
import com.example.musicsearcher.model.ApiInterface;
import com.example.musicsearcher.model.ArtistMatch;
import com.example.musicsearcher.model.ArtistResultPojo;
import com.example.musicsearcher.model.TrackMatch;
import com.example.musicsearcher.model.TrackResultPojo;
import com.example.musicsearcher.view.ViewContract;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Presenter implements PresenterContract{
    private static final String TAG = "Presenter";
    private ViewContract view;
    private final String apiKey = "43134db347aa12fd54fa1577626bd067";
    private final String responseFormat = "json";

    @Override
    public void onBindView(ViewContract view) {
        this.view = view;
    }

    @Override
    public void unBind() {
        view = null;
    }

    @Override
    public void retrofitGetArtist(String artist) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ws.audioscrobbler.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        retrofit.create(ApiInterface.class).getArtist("artist.search", artist, apiKey, responseFormat)
                .enqueue(new Callback<ArtistResultPojo>() {
                    @Override
                    public void onResponse(Call<ArtistResultPojo> call, Response<ArtistResultPojo> response) {
                        if(response.isSuccessful()){
                            onArtistDataSuccess(response.body().results.artistmatches);
                        }
                    }

                    @Override
                    public void onFailure(Call<ArtistResultPojo> call, Throwable t) {
                        onArtistDataFailure(t.getMessage());
                    }
                });
    }

    @Override
    public void retrofitGetAlbum(String album) {
        Log.d(TAG, "retrofitGetAlbum: ");
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ws.audioscrobbler.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        retrofit.create(ApiInterface.class).getAlbum("album.search", album, apiKey, responseFormat)
                .enqueue(new Callback<AlbumResultPojo>() {
                    @Override
                    public void onResponse(Call<AlbumResultPojo> call, Response<AlbumResultPojo> response) {
                        if(response.isSuccessful()){
                            Log.d(TAG, "onResponse: got album info");
                            onAlbumDataSuccess(response.body().results.albummatches);
                        }
                    }

                    @Override
                    public void onFailure(Call<AlbumResultPojo> call, Throwable t) {
                        Log.d(TAG, "onFailure: failed to get album");
                        onAlbumDataFailure(t.getMessage());
                    }
                });
    }

    @Override
    public void retrofitGetTrack(String track) {
        Log.d(TAG, "retrofitGetTrack: ");
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ws.audioscrobbler.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        Log.d(TAG, "retrofitGetTrack: trackQuery -> " + track);
        retrofit.create(ApiInterface.class).getTrack("track.search", track, apiKey, responseFormat)
                .enqueue(new Callback<TrackResultPojo>() {
                    @Override
                    public void onResponse(Call<TrackResultPojo> call, Response<TrackResultPojo> response) {
                        if(response.isSuccessful()){
//                            Log.d(TAG, "onResponse: " + new GsonBuilder().setPrettyPrinting().create().toJson(response));
                            onTrackDataSuccess(response.body().results.trackmatches);
                        }
                    }

                    @Override
                    public void onFailure(Call<TrackResultPojo> call, Throwable t) {
                        onTrackDataFailure(t.getMessage());
                    }
                });
    }

    @Override
    public void onArtistDataSuccess(ArtistMatch artists) {
        view.getArtistData(artists);
    }

    @Override
    public void onArtistDataFailure(String errorMessage) {
        view.getFailureMessage(errorMessage);
    }

    @Override
    public void onAlbumDataSuccess(AlbumMatch albums) {
        view.getAlbumData(albums);
    }

    @Override
    public void onAlbumDataFailure(String errorMessage) {
        view.getFailureMessage(errorMessage);
    }

    @Override
    public void onTrackDataSuccess(TrackMatch tracks) {
        view.getTrackData(tracks);
    }

    @Override
    public void onTrackDataFailure(String errorMessage) {
        view.getFailureMessage(errorMessage);
    }
}
