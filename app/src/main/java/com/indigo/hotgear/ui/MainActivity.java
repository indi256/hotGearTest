package com.indigo.hotgear.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.indigo.hotgear.R;
import com.indigo.hotgear.adapter.ViewPagerAdapter;
import com.indigo.hotgear.network.Api;
import com.indigo.hotgear.network.Method;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements Api.ResponseListener {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        setUpViewPager();

        HashMap<String, String> params = new HashMap<>();
        params.put("per_page", "50");

        Api.getInstance().callMethod(Method.GET_SHOTS, params, this);

    }

    private void setUpViewPager() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ShotsFragment(), "Shot");
        viewPagerAdapter.addFragment(new PictureFragment(), "Full picture");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public void onSuccess(JSONObject object) throws JSONException {
        Log.d("MAIN", object.toString());
    }

    @Override
    public void onError() {

    }
}
