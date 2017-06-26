package com.indigo.hotgear.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.indigo.hotgear.R;

public class ShotsFragment extends Fragment {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shots_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rec_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        // TODO rv
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
