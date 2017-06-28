package com.indigo.hotger.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.indigo.hotger.R;
import com.indigo.hotger.model.Shot;

import io.realm.Realm;

public class PictureFragment extends Fragment {
    private static String ARGUMENT_ID = "ID";
    private ImageView imageView;

    public static PictureFragment newInstance(long id) {

        Bundle args = new Bundle();
        args.putLong(ARGUMENT_ID, id);

        PictureFragment fragment = new PictureFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.full_image, container, false);
        imageView = (ImageView) view.findViewById(R.id.full_image);
        long id = getArguments().getLong(ARGUMENT_ID);

        Shot shot = Realm.getDefaultInstance().where(Shot.class).equalTo("id", id).findFirst();

        Glide.with(getActivity()).load(shot.getImageSize().getHighestSize()).into(imageView);

        return view;
    }
}

