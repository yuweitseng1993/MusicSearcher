package com.example.musicsearcher.view;

import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicsearcher.R;
import com.example.musicsearcher.model.Album;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AlbumCustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private static final String TAG = "AlbumCustomAdapter";
    private List<Album> albumData;
    private Context context;

    public AlbumCustomAdapter(Context context){
        this.context = context;
    }

    public void setDataset(List<Album> albumData){
        this.albumData = albumData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.music_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: position -> " + position);
        final Album tempAlbum = albumData.get(position);
        holder.tvMusicName.setText(tempAlbum.name);
        Picasso.get().load(tempAlbum.image.get(2).text).into(holder.ivMusicImg);
        holder.cvMusicCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.more_info_layout);
                ImageView ivDismissBtn = dialog.findViewById(R.id.iv_dismiss);
                ImageView ivMoreInfoImg = dialog.findViewById(R.id.fragment_img);
                TextView tvMoreInfoName = dialog.findViewById(R.id.tv_frag_name);
                TextView tvMoreInfoUrl = dialog.findViewById(R.id.tv_frag_url);
                TextView tvMoreInfoExtra = dialog.findViewById(R.id.tv_frag_extra);
                tvMoreInfoName.setText(Html.fromHtml("Album title: <b>" + tempAlbum.name + "<b>"));
                tvMoreInfoUrl.setText("Album url: " + tempAlbum.url);
                tvMoreInfoExtra.setText(Html.fromHtml("Artist: <b>" + tempAlbum.artist + "<b>"));
                tvMoreInfoExtra.setVisibility(View.VISIBLE);
                Picasso.get().load(tempAlbum.image.get(2).text).into(ivMoreInfoImg);
                dialog.show();

                ivDismissBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return albumData != null ? albumData.size() : 0;
    }
}
