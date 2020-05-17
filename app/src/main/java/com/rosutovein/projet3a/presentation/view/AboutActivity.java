package com.rosutovein.projet3a.presentation.view;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.rosutovein.projet3a.R;

public class AboutActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);

        Toolbar aboutToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(aboutToolbar);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
