package com.example.musicsearcher.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.musicsearcher.R;
import com.squareup.picasso.Picasso;

public class InfoDialogFragment extends DialogFragment {
    ImageView ivMoreInfoImg;
    TextView tvMoreInfoName, tvMoreInfoUrl, tvMoreInfoExtra;
    String name, url, extra, imgLink, dataType;

    public InfoDialogFragment(){}

    public static InfoDialogFragment newInstance(){
        InfoDialogFragment frag = new InfoDialogFragment();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        name = getArguments().getString("name");
        url = getArguments().getString("url");
        imgLink = getArguments().getString("imgLink");
        dataType = getArguments().getString("dataType");
        return inflater.inflate(R.layout.more_info_layout, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivMoreInfoImg = view.findViewById(R.id.fragment_img);
        tvMoreInfoName = view.findViewById(R.id.tv_frag_name);
        tvMoreInfoUrl = view.findViewById(R.id.tv_frag_url);
        if(dataType.equals("artist")){

        }
        else{
            tvMoreInfoExtra = view.findViewById(R.id.tv_frag_extra);
            extra = getArguments().getString("extra");
            tvMoreInfoName.setText(name);
            tvMoreInfoUrl.setText(url);
            tvMoreInfoExtra.setText(extra);
            tvMoreInfoExtra.setVisibility(View.VISIBLE);
            Picasso.get().load(imgLink).into(ivMoreInfoImg);
        }
    }
}
