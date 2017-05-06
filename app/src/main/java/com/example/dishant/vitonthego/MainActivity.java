package com.example.dishant.vitonthego;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationDrawer nav_draw=(NavigationDrawer) getSupportFragmentManager().findFragmentById(R.id.nav_draw);
        nav_draw.setUp(R.id.nav_draw, (DrawerLayout)findViewById(R.id.draw_layout), toolbar);
        frameLayout=(FrameLayout)findViewById(R.id.fragment_container);
    }
}
