package com.indigo.hotgear.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class ShotsFragment extends Fragment implements Api.ResponseListener {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ShotsAdapter shotsAdapter;
    private List<Shot> fetchedShots;
    private List<Shot> realmShots;
    private Realm realm;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.shots_fragment, container, false);
        realm = Realm.getDefaultInstance();


        recyclerView = (RecyclerView) view.findViewById(R.id.rv_shot_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchShots();
            }
        });

        setUpRecyclerView(writeFromRealm(realm, realmShots));

        fetchShots();

        return view;
    }

    private void fetchShots() {
        HashMap<String, String> params = new HashMap<>();
        params.put("per_page", "50");
        Api.getInstance().callMethod(Method.GET_SHOTS, params, this);
    }

    private List<Shot> writeFromRealm(Realm realm, List<Shot> list) {
        list = realm.where(Shot.class).findAll();
        return list;
    }

    private void setUpRecyclerView(List<Shot> list) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        shotsAdapter = new ShotsAdapter(list);
        recyclerView.setAdapter(shotsAdapter);
    }

    @Override
    public void onSuccess(JSONArray object) throws JSONException {
        fetchedShots = new ArrayList<>();
        for (int i = 0; i < object.length(); i++) {
            fetchedShots.add(Shot.fromJson(object.getJSONObject(i)));
        }

        setUpRecyclerView(fetchedShots);

        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onError() {

    }

}
