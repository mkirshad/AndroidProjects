package com.androidprojects;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ProcessingActivity extends AppCompatActivity {

    public static Activity inProgressActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        inProgressActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing);
    }

    @Override
    public void onBackPressed() {
    }
}
