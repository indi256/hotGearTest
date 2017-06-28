package com.indigo.hotgear.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.indigo.hotgear.R;


public class MainActivity extends AppCompatActivity implements OnFragmentChangeListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setCurrentFragment(new ShotsFragment(), false);

    }

    private void setCurrentFragment(Fragment fragment, boolean withBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment, fragment.getClass().getSimpleName());
        if (withBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        fragmentTransaction.commit();

    }


    @Override
    public void onFragmentChanged(Fragment fragment) {
        setCurrentFragment(fragment, true);
    }


}
