package com.example.musicsearcher.presenter;

import com.example.musicsearcher.model.AlbumMatch;
import com.example.musicsearcher.model.ArtistMatch;
import com.example.musicsearcher.model.Track;
import com.example.musicsearcher.model.TrackMatch;
import com.example.musicsearcher.view.ViewContract;

public interface PresenterContract {
    void onBindView(ViewContract view);
    void unBind();
    void retrofitGetArtist(String artist);
    void retrofitGetAlbum(String album);
    void retrofitGetTrack(String track);
    void onArtistDataSuccess(ArtistMatch artist);
    void onArtistDataFailure(String errorMessage);
    void onAlbumDataSuccess(AlbumMatch album);
    void onAlbumDataFailure(String errorMessage);
    void onTrackDataSuccess(TrackMatch track);
    void onTrackDataFailure(String errorMessage);
}
