package com.example.musicsearcher.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicsearcher.R;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    ImageView ivMusicImg;
    TextView tvMusicName;
    CardView cvMusicCard;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        cvMusicCard = itemView.findViewById(R.id.music_card);
        ivMusicImg = itemView.findViewById(R.id.iv_music_img);
        tvMusicName = itemView.findViewById(R.id.tv_music_name);
    }
}
