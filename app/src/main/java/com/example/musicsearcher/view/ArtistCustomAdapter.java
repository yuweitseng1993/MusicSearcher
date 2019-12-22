package com.example.musicsearcher.view;

import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicsearcher.R;
import com.example.musicsearcher.model.Artist;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArtistCustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private List<Artist> artistData;
    private Context context;

    public ArtistCustomAdapter(Context context){
        this.context = context;
    }

    public void setDataset(List<Artist> artistData){
        this.artistData = artistData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.music_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        final Artist tempArtist = artistData.get(position);
        holder.tvMusicName.setText(tempArtist.name);
        Picasso.get().load(tempArtist.image.get(2).text).into(holder.ivMusicImg);
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
                tvMoreInfoName.setText(Html.fromHtml("Artist name: <b>" + tempArtist.name + "<b>"));
                tvMoreInfoUrl.setText("Artist url: " + tempArtist.url);
                Picasso.get().load(tempArtist.image.get(2).text).into(ivMoreInfoImg);
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
        return artistData != null ? artistData.size() : 0;
    }
}
