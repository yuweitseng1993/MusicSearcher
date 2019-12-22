package com.example.musicsearcher.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.musicsearcher.R;
import com.example.musicsearcher.model.AlbumMatch;
import com.example.musicsearcher.model.ArtistMatch;
import com.example.musicsearcher.model.TrackMatch;
import com.example.musicsearcher.presenter.Presenter;

public class MainActivity extends AppCompatActivity implements ViewContract {
    private static final String TAG = "MainActivity";
    SearchView searchBar;
    RecyclerView artistRecyclerView, albumRecyclerView, trackRecyclerView;
    ArtistCustomAdapter artistCustomAdapter;
    AlbumCustomAdapter albumCustomAdater;
    TrackCustomAdapter trackCustomAdapter;
    TextView artistLabel, albumLabel, trackLabel;
    Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        onBindPresenter();

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(TAG, "onQueryTextSubmit: query -> " + query);
                initNetworkCall(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    @Override
    public void onBindPresenter() {
        presenter = new Presenter();
        presenter.onBindView(this);
    }

    @Override
    public void initUI() {
        searchBar = findViewById(R.id.sv_search_bar);
        searchBar.onActionViewExpanded();
        artistLabel = findViewById(R.id.tv_artist_label);
        albumLabel = findViewById(R.id.tv_album_label);
        trackLabel = findViewById(R.id.tv_track_label);

        SnapHelper helper = new LinearSnapHelper();
        artistRecyclerView = findViewById(R.id.rv_artist_list);
        artistRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        artistCustomAdapter = new ArtistCustomAdapter(this);
        helper.attachToRecyclerView(artistRecyclerView);
        artistRecyclerView.setAdapter(artistCustomAdapter);

        albumRecyclerView = findViewById(R.id.rv_album_list);
        albumRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        albumCustomAdater = new AlbumCustomAdapter(this);
        helper.attachToRecyclerView(albumRecyclerView);
        albumRecyclerView.setAdapter(albumCustomAdater);

        trackRecyclerView = findViewById(R.id.rv_track_list);
        trackRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        trackCustomAdapter = new TrackCustomAdapter(this);
        helper.attachToRecyclerView(trackRecyclerView);
        trackRecyclerView.setAdapter(trackCustomAdapter);
    }

    @Override
    public void initNetworkCall(String query) {
        presenter.retrofitGetArtist(query);
        presenter.retrofitGetAlbum(query);
        presenter.retrofitGetTrack(query);
    }

    @Override
    public void getArtistData(ArtistMatch artists) {
        artistCustomAdapter.setDataset(artists.artist);
        artistLabel.setVisibility(View.VISIBLE);
    }

    @Override
    public void getAlbumData(AlbumMatch albums) {
        albumCustomAdater.setDataset(albums.album);
        albumLabel.setVisibility(View.VISIBLE);
    }


    @Override
    public void getTrackData(TrackMatch tracks) {
        trackCustomAdapter.setDataset(tracks.track);
        trackLabel.setVisibility(View.VISIBLE);
    }

    @Override
    public void getFailureMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

}
