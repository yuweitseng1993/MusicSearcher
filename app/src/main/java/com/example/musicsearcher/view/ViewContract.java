package com.example.musicsearcher.view;

import com.example.musicsearcher.model.AlbumMatch;
import com.example.musicsearcher.model.ArtistMatch;
import com.example.musicsearcher.model.TrackMatch;

import java.util.List;

public interface ViewContract {
    void onBindPresenter();
    void initUI();
    void initNetworkCall(String query);
    void getArtistData(ArtistMatch artists);
    void getAlbumData(AlbumMatch albums);
    void getTrackData(TrackMatch tracks);
    void getFailureMessage(String errorMessage);
}
