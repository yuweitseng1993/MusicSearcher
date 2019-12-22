package com.example.musicsearcher.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    //http://ws.audioscrobbler.com/2.0/?method=artist.search&artist=ARTIST_QUERY&api_key=API_KEY&format=json
    @GET("2.0/?")
    Call<ArtistResultPojo> getArtist(
            @Query("method") String searchMethod,
            @Query("artist") String artistQuery,
            @Query("api_key") String apiKey,
            @Query("format") String responseFormat);

    //http://ws.audioscrobbler.com/2.0/?method=album.search&album=ALBUM_QUERY&api_key=API_KEY&format=json
    @GET("2.0/?")
    Call<AlbumResultPojo> getAlbum(
            @Query("method") String searchMethod,
            @Query("album") String albumQuery,
            @Query("api_key") String apiKey,
            @Query("format") String responseFormat);

    //http://ws.audioscrobbler.com/2.0/?method=track.search&track=TRACK_QUERY&api_key=API_KEY&format=json
    @GET("2.0/?")
    Call<TrackResultPojo> getTrack(
            @Query("method") String searchMethod,
            @Query("track") String trackQuery,
            @Query("api_key") String apiKey,
            @Query("format") String responseFormat);
}
