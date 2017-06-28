package com.indigo.hotgear.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.indigo.hotgear.R;
import com.indigo.hotgear.adapter.ShotsAdapter;
import com.indigo.hotgear.model.Shot;
import com.indigo.hotgear.network.Api;
import com.indigo.hotgear.network.Method;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.realm.Realm;

public class ShotsFragment extends Fragment implements Api.ResponseListener, ShotsAdapter.OnShotItemClicked {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ShotsAdapter shotsAdapter;
    private List<Shot> fetchedShots;
    private OnFragmentChangeListener listener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.shots_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_shot_list);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchShots();
            }
        });
        fetchShots();

        setUpRecyclerView(readFromRealm());

        return view;
    }


    private void fetchShots() {
        swipeRefreshLayout.setRefreshing(true);
        fetchedShots = new ArrayList<>();
        HashMap<String, String> params = new HashMap<>();
        params.put("per_page", "50");
        Api.getInstance().callMethod(Method.GET_SHOTS, params, this);
    }

    private List<Shot> readFromRealm() {
        return Realm.getDefaultInstance().where(Shot.class).equalTo("animated", false).findAll();
    }

    private void setUpRecyclerView(List<Shot> list) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        shotsAdapter = new ShotsAdapter(list, this);
        recyclerView.setAdapter(shotsAdapter);
    }

    @Override
    public void onSuccess(JSONArray object) throws JSONException {
        for (int i = 0; i < object.length(); i++) {
            Shot shot = Shot.fromJson(object.getJSONObject(i));
            if (!shot.isAnimated())
                fetchedShots.add(shot);
        }
        shotsAdapter.upDateItems(fetchedShots);

        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onError() {
        swipeRefreshLayout.setRefreshing(false);

        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentChangeListener) {
            listener = (OnFragmentChangeListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement OnShortItemClicked");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onShortItemClicked(long id) {
        listener.onFragmentChanged(PictureFragment.newInstance(id));
    }
}
