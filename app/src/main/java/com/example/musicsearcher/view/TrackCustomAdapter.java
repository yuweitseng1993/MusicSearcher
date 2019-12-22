package com.example.musicsearcher.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicsearcher.R;
import com.example.musicsearcher.model.Track;
import com.example.musicsearcher.model.TrackMatch;
import com.squareup.picasso.Picasso;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class TrackCustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private static final String TAG = "TrackCustomAdapter";
    private List<Track> trackData;
    private Context context;
    private MainActivity mainActivity;

    public TrackCustomAdapter(Context context){
        this.context = context;
    }

    public void setDataset(List<Track> trackData){
        this.trackData = trackData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.music_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        final Track tempTrack = trackData.get(position);
        holder.tvMusicName.setText(tempTrack.name);
        Picasso.get().load(tempTrack.image.get(2).text).into(holder.ivMusicImg);
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        holder.cvMusicCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setContentView(R.layout.more_info_layout);
                ImageView ivDismissBtn = dialog.findViewById(R.id.iv_dismiss);
                ImageView ivMoreInfoImg = dialog.findViewById(R.id.fragment_img);
                TextView tvMoreInfoName = dialog.findViewById(R.id.tv_frag_name);
                TextView tvMoreInfoUrl = dialog.findViewById(R.id.tv_frag_url);
                TextView tvMoreInfoExtra = dialog.findViewById(R.id.tv_frag_extra);
                tvMoreInfoName.setText(Html.fromHtml("Track title: <b>" + tempTrack.name + "<b>"));
                tvMoreInfoUrl.setText("Track url: " + tempTrack.url);
                tvMoreInfoExtra.setText(Html.fromHtml("Artist: <b>" + tempTrack.artist + "<b>"));
                tvMoreInfoExtra.setVisibility(View.VISIBLE);
                Picasso.get().load(tempTrack.image.get(2).text).into(ivMoreInfoImg);
                dialog.show();

                ivDismissBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        View myLayout = LayoutInflater.from(context).inflate(R.layout.activity_main,null);
                        SearchView searchBar = myLayout.findViewById(R.id.sv_search_bar);
                        searchBar.clearFocus();
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return trackData != null ? trackData.size() : 0;
    }

}
